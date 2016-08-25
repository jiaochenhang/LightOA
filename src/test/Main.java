package test;

import dao.IUserInfo;
import model.UserInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

/**
 * Created by Jch on 2016/8/25.
 */
public class Main {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static{
        try{
            reader = Resources.getResourceAsReader("MyBatisConfig.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            IUserInfo userOperation=session.getMapper(IUserInfo.class);
            UserInfo user = userOperation.selectByID(0);
            System.out.println(user.getUserPwd());
            System.out.println(user.getUserName());
        } finally {
            session.close();
        }
    }
}
