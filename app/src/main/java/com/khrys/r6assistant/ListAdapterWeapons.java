package com.khrys.r6assistant;

/*
 * Created by Chrysler on 3/8/2017.
 * RainbowSixAssistant
*/

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class ListAdapterWeapons extends RecyclerView.Adapter<ListAdapterWeapons.ViewHolder>
{

    public static class ViewHolder extends RecyclerView.ViewHolder
    {

        public ViewHolder(View itemView)
        {
            super(itemView);
        }
    }

    @Override
    public ListAdapterWeapons.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return null;
    }

    @Override
    public void onBindViewHolder(ListAdapterWeapons.ViewHolder holder, int position)
    {

    }

    @Override
    public int getItemCount()
    {
        return 0;
    }

}