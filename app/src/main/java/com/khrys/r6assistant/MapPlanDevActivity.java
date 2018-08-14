package com.khrys.r6assistant;
/*
 * Created by Khrys.
 *
 * App : RainbowSixAssistant
 * Info : 4/15/2018 [1:48 PM]
*/

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
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.khrys.r6assistant.data.LoadData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import uk.co.senab.photoview.PhotoViewAttacher;

public class MapPlanDevActivity extends AppCompatActivity
{
        int minFloor, maxFloor, nbFloor, currentfloorTxt, currentfloorImg, currentPos;
        ArrayList<Integer> posmaps = new ArrayList<>();

        ImageView imageView;
        TextView TextViewFloor, textViewPos, textViewX, textViewY, textViewTextsize;
        PhotoViewAttacher mAttacher;
        String mapId;
        JSONObject mapPositions;
        float posX = 0, posY = 0, posTextsize = 0;

        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_mapplan_dev);

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

            imageView = findViewById(R.id.imageViewPlan);

            TextViewFloor = findViewById(R.id.textViewEtage);

            textViewPos = findViewById(R.id.textViewPos);

            textViewX = findViewById(R.id.textViewX);
            textViewY = findViewById(R.id.textViewY);
            textViewTextsize = findViewById(R.id.textViewTextsize);

            ImageButton ZoomResetBut = findViewById(R.id.buttonZoomReset);
            ImageButton ZoomPlusBut  = findViewById(R.id.buttonZoomIn);
            ImageButton ZoomMinusBut = findViewById(R.id.buttonZoomOut);

            ImageButton prevFloorBut = findViewById(R.id.buttonMinusFloor);
            ImageButton nextFloorBut = findViewById(R.id.buttonPlusFloor);

            Button plusPosBut = findViewById(R.id.buttonPosPlus);
            Button minusPosBut = findViewById(R.id.buttonPosMinus);

            Button minusXBut = findViewById(R.id.buttonMinusX);
            Button plusXBut = findViewById(R.id.buttonPlusX);
            Button minusYBut = findViewById(R.id.buttonMinusY);
            Button plusYBut = findViewById(R.id.buttonPlusY);
            Button minusTextsizeBut = findViewById(R.id.buttonMinusTextsize);
            Button plusTextsizeBut = findViewById(R.id.buttonPlusTextsize);

            mAttacher = new PhotoViewAttacher(imageView);
            mAttacher.setMaximumScale(6.0F);

            ZoomMinusBut.setOnClickListener(new ClickListenerZoom(1, mAttacher));
            ZoomResetBut.setOnClickListener(new ClickListenerZoom(2, mAttacher));
            ZoomPlusBut.setOnClickListener(new ClickListenerZoom(3, mAttacher));

            prevFloorBut.setOnClickListener(new ClickListenerMinusPlusFloor(1));
            nextFloorBut.setOnClickListener(new ClickListenerMinusPlusFloor(2));

            plusPosBut.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    int pos = currentPos;
                    currentPos++;
                    SetTextAndImg();

                    JSONObject position;
                    try
                    {
                        JSONObject oldPosition = mapPositions.getJSONArray(String.valueOf(currentfloorImg)).getJSONObject(pos);

                        JSONObject newPositions = new JSONObject();
                        newPositions.put("posTextId", oldPosition.getString("posTextId"));
                        newPositions.put("x", posX);
                        newPositions.put("y", posY);
                        newPositions.put("textSize", posTextsize);

                        savePositions(newPositions, pos);

                        position = mapPositions.getJSONArray(String.valueOf(currentfloorImg)).getJSONObject(currentPos);
                        posX = (float) position.getDouble("x");
                        posY = (float) position.getDouble("y");
                        posTextsize = (float) position.getDouble("textSize");

                        Log.i("MAP_POS", "{\"posTextId\":\""+ oldPosition.getString("posTextId") +"\",\"x\":"+ (int) Float.parseFloat(textViewX.getText().toString()) +",\"y\":"+ (int) Float.parseFloat(textViewY.getText().toString()) +",\"textSize\":"+ (int) Float.parseFloat(textViewTextsize.getText().toString()) +"},");

                        textViewX.setText(String.valueOf(posX));
                        textViewY.setText(String.valueOf(posY));
                        textViewTextsize.setText(String.valueOf(posTextsize));
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
            });

            minusPosBut.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    if(currentPos > 0)
                    {
                        int pos = currentPos;
                        currentPos--;
                        SetTextAndImg();

                        JSONObject position;
                        try
                        {
                            JSONObject oldPosition = mapPositions.getJSONArray(String.valueOf(currentfloorImg)).getJSONObject(pos);

                            JSONObject newPositions = new JSONObject();
                            newPositions.put("posTextId", oldPosition.getString("posTextId"));
                            newPositions.put("x", posX);
                            newPositions.put("y", posY);
                            newPositions.put("textSize", posTextsize);

                            savePositions(newPositions, pos);

                            position = mapPositions.getJSONArray(String.valueOf(currentfloorImg)).getJSONObject(currentPos);
                            posX = (float) position.getDouble("x");
                            posY = (float) position.getDouble("y");
                            posTextsize = (float) position.getDouble("textSize");

                            Log.i("MAP_POS", "{\"posTextId\":\""+ oldPosition.getString("posTextId") +"\",\"x\":"+ (int) Float.parseFloat(textViewX.getText().toString()) +",\"y\":"+ (int) Float.parseFloat(textViewY.getText().toString()) +",\"textSize\":"+ (int) Float.parseFloat(textViewTextsize.getText().toString()) +"},");

                            textViewX.setText(String.valueOf(posX));
                            textViewY.setText(String.valueOf(posY));
                            textViewTextsize.setText(String.valueOf(posTextsize));


                        } catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            });

            minusXBut.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    if(posX > 0)
                    {
                        posX--;
                        textViewX.setText(String.valueOf(posX));
                        SetTextAndImg();
                    }
                }
            });

            minusXBut.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View view)
                {
                    if(posX > 0)
                    {
                        posX-=10;
                        textViewX.setText(String.valueOf(posX));
                        SetTextAndImg();
                    }
                    return false;
                }
            });

            plusXBut.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    posX++;
                    textViewX.setText(String.valueOf(posX));
                    SetTextAndImg();
                }
            });

            plusXBut.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View view)
                {
                    posX+=10;
                    textViewX.setText(String.valueOf(posX));
                    SetTextAndImg();
                    return false;
                }
            });

            minusYBut.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    if(posY > 0)
                    {
                        posY--;
                        textViewY.setText(String.valueOf(posY));
                        SetTextAndImg();
                    }
                }
            });

            minusYBut.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View view)
                {
                    if(posY > 0)
                    {
                        posY-=10;
                        textViewY.setText(String.valueOf(posY));
                        SetTextAndImg();
                    }
                    return false;
                }
            });

            plusYBut.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    posY++;
                    textViewY.setText(String.valueOf(posY));
                    SetTextAndImg();
                }
            });

            plusYBut.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View view)
                {
                    posY+=10;
                    textViewY.setText(String.valueOf(posY));
                    SetTextAndImg();
                    return false;
                }
            });

            minusTextsizeBut.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    if(posTextsize > 0)
                    {
                        posTextsize--;
                        textViewTextsize.setText(String.valueOf(posTextsize));
                        SetTextAndImg();
                    }
                }
            });

            minusTextsizeBut.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View view)
                {
                    if(posTextsize > 0)
                    {
                        posTextsize-=10;
                        textViewTextsize.setText(String.valueOf(posTextsize));
                        SetTextAndImg();
                    }
                    return false;
                }
            });

            plusTextsizeBut.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    posTextsize++;
                    textViewTextsize.setText(String.valueOf(posTextsize));
                    SetTextAndImg();
                }
            });

            plusTextsizeBut.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View view)
                {
                    posTextsize+=10;
                    textViewTextsize.setText(String.valueOf(posTextsize));
                    SetTextAndImg();
                    return false;
                }
            });

            LoadData dataLoader = new LoadData();
            JSONObject mapsData;
            try
            {
                mapsData = dataLoader.loadData(this, dataLoader.RES_MAPS).getJSONObject(mapId);
                minFloor = Integer.parseInt(String.valueOf(mapsData.getInt("minfloor")));
                maxFloor = Integer.parseInt(String.valueOf(mapsData.getInt("maxfloor")));
                mapPositions = loadPositions().getJSONObject(mapId).getJSONObject("positions");
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }

            nbFloor = maxFloor - minFloor;

            initPlans(mapId);

            setupVarAndImageAndFloor();
        }

        String folderPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator
                + "r6assistant";

        String filePath = folderPath + File.separator + "positions.json";

        File folder = new File(folderPath);
        File file = new File(filePath);

        JSONObject loadPositions()
        {
            try
            {
                LoadData dataLoader = new LoadData();
                return dataLoader.loadData(this, dataLoader.RES_MAPS);
            } catch (JSONException e)
            {
                e.printStackTrace();
            }
            return null;
        }

        JSONObject loadPosFromFile()
        {
            StringBuilder text = new StringBuilder();
            try
            {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null)
                {
                    text.append(line);
                    text.append('\n');
                }
                br.close();

                return new JSONObject(text.toString());
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
            return null;
        }

        void savePositions(JSONObject position, int currentPos)
        {
//            try
//            {
//                JSONObject mapsData;
//                if(folder.exists() && file.exists())
//                {
//                    mapsData = loadPosFromFile();
//                }
//                else
//                {
//                    folder.mkdir();
//                    file.createNewFile();
//                    LoadData dataLoader = new LoadData();
//                    mapsData = dataLoader.loadData(this, dataLoader.RES_MAPS);
//                }
//
//                FileOutputStream fileOutput = new FileOutputStream(file);
//                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutput);
//
//                JSONObject mapData = mapsData.getJSONObject(mapId);
//                JSONArray mapPosFloor = mapData.getJSONObject("positions").getJSONArray(String.valueOf(currentfloorImg));
//
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
//                {
//                    mapPosFloor.remove(currentPos);
//                }
//
//                mapPosFloor.put(position);
//
//                JSONObject newPosObj = mapsData.put("maps_data", mapsInfos).put(mapId, mapData).put("positions", mapPos);
//
//                outputStreamWriter.write(newPosObj.toString(1));
//
//                outputStreamWriter.flush();
//                fileOutput.getFD().sync();
//                outputStreamWriter.close();
//
//                //mapPositions = loadPositions().getJSONObject("maps_data").getJSONObject(mapId).getJSONObject("positions");
//            }
//            catch (IOException e)
//            {
//                e.printStackTrace();
//            }
//            catch (JSONException e)
//            {
//                e.printStackTrace();
//            }
        }

        void SetTextAndImg()
        {
            try
            {
                TextViewFloor.setText(SwitchFloorTxt(currentfloorTxt));

                String posTextId = mapPositions.getJSONArray(String.valueOf(currentfloorImg)).getJSONObject(currentPos).getString("posTextId");
                String posNameFinal = getString(getResources().getIdentifier("map_pos_" + posTextId, "string", getApplicationContext().getPackageName()));
                textViewPos.setText(posNameFinal);

                new SetImage(posmaps.get(currentfloorImg),0, mapPositions.getJSONArray(String.valueOf(currentfloorImg))).execute();
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }

        void initPlans(String mapId)
        {
            for (int i = 0; i <= nbFloor; i++)
            {
                String idPlan = "plan_" + mapId + "_" + i;
                posmaps.add(getResources().getIdentifier(idPlan, "drawable", getApplicationContext().getPackageName()));
            }
        }

        void setupVarAndImageAndFloor()
        {
            currentfloorImg = 0;
            currentPos = 0;
            currentfloorTxt = minFloor;
            try
            {
                new SetImage(posmaps.get(0),0, mapPositions.getJSONArray("0")).execute();
                TextViewFloor.setText(SwitchFloorTxt(minFloor));

                String posTextId = mapPositions.getJSONArray(String.valueOf(currentfloorImg)).getJSONObject(currentPos).getString("posTextId");
                String posNameFinal = getString(getResources().getIdentifier("map_pos_" + posTextId, "string", getApplicationContext().getPackageName()));
                textViewPos.setText(posNameFinal);
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }

        private Bitmap drawTextToBitMap(Bitmap gBitmap, JSONObject position, String posName, float x, float y, float textSize, float rotate)
        {
            Bitmap bitmap = gBitmap;

            Bitmap.Config bitmapConfig = bitmap.getConfig();
            if(bitmapConfig == null)
            {
                bitmapConfig = Bitmap.Config.ARGB_8888;
            }

            bitmap = bitmap.copy(bitmapConfig, true);

            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            int color = Color.WHITE;
            try
            {
                if(position.has("color") && position.getString("color").equals("black"))
                {
                    color = Color.BLACK;
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
            paint.setColor(color);
            paint.setTypeface(Typeface.create("sans-serif-light",Typeface.NORMAL));
            float scale = getResources().getDisplayMetrics().density;
            paint.setTextSize(1 * scale);
            paint.setShadowLayer(1f, 0f, 1f, color);

            String posNameFinal = "";
            try
            {
                posNameFinal = getString(getResources().getIdentifier("map_pos_" + posName, "string", getApplicationContext().getPackageName()));
            }
            catch (Resources.NotFoundException e)
            {
                Log.e("RESOURCES_POS_ISSUE", " for " + "map_pos_" + posName);
                e.printStackTrace();
            }

            setTextSizeForWidth(paint, textSize, posNameFinal);

            canvas.rotate(rotate, x, y); //rotates 180 degrees
            canvas.drawText(posNameFinal, x, y, paint);
            //canvas.restore();

            return bitmap;
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
                        SetTextAndImg();
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
                        SetTextAndImg();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),  R.string.error_floor_above, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            void SetTextAndImg()
            {
                try
                {
                    TextViewFloor.setText(SwitchFloorTxt(currentfloorTxt));
                    new SetImage(posmaps.get(currentfloorImg),1, mapPositions.getJSONArray(String.valueOf(currentfloorImg))).execute();
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
            JSONArray positions;

            SetImage(int redId, int type, JSONArray positions)
            {
                this.redId = redId;
                this.type = type;
                this.positions = positions;
            }

            @Override
            protected Bitmap doInBackground(Bitmap... thumb)
            {
                return BitmapFactory.decodeResource(getResources(), redId);
            }

            @Override
            protected void onPostExecute(Bitmap result)
            {
                if(type == 0)
                {
                    try
                    {
//                        JSONObject position = positions.getJSONObject(currentPos);
//
//                        if(posX == 0)
//                        {
//                            posX = (float) position.getDouble("x");
//                        }
//
//                        if(posY == 0)
//                        {
//                            posY = (float) position.getDouble("y");
//                        }
//
//                        if(posTextsize == 0)
//                        {
//                            posTextsize = (float) position.getDouble("textSize");
//                        }
//
//                        result = drawTextToBitMap(result, position.getString("posTextId"),
//                                posX,
//                                posY,
//                                posTextsize);

                        for(int i = 0; i < positions.length(); i++)
                        {
                            JSONObject position = positions.getJSONObject(i);

                            float posXf = (float) position.getDouble("x");
                            float posYf = (float) position.getDouble("y");
                            float textSizef = (float) position.getDouble("textSize");
                            float textRotate = position.has("rotate") ? (float) position.getDouble("rotate") : 0;

                            if(currentPos == i && (posX != 0 && posY != 0 && posTextsize != 0))
                            {
                                posXf = posX;
                                posYf = posY;
                                textSizef = posTextsize;
                            }

                            result = drawTextToBitMap(result, position, position.getString("posTextId"),
                                    posXf, posYf, textSizef, textRotate);
                        }
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }

                }

                float zoom = mAttacher.getScale();
                imageView.setImageBitmap(result);
                mAttacher.update();
                mAttacher.setScale(zoom);
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