package com.khrys.r6assistant;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
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

    ImageView mImageView;
    TextView titleTextView, TextViewFloor;
    PhotoViewAttacher mAttacher;
    ImageButton ZoomPlusBut, ZoomMinusBut, ZoomResetBut, prevFloorBut, nextFloorBut;
    ArrayList<Integer> posmaps = new ArrayList<>();
    int minfloor, maxfloor, currentfloorTxt, currentfloorImg = 2, posdefault;

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

        MapSwitch mapSwitch = new MapSwitch(posmaps, mapID);
        posmaps = mapSwitch.SwitchPosPlans();

        minfloor = posmaps.get(0);
        maxfloor = posmaps.get(1);
        posdefault = posmaps.get(2);
        currentfloorTxt = minfloor;

        mImageView = (ImageView) findViewById(R.id.imageViewPlan);

        titleTextView = (TextView) findViewById(R.id.txtTitlePlan);
        TextViewFloor = (TextView) findViewById(R.id.textViewEtage);

        ZoomResetBut = (ImageButton) findViewById(R.id.buttonZoomReset);
        ZoomPlusBut = (ImageButton) findViewById(R.id.buttonZoomIn);
        ZoomMinusBut = (ImageButton) findViewById(R.id.buttonZoomOut);

        prevFloorBut = (ImageButton) findViewById(R.id.buttonMinusFloor);
        nextFloorBut = (ImageButton) findViewById(R.id.buttonPlusFloor);

        mAttacher = new PhotoViewAttacher(mImageView);

        ZoomMinusBut.setOnClickListener(new ClickListenerZoom(1, mAttacher));
        ZoomResetBut.setOnClickListener(new ClickListenerZoom(2, mAttacher));
        ZoomPlusBut.setOnClickListener(new ClickListenerZoom(3, mAttacher));

        titleTextView.setText(map);

        setupImageAndFloor();

        prevFloorBut.setOnClickListener(new ClickListenerMinusPlusFloor(1));
        nextFloorBut.setOnClickListener(new ClickListenerMinusPlusFloor(2));
    }

    void setupImageAndFloor()
    {
        mImageView.setImageResource(posdefault);
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
        }
        return "";
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
            mImageView.setImageResource(posmaps.get(currentfloorImg));
        }
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:

                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
