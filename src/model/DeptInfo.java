package model;

import java.sql.Date;

/**
 * Created by Jch on 2016/8/25.
 */
public class DeptInfo {
    private String deptID;
    private String deptName;
    private String deptDate;
    private String deptWCnt;
    private String mngID;

    public DeptInfo(String deptID, String deptName, String deptDate, String deptWCnt, String mngID) {

        this.deptID = deptID;
        this.deptName = deptName;
        this.deptDate = deptDate;
        this.deptWCnt = deptWCnt;
        this.mngID = mngID;
    }

    public DeptInfo(String deptID, String deptName, Date deptDate, String deptWCnt, String mngID) {

        this.deptID = deptID;
        this.deptName = deptName;
        this.deptDate = deptDate.toString();
        this.deptWCnt = deptWCnt;
        this.mngID = mngID;
    }

    public String getDeptID() {
        return deptID;
    }

    public void setDeptID(String deptID) {
        this.deptID = deptID;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptDate() {
        return deptDate;
    }

    public void setDeptDate(String deptDate) {
        this.deptDate = deptDate;
    }

    public String getDeptWCnt() {
        return deptWCnt;
    }

    public void setDeptWCnt(String deptWCnt) {
        this.deptWCnt = deptWCnt;
    }

    public String getMngID() {
        return mngID;
    }

    public void setMngID(String mngID) {
        this.mngID = mngID;
    }
}
