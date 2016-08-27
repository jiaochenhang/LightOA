package service;

import com.google.gson.Gson;
import dao.IUserInfo;
import dao.IWorkDateInfo;
import model.WorkDateInfo;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import static util.Utils.getEntity;
import static util.Utils.getSqlSessionFactory;
import static util.Utils.getUTF8Writer;

/**
 * Created by runjiezhang on 2016/8/27.
 */

@WebServlet(name = "WorkDateServlet")
public class WorkDateServlet extends HttpServlet {
    private static SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SqlSession session = sqlSessionFactory.openSession();

        // 获取post请求的entity-body
        String entity = getEntity(request);
        WorkDateInfo info = new Gson().fromJson(entity, WorkDateInfo.class);

        IWorkDateInfo iDao = session.getMapper(IWorkDateInfo.class);
        if (iDao.selectByYearMon(info) == null) {
            iDao.insert(info);
        } else {
            iDao.update(info);
        }

        session.commit();
        session.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SqlSession session = sqlSessionFactory.openSession();

        // 获得参数
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        WorkDateInfo workDateInfo = new WorkDateInfo(Integer.valueOf(year), Integer.valueOf(month), 0);

        // 查询数据库
        IWorkDateInfo iWorkDateInfo = session.getMapper(IWorkDateInfo.class);
        workDateInfo = iWorkDateInfo.selectByYearMon(workDateInfo);
        String json = new Gson().toJson(workDateInfo);

        // 响应数据
        BufferedWriter writer = getUTF8Writer(response);
        writer.write(json);

        // 释放资源
        session.close();
        writer.close();
    }
}
