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
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import static android.provider.AlarmClock.EXTRA_MESSAGE;


public class MainActivity extends Activity {

    private static final String FILENAME = "file.sav";

    private ListView DisplayRecordList;
    private TextView countView;
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
        countView = (TextView) findViewById(R.id.Count);

        addButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                int request = 0;

                record = new Record();
                Intent intent = new Intent(MainActivity.this, EditRecord.class);
                intent.putExtra("SIZEBOOK_RECORD_REQUEST", request);
                intent.putExtra("SIZEBOOK_RECORD_INDEX", index);
                intent.putExtra("SIZEBOOK_RECORD", record);
                startActivityForResult(intent, 0);

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        loadFromFile();
        countView.setText(Integer.toString(recordList.size()));
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
        } else if (resultCode == 1) {
            int index = data.getIntExtra("EDITRECORD_RECORD_INDEX", 0);
            recordList.remove(index);
            adapter.notifyDataSetChanged();
        }
        countView.setText(Integer.toString(recordList.size()));
        saveInFile();
    }

    public void editRecord(View v) {
        final int index = DisplayRecordList.getPositionForView( v/*(View) v.getParent()*/);
        int request = 1;
        record = recordList.get(index);
        Intent intent = new Intent(MainActivity.this, EditRecord.class);
        intent.putExtra("SIZEBOOK_RECORD_REQUEST", request);
        intent.putExtra("SIZEBOOK_RECORD_INDEX", index);
        intent.putExtra("SIZEBOOK_RECORD", record);
        startActivityForResult(intent, 1);
    }
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            // Taken from
            // http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // 2017-01-26 17:53:59
            recordList = gson.fromJson(in, new TypeToken<ArrayList<Record>>(){}.getType());

            fis.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            recordList = new ArrayList<Record>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    /**
     * Saves tweets in file in JSON format
     * @throws FileNotFoundException if folder not exists
     * */
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(recordList, out);

            out.flush();
            out.close();

            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
}





