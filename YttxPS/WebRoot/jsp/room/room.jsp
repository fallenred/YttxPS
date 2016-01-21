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
<body>
	<div class="main-container" id="main-container">
		<div class="main-content">
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
											<label class="col-sm-1 control-label no-padding-right">房型类型</label>
											<div class="col-sm-2">
												<select id="type" name="type">
													<option value="">&nbsp;&nbsp;忽略房型类型&nbsp;&nbsp;</option>
												</select>
											</div>
											<label class="col-sm-2 control-label no-padding-right"
												for="stat">状态</label>
											<div class="col-sm-2">
												<select id="stat" name="stat">
													<option value="">&nbsp;&nbsp;忽略状态&nbsp;&nbsp;</option>
													<option value="1">正常</option>
													<option value="2">失效</option>
												</select>
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-4 col-md-4 col-lg-4"></div>
											<div class="col-sm-8 col-md-8 col-lg-8">
												<button class="btn btn-info btn-sm" type="button"
													id="queryfield_submit">
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

		<!-- 模态框（新增） -->
		<div class="modal fade" id="addModal" tabindex="-1" role="dialog"
			aria-labelledby="addModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h4 class="modal-title" id="addModalLabel">新增房型信息</h4>
					</div>
					<form class="form-horizontal" id="addform">
						<div class="modal-body">
							<div class="row">
								<div class="form-group">
									<label class="col-sm-2 control-label no-padding-right"
										for="company">酒店代码</label>
									<div class="col-sm-4">
										<input type="text" name="accomno" class="form-control"
											placeholder="酒店代码" readonly="readonly" />
									</div>
									<label class="col-sm-2 control-label no-padding-right"
										for="load">房型类型</label>
									<div class="col-sm-4">
										<select name="type">
											<option value="">&nbsp;&nbsp;忽略房型类型&nbsp;&nbsp;</option>
										</select>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label class="col-sm-2 control-label no-padding-right">房型名称</label>
									<div class="col-sm-4">
										<input type="text" name="name" class="form-control"
											placeholder="房型名称" />
									</div>
									<label class="col-sm-3 control-label no-padding-right">房型价格（元）</label>
									<div class="col-sm-2">
										<input type="text" name="price" class="form-control input-sm "
											placeholder="房型价格" />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label class="col-sm-2 control-label no-padding-right"
										for="regdate">三餐情况</label>
									<div class="col-sm-8">
										<label> <input name="meal"
											class="ace ace-switch ace-switch-2" type="checkbox" value="1">
											<span class="lbl">&nbsp;早餐</span>
										</label> <label> <input name="meal"
											class="ace ace-switch ace-switch-2" type="checkbox" value="2">
											<span class="lbl">&nbsp;中餐</span>
										</label> <label> <input name="meal"
											class="ace ace-switch ace-switch-2" type="checkbox" value="3">
											<span class="lbl">&nbsp;晚餐</span>
										</label>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label class="col-xs-2 col-sm-2 control-label no-padding-right"
										for="stat">状态</label>
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
							<button id="room_close" type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button>
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
		<div class="modal fade" id="editModal" tabindex="-1" role="dialog"
			aria-labelledby="editModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h4 class="modal-title" id="editModalLabel">修改房型信息</h4>
					</div>
					<form class="form-horizontal" id="editForm">
						<div class="modal-body">
							<div class="row">
								<div class="form-group">
									<label class="col-sm-2 control-label no-padding-right"
										for="company">酒店代码</label>
									<div class="col-sm-4">
										<input type="hidden" name="index" class="form-control"
											placeholder="房型唯一编号" /> <input type="text" name="accomno"
											class="form-control" placeholder="酒店代码" readonly="readonly" />
									</div>
									<label class="col-sm-2 control-label no-padding-right"
										for="load">房型类型</label>
									<div class="col-sm-4">
										<select name="type">
											<option value="">&nbsp;&nbsp;忽略房型类型&nbsp;&nbsp;</option>
										</select>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label class="col-sm-2 control-label no-padding-right">房型名称</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="name" class="form-control"
                                            placeholder="房型名称" />
                                    </div>
                                    <label class="col-sm-3 control-label no-padding-right">房型价格（元）</label>
                                    <div class="col-sm-2">
                                        <input type="text" name="price" class="form-control input-sm "
                                            placeholder="房型价格" />
                                    </div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label class="col-sm-2 control-label no-padding-right"
										for="regdate">三餐情况</label>
									<div class="col-sm-8">
										<label> <input name="meal"
											class="ace ace-switch ace-switch-2" type="checkbox" value="1">
											<span class="lbl">&nbsp;早餐</span>
										</label> <label> <input name="meal"
											class="ace ace-switch ace-switch-2" type="checkbox" value="2">
											<span class="lbl">&nbsp;中餐</span>
										</label> <label> <input name="meal"
											class="ace ace-switch ace-switch-2" type="checkbox" value="3">
											<span class="lbl">&nbsp;晚餐</span>
										</label>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label class="col-xs-2 col-sm-2 control-label no-padding-right"
										for="stat">状态</label>
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
							<button id="room_close" type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button>
							<button id="reset" type="reset" class="btn">重置</button>
							<button id="room_edit_submit" type="button" class="btn btn-primary">提交</button>
						</div>
					</form>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
		</div>

		<!-- 模态框（查询） -->
		<div class="modal fade" id="showRoomModal" tabindex="-1" role="dialog"
			aria-labelledby="showModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h4 class="modal-title" id="editModalLabel">房型信息</h4>
					</div>
					<form class="form-horizontal" id="showForm">
						<div class="modal-body">
							<div class="row">
								<div class="form-group">
									<label class="col-sm-2 control-label no-padding-right"
										for="company">酒店代码</label>
									<div class="col-sm-4">
										<input type="text" name="accomno" class="form-control"
											placeholder="酒店代码" disabled="disabled" />
									</div>
									<label class="col-sm-2 control-label no-padding-right"
										for="load">房型类型</label>
									<div class="col-sm-4">
										<select id="type" name="type"  disabled="disabled">
											<option value="">&nbsp;&nbsp;忽略房型类型&nbsp;&nbsp;</option>
										</select>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label class="col-sm-2 control-label no-padding-right">房型名称</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="name" class="form-control"
                                            placeholder="房型名称" disabled="disabled" />
                                    </div>
                                    <label class="col-sm-3 control-label no-padding-right">房型价格（元）</label>
                                    <div class="col-sm-2">
                                        <input type="text" name="price" class="form-control input-sm "
                                            placeholder="房型价格" disabled="disabled" />
                                    </div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label class="col-sm-2 control-label no-padding-right"
										for="regdate">三餐情况</label>
									<div class="col-sm-8">
										<label> <input name="meal" disabled="disabled"
											class="ace ace-switch ace-switch-2" type="checkbox" value="1">
											<span class="lbl">&nbsp;早餐</span>
										</label> <label> <input name="meal" disabled="disabled"
											class="ace ace-switch ace-switch-2" type="checkbox" value="2">
											<span class="lbl">&nbsp;中餐</span>
										</label> <label> <input name="meal" disabled="disabled"
											class="ace ace-switch ace-switch-2" type="checkbox" value="3">
											<span class="lbl">&nbsp;晚餐</span>
										</label>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label class="col-xs-2 col-sm-2 control-label no-padding-right"
										for="stat">状态</label>
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
							<button id="close" type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button>
						</div>
					</form>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
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
						<form class="form-horizontal" id="delForm">
							<input type="hidden" name="id" />
							<div class="alert alert-info bigger-110">注意：本次操作将永久性删除记录！</div>
							<div class="space-6"></div>
							<p id="question" class="bigger-110 bolder center grey">您确定需要删除吗?</p>
							<p id="message" class="bigger-110 bolder center grey"></p>
							<div class="modal-footer">
								<button id="close" type="button" class="btn btn-default"
									data-dismiss="modal">关闭</button>
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

	<script src="/js/bus/room/room.js"></script>
	<script src="/js/bus/room/add.js"></script>
	<script src="/js/bus/room/edit.js"></script>
	<script src="/js/bus/room/delete.js"></script>
</body>
</html>
