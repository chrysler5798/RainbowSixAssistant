package com.khrys.r6assistant.weapons;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.khrys.r6assistant.R;
import com.khrys.r6assistant.data.LoadData;
import com.khrys.r6assistant.operators.OperatorActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/*
 * Created by Khrys.
 *
 * App : RainbowSixAssistant
 * Info : 3/10/2017 []
*/

public class WeaponActivity extends AppCompatActivity
{

    ImageView imgRookArmor, imgArmor1, imgArmor2, imgArmor3;
    TextView txtArmorL1,txtArmorB1,txtArmorL2, txtArmorB2, txtArmorL3, txtArmorB3;
    JSONObject weaponDamage;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_stats);

        if(getSupportActionBar()!= null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setElevation(0);
        }

        //Header
        TextView txtNomArme = findViewById(R.id.txtNomArme);
        ImageView imgViewArme = findViewById(R.id.imgArme);

        //Basic informations
        TextView txtViewAmmo = findViewById(R.id.txtAmmo);
        TextView txtViewFirerate = findViewById(R.id.txtFirerate);
        TextView txtViewDamagefall = findViewById(R.id.txtDamagefalloff);

        //Rook Amor
        Switch switchRookArmor = findViewById(R.id.switchRookarmor);
        imgRookArmor = findViewById(R.id.imgRookarmor);

        //Armor image
        imgArmor1 = findViewById(R.id.imgArmor1);
        imgArmor2 = findViewById(R.id.imgArmor2);
        imgArmor3 = findViewById(R.id.imgArmor3);

        //Armor text
        txtArmorL1 = findViewById(R.id.txtArmorL1);
        txtArmorB1 = findViewById(R.id.txtArmorB1);
        txtArmorL2 = findViewById(R.id.txtArmorL2);
        txtArmorB2 = findViewById(R.id.txtArmorB2);
        txtArmorL3 = findViewById(R.id.txtArmorL3);
        txtArmorB3 = findViewById(R.id.txtArmorB3);

        String weaponId = getIntent().getStringExtra("weaponId");

        try
        {
            LoadData loadData = new LoadData();
            JSONObject weaponData = loadData.loadData(getApplicationContext(), loadData.RES_WEAPONS).getJSONObject(weaponId);

            int weaponImageId = getResources().getIdentifier("g_" + weaponId, "drawable", getPackageName());

            JSONArray weaponOperators = weaponData.getJSONArray("operators");

            int weaponAmmo     = weaponData.getInt("ammo");
            int weaponFirerate = weaponData.getInt("firerate");
            int weaponFalloff  = weaponData.getInt("falloff");

            JSONArray weaponBarrels = weaponData.getJSONArray("barrels");

            weaponDamage = weaponData.getJSONObject("damage");

            imgViewArme.setImageResource(weaponImageId);
            txtNomArme.setText(weaponData.getString("name"));

            for(int i = 0; i < weaponOperators.length(); i++)
            {
                String operatorId = weaponOperators.getString(i);

                String imgViewId = "imgAgent" + (i + 1);

                ImageView operatorImageView = findViewById(getResources().getIdentifier(imgViewId, "id", getPackageName()));
                int operatorImageId = getResources().getIdentifier("o_" + operatorId, "drawable", getPackageName());

                operatorImageView.setImageResource(operatorImageId);
                operatorImageView.setOnClickListener(new OnClickOperator(operatorId));
                operatorImageView.setVisibility(View.VISIBLE);
            }

            txtViewAmmo.setText(getStringFromDataInt(weaponAmmo));
            txtViewFirerate.setText(getStringFromDataInt(weaponFirerate));

            String weaponFalloffString = getStringFromDataInt(weaponFalloff) + "m";
            txtViewDamagefall.setText(weaponFalloffString);

            for(int i = 0; i < weaponBarrels.length(); i++)
            {
                String barrelId = weaponBarrels.getString(i);

                String intForId = String.valueOf(i + 1);
                LinearLayout barrelLayout = findViewById(getResources().getIdentifier("layoutBarrel" + intForId, "id", getPackageName()));
                ImageView barrelImageView = findViewById(getResources().getIdentifier("imgBarrel" + intForId, "id", getPackageName()));
                TextView barrelTextView = findViewById(getResources().getIdentifier("txtBarrel" + intForId, "id", getPackageName()));

                int barrelNameId = getResources().getIdentifier(barrelId, "string", getPackageName());
                int barrelImageId = getResources().getIdentifier("gb_" + barrelId, "drawable", getPackageName());

                barrelTextView.setText(getResources().getString(barrelNameId));
                barrelImageView.setImageResource(barrelImageId);

                barrelLayout.setVisibility(View.VISIBLE);
            }

            setDamageData(false);

            if(!weaponDamage.getJSONObject("armor_1").has("rook"))
            {
                switchRookArmor.setVisibility(View.INVISIBLE);
                imgRookArmor.setVisibility(View.INVISIBLE);
            }

            switchRookArmor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean checked)
                {
                    int drawRook = checked ? R.drawable.o_rook            : R.drawable.o_rook_bw;
                    int draw1    = checked ? R.drawable.armor_rook_light  : R.drawable.ostat_armor_1;
                    int draw2    = checked ? R.drawable.armor_rook_medium : R.drawable.ostat_armor_2;
                    int draw3    = checked ? R.drawable.armor_rook_strong : R.drawable.ostat_armor_3;

                    imgRookArmor.setImageResource(drawRook);
                    imgArmor1.setImageResource(draw1);
                    imgArmor2.setImageResource(draw2);
                    imgArmor3.setImageResource(draw3);

                    setDamageData(checked);
                }
            });
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    String getStringFromDataInt(int dataInt)
    {
        return dataInt > 0 ? String.valueOf(dataInt) : "-";
    }

    void setDamageData(boolean withRook)
    {
        try
        {
            String damageType = withRook ? "rook" : "default";

            JSONArray damageArmor1 = weaponDamage.getJSONObject("armor_1").getJSONArray(damageType);
            JSONArray damageArmor2 = weaponDamage.getJSONObject("armor_2").getJSONArray(damageType);
            JSONArray damageArmor3 = weaponDamage.getJSONObject("armor_3").getJSONArray(damageType);

            txtArmorL1.setText(String.valueOf(damageArmor1.get(0)));
            txtArmorB1.setText(String.valueOf(damageArmor1.get(1)));
            txtArmorL2.setText(String.valueOf(damageArmor2.get(0)));
            txtArmorB2.setText(String.valueOf(damageArmor2.get(1)));
            txtArmorL3.setText(String.valueOf(damageArmor3.get(0)));
            txtArmorB3.setText(String.valueOf(damageArmor3.get(1)));
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_stats, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:

                this.finish();
                return true;

            case R.id.action_help:
                startActivity(new Intent(this, HelpStatsActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class OnClickOperator implements View.OnClickListener
    {
        String operatorId;

        OnClickOperator(String operatorId)
        {
            super();
            this.operatorId = operatorId;
        }

        @Override
        public void onClick(View view)
        {
            Context context = view.getContext();
            Intent newOperator = new Intent(context, OperatorActivity.class);
            newOperator.putExtra("operatorId", operatorId);
            context.startActivity(newOperator);
        }
    }
}