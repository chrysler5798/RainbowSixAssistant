package com.khrys.r6assistant;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/*
 * Created by Chrysler on 12/20/2016.
 * RainbowSixAssistant
*/

public class MeufloActivity extends AppCompatActivity
{
    TextView counter;

    static final int CONNECTION_TIMEOUT=10000;
    static final int READ_TIMEOUT=15000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.meuflo);

        counter = (TextView) findViewById(R.id.textVcounter);

        new AsyncDB().execute();

        ImageView trollImg = (ImageView) findViewById(R.id.imgVtroll);

        trollImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AsyncDB().execute();
            }
        });
    }

    private class AsyncDB extends AsyncTask<String, Void, String>
    {
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected String doInBackground(String... params)
        {
            try
            {
                url = new URL("http://164.132.145.12/android_test/db_troll.php");
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
                return "";
            }

            try
            {
                conn = (HttpURLConnection)url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.setDoOutput(true);
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
                return "";
            }

            try
            {
                int response_code = conn.getResponseCode();

                if (response_code == HttpURLConnection.HTTP_OK)
                {
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    return(reader.readLine());
                }
                else
                {
                    return "";
                }
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
                return "exception";
            }
            finally
            {
                conn.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String result)
        {
            counter.setText("Troller : "+result);
        }
    }
}