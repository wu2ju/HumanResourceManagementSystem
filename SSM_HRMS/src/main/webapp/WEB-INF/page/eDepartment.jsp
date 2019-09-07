<%@ page import="java.util.List" %>
<%@ page import="com.wuju.model.Department" %>
<%@ page import="com.wuju.model.Page" %>
<%@ page import="com.wuju.model.Employee" %><%--
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
        $(function () {
        })
    </script>
</head>
<body>
<div>
    <%
        Employee e1 = (Employee) session.getAttribute("e");
        if (e1.geteType() == 1){
    %>
    <jsp:include page="employeeHead.jsp"/>
    <%
    }
    %>
</div>

<div id="main">
    <div style="-webkit-overflow-scrolling:touch;overflow:auto;height: 100%;position: absolute;z-index: 999" >


    <%--显示所有部门--%>
<fieldset>
    <legend>部门</legend>
    <div class="table-wrapper pl27 " style="min-width:1000px;">
        <table class="table text-center">
        <tr>
            <th>名称</th>
            <th>成立时间</th>
            <th>部门下的职位</th>
        </tr>
        <%
            Page<Department> departmentPage = (Page<Department>) request.getAttribute("departmentPage");
            if(departmentPage.getList()!=null && departmentPage.getList().size()!=0){
                for (Department department : departmentPage.getList()) {
        %>
        <tr>
            <td><%=department.getDpName()%></td>
            <td><%=department.getDpEstablish()%></td>
            <td><a href="ePosition?dpId=<%=department.getDpId()%>">职位信息</a></td>
        </tr>
        <%
            }
            }
        %>
    </table>
    </div>
</fieldset>

        <div class="div4" style="width: 400px; position: absolute; left: 30%; margin-left: -120px;">
            <div class="fl tb3" style="width: 350px;float: left;">
        <span>共 <%=departmentPage.getTotalPage()%> 页</span>
        <span>第 <%=departmentPage.getPageNo()%> 页</span>
        <span><a href="eDepartment?pageNo=1">首页</a></span>
        <span><a href="eDepartment?pageNo=<%=departmentPage.getPrevPage()%>">上一页</a></span>
        <span><a href="eDepartment?pageNo=<%=departmentPage.getNextPage()%>">下一页</a></span>
        <span><a href="eDepartment?pageNo=<%=departmentPage.getTotalPage()%>">尾页</a></span>
            </div>
            <div class="fr tb5" style="text-align: left;padding-right: 0px;position: absolute; right: 0; top: 0;">
        <form action="eDepartment"  onsubmit="return checkNum(this.children[1].value)">
            <span>跳转到</span><input style="width: 40px;height: 26px;" type="number" name="pageNo">
            <input type="submit" value="跳转">
        </form>
            </div>

    </div>
</div>
</div>

</body>
</html>
