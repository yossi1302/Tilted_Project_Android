package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.myapplication.R;

import java.util.ArrayList;

import com.example.myapplication.Row;

public class RowArrayAdapter extends ArrayAdapter<Row> {
    private Context context;
    private int resource;

    public RowArrayAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Row> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        if (convertView == null)
            convertView = LayoutInflater.from(this.context).inflate(this.resource, parent, false);
        Row row = getItem(position);
        if (row!=null){
            ImageView imageView = convertView.findViewById(R.id.image);
            imageView.setImageDrawable(ContextCompat.getDrawable(context,row.getImg()));
            TextView topic = convertView.findViewById(R.id.RTopic);
            topic.setText(row.getTopic());
            TextView text = convertView.findViewById(R.id.RText);
            text.setText(row.getText());
        }
        return convertView;
    }
}
