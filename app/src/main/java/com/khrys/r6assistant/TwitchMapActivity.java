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


/*
 * Created by Chrysler on 10/3/2016.
 * RainbowSixPartner
*/

public class TwitchMapActivity extends Activity implements View.OnClickListener, ChangeCamRemInterface
{

    TextView txtValkyrie, txtBandit,txtMute,txtJager,txtKapkan, txtCamera;

    ImageButton buttonValkyrie,buttonBandit,buttonMute,buttonJager,buttonKapkan, refreshBut;

    int type = 2, gadgetV, gadgetB, gadgetM, gadgetJ, gadgetK, mapID;

    String map;

    RecyclerView rv;

    ListAdapterMap listAdapterMap;

    MapSwitch mapSwitch;

    Context context;

    ArrayList<Integer> pics = new ArrayList<>();
    ArrayList<Integer> poscam = new ArrayList<>();

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
        mapID = getIntent().getIntExtra("pos", 0);

        recyclerSetup();

        listAdapterMap = new ListAdapterMap(pics,poscam, type, this);

        rv = (RecyclerView) findViewById(R.id.recyclerViewMap);
        LayoutIfManager layoutIM = new LayoutIfManager(context);
        rv.setAdapter(listAdapterMap);
        rv.setLayoutManager(layoutIM.ReturnManager());

        TextView txtMap = (TextView) findViewById(R.id.textViewMapName);

        txtValkyrie = (TextView) findViewById(R.id.textViewValkyrie);
        txtBandit = (TextView) findViewById(R.id.textViewBandit);
        txtMute = (TextView) findViewById(R.id.textViewMute);
        txtJager = (TextView) findViewById(R.id.textViewJager);
        txtKapkan = (TextView) findViewById(R.id.textViewKapkan);

        txtCamera = (TextView) findViewById(R.id.textViewCamRem);

        notifyCamRem(pics.size());

        defaultTxtSetup();

        buttonValkyrie = (ImageButton) findViewById(R.id.buttonDValkyrie);
        buttonBandit = (ImageButton) findViewById(R.id.buttonDBandit);
        buttonMute = (ImageButton) findViewById(R.id.buttonDMute);
        buttonJager = (ImageButton) findViewById(R.id.buttonDJager);
        buttonKapkan = (ImageButton) findViewById(R.id.buttonDKapkan);

        refreshBut = (ImageButton) findViewById(R.id.TwitchRefreshButton);

        refreshBut.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                defaultTxtSetup();
                pics.clear();
                poscam.clear();
                recyclerSetup();
                rv.removeAllViews();
                rv.setAdapter(listAdapterMap);
                notifyCamRem(pics.size());
            }
        });

        buttonBandit.setOnClickListener(this);
        buttonJager.setOnClickListener(this);
        buttonMute.setOnClickListener(this);
        buttonValkyrie.setOnClickListener(this);
        buttonKapkan.setOnClickListener(this);

        txtMap.setText(map);

    }

    void defaultTxtSetup()
    {
        gadgetV = 3;
        gadgetB = 4;
        gadgetM = 4;
        gadgetJ = 3;
        gadgetK = 3;
        textSetup(gadgetV, txtValkyrie);
        textSetup(gadgetB, txtBandit);
        textSetup(gadgetM, txtMute);
        textSetup(gadgetJ, txtJager);
        textSetup(gadgetK, txtKapkan);
    }

    void textSetup(int gadget, TextView txtView)
    {
        String txtstr;
        txtstr = String.format(getResources().getString(R.string.destroyPhrase), gadget);
        txtView.setText(txtstr);
    }

    void recyclerSetup()
    {
        String arrayMapid = "m"+String.valueOf(mapID);
        int arrayId = getResources().getIdentifier(arrayMapid, "array", getApplicationContext().getPackageName());

        String[] infoMap = getResources().getStringArray(arrayId);
        int nbCamera = Integer.parseInt(infoMap[1]);

        for(int i = 1; i <= nbCamera; i++)
        {
            String idName = infoMap[0]+"_cam_s"+String.valueOf(i);
            String idPic = "cam_"+infoMap[0]+"_"+String.valueOf(i);
            int intName = getResources().getIdentifier(idName, "string", getApplicationContext().getPackageName());
            int intPic = getResources().getIdentifier(idPic, "drawable", getApplicationContext().getPackageName());
            poscam.add(intName);
            pics.add(intPic);
        }
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

    @Override
    public void notifyCamRem(int count)
    {
        String txtCam;
        if(count == 1)
        {
            txtCam = String.format(getResources().getString(R.string.cameraPhrase2_1), count);
            txtCamera.setText(txtCam);
        }
        else if(count <= 0)
        {
            txtCam = getResources().getString(R.string.cameraAllDestroyed);
            txtCamera.setText(txtCam);
        }
        else
        {
            txtCam = String.format(getResources().getString(R.string.cameraPhrase2_2), count);
            txtCamera.setText(txtCam);
        }
    }

    private class algoButton
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
}