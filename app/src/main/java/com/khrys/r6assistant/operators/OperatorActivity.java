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
import android.view.View;
import android.widget.TextView;

import com.khrys.r6assistant.R;

import java.util.ArrayList;

public class OperatorActivity extends AppCompatActivity
{
    String operator;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_operator);

        operator = getIntent().getStringExtra("operator");

        RecyclerView rvPrim = (RecyclerView) findViewById(R.id.recyclerPrimary);
        RecyclerView rvScd = (RecyclerView) findViewById(R.id.recyclerSecondary);

        TextView txtPrimary = (TextView) findViewById(R.id.textPrimary);
        TextView txtSecondary = (TextView) findViewById(R.id.textSecondary);

        ArrayList<String> namePrimWeapon = new ArrayList<>();
        ArrayList<Integer> picsPrimWeapon = new ArrayList<>();

        ArrayList<String> nameScdWeapon = new ArrayList<>();
        ArrayList<Integer> picsScdWeapon = new ArrayList<>();

        String operatorConvert = operator.replace('ä','a');
        operatorConvert = operatorConvert.replace('ã','a');

        TypedArray weaponArray = getResources().obtainTypedArray(getResources().getIdentifier(operatorConvert, "array", getApplicationContext().getPackageName()));

        int numberPrim = weaponArray.getInt(0,0)+1;

        for(int i = 1; i < numberPrim; i++)
        {
            String weapon = weaponArray.getString(i);
            namePrimWeapon.add(weapon);

            String imgId = "g_"+weapon;
            imgId = imgId.replace(' ', '_');
            imgId = imgId.replace('-', '_');

            picsPrimWeapon.add(getResources().getIdentifier(imgId,"drawable",getApplicationContext().getPackageName()));
        }

        for(int i = numberPrim; i < weaponArray.length(); i++)
        {
            String weapon = weaponArray.getString(i);
            nameScdWeapon.add(weapon);

            String imgId = "g_"+weapon;
            imgId = imgId.replace(' ', '_');
            imgId = imgId.replace('-', '_');

            picsScdWeapon.add(getResources().getIdentifier(imgId,"drawable",getApplicationContext().getPackageName()));
        }

        weaponArray.recycle();

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
    }
}