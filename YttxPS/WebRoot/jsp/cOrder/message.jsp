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
							<a href="javascript:void(0)">结算单生成提示</a>
						</li>
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
							<div class="message-content" id="id-message-content">
								<div class="widget-box transparent">
									${message}
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>					
		</div>
	</div>
</body>
</html>
