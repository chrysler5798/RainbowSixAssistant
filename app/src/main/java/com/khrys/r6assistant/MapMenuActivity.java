package com.khrys.r6assistant;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

/**
 * Created by Chrysler on 11/27/2016.
 * <p>
 * RainbowSixPartner
 */

public class MapMenuActivity extends AppCompatActivity
{
    private GridLayoutManager lLayout;

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


        lLayout = new GridLayoutManager(MapMenuActivity.this, 2);
        RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerView);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(lLayout);
//        LayoutIfManager layoutIM = new LayoutIfManager(getApplicationContext());
        rv.setLayoutManager(lLayout);
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
