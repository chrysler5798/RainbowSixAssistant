package com.khrys.r6assistant;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.khrys.r6assistant.data.LoadData;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

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

        ArrayList<Map> maps = new ArrayList<>();
        LoadData dataLoader = new LoadData();
        try
        {
            JSONArray mapsList = dataLoader.loadData(this, dataLoader.RES_MAPS).getJSONArray("maps");
            for(int i = 0; i < mapsList.length(); i++)
            {
                String mapId = mapsList.getString(i);
                String stringMapName = "map_name_" + mapId;
                String drawMapName   = "map_img_" + mapId;

                int idName = getResources().getIdentifier(stringMapName, "string", getApplicationContext().getPackageName());
                int idImg = getResources().getIdentifier(drawMapName, "drawable", getApplicationContext().getPackageName());

                maps.add(new Map(mapId, getString(idName), idName, idImg));
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
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