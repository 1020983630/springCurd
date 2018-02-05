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

    <a id="testJson" href="testJson">Test Json</a>
</body>
</html>
