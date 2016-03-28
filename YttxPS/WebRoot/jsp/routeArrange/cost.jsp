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
								<li><a href="javascript:void(0)">线路资源</a></li>
								<li><a href="javascript:void(0)">${res.fsName}</a></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<div class="space-10"></div>
						<input type="hidden" name="fsId" id="fsId" value="${res.fsId}" />
						<div id="calendar"></div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="/js/fullcalendar.min.js"></script>
	<script src="/js/bus/routeArrange/cost.js"></script>
</body>
</html>
