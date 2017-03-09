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

        weaponsimg.add(R.drawable.o_kapkan);
        weaponstxt.add("kapkan");

        weaponsimg.add(R.drawable.o_tachanka);
        weaponstxt.add("tachanka");

        weaponsimg.add(R.drawable.o_glaz);
        weaponstxt.add("glaz");

        weaponsimg.add(R.drawable.o_fuze);
        weaponstxt.add("fuze");

        weaponsimg.add(R.drawable.o_iq);
        weaponstxt.add("iq");

        weaponsimg.add(R.drawable.o_blitz);
        weaponstxt.add("blitz");

        weaponsimg.add(R.drawable.o_bandit);
        weaponstxt.add("bandit");

        weaponsimg.add(R.drawable.o_jager);
        weaponstxt.add("jäger");

        weaponsimg.add(R.drawable.o_rook);
        weaponstxt.add("rook");

        weaponsimg.add(R.drawable.o_doc);
        weaponstxt.add("doc");

        weaponsimg.add(R.drawable.o_twitch);
        weaponstxt.add("twitch");

        weaponsimg.add(R.drawable.o_montagne);
        weaponstxt.add("montagne");

        weaponsimg.add(R.drawable.o_thermite);
        weaponstxt.add("thermite");

        weaponsimg.add(R.drawable.o_pulse);
        weaponstxt.add("pulse");

        weaponsimg.add(R.drawable.o_castle);
        weaponstxt.add("castle");

        weaponsimg.add(R.drawable.o_ash);
        weaponstxt.add("ash");

        weaponsimg.add(R.drawable.o_thatcher);
        weaponstxt.add("thatcher");

        weaponsimg.add(R.drawable.o_smoke);
        weaponstxt.add("smoke");

        weaponsimg.add(R.drawable.o_sledge);
        weaponstxt.add("sledge");

        weaponsimg.add(R.drawable.o_mute);
        weaponstxt.add("mute");

        weaponsimg.add(R.drawable.o_frost);
        weaponstxt.add("frost");

        weaponsimg.add(R.drawable.o_buck);
        weaponstxt.add("buck");

        weaponsimg.add(R.drawable.o_valkyrie);
        weaponstxt.add("valkyrie");

        weaponsimg.add(R.drawable.o_blackbeard);
        weaponstxt.add("blackbeard");

        weaponsimg.add(R.drawable.o_capitao);
        weaponstxt.add("capitão");

        weaponsimg.add(R.drawable.o_caveira);
        weaponstxt.add("caveira");

        weaponsimg.add(R.drawable.o_echo);
        weaponstxt.add("echo");

        weaponsimg.add(R.drawable.o_hibana);
        weaponstxt.add("hibana");

        weaponsimg.add(R.drawable.o_mira);
        weaponstxt.add("mira");

        weaponsimg.add(R.drawable.o_jackal);
        weaponstxt.add("jackal");

        mRecycler.setAdapter(new ListAdapterWeapons(weaponsimg,weaponstxt));
    }

}

