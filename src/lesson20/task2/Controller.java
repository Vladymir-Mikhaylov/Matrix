package lesson20.task2;

public class Controller {

    private TransactionDAO transactionDAO = new TransactionDAO();

    public Transaction save (Transaction transaction){
        Transaction tr = null;
        try{
           tr = transactionDAO.save(transaction);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        return tr;
    }

    public Transaction [] transactionList(){
        return transactionDAO.transactionList();
    }

    public Transaction [] transactionList(String city){
        return transactionDAO.transactionList(city);
    }

    public Transaction [] transactionList(int amount){
        return transactionDAO.transactionList(amount);
    }

    /**
     * Constructors
     */
    public Controller() {
    }
}
