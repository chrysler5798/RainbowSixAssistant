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

import com.khrys.r6assistant.HeaderListAdapter;
import com.khrys.r6assistant.R;
import com.khrys.r6assistant.data.LoadData;

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

        ArrayList<Integer> operatorsImg = new ArrayList<>();
        ArrayList<String> operatorsTxt = new ArrayList<>();

        ArrayList<Integer> operatorsImgAtk = new ArrayList<>();
        ArrayList<String> operatorsTxtAtk = new ArrayList<>();
        ArrayList<Integer> operatorsImgDef = new ArrayList<>();
        ArrayList<String> operatorsTxtDef = new ArrayList<>();

        LoadData dataLoader = new LoadData();
        JSONObject operatorsList = dataLoader.loadData(view.getContext(), dataLoader.RES_OPERATORS);

        for (int i = 0; i < operatorsList.length(); i++)
        {
            try
            {
                JSONObject operator = operatorsList.getJSONObject(String.valueOf(i));
                String operatorName = operator.getString("name");
                int operatorSide = operator.getInt("side");
                String iconOperatorId = "op_icon_"+i;

                int imgid = getResources().getIdentifier(iconOperatorId, "drawable", view.getContext().getPackageName());

                if(operatorSide == 0)
                {
                    operatorsImgDef.add(imgid);
                    operatorsTxtDef.add(operatorName);
                }
                else
                {
                    operatorsImgAtk.add(imgid);
                    operatorsTxtAtk.add(operatorName);
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }

        operatorsImg.addAll(operatorsImgAtk);
        operatorsImg.addAll(operatorsImgDef);

        operatorsTxt.addAll(operatorsTxtAtk);
        operatorsTxt.addAll(operatorsTxtDef);

        HeaderListAdapter mAdapter = new HeaderListAdapter(operatorsImg,operatorsTxt);
        mRecycler.setAdapter(mAdapter);
        mRecycler.addItemDecoration(new StickyHeaderDecoration(mAdapter));
    }
}