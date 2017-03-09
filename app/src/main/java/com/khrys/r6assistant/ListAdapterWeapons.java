package com.khrys.r6assistant;

/*
 * Created by Chrysler on 3/8/2017.
 * RainbowSixAssistant
*/

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class ListAdapterWeapons extends RecyclerView.Adapter<ListAdapterWeapons.MyViewHolder>
{
    private List<Integer> weaponsimg;
    private List<String> weaponstxt;

    ListAdapterWeapons(ArrayList<Integer> weaponsimg,ArrayList<String> weaponstxt)
    {
        this.weaponsimg = weaponsimg;
        this.weaponstxt = weaponstxt;
    }

    @Override
    public int getItemCount()
    {
        return weaponstxt.size();
    }

    @Override
    public ListAdapterWeapons.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_weapons, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListAdapterWeapons.MyViewHolder holder, int position)
    {
        if(weaponstxt != null)
        {
            Integer imgdata = weaponsimg.get(position);
            String txtdata = weaponstxt.get(position);
            holder.display(imgdata,txtdata);
        }
    }

    static class MyViewHolder extends RecyclerView.ViewHolder
    {
        private final ImageView imageV;
        private final TextView textV;

        MyViewHolder(View itemView)
        {
            super(itemView);

            imageV = (ImageView) itemView.findViewById(R.id.imageVarme);
            textV = (TextView) itemView.findViewById(R.id.textVarme);

        }

        void display(Integer imgdata, String txtdata)
        {
            imageV.setImageResource(imgdata);
            textV.setText(txtdata);
        }
    }

}