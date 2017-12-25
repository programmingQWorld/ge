<%--
  Created by IntelliJ IDEA.
  User: YJX
  Date: 2017/12/19
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>忘记密码</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/user//sendEmail" method="get">
    邮箱：<input type="text" name="email">
    <input type="submit" value="提交">
</form>

</body>
</html>
