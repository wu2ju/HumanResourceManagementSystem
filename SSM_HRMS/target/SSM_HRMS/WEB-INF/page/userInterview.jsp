<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Set" %>
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

    </script>


</head>
<body>
<%--<%@include file="recruitHead.jsp"%>--%>
<jsp:include page="recruitHead.jsp"/>
<div>
    <%--显示投递简历之后的面试消息--%>
    <p style="color: red">${str}</p>
    <fieldset>
        <legend>招聘信息</legend>
        <table>
            <tr>
                <th>职位名称</th>
                <th>投简历者</th>
                <th>投递时间</th>
                <th>面试状态</th>
                <th>面试时间</th>
                <th>面试地址</th>
                <th>面试官</th>
                <th>操作</th>
            </tr>
            <%
                Page<Interview> interviewPage = (Page<Interview>) request.getAttribute("interviewPage");
                if (interviewPage.getList() != null && interviewPage.getList().size() > 0){
                    for (Interview interview : interviewPage.getList()) {
                        if (interview.getItState() == 0 || interview.getItState() == 1){
            %>
            <tr>
                <td><%=interview.getRecruit().getPosition().getpName()%></td>
                <td><%=interview.getResumeForIV().getrName()%></td>
                <td><%=interview.getItDeliveryResume()%></td>
                <td><%=interview.getItState()%> 等待面试通知</td>
            </tr>
            <%
                } else if (interview.getItState() == 2){
                          %>
            <tr>
                <td><%=interview.getRecruit().getPosition().getpName()%></td>
                <td><%=interview.getResumeForIV().getrName()%></td>
                <td><%=interview.getItDeliveryResume()%></td>
                <td><%=interview.getItState()%> 通知面试</td>
                <td><%=interview.getItTime()%></td>
                <td><%=interview.getItAddress()%></td>
                <td><%=interview.getEmployee().geteName()%></td>
                <td><a href="updateInterview?itId=<%=interview.getItId()%>&itState=3">接受面试</a></td>
                <td><a href="updateInterview?itId=<%=interview.getItId()%>&itState=4">拒绝面试</a></td>
            </tr>
            <%
                }else if (interview.getItState() == 3){
            %>
            <tr>
                <td><%=interview.getRecruit().getPosition().getpName()%></td>
                <td><%=interview.getResumeForIV().getrName()%></td>
                <td><%=interview.getItDeliveryResume()%></td>
                <td><%=interview.getItState()%> 等待面试结果</td>
                <td><%=interview.getItTime()%></td>
                <td><%=interview.getItAddress()%></td>
                <td><%=interview.getEmployee().geteName()%></td>
            </tr>
            <%
                }else if (interview.getItState() == 4){
            %>
            <tr>
                <td><%=interview.getRecruit().getPosition().getpName()%></td>
                <td><%=interview.getResumeForIV().getrName()%></td>
                <td><%=interview.getItDeliveryResume()%></td>
                <td><%=interview.getItState()%> 拒绝面试</td>
                <td><%=interview.getItTime()%></td>
                <td><%=interview.getItAddress()%></td>
                <td><%=interview.getEmployee().geteName()%></td>
            </tr>
            <%
                }else if (interview.getItState() == 5){
            %>
            <tr>
                <td><%=interview.getRecruit().getPosition().getpName()%></td>
                <td><%=interview.getResumeForIV().getrName()%></td>
                <td><%=interview.getItDeliveryResume()%></td>
                <td><%=interview.getItState()%> 面试结果</td>
                <td><%=interview.getItTime()%></td>
                <td><%=interview.getItAddress()%></td>
                <td><%=interview.getEmployee().geteName()%></td>
                <td>面试通过，明天入职</td>
            </tr>
            <%
            }else if (interview.getItState() == 6){
            %>
            <tr>
                <td><%=interview.getRecruit().getPosition().getpName()%></td>
                <td><%=interview.getResumeForIV().getrName()%></td>
                <td><%=interview.getItDeliveryResume()%></td>
                <td><%=interview.getItState()%> 面试结果</td>
                <td><%=interview.getItTime()%></td>
                <td><%=interview.getItAddress()%></td>
                <td><%=interview.getEmployee().geteName()%></td>
                <td>面试没通过</td>
            </tr>
            <%
                        }
                    }
                }
            %>
        </table>
    </fieldset>

    <div class="div4">
        <span>共 <%=interviewPage.getTotalPage()%> 页</span>
        <span>当前在第 <%=interviewPage.getPageNo()%> 页</span>
        <span><a id="b1" class="aPageState" href="userInterview?pageNo=1">首页</a></span>
        <span><a id="b2" class="aPageState" href="userInterview?pageNo=<%=interviewPage.getPrevPage()%>">上一页</a></span>
        <span><a id="b3" class="aPageState" href="userInterview?pageNo=<%=interviewPage.getNextPage()%>">下一页</a></span>
        <span><a id="b4" class="aPageState" href="userInterview?pageNo=<%=interviewPage.getTotalPage()%>">尾页</a></span>

        <form action="userInterview"  onsubmit="return checkNum(this.children[1].value)">
            <span>跳转到</span><input name="pageNo">
            <input type="hidden" name="dpName" value="">
            <input id="b5" type="submit" value="跳转">
        </form>

    </div>
</div>
</body>
</html>
