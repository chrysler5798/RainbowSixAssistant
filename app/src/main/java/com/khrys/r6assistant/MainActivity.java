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
import com.khrys.r6assistant.data.CheckAppVersion;
import com.khrys.r6assistant.operators.OperatorMenuActivity;
import com.khrys.r6assistant.settings.SettingsActivity;
import com.khrys.r6assistant.weapons.WeaponMenuActivity;

public class MainActivity extends AppCompatActivity
{
    int meuflotroll = 0;
    boolean isConnectedToNetwork = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setTitle(R.string.app_name);

        Button buttoncamera   = findViewById(R.id.buttonCamera);
        Button buttontwitch   = findViewById(R.id.buttonTwitch);
        Button buttonmap      = findViewById(R.id.buttonMap);
        Button buttonweapon   = findViewById(R.id.buttonWeapon);
        Button buttonoperator = findViewById(R.id.buttonOperator);
        Button buttononline   = findViewById(R.id.buttonOnline);
        Button buttonmore     = findViewById(R.id.buttonMore);
        ImageButton buttonset = findViewById(R.id.buttonSettings);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getActiveNetworkInfo() != null)
        {
            isConnectedToNetwork = connectivityManager.getActiveNetworkInfo().isConnected();
        }

        if(isConnectedToNetwork)
        {
            new CheckAppVersion(this).execute();
        }

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
            public void onClick(View view)
            {

                //new DownloadFileFromURL(view.getContext()).execute("https://goo.gl/rmfKPW");

//                InputStream is = getResources().openRawResource(R.raw.operators);
//                Writer writer = new StringWriter();
//                char[] buffer = new char[1024];
//                try {
//                    Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//                    int n;
//                    while ((n = reader.read(buffer)) != -1) {
//                        writer.write(buffer, 0, n);
//                    }
//
//                    is.close();
//                } catch (UnsupportedEncodingException e)
//                {
//                    e.printStackTrace();
//                } catch (IOException e)
//                {
//                    e.printStackTrace();
//                }
//
//                String jsonString = writer.toString();
//                Log.e("TEST", jsonString);

                //new DataUpdateTask(MainActivity.this, "test").execute(new LoadData().URL_TEST);
                //new LoadData().loadDataFromInternal(getApplicationContext(), "test");
               // new LoadData().loadDataFromRes(getApplicationContext(), R.raw.weapons);


                AlertDialog.Builder dialogNoInternetBuilder = new AlertDialog.Builder(view.getContext(), R.style.MyAlertDialogStyle);
                dialogNoInternetBuilder.setTitle(R.string.maintenance);
                dialogNoInternetBuilder.setMessage("R6 Assistant Online is currently unvailable, this service is limited because I need to pay servers. Thanks for your understanding !");
                dialogNoInternetBuilder.setIcon(android.R.drawable.stat_sys_warning);
                dialogNoInternetBuilder.setPositiveButton(android.R.string.ok, null);
                AlertDialog alertNoInternet = dialogNoInternetBuilder.create();
                alertNoInternet.show();


//                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
//                if(activeNetworkInfo != null && activeNetworkInfo.isConnected())
//                {
//                    Intent myIntent = new Intent(MainActivity.this, MenuChatActivity.class);
//                    startActivity(myIntent);
//                }
//                else
//                {
//                    AlertDialog.Builder dialogNoInternetBuilder = new AlertDialog.Builder(view.getContext(), R.style.MyAlertDialogStyle);
//                    dialogNoInternetBuilder.setTitle(R.string.no_internet);
//                    dialogNoInternetBuilder.setIcon(android.R.drawable.stat_sys_warning);
//                    dialogNoInternetBuilder.setPositiveButton(android.R.string.ok, null);
//
//                    AlertDialog alertNoInternet = dialogNoInternetBuilder.create();
//                    alertNoInternet.show();
//                }
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