package test;

import com.google.gson.Gson;
import dao.IDeptInfo;
import dao.IUserInfo;
import dao.IWorkDateInfo;
import model.DeptInfo;
import model.UserInfo;
import model.WorkDateInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;
/**
 * Created by runjiezhang on 2016/8/27.
 */
public class test {
    /**
     * 测试增加,增加后，必须提交事务，否则不会写入到数据库.
     */
    private static SqlSessionFactory sqlSessionFactory;
    static{
        try{
            Reader reader = Resources.getResourceAsReader("MyBatisConfig.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void addWorkDateInfo(){
        WorkDateInfo workDateInfo = new WorkDateInfo();
        workDateInfo.setYear(2016);
        workDateInfo.setMonth(1);
        workDateInfo.setDay(0xb6ff9f1c);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            IWorkDateInfo iWorkDateInfo=session.getMapper(IWorkDateInfo.class);
            iWorkDateInfo.insert(workDateInfo);
            session.commit();
            System.out.println("当前增加的工作时间为:"+ workDateInfo.getYear() + workDateInfo.getMonth());
        } finally {
            session.close();
        }
    }
    public void updateWorkDateInfo(){
        //先得到时间,然后修改，提交。
        SqlSession session = sqlSessionFactory.openSession();
        try {
            IWorkDateInfo iWorkDateInfo=session.getMapper(IWorkDateInfo.class);
            WorkDateInfo workDateInfo = iWorkDateInfo.selectByYearMon(2016,1);
            workDateInfo.setDay(0x12345678);
            iWorkDateInfo.update(workDateInfo);
            session.commit();
        } finally {
            session.close();
        }
    }
    public static void main(String[] args) {
        test testWork = new test();
        testWork.addWorkDateInfo();
    }
}


