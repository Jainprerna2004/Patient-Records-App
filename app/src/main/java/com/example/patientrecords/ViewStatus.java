package com.example.patientrecords;

import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.SearchView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class ViewStatus extends AppCompatActivity {

    HelperClass helperClass;
    TableLayout tableLayout;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_status);

        helperClass = new HelperClass(this);
        tableLayout = findViewById(R.id.table);
        searchView = findViewById(R.id.searchView);

        // Initially load all patients
        loadPatientRecords(null);

        // Set up search view listener
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                loadPatientRecords(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                loadPatientRecords(newText);
                return false;
            }
        });
    }

    private void loadPatientRecords(String searchQuery) {
        tableLayout.removeAllViews(); // Clear existing rows
        Cursor cursor;

        if (searchQuery == null || searchQuery.isEmpty()) {
            cursor = helperClass.getAllPatients(); // Get all records if no search query
        } else {
            cursor = helperClass.searchPatients(searchQuery); // Get filtered records based on search
        }

        // Create table header
        TableRow headerRow = new TableRow(this);
        String[] headers = {"ID", "Name", "Admission Date", "Ailment", "Doctor Name", "Status", "Number"};
        for (String header : headers) {
            TextView headerText = new TextView(this);
            headerText.setText(header);
            headerText.setPadding(8, 8, 8, 8); // Add padding
            headerText.setBackgroundColor(ContextCompat.getColor(this, R.color.lightgrey));
            headerText.setGravity(Gravity.CENTER);
            headerRow.addView(headerText);
        }
        tableLayout.addView(headerRow);

        int rowIndex = 0;
        while (cursor.moveToNext()) {
            TableRow row = new TableRow(this);

            // Alternate row colors
            if (rowIndex % 2 == 0) {
                row.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
            } else {
                row.setBackgroundColor(ContextCompat.getColor(this, R.color.lightgoldenrodyellow));
            }

            for (int i = 0; i < cursor.getColumnCount(); i++) {
                TextView textView = new TextView(this);
                textView.setText(cursor.getString(i));
                textView.setPadding(8, 8, 8, 8); // Add padding
                textView.setGravity(Gravity.CENTER);
                row.addView(textView);
            }

            tableLayout.addView(row);
            rowIndex++;
        }
        cursor.close();
    }
}
