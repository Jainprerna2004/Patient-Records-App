package com.example.patientrecords;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class HelperClass extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "PatientRecords.db";
    public static final int DATABASE_V = 1;
    public static final String TABLE_NAME = "patient";
    public static final String COL_ID = "ID";
    public static final String COL_NAME = "Name";
    public static final String COL_ADMISSION_DATE = "AdmissionDate";
    public static final String COL_DOCTOR_NAME = "DoctorName";
    public static final String COL_STATUS = "Status";
    public static final String COL_AILMENT = "Ailment";
    public static final String COL_NUMBER = "Number";

    public HelperClass(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_V);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " TEXT, " +
                COL_ADMISSION_DATE + " TEXT, " +
                COL_AILMENT + " TEXT, " +
                COL_DOCTOR_NAME + " TEXT, " +
                COL_STATUS + " TEXT, " +
                COL_NUMBER + " TEXT)";
        db.execSQL(createTable);    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addPatient(String name, String admissionDate, String ailment, String doctorName, String number){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_NAME, name);
        cv.put(COL_ADMISSION_DATE, admissionDate);
        cv.put(COL_AILMENT, ailment);
        cv.put(COL_DOCTOR_NAME, doctorName);
        cv.put(COL_NUMBER, number);
        cv.put(COL_STATUS, "Admitted");

        db.insert(TABLE_NAME, null, cv);
        db.close();
    }

    public void updatePatient(String id, String name,String status, String doctorName, String number){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_STATUS, status);
        cv.put(COL_NAME, name);
        cv.put(COL_DOCTOR_NAME, doctorName);
        cv.put(COL_NUMBER, number);
        db.update(TABLE_NAME, cv, COL_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public Cursor getAllPatients(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public Cursor searchPatients(String searchQuery) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " +
                COL_NAME + " LIKE ? OR " +
                COL_AILMENT + " LIKE ? OR " +
                COL_DOCTOR_NAME + " LIKE ? OR " +
                COL_NUMBER + " LIKE ?";

        String[] selectionArgs = new String[]{"%" + searchQuery + "%", "%" + searchQuery + "%", "%" + searchQuery + "%", "%" + searchQuery + "%"}; // Update this line

        return db.rawQuery(query, selectionArgs);
    }

}
