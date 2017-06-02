package com.khrys.r6assistant.operators;
/*
 * Created by Louis on 31/05/2017.
 * 
 * R6Assistant
*/

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khrys.r6assistant.HeaderListAdapter;
import com.khrys.r6assistant.R;

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

        mRecycler = (RecyclerView) view.findViewById(R.id.WeaponsRecycler);

        mRecycler.setHasFixedSize(true);
        mLayout = new LinearLayoutManager(getContext());
        mRecycler.setLayoutManager(mLayout);

        ArrayList<Integer> weaponsimg = new ArrayList<>();
        ArrayList<String> weaponstxt = new ArrayList<>();

        TypedArray tableOps = getResources().obtainTypedArray(R.array.operatorsAtkDef);

        for (int i = 0; i < tableOps.length(); i++)
        {
            String actual = tableOps.getString(i);
            String img = "o_" + actual;
            img = img.replace('ä','a');
            img = img.replace('ã','a');

            int imgid = getResources().getIdentifier(img, "drawable", view.getContext().getPackageName());

            weaponsimg.add(imgid);
            weaponstxt.add(actual);
        }

        tableOps.recycle();

        HeaderListAdapter mAdapter = new HeaderListAdapter(weaponsimg,weaponstxt);
        mRecycler.setAdapter(mAdapter);
        mRecycler.addItemDecoration(new StickyHeaderDecoration(mAdapter));
    }
}