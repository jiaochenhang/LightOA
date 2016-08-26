package dao;

import model.UserInfo;

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
}
