<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<%@page language="java" pageEncoding="UTF-8"%>

<script type="text/javascript">

	$(document).ready(function() {
		
	});

	function _submit() {
		$('#editForm').form('submit', {
		    url:'${classNameLower}Controller/${classNameLower}EditSubmit', 
		    ajax:true,
		    onSubmit:function(){
				var isValid = $(this).form('validate');
				if (isValid){
					$.messager.progress();	// 显示进度条
				}
				return isValid;	// 返回false终止表单提交
			},
		    success:function(msg){
		    	$.messager.progress('close');
		    	var data = eval('(' + msg + ')');
		    	if (data.retCode == '0') {
					$.messager.show({
						title : '提示',
						msg : '操作成功！'
					});
					// 提交成功关闭窗口
			    	_cancel();
				} else {
					$.messager.alert('警告','提交失败,原因：' + data.retMsg, 'warning',function(){}); 
				}
		    },
		    onLoadError:function() {
		    	$.messager.progress('close');
		    	$.messager.alert('警告','提交失败,请联系系统管理员~','warning',function(){});
		    }
		}); 
	}
</script>

<div class="titleDetail">${table.remarks }--编辑</div>
<div class="selectContentDiv">
	<form action="${classNameLower}Controller/${classNameLower}EditSubmit" method="post" id="editForm" name="editForm">
		<table class="statTable" id="user_table1">
			<input type=hidden name=id value="${r"${"}model.id${r"}"}">
			<#list table.columns as column>
			<#if column.columnNameFirstLower!="id">
			<tr>
				<td class="detailTd">
					<#if column.remarks=="">
					${column.constantName}
					<#else>
					${column.remarks}
					</#if>
				</td>
				<td class="nameTd">
					<input class="easyui-textbox" id="${column.columnNameFirstLower}" name="${column.columnNameFirstLower}" data-options="required: true,validType:['maxLength[256]']" 
						value="${r"${"}model.${column.columnNameFirstLower}${r"}"}">
				</td>
			</tr>
			</#if>
			</#list>
		</table>
		<table id="user_table2" border="0" width="100%">
			<tr>
	        	<td align="right" style="padding-right: 50px;"> 
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="_submit();">确认提交</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="_close();">放弃编辑</a>
	        	</td>
	        </tr>
		</table>
	</form>
</div>
 


