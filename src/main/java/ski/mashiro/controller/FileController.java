package ski.mashiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ski.mashiro.pojo.Result;
import ski.mashiro.service.CourseService;

/**
 * @author MashiroT
 */
@RestController
@RequestMapping("/file")
public class FileController {

    private final CourseService courseService;

    @Autowired
    public FileController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/update")
    public Result update(@RequestBody MultipartFile file, @CookieValue(value = "userCode") String userCode) {
        return courseService.saveCourseFromFile(file, "tb_" + userCode);
    }

}