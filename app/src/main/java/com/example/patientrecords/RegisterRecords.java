package com.example.patientrecords;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterRecords extends AppCompatActivity {

    EditText patientName, admissionDate, ailment, DrName, number;
    Button register;
    HelperClass helperClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_records);

        patientName = findViewById(R.id.patientName);
        admissionDate = findViewById(R.id.admissionDate);
        ailment = findViewById(R.id.ailment);
        DrName = findViewById(R.id.DrName);
        register = findViewById(R.id.register);
        number = findViewById(R.id.number);

        helperClass = new HelperClass(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = patientName.getText().toString();
                String admission = admissionDate.getText().toString();
                String docName = DrName.getText().toString();
                String ail = ailment.getText().toString();
                String num = number.getText().toString();
                helperClass.addPatient(name, admission, ail, docName, num);
                Toast.makeText(RegisterRecords.this, "Patient Register", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}