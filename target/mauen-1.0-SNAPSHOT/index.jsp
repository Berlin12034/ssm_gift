<%--
  Created by IntelliJ IDEA.
  User: 柏林
  Date: 2020/3/16
  Time: 0:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<html class="loginHtml">
<head>
    <meta charset="utf-8">
    <title>登录--礼品店后台管理系统</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="icon" href="resources/favicon.ico">
    <link rel="stylesheet" href="<%=basePath%>/resources/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="<%=basePath%>/resources/css/public.css" media="all" />
</head>

<style>
    .loginBody{
        background-image: url(<%=basePath%>/resources/images/login_bg.jpg);
        background-repeat: no-repeat;
    }
</style>

<body class="loginBody" >
<div style="text-align: center;margin-top: 130px" >
    <h1>礼品店信息系统</h1>
</div>
<form class="layui-form" id="loginFrm" method="post" action="<%=basePath%>/User/login">
    <div class="login_face"><img src="<%=basePath%>/resources/images/face.jpg" class="userAvatar"></div>
    <div class="layui-form-item input-item">
        <label for="userName">用户名</label>
        <input type="text" placeholder="请输入用户名" autocomplete="off" name="loginname" id="userName" class="layui-input" lay-verify="required">
    </div>
    <div class="layui-form-item input-item">
        <label for="password">密码</label>
        <input type="password" placeholder="请输入密码" autocomplete="off" name="password"  id="password" class="layui-input" lay-verify="required">
    </div>
    <div class="layui-form-item input-item" id="imgCode">
        <label for="code">验证码</label>
        <input type="text" placeholder="请输入验证码" autocomplete="off" name="code" id="code" class="layui-input">
        <%--onclick="this.src=this.src+'?'相当把src重新请求一次--%>
        <img src="<%=basePath%>/User/getCode" onclick="this.src=this.src+'?'">
    </div>
    <div class="layui-form-item">
        <button class="layui-btn layui-block" lay-filter="login" lay-submit>登录</button>
    </div>
    <div class="layui-form-item layui-row" style="align-content: center;color: red;">
        <%--<a href="javascript:;" class="seraph icon-qq layui-col-xs4 layui-col-sm4 layui-col-md4 layui-col-lg4"></a>
        <a href="javascript:;" class="seraph icon-wechat layui-col-xs4 layui-col-sm4 layui-col-md4 layui-col-lg4"></a>
        <a href="javascript:;" class="seraph icon-sina layui-col-xs4 layui-col-sm4 layui-col-md4 layui-col-lg4"></a>--%>
        ${error}
    </div>
</form>
<script type="text/javascript" src="<%=basePath%>/resources/layui/layui.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/js/cache.js"></script>
<script type="text/javascript">
    layui.use(['form','layer','jquery'],function(){
        var form = layui.form,
            layer = parent.layer === undefined ? layui.layer : top.layer
        $ = layui.jquery;


        //登录按钮
        form.on("submit(login)",function(data){
            $(this).text("登录中...").attr("disabled","disabled").addClass("layui-disabled");
            setTimeout(function(){
                //是jq的一个提交方法
                $("#loginFrm").submit();
            },1000);
            return false;
        })

        //表单输入效果
        $(".loginBody .input-item").click(function(e){
            e.stopPropagation();
            $(this).addClass("layui-input-focus").find(".layui-input").focus();
        })
        $(".loginBody .layui-form-item .layui-input").focus(function(){
            $(this).parent().addClass("layui-input-focus");
        })
        $(".loginBody .layui-form-item .layui-input").blur(function(){
            $(this).parent().removeClass("layui-input-focus");
            if($(this).val() != ''){
                $(this).parent().addClass("layui-input-active");
            }else{
                $(this).parent().removeClass("layui-input-active");
            }
        })
    })


</script>
</body>
</html>