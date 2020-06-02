package com.adapt.attendance.database;

import android.content.Context;
import android.content.SharedPreferences;

import com.adapt.attendance.R;
import com.adapt.attendance.constants.SC;
import com.adapt.attendance.models.UserModel;

public class Database {
    private static Database db;
    private Context ctx;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;


    private Database (Context ctx){
        this.ctx =ctx;
        preferences = ctx.getSharedPreferences(ctx.getResources().getString(R.string.sp_key),Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public static Database getInstance(Context ctx){
        if(db==null){
            db=new Database(ctx);
        }
        return db;
    }

    public void addUser(UserModel user){
        editor.putInt(SC.ID,user.getId());
        editor.putString(SC.CODE,user.getCode());
        editor.putString(SC.NAME,user.getName());
        editor.putString(SC.DEPT,user.getDepartment());
        editor.putString(SC.PHONE,user.getPhone());
        editor.putString(SC.EVENT,user.getEvent());
        editor.putString(SC.TIME,user.getTime());
        editor.putString(SC.DATE,user.getDate());

        editor.commit();
        editor.apply();
    }

    public UserModel getUser(){
        UserModel b = new UserModel();
        b.setId(preferences.getInt(SC.ID,0));
        b.setName(preferences.getString(SC.NAME,SC.NAME));
        b.setCode(preferences.getString(SC.CODE,SC.CODE));
        b.setDepartment(preferences.getString(SC.DEPT,SC.DEPT));
        b.setPhone(preferences.getString(SC.PHONE,SC.PHONE));
        b.setEvent(preferences.getString(SC.EVENT,SC.EVENT));
        b.setTime(preferences.getString(SC.TIME,SC.TIME));
        b.setDate(preferences.getString(SC.DATE,SC.DATE));

        return b;
    }

    public void deleteUser(){
        editor.clear().commit();
        editor.apply();
    }


}
