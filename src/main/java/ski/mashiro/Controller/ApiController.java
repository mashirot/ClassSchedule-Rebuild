package ski.mashiro.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ski.mashiro.pojo.Code;
import ski.mashiro.pojo.Result;
import ski.mashiro.pojo.User;
import ski.mashiro.service.CourseService;
import ski.mashiro.service.UserService;
import ski.mashiro.util.Utils;

/**
 * @author MashiroT
 */
@RestController
@RequestMapping("/api")
public class ApiController {
    private final UserService userService;
    private final CourseService courseService;

    @Autowired
    public ApiController(UserService userService, CourseService courseService) {
        this.userService = userService;
        this.courseService = courseService;
    }

    @PostMapping("/effSchedules")
    public Result listAllEffectiveCourses(@RequestBody User user) {
        Result loginRs = userService.getUserByApiToken(user.getUserCode(), user.getUserApiToken());
        if (loginRs.getCode().equals(Code.GET_USER_API_SUCCESS)) {
            User data = (User) loginRs.getData();
            return courseService.listAllEffCourse(data.getTermInitDate(), data.getUserTableName());
        }
        return loginRs;
    }

    @PostMapping("/schedules")
    public Result listAllCourses(@RequestBody User user) {
        Result loginRs = userService.getUserByApiToken(user.getUserCode(), user.getUserApiToken());
        if (loginRs.getCode().equals(Code.GET_USER_API_SUCCESS)) {
            User data = (User) loginRs.getData();
            return courseService.listAllCourse(data.getUserTableName());
        }
        return loginRs;
    }

    @PostMapping("/effDateSchedules")
    public Result listEffCourseByDate(@RequestBody User user) {
        Result loginRs = userService.getUserByApiToken(user.getUserCode(), user.getUserApiToken());
        if (loginRs.getCode().equals(Code.GET_USER_API_SUCCESS)) {
            User data = (User) loginRs.getData();
            return courseService.listEffCourseByDate(Utils.getWeek(), data.getTermInitDate(), data.getUserTableName());
        }
        return loginRs;
    }

    @PostMapping("/dateSchedules")
    public Result listCourseByDate(@RequestBody User user) {
        Result loginRs = userService.getUserByApiToken(user.getUserCode(), user.getUserApiToken());
        if (loginRs.getCode().equals(Code.GET_USER_API_SUCCESS)) {
            User data = (User) loginRs.getData();
            return courseService.listCourseByDate(Utils.getWeek(), data.getUserTableName());
        }
        return loginRs;
    }

}
