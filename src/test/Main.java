package test;

import com.google.gson.Gson;
import dao.IDeptInfo;
import dao.IUserInfo;
import model.DeptInfo;
import model.UserInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;
import java.sql.Date;
import java.sql.Time;

/**
 * Created by Jch on 2016/8/25.
 */
public class Main {
    public static void main(String[] args) {
        Date date = new Date( System.currentTimeMillis());
        System.out.println(date);
        Time time = new Time(System.currentTimeMillis());
        System.out.println(time);
    }
}
