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

/**
 * Created by Jch on 2016/8/26.
 * 处理部门信息和员工信息的Servlet
 * 处理工作时间的servlet
 * 映射的路径是/info
 */

@WebServlet(name = "InfoServlet")
public class InfoServlet extends HttpServlet  {
    // 静态的session工厂，使得每次进行数据库操作都很方便
    private static SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

    // 处理post请求
    // 客户端用post请求把json数据发过来
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SqlSession session = sqlSessionFactory.openSession();
        PrintWriter writer = response.getWriter();

        // 获取post请求的entity-body
        String entity = getEntity(request);

        // 数据操作接口和post过来的json数据转换成的对象
        IBaseDao iDao = null;
        Object jObj = null;

        // 根据不同的表将上面两个变量赋值
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
        writer.print("true");

        session.commit();
        session.close();
        writer.close();
    }

    //处理Get请求
    //服务端通过Get把JSON发送给客户端
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SqlSession session = sqlSessionFactory.openSession();
        PrintWriter writer = response.getWriter();

        switch (request.getParameter("tblname")) {
            case "tbl_userinfo":
                IUserInfo iUserInfo = session.getMapper(IUserInfo.class);

                String userID = request.getParameter("userID");
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
            case "tbl_deptinfo":
                IDeptInfo iDeptInfo = session.getMapper(IDeptInfo.class);

                String deptID = request.getParameter("deptID");
                Object obj;
                if (deptID != null) {
                    DeptInfo info = iDeptInfo.selectByID(Integer.valueOf(deptID));
                    writer.write(new Gson().toJson(info));
                } else {
                    List<DeptInfo> list = iDeptInfo.selectMany();
                    writer.write(new Gson().toJson(list));
                }
        }

        session.close();
        writer.close();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession session = sqlSessionFactory.openSession();
        PrintWriter writer = resp.getWriter();

        switch (req.getParameter("tblname")) {
            case "tbl_userinfo":
                IUserInfo iUserInfo = session.getMapper(IUserInfo.class);
                iUserInfo.deleteByID(Integer.valueOf(req.getParameter("ID")));
                break;
            case "tbl_deptinfo":
                IDeptInfo iDeptInfo = session.getMapper(IDeptInfo.class);
                iDeptInfo.deleteByID(Integer.valueOf(req.getParameter("ID")));
                break;
        }
        writer.print("true");

        session.commit();
        session.close();
        writer.close();
    }
}
