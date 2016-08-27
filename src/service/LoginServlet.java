package service;

import com.google.gson.Gson;
import dao.IUserInfo;
import model.UserInfo;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import static util.Utils.*;

/**
 * 只处理登录的Servlet
 * Created by Jch on 2016/8/25.
 */
public class LoginServlet extends HttpServlet {
    private static SqlSessionFactory sessionFactory = getSqlSessionFactory();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession session = sessionFactory.openSession();

        // 从参数里拿到用户输入
        String password = req.getParameter("userPwd");
        String username = req.getParameter("userID");
        String position = req.getParameter("position");

        // 查询数据库
        IUserInfo iUserInfo = session.getMapper(IUserInfo.class);
        UserInfo info = iUserInfo.login(username, password, position);
        String json = new Gson().toJson(info);

        // 返回结果
        BufferedWriter writer = getUTF8Writer(resp);
        writer.write(json);

        // 资源释放
        session.close();
        writer.close();
    }
}
