package com.khrys.r6assistant;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.MenuItem;

import com.khrys.r6assistant.data.LoadData;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by Chrysler on 11/27/2016.
 * RainbowSixPartner
*/

public class MapMenuActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mapmenu);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        int requesttype = getIntent().getIntExtra("request",0);
        GridLayoutManager gridLayout = new GridLayoutManager(MapMenuActivity.this, 2);
        RecyclerView rv = findViewById(R.id.recyclerView);
        LoadData dataLoader = new LoadData();
        JSONObject mapsData = new LoadData().loadData(this, dataLoader.RES_MAPS);
        int mapsNumber = mapsData.length();
        //new LoadData().loadDataFromRes(this, R.raw.maps).getJSONObject("maps").getJSONObject("0").get("name")

        List<Pair<Integer, Integer>> maps = new ArrayList<>();

        for(int i = 0; i < mapsNumber; i++)
        {
            String stringMapName = "map_name_"+i;
            String drawMapName   = "thum_map_"+i;

            int idName = getResources().getIdentifier(stringMapName, "string", getApplicationContext().getPackageName());
            int idDraw = getResources().getIdentifier(drawMapName, "drawable", getApplicationContext().getPackageName());

            maps.add(i, Pair.create(idName,idDraw));
        }

        rv.setHasFixedSize(true);
        rv.setLayoutManager(gridLayout);
        rv.setAdapter(new ListAdapterMapMenu(requesttype, maps));
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case android.R.id.home:

                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}