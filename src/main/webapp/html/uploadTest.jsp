<%--
  Created by IntelliJ IDEA.
  User: YJX
  Date: 2017/12/14
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <form action="${pageContext.request.contextPath}/user/updateUser" enctype="multipart/form-data" method="POST">

        <input type="text" name="nickname"><br>
        <input type="text" name="sex"><br>
        <input type="text" name="email"><br>
        <input type="date" name="birthday"><br>
        selectImage: <input type="file"name="file"/><br>
                    <input type="submit"value="upload"/>

    </form>


</body>
</html>
