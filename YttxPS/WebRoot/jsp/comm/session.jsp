<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
	<head>
		<meta charset="utf-8" />
		<title>后台管理系统</title>
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		<jsp:include page="/jsp/comm/css.jsp" flush="true" />
		<jsp:include page="/jsp/comm/scripts.jsp" flush="true" />
	</head>
	<body>
		<div class="navbar navbar-default" id="navbar">
			<script type="text/javascript">
				try {
					ace.settings.check('navbar', 'fixed')
				} catch (e) {
				}
			</script>
		
			<div class="navbar-container" id="navbar-container">
				<div class="navbar-header pull-left">
					<a href="#" class="navbar-brand"> <small> <i
							class="fa fa-leaf"></i> 平台管理系统  </small> </a>
					<!-- /.brand -->
				</div>
				<!-- /.navbar-header -->
		
				<div class="navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">
					</ul>
					<!-- /.ace-nav -->
				</div>
				<!-- /.navbar-header -->
			</div>
			<!-- /.container -->
		</div>
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<div class="main-container-inner">
				<div class="main-content">
					<div class="page-content">
						<div class="jumbotron">
						  <h1>会话超时,请重新登陆...</h1>
						  <p><a class="btn btn-primary btn-lg pull-right" href="/index.htm" role="button">返回登陆页面</a></p>
						</div>
					</div>
				</div>
			</div>
		</div>
</body>
</html>
