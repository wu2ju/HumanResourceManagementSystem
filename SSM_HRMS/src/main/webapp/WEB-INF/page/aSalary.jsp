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
        <div id="div1">
            <form action="addSalary" method="post">
                <input type="submit" value="结算上个月薪资">
            </form>
        </div>
        <form action="aSalary" method="post">
            <select id="selEmp" name="eId">
                <option></option>
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
            <input type="submit" value="确认员工">
        </form>
        <%
            }
        %>
    <%--显示员工所有的奖惩信息--%>
    <td><p style="color: red">${str}</p></td>

<fieldset>
    <legend>所有薪资记录</legend>
    <div class="table-wrapper pl27 " style="min-width:1000px;">
        <table class="table text-center">
        <tr>
            <th>员工</th>
            <th>基本薪资</th>
            <th>奖金</th>
            <th>奖惩</th>
            <th>社保</th>
            <th>结算日期</th>
            <th>总计</th>
            <th>实际工资</th>
        </tr>
        <%
            Page<Salary> salaryPage = (Page<Salary>) request.getAttribute("salaryPage");
            if (salaryPage.getList() != null && salaryPage.getList().size() > 0){
            for (Salary salary : salaryPage.getList()) {
                %>
        <tr>
            <td><%=salary.getEmployee().geteName()%></td>
            <td><%=salary.getSlBase()%></td>
            <td><%=salary.getSlBonus()%></td>
            <td><%=salary.getSlRp()%></td>
            <td><%=salary.getSlSecurity()%></td>
            <td><%=salary.getSlDate()%></td>
            <td><%=salary.getSlTotal()%></td>
            <td><%=salary.getSlReal()%></td>
            <%
                if (e1.geteType() == 1){
                    %>
            <form action="updateSalary" method="post">
                <input type="hidden" name="slId" value="<%=salary.getSlId()%>">
                <td><input type="submit" value="复议"></td>
            </form>

            <%
                }else {
                    if (salary.getSlState() == 2){
                        %>
            <td>复议</td>
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
    </div>
</fieldset>

        <div class="div4" style="width: 400px; position: absolute; left: 30%; margin-left: -120px;">
            <div class="fl tb3" style="width: 350px;float: left;">
        <span>共 <%=salaryPage.getTotalPage()%> 页</span>
        <span>第 <%=salaryPage.getPageNo()%> 页</span>
        <span><a id="b1" class="aPageState" href="aSalary?pageNo=1">首页</a></span>
        <span><a id="b2" class="aPageState" href="aSalary?pageNo=<%=salaryPage.getPrevPage()%>">上一页</a></span>
        <span><a id="b3" class="aPageState" href="aSalary?pageNo=<%=salaryPage.getNextPage()%>">下一页</a></span>
        <span><a id="b4" class="aPageState" href="aSalary?pageNo=<%=salaryPage.getTotalPage()%>">尾页</a></span>
            </div>
            <div class="fr tb5" style="text-align: left;padding-right: 0px;position: absolute; right: 0; top: 0;">
        <form action="aSalary"  onsubmit="return checkNum(this.children[1].value)">
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
