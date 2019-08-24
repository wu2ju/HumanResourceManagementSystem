<%--
  Created by IntelliJ IDEA.
  User: 吴炬
  Date: 2019/8/22
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<head>
    <base href="<%=basePath%>"/>
    <title>Company Homepage</title>
</head>
<body>
<%@include file="homepageHead.jsp" %>
<<a href="hasCookie">aaaa</a>
</body>
</html>
