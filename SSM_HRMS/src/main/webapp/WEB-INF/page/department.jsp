<%@ page import="java.util.List" %>
<%@ page import="com.wuju.model.Department" %>
<%@ page import="com.wuju.model.Page" %><%--
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

        function delDepart(dpId){
            var div2 = document.getElementById("div2");
            if (div2.style.display == "none"){
                // $("#dpId").val(dpId);
                // document.getElementById("dpId").valueOf(dpId);
                var dp = document.getElementById("dpId");
                dp.innerHTML = "<input name='dpId' value='"+ dpId +"'>" +
                    "        <input type='submit' value='确认删除'>";
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
            })

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
<div id="main">
    <div style="-webkit-overflow-scrolling:touch;overflow:auto;height: 100%;position: absolute;z-index: 999" >


    <input id="addBtn" type="button" value="添加部门">
<div id="div1" style="display: none">
    <form action="addDep" method="post">
        <input name="dpName">
        <input type="submit" value="确认添加">
    </form>
</div>
<div id="div2" style="display: none">
    <form id="dpId" action="delDep" method="post">

    </form>
</div>
    <td><p style="color: red">${str}</p></td>
<%--显示所有部门--%>
<fieldset>
    <legend>部门</legend>
    <div class="table-wrapper pl27 " style="min-width:1000px;">
        <table class="table text-center">
        <tr>
            <th>名称</th>
            <th>成立时间</th>
            <th>修改</th>
            <th>删除</th>
        </tr>
        <%
//            List<Department> departments = (List<Department>) request.getAttribute("departments");
            Page<Department> departmentPage = (Page<Department>) request.getAttribute("departmentPage");
            if(departmentPage.getList()!=null && departmentPage.getList().size()!=0){
                for (Department department : departmentPage.getList()) {
        %>
        <tr>
            <form action="updateDep" method="post">
                <input type="hidden" name="dpId" value="<%=department.getDpId()%>">
                <input type="hidden" name="dpName" value="<%=department.getDpName()%>">
                <td><%=department.getDpName()%></td>
                <td><%=department.getDpEstablish()%></td>
                <td><input type="submit" value="修改"></td>
            </form>
            <%--<td><a href="delDep?dpId=<%=department.getDpId()%>">删除</a></td>--%>
            <td><input id="delBtn" type="button" onclick="delDepart(<%=department.getDpId()%>)" value="删除"></td>
            <%--<td><button id="delBtn" type="button" value="<%=department.getDpId()%>">删除</button></td>--%>
            <td><a href="position?dpId=<%=department.getDpId()%>">职位信息</a></td>

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
        <span><a href="department?pageNo=1">首页</a></span>
        <span><a href="department?pageNo=<%=departmentPage.getPrevPage()%>">上一页</a></span>
        <span><a href="department?pageNo=<%=departmentPage.getNextPage()%>">下一页</a></span>
        <span><a href="department?pageNo=<%=departmentPage.getTotalPage()%>">尾页</a></span>
            </div>
            <div class="fr tb5" style="text-align: left;padding-right: 0px;position: absolute; right: 0; top: 0;">
        <form action="department"  onsubmit="return checkNum(this.children[1].value)">
            <span>跳转到</span><input style="width: 40px;height: 26px;" type="number" name="pageNo">
            <input type="submit" value="跳转">
        </form>
            </div>

    </div>
</div>
</div>

</body>
</html>
