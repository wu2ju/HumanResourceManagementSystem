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
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
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


    <form action="register" method="post">

        <a href=""><img class="login-logo" src="img/logo.png"></a>
        <div class="login-title default"></div>
        <div class="login-name">用户注册</div>
        <div class="login-form-area">

            <div class="login-input">
                <label for="">账&nbsp;&nbsp;&nbsp;号：</label>
                <input class="ob-form login-form-user" id="iName" name="uName" type="text">
            </div>
            <div class="login-input">
                <label for="">密&nbsp;&nbsp;&nbsp;码：</label>
                <input class="ob-form login-form-user" name="uPass" type="password">
            </div>
            <div class="login-input">
                <label for="">手&nbsp;&nbsp;&nbsp;机：</label>
                <input class="ob-form login-form-vc" name="uPhone" type="text">
            </div>
            <div class="login-input">
                <span style="color: red;">${str}</span>
            </div>
            <input class="btn order-food-menu-btn login-btn " type="submit" value="注册">

        </div>

    </form>

    <div class="login-footer clearfix"><img class="ybs" style="cursor: pointer;" src="img/login_jj.jpg" width="57" height="42" alt=""></div>

</body>
</html>
