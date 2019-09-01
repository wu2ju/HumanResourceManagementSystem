<%--
  Created by IntelliJ IDEA.
  User: 吴炬
  Date: 2019/8/22
  Time: 15:37
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

<div>
    <a href="index.jsp">公司主页</a>
    <a href="recruitment">招聘信息</a>
    <a href="firstRegister">注册</a>
    <a href="userHasCookie">登录</a>
    <a href="resume">完善简历</a>
    <a href="userInterview">面试信息</a>
</div>

</body>
</html>
