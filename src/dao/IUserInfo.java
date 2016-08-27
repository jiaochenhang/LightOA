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

    List<UserInfo> selectMany(@Param("deptID") String deptID, @Param("userPosition") String userPosition);

    UserInfo login(@Param("id") String id, @Param("password") String password, @Param("position") String position);
}
