<%--
  Created by IntelliJ IDEA.
  User: 吴炬
  Date: 2019/8/31
  Time: 22:02
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
    <a href="employee">个人信息</a>
    <a href="eDepartment">通讯录</a>
    <a href="eTrain">培训</a>
    <a href="eRewardPunish">奖惩</a>
    <a href="eCheckInLog">考勤打卡</a>
    <a href="">薪资</a>
</div>
</body>
</html>
