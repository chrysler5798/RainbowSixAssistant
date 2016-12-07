package com.khrys.r6assistant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {

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

        buttonsoon.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),R.string.msgsoon, Toast.LENGTH_SHORT).show();
            }
        });

        menumapIntent = new Intent(MainActivity.this, MapMenuActivity.class);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle(R.string.app_name);

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