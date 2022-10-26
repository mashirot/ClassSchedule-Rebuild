package ski.mashiro.service;

/**
 * @author MashiroT
 */
public interface TableService {
    /**
     * 创建用户表
     */
    void createUserTable();

    /**
     * 创建对应用户的课程表
     * @param tableName 表名
     */
    void createCourseTable(String tableName);

    /**
     * 删除对应用户的课程表
     * @param tableName 表名
     */
    void deleteCourseTable(String tableName);
}
