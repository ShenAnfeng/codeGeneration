<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<%@page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/common/header.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
	<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
	<title>${table.remarks }-业务查询</title>
	<base href="<%=basePath%>">
    <script type="text/javascript">
    
    	$(document).ready(function() {
    		$('#resultTab').datagrid({
    			url : "${classNameLower}Controller/${classNameLower}List.do",
				title : '${table.remarks }-业务查询',
    			idField : 'id',
    			singleSelect : true,
    			multiSort : true,
    			striped : true,
    			fit : true,
    			//fitColumns : true,
    			toolbar : '#toolbar',
    			pagination : true,//分页控件
    			rownumbers : true, //行号
    			pageNumber : 1,
    			pageSize : 10,
    			columns:[[
						  {field:'cbx',checkbox:true,align:'center',halign:'center'},
						  <#list table.columns as column>
						  <#if column.columnNameFirstLower!="id">
						  <#if column.remarks=="">
						  {field:'${column.columnNameFirstLower}',width:80,align:'center',halign:'center',title:'${column.constantName}'},
						  <#else>
						  {field:'${column.columnNameFirstLower}',width:80,align:'center',halign:'center',title:'${column.remarks}'},
						  </#if>
						  </#if>
						  </#list>
						  {field:'id',hidden:true}
    			      ]],
    			onDblClickRow : function (rowIndex, rowData){
      				_view (rowIndex, rowData);
      			},
      			onLoadError : function () {
      				$.messager.alert('警告','数据初始化失败,请联系系统管理员~','warning',function(){});
      			},
    			loadMsg : "正在加载。。。"
    		});
    	});
    
		function _view(rowIndex, rowData) {
			var dialogDiv = "<div id='${classNameLower}Div'></div>";
			$(dialogDiv).dialog({
				title : '${table.remarks }-详细信息',
                width : document.body.scrollWidth-500,
                height : document.body.scrollHeight-200,
			    resizable : true,
			    href : "${classNameLower}Controller/${classNameLower}View.do?id=" + rowData.id,
			    modal : true,
			    onClose : function () {
			    	$('#${classNameLower}Div').dialog('destroy');
			    }
			}); 
		}
		
		function _edit() {
			var selRec = $('#resultTab').datagrid("getSelected");
			if (!selRec) {
				$.messager.alert('提示','请选择一条记录');
				return;
			}
			var dialogDiv = "<div id='${classNameLower}Div'></div>";
			$(dialogDiv).dialog({
			    title : '${table.remarks}-编辑',
                width : document.body.scrollWidth-500,
                height : document.body.scrollHeight-200,
			    resizable : true,
			    href : "${classNameLower}Controller/${classNameLower}Edit.do?id=" + selRec.id,
			    modal : true,
			    onClose : function () {
			    	$('#${classNameLower}Div').dialog('destroy');
			    }
			});
		}
		
		function _add() {
			var dialogDiv = "<div id='${classNameLower}Div'></div>";
			$(dialogDiv).dialog({
			    title : '${table.remarks}-新增',
                width : document.body.scrollWidth-500,
                height : document.body.scrollHeight-200,
			    resizable : true,
			    href : "${classNameLower}Controller/${classNameLower}Add.do",
			    modal : true,
			    onClose : function () {
			    	$('#${classNameLower}Div').dialog('destroy');
			    }
			});
		}
		
		function _query() {
			var params = $("#queryForm").serializeArray();
			var paramsArray = new Array();
			
			jQuery.each(params, function(i, param){
				paramsArray[param.name]=param.value;
			});
			
			$('#resultTab').datagrid('load', paramsArray);
		}
		
		function _reset() {
			$('#queryForm').form("reset");
		}
		
		function _del() {
			var selRec = $('#resultTab').datagrid("getSelected");
			if (!selRec) {
				$.messager.alert('提示','请选择一条记录');
				return;
			}
			$.messager.confirm('确认对话框', '请确认是否提交，提交后数据将删除。', function(r) {
				if (r){
					$.ajax({
						url : "${classNameLower}Controller/${classNameLower}Del.do",
						dataType : "json",
						type : "POST",
						//async : false,
						data : {
							id : selRec.id,
						},
						success : function (msg) {
							if (msg.retCode == '0') {
								$.messager.show({
									title : '提示',
									msg : '操作成功！'
								});
								$('#resultTab').datagrid('reload');
							} else {
								$.messager.alert('警告','提交失败,原因：' + msg.retMsg,'warning',function(){});
							}
						},
						error: function (XMLHttpRequest, textStatus, errorThrown) {
							$.messager.alert('警告','提交失败,请联系系统管理员~','warning',function(){}); 
					    }
					});
				}
			});
		}
		
		function _cancel() {
			$('#resultTab').datagrid('reload');
			$('#${classNameLower}Div').dialog('close');
		}
		
		function _close() {
			$('#${classNameLower}Div').dialog('close');
		}
	</script>
