<%--
  Created by IntelliJ IDEA.
  User: 吴炬
  Date: 2019/8/23
  Time: 15:05
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
<%--管理员功能--%>
<%--<%@include file="adminHead.jsp"%>--%>
<jsp:include page="adminHead.jsp"/>
<div id="main">
    <div style="-webkit-overflow-scrolling:touch;overflow:auto;height: 100%;position: absolute;z-index: 999" >
    </div>
</div>
</body>
</html>
