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

							<!-- 模态框（新增） -->

							<form class="form-horizontal" id="addform" >

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
											<label class="col-sm-2 control-label no-padding-right" for="fsName">车型名称</label>
											<div class="col-sm-3">
												<input class="form-control" type="text" id="fsName"  class="form-control" name="fsName" placeholder="车型名称"  maxlength="50" />
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="fiLoadMin">准载下限</label>
											<div class="col-sm-3">
												<input class="form-control non-negative-integer" maxlength="10" type="text"  class="form-control isFloatGteZero digits" id="fiLoadMin" name="fiLoadMin" placeholder="准载下限" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											
											<label class="col-sm-2 control-label no-padding-right" for="fiLoadMax">准载上限</label>
											<div class="col-sm-3">
												<input class="form-control non-negative-integer" maxlength="10" type="text"  class="form-control  isFloatGteZero digits" id="fiLoadMax" name="fiLoadMax" placeholder="准载上限" />
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="fiFitMin">适应范围下限</label>
											<div class="col-sm-3">
												<input class="form-control non-negative-integer" maxlength="10" type="text"  class="form-control  isFloatGteZero digits" id="fiFitMin" name="fiFitMin" placeholder="适应范围下限" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fiFitMax">适应范围上限</label>
											<div class="col-sm-3">
												<input class="form-control non-negative-integer" type="text" id="fiFitMax" name="fiFitMax" class="form-control  isFloatGteZero digits" placeholder="适应范围上限"  />
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fiStat">状态</label>
											<div class="col-sm-3">
												<select class="form-control" id="fiStat" name="fiStat">
													<option value="1">正常</option>
													<option value="2">失效</option>
												</select>
											</div>
										</div>
									</div>
									<div id = "message" class="alert alert-warning">
										
									</div>
								</div>
									<div class="modal-footer">
									<button id="close" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
									<button id="reset" type="reset" class="btn" >重置</button>
									<button id="submit" type="button" class="btn btn-primary">提交</button>
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
	<script src="/js/bus/transport/add.js"></script>

	<c:if test="${!empty succflag && succflag =='1'}">

		<script type="text/javascript">
	          $('.jump-step').removeClass('hide');
	          setTimeout('window.location="/home.htm"',5000);
	    </script>

	</c:if>

</body>
</html>
