package lesson10.abstractBigExample;

public class Demo {
    public static void main(String[] args) {
        Company [] companies = new Company [5];
        String [] positions = new String[2];
        BankAccount bankAccount = new BankAccount(null, 0);
        DeveloperEmployee developerEmployee = new DeveloperEmployee("devEmpl", 25, 2, "midle", 1000, null, companies, positions);
        bankAccount.setEmployee(developerEmployee);
        //developerEmployee.setBankAccount(bankAccount);
        developerEmployee.paySalery();//pay to account who has an user
        developerEmployee.paySalery();//oay salary to a user with null bank account

        developerEmployee.changePosition("junior");//cahnge position
        //developerEmployee.changePosition(null);//change position with null

        //testing Employee.savePositionToHistory
        developerEmployee.savePositionToHistory();//save positions
        DeveloperEmployee developerEmployee1 = new DeveloperEmployee("devEmpl", 25, 2, "midle", 1000, null, companies, null);
        developerEmployee1.savePositionToHistory();//save positions to null history
        developerEmployee.savePositionToHistory();//save position to full array

        EmployeeController employeeController = new EmployeeController();
        employeeController.paySalariesToEmployees();//pay to null

        DeveloperEmployee developerEmployee22 = new DeveloperEmployee("devEmpl", 25, 2, "midle", 1000, null, companies, positions);
        BankAccount bankAccount11 = new BankAccount(null, 0);
        bankAccount11.setEmployee(developerEmployee22);
        developerEmployee22.setBankAccount(bankAccount11);
        Employee[] employers = new Employee[5];
        employers[0] = developerEmployee22;

        EmployeeController eController = new EmployeeController(employers);

        eController.paySalariesToEmployees();//pay to employers;
        eController.paySalariesToEmployees();//pay to employers in array where is null;

        String end = "";


    }
}
