package com.adapt.attendance.retrofit;

import com.adapt.attendance.models.Department;
import com.adapt.attendance.models.SendBody;
import com.adapt.attendance.models.SendResponse;
import com.adapt.attendance.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @GET("departments")
    Call<List<Department>> getAllDepartment();

    @GET("users")
    Call<List<User>> getAllUser();

    @POST("create")
    Call<List<SendResponse>> sendUser(@Body SendBody body);

}
