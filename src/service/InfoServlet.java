package service;

import com.google.gson.Gson;
import dao.IBaseDao;
import dao.IDeptInfo;
import dao.IUserInfo;
import model.DeptInfo;
import model.UserInfo;
import model.WorkDateInfo;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

import static util.Utils.getEntity;
import static util.Utils.getSqlSessionFactory;
import static util.Utils.getUTF8Writer;

/**
 * Created by Jch on 2016/8/26.
 * 处理部门信息和员工信息的Servlet
 * 处理工作时间的servlet
 * 映射的路径是/info
 */

@WebServlet(name = "InfoServlet")
public class InfoServlet extends HttpServlet  {
    private static SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

    // 客户端发送数据用post请求，数据格式为json
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SqlSession session = sqlSessionFactory.openSession();

        // 获取post请求的entity-body
        String entity = getEntity(request);

        // 数据操作接口和post过来的json数据转换成的对象
        Object jObj = null;
        IBaseDao iDao = null;

        // 针对不同的表将上面两个变量实例化
        switch (request.getParameter("tblname")){
            case "tbl_userinfo":
                jObj = new Gson().fromJson(entity, UserInfo.class);
                iDao = session.getMapper(IUserInfo.class);
                break;
            case "tbl_deptinfo" :
                jObj = new Gson().fromJson(entity, DeptInfo.class);
                iDao = session.getMapper(IDeptInfo.class);
                break;
        }

        // 执行相应的数据库操作
        switch (request.getParameter("method")) {
            case "input":
                iDao.insert(jObj);
                break;
            case "update":
                iDao.update(jObj);
                break;
        }

        // 返回信息
        PrintWriter writer = response.getWriter();
        writer.print("true");

        // 释放资源
        session.commit();
        session.close();
        writer.close();
    }

    // 客户端请求数据用get请求
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SqlSession session = sqlSessionFactory.openSession();
        BufferedWriter writer = getUTF8Writer(response);

        switch (request.getParameter("tblname")){
            case "tbl_userinfo":
                IUserInfo iUserInfo = session.getMapper(IUserInfo.class);

                String userID = request.getParameter("userID");
                // 如果userID存在，那么只返回该ID的信息，否则进行条件查找
                if (userID != null) {
                    UserInfo info = iUserInfo.selectByID(Integer.valueOf(userID));
                    writer.write(new Gson().toJson(info));
                } else {
                    String deptID = request.getParameter("deptID");
                    String userPosition = request.getParameter("userPosition");

                    List<UserInfo> list = iUserInfo.selectMany(deptID, userPosition);
                    writer.write(new Gson().toJson(list));
                }
                break;

            case "tbl_deptinfo" :
                IDeptInfo iDeptInfo = session.getMapper(IDeptInfo.class);

                String deptID = request.getParameter("deptID");
                // deptID，那么只返回该ID的信息，否则返回全部部门
                if (deptID != null) {
                    DeptInfo info = iDeptInfo.selectByID(Integer.valueOf(deptID));
                    writer.write(new Gson().toJson(info));
                } else {
                    List<DeptInfo> list = iDeptInfo.selectMany();
                    writer.write(new Gson().toJson(list));
                }
                break;
        }

        // 释放资源
        session.close();
        writer.close();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession session = sqlSessionFactory.openSession();

        IBaseDao iDao = null;
        int id = Integer.valueOf(req.getParameter("ID"));

        switch (req.getParameter("tblname")) {
            case "tbl_userinfo":
                iDao = session.getMapper(IUserInfo.class);
                break;
            case "tbl_deptinfo":
                iDao = session.getMapper(IDeptInfo.class);
                break;
        }
        iDao.deleteByID(id);

        // 返回信息
        PrintWriter writer = resp.getWriter();
        writer.print("true");

        // 释放资源
        session.commit();
        session.close();
        writer.close();
    }
}
