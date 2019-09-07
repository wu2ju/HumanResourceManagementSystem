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
<div id="main">
    <div style="-webkit-overflow-scrolling:touch;overflow:auto;height: 100%;position: absolute;z-index: 999" >
    <fieldset>
    <legend>公司信息</legend>
        <div class="table-wrapper pl27 " style="min-width:1000px;">
            <table class="table text-center">
                <form action="companyInfo" method="post">
                <tr>
                    <td>公司名称：</td>
                    <td><input name="cpName" value="${company.cpName}"></td>
                </tr>
                    <tr>
                        <td>联系电话：</td>
                        <td><input name="cpPhone" value="${company.cpPhone}"></td>
                    </tr>
                    <tr>
                        <td>办公地址：</td>
                        <td><input name="cpAddress" value="${company.cpAddress}"></td>
                    </tr>
                    <tr>
                        <td>创立时间：</td>
                        <td><input type="date" name="cpEstablish" value="${company.cpEstablish}"></td>
                    </tr>
                    <tr>
                        <td>员工人数：</td>
                        <td><input name="cpStaffsize" value="${company.cpStaffsize}"></td>
                    </tr>
                    <tr>
                        <td>所属行业：</td>
                        <td><input name="cpIndustry" value="${company.cpIndustry}"></td>
                    </tr>
                    <tr>
                        <td>介绍：</td>
                        <td><input type="text" name="cpIntroduction" value="${company.cpIntroduction}"></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="完善公司信息"></td>
                    </tr>
    </form>
            </table>
        </div>

</fieldset>

    </div>
</div>

</body>
</html>
