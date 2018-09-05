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
import java.util.Map;

import ca.barrenechea.widget.recyclerview.decoration.StickyHeaderAdapter;

public class WeaponsHeaderListAdapter extends RecyclerView.Adapter<WeaponsHeaderListAdapter.MyViewHolder> implements StickyHeaderAdapter<WeaponsHeaderListAdapter.HeaderHolder>
{
    private ArrayList<Weapon> weapons;
    private Map<String, Integer> weaponsCount;
    private LayoutInflater inflater;

    WeaponsHeaderListAdapter(ArrayList<Weapon> weapons, Map<String, Integer> weaponsCount)
    {
        this.weapons = weapons;
        this.weaponsCount = weaponsCount;
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
        Integer lastCount = 0;
        Integer weaponTypeCount = 0;
        for (Map.Entry<String, Integer> entry : weaponsCount.entrySet())
        {
            weaponTypeCount += entry.getValue();
            if(position < weaponTypeCount) {
                return lastCount;
            }

            lastCount = weaponTypeCount;
        }

        return 0;
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
        Context viewHolderContext = viewholder.header.getContext();
        Integer weaponTypeCount = 0;
        for (Map.Entry<String, Integer> entry : weaponsCount.entrySet())
        {
            String weaponType = entry.getKey();
            weaponTypeCount += entry.getValue();

            if(position < weaponTypeCount) {
                int weaponTypeStrIdentifier = viewHolderContext.getResources().getIdentifier("weapontype_" + weaponType, "string", viewHolderContext.getPackageName());
                viewholder.header.setText(viewHolderContext.getString(weaponTypeStrIdentifier));
                break;
            }
        }
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