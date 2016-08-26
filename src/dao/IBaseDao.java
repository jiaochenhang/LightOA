package dao;

/**
 * Created by Jch on 2016/8/26.
 * 基本操作接口，其它接口都继承于此
 * 针对不同数据库的操作可以用同一份儿代码
 */
public interface IBaseDao<T> {
    T selectByID(int id);
    void insert(T info);
    void update(T info);
    void deleteByID(int id);
}
