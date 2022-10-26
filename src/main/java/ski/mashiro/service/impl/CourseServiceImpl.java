package ski.mashiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ski.mashiro.dao.CourseDao;
import ski.mashiro.pojo.Code;
import ski.mashiro.pojo.Course;
import ski.mashiro.pojo.Result;
import ski.mashiro.service.CourseService;
import ski.mashiro.util.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author MashiroT
 */
@Service
public class CourseServiceImpl implements CourseService {
    private final CourseDao courseDao;

    @Autowired
    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public Result saveCourse(Course course, String tableName) {
        return new Result(courseDao.saveCourse(course, tableName) == 0 ? Code.SAVE_COURSE_FAILED : Code.SAVE_COURSE_SUCCESS, null);
    }

    @Override
    public Result delCourseById(int id, String tableName) {
        return new Result(courseDao.delCourseById(id, tableName) == 0 ? Code.DELETE_COURSE_FAILED : Code.DELETE_COURSE_SUCCESS, null);
    }

    @Override
    public Result delCourseByName(String courseName, String tableName) {
        return new Result(courseDao.delCourseByName(courseName, tableName) == 0 ? Code.DELETE_COURSE_FAILED : Code.DELETE_COURSE_SUCCESS, null);
    }

    @Override
    public Result updateCourse(Course course, String tableName) {
        return new Result(courseDao.updateCourse(course, tableName) == 0 ? Code.UPDATE_COURSE_FAILED : Code.UPDATE_COURSE_SUCCESS, null);
    }

    @Override
    public Result getCourseById(int id, String tableName) {
        Course courseById = courseDao.getCourseById(id, tableName);
        if (courseById == null) {
            return new Result(Code.GET_COURSE_BY_ID_FAILED, null);
        }
        return new Result(Code.GET_COURSE_BY_ID_SUCCESS, courseById);
    }

    @Override
    public Result listCourseByName(String courseName, String tableName) {
        List<Course> courseList = courseDao.listCourseByName(courseName, tableName);
        if (courseList == null) {
            return new Result(Code.LIST_COURSE_BY_NAME_FAILED, null);
        }
        courseList.sort(((o1, o2) -> comp(o1, o2)));
        return new Result(Code.LIST_COURSE_BY_NAME_SUCCESS, courseList);
    }

    @Override
    public Result listCourseByDate(String date, String tableName) {
        List<Course> courseList = courseDao.listCourseByDate(date, tableName);
        if (courseList == null) {
            return new Result(Code.LIST_COURSE_BY_DATE_FAILED, null);
        }
        courseList.sort((o1, o2) -> comp(o1, o2));
        return new Result(Code.LIST_COURSE_BY_DATE_SUCCESS, courseList);
    }

    @Override
    public Result listAllCourse(String tableName) {
        List<Course> courseList = courseDao.listAllCourse(tableName);
        if (courseList == null) {
            return new Result(Code.LIST_ALL_COURSE_FAILED, null);
        }
        courseList.sort((o1, o2) -> comp(o1, o2));
        return new Result(Code.LIST_ALL_COURSE_SUCCESS, courseList);
    }

    @Override
    public Result listEffCourseByName(String courseName, Date initDate, String tableName) {
        List<Course> courseList = courseDao.listCourseByName(courseName, tableName);
        if (courseList == null) {
            return new Result(Code.LIST_COURSE_BY_NAME_FAILED, null);
        }
        List<Course> effCourseList = getEffCourseList(initDate, courseList);
        return new Result(Code.LIST_COURSE_BY_NAME_SUCCESS, effCourseList);
    }

    @Override
    public Result listEffCourseByDate(String date, Date initDate, String tableName) {
        List<Course> courseList = courseDao.listCourseByDate(date, tableName);
        if (courseList == null) {
            return new Result(Code.LIST_COURSE_BY_DATE_FAILED, null);
        }
        List<Course> effCourseList = getEffCourseList(initDate, courseList);
        return new Result(Code.LIST_COURSE_BY_DATE_SUCCESS, effCourseList);
    }

    @Override
    public Result listAllEffCourse(Date initDate,String tableName) {
        List<Course> courseList = courseDao.listAllCourse(tableName);
        if (courseList == null) {
            return new Result(Code.LIST_ALL_COURSE_FAILED, null);
        }
        List<Course> effCourseList = getEffCourseList(initDate, courseList);
        return new Result(Code.LIST_ALL_COURSE_SUCCESS, effCourseList);
    }

    private List<Course> getEffCourseList(Date initDate, List<Course> courseList) {
        List<Course> rsList = new ArrayList<>(courseList.size());
        int currentWeek = Integer.parseInt(Utils.calcCurrentWeek(new Date(), initDate));
        for (Course course : courseList) {
            String[] weeks = course.getCourseWeek().split("-");
            if (Integer.parseInt(weeks[0]) > currentWeek || Integer.parseInt(weeks[1]) < currentWeek) {
                continue;
            }
            rsList.add(course);
        }
        rsList.sort(((o1, o2) -> comp(o1, o2)));
        return rsList;
    }

    private static int comp(Course o1, Course o2) {
        if (Utils.translateEngWeekToInteger(o1.getCourseDate().split(" ")[0]) < Utils.translateEngWeekToInteger(o2.getCourseDate().split(" ")[0])) {
            return -1;
        }
        if (Utils.translateEngWeekToInteger(o1.getCourseDate().split(" ")[0]).equals(Utils.translateEngWeekToInteger(o2.getCourseDate().split(" ")[0]))) {
            return (Integer.parseInt(o1.getCourseDate().split(" ")[1].split("-")[1].split(":")[0]) +
                    Integer.parseInt(o1.getCourseDate().split(" ")[1].split("-")[1].split(":")[1])) -
                    (Integer.parseInt(o2.getCourseDate().split(" ")[1].split("-")[1].split(":")[0]) +
                    Integer.parseInt(o2.getCourseDate().split(" ")[1].split("-")[1].split(":")[1]));
        }
        return 1;
    }
}
