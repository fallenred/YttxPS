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
											<!-- <label class="col-sm-2 control-label no-padding-right" for="fsNo">门票代码</label>
											<div class="col-sm-3">
												<input maxlength="10" type="text" id="fsNo" name="fsNo" placeholder="门票代码需唯一" />
											</div> -->
											<label class="col-sm-2 control-label no-padding-right" for="fsName">票名称</label>
											<div class="col-sm-3">
												<select class="form-control" id="fsNo" name="fsNo"></select>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											
											<label class="col-sm-2 control-label no-padding-right" for="fsType">价格类型</label>
											<div class="col-sm-3" >
												<select id="priceType" name="priceType" class="form-control">
													<option value="1">淡季</option>
													<option value="2">旺季</option>
												</select>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="ftStartdate">开始日期</label>
											<div class="col-sm-3">
												<input class="form-control" maxlength="10" type="text" id="ftStartdate" name="ftStartdate" placeholder="淡季开始日期" />
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="ftEnddate">结束日期</label>
											<div class="col-sm-3">
												<input class="form-control" maxlength="10" type="text" id="ftEnddate" name="ftEnddate" placeholder="淡季结束日期" />
											</div>
										</div>
									</div>
									<div class="row low">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fdFullLowQp">淡季挂牌价格全票</label>
											<div class="col-sm-3">
												<input type="hidden" name="tccPrices[0].fsCcno" value="000001">
												<input class="form-control" maxlength="10" type="text" id="fdFullLowQp" name="tccPrices[0].fdPrice" placeholder="淡季挂牌价格全票" />
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="fdHalfLowQp">淡季挂牌价格半票</label>
											<div class="col-sm-3">
												<input type="hidden" name="tccPrices[1].fsCcno" value="000002">
												<input class="form-control" maxlength="10" type="text" id="fdHalfLowQp" name="tccPrices[1].fdPrice" placeholder="淡季挂牌价格半票" />
											</div>
										</div>
									</div>
									<div class="row low">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fdChildLowQp">淡季挂牌价格儿童票</label>
											<div class="col-sm-3">
												<input type="hidden" name="tccPrices[2].fsCcno" value="000003">
												<input class="form-control" maxlength="10" type="text" id="fdChildLowQp" name="tccPrices[2].fdPrice" placeholder="淡季挂牌价格儿童票" />
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="fdFreeLowQp">淡季挂牌价格免票</label>
											<div class="col-sm-3">
												<input type="hidden" name="tccPrices[3].fsCcno" value="000004">
												<input class="form-control" maxlength="10" type="text" id="fdFreeLowQp" name="tccPrices[3].fdPrice" placeholder="淡季挂牌价格免票" />
											</div>
										</div>
									</div>
									<div class="row low">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fdFullLowTp">淡季团队价格全票</label>
											<div class="col-sm-3">
												<input type="hidden" name="tccPrices[4].fsCcno" value="000005">
												<input class="form-control" maxlength="10" type="text" id="fdFullLowTp" name="tccPrices[4].fdPrice" placeholder="淡季团队价格全票" />
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="fdHalfLowTp">淡季团队价格半票</label>
											<div class="col-sm-3">
												<input type="hidden" name="tccPrices[5].fsCcno" value="000006">
												<input class="form-control" maxlength="10" type="text" id="fdHalfLowTp" name="tccPrices[5].fdPrice" placeholder="淡季团队价格半票" />
											</div>
										</div>
									</div>
									<div class="row low">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fdChildLowTp">淡季团队价格儿童票</label>
											<div class="col-sm-3">
												<input type="hidden" name="tccPrices[6].fsCcno" value="000007">
												<input class="form-control" maxlength="10" type="text" id="fdChildLowTp" name="tccPrices[6].fdPrice" placeholder="淡季团队价格儿童票" />
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="fdFreeLowTp">淡季团队价格免票</label>
											<div class="col-sm-3">
												<input type="hidden" name="tccPrices[7].fsCcno" value="000008">
												<input class="form-control" maxlength="10" type="text" id="fdFreeLowTp" name="tccPrices[7].fdPrice" placeholder="淡季团队价格免票" />
											</div>
										</div>
									</div>
									<div class="row peak">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fdFullPeakQp">旺季挂牌价格全票</label>
											<div class="col-sm-3">
												<input type="hidden" name="tccPrices[8].fsCcno" value="000009">
												<input class="form-control" maxlength="10" type="text" id="fdFullPeakQp" name="tccPrices[8].fdPrice" placeholder="旺季挂牌价格全票" />
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="fdHalfPeakQp">旺季挂牌价格半票</label>
											<div class="col-sm-3">
												<input type="hidden" name="tccPrices[9].fsCcno" value="000010">
												<input class="form-control" maxlength="10" type="text" id="fdHalfPeakQp" name="tccPrices[9].fdPrice" placeholder="旺季挂牌价格半票" />
											</div>
										</div>
									</div>
									<div class="row peak">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fdChildPeakQp">旺季挂牌价格儿童票</label>
											<div class="col-sm-3">
												<input type="hidden" name="tccPrices[10].fsCcno" value="000011">
												<input class="form-control" maxlength="10" type="text" id="fdChildPeakQp" name="tccPrices[10].fdPrice" placeholder="旺季挂牌价格儿童票" />
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="fdFreePeakQp">旺季挂牌价格免票</label>
											<div class="col-sm-3">
												<input type="hidden" name="tccPrices[11].fsCcno" value="000012">
												<input class="form-control" maxlength="10" type="text" id="fdFreePeakQp" name="tccPrices[11].fdPrice" placeholder="旺季挂牌价格免票" />
											</div>
										</div>
									</div>
									<div class="row peak">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fdFullPeakTp">旺季团队价格全票</label>
											<div class="col-sm-3">
												<input type="hidden" name="tccPrices[12].fsCcno" value="000013">
												<input class="form-control" maxlength="10" type="text" id="fdFullPeakTp" name="tccPrices[12].fdPrice" placeholder="旺季团队价格全票" />
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="fdHalfPeakTp">旺季团队价格半票</label>
											<div class="col-sm-3">
												<input type="hidden" name="tccPrices[13].fsCcno" value="000014">
												<input class="form-control" maxlength="10" type="text" id="fdHalfPeakTp" name="tccPrices[13].fdPrice" placeholder="旺季团队价格半票" />
											</div>
										</div>
									</div>
									<div class="row peak">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fdChildPeakTp">旺季团队价格儿童票</label>
											<div class="col-sm-3">
												<input type="hidden" name="tccPrices[14].fsCcno" value="000015">
												<input class="form-control" maxlength="10" type="text" id="fdChildPeakTp" name="tccPrices[14].fdPrice" placeholder="旺季团队价格儿童票" />
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="fdFreePeakTp">旺季团队价格免票</label>
											<div class="col-sm-3">
												<input type="hidden" name="tccPrices[15].fsCcno" value="000016">
												<input class="form-control" maxlength="10" type="text" id="fdFreePeakTp" name="tccPrices[15].fdPrice" placeholder="旺季团队价格免票" />
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

</body>
</html>
