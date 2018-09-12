<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>表列表</title>
<script type="text/javascript">
	$(function() {
		$('#u_base_menu').datagrid({
			rownumbers : true,
			pagination : true,
			pageSize : 30,
			singleSelect : false,
			toolbar : '#search_menu',
			showFooter : true,
			fitColumns : true,
			loadMsg : '数据加载中....请稍候',
			method : 'post',
			url : "",
			sortOrder : 'desc',
			fit : true,
			remoteSort : false,
			onLoadError : '数据加载错误',
			columns : [ [ {
				field : 'ck',
				checkbox : true
			}, {
				field : 'tableName',
				title : '表名称',
				width : 120,
				align : 'center'
			}, {
				field : 'comments',
				title : '表注释',
				width : 120,
				align : 'center'
			} ] ]

		});
	});

	//默认库查询
	var find_query = function() {
		var opts = $("#u_base_menu").datagrid("options");
		opts.url = "${ctx}/QDateBaseTable/query.do";
		$("#u_base_menu").datagrid('load',
				serializeObject($("#search_conditions")));
	}

	//动态库查询
	var refreshQuery = function() {
		var isRight = checkDateSource();
		if (isRight) {
			var opts = $("#u_base_menu").datagrid("options");
			opts.url = "${ctx}/QDateBaseTable/refreshQuery.do";
			var refreshTableName = $('#tableName').val();
			$('#refreshTableName').val(refreshTableName);
			$("#u_base_menu").datagrid('load',
					serializeObject($("#gencode_conditions")));
		}
	}

	//校验数据库连接填写
	var checkDateSource = function() {
		var jdbc_driver = $('#jdbc_driver').combobox('getValue');
		if (jdbc_driver == null || jdbc_driver == "") {
			$.messager.alert("消息", "jdbc_driver不能为空！");
			return false;
		}
		
		var jdbc_url = $('#jdbc_url').val();
		if (jdbc_url == null || jdbc_url == "") {
			$.messager.alert("消息", "jdbc_url不能为空！");
			return false;
		}

		var jdbc_username = $('#jdbc_username').val();
		if (jdbc_username == null || jdbc_username == "") {
			$.messager.alert("消息", "jdbc_username不能为空！");
			return false;
		}

		var jdbc_password = $('#jdbc_password').val();
		if (jdbc_password == null || jdbc_password == "") {
			$.messager.alert("消息", "jdbc_password不能为空！");
			return false;
		}

		var jdbc_schema = $('#jdbc_schema').val();
		if (jdbc_schema == null || jdbc_schema == "") {
			$.messager.alert("消息", "jdbc_schema不能为空！");
			return false;
		}
		return true;
	}
	
	//生成代码
	function gencode() {
		var checkedItems = $('#u_base_menu').datagrid('getChecked');
		var packageName = $('#packageName').val();
		if (packageName == null || packageName == "") {
			$.messager.alert("消息", "包路径不能为空！");
			return false;
		}
		var isRight = checkDateSource();
		if (!isRight) {
			return false;
		}

		var jdbc_url = $('#jdbc_url').val();
		var jdbc_driver = $('#jdbc_driver').combobox('getValue');
		var jdbc_username = $('#jdbc_username').val();
		var jdbc_password = $('#jdbc_password').val();
		var jdbc_schema = $('#jdbc_schema').val();
		if (checkedItems == null || checkedItems.length < 1) {
			$.messager.alert("消息", "请至少选择一行记录");
		} else {
			$.messager.progress({ 
			    title: '温馨提示', 
			    msg: '正在生成代码...'
			}); 
			var tableNames = "";
			if (checkedItems.length > 0) {
				for (var i = 0; i <= checkedItems.length - 1; i++) {
					if (i == checkedItems.length - 1) {
						tableNames = tableNames + checkedItems[i].tableName;
					} else {
						tableNames = tableNames + checkedItems[i].tableName
								+ ',';
					}
				}
			}
			$.ajax({
				type : "POST",
				url : "${ctx}/QDateBaseTable/genCode.do",
				data : {
					packageName : packageName,
					tableNames : tableNames,
					jdbc_url : jdbc_url,
					jdbc_driver : jdbc_driver,
					jdbc_username : jdbc_username,
					jdbc_password : jdbc_password,
					jdbc_schema : jdbc_schema
				},
				cache : false,
				success : function(data) {
					$.messager.progress('close');
					var date = eval('(' + data + ')');
					var returnMsg = date.returnMsg;
					var returnCode = date.returnCode;
					if (returnCode == "success") {
						var url = '${ctx}/QDateBaseTable/downFile.do?timeTip='+returnMsg;
						window.location.href = url;
					} else {
						$.messager.alert("消息", returnMsg,'error');
					}
				},
				beforeSend : function(){
				}
			});

		}
	}

	//连接测试
	function testConnection() {
		var isRight = checkDateSource();
		if (!isRight) {
			return false;
		}
		var jdbc_url = $('#jdbc_url').val();
		var jdbc_driver = $('#jdbc_driver').combobox('getValue');
		var jdbc_username = $('#jdbc_username').val();
		var jdbc_password = $('#jdbc_password').val();
		var jdbc_schema = $('#jdbc_schema').val();
		$.ajax({
			type : "POST",
			url : "${ctx}/QDateBaseTable/testConnection.do",
			data : {
				jdbc_url : jdbc_url,
				jdbc_driver : jdbc_driver,
				jdbc_username : jdbc_username,
				jdbc_password : jdbc_password,
				jdbc_schema : jdbc_schema
			},
			async : false,
			cache : false,
			success : function(data) {
				var dateNew = eval('(' + data + ')');
				$.messager.alert("消息", dateNew);
			}
		});
	}

	//下载
	function downFile() {
		window.open("${ctx}/QDateBaseTable/downFile.do")
	}
