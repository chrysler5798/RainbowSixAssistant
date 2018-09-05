package com.khrys.r6assistant.weapons;

/*
 * Created by Louis on 30/12/2016.
 * RainbowSixAssistant
*/

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.khrys.r6assistant.R;
import com.khrys.r6assistant.data.LoadData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ca.barrenechea.widget.recyclerview.decoration.StickyHeaderDecoration;

public class WeaponsFragment extends Fragment
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

        RecyclerView mRecycler;
        RecyclerView.LayoutManager mLayout;

        mRecycler = view.findViewById(R.id.WeaponsRecycler);

        mRecycler.setHasFixedSize(true);
        mLayout = new LinearLayoutManager(getContext());
        mRecycler.setLayoutManager(mLayout);

        Map<String, ArrayList<Weapon>> weaponsMap = new HashMap<>();
        Map<String, Integer> weaponsTypeCount = new HashMap<>();
        try
        {
            LoadData loadData = new LoadData();
            JSONArray weaponsList = loadData.loadList(getContext(), loadData.RES_WEAPONS);
            JSONObject weaponsData = loadData.loadData(getContext(), loadData.RES_WEAPONS);

            for (int i = 0; i < weaponsList.length(); i++)
            {
                String weaponId = weaponsList.getString(i);
                JSONObject weaponJson = weaponsData.getJSONObject(weaponId);

                int imgId = getResources().getIdentifier("g_" + weaponId, "drawable", view.getContext().getPackageName());

                Weapon weapon = new Weapon(weaponId, imgId, weaponJson.getString("name"));
                String weaponType = weaponJson.getString("type");

                if(weaponsMap.containsKey(weaponType))
                {
                    weaponsMap.get(weaponType).add(weapon);

                    Integer count = weaponsTypeCount.get(weaponType);
                    count++;
                    weaponsTypeCount.put(weaponType, count);
                }
                else
                {
                    ArrayList<Weapon> weaponsType = new ArrayList<>();
                    weaponsType.add(weapon);

                    weaponsMap.put(weaponType, weaponsType);
                    weaponsTypeCount.put(weaponType, 1);
                }
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        ArrayList<Weapon> weapons = new ArrayList<>();
        for(ArrayList<Weapon> weaponsType : weaponsMap.values())
        {
            weapons.addAll(weaponsType);
        }

        WeaponsHeaderListAdapter mAdapter = new WeaponsHeaderListAdapter(weapons, weaponsTypeCount);
        mRecycler.setAdapter(mAdapter);
        mRecycler.addItemDecoration(new StickyHeaderDecoration(mAdapter));
    }
}