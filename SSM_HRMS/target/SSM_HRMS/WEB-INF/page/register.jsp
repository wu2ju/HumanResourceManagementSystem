<%--
  Created by IntelliJ IDEA.
  User: 吴炬
  Date: 2019/7/30
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>"/>
    <title>$</title>
    <script src="js/jquery-1.7.2.js"></script>
    <script>
        $(function () {
            //ajax
            $("#iName").blur(function () {
                var uName = $("#iName").val();
                $.get("registerName",{"uName":uName},function (obj) {
                    $("input[type=submit]")[0].removeAttribute("disabled");
                    // alert(obj);
                    if ("false" == obj){
                        $("input[type=submit]")[0].setAttribute("disabled","true");
                    }
                })
            });
        })
    </script>

</head>
<body>
<fieldset>
    <legend>用户注册</legend>
    <form action="register" method="post">
        账号：<input id="iName" name="uName"><br>
        密码：<input name="uPass"><br>
        电话：<input name="uPhone"><br>
        <input type="submit" value="注册">
    </form>
    <span style="color: red;">${str}</span>
</fieldset>

</body>
</html>
