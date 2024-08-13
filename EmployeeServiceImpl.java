package backend.smart.project;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl  implements EmployeeService{
    
    @Autowired
   private  EmployeeRepository employeeRepository;
   // List <Employee> employees=new ArrayList<>();

    @Override
    public String createEmployee(Employee employee) {

        EmployeeEntity employeeEntity =new EmployeeEntity();
        BeanUtils.copyProperties(employee, employeeEntity);
     //  employees.add(employee);
         employeeRepository.save(employeeEntity);
       return "saved successfully";
    }



    @Override
    public Employee readEmployee(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
     
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeEntity,employee);

        return employee;
    }
    @Override
    public List<Employee> readEmployees() {
        List<EmployeeEntity> employeesList = employeeRepository.findAll();
        List<Employee> employees= new ArrayList<>();
   //    for(int i=0;i<employeesList.size();i++){
        for (EmployeeEntity employeeEntity : employeesList) {
            
            Employee emp = new Employee();
            emp.setId(employeeEntity.getId());
            emp.setName(employeeEntity.getName());
            emp.setDescription(employeeEntity.getDescription());
            emp.setEmail(employeeEntity.getEmail());
            
          
            employees.add(emp);
        }
        return employees;     
    }

        
    
    @Override
    public boolean deleteEmployee(Long id) {
         EmployeeEntity emp = employeeRepository.findById(id).get();
        employeeRepository.delete(emp);
        return true;
    }

    @Override
    public String updateEmployee(Long id, Employee employee) {
        EmployeeEntity exestingEmployee = employeeRepository.findById(id).get();
        
        exestingEmployee.setName(employee.getName());
        exestingEmployee.setEmail(employee.getEmail());
        exestingEmployee.setDescription(employee.getDescription());

        employeeRepository.save(exestingEmployee);
        
       return "Update Succesfully";
    }




   
}
