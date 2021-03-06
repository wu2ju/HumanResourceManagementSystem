<%--
  Created by IntelliJ IDEA.
  User: 吴炬
  Date: 2019/8/22
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<head>
    <base href="<%=basePath%>"/>
    <title>Company Homepage</title>
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link type="text/css" rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="css/base.css">
    <link type="text/css" rel="stylesheet" href="css/frame.css">
    <link type="text/css" rel="stylesheet" href="css/addClass.css">
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
        #divBackground{
            background-image: url("img/PD1902.png");
            width: 1200px;
            height: 500px;
        }
    </style>
</head>
<body>
<!-- common-head -->
<div class="common-head clearfix">
    <a class="logo" href="">
        <img src="img/logo.png" alt="">
    </a>
    <div class="info clearfix">
        <a href="">站点首页</a> |
        <a href="">更新首页缓存</a>
    </div>
    <div class="right pull-right text-right" id="hovpad">
        <dl class="user-wrapper">
            <dt><span class="time">欢迎您</span>${e.eAccount} ${e.eName} ${u.uName}<img src="img/headjt.png" alt=""><img class="active" src="img/headjt-active.png" alt=""></dt>
        </dl>
        <dl class="msg-wrapper">
            <dt class="clearfix"><img src="img/msg.png" alt=""><img class="active" src="img/msg-active.png" alt=""><span> 消息 </span><span class="tag"></span></dt>
            <dd>
                <a href="">交易通知：<span>123</span></a>
                <a href="">系统通知：<span>123</span></a>
                <a href="">系统消息：<span>123</span></a>
            </dd>
        </dl>
    </div>
</div>

<jsp:include page="homepageHead.jsp"/>

<div id="divBackground">

</div>
</body>
<script src="js/jquery-1.11.3.js"></script>


<script>
    //首次加载至url
/*
    var u = window.location.href.split('#')[1];
    $('#main iframe').attr('src',u);
    $('.side-bar dd a[href="#'+ $('#main iframe').attr('src') + '"]').addClass('active');

*/

    //侧栏菜单
    $(function() {
        $('.side-bar dt').click(function() {
            var dd = $(this).siblings('dd');
            dd.slideToggle();
            $(this).find('.b').toggle()
            $(this).find('.r').toggle()
        });
        /*$('.side-bar dd a').click(function() {
            $('.side-bar dd a').removeClass('active');
            $(this).addClass('active');
            //页面显示控制
            var url = $(this).attr('href').substring(1);
            var f = $('#main iframe');
            f.attr('src', url);
        });*/
    });

    $('#hovpad dl').on('click', function(e) {
        var dd = $(this).find('dd');
        if (dd.css('display') === 'none') {
            dd.show();
            $(this).addClass('active');
        } else {
            dd.hide();
            $(this).removeClass('active');
        }
        e.stopPropagation();
    });
    $('#hovpad dl').hover(function(e) {
        var dd = $(this).find('dd');
        dd.show();
        $(this).addClass('active');
    }, function(e) {
        var dd = $(this).find('dd');
        dd.hide();
        $(this).removeClass('active');
    })
    $('body').on('click', function() {
        $('#hovpad dl').removeClass('active');
    })


</script>

</html>
