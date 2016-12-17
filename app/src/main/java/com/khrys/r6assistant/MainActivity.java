package com.khrys.r6assistant;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Intent menumapIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttoncamera = (Button) findViewById(R.id.buttonmain1);
        Button buttontwitch = (Button) findViewById(R.id.buttonmain2);
        Button buttonmapplans = (Button) findViewById(R.id.buttonmain3);
        Button buttonsoon = (Button) findViewById(R.id.buttonmain4);

        Button buttonmore = (Button) findViewById(R.id.buttonMore);

        buttonsoon.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(getApplicationContext(),R.string.msgsoon, Toast.LENGTH_SHORT).show();
            }
        });

        buttonmore.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final String[] items = {
                        getResources().getString(R.string.about), getResources().getString(R.string.support), getResources().getString(R.string.settings)
                };

                new AlertDialog.Builder(MainActivity.this, R.style.MyAlertDialogStyle)
                        .setTitle(R.string.more)
                        .setItems(items, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                // Do something with the selection
                            }
                        })
                        .setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {

                            }
                        })
                        .setIcon(R.drawable.info_icon)
                        .show();
            }
        });

        menumapIntent = new Intent(MainActivity.this, MapMenuActivity.class);

        setTitle(R.string.app_name);

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
            menumapIntent.putExtra("request",type);
            startActivity(menumapIntent);
        }
    }
}