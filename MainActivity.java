package com.example.sagar.lab10;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
TextView t1;
DBHandler db;
EditText car_no,car_model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        car_no=findViewById(R.id.car_no);
        car_model=findViewById(R.id.car_model);
        db=new DBHandler(this,"crud.db",null,1);
        t1=findViewById(R.id.data);
        t1.setText(db.readData());
    }

    public void insert(View view) {
        try{
        db.insertData(car_no.getText().toString(),car_model.getText().toString());
            Toast.makeText(this,"Inserted Successfully",Toast.LENGTH_SHORT).show();
            t1.setText(db.readData());}
        catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    public void delete(View view) {
        try{
            db.deleteData(car_no.getText().toString());
            Toast.makeText(this,"Deleted Successfully",Toast.LENGTH_SHORT).show();
            t1.setText(db.readData());}
        catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }

    public void update(View view) {
        try{
            db.updateData(car_no.getText().toString(),car_model.getText().toString());
            Toast.makeText(this,"Updated Successfully",Toast.LENGTH_SHORT).show();
            t1.setText(db.readData());}
        catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    public void email(View view) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","abc@gmail.com", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Automobile Details");
        emailIntent.putExtra(Intent.EXTRA_TEXT, db.readData());
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }
}
