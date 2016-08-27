package model;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by Jch on 2016/8/27.
 */
public class Attendance {
    private String userID;
    private String date;
    private String time;
    private String status;

    public Attendance(String userID, String date, String time, String status) {
        this.userID = userID;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public Attendance(String userID, Date date, Time time, String status) {
        this.userID = userID;
        this.date = date.toString();
        this.time = time.toString();
        this.status = status;
    }

    public String getUserID() {

        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
