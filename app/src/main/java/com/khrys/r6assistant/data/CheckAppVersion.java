package com.khrys.r6assistant.data;
/*
 * Created by Khrys.
 *
 * App : RainbowSixAssistant
 * Info : 1/6/2018 [8:07 PM]
*/

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.khrys.r6assistant.R;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CheckAppVersion extends AsyncTask<String, Void, String>
{
    private final String URL_VERSION_TXT = "http://louis-jeancolin.fr/r6assistant/version/version.php";
    private WeakReference<Context> weakContext;

    public CheckAppVersion(Context context)
    {
        weakContext = new WeakReference<>(context);
    }

    @Override
    protected String doInBackground(String... params)
    {
        try
        {
            URL url = new URL(URL_VERSION_TXT);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            StringBuilder result = new StringBuilder();

            int responseCode = urlConnection.getResponseCode();

            if(responseCode == 200)
            {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                String line;

                while ((line = bufferedReader.readLine()) != null)
                {
                    result.append(line);
                }
                in.close();
            }
            else
            {
                return "0";
            }
            urlConnection.disconnect();

            return result.toString();
        }
        catch(MalformedURLException e)
        {
            e.printStackTrace();
            return "0";
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return "0";
        }
    }

    @Override
    protected void onPostExecute(String result)
    {
        Context context = weakContext.get();

        boolean needUpdate = false;
        int actualVersionCode = 0;
        int onlineVersionCode = 0;

        try
        {
            actualVersionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
            onlineVersionCode = Integer.valueOf(result);
        }
        catch(PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(NumberFormatException e)
        {
            e.printStackTrace();
        }
        catch(NullPointerException e)
        {
            e.printStackTrace();
        }

        if(actualVersionCode != 0 && onlineVersionCode != 0)
        {
            if(actualVersionCode < onlineVersionCode)
            {
                needUpdate = true;

                AlertDialog.Builder dialogNeedUpdateBuilder = new AlertDialog.Builder(context, R.style.MyAlertDialogStyle);
                dialogNeedUpdateBuilder.setTitle(R.string.update_title);
                dialogNeedUpdateBuilder.setMessage(R.string.update_text);
                dialogNeedUpdateBuilder.setIcon(android.R.drawable.stat_sys_download_done);
                dialogNeedUpdateBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        Context context = weakContext.get();
                        Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
                        Intent openPlayStore = new Intent(Intent.ACTION_VIEW, uri);
                        try
                        {
                            context.startActivity(openPlayStore);
                        }
                        catch (ActivityNotFoundException e)
                        {
                            Toast.makeText(context, context.getResources().getString(R.string.error_market), Toast.LENGTH_LONG).show();
                        }
                    }
                });
                dialogNeedUpdateBuilder.setNegativeButton(android.R.string.no, null);
                AlertDialog dialogNeedUpdate = dialogNeedUpdateBuilder.create();
                dialogNeedUpdate.show();
            }
        }

        if(!needUpdate)
        {
            new CheckDataVersion(weakContext.get()).execute();
        }
    }
}