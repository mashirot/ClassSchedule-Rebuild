package ski.mashiro.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ski.mashiro.pojo.Course;
import ski.mashiro.pojo.Result;
import ski.mashiro.util.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class TestCourseService {
    private static final String TABLE_NAME = "tb_6657";
    private final Course c1 = new Course("aaa", "aaa", "aaa", "Monday 10:00-11:00", "01-16");
    private final Course c2 = new Course("bbb", "bbb", "bbb", "Monday 14:00-15:00", "01-16");
    private final Course c3 = new Course("ccc", "ccc", "ccc", "Tuesday 08:00-10:00", "01-16");
    private final Course c4 = new Course("ddd", "ddd", "ddd", "Tuesday 15:00-17:00", "01-16");

    @Autowired
    private CourseService courseService;

    @Test
    public void testSaveCourse() {
        Result result = courseService.saveCourse(c1, TABLE_NAME);
        System.out.println(result.getCode());
    }

    @Test
    public void testDeleteCourseById() {
        Result result = courseService.delCourseById(6, TABLE_NAME);
        System.out.println(result.getCode());
    }

    @Test
    public void testDeleteCourseByName() {
        Result result = courseService.delCourseByName("c", TABLE_NAME);
        System.out.println(result.getCode());
    }

    @Test
    public void testUpdateCourse() {
        Course course = new Course();
        course.setCourseId(7);
        course.setCourseLecturer("mxx");
        course.setCourseLocation("hy123321");
        Result result = courseService.updateCourse(course, TABLE_NAME);
        System.out.println(result.getCode());
    }

    @Test
    public void testGetCourseById() {
        Result courseById = courseService.getCourseById(7, TABLE_NAME);
        System.out.println(courseById.getData());
    }

    @Test
    public void testListCourseByName() {
        Result result = courseService.listCourseByName("aaa", TABLE_NAME);
        System.out.println(result.getData());
    }

    @Test
    public void testListCourseByDate() {
        Result result = courseService.listCourseByDate("Monday", TABLE_NAME);
        System.out.println(result.getData());
    }

    @Test
    public void testListAllDate() {
        Result result = courseService.listAllCourse(TABLE_NAME);
        // [Course(courseId=10, courseName=aaa, courseLocation=aaa, courseLecturer=aaa, courseDate=Monday 10:00-11:00), Course(courseId=8, courseName=ccc, courseLocation=ccc, courseLecturer=ccc, courseDate=Tuesday 08:00-10:00), Course(courseId=7, courseName=bbb, courseLocation=bbb, courseLecturer=bbb, courseDate=Monday 14:00-15:00), Course(courseId=9, courseName=ddd, courseLocation=ddd, courseLecturer=ddd, courseDate=Tuesday 15:00-17:00)]
        System.out.println(result.getData());
    }

    @Test
    public void testSort() {
        List<Course> list = new ArrayList<>();
        list.add(c3);
        list.add(c1);
        list.add(c4);
        list.add(c2);
        Collections.addAll(list, c3, c2, c1, c4);
        list.sort(((o1, o2) -> {
            if (Utils.translateEngWeekToInteger(o1.getCourseDate().split(" ")[0]) < Utils.translateEngWeekToInteger(o2.getCourseDate().split(" ")[0])) {
                return -1;
            }
            return (Integer.parseInt(o1.getCourseDate().split(" ")[1].split("-")[1].split(":")[0]) +
                    Integer.parseInt(o1.getCourseDate().split(" ")[1].split("-")[1].split(":")[1])) -
                    (Integer.parseInt(o2.getCourseDate().split(" ")[1].split("-")[1].split(":")[0]) +
                    Integer.parseInt(o2.getCourseDate().split(" ")[1].split("-")[1].split(":")[1]));
        }));
        for (Course course : list) {
            System.out.println(course.getCourseDate());
        }
    }
}
