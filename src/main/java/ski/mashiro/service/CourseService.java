package ski.mashiro.service;

import org.springframework.web.multipart.MultipartFile;
import ski.mashiro.pojo.Course;
import ski.mashiro.pojo.Result;

import java.util.Date;

/**
 * @author MashiroT
 */
public interface CourseService {
    /**
     * 添加课程
     * @param course 入参
     * @param tableName 表名
     * @return 结果
     */
    Result saveCourse(Course course, String tableName);

    /**
     * 添加课程
     * @param multipartFile 文件
     * @param tableName 表名
     * @return 结果
     */
    Result saveCourseFromFile(MultipartFile multipartFile, String tableName);

    /**
     * 添加课程
     * @param id 入参
     * @param tableName 表名
     * @return 结果
     */
    Result delCourseById(int id, String tableName);

    /**
     * 添加课程
     * @param courseName 课程名
     * @param tableName 表名
     * @return 结果
     */
    Result delCourseByName(String courseName, String tableName);

    /**
     * 添加课程
     * @param course 入参
     * @param tableName 表名
     * @return 结果
     */
    Result updateCourse(Course course, String tableName);

    /**
     * 添加课程
     * @param id 入参
     * @param tableName 表名
     * @return 结果
     */
    Result getCourseById(int id, String tableName);

    /**
     * 添加课程
     * @param courseName 入参
     * @param tableName 表名
     * @return 结果
     */
    Result listCourseByName(String courseName, String tableName);

    /**
     * 添加课程
     * @param date 入参
     * @param tableName 表名
     * @return 结果
     */
    Result listCourseByDate(String date, String tableName);

    /**
     * 添加课程
     * @param tableName 表名
     * @return 结果
     */
    Result listAllCourse(String tableName);

    /**
     * 添加课程
     * @param courseName 入参
     * @param tableName 表名
     * @param initDate 学期开始日期
     * @return 结果
     */
    Result listEffCourseByName(String courseName, Date initDate, String tableName);

    /**
     * 添加课程
     * @param date 入参
     * @param tableName 表名
     * @param initDate 学期开始日期
     * @return 结果
     */
    Result listEffCourseByDate(String date, Date initDate, String tableName);

    /**
     * 添加课程
     * @param tableName 表名
     * @param initDate 学期开始日期
     * @return 结果
     */
    Result listAllEffCourse(Date initDate, String tableName);
}
