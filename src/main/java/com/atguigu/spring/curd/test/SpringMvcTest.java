package com.atguigu.spring.curd.test;

import com.atguigu.spring.curd.dao.EmployeeDao;
import com.atguigu.spring.curd.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

@Controller
public class SpringMvcTest {
    @Autowired
    private EmployeeDao employeeDao;

    @ResponseBody
    @RequestMapping("/testJson")
    public Collection<Employee> testJson(){
        return employeeDao.getAll();
    }

    @RequestMapping("/testConversionServiceConvertor")
    public String testConvertor(@RequestParam("employee") Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }

}
