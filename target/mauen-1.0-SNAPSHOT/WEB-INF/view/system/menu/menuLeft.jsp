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
        <title>layui后台管理模板 2.0</title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta http-equiv="Access-Control-Allow-Origin" content="*">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="format-detection" content="telephone=no">
        <link rel="icon" href="<%=basePath%>/resources/favicon.ico">
        <link rel="stylesheet" href="<%=basePath%>/resources/layui/css/layui.css" media="all" />
        <link rel="stylesheet" href="<%=basePath%>/resources/css/public.css" media="all" />
        <link rel="stylesheet" href="<%=basePath%>/resources/layui_ext/dtree/dtree.css" media="all" />
        <link rel="stylesheet" href="<%=basePath%>/resources/layui_ext/dtree/font/dtreefont.css" media="all" />
        </head>
        <body class="childrenBody">
           <ul id="menuTree" class="dtree" data-id="0"></ul>
        <script type="text/javascript" src="<%=basePath%>/resources/layui/layui.js"></script>
        <script type="text/javascript">
            layui.extend({
            dtree: '<%=basePath%>/resources/layui_ext/dist/dtree'   // {/}的意思即代表采用自有路径，即不跟随 base 路径
            }).use(['dtree','layer','jquery','form'], function(){
            var dtree = layui.dtree, layer = layui.layer, $ = layui.jquery,from=layer.form;

            // 初始化树
            var DemoTree = dtree.render({
            elem: "#menuTree",
            url: "<%=basePath%>/menu/loadMenuManagerLeftTreeJson?spread=1",// 使用url加载（可与data加载同时存在）
            dataStyle: "layuiStyle",  //使用layui风格的数据格式
            dataFormat: "list",  //配置data的风格为list
            response:{message:"msg",statusCode:0},  //修改response中返回数据的定义
            });

            // 绑定节点点击
            //也是节点监听事件
            dtree.on("node('menuTree')" ,function(obj){
                /*为了取到点击到节点的id*/
                /*alert(obj.param.nodeId);
                layer.msg(JSON.stringify(obj.param));*/
                var id=obj.param.nodeId;
                /*因为左右页面的父页面是menuManager,所以可以用这个方法调用右边页面的方法
                * 实现监听到id的值传给另一个页面*/
                window.parent.right.reloadTable(id);
            });
            });


        </script>
        </body>
        </html>