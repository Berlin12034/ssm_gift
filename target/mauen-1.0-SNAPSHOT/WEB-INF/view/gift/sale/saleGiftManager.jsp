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
	<title>礼品出售管理</title>
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
	<!-- 搜索条件开始 -->
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
	  <legend>查询条件</legend>
	</fieldset>
	<form class="layui-form" method="post" id="searchFrm">
		<div class="layui-form-item">
			<div class="layui-inline">
			      <label class="layui-form-label">客户编号:</label>
			      <div class="layui-input-inline">
			        <input type="text" name="identity"  id="identity" autocomplete="off" class="layui-input">
			      </div>
		     </div>
			<div class="layui-inline">
		            <button type="button" class="layui-btn layui-btn-normal  layui-icon layui-icon-search" id="doSearch">查询</button>
		     </div>
		 </div>
	</form>
	
	<!-- 搜索条件结束 -->
	
	<!-- 数据表格开始 -->
	<div id="content" style="display: none;">
		<table  id="messageTable" lay-filter="messageTable"></table>
        <%--messageBar用来给行监听点击事件的--%>
		<div id="messageBar" style="display: none;">
		  <a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="saleGift">礼品出售</a>
		  <a class="layui-btn layui-btn-xs" lay-event="viewImage">查看礼品大图</a>
		</div>
	</div>
	<!-- 数据表格结束 -->
	
	<!-- 添加和修改的弹出层开始 -->
	<div style="display: none;padding: 20px" id="saveOrUpdateDiv" >
		<form class="layui-form"  lay-filter="dataFrm" id="dataFrm">
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">出售时间:</label>
					<div class="layui-input-inline">
						<input type="text" name="createtime"  id="createtime" placeholder="请输入出售时间"  autocomplete="off"
							class="layui-input">
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">客户姓名:</label>
					<div class="layui-input-inline">
						<input type="text" name="custname"  id="returndate" placeholder="请输入客户姓名" readonly="readonly" autocomplete="off"
							class="layui-input">
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">出售单号:</label>
				<div class="layui-input-block">
					<input type="text" name="saleid" lay-verify="required"  readonly="readonly"  placeholder="请输入礼品出售单号" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">客户编号:</label>
				<div class="layui-input-block">
					<input type="text" name="identity" lay-verify="required" readonly="readonly"  placeholder="请输入身份证号" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">礼品编号:</label>
					<div class="layui-input-inline">
						<input type="text" name="giftnumber" lay-verify="required"  readonly="readonly"  placeholder="请输入礼品编号" autocomplete="off"
							class="layui-input">
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">出售单价:</label>
					<div class="layui-input-inline">
						<input type="text" name="price" lay-verify="required|number"   placeholder="请输入礼品出售价格" autocomplete="off"
							class="layui-input">
					</div>
                    <label class="layui-form-label">出售数量:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="number" lay-verify="required|number"   placeholder="请输入出售数量" autocomplete="off"
                               class="layui-input">
                    </div>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">操作员:</label>
				<div class="layui-input-block">
					<input type="text" name="opername" lay-verify="required"  readonly="readonly" placeholder="请输入操作员" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item" style="text-align: center;">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-release" lay-filter="doSubmit" lay-submit="">提交</button>
			      <button type="reset" class="layui-btn layui-btn-warm layui-btn-sm layui-icon layui-icon-refresh" >重置</button>
			    </div>
		  	</div>
		
		</form>
	</div>
	<!-- 添加和修改的弹出层结束 -->
	<!-- 查看大图弹出的层 开始 -->
	<div id="viewCarImageDiv" style="display: none;text-align: center;">
		<img alt="礼品出售图片" width="550" height="350" id="view_carimg">
	</div>
	<!-- 查看大图弹出的层 结束 -->
	
	<script src="<%=basePath%>/resources/layui/layui.js"></script>
	<script type="text/javascript">
	    var tableIns;
	    layui.use([ 'jquery', 'layer', 'form', 'table','laydate'], function() {
			var $ = layui.jquery;
			var layer = layui.layer;
			var form = layui.form;
			var table = layui.table;
			var laydate=layui.laydate;
			
			laydate.render({
				elem:'#createtime',
				type:'datetime'
			});
			function initGiftData(){
				//渲染数据表格
                tableIns=table.render({
                    elem: '#messageTable'   //渲染的目标对象
                    ,url:'<%=basePath%>/message/loadAllMessage.action' //数据接口
                    ,title: '礼品数据表'//数据导出来的标题
                    ,height:'full-220'
                    ,cellMinWidth:100 //设置列的最小默认宽度
                    ,page: true  //是否启用分页
                    ,cols: [[   //列表数据
                        ,{field:'giftnumber', title:'礼品序号',align:'center',width:'120'}
                        ,{field:'giftname', title:'礼品名称',align:'center',width:'100'}
                        ,{field:'color', title:'礼品颜色',align:'center',width:'120'}
                        ,{field:'gifttype', title:'礼品类型',align:'center',width:'150'}
                        ,{field:'describr', title:'礼品描述',align:'center',width:'120'}
                        ,{field:'carimg', title:'礼品图片',align:'center',width:'140',templet:function (d) {
                                return "<img width=40 height=40 src=<%=basePath%>/file/downloadShowFile.action?path="+d.carimg+"/>"
                            }}
                        ,{field:'buyprice', title:'进货价格',align:'center',width:'120'}
                        ,{field:'rentprice', title:'售价',align:'center',width:'180'}
                        ,{field:'s_number', title:'库存数量',align:'center',width:'180'}
                        ,{field:'createtime', title:'进货时间',align:'center',width:'180'}
                        ,{fixed: 'right', title:'操作', toolbar: '#messageBar', width:220,align:'center'}
                    ]]
				})
			}
			
			//模糊查询
			$("#doSearch").click(function(){
				var params=$("#searchFrm").serialize();
				$.post("<%=basePath%>/sale/checkCustomerExist.action",params,function(obj){
					if(obj.code>=0){
						$("#content").show();
						/*初始化数据*/
						initGiftData();
					}else{
						layer.msg("客户身份证号不存在,请更正后再查询");
						//隐藏 
						$("#content").hide();
					}
				})
			});
			
			//监听行工具事件
            /*messageTable表格的lay-filter*/
		   table.on('tool(messageTable)', function(obj){
			   var data = obj.data; //获得当前行数据
			   var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		       if(layEvent === 'saleGift'){ //编辑
			      openSaleGift(data);
			   }else if(layEvent==='viewImage'){
				   showCarImage(data);
			   }
			 });
			
			var mainIndex;
			//打开添加出售页面
			function openSaleGift(data){
				mainIndex=layer.open({
					type:1,
					title:'添加礼品出售表单',
					content:$("#saveOrUpdateDiv"),
					area:['800px','500px'],
					success:function(index){
						//清空表单数据       
						$("#dataFrm")[0].reset();
						//请求数据
						var identity=$("#identity").val();
						var price=data.rentprice;
						var giftnumber=data.giftnumber;
						$.get("<%=basePath%>/sale/initSaleFrom.action",{
							identity:identity,
							price:price,
                            giftnumber:giftnumber
						},function(obj){//---obj---RentVo得回来的数据赋值
							//赋值
							form.val("dataFrm",obj);
						})
					}
				});
			}
			//保存
			form.on("submit(doSubmit)",function(obj){
				//序列化表单数据
				var params=$("#dataFrm").serialize();
				$.post("<%=basePath%>/sale/saveSale.action",params,function(obj){
					layer.msg(obj.msg);
					//关闭弹出层
					layer.close(mainIndex)
					$("#content").hide();
				})
			});
			//查看大图
			function showCarImage(data){
				
				mainIndex=layer.open({
					type:1,
					title:"【"+data.giftname+'】的礼品出售图片',
					content:$("#viewCarImageDiv"),
					area:['600px','400px'],
					success:function(index){
						$("#view_carimg").attr("src","<%=basePath%>/file/downloadShowFile.action?path="+data.carimg);
					}
				});
			}
		});
	</script>
</body>
</html>