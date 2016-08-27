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
 * Created by Jch on 2016/8/25.
 */
public class LoginServlet extends HttpServlet {
    private static SqlSessionFactory sessionFactory = getSqlSessionFactory();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession session = sessionFactory.openSession();

        OutputStream stream= resp.getOutputStream();
        OutputStreamWriter streamWriter = new OutputStreamWriter(stream, "UTF-8");
        BufferedWriter writer = new BufferedWriter(streamWriter);

        String password = req.getParameter("userPwd");
        String username = req.getParameter("userID");
        String position = req.getParameter("position");

        IUserInfo iUserInfo = session.getMapper(IUserInfo.class);
        UserInfo info = iUserInfo.login(username, password, position);

        String json = new Gson().toJson(info);
        writer.write(json);

        session.close();
        writer.close();
    }
}
