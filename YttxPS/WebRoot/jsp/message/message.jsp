<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
			<a class="menu-toggler" id="menu-toggler" href="#"> <span class="menu-text"></span>
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

					<div class="nav-search" id="nav-search">
						<form class="form-search">
							<span class="input-icon">
								<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
								<i class="ace-icon fa fa-search nav-search-icon"></i>
							</span>
						</form>
					</div>
					<!-- #nav-search -->
				</div>

				<!-- 右边页面的主页面 -->
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<div class="widget-box">
								<div class="widget-body">
									<div class="widget-main">
										<!-- 选项卡开始 -->
										<div class="tabbable">
											<ul class="nav nav-tabs" id="tab">
												<li class="active">
													<input type="hidden" id="msgStat" value="0">
													<a data-toggle="tab" id="unreadMsg" onclick="refreshMsg(0)">
														<i></i>未读消息
													</a>
												</li>
												<li>
													<a data-toggle="tab" id="readedMsg" onclick="refreshMsg(1)">
														<i></i>已读消息
													</a>
												</li>
											</ul>
										</div>
										<!-- 选项卡 结束-->
									</div>
								</div>
							</div>
							<table id="grid-table"></table>
							<div id="grid-pager"></div>

							<!-- 模态框（查询） -->
							<div class="modal fade" id="showModal" tabindex="-1" role="dialog" aria-labelledby="showModalLabel" aria-hidden="false">
								<div class="modal-dialog" style="width: 512px;">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
											<h5 class="modal-title" id="showModalLabel">查看消息</h5>
										</div>
										<div class="modal-body">
											<iframe id="showIframe" width="100%" height="160px" frameborder="0" scrolling="no"></iframe>
										</div>
									</div>
								</div>
							</div>
							
							<!-- 删除 -->
							<!-- 模态框（查询） -->
							<div class="modal fade" id="delModal" tabindex="-1" role="dialog" aria-labelledby="delModalLabel" aria-hidden="false">
								<div class="modal-dialog" style="width: 400px;">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
											<h4 class="modal-title" id="delModalLabel">删除警告</h4>
										</div>
										<div class="modal-body">
											<iframe id="delIframe" width="100%" height="200px" frameborder="0" scrolling="no"></iframe>
										</div>
										<!-- /.modal-content -->
									</div>
									<!-- /.modal-dialog -->
								</div>
								<!-- /.modal -->
							</div>
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
	<script src="/js/bus/message/message.js"></script>
</body>
</html>
