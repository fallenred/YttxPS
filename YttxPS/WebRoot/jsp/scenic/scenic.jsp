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
													for="no">景区编码</label>
												<div class="col-sm-2">
													<input maxlength="10" type="text" id="no"
														placeholder="景区编码" />
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-1 control-label no-padding-right"
													for="region">所属地区</label>
												<div class="col-sm-2">
													<input type="text" placeholder="请选择行政区域" name="regionname"
														data-key="0086" data-idx="0" data-full="中国"
														id="regionname" class="inp-search" /> <input
														type="hidden" name="region" id="regionno" />
													<div id="selectCity" class="localcity"></div>
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-1 control-label no-padding-right"
													for="name">景区名称</label>
												<div class="col-sm-10">
													<input class="col-sm-5 col-xs-5" maxlength="150"
														type="text" id="name" placeholder="景区名称" />
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-1 control-label no-padding-right"
													for="lvl">景区等级</label>
												<div class="col-sm-1">
													<input maxlength="5" type="text" id="lvl"
														placeholder="景区等级" />
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-1 control-label no-padding-right"
													for="lvl">状态</label>
												<div class="col-sm-1">
													<select id="stat" placeholder="状态">
														<option value="1">正常</option>
														<option value="0">失效</option>
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



							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a class="accordion-toggle collapsed" href="#collapseTwo"
											data-toggle="collapse" data-parent="#accordion"> <i
											class="bigger-110 ace-icon fa fa-angle-right"
											data-icon-show="ace-icon fa fa-angle-right"
											data-icon-hide="ace-icon fa fa-angle-down"></i> &nbsp;查询结果
										</a>
									</h4>
								</div>

								<div class="panel-collapse collapse" id="collapseTwo"
									style="height: 0px;">
									<div class="panel-body">
										<table id="grid-table"></table>
										<div id="grid-pager"></div>
									</div>
								</div>
							</div>
						</div>
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
	<script src="/js/bus/scenic.js"></script>
</body>
</html>
