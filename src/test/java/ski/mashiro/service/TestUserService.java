package ski.mashiro.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ski.mashiro.pojo.Result;
import ski.mashiro.pojo.User;

import java.util.Date;

@SpringBootTest
public class TestUserService {
    @Autowired
    private UserService userService;
    @Test
    public void testSaveUser(){
        User user = new User();
        user.setUserCode("6657");
        user.setUserPassword("91043222");
        user.setUserNickname("MachineWJQ");
        user.setTermInitDate(new Date());
        Result result = userService.saveUser(user);
        System.out.println(result.getCode());
    }
    @Test
    public void testDeleteUser(){
        User user = new User();
        user.setUserCode("6657");
        user.setUserPassword("91043222");
        Result result = userService.deleteUser(user.getUserCode());
        System.out.println(result.getCode());
    }
    @Test
    public void testUpdateUser(){
        User user = new User();
        user.setUserCode("6657");
        user.setUserNickname("WJQMachine");
        Result result = userService.updateUser(user);
        System.out.println(result.getCode());
    }
    @Test
    public void testGetInitDate(){
        User user = new User();
        user.setUserCode("6657");
        Result initDate = userService.getInitDate(user.getUserCode());
        System.out.println(initDate.getData());
    }
    @Test
    public void testGetApiToken(){
        User user = new User();
        user.setUserCode("6657");
        Result apiToken = userService.getApiToken(user.getUserCode());
        System.out.println(apiToken.getData());
    }
    @Test
    public void testGetUserByPassword(){
        User user = new User();
        user.setUserCode("6657");
        user.setUserPassword("91043222");
        Result userByPassword = userService.getUserByPassword(user.getUserCode(), user.getUserPassword());
        System.out.println(userByPassword.getData());
    }
    @Test
    public void testGetUserByApiToken(){
        User user = new User();
        user.setUserCode("6657");
        user.setUserApiToken("a2234a407c0379237dd1b09881e8fc85");
        Result userByApiToken = userService.getUserByApiToken(user.getUserCode(), user.getUserApiToken());
        System.out.println(userByApiToken.getData());
    }
}
