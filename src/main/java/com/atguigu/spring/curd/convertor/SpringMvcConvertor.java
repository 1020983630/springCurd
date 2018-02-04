package com.atguigu.spring.curd.convertor;

import com.atguigu.spring.curd.entities.Department;
import com.atguigu.spring.curd.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SpringMvcConvertor implements Converter<String,Employee> {
//    @Autowired
//    private Department department;

    public Employee convert(String s) {
        String[] args = s.split("-");
        if(args != null && args.length == 4){
            String lastName = args[0];
            String email = args[1];
            Integer gender = Integer.parseInt(args[2]);

            Department department = new Department();
            department.setId(Integer.parseInt(args[3]));

            Employee employee = new Employee(null,lastName,email,gender,department);
            return employee;
        }
        return null;
    }
}
