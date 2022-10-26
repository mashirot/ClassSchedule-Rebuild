package ski.mashiro.Controller;

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

    @DeleteMapping
    public Result deleteUser(@RequestBody User user) {
        return userService.deleteUser(user.getUserCode(), user.getUserPassword());
    }

    @PutMapping
    public Result updateUser(@RequestBody User user) {
        return userService.updateUser(user);
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
        Cookie initDate = new Cookie("initDate", Utils.dateToStr(((User) userByPassword.getData()).getTermInitDate()));
        userCode.setPath("/");
        userCode.setMaxAge(24 * 60 * 60);
        initDate.setPath("/");
        initDate.setMaxAge(24 * 60 * 60);
        response.addCookie(userCode);
        response.addCookie(initDate);
        HttpSession session = request.getSession();
        session.setAttribute("userCode", ((User) userByPassword.getData()).getUserCode());
        return userByPassword;
    }

}
