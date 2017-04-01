package com.khrys.r6assistant.weapons;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.khrys.r6assistant.R;

/*
 * Created by Chrysler on 3/16/2017.
 * RainbowSixAssistant
*/

class OperatorsViewHolder extends ParentViewHolder {

    ImageView imageView;
    TextView mCrimeTitleTextView;

    OperatorsViewHolder(View itemView) {
        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.imageVarme);
        mCrimeTitleTextView = (TextView) itemView.findViewById(R.id.textVarme);
        ImageButton mParentDropDownArrow = (ImageButton) itemView.findViewById(R.id.parent_list_item_expand_arrow);
    }
}
