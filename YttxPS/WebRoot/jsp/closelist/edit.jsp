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
	<script type="text/javascript" src="../../js/bootstrap-wysiwyg/jquery.hotkeys.min.js"></script>
	<script type="text/javascript" src="../../js/bootstrap-wysiwyg/bootstrap-wysiwyg.min.js"></script>
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
											<label class="col-sm-2 control-label no-padding-right" for="fsRouteId">线路名称</label>
											<div class="col-sm-2">
												<input type="hidden" id="fsRouteId" name="fsRouteId" class="form-control" placeholder="线路i"  />
												<input type="text" id="fsRoutename" name="fsRoutename" class="form-control" placeholder="线路名称"  />
											</div>
											<label class="col-sm-2 col-md-offset-2 control-label no-padding-right" for="fsName">结算单名称</label>
											<div class="col-sm-2">
												<input type="hidden" id="fsNo" name="fsNo" class="form-control" placeholder="结算id"  />
												<input type="text" id="fsName" name="fsName" class="form-control" placeholder="结算单名称"  />
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="form-group">
										<!-- <label class="col-sm-2 control-label no-padding-right" for="fsOrderId">订单编号</label>
											<div class="col-sm-2">
												<input type="text" id="fsOrderId" name="fsOrderId" class="form-control" placeholder="线路名称"  />
											</div> -->
											<label class="col-sm-2 control-label no-padding-right" for="fsOrderName">订单名称</label>
											<div class="col-sm-2">
												<input type="hidden" id="fsOrderId" name="fsOrderId" class="form-control" placeholder="订单id"  />
												<input type="text" id="fsOrderName" name="fsOrderName" class="form-control" placeholder="订单名称"  />
											</div>
											<label class="col-sm-2 col-md-offset-2 control-label no-padding-right" for="fsOrderName">用户名称</label>
											<div class="col-sm-2">
												<input type="hidden" id="fsUserId" name="fsUserId" class="form-control" placeholder="用户id"  />
												<input type="text" id="fsUserName" name="fsUserName" class="form-control" placeholder="用户名称"  />
												<input type="hidden" id="fsUserSubid" name="fsUserSubid" class="form-control" placeholder="用户子id"  />
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fsOperId">计调员名称</label>
											<div class="col-sm-2">
												<input type="hidden" id="fsOperId" name="fsOperId" class="form-control" placeholder="计调员id"  />
												<input type="text" id="fsOperName" name="fsOperName" class="form-control" placeholder="计调员名称"  />
											</div>
											<label class="col-sm-2 col-md-offset-2 control-label no-padding-right" for="fdTotalfee">预估全价</label>
											<div class="col-sm-2">
												<input type="text" id="fdTotalfee" name="fdTotalfee" class="form-control" placeholder="预估全价"  />
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fdPaidamt">已付金额</label>
											<div class="col-sm-2">
												<input type="text" id="fdPaidamt" name="fdPaidamt" class="form-control" placeholder="已付金额"  />
											</div>
											<label class="col-sm-2 col-md-offset-2 control-label no-padding-right" for="fdAmt">双方需交易金额</label>
											<div class="col-sm-2">
												<input type="text" id="fdAmt" name="fdAmt" class="form-control" placeholder="预估全价"  />
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fiStat">状态</label>
											<div class="col-sm-2">
												<select id="fiStat" name="fiStat">
													<option value="0">客户待确认</option>
													<option value="1">结算单已确认</option>
													<option value="2">结算完毕</option>
												</select>
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fiStat">整体备注</label>
											<div class="col-sm-8">
												<textarea class="form-control" id="fsRemark" name="fsRemark" rows="3" placeholder="整体备注"></textarea>
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fiStat">结算单快照</label>
											<div class="col-sm-8">
												<textarea class="ckeditor" id="fcContent" name="fcContent"></textarea>
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
	<script src="/js/bus/closelist/edit.js"></script>
	
	<c:if test="${!empty succflag && succflag =='1'}">

		<script type="text/javascript">
	          $('.jump-step').removeClass('hide');
	          setTimeout('window.location="/home.htm"',5000);
	    </script>

	</c:if>
</body>
</html>
