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


/**
 * RecordListAdapter is an adapter to list the record name and its attributes
 * as text views
 * Reference taken from
 * //https://looksok.wordpress.com/2012/11/03/android-custom-listview-tutorial/
 * on 2017-01-24
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
        View row;

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        row = inflater.inflate(layoutResourceId, parent, false);

        RecordHolder holder = new RecordHolder();
        holder.record = items.get(position);

        holder.name = (TextView) row.findViewById(R.id.record_name);
        holder.date = (TextView) row.findViewById(R.id.record_date);
        holder.neck = (TextView) row.findViewById(R.id.record_neck);
        holder.bust = (TextView) row.findViewById(R.id.record_bust);
        holder.chest = (TextView) row.findViewById(R.id.record_chest);

        setupItem(holder);

        return row;
    }

    private void setupItem(RecordHolder holder) {
        holder.name.setText(holder.record.getName());
        holder.date.setText(holder.record.getDate());
        holder.neck.setText(Float.toString(holder.record.getNeck()));
        holder.bust.setText(Float.toString(holder.record.getBust()));
        holder.chest.setText(Float.toString(holder.record.getChest()));

    }

    public static class RecordHolder {
        Record record;
        TextView name;
        TextView date;
        TextView neck;
        TextView bust;
        TextView chest;

    }

}
