//http://stackoverflow.com/questions/2115758/how-do-i-display-an-alert-dialog-on-android

package com.example.assignment1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class EditRecord extends Activity {

    Record record;
    EditText editName, editDate, editNeck, editBust,
            editChest, editWaist, editHip, editInseam;

    ArrayList<EditText> editTextArray;
    boolean editing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_record);

        Button doneButton = (Button) findViewById(R.id.return_button);
        final Button deleteButton = (Button) findViewById(R.id.delete_button);
        final int request = getIntent().getIntExtra("SIZEBOOK_RECORD_REQUEST", 0);
        final int index = getIntent().getIntExtra("SIZEBOOK_RECORD_INDEX", 0);
        record = getIntent().getParcelableExtra("SIZEBOOK_RECORD");



        editName = (EditText) findViewById(R.id.edit_name);
        editDate = (EditText) findViewById(R.id.edit_date);
        editNeck = (EditText) findViewById(R.id.edit_neck);
        editBust = (EditText) findViewById(R.id.edit_bust);
        editChest = (EditText) findViewById(R.id.edit_chest);
        editWaist = (EditText) findViewById(R.id.edit_waist);
        editHip = (EditText) findViewById(R.id.edit_hip);
        editInseam = (EditText) findViewById(R.id.edit_inseam);


        editName.setText(record.getName(), TextView.BufferType.EDITABLE);
        editDate.setText(record.getDate(), TextView.BufferType.EDITABLE);
        editNeck.setText(Float.toString(record.getNeck()), TextView.BufferType.EDITABLE);
        editBust.setText(Float.toString(record.getBust()), TextView.BufferType.EDITABLE);
        editChest.setText(Float.toString(record.getChest()), TextView.BufferType.EDITABLE);
        editWaist.setText(Float.toString(record.getWaist()), TextView.BufferType.EDITABLE);
        editHip.setText(Float.toString(record.getHip()), TextView.BufferType.EDITABLE);
        editInseam.setText(Float.toString(record.getInseam()), TextView.BufferType.EDITABLE);

        // we wanted to edit a record
        if(request != 1) {
            deleteButton.setVisibility(View.GONE);
        }

        doneButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String name = editName.getText().toString();
                String date = editDate.getText().toString();
                String neck = editNeck.getText().toString();
                String bust = editBust.getText().toString();
                String chest = editChest.getText().toString();
                String waist = editWaist.getText().toString();
                String hip = editHip.getText().toString();
                String inseam = editInseam.getText().toString();

                // do error checking

                record.setName(name);
                record.setDate(date);
                record.setNeck(Float.parseFloat(neck));
                record.setBust(Float.parseFloat(bust));
                record.setChest(Float.parseFloat(chest));
                record.setWaist(Float.parseFloat(waist));
                record.setHip(Float.parseFloat(hip));
                record.setInseam(Float.parseFloat(inseam));

                Intent output = new Intent();
                output.putExtra("EDITRECORD_RECORD_INDEX", index);
                output.putExtra("EDITRECORD_RECORD", record);
                setResult(RESULT_OK, output);
                finish();

            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(EditRecord.this).create();
                alertDialog.setTitle("Delete");
                alertDialog.setMessage("Are you sure you want to delete?");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent output = new Intent();
                            output.putExtra("EDITRECORD_RECORD_INDEX", index);
                            setResult(1, output);
                            finish();
                            dialog.dismiss();

                        }
                    });
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                        }
                    });

                alertDialog.show();

            }
        });
    }

    @Override
    public void onBackPressed() {
        setResult(0);
        finish();
    }


}
