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
<div>
    <a href="companyInfo?cpName=IOTEK">公司信息</a>
    <a href="department">部门管理</a>
    <a href="position">职位管理</a>
    <a href="eEmployee">员工管理</a>
    <a href="">薪资结算</a>
    <a href="aTrain">培训</a>
    <a href="eCheckInLog">考勤</a>
    <a href="recruit">招聘</a>
    <a href="adminInterview">面试</a>
</div>

</body>
</html>
