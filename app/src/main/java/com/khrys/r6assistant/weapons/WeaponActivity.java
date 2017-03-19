package com.khrys.r6assistant.weapons;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.khrys.r6assistant.R;

/*
 * Created by Chrysler on 3/10/2017.
 * RainbowSixAssistant
*/

public class WeaponActivity extends AppCompatActivity
{

    ImageView imgRookArmor, imgArmor1, imgArmor2, imgArmor3;
    TextView txtArmorL1,txtArmorB1,txtArmorL2, txtArmorB2, txtArmorL3, txtArmorB3;
    Object weapon[];
    LinearLayout layoutBarrel1,layoutBarrel2,layoutBarrel3,layoutBarrel4,layoutBarrel5;

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
        TextView txtNomArme = (TextView) findViewById(R.id.txtNomArme);
        ImageView imgViewArme = (ImageView) findViewById(R.id.imgArme);

        //Operators image
        ImageView imgViewOp1 = (ImageView) findViewById(R.id.imgAgent1);
        ImageView imgViewOp2 = (ImageView) findViewById(R.id.imgAgent2);
        ImageView imgViewOp3 = (ImageView) findViewById(R.id.imgAgent3);
        ImageView imgViewOp4 = (ImageView) findViewById(R.id.imgAgent4);

        //Basic informations
        TextView txtViewAmmo = (TextView) findViewById(R.id.txtAmmo);
        TextView txtViewFirerate = (TextView) findViewById(R.id.txtFirerate);
        TextView txtViewDamagefall = (TextView) findViewById(R.id.txtDamagefalloff);

        /*
            ************
            Damage stats
        */

        //Rook Amor
        Switch switchRookArmor = (Switch) findViewById(R.id.switchRookarmor);
        imgRookArmor = (ImageView) findViewById(R.id.imgRookarmor);

        //Armor image
        imgArmor1 = (ImageView) findViewById(R.id.imgArmor1);
        imgArmor2 = (ImageView) findViewById(R.id.imgArmor2);
        imgArmor3 = (ImageView) findViewById(R.id.imgArmor3);

        //Armor text
        txtArmorL1 = (TextView) findViewById(R.id.txtArmorL1);
        txtArmorB1 = (TextView) findViewById(R.id.txtArmorB1);
        txtArmorL2 = (TextView) findViewById(R.id.txtArmorL2);
        txtArmorB2 = (TextView) findViewById(R.id.txtArmorB2);
        txtArmorL3 = (TextView) findViewById(R.id.txtArmorL3);
        txtArmorB3 = (TextView) findViewById(R.id.txtArmorB3);

        //Barrel Layouts
        layoutBarrel1 = (LinearLayout) findViewById(R.id.layoutBarrel1);
        layoutBarrel2 = (LinearLayout) findViewById(R.id.layoutBarrel2);
        layoutBarrel3 = (LinearLayout) findViewById(R.id.layoutBarrel3);
        layoutBarrel4 = (LinearLayout) findViewById(R.id.layoutBarrel4);
        layoutBarrel5 = (LinearLayout) findViewById(R.id.layoutBarrel5);

        //Barrel infos
        ImageView imgBarrel1 = (ImageView) findViewById(R.id.imgBarrel1);
        ImageView imgBarrel2 = (ImageView) findViewById(R.id.imgBarrel2);
        ImageView imgBarrel3 = (ImageView) findViewById(R.id.imgBarrel3);
        ImageView imgBarrel4 = (ImageView) findViewById(R.id.imgBarrel4);
        ImageView imgBarrel5 = (ImageView) findViewById(R.id.imgBarrel5);

        TextView txtBarrel1 = (TextView) findViewById(R.id.txtBarrel1);
        TextView txtBarrel2 = (TextView) findViewById(R.id.txtBarrel2);
        TextView txtBarrel3 = (TextView) findViewById(R.id.txtBarrel3);
        TextView txtBarrel4 = (TextView) findViewById(R.id.txtBarrel4);
        TextView txtBarrel5 = (TextView) findViewById(R.id.txtBarrel5);

