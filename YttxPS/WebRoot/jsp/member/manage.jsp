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
												<label class="col-sm-1 control-label no-padding-right" for="name">客户ID</label>
												<div class="col-sm-5">
													<input maxlength="50" name="name" type="text" id="name" placeholder="忽略餐厅名称" />
												</div>
												<label class="col-sm-1 control-label no-padding-right" for="regionname">所属地区</label>
												<div class="col-sm-2">
													<input type="text" placeholder="忽略行政区域" name="regionname"
														data-key="000086" data-idx="0" data-full="中国" id="regionname" class="inp-search" /> 
													<input type="hidden" name="regionno" id="regionno" />
													<div id="selectCity" class="localcity"></div>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-1 control-label no-padding-right" for="scale">接待规模</label>
												<div class="col-sm-5">
													<input maxlength="4" id="scale_min" name="scale_min" type="text"  placeholder="忽略接待规模最小值" />
													--
													<input maxlength="4" id="scale_max" name="scale_max" type="text"  placeholder="忽略接待规模最大值" />
												</div>
												<label class="col-sm-1 control-label no-padding-right" for="special">菜品特色</label>
												<div class="col-sm-5">
													<select name="special"  id="special">
														<!-- TODO:应该从数据字典中读取 -->
														<option value="">忽略菜品特色</option>
														<option value="01">小吃</option>
														<option value="02">藏餐</option>
														<option value="03">火锅</option>
													</select>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-1 control-label no-padding-right" for="lvl">等级</label>
												<div class="col-sm-5">
													<select name="lvl"  id="lvl">
														<!-- TODO:应该从数据字典中读取 -->
														<option value="">忽略等级</option>
														<option value="01">lvl1</option>
														<option value="02">lvl2</option>
														<option value="03">lvl3</option>
													</select>
												</div>
												<label class="col-sm-1 control-label no-padding-right"
													for="stat">状态</label>
												<div class="col-sm-5">
													<select id="stat" name="stat">
														<option value="">忽略状态</option>
														<option value="1">正常</option>
														<option value="2">失效</option>
													</select>
												</div>
											</div>
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
		<!-- 模态框（餐厅消费选项定价） -->
		<div class="modal fade" id="costModal" tabindex="-1" role="dialog" 
			aria-labelledby="costModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 1024px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h4 class="modal-title" id="costModalLabel">餐厅消费选项定价</h4>
					</div>
					
					<div class="modal-body">
						<iframe id="costIframe" width="100%" height="600px" frameborder="0" scrolling="no"></iframe>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 模态框（新增） -->
		<div class="modal fade" id="addModal" tabindex="-1" role="dialog" 
			aria-labelledby="addModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 1024px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h4 class="modal-title" id="addModalLabel">新增餐厅</h4>
					</div>
					
					<div class="modal-body">
						<iframe id="addIframe" width="100%" height="1200px" frameborder="0" scrolling="no"></iframe>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 模态框（修改） -->
		<div class="modal fade" id="editModal" tabindex="-1" role="dialog"
			aria-labelledby="editModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"aria-hidden="true">×</button>
						<h4 class="modal-title" id="editModalLabel">修改餐厅信息</h4>
					</div>
					<div class="modal-body">
						<iframe id="editIframe" width="100%" height="1000px" frameborder="0" scrolling="no"></iframe>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 模态框（查询） -->
		<div class="modal fade" id="showModal" tabindex="-1" role="dialog"
			aria-labelledby="showModalLabel" aria-hidden="false">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							<h4 class="modal-title" id="showModalLabel">餐厅信息</h4>
						</div>
						<div class="modal-body">
							<iframe id="showIframe" width="100%" height="800px" frameborder="0" scrolling="no"></iframe>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- 模态框（删除） -->
		<div class="modal fade" id="delModal" tabindex="-1" role="dialog"
			aria-labelledby="delModalLabel" aria-hidden="false">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h4 class="modal-title" id="delModalLabel">删除警告</h4>
					</div>
					<div class="modal-body">
						<iframe id="delIframe" width="100%" height="200px" frameborder="0" scrolling="no"></iframe>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 图片上传 -->
		<div class="modal fade" id="picModal" tabindex="-1" role="dialog"
			aria-labelledby="picModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: auto; height: auto">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h4 class="modal-title" id="picModalLabel">图片管理</h4>
					</div>
					<div class="modal-body">
						<iframe id="picIframe" width="100%" height="500px" frameborder="0"
							scrolling="yes"></iframe>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="/js/bus/restaurant/restaurant.js"></script>
</body>
</html>
