package com.khrys.r6assistant.weapons;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.khrys.r6assistant.R;

import java.util.List;

/*
 * Created by Chrysler on 3/16/2017.
 * RainbowSixAssistant
*/

class OperatorsListExpandableAdapter extends ExpandableRecyclerAdapter<OperatorsViewHolder, WeaponsViewHolder>
{

    private LayoutInflater mInflater;
    OperatorsListExpandableAdapter(Context context, List<ParentObject> parentItemList) {
        super(context, parentItemList);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public OperatorsViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
        View view = mInflater.inflate(R.layout.list_operators, viewGroup, false);
        return new OperatorsViewHolder(view);
    }

    @Override
    public WeaponsViewHolder onCreateChildViewHolder(ViewGroup viewGroup) {
        View view = mInflater.inflate(R.layout.list_weapons_child, viewGroup, false);
        return new WeaponsViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(OperatorsViewHolder operatorsViewHolder, int i, Object o) {
        Operators operators = (Operators) o;
        operatorsViewHolder.mCrimeTitleTextView.setText(operators.getName());
        operatorsViewHolder.imageView.setImageResource(operators.getImage());
    }

    @Override
    public void onBindChildViewHolder(WeaponsViewHolder weaponsViewHolder, int i, Object o) {
        Weapons weapons = (Weapons) o;
        weaponsViewHolder.imgArme.setImageResource(weapons.getImg());
        weaponsViewHolder.nomArme.setText(weapons.getNom());
    }
}