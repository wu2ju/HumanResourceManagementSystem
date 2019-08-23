<%--
  Created by IntelliJ IDEA.
  User: 吴炬
  Date: 2019/8/22
  Time: 15:31
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
    <title>Company Homepage</title>
</head>
<body>
<%@include file="homepageHead.jsp" %>
</body>
</html>
