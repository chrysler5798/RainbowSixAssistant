package com.khrys.r6assistant.settings;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.khrys.r6assistant.R;

import java.util.Locale;

/*
 * Created by Chrysler on 12/18/2016.
 * RainbowSixPartner
*/

public class SettingsActivity extends AppCompatActivity
{

    private static final String PREFS_LANG = "PREFS_LANG";
    private static final String ON_LANG = "ON_LANG";
    int yes;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        String[] textArray = {getRes(R.string.english),getRes(R.string.french),getRes(R.string.portuguese),getRes(R.string.german),getRes(R.string.italian), getRes(R.string.russian), getRes(R.string.chinese), getRes(R.string.polish)};
        Integer[] imageArray = {R.drawable.flag_gb,R.drawable.flag_fr,R.drawable.flag_pt,R.drawable.flag_de,R.drawable.flag_it, R.drawable.flag_ru, R.drawable.flag_zh, R.drawable.flag_pl};

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);

        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Spinner spinner = (Spinner) findViewById(R.id.spinnerLanguage);

        SpinnerAdapter adapter = new SpinnerAdapter(this, textArray, imageArray);
        spinner.setAdapter(adapter);

        switch(Locale.getDefault().getLanguage())
        {
            case "en":
                yes = 0;
                break;

            case "fr":
                yes = 1;
                break;

            case "pt":
                yes = 2;
                break;

            case "de":
                yes = 3;
                break;

            case "it":
                yes = 4;
                break;

            case "ru":
                yes = 5;
                break;

            case "zh":
                yes = 6;
                break;

            case "pl":
                yes = 7;
                break;

            default:
                yes = 0;
                break;
        }
        spinner.setSelection(yes);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position != yes)
                {
                    switch(position)
                    {
                        case 0:
                            showDialogToExit(0);
                            break;

                        case 1:
                            showDialogToExit(1);
                            break;

                        case 2:
                            showDialogToExit(2);
                            break;

                        case 3:
                            showDialogToExit(3);
                            break;

                        case 4:
                            showDialogToExit(4);
                            break;

                        case 5:
                            showDialogToExit(5);
                            break;

                        case 6:
                            showDialogToExit(6);
                            break;

                        case 7:
                            showDialogToExit(7);
                            break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

            void showDialogToExit(int typeLanguage)
            {
                setDefaults(PREFS_LANG,typeLanguage,getApplicationContext());
                setDefaults(ON_LANG,1,getApplicationContext());
                new AlertDialog.Builder(SettingsActivity.this)
                        .setTitle(R.string.warning)
                        .setMessage(R.string.settings_end)
                        .setNegativeButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setIcon(R.drawable.ic_info)
                        .show();
            }

        });
    }

    String getRes(int txt)
    {
        return getResources().getString(txt);
    }

    public static void setDefaults(String key, int value, Context context)
    {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.apply();
    }
    public static int getDefaults(String key, Context context)
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getInt(key, 0);
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