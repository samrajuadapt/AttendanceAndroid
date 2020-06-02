package com.adapt.attendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.adapt.attendance.database.Database;
import com.adapt.attendance.models.SendBody;
import com.adapt.attendance.models.SendResponse;
import com.adapt.attendance.models.UserModel;
import com.adapt.attendance.retrofit.HC;
import com.adapt.attendance.retrofit.RetroClient;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckOutActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtName;
    private TextView txtDate;
    private TextView txtTime;
    private TextView txtDept;
    private Button btnCheckOut;

    private Database db;

    private SimpleDateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        init();
        getIds();
        setListener();
        setData();
    }

    private void init(){
        db = Database.getInstance(this);
        dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
    }

    private void getIds(){
        txtName = findViewById(R.id.txtName);
        txtDate = findViewById(R.id.txtDate);
        txtTime = findViewById(R.id.txtCheckInTime);
        txtDept = findViewById(R.id.txtDept);
        btnCheckOut = findViewById(R.id.btnCheckOut);
    }

    private void setListener(){
        btnCheckOut.setOnClickListener(this);
    }

    private void setData(){
        String date = dateFormat.format(new Date());
        UserModel user = db.getUser();
        txtName.setText(user.getName());
        txtDate.setText(date);
        txtTime.setText(user.getTime());
        txtDept.setText(user.getDepartment());

    }
    private void gotoHome(){
        db.deleteUser();
        if(!isFinishing()){
            startActivity(new Intent(this,CheckInActivity.class));
            finish();
        }
    }

    private void sendUserData(){
        UserModel user = db.getUser();
        if(user.getId() == 11001122){
            gotoHome();
        }
        SendBody body = new SendBody(String.valueOf(user.getId()),"Check-out",user.getDate(),"");

        sendData(body).enqueue(new Callback<List<SendResponse>>() {
            @Override
            public void onResponse(Call<List<SendResponse>> call, Response<List<SendResponse>> response) {

                if (response.isSuccessful() || response.body() != null){
                    SendResponse success = response.body().get(0);
                    if(success.getCode()==1){
                        gotoHome();
                    }else {
                        Toast.makeText(CheckOutActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
                    }

                }

            }

            @Override
            public void onFailure(Call<List<SendResponse>> call, Throwable t) {
                Log.e("TAG", "onFailure: "+t );
                Toast.makeText(CheckOutActivity.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private Call<List<SendResponse>> sendData(SendBody body){
        return RetroClient.getApiService(HC.SERVER_URL).sendUser(body);
    }

    @Override
    public void onClick(View view) {
        sendUserData();
    }
}
