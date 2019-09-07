<%@ page import="java.util.List" %>
<%@ page import="com.wuju.model.Department" %>
<%@ page import="com.wuju.model.Position" %>
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

<input id="addBtn" type="button" value="添加职位">
<div id="div1" style="display: none">
    <form action="addPos" method="post">
        职位名称：<input name="pName"><br>
        入职薪资：<input name="pSalary"><br>
        工作地点：<input name="pLocation"><br>
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
        <select id="selDp" name="dpName">
            <option></option>
            <%
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
    <td><p style="color: red">${str}</p></td>
<%--显示部门下所有职位--%>
<fieldset>
    <legend>职位</legend>
    <div class="table-wrapper pl27 " style="min-width:1000px;">
        <table class="table text-center">
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
//            List<Position> positions = (List<Position>) request.getAttribute("positions");
            Page<Position> positionPage = (Page<Position>) request.getAttribute("positionPage");
            if (positionPage.getList() != null && positionPage.getList().size() > 0){
                for (Position position : positionPage.getList()) {
        %>

        <tr>
            <form action="updatePos" method="post">
                <input type="hidden" name="pId" value="<%=position.getpId()%>">
                <input type="hidden" name="pName" value="<%=position.getpName()%>">
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
            <td><a href="eEmployee?pId=<%=position.getpId()%>">员工信息</a></td>

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
        <span>共 <%=positionPage.getTotalPage()%> 页</span>
        <span>第 <%=positionPage.getPageNo()%> 页</span>
        <span><a id="b1" class="aPageState" href="position?pageNo=1">首页</a></span>
        <span><a id="b2" class="aPageState" href="position?pageNo=<%=positionPage.getPrevPage()%>">上一页</a></span>
        <span><a id="b3" class="aPageState" href="position?pageNo=<%=positionPage.getNextPage()%>">下一页</a></span>
        <span><a id="b4" class="aPageState" href="position?pageNo=<%=positionPage.getTotalPage()%>">尾页</a></span>
            </div>
            <div class="fr tb5" style="text-align: left;padding-right: 0px;position: absolute; right: 0; top: 0;">
        <form action="position"  onsubmit="return checkNum(this.children[1].value)">
            <span>跳转到</span><input style="width: 40px;height: 26px;" type="number" name="pageNo">
            <input type="hidden" name="dpName" value="">
            <input id="b5" type="submit" value="跳转">
        </form>
            </div>

    </div>
</div>
</div>
</body>
</html>
