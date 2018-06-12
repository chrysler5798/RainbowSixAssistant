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

        ArrayList<Weapon> weapons = new ArrayList<>();

        try
        {
            LoadData loadData = new LoadData();
            JSONObject operatorsJSON = loadData.loadData(getContext(), loadData.RES_WEAPONS);
            JSONArray weaponsList = operatorsJSON.getJSONArray("weapons_list");
            JSONObject weaponsData = operatorsJSON.getJSONObject("weapons");

            for (int i = 0; i < weaponsList.length(); i++)
            {
                String weaponId = weaponsList.getString(i);
                JSONObject weapon = weaponsData.getJSONObject(weaponId);

                int imgid = getResources().getIdentifier("g_" + weaponId, "drawable", view.getContext().getPackageName());

                weapons.add(new Weapon(imgid, weapon.getString("name")));
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

/*        Compte
            Assault : 16
            Pistols : 16
            LMG : 5
            SMG : 4
            Sniper : 5
            Shotgun : 15
            Submachine : 16
            TOTAL : 77
 */

        WeaponsHeaderListAdapter mAdapter = new WeaponsHeaderListAdapter(weapons);
        mRecycler.setAdapter(mAdapter);
        mRecycler.addItemDecoration(new StickyHeaderDecoration(mAdapter));
    }

}