</script>
</head>
<body>
	<div class="easyui-panel" title="数据库物理表" data-options="fit:true">
		<table id="u_base_menu"></table>
		<div id="search_menu">
			<form id="search_conditions">
				<table>
					<tr>
						<td style="padding-left: 20px;">表名称：</td>
						<td><input type="text" id="tableName" name="tableName" /></td>
						<td style="padding-left: 20px;"><a href="javascript:void(0)"
							class="easyui-linkbutton" iconCls="icon-search"
							onclick='find_query();'>默认库查询 </a></td>
					</tr>
				</table>
			</form>
			<form id="gencode_conditions">
				<table>
					<tr>
						<td style="padding-left: 20px;">包路径：</td>
						<td>
							<input type="hidden" id="refreshTableName" name="refreshTableName" />
							<input type="text" id="packageName" name="packageName" value="com.hrsf"
								class="easyui-validatebox" required="true" />
						</td>
						<td style="padding-left: 20px;">jdbc_driver：</td>
						<td><select class="easyui-combobox" id="jdbc_driver" name="jdbc_driver" style="width: 120px">
								<!-- <option value="">请选择</option> -->
								<option value="Oracle">Oracle</option>
								<option value="Mysql">Mysql</option>
						    </select>
					    </td>
						<td style="padding-left: 20px;">jdbc_url：</td>
						<td>
							<!-- <input type="text" id="jdbc_url" name="jdbc_url" value="192.168.112.175:1521:orcl"
								class="easyui-validatebox" required="true" /> -->
							<input type="text" id="jdbc_url" name="jdbc_url" value="192.168.112.175:3306/walletCodeGen"
								class="easyui-validatebox" required="true" />
						</td>
						<td style="padding-left: 20px;">jdbc_username：</td>
						<td><input type="text" id="jdbc_username"
							name="jdbc_username" class="easyui-validatebox" required="true" value="root"/></td>
						<td style="padding-left: 20px;">jdbc_password：</td>
						<td><input type="password" id="jdbc_password"
							name="jdbc_password" class="easyui-validatebox" required="true" value="Rs1mysql2op"/></td>
						<td style="padding-left: 20px;">jdbc_schema：</td>
						<td><input type="text" id="jdbc_schema" name="jdbc_schema"
							class="easyui-validatebox" required="true" value="walletCodeGen"/></td>
					</tr>
				</table>
			</form>
			<form id="gencode_button">
				<table>
					<tr>
						<td style="padding-left: 20px;"><a href="javascript:void(0)"
							class="easyui-linkbutton" iconCls="icon-tip"
							onclick='testConnection();'>连接测试 </a></td>
						<td style="padding-left: 20px;"><a href="javascript:void(0)"
							class="easyui-linkbutton" iconCls="icon-tip"
							onclick='refreshQuery();'>查询 </a></td>
						<td style="padding-left: 20px;"><a href="javascript:void(0)"
							class="easyui-linkbutton" iconCls="icon-tip" onclick='gencode();'>生成代码
						</a></td>
						<!-- <td style="padding-left:20px;"><a href="javascript:void(0)"
							class="easyui-linkbutton" iconCls="icon-tip"
							onclick='downFile();'>下载 </a></td> -->
					</tr>
				</table>
			</form>
		</div>
	</div>

</body>
</html>