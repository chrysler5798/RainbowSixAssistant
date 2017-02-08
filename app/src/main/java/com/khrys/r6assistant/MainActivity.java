package com.khrys.r6assistant;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
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


public class MainActivity extends AppCompatActivity {

    int meuflotroll = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setTitle(R.string.app_name);

        Button buttoncamera = (Button) findViewById(R.id.buttonmain1);
        Button buttontwitch = (Button) findViewById(R.id.buttonmain2);
        Button buttonmapplans = (Button) findViewById(R.id.buttonmain3);
        Button buttonlobby = (Button) findViewById(R.id.buttonmain4);
        Button buttonmore = (Button) findViewById(R.id.buttonMore);
        ImageButton buttonset = (ImageButton) findViewById(R.id.buttonSettings);

        buttonlobby.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(getApplicationContext(), "I'm working on this feature, update soon !", Toast.LENGTH_LONG).show();
            }
        });

        buttonlobby.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {
                meuflotroll = 1;
                return false;
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
        buttonmapplans.setOnClickListener(new MenuCLickListener(3));
    }

    class MenuCLickListener implements View.OnClickListener
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
                            .setIcon(R.drawable.staricon)
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