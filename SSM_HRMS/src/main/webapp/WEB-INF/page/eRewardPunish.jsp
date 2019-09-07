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
        $(function () {
            $("#addBtn").click(function () {
                var display = $("#div1").css("display");
                if (display == "none"){
                    $("#div1").css("display","block");
                }else {
                    $("#div1").css("display", "none");
                }
            });
            //存储数据itState，跳转页面的时候，记住选择的部门
            $("#selEmp").change(function () {
                sessionStorage.removeItem("eId");
                sessionStorage.clear();
                var eId = $(this).val();
                sessionStorage.setItem("eId",eId);
            });
            $("a[id^='b']").click(function () {
                var eId = sessionStorage.getItem("eId");
                if (eId != null){
                    $(this)[0].href += ("&eId="+eId);
                    $("input[name=eId]").val(eId);
                }
            });
            $("input[id^='b']").click(function () {
                var eId = sessionStorage.getItem("eId");
                if (eId != null){
                    $("input[name=eId]").val(eId);
                }
            })

            $("#selMon").change(function () {
                sessionStorage.removeItem("month");
                sessionStorage.clear();
                var month = $(this).val();
                sessionStorage.setItem("month",month);
            });
            $("a[id^='b']").click(function () {
                var month = sessionStorage.getItem("month");
                if (month != null){
                    $(this)[0].href += ("&month="+month);
                    $("input[name=month]").val(month);
                }
            });
            $("input[id^='b']").click(function () {
                var month = sessionStorage.getItem("month");
                if (month != null){
                    $("input[name=month]").val(month);
                }
            })
        })
    </script>


</head>
<body>
<%
    Employee e1 = (Employee) session.getAttribute("e");
    if (e1.geteType() == 1){
%>
<jsp:include page="employeeHead.jsp"/>
<%
}else {
%>
<jsp:include page="adminHead.jsp"/>
<%
    }
%>

<div id="main">
    <div style="-webkit-overflow-scrolling:touch;overflow:auto;height: 100%;position: absolute;z-index: 999" >
        <%
            if (e1.geteType() == 0){
        %>
        <input id="addBtn" type="button" value="添加奖惩">
        <div id="div1" style="display: none">
            <form action="addRewardPunish" method="post">
                奖惩时间：<input type="datetime-local" name="rpTime1"><br>
                奖惩理由：<input name="rpReason"><br>
                奖惩金额：<input name="rpMoney1"><br>
                <select name="eId">
                    <%
                        List<Employee> employees1 = (List<Employee>) request.getAttribute("employees");
                        if (employees1 != null && employees1.size() > 0){
                            for (Employee employee : employees1) {
                    %>
                    <option value="<%=employee.geteId()%>"><%=employee.geteAccount()%> <%=employee.geteName()%></option>
                    <%
                            }
                        }
                    %>
                </select>

                <input type="submit" value="确认添加">
            </form>
        </div>


        <form action="eRewardPunish" method="post">
            月份：
            <select id="selMon"  name="month">
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>
                <option>6</option>
                <option>7</option>
                <option>8</option>
                <option>9</option>
                <option>10</option>
                <option>11</option>
                <option>12</option>
            </select>
            <select id="selEmp" name="eId">
                <option></option>
                <%
                    if (employees1 != null && employees1.size() > 0){
                    for (Employee employee : employees1) {
                %>
                <option value="<%=employee.geteId()%>"><%=employee.geteAccount()%> <%=employee.geteName()%></option>
                <%
                    }
                    }
                %>
            </select>
            <input type="submit" value="确认月份和员工">
        </form>
        <%
            }
        %>
    <%--显示员工所有的奖惩信息--%>
    <td><p style="color: red">${str}</p></td>

<fieldset>
    <legend>所有奖惩记录</legend>
    <div class="table-wrapper pl27 " style="min-width:1000px;">
        <table class="table text-center">
        <tr>
            <th>时间</th>
            <th>原因</th>
            <th>金额</th>
        </tr>
        <%
            Page<RewardPunish> rewardPunishPage = (Page<RewardPunish>) request.getAttribute("rewardPunishPage");
            if (rewardPunishPage.getList() != null && rewardPunishPage.getList().size() > 0){
            for (RewardPunish rewardPunish : rewardPunishPage.getList()) {
                %>
        <tr>
            <form action="updateRewardPunish" method="post">
                <input type="hidden" name="rpId" value="<%=rewardPunish.getRpId()%>">
                <td><input type="datetime-local" name="rpTime1" value="<%=ControllerUtil.timestampToStr(rewardPunish.getRpTime())%>"></td>
                <td><input name="rpReason" value="<%=rewardPunish.getRpReason()%>"></td>
                <td><input name="rpMoney1" value="<%=rewardPunish.getRpMoney()%>"></td>
                <%
                    if (e1.geteType() == 0){
                        %>
                <td><input type="submit" value="修改"></td>
                <%
                    }
                %>
            </form>

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
        <span>共 <%=rewardPunishPage.getTotalPage()%> 页</span>
        <span>第 <%=rewardPunishPage.getPageNo()%> 页</span>
        <span><a id="b1" class="aPageState" href="eRewardPunish?pageNo=1">首页</a></span>
        <span><a id="b2" class="aPageState" href="eRewardPunish?pageNo=<%=rewardPunishPage.getPrevPage()%>">上一页</a></span>
        <span><a id="b3" class="aPageState" href="eRewardPunish?pageNo=<%=rewardPunishPage.getNextPage()%>">下一页</a></span>
        <span><a id="b4" class="aPageState" href="eRewardPunish?pageNo=<%=rewardPunishPage.getTotalPage()%>">尾页</a></span>
            </div>
            <div class="fr tb5" style="text-align: left;padding-right: 0px;position: absolute; right: 0; top: 0;">
        <form action="eRewardPunish"  onsubmit="return checkNum(this.children[1].value)">
            <span>跳转到</span><input style="width: 40px;height: 26px;" type="number" name="pageNo">
            <input type="hidden" name="eId" value="">
            <input type="hidden" name="month" value="1">
            <input id="b5" type="submit" value="跳转">
        </form>
            </div>

    </div>

</div>
</div>
</body>
</html>
