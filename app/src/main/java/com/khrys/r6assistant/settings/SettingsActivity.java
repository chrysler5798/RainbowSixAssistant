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
    int languageId;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        String[] textArray = {getRes(R.string.language_english),getRes(R.string.language_french),getRes(R.string.language_spanish),getRes(R.string.language_portuguese),getRes(R.string.language_german),getRes(R.string.language_italian), getRes(R.string.language_russian), getRes(R.string.language_polish), getRes(R.string.language_korean), getRes(R.string.language_simplified_chinese), getRes(R.string.language_traditional_chinese), getRes(R.string.language_serbian)};
        Integer[] imageArray = {R.drawable.flag_gb,R.drawable.flag_fr,R.drawable.flag_es,R.drawable.flag_pt,R.drawable.flag_de,R.drawable.flag_it, R.drawable.flag_ru, R.drawable.flag_pl, R.drawable.flag_kr, R.drawable.flag_zhcn, R.drawable.flag_zhtw, R.drawable.flag_sr};

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);

        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Spinner spinner = findViewById(R.id.spinnerLanguage);

        SpinnerAdapter adapter = new SpinnerAdapter(this, textArray, imageArray);
        spinner.setAdapter(adapter);

        switch(Locale.getDefault().getLanguage())
        {
            case "en":
                languageId = 0;
                break;

            case "fr":
                languageId = 1;
                break;

            case "es":
                languageId = 2;
                break;

            case "pt":
                languageId = 3;
                break;

            case "de":
                languageId = 4;
                break;

            case "it":
                languageId = 5;
                break;

            case "ru":
                languageId = 6;
                break;

            case "pl":
                languageId = 7;
                break;

            case "ko":
                languageId = 8;
                break;

            case "zh":
                switch (Locale.getDefault().getCountry())
                {
                    case "CN":
                        languageId = 9;
                        break;

                    case "TW":
                        languageId = 10;
                        break;

                    default:
                        languageId = 9;
                        break;
                }
                break;

            case "sr":
                languageId = 11;
                break;

            default:
                languageId = 0;
                break;
        }
        spinner.setSelection(languageId);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position != languageId)
                {
                    showDialogToExit(position);
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