package com.khrys.rainbowsixpartner;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;

/**
 * Created by Chrysler on 10/23/2016.
 * <p>
 * RainbowSixPartner
 */

public class ZoomActivity extends AppCompatActivity{

    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        image = (ImageView) findViewById(R.id.imageViewZoom);

        int idimg = getIntent().getIntExtra("imgid",0);

        image.setImageResource(idimg);
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
