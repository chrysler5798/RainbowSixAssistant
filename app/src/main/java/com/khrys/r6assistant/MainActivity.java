package com.khrys.r6assistant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttoncamera = (Button) findViewById(R.id.buttonmain1);
        Button buttontwitch = (Button) findViewById(R.id.buttonmain2);
        Button buttonsoon = (Button) findViewById(R.id.buttonmain3);
        Button buttonsoon2 = (Button) findViewById(R.id.buttonmain4);

        buttonsoon.setOnClickListener(new SoonCLickListener());
        buttonsoon2.setOnClickListener(new SoonCLickListener());

        final Intent menumapIntent = new Intent(MainActivity.this, MapMenuActivity.class);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle(R.string.app_name);

        buttoncamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menumapIntent.putExtra("request",1);
                startActivity(menumapIntent);
            }
        });

        buttontwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menumapIntent.putExtra("request",2);
                startActivity(menumapIntent);
            }
        });
    }

    class SoonCLickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(),R.string.msgsoon, Toast.LENGTH_SHORT).show();
        }
    }
}