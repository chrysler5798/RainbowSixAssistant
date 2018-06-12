package com.khrys.r6assistant.operators;

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

public class OperatorHeaderListAdapter extends RecyclerView.Adapter<OperatorHeaderListAdapter.MyViewHolder> implements StickyHeaderAdapter<OperatorHeaderListAdapter.HeaderHolder>
{
    private ArrayList<Operator> operators;
    private LayoutInflater inflater;

    public OperatorHeaderListAdapter(ArrayList<Operator> operators)
    {
        this.operators = operators;
    }

    @Override
    public int getItemCount()
    {
        return operators.size();
    }

    @Override
    public OperatorHeaderListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_weapons, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OperatorHeaderListAdapter.MyViewHolder holder, int position)
    {
        Integer imgdata = operators.get(position).getImg();
        String txtdata = operators.get(position).getNom();
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
                    Intent newAct = new Intent(context, OperatorActivity.class);
                    newAct.putExtra("position", getAdapterPosition());
                    newAct.putExtra("operator", operators.get(getAdapterPosition()).getId());
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
        if(position<20){
            return 0;
        }else if(position<40){
            return 20;
        }else {
            return 0;
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
        if(position<20){
            txtId = R.string.attackers;
        }else if(position<40){
            txtId = R.string.defenders;
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