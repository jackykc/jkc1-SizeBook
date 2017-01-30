package com.example.assignment1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import static android.provider.AlarmClock.EXTRA_MESSAGE;


public class MainActivity extends Activity {

    private ListView DisplayRecordList;
    RecordListAdapter adapter;
    Record record;
    int index = 0;
    private ArrayList<Record> recordList = new ArrayList<Record>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addButton = (Button) findViewById(R.id.add);
        DisplayRecordList = (ListView) findViewById(R.id.DisplayRecordList);

        addButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);

                record = new Record();

                Intent intent = new Intent(MainActivity.this, EditRecord.class);
                intent.putExtra("SIZEBOOK_RECORD_INDEX", index);
                intent.putExtra("SIZEBOOK_RECORD", record);
                startActivityForResult(intent, 0);

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter = new RecordListAdapter(this, R.layout.list_item, recordList);
        DisplayRecordList.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == RESULT_OK) {
            if (requestCode == 0) {
                record = data.getParcelableExtra("EDITRECORD_RECORD");
                recordList.add(record);
                adapter.notifyDataSetChanged();
            } else if (requestCode == 1) {
                int index = data.getIntExtra("EDITRECORD_RECORD_INDEX", 0);
                record = data.getParcelableExtra("EDITRECORD_RECORD");
                recordList.set(index, record);
                adapter.notifyDataSetChanged();
            }
        }
    }

    public void editRecord(View v) {
        final int index = DisplayRecordList.getPositionForView((View) v.getParent());
        record = recordList.get(index);
        Intent intent = new Intent(MainActivity.this, EditRecord.class);
        intent.putExtra("SIZEBOOK_RECORD_INDEX", index);
        intent.putExtra("SIZEBOOK_RECORD", record);
        startActivityForResult(intent, 1);
    }

    public void deleteRecord(View v) {

        final int position = DisplayRecordList.getPositionForView((View) v.getParent());

        recordList.remove(position);

        // do confirmation here

        adapter.notifyDataSetChanged();
    }


}
