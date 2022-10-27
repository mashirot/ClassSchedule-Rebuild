package ski.mashiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ski.mashiro.pojo.Course;
import ski.mashiro.pojo.Result;
import ski.mashiro.service.CourseService;
import ski.mashiro.util.Utils;

/**
 * @author MashiroT
 */
@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public Result saveCourse(@RequestBody Course course, @CookieValue String userCode) {
        return courseService.saveCourse(course, "tb_" + userCode);
    }

    @DeleteMapping("/id/{id}")
    public Result deleteCourseById(@PathVariable Integer id, @CookieValue String userCode) {
        return courseService.delCourseById(id, "tb_" + userCode);
    }

    @DeleteMapping("/name/{courseName}")
    public Result deleteCourseByName(@PathVariable String courseName, @CookieValue String userCode) {
        return courseService.delCourseByName(courseName, "tb_" + userCode);
    }

    @PutMapping
    public Result updateCourse(@RequestBody Course course, @CookieValue String userCode) {
        return courseService.updateCourse(course, "tb_" + userCode);
    }

    @GetMapping("/{id}")
    public Result getCourseById(@PathVariable Integer id, @CookieValue String userCode) {
        return courseService.getCourseById(id, "tb_" + userCode);
    }

    @GetMapping("/name/{courseName}")
    public Result listCourseByName(@PathVariable String courseName, @CookieValue String userCode) {
        return courseService.listCourseByName(courseName, "tb_" + userCode);
    }

    @GetMapping("/date/{date}")
    public Result listCourseByDate(@PathVariable String date, @CookieValue String userCode) {
        return courseService.listCourseByDate(date, "tb_" + userCode);
    }

    @GetMapping
    public Result listAllCourse(@CookieValue String userCode) {
        return courseService.listAllCourse("tb_" + userCode);
    }

    @GetMapping("/eff/name/{courseName}")
    public Result listEffCourseByName(@PathVariable String courseName, @CookieValue String initDate, @CookieValue String userCode) {
        return courseService.listEffCourseByName(courseName, Utils.strToDate(initDate), "tb_" + userCode);
    }

    @GetMapping("/eff/date/{date}")
    public Result listEffCourseByDate(@PathVariable String date, @CookieValue String initDate, @CookieValue String userCode) {
        return courseService.listEffCourseByDate(date, Utils.strToDate(initDate), "tb_" + userCode);
    }

    @GetMapping("/eff")
    public Result listAllEffCourse(@CookieValue String initDate, @CookieValue String userCode) {
        return courseService.listAllEffCourse(Utils.strToDate(initDate), "tb_" + userCode);
    }

}
