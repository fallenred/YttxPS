<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8" />
<title>后台管理系统</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<jsp:include page="/jsp/comm/css.jsp" flush="true" />
<jsp:include page="/jsp/comm/scripts.jsp" flush="true" />
<style type="text/css">
</style>
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
			<input type="hidden" id="depNo" value="${sessionEntity.depNo }">
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
						<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Home</a>
						</li>

						<li><a href="#">Other Pages</a></li>
						<li class="active">Blank Page</li>
					</ul>
					.breadcrumb -->

					<div class="nav-search" id="nav-search">
						<form class="form-search">
							<span class="input-icon"> <input type="text"
								placeholder="Search ..." class="nav-search-input"
								id="nav-search-input" autocomplete="off" /> <i
								class="ace-icon fa fa-search nav-search-icon"></i>
							</span>
						</form>
					</div>
					<!-- #nav-search -->
				</div>

				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->
							<!-- 查询条件 -->
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a class="accordion-toggle" href="#collapseOne"
											data-toggle="collapse" data-parent="#accordion"> <i
											class="bigger-110 ace-icon fa fa-angle-down"
											data-icon-show="ace-icon fa fa-angle-right"
											data-icon-hide="ace-icon fa fa-angle-down"></i> &nbsp;查询条件
										</a>
									</h4>
								</div>

								<div class="panel-collapse collapse in" id="collapseOne">
									<div class="panel-body">
										<form class="form-horizontal" role="form" id="queryfield">
											<div class="form-group">
												<label class="col-sm-1 control-label no-padding-right"
													for="fsNo">订单编号</label>
												<div class="col-sm-2">
													<input class="form-control" maxlength="20" type="text" value="${orderID }" id="fsNo" placeholder="订单编号" />
												</div>
												<label class="col-sm-1 control-label no-padding-right"
													for="fsName">订单名称</label>
												<div class="col-sm-2">
													<input class="form-control" maxlength="150" type="text"
														id="fsName" placeholder="订单名称" />
												</div>
												<label class="col-sm-1 control-label no-padding-right"
													for="fsTaName">旅行社名称</label>
												<div class="col-sm-2">
													<input class="form-control" maxlength="100" type="text"
														id="fsTaName" placeholder="旅行社名称" />
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-1 control-label no-padding-right"
													for="fsId">旅行社账号</label>
												<div class="col-sm-2">
													<input class="form-control" maxlength="100" type="text"
														id="fsId" placeholder="旅行社账号" />
												</div>
												<label class="col-sm-1 control-label no-padding-right"
													for="fsType">线路类型</label>
												<div class="col-sm-2">
													<select class="form-control" id="fsType">
														<option value="">----线路类型----</option>
														<option value="02">专家线路</option>
														<option value="03">订制线路</option>
													</select>
												</div>
												<label class="col-sm-1 control-label no-padding-right"
													for="fiStat">状态</label>
												<div class="col-sm-2">
													<!-- 
														modify by marongcai
														已完结状态改为已结算
														2016-4-5
														modify by start
													 -->
													<select class="form-control" id="fiStat">
														<option value="">----状态----</option>
														<option value="0">待审核</option>
														<option value="1">已审核</option>
														<option value="6">线下支付</option>
														<option value="8">已付全款(可出团)</option>
														<option value="32">已结算</option>
														<option value="-100">已删除</option>
													</select>
													<!-- 
														modify end
													 -->
												</div>
											</div>
											<div class="col-md-offset-3 col-md-9">
												<button class="btn btn-xs btn-info" type="button"
													id="submit">
													<i class="ace-icon fa fa-check bigger-110"></i> 查询
												</button>

												&nbsp; &nbsp; &nbsp;
												<button class="btn btn-xs" type="reset" id="reset">
													<i class="ace-icon fa fa-undo bigger-110"></i> 重置
												</button>
											</div>
										</form>
									</div>
								</div>
							</div>
							<table id="grid-table"></table>
							<div id="grid-pager"></div>

							<!-- 定制线路 -->
							<div class="modal fade" id="customizationModal" tabindex="-1"
								role="dialog" aria-labelledby="customizationModalLabel"
								aria-hidden="true">
								<div class="modal-dialog" style="width: 100%;">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">×</button>
											<h4 class="modal-title" id="customizationModalLabel">计调订单</h4>
										</div>
										<div class="modal-body">
											<iframe id="customizationIframe" width="100%" height="4000px"
												frameborder="0" scrolling="no"></iframe>
										</div>

										<!-- /.modal-content -->
									</div>
									<!-- /.modal-dialog -->
								</div>
								<!-- /.modal -->
							</div>

							<!-- 衍生线路 -->
							<div class="modal fade" id="editModal" tabindex="-1"
								role="dialog" aria-labelledby="editModalLabel"
								aria-hidden="true">
								<div class="modal-dialog" style="width: auto;">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">×</button>
											<h4 class="modal-title" id="addModalLabel">计调订单</h4>
										</div>
										<div class="modal-body">
											<iframe id="editIframe" width="100%" height="3500px"
												frameborder="0" scrolling="no"></iframe>
										</div>

										<!-- /.modal-content -->
									</div>
									<!-- /.modal-dialog -->
								</div>
								<!-- /.modal -->
							</div>

							<!-- 删除 -->
							<!-- 模态框（查询） -->
							<div class="modal fade" id="delModal" tabindex="-1" role="dialog"
								aria-labelledby="delModalLabel" aria-hidden="false">
								<div class="modal-dialog" style="width: 400px;">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">×</button>
											<h4 class="modal-title" id="delModalLabel">删除警告</h4>
										</div>
										<div class="modal-body">
											<iframe id="delIframe" width="100%" height="200px"
												frameborder="0" scrolling="no"></iframe>
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
	<script src="/js/bus/orderlist/orderlist.js"></script>
</body>
</html>
