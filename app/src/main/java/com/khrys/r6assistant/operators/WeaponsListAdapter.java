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
import com.khrys.r6assistant.weapons.Weapon;
import com.khrys.r6assistant.weapons.WeaponActivity;

import java.util.ArrayList;

class WeaponsListAdapter extends RecyclerView.Adapter<WeaponsListAdapter.MyViewHolder>
{

    private ArrayList<Weapon> weapons;

    WeaponsListAdapter(ArrayList<Weapon> weapons)
    {
        this.weapons = weapons;
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
        holder.txtV.setText(weapons.get(position).getName());
        holder.imgV.setImageResource(weapons.get(position).getImageId());
    }

    @Override
    public int getItemCount()
    {
        return weapons.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView txtV = (TextView) itemView.findViewById(R.id.textVarme);
        ImageView imgV = (ImageView) itemView.findViewById(R.id.imageVarme);
        Context context;

        MyViewHolder(final View itemView)
        {
            super(itemView);

            this.context = itemView.getContext();

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Intent newWeapon = new Intent(context, WeaponActivity.class);
                    newWeapon.putExtra("weaponId", weapons.get(getAdapterPosition()).getId());
                    context.startActivity(newWeapon);
                }
            });
        }
    }
}
