<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>抱歉，未找到页面</title>
    <jsp:include page="../taglib.jsp"></jsp:include>
</head>

<body>

<div id="no-context" style="padding-top: 55%;padding-bottom: 10%;text-align: center;">
    <img src="${pageContext.request.contextPath}/static/images/error.png" style="width: 120px;height: 120px;"><br>
    <p style="color: #858585;font-size: 14px;">抱歉，未找到页面</p>
</div>

</body>
</html>