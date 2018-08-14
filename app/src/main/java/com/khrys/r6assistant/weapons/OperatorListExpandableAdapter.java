package com.khrys.r6assistant.weapons;

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

/*
 * Created by Khrys.
 *
 * App : RainbowSixAssistant
 * Info : 3/16/2017 []
*/

class OperatorListExpandableAdapter extends ExpandableRecyclerAdapter<OperatorViewHolder, WeaponViewHolder>
{
    private LayoutInflater mInflater;
    OperatorListExpandableAdapter(Context context, List<ParentObject> parentItemList)
    {
        super(context, parentItemList);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public OperatorViewHolder onCreateParentViewHolder(ViewGroup viewGroup)
    {
        View view = mInflater.inflate(R.layout.list_operators, viewGroup, false);
        return new OperatorViewHolder(view);
    }

    @Override
    public WeaponViewHolder onCreateChildViewHolder(ViewGroup viewGroup)
    {
        View view = mInflater.inflate(R.layout.list_weapons_child, viewGroup, false);
        return new WeaponViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(OperatorViewHolder operatorViewHolder, int i, Object o)
    {
        Operator operators = (Operator) o;
        operatorViewHolder.mCrimeTitleTextView.setText(operators.getName());
        operatorViewHolder.imageView.setImageResource(operators.getImage());
    }

    @Override
    public void onBindChildViewHolder(WeaponViewHolder weaponViewHolder, int i, Object o)
    {
        final Weapon weapon = (Weapon) o;

        weaponViewHolder.imgArme.setImageResource(weapon.getImageId());
        weaponViewHolder.nomArme.setText(weapon.getName());

        weaponViewHolder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Context context = view.getContext();
                Intent newWeapon = new Intent(context, WeaponActivity.class);
                newWeapon.putExtra("weaponId", weapon.getId());
                context.startActivity(newWeapon);
            }
        });
    }
}

class OperatorViewHolder extends ParentViewHolder
{

    ImageView imageView;
    TextView mCrimeTitleTextView;

    OperatorViewHolder(View itemView)
    {
        super(itemView);

        imageView = itemView.findViewById(R.id.imageVarme);
        mCrimeTitleTextView = itemView.findViewById(R.id.textVarme);
    }
}

class WeaponViewHolder extends ChildViewHolder
{
    ImageView imgArme;
    TextView nomArme;

    WeaponViewHolder(View itemView) {
        super(itemView);

        imgArme = itemView.findViewById(R.id.imageVarme);
        nomArme = itemView.findViewById(R.id.textVarme);
    }
}