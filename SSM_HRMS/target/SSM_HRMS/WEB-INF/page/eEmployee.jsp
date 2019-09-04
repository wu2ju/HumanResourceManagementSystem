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
        });

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
            });

            $(".changePos").click(function () {
                sessionStorage.removeItem("eId");
                sessionStorage.clear();
                var eId = $(this)[0].name;
                sessionStorage.setItem("eId",eId);
                var display = $("#div3").css("display");
                if (display == "none"){
                    $("#div3").css("display","block");
                }else {
                    $("#div3").css("display", "none");
                }
            });

            $(".leavePos").click(function () {
                sessionStorage.removeItem("eId2");
                sessionStorage.clear();
                var eId2 = $(this)[0].name;
                sessionStorage.setItem("eId2",eId2);
                var display = $("#div5").css("display");
                if (display == "none"){
                    $("#div5").css("display","block");
                }else {
                    $("#div5").css("display", "none");
                }
            });

            //    ajax实现二级联动，选择部门，选择职位
            $("#selDep1").change(function () {
                var dpName = $(this).val();
                $("#selEmp1").html("");
                $.ajax({
                    type:"post",
                    url:"getPositionByDpName",
                    data:{"dpName":dpName},
                    success:function (obj) {
                        //返回为列表，列表里是List<Position.pName>
                        var tableHtml = "";
                        for (var i in obj){
                            tableHtml += ("<option>"+ obj[i] +"</option>");
                        }
                        $("#selPos1").html(tableHtml);
                    }
                })
            });

            $("#changePos2").click(function () {
                var pName = $("#selPos1").val();
                var dpName = $("#selDep1").val();
                var eId = sessionStorage.getItem("eId");
                $.ajax({
                    type:"post",
                    url:"changeEmployeePosition",
                    data:{"pName":pName,"eId":eId},
                    success:function (obj) {
                        //将该账号的员工添加到培训对象中
                        alert(obj);
                    }
                })
            });

            $("#leavePos2").click(function () {
                var qtReason = $("#qtReason").val();
                var eId2 = sessionStorage.getItem("eId2");
                $.ajax({
                    type:"post",
                    url:"retireEmployee",
                    data:{"qtReason":qtReason,"eId":eId2},
                    success:function (obj) {
                        //将该账号的员工添加到培训对象中
                        alert(obj);
                    }
                })
            });
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
        }else {
            %>
    <jsp:include page="adminHead.jsp"/>
    <%
        }
    %>
</div>

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

    <div id="div3" style="display: none">
        部门名称：<select id="selDep1" name="dpName">
        <option></option>
        <%
            for (Department department : departments) {
        %>
        <option><%=department.getDpName()%></option>
        <%
            }
        %>
    </select>
        职位名称：<select id="selPos1" name="pName">
    </select>
    </select><br>
        <input id="changePos2" type="button" value="确定职位">
    </div>

    <div id="div5" style="display: none">
        下岗原因：<textarea id="qtReason"></textarea>
        <input id="leavePos2" type="button" value="确定该员工下岗">
    </div>


<fieldset>
    <legend>员工信息</legend>
    <table>
        <tr>
            <th>名字</th>
            <th>性别</th>
            <th>出生日期</th>
            <th>电话</th>
            <th>邮箱</th>
            <th>状态(0 试用期 1 正式职工 2 离职)</th>
            <th>操作</th>
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
            <td><%=employee.geteState()%></td>

            <%
                if (e1.geteType() == 0 ){
                    if (employee.geteState() != 2){
                        %>
            <td><input class="changePos" name="<%=employee.geteId()%>" type="button" value="换岗"></td>
            <td><input class="leavePos" name="<%=employee.geteId()%>" type="button" value="下岗"></td>
            <%
                    }
                    %>
            <td><a href="eTrain?eId=<%=employee.geteId()%>">培训</a></td>
            <td><a href="eCheckInLog?eId=<%=employee.geteId()%>">考勤</a></td>
            <td><a href="eTrain?eId=<%=employee.geteId()%>">薪资</a></td>
            <%
                }
            %>

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
