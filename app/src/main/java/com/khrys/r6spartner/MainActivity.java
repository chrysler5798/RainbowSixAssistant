package com.khrys.r6spartner;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerView);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        } else {
            rv.setLayoutManager(new LinearLayoutManager(this));
        }
        rv.setAdapter(new ListAdapterMapMenu());

    }
}