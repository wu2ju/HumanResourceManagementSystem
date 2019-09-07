<%@ page import="java.util.List" %>
<%@ page import="com.wuju.model.*" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %><%--
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
        })
    </script>


</head>
<body>
<%
    User user = (User) session.getAttribute("u");
    if (user != null){
        %>
<jsp:include page="recruitHead.jsp"/>
<%
    }else {
        %>
<jsp:include page="adminHead.jsp"/>
<%
    }
%>
<div id="main" >
<div style="-webkit-overflow-scrolling:touch;overflow:auto;height: 100%;">
    <%--只显示一个简历的信息--%>
    <fieldset>
        <legend>简历信息</legend>
        <div class="table-wrapper pl27 " style="min-width:1000px;">
            <table id="1" class="table text-center">
            <tr>
                <th>姓名</th>
                <th>性别</th>
                <th>生日</th>
                <th>电话</th>
                <th>工作时间</th>
                <th>个人特色</th>
            </tr>
            <tr>
                    <td>${resumeForIV.rName}</td>
                    <td><input type="radio" name="rSex" value="男" ${(resumeForIV.rSex eq "男") ? "checked" : ""}>男
                    <input type="radio" name="rSex" value="女" ${(resumeForIV.rSex eq "女") ? "checked" : ""}>女</td>
                    <td>${resumeForIV.rBirthday}</td>
                    <td>${resumeForIV.user.uPhone}</td>
                    <td>${resumeForIV.rWorktime}</td>
                    <td>${resumeForIV.rFeature}</td>
            </tr>
        </table>

        <p>教育经历</p>
            <table id="2" class="table text-center">
            <tr>
                <th>学校</th>
                <th>专业</th>
                <th>学历</th>
                <th>入学时间</th>
                <th>毕业时间</th>
                <th>学校经历</th>
            </tr>
            <tr>
                    <td>${resumeForIV.rSchool}</td>
                    <td>${resumeForIV.rMajor}</td>
                    <td>${resumeForIV.rEducation}</td>
                    <td>${resumeForIV.rAdmission}</td>
                    <td>${resumeForIV.rGraduation}</td>
                    <td>${resumeForIV.rSchoolexp}</td>
            </tr>
        </table>

        <p>工作经历</p>
            <table id="3" class="table text-center">
            <tr>
                <th>公司名称</th>
                <th>职位名称</th>
                <th>入职时间</th>
                <th>离职时间</th>
                <th>技能</th>
            </tr>
            <tr>
                    <td>${resumeForIV.rCompany}</td>
                    <td>${resumeForIV.rJob}</td>
                    <td>${resumeForIV.rEntry}</td>
                    <td>${resumeForIV.rQuit}</td>
                    <td>${resumeForIV.rAbility}</td>
            </tr>
        </table>

        <p>期望职位</p>
            <table id="4" class="table text-center">
            <tr>
                <th>期望职位</th>
                <th>薪资要求</th>
                <th>行业</th>
                <th>城市</th>
            </tr>
            <tr>
                    <td>${resumeForIV.rHopejob}</td>
                    <td>${resumeForIV.rSalary}</td>
                    <td>${resumeForIV.rIndustry}</td>
                    <td>${resumeForIV.rLocation}</td>
            </tr>
        </table>
        </div>
    </fieldset>

</div>
</div>
</body>
</html>
