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
						<li><i class="ace-icon fa fa-home home-icon"></i> <a href="">后台管理</a>
						</li>

						<li class="active"><a href="#">景区管理</a></li>
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
													for="depno">部门编号</label>
												<div class="col-sm-10">
													<input class="col-sm-6 col-xs-6" maxlength="10"
														type="text" id="depno" placeholder="部门编号" />
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-1 control-label no-padding-right"
													for="depname">部门名称</label>
												<div class="col-sm-10">
													<input class="col-sm-6 col-xs-6" maxlength="128" type="text" id="depname"
														placeholder="部门名称" />
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-1 control-label no-padding-right"
													for="stat">状态</label>
												<div class="col-sm-1">
													<select id="stat">
														<option value="1">正常</option>
														<option value="2">失效</option>
													</select>
												</div>
											</div>

											<div class="clearfix form-actions">
												<div class="col-md-offset-3 col-md-9">
													<button class="btn btn-info" type="button" id="submit">
														<i class="ace-icon fa fa-check bigger-110"></i> 提交
													</button>

													&nbsp; &nbsp; &nbsp;
													<button class="btn" type="reset" id="reset">
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
	</div>
	<!-- /.main-container -->
</body>
</html>
