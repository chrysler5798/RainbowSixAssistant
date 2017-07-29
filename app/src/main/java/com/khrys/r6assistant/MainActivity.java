package com.khrys.r6assistant;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.khrys.r6assistant.about.AboutActivity;
import com.khrys.r6assistant.chat.MenuChatActivity;
import com.khrys.r6assistant.operators.OperatorMenuActivity;
import com.khrys.r6assistant.settings.SettingsActivity;
import com.khrys.r6assistant.weapons.WeaponMenuActivity;

public class MainActivity extends AppCompatActivity
{
    int meuflotroll = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setTitle(R.string.app_name);

        Button buttoncamera   = (Button) findViewById(R.id.buttonCamera);
        Button buttontwitch   = (Button) findViewById(R.id.buttonTwitch);
        Button buttonmap      = (Button) findViewById(R.id.buttonMap);
        Button buttonweapon   = (Button) findViewById(R.id.buttonWeapon);
        Button buttonoperator = (Button) findViewById(R.id.buttonOperator);
        Button buttononline   = (Button) findViewById(R.id.buttonOnline);
        Button buttonmore     = (Button) findViewById(R.id.buttonMore);
        ImageButton buttonset = (ImageButton) findViewById(R.id.buttonSettings);

        buttonweapon.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startAct(WeaponMenuActivity.class);
            }
        });

        buttonweapon.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {
                meuflotroll = 1;
                return false;
            }
        });

        buttonoperator.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startAct(OperatorMenuActivity.class);
            }
        });

        buttononline.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(final View view)
            {

                //Toast.makeText(getApplicationContext(), R.string.msgsoon, Toast.LENGTH_SHORT).show();

                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if(activeNetworkInfo != null && activeNetworkInfo.isConnected())
                {
                    Intent myIntent = new Intent(MainActivity.this, MenuChatActivity.class);
                    startActivity(myIntent);
                }
                else
                {
                    AlertDialog.Builder dialogNoInternetBuilder = new AlertDialog.Builder(view.getContext(), R.style.MyAlertDialogStyle);
                    dialogNoInternetBuilder.setTitle(R.string.no_internet);
                    dialogNoInternetBuilder.setIcon(android.R.drawable.stat_sys_warning);
                    dialogNoInternetBuilder.setPositiveButton(android.R.string.ok, null);

                    AlertDialog alertNoInternet = dialogNoInternetBuilder.create();
                    alertNoInternet.show();
                }
            }
        });

        buttonmore.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent myIntent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(myIntent);
            }
        });

        buttonset.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            }
        });

        buttoncamera.setOnClickListener(new MenuCLickListener(1));
        buttontwitch.setOnClickListener(new MenuCLickListener(2));
        buttonmap.setOnClickListener(new MenuCLickListener(3));
    }

    private void startAct(Class classToGo)
    {
        startActivity(new Intent(MainActivity.this, classToGo));
    }

    private class MenuCLickListener implements View.OnClickListener
    {
        int type;
        MenuCLickListener(int type)
        {
            this.type = type;
        }

        @Override
        public void onClick(View v)
        {
            Intent menumapIntent = new Intent(MainActivity.this, MapMenuActivity.class);
            menumapIntent.putExtra("request",type);
            startActivity(menumapIntent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        switch (menuItem.getItemId())
        {
            case R.id.action_rate:
                if(meuflotroll == 1)
                {
                    meuflotroll = 0;
                   startActivity(new Intent(MainActivity.this, MeufloActivity.class));
                }
                else
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.MyAlertDialogStyle);
                    builder.setTitle(R.string.ratetitle)
                            .setMessage(R.string.ratemsg)
                            .setIcon(R.drawable.ic_star)
                            .setPositiveButton(R.string.go, new DialogInterface.OnClickListener()
                            {
                                public void onClick(DialogInterface dialog, int id)
                                {
                                    Uri uri = Uri.parse("market://details?id=" + getPackageName());
                                    Intent openPlayStore = new Intent(Intent.ACTION_VIEW, uri);
                                    try
                                    {
                                        startActivity(openPlayStore);
                                    } catch (ActivityNotFoundException e)
                                    {
                                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_market),   Toast.LENGTH_LONG).show();
                                    }
                                }
                            })
                            .setNeutralButton(R.string.close, new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {

                                }
                            });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                return true;

            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}