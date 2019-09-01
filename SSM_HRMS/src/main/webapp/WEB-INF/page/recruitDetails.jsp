<%@ page import="com.wuju.model.Recruit" %>
<%@ page import="com.wuju.model.Resume" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 吴炬
  Date: 2019/8/30
  Time: 9:28
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
</head>
<body>
<jsp:include page="recruitHead.jsp"/>
<fieldset>
    <legend>招聘信息</legend>
    <table>
        <tr>
            <th>职位名称</th>
            <th>薪资</th>
            <th>地址</th>
            <th>经验要求</th>
            <th>学历要求</th>
        </tr>
        <%
            Recruit recruit = (Recruit) request.getAttribute("recruit");
        %>
        <tr>
            <%--跳转到页面，职位，薪资，公司名，地址，经验，学历--%>
            <td><%=recruit.getPosition().getpName()%></td>
            <td><%=recruit.getPosition().getpSalary()%></td>
            <td><%=recruit.getPosition().getpLocation()%></td>
            <td><%=recruit.getPosition().getpExperience()%></td>
            <td><%=recruit.getPosition().getpEducation()%></td>
        </tr>
    </table>
    <div>
        <form action="addInterview" method="post">
            <input type="hidden" name="rcId" value="<%=recruit.getRcId()%>">
            <select name="rId">
                <%
                    List<Resume> resumes = (List<Resume>) request.getAttribute("resumes");
                    if (resumes != null && resumes.size()>0){
                        for (Resume resume : resumes) {
                %>
                <option value="<%=resume.getrId()%>">简历<%=resume.getrId()%></option>
                <%
                        }
                    }
                %>
            </select>
            <input type="submit" value="投递简历">
        </form>
    </div>
    <p>职位描述</p>
    <p><%=recruit.getPosition().getpIntroduction()%></p>
    <p>职位要求</p>
    <p><%=recruit.getPosition().getpRequest()%></p>
    <p>公司介绍</p>
    <p><%=recruit.getDepartment().getCompany().getCpName()%></p>
    <p><%=recruit.getDepartment().getCompany().getCpIntroduction()%></p>
    <p>工作地址</p>
    <p><%=recruit.getPosition().getpLocation()%></p>
</fieldset>
</body>
</html>
