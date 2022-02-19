package javajedi.com.service;

import javajedi.com.model.Employee;

import java.util.List;

public interface IEmployeeService {

    Employee addEmployee(Employee employee);

    List<Employee> findAllEmployees();

    Employee updateEmployee(Employee employee);

    void deleteEmployee(Long id);

    Employee findEmployeeById(Long id);

}
