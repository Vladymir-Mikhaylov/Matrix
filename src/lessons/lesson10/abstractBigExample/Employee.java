package lessons.lesson10.abstractBigExample;

public abstract class Employee {

    private String name;
    private int age;
    private int yearsOfExperience;

    private String curPosition;

    private int salaryPerMonth;
    private BankAccount bankAccount;
    private Company[] companiesWorked = new Company[10];
    private String[] positionsWorked = new String[10];

    abstract void paySalery();

    void changePosition(String newPosition){
        //save current position to history
        //change position
        changePosition(newPosition);
        curPosition = newPosition;
    }

    private void savePositionToHistory(){
        int index = 0;
        for(String position : positionsWorked){
            if(position == null){
                positionsWorked[index] = curPosition;
                break;
            }
            index++;
        }
    }

    int getBalance(){
        return bankAccount.getBalance();
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public int getSalaryPerMonth() {
        return salaryPerMonth;
    }

    public String getName() {
        return name;
    }
}
