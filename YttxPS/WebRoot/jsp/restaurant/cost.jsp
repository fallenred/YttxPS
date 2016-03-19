<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8" />
<title>后台管理系统</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<jsp:include page="/jsp/comm/css.jsp" flush="true" />
<jsp:include page="/jsp/comm/scripts.jsp" flush="true" />
<link href="/css/fullcalendar.css" rel="stylesheet">
</head>
<body class="navbar-fixed yt-skin">
	<div class="main-container" id="main-container">
		<div class="main-content">
			<div class="col-xs-10 col-xs-offset-1">
				<div class="row">
					<div class="col-xs-12">
						<div class="space-10"></div>
						<div class="breadcrumbs" id="breadcrumbs">
							<ul class="breadcrumb">
								<li><a href="javascript:void(0)">菜单资源</a></li>
								<li><a href="javascript:void(0)">${res.name}</a></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="space-10"></div>
					<div class="col-xs-1 col-xs-offset-10">
						<button id="configPrice" type="button" class="btn btn-primary">菜单资源定价</button>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<div class="space-10"></div>
						<input type="hidden" name="no" id="no" value="${res.no}" />
						<div id="calendar"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 模态框（菜单消费选项定价） -->
	<div class="modal fade" id="priceModal" tabindex="-1" role="dialog" 
		aria-labelledby="priceModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 1024px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="priceModalLabel">菜单消费选项定价</h4>
				</div>
					
				<div class="modal-body">
					<iframe id="priceIframe" width="100%" height="500px" frameborder="0" scrolling="no"></iframe>
				</div>
			</div>
		</div>
	</div>
	<script src="/js/fullcalendar.min.js"></script>
	<script src="/js/bus/restaurant/cost.js"></script>
</body>
</html>
