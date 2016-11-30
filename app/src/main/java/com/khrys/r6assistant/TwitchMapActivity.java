package com.khrys.r6assistant;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;


/**
 * Created by Chrysler on 10/3/2016.
 * <p>
 * RainbowSixPartner
 */

public class TwitchMapActivity extends Activity implements View.OnClickListener {

    TextView txtValkyrie, txtBandit,txtMute,txtJager,txtKapkan;

    ImageButton buttonValkyrie,buttonBandit,buttonMute,buttonJager,buttonKapkan;

    int type = 2, gadgetV = 3, gadgetB = 3, gadgetM = 4, gadgetJ = 3, gadgetK = 3;

    String map;

    RecyclerView rv;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitchmap);

        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        context = getApplicationContext();

        map = getIntent().getStringExtra("nommap");
        int mapID = getIntent().getIntExtra("pos", 0);

        ArrayList<Integer> pics = new ArrayList<>();
        ArrayList<Integer> poscam = new ArrayList<>();

        MapSwitch mapSwitch = new MapSwitch(pics, poscam, mapID);
        poscam = mapSwitch.SwitchPos();
        pics = mapSwitch.SwitcherPics();

        rv = (RecyclerView) findViewById(R.id.recyclerViewMap);
        LayoutIfManager layoutIM = new LayoutIfManager(context);
        rv.setAdapter(new ListAdapterMap(pics,poscam, type));
        rv.setLayoutManager(layoutIM.ReturnManager());


        TextView txtMap = (TextView) findViewById(R.id.textViewMapName);

        txtValkyrie = (TextView) findViewById(R.id.textViewValkyrie);
        txtBandit = (TextView) findViewById(R.id.textViewBandit);
        txtMute = (TextView) findViewById(R.id.textViewMute);
        txtJager = (TextView) findViewById(R.id.textViewJager);
        txtKapkan = (TextView) findViewById(R.id.textViewKapkan);

        textSetup(gadgetV, txtValkyrie);
        textSetup(gadgetB, txtBandit);
        textSetup(gadgetM, txtMute);
        textSetup(gadgetJ, txtJager);
        textSetup(gadgetK, txtKapkan);

        buttonValkyrie = (ImageButton) findViewById(R.id.buttonDValkyrie);
        buttonBandit = (ImageButton) findViewById(R.id.buttonDBandit);
        buttonMute = (ImageButton) findViewById(R.id.buttonDMute);
        buttonJager = (ImageButton) findViewById(R.id.buttonDJager);
        buttonKapkan = (ImageButton) findViewById(R.id.buttonDKapkan);

        buttonBandit.setOnClickListener(this);
        buttonJager.setOnClickListener(this);
        buttonMute.setOnClickListener(this);
        buttonValkyrie.setOnClickListener(this);
        buttonKapkan.setOnClickListener(this);

        txtMap.setText(map);

    }

    void textSetup(int gadget, TextView txtView)
    {
        String txtstr;
        txtstr = String.format(getResources().getString(R.string.destroyPhrase), gadget);
        txtView.setText(txtstr);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case(R.id.buttonDValkyrie):
                new algoButton(context, --gadgetV, txtValkyrie);
                break;

            case(R.id.buttonDBandit):
                new algoButton(context, --gadgetB, txtBandit);
                break;

            case(R.id.buttonDJager):
                new algoButton(context, --gadgetJ, txtJager);
                break;

            case(R.id.buttonDKapkan):
                new algoButton(context, --gadgetK, txtKapkan);
                break;

            case(R.id.buttonDMute):
                new algoButton(context, --gadgetM, txtMute);
                break;
        }
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:

                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

class algoButton
{
    algoButton(Context context, int gadget, TextView textView)
    {
        String txtF;

        if(check0(gadget)) {
            txtF = String.format(context.getResources().getString(R.string.destroyPhrase), gadget);
            textView.setText(txtF);
        } else {
            textView.setText(R.string.destroyed);
        }
    }

    private boolean check0(int gadget){
        return gadget > 0;
    }
}