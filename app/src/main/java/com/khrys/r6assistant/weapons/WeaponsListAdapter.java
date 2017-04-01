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

import ca.barrenechea.widget.recyclerview.decoration.StickyHeaderAdapter;

class WeaponsListAdapter extends RecyclerView.Adapter<WeaponsListAdapter.MyViewHolder> implements StickyHeaderAdapter<WeaponsListAdapter.HeaderHolder>
{
    private List<Integer> weaponsimg;
    private List<String> weaponstxt;
    private int type;
    private LayoutInflater inflater;

    WeaponsListAdapter(int type, ArrayList<Integer> weaponsimg, ArrayList<String> weaponstxt)
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
    public WeaponsListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_weapons, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WeaponsListAdapter.MyViewHolder holder, int position)
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

    @Override
    public long getHeaderId(int position) {
        if(position<15){
            return 0;
        }else if(position<29){
            return 15;
        }else if(position<32){
            return 29;
        }else if(position<34){
            return 32;
        }else if(position<38){
            return 34;
        }else if(position<49){
            return 38;
        }else if(position<62){
            return 49;
        }else{
            return 62;
        }
    }

    @Override
    public HeaderHolder onCreateHeaderViewHolder(ViewGroup parent) {
        final View view = inflater.inflate(R.layout.header, parent, false);
        return new HeaderHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(HeaderHolder viewholder, int position) {
        String txt = "";
        if(position<15){
            txt = "Assault Rifle";
        }else if(position<29){
            txt = "Pistols";
        }else if(position<32){
            txt = "Light Machine Gun";
        }else if(position<34){
            txt = "Machine Pistol";
        }else if(position<38){
            txt = "Marksman Rifle";
        }else if(position<49){
            txt = "Shotgun";
        }else if(position<62){
            txt = "Submachine Gun";
        }
        viewholder.header.setText(txt);
    }

    static class HeaderHolder extends RecyclerView.ViewHolder {
        TextView header;

        HeaderHolder(View itemView) {
            super(itemView);

            header = (TextView) itemView;
        }
    }
}