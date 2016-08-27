package dao;

import model.WorkDateInfo;

import java.lang.invoke.WrongMethodTypeException;
import java.util.List;

/**
 * Created by Cambridgewoo on 2016/8/27.
 */
public interface IWorkDateInfo extends IBaseDao<WorkDateInfo>{
    @Override
    void insert(WorkDateInfo info);

    @Override
    void update(WorkDateInfo info);
    WorkDateInfo selectByYearMon(WorkDateInfo info);
}
