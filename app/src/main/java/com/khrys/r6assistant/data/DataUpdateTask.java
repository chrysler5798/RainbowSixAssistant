package com.khrys.r6assistant.data;
/*
 * Created by Khrys.
 *
 * App : RainbowSixAssistant
 * Info : 12/2/2017 [10:55 AM]
*/

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.khrys.r6assistant.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.net.URLConnection;

public class DataUpdateTask extends AsyncTask<String, Integer, String>
{
    private WeakReference<Context> weakContext;
    private WeakReference<ProgressBar> progressBar;
    private WeakReference<TextView> progressTextView;
    private JSONArray dataToUpdate;
    private JSONObject newVersionJson;
    private Dialog dialog;
    private String dataIdUpdating;

    DataUpdateTask(Context context, JSONArray dataToUpdate, JSONObject newVersionJson)
    {
        this.weakContext = new WeakReference<>(context);
        this.dataToUpdate = dataToUpdate;
        this.newVersionJson = newVersionJson;
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();

        dialog = new Dialog(weakContext.get());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_progress);

        progressBar = new WeakReference<>((ProgressBar) dialog.findViewById(R.id.progressBarDialog));
        progressTextView = new WeakReference<>((TextView) dialog.findViewById(R.id.textViewProgressDialog));

        progressBar.get().setProgress(0);
        progressTextView.get().setText(R.string.update_launching);
        dialog.show();
    }

    @Override
    protected String doInBackground(String... urls)
    {
        try
        {
            long filePercent = 100 / dataToUpdate.length();
            long total = 0;

            for(int i = 0; i < dataToUpdate.length(); i++)
            {
                JSONObject data = dataToUpdate.getJSONObject(i);
                String dataId = data.getString("id");
                String dataUrl = data.getString("url");

                dataIdUpdating = dataId;
                publishProgress();

                URL url = new URL(dataUrl);
                URLConnection conection = url.openConnection();
                conection.connect();

                int lenghtOfFile = conection.getContentLength();

                InputStream input = new BufferedInputStream(url.openStream(),8192);

                String newDataFilePath = weakContext.get().getFilesDir() + File.separator + dataId + ".json";
                OutputStream output = new FileOutputStream(newDataFilePath);

                byte dataReceived[] = new byte[1024];

                long totalFile = 0;
                int count;

                while ((count = input.read(dataReceived)) != -1) {
                   totalFile += count;
                   long progress = total + (totalFile * filePercent / lenghtOfFile);
                   publishProgress((int) progress);
                   output.write(dataReceived, 0, count);
                }

                output.flush();

                output.close();
                input.close();

                total += filePercent;
            }

            publishProgress(100);

            String versionFilePath = weakContext.get().getFilesDir() + File.separator + "version.json";
            File versionFile = new File(versionFilePath);

            if(!versionFile.exists())
                versionFile.createNewFile();

            FileOutputStream versionFileOutputStream = new FileOutputStream(versionFile);
            OutputStreamWriter versionFileOutputStreamWriter = new OutputStreamWriter(versionFileOutputStream);

            versionFileOutputStreamWriter.write(newVersionJson.toString());

            versionFileOutputStreamWriter.flush();
            versionFileOutputStreamWriter.close();

            versionFileOutputStream.flush();
            versionFileOutputStream.close();

            Thread.sleep(1000);

            return "DONE";
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values)
    {
        int strResId = weakContext.get().getResources().getIdentifier("update_data_" + dataIdUpdating, "string", weakContext.get().getPackageName());
        progressTextView.get().setText(String.format(weakContext.get().getString(R.string.update_updating_x), weakContext.get().getString(strResId).toLowerCase()));

        if(values.length > 0) {
            int progress = values[0];
            switch (progress) {
                case 100:
                    progressTextView.get().setText(R.string.update_data_done);
                    break;

                default:
                    progressBar.get().setProgress(values[0]);
                    break;
            }
        }
    }

    @Override
    protected void onPostExecute(String result)
    {
        if(result.equals("DONE"))
            dialog.cancel();
        else
            progressTextView.get().setText("Error: " + result);
    }
}