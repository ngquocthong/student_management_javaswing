package lib;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class XUtil {
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public static Date convertStringToDate(String text) {
        try {
            return sdf.parse(text);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String convertDateToString(Date d) {
        try {
        return sdf.format(d);
    } catch (Exception e) {
        return sdf.format(new Date());}
    }
}
