<%@ page import="java.util.List" %>
<%@ page import="com.wuju.model.Department" %>
<%@ page import="com.wuju.model.Page" %>
<%@ page import="com.wuju.model.Train" %>
<%@ page import="com.wuju.model.Employee" %><%--
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
    <%
    }else {
        List<Employee> employees1 = (List<Employee>) request.getAttribute("employees");
        if(employees1!= null && employees1.size()>0){
    %>

    <jsp:include page="adminHead.jsp"/>
    <a href="aTrain">培训</a>
    <a href="eTrain">所有培训记录</a>

    <%--选择员工--%>
    <form action="eTrain" method="post">
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

<div>

<%--显示所有发布的培训--%>
<fieldset>
    <legend>培训</legend>
    <table>
        <tr>
            <th>主题</th>
            <th>具体内容</th>
            <th>开始时间</th>
            <th>结束时间</th>
            <th>状态（1 草稿 2 发布）</th>
            <th>发布时间</th>
            <th>培训对象</th>
        </tr>
        <%
            Page<Train> trainPage = (Page<Train>) request.getAttribute("trainPage");
            if (trainPage.getList() != null && trainPage.getList().size()>0){
                for (Train train : trainPage.getList()) {
        %>
        <tr>
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
        </tr>
        <%
            }
            }
        %>
    </table>
</fieldset>
</div>
<div class="div4">
    <span>共 <%=trainPage.getTotalPage()%> 页</span>
    <span>当前在第 <%=trainPage.getPageNo()%> 页</span>
    <span><a id="b1" class="aPageState" href="eTrain?pageNo=1">首页</a></span>
    <span><a id="b2" class="aPageState" href="eTrain?pageNo=<%=trainPage.getPrevPage()%>">上一页</a></span>
    <span><a id="b3" class="aPageState" href="eTrain?pageNo=<%=trainPage.getNextPage()%>">下一页</a></span>
    <span><a id="b4" class="aPageState" href="eTrain?pageNo=<%=trainPage.getTotalPage()%>">尾页</a></span>

    <form action="eTrain"  onsubmit="return checkNum(this.children[1].value)">
        <span>跳转到</span><input name="pageNo">
        <input type="hidden" name="eId" value="">
        <input id="b5" type="submit" value="跳转">
    </form>

</div>

</body>
</html>
