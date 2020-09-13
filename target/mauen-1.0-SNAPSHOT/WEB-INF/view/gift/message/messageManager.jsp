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
	<title>礼品管理</title>
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
		      <label class="layui-form-label">礼品序号:</label>
		      <div class="layui-input-inline">
		        <input type="text" name="giftnumber"  autocomplete="off" class="layui-input">
		      </div>
		    </div>
		    <div class="layui-inline">
		      <label class="layui-form-label">礼品名称:</label>
		      <div class="layui-input-inline">
		        <input type="text" name="giftname"  autocomplete="off" class="layui-input">
		      </div>
		    </div>
		    <div class="layui-inline">
		      <label class="layui-form-label">礼品颜色:</label>
		      <div class="layui-input-inline">
		        <input type="text" name="color"  autocomplete="off" class="layui-input">
		      </div>
		    </div>
		 </div>
		 
		 <div class="layui-form-item">
		    <div class="layui-inline">
		      <label class="layui-form-label">礼品类型:</label>
		      <div class="layui-input-inline">
		        <input type="text" name="gifttype"  autocomplete="off" class="layui-input">
		      </div>
		    </div>
		    <div class="layui-inline">
		      <label class="layui-form-label">礼品描述:</label>
		      <div class="layui-input-inline">
		        <input type="text" name="describr"  autocomplete="off" class="layui-input">
		      </div>
		    </div>
		     <%--<div class="layui-inline">
		      <label class="layui-form-label">性别:</label>
		      <div class="layui-input-inline">
		       		 <input type="radio" name="sex" value="1" title="男">
					 <input type="radio" name="sex" value="0" title="女">
		      </div>
		    </div>--%>
		 </div>
		 <div  class="layui-form-item" style="text-align: center;">
		  <div class="layui-input-block" >
		      <button type="button" class="layui-btn layui-btn-normal  layui-icon layui-icon-search" id="doSearch">查询</button>
		      <button type="reset" class="layui-btn layui-btn-warm  layui-icon layui-icon-refresh">重置</button>
		     <%-- <button type="button" class="layui-btn layui-btn-normal  layui-icon layui-icon-download-circle" id="doExport">导出</button>--%>
		    </div>
		 </div>
	</form>
	
	<!-- 搜索条件结束 -->
	
	<!-- 数据表格开始 -->
	<table class="layui-hide" id="messageTable" lay-filter="messageTable"></table>
	<div style="display: none;" id="messageToolBar">
	   <button type="button" class="layui-btn layui-btn-sm" lay-event="add">增加</button>
       <button type="button" class="layui-btn layui-btn-danger layui-btn-sm" lay-event="deleteBatch">批量删除</button>
	</div>
	<div  id="messageBar" style="display: none;">
	  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
	  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
		<a class="layui-btn layui-btn-xs" lay-event="viewImage">查看大图</a>
	</div>
	<!-- 数据表格结束 -->
	
	<!-- 添加和修改的弹出层开始 -->
	<div style="display: none;padding: 20px" id="saveOrUpdateDiv" >
		<form class="layui-form layui-row layui-col-space10"  lay-filter="dataFrm" id="dataFrm">
			<div class="layui-col-md12 layui-col-xs12">
				<div class="layui-row layui-col-space10">
					<div class="layui-col-md9 layui-col-xs7">
						<div class="layui-form-item magt3">
							<label class="layui-form-label">礼品型号:</label>
							<div class="layui-input-block">
								<input type="text" name="giftnumber" id="giftnumber" class="layui-input" lay-verify="required" placeholder="请输入礼品型号">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">礼品名称:</label>
							<div class="layui-input-block">
								<input type="text" name="giftname" class="layui-input" lay-verify="required" placeholder="请输入礼品名称">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">礼品类型:</label>
							<div class="layui-input-block">
								<input type="text" name="gifttype" class="layui-input" lay-verify="required" placeholder="请输入礼品类型">
							</div>
						</div>
					</div>
					<div class="layui-col-md3 layui-col-xs5">
						<div class="layui-upload-list thumbBox mag0 magt3" id="carimgDiv">
							<%--显示上传的图片--%>
							<img class="layui-upload-img thumbImg"  id="showCarImg">
							<%--保存当前图片地址--%>
							<input type="hidden" name="carimg" id="carimg">
						</div>
					</div>
				</div>
				<div class="layui-form-item magb0">
					<div class="layui-inline">
						<label class="layui-form-label">礼品颜色:</label>
						<div class="layui-input-block">
							<input type="text" name="color" class="layui-input" lay-verify="required" placeholder="请输入礼品颜色">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">进货价格:</label>
						<div class="layui-input-block">
							<input type="text" name="buyprice" class="layui-input" lay-verify="required|number" placeholder="请输入礼品进货价格">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">售价:</label>
						<div class="layui-input-block">
							<input type="text" name="rentprice" class="layui-input" lay-verify="required|number" placeholder="请输入礼品售价">
						</div>
					</div>
				</div>
				<div class="layui-form-item magb0">

					<label class="layui-form-label">礼品描述:</label>
					<div class="layui-input-block">
						<input type="text" name="describr" class="layui-input" lay-verify="required" placeholder="请输入礼品描述">
					</div>

				</div>
				<div class="layui-form-item magb0">
					<div class="layui-inline">
						<label class="layui-form-label">进货数量:</label>
						<div class="layui-input-block">
							<input type="text" name="s_number" class="layui-input" lay-verify="required|number" placeholder="请输入进货数量">
						</div>
					</div>
				</div>
			</div>
			<div class="layui-form-item magb0" style="text-align: center;">
				<div class="layui-input-block">
					<button type="button" class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-release" lay-filter="doSubmit" lay-submit="">提交</button>
					<button type="reset" class="layui-btn layui-btn-warm layui-btn-sm layui-icon layui-icon-refresh" >重置</button>
				</div>
			</div>
		</form>
	
	</div>
    <%--查看大图弹出的层开始--%>
    <div id="viewCarImageDiv" style="display: none; text-align: center">
        <img alt="礼品图片" id="view_carimg">
    </div>
	<!-- 添加和修改的弹出层结束 -->
	
	<script src="<%=basePath%>/resources/layui/layui.js"></script>
	<script type="text/javascript">
	    var tableIns;
	    /*引入图片上次模块upload*/
	    layui.use([ 'jquery', 'layer', 'form', 'table','upload'  ], function() {
			var $ = layui.jquery;
			var layer = layui.layer;
			var form = layui.form;
			var table = layui.table;
			var upload = layui.upload;
			//渲染数据表格
			 tableIns=table.render({
                     elem: '#messageTable'   //渲染的目标对象
                     ,url:'<%=basePath%>/message/loadAllMessage.action' //数据接口
                     ,title: '礼品数据表'//数据导出来的标题
                     ,toolbar:"#messageToolBar"   //表格的工具条
                     ,height:'full-220'
                     ,cellMinWidth:100 //设置列的最小默认宽度
                     ,page: true  //是否启用分页
                     ,cols: [[   //列表数据
                         {type: 'checkbox', fixed: 'left'}
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
					url:"<%=basePath%>/message/loadAllMessage.action?"+params ,
				    page:{
				    	curr:1
				    }
				})
			});
			//导出
				$("#doExport").click(function(){
					var params=$("#searchFrm").serialize();
					window.location.href="<%=basePath%>/stat/exportMessage.action?"+params;
				});
			
			//监听头部工具栏事件
			table.on("toolbar(messageTable)",function(obj){
				 switch(obj.event){
				    case 'add':
				      openAddMessage();
				    break;
				    case 'deleteBatch':
				      deleteBatch();
					break;
				  };
			})
			//监听行工具事件
		   table.on('tool(messageTable)', function(obj){
			   var data = obj.data; //获得当前行数据
			   var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
			  if(layEvent === 'del'){ //删除
				  layer.confirm('真的删除【'+data.giftname+'】这个礼品吗', function(index){
				       //向服务端发送删除指令
				       $.post("<%=basePath%>/message/deleteMessage.action",{giftnumber:data.giftnumber},function(res){
				    	   layer.msg(res.msg);
				    	    //刷新数据 表格
							tableIns.reload();
				       })
				     }); 
			   } else if(layEvent === 'edit'){ //编辑
			     openUpdateMessage(data);
			   } else if(layEvent === 'viewImage'){ //查看大图
                  showCarImage(data);
              }
			 });
			
			var url;
			var mainIndex;
			//打开添加页面
			function openAddMessage(){
				mainIndex=layer.open({
					type:1,
					title:'添加礼品',
					content:$("#saveOrUpdateDiv"),
					area:['900px','520px'],
					success:function(index){
						//清空表单数据       
						$("#dataFrm")[0].reset();
                        /*加载默认图片  给src赋值*/
                        $('#showCarImg').attr('src',"<%=basePath%>/file/downloadShowFile.action?path=images/defaultcarimg.jpg");
                        /*显示出来*/
                        $('#carimg').val("images/defaultcarimg.jpg");
                        /*移除修改设置的礼品型号只可读设置*/
                        $('#giftnumber').removeAttr("readonly");
                        url="<%=basePath%>/message/addMessage.action";
					}
				});
			}
			//打开修改页面
			function openUpdateMessage(data){
				mainIndex=layer.open({
					type:1,
					title:'修改礼品',
					content:$("#saveOrUpdateDiv"),
					area:['800px','400px'],
					success:function(index){
					    /*已经反选到form中所有数据,放在form表单中*/
						form.val("dataFrm",data);
                        /*加载默认图片  给src赋值*/
                        $('#showCarImg').attr('src',"<%=basePath%>/file/downloadShowFile.action?path="+data.carimg);
                       /*把主键礼品型号设置成只读，不能修改*/
                        $('#giftnumber').attr("readonly","readonly");
                        url="<%=basePath%>/message/updateMessage.action";
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
			
			//批量删除
			function deleteBatch(){
				//得到选中的数据行
				var checkStatus = table.checkStatus('messageTable');
			    var data = checkStatus.data;
			    var params="";
			    $.each(data,function(i,item){
			    	if(i==0){
			    		params+="ids="+item.giftnumber;
			    	}else{
			    		params+="&ids="+item.giftnumber;
			    	}
			    });
			    layer.confirm('真的删除选中的这些礼品吗', function(index){
				       //向服务端发送删除指令
				       $.post("<%=basePath%>/message/deleteBatchMessage.action",params,function(res){
				    	   layer.msg(res.msg);
				    	    //刷新数据 表格
							tableIns.reload();
				       })
				     }); 
			}

            //拖拽上传
            <%--upload.render({--%>
            <%--    elem: '#carimgDiv'--%>
            <%--    ,url:"<%=basePath%>/file/uploadFile.action"//改成您自己的上传接口--%>
            <%--    ,multipart:true--%>
            <%--    ,done: function(res){--%>
            <%--        layer.msg('上传成功');--%>
            <%--        // layui.$('#uploadDemoView').removeClass('layui-hide').find('img').attr('src', res.files.file);--%>
            <%--        console.log(res)--%>
            <%--    }--%>
            <%--});--%>
			//上传图片
			upload.render({
				elem: '#carimgDiv',
				url:"<%=basePath%>/file/uploadFile.action",
				method : "post",  //此处是为了演示之用，实际使用中请将此删除，默认用post方式提交
                multiple: true,
                acceptMime:'images/!*',
				// field:'mf',
				done: function(res, index, upload){
				/*	var num = parseInt(4*Math.random());  //生成0-4的随机数，随机显示一个头像信息*/
                    /* 给src赋值,展现出来*/
                    $('#showCarImg').attr('src',"<%=basePath%>/file/downloadShowFile.action?path="+res.data.src);
					/*给input中赋值，到时候添加时候传过去*/
					$('#carimg').val(res.data.src);
					$('#carimgDiv').css("background","#fff");
				}
			});
			//查看大图
            function showCarImage(data) {
                /*弹出层*/
                mainIndex=layer.open({
                    type:1,
                    title:data.giftname+'的礼品图片',
                    content:$("#viewCarImageDiv"),
                    area:['800px','550px'],
                    success:function(index){
                            /*给img 的src赋值*/
                        $('#view_carimg').attr('src',"<%=basePath%>/file/downloadShowFile.action?path="+data.carimg);

                    }
                });

            }
		});
	</script>
</body>
</html>