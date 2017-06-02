package com.khrys.r6assistant.operators;
/*
 * Created by Louis on 31/05/2017.
 * 
 * R6Assistant
*/

import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.khrys.r6assistant.R;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class OperatorMenuActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menufragment);

        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        FragmentPagerAdapter fragmentAdapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("By operators", OperatorFragment.class)
                .add("By countries", CountryFragment.class)
                .create());

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(fragmentAdapter);

        SmartTabLayout tabLayout = (SmartTabLayout) findViewById(R.id.viewpagertab);
        tabLayout.setViewPager(viewPager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case android.R.id.home:
                this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}