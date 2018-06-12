package com.khrys.r6assistant;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/*
 * Created by Chrysler on 10/1/2016.
 * RainbowSixPartner
*/

class ListAdapterMapMenu extends RecyclerView.Adapter<ListAdapterMapMenu.MyViewHolder>
{

        private int requesttype;
        private ArrayList<Map> maps;

        ListAdapterMapMenu (int request, ArrayList<Map> maps)
        {
            requesttype = request;
            this.maps = maps;
        }

        @Override
        public int getItemCount()
        {
            return maps.size();
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.list_grid, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position)
        {
            Map map = maps.get(position);
            holder.display(map);
        }

        class MyViewHolder extends RecyclerView.ViewHolder
        {
            private final TextView name;
            private final ImageView image;
            private final Context context;

             MyViewHolder(final View itemView)
             {
                super(itemView);

                context = itemView.getContext();

                 WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
                 if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
                 {
                     //int width1 = windowManager.getDefaultDisplay().getWidth();
                     //itemView.setLayoutParams(new RecyclerView.LayoutParams(width1, RecyclerView.LayoutParams.MATCH_PARENT));
                 }

                name = itemView.findViewById(R.id.map);
                image = itemView.findViewById(R.id.imgmap);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(requesttype == 1)
                        {
                            startIntentWithInput(MapActivity.class);
                        }
                        else if (requesttype == 2)
                        {
                            startIntentWithInput(TwitchMapActivity.class);
                        }
                        else if (requesttype == 3)
                        {
                            startIntentWithInput(MapPlanActivity.class);
                        }

                    }
                });
            }

            void startIntentWithInput(Class newclass)
            {
                Intent intent = new Intent(context, newclass);
                intent.putExtra("map", maps.get(getAdapterPosition()));
                context.startActivity(intent);
            }

            void display(Map map) {
                name.setText(map.getMapNameId());
                new SetImage(map.getMapImgId(), image, context).execute();
            }
        }

        private class SetImage extends AsyncTask<Bitmap, Void, Bitmap>
        {
            int redId;
            ImageView imgV;
            Context context;


            SetImage(int redId, ImageView imgV, Context context)
            {
                this.redId = redId;
                this.imgV = imgV;
                this.context = context;
            }

            @Override
            protected void onPreExecute() {
                imgV.setVisibility(View.GONE);
            }

            @Override
            protected Bitmap doInBackground(Bitmap... thumb)
            {
                return BitmapFactory.decodeResource(context.getResources(), redId);
            }

            @Override
            protected void onPostExecute(Bitmap result)
            {
                imgV.setImageBitmap(result);
                imgV.setVisibility(View.VISIBLE);
            }
        }
}