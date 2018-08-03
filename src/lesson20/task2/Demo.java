package lesson20.task2;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Demo {
    public static void main(String[] args) {

        Transaction tr1 = new Transaction(1, "Kiev", 100, "test1", TransactionType.OUTCOME, new Date());
        Transaction tr2 = new Transaction(1, "Odessa", 100, "test1", TransactionType.INCOME, new Date());

        Utils utils = new Utils();
        String date_ = new SimpleDateFormat("DD/MM/YYYY").format(Calendar.getInstance().getTime());
        System.out.println("date as sting: " + date_);
        long day = new Date().getTime();

        String calendar = Calendar.getInstance().getTime().toString().substring(0, 10);
        System.out.println(day);
        System.out.println(calendar);

        TransactionDAO dao = new TransactionDAO();

        Controller controller = new Controller(dao);

        try {
            controller.save(tr1);
        }catch (Exception e){
            e.getMessage();
        }
    }
}
