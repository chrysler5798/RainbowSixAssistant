package com.khrys.r6assistant.operators;
/*
 * Created by Khrys.
 *
 * App : RainbowSixAssistant
 * Info : 05/31/2017[00:00 PM]
*/

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khrys.r6assistant.weapons.WeaponsHeaderListAdapter;
import com.khrys.r6assistant.R;
import com.khrys.r6assistant.data.LoadData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ca.barrenechea.widget.recyclerview.decoration.StickyHeaderDecoration;

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

        RecyclerView mRecycler;
        RecyclerView.LayoutManager mLayout;

        mRecycler = view.findViewById(R.id.WeaponsRecycler);

        mRecycler.setHasFixedSize(true);
        mLayout = new LinearLayoutManager(getContext());
        mRecycler.setLayoutManager(mLayout);

        ArrayList<Operator> operatorsAtk = new ArrayList<>();
        ArrayList<Operator> operatorsDef = new ArrayList<>();

        try
        {
            LoadData loadData = new LoadData();
            JSONObject operatorsJSON = loadData.loadData(getContext(), loadData.RES_OPERATORS);
            JSONArray operatorsList = operatorsJSON.getJSONArray("operators");
            JSONObject operatorsData = operatorsJSON.getJSONObject("operators_data");

            for (int i = 0; i < operatorsList.length(); i++)
            {
                String operatorId = operatorsList.getString(i);
                JSONObject operator = operatorsData.getJSONObject(operatorId);

                int imgid = getResources().getIdentifier("o_" + operatorId, "drawable", view.getContext().getPackageName());
                Operator operatorForList = new Operator(operatorId, imgid, operator.getString("name"));

                if(operator.getInt("side") == 1)
                {
                    operatorsAtk.add(operatorForList);
                }
                else
                {
                    operatorsDef.add(operatorForList);
                }
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        ArrayList<Operator> finalOperators = new ArrayList<>(operatorsAtk);
        finalOperators.addAll(operatorsDef);

        OperatorHeaderListAdapter mAdapter = new OperatorHeaderListAdapter(finalOperators);
        mRecycler.setAdapter(mAdapter);
        mRecycler.addItemDecoration(new StickyHeaderDecoration(mAdapter));
    }
}