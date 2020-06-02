package com.adapt.attendance.models;

public class SendBody {

    private String userId;
    private String EventIn;
    private String date;
    private String ipAddress;

    public SendBody() {
    }

    public SendBody(String userId, String eventIn, String date, String ipAddress) {
        this.userId = userId;
        EventIn = eventIn;
        this.date = date;
        this.ipAddress = ipAddress;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEventIn() {
        return EventIn;
    }

    public void setEventIn(String eventIn) {
        this.EventIn = eventIn;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
