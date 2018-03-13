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

import com.khrys.r6assistant.HeaderListAdapter;
import com.khrys.r6assistant.R;

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

        ArrayList<Integer> weaponsimg = new ArrayList<>();
        ArrayList<String> weaponstxt = new ArrayList<>();

        String tableArme[] = {"l85a2","ar33","g36c","r4-c","556xi","f2","ak-12","aug","552 commando","416-c carbine","c8-sfw","mk17 cqb","para 308","type 89","c7e","m762","v308","spear",
                              "p226 mk 25","57 usg","m45 meusoc","p9","lfp586","gsh-18","pmm","p12","mk1","d-50","prb92","luison","p229","usp40","q-929","rg15",
                              "6p41","g8a1","m249","t-95 lsw","lmg-e",
                              "smg-11","bearing-9","c75","smg-12",
                              "417","ots-03","camrs","sr-25","mk 14 ebr",
                              "m590a1","m1014","sg-cqb","sasg-12","m870","super 90","spas-12","spas-15","supernova","ita12l","ita12s","six12","six12 sd","f0-12","bosg 12 2",
                              "fmg-9","mp5k","ump45","mp5","p90","9x19vsn","mp7","c1","mpx","m12","mp5sd","pdw9","vector","t-5 smg","scorpion evo 3 a1","k1a"};

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
        for (String aTableArme : tableArme)
        {
            String img = "g_" + aTableArme;
            img = img.replace(' ', '_');
            img = img.replace('-', '_');

            int imgid = getResources().getIdentifier(img, "drawable", view.getContext().getPackageName());

            weaponsimg.add(imgid);
            weaponstxt.add(aTableArme);
        }

        HeaderListAdapter mAdapter = new HeaderListAdapter(weaponsimg,weaponstxt);
        mRecycler.setAdapter(mAdapter);
        mRecycler.addItemDecoration(new StickyHeaderDecoration(mAdapter));
    }

}