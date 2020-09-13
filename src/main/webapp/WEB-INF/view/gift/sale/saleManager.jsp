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
	<title>礼品出售单管理</title>
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
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">出售单号:</label>
					<div class="layui-input-inline">
						<input type="text" name="saleid"     autocomplete="off"
							class="layui-input">
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">客户编号:</label>
					<div class="layui-input-inline">
						<input type="text" name="identity"     autocomplete="off"
							class="layui-input">
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">客户姓名:</label>
					<div class="layui-input-inline">
						<input type="text" name="custname"   autocomplete="off"
							class="layui-input">
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">礼品编号:</label>
					<div class="layui-input-inline">
						<input type="text" name="giftnumber" id="giftnumber"  autocomplete="off"
							class="layui-input">
					</div>
				</div>
			</div>
		  </div>
		 <div  class="layui-form-item" style="text-align: center;">
		  <div class="layui-input-block" >
		      <button type="button" class="layui-btn layui-btn-normal  layui-icon layui-icon-search" id="doSearch">查询</button>
		      <button type="reset" class="layui-btn layui-btn-warm  layui-icon layui-icon-refresh">重置</button>
		    </div>
		 </div>
	</form>
	
	<!-- 搜索条件结束 -->
	
	<!-- 数据表格开始 -->
	<table class="layui-hide" id="saleTable" lay-filter="saleTable"></table>
	<div style="display: none;" id="saleToolBar">
	</div>
    <%--d.saleflag ==1的话就什么不显示，否展就展示出来--%>
