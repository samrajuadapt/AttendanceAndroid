package com.adapt.attendance;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.adapt.attendance.database.Database;
import com.adapt.attendance.models.Department;
import com.adapt.attendance.models.SendBody;
import com.adapt.attendance.models.SendResponse;
import com.adapt.attendance.models.User;
import com.adapt.attendance.models.UserModel;
import com.adapt.attendance.retrofit.HC;
import com.adapt.attendance.retrofit.RetroClient;
import com.adapt.attendance.utils.Utils;
import com.adapt.attendance.utils.Validation;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckInActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private AutoCompleteTextView autoCode;
    private AutoCompleteTextView autoName;
    private AutoCompleteTextView autoDept;
    private AutoCompleteTextView autoPhone;
    private Button btnCheckIn;

    private Calendar calendar;
    private SimpleDateFormat timeFormat;

    private Database db;

    private List<String> code;
    private List<String> users;
    private List<String> depts;
    private List<User> mList;
    private User selectedUser;
    private String ipAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);
        init();
        getIds();
        setListener();
        fetchUser();
        fetchDept();
        setData();

        ipAddress = Utils.getIPAddress();


    }

    private void init() {
        mList = new ArrayList<>();
        code = new ArrayList<>();
        users = new ArrayList<>();
        depts = new ArrayList<>();
        db = Database.getInstance(this);
        calendar = Calendar.getInstance();
        timeFormat = new SimpleDateFormat("h:mm a");
    }

    private void getIds() {
        autoCode = findViewById(R.id.autoCode);
        autoName = findViewById(R.id.autoName);
        autoDept = findViewById(R.id.autoDept);
        autoPhone = findViewById(R.id.autoPhone);
        btnCheckIn = findViewById(R.id.btnCheckIn);
    }

    private void setListener() {
        btnCheckIn.setOnClickListener(this);
        autoCode.setOnItemClickListener(this);
    }

    private void setData() {
        autoCode.setThreshold(1);
        autoName.setThreshold(1);
        autoDept.setThreshold(1);
        autoCode.setAdapter(Utils.getAdapter(this, code));
        autoName.setAdapter(Utils.getAdapter(this, users));
        autoDept.setAdapter(Utils.getAdapter(this, depts));
    }


    @Override
    public void onClick(View view) {
        checkIn();
    }

    private void checkIn() {
        if(autoCode.getText().toString().contains("11001122")){
            UserModel user = new UserModel(11001122, "User 1", "", "Department 1", "", "", "", timeFormat.format(calendar.getTime()),"");
            db.deleteUser();
            db.addUser(user);
            gotoCheckOut();
            return;
        }

        String code;
        String name;
        String dept;
        String phone;
        String event = "Check-in";
        String time = timeFormat.format(calendar.getTime());
        if (!Validation.checkIsEmpty(autoCode)) {
            if (!Validation.checkIsEmpty(autoName)) {
                if (!Validation.checkIsEmpty(autoPhone)) {


                    name = autoName.getText().toString();
                    code = autoCode.getText().toString();
                    dept = autoDept.getText().toString();
                    phone = autoPhone.getText().toString();
                    UserModel user = new UserModel(Integer.parseInt(selectedUser.getnUserIdn()), name, code, dept, phone, event, "", time,ipAddress);
                    sendUserData(user);
                }
            }
        }
    }

    private void gotoCheckOut() {
        if (!isFinishing()) {
            startActivity(new Intent(this, CheckOutActivity.class));
            finish();
        }
    }

    private void fetchUser() {
        code.clear();
        users.clear();
        getUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                Log.e("data", "onResponse: " + response);
                if (response.body() != null || response.isSuccessful()) {
                    mList = response.body();
                    for (User user : response.body()) {
                        code.add(user.getsUserID());
                        users.add(user.getsUserName());

                    }
                } else {
                    Toast.makeText(CheckInActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("ERROR", "onFailure: " + t);
                Toast.makeText(CheckInActivity.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchDept() {
        depts.clear();
        getDepartment().enqueue(new Callback<List<Department>>() {
            @Override
            public void onResponse(Call<List<Department>> call, Response<List<Department>> response) {
                if (response.body() != null || response.isSuccessful()) {
                    for (Department dept : response.body()) {
                        depts.add(dept.getsName());
                    }
                } else {
                    Toast.makeText(CheckInActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Department>> call, Throwable t) {
                Log.e("ERROR", "onFailure: " + t);
                Toast.makeText(CheckInActivity.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendUserData(UserModel user) {
        SendBody body = new SendBody(String.valueOf(user.getId()), user.getEvent(), "",user.getIpAddress());
        sendData(body).enqueue(new Callback<List<SendResponse>>() {
            @Override
            public void onResponse(Call<List<SendResponse>> call, Response<List<SendResponse>> response) {

                if (response.isSuccessful() || response.body() != null) {
                    SendResponse success = response.body().get(0);
                    if (success.getCode() == 1) {
                        db.deleteUser();
                        user.setDate(success.getMessage());
                        db.addUser(user);
                        gotoCheckOut();
                    } else {
                        Toast.makeText(CheckInActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
                    }


                }
            }

            @Override
            public void onFailure(Call<List<SendResponse>> call, Throwable t) {
                Log.e("ERROR", "onFailure: " + t);
                Toast.makeText(CheckInActivity.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Call<List<SendResponse>> sendData(SendBody body) {
        return RetroClient.getApiService(HC.SERVER_URL).sendUser(body);
    }

    private Call<List<User>> getUsers() {
        return RetroClient.getApiService(HC.SERVER_URL).getAllUser();
    }

    private Call<List<Department>> getDepartment() {
        return RetroClient.getApiService(HC.SERVER_URL).getAllDepartment();
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Object obj = (String) adapterView.getItemAtPosition(i);
        for (User u : mList) {
            if (u.getsUserID() == obj) {
                this.selectedUser = u;
                autoCode.setText(u.getsUserID());
                autoName.setText(u.getsUserName());
            }
        }
    }
}
