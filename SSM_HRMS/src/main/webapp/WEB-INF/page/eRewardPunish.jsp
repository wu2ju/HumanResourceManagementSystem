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
    </script>


</head>
<body>
<jsp:include page="employeeHead.jsp"/>
<div>

<%--显示员工所有的奖惩信息--%>
    <td><p style="color: red">${str}</p></td>

<fieldset>
    <legend>所有奖惩记录</legend>
    <table>
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
            <td><%=rewardPunish.getRpTime()%></td>
            <td><%=rewardPunish.getRpReason()%></td>
            <td><%=rewardPunish.getRpMoney()%></td>
        </tr>
        <%
            }
            }
        %>

    </table>

</fieldset>
    <div class="div4">
        <span>共 <%=rewardPunishPage.getTotalPage()%> 页</span>
        <span>当前在第 <%=rewardPunishPage.getPageNo()%> 页</span>
        <span><a id="b1" class="aPageState" href="eRewardPunish?pageNo=1">首页</a></span>
        <span><a id="b2" class="aPageState" href="eRewardPunish?pageNo=<%=rewardPunishPage.getPrevPage()%>">上一页</a></span>
        <span><a id="b3" class="aPageState" href="eRewardPunish?pageNo=<%=rewardPunishPage.getNextPage()%>">下一页</a></span>
        <span><a id="b4" class="aPageState" href="eRewardPunish?pageNo=<%=rewardPunishPage.getTotalPage()%>">尾页</a></span>

        <form action="eRewardPunish"  onsubmit="return checkNum(this.children[1].value)">
            <span>跳转到</span><input name="pageNo">
            <input type="hidden" name="dpName" value="">
            <input id="b5" type="submit" value="跳转">
        </form>

    </div>

</div>
</body>
</html>
