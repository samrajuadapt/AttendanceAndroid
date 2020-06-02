package com.adapt.attendance.models;

public class User {

    private String sUserID;

    private String bPassword2;

    private String nDepartmentIdn;

    private String nAdminLevel;

    private String nStartDate;

    private String nUserIdn;

    private String sPassword;

    private String nAuthLimitCount;

    private String sEmail;

    private String sTelNumber;

    private String sUserName;

    private String nTimedAPB;

    private String nEncryption;

    private String nEndDate;

    public User() {
    }

    public User(String sUserID, String bPassword2, String nDepartmentIdn, String nAdminLevel, String nStartDate, String nUserIdn, String sPassword, String nAuthLimitCount, String sEmail, String sTelNumber, String sUserName, String nTimedAPB, String nEncryption, String nEndDate) {
        this.sUserID = sUserID;
        this.bPassword2 = bPassword2;
        this.nDepartmentIdn = nDepartmentIdn;
        this.nAdminLevel = nAdminLevel;
        this.nStartDate = nStartDate;
        this.nUserIdn = nUserIdn;
        this.sPassword = sPassword;
        this.nAuthLimitCount = nAuthLimitCount;
        this.sEmail = sEmail;
        this.sTelNumber = sTelNumber;
        this.sUserName = sUserName;
        this.nTimedAPB = nTimedAPB;
        this.nEncryption = nEncryption;
        this.nEndDate = nEndDate;
    }

    public String getsUserID() {
        return sUserID;
    }

    public void setsUserID(String sUserID) {
        this.sUserID = sUserID;
    }

    public String getbPassword2() {
        return bPassword2;
    }

    public void setbPassword2(String bPassword2) {
        this.bPassword2 = bPassword2;
    }

    public String getnDepartmentIdn() {
        return nDepartmentIdn;
    }

    public void setnDepartmentIdn(String nDepartmentIdn) {
        this.nDepartmentIdn = nDepartmentIdn;
    }

    public String getnAdminLevel() {
        return nAdminLevel;
    }

    public void setnAdminLevel(String nAdminLevel) {
        this.nAdminLevel = nAdminLevel;
    }

    public String getnStartDate() {
        return nStartDate;
    }

    public void setnStartDate(String nStartDate) {
        this.nStartDate = nStartDate;
    }

    public String getnUserIdn() {
        return nUserIdn;
    }

    public void setnUserIdn(String nUserIdn) {
        this.nUserIdn = nUserIdn;
    }

    public String getsPassword() {
        return sPassword;
    }

    public void setsPassword(String sPassword) {
        this.sPassword = sPassword;
    }

    public String getnAuthLimitCount() {
        return nAuthLimitCount;
    }

    public void setnAuthLimitCount(String nAuthLimitCount) {
        this.nAuthLimitCount = nAuthLimitCount;
    }

    public String getsEmail() {
        return sEmail;
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public String getsTelNumber() {
        return sTelNumber;
    }

    public void setsTelNumber(String sTelNumber) {
        this.sTelNumber = sTelNumber;
    }

    public String getsUserName() {
        return sUserName;
    }

    public void setsUserName(String sUserName) {
        this.sUserName = sUserName;
    }

    public String getnTimedAPB() {
        return nTimedAPB;
    }

    public void setnTimedAPB(String nTimedAPB) {
        this.nTimedAPB = nTimedAPB;
    }

    public String getnEncryption() {
        return nEncryption;
    }

    public void setnEncryption(String nEncryption) {
        this.nEncryption = nEncryption;
    }

    public String getnEndDate() {
        return nEndDate;
    }

    public void setnEndDate(String nEndDate) {
        this.nEndDate = nEndDate;
    }
}
