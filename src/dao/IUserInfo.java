package dao;

import model.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Jch on 2016/8/25.
 */

public interface IUserInfo extends IBaseDao<UserInfo>{
    @Override
    UserInfo selectByID(int id);

    @Override
    void insert(UserInfo info);

    @Override
    void update(UserInfo info);

    @Override
    void deleteByID(int id);

    /**
     * 根据特定条件返回多个UserInfo
     * @param deptID 按部门ID查找
     * @param userPosition 按用户类型查找
     * @return UserInfo的List
     */
    List<UserInfo> selectMany(@Param("deptID") String deptID, @Param("userPosition") String userPosition);

    /**
     * 登录用接口
     * @param id 用户ID
     * @param password 用户密码
     * @param position 用户类型
     * @return 该用户信息，若用户ID或者密码错误则返回null
     */
    UserInfo login(@Param("id") String id, @Param("password") String password, @Param("position") String position);
}
