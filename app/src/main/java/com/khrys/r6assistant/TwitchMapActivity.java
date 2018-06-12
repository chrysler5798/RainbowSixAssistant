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

import com.khrys.r6assistant.data.LoadData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/*
 * Created by Chrysler on 10/3/2016.
 * RainbowSixPartner
*/

public class TwitchMapActivity extends Activity implements View.OnClickListener, ChangeCamRemInterface
{

    TextView txtValkyrie, txtBandit,txtMute,txtJager,txtKapkan, txtCamera;

    ImageButton buttonValkyrie,buttonBandit,buttonMute,buttonJager,buttonKapkan, refreshBut;

    int type = 2, gadgetV, gadgetB, gadgetM, gadgetJ, gadgetK;

    String mapId;

    RecyclerView rv;

    ListAdapterMap listAdapterMap;

    Context context;

    ArrayList<Integer> pics = new ArrayList<>();
    ArrayList<Integer> poscam = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitchmap);

        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        context = getApplicationContext();

        Map map = (Map) getIntent().getSerializableExtra("map");
        mapId = map.getMapId();
        String mapName = map.getMapName();

        recyclerSetup();

        listAdapterMap = new ListAdapterMap(pics,poscam, type, this);

        rv = findViewById(R.id.recyclerViewMap);
        LayoutIfManager layoutIM = new LayoutIfManager(context);
        rv.setAdapter(listAdapterMap);
        rv.setLayoutManager(layoutIM.ReturnManager());

        TextView txtMap = findViewById(R.id.textViewMapName);

        txtValkyrie = findViewById(R.id.textViewValkyrie);
        txtBandit = findViewById(R.id.textViewBandit);
        txtMute = findViewById(R.id.textViewMute);
        txtJager = findViewById(R.id.textViewJager);
        txtKapkan = findViewById(R.id.textViewKapkan);

        txtCamera = findViewById(R.id.textViewCamRem);

        notifyCamRem(pics.size());

        defaultTxtSetup();

        buttonValkyrie = findViewById(R.id.buttonDValkyrie);
        buttonBandit = findViewById(R.id.buttonDBandit);
        buttonMute = findViewById(R.id.buttonDMute);
        buttonJager = findViewById(R.id.buttonDJager);
        buttonKapkan = findViewById(R.id.buttonDKapkan);

        refreshBut = findViewById(R.id.TwitchRefreshButton);

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

        txtMap.setText(mapName);

    }

    void defaultTxtSetup()
    {
        gadgetV = 3;
        gadgetB = 4;
        gadgetM = 4;
        gadgetJ = 3;
        gadgetK = 5;
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
        LoadData dataLoader = new LoadData();
        JSONObject mapsData = dataLoader.loadData(this, dataLoader.RES_MAPS);

        int nbCamera = 0;

        try
        {
            nbCamera = mapsData.getJSONObject("maps_data").getJSONObject(mapId).getInt("cameras");
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        for(int i = 0; i < nbCamera; i++)
        {
            String id = "cam_" + mapId + "_"+i;
            int intName = getResources().getIdentifier(id, "string", getApplicationContext().getPackageName());
            int intPic = getResources().getIdentifier(id, "drawable", getApplicationContext().getPackageName());

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