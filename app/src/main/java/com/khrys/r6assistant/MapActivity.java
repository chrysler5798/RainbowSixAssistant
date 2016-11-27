package com.khrys.r6assistant;

import android.app.ActionBar;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;
import java.util.ArrayList;


/**
 * Created by Chrysler on 10/3/2016.
 * <p>
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

        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        map = getIntent().getStringExtra("nommap");
        int mapID = getIntent().getIntExtra("pos", 0);

        ArrayList<Integer> pics = new ArrayList<>();
        ArrayList<Integer> poscam = new ArrayList<>();

        switch (mapID)
        {
            case 0:
                pics.add(R.drawable.bank_cam_1);
                pics.add(R.drawable.bank_cam_2);
                pics.add(R.drawable.bank_cam_3);
                pics.add(R.drawable.bank_cam_4);
                pics.add(R.drawable.bank_cam_5);
                pics.add(R.drawable.bank_cam_6);
                pics.add(R.drawable.bank_cam_7);

                poscam.add(R.string.bank_cam_s1);
                poscam.add(R.string.bank_cam_s2);
                poscam.add(R.string.bank_cam_s3);
                poscam.add(R.string.bank_cam_s4);
                poscam.add(R.string.bank_cam_s5);
                poscam.add(R.string.bank_cam_s6);
                poscam.add(R.string.bank_cam_s7);
                break;

            case 1:
                pics.add(R.drawable.chalet_cam_1);
                pics.add(R.drawable.chalet_cam_2);
                pics.add(R.drawable.chalet_cam_3);
                pics.add(R.drawable.chalet_cam_4);
                pics.add(R.drawable.chalet_cam_5);
                pics.add(R.drawable.chalet_cam_6);

                poscam.add(R.string.chalet_cam_s1);
                poscam.add(R.string.chalet_cam_s1);
                poscam.add(R.string.chalet_cam_s1);
                poscam.add(R.string.chalet_cam_s1);
                poscam.add(R.string.chalet_cam_s1);
                poscam.add(R.string.chalet_cam_s1);
                break;

            case 2:
                pics.add(R.drawable.clubhouse_cam_1);
                pics.add(R.drawable.clubhouse_cam_2);
                pics.add(R.drawable.clubhouse_cam_3);
                pics.add(R.drawable.clubhouse_cam_4);
                pics.add(R.drawable.clubhouse_cam_5);
                pics.add(R.drawable.clubhouse_cam_6);
                pics.add(R.drawable.clubhouse_cam_7);

                poscam.add(R.string.clubhouse_cam_s1);
                poscam.add(R.string.clubhouse_cam_s2);
                poscam.add(R.string.clubhouse_cam_s3);
                poscam.add(R.string.clubhouse_cam_s4);
                poscam.add(R.string.clubhouse_cam_s5);
                poscam.add(R.string.clubhouse_cam_s6);
                poscam.add(R.string.clubhouse_cam_s7);
                break;

            case 3:
                pics.add(R.drawable.consulate_cam_1);
                pics.add(R.drawable.consulate_cam_2);
                pics.add(R.drawable.consulate_cam_3);
                pics.add(R.drawable.consulate_cam_4);
                pics.add(R.drawable.consulate_cam_5);
                pics.add(R.drawable.consulate_cam_6);
                pics.add(R.drawable.consulate_cam_7);
                pics.add(R.drawable.consulate_cam_8);

                poscam.add(R.string.consulate_cam_s1);
                poscam.add(R.string.consulate_cam_s2);
                poscam.add(R.string.consulate_cam_s3);
                poscam.add(R.string.consulate_cam_s4);
                poscam.add(R.string.consulate_cam_s5);
                poscam.add(R.string.consulate_cam_s6);
                poscam.add(R.string.consulate_cam_s7);
                poscam.add(R.string.consulate_cam_s8);
                break;

            case 4:
                pics.add(R.drawable.hereford_cam_1);
                pics.add(R.drawable.hereford_cam_2);
                pics.add(R.drawable.hereford_cam_3);
                pics.add(R.drawable.hereford_cam_4);
                pics.add(R.drawable.hereford_cam_5);
                pics.add(R.drawable.hereford_cam_6);

                poscam.add(R.string.hereford_cam_s1);
                poscam.add(R.string.hereford_cam_s2);
                poscam.add(R.string.hereford_cam_s3);
                poscam.add(R.string.hereford_cam_s4);
                poscam.add(R.string.hereford_cam_s5);
                poscam.add(R.string.hereford_cam_s6);
                break;

            case 5:
                pics.add(R.drawable.house_cam_1);
                pics.add(R.drawable.house_cam_2);
                pics.add(R.drawable.house_cam_3);
                pics.add(R.drawable.house_cam_4);
                pics.add(R.drawable.house_cam_5);
                pics.add(R.drawable.house_cam_6);

                poscam.add(R.string.house_cam_s1);
                poscam.add(R.string.house_cam_s2);
                poscam.add(R.string.house_cam_s3);
                poscam.add(R.string.house_cam_s4);
                poscam.add(R.string.house_cam_s5);
                poscam.add(R.string.house_cam_s6);
                break;

            case 6:
                pics.add(R.drawable.kafedostoyevsky_cam_1);
                pics.add(R.drawable.kafedostoyevsky_cam_2);
                pics.add(R.drawable.kafedostoyevsky_cam_3);
                pics.add(R.drawable.kafedostoyevsky_cam_4);
                pics.add(R.drawable.kafedostoyevsky_cam_5);
                pics.add(R.drawable.kafedostoyevsky_cam_6);

                poscam.add(R.string.kafedostoyevsky_cam_s1);
                poscam.add(R.string.kafedostoyevsky_cam_s2);
                poscam.add(R.string.kafedostoyevsky_cam_s3);
                poscam.add(R.string.kafedostoyevsky_cam_s4);
                poscam.add(R.string.kafedostoyevsky_cam_s5);
                poscam.add(R.string.kafedostoyevsky_cam_s6);
                break;

            case 7:
                pics.add(R.drawable.kanal_cam_1);
                pics.add(R.drawable.kanal_cam_2);
                pics.add(R.drawable.kanal_cam_3);
                pics.add(R.drawable.kanal_cam_4);
                pics.add(R.drawable.kanal_cam_5);
                pics.add(R.drawable.kanal_cam_6);
                pics.add(R.drawable.kanal_cam_7);

                poscam.add(R.string.kanal_cam_s1);
                poscam.add(R.string.kanal_cam_s2);
                poscam.add(R.string.kanal_cam_s3);
                poscam.add(R.string.kanal_cam_s4);
                poscam.add(R.string.kanal_cam_s5);
                poscam.add(R.string.kanal_cam_s6);
                poscam.add(R.string.kanal_cam_s7);
                break;

            case 8:
                pics.add(R.drawable.oregon_cam_1);
                pics.add(R.drawable.oregon_cam_2);
                pics.add(R.drawable.oregon_cam_3);
                pics.add(R.drawable.oregon_cam_4);
                pics.add(R.drawable.oregon_cam_5);
                pics.add(R.drawable.oregon_cam_6);
                pics.add(R.drawable.oregon_cam_7);

                poscam.add(R.string.oregon_cam_s1);
                poscam.add(R.string.oregon_cam_s2);
                poscam.add(R.string.oregon_cam_s3);
                poscam.add(R.string.oregon_cam_s4);
                poscam.add(R.string.oregon_cam_s5);
                poscam.add(R.string.oregon_cam_s6);
                poscam.add(R.string.oregon_cam_s7);
                break;

            case 9:
                pics.add(R.drawable.plane_cam_1);
                pics.add(R.drawable.plane_cam_2);
                pics.add(R.drawable.plane_cam_3);
                pics.add(R.drawable.plane_cam_4);
                pics.add(R.drawable.plane_cam_5);

                poscam.add(R.string.plane_cam_s1);
                poscam.add(R.string.plane_cam_s2);
                poscam.add(R.string.plane_cam_s3);
                poscam.add(R.string.plane_cam_s4);
                poscam.add(R.string.plane_cam_s5);
                break;

            case 10:
                pics.add(R.drawable.favela_cam_1);
                pics.add(R.drawable.favela_cam_2);
                pics.add(R.drawable.favela_cam_3);
                pics.add(R.drawable.favela_cam_4);
                pics.add(R.drawable.favela_cam_5);
                pics.add(R.drawable.favela_cam_6);
                pics.add(R.drawable.favela_cam_7);

                poscam.add(R.string.favela_cam_s1);
                poscam.add(R.string.favela_cam_s2);
                poscam.add(R.string.favela_cam_s3);
                poscam.add(R.string.favela_cam_s4);
                poscam.add(R.string.favela_cam_s5);
                poscam.add(R.string.favela_cam_s6);
                poscam.add(R.string.favela_cam_s7);
                break;

            case 11:
                pics.add(R.drawable.border_cam_1);
                pics.add(R.drawable.border_cam_2);
                pics.add(R.drawable.border_cam_3);
                pics.add(R.drawable.border_cam_4);
                pics.add(R.drawable.border_cam_5);
                pics.add(R.drawable.border_cam_6);
                pics.add(R.drawable.border_cam_7);

                poscam.add(R.string.border_cam_s1);
                poscam.add(R.string.border_cam_s2);
                poscam.add(R.string.border_cam_s3);
                poscam.add(R.string.border_cam_s4);
                poscam.add(R.string.border_cam_s5);
                poscam.add(R.string.border_cam_s6);
                poscam.add(R.string.border_cam_s7);
                break;

            case 12:
                pics.add(R.drawable.yacht_cam_1);
                pics.add(R.drawable.yacht_cam_2);
                pics.add(R.drawable.yacht_cam_3);
                pics.add(R.drawable.yacht_cam_4);
                pics.add(R.drawable.yacht_cam_5);
                pics.add(R.drawable.yacht_cam_6);
                pics.add(R.drawable.yacht_cam_7);
                pics.add(R.drawable.yacht_cam_8);

                poscam.add(R.string.yacht_cam_s1);
                poscam.add(R.string.yacht_cam_s2);
                poscam.add(R.string.yacht_cam_s3);
                poscam.add(R.string.yacht_cam_s4);
                poscam.add(R.string.yacht_cam_s5);
                poscam.add(R.string.yacht_cam_s6);
                poscam.add(R.string.yacht_cam_s7);
                poscam.add(R.string.yacht_cam_s8);
                break;

            case 13:
                pics.add(R.drawable.skyscraper_cam_1);
                pics.add(R.drawable.skyscraper_cam_2);
                pics.add(R.drawable.skyscraper_cam_3);
                pics.add(R.drawable.skyscraper_cam_4);
                pics.add(R.drawable.skyscraper_cam_5);
                pics.add(R.drawable.skyscraper_cam_6);
                pics.add(R.drawable.skyscraper_cam_7);

                poscam.add(R.string.skyscraper_cam_s1);
                poscam.add(R.string.skyscraper_cam_s2);
                poscam.add(R.string.skyscraper_cam_s3);
                poscam.add(R.string.skyscraper_cam_s4);
                poscam.add(R.string.skyscraper_cam_s5);
                poscam.add(R.string.skyscraper_cam_s6);
                poscam.add(R.string.skyscraper_cam_s7);
                break;

        }

        rv = (RecyclerView) findViewById(R.id.recyclerViewMap);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            LayoutM = new LinearLayoutManager(MapActivity.this, LinearLayoutManager.HORIZONTAL, false);
        } else {
            LayoutM = new LinearLayoutManager(MapActivity.this);
        }
        rv.setAdapter(new ListAdapterMap(pics,poscam, type));
        rv.setLayoutManager(LayoutM);

        TextView txtMap = (TextView) findViewById(R.id.textViewMapName);
        txtMap.setText(map);

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
