<%--
  Created by IntelliJ IDEA.
  User: 吴炬
  Date: 2019/8/23
  Time: 16:49
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
<%--管理员--%>
<a href="companyInfo">公司信息</a>
<a href="">部门管理</a>
<a href="">职位管理</a>
<a href="">员工管理</a>
<a href="">薪资结算</a>
<a href="">培训</a>
<a href="">招聘</a>
</body>
</html>
