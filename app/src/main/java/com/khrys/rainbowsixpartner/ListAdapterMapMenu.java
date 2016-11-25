package com.khrys.rainbowsixpartner;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Chrysler on 10/1/2016.
 * <p>
 * RainbowSixPartner
 */

class ListAdapterMapMenu extends RecyclerView.Adapter<ListAdapterMapMenu.MyViewHolder> {


        private final List<Pair<Integer, Integer>> maps = Arrays.asList(
                Pair.create(R.string.bank, R.drawable.mapbank),
                Pair.create(R.string.chalet, R.drawable.mapchalet),
                Pair.create(R.string.clubhouse, R.drawable.mapclubhouse),
                Pair.create(R.string.consulate, R.drawable.mapconsulate),
                Pair.create(R.string.hereford, R.drawable.maphereford),
                Pair.create(R.string.house, R.drawable.maphouse),
                Pair.create(R.string.kafedostoyevsky, R.drawable.mapkafedostoyevsky),
                Pair.create(R.string.kanal, R.drawable.mapkanal),
                Pair.create(R.string.oregon, R.drawable.maporegon),
                Pair.create(R.string.plane, R.drawable.mapplane),
                Pair.create(R.string.favela, R.drawable.mapfavela),
                Pair.create(R.string.border, R.drawable.mapborder),
                Pair.create(R.string.yacht, R.drawable.mapyacht)
        );


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

             MyViewHolder(final View itemView) {
                super(itemView);

                context = itemView.getContext();

                name = ((TextView) itemView.findViewById(R.id.map));
                image = ((ImageView) itemView.findViewById(R.id.imgmap));

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Intent intent;
                        intent = new Intent(context, MapActivity.class);
                        intent.putExtra("nommap",name.getText().toString());
                        intent.putExtra("pos", getAdapterPosition());
                        context.startActivity(intent);
                    }
                });
            }

            void display(Pair<Integer, Integer> pair) {
                name.setText(pair.first);
                image.setImageResource(pair.second);
            }
        }

}
