package dao;

import model.DeptInfo;

import java.util.List;

/**
 * Created by Jch on 2016/8/25.
 */
public interface IDeptInfo extends IBaseDao<DeptInfo> {
    @Override
    DeptInfo selectByID(int id);

    @Override
    void insert(DeptInfo info);

    @Override
    void update(DeptInfo info);

    @Override
    void deleteByID(int id);


    /**
     * 获得所有部门信息
     * @return DeptInfo的List
     */
    List<DeptInfo> selectMany();
}
