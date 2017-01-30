package com.example.assignment1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


//https://looksok.wordpress.com/2012/11/03/android-custom-listview-tutorial/

/**
 * Created by Ayuzawa on 2017-01-29.
 */

public class RecordListAdapter extends ArrayAdapter<Record> {

    private ArrayList<Record> items;
    private int layoutResourceId;
    private Context context;

    public RecordListAdapter(Context context, int layoutResourceId, ArrayList<Record> items) {
        super(context, layoutResourceId, items); // default ArrayAdapter constructor
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        row = inflater.inflate(layoutResourceId, parent, false);

        RecordHolder holder = new RecordHolder();
        holder.record = items.get(position);
//        holder.editButton = (Button) row.findViewById(R.id.edit);
//        holder.deleteButton = (Button) row.findViewById(R.id.delete);

        holder.name = (TextView) row.findViewById(R.id.record_name);
        holder.chest = (TextView) row.findViewById(R.id.record_chest);

//        System.out.println(holder.record.getName());
//
        setupItem(holder);
//        row.setTag(1);


        return row;
    }

    private void setupItem(RecordHolder holder) {
        holder.name.setText(holder.record.getName());
        holder.chest.setText(Float.toString(holder.record.getChest()));
    }

    // move this to a different file?
    public static class RecordHolder {
        Record record;
        TextView name;
        TextView chest;
        Button editButton;
        Button deleteButton;
    }

}
