package dao;

import model.Attendance;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Jch on 2016/8/27.
 */
public interface IAttendance extends IBaseDao<Attendance> {
    @Override
    void insert(Attendance info);

    /**
     * 根据特定条件返回多个或一个Attendance
     * @param deptID 按照部门查找
     * @param date 按日期查找
     * @param userID 按特定用户查找
     * @return Attendance的List
     */
    List<Attendance> selectMany(@Param("deptID") String deptID, @Param("date") String date, @Param("userID") String userID);
}
