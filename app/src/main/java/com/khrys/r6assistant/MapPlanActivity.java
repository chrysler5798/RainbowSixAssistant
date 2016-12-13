package com.khrys.r6assistant;

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
import java.util.ArrayList;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Louis on 06/12/2016.
 * <p>
 * RainbowSixAssistant
 */

public class MapPlanActivity extends AppCompatActivity
{

    int minfloor, maxfloor, currentfloorTxt, currentfloorImg, posdefault;
    ArrayList<Integer> posmaps = new ArrayList<>();

    Switch switchPosRef;
    MapSwitch mapSwitch;

    ImageView mImageView;
    TextView TextViewFloor;
    PhotoViewAttacher mAttacher;

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
        int mapID = getIntent().getIntExtra("pos", 0);

        mapSwitch = new MapSwitch(posmaps, mapID);

        switchPosRef = (Switch) findViewById(R.id.switchPos);

        mImageView = (ImageView) findViewById(R.id.imageViewPlan);

        TextView titleTextView = (TextView) findViewById(R.id.txtTitlePlan);
        TextViewFloor = (TextView) findViewById(R.id.textViewEtage);

        ImageButton ZoomResetBut = (ImageButton) findViewById(R.id.buttonZoomReset);
        ImageButton ZoomPlusBut = (ImageButton) findViewById(R.id.buttonZoomIn);
        ImageButton ZoomMinusBut = (ImageButton) findViewById(R.id.buttonZoomOut);

        ImageButton prevFloorBut = (ImageButton) findViewById(R.id.buttonMinusFloor);
        ImageButton nextFloorBut = (ImageButton) findViewById(R.id.buttonPlusFloor);

        mAttacher = new PhotoViewAttacher(mImageView);
        mAttacher.setMaximumScale(6.0F);

        ZoomMinusBut.setOnClickListener(new ClickListenerZoom(1, mAttacher));
        ZoomResetBut.setOnClickListener(new ClickListenerZoom(2, mAttacher));
        ZoomPlusBut.setOnClickListener(new ClickListenerZoom(3, mAttacher));

        titleTextView.setText(map);

        prevFloorBut.setOnClickListener(new ClickListenerMinusPlusFloor(1));
        nextFloorBut.setOnClickListener(new ClickListenerMinusPlusFloor(2));

        if(switchPosRef.isChecked())
        {
            posmaps = mapSwitch.SwitchPosPlans();
        }
        else
        {
            posmaps = mapSwitch.SwitchPosPlansWithPos();
        }

        switchPosRef.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                posmaps.clear();
                if(isChecked)
                {
                    posmaps = mapSwitch.SwitchPosPlans();
                }
                else
                {
                    posmaps = mapSwitch.SwitchPosPlansWithPos();
                }
                setupVarAndImageAndFloor();
            }
        });

        setupVarAndImageAndFloor();
    }

    void setupVarAndImageAndFloor()
    {
        minfloor = posmaps.get(0);
        maxfloor = posmaps.get(1);
        posdefault = posmaps.get(2);
        currentfloorImg = 2;
        currentfloorTxt = minfloor;

        new SetImage(posdefault,0).execute();
        TextViewFloor.setText(SwitchFloorTxt(minfloor));
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

    class ClickListenerMinusPlusFloor implements View.OnClickListener
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
                if(currentfloorTxt != minfloor)
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
                if(currentfloorTxt != maxfloor)
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
        Bitmap imageBM;
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
            imageBM = BitmapFactory.decodeResource(getResources(), redId);
            return imageBM;
        }

        @Override
        protected void onPostExecute(Bitmap result)
        {
            if(type == 0)
            {
                mImageView.setImageBitmap(result);
                mAttacher.update();
            }
            else
            {
                mImageView.setImageBitmap(result);
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
