package test;

import com.google.gson.Gson;
import dao.IDeptInfo;
import dao.IUserInfo;
import model.DeptInfo;
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
//        String str = "{\"userID\":\"11\",\"userName\":\"11\",\"userPwd\":\"\",\"userSex\":\"男\",\"userEmail\":\"\",\"userPosition\":\"\",\"deptID\":\"\",\"userAge\":\"44\",\"userPhone\":\"234\",\"userDate\":\"20130909\"}";
//        UserInfo info = new Gson().fromJson(str, UserInfo.class);
//        SqlSession session = sqlSessionFactory.openSession();
//        session.getMapper(IUserInfo.class);
//        System.out.println();
        String str = null;
        int i = Integer.valueOf(str);
    }
}
