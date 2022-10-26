package ski.mashiro.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author MashiroT
 */
@Data
@ToString
public class User {
    private String userCode;
    private String userPassword;
    private String userPasswordSalt;
    private String userNickname;
    private Date termInitDate;
    private String userTableName;
    private String userApiToken;
}
