<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<%@page language="java" pageEncoding="UTF-8"%>

<script type="text/javascript">

	$(document).ready(function() {
		
	});

	function _submit() {
		$('#editForm').form('submit', {
		    url:'${classNameLower}Controller/${classNameLower}EditSubmit.do', 
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

	<div class="easyui-layout" fit="true">
		<div region="center" border="false"
			 style="overflow:auto;padding:30px;padding-left:60px;background:#fff;border:1px solid #ccc;">
			<form action="${classNameLower}Controller/${classNameLower}EditSubmit.do" method="post" id="editForm"
				  name="editForm">
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
									<input class="easyui-textbox" id="${column.columnNameFirstLower}"
										   name="${column.columnNameFirstLower}"
										   data-options="required: true,validType:['maxLength[${column.size}]']"
										   value="${r"${"}model.${column.columnNameFirstLower}${r"}"}">
								</td>
							</tr>
						</#if>
					</#list>
				</table>
			</form>
		</div>
		<div region="south" border="false" style="text-align:right;padding:5px 5px;">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-man" onclick="_submit()">确认修改</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="_close();">放弃编辑</a>
		</div>
	</div>
 


