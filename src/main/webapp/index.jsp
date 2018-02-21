<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#testJson").click(function () {
                var url = this.href;
                var args = {};
                $.post(url,args,function (data) {
                    for(var i=0;i<data.length;i++){
                        var id = data[i].id;
                        var lastName = data[i].lastName;
                        alert(id + ":" + lastName);
                    }
                });
                return false;
            });
        })
    </script>
</head>
<body>
<h2>Hello World!</h2>

    <a href="emps">List All Employees</a>

    <br>
    <a id="testJson" href="testJson">Test Json</a>

    <br><br>
    <form action="testHttpMessageConverter" method="post" enctype="multipart/form-data">
        File: <input type="file" name="file"/>
        Desc: <input type="text" name="desc"/>
        <input type="submit" value="Submit"/>
    </form>

    <br><br>
    <a href="testResponseEntity">Test ResponseEntity</a>

    <br>
    <a href="i18n">i18n page</a>

    <br><br>
    <form action="testFileUpload" method="post" enctype="multipart/form-data">
        File: <input type="file" name="file"/>
        Desc: <input type="text" name="desc"/>
        <input type="submit" value="Submit"/>
    </form>

    <br>
    <a href="testExceprionHandlerExceptionResoulver?i=0">Test ExceprionHandlerExceptionResoulver</a>

    <br><br>
    <a href="testResponseStatusExceptionResolver?i=10">Test ResponseStatusExceptionResolver</a>

    <br><br>
    <a href="testSimpleMappingExceptionResolver?i=10">Test SimpleMappingExceptionResolver</a>

</body>
</html>
