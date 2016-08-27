package util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.*;

/**
 * Created by Jch on 2016/8/27.
 */
public class Utils {
    private static SqlSessionFactory sqlSessionFactory;

    static{
        try{
            Reader reader = Resources.getResourceAsReader("MyBatisConfig.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSqlSessionFactory(){
        return sqlSessionFactory;
    }

    // 客户端用post方法把要发送的json数据放在entity里面
    // 这个方法获取entity里的数据并转换成String
    public static String getEntity(HttpServletRequest request) throws ServletException, IOException {
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
}
