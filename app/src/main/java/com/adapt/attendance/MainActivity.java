package com.adapt.attendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.adapt.attendance.database.Database;
import com.adapt.attendance.models.UserModel;

public class MainActivity extends AppCompatActivity {

    private Database db;
    private UserModel user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = Database.getInstance(this);
        user = db.getUser();
        startTimer();
    }

    private void startTimer(){
        new Handler().postDelayed(()->{
           if(user==null||user.getId()==0){
               gotoHome();
           }else {
               gotoChekOut();
           }
        },2000);
    }

    private void gotoHome(){
        if(!isFinishing()){
            startActivity(new Intent(this,CheckInActivity.class));
            finish();
        }
    }
    private void gotoChekOut(){
        if(!isFinishing()){
            startActivity(new Intent(this,CheckOutActivity.class));
            finish();
        }
    }
}
