<%@ page import="java.util.List" %>
<%@ page import="com.wuju.model.*" %><%--
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
        $("#choosePos").click(function () {
            sessionStorage.removeItem("pName");
            sessionStorage.clear();
            var pName = $("#selPos").val();
            sessionStorage.setItem("pName",pName);
        });
        $("a[id^='b']").click(function () {
            var pName = sessionStorage.getItem("pName");
            if (pName != null){
                $(this)[0].href += ("&pName="+pName);
                $("input[name=pName]").val(pName);
            }
        });
        $("input[id^='b']").click(function () {
            var pName = sessionStorage.getItem("pName");
            if (pName != null){
                $("input[name=pName]").val(pName);
            }
        })

        //    ajax实现二级联动
            $("#selDep").change(function () {
                var dpName = $(this).val();
                $.ajax({
                    type:"post",
                    url:"getPositionByDpName",
                    data:{"dpName":dpName},
                    success:function (obj) {
                        //返回为列表，列表里是List<Position>
                        var tableHtml = "";
                        for (var i in obj){
                            tableHtml += ("<option>"+ obj[i] +"</option>");
                        }
                        $("#selPos").html(tableHtml);
                    }
                })
            })
        })
    </script>


</head>
<body>
<jsp:include page="adminHead.jsp"/>
<jsp:include page="eHead.jsp"/>
<div>

<div id="div1">
    部门名称：<select id="selDep" name="dpName">
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
    职位名称：
    <form action="eEmployee" method="post">
        <select id="selPos" name="pName"></select>
        <input id="choosePos" type="submit" value="选择职位">
    </form>

    <br>
</div>


<fieldset>
    <legend>招聘信息</legend>
    <table>
        <tr>
            <th>名字</th>
            <th>性别</th>
            <th>出生日期</th>
            <th>电话</th>
            <th>邮箱</th>
        </tr>
        <%
            Page<Employee> employeePage = (Page<Employee>) request.getAttribute("employeePage");
            if (employeePage.getList() != null && employeePage.getList().size() > 0){
                for (Employee employee : employeePage.getList()) {
                    if (employee.geteId() != 1){
        %>

        <tr>
                <td><%=employee.geteName()%></td>
                <td><%=employee.geteSex()%></td>
                <td><%=employee.geteBirthday()%></td>
                <td><%=employee.getePhone()%></td>
                <td><%=employee.geteEmail()%></td>
        </tr>
        <%
                    }
                }
            }
        %>
    </table>
</fieldset>

    <div class="div4">
        <span>共 <%=employeePage.getTotalPage()%> 页</span>
        <span>当前在第 <%=employeePage.getPageNo()%> 页</span>
        <span><a id="b1" class="aPageState" href="eEmployee?pageNo=1">首页</a></span>
        <span><a id="b2" class="aPageState" href="eEmployee?pageNo=<%=employeePage.getPrevPage()%>">上一页</a></span>
        <span><a id="b3" class="aPageState" href="eEmployee?pageNo=<%=employeePage.getNextPage()%>">下一页</a></span>
        <span><a id="b4" class="aPageState" href="eEmployee?pageNo=<%=employeePage.getTotalPage()%>">尾页</a></span>

        <form action="eEmployee"  onsubmit="return checkNum(this.children[1].value)">
            <span>跳转到</span><input name="pageNo">
            <input type="hidden" name="pName" value="">
            <input id="b5" type="submit" value="跳转">
        </form>

    </div>
</div>
</body>
</html>
