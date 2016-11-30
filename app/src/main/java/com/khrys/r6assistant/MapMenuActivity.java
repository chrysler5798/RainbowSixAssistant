package com.khrys.r6assistant;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

/**
 * Created by Chrysler on 11/27/2016.
 * <p>
 * RainbowSixPartner
 */

public class MapMenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mapmenu);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        int requesttype = getIntent().getIntExtra("request",0);

        RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerView);
        LayoutIfManager layoutIM = new LayoutIfManager(getApplicationContext());
        rv.setLayoutManager(layoutIM.ReturnManagerChange());
        rv.setAdapter(new ListAdapterMapMenu(requesttype));
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
