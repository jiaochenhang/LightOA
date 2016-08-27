package service;

import com.google.gson.Gson;
import dao.IBaseDao;
import dao.IDeptInfo;
import dao.IUserInfo;
import model.DeptInfo;
import model.UserInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * Created by Jch on 2016/8/26.
 * 处理部门信息和员工信息的Servlet
 * 映射的路径是/info
 */

@WebServlet(name = "InfoServlet")
public class InfoServlet extends HttpServlet  {
    // 静态的session工厂，使得每次进行数据库操作都很方便
    private static SqlSessionFactory sqlSessionFactory;

    static{
        try{
            Reader reader = Resources.getResourceAsReader("MyBatisConfig.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // 客户端用post方法把要发送的json数据放在entity里面
    // 这个方法获取entity里的数据并转换成String
    private String getEntity(HttpServletRequest request) throws ServletException, IOException {
        StringBuilder builder = new StringBuilder();
        String line;

        // 必须要先获得InputStream，这样才能设置正确的编码格式
        InputStream stream = request.getInputStream();
        InputStreamReader streamReader = new InputStreamReader(stream, "UTF-8");
        BufferedReader reader = new BufferedReader(streamReader);

        while((line = reader.readLine())!= null)
            builder.append(line);
        return builder.toString();
    }

    // 处理post请求
    // 客户端用post请求把json数据发过来
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SqlSession session = sqlSessionFactory.openSession();
        // 获取post请求的entity-body
        String str = getEntity(request);
        System.out.println(str);

        // 数据操作接口和post过来的json数据转换成的对象
        IBaseDao iDao = null;
        Object jObj = null;

        // 根据不同的表将上面两个变量赋值
        switch (request.getParameter("tblname")){
            case "tbl_userinfo":
                jObj = new Gson().fromJson(str, UserInfo.class);
                iDao = session.getMapper(IUserInfo.class);
                break;
            case "tbl_deptinfo" :
                jObj = new Gson().fromJson(str, DeptInfo.class);
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

        session.commit();
        session.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SqlSession session = sqlSessionFactory.openSession();
        PrintWriter writer = response.getWriter();

        switch (request.getParameter("tblname")){
            case "tbl_userinfo":
                IUserInfo iUserInfo = session.getMapper(IUserInfo.class);

                String userID = request.getParameter("userID");
                if (userID != null) {
                    UserInfo info = iUserInfo.selectByID(Integer.valueOf(userID));
                    writer.print(new Gson().toJson(info));
                    break;
                }

                String deptID = request.getParameter("deptID");
                String userPosition = request.getParameter("userPosition");
                List<UserInfo> list = iUserInfo.selectMany(deptID,userPosition);
                writer.print(new Gson().toJson(list));
                break;
            case "tbl_deptinfo" :
                break;
        }

        session.close();
        writer.close();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession session = sqlSessionFactory.openSession();
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
        session.commit();
        session.close();
    }
}
