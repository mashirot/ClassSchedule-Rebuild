package ski.mashiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ski.mashiro.pojo.Code;
import ski.mashiro.pojo.Result;
import ski.mashiro.pojo.User;
import ski.mashiro.service.UserService;
import ski.mashiro.util.Utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author MashiroT
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public Result saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("/{userCode}")
    public Result deleteUser(@PathVariable String userCode) {
        return userService.deleteUser(userCode);
    }

    @PutMapping
    public Result updateUser(@RequestBody User user, HttpServletResponse response) {
        Result result = userService.updateUser(user);
        if (user.getTermInitDate() != null) {
            if (result.getCode().equals(Code.UPDATE_USER_SUCCESS)) {
                Cookie initDate = new Cookie("initDate", Utils.dateToStr(user.getTermInitDate()));
                initDate.setPath("/");
                initDate.setMaxAge(24 * 60 * 60);
                response.addCookie(initDate);
            }
        }
        return result;
    }

    @GetMapping("/initDate")
    public Result getInitDate(@CookieValue String userCode) {
        return userService.getInitDate(userCode);
    }

    @GetMapping("/api")
    public Result getApiToken(@CookieValue String userCode) {
        return userService.getApiToken(userCode);
    }

    @PostMapping("/login")
    public Result getUser(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        Result userByPassword = userService.getUserByPassword(user.getUserCode(), user.getUserPassword());
        if (userByPassword.getCode().equals(Code.GET_USER_BY_PASSWORD_FAILED)) {
            return userByPassword;
        }
        Cookie userCode = new Cookie("userCode", ((User) userByPassword.getData()).getUserCode());
        Cookie userNickname = new Cookie("userNickname", ((User) userByPassword.getData()).getUserNickname());
        Cookie initDate = new Cookie("initDate", Utils.dateToStr(((User) userByPassword.getData()).getTermInitDate()));
        Cookie currentWeek = new Cookie("currentWeek", Utils.calcCurrentWeek(new Date(), ((User) userByPassword.getData()).getTermInitDate()));
        userCode.setPath("/");
        userCode.setMaxAge(24 * 60 * 60);
        userNickname.setPath("/");
        userNickname.setMaxAge(24 * 60 * 60);
        initDate.setPath("/");
        initDate.setMaxAge(24 * 60 * 60);
        currentWeek.setPath("/");
        currentWeek.setMaxAge(24 * 60 * 60);
        response.addCookie(userCode);
        response.addCookie(userNickname);
        response.addCookie(initDate);
        response.addCookie(currentWeek);
        HttpSession session = request.getSession();
        session.setAttribute("userCode", ((User) userByPassword.getData()).getUserCode());
        return userByPassword;
    }

    @PostMapping("/logout")
    public Result logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie userCode = new Cookie("userCode", null);
        Cookie userNickname = new Cookie("userNickname", null);
        Cookie initDate = new Cookie("initDate", null);
        Cookie currentWeek = new Cookie("currentWeek", null);
        userCode.setPath("/");
        userCode.setMaxAge(0);
        userNickname.setPath("/");
        userNickname.setMaxAge(0);
        initDate.setPath("/");
        initDate.setMaxAge(0);
        currentWeek.setPath("/");
        currentWeek.setMaxAge(0);
        response.addCookie(userCode);
        response.addCookie(userNickname);
        response.addCookie(initDate);
        response.addCookie(currentWeek);
        request.getSession().invalidate();
        return new Result(Code.USER_LOGOUT_SUCCESS,null);
    }

}
