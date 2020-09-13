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
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>出售信息查询</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta http-equiv="Access-Control-Allow-Origin" content="*">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="icon" href="favicon.ico">
	<link rel="stylesheet" href="<%=basePath%>/resources/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="<%=basePath%>/resources/css/public.css" media="all" />
</head>
<body class="childrenBody">
<fieldset class="layui-elem-field layui-field-title" style="text-align: center;">
    <legend>出售信息查询</legend>
</fieldset>
	<!-- 搜索条件开始 -->
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
	  <legend>查询条件</legend>
	</fieldset>
	<form class="layui-form" method="post" id="searchFrm">
		<div class="layui-form-item">
			<div class="layui-inline">
			      <label class="layui-form-label">出售单号:</label>
			      <div class="layui-input-inline">
			        <input type="text" name="search_saleid"  id="search_saleid" autocomplete="off" class="layui-input">
			      </div>
		     </div>
			<div class="layui-inline">
		            <button type="button" class="layui-btn layui-btn-normal  layui-icon layui-icon-search" id="doSearch">查询</button>
		     </div>
		 </div>
	</form>


	<div id="content" style="display: none;">
		<!-- 检查单位的表单 -->
		<div class="layui-elem-quote" style="margin-top: 10px;">
			<h2>出售信息表单</h2>
			<hr>
            
		<!-- 出售单  礼品  客户的信息展开 -->
		<div style="padding: 10px; background-color: #F2F2F2;">
			<div class="layui-row layui-col-space8">
				<div class="layui-col-md4">
					<div class="layui-saled">
						<div class="layui-saled-header">礼品信息</div>
						<div class="layui-saled-body" id="message_giftnumber">
						</div>
						<div class="layui-saled-body" id="message_giftname">
						</div>
						<div class="layui-saled-body" id="message_color">
						</div>
						<div class="layui-saled-body" id="message_type">
						</div>
						<div class="layui-saled-body" id="message_rentprice">
						</div>
						<div class="layui-saled-body" id="message_describr">
						</div>
						<%--<div class="layui-saled-body" id="sale_carimg">
						</div>--%>
						<div class="layui-saled-body">
							礼品图片:<img alt="" width="200" height="150" id="message_carimg" src="">
						</div>
					</div>
				</div>
				<div class="layui-col-md4">
					<div class="layui-saled">
						<div class="layui-saled-header">出售单信息</div>
						<div class="layui-saled-body" id="sale_saleid">
						</div>
						<div class="layui-saled-body" id="sale_giftnumber">
						</div>
						<div class="layui-saled-body" id="sale_price">
						</div>
						<div class="layui-saled-body" id="sale_number">
						</div>
						<div class="layui-saled-body" id="sale_t_price">
						</div>
                        <div class="layui-saled-body" id="sale_identity">
                        </div>
                        <div class="layui-saled-body" id="sale_opername">
                        </div>
                        <div class="layui-saled-body" id="sale_custname">
                        </div>
					</div>
				</div>
				<div class="layui-col-md4">
					<div class="layui-saled">
						<div class="layui-saled-header">客户信息</div>
						<div class="layui-saled-body" id="customer_identity">
						</div>
						<div class="layui-saled-body" id="customer_custname">
						</div>
						<div class="layui-saled-body" id="customer_sex">
						</div>
						<div class="layui-saled-body" id="customer_address">
						</div>
						<div class="layui-saled-body" id="customer_phone">
						</div>
						<div class="layui-saled-body" id="customer_career">
						</div>
					</div>
				</div>
				
			</div>
		</div>
        </div>

	<script src="<%=basePath%>/resources/layui/layui.js"></script>
	<script type="text/javascript">
		var tableIns;
		layui.use([ 'jquery', 'layer', 'form', 'table', 'laydate' ],
				function() {
					var $ = layui.jquery;
					var layer = layui.layer;
					var form = layui.form;
					var table = layui.table;
					var laydate = layui.laydate;

					//根据出售单号查询
					$("#doSearch").click(function() {
						var saleid = $("#search_saleid").val();
						$.post("<%=basePath%>/sale/querySaleExist.action", {
							saleid : saleid
						}, function(obj) {
							if (obj === "") {
								layer.msg("您输入的出售单号不存在,请更正后再查询");
								$("#content").hide();
							} else {

                                initSaleFormData(saleid);
									$("#content").show();
								}
						})
					});
					
					//加载表单数据和 卡片面板的数据
					function initSaleFormData(saleid){
						$.post("<%=basePath%>/sale/initSaleFormData.action",{saleid:saleid},function(obj){
							//检查单
							/*var check=obj.check;
							form.val("checkFrm",check);*/
							//客户
                            /*html是给id里面赋值，让它显示出来*/
							var customer=obj.customer;
							$("#customer_identity").html("客户编号: "+customer.identity);
							$("#customer_custname").html("客户姓名: "+customer.custname);
							$("#customer_sex").html("客户性别: "+(customer.sex==1?'男':'女'));
							$("#customer_address").html("客户地址: "+customer.address);
							$("#customer_phone").html("客户电话: "+customer.phone);
							$("#customer_career").html("客户职位: "+customer.career);
							
							//出售单
							var sale=obj.sale;
							
							$("#sale_saleid").html("出售单号: "+sale.saleid);	
							$("#sale_giftnumber").html("礼品编号: "+sale.giftnumber);
							$("#sale_price").html("出售单价: "+sale.price);
							$("#sale_number").html("出售数量: "+sale.number);
							$("#sale_t_price").html("总金额: "+sale.t_price);
                            $("#sale_identity").html("客户编号: "+sale.identity);
                            $("#sale_opername").html("操作员: "+sale.opername);
                            $("#sale_custname").html("客户姓名: "+sale.custname);

                            //礼品信息
							var message=obj.message;
							$("#message_giftnumber").html("礼品编号号: "+message.giftnumber);
							$("#message_giftname").html("礼品名称: "+message.giftname);
							$("#message_color").html("礼品颜色: "+message.color);
							$("#message_type").html("礼品类型: "+message.gifttype);
							$("#message_rentprice").html("出售价格: "+message.rentprice);
							$("#message_describr").html("礼品描述: "+message.describr);
							/*$("#sale_description").html("礼品描述: "+message.description);*/
							$("#message_carimg").attr("src","<%=basePath%>/file/downloadShowFile.action?path="+message.carimg);
						})
					}
					
					//保存
				/*	form.on("submit(doSubmit)",function(){
						var params=$("#checkFrm").serialize();
						$.post("${ctx}/check/saveCheck.action",params,function(obj){
							layer.msg(obj.msg);
							$("#content").hide();
						})
						return false;
					});*/
				});
	</script>
</body>
</html>