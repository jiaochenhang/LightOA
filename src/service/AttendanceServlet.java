package service;

import com.google.gson.Gson;
import dao.IAttendance;
import model.Attendance;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

import static util.Utils.*;

/**
 * 只用于处理签到
 * Created by Jch on 2016/8/27.
 */

@WebServlet(name = "AttendanceServlet")
public class AttendanceServlet extends HttpServlet {
    private static SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

    // 签到时用post方法，签到的类型、时间等信息由客户端传入
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SqlSession session = sqlSessionFactory.openSession();

        // 从entity中获得Attendance对象
        String body = getEntity(request);
        Attendance attendance = new Gson().fromJson(body, Attendance.class);

        // 插入数据库
        IAttendance iAttendance = session.getMapper(IAttendance.class);
        iAttendance.insert(attendance);

        // 释放资源
        session.commit();
        session.close();
    }

    // 查询签到信息用get方法
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SqlSession session = sqlSessionFactory.openSession();

        // 从参数里拿到相应条件
        String deptID = request.getParameter("deptID");
        String userID = request.getParameter("userID");
        String date = request.getParameter("date");

        // 查询数据库
        IAttendance iAttendance = session.getMapper(IAttendance.class);
        List<Attendance> list = iAttendance.selectMany(deptID, date, userID);
        String json = new Gson().toJson(list);

        // 返回结果
        BufferedWriter writer = getUTF8Writer(response);
        writer.write(json);

        // 释放资源
        session.close();
        writer.close();
    }
}
