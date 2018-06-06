package lessons.lesson10.abstractBigExample;

public class EmployeeController {

    private Employee[] employees = new Employee[100];

    void paySalariesToEmployees(){
        for(Employee employee: employees){
            employee.paySalery();
            System.out.println("Salary was paid successfully to " + employee.getName() + "employee");
        }
    }

}
