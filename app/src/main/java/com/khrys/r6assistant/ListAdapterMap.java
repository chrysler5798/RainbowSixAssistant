package com.khrys.r6assistant;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
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

/*
 * Created by Chrysler on 10/14/2016.
 * RainbowSixPartner
*/

class ListAdapterMap extends RecyclerView.Adapter<ListAdapterMap.MyViewHolder>
{

    private List<Integer> pics;
    private List<Integer> poscam;
    private int type;
    private ChangeCamRemInterface listenerCam;

    ListAdapterMap(ArrayList<Integer> pics, ArrayList<Integer> poscam, int type)
    {

        this.pics = pics;
        this.poscam = poscam;
        this.type = type;
    }


     ListAdapterMap(ArrayList<Integer> pics, ArrayList<Integer> poscam, int type, ChangeCamRemInterface listener)
     {
        this.pics = pics;
        this.poscam = poscam;
        this.type = type;
        this.listenerCam = listener;
    }

    @Override
    public int getItemCount()
    {
        return pics.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        if(type == 1)
        {
            view = inflater.inflate(R.layout.list_map, parent, false);
        } else {
            view = inflater.inflate(R.layout.list_map_twitch, parent, false);
        }
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


    class MyViewHolder extends RecyclerView.ViewHolder
    {

        private final ImageView image;
        private final TextView txtpos;
        private final Context context;
        private long mLastClickTime = 0;

         MyViewHolder(final View itemView)
         {
            super(itemView);

             image = (ImageView) itemView.findViewById(R.id.imgmap2);
             txtpos = (TextView) itemView.findViewById(R.id.textPosCam);

             context = itemView.getContext();

             if (type == 1)
             {
                 image.setOnClickListener(new View.OnClickListener()
                 {
                     @Override
                     public void onClick(View v)
                     {
                         Intent zoomintent = new Intent(context,ZoomActivity.class);
                         zoomintent.putExtra("imgid", (Integer) image.getTag());
                         context.startActivity(zoomintent);
                     }
                 });
             }
             else if(type == 2)
             {
                 ImageButton buttontwitch = (ImageButton) itemView.findViewById(R.id.twitchButtonCam);
                 ImageButton buttonzoom = (ImageButton) itemView.findViewById(R.id.zoomButtonCam);

                 buttontwitch.setOnClickListener(new View.OnClickListener()
                 {
                     @Override
                     public void onClick(View v)
                     {
                         int position = getAdapterPosition();
                         if (SystemClock.elapsedRealtime() - mLastClickTime < 1000){
                             return;
                         }
                         mLastClickTime = SystemClock.elapsedRealtime();
                         removeAt(position);
                         listenerCam.notifyCamRem(getItemCount());
                     }
                 });

                 buttonzoom.setOnClickListener(new View.OnClickListener()
                 {
                     @Override
                     public void onClick(View v)
                     {
                         Intent zoomintent = new Intent(context,ZoomActivity.class);
                         zoomintent.putExtra("imgid", (Integer) image.getTag());
                         context.startActivity(zoomintent);
                     }
                 });
             }
        }

        void display(Integer idimg, Integer idtxt)
        {
            image.setImageResource(idimg);
            image.setTag(idimg);
            txtpos.setText(idtxt);
        }

        void removeAt(int position)
        {
            poscam.remove(position);
            pics.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, poscam.size());
            notifyItemRangeChanged(position, pics.size());
        }
    }
}