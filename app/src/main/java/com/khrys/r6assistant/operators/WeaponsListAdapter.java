package com.khrys.r6assistant.operators;
/*
 * Created by Louis on 01/06/2017.
 * 
 * R6Assistant
*/

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.khrys.r6assistant.R;
import com.khrys.r6assistant.weapons.WeaponActivity;

import java.util.ArrayList;

class WeaponsListAdapter extends RecyclerView.Adapter<WeaponsListAdapter.MyViewHolder>
{

    private ArrayList<String> txtWeap;
    private ArrayList<Integer> imgWeap;

    WeaponsListAdapter(ArrayList<String> txtWeap, ArrayList<Integer> imgWeap)
    {
        super();
        this.txtWeap = txtWeap;
        this.imgWeap = imgWeap;
    }

    @Override
    public WeaponsListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new MyViewHolder(inflater.inflate(R.layout.list_weapons_child, parent, false));
    }

    @Override
    public void onBindViewHolder(WeaponsListAdapter.MyViewHolder holder, int position)
    {
        String txtW = txtWeap.get(position);
        Integer imgW = imgWeap.get(position);

        holder.display(txtW, imgW);
    }

    @Override
    public int getItemCount()
    {
        return txtWeap.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView txtV = (TextView) itemView.findViewById(R.id.textVarme);
        ImageView imgV = (ImageView) itemView.findViewById(R.id.imageVarme);

        MyViewHolder(final View itemView)
        {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Context context = itemView.getContext();
                    Intent newWeapon = new Intent(context, WeaponActivity.class);
                    newWeapon.putExtra("arme", txtV.getText().toString());
                    context.startActivity(newWeapon);
                }
            });
        }

        void display(String txt, Integer img)
        {
            txtV.setText(txt);
            imgV.setImageResource(img);
        }
    }
}
