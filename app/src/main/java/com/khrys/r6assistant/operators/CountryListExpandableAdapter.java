package com.khrys.r6assistant.operators;
/*
 * Created by Louis on 31/05/2017.
 * 
 * R6Assistant
*/

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.khrys.r6assistant.R;

import java.util.List;

class CountryListExpandableAdapter extends ExpandableRecyclerAdapter<OperatorsViewHolder, WeaponsViewHolder>
{

    private LayoutInflater mInflater;
    CountryListExpandableAdapter(Context context, List<ParentObject> parentItemList)
    {
        super(context, parentItemList);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public OperatorsViewHolder onCreateParentViewHolder(ViewGroup viewGroup)
    {
        View view = mInflater.inflate(R.layout.list_countries, viewGroup, false);
        return new OperatorsViewHolder(view);
    }

    @Override
    public WeaponsViewHolder onCreateChildViewHolder(ViewGroup viewGroup)
    {
        View view = mInflater.inflate(R.layout.list_weapons_child, viewGroup, false);
        return new WeaponsViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(OperatorsViewHolder operatorsViewHolder, int i, Object c)
    {
        Army countries = (Army) c;
        operatorsViewHolder.mCrimeTitleTextView.setText(countries.getName());
        operatorsViewHolder.imageView.setImageResource(countries.getImage());
    }

    @Override
    public void onBindChildViewHolder(WeaponsViewHolder weaponsViewHolder, int i, Object c)
    {
        final Operator operator = (Operator) c;

        weaponsViewHolder.imgOperator.setImageResource(operator.getImageId());
        weaponsViewHolder.imgOperator.setPadding(20,20,20,20);
        weaponsViewHolder.nomOperator.setText(operator.getName());
        weaponsViewHolder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Context context = view.getContext();
                Intent newOperator = new Intent(context, OperatorActivity.class);
                newOperator.putExtra("operatorId", operator.getId());
                context.startActivity(newOperator);
            }
        });
    }
}

class OperatorsViewHolder extends ParentViewHolder
{
    ImageView imageView;
    TextView mCrimeTitleTextView;

    OperatorsViewHolder(View itemView)
    {
        super(itemView);

        imageView = itemView.findViewById(R.id.imageVarme);
        mCrimeTitleTextView = itemView.findViewById(R.id.textVarme);
    }
}

class WeaponsViewHolder extends ChildViewHolder
{
    ImageView imgOperator;
    TextView nomOperator;

    WeaponsViewHolder(View itemView)
    {
        super(itemView);

        imgOperator = itemView.findViewById(R.id.imageVarme);
        nomOperator = itemView.findViewById(R.id.textVarme);
    }
}