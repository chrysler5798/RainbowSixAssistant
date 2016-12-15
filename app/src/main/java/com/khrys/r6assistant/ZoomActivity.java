package com.khrys.r6assistant;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Chrysler on 10/23/2016.
 * <p>
 * RainbowSixPartner
 */

public class ZoomActivity extends AppCompatActivity
{

    ImageView image;
    PhotoViewAttacher mAttacher;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        image = (ImageView) findViewById(R.id.imageViewZoom);
        mAttacher = new PhotoViewAttacher(image);

        int idimg = getIntent().getIntExtra("imgid",0);

        image.setImageResource(idimg);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_zoom, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        float Scale = mAttacher.getScale();
        switch (item.getItemId())
        {
            case android.R.id.home:

                this.finish();
                return true;

            case R.id.action_zoomout:
                if(Scale > 1F)
                {
                    mAttacher.setScale(Scale-1.0F);
                }
                return true;

            case R.id.action_zoomreset:
                mAttacher.update();
                return true;

            case R.id.action_zoomin:
                if(Scale < 4F)
                {
                    mAttacher.setScale(Scale+1.0F);
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
