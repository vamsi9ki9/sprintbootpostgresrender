package com.luv2code.springboot.cruddemo.thymeleafdemo.Controller;


import com.luv2code.springboot.cruddemo.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.cruddemo.thymeleafdemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String listEmployeees(Model theModel){
//get the employyes for the DB.
        System.out.println("employees/list");
List<Employee> theEmployees=employeeService.findAll();
theModel.addAttribute("employees",theEmployees);
     return   "employees/list-employees";
    }


    @GetMapping("/showFormForAdd")
    public String AddEmployee(Model theModel){
        System.out.println("employees/showFormforadd");
        Employee emp=new Employee();
        theModel.addAttribute("employee",emp);
        return   "employees/showFormForAdd";
    }


    @PostMapping("/save")
    public String AddEmployee( @ModelAttribute("employee") Employee theemployee,Model theModel){
        System.out.println("employees/save");
        theModel.addAttribute("employee",employeeService.save(theemployee));
        return   "redirect:/employees/list";
    }

    @GetMapping("/showFormForUpdate")
    public String UpdateEmployee(@RequestParam ("employeeId") int theID, Model theModel){
        System.out.println("employees/showformupdate");
        theModel.addAttribute("employee",employeeService.findById(theID));
        return   "employees/showFormForAdd";
    }


    @GetMapping("/showFormForDelete")
    public String DeleteEmployee(@RequestParam ("employeeId") int theID){
        System.out.println("employees/showformfordeleyte");
        employeeService.deleteById(theID);
     //   theModel.addAttribute("",);;
        return   "redirect:/employees/list";
    }

}
