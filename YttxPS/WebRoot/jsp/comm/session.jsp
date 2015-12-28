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
			
				<div class="main-content">
					
					<div class="page-content">
						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								
								
								会话过期,请重新登录

								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->
			</div><!-- /.main-container-inner -->

		</div><!-- /.main-container -->

</body>
</html>
