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
            //存储数据dpName，跳转页面的时候，记住选择的部门
            $(".a0").click(function () {
                sessionStorage.removeItem("dpName");
                sessionStorage.clear();
                var dpName = $(this).name();
                sessionStorage.setItem("dpName",dpName);
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
        })
    </script>
</head>
<body>
<jsp:include page="recruitHead.jsp"/>
<div id="main">
    <div style="-webkit-overflow-scrolling:touch;overflow:auto;height: 100%;position: absolute;z-index: 999" >
    <div>
        <a class="a0" name="" href="recruitment">所有招聘信息</a>
        <%
            List<Department> departments = (List<Department>) request.getAttribute("departments");
            for (Department department : departments) {
        %>
        <a class="a0" name="<%=department.getDpName().replace("部","")%>" href="recruitment?dpName=<%=department.getDpName()%>"><%=department.getDpName()%></a>
        <%
            }
        %>
    </div>

    <%
        Notification nt = (Notification) session.getAttribute("nt");
        if (nt != null){
    %>
    <a href="userInterview" style="color: red">面试结果通知</a>
    <%
    }
    %>

    <%--显示部门下所有职位的招聘信息--%>
    <td><p style="color: red">${str}</p></td>
    <fieldset>
        <legend>招聘信息</legend>
        <div class="table-wrapper pl27 "  style="min-width:790px;">
            <table class="table text-center">
            <tr>
                <th>职位名称</th>
                <th>公司名称</th>
                <th>薪资</th>
                <th>地址</th>
                <th>经验要求</th>
                <th>学历要求</th>
                <th>发布时间</th>
                <th>截止时间</th>
            </tr>
            <%
                Page<Recruit> recruitPage = (Page<Recruit>) request.getAttribute("recruitPage");
                if (recruitPage.getList() != null && recruitPage.getList().size() > 0){
                    for (Recruit recruit : recruitPage.getList()) {
            %>
            <tr>
                <%--跳转到页面，职位，薪资，公司名，地址，经验，学历--%>
                <td><a href="recruitDetails?rcId=<%=recruit.getRcId()%>"><%=recruit.getPosition().getpName()%></a></td>
                    <td><%=recruit.getDepartment().getCompany().getCpName()%></td>
                    <td><%=recruit.getPosition().getpSalary()%></td>
                    <td><%=recruit.getPosition().getpLocation()%></td>
                    <td><%=recruit.getPosition().getpExperience()%></td>
                    <td><%=recruit.getPosition().getpEducation()%></td>
                    <td><%=recruit.getRcRelease()%></td>
                    <td><%=recruit.getRcDeadline()%></td>
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
        <span>共 <%=recruitPage.getTotalPage()%> 页</span>
        <span>第 <%=recruitPage.getPageNo()%> 页</span>
        <span><a id="b1" class="aPageState" href="recruitment?pageNo=1">首页</a></span>
        <span><a id="b2" class="aPageState" href="recruitment?pageNo=<%=recruitPage.getPrevPage()%>">上一页</a></span>
        <span><a id="b3" class="aPageState" href="recruitment?pageNo=<%=recruitPage.getNextPage()%>">下一页</a></span>
        <span><a id="b4" class="aPageState" href="recruitment?pageNo=<%=recruitPage.getTotalPage()%>">尾页</a></span>
            </div>
            <div class="fr tb5" style="text-align: left;padding-right: 0px;position: absolute; right: 0; top: 0;">
        <form action="recruitment"  onsubmit="return checkNum(this.children[1].value)">
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
