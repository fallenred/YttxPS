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
									<h4 class="modal-title" id="showModalLabel">娱乐项目详情</h4>
								</div>
								<div class="modal-body">
									<div>
										<ul class="ace-thumbnails clearfix">
											<div style="visibility: hidden;">
												<li><a class="cboxElement" data-rel="colorbox"
													href="http://127.0.0.1:81/1.jpg"> <img width="0" height="0" src="http://127.0.0.1:81/2.png" alt="0*0">
												</a></li>
											</div>
										</ul>
									</div>

									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fsName">娱乐项目名称</label>
											<div class="col-sm-2">
												<input class="form-control" type="text" id="fsName"/>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											
											<label class="col-sm-2 control-label no-padding-right" for="fsType">价格类型</label>
											<div class="col-sm-2" >
												<select id="priceType" name="priceType" class="form-control">
													<option value="1">淡季</option>
													<option value="2">旺季</option>
													<option value="3">接送费用</option>
												</select>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="ftStartdate">开始日期</label>
											<div class="col-sm-2">
												<input class="form-control" maxlength="10" type="text" id="ftStartdate" name="ftStartdate" placeholder="淡季开始日期" />
											</div>
											<label class="col-sm-2 col-md-offset-2 control-label no-padding-right" for="ftEnddate">结束日期</label>
											<div class="col-sm-2">
												<input class="form-control" maxlength="10" type="text" id="ftEnddate" name="ftEnddate" placeholder="淡季结束日期" />
											</div>
										</div>
									</div>
									<div class="row low">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fdfulllowqp">淡季挂牌价格全票</label>
											<div class="col-sm-2">
												<input type="hidden" name="tccPrices[0].fsCcno" value="000001">
												<input class="form-control" maxlength="10" type="text" id="fdfulllowqp" name="tccPrices[0].fdPrice" placeholder="淡季挂牌价格全票" />
											</div>
											<label class="col-sm-2 col-md-offset-2 control-label no-padding-right" for="fdhalflowqp">淡季挂牌价格半票</label>
											<div class="col-sm-2">
												<input type="hidden" name="tccPrices[1].fsCcno" value="000002">
												<input class="form-control" maxlength="10" type="text" id="fdhalflowqp" name="tccPrices[1].fdPrice" placeholder="淡季挂牌价格半票" />
											</div>
										</div>
									</div>
									<div class="row low">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fdchildlowqp">淡季挂牌价格儿童票</label>
											<div class="col-sm-2">
												<input type="hidden" name="tccPrices[2].fsCcno" value="000003">
												<input class="form-control" maxlength="10" type="text" id="fdchildlowqp" name="tccPrices[2].fdPrice" placeholder="淡季挂牌价格儿童票" />
											</div>
											<label class="col-sm-2 col-md-offset-2 control-label no-padding-right" for="fdfreelowqp">淡季挂牌价格免票</label>
											<div class="col-sm-2">
												<input type="hidden" name="tccPrices[3].fsCcno" value="000004">
												<input class="form-control" maxlength="10" type="text" id="fdfreelowqp" name="tccPrices[3].fdPrice" placeholder="淡季挂牌价格免票" />
											</div>
										</div>
									</div>
									<div class="row low">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fdfulllowtp">淡季团队价格全票</label>
											<div class="col-sm-2">
												<input type="hidden" name="tccPrices[4].fsCcno" value="000005">
												<input class="form-control" maxlength="10" type="text" id="fdfulllowtp" name="tccPrices[4].fdPrice" placeholder="淡季团队价格全票" />
											</div>
											<label class="col-sm-2 col-md-offset-2 control-label no-padding-right" for="fdhalflowtp">淡季团队价格半票</label>
											<div class="col-sm-2">
												<input type="hidden" name="tccPrices[5].fsCcno" value="000006">
												<input class="form-control" maxlength="10" type="text" id="fdhalflowtp" name="tccPrices[5].fdPrice" placeholder="淡季团队价格半票" />
											</div>
										</div>
									</div>
									<div class="row low">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fdchildlowtp">淡季团队价格儿童票</label>
											<div class="col-sm-2">
												<input type="hidden" name="tccPrices[6].fsCcno" value="000007">
												<input class="form-control" maxlength="10" type="text" id="fdchildlowtp" name="tccPrices[6].fdPrice" placeholder="淡季团队价格儿童票" />
											</div>
											<label class="col-sm-2 col-md-offset-2 control-label no-padding-right" for="fdfreelowtp">淡季团队价格免票</label>
											<div class="col-sm-2">
												<input type="hidden" name="tccPrices[7].fsCcno" value="000008">
												<input class="form-control" maxlength="10" type="text" id="fdfreelowtp" name="tccPrices[7].fdPrice" placeholder="淡季团队价格免票" />
											</div>
										</div>
									</div>
									<div class="row peak">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fdfullpeakqp">旺季挂牌价格全票</label>
											<div class="col-sm-2">
												<input type="hidden" name="tccPrices[8].fsCcno" value="000009">
												<input class="form-control" maxlength="10" type="text" id="fdfullpeakqp" name="tccPrices[8].fdPrice" placeholder="旺季挂牌价格全票" />
											</div>
											<label class="col-sm-2 col-md-offset-2 control-label no-padding-right" for="fdhalfpeakqp">旺季挂牌价格半票</label>
											<div class="col-sm-2">
												<input type="hidden" name="tccPrices[9].fsCcno" value="000010">
												<input class="form-control" maxlength="10" type="text" id="fdhalfpeakqp" name="tccPrices[9].fdPrice" placeholder="旺季挂牌价格半票" />
											</div>
										</div>
									</div>
									<div class="row peak">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fdchildpeakqp">旺季挂牌价格儿童票</label>
											<div class="col-sm-2">
												<input type="hidden" name="tccPrices[10].fsCcno" value="000011">
												<input class="form-control" maxlength="10" type="text" id="fdchildpeakqp" name="tccPrices[10].fdPrice" placeholder="旺季挂牌价格儿童票" />
											</div>
											<label class="col-sm-2 col-md-offset-2 control-label no-padding-right" for="fdfreepeakqp">旺季挂牌价格免票</label>
											<div class="col-sm-2">
												<input type="hidden" name="tccPrices[11].fsCcno" value="000012">
												<input class="form-control" maxlength="10" type="text" id="fdfreepeakqp" name="tccPrices[11].fdPrice" placeholder="旺季挂牌价格免票" />
											</div>
										</div>
									</div>
									<div class="row peak">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fdfullpeaktp">旺季团队价格全票</label>
											<div class="col-sm-2">
												<input type="hidden" name="tccPrices[12].fsCcno" value="000013">
												<input class="form-control" maxlength="10" type="text" id="fdfullpeaktp" name="tccPrices[12].fdPrice" placeholder="旺季团队价格全票" />
											</div>
											<label class="col-sm-2 col-md-offset-2 control-label no-padding-right" for="fdhalfpeaktp">旺季团队价格半票</label>
											<div class="col-sm-2">
												<input type="hidden" name="tccPrices[13].fsCcno" value="000014">
												<input class="form-control" maxlength="10" type="text" id="fdhalfpeaktp" name="tccPrices[13].fdPrice" placeholder="旺季团队价格半票" />
											</div>
										</div>
									</div>
									<div class="row peak">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fdchildpeaktp">旺季团队价格儿童票</label>
											<div class="col-sm-2">
												<input type="hidden" name="tccPrices[14].fsCcno" value="000015">
												<input class="form-control" maxlength="10" type="text" id="fdchildpeaktp" name="tccPrices[14].fdPrice" placeholder="旺季团队价格儿童票" />
											</div>
											<label class="col-sm-2 col-md-offset-2 control-label no-padding-right" for="fdfreepeaktp">旺季团队价格免票</label>
											<div class="col-sm-2">
												<input type="hidden" name="tccPrices[15].fsCcno" value="000016">
												<input class="form-control" maxlength="10" type="text" id="fdfreepeaktp" name="tccPrices[15].fdPrice" placeholder="旺季团队价格免票" />
											</div>
										</div>
									</div>
									
									<div class="row trans">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fdtranscoststp">接送费用</label>
											<div class="col-sm-2">
												<input type="hidden" name="tccPrices[16].fsCcno" value="000017">
												<input class="form-control" maxlength="10" type="text" id="fdtranscoststp" name="tccPrices[16].fdPrice" placeholder="接送费用" />
											</div>
										</div>
									</div>

								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
									<button id="reset" type="reset" class="btn" style="display: none;">重置</button>
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
	<script src="/js/bus/entertainmentPrice/show.js"></script>

	<c:if test="${!empty succflag && succflag =='1'}">

		<script type="text/javascript">
	          $('.jump-step').removeClass('hide');
	          setTimeout('window.location="/home.htm"',5000);
	    </script>
	</c:if>

</body>
</html>