package com.khrys.r6assistant;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.khrys.r6assistant.data.LoadData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import uk.co.senab.photoview.PhotoViewAttacher;

/*
 * Created by Louis on 06/12/2016.
 * RainbowSixAssistant
*/

public class MapPlanActivity extends AppCompatActivity
{

    int minEtage, maxEtage, nbEtage, currentfloorTxt, currentfloorImg, posdefault;
    ArrayList<Integer> posmaps = new ArrayList<>();

    Switch switchPosRef;

    ImageView imageView;
    TextView TextViewFloor;
    PhotoViewAttacher mAttacher;
    int mapID;
    String[] infoMap;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mapplan);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        String map = getIntent().getStringExtra("nommap");
        mapID = getIntent().getIntExtra("pos", 0);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            setTitle(getResources().getString(R.string.app_name) + " - " + map);
        }
        else
        {
            TextView titleTextView = (TextView) findViewById(R.id.txtTitlePlan);
            titleTextView.setText(map);
        }

        switchPosRef = (Switch) findViewById(R.id.switchPos);

        imageView = (ImageView) findViewById(R.id.imageViewPlan);

        TextViewFloor = (TextView) findViewById(R.id.textViewEtage);

        ImageButton ZoomResetBut = (ImageButton) findViewById(R.id.buttonZoomReset);
        ImageButton ZoomPlusBut = (ImageButton) findViewById(R.id.buttonZoomIn);
        ImageButton ZoomMinusBut = (ImageButton) findViewById(R.id.buttonZoomOut);

        ImageButton prevFloorBut = (ImageButton) findViewById(R.id.buttonMinusFloor);
        ImageButton nextFloorBut = (ImageButton) findViewById(R.id.buttonPlusFloor);

        mAttacher = new PhotoViewAttacher(imageView);
        mAttacher.setMaximumScale(6.0F);

        ZoomMinusBut.setOnClickListener(new ClickListenerZoom(1, mAttacher));
        ZoomResetBut.setOnClickListener(new ClickListenerZoom(2, mAttacher));
        ZoomPlusBut.setOnClickListener(new ClickListenerZoom(3, mAttacher));



        prevFloorBut.setOnClickListener(new ClickListenerMinusPlusFloor(1));
        nextFloorBut.setOnClickListener(new ClickListenerMinusPlusFloor(2));

        LoadData dataLoader = new LoadData();
        JSONObject mapsData;
        try
        {
            mapsData = dataLoader.loadData(this, dataLoader.RES_MAPS).getJSONObject(String.valueOf(mapID));
            minEtage = Integer.parseInt(String.valueOf(mapsData.getInt("minfloor")));
            maxEtage = Integer.parseInt(String.valueOf(mapsData.getInt("maxfloor")));
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        nbEtage = maxEtage-minEtage;

        initPlans(mapID,switchPosRef.isChecked());

        switchPosRef.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                posmaps.clear();
                initPlans(mapID,isChecked);
                setupVarAndImageAndFloor();
            }
        });

        setupVarAndImageAndFloor();
    }

    void initPlans(int idMap, boolean posOn)
    {
        String ifPos = "";
        if(posOn)
        {
            ifPos = "_pos";
        }
        String[] prefix = {"_1", "f0", "f1", "f2", "f3"};
        for (int i = minEtage+1; i < maxEtage+2; i++)
        {
            String idPlan = "plan_"+idMap+"_"+prefix[i]+ifPos;
            posmaps.add(getResources().getIdentifier(idPlan, "drawable", getApplicationContext().getPackageName()));
        }
    }

    void setupVarAndImageAndFloor()
    {
        posdefault = posmaps.get(0);
        currentfloorImg = 0;
        currentfloorTxt = minEtage;

        new SetImage(posdefault,0).execute();
        TextViewFloor.setText(SwitchFloorTxt(minEtage));
    }

    String SwitchFloorTxt(int floor)
    {
        switch(floor)
        {
            case -1:
                return getResources().getString(R.string.f_base);

            case 0:
                return getResources().getString(R.string.f_0);

            case 1:
                return getResources().getString(R.string.f_1);

            case 2:
                return getResources().getString(R.string.f_2);

            case 3:
                return getResources().getString(R.string.f_3);

            default:
                return "";
        }
    }

    private class ClickListenerMinusPlusFloor implements View.OnClickListener
    {
        int requesttype;

        ClickListenerMinusPlusFloor(int requesttype)
        {
            this.requesttype = requesttype;
        }

        @Override
        public void onClick(View view)
        {
            if(requesttype == 1)
            {
                if(currentfloorTxt != minEtage)
                {
                    currentfloorTxt -=1;
                    currentfloorImg -=1;
                    SetTextAndImg();

                }
                else
                {
                    Toast.makeText(getApplicationContext(), R.string.error_floor_below, Toast.LENGTH_SHORT).show();
                }
            }
            else if (requesttype == 2)
            {
                if(currentfloorTxt != maxEtage)
                {
                    currentfloorTxt +=1;
                    currentfloorImg +=1;
                    SetTextAndImg();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),  R.string.error_floor_above, Toast.LENGTH_SHORT).show();
                }
            }
        }

        void SetTextAndImg()
        {
            TextViewFloor.setText(SwitchFloorTxt(currentfloorTxt));
            new SetImage(posmaps.get(currentfloorImg),1).execute();
        }
    }

    private class SetImage extends AsyncTask<Bitmap, Void, Bitmap>
    {
        int redId;
        int type;

        SetImage(int redId, int type)
        {
            this.redId = redId;
            this.type = type;
        }

        @Override
        protected Bitmap doInBackground(Bitmap... thumb)
        {
            return BitmapFactory.decodeResource(getResources(), redId);
        }

        @Override
        protected void onPostExecute(Bitmap result)
        {
            if(type == 0)
            {
                imageView.setImageBitmap(result);
                mAttacher.update();
            }
            else
            {
                imageView.setImageBitmap(result);
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:

                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}