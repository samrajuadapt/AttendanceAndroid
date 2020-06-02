package com.adapt.attendance.models;

public class Department {
    private String sName;

    private String nDepth;

    private String sDepartment;

    private String nDepartmentIdn;

    private String nParentIdn;

    public Department() {
    }

    public Department(String sName, String nDepth, String sDepartment, String nDepartmentIdn, String nParentIdn) {
        this.sName = sName;
        this.nDepth = nDepth;
        this.sDepartment = sDepartment;
        this.nDepartmentIdn = nDepartmentIdn;
        this.nParentIdn = nParentIdn;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getnDepth() {
        return nDepth;
    }

    public void setnDepth(String nDepth) {
        this.nDepth = nDepth;
    }

    public String getsDepartment() {
        return sDepartment;
    }

    public void setsDepartment(String sDepartment) {
        this.sDepartment = sDepartment;
    }

    public String getnDepartmentIdn() {
        return nDepartmentIdn;
    }

    public void setnDepartmentIdn(String nDepartmentIdn) {
        this.nDepartmentIdn = nDepartmentIdn;
    }

    public String getnParentIdn() {
        return nParentIdn;
    }

    public void setnParentIdn(String nParentIdn) {
        this.nParentIdn = nParentIdn;
    }
}
