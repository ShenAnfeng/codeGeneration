<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<%@page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">

	$(document).ready(function() {
		
	})

</script>

	<div class="easyui-layout" fit="true">
		<div region="center" border="false"
			 style="overflow:auto;padding:30px;padding-left:60px;background:#fff;border:1px solid #ccc;">
			<table class="statTable" id="user_table1">
				<#list table.columns as column>
					<#if column.columnNameFirstLower!="id">
						<tr>
							<#if column.remarks=="">
								<td class="detailTd">${column.constantName}</td>
								<#else>
									<td class="detailTd">${column.remarks}</td>
							</#if>
							<td class="nameTd" id="${column.columnNameFirstLower}" width="300px">
								<%--<#if column.javaType == "java.util.Date">
									<fmt:formatDate value='${r"${"}model.${column.columnNameFirstLower}${r"}"}'
													pattern='yyyy-MM-dd HH:mm:ss'/>
									<#else>
										${r"${"}model.${column.columnNameFirstLower}${r"}"}
								</#if>--%>
								<#if column.javaType == "java.util.Date">
									<input class="easyui-datebox" id="createTime" name="createTime" disabled
										   data-options="readonly:true,formatter:formatter_date,parser:paserDateTime"
										   value="${r"${"}model.${column.columnNameFirstLower}${r"}"}">
								<#else>
									<input class="easyui-textbox" data-options="readonly:true"
										   value="${r"${"}model.${column.columnNameFirstLower}${r"}"}">
								</#if>


							</td>
						</tr>
					</#if>
				</#list>
			</table>
		</div>
		<div region="south" border="false" style="text-align:right;padding:5px 5px;">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="_close();">关闭</a>
		</div>
	</div>
