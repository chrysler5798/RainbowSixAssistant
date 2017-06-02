package com.khrys.r6assistant.weapons;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.khrys.r6assistant.R;

import java.util.List;

/*
 * Created by Chrysler on 3/16/2017.
 * RainbowSixAssistant
*/

class OperatorsListExpandableAdapter extends ExpandableRecyclerAdapter<OperatorsViewHolder, WeaponsViewHolder>
{

    private LayoutInflater mInflater;
    OperatorsListExpandableAdapter(Context context, List<ParentObject> parentItemList)
    {
        super(context, parentItemList);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public OperatorsViewHolder onCreateParentViewHolder(ViewGroup viewGroup)
    {
        View view = mInflater.inflate(R.layout.list_operators, viewGroup, false);
        return new OperatorsViewHolder(view);
    }

    @Override
    public WeaponsViewHolder onCreateChildViewHolder(ViewGroup viewGroup)
    {
        View view = mInflater.inflate(R.layout.list_weapons_child, viewGroup, false);
        return new WeaponsViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(OperatorsViewHolder operatorsViewHolder, int i, Object o)
    {
        Operators operators = (Operators) o;
        operatorsViewHolder.mCrimeTitleTextView.setText(operators.getName());
        operatorsViewHolder.imageView.setImageResource(operators.getImage());
    }

    @Override
    public void onBindChildViewHolder(WeaponsViewHolder weaponsViewHolder, int i, Object o)
    {
        Weapons weapons = (Weapons) o;
        weaponsViewHolder.imgArme.setImageResource(weapons.getImg());
        weaponsViewHolder.nomArme.setText(weapons.getNom());
    }
}

class OperatorsViewHolder extends ParentViewHolder
{

    ImageView imageView;
    TextView mCrimeTitleTextView;

    OperatorsViewHolder(View itemView)
    {
        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.imageVarme);
        mCrimeTitleTextView = (TextView) itemView.findViewById(R.id.textVarme);
        ImageButton mParentDropDownArrow = (ImageButton) itemView.findViewById(R.id.parent_list_item_expand_arrow);
    }
}

class WeaponsViewHolder extends ChildViewHolder
{

    ImageView imgArme;
    TextView nomArme;

    WeaponsViewHolder(final View itemView) {
        super(itemView);

        imgArme = (ImageView) itemView.findViewById(R.id.imageVarme);
        nomArme = (TextView) itemView.findViewById(R.id.textVarme);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = itemView.getContext();
                Intent newWeapon = new Intent(context, WeaponActivity.class);
                newWeapon.putExtra("arme", nomArme.getText().toString());
                context.startActivity(newWeapon);
            }
        });
    }
}