        /*
            ************
        */

        String armeId = getIntent().getStringExtra("arme");

        String imgArmeId = "g_"+armeId;
        imgArmeId = imgArmeId.replace('-','_');
        imgArmeId = imgArmeId.replace(' ','_');

        int resID = getResources().getIdentifier(imgArmeId, "drawable", getPackageName());

        imgViewArme.setImageResource(resID);
        txtNomArme.setText(armeId);

        weapon = new Object[]{"", "", "", "", 0, 0, "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "","","","",""};

        switch (armeId)
        {
            case "l85a2":
                weapon = new Object[]{"thatcher", "sledge", "", "", 30, 670, "27m", 33, 45, 30, 40, 23, 36, 28, 38, 24, 33, 18, 28, "compensator","flash_hider","muzzle_brake","suppressor",""};
                break;

            case "ar33":
                weapon = new Object[]{"thatcher", "", "", "", 25, 749, "15m", 31, 42, 24, 38, 21, 33, 26, 35, 23, 31, 17, 27, "suppressor","compensator","flash_hider","muzzle_brake",""};
                break;

            case "g36c":
                weapon = new Object[]{"ash", "", "", "", 30, 780, "27m", 28, 38, 25, 34, 19, 30, 23, 31, 21, 28, 15, 24, "compensator","suppressor","muzzle_brake","flash_hider",""};
                break;

            case "r4-c":
                weapon = new Object[]{"ash", "", "", "", 30, 860, "20m", 30, 41, 27, 37, 21, 33, 26, 35, 22, 30, 16, 26, "compensator","suppressor","flash_hider","muzzle_brake",""};
                break;

            case "556xi":
                weapon = new Object[]{"thermite", "", "", "", 30, 670, "27m", 33, 45, 30, 40, 23, 36, 29, 39, 25, 34, 18, 29, "compensator","muzzle_brake","suppressor","flash_hider",""};
                break;

            case "f2":
                weapon = new Object[]{"twitch", "", "", "", 30, 980, "20m", 29, 39, 26, 35, 20, 31, 24, 33, 21, 29, 16, 25, "flash_hider","muzzle_brake","compensator","suppressor",""};
                break;

            case "ak-12":
                weapon = new Object[]{"fuze", "", "", "", 30, 850, "23m", 33, 44, 29, 39, 22, 35, 27, 37, 24, 32, 18, 28, "compensator","flash_hider","muzzle_brake","suppressor",""};
                break;

            case "aug":
                weapon = new Object[]{"iq", "", "", "", 30, 720, "25m", 30, 41, 27, 37, 21, 33, 26, 35, 22, 30, 16, 26, "compensator","flash_hider","suppressor","",""};
                break;

            case "552 commando":
                weapon = new Object[]{"iq", "", "", "", 30, 690, "20m", 35, 47, 27, 42, 24, 37, 29, 39, 26, 35, 19, 30, "flash_hider","compensator","suppressor","muzzle_brake",""};
                break;

            case "416-c carbine":
                weapon = new Object[]{"jager", "", "", "", 30, 740, "20m", 31, 42, 28, 38, 21, 33, 0, 0, 0, 0, 0, 0, "compensator","extended_barrel","flash_hider","suppressor","muzzle_brake"};
                break;

            case "c8-sfw":
                weapon = new Object[]{"buck", "", "", "", 30, 837, "20m", 28, 38, 25, 34, 19, 30, 24, 32, 21, 28, 15, 24, "muzzle_brake","compensator","flash_hider","suppressor",""};
                break;

            case "mk17 cqb":
                weapon = new Object[]{"blackbeard", "", "", "", 21, 585, "21m", 30, 41, 27, 37, 21, 33, 24, 32, 22, 30, 16, 26, "flash_hider","muzzle_brake","compensator","suppressor",""};
                break;

            case "para-308":
                weapon = new Object[]{"capitao", "", "", "", 30, 650, "28m", 31, 42, 28, 38, 21, 33, 26, 35, 23, 31, 16, 26, "compensator","flash_hider","suppressor","muzzle_brake",""};
                break;

            case "type 89":
                weapon = new Object[]{"hibana", "", "", "", 20, 850, "20m", 30, 41, 27, 37, 21, 33, 25, 34, 22, 30, 16, 26, "compensator","suppressor","flash_hider","muzzle_brake",""};
                break;

            case "c7e":
                weapon = new Object[]{"jackal", "", "", "", 30, 800, "20m", 34, 46, 30, 41, 23, 36, 28, 39, 24, 34, 18, 28, "compensator","flash_hider","suppressor","muzzle_brake",""};
                break;

            case "p226 mk 25":
                weapon = new Object[]{"sledge", "thatcher","smoke", "mute", 15, 420, "8m", 39, 53, 35, 47, 27, 42, 0, 0, 0, 0, 0, 0, "","","","",""};
                break;

            case "57 usg":
                weapon = new Object[]{"ash", "thermite","castle", "pulse", 20, 400, "18m", 19, 26, 17, 23, 13, 21, 0, 0, 0, 0, 0, 0, "","","","",""};
                break;

            case "m45 meusoc":
                weapon = new Object[]{"ash", "thermite","castle", "pulse", 7, 360, "15m", 35, 47, 31, 42, 24, 37, 0, 0, 0, 0, 0, 0, "","","","",""};
                break;

            case "p9":
                weapon = new Object[]{"twitch", "montagne","doc", "rook", 16, 360, "20m", 22, 30, 20, 27, 15, 24, 18, 25, 16, 22, 12, 19, "","","","",""};
                break;

            case "lfp586":
                weapon = new Object[]{"twitch", "montagne","doc", "rook", 6, 360, "15m", 67, 76, 51, 68, 39, 60, 48, 64, 42, 56, 31, 48, "","","","",""};
                break;

            case "gsh-18":
                weapon = new Object[]{"glaz", "fuze","kapkan", "tachanka", 18, 360, "18m", 24, 33, 21, 29, 16, 26, 0, 0, 0, 0, 0, 0, "","","","",""};
                break;

            case "pmm":
                weapon = new Object[]{"glaz", "fuze","kapkan", "tachanka", 8, 420, "5m", 47, 63, 42, 56, 32, 50, 0, 0, 0, 0, 0, 0, "","","","",""};
                break;

            case "p12":
                weapon = new Object[]{"blitz", "iq", "jager", "bandit", 15, 420, "8m", 32, 43, 29, 39, 22, 34, 27, 36, 24, 32, 17, 27, "","","","",""};
                break;

            case "mk1":
                weapon = new Object[]{"buck", "frost", "", "", 13, 360, "25m", 32, 43, 29, 39, 22, 34, 0, 0, 0, 0, 0, 0, "","","","",""};
                break;

            case "d-50":
                weapon = new Object[]{"blackbeard", "valkyrie", "", "", 7, 0, "18m", 51, 68, 45, 61, 35, 54, 42, 57, 37, 50, 27, 43, "","","","",""};
                break;

            case "prb92":
                weapon = new Object[]{"capitao", "", "", "", 15, 0, "18m", 29, 39, 26, 35, 20, 31, 24, 33, 21, 29, 16, 25, "","","","",""};
                break;

            case "luison":
                weapon = new Object[]{"caveira", "", "", "", 15, 0, "3m", 66, 99, 66, 89, 50, 78, 0, 0, 0, 0, 0, 0, "","","","",""};
                break;

            case "p229":
                weapon = new Object[]{"hibana", "echo", "", "", 12, 0, "10m", 37, 50, 33, 45, 25, 39, 0, 0, 0, 0, 0, 0, "","","","",""};
                break;

            case "usp40":
                weapon = new Object[]{"jackal", "mira", "", "", 12, 0, "", 32, 43, 29, 39, 22, 34, 0, 0, 0, 0, 0, 0, "","","","",""};
                break;

            case "6p41":
                weapon = new Object[]{"fuze", "", "", "", 100, 680, "8m", 36, 49, 33, 44, 25, 39, 30, 40, 27, 35, 20, 31, "suppressor","flash_hider","","",""};
                break;

            case "g8a1":
                weapon = new Object[]{"iq", "", "", "", 50, 850, "16m", 27, 37, 24, 33, 18, 29, 23, 31, 20, 27, 14, 23, "compensator","flash_hider","suppressor","",""};
                break;

            case "m249":
                weapon = new Object[]{"capitao", "", "", "", 100, 650, "10m", 24, 33, 20, 29, 16, 26, 20, 27, 18, 24, 13, 21, "compensator","flash_hider","","",""};
                break;

            case "smg-11":
                weapon = new Object[]{"smoke", "sledge", "", "", 16, 1270, "15m", 24, 32, 21, 28, 16, 25, 0, 0, 0, 0, 0, 0, "compensator","extended_barrel","flash_hider","suppressor",""};
                break;

            case "bearing-9":
                weapon = new Object[]{"hibana", "echo", "", "", 25, 1100, "15m", 24, 32, 21, 28, 16, 25, 0, 0, 0, 0, 0, 0, "","","","",""};
                break;

            case "417":
                weapon = new Object[]{"twitch", "", "", "", 12, 660, "30m", 49, 66, 44, 59, 33, 52, 41, 55, 35, 48, 26, 42, "muzzle_brake","flash_hider","compensator","suppressor",""};
                break;

            case "ots-03":
                weapon = new Object[]{"glaz", "", "", "", 10, 0, "5m", 170, 170, 106, 164, 78, 121, 150, 100, 84, 130, 62, 96, "muzzle_brake","flash_hider","suppressor","",""};
                break;

            case "camrs":
                weapon = new Object[]{"buck", "", "", "", 20, 570, "30m", 51, 68, 45, 61, 35, 54, 43, 57, 36, 50, 27, 43, "muzzle_brake","flash_hider","compensator","suppressor",""};
                break;

            case "sr-25":
                weapon = new Object[]{"blackbeard", "", "", "", 20, 0, "35m", 53, 71, 47, 63, 36, 56, 44, 59, 39, 52, 29, 45, "muzzle_brake","flash_hider","compensator","suppressor",""};
                break;

            case "m590a1":
                weapon = new Object[]{"sledge", "thatcher", "smoke", "mute", 7, 0, "8m", 34, 34, 22, 22, 14, 14, 0, 0, 0, 0, 0, 0, "","","","",""};
                break;

            case "m1014":
                weapon = new Object[]{"thermite", "castle", "pulse", "", 8, 0, "8m", 36, 36, 24, 24, 22, 22, 0, 0, 0, 0, 0, 0, "","","","",""};
                break;

            case "sg-cqb":
                weapon = new Object[]{"twitch", "doc", "rook", "", 7, 0, "8m", 32, 32, 22, 22, 21, 21, 0, 0, 0, 0, 0, 0, "","","","",""};
                break;

            case "sasg-12":
                weapon = new Object[]{"kapkan", "tachanka", "", "", 10, 0, "8m", 19, 19, 17, 17, 14, 14, 0, 0, 0, 0, 0, 0, "","","","",""};

                break;
            case "m870":
                weapon = new Object[]{"jager", "bandit", "", "", 7, 0, "8m", 31, 31, 21, 21, 18, 18, 0, 0, 0, 0, 0, 0, "","","","",""};
                break;

            case "super 90":
                weapon = new Object[]{"frost", "", "", "", 8, 0, "8m", 36, 36, 31, 31, 22, 22, 0, 0, 0, 0, 0, 0, "","","","",""};
                break;

            case "spas-12":
                weapon = new Object[]{"valkyrie", "", "", "", 7, 0, "8m", 32, 32, 28, 28, 20, 20, 0, 0, 0, 0, 0, 0, "","","","",""};
                break;

            case "spas-15":
                weapon = new Object[]{"caveira", "", "", "", 6, 0, "8m", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "","","","",""};
                break;

            case "supernova":
                weapon = new Object[]{"hibana", "echo", "", "", 7, 0, "8m", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "","","","",""};
                break;

            case "ita12l":
                weapon = new Object[]{"jackal", "mira", "", "", 8, 0, "8m", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "","","","",""};
                break;

            case "ita12s":
                weapon = new Object[]{"jackal", "mira", "", "", 5, 0, "8m", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "","","","",""};
                break;

            case "fmg-9":
                weapon = new Object[]{"smoke", "", "", "", 30, 800, "17m", 21, 28, 18, 25, 14, 22, 0, 0, 0, 0, 0, 0, "flash_hider","suppressor","muzzle_brake","",""};
                break;

            case "mp5k":
                weapon = new Object[]{"mute", "", "", "", 30, 800, "15m", 21, 29, 19, 26, 14, 23, 0, 0, 0, 0, 0, 0, "compensator","flash_hider","muzzle_brake","suppressor",""};
                break;

            case "ump45":
                weapon = new Object[]{"castle", "pulse", "", "", 25, 600, "15m", 28, 38, 25, 34, 19, 30, 0, 0, 0, 0, 0, 0, "flash_hider","compensator","extended_barrel","muzzle_brake","suppressor"};
                break;

            case "mp5":
                weapon = new Object[]{"doc", "rook", "", "", 30, 800, "18m", 21, 28, 17, 25, 14, 22, 0, 0, 0, 0, 0, 0, "flash_hider","suppressor","muzzle_brake","",""};
                break;

            case "p90":
                weapon = new Object[]{"doc", "rook", "", "", 50, 970, "18m", 15, 20, 13, 18, 10, 16, 0, 0, 0, 0, 0, 0, "flash_hider","muzzle_brake","suppressor","extended_barrel",""};
                break;

            case "9x19vsn":
                weapon = new Object[]{"kapkan", "tachanka", "", "", 30, 750, "15m", 25, 34, 22, 30, 17, 27, 0, 0, 0, 0, 0, 0, "compensator","flash_hider","muzzle_brake","suppressor",""};
                break;

            case "mp7":
                weapon = new Object[]{"bandit", "", "", "", 30, 900, "10m", 25, 34, 22, 30, 17, 27, 0, 0, 0, 0, 0, 0, "compensator","flash_hider","muzzle_brake","suppressor",""};
                break;

            case "c1":
                weapon = new Object[]{"frost", "", "", "", 35, 575, "15m", 31, 42, 32, 38, 21, 33, 0, 0, 0, 0, 0, 0, "extended_barrel","suppressor","","",""};
                break;

            case "mpx":
                weapon = new Object[]{"valkyrie", "", "", "", 31, 830, "23m", 17, 23, 15, 21, 11, 18, 0, 0, 0, 0, 0, 0, "muzzle_brake","flash_hider","compensator","suppressor",""};
                break;

            case "m12":
                weapon = new Object[]{"caveira", "", "", "", 30, 550, "20m", 27, 36, 24, 32, 18, 28, 0, 0, 0, 0, 0, 0, "extended_barrel","suppressor","muzzle_brake","flash_hider",""};
                break;

            case "mp5sd":
                weapon = new Object[]{"echo", "", "", "", 30, 800, "10m", 17, 23, 15, 21, 11, 18, 0, 0, 0, 0, 0, 0, "","","","",""};
                break;

            case "pdw9":
                weapon = new Object[]{"jackal", "", "", "", 50, 800, "28m", 24, 33, 21, 29, 16, 26, 0, 0, 0, 0, 0, 0, "compensator","flash_hider","suppressor","muzzle_brake",""};
                break;

            case "vector":
                weapon = new Object[]{"mira", "", "", "", 30, 1200, "5m", 17, 23, 15, 21, 11, 18, 0, 0, 0, 0, 0, 0, "compensator","suppressor","muzzle_brake","flash_hider",""};
                break;
        }

        setupAgent(0,imgViewOp1);
        setupAgent(1,imgViewOp2);
        setupAgent(2,imgViewOp3);
        setupAgent(3,imgViewOp4);

        if(weapon[4].equals(0))
        {
            txtViewAmmo.setText("-");
        }
        else
        {
            txtViewAmmo.setText(String.valueOf(weapon[4]));
        }

        if(weapon[5].equals(0))
        {
            txtViewFirerate.setText("-");
        }
        else
        {
            txtViewFirerate.setText(String.valueOf(weapon[5]));
        }

        if(String.valueOf(weapon[6]).equals(""))
        {
            txtViewDamagefall.setText("-");
        }
        else
        {
            txtViewDamagefall.setText(String.valueOf(weapon[6]));
        }

        switchTextArmor(0);

        if(weapon[13].equals(0))
        {
            switchRookArmor.setVisibility(View.INVISIBLE);
            imgRookArmor.setVisibility(View.INVISIBLE);
        }

        switchRookArmor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                int drawRook, draw1, draw2, draw3;
                if(b)
                {
                    drawRook = R.drawable.o_rook;
                    draw1 = R.drawable.armor_rook_light;
                    draw2 = R.drawable.armor_rook_medium;
                    draw3 = R.drawable.armor_rook_strong;
                    switchTextArmor(1);
                }
                else
                {
                    drawRook = R.drawable.o_rook_bw;
                    draw1 = R.drawable.armor_light;
                    draw2 = R.drawable.armor_medium;
                    draw3 = R.drawable.armor_strong;
                    switchTextArmor(0);
                }

                imgRookArmor.setImageResource(drawRook);
                imgArmor1.setImageResource(draw1);
                imgArmor2.setImageResource(draw2);
                imgArmor3.setImageResource(draw3);

            }
        });

        setupBarrel(19, layoutBarrel1, imgBarrel1, txtBarrel1);
        setupBarrel(20, layoutBarrel2, imgBarrel2, txtBarrel2);
        setupBarrel(21, layoutBarrel3, imgBarrel3, txtBarrel3);
        setupBarrel(22, layoutBarrel4, imgBarrel4, txtBarrel4);
        setupBarrel(23, layoutBarrel5, imgBarrel5, txtBarrel5);
    }

    void switchTextArmor(int type)
    {
        if(type == 0)
        {
            txtArmorL1.setText(String.valueOf(weapon[7]));
            txtArmorB1.setText(String.valueOf(weapon[8]));
            txtArmorL2.setText(String.valueOf(weapon[9]));
            txtArmorB2.setText(String.valueOf(weapon[10]));
            txtArmorL3.setText(String.valueOf(weapon[11]));
            txtArmorB3.setText(String.valueOf(weapon[12]));
        } else {
            txtArmorL1.setText(String.valueOf(weapon[13]));
            txtArmorB1.setText(String.valueOf(weapon[14]));
            txtArmorL2.setText(String.valueOf(weapon[15]));
            txtArmorB2.setText(String.valueOf(weapon[16]));
            txtArmorL3.setText(String.valueOf(weapon[17]));
            txtArmorB3.setText(String.valueOf(weapon[18]));
        }

    }

    void setupAgent(int type, ImageView imgV)
    {
        if(!weapon[type].equals(""))
        {
            String op = "o_"+String.valueOf(weapon[type]);
            int imgid = getResources().getIdentifier(op, "drawable", getPackageName());
            imgV.setImageResource(imgid);
        }
        else
        {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)
            imgV.getLayoutParams();
            params.weight = 0.0f;
            imgV.setLayoutParams(params);
            imgV.getLayoutParams().width= ViewGroup.LayoutParams.WRAP_CONTENT;
        }
    }

    void setupBarrel(int type, LinearLayout layoutV, ImageView imgV, TextView txtV)
    {
        if(!weapon[type].equals(""))
        {
            String finaltxt = String.valueOf(weapon[type]).toUpperCase();
            txtV.setText(finaltxt.replace('_',' '));

            String txt = String.valueOf(weapon[type]);
            String bar = "gb_"+txt;
            int imgid = getResources().getIdentifier(bar, "drawable", getPackageName());
            imgV.setImageResource(imgid);
        }
        else
        {
            layoutV.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_stats, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

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
}