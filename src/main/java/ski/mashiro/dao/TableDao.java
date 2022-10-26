package ski.mashiro.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author MashiroT
 */
@Mapper
public interface TableDao {
    void createUserTable();
    void createCourseTable(@Param("tableName") String tableName);
    void deleteCourseTable(@Param("tableName") String tableName);
}
