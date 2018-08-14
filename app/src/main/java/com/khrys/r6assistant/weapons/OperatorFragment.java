package com.khrys.r6assistant.weapons;

/*
 * Created by Louis on 30/12/2016.
 * RainbowSixAssistant
*/

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.khrys.r6assistant.R;
import com.khrys.r6assistant.data.LoadData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class OperatorFragment extends Fragment
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

        Context mContext = getContext();

        OperatorListExpandableAdapter mOperatorsListExpandableAdapter = new OperatorListExpandableAdapter(mContext, generateOperatorsList());
        mOperatorsListExpandableAdapter.setCustomParentAnimationViewId(R.id.parent_list_item_expand_arrow);
        mOperatorsListExpandableAdapter.setParentClickableViewAnimationDefaultDuration();
        mOperatorsListExpandableAdapter.setParentAndIconExpandOnClick(true);

        recyclerView.setAdapter(mOperatorsListExpandableAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }

    private ArrayList<ParentObject> generateOperatorsList()
    {
        ArrayList<ParentObject> parentObjects = new ArrayList<>();

        try
        {
            LoadData loadData = new LoadData();
            JSONArray operatorsList = loadData.loadList(getContext(), loadData.RES_OPERATORS);

            JSONObject operatorsData = loadData.loadData(getContext(), loadData.RES_OPERATORS);
            JSONObject weaponsData   = loadData.loadData(getContext(), loadData.RES_WEAPONS);

            for (int i = 0; i < operatorsList.length(); i++)
            {
                String operatorId = operatorsList.getString(i);
                JSONObject operatorData = operatorsData.getJSONObject(operatorId);

                int operatorImageId = getResources().getIdentifier("o_" + operatorId, "drawable", getContext().getPackageName());

                JSONArray operatorWeaponsPrimary = operatorData.getJSONArray("weapons_primary");
                JSONArray operatorWeaponsSecondary = operatorData.getJSONArray("weapons_secondary");

                ArrayList<Object> childList = new ArrayList<>();
                for (int j = 0; j < operatorWeaponsPrimary.length(); j++)
                {
                    childList.add(generateWeaponObj(weaponsData, operatorWeaponsPrimary.getString(j)));
                }

                for (int j = 0; j < operatorWeaponsSecondary.length(); j++)
                {
                    childList.add(generateWeaponObj(weaponsData, operatorWeaponsSecondary.getString(j)));
                }

                Operator operator = new Operator(operatorId, operatorData.getString("name"), operatorImageId);
                operator.setChildObjectList(childList);

                parentObjects.add(operator);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return parentObjects;
    }

    private Weapon generateWeaponObj(JSONObject weaponsData, String weaponId) throws JSONException
    {
        String weaponName = weaponsData.getJSONObject(weaponId).getString("name");
        int weaponImageId = getResources().getIdentifier("g_" + weaponId, "drawable", getContext().getPackageName());

        return new Weapon(weaponId, weaponImageId, weaponName);
    }
}