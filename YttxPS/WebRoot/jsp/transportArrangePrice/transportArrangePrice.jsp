<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8" />
<title>车型线路价格维护</title>
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
		<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="addModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h4 class="modal-title" id="addModalLabel">新增车型线路价格信息</h4>
					</div>
					<form class="form-horizontal" id="addform">
						<div class="modal-body">
							<div class="row">
								<div class="form-group">
									<label class="col-sm-2 control-label no-padding-right" for="tgenname">线路统称名称</label>
									<div class="col-sm-4">
										<input type="hidden" id="fsResno" name="fsResno">
										<input type="hidden" id="fiGenindex" name="fiGenindex">
										<input type="text" id="tgenname" name="tgenname" class="form-control" placeholder="线路统称名称" readonly="readonly" />
									</div>
									<label class="col-sm-2 control-label no-padding-right" for="transName">车型名称</label>
									<div class="col-sm-4">
										<input type="hidden" id="fsTransNo" name="fsTransNo">
										<input type="text" name="transName" class="form-control" placeholder="车型名称" readonly="readonly" />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label class="col-sm-2 control-label no-padding-right" for="ftStartdate">开始日期</label>
									<div class="col-sm-4">
										<input class="form-control datetimepicker" maxlength="10" type="text" id="ftStartdate" data-date-format="yyyy-mm-dd" readonly="readonly" name="ftStartdate" placeholder="开始日期" />
									</div>
									<label class="col-sm-2 control-label no-padding-right" for="ftEnddate">结束日期</label>
									<div class="col-sm-4">
										<input class="form-control datetimepicker" maxlength="10" type="text" id="ftEnddate" data-date-format="yyyy-mm-dd" readonly="readonly" name="ftEnddate" placeholder="结束日期" />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label class="col-sm-2 control-label no-padding-right" for="fdPrice">价格</label>
									<div class="col-sm-4">
										<input class="form-control" maxlength="10" type="text" id="fdPrice" name="fdPrice" placeholder="价格" />
									</div>
								</div>
							</div>
							<div class="row">
								<div id="message" class="alert alert-warning"></div>
							</div>
						</div>
						<div class="modal-footer">
							<button id="room_close" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
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
							<input type="hidden" name="fsResno" />
							<input type="hidden" name="ftStartdate" />
							<input type="hidden" name="ftEnddate" />
							<input type="hidden" name="fsCcno" />
							<input type="hidden" name="fsRestype" />
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

	<script src="/js/bus/transportArrangePrice/transportArrangePrice.js"></script>
	<script src="/js/bus/transportArrangePrice/add.js"></script>
	<script src="/js/bus/transportArrangePrice/delete.js"></script>
	<script type="text/javascript">
		$('.datetimepicker').datetimepicker({
			language : 'zh-CN',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0
		});
	</script>
</body>
</html>
