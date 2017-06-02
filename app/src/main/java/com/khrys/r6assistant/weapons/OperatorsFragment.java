package com.khrys.r6assistant.weapons;

/*
 * Created by Louis on 30/12/2016.
 * RainbowSixAssistant
*/

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.khrys.r6assistant.R;

import java.util.ArrayList;
import java.util.List;

public class OperatorsFragment extends Fragment
{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_recycler, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.WeaponsRecycler);

        OperatorsListExpandableAdapter mOperatorsListExpandableAdapter = new OperatorsListExpandableAdapter(getContext(), generateOperatorsList());
        mOperatorsListExpandableAdapter.setCustomParentAnimationViewId(R.id.parent_list_item_expand_arrow);
        mOperatorsListExpandableAdapter.setParentClickableViewAnimationDefaultDuration();
        mOperatorsListExpandableAdapter.setParentAndIconExpandOnClick(true);

        recyclerView.setAdapter(mOperatorsListExpandableAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private ArrayList<ParentObject> generateOperatorsList()
    {
        List<Operators> operatorsList = new ArrayList<>();

        TypedArray tableOps = getResources().obtainTypedArray(R.array.operators);
        for(int i = 0; i < tableOps.length(); i++)
        {
            String op = tableOps.getString(i);
            String img = "o_"+op;

            img = img.replace('ä','a');
            img = img.replace('ã','a');

            int imgid = getResources().getIdentifier(img, "drawable", getContext().getPackageName());

            operatorsList.add(new Operators(op, imgid));
        }
        tableOps.recycle();

        ArrayList<ParentObject> parentObjects = new ArrayList<>();
        for (Operators operators : operatorsList) {
            String op = operators.getName();
            op = op.replace('ä','a');
            op = op.replace('ã','a');
            int arrayId = getResources().getIdentifier(op, "array", getContext().getPackageName());
            String[] opWeapon = getResources().getStringArray(arrayId);

            ArrayList<Object> childList = new ArrayList<>();

            for (int j = 1; j < opWeapon.length; j++)
            {
                String arme = opWeapon[j];

                String imgArmeId = "g_" + arme;
                imgArmeId = imgArmeId.replace('-', '_');
                imgArmeId = imgArmeId.replace(' ', '_');

                int resID = getResources().getIdentifier(imgArmeId, "drawable", getContext().getPackageName());

                childList.add(new Weapons(resID, arme));
            }
            operators.setChildObjectList(childList);
            parentObjects.add(operators);
        }
        return parentObjects;
    }
}