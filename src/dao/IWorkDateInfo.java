package dao;

import model.WorkingDateInfo;

/**
 * Created by Cambridgewoo on 2016/8/27.
 */
public interface IWorkingDateInfo extends IBaseDao<WorkingDateInfo>{
    void insert(WorkingDateInfo info);
    void update(WorkingDateInfo info);
}
