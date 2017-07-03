package com.khrys.r6assistant.online;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.khrys.r6assistant.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/*
 * Created by Chrysler on 12/23/2016.
 * RainbowSixAssistant
*/

public class LobbyActivity extends AppCompatActivity
{

    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;

    TextView txtViewID, txtViewName1, txtViewName2, txtViewName3, txtViewName4, txtViewName5, txtViewNothing;
    EditText editName, editID;
    Button createBut, joinBut, playBut;

    int isFull = 0, inLobby = 0;

    public final String DB_CREATE_GROUP = "http://164.132.145.12/android_test/db_add.php";
    public final String DB_ADD_PLAYER = "http://164.132.145.12/android_test/db_addname.php";

    JSONArray twitch = null;

    String txtID;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lobby);

        createBut = (Button) findViewById(R.id.buttonCreate);
        joinBut = (Button) findViewById(R.id.buttonJoin);
        playBut = (Button) findViewById(R.id.buttonStart);

        txtViewID = (TextView) findViewById(R.id.textViewID);
        txtViewName1 = (TextView) findViewById(R.id.textVname1);
        txtViewName2 = (TextView) findViewById(R.id.textVname2);
        txtViewName3 = (TextView) findViewById(R.id.textVname3);
        txtViewName4 = (TextView) findViewById(R.id.textVname4);
        txtViewName5 = (TextView) findViewById(R.id.textVname5);
        txtViewNothing = (TextView) findViewById(R.id.textViewNothing);

        defaultSetup();

        createBut.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(LobbyActivity.this);

                builder.setTitle("Custom AlertDialog");

                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("Create", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                String txt = editName.getText().toString();
                                Uri.Builder builder = new Uri.Builder().appendQueryParameter("name",txt);
                                new AsyncDB(DB_CREATE_GROUP,builder,"create").execute(txt);
                                txtViewName1.setText(txt);
                            }
                        });


                LayoutInflater inflater = getLayoutInflater();
                View dialoglayout = inflater.inflate(R.layout.dialog_lobby_create, null);

                editName = dialoglayout.findViewById(R.id.editTextName);

                builder.setView(dialoglayout);
                builder.show();

            }
        });

        joinBut.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(LobbyActivity.this);

                builder.setTitle("Custom AlertDialog");

                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                    }
                })
                        .setPositiveButton("Join", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                String txtName = editName.getText().toString();
                                txtID = editID.getText().toString();
                                Uri.Builder builder = new Uri.Builder()
                                            .appendQueryParameter("id",txtID)
                                            .appendQueryParameter("name",txtName);
                                new AsyncDB(DB_ADD_PLAYER,builder,"join").execute();
                            }
                        });


                LayoutInflater inflater = getLayoutInflater();
                View dialoglayout = inflater.inflate(R.layout.dialog_lobby_join, null);

                editName = dialoglayout.findViewById(R.id.editTextName);
                editID = dialoglayout.findViewById(R.id.editTextID);

                builder.setView(dialoglayout);
                builder.show();

            }
        });

    }


    void defaultSetup()
    {
        txtViewName1.setVisibility(View.GONE);
        txtViewName2.setVisibility(View.GONE);
        txtViewName3.setVisibility(View.GONE);
        txtViewName4.setVisibility(View.GONE);
        txtViewName5.setVisibility(View.GONE);

        txtViewNothing.setText("Create or join a lobby to start");
    }

    //CREATE A LOBBY
    private class AsyncDB extends AsyncTask<String, Void, String>
    {

        HttpURLConnection conn;
        URL url = null;
        Uri.Builder builder;
        String type, urltxt, query, name1, name2, name3, name4, name5;

        AsyncDB(String urltxt, Uri.Builder builder, String type)
        {
            this.urltxt = urltxt;
            this.builder = builder;
            this.type = type;
        }

        @Override
        protected String doInBackground(String... params)
        {
            try
            {
                url = new URL(urltxt);
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
                return "exception";
            }

            try
            {
                conn = (HttpURLConnection)url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                query = builder.build().getEncodedQuery();

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conn.connect();
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
                return "exception";
            }

            try
            {
                int response_code = conn.getResponseCode();

                if (response_code == HttpURLConnection.HTTP_OK)
                {
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    if(type.equals("create"))
                    {
                        while ((line = reader.readLine()) != null)
                        {
                            result.append(line);
                        }
                        return(result.toString());
                    }
                    else
                    {
                        try
                        {
                            JSONObject jsonObj = new JSONObject(reader.readLine());
                            twitch = jsonObj.getJSONArray("resultarray");
                            JSONObject c = twitch.getJSONObject(0);
                            if(c.getInt("full") == 1)
                            {
                                isFull = 1;
                            }
                            else
                            {
                                name1 = c.getString("name1");
                                name2 = c.getString("name2");
                                name3 = c.getString("name3");
                                name4 = c.getString("name4");
                                name5 = c.getString("name5");
                            }

                            return "GO";
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                            return "JSON error";
                        }
                    }
                }
                else
                {
                    return "unsuccessful";
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
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
            if(type.equals("create"))
            {
                txtViewID.setText(String.format(getResources().getString(R.string.lobbytitle),Integer.parseInt(result)));

                txtViewName1.setVisibility(View.VISIBLE);

                txtViewNothing.setVisibility(View.GONE);
            }
            else if(type.equals("join"))
            {
                if(isFull == 0)
                {
                    txtViewID.setText(String.format(getResources().getString(R.string.lobbytitle),Integer.parseInt(txtID)));
                    txtViewName1.setText(name1);
                    txtViewName2.setText(name2);
                    txtViewName3.setText(name3);
                    txtViewName4.setText(name4);
                    txtViewName5.setText(name5);

                    txtViewName1.setVisibility(View.VISIBLE);
                    txtViewName2.setVisibility(View.VISIBLE);

                    if(name3 != null)
                    {
                        txtViewName3.setVisibility(View.VISIBLE);
                    }

                    if(name4 != null)
                    {
                        txtViewName4.setVisibility(View.VISIBLE);
                    }

                    if(name5 != null)
                    {
                        txtViewName5.setVisibility(View.VISIBLE);
                    }

                    txtViewNothing.setVisibility(View.GONE);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "FULL", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}