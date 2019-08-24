<%--
  Created by IntelliJ IDEA.
  User: 吴炬
  Date: 2019/8/23
  Time: 19:14
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
<jsp:include page="adminHead.jsp"/>
<%--${company}--%>
<fieldset>
    <legend>公司信息</legend>
    <form action="companyInfo" method="post">
        名称：<input name="cpName" value="${company.cpName}"><br>
        电话：<input name="cpPhone" value="${company.cpPhone}"><br>
        地址：<input name="cpAddress" value="${company.cpAddress}"><br>
        创立时间：<input type="date" name="cpEstablish" value="${company.cpEstablish}"><br>
        员工人数：<input name="cpStaffsize" value="${company.cpStaffsize}"><br>
        所属行业：<input name="cpIndustry" value="${company.cpIndustry}"><br>
        介绍：<input name="cpIntroduction" value="${company.cpIntroduction}"><br>
        <input type="submit" value="完善公司信息">
    </form>
</fieldset>



</body>
</html>