<%--	<script type="text/html"  id="saleBar">
		 {{#  if(d.saleflag ==1){ }}
	  		<a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="exportSale">导出礼品出售单</a>
 		 {{#  } else { }}
   		 	<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
	  		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	  		<a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="exportSale">导出礼品出售单</a>
  		 {{#  } }}		

	  
	</script>--%>
    <div  id="saleBar" style="display: none;">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
        <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="exportSale">导出出售单</a>
    </div>
	<!-- 数据表格结束 -->
	
	<!-- 添加和修改的弹出层开始 -->
	<div style="display: none;padding: 20px" id="saveOrUpdateDiv" >
		<form class="layui-form"  lay-filter="dataFrm" id="dataFrm">
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">录入时间:</label>
					<div class="layui-input-inline">
						<input type="text" name="createtime"  id="createtime" readonly="readonly" placeholder="请输入录入时间"  autocomplete="off"
							class="layui-input">
					</div>
				</div>
				<div class="layui-inline">
                    <label class="layui-form-label">客户编号:</label>
                    <div class="layui-input-block">
                        <input type="text" name="identity" lay-verify="required" readonly="readonly"  placeholder="请输入身份证号客户编号" autocomplete="off"
                               class="layui-input">
                    </div>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">礼品编号:</label>
				<div class="layui-input-block">
					<input type="text" name="giftnumber" lay-verify="required"  readonly="readonly"  placeholder="请输入礼品编号" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
                <label class="layui-form-label">出售单号:</label>
                <div class="layui-input-inline">
                    <input type="text" name="saleid" style="width: 300px" id="saleid" readonly="readonly" placeholder="请输礼品出售单号"  autocomplete="off"
                           class="layui-input">
                </div>
			</div>
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">客户姓名:</label>
					<div class="layui-input-inline">
						<input type="text" name="custname" lay-verify="required"  readonly="readonly"  placeholder="请输入客户姓名" autocomplete="off"
							class="layui-input">
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">单价:</label>
					<div class="layui-input-inline">
						<input type="text" name="price" lay-verify="required"   placeholder="请输入单价" autocomplete="off"
							class="layui-input">
					</div>
				</div>
			</div>
			<div class="layui-form-item">
                <label class="layui-form-label">订购数量:</label>
                <div class="layui-input-block">
                    <input type="text" name="number" lay-verify="required"   placeholder="请输入订购数量" autocomplete="off"
                           class="layui-input">
                </div>
                <label class="layui-form-label">总金额:</label>
                <div class="layui-input-block">
                    <input type="text" name="t_price" lay-verify="required"   placeholder="请输入总金额" autocomplete="off"
                           class="layui-input">
                </div>
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
	
	<script src="<%=basePath%>/resources/layui/layui.js"></script>
	<script type="text/javascript">
	    var tableIns;
	    layui.use([ 'jquery', 'layer', 'form', 'table','laydate'  ], function() {
			var $ = layui.jquery;
			var layer = layui.layer;
			var form = layui.form;
			var table = layui.table;
			var laydate = layui.laydate;
			laydate.render({
				elem:'#createtime',
				type:'datetime'
			});
/*			laydate.render({
				elem:'#endTime',
				type:'datetime'
			});


			laydate.render({
				elem:'#begindate',
				type:'datetime'
			});
			laydate.render({
				elem:'#returndate',
				type:'datetime'
			});*/
			//渲染数据表格
			 tableIns=table.render({
				 elem: '#saleTable'   //渲染的目标对象
			    ,url:'<%=basePath%>/sale/loadAllSale.action' //数据接口
			    ,title: '礼品出售单数据表'//数据导出来的标题
			    ,toolbar:"#saleToolBar"   //表格的工具条
			    ,height:'full-220'
			    ,cellMinWidth:100 //设置列的最小默认宽度
			    ,page: true  //是否启用分页
			    ,cols: [[   //列表数据
			      {field:'saleid', title:'礼品出售单号',align:'center',width:'280'}
			      ,{field:'identity', title:'客户编号',align:'center',width:'180'}
			      ,{field:'custname', title:'客户姓名',align:'center',width:'120'}
			      ,{field:'giftnumber', title:'礼品编号',align:'center',width:'150'}
			      ,{field:'price', title:'单价',align:'center',width:'120'}
			      ,{field:'number', title:'订购数量',align:'center',width:'180'}
			      ,{field:'t_price', title:'总金额',align:'center',width:'180'}
			      ,{field:'opername', title:'操作员',align:'center',width:'120'}
			      ,{field:'createtime', title:'录入时间',align:'center',width:'180'}
			      ,{fixed: 'right', title:'操作', toolbar: '#saleBar', width:'220',align:'center'}
			    ]],
			    done:function(data,curr,count){
			    	//不是第一页时如果当前返回的的数据为0那么就返回上一页
			    	if(data.data.length==0&&curr!=1){
			    		tableIns.reload({
						    page:{
						    	curr:curr-1
						    }
						});
			    	}
			    }
			})
			//模糊查询
			$("#doSearch").click(function(){
				var params=$("#searchFrm").serialize();
				tableIns.reload({
					url:"<%=basePath%>/sale/loadAllSale.action?"+params ,
				    page:{
				    	curr:1
				    }
				})
			});
			//监听行工具事件
		   table.on('tool(saleTable)', function(obj){
			   var data = obj.data; //获得当前行数据
			   var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
			  if(layEvent === 'del'){ //删除
				  layer.confirm('真的删除【'+data.saleid+'】这个礼品出售单吗', function(index){
				       //向服务端发送删除指令
				       $.post("<%=basePath%>/sale/deleteSale.action",{saleid:data.saleid},function(res){
				    	   layer.msg(res.msg);
				    	    //刷新数据 表格
						tableIns.reload();
				       })
				     }); 
			   } else if(layEvent === 'edit'){ //编辑
			     openUpdateSale(data);
			   }else if(layEvent==='exportSale'){
				   window.location.href="<%=basePath%>/stat/exportSale.action?saleid="+data.saleid;
			   }
			 });
			
			var url;
			var mainIndex;
			//打开修改页面
			function openUpdateSale(data){
				mainIndex=layer.open({
					type:1,
					title:'修改礼品出售单',
					content:$("#saveOrUpdateDiv"),
					area:['800px','500px'],
					success:function(index){
					    /*往修改表单注入值*/
						form.val("dataFrm",data);
						url="<%=basePath%>/sale/updateSale.action";
					}
				});
			}
			//保存
			form.on("submit(doSubmit)",function(obj){
				//序列化表单数据
				var params=$("#dataFrm").serialize();
				$.post(url,params,function(obj){
					layer.msg(obj.msg);
					//关闭弹出层
					layer.close(mainIndex)
					//刷新数据 表格
					tableIns.reload();
				})
			});
		});
	</script>
</body>
</html>