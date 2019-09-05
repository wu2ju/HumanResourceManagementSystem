<%@ page import="java.util.List" %>
<%@ page import="com.wuju.model.Department" %>
<%@ page import="com.wuju.model.Position" %>
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
            //存储数据dpName，
            $("#chooseDep").click(function () {
                sessionStorage.removeItem("dpName");
                sessionStorage.clear();
                var dpName = $("#selDp").val();
                sessionStorage.setItem("dpName",dpName);
                alert("dpName = " + dpName);
            });
            $("a[id^='b']").click(function () {
                var dpName = sessionStorage.getItem("dpName");
                if (dpName != null){
                    $(this)[0].href += ("&dpName="+dpName);
                    $("input[name=dpName]").val(dpName);
                }
            });
            $("input[id^='b']").click(function () {
                var dpName = sessionStorage.getItem("dpName");
                if (dpName != null){
                    $("input[name=dpName]").val(dpName);
                }
            })
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

<div>
    <form action="ePosition" method="post">
        <select id="selDp" name="dpName">
            <option></option>
            <%
                List<Department> departments = (List<Department>) request.getAttribute("departments");
                for (Department department : departments) {
            %>
            <option><%=department.getDpName()%></option>
            <%
                }
            %>
        </select>
        <input id="chooseDep" type="submit" value="选择部门">
    </form>
</div>
<%--显示部门下所有职位--%>
<fieldset>
    <legend>职位</legend>
    <table>
        <tr>
            <th>名称</th>
            <th>薪资</th>
            <th>地址</th>
            <th>经验需求</th>
            <th>学历需求</th>
            <th>职位简介</th>
            <th>具体要求</th>
            <th>修改</th>
            <th>删除</th>
        </tr>
        <%
            Page<Position> positionPage = (Page<Position>) request.getAttribute("positionPage");
            if (positionPage.getList() != null && positionPage.getList().size() > 0){
                for (Position position : positionPage.getList()) {
        %>

        <tr>
            <td><%=position.getpName()%></td>
            <td><%=position.getpSalary()%></td>
            <td><%=position.getpLocation()%></td>
            <td><%=position.getpExperience()%></td>
            <td><%=position.getpEducation()%></td>
            <td><%=position.getpIntroduction()%></td>
            <td><%=position.getpRequest()%></td>
            <td><a href="eEmployee?pId=<%=position.getpId()%>">员工信息</a></td>
        </tr>
        <%
            }
            }
        %>
    </table>
</fieldset>

    <div class="div4">
        <span>共 <%=positionPage.getTotalPage()%> 页</span>
        <span>当前在第 <%=positionPage.getPageNo()%> 页</span>
        <span><a id="b1" class="aPageState" href="ePosition?pageNo=1">首页</a></span>
        <span><a id="b2" class="aPageState" href="ePosition?pageNo=<%=positionPage.getPrevPage()%>">上一页</a></span>
        <span><a id="b3" class="aPageState" href="ePosition?pageNo=<%=positionPage.getNextPage()%>">下一页</a></span>
        <span><a id="b4" class="aPageState" href="ePosition?pageNo=<%=positionPage.getTotalPage()%>">尾页</a></span>

        <form action="ePosition"  onsubmit="return checkNum(this.children[1].value)">
            <span>跳转到</span><input name="pageNo">
            <input type="hidden" name="dpName" value="">
            <input id="b5" type="submit" value="跳转">
        </form>
    </div>
</div>
</body>
</html>
