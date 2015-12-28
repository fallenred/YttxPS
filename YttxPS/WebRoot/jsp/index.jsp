<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="utf-8" />
	<meta name="keywords" content="" />
	<meta name="description" content="" />
	<jsp:include page="/jsp/comm/css.jsp" flush="true" />
	
	<jsp:include page="/jsp/comm/scripts.jsp" flush="true" />
	
    <title>后台管理系统</title>
</head>
<body class="login-layout">
	<div class="main-container">
		<div class="main-content">
			<div class="row">
				<div class="col-sm-10 col-sm-offset-1">
					<div class="login-container">
						<div class="center">
							<h1>
								<i class="icon-leaf green"></i> <span
									class="white">后台管理系统</span>
							</h1>
							<h4 class="blue">&copy; YTTX</h4>
						</div>

						<div class="space-6"></div>

						<div class="position-relative">
							<div id="login-box"
								class="login-box visible widget-box no-border">
								<div class="widget-body">
									<div class="widget-main">
										<h4 class="header blue lighter bigger">
											<i class="icon-coffee green"></i> 请输入您的登录信息
										</h4>

										<div class="space-6"></div>

										<form action="login.htm" method="post" id="login-form">
											<fieldset>
												<label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														type="text" class="form-control" placeholder="用户名" id="userid" name="userid" />
														<i class="icon-user"></i> </span> </label> <label class="block clearfix">
													<span class="block input-icon input-icon-right"> <input
														type="password" class="form-control"
														placeholder="密码" id="password" name="password" /> <i class="icon-lock"></i> </span> </label>

												<div class="space"></div>

												<div class="clearfix">
													<label class="inline"> <input type="checkbox" id="rememberflag" name="rememberflag"
														class="ace" /> <span class="lbl"> 记住账号</span> </label>

													<button type="button" id="loginbtn"
														class="width-35 pull-right btn btn-sm btn-primary">
														<i class="icon-key"></i> 登录
													</button>
												</div>

												<div class="space-4"></div>
											</fieldset>
										</form>
											<div class="login-msg center">
												<span class="bigger-150 red">${message}</span>
											</div>
										
									</div>
									<!-- /widget-main -->

									<div class="toolbar clearfix">
										<div>
											<a href="#" onclick="show_box('forgot-box'); return false;"
												class="forgot-password-link"> <i class="icon-arrow-left"></i>
												忘记密码?</a>
										</div>

										
									</div>
								</div>
								<!-- /widget-body -->
							</div>
							<!-- /login-box -->

							<div id="forgot-box" class="forgot-box widget-box no-border">
								<div class="widget-body">
									<div class="widget-main">
										<h4 class="header red lighter bigger">
											<i class="icon-key"></i> 找回密码
										</h4>

										<div class="space-6"></div>
										<p>请输入申报时的电子邮箱或直接联系系统管理员</p>

										<form method="post" id="forgot-form" action="forgot.htm">
											<fieldset>
												<label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														type="email" class="form-control" placeholder="电子邮箱" />
														<i class="icon-envelope"></i> </span> </label>

												<div class="clearfix">
													<button type="button"
														class="width-35 pull-right btn btn-sm btn-danger">
														<i class="icon-lightbulb"></i> 找回密码！
													</button>
												</div>
											</fieldset>
										</form>
									</div>
									<!-- /widget-main -->
									<div class="toolbar center">
										<a href="#" onclick="show_box('login-box'); return false;"
											class="back-to-login-link"> 返加登录 <i
											class="icon-arrow-right"></i> </a>
									</div>
								</div>
								<!-- /widget-body -->
							</div>
							<!-- /forgot-box -->
						</div>
						<!-- /position-relative -->
					</div>
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->
		</div>
	</div>
	<!-- /.main-container -->

	

	<!-- inline scripts related to this page -->
	<script type="text/javascript">
		function show_box(id) {
			jQuery('.widget-box.visible').removeClass('visible');
			jQuery('#' + id).addClass('visible');
		}
	</script>
	<script src="/js/bus/jquery.cookie.js"></script>
	<script src="/js/bus/sha.js"></script>
    <script src="/js/bus/login.js"></script>
    
</body>
</html>



