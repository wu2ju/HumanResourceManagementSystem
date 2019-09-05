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
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>后台管理系统</title>
    <meta name="keywords" content="后台管理系统">
    <meta name="description" content="后台管理系统">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/frame.css">
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="css/merchant.css">
    <link rel="stylesheet" href="css/addClass.css">
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
</head>
<body>
<form action="login" method="post">
    <a href=""><img class="login-logo" src="img/logo.png"></a>
    <div class="login-title default"></div>
    <div class="login-name">员工登录</div>
    <div class="login-form-area">

        <div class="login-input">
            <label for="">账&nbsp;&nbsp;&nbsp;号：</label>
            <input class="ob-form login-form-user" type="text" name="eAccount">
        </div>
        <div class="login-input">
            <label for="">密&nbsp;&nbsp;&nbsp;码：</label>
            <input class="ob-form login-form-user" type="password" name="ePassword">
        </div>
        <div class="login-input">
            <label for="">手&nbsp;&nbsp;&nbsp;机：</label>
            <input class="ob-form login-form-vc" type="text">
        </div>
        <div class="login-input">
            <input type="checkbox" name="remember" value="yes">是否记住密码<br>
            <p style="color: red" class="errorInfo">${str}</p>
        </div>
        <input class="btn order-food-menu-btn login-btn " type="submit" value="登录">
    </div>
    <div class="login-footer clearfix"><img class="ybs" style="cursor: pointer;" src="img/login_jj.jpg" width="57" height="42" alt=""></div>

</form>

</body>
</html>
