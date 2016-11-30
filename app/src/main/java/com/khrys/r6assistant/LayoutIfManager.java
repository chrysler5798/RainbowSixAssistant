package com.khrys.r6assistant;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Chrysler on 11/30/2016.
 * <p>
 * RainbowSixPartner
 */

class LayoutIfManager
{

    private Context thiscontext;
    private RecyclerView.LayoutManager thislayout;

    LayoutIfManager(Context context)
    {
        thiscontext = context;
    }

    RecyclerView.LayoutManager ReturnManagerChange()
    {
        if(thiscontext.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            thislayout = new LinearLayoutManager(thiscontext, LinearLayoutManager.HORIZONTAL, false);
        } else
        {
            thislayout = new LinearLayoutManager(thiscontext);
        }
        return thislayout;
    }

    RecyclerView.LayoutManager ReturnManager()
    {
        thislayout = new LinearLayoutManager(thiscontext, LinearLayoutManager.HORIZONTAL, false);
        return thislayout;
    }
}