package ski.mashiro.util;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import ski.mashiro.pojo.Course;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author MashiroT
 */
public class Utils {
    public static Integer translateEngWeekToInteger(String str) {
        switch (str) {
            case "Monday":
                return 1;
            case "Tuesday":
                return 2;
            case "Wednesday":
                return 3;
            case "Thursday":
                return 4;
            case "Friday":
                return 5;
            case "Saturday":
                return 6;
            case "Sunday":
                return 7;
            default:
                return -1;
        }
    }

    public static String getWeek() {
        int dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
        switch (dayOfWeek) {
            case 1: return "Monday";
            case 2: return "Tuesday";
            case 3: return "Wednesday";
            case 4: return "Thursday";
            case 5: return "Friday";
            case 6: return "Saturday";
            case 7: return "Sunday";
            default: return null;
        }
    }

    public static String calcCurrentWeek(Date currentDate, Date initDate) {
        Calendar init = Calendar.getInstance();
        init.setTime(initDate);
        return String.valueOf((currentDate.getTime() - initDate.getTime() - (init.get(Calendar.DAY_OF_WEEK) - 1) * (24 * 60 * 60 * 1000)) / (7 * 24 * 60 * 60 * 1000) + 1);
    }

    public static String dateToStr(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public static Date strToDate(String str) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Course> analyzeFile(MultipartFile multipartFile) {
        try {
            String data = FileCopyUtils.copyToString(new InputStreamReader(multipartFile.getInputStream(), StandardCharsets.UTF_8));
            String[] courses = data.trim().split("#");
            List<Course> courseList = new ArrayList<>(courses.length);
            for (String course : courses) {
                String[] details = course.split("\\|");
                Course c = new Course();
                c.setCourseName(details[0]);
                c.setCourseLocation(details[1]);
                c.setCourseLecturer(details[2]);
                c.setCourseDate(details[3]);
                c.setCourseWeek(details[4]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
