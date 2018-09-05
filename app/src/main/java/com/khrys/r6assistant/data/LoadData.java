package com.khrys.r6assistant.data;
/*
 * Created by Khrys.
 *
 * App : RainbowSixAssistant
 * Info : 12/2/2017 [10:56 AM]
*/

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class LoadData
{
    public final String RES_MAPS = "maps";
    public final String RES_OPERATORS = "operators";
    public final String RES_ARMIES = "armies";
    public final String RES_WEAPONS = "weapons";

    public final String URL_VERSION = "https://www.dropbox.com/s/i9fvzjpg6hlupla/version.json?dl=1";

    public JSONArray loadList(Context context, String dataName) throws JSONException
    {
        return loadData(context, dataName, false).getJSONArray(dataName);
    }

    public JSONObject loadData(Context context, String dataName) throws JSONException
    {
        return loadData(context, dataName, true);
    }

    public JSONObject loadData(Context context, String dataName, boolean getData) throws JSONException
    {
        JSONObject result;
        String pathToInternalFile = context.getFilesDir()+ File.separator + dataName +".json";
        File internalDataFile = new File(pathToInternalFile);

        if(internalDataFile.exists() && internalDataFile.length() > 0 && internalDataFile.canRead())
        {
            try
            {
                result = loadJSON(new FileInputStream(internalDataFile));
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
                return null;
            }
        }
        else
        {
            int res = context.getResources().getIdentifier(dataName, "raw", context.getPackageName());
            result = loadJSON(context.getResources().openRawResource(res));
        }

        if(getData && result != null)
            result = result.getJSONObject(dataName + "_data");

        return result;
    }

    private JSONObject loadJSON(InputStream is)
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