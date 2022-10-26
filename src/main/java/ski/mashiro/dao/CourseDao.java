package ski.mashiro.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import ski.mashiro.pojo.Course;

import java.util.List;

/**
 * @author MashiroT
 */
@Mapper
public interface CourseDao {
    int saveCourse(@Param("course") Course course, @Param("tableName") String tableName);
    int delCourseById(@Param("id") int id, @Param("tableName") String tableName);
    int delCourseByName(@Param("courseName") String courseName, @Param("tableName") String tableName);
    int updateCourse(@Param("course") Course course, @Param("tableName") String tableName);
    Course getCourseById(@Param("id") int id, @Param("tableName") String tableName);
    List<Course> listCourseByName(@Param("courseName") String courseName, @Param("tableName") String tableName);
    List<Course> listCourseByDate(@Param("date") String date, @Param("tableName") String tableName);
    List<Course> listAllCourse(@Param("tableName") String tableName);
}
