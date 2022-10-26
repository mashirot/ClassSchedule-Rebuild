package ski.mashiro.util;

import java.util.Calendar;
import java.util.Date;

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

}
