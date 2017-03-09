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

public class WeaponsFragment extends Fragment
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

        weaponsimg.add(R.drawable.g_416_c);
        weaponstxt.add("416");

        weaponsimg.add(R.drawable.g_552_commando);
        weaponstxt.add("552 Commando");

        weaponsimg.add(R.drawable.g_556xi);
        weaponstxt.add("556XI");

         weaponsimg.add(R.drawable.g_ak_12);
        weaponstxt.add("AK-12");

         weaponsimg.add(R.drawable.g_ar33);
        weaponstxt.add("AR33");

         weaponsimg.add(R.drawable.g_aug);
        weaponstxt.add("AUG");

         weaponsimg.add(R.drawable.g_c7e);
        weaponstxt.add("C7E");

         weaponsimg.add(R.drawable.g_c8_sfw);
        weaponstxt.add("C8-SFW");

         weaponsimg.add(R.drawable.g_f2);
        weaponstxt.add("F2");

         weaponsimg.add(R.drawable.g_g36c);
        weaponstxt.add("G36C");

         weaponsimg.add(R.drawable.g_l85a2);
        weaponstxt.add("L85A2");

         weaponsimg.add(R.drawable.g_mk17_cqb);
        weaponstxt.add("MK17 CQB");

         weaponsimg.add(R.drawable.g_para_308);
        weaponstxt.add("PARA 308");

         weaponsimg.add(R.drawable.g_r4_c);
        weaponstxt.add("R4-C");

         weaponsimg.add(R.drawable.g_type_89);
        weaponstxt.add("Type 89");

         weaponsimg.add(R.drawable.g_57_usg);
        weaponstxt.add("57 USG");

         weaponsimg.add(R.drawable.g_d_50);
        weaponstxt.add("D-50");

         weaponsimg.add(R.drawable.g_gsh18);
        weaponstxt.add("GSH18");

         weaponsimg.add(R.drawable.g_lfp586);
        weaponstxt.add("LFP586");

         weaponsimg.add(R.drawable.g_luison);
        weaponstxt.add("LUISON");

         weaponsimg.add(R.drawable.g_m45_meusoc);
        weaponstxt.add("m45 meusoc");

         weaponsimg.add(R.drawable.g_mk1);
        weaponstxt.add("mk1");

         weaponsimg.add(R.drawable.g_p9);
        weaponstxt.add("p9");

         weaponsimg.add(R.drawable.g_p12);
        weaponstxt.add("p12");

         weaponsimg.add(R.drawable.g_p226_mk_25);
        weaponstxt.add("p226 mk 25");

         weaponsimg.add(R.drawable.g_p229);
        weaponstxt.add("p229");

         weaponsimg.add(R.drawable.g_pmm);
        weaponstxt.add("pmm");

         weaponsimg.add(R.drawable.g_prb92);
        weaponstxt.add("prb92");

         weaponsimg.add(R.drawable.g_usp40);
        weaponstxt.add("usp40");

         weaponsimg.add(R.drawable.g_6p41);
        weaponstxt.add("6p41");

         weaponsimg.add(R.drawable.g_g8a1);
        weaponstxt.add("g8a1");

         weaponsimg.add(R.drawable.g_m249);
        weaponstxt.add("m249");

         weaponsimg.add(R.drawable.g_bearing_9);
        weaponstxt.add("bearing-9");

         weaponsimg.add(R.drawable.g_smg_11);
        weaponstxt.add("smg-11");

         weaponsimg.add(R.drawable.g_417);
        weaponstxt.add("417");

         weaponsimg.add(R.drawable.g_camrs);
        weaponstxt.add("camrs");

         weaponsimg.add(R.drawable.g_ots_03);
        weaponstxt.add("OTs-03");

         weaponsimg.add(R.drawable.g_sr_25);
        weaponstxt.add("SR-25");

         weaponsimg.add(R.drawable.g_ita12l);
        weaponstxt.add("ita12l");

         weaponsimg.add(R.drawable.g_ita12s);
        weaponstxt.add("ita12s");

         weaponsimg.add(R.drawable.g_m590a1);
        weaponstxt.add("m590a1");

         weaponsimg.add(R.drawable.g_m870);
        weaponstxt.add("m870");

         weaponsimg.add(R.drawable.g_m1014);
        weaponstxt.add("m1014");

         weaponsimg.add(R.drawable.g_sasg_12);
        weaponstxt.add("sasg_12");

         weaponsimg.add(R.drawable.g_sg_cqb);
        weaponstxt.add("sg-cqb");

         weaponsimg.add(R.drawable.g_spas_12);
        weaponstxt.add("spas 12");

         weaponsimg.add(R.drawable.g_spas_15);
        weaponstxt.add("spas 15");

         weaponsimg.add(R.drawable.g_super90);
        weaponstxt.add("super 90");

         weaponsimg.add(R.drawable.g_supernova);
        weaponstxt.add("supernova");

         weaponsimg.add(R.drawable.g_9x19vsn);
        weaponstxt.add("9x19vsn");

         weaponsimg.add(R.drawable.g_c1);
        weaponstxt.add("c1");

         weaponsimg.add(R.drawable.g_fmg_9);
        weaponstxt.add("fmg 9");

         weaponsimg.add(R.drawable.g_m12);
        weaponstxt.add("m12");

         weaponsimg.add(R.drawable.g_mp5);
        weaponstxt.add("mp5");

         weaponsimg.add(R.drawable.g_mp5k);
        weaponstxt.add("mp5k");

         weaponsimg.add(R.drawable.g_mp5sd);
        weaponstxt.add("mp5sd");

         weaponsimg.add(R.drawable.g_mp7);
        weaponstxt.add("mp7");

         weaponsimg.add(R.drawable.g_mpx);
        weaponstxt.add("mpx");

         weaponsimg.add(R.drawable.g_p90);
        weaponstxt.add("p90");

         weaponsimg.add(R.drawable.g_pdw9);
        weaponstxt.add("pdw9");

         weaponsimg.add(R.drawable.g_ump45);
        weaponstxt.add("ump45");

        weaponsimg.add(R.drawable.g_vector);
        weaponstxt.add("vector");

        mRecycler.setAdapter(new ListAdapterWeapons(weaponsimg,weaponstxt));
    }

}