package com.khrys.r6assistant;

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

import java.util.ArrayList;

public class OperatorsFragment extends Fragment
{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_weapons, container, false);
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

        String tableOps[] = {"kapkan","tachanka","glaz","fuze",
                            "iq","blitz","bandit","jäger",
                            "rook","doc","twitch","montagne",
                            "thermite","pulse","castle","ash",
                            "thatcher","smoke","sledge","mute",
                            "frost","buck",
                            "valkyrie","blackbeard",
                            "cãpitao","caveira",
                            "echo","hibana",
                            "mira", "jackal"};

        for(int i = 0; i < 30; i++)
        {
            String img = "o_"+tableOps[i];

            img = img.replace('ä','a');
            img = img.replace('ã','a');

            int imgid = getResources().getIdentifier(img, "drawable", view.getContext().getPackageName());

            weaponsimg.add(imgid);
            weaponstxt.add(tableOps[i]);
        }

        mRecycler.setAdapter(new ListAdapterWeapons(0,weaponsimg,weaponstxt));
    }

}

