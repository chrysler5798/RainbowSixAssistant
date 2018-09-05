package com.khrys.r6assistant.operators;
/*
 * Created by Khrys.
 *
 * App : RainbowSixAssistant
 * Info : 05/31/2017 []
*/

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.khrys.r6assistant.R;
import com.khrys.r6assistant.data.LoadData;
import com.khrys.r6assistant.weapons.Weapon;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class OperatorActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_operator);

        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        String operatorId = getIntent().getStringExtra("operatorId");

        //ScrollView
        ScrollView scroll = findViewById(R.id.globalScroll);
        scroll.smoothScrollTo(0,0);

        //Operator icon
        ImageView imgIcon = findViewById(R.id.iconOperator);

        //Operator name
        TextView txtName  = findViewById(R.id.textName);

        //Side icon and name
        ImageView imgSide = findViewById(R.id.iconSide);
        TextView txtSide  = findViewById(R.id.textSide);

        //Flag and army
        ImageView imgFlag = findViewById(R.id.iconFlag);
        TextView txtArmy  = findViewById(R.id.textArmy);

        //Operator image
        ImageView imgOp = findViewById(R.id.imageOperator);

        //Speed stat section
        ImageView imgSpeed = findViewById(R.id.imageSpeed);
        TextView txtSpeed  = findViewById(R.id.textSpeed);

        //Armor stat section
        ImageView imgArmor = findViewById(R.id.imageArmor);
        TextView txtArmor  = findViewById(R.id.textArmor);

        //Primary weapon section
        TextView txtPrimary = findViewById(R.id.textPrimary);
        RecyclerView rvPrim = findViewById(R.id.recyclerPrimary);

        //Secondary weapon section
        TextView txtSecondary = findViewById(R.id.textSecondary);
        RecyclerView rvScd    = findViewById(R.id.recyclerSecondary);

        //Gadget section
        TextView txtNumGadget1  =  findViewById(R.id.txtNumGadget1);
        TextView txtNameGadget1 =  findViewById(R.id.txtNameGadget1);
        ImageView imgGadget1    =  findViewById(R.id.imageGadget1);

        TextView txtNumGadget2  = findViewById(R.id.txtNumGadget2);
        TextView txtNameGadget2 = findViewById(R.id.txtNameGadget2);
        ImageView imgGadget2    = findViewById(R.id.imageGadget2);

        //Unique gadget
        TextView titleUniqueGadget =  findViewById(R.id.titleUniqueGadget);
        ImageView imgUniqueGadget =  findViewById(R.id.imageUniqueGadget);
        TextView txtUniqueGadget =  findViewById(R.id.textUniqueGadget);

        ArrayList<Weapon> operatorWeaponsPrimaryList = new ArrayList<>();
        ArrayList<Weapon> operatorWeaponsSecondaryList = new ArrayList<>();

        LoadData dataLoader = new LoadData();
        try
        {
            JSONObject operatorData = dataLoader.loadData(getApplicationContext(), dataLoader.RES_OPERATORS).getJSONObject(operatorId);
            JSONObject armiesData = dataLoader.loadData(getApplicationContext(), dataLoader.RES_ARMIES);
            JSONObject weaponsData = dataLoader.loadData(getApplicationContext(), dataLoader.RES_WEAPONS);

            String operatorName = operatorData.getString("name");
            int operatorSide = operatorData.getInt("side");

            String operatorArmyId = operatorData.getString("army");
            JSONObject operatorArmy = armiesData.getJSONObject(operatorArmyId);
            String operatorArmyName = operatorArmy.getString("name");
            String operatorArmyCountry = operatorArmy.getString("country");

            JSONArray operatorWeaponsPrimary = operatorData.getJSONArray("weapons_primary");
            JSONArray operatorWeaponsSecondary = operatorData.getJSONArray("weapons_secondary");

            int operatorSpeed = operatorData.getInt("speed");
            int operatorArmor = operatorData.getInt("armor");

            JSONArray operatorGadgets = operatorData.getJSONArray("gadgets");
            JSONArray operatorNbGadgets = operatorData.getJSONArray("gadgets_nb");

            int operatorUniqueGadgetNb = operatorData.getInt("gadget_unique_nb");

            for (int i = 0; i < operatorWeaponsPrimary.length(); i++)
            {
                String weaponId = operatorWeaponsPrimary.getString(i);
                String weaponName = weaponsData.getJSONObject(weaponId).getString("name");

                int weaponImageId = getResources().getIdentifier("g_" + weaponId, "drawable", getPackageName());

                operatorWeaponsPrimaryList.add(new Weapon(weaponId, weaponImageId, weaponName));
            }

            for (int i = 0; i < operatorWeaponsSecondary.length(); i++)
            {
                String weaponId = operatorWeaponsSecondary.getString(i);
                String weaponName = weaponsData.getJSONObject(weaponId).getString("name");

                int weaponImageId = getResources().getIdentifier("g_" + weaponId, "drawable", getPackageName());

                operatorWeaponsSecondaryList.add(new Weapon(weaponId, weaponImageId, weaponName));
            }

            //Setup name and icon
            //
            txtName.setText(operatorName);

            int operatorIconImageId = getResources().getIdentifier("o_" + operatorId, "drawable", getPackageName());
            imgIcon.setImageResource(operatorIconImageId);


            //Setup side
            //
            int operatorSideName = (operatorSide == 1) ? R.string.attacker : R.string.defender;
            int operatorSideIconId = getResources().getIdentifier("op_side_" + operatorSide, "drawable", getPackageName());

            txtSide.setText(operatorSideName);
            imgSide.setImageResource(operatorSideIconId);


            //Setup flag and army
            //
            int operatorArmyFlagId = getResources().getIdentifier("flag_" + operatorArmyCountry, "drawable", getPackageName());

            txtArmy.setText(operatorArmyName);
            imgFlag.setImageResource(operatorArmyFlagId);


            //Setup armor
            //
            int armorTxt = 0, armorImg = 0;

            switch (operatorArmor)
            {
                case 1:
                    armorTxt = R.string.armor_light;
                    armorImg = R.drawable.ostat_armor_1;
                    break;

                case 2:
                    armorTxt = R.string.medium;
                    armorImg = R.drawable.ostat_armor_2;
                    break;

                case 3:
                    armorTxt = R.string.armor_heavy;
                    armorImg = R.drawable.ostat_armor_3;
                    break;
            }

            txtArmor.setText(armorTxt);
            imgArmor.setImageResource(armorImg);


            //Setup image operator
            int operatorImageId = getResources().getIdentifier("oi_" + operatorId, "drawable", getPackageName());
            imgOp.setImageResource(operatorImageId);


            //Setup speed
            //
            int speedTxt = 0, speedImg = 0;

            switch (operatorSpeed)
            {
                case 1:
                    speedTxt = R.string.speed_slow;
                    speedImg = R.drawable.ostat_speed_1;
                    break;

                case 2:
                    speedTxt = R.string.medium;
                    speedImg = R.drawable.ostat_speed_2;
                    break;

                case 3:
                    speedTxt = R.string.speed_fast;
                    speedImg = R.drawable.ostat_speed_3;
                    break;
            }

            txtSpeed.setText(speedTxt);
            imgSpeed.setImageResource(speedImg);


            //Setup weapons
            //
            if(operatorWeaponsPrimaryList.size() > 0)
            {
                rvPrim.setAdapter(new WeaponsListAdapter(operatorWeaponsPrimaryList));
                rvPrim.setLayoutManager(new LinearLayoutManager(this));
            }
            else
            {
                rvPrim.setVisibility(View.GONE);
                txtPrimary.setVisibility(View.GONE);
            }

            if(operatorWeaponsSecondaryList.size() > 0)
            {
                rvScd.setAdapter(new WeaponsListAdapter(operatorWeaponsSecondaryList));
                rvScd.setLayoutManager(new LinearLayoutManager(this));
            }
            else
            {
                rvScd.setVisibility(View.GONE);
                txtSecondary.setVisibility(View.GONE);
            }


            //Setup gadget
            //
            String operatorGadget1Id = "gad_" + operatorGadgets.getString(0);
            String operatorGadget2Id = "gad_" + operatorGadgets.getString(1);

            int operatorGadget1Number = operatorNbGadgets.getInt(0);
            int operatorGadget2Number = operatorNbGadgets.getInt(1);

            int operatorGadget1ImageId = getResources().getIdentifier(operatorGadget1Id, "drawable", getPackageName());
            int operatorGadget2ImageId = getResources().getIdentifier(operatorGadget2Id, "drawable", getPackageName());

            int operatorGadget1NameId = getResources().getIdentifier(operatorGadget1Id, "string", getPackageName());
            int operatorGadget2NameId = getResources().getIdentifier(operatorGadget2Id, "string", getPackageName());

            txtNumGadget1.setText(String.valueOf(operatorGadget1Number));
            txtNameGadget1.setText(getString(operatorGadget1NameId));

            txtNumGadget2.setText(String.valueOf(operatorGadget2Number));
            txtNameGadget2.setText(getString(operatorGadget2NameId));

            imgGadget1.setImageResource(operatorGadget1ImageId);
            imgGadget2.setImageResource(operatorGadget2ImageId);

            //Setup unique gadget
            //
            int operatorUniqueGadgetNameId = getResources().getIdentifier("ugat_" + operatorId, "string", getPackageName());
            int operatorUniqueGadgetDescId = getResources().getIdentifier("uga_" + operatorId, "string", getPackageName());
            int operatorUniqueGadgetImageId = getResources().getIdentifier("ga_" + operatorId, "drawable", getPackageName());

            String operatorUniqueGadgetName = getString(operatorUniqueGadgetNameId);
            if(operatorUniqueGadgetNb > 0)
            {
                operatorUniqueGadgetName += " x" + operatorUniqueGadgetNb;
            }

            titleUniqueGadget.setText(operatorUniqueGadgetName);
            imgUniqueGadget.setImageResource(operatorUniqueGadgetImageId);
            txtUniqueGadget.setText(operatorUniqueGadgetDescId);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:

                this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}