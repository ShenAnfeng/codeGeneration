<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.manager.pojo.GenTable"%>
<%@ page import="java.util.List"%>
<%
	List<GenTable> genTableList = (List<GenTable>) request.getAttribute("genTableList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>表列表</title>
<%-- <link rel="stylesheet" type="text/css"
	href="${ctxPath}/pub/css/insure/css/index.css">
<link rel="stylesheet" type="text/css" href="${ctxPath}/pub/css/insure/css/index.css">
<link rel="stylesheet" type="text/css" href="${ctxPath}/pub/lib/jquery-easyui-1.3.4/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctxPath}/pub/lib/jquery-easyui-1.3.4/themes/icon.css">
<script type="text/javascript" src="${ctxPath}/pub/lib/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctxPath}/pub/lib/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctxPath}/pub/lib/jquery-easyui-1.3.4/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctxPath}/pub/js/insure/liandong.js"></script> --%>

<script src='../js/jquery1.11/jquery.min.js' type="text/javascript"></script>
<script src='../js/jquery-easyui-1.4.2/jquery.min.js' type="text/javascript"></script>
<script src="../js/jquery-easyui-1.4.2/jquery.easyui.min.js" type="text/javascript"></script>
<link href="../js/jquery-easyui-1.4.2/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="../js/jquery-easyui-1.4.2/themes/icon.css" rel="stylesheet" type="text/css" />
<script src="../js/jquery-easyui-1.4.2/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){
	//初始化保单表格
	<%
		for (GenTable genTable : genTableList) {
	%>
		var valueTable = "<%=genTable.getName()%>";
		$("#insureSepTable").append("<tr><td><input type='checkbox' name='checkTable' value='"+valueTable+"'></td><td><%=genTable.getName()%></td></tr>");
	<%
		}
	%>
	
	// 全选
	/* $("#allCheck").click(function(){
	     $("input[name='checkTable']").attr("checked",$(this).attr("checked"));
	}); */
	
	//全选
     /* $("#allCheck").click(function(){
        $('[name=checkTable]:checkbox').attr("checked", this.checked );
    }); */
    $('[name=checkTable]:checkbox').click(function(){
        //定义一个临时变量，避免重复使用同一个选择器选择页面中的元素，提升程序效率。
        var $tmp=$('[name=checkTable]:checkbox');
        //用filter方法筛选出选中的复选框。并直接给CheckedAll赋值。
        $('#allCheck').attr('checked', $tmp.length==$tmp.filter(':checked').length);
    });

});

	function checkAll(){
		$('[name=checkTable]:checkbox').attr("checked", this.checked);
	}


</script>
</head>
<body>
	<div class="main">
		<h3 class="title">数据库物理表列表</h3>
		<div class="content">
			<div class="c-title">
				<table>
					<tr>
						<td>包路径：</td>
						<td><input type="text" id ='packageParh' name='packageParh' value=''/></td>
						<td><input type="button" id ='genCode' name='genCode' value='生成代码'/></td>
					</tr>
				</table>
			</div>
			<table class="table3" id="insureSepTable" border="1" cellspacing="0">
				<tr>
					<th><input type='checkbox' id ='allCheck' name='allCheck' value='allCheck' onclick />全选</th>
					<th>表名称</th>
				</tr>
			</table>
			<br> <br> <br> <br>
			<div class="btn btn1">
				<a onclick="window.close();" href="#">关闭</a>
			</div>
		</div>
	</div>
</body>
</html>