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
		<jsp:include page="/jsp/comm/topbar.jsp" flush="true" />
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>

				<jsp:include page="/jsp/comm/sidebar.jsp" flush="true" />

				<div class="main-content">
	

					<div class="page-content">
						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								<div class="error-container">
									<div class="well">
										<h1 class="grey lighter smaller">
											<span class="blue bigger-125">
												<i class="icon-sitemap"></i>
												404
											</span>
											页面无法找到
										</h1>

										<hr />
										<h3 class="lighter smaller">你所访问的资源无法找到或系统正在开发中</h3>

										<div>
											

											<div class="space"></div>
											<h4 class="smaller">您可以使用下面的方法尝试:</h4>

											<ul class="list-unstyled spaced inline bigger-110 margin-15">
												<li>
													<i class="icon-hand-right blue"></i>
													请检查所访问的URL地址
												</li>

												<li>
													<i class="icon-hand-right blue"></i>
													重新点击相关页面
												</li>

												<li>
													<i class="icon-hand-right blue"></i>
													联系系统供应商
												</li>
											</ul>
										</div>

										<hr />
										<div class="space"></div>


									</div>
								</div>
								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->

			</div><!-- /.main-container-inner -->

		</div><!-- /.main-container -->

</body>
</html>
