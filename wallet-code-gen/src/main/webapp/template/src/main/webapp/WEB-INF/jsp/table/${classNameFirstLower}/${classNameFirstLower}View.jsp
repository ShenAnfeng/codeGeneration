<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<%@page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">

	$(document).ready(function() {
		
	})

</script>

<div class="titleDetail">${table.remarks }--详细信息</div>
	<div class="selectContentDiv">
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
					<#if column.javaType == "java.util.Date">
					<fmt:formatDate value='${r"${"}model.${column.columnNameFirstLower}${r"}"}' pattern='yyyy-MM-dd HH:mm:ss' />
					<#else>
					${r"${"}model.${column.columnNameFirstLower}${r"}"}
					</#if>
				</td>									 
			</tr>
			</#if>
			</#list>
		</table>
		<table id="user_table2" border="0" width="100%">
			<tr>
	        	<td align="center"> 
	        		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="_close();">关  闭</a>
	        	</td>
	        </tr>
		</table>
	</div>
</div>
