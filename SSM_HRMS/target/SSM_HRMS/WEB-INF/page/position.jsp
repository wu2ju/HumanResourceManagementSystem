<%@ page import="java.util.List" %>
<%@ page import="com.wuju.model.Department" %>
<%@ page import="com.wuju.model.Position" %><%--
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
        var flag = <%=request.getAttribute("flag")%>;
        if (flag != null) {
            if (flag == true){
                alert("添加成功");
            }
            else {
                alert("添加失败");
            }
        }

        function delPosition(pId){
            var div2 = document.getElementById("div2");
            if (div2.style.display == "none"){
                // $("#pId").val(pId);
                var p = document.getElementById("pId");
                p.innerHTML = "<input name='pId' value='"+ pId +"'>" +
                    "        <input type='submit' value='确认删除'>";
                // document.getElementById("dpId")

                div2.style.display = "block";
            }else {
                div2.style.display = "none";
            }
        }

        $(function () {
            $("#addBtn").click(function () {
                var display = $("#div1").css("display");
                if (display == "none"){
                    $("#div1").css("display","block");
                }else {
                    $("#div1").css("display", "none");
                }
            });

            /*$("#delBtn").click(function () {
                var display = $("#div2").css("display");
                if (display == "none"){
                    $("#dpId").val($(this).val());
                    $("#div2").css("display","block");
                }else {
                    $("#div2").css("display", "none");
                }
            })*/
        })
    </script>
</head>
<body>
<jsp:include page="adminHead.jsp"/>
<div>

<input id="addBtn" type="button" value="添加职位">
<div id="div1" style="display: none">
    <form action="addPos" method="post">
        职位名称：<input name="pName"><br>
        薪资：<input name="pSalary"><br>
        地址：<input name="pLocation"><br>
        经验需求<input name="pExperience"><br>
        学历需求<input name="pEducation"><br>
        职位简介：<input name="pIntroduction"><br>
        具体需求：<input name="pRequest"><br>
        部门名称：<select name="dpName">
            <%
                List<Department> departments = (List<Department>) request.getAttribute("departments");
                for (Department department : departments) {
            %>
            <option><%=department.getDpName()%></option>
            <%
                }
            %>
        </select><br>
        <input type="submit" value="确认添加">
    </form>
</div>
<div id="div2" style="display: none">
    <form id="pId" action="delPos" method="post">

    </form>
</div>

<div>
    <form action="position" method="post">
        <select name="dpName">
            <%
                for (Department department : departments) {
            %>
            <option><%=department.getDpName()%></option>
            <%
                }
            %>
        </select>
        <input type="submit" value="选择部门">
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
            List<Position> positions = (List<Position>) request.getAttribute("positions");
            for (Position position : positions) {
        %>

        <tr>
            <form action="updatePos" method="post">
                <td><input type="hidden" name="pId" value="<%=position.getpId()%>"></td>
                <td><input type="hidden" name="pName" value="<%=position.getpName()%>"></td>
                <td><%=position.getpName()%></td>
                <td><input name="pSalary" value="<%=position.getpSalary()%>"></td>
                <td><input name="pLocation" value="<%=position.getpLocation()%>"></td>
                <td><input name="pExperience" value="<%=position.getpExperience()%>"></td>
                <td><input name="pEducation" value="<%=position.getpEducation()%>"></td>
                <td><input name="pIntroduction" value="<%=position.getpIntroduction()%>"></td>
                <td><input name="pRequest" value="<%=position.getpRequest()%>"></td>
                <td><input type="submit" value="修改"></td>
            </form>
            <%--<td><a href="delDep?dpId=<%=department.getDpId()%>">删除</a></td>--%>
            <td><input id="delBtn" type="button" value="删除" onclick="delPosition(<%=position.getpId()%>)"></td>
            <%--<td><button id="delBtn" type="button" value="删除" ></button></td>--%>
            <td><a href="employee?pId=<%=position.getpId()%>">员工信息</a></td>
            <td><p style="color: red">${str}</p></td>
        </tr>

        <%
            }
        %>
    </table>
</fieldset>

</div>
</body>
</html>
