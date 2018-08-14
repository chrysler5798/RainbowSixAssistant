package com.khrys.r6assistant.operators;
/*
 * Created by Khrys.
 *
 * App : RainbowSixAssistant
 * Info : 05/31/2017 [00:00 PM]
*/

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.khrys.r6assistant.R;
import com.khrys.r6assistant.data.LoadData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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

        CountryListExpandableAdapter mOperatorsListExpandableAdapter = new CountryListExpandableAdapter(getContext(), generateOperatorsList());
        mOperatorsListExpandableAdapter.setCustomParentAnimationViewId(R.id.parent_list_item_expand_arrow);
        mOperatorsListExpandableAdapter.setParentClickableViewAnimationDefaultDuration();
        mOperatorsListExpandableAdapter.setParentAndIconExpandOnClick(true);

        recyclerView.setAdapter(mOperatorsListExpandableAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private ArrayList<ParentObject> generateOperatorsList()
    {
        ArrayList<ParentObject> parentObjects = new ArrayList<>();

        try
        {
            LoadData loadData = new LoadData();
            JSONArray armiesList = loadData.loadList(getContext(), loadData.RES_ARMIES);

            JSONObject armiesData    = loadData.loadData(getContext(), loadData.RES_ARMIES);
            JSONObject operatorsData = loadData.loadData(getContext(), loadData.RES_OPERATORS);

            for (int i = 0; i < armiesList.length(); i++)
            {
                String armyId = armiesList.getString(i);

                JSONObject armyData = armiesData.getJSONObject(armyId);
                JSONArray armyOperators = armyData.getJSONArray("operators");

                String armyCountry =armyData.getString("country");
                int armyCountryImageId = getResources().getIdentifier("flag_" + armyCountry, "drawable", getContext().getPackageName());


                ArrayList<Object> childList = new ArrayList<>();
                for (int j = 0; j < armyOperators.length(); j++)
                {
                    String operatorId = armyOperators.getString(j);
                    JSONObject operatorData = operatorsData.getJSONObject(operatorId);

                    int operatorImageId = getResources().getIdentifier("o_" + operatorId, "drawable", getContext().getPackageName());

                    childList.add(new Operator(operatorId, operatorImageId, operatorData.getString("name")));
                }

                Army army = new Army(armyData.getString("name"), armyCountryImageId);
                army.setChildObjectList(childList);
                parentObjects.add(army);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return parentObjects;
    }
}