<%--
  Created by IntelliJ IDEA.
  User: 柏林
  Date: 2020/3/20
  Time: 14:30
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户地区统计</title>
</head>
<body style="height: 100%; margin: 0">
       <div id="container" style="height: 100%"></div>
       <script type="text/javascript" src="<%=basePath%>/resources/echarts/js/echarts.min.js"></script>
       <script type="text/javascript" src="<%=basePath%>/resources/echarts/js/jquery-3.1.1.min.js"></script>
       <script type="text/javascript">

		$.get("<%=basePath%>/stat/loadCustomerAreaStatJosn.action",function(data){
			var dom = document.getElementById("container");
			var myChart = echarts.init(dom);
			var app = {};
			option = null;
			option = {
			    title : {
			        text: '礼品店系统客户地址统计',
			        subtext: '真实有效',
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
			    legend: {
			        orient: 'vertical',
			        left: 'left',
			        data: data
			    },
			    series : [
			        {
			            name: '客户数量(占比)',
			            type: 'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data:data,
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                }
			            }
			        }
			    ]
			};
			if (option && typeof option === "object") {
			    myChart.setOption(option, true);
			}
		})
       
       </script>
   </body>
</html>