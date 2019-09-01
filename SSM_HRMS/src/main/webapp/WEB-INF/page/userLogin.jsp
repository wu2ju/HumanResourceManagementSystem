<%--
  Created by IntelliJ IDEA.
  User: 吴炬
  Date: 2019/8/23
  Time: 14:52
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
</head>
<body>
<form action="userLogin" method="post">
    <input name="uName"><br>
    <input name="uPass"><br>
    <input type="checkbox" name="remember" value="yes">是否记住密码<br>
    <input type="submit" value="登录">
</form>
<%--<p style="color: red"><%=request.getAttribute("str")%></p>--%>
<p style="color: red">${str}</p>


</body>
</html>
