package com.example.pustikom.adapterplay.com.example.pustikom.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.pustikom.adapterplay.com.example.pustikom.user.Student;
import com.example.pustikom.adapterplay.com.example.pustikom.user.StudentList;

/**
 * Created by Lenovo on 23/11/2016.
 */

public class StudentDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "college.db";
    private static final int DATABASE_VERSION = 1;

    public StudentDbHelper(Context context) {super(context, DATABASE_NAME, null, DATABASE_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_STUDENT_TABLE =  "CREATE TABLE " + StudentContract.TABLE_NAME + " ("
                + StudentContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + StudentContract.COLUMN_NIM + " TEXT NOT NULL, "
                + StudentContract.COLUMN_NAME + " TEXT, "
                + StudentContract.COLUMN_GENDER + " INTEGER, "
                + StudentContract.COLUMN_MAIL + " TEXT,"
                + StudentContract.COLUMN_PHONE + " TEXT ;";

    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertStudent(SQLiteDatabase db, Student student) {
        ContentValues values = new ContentValues();
        values.put(StudentContract.COLUMN_NIM,student.getNoreg());
        values.put(StudentContract.COLUMN_NAME,student.getName());
        values.put(StudentContract.COLUMN_GENDER,student.getGender());
        values.put(StudentContract.COLUMN_MAIL, student.getMail());
        values.put(StudentContract.COLUMN_PHONE, student.getPhone());
        long rowId = db.insert(StudentContract.TABLE_NAME,null,values);
        student.setId((int)rowId);

    }

    public int updateStudent(SQLiteDatabase db, Student student) {
        ContentValues values = new ContentValues();
        values.put(StudentContract.COLUMN_NIM,student.getNoreg());
        values.put(StudentContract.COLUMN_NAME,student.getName());
        values.put(StudentContract.COLUMN_GENDER,student.getGender());
        values.put(StudentContract.COLUMN_MAIL, student.getMail());
        values.put(StudentContract.COLUMN_PHONE, student.getPhone());

        String selection = StudentContract._ID + " = ?,";
        String[] selectionArgs = { student.getId()+"" };
        int thisrow = db.update(StudentContract.TABLE_NAME,values,selection,selectionArgs);
        return thisrow;
    }

    public StudentList fetchAllData(SQLiteDatabase rdb){
        //define columns to project
        String[] projection = {
                StudentContract
                        ._ID, StudentContract.COLUMN_NIM, StudentContract.COLUMN_NAME, StudentContract.COLUMN_GENDER,
                StudentContract.COLUMN_MAIL, StudentContract.COLUMN_PHONE
        };
        Cursor cursor = rdb.query(
                StudentContract.TABLE_NAME, //table to query
                projection, //columns to project
                null, //column for the where clause
                null, //value for the where clause
                null, //group statement
                null, //don't filter by row groups
                null); //order by statemnt

        //figure out column index given id
        int idIndex = cursor.getColumnIndex(StudentContract._ID);
        int nimIndex = cursor.getColumnIndex(StudentContract.COLUMN_NIM);
        int nameIndex = cursor.getColumnIndex(StudentContract.COLUMN_NAME);
        int genderIndex = cursor.getColumnIndex(StudentContract.COLUMN_GENDER);
        int phoneIndex = cursor.getColumnIndex(StudentContract.COLUMN_PHONE);
        int mailIndex = cursor.getColumnIndex(StudentContract.COLUMN_MAIL);

        StudentList studentList = new StudentList();
        while(cursor.moveToNext()){
            long id = cursor.getLong(idIndex);
            String nim = cursor.getString(nimIndex);
            String name = cursor.getString(nameIndex);
            int gender = cursor.getInt(genderIndex);
            String mail = cursor.getString(mailIndex);
            String phone = cursor.getString(phoneIndex);
            //create student instance based on these data
            Student student = new Student((int)id, nim,name,gender,mail,phone);

            studentList.add(student);
        }

        cursor.close();
        return studentList;
    }

    public void truncate(SQLiteDatabase db){
        String sql = "DELETE FROM " + StudentContract.TABLE_NAME + ";VACUUM;";
        db.execSQL(sql);
    }

    public int delete(SQLiteDatabase db, int id){
        String condition = StudentContract._ID + "=?";
        String[] conditionArg = {id + ""};
        int affectedRows = db.delete(StudentContract.TABLE_NAME,condition, conditionArg);
        return affectedRows;
    }
}


