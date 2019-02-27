<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="ctxs" value="${pageContext.request.contextPath}/static" />
<%--<link rel="stylesheet" type="text/css" href="${ctxs}/css/css.css?t=<%=System.currentTimeMillis()%>" />
<link rel="stylesheet" type="text/css" href="${ctxs}/css/mui.css" />
<link rel="stylesheet" type="text/css" href="${ctxs}/css/mui.load.css" />--%>
<script>
    window.Main = '${ctx}';
</script>
<script type="text/javascript" src="${ctxs}/js/jquery-1.9.1.min.js"></script>
<%--
<script type="text/javascript" src="${ctxs}/js/vue.min.js"></script>
<script type="text/javascript" src="${ctxs}/js/mui.min.js"></script>
<script type="text/javascript" src="${ctxs}/js/mui.load.js"></script>
<script type="text/javascript" src="${ctxs}/js/comm.js"></script>--%>
