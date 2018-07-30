package lesson20.task2;

import lesson20.task2.exception.LimitExceeded;

import java.util.Calendar;
import java.util.Date;

public class TransactionDAO {

    private Transaction[] transactions = new Transaction[10];
    private Utils utils = new Utils();

    public Transaction save (Transaction transaction) throws LimitExceeded {

        validate(transaction);
        //TODO return transaction from ARRAY "transactions[i]"
        return transaction;
    }

    public Transaction [] transactionList(){

        return null;
    }

    public Transaction [] transactionList(String city){

        return null;
    }

    public Transaction [] transactionList(int amount){

        return null;
    }

    /**
     *
     * Additional methods
     */

    private Transaction[] getTransactionsPerDay(Date dateOfCurTransaction){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateOfCurTransaction);

        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        int count = 0;
        for (Transaction transaction : transactions){
            if(transaction != null){
                calendar.setTime(transaction.getDateCreated());
                int trMonth = calendar.get(Calendar.MONTH);
                int trDay = calendar.get(Calendar.DAY_OF_MONTH);

                if(trDay == day && trMonth == month){
                    count++;
                }
            }
        }

        Transaction[] result = new Transaction[count];
        int index = 0;
        for (Transaction transaction : transactions){
            if(transaction != null){
                calendar.setTime(transaction.getDateCreated());
                int trMonth = calendar.get(Calendar.MONTH);
                int trDay = calendar.get(Calendar.DAY_OF_MONTH);

                if(trDay == day && trMonth == month){
                    result[index] = transaction;
                    index++;
                }
            }
        }

        return result;
    }

    private void validate(Transaction transaction)throws LimitExceeded{
        //sum of transaction is more than total limit
        //sum of transaction is more than limit per day
        //amount of transactions per day is more than limited
        //transactions city is not accepted >> BadRequestException
        //not enough space in array >> internalServerException;
        if(transaction.getAmount() > utils.getLimitSimpleTransactionAmount()){
            throw new LimitExceeded("Transaction limit is exceeded" + transaction.getId() + ". Can't be saved");
        }

        int sum = 0;
        for(Transaction tr : getTransactionsPerDay(transaction.getDateCreated())){
            sum += tr.getAmount();
        }

        if(sum > utils.getLimitTransactionsPerDayAmount()){
            throw new LimitExceeded("Transaction limit sum is exceeded " + transaction.getId() + ". Can't be saved");
        }

        int count = getTransactionsPerDay(transaction.getDateCreated()).length;
        if(count > utils.getLimitSimpleTransactionAmount()){
            throw new LimitExceeded("Transaction limit amount is exceeded " + transaction.getId() + ". Can't be saved");
        }

        //TODO validate transaction city
        //TODO validate enough space in array
    }
}
