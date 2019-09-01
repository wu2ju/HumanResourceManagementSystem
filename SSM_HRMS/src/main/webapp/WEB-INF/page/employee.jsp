<%--
  Created by IntelliJ IDEA.
  User: 吴炬
  Date: 2019/8/23
  Time: 15:06
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
<jsp:include page="employeeHead.jsp"/>
<div>
    <fieldset>
        <legend>个人信息</legend>
        <table>
            <tr>
                <th>名字</th>
                <th>性别</th>
                <th>出生日期</th>
                <th>电话</th>
                <th>邮箱</th>
                <th>状态（0 试用期，1 入职， 2 离职）</th>
                <th>入职时间</th>
                <th>转正时间</th>
            </tr>

            <tr>
                <td>${employee.eName}</td>
                <td>${employee.eSex}</td>
                <td>${employee.eBirthday}</td>
                <td>${employee.ePhone}</td>
                <td>${employee.eEmail}</td>
                <td>${employee.eState}</td>
                <td>${employee.eEntry}</td>
                <td>${employee.eBefull}</td>
            </tr>

        </table>
    </fieldset>
</div>
</body>
</html>
