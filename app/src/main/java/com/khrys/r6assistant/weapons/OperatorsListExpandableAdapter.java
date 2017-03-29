package com.khrys.r6assistant.weapons;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.khrys.r6assistant.R;

import java.util.List;

import ca.barrenechea.widget.recyclerview.decoration.StickyHeaderAdapter;
import ca.barrenechea.widget.recyclerview.decoration.StickyHeaderDecoration;

/*
 * Created by Chrysler on 3/16/2017.
 * RainbowSixAssistant
*/

public class OperatorsListExpandableAdapter extends ExpandableRecyclerAdapter<OperatorsViewHolder, WeaponsViewHolder> implements StickyHeaderAdapter<OperatorsListExpandableAdapter.HeaderHolder>
{

    LayoutInflater mInflater;

    public OperatorsListExpandableAdapter(Context context, List<ParentObject> parentItemList) {
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

    @Override
    public long getHeaderId(int position) {
        if (position == 0) { // don't show header for first item
            return StickyHeaderDecoration.NO_HEADER_ID;
        }
        return (long) position / 7;
    }

    @Override
    public HeaderHolder onCreateHeaderViewHolder(ViewGroup parent) {
        final View view = mInflater.inflate(R.layout.header, parent, false);
        return new HeaderHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(HeaderHolder viewholder, int position) {
        viewholder.header.setText("Header " + getHeaderId(position));
    }

    static class HeaderHolder extends RecyclerView.ViewHolder {
        public TextView header;

        public HeaderHolder(View itemView) {
            super(itemView);

            header = (TextView) itemView;
        }
    }
}