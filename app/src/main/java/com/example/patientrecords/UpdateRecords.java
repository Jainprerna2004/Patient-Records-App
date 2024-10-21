package com.example.patientrecords;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateRecords extends AppCompatActivity {

    EditText id, patientName, status, DrName, number;
    Button update;
    HelperClass helperClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_records);


        id = findViewById(R.id.id);
        patientName = findViewById(R.id.patientName);
        status = findViewById(R.id.status);
        DrName = findViewById(R.id.DrName);
        update = findViewById(R.id.update);
        number = findViewById(R.id.number);

        helperClass = new HelperClass(this);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputs()) {
                    String idText = id.getText().toString();
                    String name = patientName.getText().toString();
                    String statusText = status.getText().toString();
                    String docName = DrName.getText().toString();
                    String numberText = number.getText().toString();

                    helperClass.updatePatient(idText, name, statusText, docName, numberText);
                    finish();
                }
            }
        });
    }

    private boolean validateInputs() {
        if (id.getText().toString().isEmpty()) {
            id.setError("ID is required");
            return false;
        }
        if (patientName.getText().toString().isEmpty()) {
            patientName.setError("Patient name is required");
            return false;
        }
        if (status.getText().toString().isEmpty()) {
            status.setError("Status is required");
            return false;
        }
        if (DrName.getText().toString().isEmpty()) {
            DrName.setError("Doctor's name is required");
            return false;
        }
        if (number.getText().toString().isEmpty()) {
            number.setError("Number is required");
            return false;
        }
        return true;
    }
}
