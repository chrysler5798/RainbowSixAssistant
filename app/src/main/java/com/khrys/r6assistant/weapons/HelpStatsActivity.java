package com.khrys.r6assistant.weapons;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.khrys.r6assistant.R;

/*
 * Created by Chrysler on 3/12/2017.
 * RainbowSixAssistant
 *
*/

public class HelpStatsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_help);

        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        TextView segioTxtV = (TextView) findViewById(R.id.textVhelpSegio);
        TextView varisityTxtV = (TextView) findViewById(R.id.textVhelpVarsity);

        segioTxtV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/user/SergioSmeets1988"));
                startActivity(browserIntent);
            }
        });

        varisityTxtV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/user/VarsityGaming"));
                startActivity(browserIntent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

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
