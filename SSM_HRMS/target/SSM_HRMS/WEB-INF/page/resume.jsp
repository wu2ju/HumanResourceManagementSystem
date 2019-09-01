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
<jsp:include page="recruitHead.jsp"/>
<div>
    <%--只显示一个简历的信息--%>
    <div>
        <form action="resume" method="post">
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
            <select name="method">
                <option>添加</option>
                <option>删除</option>
                <option>查看</option>
            </select>
            <input type="submit" value="操作简历">
        </form>
    </div>
    <fieldset>
        <legend>简历信息</legend>
        <table id="1">
            <tr>
                <th>姓名</th>
                <th>性别</th>
                <th>生日</th>
                <th>电话</th>
                <th>工作时间</th>
                <th>个人特色</th>
            </tr>
            <tr>
                <form action="updateResume" method="post">
                    <input type="hidden" name="rId" value="${resume.rId}">
                    <td><input name="rName" value="${resume.rName}"></td>
                    <td><input type="radio" name="rSex" value="男" ${(resume.rSex eq "男") ? "checked" : ""}>男
                    <input type="radio" name="rSex" value="女" ${(resume.rSex eq "女") ? "checked" : ""}>女</td>
                    <td><input type="date" name="rBirthday" value="${resume.rBirthday}"></td>
                    <td>${resume.user.uPhone}</td>
                    <td><input type="date" name="rWorktime" value="${resume.rWorktime}"></td>
                    <%--<%
                        Date date = new Date();
                        Resume resume = (Resume) request.getAttribute("resume");
                        if (resume.getrWorktime() == null){
                            %>
                    <td>无</td>
                    <%
                        } else {
//                            int work = (int) ((date.getTime()-resume.getrWorktime().getTime())/(1000*60*60*24*30*365));
                            int year = date.getYear() - resume.getrWorktime().getYear();
                            %>
                    <td><%=year%>年</td>
                    <%
                        }
                    %>--%>
                    <td><textarea name="rFeature">${resume.rFeature}</textarea></td>
                    <input type="submit" value="确认修改">
                </form>
            </tr>
        </table>

        <p>教育经历</p>
        <table id="2">
            <tr>
                <th>学校</th>
                <th>专业</th>
                <th>学历</th>
                <th>入学时间</th>
                <th>毕业时间</th>
                <th>学校经历</th>
            </tr>
            <tr>
                <form action="updateResume" method="post">
                    <input type="hidden" name="rId" value="${resume.rId}">
                    <td><input name="rSchool" value="${resume.rSchool}"></td>
                    <td><input name="rMajor" value="${resume.rMajor}"></td>
                    <td><input name="rEducation" value="${resume.rEducation}"></td>
                    <td><input type="date" name="rAdmission" value="${resume.rAdmission}"></td>
                    <td><input type="date" name="rGraduation" value="${resume.rGraduation}"></td>
                    <td><textarea name="rSchoolexp">${resume.rSchoolexp}</textarea></td>
                    <input type="submit" value="确认修改">
                </form>
            </tr>
        </table>

        <p>工作经历</p>
        <table id="3">
            <tr>
                <th>公司名称</th>
                <th>职位名称</th>
                <th>入职时间</th>
                <th>离职时间</th>
                <th>技能</th>
            </tr>
            <tr>
                <form action="updateResume" method="post">
                    <input type="hidden" name="rId" value="${resume.rId}">
                    <td><input name="rCompany" value="${resume.rCompany}"></td>
                    <td><input name="rJob" value="${resume.rJob}"></td>
                    <td><input type="date" name="rEntry" value="${resume.rEntry}"></td>
                    <td><input type="date" name="rQuit" value="${resume.rQuit}"></td>
                    <td><textarea name="rAbility">${resume.rAbility}</textarea></td>
                    <input type="submit" value="确认修改">
                </form>
            </tr>
        </table>

        <p>期望职位</p>
        <table id="4">
            <tr>
                <th>期望职位</th>
                <th>薪资要求</th>
                <th>行业</th>
                <th>城市</th>
            </tr>
            <tr>
                <form action="updateResume" method="post">
                    <input type="hidden" name="rId" value="${resume.rId}">
                    <td><input name="rHopejob" value="${resume.rHopejob}"></td>
                    <td><input name="rSalary" value="${resume.rSalary}"></td>
                    <td><input name="rIndustry" value="${resume.rIndustry}"></td>
                    <td><input name="rLocation" value="${resume.rLocation}"></td>
                    <input type="submit" value="确认修改">
                </form>
            </tr>
        </table>
    </fieldset>

</div>
</body>
</html>
