<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%
	String contextPath = request.getContextPath();
%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%-- <c:set var="title" value=""/> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script src='${ctx}/js/jquery-easyui-1.4.2/jquery.min.js'
	type="text/javascript"></script>
<script src="${ctx}/js/jquery-easyui-1.4.2/jquery.easyui.min.js"
	type="text/javascript"></script>
<link href="${ctx}/js/jquery-easyui-1.4.2/themes/default/easyui.css"
	rel="stylesheet" type="text/css" />
<link href="${ctx}/js/jquery-easyui-1.4.2/themes/icon.css"
	rel="stylesheet" type="text/css" />
<script src="${ctx}/js/jquery-easyui-1.4.2/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<script src="${ctx}/js/utils/common.js" type="text/javascript"></script>
</head>