package lesson30.task2;

import java.util.ArrayList;

public class EmployeeDAO {
    private ArrayList<Employee> employees = new ArrayList<>();

    public EmployeeDAO() {
    }

    public Employee add (Employee empl){
        employees.add(empl);
        return empl;
    }

    public void remove(Employee empl){
        employees.remove(empl);
    }

    public ArrayList<Employee> getAll (){
        return employees;
    }

    public ArrayList<Employee> employeesByProject(String projectName){
        ArrayList<Employee> result = new ArrayList<>();
        for(Employee e : employees){
            if(e != null) {
                for (Project project : e.getProjects()) {
                    if (project != null && project.getName() == projectName) {
                        result.add(e);
                    }
                }
            }
        }
        return result;
    }

    public ArrayList<Employee> employeesByDepartmentWithoutProject(DepartmentType depType){
        ArrayList<Employee> result = new ArrayList<>();

        for(Employee e : employees){
            if(e != null && e.getDepartment().getDepType() == depType && e.getProjects() == null){
                result.add(e);
            }
        }
        return result;
    }


    public ArrayList<Employee> employeesWithoutProject(){
        ArrayList<Employee> result = new ArrayList<>();

        for(Employee e : employees){
            if(e != null && e.getProjects() == null){
                result.add(e);
            }
        }
        return result;
    }

    public ArrayList<Employee> employeesByTeamLead(Employee lead){
        ArrayList<Employee> result = new ArrayList<>();
        //todo
        /**
        for(Employee e : employees){
            if(e != null && e.getPosition().equals(lead.getPosition())){
                result.add(e);
            }
        }
         */
        return result;
    }

    public ArrayList<Employee> employeesByProjectEmployee(Employee employee){
        ArrayList<Employee> result = new ArrayList<>();
        if(employee.getProjects() != null) {
            for (Project project : employee.getProjects()) {
                result.addAll(employeesByProject(project.getName()));
            }
        }
        return result;
    }

    public ArrayList<Employee> employeesByCustomerProjects(Customer customer){
        ArrayList<Employee> result = new ArrayList<>();

        for (Employee e : employees){
            if(e != null && e.getProjects() != null){
                for (Project project : e.getProjects()){
                    if (project != null && project.getCustomer().equals(customer)){
                        result.add(e);
                    }
                }
            }
        }

        return result;
    }

    public ArrayList<Employee> teamLeadsByEmployee(Employee employee){
        ArrayList<Employee> result = new ArrayList<>();

        if(employee.getProjects() != null){

        }

        return result;
    }
}
