<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Set" %>
<%@ page import="com.wuju.model.*" %>
<%@ page import="java.util.Date" %><%--
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
            $(".a0").click(function () {
                sessionStorage.removeItem("itState");
                sessionStorage.clear();
                var itState = $(this).name();
                sessionStorage.setItem("itState",itState);
            });
            $("a[id^='b']").click(function () {
                var itState = sessionStorage.getItem("itState");
                if (itState != null){
                    $(this)[0].href += ("&itState="+itState);
                    $("input[name=itState]").val(itState);
                }
            });
            $("input[id^='b']").click(function () {
                var itState = sessionStorage.getItem("itState");
                if (itState != null){
                    $("input[name=itState]").val(itState);
                }
            })
        })
    </script>
    <style>
        .table-wrapper .table td label{
            width: 40px;
            text-align: left;
        }
    </style>

</head>
<body>
<jsp:include page="adminHead.jsp"/>
<div id="main">
    <div style="-webkit-overflow-scrolling:touch;overflow:auto;height: 100%;position: absolute;z-index: 999" >
    <div>
        <a style="font-size: larger"  class="a0" name="" href="adminInterview">所有面试</a>
        <%
            HashMap<Integer, String> chooseItState = (HashMap<Integer, String>) request.getAttribute("chooseItState");
            if (chooseItState != null){
                Set<Integer> keySet = chooseItState.keySet();
                for (Integer key : keySet) {
        %>
        <a style="font-size: larger" class="a0" name="<%=key%>" href="adminInterview?itState=<%=key%>"><%=chooseItState.get(key)%></a>
        <%
            }
            }
        %>
    </div>

    <%--显示投递简历之后的面试消息--%>
    <fieldset>
        <legend><div class="frame-header"><span class="page-reload cur">招聘信息</span></div></legend>
        <div class="table-wrapper pl27 " style="min-width:1000px;">
            <table class="table text-center">
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
                        if (interview.getItState() == 0){
            %>
            <tr>
                <td><%=interview.getRecruit().getPosition().getpName()%></td>
                <td><%=interview.getResumeForIV().getrName()%></td>
                <td><%=interview.getItDeliveryResume()%></td>
                <td><%=interview.getItState()%> 是否发送面试通知</td>
                <form action="updateInterview" method="post">
                    <td><input style="width: 170px" type="datetime-local" min="<%=new Date()%>" name="itTime1" required></td>
                    <td><input name="itAddress" value="<%=interview.getRecruit().getPosition().getpLocation()%>"></td>
                    <td><select name="eId">
                        <%
                            HashMap<Integer, List<Employee>> employeeMap = (HashMap<Integer, List<Employee>>) request.getAttribute("employeeMap");
                            List<Employee> employees = employeeMap.get(interview.getItId());
                            for (Employee employee : employees) {
                                %>
                        <option value="<%=employee.geteId()%>"><%=employee.geteName()%></option>
                        <%
                            }
                        %>
                    </select></td>
                    <input type="hidden" name="itId" value="<%=interview.getItId()%>">
                    <input type="hidden" name="itState" value="2">
                    <td><input type="submit" value="通知面试"></td>
                </form>
                <td><a href="updateInterview?itId=<%=interview.getItId()%>&itState=1">不面试</a></td>
                <td><a href="resumeForIV?rId=<%=interview.getResumeForIV().getrId()%>&rState=2">查看简历</a>
                <%
                    if (interview.getResumeForIV().getrState() == 2){
                        %>
                    <span style="color: red">已阅</span>
                    <%
                    }
                %>
                </td>
            </tr>
            <%
            } else if (interview.getItState() == 3){
            %>
            <tr>
                <td><%=interview.getRecruit().getPosition().getpName()%></td>
                <td><%=interview.getResumeForIV().getrName()%></td>
                <td><%=interview.getItDeliveryResume()%></td>
                <td><%=interview.getItState()%> 通知面试</td>
                <td><%=interview.getItTime()%></td>
                <td><%=interview.getItAddress()%></td>
                <td><%=interview.getEmployee().geteName()%></td>
                <td><a href="updateInterview?itId=<%=interview.getItId()%>&itState=5">面试通过，发送消息</a></td>
                <td><a href="updateInterview?itId=<%=interview.getItId()%>&itState=6">面试不通过</a></td>
            </tr>
            <%
            }else if (interview.getItState() == 4){
            %>
            <tr>
                <td><%=interview.getRecruit().getPosition().getpName()%></td>
                <td><%=interview.getResumeForIV().getrName()%></td>
                <td><%=interview.getItDeliveryResume()%></td>
                <td><%=interview.getItState()%> 用户拒绝面试</td>
                <td><%=interview.getItTime()%></td>
                <td><%=interview.getItAddress()%></td>
                <td><%=interview.getEmployee().geteName()%></td>
            </tr>
            <%
            }else{
            %>
            <tr>
                <td><%=interview.getRecruit().getPosition().getpName()%></td>
                <td><%=interview.getResumeForIV().getrName()%></td>
                <td><%=interview.getItDeliveryResume()%></td>
                <td><%=interview.getItState()%><%=chooseItState.get(interview.getItState())%></td>
                <td><%=interview.getItTime()%></td>
                <td><%=interview.getItAddress()%></td>
                <td><%=interview.getEmployee().geteName()%></td>
            </tr>
            <%
                        }
                    }
                }
            %>
        </table>
        </div>
    </fieldset>

    <div class="div4" style="width: 400px; position: absolute; left: 30%; margin-left: -120px;">
        <div class="fl tb3" style="width: 350px;float: left;">
        <span>共 <%=interviewPage.getTotalPage()%> 页</span>
        <span>第 <%=interviewPage.getPageNo()%> 页</span>
        <span><a id="b1" class="aPageState" href="adminInterview?pageNo=1">首页</a></span>
        <span><a id="b2" class="aPageState" href="adminInterview?pageNo=<%=interviewPage.getPrevPage()%>">上一页</a></span>
        <span><a id="b3" class="aPageState" href="adminInterview?pageNo=<%=interviewPage.getNextPage()%>">下一页</a></span>
        <span><a id="b4" class="aPageState" href="adminInterview?pageNo=<%=interviewPage.getTotalPage()%>">尾页</a></span>
        </div>

        <div class="fr tb5" style="text-align: left;padding-right: 0px;position: absolute; right: 0; top: 0;">
        <form action="adminInterview"  onsubmit="return checkNum(this.children[1].value)">
            <span>跳转到</span><input style="width: 40px;height: 26px;" class="text-center" name="pageNo">
            <input type="hidden" name="itState" value="">
            <input id="b5" type="submit" value="跳转">
        </form>
        </div>

    </div>
</div>
</div>
</body>
</html>
