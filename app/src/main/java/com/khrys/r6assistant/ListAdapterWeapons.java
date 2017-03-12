package com.khrys.r6assistant;

/*
 * Created by Chrysler on 3/8/2017.
 * RainbowSixAssistant
*/

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class ListAdapterWeapons extends RecyclerView.Adapter<ListAdapterWeapons.MyViewHolder>
{
    private List<Integer> weaponsimg;
    private List<String> weaponstxt;
    int type;
    private Button buttonBack;

    ListAdapterWeapons(int type, ArrayList<Integer> weaponsimg, ArrayList<String> weaponstxt, Button buttonBack)
    {
        this.weaponsimg = weaponsimg;
        this.weaponstxt = weaponstxt;
        this.type = type;
        this.buttonBack = buttonBack;
    }

    @Override
    public int getItemCount()
    {
        return weaponstxt.size();
    }

    @Override
    public ListAdapterWeapons.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_weapons, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListAdapterWeapons.MyViewHolder holder, int position)
    {
        if(weaponstxt != null)
        {
            Integer imgdata = weaponsimg.get(position);
            String txtdata = weaponstxt.get(position);
            holder.display(type,imgdata,txtdata);
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        private final ImageView imageV;
        private final TextView textV;
        private final Context context;
        private int type;

        MyViewHolder(final View itemView)
        {
            super(itemView);

            imageV = (ImageView) itemView.findViewById(R.id.imageVarme);
            textV = (TextView) itemView.findViewById(R.id.textVarme);
            context = itemView.getContext();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(type == 1)
                    {
                        Intent newWeapon = new Intent(context, WeaponActivity.class);
                        newWeapon.putExtra("arme", textV.getText().toString());
                        context.startActivity(newWeapon);
                    }
                    else
                    {
                        ArrayList<Integer> dataweaponsimg = new ArrayList<>();
                        ArrayList<String> dataweaponstxt = new ArrayList<>();
                        switch(textV.getText().toString())
                        {
                            case "kapkan":
                                dataweaponsimg.add(R.drawable.g_9x19vsn);
                                dataweaponstxt.add("9x19vsn");

                                dataweaponsimg.add(R.drawable.g_sasg_12);
                                dataweaponstxt.add("sasg-12");

                                dataweaponsimg.add(R.drawable.g_pmm);
                                dataweaponstxt.add("pmm");

                                dataweaponsimg.add(R.drawable.g_gsh_18);
                                dataweaponstxt.add("gsh-18");
                                break;

                            case "tachanka":
                                dataweaponsimg.add(R.drawable.g_9x19vsn);
                                dataweaponstxt.add("9x19vsn");

                                dataweaponsimg.add(R.drawable.g_sasg_12);
                                dataweaponstxt.add("sasg-12");

                                dataweaponsimg.add(R.drawable.g_pmm);
                                dataweaponstxt.add("pmm");

                                dataweaponsimg.add(R.drawable.g_gsh_18);
                                dataweaponstxt.add("gsh-18");
                                break;

                            case "glaz":
                                dataweaponsimg.add(R.drawable.g_ots_03);
                                dataweaponstxt.add("ots-03");

                                dataweaponsimg.add(R.drawable.g_pmm);
                                dataweaponstxt.add("pmm");

                                dataweaponsimg.add(R.drawable.g_gsh_18);
                                dataweaponstxt.add("gsh-18");
                                break;

                            case "fuze":
                                dataweaponsimg.add(R.drawable.g_6p41);
                                dataweaponstxt.add("6p41");

                                dataweaponsimg.add(R.drawable.g_ak_12);
                                dataweaponstxt.add("ak-12");

                                dataweaponsimg.add(R.drawable.g_pmm);
                                dataweaponstxt.add("pmm");

                                dataweaponsimg.add(R.drawable.g_gsh_18);
                                dataweaponstxt.add("gsh-18");
                                break;

                            case "iq":
                                dataweaponsimg.add(R.drawable.g_aug);
                                dataweaponstxt.add("aug");

                                dataweaponsimg.add(R.drawable.g_552_commando);
                                dataweaponstxt.add("552 commando");

                                dataweaponsimg.add(R.drawable.g_g8a1);
                                dataweaponstxt.add("g8a1");

                                dataweaponsimg.add(R.drawable.g_p12);
                                dataweaponstxt.add("p12");
                                break;

                            case "blitz":
                                dataweaponsimg.add(R.drawable.g_p12);
                                dataweaponstxt.add("p12");
                                break;

                            case "bandit":
                                dataweaponsimg.add(R.drawable.g_mp7);
                                dataweaponstxt.add("mp7");

                                dataweaponsimg.add(R.drawable.g_m870);
                                dataweaponstxt.add("m870");

                                dataweaponsimg.add(R.drawable.g_p12);
                                dataweaponstxt.add("p12");
                                break;

                            case "jäger":
                                dataweaponsimg.add(R.drawable.g_416_c_carbine);
                                dataweaponstxt.add("416-c carbine");

                                dataweaponsimg.add(R.drawable.g_m870);
                                dataweaponstxt.add("m870");

                                dataweaponsimg.add(R.drawable.g_p12);
                                dataweaponstxt.add("p12");
                                break;

                            case "rook":
                                dataweaponsimg.add(R.drawable.g_p90);
                                dataweaponstxt.add("p90");

                                dataweaponsimg.add(R.drawable.g_mp5);
                                dataweaponstxt.add("mp5");

                                dataweaponsimg.add(R.drawable.g_sg_cqb);
                                dataweaponstxt.add("sg-cqb");

                                dataweaponsimg.add(R.drawable.g_lfp586);
                                dataweaponstxt.add("lfp586");

                                dataweaponsimg.add(R.drawable.g_p9);
                                dataweaponstxt.add("p9");
                                break;

                            case "doc":
                                dataweaponsimg.add(R.drawable.g_p90);
                                dataweaponstxt.add("p90");

                                dataweaponsimg.add(R.drawable.g_mp5);
                                dataweaponstxt.add("mp5");

                                dataweaponsimg.add(R.drawable.g_sg_cqb);
                                dataweaponstxt.add("sg-cqb");

                                dataweaponsimg.add(R.drawable.g_lfp586);
                                dataweaponstxt.add("lfp586");

                                dataweaponsimg.add(R.drawable.g_p9);
                                dataweaponstxt.add("p9");
                                break;

                            case "twitch":
                                dataweaponsimg.add(R.drawable.g_f2);
                                dataweaponstxt.add("f2");

                                dataweaponsimg.add(R.drawable.g_417);
                                dataweaponstxt.add("417");

                                dataweaponsimg.add(R.drawable.g_sg_cqb);
                                dataweaponstxt.add("sg-cqb");

                                dataweaponsimg.add(R.drawable.g_lfp586);
                                dataweaponstxt.add("lfp586");

                                dataweaponsimg.add(R.drawable.g_p9);
                                dataweaponstxt.add("p9");
                                break;

                            case "montagne":
                                dataweaponsimg.add(R.drawable.g_lfp586);
                                dataweaponstxt.add("lfp586");

                                dataweaponsimg.add(R.drawable.g_p9);
                                dataweaponstxt.add("p9");
                                break;

                            case "thermite":
                                dataweaponsimg.add(R.drawable.g_m1014);
                                dataweaponstxt.add("m1014");

                                dataweaponsimg.add(R.drawable.g_556xi);
                                dataweaponstxt.add("556xi");

                                dataweaponsimg.add(R.drawable.g_m45_meusoc);
                                dataweaponstxt.add("m45 meusoc");

                                dataweaponsimg.add(R.drawable.g_57_usg);
                                dataweaponstxt.add("57 usg");
                                break;

                            case "pulse":
                                dataweaponsimg.add(R.drawable.g_m1014);
                                dataweaponstxt.add("m1014");

                                dataweaponsimg.add(R.drawable.g_ump45);
                                dataweaponstxt.add("ump45");

                                dataweaponsimg.add(R.drawable.g_m45_meusoc);
                                dataweaponstxt.add("m45 meusoc");

                                dataweaponsimg.add(R.drawable.g_57_usg);
                                dataweaponstxt.add("57 usg");
                                break;

                            case "castle":
                                dataweaponsimg.add(R.drawable.g_m1014);
                                dataweaponstxt.add("m1014");

                                dataweaponsimg.add(R.drawable.g_ump45);
                                dataweaponstxt.add("ump45");

                                dataweaponsimg.add(R.drawable.g_m45_meusoc);
                                dataweaponstxt.add("m45 meusoc");

                                dataweaponsimg.add(R.drawable.g_57_usg);
                                dataweaponstxt.add("57 usg");
                                break;

                            case "ash":
                                dataweaponsimg.add(R.drawable.g_g36c);
                                dataweaponstxt.add("g36c");

                                dataweaponsimg.add(R.drawable.g_r4_c);
                                dataweaponstxt.add("r4-c");

                                dataweaponsimg.add(R.drawable.g_m45_meusoc);
                                dataweaponstxt.add("m45 meusoc");

                                dataweaponsimg.add(R.drawable.g_57_usg);
                                dataweaponstxt.add("57 usg");
                                break;

                            case "thatcher":
                                dataweaponsimg.add(R.drawable.g_ar33);
                                dataweaponstxt.add("ar33");

                                dataweaponsimg.add(R.drawable.g_l85a2);
                                dataweaponstxt.add("l85a2");

                                dataweaponsimg.add(R.drawable.g_m590a1);
                                dataweaponstxt.add("m590a1");

                                dataweaponsimg.add(R.drawable.g_p226_mk_25);
                                dataweaponstxt.add("p226 mk 25");
                                break;

                            case "smoke":
                                dataweaponsimg.add(R.drawable.g_fmg_9);
                                dataweaponstxt.add("fmg-9");

                                dataweaponsimg.add(R.drawable.g_m590a1);
                                dataweaponstxt.add("m590a1");

                                dataweaponsimg.add(R.drawable.g_p226_mk_25);
                                dataweaponstxt.add("p226 mk 25");

                                dataweaponsimg.add(R.drawable.g_smg_11);
                                dataweaponstxt.add("smg-11");
                                break;

                            case "sledge":
                                dataweaponsimg.add(R.drawable.g_l85a2);
                                dataweaponstxt.add("l85a2");

                                dataweaponsimg.add(R.drawable.g_m590a1);
                                dataweaponstxt.add("m590a1");

                                dataweaponsimg.add(R.drawable.g_p226_mk_25);
                                dataweaponstxt.add("p226 mk 25");

                                dataweaponsimg.add(R.drawable.g_smg_11);
                                dataweaponstxt.add("smg-11");
                                break;

                            case "mute":
                                dataweaponsimg.add(R.drawable.g_mp5k);
                                dataweaponstxt.add("mp5k");

                                dataweaponsimg.add(R.drawable.g_m590a1);
                                dataweaponstxt.add("m590a1");

                                dataweaponsimg.add(R.drawable.g_p226_mk_25);
                                dataweaponstxt.add("p226 mk 25");
                                break;

                            case "frost":
                                dataweaponsimg.add(R.drawable.g_c1);
                                dataweaponstxt.add("c1");

                                dataweaponsimg.add(R.drawable.g_super_90);
                                dataweaponstxt.add("super 90");

                                dataweaponsimg.add(R.drawable.g_mk1);
                                dataweaponstxt.add("mk1");
                                break;

                            case "buck":
                                dataweaponsimg.add(R.drawable.g_c8_sfw);
                                dataweaponstxt.add("c8-sfw");

                                dataweaponsimg.add(R.drawable.g_camrs);
                                dataweaponstxt.add("camrs");

                                dataweaponsimg.add(R.drawable.g_mk1);
                                dataweaponstxt.add("mk1");
                                break;

                            case "valkyrie":
                                dataweaponsimg.add(R.drawable.g_mpx);
                                dataweaponstxt.add("mpx");

                                dataweaponsimg.add(R.drawable.g_spas_12);
                                dataweaponstxt.add("spas-12");

                                dataweaponsimg.add(R.drawable.g_d_50);
                                dataweaponstxt.add("d-50");
                                break;

                            case "blackbeard":
                                dataweaponsimg.add(R.drawable.g_sr_25);
                                dataweaponstxt.add("sr-25");

                                dataweaponsimg.add(R.drawable.g_mk17_cqb);
                                dataweaponstxt.add("mk17 cqb");

                                dataweaponsimg.add(R.drawable.g_d_50);
                                dataweaponstxt.add("d-50");
                                break;

                            case "cãpitao":
                                dataweaponsimg.add(R.drawable.g_para_308);
                                dataweaponstxt.add("para-308");

                                dataweaponsimg.add(R.drawable.g_m249);
                                dataweaponstxt.add("m249");

                                dataweaponsimg.add(R.drawable.g_prb92);
                                dataweaponstxt.add("prb92");
                                break;

                            case "caveira":
                                dataweaponsimg.add(R.drawable.g_m12);
                                dataweaponstxt.add("m12");

                                dataweaponsimg.add(R.drawable.g_spas_15);
                                dataweaponstxt.add("spas-15");

                                dataweaponsimg.add(R.drawable.g_luison);
                                dataweaponstxt.add("luison");
                                break;

                            case "echo":
                                dataweaponsimg.add(R.drawable.g_supernova);
                                dataweaponstxt.add("supernova");

                                dataweaponsimg.add(R.drawable.g_mp5sd);
                                dataweaponstxt.add("mp5sd");

                                dataweaponsimg.add(R.drawable.g_p229);
                                dataweaponstxt.add("p229");

                                dataweaponsimg.add(R.drawable.g_bearing_9);
                                dataweaponstxt.add("bearing 9");
                                break;

                            case "hibana":
                                dataweaponsimg.add(R.drawable.g_supernova);
                                dataweaponstxt.add("supernova");

                                dataweaponsimg.add(R.drawable.g_type_89);
                                dataweaponstxt.add("type 89");

                                dataweaponsimg.add(R.drawable.g_p229);
                                dataweaponstxt.add("p229");

                                dataweaponsimg.add(R.drawable.g_bearing_9);
                                dataweaponstxt.add("bearing 9");
                                break;

                            case "mira":
                                dataweaponsimg.add(R.drawable.g_vector);
                                dataweaponstxt.add("vector");

                                dataweaponsimg.add(R.drawable.g_ita12l);
                                dataweaponstxt.add("ita12l");

                                dataweaponsimg.add(R.drawable.g_usp40);
                                dataweaponstxt.add("usp40");

                                dataweaponsimg.add(R.drawable.g_ita12s);
                                dataweaponstxt.add("ita12s");
                                break;

                            case "jackal":
                                dataweaponsimg.add(R.drawable.g_pdw9);
                                dataweaponstxt.add("pdw9");

                                dataweaponsimg.add(R.drawable.g_c7e);
                                dataweaponstxt.add("c7e");

                                dataweaponsimg.add(R.drawable.g_usp40);
                                dataweaponstxt.add("usp40");

                                dataweaponsimg.add(R.drawable.g_ita12s);
                                dataweaponstxt.add("ita12s");
                                break;
                        }

                        swap(dataweaponsimg, dataweaponstxt);
                    }
                }
            });

        }

        void display(int type, Integer imgdata, String txtdata)
        {
            imageV.setImageResource(imgdata);
            textV.setText(txtdata);
            this.type = type;
        }
    }

    private void swap(ArrayList<Integer> newweaponsimg, ArrayList<String> newweaponstxt){

        buttonBack.setVisibility(View.VISIBLE);

        final ArrayList<Integer> oldweaponsimg = new ArrayList<>(weaponsimg);
        final ArrayList<String> oldweaponstxt = new ArrayList<>(weaponstxt);

        weaponsimg.clear();
        weaponstxt.clear();

        weaponsimg.addAll(newweaponsimg);
        weaponstxt.addAll(newweaponstxt);

        notifyDataSetChanged();

        type = 1;

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weaponsimg.clear();
                weaponstxt.clear();

                weaponsimg.addAll(oldweaponsimg);
                weaponstxt.addAll(oldweaponstxt);

                notifyDataSetChanged();

                buttonBack.setVisibility(View.GONE);

                type = 0;
            }
        });
    }
}