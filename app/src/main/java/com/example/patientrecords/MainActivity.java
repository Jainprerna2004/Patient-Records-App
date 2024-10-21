package com.example.patientrecords;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button enterRecords, viewStatus, updateRecord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterRecords = findViewById(R.id.enterPatientRecordsButton);
        viewStatus = findViewById(R.id.viewStatusButton);
        updateRecord = findViewById(R.id.updateRecordButton);

        enterRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterRecords.class);
                startActivity(intent);
            }
        });

        viewStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewStatus.class);
                startActivity(intent);
            }

        });

        updateRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UpdateRecords.class);
                startActivity(intent);
            }
        });
    }
}