package com.khrys.r6assistant.operators;
/*
 * Created by Louis on 31/05/2017.
 * 
 * R6Assistant
*/

import android.content.res.TypedArray;
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

import java.util.ArrayList;

@SuppressWarnings("ResourceType")
public class OperatorActivity extends AppCompatActivity
{
    String operator;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_operator);

        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        operator = getIntent().getStringExtra("operator");

        //ScrollView
        ScrollView scroll = (ScrollView) findViewById(R.id.globalScroll);
        scroll.smoothScrollTo(0,0);

        //Operator icon
        ImageView imgIcon = (ImageView) findViewById(R.id.iconOperator);

        //Operator name
        TextView txtName  = (TextView) findViewById(R.id.textName);

        //Side icon and name
        ImageView imgSide = (ImageView) findViewById(R.id.iconSide);
        TextView txtSide  = (TextView) findViewById(R.id.textSide);

        //Flag and army
        ImageView imgFlag = (ImageView) findViewById(R.id.iconFlag);
        TextView txtArmy  = (TextView) findViewById(R.id.textArmy);

        //Operator image
        ImageView imgOp = (ImageView) findViewById(R.id.imageOperator);

        //Speed stat section
        ImageView imgSpeed = (ImageView) findViewById(R.id.imageSpeed);
        TextView txtSpeed  = (TextView) findViewById(R.id.textSpeed);

        //Armor stat section
        ImageView imgArmor = (ImageView) findViewById(R.id.imageArmor);
        TextView txtArmor  = (TextView) findViewById(R.id.textArmor);

        //Primary weapon section
        TextView txtPrimary = (TextView) findViewById(R.id.textPrimary);
        RecyclerView rvPrim = (RecyclerView) findViewById(R.id.recyclerPrimary);

        //Secondary weapon section
        TextView txtSecondary = (TextView) findViewById(R.id.textSecondary);
        RecyclerView rvScd    = (RecyclerView) findViewById(R.id.recyclerSecondary);

        //Gadget section
        TextView txtNumGadget1  = (TextView) findViewById(R.id.txtNumGadget1);
        TextView txtNameGadget1 = (TextView) findViewById(R.id.txtNameGadget1);
        ImageView imgGadget1    = (ImageView) findViewById(R.id.imageGadget1);

        TextView txtNumGadget2  = (TextView) findViewById(R.id.txtNumGadget2);
        TextView txtNameGadget2 = (TextView) findViewById(R.id.txtNameGadget2);
        ImageView imgGadget2    = (ImageView) findViewById(R.id.imageGadget2);

        //Unique gadget
        TextView titleUniqueGadget = (TextView) findViewById(R.id.titleUniqueGadget);
        ImageView imgUniqueGadget = (ImageView) findViewById(R.id.imageUniqueGadget);
        TextView txtUniqueGadget = (TextView) findViewById(R.id.textUniqueGadget);

        ArrayList<String> namePrimWeapon = new ArrayList<>();
        ArrayList<Integer> picsPrimWeapon = new ArrayList<>();

        ArrayList<String> nameScdWeapon = new ArrayList<>();
        ArrayList<Integer> picsScdWeapon = new ArrayList<>();

        String operatorConvert = operator.replace('ä', 'a');
        operatorConvert = operatorConvert.replace('ã', 'a');

        TypedArray opArray = getResources().obtainTypedArray(getResources().getIdentifier(operatorConvert, "array", getApplicationContext().getPackageName()));

        String sideOp = opArray.getString(0);
        String flagOp = opArray.getString(1);
        String armyOp = opArray.getString(2);

        int numberWeapons = opArray.getInt(3, 0) + 5;
        int numberScd = opArray.getInt(4, 0) + 5;
        int numberSpeed = opArray.getInt(numberWeapons, 0);
        int numberArmor = opArray.getInt(numberWeapons+ 1, 0);

        String numberGadget1 = opArray.getString(numberWeapons + 2);
        String nameGadget1 = opArray.getString(numberWeapons + 3);

        String numberGadget2 = opArray.getString(numberWeapons + 4);
        String nameGadget2 = opArray.getString(numberWeapons + 5);

        int numberUniqueGadget = opArray.getInt(numberWeapons + 6, 0);

        for (int i = 5; i < numberScd; i++)
        {
            String weapon = opArray.getString(i);
            namePrimWeapon.add(weapon);

            picsPrimWeapon.add(getResources().getIdentifier(txtToIdImg(weapon), "drawable", getApplicationContext().getPackageName()));
        }

        for (int i = numberScd; i < numberWeapons; i++)
        {
            String weapon = opArray.getString(i);
            nameScdWeapon.add(weapon);

            picsScdWeapon.add(getResources().getIdentifier(txtToIdImg(weapon), "drawable", getApplicationContext().getPackageName()));
        }

        opArray.recycle();


        //Setup name and icon
        //
        int iconRess = getResources().getIdentifier("o_"+operatorConvert, "drawable", getApplicationContext().getPackageName());
        imgIcon.setImageResource(iconRess);

        txtName.setText(operator);


        //Setup side
        //
        int sideTxtId = 0;

        if(sideOp.equals("def"))
        {
            sideTxtId = R.string.defender;
        }
        else if(sideOp.equals("atk"))
        {
            sideTxtId = R.string.attacker;
        }

        int iconSide = getResources().getIdentifier("os_"+sideOp, "drawable", getApplicationContext().getPackageName());

        txtSide.setText(sideTxtId);
        imgSide.setImageResource(iconSide);


        //Setup flag and army
        //
        int iconFlag = getResources().getIdentifier("flag_"+flagOp, "drawable", getApplicationContext().getPackageName());

        txtArmy.setText(armyOp);
        imgFlag.setImageResource(iconFlag);


        //Setup armor
        //
        int armorTxt, armorImg;

        switch (numberArmor)
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

            default:
                armorTxt = 0;
                armorImg = 0;
        }

       txtArmor.setText(armorTxt);
       imgArmor.setImageResource(armorImg);


        //Setup image operator
        int imageOp = getResources().getIdentifier("oi_"+operatorConvert, "drawable", getApplicationContext().getPackageName());
        imgOp.setImageResource(imageOp);


        //Setup speed
        //
        int speedTxt, speedImg;

        switch (numberSpeed)
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

            default:
                speedTxt = 0;
                speedImg = 0;
        }

        txtSpeed.setText(speedTxt);
        imgSpeed.setImageResource(speedImg);


        //Setup weapons
        //
        if(!namePrimWeapon.isEmpty())
        {
            rvPrim.setAdapter(new WeaponsListAdapter(namePrimWeapon, picsPrimWeapon));
            rvPrim.setLayoutManager(new LinearLayoutManager(this));
        }
        else
        {
            rvPrim.setVisibility(View.GONE);
            txtPrimary.setVisibility(View.GONE);
        }

        if(!nameScdWeapon.isEmpty())
        {
            rvScd.setAdapter(new WeaponsListAdapter(nameScdWeapon, picsScdWeapon));
            rvScd.setLayoutManager(new LinearLayoutManager(this));
        }
        else
        {
            rvScd.setVisibility(View.GONE);
            txtSecondary.setVisibility(View.GONE);
        }


        //Setup gadget
        //
        String idGadget1 = "gad_"+nameGadget1;
        String idGadget2 = "gad_"+nameGadget2;

        int imgeGadget1 = getResources().getIdentifier(idGadget1, "drawable", getPackageName());
        int imageGadget2 = getResources().getIdentifier(idGadget2, "drawable", getPackageName());

        int textGadget1 = getResources().getIdentifier(idGadget1, "string", getPackageName());
        int textGadget2 = getResources().getIdentifier(idGadget2, "string", getPackageName());

        txtNumGadget1.setText(numberGadget1);
        txtNameGadget1.setText(getString(textGadget1));

        txtNumGadget2.setText(numberGadget2);
        txtNameGadget2.setText(getString(textGadget2));

        imgGadget1.setImageResource(imgeGadget1);
        imgGadget2.setImageResource(imageGadget2);

        //Setup unique gadget
        //

        String idGadget = "ga_"+operatorConvert;
        String idTitleGadget = "ugat_"+operatorConvert;
        String idDescGadget = "uga_"+operatorConvert;

        int titleUnGadget = getResources().getIdentifier(idTitleGadget, "string", getPackageName());
        int imgUnGadget = getResources().getIdentifier(idGadget, "drawable", getPackageName());
        int txtUnGadget = getResources().getIdentifier(idDescGadget, "string", getPackageName());

        String titleGadgetString = getString(titleUnGadget);
        if(numberUniqueGadget != 0)
        {
            titleGadgetString += " x"+numberUniqueGadget;
        }

        titleUniqueGadget.setText(titleGadgetString);
        imgUniqueGadget.setImageResource(imgUnGadget);
        txtUniqueGadget.setText(txtUnGadget);
    }

    String txtToIdImg(String txtToEdit)
    {
        String imgId = "g_"+txtToEdit;
        imgId = imgId.replace(' ', '_');
        imgId = imgId.replace('-', '_');
        return imgId;
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