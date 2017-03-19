package com.khrys.r6assistant.settings;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.khrys.r6assistant.R;

/**
 * Created by Chrysler on 12/20/2016.
 * <p>
 * RainbowSixAssistant
 */

class SpinnerAdapter extends ArrayAdapter<String>
{

    private Context ctx;
    private String[] contentArray;
    private Integer[] imageArray;

    SpinnerAdapter(Context context, String[] objects, Integer[] imageArray)
    {
        super(context,  R.layout.activity_settings, R.id.spinnerLanguage, objects);
        this.ctx = context;
        this.contentArray = objects;
        this.imageArray = imageArray;
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent)
    {
        return getCustomView(position, parent);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent)
    {
        return getCustomView(position, parent);
    }

    private View getCustomView(int position, ViewGroup parent)
    {

        LayoutInflater inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.spinner_lang_value, parent, false);

        TextView textView = (TextView) row.findViewById(R.id.spinnerTextView);
        textView.setText(contentArray[position]);

        ImageView imageView = (ImageView)row.findViewById(R.id.spinnerImages);
        imageView.setImageResource(imageArray[position]);

        return row;
    }
}
