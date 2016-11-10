package com.khrys.rainbowsixpartner;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class ListAdapterMap extends RecyclerView.Adapter<ListAdapterMap.MyViewHolder> {

    List<Integer> pics;
    List<Integer> poscam;

    public ListAdapterMap(ArrayList<Integer> pics, ArrayList<Integer> poscam){
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


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView image;
        private final TextView txtpos;
        public ImageButton buttontwitch, buttonzoom;
        private final Context context;

        public MyViewHolder(final View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.imgmap2);
            txtpos = (TextView) itemView.findViewById(R.id.textPosCam);
            buttontwitch = (ImageButton) itemView.findViewById(R.id.twitchButtonCam);
            buttonzoom = (ImageButton) itemView.findViewById(R.id.zoomButtonCam);

            context = itemView.getContext();

            buttontwitch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    removeAt(position);
                    buttontwitch.setEnabled(false);
                }
            });

            buttonzoom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent thisintent = new Intent(context,ZoomActivity.class);
                    thisintent.putExtra("imgid",(Integer) image.getTag());
                   context.startActivity(thisintent);
                }
         });
        }

        public void display(Integer idimg, Integer idtxt){
            image.setImageResource(idimg);
            image.setTag(idimg);
            txtpos.setText(idtxt);
        }

        public void removeAt(int position) {
                poscam.remove(position);
                pics.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, poscam.size());
                notifyItemRangeChanged(position, pics.size());
        }
    }
}
