package instavans.sanchit.instavans.utilties;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sanchitjain on 06/03/16.
 */
public class Universal {

    public static String convertTime(String Time){
        long time = Long.valueOf(Time);
        Date date = new Date(time);
        Format format = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
        return format.format(date);
    }

}
