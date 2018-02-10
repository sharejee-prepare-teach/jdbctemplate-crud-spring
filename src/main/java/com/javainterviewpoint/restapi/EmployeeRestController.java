package com.javainterviewpoint.restapi;

import com.javainterviewpoint.models.Employee;
import com.javainterviewpoint.repository.EmployeeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.List;

/**
 * Created by : Ron Rith
 * Create Date: 02/04/2018.
 */
@RestController
@RequestMapping(value = "/api/employees")
public class EmployeeRestController {
    @Autowired
    private EmployeeDAO employeeDAO;

    @RequestMapping(method = RequestMethod.GET)
    public List<Employee> getAllEmployees() {
        List<Employee> employees = (List<Employee>) employeeDAO.getAllEmployees();
        if (employees != null) {
            return employees;
        }
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Employee getById(@PathVariable("id") int id) {
        Employee employee = (Employee) employeeDAO.getEmployeeById(id);
        return employee;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void saveEmployee(@RequestBody @Valid Employee employee) {
        if (employee != null) {
            employeeDAO.saveEmployee(employee);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateEmployee(@RequestBody @Valid Employee employee, @PathVariable("id") int id) {
        if (employee != null) {
            employee.setId(id);
            employeeDAO.updateEmployee(employee);
        }
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") int id) {
        Employee employee = (Employee) employeeDAO.getEmployeeById(id);
        if (employee != null) {
            employeeDAO.deleteEmployee(id);
            return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
    }
}
