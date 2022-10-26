package ski.mashiro.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author MashiroT
 */
@Data
@ToString
@NoArgsConstructor
public class Course {
    private Integer courseId;
    private String courseName;
    private String courseLocation;
    private String courseLecturer;
    private String courseDate;
    private String courseWeek;

    public Course(String courseName, String courseLocation, String courseLecturer, String courseDate, String courseWeek) {
        this.courseName = courseName;
        this.courseLocation = courseLocation;
        this.courseLecturer = courseLecturer;
        this.courseDate = courseDate;
        this.courseWeek = courseWeek;
    }
}