</head>

<body>
	<%--<div class="easyui-layout" data-options="fit:true">
		<div region="north" border="true" style="overflow: hidden;">
			<form action="${classNameLower}Controller/${classNameLower}List" method="post" id="queryForm" name="queryForm">
				<table class="statTable" border="0" width="100%" id="queryTab" style="margin-bottom: -10px;">
					<tr>
						<td width="5%" align="right" style="font-size:12px;">商户号： </td>
						<td width="7%" align="left">
							<input id="corpNoQuery" name="corpNo" class="easyui-textbox"> 
						</td>
						<td width="5%" align="right" style="font-size:12px;">收款账号：  </td>
						<td width="7%" align="left">
							<input id="recAccNoQuery" name="recAccNo" class="easyui-textbox">
						</td>
						<td width="5%" align="right" style="font-size:12px;">户名：</td>
						<td width="7%" align="left">
							<input id="recAccNameQuery" name="recAccName" class="easyui-textbox">
						</td>
					</tr>
					<tr>
						<td width="5%" align="right" style="font-size:12px;">金额：</td>
						<td width="7%" align="left">
							<input id="amountQuery" name="amount" class="easyui-textbox">
						</td>
						<td width="5%" align="right" style="font-size:12px;">日期：</td>
						<td width="7%" align="left">
							<input id="createTimeQuery" name="createTime" class="easyui-datebox">
						</td>
					</tr>
					<tr>
						<td align="right" colspan="6" style="padding-right: 50px;">
							<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="_query();">查 询</a>
							<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-undo" onclick="_reset();">重 置</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div region="center" border="false">
			<div id="toolbar">
				<div class="buttonDiv">
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="_add();">新增</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="_edit();">修改</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="_del();">删除</a>
				</div>
			</div>
			<table id="resultTab"></table>
		</div>
	</div>--%>

	<div id="toolbar" style="height: 20%">
		<div class="easyui-layout" data-options="fit:true">
			<div region="north" border="false" style="overflow: hidden;">
				<form action="${classNameLower}Controller/${classNameLower}List" method="post" id="queryForm" name="queryForm">
					<table class="statTable" border="0" width="100%" id="queryTab">
						<tr>
							<td width="10%" align="center" style="font-size:12px;">商户号： </td>
							<td width="7%" align="left">
								<input id="corpNoQuery" name="corpNo" class="easyui-textbox">
							</td>
							<td width="10%" align="center" style="font-size:12px;">收款账号：  </td>
							<td width="7%" align="left">
								<input id="recAccNoQuery" name="recAccNo" class="easyui-textbox">
							</td>
						</tr>
						<tr>
							<td width="10%" align="center" style="font-size:12px;">金额：</td>
							<td width="7%" align="left">
								<input id="amountQuery" name="amount" class="easyui-textbox">
							</td>
							<td width="10%" align="center" style="font-size:12px;">日期：</td>
							<td width="7%" align="left">
								<input id="createTimeQuery" name="createTime" class="easyui-datebox">
							</td>
							<td align="right" style="padding-right: 50px;">
								<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search"
								   onclick="_query();">查 询</a>
								<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-undo"
								   onclick="_reset();">重 置</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div region="center" border="false">
				<div class="buttonDiv">
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true"
					   onclick="_add();">新增</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true"
					   onclick="_edit();">修改</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
					   onclick="_del();">删除</a>
				</div>
			</div>
		</div>
	</div>


	<table id="resultTab"></table>
</body>
</html>
