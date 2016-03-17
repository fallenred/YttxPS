<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt'%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8" />
<title>后台管理系统</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<jsp:include page="/jsp/comm/css.jsp" flush="true" />
<jsp:include page="/jsp/comm/scripts.jsp" flush="true" />
<link rel="stylesheet" href="/css/daterangepicker.css" />
<script src="/js/date-time/moment.min.js"></script>
<script src="/js/date-time/daterangepicker.min.js"></script>

<script src="/js/handlebars.js"></script>
</head>
<body class="no-skin">
	<jsp:include page="/jsp/comm/topbar.jsp" flush="true" />
	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			}catch(e){}
		</script>
		
		<div class="main-container-inner">
			<a class="menu-toggler" id="menu-toggler" href="#"> 
				<span class="menu-text"></span>
			</a>
			<jsp:include page="/jsp/comm/sidebar.jsp" flush="true" />
			
			<div class="main-content">
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
						try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
					</script>
					<ul class="breadcrumb">
						<li>
							<a href="/cOrder/page.htm">结算管理</a>
						</li>
						<li>
							<a href="/cOrder/page.htm">全部订单</a>
						</li>
							<li>${order.name}(${order.no})</li>
					</ul>
					<div class="nav-search" id="nav-search">
						<form class="form-search">
							<span class="input-icon">
								<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off">
								<i class="icon-search nav-search-icon"></i>
							</span>
						</form>
					</div>
				</div>
	
				
				<!-- 右边页面的主页面 -->
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<jsp:include page="orderContent.jsp" flush="true"/>
						</div>
						<div class="space-10"></div>
					</div>
					<div class="row">
						<div class="space-10"></div>
						<div class="col-sm-offset-8 col-sm-1">
							<c:if test="${order.stat==32}">
								<button class="btn btn-warning .btn-lg disabled" type="button">
									已入结算单
								</button>
							</c:if>
							<c:if  test="${order.stat==8}">
								<form method="post" action="/cOrder/prodStat.htm">
									<input type="hidden" value="${order.no}" name="orderId">
									<button class="btn btn-info .btn-lg " type="submit">
										生成结算单
									</button>
								</form>
								
							</c:if>
						</div>
					</div>
				</div>
				<!-- 右边页面的主页面 -->
			</div>
		</div>
	</div>		
	<!-- /.main-container -->
	<script src="/js/bus/corder/corder.js"></script>
</body>
</html>
