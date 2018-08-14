package com.khrys.r6assistant;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.khrys.r6assistant.data.LoadData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import uk.co.senab.photoview.PhotoViewAttacher;

/*
 * Created by Louis on 06/12/2016.
 * RainbowSixAssistant
*/

public class MapPlanActivity extends AppCompatActivity
{
    int minFloor, maxFloor, nbFloor, currentfloorTxt, currentfloorImg;
    ArrayList<Integer> posmaps = new ArrayList<>();

    Switch switchPosRef;

    ImageView imageView;
    TextView TextViewFloor;
    PhotoViewAttacher mAttacher;
    String mapId;
    JSONObject mapPositions;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mapplan);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Map map = (Map) getIntent().getSerializableExtra("map");
        mapId = map.getMapId();
        String mapName = map.getMapName();

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            setTitle(getResources().getString(R.string.app_name) + " - " + mapName);
        }
        else
        {
            TextView titleTextView = findViewById(R.id.txtTitlePlan);
            titleTextView.setText(mapName);
        }

        switchPosRef = findViewById(R.id.switchPos);

        imageView = findViewById(R.id.imageViewPlan);

        TextViewFloor = findViewById(R.id.textViewEtage);

        ImageButton ZoomResetBut = findViewById(R.id.buttonZoomReset);
        ImageButton ZoomPlusBut  = findViewById(R.id.buttonZoomIn);
        ImageButton ZoomMinusBut = findViewById(R.id.buttonZoomOut);

        ImageButton prevFloorBut = findViewById(R.id.buttonMinusFloor);
        ImageButton nextFloorBut = findViewById(R.id.buttonPlusFloor);

        mAttacher = new PhotoViewAttacher(imageView);
        mAttacher.setMaximumScale(6.0F);

        ZoomMinusBut.setOnClickListener(new ClickListenerZoom(1, mAttacher));
        ZoomResetBut.setOnClickListener(new ClickListenerZoom(2, mAttacher));
        ZoomPlusBut.setOnClickListener(new ClickListenerZoom(3, mAttacher));

        prevFloorBut.setOnClickListener(new ClickListenerMinusPlusFloor(1));
        nextFloorBut.setOnClickListener(new ClickListenerMinusPlusFloor(2));

        LoadData dataLoader = new LoadData();
        JSONObject mapsData;
        try
        {
            mapsData = dataLoader.loadData(this, dataLoader.RES_MAPS).getJSONObject(mapId);
            minFloor = Integer.parseInt(String.valueOf(mapsData.getInt("minfloor")));
            maxFloor = Integer.parseInt(String.valueOf(mapsData.getInt("maxfloor")));
            mapPositions = mapsData.getJSONObject("positions");
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        nbFloor = maxFloor - minFloor;

        initPlans(mapId);

        switchPosRef.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                setupVarAndImageAndFloor(isChecked);
            }
        });

        setupVarAndImageAndFloor(false);
    }

    void initPlans(String mapId)
    {
        for (int i = 0; i <= nbFloor; i++)
        {
            String idPlan = "plan_" + mapId + "_" + i;
            posmaps.add(getResources().getIdentifier(idPlan, "drawable", getApplicationContext().getPackageName()));
        }
    }

    void setupVarAndImageAndFloor(boolean withPositions)
    {
        currentfloorImg = 0;
        currentfloorTxt = minFloor;

        try
        {
            new SetImage(posmaps.get(0),0, withPositions, mapPositions.getJSONArray("0")).execute();
            TextViewFloor.setText(SwitchFloorTxt(minFloor));
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    private Bitmap drawTextToBitMap(Bitmap gBitmap, JSONObject position)
    {
        String posNameForLog = "";
        try
        {
            String posName = position.getString("posTextId");
            posNameForLog = "map_pos_" + posName;
            String posNameFinal = getString(getResources().getIdentifier("map_pos_" + posName, "string", getApplicationContext().getPackageName()));

            float x = (float) position.getDouble("x");
            float y = (float) position.getDouble("y");
            float textSize = (float) position.getDouble("textSize");
            float textRotate = position.has("rotate") ? (float) position.getDouble("rotate") : 0;

            int color = Color.WHITE;
            if(position.has("color") && position.getString("color").equals("black"))
            {
                color = Color.BLACK;
            }

            Bitmap bitmap = gBitmap;

            Bitmap.Config bitmapConfig = bitmap.getConfig();
            if(bitmapConfig == null)
            {
                bitmapConfig = Bitmap.Config.ARGB_8888;
            }

            bitmap = bitmap.copy(bitmapConfig, true);

            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

            paint.setColor(color);
            paint.setTypeface(Typeface.create("sans-serif-light",Typeface.NORMAL));

            float scale = getResources().getDisplayMetrics().density;
            paint.setTextSize(100 * scale);
            paint.setShadowLayer(1f, 0f, 1f, color);
            setTextSizeForWidth(paint, textSize, posNameFinal);
            canvas.rotate(textRotate, x, y);
            canvas.drawText(posNameFinal, x, y, paint);

            return bitmap;
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        catch (Resources.NotFoundException e)
        {
            Log.e("RESOURCES_POS_ISSUE", " for " + posNameForLog);
            e.printStackTrace();
        }

        return gBitmap;
    }

    private void setTextSizeForWidth(Paint paint, float desiredWidth, String text)
    {
        final float testTextSize = 48f;
        paint.setTextSize(testTextSize);
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        float desiredTextSize = testTextSize * desiredWidth / bounds.width();
        paint.setTextSize(desiredTextSize);
    }

    String SwitchFloorTxt(int floor)
    {
        switch(floor)
        {
            case -1:
                return getResources().getString(R.string.f_base);

            case 0:
                return getResources().getString(R.string.f_0);

            case 1:
                return getResources().getString(R.string.f_1);

            case 2:
                return getResources().getString(R.string.f_2);

            case 3:
                return getResources().getString(R.string.f_3);

            default:
                return "";
        }
    }

    private class ClickListenerMinusPlusFloor implements View.OnClickListener
    {
        int requesttype;

        ClickListenerMinusPlusFloor(int requesttype)
        {
            this.requesttype = requesttype;
        }

        @Override
        public void onClick(View view)
        {
            if(requesttype == 1)
            {
                if(currentfloorTxt != minFloor)
                {
                    currentfloorTxt--;
                    currentfloorImg--;
                    SetTextAndImg(switchPosRef.isChecked());
                }
                else
                {
                    Toast.makeText(getApplicationContext(), R.string.error_floor_below, Toast.LENGTH_SHORT).show();
                }
            }
            else if (requesttype == 2)
            {
                if(currentfloorTxt != maxFloor)
                {
                    currentfloorTxt++;
                    currentfloorImg++;
                    SetTextAndImg(switchPosRef.isChecked());
                }
                else
                {
                    Toast.makeText(getApplicationContext(),  R.string.error_floor_above, Toast.LENGTH_SHORT).show();
                }
            }
        }

        void SetTextAndImg(boolean withPositions)
        {
            try
            {
                TextViewFloor.setText(SwitchFloorTxt(currentfloorTxt));
                new SetImage(posmaps.get(currentfloorImg),1, withPositions, mapPositions.getJSONArray(String.valueOf(currentfloorImg))).execute();
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
    }

    private class SetImage extends AsyncTask<Bitmap, Void, Bitmap>
    {
        int redId;
        int type;
        boolean withPositions;
        JSONArray positions;

        SetImage(int redId, int type, boolean withPositions, JSONArray positions)
        {
            this.redId = redId;
            this.type = type;
            this.withPositions = withPositions;
            this.positions = positions;
        }

        @Override
        protected Bitmap doInBackground(Bitmap... thumb)
        {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), redId);
            if(withPositions)
            {
                int nbPositions = positions.length();

                for(int i = 0; i < nbPositions; i++)
                {
                    try
                    {
                        JSONObject position = positions.getJSONObject(i);

                        bitmap = drawTextToBitMap(bitmap, position);
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }

                }
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result)
        {
            imageView.setImageBitmap(result);

            if(type == 0)
            {
                mAttacher.update();
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
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