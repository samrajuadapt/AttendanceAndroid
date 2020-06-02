package com.adapt.attendance.models;

public class UserModel {
    private int id;
    private String name;
    private String code;
    private String department;
    private String phone;
    private String event;
    private String date;
    private String time;
    private String ipAddress;

    public UserModel() {
    }

    public UserModel(int id, String name, String code, String department, String phone, String event, String date, String time,String ipAddress) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.department = department;
        this.phone = phone;
        this.event = event;
        this.date = date;
        this.time = time;
        this.ipAddress = ipAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}