<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>后台管理系统</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<jsp:include page="/jsp/comm/css.jsp" flush="true" />
<jsp:include page="/jsp/comm/scripts.jsp" flush="true" />
</head>
<body class="no-skin">
	<jsp:include page="/jsp/comm/topbar.jsp" flush="true" />
	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			}
		</script>

		<div class="main-container-inner">
			<a class="menu-toggler" id="menu-toggler" href="#"> <span
				class="menu-text"></span>
			</a>

			<jsp:include page="/jsp/comm/sidebar.jsp" flush="true" />

			<div class="main-content">
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
						try {
							ace.settings.check('breadcrumbs', 'fixed')
						} catch (e) {
						}
					</script>
					<!--
						<ul class="breadcrumb">
							<li>
								<i class="fa fa-home home-icon"></i>
								<a href="#">Home</a>
							</li>

							<li>
								<a href="#">Other Pages</a>
							</li>
							<li class="active">Blank Page</li>
						</ul> .breadcrumb -->

					<div class="nav-search" id="nav-search">
						<form class="form-search">
							<span class="input-icon"> <input type="text"
								placeholder="Search ..." class="nav-search-input"
								id="nav-search-input" autocomplete="off" /> <i
								class="icon-search nav-search-icon"></i>
							</span>
						</form>
					</div>
					<!-- #nav-search -->
				</div>

				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->

							<!-- PAGE CONTENT ENDS -->
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.page-content -->
			</div>
			<!-- /.main-content -->

		</div>
		<!-- /.main-container-inner -->

	</div>
	<!-- /.main-container -->

</body>
</html>
