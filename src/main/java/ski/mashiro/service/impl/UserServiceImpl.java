package ski.mashiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ski.mashiro.dao.UserDao;
import ski.mashiro.pojo.Code;
import ski.mashiro.pojo.Result;
import ski.mashiro.pojo.User;
import ski.mashiro.service.TableService;
import ski.mashiro.service.UserService;
import ski.mashiro.util.Encrypt;

import java.util.Date;

/**
 * @author MashiroT
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final TableService tableService;

    @Autowired
    public UserServiceImpl(UserDao userDao, TableService tableService) {
        this.userDao = userDao;
        this.tableService = tableService;
    }

    @Override
    public Result saveUser(User user) {
        if (user.getUserCode().length() < 4 || user.getUserCode().length() > 30) {
            return new Result(Code.SAVE_USER_FAILED, null);
        }
        if (user.getUserPassword().length() < 8 || user.getUserPassword().length() > 32) {
            return new Result(Code.SAVE_USER_FAILED, null);
        }
        tableService.createUserTable();
        user.setUserPasswordSalt(Encrypt.generateSalt(50));
        user.setUserPassword(Encrypt.encrypt(user.getUserPassword(), user.getUserPasswordSalt()));
        user.setUserTableName("tb_" + user.getUserCode());
        user.setUserApiToken(Encrypt.encrypt(Encrypt.generateSalt(50), Encrypt.generateSalt(50)));
        tableService.createCourseTable(user.getUserTableName());
        return new Result(userDao.saveUser(user) == 0 ? Code.SAVE_USER_FAILED : Code.SAVE_USER_SUCCESS, null);
    }

    @Override
    public Result deleteUser(String userCode, String password) {
        if (password.length() < 8 || password.length() > 32) {
            return new Result(Code.DELETE_USER_FAILED, null);
        }
        password = Encrypt.encrypt(password, userDao.getUserSalt(userCode));
        tableService.deleteCourseTable("tb_" + userCode);
        return new Result(userDao.delUser(userCode, password) == 0 ? Code.DELETE_USER_FAILED : Code.DELETE_USER_SUCCESS, null);
    }

    @Override
    public Result updateUser(User user) {
        if (user.getUserPassword() != null) {
            if (user.getUserPassword().length() < 8 || user.getUserPassword().length() > 32) {
                return new Result(Code.UPDATE_USER_FAILED, null);
            }
            user.setUserPassword(Encrypt.encrypt(user.getUserPassword(), userDao.getUserSalt(user.getUserCode())));
        }
        return new Result(userDao.updateUser(user) == 0 ? Code.UPDATE_USER_FAILED : Code.UPDATE_USER_SUCCESS, null);
    }

    @Override
    public Result getInitDate(String userCode) {
        Date date = userDao.getInitDate(userCode);
        if (date == null) {
            return  new Result(Code.GET_USER_INIT_DATE_FAILED, null);
        }
        return new Result(Code.GET_USER_INIT_DATE_SUCCESS, date);
    }

    @Override
    public Result getApiToken(String userCode) {
        String apiToken = userDao.getApiToken(userCode);
        if (apiToken == null) {
            return new Result(Code.GET_USER_API_FAILED, null);
        }
        return new Result(Code.GET_USER_API_SUCCESS, apiToken);
    }

    @Override
    public Result getUserByPassword(String userCode, String userPasswd) {
        if (userPasswd.length() < 8 || userPasswd.length() > 32) {
            return new Result(Code.GET_USER_BY_PASSWORD_SUCCESS, null);
        }
        User userByPassword = userDao.getUserByPassword(userCode, Encrypt.encrypt(userPasswd, userDao.getUserSalt(userCode)));
        if (userByPassword == null) {
            return new Result(Code.GET_USER_BY_PASSWORD_FAILED, null);
        }
        return new Result(Code.GET_USER_BY_PASSWORD_SUCCESS, userByPassword);
    }

    @Override
    public Result getUserByApiToken(String userCode, String apiToken) {
        User userByApiToken = userDao.getUserByApiToken(userCode, apiToken);
        if (userByApiToken == null) {
            return new Result(Code.GET_USER_BY_API_TOKEN_FAILED, null);
        }
        return new Result(Code.GET_USER_BY_API_TOKEN_SUCCESS, userByApiToken);
    }
}
