package practise.wu.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author dihua.wu
 * @description
 * @create 2020/7/21
 */
public class CommUtils {

    public static final String format = "yyyy-MM-dd HH:mm:ss";

    public static String getTimeStr() {
        Calendar cal = Calendar.getInstance();
        return new SimpleDateFormat(format).format(cal.getTime());
    }
}
