package com.khrys.r6assistant;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;
import java.util.ArrayList;


/*
 * Created by Chrysler on 10/3/2016.
 * RainbowSixPartner
*/

public class MapActivity extends AppCompatActivity
{
    int type = 1;

    String map;

    RecyclerView rv;

    LinearLayoutManager LayoutM;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        map = getIntent().getStringExtra("nommap");
        int mapID = getIntent().getIntExtra("pos", 0);

        ArrayList<Integer> pics = new ArrayList<>();
        ArrayList<Integer> poscam = new ArrayList<>();

        MapSwitch mapSwitch = new MapSwitch(pics, poscam, mapID);
        poscam = mapSwitch.SwitchPos();
        pics = mapSwitch.SwitcherPics();

        rv = (RecyclerView) findViewById(R.id.recyclerViewMap);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            LayoutM = new LinearLayoutManager(MapActivity.this, LinearLayoutManager.HORIZONTAL, false);
            String txtTitle = String.format(getResources().getString(R.string.app_name)+" - %s",map);
            setTitle(txtTitle);
        } else {
            LayoutM = new LinearLayoutManager(MapActivity.this);
            TextView txtMap = (TextView) findViewById(R.id.textViewMapName);
            txtMap.setText(map);
        }
        rv.setAdapter(new ListAdapterMap(pics,poscam, type));
        rv.setLayoutManager(LayoutM);


        TextView txtNbCam = (TextView) findViewById(R.id.textViewNbCam);
        String txtCam = String.format(getResources().getString(R.string.cameraPhrase), pics.size());
        txtNbCam.setText(txtCam);

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
