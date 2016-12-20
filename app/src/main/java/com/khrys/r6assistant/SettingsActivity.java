package com.khrys.r6assistant;

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
import java.util.Locale;

/**
 * Created by Chrysler on 12/18/2016.
 * <p>
 * RainbowSixPartner
 */

public class SettingsActivity extends AppCompatActivity
{

    private static final String PREFS_LANG = "PREFS_LANG";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        String[] textArray = {getResources().getString(R.string.english),getResources().getString(R.string.french)};
        Integer[] imageArray = {R.drawable.flag_uk,R.drawable.flag_fr};

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);

        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Spinner spinner = (Spinner) findViewById(R.id.spinnerLanguage);

        SpinnerAdapter adapter = new SpinnerAdapter(this, textArray, imageArray);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0 && getDefaults(PREFS_LANG, getApplicationContext()) != 1)
                {
                        setDefaults(PREFS_LANG,1,getApplicationContext());
                        showDialogToExit();

                } else if(position == 1 && getDefaults(PREFS_LANG, getApplicationContext()) != 0)
                {
                    setDefaults(PREFS_LANG,0,getApplicationContext());
                    showDialogToExit();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

            void showDialogToExit()
            {
                new AlertDialog.Builder(SettingsActivity.this)
                        .setTitle(R.string.warning)
                        .setMessage(R.string.settings_end)
                        .setNegativeButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setIcon(R.drawable.info_icon)
                        .show();
            }
        });

        if(Locale.getDefault().getLanguage().equals("fr") && getDefaults(PREFS_LANG, getApplicationContext()) == 0)
        {
            spinner.setSelection(1);
        }
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
