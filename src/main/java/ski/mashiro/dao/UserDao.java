package ski.mashiro.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import ski.mashiro.pojo.User;

import java.util.Date;
/**
 * @author MashiroT
 */
@Mapper
public interface UserDao {
    int saveUser(@Param("user") User user);
    int delUser(@Param("userCode") String userCode, @Param("userPassword") String userPassword);
    int updateUser(@Param("user") User user);
    String getUserSalt(@Param("userCode") String userCode);
    Date getInitDate(@Param("userCode") String userCode);
    String getApiToken(@Param("userCode") String userCode);
    User getUserByPassword(@Param("userCode") String userCode, @Param("userPassword") String userPassword);
    User getUserByApiToken(@Param("userCode") String userCode, @Param("apiToken") String apiToken);
}
