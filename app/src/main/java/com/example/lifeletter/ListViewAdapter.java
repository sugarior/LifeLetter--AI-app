package com.example.lifeletter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends ArrayAdapter<TodoList> {

    private Context mcontext;
    private int mresource;


    public ListViewAdapter(@NonNull Context context, int resource, @NonNull ArrayList<TodoList> objects) {
        super(context, resource, objects);

        this.mcontext =context;
        this.mresource= resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(this.mcontext).inflate(this.mresource, parent,false);
        TextView name = convertView.findViewById(R.id.name);
        TextView time = convertView.findViewById(R.id.time);
        TextView location = convertView.findViewById(R.id.location);

        name.setText(getItem(position).getName());
        time.setText(getItem(position).getTime());
        location.setText(getItem(position).getLocation());


        return convertView;
    }
}
