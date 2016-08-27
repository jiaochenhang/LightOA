package model;

/**
 * Created by Cambridgewoo on 2016/8/27.
 */
public class WorkDateInfo {
    public WorkDateInfo(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    private int year;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    private int month;
    private int day;
}
