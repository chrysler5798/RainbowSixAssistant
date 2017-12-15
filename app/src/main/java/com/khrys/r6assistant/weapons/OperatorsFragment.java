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

import org.json.JSONException;
import org.json.JSONObject;

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

        RecyclerView recyclerView = view.findViewById(R.id.WeaponsRecycler);

        Context mContext = getContext();

        OperatorsListExpandableAdapter mOperatorsListExpandableAdapter = new OperatorsListExpandableAdapter(mContext, generateOperatorsList(mContext));
        mOperatorsListExpandableAdapter.setCustomParentAnimationViewId(R.id.parent_list_item_expand_arrow);
        mOperatorsListExpandableAdapter.setParentClickableViewAnimationDefaultDuration();
        mOperatorsListExpandableAdapter.setParentAndIconExpandOnClick(true);

        recyclerView.setAdapter(mOperatorsListExpandableAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }

    private ArrayList<ParentObject> generateOperatorsList(Context context)
    {
        List<Operators> operatorsList = new ArrayList<>();

        LoadData dataLoader = new LoadData();
        JSONObject operatorsListData = dataLoader.loadData(context, dataLoader.RES_OPERATORS);

        for(int i = 0; i < operatorsListData.length(); i++)
        {
            try
            {
                JSONObject operator = operatorsListData.getJSONObject(String.valueOf(i));
                String operatorName = operator.getString("name");
                String iconOperatorId = "op_icon_"+i;

                int iconOperatorid = getResources().getIdentifier(iconOperatorId, "drawable", getContext().getPackageName());

                operatorsList.add(new Operators(operatorName, iconOperatorid));
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }

        ArrayList<ParentObject> parentObjects = new ArrayList<>();
        for (Operators operators : operatorsList)
        {
            String op = operators.getName();
            op = op.replace('ä','a');
            op = op.replace('ã','a');
            int arrayId = getResources().getIdentifier(op, "array", getContext().getPackageName());
            String[] opWeapon = getResources().getStringArray(arrayId);
            int endWeapons = Integer.parseInt(opWeapon[3])+5;

            ArrayList<Object> childList = new ArrayList<>();

            for (int j = 5; j < endWeapons; j++)
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