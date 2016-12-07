package com.khrys.r6assistant;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

/**
 * Created by Chrysler on 10/1/2016.
 * <p>
 * RainbowSixPartner
 */

class ListAdapterMapMenu extends RecyclerView.Adapter<ListAdapterMapMenu.MyViewHolder> {

        private int requesttype;


        ListAdapterMapMenu (int request)
        {
            requesttype = request;
        }

        private MapSwitch mapS = new MapSwitch();
        private List<Pair<Integer, Integer>> maps = mapS.SwitchMapName();

        @Override
        public int getItemCount() {
            return maps.size();
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.list_cell, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Pair<Integer, Integer> pair = maps.get(position);
            holder.display(pair);
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            private final TextView name;
            private final ImageView image;

            private final Context context;

            Intent intent;

             MyViewHolder(final View itemView) {
                super(itemView);

                context = itemView.getContext();

                 int width1;
                 WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
                 if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                     width1 = windowManager.getDefaultDisplay().getWidth();
                     itemView.setLayoutParams(new RecyclerView.LayoutParams(width1, RecyclerView.LayoutParams.MATCH_PARENT));
                 }

                name = ((TextView) itemView.findViewById(R.id.map));
                image = ((ImageView) itemView.findViewById(R.id.imgmap));

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
                intent = new Intent(context, newclass);
                intent.putExtra("nommap",name.getText().toString());
                intent.putExtra("pos", getAdapterPosition());
                context.startActivity(intent);
            }

            void display(Pair<Integer, Integer> pair) {
                name.setText(pair.first);
                image.setImageResource(pair.second);
            }
        }

}
