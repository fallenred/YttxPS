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
<body>
	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			}
		</script>

		<div class="main-container-inner">

			<div class="main-content">


				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->

							<!-- 模态框（查询） -->

							<form class="form-horizontal">
								<div class="modal-body">
									<div>
										<ul class="ace-thumbnails clearfix">
											<div style="visibility: hidden;">
												<li>
													<a class="cboxElement" data-rel="colorbox" href="http://127.0.0.1:81/1.jpg">
														<img width="0" height="0" src="http://127.0.0.1:81/2.png" alt="0*0">
													</a>
												</li>
											</div>
										</ul>
									</div>

									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fsName">路线名称</label>
											<div class="col-sm-3">
												<input class="form-control" type="text" class="form-control" class="form-control" id="fsName" name="fsName" placeholder="路线名称" maxlength="50" readonly="readonly" />
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="fiDays">路线天数</label>
											<div class="col-sm-3">
												<input class="form-control" maxlength="10" type="text" class="form-control" class="form-control" id="fiDays" name="fiDays" placeholder="路线天数" readonly="readonly" />
											</div>
										</div>
									</div>

									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fsRegions">景区</label>
											<div class="col-sm-3">
												<select id="scenic" name="scenic" class="form-control" disabled="disabled">
												</select>
											</div>
											<div class="col-sm-3">
												<button type="button" class="btn btn-success pull-right" id="addScenicBtn" disabled="disabled">添加</button>
											</div>
											<div class="col-sm-3">
												<button type="button" class="btn btn-success pull-left" id="rmScenicBtn" disabled="disabled">删除</button>
											</div>
										</div>
									</div>

									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fsProperty"></label>
											<div class="col-sm-8" id="div_scenics"></div>
										</div>
									</div>

									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fsType">状态</label>
											<div class="col-sm-3">
												<select id="fiStat" name="fiStat" disabled="disabled" class="form-control">
													<option value="1">正常</option>
													<option value="-100">已销</option>
												</select>
											</div>
										</div>
									</div>
								</div>

								<div class="modal-footer">
									<button type="button" id="close" class="btn btn-default" data-dismiss="modal">关闭</button>
								</div>
							</form>
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
	<script src="/js/bus/gen/show.js"></script>

	<c:if test="${!empty succflag && succflag =='1'}">

		<script type="text/javascript">
			$('.jump-step').removeClass('hide');
			setTimeout('window.location="/home.htm"', 5000);
		</script>
	</c:if>

</body>
</html>
