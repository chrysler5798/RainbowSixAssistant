package com.khrys.r6assistant;

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

import com.khrys.r6assistant.operators.Operator;
import com.khrys.r6assistant.operators.OperatorActivity;
import com.khrys.r6assistant.weapons.WeaponActivity;

import java.util.ArrayList;
import java.util.List;

import ca.barrenechea.widget.recyclerview.decoration.StickyHeaderAdapter;

public class HeaderListAdapter extends RecyclerView.Adapter<HeaderListAdapter.MyViewHolder> implements StickyHeaderAdapter<HeaderListAdapter.HeaderHolder>
{
    private List<Integer> img;
    private List<String> txt;
    private LayoutInflater inflater;

    public HeaderListAdapter(ArrayList<Integer> img, ArrayList<String> txt)
    {
        this.img = img;
        this.txt = txt;
    }

    @Override
    public int getItemCount()
    {
        return txt.size();
    }

    private boolean isWeapon()
    {
        return getItemCount() > 60;
    }

    @Override
    public HeaderListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_weapons, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HeaderListAdapter.MyViewHolder holder, int position)
    {
        if(txt != null)
        {
            Integer imgdata = img.get(position);
            String txtdata = txt.get(position);
            holder.display(imgdata,txtdata);
        }
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
                    String typeExtra;
                    Intent newAct;

                    if(isWeapon())
                    {
                        newAct = new Intent(context, WeaponActivity.class);
                        typeExtra = "arme";
                    }
                    else
                    {
                        newAct = new Intent(context, OperatorActivity.class);
                        newAct.putExtra("position", getAdapterPosition());
                        typeExtra = "operator";
                    }

                    newAct.putExtra(typeExtra, textV.getText().toString());
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
                Assault : 16
                Pistols : 16
                LMG : 5
                SMG : 4
                Sniper : 5
                Shotgun : 15
                Submachine : 16
                TOTAL : 77
         */
        if(isWeapon())
        {
            if(position<16){
                return 0;
            }else if(position<32){
                return 16;
            }else if(position<37){
                return 32;
            }else if(position<41){
                return 37;
            }else if(position<46){
                return 41;
            }else if(position<61){
                return 46;
            }else if(position<77){
                return 61;
            }else{
                return 77;
            }
        }
        else
        {
            if(position<18){
                return 0;
            }else if(position<36){
                return 18;
            }else {
                return 0;
            }
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
        if(isWeapon())
        {
            if(position<16){
                txtId = R.string.assault;
            }else if(position<32){
                txtId = R.string.pistol;
            }else if(position<37){
                txtId = R.string.lightmachine;
            }else if(position<41){
                txtId = R.string.machinepistol;
            }else if(position<46){
                txtId = R.string.marksmanrifle;
            }else if(position<61){
                txtId = R.string.shotgun;
            }else if(position<77){
                txtId = R.string.submachinegun;
            }
        }
        else
        {
            if(position<18){
                txtId = R.string.attackers;
            }else if(position<36){
                txtId = R.string.defenders;
            }
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