package io.reactapp.SpringReactBackEnd.Controller;

import io.reactapp.SpringReactBackEnd.Exception.ResourceNotFound;
import io.reactapp.SpringReactBackEnd.Model.Employee;
import io.reactapp.SpringReactBackEnd.Repository.EmployeeRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.module.ResolutionException;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin("*")
@RestController
@RequestMapping("/")
public class EmployeeController {

//    private final EmployeeRepository employeeRepository;

//    public EmployeeController(EmployeeRepository employeeRepository) {
//        this.employeeRepository = employeeRepository;
//    }

    @Autowired
    private EmployeeRepository employeeRepository;


    @GetMapping("/allemployees")
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }
    
    @PostMapping ("/addemployee")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    @GetMapping("/getemployee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable  Long id){
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Employee with id " + id + " not found"));
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/updateemployee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
        Employee updatedEmployee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Employee with id " + id + " not found"));
        updatedEmployee.setFirstName(employeeDetails.getFirstName());
        updatedEmployee.setLastName(employeeDetails.getLastName());
        updatedEmployee.setEmailId(employeeDetails.getEmailId());
        employeeRepository.save(updatedEmployee);
        return ResponseEntity.ok(updatedEmployee);
    }


    @DeleteMapping("/deleteemployee/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id){
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Employee with id " + id + " not found"));
        employeeRepository.delete(employee);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
