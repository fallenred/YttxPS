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

							<!-- 模态框（修改） -->

							<form class="form-horizontal" id="editform" >

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
											<input maxlength="10" type="hidden" id="fsNo" name="fsNo" placeholder="门票代码需唯一" />
											<label class="col-sm-2 control-label no-padding-right" for="fsName">票名称</label>
											<div class="col-sm-2">
												<input type="text" id="fsName" name="fsName" placeholder="票名称"  maxlength="10" />
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="fsScenicno">所属景区</label>
											<div class="col-sm-2">
												<input maxlength="10" type="text" id="fsScenicno" name="fsScenicno" placeholder="所属景区" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fsType">门票类型</label>
											<div class="col-sm-2">
												<select id="fsType" name="fsType">
													<option value="01">主门票</option>
													<option value="02">车票</option>
													<option value="03">小景区</option>
												</select>
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="fsType">状态</label>
											<div class="col-sm-2">
												<select id="fiStat" name="fiStat">
													<option value="1">正常</option>
													<option value="2">失效</option>
												</select>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fsDesc">描述</label>
											<div class="col-sm-6">
												<input type="text" id="fsDesc" name="fsDesc" class="form-control"
													placeholder="描述" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fdFullLowQp">淡季挂牌价格全票</label>
											<div class="col-sm-2">
												<input maxlength="10" type="text" id="fdFullLowQp" name="fdFullLowQp" placeholder="淡季挂牌价格全票" />
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="fdHalfLowQp">淡季挂牌价格半票</label>
											<div class="col-sm-2">
												<input maxlength="10" type="text" id="fdHalfLowQp" name="fdHalfLowQp" placeholder="淡季挂牌价格半票" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fdChildLowQp">淡季挂牌价格儿童票</label>
											<div class="col-sm-2">
												<input maxlength="10" type="text" id="fdChildLowQp" name="fdChildLowQp" placeholder="淡季挂牌价格儿童票" />
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="fdFreeLowQp">淡季挂牌价格免票</label>
											<div class="col-sm-2">
												<input maxlength="10" type="text" id="fdFreeLowQp" name="fdFreeLowQp" placeholder="淡季挂牌价格免票" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fdFullLowTp">淡季团队价格全票</label>
											<div class="col-sm-2">
												<input maxlength="10" type="text" id="fdFullLowTp" name="fdFullLowTp" placeholder="淡季团队价格全票" />
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="fdHalfLowTp">淡季团队价格半票</label>
											<div class="col-sm-2">
												<input maxlength="10" type="text" id="fdHalfLowTp" name="fdHalfLowTp" placeholder="淡季团队价格半票" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fdChildLowTp">淡季团队价格儿童票</label>
											<div class="col-sm-2">
												<input maxlength="10" type="text" id="fdChildLowTp" name="fdChildLowTp" placeholder="淡季团队价格儿童票" />
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="fdFreeLowTp">淡季团队价格免票</label>
											<div class="col-sm-2">
												<input maxlength="10" type="text" id="fdFreeLowTp" name="fdFreeLowTp" placeholder="淡季团队价格免票" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fdFullPeakQp">旺季挂牌价格全票</label>
											<div class="col-sm-2">
												<input maxlength="10" type="text" id="fdFullPeakQp" name="fdFullPeakQp" placeholder="旺季挂牌价格全票" />
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="fdHalfPeakQp">旺季挂牌价格半票</label>
											<div class="col-sm-2">
												<input maxlength="10" type="text" id="fdHalfPeakQp" name="fdHalfPeakQp" placeholder="旺季挂牌价格半票" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fdChildPeakQp">旺季挂牌价格儿童票</label>
											<div class="col-sm-2">
												<input maxlength="10" type="text" id="fdChildPeakQp" name="fdChildPeakQp" placeholder="旺季挂牌价格儿童票" />
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="fdFreePeakQp">旺季挂牌价格免票</label>
											<div class="col-sm-2">
												<input maxlength="10" type="text" id="fdFreePeakQp" name="fdFreePeakQp" placeholder="旺季挂牌价格免票" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fdFullPeakTp">旺季团队价格全票</label>
											<div class="col-sm-2">
												<input maxlength="10" type="text" id="fdFullPeakTp" name="fdFullPeakTp" placeholder="旺季团队价格全票" />
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="fdHalfPeakTp">旺季团队价格半票</label>
											<div class="col-sm-2">
												<input maxlength="10" type="text" id="fdHalfPeakTp" name="fdHalfPeakTp" placeholder="旺季团队价格半票" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fdChildPeakTp">旺季团队价格儿童票</label>
											<div class="col-sm-2">
												<input maxlength="10" type="text" id="fdChildPeakTp" name="fdChildPeakTp" placeholder="旺季团队价格儿童票" />
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="fdFreePeakTp">旺季团队价格免票</label>
											<div class="col-sm-2">
												<input maxlength="10" type="text" id="fdFreePeakTp" name="fdFreePeakTp" placeholder="旺季团队价格免票" />
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
	<script src="/js/bus/ticket/edit.js"></script>

	<c:if test="${!empty succflag && succflag =='1'}">

		<script type="text/javascript">
	          $('.jump-step').removeClass('hide');
	          setTimeout('window.location="/home.htm"',5000);
	    </script>

	</c:if>

</body>
</html>