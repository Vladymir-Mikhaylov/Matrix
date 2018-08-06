package lesson20.task2;

import lesson20.task2.exception.BadRequestException;
import lesson20.task2.exception.InternalServerException;
import lesson20.task2.exception.LimitExceeded;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionDAO {

    private Transaction[] transactions = new Transaction[10];
    private Utils utils = new Utils();

    public Transaction save (Transaction transaction){
        Transaction result = null;
        try {
            validate(transaction);
            result = insert(transaction);
        }catch (LimitExceeded ex){
            System.out.println(ex.getMessage());
        }catch (InternalServerException ex){
            System.out.println(ex.getMessage());
        }catch (BadRequestException ex){
            System.out.println(ex.getMessage());
        }
        return result;
    }

    public Transaction [] transactionList(){
        int counter = 0;
        for(Transaction tr : transactions){
            if(tr != null){
                counter++;
            }
        }
        Transaction[] result = new Transaction[counter];
        int position = 0;
        for(Transaction tr : transactions){
            if(tr != null){
                result[position] = tr;
                position++;
            }
        }
        return result;
    }

    public Transaction [] transactionList(String city){
        int counter = 0;
        for(Transaction tr : transactions){
            if(tr != null && tr.getCity() == city){
                counter++;
            }
        }
        Transaction[] result = new Transaction[counter];
        int position = 0;
        for(Transaction tr : transactions){
            if(tr != null && tr.getCity() == city){
                result[position] = tr;
                position++;
            }
        }
        return result;
    }

    public Transaction [] transactionList(int amount){
        int counter = 0;
        for(Transaction tr : transactions){
            if(tr != null && tr.getAmount() == amount){
                counter++;
            }
        }
        Transaction[] result = new Transaction[counter];
        int position = 0;
        for(Transaction tr : transactions){
            if(tr != null && tr.getAmount() == amount){
                result[position] = tr;
                position++;
            }
        }
        return result;
    }

    private void validate(Transaction transaction) throws BadRequestException, InternalServerException {
        //sum of transaction is more than total limit
        //sum of transaction is more than limit per day
        //amount of transactions per day is more than limited
        //transactions city is not accepted >> BadRequestException
        //not enough space in array >> internalServerException;
        if(transaction.getAmount() > utils.getLimitSimpleTransactionAmount()){
            throw new LimitExceeded("Transaction limit is exceeded. Transaction " + transaction.getId() + " can't be saved");
        }

        int sum = 0;
        for(Transaction tr : getTransactionsPerDay(transaction.getDateCreated())){
            if(tr != null) {
                sum += tr.getAmount();
            }
        }

        if(sum > utils.getLimitTransactionsPerDayAmount()){
            throw new LimitExceeded("Transaction limit sum is exceeded. Transaction " + transaction.getId() + " can't be saved");
        }

        int count = getTransactionsPerDay(transaction.getDateCreated()).length;
        if(count > utils.getLimitSimpleTransactionAmount()){
            throw new LimitExceeded("Transaction limit amount is exceeded " + transaction.getId() + ". Can't be saved");
        }
        //validate is transaction city is allowed or not?
        validateTransactionCity(transaction.getCity(), transaction.getId());
        //validate is there any free space for new transaction in our storage
        validateFreeSpaceInTransactionsArrayAndCheckExistedTransaction(transaction);
    }

    /**
     * Additional methods
     */

    private Transaction[] getTransactionsPerDay(Date dateOfCurTransaction){
        int count = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YY");
        for (Transaction tr : transactions){
            if(tr != null && sdf.format(dateOfCurTransaction).equals(sdf.format(tr.getDateCreated()))){
                    count++;
            }
        }

        Transaction[] result = new Transaction[count];
        int position = 0;
        for (Transaction tr : transactions){
            if(tr != null && sdf.format(dateOfCurTransaction).equals(sdf.format(tr.getDateCreated()))){
                transactions[position] = tr;
                position++;
            }
        }
        return result;
    }

    private void validateTransactionCity(String transactionCity, long id) throws BadRequestException {
        for (String city : utils.getCities()){
            if(city == transactionCity){
                return;
            }
        }
        throw new BadRequestException("Transaction is denied. Not acceptable city for transaction: " + id);
    }

    private void validateFreeSpaceInTransactionsArrayAndCheckExistedTransaction(Transaction transaction) throws InternalServerException, BadRequestException {
        boolean freeSpace = false;
        //going by loop and checking free space and already existed transaction
        for(Transaction tr : transactions){
            if(tr == null){
                freeSpace = true;
            }
            if(tr != null && tr.equals(transaction)){
                throw new BadRequestException("Transaction is denied. Transaction: " + transaction.getId() + " already exist");
            }
        }
        if(!freeSpace) {
            throw new InternalServerException("Transaction is denied. There is not enough free space in storage for transaction: " + transaction.getId());
        }
    }

    private Transaction insert (Transaction transaction){
        for(int i = 0; i<transactions.length; i++){
            if(transactions[i] == null){
                transactions[i] = transaction;
                return transactions[i];
            }
        }
        return null;//this line newer will be compiled;
    }
}
