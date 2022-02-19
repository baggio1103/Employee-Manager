package javajedi.com.controller;

import javajedi.com.model.Employee;
import javajedi.com.service.EmployeeService;
import javajedi.com.service.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
@CrossOrigin("*")
public class EmployeeController {

    private final IEmployeeService service;

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> allEmployees = service.findAllEmployees();
        return new ResponseEntity<>(allEmployees, OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id){
        Employee employee = service.findEmployeeById(id);
        return new ResponseEntity<>(employee, OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        Employee newEmployee = service.addEmployee(employee);
        return new ResponseEntity<>(newEmployee, CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
        System.out.println(1);
        Employee updateEmployee = service.updateEmployee(employee);
        return new ResponseEntity<>(updateEmployee, OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id")Long id){
        service.deleteEmployee(id);
        return new ResponseEntity<>(OK);
    }

}
