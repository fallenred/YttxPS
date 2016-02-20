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

							<!-- 模态框（查询） -->

							<form class="form-horizontal">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">×</button>
									<h4 class="modal-title" id="showModalLabel">门票详情</h4>
								</div>
								<div class="modal-body">
									<!-- 图片TODO： -->
									<div>
										<ul class="ace-thumbnails clearfix">
											<div style="visibility: hidden;">
												<li><a class="cboxElement" data-rel="colorbox"
													href="http://127.0.0.1:81/1.jpg"> <img width="0"
														height="0" src="http://127.0.0.1:81/2.png" alt="0*0">
												</a></li>
											</div>
										</ul>
									</div>

									<div class="row">
										<div class="form-group">
											<!-- <label class="col-sm-2 control-label no-padding-right" for="fsNo">门票代码</label>
											<div class="col-sm-3">
												<input class="form-control" maxlength="10" type="text" class="form-control" id="fsNo" name="fsNo" placeholder="门票代码需唯一" />
											</div> -->
											<label class="col-sm-2 control-label no-padding-right" for="fsName">票名称</label>
											<div class="col-sm-3">
												<input class="form-control" type="text" class="form-control" id="fsName" name="fsName" placeholder="票名称"  maxlength="10" readonly="readonly"/>
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="fsScenicno">所属景区</label>
											<div class="col-sm-3">
												<input class="form-control" maxlength="10" type="text" class="form-control" id="fsScenicno" name="fsScenicno" placeholder="所属景区" readonly="readonly"/>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fsType">门票类型</label>
											<div class="col-sm-3">
												<select id="fsType" class="form-control" name="fsType" disabled="disabled">
													<option value="01">主门票</option>
													<option value="02">车票</option>
													<option value="03">小景区</option>
												</select>
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="fsType">状态</label>
											<div class="col-sm-3">
												<select id="fiStat" class="form-control" name="fiStat" disabled="disabled">
													<option value="1">正常</option>
													<option value="2">失效</option>
												</select>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fsDesc">描述</label>
											<div class="col-sm-8">
												<input class="form-control" type="text" class="form-control" id="fsDesc" name="fsDesc" 
													placeholder="描述" readonly="readonly"/>
											</div>
										</div>
									</div>

								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">关闭</button>
									<button id="reset" type="reset" class="btn"
										style="display: none;">重置</button>
									<!--  
											<button type="button" class="btn btn-primary">提交</button>
											-->
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
	<script src="/js/bus/ticket/show.js"></script>

	<c:if test="${!empty succflag && succflag =='1'}">

		<script type="text/javascript">
	          $('.jump-step').removeClass('hide');
	          setTimeout('window.location="/home.htm"',5000);
	    </script>
	</c:if>

</body>
</html>
