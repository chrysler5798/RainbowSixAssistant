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

import com.khrys.r6assistant.R;

import java.util.ArrayList;

import ca.barrenechea.widget.recyclerview.decoration.StickyHeaderAdapter;

public class WeaponsHeaderListAdapter extends RecyclerView.Adapter<WeaponsHeaderListAdapter.MyViewHolder> implements StickyHeaderAdapter<WeaponsHeaderListAdapter.HeaderHolder>
{
    private ArrayList<Weapon> weapons;
    private LayoutInflater inflater;

    WeaponsHeaderListAdapter(ArrayList<Weapon> weapons)
    {
        this.weapons = weapons;
    }

    @Override
    public int getItemCount()
    {
        return weapons.size();
    }

    @Override
    public WeaponsHeaderListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_weapons, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WeaponsHeaderListAdapter.MyViewHolder holder, int position)
    {
        Integer imgdata = weapons.get(position).getImageId();
        String txtdata = weapons.get(position).getName();
        holder.display(imgdata,txtdata);
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        private final ImageView imageV;
        private final TextView textV;
        private final Context context;

        MyViewHolder(final View itemView)
        {
            super(itemView);

            imageV = itemView.findViewById(R.id.imageVarme);
            textV = itemView.findViewById(R.id.textVarme);
            context = itemView.getContext();
            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Intent newAct = new Intent(context, WeaponActivity.class);
                    newAct.putExtra("weaponId", weapons.get(getAdapterPosition()).getId());
                    context.startActivity(newAct);
                }
            });
        }

        void display(Integer imgdata, String txtdata)
        {
            imageV.setImageResource(imgdata);
            textV.setText(txtdata);
        }
    }

    @Override
    public long getHeaderId(int position)
    {
        /*        Compte
                Assault : 18
                Pistols : 18
                LMG : 6
                SMG : 4
                Sniper : 5
                Shotgun : 16
                Submachine : 17
                TOTAL : 84
         */
        if(position<18){
            return 0;
        }else if(position<36){
            return 18;
        }else if(position<42){
            return 36;
        }else if(position<46){
            return 42;
        }else if(position<51){
            return 46;
        }else if(position<67){
            return 51;
        }else if(position<84){
            return 67;
        }else{
            return 84;
        }
    }

    @Override
    public HeaderHolder onCreateHeaderViewHolder(ViewGroup parent)
    {
        final View view = inflater.inflate(R.layout.header, parent, false);
        return new HeaderHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(HeaderHolder viewholder, int position)
    {
        int txtId = 0;
        if(position<18){
            txtId = R.string.assault;
        }else if(position<34){
            txtId = R.string.pistol;
        }else if(position<39){
            txtId = R.string.lightmachine;
        }else if(position<43){
            txtId = R.string.machinepistol;
        }else if(position<48){
            txtId = R.string.marksmanrifle;
        }else if(position<63){
            txtId = R.string.shotgun;
        }else if(position<79){
            txtId = R.string.submachinegun;
        }
        viewholder.header.setText(txtId);
    }

    class HeaderHolder extends RecyclerView.ViewHolder
    {
        TextView header;

        HeaderHolder(View itemView)
        {
            super(itemView);

            header = (TextView) itemView;
        }
    }
}