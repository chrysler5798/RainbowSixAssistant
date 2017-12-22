package com.khrys.r6assistant.operators;
/*
 * Created by Khrys.
 *
 * App : RainbowSixAssistant
 * Info : 05/31/2017[00:00 PM]
*/

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.khrys.r6assistant.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CountryFragment extends Fragment
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
        RecyclerView recyclerView = view.findViewById(R.id.WeaponsRecycler);

        CountryListExpandableAdapter mOperatorsListExpandableAdapter = new CountryListExpandableAdapter(getContext(), generateOperatorsList(view));
        mOperatorsListExpandableAdapter.setCustomParentAnimationViewId(R.id.parent_list_item_expand_arrow);
        mOperatorsListExpandableAdapter.setParentClickableViewAnimationDefaultDuration();
        mOperatorsListExpandableAdapter.setParentAndIconExpandOnClick(true);

        recyclerView.setAdapter(mOperatorsListExpandableAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private ArrayList<ParentObject> generateOperatorsList(View view)
    {
        List<Country> countriesList = new ArrayList<>();

        TypedArray tableCtries = getResources().obtainTypedArray(R.array.countriesArmy);
        for (int i = 1; i < tableCtries.length(); i += 2) {
            String ct = tableCtries.getString(i);
            String img = "flag_" + tableCtries.getString(i - 1);

            int imgid = getResources().getIdentifier(img, "drawable", view.getContext().getPackageName());

            countriesList.add(new Country(ct, imgid));
        }
        tableCtries.recycle();

        ArrayList<ParentObject> parentObjects = new ArrayList<>();

        for (Country country : countriesList)
        {
            String ct = country.getName();
            ct = ct.replace(' ', '_');
            ct = ct.replace('.', '_');
            ct = ct.toLowerCase(Locale.ENGLISH);
            if(ct.equals("707th"))
            {
                ct = "m707th";
            }
            int arrayId = getResources().getIdentifier(ct, "array", view.getContext().getPackageName());
            String[] ctOp = getResources().getStringArray(arrayId);

            ArrayList<Object> childList = new ArrayList<>();

            for (String operators : ctOp)
            {
                String imgArmeId = "o_" + operators;
                imgArmeId = imgArmeId.replace('ä','a');
                imgArmeId = imgArmeId.replace('ã','a');

                int resID = getResources().getIdentifier(imgArmeId, "drawable", view.getContext().getPackageName());

                childList.add(new Operator(resID, operators));
            }
            country.setChildObjectList(childList);
            parentObjects.add(country);
        }
        return parentObjects;
    }
}