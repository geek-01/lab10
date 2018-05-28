package com.example.sagar.lab10;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {
    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "crud.db", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    sqLiteDatabase.execSQL("create table crud(car_no varchar(20) primary key,car_model varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    onCreate(sqLiteDatabase);
    }

    public void insertData(String car_no,String car_model){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues v=new ContentValues();
        v.put("car_no",car_no);
        v.put("car_model",car_model);
        db.insertOrThrow("crud",null,v);
    }
    public void deleteData(String car_no){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete("crud","car_no='"+car_no+"'",null);
        db.close();
    }
    public void updateData(String car_no,String car_model){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues v=new ContentValues();
        v.put("car_no",car_no);
        v.put("car_model",car_model);
        db.update("crud",v,"car_no='"+car_no+"'",null);
        db.close();
    }
    public String readData(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery("select * from crud",null);
        String car_details="";
        if(c.moveToFirst()){
            do{
             car_details=car_details+c.getString(0)+":"+c.getString(1)+"\n";
            }while (c.moveToNext());
        }
        return car_details;
    }
}
