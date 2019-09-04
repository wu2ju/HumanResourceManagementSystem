<%@ page import="java.util.List" %>
<%@ page import="com.wuju.model.*" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.wuju.util.ControllerUtil" %><%--
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
        function delTrain(trId){
            var div2 = document.getElementById("div2");
            if (div2.style.display == "none"){
                var p = document.getElementById("trId");
                p.innerHTML = "<input name='trId' value='"+ trId +"'>" +
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
            });

            $(".addEmp1").click(function () {
                sessionStorage.removeItem("trId");
                sessionStorage.clear();
                var trId = $(this)[0].name;
                // alert("addEmp: " + trId);
                sessionStorage.setItem("trId",trId);
                var display = $("#div3").css("display");
                if (display == "none"){
                    $("#div3").css("display","block");
                }else {
                    $("#div3").css("display", "none");
                }
            });


        //    ajax实现三级联动，选择部门，选择职位，选择职位下的员工
            $("#selDep").change(function () {
                var dpName = $(this).val();
                $("#selEmp").html("");
                $.ajax({
                    type:"post",
                    url:"getPositionByDpName",
                    data:{"dpName":dpName},
                    success:function (obj) {
                        //返回为列表，列表里是List<Position.pName>
                        var tableHtml = "<option></option>";
                        for (var i in obj){
                            tableHtml += ("<option>"+ obj[i] +"</option>");
                        }
                        $("#selPos").html(tableHtml);
                    }
                })
            });
            $("#selPos").change(function () {
                var pName = $(this).val();
                $.ajax({
                    type:"post",
                    url:"getEmployeeBypName",
                    data:{"pName":pName},
                    success:function (obj) {
                        //返回为列表，列表里是List<Employee.eName>
                        var tableHtml = "<option></option>";
                        for (var i in obj){
                            tableHtml += ("<option>"+ obj[i] +"</option>");
                        }
                        $("#selEmp").html(tableHtml);
                    }
                })
            });

            //    ajax实现三级联动，选择部门，选择职位，选择职位下的员工
            $("#selDep1").change(function () {
                var dpName = $(this).val();
                $("#selEmp1").html("");
                $.ajax({
                    type:"post",
                    url:"getPositionByDpName",
                    data:{"dpName":dpName},
                    success:function (obj) {
                        //返回为列表，列表里是List<Position.pName>
                        var tableHtml = "<option></option>";
                        for (var i in obj){
                            tableHtml += ("<option>"+ obj[i] +"</option>");
                        }
                        $("#selPos1").html(tableHtml);
                    }
                })
            });
            $("#selPos1").change(function () {
                var pName = $(this).val();
                $.ajax({
                    type:"post",
                    url:"getEmployeeBypName",
                    data:{"pName":pName},
                    success:function (obj) {
                        //返回为列表，列表里是List<Employee.eName>
                        var tableHtml = "";
                        for (var i in obj){
                            tableHtml += ("<option>"+ obj[i] +"</option>");
                        }
                        $("#selEmp1").html(tableHtml);
                    }
                })
            });

            $("#addEmp2").click(function () {
                var eAccount = $("#selEmp1").val();
                var pName = $("#selPos1").val();
                var dpName = $("#selDep1").val();
                var trId = sessionStorage.getItem("trId");
                $.ajax({
                    type:"post",
                    url:"addEmployeeToTrain",
                    data:{"eAccount":eAccount,"trId":trId},
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
<jsp:include page="adminHead.jsp"/>
<a href="aTrain">培训</a>
<a href="eTrain">所有培训记录</a>
<div>

<input id="addBtn" type="button" value="添加培训">
<div id="div1" style="display: none">
    <form action="addTrain" method="post">
        主题：<input name="trTheme"><br>
        具体内容：<textarea name="trContent"></textarea><br>
        开始时间：<input type="datetime-local" name="trBegin1"><br>
        结束时间：<input type="datetime-local" name="trEnd1"><br>
        <input type="hidden" name="trState" value="1">
        <%--发布时间：<input type="hidden" name="trRelease">--%>
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
        职位名称：<select id="selPos" name="pName">
            <option></option>
        </select>
        员工名字：<select id="selEmp" name="eAccount">
            <option></option>
        </select><br>
        <input type="submit" value="确认添加">
    </form>
</div>
<div id="div2" style="display: none">
    <form id="trId" action="delTrain" method="post">
    </form>
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
            <option></option>
        </select>
            员工名字：<select id="selEmp1" name="eAccount">
            <option></option>
        </select><br>
        <input id="addEmp2" type="button" value="添加该培训对象">
    </div>
<%--显示部门下所有职位的招聘信息--%>
    <td><p style="color: red">${str}</p></td>
<fieldset>
    <legend>培训信息</legend>
    <table>
        <tr>
            <th>主题</th>
            <th>具体内容</th>
            <th>开始时间</th>
            <th>结束时间</th>
            <th>状态（1 草稿 2 发布 3 结束）</th>
            <th>发布时间</th>
            <th>培训对象</th>
            <th>修改</th>
            <th>删除</th>
        </tr>
        <%
            Page<Train> trainPage = (Page<Train>) request.getAttribute("trainPage");
            if (trainPage.getList() != null && trainPage.getList().size() > 0){
                for (Train train : trainPage.getList()) {
                    if (train.getTrState() == 1){
        %>
        <tr>
            <form action="updateTrain" method="post">
                <input type="hidden" name="trId" value="<%=train.getTrId()%>">
                <td><input name="trTheme" value="<%=train.getTrTheme()%>"></td>
                <td><textarea name="trContent"><%=train.getTrContent()%></textarea></td>
                <td><input type="datetime-local" name="trBegin1" value="<%=ControllerUtil.timestampToStr(train.getTrBegin())%>"></td>
                <td><input type="datetime-local" name="trEnd1" value="<%=ControllerUtil.timestampToStr(train.getTrEnd())%>"></td>
                <td><%=train.getTrState()%></td>
                <td><input type="submit" value="修改"></td>
            </form>
            <td><input class="addEmp1" name="<%=train.getTrId()%>" type="button" value="添加培训对象"></td>
            <td>
                <dl>
                    <%
                        List<Employee> employees = train.getEmployees();
                        if (employees != null && employees.size() > 0){
                        for (Employee e : employees) {
                            %>
                    <dd><a href="delTrainObject?eId=<%=e.geteId()%>&trId=<%=train.getTrId()%>">手机号：<%=e.geteAccount()%> 姓名：<%=e.geteName()%></a></dd>
                    <%
                        }
                        }
                    %>
                </dl>
            </td>
            <td><a href="updateTrState?trId=<%=train.getTrId()%>&trState=2">发布</a></td>
            <%
            } else if (train.getTrState() == 2 ){
            %>
                <td><%=train.getTrTheme()%></td>
                <td><%=train.getTrContent()%></td>
                <td><%=train.getTrBegin()%></td>
                <td><%=train.getTrEnd()%></td>
                <td><%=train.getTrState()%></td>
                <td><%=train.getTrRelease()%></td>
            <td>
                <dl>
                    <%
                        List<Employee> employees = train.getEmployees();
                        if (employees != null && employees.size() > 0){
                            for (Employee e : employees) {
                    %>
                    <dd>手机号：<%=e.geteAccount()%> 姓名：<%=e.geteName()%></dd>
                    <%
                            }
                        }
                    %>
                </dl>
            </td>
            <%
            } else if (train.getTrState() == 3){
            %>
                <td><%=train.getTrTheme()%></td>
                <td><%=train.getTrContent()%></td>
                <td><%=train.getTrBegin()%></td>
                <td><%=train.getTrEnd()%></td>
                <td><%=train.getTrState()%></td>
                <td><%=train.getTrRelease()%></td>
            <td>
                <dl>
                    <%
                        List<Employee> employees = train.getEmployees();
                        if (employees != null && employees.size() > 0){
                            for (Employee e : employees) {
                    %>
                    <dd>手机号：<%=e.geteAccount()%> 姓名：<%=e.geteName()%></dd>
                    <%
                            }
                        }
                    %>
                </dl>
            </td>
            <%
                if (train.getTrEnd().before(new Timestamp(new Date().getTime()))){
            %>
            <td><input id="delBtn" type="button" value="删除" onclick="delTrain(<%=train.getTrId()%>)"></td>
            <%
                    }
                }
            %>
        </tr>

        <%
            }
            }
        %>
    </table>
</fieldset>

    <div class="div4">
        <span>共 <%=trainPage.getTotalPage()%> 页</span>
        <span>当前在第 <%=trainPage.getPageNo()%> 页</span>
        <span><a id="b1" class="aPageState" href="aTrain?pageNo=1">首页</a></span>
        <span><a id="b2" class="aPageState" href="aTrain?pageNo=<%=trainPage.getPrevPage()%>">上一页</a></span>
        <span><a id="b3" class="aPageState" href="aTrain?pageNo=<%=trainPage.getNextPage()%>">下一页</a></span>
        <span><a id="b4" class="aPageState" href="aTrain?pageNo=<%=trainPage.getTotalPage()%>">尾页</a></span>

        <form action="aTrain"  onsubmit="return checkNum(this.children[1].value)">
            <span>跳转到</span><input name="pageNo">
            <input type="hidden" name="dpName" value="">
            <input id="b5" type="submit" value="跳转">
        </form>

    </div>
</div>
</body>
</html>
