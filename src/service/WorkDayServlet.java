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

/**
 * Created by runjiezhang on 2016/8/27.
 */
@WebServlet(name = "WorkDayServlet")
public class WorkDayServlet extends HttpServlet {
    private static SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SqlSession session = sqlSessionFactory.openSession();
        PrintWriter writer = response.getWriter();

        // 获取post请求的entity-body
        String entity = getEntity(request);

        WorkDateInfo jObj = new Gson().fromJson(entity, WorkDateInfo.class);
        IWorkDateInfo iDao = session.getMapper(IWorkDateInfo.class);
        if (iDao.selectByYearMon( jObj ) == null ){
            iDao.insert( jObj );
        }
        else{
            iDao.update( jObj );
        }

        session.commit();
        session.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SqlSession session = sqlSessionFactory.openSession();

        OutputStream stream= response.getOutputStream();
        OutputStreamWriter streamWriter = new OutputStreamWriter(stream, "UTF-8");
        BufferedWriter writer = new BufferedWriter(streamWriter);

        IWorkDateInfo iWorkDateInfo = session.getMapper(IWorkDateInfo.class);
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        WorkDateInfo workDateInfo = new WorkDateInfo(Integer.valueOf(year), Integer.valueOf(month), 0);
        workDateInfo = iWorkDateInfo.selectByYearMon(workDateInfo);
        String json = new Gson().toJson(workDateInfo);
        writer.write( json );

        session.close();
        writer.close();
    }
}
