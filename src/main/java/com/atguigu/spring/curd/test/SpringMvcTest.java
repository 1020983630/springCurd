package com.atguigu.spring.curd.test;

import com.atguigu.spring.curd.dao.EmployeeDao;
import com.atguigu.spring.curd.entities.Employee;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

@Controller
public class SpringMvcTest {
    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private ResourceBundleMessageSource messageSource;

    @RequestMapping("testSimpleMappingExceptionResolver")
    public String testSimpleMappingExceptionResolver(@RequestParam("i") int i){
        String[] vals = new String[10];
        System.out.println(vals[i]);
        return "success";
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "测试")
    @RequestMapping("/testResponseStatusExceptionResolver")
    public String testResponseStatusExceptionResolver(@RequestParam("i") int i){
        if(i == 13){
            throw new UserNameNotMatchPasswordException();
        }else {
            System.out.println("testResponseStatusExceptionResolver...");
        }
        return "success";
    }

//    @ExceptionHandler(RuntimeException.class)
//    public ModelAndView handlerArithmeticException2(Exception ex){
//        System.out.println("出异常了2：" + ex);
//        ModelAndView mv = new ModelAndView("error");
//        mv.addObject("exception",ex);
//        return mv;
//    }

//    @ExceptionHandler(ArithmeticException.class)
//    public ModelAndView handlerArithmeticException(Exception ex){
//        System.out.println("出异常了：" + ex);
//        ModelAndView mv = new ModelAndView("error");
//        mv.addObject("exception",ex);
//        return mv;
//    }

    @RequestMapping("/testExceprionHandlerExceptionResoulver")
    public String testExceprionHandlerExceptionResoulver(@RequestParam("i") int i){
        System.out.println("result" + 10/i);
        return "success";
    }

    @RequestMapping("/testFileUpload")
    public String testFileUpload(@RequestParam("desc") String desc,
                                 @RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("desc: " + desc);
        System.out.println("originalFilename: " + file.getOriginalFilename());
        System.out.println("inputStream: " + file.getInputStream());

        return "success";

    }

    @RequestMapping("i18n")
    public String testi18n(Locale locale){
        final String message = messageSource.getMessage("i18n.user", null, locale);
        System.out.println(message);
        return "i18n";
    }

    @RequestMapping("/testResponseEntity")
    public ResponseEntity<byte[]> testResponseEntity(HttpSession httpSession) throws IOException {
        byte[] body = null;
        ServletContext context = httpSession.getServletContext();
        InputStream in = context.getResourceAsStream("/files/abc.txt");
        body = new byte[in.available()];
        in.read(body);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition","atachment;filename=abc.txt");

        HttpStatus statusCode = HttpStatus.OK;

        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(body,headers,statusCode);
        return response;
    }

    @ResponseBody
    @RequestMapping("testHttpMessageConverter")
    public String testHttpMessageConverter(@RequestBody String body){
        System.out.println(body);
        return "helloWorld!" + new Date();
    }

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
