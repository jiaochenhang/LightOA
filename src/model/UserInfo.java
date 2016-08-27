package model;

import java.sql.Date;

/**
 * Created by Jch on 2016/8/25.
 */
public class UserInfo {
    private String userID;
    private String userName;
    private String userPwd;
    private String userAge;
    private String userSex;
    private String userEmail;
    private String userPhone;
    private String userDate;
    private String userPosition;
    private String deptID;

    public UserInfo(String userID, String userName, String userPwd, String userAge, String userSex, String userEmail, String userPhone, String userDate, String userPosition, String deptID) {
        this.userID = userID;
        this.userName = userName;
        this.userPwd = userPwd;
        this.userAge = userAge;
        this.userSex = userSex;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userDate = userDate;
        this.userPosition = userPosition;
        this.deptID = deptID;
    }

    public UserInfo(String userID, String userName, String userPwd, Long userAge, String userSex, String userEmail, Long userPhone, Date userDate, String userPosition, String deptID) {
        this.userID = userID;
        this.userName = userName;
        this.userPwd = userPwd;
        this.userAge = userAge.toString();
        this.userSex = userSex;
        this.userEmail = userEmail;
        this.userPhone = userPhone.toString();
        this.userDate = userDate.toString();
        this.userPosition = userPosition;
        this.deptID = deptID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserDate() {
        return userDate;
    }

    public void setUserDate(String userDate) {
        this.userDate = userDate;
    }

    public String getUserPosition() {
        return userPosition;
    }

    public void setUserPosition(String userPosition) {
        this.userPosition = userPosition;
    }

    public String getDeptID() {
        return deptID;
    }

    public void setDeptID(String deptID) {
        this.deptID = deptID;
    }
}
