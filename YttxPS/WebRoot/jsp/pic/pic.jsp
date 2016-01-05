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


							<div class="modal-body">
								<div class="row">
									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right"
											for="no">资源编码</label>
										<div class="col-sm-2">
											<input type="text" id="no" name="no" class="form-control"
												placeholder="资源编码" maxlength="10" readonly="readonly" />
										</div>
										<label class="col-sm-2 control-label no-padding-right"
											for="seq">图片序号</label>
										<div class="col-sm-2">
											<input maxlength="2" type="text" id="seq" name="seq"
												class="form-control" placeholder="图片序号" />
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right"
											for="belong">归属资源代码</label>
										<div class="col-sm-2">
											<input maxlength="4" type="text" id="belong" name="belong"
												class="form-control" placeholder="归属资源代码" />
										</div>
										<label class="col-sm-2 control-label no-padding-right"
											for="subtype">归属资源子码</label>
										<div class="col-sm-2">
											<input maxlength="2" type="text" id="subtype" name="subtype"
												class="form-control" placeholder="归属资源子码" />
										</div>
									</div>
								</div>
								<div class="modal-footer">
								<button id="close" type="button" class="btn btn-default"
									data-dismiss="modal">关闭</button>
								<button id="reset" type="reset" class="btn">重置</button>
								<button id="submit" type="button" class="btn btn-primary">提交</button>
								</div>
								<!-- 图片TODO： -->
								<div>
									<ul class="ace-thumbnails clearfix">
										<div">
											<li><a class="cboxElement" data-rel="colorbox"
												href="http://127.0.0.1:81/1.jpg"> <img width="150"
													height="150" src="http://127.0.0.1:81/2.png" alt="150*150">
											</a></li>
										</div>
									</ul>
								</div>
								<div id="message" class="alert alert-warning"
									style="visibility: hidden;"></div>

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
	<script src="/js/bus/pic/pic.js"></script>

	<c:if test="${!empty succflag && succflag =='1'}">

		<script type="text/javascript">
			$('.jump-step').removeClass('hide');
			setTimeout('window.location="/home.htm"', 5000);
		</script>

	</c:if>

</body>
</html>
