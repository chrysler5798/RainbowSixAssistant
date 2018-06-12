package com.khrys.r6assistant.data;
/*
 * Created by Khrys.
 *
 * App : RainbowSixAssistant
 * Info : 12/2/2017 [10:55 AM]
*/

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.khrys.r6assistant.R;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.net.URLConnection;

public class DownloadTask extends AsyncTask<String, String, String>
{
    private boolean needCheckUpVersion = false;
    private String pathToData;
    private WeakReference<Activity> weakActivity;

    public DownloadTask(Activity activity, String nameData, boolean needCheckUpVersion)
    {
        this.weakActivity = new WeakReference<>(activity);
        this.pathToData = weakActivity.get().getApplicationContext().getFilesDir()+"/"+nameData+".json";
        this.needCheckUpVersion = needCheckUpVersion;
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... f_url)
    {
        int count;
        try
        {
            URL url = new URL(f_url[0]);
            URLConnection conection = url.openConnection();
            conection.connect();

            // this will be useful so that you can show a tipical 0-100%
            // progress bar
            int lenghtOfFile = conection.getContentLength();

            // download the file
            InputStream input = new BufferedInputStream(url.openStream(),8192);

            //context.getFilesDir()+"/_operators.jsonn"
            OutputStream output = new FileOutputStream(pathToData);

            byte data[] = new byte[1024];

            long total = 0;

            while ((count = input.read(data)) != -1) {
                total += count;
                output.write(data, 0, count);
            }

            // flushing output
            output.flush();

            // closing streams
            output.close();
            input.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            Log.e("Error: ", e.getMessage());
        }

        return null;
    }

    @Override
    protected void onPostExecute(String file_url)
    {
//        boolean exist = new File(pathToData).exists();
//        String test = exist ? "OK" : "NOK";
//        Log.e("FILE_KHRYS:", test);
//        new LoadData().loadDataFromInternal(weakActivity.get().getApplicationContext(), "test");

        if(needCheckUpVersion)
        {
            try
            {
                InputStream inputStreamInternal = new FileInputStream(pathToData);
                InputStream inputStreamRes = weakActivity.get().getApplicationContext().getResources().openRawResource(R.raw.version);
                new LoadData().checkForUpdate(inputStreamInternal, inputStreamRes);
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }
    }
}