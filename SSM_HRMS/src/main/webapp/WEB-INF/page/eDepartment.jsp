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
    <a href="eDepartment">部门信息</a>
    <a href="ePosition">职位信息</a>
    <a href="eEmployee">员工信息</a>
    <%
    }
    %>
</div>

<div>

<%--显示所有部门--%>
<fieldset>
    <legend>部门</legend>
    <table>
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
</fieldset>

    <div class="div4">
        <span>共 <%=departmentPage.getTotalPage()%> 页</span>
        <span>当前在第 <%=departmentPage.getPageNo()%> 页</span>
        <span><a href="eDepartment?pageNo=1">首页</a></span>
        <span><a href="eDepartment?pageNo=<%=departmentPage.getPrevPage()%>">上一页</a></span>
        <span><a href="eDepartment?pageNo=<%=departmentPage.getNextPage()%>">下一页</a></span>
        <span><a href="eDepartment?pageNo=<%=departmentPage.getTotalPage()%>">尾页</a></span>

        <form action="eDepartment"  onsubmit="return checkNum(this.children[1].value)">
            <span>跳转到</span><input name="pageNo">
            <input type="submit" value="跳转">
        </form>

    </div>
</div>

</body>
</html>
