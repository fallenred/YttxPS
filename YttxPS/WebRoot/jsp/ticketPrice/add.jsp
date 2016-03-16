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
											<label class="col-sm-2 control-label no-padding-right" for="fsName">票名称</label>
											<div class="col-sm-3">
												<select class="form-control" id="fsNo" name="fsNo" disabled="disabled"></select>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="ftStartdate">开始日期</label>
											<div class="col-sm-3">
												<input class="form-control datetimepicker" data-date-format="yyyy-mm-dd" readonly="readonly" maxlength="10" type="text" id="ftStartdate" name="ftStartdate" placeholder="开始日期" />
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="ftEnddate">结束日期</label>
											<div class="col-sm-3">
												<input class="form-control datetimepicker" data-date-format="yyyy-mm-dd" readonly="readonly" maxlength="10" type="text" id="ftEnddate" name="ftEnddate" placeholder="结束日期" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fdFullLowQp">挂牌价格全票</label>
											<div class="col-sm-3">
												<input type="hidden" name="tccPrices[0].fsCcno" value="000001">
												<input class="form-control" maxlength="10" type="text" id="fdFullLowQp" name="tccPrices[0].fdPrice" placeholder="挂牌价格全票" />
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="fdHalfLowQp">挂牌价格半票</label>
											<div class="col-sm-3">
												<input type="hidden" name="tccPrices[1].fsCcno" value="000002">
												<input class="form-control" maxlength="10" type="text" id="fdHalfLowQp" name="tccPrices[1].fdPrice" placeholder="挂牌价格半票" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fdChildLowQp">挂牌价格儿童票</label>
											<div class="col-sm-3">
												<input type="hidden" name="tccPrices[2].fsCcno" value="000003">
												<input class="form-control" maxlength="10" type="text" id="fdChildLowQp" name="tccPrices[2].fdPrice" placeholder="挂牌价格儿童票" />
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="fdFreeLowQp">挂牌价格免票</label>
											<div class="col-sm-3">
												<input type="hidden" name="tccPrices[3].fsCcno" value="000004">
												<input class="form-control" maxlength="10" type="text" id="fdFreeLowQp" name="tccPrices[3].fdPrice" placeholder="挂牌价格免票" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fdFullLowTp">团队价格全票</label>
											<div class="col-sm-3">
												<input type="hidden" name="tccPrices[4].fsCcno" value="000005">
												<input class="form-control" maxlength="10" type="text" id="fdFullLowTp" name="tccPrices[4].fdPrice" placeholder="团队价格全票" />
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="fdHalfLowTp">团队价格半票</label>
											<div class="col-sm-3">
												<input type="hidden" name="tccPrices[5].fsCcno" value="000006">
												<input class="form-control" maxlength="10" type="text" id="fdHalfLowTp" name="tccPrices[5].fdPrice" placeholder="团队价格半票" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fdChildLowTp">团队价格儿童票</label>
											<div class="col-sm-3">
												<input type="hidden" name="tccPrices[6].fsCcno" value="000007">
												<input class="form-control" maxlength="10" type="text" id="fdChildLowTp" name="tccPrices[6].fdPrice" placeholder="团队价格儿童票" />
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="fdFreeLowTp">团队价格免票</label>
											<div class="col-sm-3">
												<input type="hidden" name="tccPrices[7].fsCcno" value="000008">
												<input class="form-control" maxlength="10" type="text" id="fdFreeLowTp" name="tccPrices[7].fdPrice" placeholder="团队价格免票" />
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
	<script src="/js/bus/ticketPrice/add.js"></script>

	<c:if test="${!empty succflag && succflag =='1'}">

		<script type="text/javascript">
	          $('.jump-step').removeClass('hide');
	          setTimeout('window.location="/home.htm"',5000);
	    </script>

	</c:if>
	<script type="text/javascript">
		$('.datetimepicker').datetimepicker({
			language:  'zh-CN',
	        weekStart: 1,
	        todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			minView: 2,
			forceParse: 0
		});
	</script>
</body>
</html>
