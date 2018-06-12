package com.khrys.r6assistant.weapons;

/*
 * Created by Louis on 30/12/2016.
 * RainbowSixAssistant
*/

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import java.util.List;
import java.util.Locale;

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

        OperatorListExpandableAdapter mOperatorsListExpandableAdapter = new OperatorListExpandableAdapter(mContext, generateOperatorsList(mContext));
        mOperatorsListExpandableAdapter.setCustomParentAnimationViewId(R.id.parent_list_item_expand_arrow);
        mOperatorsListExpandableAdapter.setParentClickableViewAnimationDefaultDuration();
        mOperatorsListExpandableAdapter.setParentAndIconExpandOnClick(true);

        recyclerView.setAdapter(mOperatorsListExpandableAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }

    private ArrayList<ParentObject> generateOperatorsList(Context context)
    {
        List<Operator> operators = new ArrayList<>();

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

                int imgid = getResources().getIdentifier("o_" + operatorId, "drawable", context.getPackageName());

                operators.add(new Operator(operator.getString("name"), imgid));
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        ArrayList<ParentObject> parentObjects = new ArrayList<>();
        for (Operator operator : operators)
        {
            String op = operator.getName().toLowerCase(Locale.ENGLISH);
            op = op.replace('ä','a');
            op = op.replace('ã','a');
            int arrayId = getResources().getIdentifier(op, "array", getContext().getPackageName());
            Log.i("GETSTRINGARRAY", "[" + op + "]");
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

                childList.add(new Weapon(resID, arme));
            }
            operator.setChildObjectList(childList);
            parentObjects.add(operator);
        }
        return parentObjects;
    }
}