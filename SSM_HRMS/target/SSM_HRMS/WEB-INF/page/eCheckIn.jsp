<%@ page import="java.util.List" %>
<%@ page import="com.wuju.model.*" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.wuju.util.ControllerUtil" %><%--
  Created by IntelliJ IDEA.
  User: 吴炬
  Date: 2019/8/27
  Time: 11:40
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
    <script src="js/jquery-1.7.2.js"></script>
    <script>
    </script>


</head>
<body>
<jsp:include page="employeeHead.jsp"/>
<div>
    <a href="eCheckIn">打卡</a>
    <a href="eCheckInLog">所有打卡记录</a>

<%--显示部门下所有职位的招聘信息--%>
    <td><p style="color: red">${str}</p></td>

<fieldset>
    <legend>今日打卡记录</legend>
    <table>
        <tr>
            <th>上班时间</th>
            <th>下班时间</th>
        </tr>
        <tr>
            <td>${checkIn.ciAttendtime.getTime()<1 ? "未打上班卡" : checkIn.ciAttendtime}</td>
            <td>${checkIn.ciClosetime.getTime()<1 ? "未打下班卡" : checkIn.ciClosetime}</td>
        </tr>
    </table>
    <form action="eCheckIn" method="post">
        <input type="hidden" name="method" value="attend">
        <td><input type="submit" value="上班打卡"></td>
    </form>
    <form action="eCheckIn" method="post">
        <input type="hidden" name="method" value="close">
        <td><input type="submit" value="下班打卡"></td>
    </form>
</fieldset>

</div>
</body>
</html>
