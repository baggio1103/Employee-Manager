package javajedi.com.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javajedi.com.exception.UserNotFoundException;
import javajedi.com.model.Employee;
import javajedi.com.repository.EmployeeRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService{

    private final EmployeeRepository repository;

    public Employee addEmployee(Employee employee){
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return repository.save(employee);
    }

    public List<Employee> findAllEmployees(){
        return repository.findAll();
    }

    public Employee updateEmployee(Employee employee){
        return repository.save(employee);
    }

    public void deleteEmployee(Long id){
        repository.deleteById(id);
    }

    public Employee findEmployeeById(Long id){
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found by id : " + id));
    }

}
