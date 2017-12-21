<%--
  Created by IntelliJ IDEA.
  User: YJX
  Date: 2017/12/18
  Time: 10:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/resetPassword" method="post">
    <span class="error" style="display: block;">${errors.passwordError}</span>
    新密码：<input type="password" name="newPassword" /><span class="error">${errors.newPassword }</span><br/>
    确认新密码：<input type="password" name="newPassword2"/><span class="error">${errors.newPassword2 }</span><br/>
    <input type="submit" value="修改" />
</form>

</body>
</html>
