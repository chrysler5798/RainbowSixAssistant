package com.khrys.r6assistant.data;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.khrys.r6assistant.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.URL;

/*
 * Created by Khrys.
 *
 * App : RainbowSixAssistant
 * Info : 6/19/2018 [8:01 PM]
 */
public class CheckDataVersion extends AsyncTask<String, String, JSONObject>
{
    private final String LINE_SEPARATOR = System.getProperty("line.separator");

    private LoadData loadData = new LoadData();
    private WeakReference<Context> weakContext;
    private JSONArray dataToUpdate;

    CheckDataVersion(Context context)
    {
        weakContext = new WeakReference<>(context);
    }

    @Override
    protected JSONObject doInBackground(String... strings)
    {
        try
        {
            URL url = new URL(loadData.URL_VERSION);

            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine;
            StringBuilder urlContent = new StringBuilder();
            while ((inputLine = in.readLine()) != null)
            {
                urlContent.append(inputLine);
            }

            in.close();

            return new JSONObject(urlContent.toString());

        }
        catch (Exception e)
        {
            e.printStackTrace();
            Log.e("Error: ", e.getMessage());
            return null;
        }
    }

    @Override
    protected void onPostExecute(final JSONObject onlineVersionJson)
    {
        Context context = weakContext.get();
        String localVersionStr = getLocalVersion(context);

        if(onlineVersionJson != null && localVersionStr != null)
        {
            try
            {
                JSONObject localVersionJson = new JSONObject(localVersionStr);

                dataToUpdate = new JSONArray();
                StringBuilder dataToUpdateStrList = new StringBuilder();

                JSONArray localVersionsList = localVersionJson.getJSONArray("version");
                JSONObject localVersionsData = localVersionJson.getJSONObject("version_data");

                for(int i = 0; i < localVersionsList.length(); i++)
                {
                    String dataId = localVersionsList.getString(i);

                    int localVersion = localVersionsData.getInt(dataId);
                    int onlineVersion = onlineVersionJson.getJSONObject("version_data").getInt(dataId);

                    if(localVersion < onlineVersion)
                    {
                        String dataUrl = onlineVersionJson.getJSONObject("version_links").getString(dataId);

                        JSONObject data = new JSONObject();
                        data.put("id", dataId);
                        data.put("url", dataUrl);

                        dataToUpdate.put(data);

                        int strResId = context.getResources().getIdentifier("update_data_" + dataId, "string", context.getPackageName());
                        dataToUpdateStrList.append(LINE_SEPARATOR).append("- ").append(context.getString(strResId));
                    }
                }

                if(dataToUpdate.length() > 0)
                {
                    String message = context.getString(R.string.update_data_text) + dataToUpdateStrList;

                    AlertDialog.Builder dialogNeedUpdateBuilder = new AlertDialog.Builder(context, R.style.MyAlertDialogStyle);
                    dialogNeedUpdateBuilder.setTitle(R.string.update_title);
                    dialogNeedUpdateBuilder.setMessage(message);
                    dialogNeedUpdateBuilder.setIcon(android.R.drawable.stat_sys_download_done);
                    dialogNeedUpdateBuilder.setPositiveButton(R.string.update, new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i)
                        {
                            new DataUpdateTask(weakContext.get(), dataToUpdate, onlineVersionJson).execute();
                        }
                    });
                    dialogNeedUpdateBuilder.setNegativeButton(android.R.string.cancel, null);
                    AlertDialog dialogNeedUpdate = dialogNeedUpdateBuilder.create();
                    dialogNeedUpdate.show();

                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
    }

    private String getLocalVersion(Context context)
    {
        try
        {
            String versionPath = context.getFilesDir() + File.separator + "version.json";
            File versionFile = new File(versionPath);

            if(versionFile.exists() && versionFile.length() > 0 && versionFile.canRead())
            {
                try
                {
                    return readInputStream(new FileInputStream(versionFile));
                }
                catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                try
                {
                    return readInputStream(context.getResources().openRawResource(R.raw.version));
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    private String readInputStream(InputStream inputStream) throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

        String inputLine;
        StringBuilder fileContent = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
        {
            fileContent.append(inputLine);
        }

        in.close();

        return fileContent.toString();
    }
}