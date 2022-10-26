package ski.mashiro.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author MashiroT
 */
@Getter
@Setter
public class Result {
    private Integer code;
    private Object data;

    public Result(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }
}
