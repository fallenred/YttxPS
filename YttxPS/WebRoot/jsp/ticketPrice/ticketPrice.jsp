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
<body class="no-skin">
	<div class="main-container" id="main-container">
		<div class="main-content">
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
						<!-- PAGE CONTENT BEGINS -->
						<table id="grid-table"></table>
						<div id="grid-pager"></div>

						<!-- 模态框（新增） -->
						<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="addModalLabel" aria-hidden="true">
							<div class="modal-dialog" style="width: 1024px;">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
										<h4 class="modal-title" id="addModalLabel">新增门票价格</h4>
									</div>
									<div class="modal-body">
										<iframe id="addIframe" width="100%" height="600px" frameborder="0" scrolling="no"></iframe>
									</div>

									<!-- /.modal-content -->
								</div>
								<!-- /.modal-dialog -->
							</div>
							<!-- /.modal -->
						</div>

						<!-- 模态框（修改） -->
						<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
							<div class="modal-dialog" style="width: 1024px;">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
										<h4 class="modal-title" id="addModalLabel">修改门票价格</h4>
									</div>
									<div class="modal-body">
										<iframe id="editIframe" width="100%" height="600px" frameborder="0" scrolling="no"></iframe>
									</div>

									<!-- /.modal-content -->
								</div>
								<!-- /.modal-dialog -->
							</div>
							<!-- /.modal -->
						</div>

						<!-- 模态框（查询） -->
						<div class="modal fade" id="showModal" tabindex="-1" role="dialog" aria-labelledby="showModalLabel" aria-hidden="false">
							<div class="modal-dialog" style="width: 1024px;">
								<div class="modal-content">

									<!-- /.modal-content -->
								</div>
								<!-- /.modal-dialog -->
							</div>
							<!-- /.modal -->
						</div>

						<!-- 图片上传 -->
						<div class="modal fade" id="picModal" tabindex="-1" role="dialog" aria-labelledby="picModalLabel" aria-hidden="true">
							<div class="modal-dialog" style="width: auto; height: auto">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
										<h4 class="modal-title" id="picModalLabel">图片管理</h4>
									</div>
									<div class="modal-body">
										<iframe id="picIframe" width="100%" height="500px" frameborder="0" scrolling="yes"></iframe>

									</div>

									<!-- /.modal-content -->
								</div>
								<!-- /.modal-dialog -->
							</div>
							<!-- /.modal -->
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
	<script src="/js/bus/ticketPrice/ticketPrice.js"></script>
</body>
</html>
