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
							<span class="input-icon"> <input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" /> <i
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
										<a class="accordion-toggle" href="#collapseOne" data-toggle="collapse" data-parent="#accordion">
											<i class="bigger-110 ace-icon fa fa-angle-down" data-icon-show="ace-icon fa fa-angle-right" data-icon-hide="ace-icon fa fa-angle-down"></i> &nbsp;查询条件
										</a>
									</h4>
								</div>

								<div class="panel-collapse collapse in" id="collapseOne">
									<div class="panel-body">
										<form class="form-horizontal" role="form" id="queryfield">
											<!-- 
												modify by marongcai
												修改查询条件布局
												2016-3-16
												modify by start
											 -->
											<div class="form-group">
												<label class="col-sm-1 control-label no-padding-right" for="name">驾驶员姓名</label>
												<div class="col-sm-2">
													<input class="form-control" maxlength="10" type="text" id="name" placeholder="忽略驾驶员姓名" />
												</div>
												<label class="col-sm-1 control-label no-padding-right" for="gender">性别</label>
												<div class="col-sm-2">
													<select class="form-control" id="gender">
														<option value="">&nbsp;&nbsp;忽略性别&nbsp;&nbsp;</option>
														<option value="1">男</option>
														<option value="2">女</option>
													</select>
												</div>
												<label class="col-sm-1 control-label no-padding-right" for="stat">状态</label>
												<div class="col-sm-2">
													<select class="form-control" id="stat">
														<option value="">&nbsp;&nbsp;忽略状态&nbsp;&nbsp;</option>
														<option value="1">正常</option>
														<option value="2">失效</option>
													</select>
												</div>
											</div>
											<!-- 
												modify end
											 -->
											<div class="clearfix form-actions">
												<div class="col-md-offset-3 col-md-9">
													<button class="btn btn-info btn-sm" type="button" id="queryfield_submit">
														<i class="ace-icon fa fa-check bigger-110"></i> 查询
													</button>

													&nbsp; &nbsp; &nbsp;
													<button class="btn btn-sm" type="reset" id="reset">
														<i class="ace-icon fa fa-undo bigger-110"></i> 重置
													</button>
												</div>
											</div>
										</form>
									</div>

								</div>
							</div>
							<table id="grid-table"></table>
							<div id="grid-pager"></div>
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
		<!-- 模态框（新增） -->
		<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="addModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h4 class="modal-title" id="addModalLabel">新增驾驶员信息</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" id="addform">
							<div class="modal-body">
								<div class="row">
									<div class="form-group">
										<label class="col-sm-1 control-label no-padding-right" for="name">姓名</label>
										<div class="col-sm-3">
											<input type="text" name="name" class="form-control" placeholder="姓名" />
										</div>
										<label class="col-sm-1 control-label no-padding-right" for="lvl">性别</label>
										<div class="col-sm-3">
											<select name="gender">
												<option value="">&nbsp;&nbsp;请选择&nbsp;&nbsp;</option>
												<option value="1">&nbsp;男&nbsp;</option>
												<option value="2">女</option>
											</select>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group">
										<label class="col-sm-1 control-label no-padding-right" for="addr">出生日期</label>
										<div class="col-sm-3">
											<input type="text" placeholder="出生日期" name="birth" class="form-control datetimepicker" data-date-format="yyyy-mm-dd" readonly="readonly"></input>
										</div>
										<label class="col-sm-1 control-label no-padding-right" for="lvl">状态</label>
										<div class="col-sm-3">
											<select name="stat">
												<option value="">&nbsp;&nbsp;请选择&nbsp;&nbsp;</option>
												<option value="1">&nbsp;&nbsp;正常&nbsp;&nbsp;</option>
												<option value="2">&nbsp;&nbsp;失效&nbsp;&nbsp;</option>
											</select>
										</div>
									</div>
								</div>
								<div class="row">
									<div id="message" class="alert alert-warning"></div>
								</div>
							</div>
							<div class="modal-footer">
								<button id="close" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
								<button id="reset" type="reset" class="btn">重置</button>
								<button id="add_submit" type="button" class="btn btn-primary">提交</button>
							</div>
						</form>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
		</div>
		<!-- 模态框（修改） -->
		<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h4 class="modal-title" id="editModalLabel">修改驾驶员信息</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" id="editForm">
							<div class="modal-body">
								<div class="row">
									<div class="form-group">
										<label class="col-sm-1 control-label no-padding-right" for="name">姓名</label>
										<div class="col-sm-3">
											<input type="hidden" name="index" class="form-control" placeholder="唯一" />
											<input type="text" name="name" class="form-control" placeholder="姓名" />
										</div>
										<label class="col-sm-1 control-label no-padding-right" for="lvl">性别</label>
										<div class="col-sm-3">
											<select name="gender">
												<option value="">&nbsp;&nbsp;请选择&nbsp;&nbsp;</option>
												<option value="1">&nbsp;男&nbsp;</option>
												<option value="2">女</option>
											</select>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group">
										<label class="col-sm-1 control-label no-padding-right" for="addr">出生日期</label>
										<div class="col-sm-3">
											<input type="text" placeholder="出生日期" name="birth" class="form-control datetimepicker" data-date-format="yyyy-mm-dd" readonly="readonly"></input>
										</div>
										<label class="col-sm-1 control-label no-padding-right" for="lvl">状态</label>
										<div class="col-sm-3">
											<select name="stat">
												<option value="">&nbsp;&nbsp;请选择&nbsp;&nbsp;</option>
												<option value="1">&nbsp;&nbsp;正常&nbsp;&nbsp;</option>
												<option value="2">&nbsp;&nbsp;失效&nbsp;&nbsp;</option>
											</select>
										</div>
									</div>
								</div>
								<div class="row">
									<div id="message" class="alert alert-warning"></div>
								</div>
							</div>
							<div class="modal-footer">
								<button id="close" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
								<!--<button id="reset" type="reset" class="btn" >重置</button>  -->
								<button id="submit" type="button" class="btn btn-primary">提交</button>
							</div>
						</form>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
		</div>


		<!-- 模态框（查询） -->
		<div class="modal fade" id="showModal" tabindex="-1" role="dialog" aria-labelledby="showModalLabel" aria-hidden="false">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h4 class="modal-title" id="editModalLabel">驾驶员信息</h4>
					</div>
					<form class="form-horizontal" id="showForm">
						<div class="modal-body">
							<div class="row">
								<div class="form-group">
									<label class="col-sm-1 control-label no-padding-right" for="name">姓名</label>
									<div class="col-sm-3">
										<input type="hidden" name="index" class="form-control" readonly="readonly" placeholder="唯一" />
										<input type="text" name="name" class="form-control" readonly="readonly" placeholder="姓名" />
									</div>
									<label class="col-sm-1 control-label no-padding-right" for="lvl">性别</label>
									<div class="col-sm-3">
										<select name="gender" disabled="disabled">
											<option value="">&nbsp;&nbsp;请选择&nbsp;&nbsp;</option>
											<option value="1">&nbsp;男&nbsp;</option>
											<option value="2">女</option>
										</select>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label class="col-sm-1 control-label no-padding-right" for="addr">出生日期</label>
									<div class="col-sm-3">
										<input type="text" placeholder="出生日期" name="birth" class="form-control datetimepicker" data-date-format="yyyy-mm-dd" readonly="readonly"></input>
									</div>
									<label class="col-sm-1 control-label no-padding-right" for="lvl">状态</label>
									<div class="col-sm-3">
										<select name="stat" disabled="disabled">
											<option value="">&nbsp;&nbsp;请选择&nbsp;&nbsp;</option>
											<option value="1">&nbsp;&nbsp;正常&nbsp;&nbsp;</option>
											<option value="2">&nbsp;&nbsp;失效&nbsp;&nbsp;</option>
										</select>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button id="close" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						</div>
					</form>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
		</div>

		<!-- 模态框（删除） -->
		<div class="modal fade" id="delModal" tabindex="-1" role="dialog" aria-labelledby="delModalLabel" aria-hidden="false">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h4 class="modal-title" id="delModalLabel">删除警告</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" id="delForm">
							<input type="hidden" name="id" />
							<div class="alert alert-info bigger-110">注意：本次操作将永久性删除记录！</div>
							<div class="space-6"></div>
							<p id="question" class="bigger-110 bolder center grey">您确定需要删除吗?</p>
							<p id="message" class="bigger-110 bolder center grey"></p>
							<div class="modal-footer">
								<button id="close" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
								<button id="submit" type="button" class="btn btn-primary">提交</button>
							</div>
						</form>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
		</div>
	</div>
	<!-- /.main-container -->
	<script src="/js/bus/driver/driver.js"></script>
	<script src="/js/bus/driver/add.js"></script>
	<script src="/js/bus/driver/edit.js"></script>
	<script src="/js/bus/driver/delete.js"></script>

</body>
</html>
