package javajedi.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import javajedi.com.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
}
