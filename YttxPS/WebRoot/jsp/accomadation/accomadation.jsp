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
										<a class="accordion-toggle" href="#collapseOne" data-toggle="collapse" data-parent="#accordion"> <i class="bigger-110 ace-icon fa fa-angle-down"
											data-icon-show="ace-icon fa fa-angle-right" data-icon-hide="ace-icon fa fa-angle-down"></i> &nbsp;查询条件
										</a>
									</h4>
								</div>

								<div class="panel-collapse collapse in" id="collapseOne">
									<div class="panel-body">
										<form class="form-horizontal" role="form" id="queryfield">
											<div class="form-group">
												<label class="col-sm-1 control-label no-padding-right" for="no">酒店代码</label>
												<div class="col-sm-2">
													<input maxlength="10" name="no" type="text" id="no" placeholder="忽略酒店代码" />
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-1 control-label no-padding-right" for="name">酒店名称</label>
												<div class="col-sm-2">
													<input maxlength="200" name="name" type="text" id="name" placeholder="忽略酒店名称" />
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-1 control-label no-padding-right" for="regionname">所属地区</label>
												<div class="col-sm-2">
													<input type="text" placeholder="忽略行政区域" name="regionname" data-key="0086" data-idx="0" data-full="中国" id="regionname" class="inp-search" />
													<input type="hidden" name="regionno" id="regionno" />
													<div id="selectCity" class="localcity"></div>
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-1 control-label no-padding-right" for="stat">状态</label>
												<div class="col-sm-1">
													<select id="stat" name="stat">
														<option value="">&nbsp;&nbsp;忽略状态&nbsp;&nbsp;</option>
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

		<!-- 模态框（新增） -->
		<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="addModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h4 class="modal-title" id="addModalLabel">新增酒店信息</h4>
					</div>
					<form class="form-horizontal" id="addform">
						<div class="modal-body">
							<div class="row">
								<div class="form-group">
									<label class="col-sm-2 control-label no-padding-right" for="name">酒店名称</label>
									<div class="col-sm-3">
										<input type="text" name="name" class="form-control" placeholder="酒店名称" />
									</div>
									<label class="col-sm-2 control-label no-padding-right" for="brand">所属地区</label>
									<div class="col-sm-3">
										<input type="text" placeholder="忽略行政区域" name="regionname" data-key="0086" data-idx="0" data-full="中国" class="form-control inp-search" />
										<input type="hidden" name="regionno" />
										<div class="localcity selectCity" id="addSelectCity"></div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label class="col-sm-2 control-label no-padding-right" for="addr">酒店地址</label>
									<div class="col-sm-8">
										<input type="text" name="addr" class="form-control" placeholder="酒店地址" />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label class="col-sm-2 control-label no-padding-right" for="regdate">酒店介绍</label>
									<div class="col-sm-8">
										<textarea title="ckeditor" id="desc_add" rows="4" cols="30" placeholder="酒店介绍" name="desc" class="form-control"></textarea>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label class="col-sm-2 control-label no-padding-right" for="regdate">酒店星级</label>
									<div class="col-sm-8">
										<div class="checkbox-inline">
											<label> <input name="starlvl" type="radio" class="ace" value="01"><span class="lbl">挂五星酒店</span>
											</label>
										</div>
										<div class="checkbox-inline">
											<label> <input name="starlvl" type="radio" class="ace" value="02"><span class="lbl">准五星酒店</span>
											</label>
										</div>
										<div class="checkbox-inline">
											<label> <input name="starlvl" type="radio" class="ace" value="03"><span class="lbl">挂四星酒店</span>
											</label>
										</div>
										<div class="checkbox-inline">
											<label> <input name="starlvl" type="radio" class="ace" value="04"><span class="lbl">一线准四酒店</span>
											</label>
										</div>
										<div class="checkbox-inline">
											<label> <input name="starlvl" type="radio" class="ace" value="05"><span class="lbl">二线准四酒店</span>
											</label>
										</div>
										<div class="checkbox-inline">
											<label> <input name="starlvl" type="radio" class="ace" value="06"><span class="lbl">挂三星酒店</span>
											</label>
										</div>
										<div class="checkbox-inline">
											<label> <input name="starlvl" type="radio" class="ace" value="07"><span class="lbl">准三星酒店</span>
											</label>
										</div>
										<div class="checkbox-inline">
											<label> <input name="starlvl" type="radio" class="ace" value="08"><span class="lbl">乡村酒店</span>
											</label>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label class="col-sm-2 control-label no-padding-right" for="regdate">酒店设施</label>
									<div class="col-sm-8">
										<label> <input name="speciality" class="ace ace-switch ace-switch-2" type="checkbox" value="01:WIFI"> <span class="lbl">&nbsp;WIFI</span>
										</label> <label> <input name="speciality" class="ace ace-switch ace-switch-2" type="checkbox" value="02:空调"> <span class="lbl">&nbsp;空调</span>
										</label> <label> <input name="speciality" class="ace ace-switch ace-switch-2" type="checkbox" value="03:一体电脑"> <span class="lbl">&nbsp;一体电脑</span>
										</label> <label> <input name="speciality" class="ace ace-switch ace-switch-2" type="checkbox" value="04:棋牌"> <span class="lbl">&nbsp;棋牌</span>
										</label> <label> <input name="speciality" class="ace ace-switch ace-switch-2" type="checkbox" value="05:其它"> <span class="lbl">&nbsp;其它</span>
										</label>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label class="col-xs-2 col-sm-2 control-label no-padding-right" for="stat">状态</label>
									<div class="col-xs-4 col-sm-4">
										<select name="stat">
											<option value="">&nbsp;&nbsp;请选择状态&nbsp;&nbsp;</option>
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

		<!-- 模态框（修改） -->
		<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h4 class="modal-title" id="editModalLabel">修改酒店信息</h4>
					</div>
					<form class="form-horizontal" id="editForm">
						<div class="modal-body">
							<div class="row">
								<div class="form-group">
									<label class="col-sm-2 control-label no-padding-right" for="load">酒店名称</label>
									<div class="col-sm-3">
										<input type="hidden" name="no" id="no" />
										<input type="text" name="name" class="form-control" placeholder="酒店名称" />
									</div>
									<label class="col-sm-2 control-label no-padding-right" for="brand">所属地区</label>
									<div class="col-sm-3">
										<input type="text" placeholder="忽略行政区域" name="regionname" data-key="0086" data-idx="0" data-full="中国" class="form-control inp-search" />
										<input type="hidden" name="regionno" />
										<div class="localcity selectCity" id="editSelectCity"></div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label class="col-sm-2 control-label no-padding-right" for="company">酒店地址</label>
									<div class="col-sm-8">
										<input type="text" name="addr" class="form-control" placeholder="酒店地址" />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label class="col-sm-2 control-label no-padding-right" for="regdate">酒店介绍</label>
									<div class="col-sm-8">
										<textarea title="ckeditor" id="desc_edit" rows="4" cols="30" placeholder="酒店介绍" name="desc" class="form-control"></textarea>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label class="col-sm-2 control-label no-padding-right" for="regdate">酒店星级</label>
									<div class="col-sm-8">
										<div class="checkbox-inline">
											<label> <input name="starlvl" type="radio" class="ace" value="01"><span class="lbl">挂五星酒店</span>
											</label>
										</div>
										<div class="checkbox-inline">
											<label> <input name="starlvl" type="radio" class="ace" value="02"><span class="lbl">准五星酒店</span>
											</label>
										</div>
										<div class="checkbox-inline">
											<label> <input name="starlvl" type="radio" class="ace" value="03"><span class="lbl">挂四星酒店</span>
											</label>
										</div>
										<div class="checkbox-inline">
											<label> <input name="starlvl" type="radio" class="ace" value="04"><span class="lbl">一线准四酒店</span>
											</label>
										</div>
										<div class="checkbox-inline">
											<label> <input name="starlvl" type="radio" class="ace" value="05"><span class="lbl">二线准四酒店</span>
											</label>
										</div>
										<div class="checkbox-inline">
											<label> <input name="starlvl" type="radio" class="ace" value="06"><span class="lbl">挂三星酒店</span>
											</label>
										</div>
										<div class="checkbox-inline">
											<label> <input name="starlvl" type="radio" class="ace" value="07"><span class="lbl">准三星酒店</span>
											</label>
										</div>
										<div class="checkbox-inline">
											<label> <input name="starlvl" type="radio" class="ace" value="08"><span class="lbl">乡村酒店</span>
											</label>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label class="col-sm-2 control-label no-padding-right" for="regdate">酒店设施</label>
									<div class="col-sm-8">
										<label> <input name="speciality" class="ace ace-switch ace-switch-2" type="checkbox" value="01:WIFI"> <span class="lbl">&nbsp;WIFI</span>
										</label> <label> <input name="speciality" class="ace ace-switch ace-switch-2" type="checkbox" value="02:空调"> <span class="lbl">&nbsp;空调</span>
										</label> <label> <input name="speciality" class="ace ace-switch ace-switch-2" type="checkbox" value="03:一体电脑"> <span class="lbl">&nbsp;一体电脑</span>
										</label> <label> <input name="speciality" class="ace ace-switch ace-switch-2" type="checkbox" value="04:棋牌"> <span class="lbl">&nbsp;棋牌</span>
										</label> <label> <input name="speciality" class="ace ace-switch ace-switch-2" type="checkbox" value="05:其它"> <span class="lbl">&nbsp;其它</span>
										</label>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label class="col-xs-2 col-sm-2 control-label no-padding-right" for="stat">状态</label>
									<div class="col-xs-4 col-sm-4">
										<select name="stat">
											<option value="">&nbsp;&nbsp;请选择状态&nbsp;&nbsp;</option>
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
							<button id="edit_submit" type="button" class="btn btn-primary">提交</button>
						</div>
					</form>
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
						<h4 class="modal-title" id="editModalLabel">酒店信息</h4>
					</div>
					<form class="form-horizontal" id="showForm">
						<div class="modal-body">
							<div class="row">
								<div class="form-group">
									<label class="col-sm-2 control-label no-padding-right" for="load">酒店名称</label>
									<div class="col-sm-3">
										<input type="hidden" name="no" id="no" />
										<input type="text" name="name" class="form-control" placeholder="酒店名称" disabled="disabled" />
									</div>
									<label class="col-sm-2 control-label no-padding-right" for="brand">所属地区</label>
									<div class="col-sm-3">
										<input type="text" placeholder="忽略行政区域" name="regionname" disabled="disabled" data-key="0086" data-idx="0" data-full="中国" class="form-control inp-search" />
										<input type="hidden" name="regionno" disabled="disabled" />
										<div class="localcity selectCity" id="editSelectCity"></div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label class="col-sm-2 control-label no-padding-right" for="company">酒店地址</label>
									<div class="col-sm-8">
										<input type="text" name="addr" class="form-control" placeholder="酒店地址" disabled="disabled" />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label class="col-sm-2 control-label no-padding-right" for="regdate">酒店介绍</label>
									<div class="col-sm-8">
										<textarea title="ckeditor" id="desc_show" rows="4" cols="30" placeholder="酒店介绍" name="desc" class="form-control" disabled="disabled"></textarea>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label class="col-sm-2 control-label no-padding-right" for="regdate">酒店星级</label>
									<div class="col-sm-8">
										<div class="checkbox-inline">
											<label> <input name="starlvl" type="radio" class="ace" value="01"><span class="lbl">挂五星酒店</span>
											</label>
										</div>
										<div class="checkbox-inline">
											<label> <input name="starlvl" type="radio" class="ace" value="02"><span class="lbl">准五星酒店</span>
											</label>
										</div>
										<div class="checkbox-inline">
											<label> <input name="starlvl" type="radio" class="ace" value="03"><span class="lbl">挂四星酒店</span>
											</label>
										</div>
										<div class="checkbox-inline">
											<label> <input name="starlvl" type="radio" class="ace" value="04"><span class="lbl">一线准四酒店</span>
											</label>
										</div>
										<div class="checkbox-inline">
											<label> <input name="starlvl" type="radio" class="ace" value="05"><span class="lbl">二线准四酒店</span>
											</label>
										</div>
										<div class="checkbox-inline">
											<label> <input name="starlvl" type="radio" class="ace" value="06"><span class="lbl">挂三星酒店</span>
											</label>
										</div>
										<div class="checkbox-inline">
											<label> <input name="starlvl" type="radio" class="ace" value="07"><span class="lbl">准三星酒店</span>
											</label>
										</div>
										<div class="checkbox-inline">
											<label> <input name="starlvl" type="radio" class="ace" value="08"><span class="lbl">乡村酒店</span>
											</label>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label class="col-sm-2 control-label no-padding-right" for="regdate">酒店设施</label>
									<div class="col-sm-8">
										<label> <input name="speciality" class="ace ace-switch ace-switch-2" type="checkbox" disabled="disabled" value="01:WIFI"> <span class="lbl">&nbsp;WIFI</span>
										</label> <label> <input name="speciality" class="ace ace-switch ace-switch-2" type="checkbox" disabled="disabled" value="02:空调"> <span class="lbl">&nbsp;空调</span>
										</label> <label> <input name="speciality" class="ace ace-switch ace-switch-2" type="checkbox" disabled="disabled" value="03:一体电脑"> <span class="lbl">&nbsp;一体电脑</span>
										</label> <label> <input name="speciality" class="ace ace-switch ace-switch-2" type="checkbox" disabled="disabled" value="04:棋牌"> <span class="lbl">&nbsp;棋牌</span>
										</label> <label> <input name="speciality" class="ace ace-switch ace-switch-2" type="checkbox" disabled="disabled" value="05:其它"> <span class="lbl">&nbsp;其它</span>
										</label>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label class="col-xs-2 col-sm-2 control-label no-padding-right" for="stat">状态</label>
									<div class="col-xs-4 col-sm-4">
										<select name="stat" disabled="disabled">
											<option value="">&nbsp;&nbsp;请选择状态&nbsp;&nbsp;</option>
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

		<div class="modal fade" id="roomModal" tabindex="-1" role="dialog" aria-labelledby="showModalLabel" aria-hidden="false">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h4 class="modal-title" id="editModalLabel">酒店房型配置</h4>
					</div>
					<div class="modal-body">
						<iframe id="roomIframe" width="100%" frameborder="0" scrolling="auto"></iframe>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
		</div>

	</div>
	<!-- /.main-container -->
	<script type="text/javascript">
		CKEDITOR.replace('desc_add', {
			uiColor : '#E2E2E2'
		});
		CKEDITOR.replace('desc_edit', {
			uiColor : '#E2E2E2'
		});
		CKEDITOR.replace('desc_show', {
			uiColor : '#E2E2E2'
		});
	</script>
	<script src="/js/bus/accomadation/accomadation.js"></script>
	<script src="/js/bus/accomadation/add.js"></script>
	<script src="/js/bus/accomadation/edit.js"></script>
	<script src="/js/bus/accomadation/delete.js"></script>
</body>
</html>
