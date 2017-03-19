package com.khrys.r6assistant.weapons;

/*
 * Created by Chrysler on 3/8/2017.
 * RainbowSixAssistant
*/

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.khrys.r6assistant.*;

import java.util.ArrayList;
import java.util.List;

class ListAdapterWeapons extends RecyclerView.Adapter<ListAdapterWeapons.MyViewHolder>
{
    private List<Integer> weaponsimg;
    private List<String> weaponstxt;
    private int type;

    ListAdapterWeapons(int type, ArrayList<Integer> weaponsimg, ArrayList<String> weaponstxt)
    {
        this.weaponsimg = weaponsimg;
        this.weaponstxt = weaponstxt;
        this.type = type;
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
            holder.display(type,imgdata,txtdata);
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        private final ImageView imageV;
        private final TextView textV;
        private final Context context;
        private int type;

        MyViewHolder(final View itemView)
        {
            super(itemView);

            imageV = (ImageView) itemView.findViewById(R.id.imageVarme);
            textV = (TextView) itemView.findViewById(R.id.textVarme);
            context = itemView.getContext();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(type == 1)
                    {
                        Intent newWeapon = new Intent(context, WeaponActivity.class);
                        newWeapon.putExtra("arme", textV.getText().toString());
                        context.startActivity(newWeapon);
                    }
                }
            });

        }

        void display(int type, Integer imgdata, String txtdata)
        {
            imageV.setImageResource(imgdata);
            textV.setText(txtdata);
            this.type = type;
        }
    }
}