package com.khrys.r6assistant.data;
/*
 * Created by Khrys.
 *
 * App : RainbowSixAssistant
 * Info : 12/2/2017 [10:56 AM]
*/

import android.app.Activity;
import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class LoadData
{
    public final String RES_VERSION = "version";
    public final String RES_MAPS = "maps";
    //public final String RES_OPERATORS = "_operators";
    public final String RES_OPERATORS = "operators";
    public final String RES_ARMIES = "armies";
    public final String RES_WEAPONS = "weapons";

    public final String URL_VERSION = "https://goo.gl/SRkxW7";
    public final String URL_MAPS = "https://goo.gl/zDbmmK";
    public final String URL_OPERATORS = "";
    public final String URL_OPERATORS_INFOS = "";
    public final String URL_ARMIES = "";
    public final String URL_WEAPONS = "";

    public void launchCheckForUpdate(Activity activity)
    {
        new DownloadTask(activity, RES_VERSION, true).execute(URL_VERSION);
    }

    void checkForUpdate(InputStream inputStreamInternal, InputStream inputStreamRes)
    {
//        JSONObject versionFileInternal = loadJsonFile(inputStreamInternal, "version");
//        JSONObject versionFileRes = loadJsonFile(inputStreamRes, "version");
    }

    public JSONObject loadData(Context context, String fileName)
    {
        String pathToInternalFile = context.getFilesDir()+"/"+fileName+".json";
        File internalDataFile = new File(pathToInternalFile);
        if(internalDataFile.exists())
        {
            try
            {
                return loadJSON(new FileInputStream(internalDataFile));
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
                return null;
            }
        }
        else
        {
            int res = context.getResources().getIdentifier(fileName, "raw", context.getPackageName());
            return loadJSON(context.getResources().openRawResource(res));
        }
    }

    JSONObject loadJSON(InputStream is)
    {
        try
        {
            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();

            String json = new String(buffer, "UTF-8");
            return new JSONObject(json);
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
            return null;
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}