<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: creator
  Date: 18-1-31
  Time: 下午9:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>新增员工</title>
</head>
<body>


    <form action="testConversionServiceConvertor" method="post">
        <input type="text" name="employee"/>
        <input type="submit" value="Submit"/>
    </form>

    <form:form action="/emp" method="post" modelAttribute="employee">
        <form:errors path="*"></form:errors>

        <c:if test="${employee.id == null}">
            LastName: <form:input path="lastName"/>
            <form:errors path="lastName"></form:errors>
        </c:if>
        <c:if test="${employee.id != null}">
            <form:hidden path="id"/>
            <input type="hidden" name="_method" value="PUT"/>
        </c:if>
        <br>
        Email: <form:input path="email"/>
        <form:errors path="email"></form:errors>
        <br>
        <%
            Map<String,String> genders = new HashMap<String,String>();
            genders.put("1","Male");
            genders.put("0","Female");
            request.setAttribute("genders",genders);
        %>
        Gender: <form:radiobuttons path="gender" items="${genders}"/>
        <br>
        Department: <form:select path="department.id" items="${departments}" itemLabel="departmentName" itemValue="id"/>
        <br>
        Birth: <form:input path="birth"/>
        <form:errors path="birth"></form:errors>
        <br>
        Salary: <form:input path="salary"/>
        <br>
        <input type="submit" value="Submit"/>
    </form:form>
</body>
</html>
