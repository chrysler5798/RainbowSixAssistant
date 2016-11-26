package com.khrys.r6spartner;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.SystemClock;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Chrysler on 10/14/2016.
 * <p>
 * RainbowSixPartner
 */

class ListAdapterMap extends RecyclerView.Adapter<ListAdapterMap.MyViewHolder> {

    private List<Integer> pics;
    private List<Integer> poscam;

    ListAdapterMap(ArrayList<Integer> pics, ArrayList<Integer> poscam){
        this.pics = pics;
        this.poscam = poscam;
    }

    @Override
    public int getItemCount() {
        return pics.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_map, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        if(pics != null) {
            Integer idimg = pics.get(position);
            Integer idtxt = poscam.get(position);
            holder.display(idimg, idtxt);
        }
    }


    @SuppressWarnings("deprecation")
    class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView image;
        private final TextView txtpos;
        private final ImageButton buttontwitch, buttonzoom;
        private final Context context;
        private long mLastClickTime = 0;

         MyViewHolder(final View itemView)
         {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.imgmap2);
            txtpos = (TextView) itemView.findViewById(R.id.textPosCam);
            buttontwitch = (ImageButton) itemView.findViewById(R.id.twitchButtonCam);
            buttonzoom = (ImageButton) itemView.findViewById(R.id.zoomButtonCam);

            context = itemView.getContext();

             int width1;
             WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
             if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                 width1 = windowManager.getDefaultDisplay().getWidth()/2;
             } else {
                 width1 = windowManager.getDefaultDisplay().getWidth();
             }
             itemView.setLayoutParams(new RecyclerView.LayoutParams(width1, RecyclerView.LayoutParams.MATCH_PARENT));

            buttontwitch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (SystemClock.elapsedRealtime() - mLastClickTime < 1000){
                        return;
                    }
                    mLastClickTime = SystemClock.elapsedRealtime();
                    removeAt(position);
                }
            });

            buttonzoom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent zoomintent = new Intent(context,ZoomActivity.class);
                    zoomintent.putExtra("imgid",(Integer) image.getTag());
                   context.startActivity(zoomintent);
                }
         });
        }

        void display(Integer idimg, Integer idtxt){
            image.setImageResource(idimg);
            image.setTag(idimg);
            txtpos.setText(idtxt);
        }

        void removeAt(int position) {
            poscam.remove(position);
            pics.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, poscam.size());
            notifyItemRangeChanged(position, pics.size());
        }
    }
}
