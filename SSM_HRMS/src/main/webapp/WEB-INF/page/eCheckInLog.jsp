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
    <%--<a href="eCheckIn">打卡</a>
    <a href="eCheckInLog">所有打卡记录</a>--%>
    <%
    }else {
        List<Employee> employees1 = (List<Employee>) request.getAttribute("employees");
        if(employees1!= null && employees1.size()>0){
    %>
    <jsp:include page="adminHead.jsp"/>

    <%--选择员工--%>
    <form action="eCheckInLog" method="post">
        <select id="selEmp" name="eId">
            <option></option>
            <%
                for (Employee employee : employees1) {
            %>
            <option value="<%=employee.geteId()%>"><%=employee.geteAccount()%> <%=employee.geteName()%></option>
            <%
                }
            %>
        </select>
        <input type="submit" value="选择员工">
    </form>
    <%
        }
        }
    %>
</div>

<div id="main">
    <div style="-webkit-overflow-scrolling:touch;overflow:auto;height: 100%;position: absolute;z-index: 999" >

    <%--显示部门下所有职位的招聘信息--%>
    <td><p style="color: red">${str}</p></td>

<fieldset>
    <legend>所有打卡记录</legend>
    <div class="table-wrapper pl27 " style="min-width:1000px;">
        <table class="table text-center">
        <tr>
            <th>上班时间</th>
            <th>下班时间</th>
        </tr>
        <%
            Page<CheckIn> checkInPage = (Page<CheckIn>) request.getAttribute("checkInPage");
            if (checkInPage.getList() != null && checkInPage.getList().size() > 0){
            for (CheckIn checkIn : checkInPage.getList()) {
                %>
        <tr>
            <td><%=checkIn.getCiAttendtime()%></td>
            <td><%=checkIn.getCiClosetime()%></td>
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
        <span>共 <%=checkInPage.getTotalPage()%> 页</span>
        <span>第 <%=checkInPage.getPageNo()%> 页</span>
        <span><a id="b1" class="aPageState" href="eCheckInLog?pageNo=1">首页</a></span>
        <span><a id="b2" class="aPageState" href="eCheckInLog?pageNo=<%=checkInPage.getPrevPage()%>">上一页</a></span>
        <span><a id="b3" class="aPageState" href="eCheckInLog?pageNo=<%=checkInPage.getNextPage()%>">下一页</a></span>
        <span><a id="b4" class="aPageState" href="eCheckInLog?pageNo=<%=checkInPage.getTotalPage()%>">尾页</a></span>
            </div>
            <div class="fr tb5" style="text-align: left;padding-right: 0px;position: absolute; right: 0; top: 0;">
        <form action="eCheckInLog"  onsubmit="return checkNum(this.children[1].value)">
            <span>跳转到</span><input style="width: 40px;height: 26px;" type="number" name="pageNo">
            <input type="hidden" name="eId" value="">
            <input id="b5" type="submit" value="跳转">
        </form>
            </div>

    </div>

</div>
</div>
</body>
</html>
