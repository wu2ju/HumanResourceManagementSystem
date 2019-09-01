<%@ page import="java.util.List" %>
<%@ page import="com.wuju.model.Department" %>
<%@ page import="com.wuju.model.Position" %>
<%@ page import="com.wuju.model.Page" %>
<%@ page import="com.wuju.model.Recruit" %><%--
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
        function delRecurit(rcId){
            var div2 = document.getElementById("div2");
            if (div2.style.display == "none"){
                var p = document.getElementById("rcId");
                p.innerHTML = "<input name='rcId' value='"+ rcId +"'>" +
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

            //存储数据dpName，跳转页面的时候，记住选择的部门
            $("a[id^='a']").click(function () {
                sessionStorage.removeItem("rcState");
                sessionStorage.clear();
                var rcState = $(this)[0].id[1];
                sessionStorage.setItem("rcState",rcState);
            });
            $("a[id^='b']").click(function () {
                var rcState = sessionStorage.getItem("rcState");
                if (rcState != null){
                    $(this)[0].href += ("&rcState="+rcState);
                    $("input[name=rcState]").val(rcState);
                }
            });
            $("input[id^='b']").click(function () {
                var rcState = sessionStorage.getItem("rcState");
                if (rcState != null){
                    $("input[name=rcState]").val(rcState);
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
            })
        })
    </script>


</head>
<body>
<jsp:include page="adminHead.jsp"/>
<div>

<input id="addBtn" type="button" value="添加招聘信息">
<div id="div1" style="display: none">
    <form action="addRecruit" method="post">
        <%--发布时间：<input type="hidden" name="rcRelease">--%>
        截止时间：<input type="date" name="rcDeadline"><br>
        <%--状态初始值为1：草稿：--%>
        <input type="hidden" name="rcState" value="1">
        <%--撤销时间：<input type="hidden" name="rcBackout">--%>
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
        </select><br>
        <input type="submit" value="确认添加">
    </form>
</div>
<div id="div2" style="display: none">
    <form id="rcId" action="delRecruit" method="post">
    </form>
</div>

<div>
    <a id="a0" href="recruit?rcState=0">所有招聘信息</a>
    <a id="a1" href="recruit?rcState=1" >待发布的招聘信息</a>
    <a id="a2" href="recruit?rcState=2" >发布的招聘信息</a>
    <a id="a3" href="recruit?rcState=3" >撤销的招聘信息</a>
</div>
<%--显示部门下所有职位的招聘信息--%>
    <td><p style="color: red">${str}</p></td>
<fieldset>
    <legend>招聘信息</legend>
    <table>
        <tr>
            <th>职位名称</th>
            <th>部门名称</th>
            <th>发布时间</th>
            <th>截止时间</th>
            <th>状态（1 草稿 2 发布 3 撤销）</th>
            <th>撤销时间</th>
            <th>修改</th>
            <th>删除</th>
        </tr>
        <%
            Page<Recruit> recruitPage = (Page<Recruit>) request.getAttribute("recruitPage");
            if (recruitPage.getList() != null && recruitPage.getList().size() > 0){
                for (Recruit recruit : recruitPage.getList()) {
                    if (recruit.getRcState() == 1){
        %>

        <tr>
            <form action="updateRecruit" method="post">
                <input type="hidden" name="rcId" value="<%=recruit.getRcId()%>">
                <td><%=recruit.getPosition().getpName()%></td>
                <td><%=recruit.getDepartment().getDpName()%></td>
                <td><%=recruit.getRcRelease()%></td>
                <td><input type="date" name="rcDeadline" value="<%=recruit.getRcDeadline()%>"></td>
                <td><%=recruit.getRcState()%></td>
                <td><%=recruit.getRcBackout()%></td>
                <td><input type="submit" value="修改"></td>
            </form>
            <td><a href="updateRcState?rcId=<%=recruit.getRcId()%>&rcState=2">发布</a></td>
            <%
            }
                else {
            %>
                <td><%=recruit.getPosition().getpName()%></td>
                <td><%=recruit.getDepartment().getDpName()%></td>
                <td><%=recruit.getRcRelease()%></td>
                <td><%=recruit.getRcDeadline()%></td>
                <td><%=recruit.getRcState()%></td>
                <td><%=recruit.getRcBackout()%></td>

            <%
                    if (recruit.getRcState() == 2){
                        %>
            <td><a href="updateRcState?rcId=<%=recruit.getRcId()%>&rcState=3">撤销</a></td>
            <%
                    }else {
                        %>
            <td><a href="updateRcState?rcId=<%=recruit.getRcId()%>&rcState=1">重新编辑</a></td>
            <%
                    }
                }
            %>


            <td><input id="delBtn" type="button" value="删除" onclick="delRecurit(<%=recruit.getRcId()%>)"></td>
            <%--<td><a href="employee?pId=<%=position.getpId()%>">投递的简历</a></td>--%>

        </tr>

        <%
            }
            }
        %>
    </table>
</fieldset>

    <div class="div4">
        <span>共 <%=recruitPage.getTotalPage()%> 页</span>
        <span>当前在第 <%=recruitPage.getPageNo()%> 页</span>
        <span><a id="b1" class="aPageState" href="recruit?pageNo=1">首页</a></span>
        <span><a id="b2" class="aPageState" href="recruit?pageNo=<%=recruitPage.getPrevPage()%>">上一页</a></span>
        <span><a id="b3" class="aPageState" href="recruit?pageNo=<%=recruitPage.getNextPage()%>">下一页</a></span>
        <span><a id="b4" class="aPageState" href="recruit?pageNo=<%=recruitPage.getTotalPage()%>">尾页</a></span>

        <form action="recruit"  onsubmit="return checkNum(this.children[1].value)">
            <span>跳转到</span><input name="pageNo">
            <input type="hidden" name="rcState" value="0">
            <input id="b5" type="submit" value="跳转">
        </form>

    </div>
</div>
</body>
</html>
