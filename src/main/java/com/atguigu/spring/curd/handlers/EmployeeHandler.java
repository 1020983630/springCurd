package com.atguigu.spring.curd.handlers;

import com.atguigu.spring.curd.dao.DepartmentDao;
import com.atguigu.spring.curd.dao.EmployeeDao;
import com.atguigu.spring.curd.entities.Employee;
import org.apache.taglibs.standard.lang.jstl.EmptyOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;

@Controller
public class EmployeeHandler {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

//    @InitBinder
//    public void initBinder(WebDataBinder binder){
//        binder.setDisallowedFields("lastName");
//    }


    @ModelAttribute
    public void getEmployee(@RequestParam(value = "id",required = false) Integer id,
                            Map<String,Object> map){
        if(id != null){
            map.put("employee",employeeDao.get(id));

        }
    }

    @RequestMapping(value = "/emp",method = RequestMethod.PUT)
    public String update(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @RequestMapping(value = "emp/{id}",method = RequestMethod.GET)
    public String input(@PathVariable("id") Integer id,Map<String,Object> map){
        map.put("employee",employeeDao.get(id));
        map.put("departments",departmentDao.getDepartments());
        return "input";
    }

    @RequestMapping(value = "emp/{id}",method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }

    @RequestMapping(value = "emp",method = RequestMethod.POST)
    public String save(@Valid Employee employee, BindingResult bs, Map<String,Object> map){
        employeeDao.save(employee);
        System.out.println(employee);
        if(bs.getErrorCount() > 0){
            for(FieldError error: bs.getFieldErrors()){
                System.out.println(error.getField()+":"+error.getDefaultMessage());
            }

            map.put("departments",departmentDao.getDepartments());
            return "input";
        }
        return "redirect:/emps";
    }

    @RequestMapping(value = "emp",method = RequestMethod.GET)
    public String input(Map<String,Object> map){
        map.put("departments",departmentDao.getDepartments());
        map.put("employee",new Employee());
        return "input";
    }

    @RequestMapping("emps")
    public String list(Map<String,Object> map){
        map.put("employees",employeeDao.getAll());
        return "list";
    }

}
