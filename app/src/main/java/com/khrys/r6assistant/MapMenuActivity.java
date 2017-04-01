package com.khrys.r6assistant;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.MenuItem;

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

        GridLayoutManager lLayout;

        setContentView(R.layout.activity_mapmenu);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        int requesttype = getIntent().getIntExtra("request",0);

        List<Pair<Integer, Integer>> maps = new ArrayList<>();
        String[] allmaps = getResources().getStringArray(R.array.maps);

        for(int i = 0; i < allmaps.length; i++)
        {
            String drawName = "thunmap_"+allmaps[i];
            int idName = getResources().getIdentifier(allmaps[i], "string", getApplicationContext().getPackageName());
            int idDraw = getResources().getIdentifier(drawName, "drawable", getApplicationContext().getPackageName());
            maps.add(i, Pair.create(idName,idDraw));

        }

        lLayout = new GridLayoutManager(MapMenuActivity.this, 2);
        RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerView);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(lLayout);
        rv.setAdapter(new ListAdapterMapMenu(requesttype, maps));
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