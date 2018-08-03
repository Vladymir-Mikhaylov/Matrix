package lesson20;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class test {
    public static void main(String[] args) {
        String date1 = new SimpleDateFormat("DD/MM/YYYY").format(Calendar.getInstance().getTime());
        String date2 = new SimpleDateFormat("YY-d-MMM").format(new Date());
        System.out.println("date1 as sting: " + date1);
        System.out.println("date1 as sting: " + date2);
        /**
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String date = simpleDateFormat.format(new Date());
        System.out.println(date);
         */
    }
}
