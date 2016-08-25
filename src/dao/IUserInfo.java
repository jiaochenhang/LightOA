package dao;

import model.UserInfo;

/**
 * Created by Jch on 2016/8/25.
 */
public interface IUserInfo {
    public UserInfo selectByID(int id);
}
