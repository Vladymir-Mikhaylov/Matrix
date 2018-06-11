package lesson10.abstractBigExample;

public class ManagerEmployee extends Employee {

    @Override
    void paySalery() {
        int newBalance = getBankAccount().getBalance() + getSalaryPerMonth();
        newBalance += newBalance*0.25;
        getBankAccount().setBalance(newBalance);

    }
}
