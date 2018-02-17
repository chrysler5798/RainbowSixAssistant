package com.khrys.r6assistant;

/*
 * Created by Khrys.
 *
 * App : RainbowSixAssistant
 * Info : 10/1/2016 [00:00 AM]
*/

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import java.util.Locale;

import static com.khrys.r6assistant.settings.SettingsActivity.getDefaults;

public class SplashActivity extends Activity
{
    private static final int SPLASH_TIME_OUT = 3000;
    private static final String PREFS_LANG = "PREFS_LANG";
    private static final String ON_LANG = "ON_LANG";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        int langueon = getDefaults(ON_LANG, getApplicationContext());
        String language;

        if (langueon == 1)
        {
            int langue = getDefaults(PREFS_LANG, getApplicationContext());

            switch (langue)
            {
                case 0:
                    language = "en";
                    break;

                case 1:
                    language = "fr";
                    break;

                case 2:
                    language = "es";
                    break;

                case 3:
                    language = "pt";
                    break;

                case 4:
                    language = "de";
                    break;

                case 5:
                    language = "it";
                    break;

                case 6:
                    language = "ru";
                    break;

                case 7:
                    language = "pl";
                    break;

                case 8:
                    language = "ko";
                    break;

                case 9:
                    language = "zh";
                    break;

                case 10:
                    language = "zh";
                    break;

                case 11:
                    language = "sr";
                    break;

                default:
                    language = "en";
                    break;
            }

            Locale locale;
            if(langue == 8)
            {
                locale = new Locale(language, "CN");
            }
            else if(langue == 9)
            {
                locale = new Locale(language, "TW");
            }
            else
            {
                locale = new Locale(language);
            }

            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }

        setContentView(R.layout.activity_splash);

        String versionName = "";
        try
        {
            versionName = String.format(getResources().getString(R.string.version_d), getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0).versionName);
        }
        catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }
        TextView versionname = findViewById(R.id.textViewVersion);
        versionname.setText(versionName);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}