package com.khrys.r6assistant;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

/*
 * Created by Chrysler on 3/10/2017.
 * RainbowSixAssistant
*/

public class WeaponActivity extends AppCompatActivity
{

    ImageView imgRookArmor, imgArmor1, imgArmor2, imgArmor3;
    TextView txtArmorL1,txtArmorB1,txtArmorL2, txtArmorB2, txtArmorL3, txtArmorB3;
    Object weapon[];

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

        weapon = new Object[]{"thatcher", "sledge", "", "", 30, 670, "27M", 33, 45, 30, 40, 23, 36, 40, 80, 30, 70, 20, 60, "suppressor","flash_hider","compensator","muzzle_break","extended_barrel"};

        switch (armeId)
        {
            case "l85a2":
                weapon = new Object[]{"thatcher", "sledge", "", "", 30, 670, "27M", 33, 45, 30, 40, 23, 36, 40, 80, 30, 70, 20, 60, "compensator","flash_hider","muzzle_break","suppressor",""};
                break;

            case "ar33":
                break;

            case "g36c":
                break;

            case "r4-c":
                break;

            case "556xi":
                weapon = new Object[]{"thermite", "sledge", "", "", 30, 670, "27M", 33, 45, 30, 40, 23, 36, 40, 80, 30, 70, 20, 60, "suppressor","flash_hider","compensator","muzzle_break","extended_barrel"};
                break;

            case "f2":
                break;

            case "ak-12":
                break;

            case "aug":
                break;

            case "552 commando":
                break;

            case "416-c carbine":
                break;

            case "c8-sfw":
                break;

            case "mk17 cqb":
                break;

            case "para 308":
                break;

            case "type 89":
                break;

            case "c7e":
                break;

            case "p226 mk 25":
                break;

            case "57 usg":
                break;

            case "m45 meusoc":
                break;

            case "p9":
                break;

            case "lfp586":
                break;

            case "gsh-18":
                break;

            case "pmm":
                break;

            case "p12":
                break;

            case "mk1":
                break;

            case "d-50":
                break;

            case "prb92":
                break;

            case "luison":
                break;

            case "p229":
                break;

            case "usp40":
                break;

            case "6p41":
                break;

            case "g8a1":
                break;

            case "m249":
                break;

            case "smg-11":
                break;

            case "bearing-9":
                break;

            case "417":
                break;

            case "ots-03":
                break;

            case "camrs":
                break;

            case "sr-25":
                break;

            case "m590a1":
                break;

            case "m1014":
                break;

            case "sg-cqb":
                break;

            case "sasg-12":

                break;
            case "m870":
                break;

            case "super 90":
                break;

            case "spas-12":
                break;

            case "spas-15":
                break;

            case "supernova":
                break;

            case "ita12l":
                break;

            case "ita12s":
                break;

            case "fmg-9":
                break;

            case "mp5k":
                break;

            case "ump45":
                break;

            case "mp5":
                break;

            case "p90":
                break;

            case "9x19vsn":
                break;

            case "mp7":
                break;

            case "c1":
                break;

            case "mpx":
                break;

            case "m12":
                break;

            case "mp5sd":
                break;

            case "pdw9":
                break;

            case "vector":
                weapon = new Object[]{"thermite", "sledge", "", "", 30, 670, "27M", 33, 45, 30, 40, 23, 36, 40, 80, 30, 70, 20, 60, "suppressor","flash_hider","compensator","muzzle_break","extended_barrel"};
                break;
        }

        setupAgent(0,imgViewOp1, weapon);
        setupAgent(1,imgViewOp2, weapon);
        setupAgent(2,imgViewOp3, weapon);
        setupAgent(3,imgViewOp4, weapon);

        txtViewAmmo.setText(String.valueOf(weapon[4]));
        txtViewFirerate.setText(String.valueOf(weapon[5]));
        txtViewDamagefall.setText(String.valueOf(weapon[6]));

        switchTextArmor(0);

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

    void setupAgent(int type, ImageView imgV, Object arme[])
    {
        if(!arme[type].equals(""))
        {
            String op = "o_"+String.valueOf(arme[type]);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

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
