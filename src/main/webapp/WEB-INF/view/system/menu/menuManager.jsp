    <%--
      Created by IntelliJ IDEA.
      User: 柏林
      Date: 2020/3/19
      Time: 21:29
      To change this template use File | Settings | File Templates.
    --%>
        <%@ page isELIgnored="false" %>
        <%@ page contentType="text/html;charset=UTF-8" language="java" %>
            <%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
        <html>
        <head>
        <meta charset="utf-8">
        <title>礼品店后台管理系统</title>
        </head>
        <%--如果使用frameset的包含页面 主页面不能有body--%>
       <frameset cols="200,*" border="1">
            <frame src="<%=basePath%>/sys/toMenuLeft" name="left">
            <frame src="<%=basePath%>/sys/toMenuRight" name="right">

        </frameset>

        </html>