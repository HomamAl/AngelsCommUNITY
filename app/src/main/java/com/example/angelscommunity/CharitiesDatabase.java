package com.example.angelscommunity;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CharitiesDatabase extends SQLiteOpenHelper {

    private static String DB_NAME = "Charities.sqlite";
    private static String DB_PATH = "";
    private SQLiteDatabase myDatabase;
    private final Context myContext;
    public CharitiesDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, 1);

        if(Build.VERSION.SDK_INT>=15)
        {
            DB_PATH = context.getApplicationInfo().dataDir + "/databases";
        }else{
            DB_PATH = Environment.getDataDirectory() + "/data/" + context.getPackageName() + "/databases";
        }
        this.myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void checkAndCopyDatabase(){
        boolean dbExists = checkDatabase();
        if(dbExists){
            Log.d("TAG", "database already exist");
        }else{
            this.getReadableDatabase();
        }

        try {
            copyDatabase();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("TAG", "Error copy database");

        }
    }

    public boolean checkDatabase(){
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        }catch(SQLException e){

        }
        if (checkDB != null){
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    public void copyDatabase() throws IOException {
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0){
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }
    public void openDatabase(){
        String myPath = DB_PATH + DB_NAME;
        myDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }
    public synchronized void close(){
        if(myDatabase != null){
            myDatabase.close();
        }
        super.close();
    }

    public Cursor QueryData(String query){
        return myDatabase.rawQuery(query,null);
    }
}
