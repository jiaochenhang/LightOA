package dao;

import model.WorkDateInfo;

/**
 * Created by Cambridgewoo on 2016/8/27.
 */
public interface IWorkDateInfo extends IBaseDao<WorkDateInfo>{
    @Override
    void insert(WorkDateInfo info);

    @Override
    void update(WorkDateInfo info);
    WorkDateInfo selectByYearMon(int year,int month);
}
