<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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

							<!-- 模态框（图片） -->
							<div class="row">
								<div class="col-xs-10">
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="resType">归属资源代码</label>
											<div class="col-sm-3">
												<input type="text" id="resType" name="resType"
													class="form-control" placeholder="归属资源代码" maxlength="2" />
											</div>
											<label class="col-sm-2 control-label no-padding-right"
												for="resNo">资源编码</label>
											<div class="col-sm-3">
												<input maxlength="10" type="text" id="resNo" name="resNo"
													class="form-control" placeholder="资源编码" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="seq">序号</label>
											<div class="col-sm-3">
												<input maxlength="4" type="text" id="seq" name="seq"
													class="form-control" placeholder="序号" />
											</div>
											<label class="col-sm-2 control-label no-padding-right"
												for="main">是否主图</label>
											<div class="col-sm-3">
												<input maxlength="1" type="text" id="main" name="main"
													class="form-control" placeholder="是否主图" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="desc">图片简述</label>
											<div class="col-sm-3">
												<input maxlength="500" type="text" id="desc" name="desc"
													class="form-control" placeholder="图片简述" />
											</div>
										</div>
									</div>
								</div>
								<div class="col-xs-1">
									<button id="upfileBtn" class="btn btn-app btn-purple btn-xs">
										<i class="ace-icon fa fa-cloud-upload bigger-180"></i> 文件上传
									</button>
								</div>
							</div>
							<div class="row">
								<div class="modal-footer">
									<div id="message" class="center alert alert-warning"></div>
									<button id="close" type="button" class="btn btn-default"
										data-dismiss="modal">关闭</button>
									<button id="reset" type="reset" class="btn">重置</button>
									<button id="submit" type="button" class="btn btn-primary">提交</button>
								</div>
							</div>

							<!-- 图片TODO： -->
							<div class="col-xs-12">
								<ul class="ace-thumbnails clearfix" id="colorbox">
								</ul>
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
			<div class="modal fade" id="upfileModal" tabindex="-1" role="dialog"
				aria-labelledby="upfileModalLabel" aria-hidden="false">
				<div class="modal-dialog" style="width: 75%;height:200px">
					<div class="modal-content"></div>
				</div>
			</div>
			<!-- /.main-container-inner -->
		</div>
		<!-- /.main-container -->
	</div>
	<script src="/js/bus/pic/pic.js"></script>

	<c:if test="${!empty succflag && succflag =='1'}">

		<script type="text/javascript">
			$('.jump-step').removeClass('hide');
			setTimeout('window.location="/home.htm"', 5000);
		</script>

	</c:if>
</body>
</html>
