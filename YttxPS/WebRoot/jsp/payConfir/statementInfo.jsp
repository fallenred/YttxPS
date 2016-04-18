<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt'%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8" />
<title>后台管理系统</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<jsp:include page="/jsp/comm/css.jsp" flush="true" />
<jsp:include page="/jsp/comm/scripts.jsp" flush="true" />
<link rel="stylesheet" href="/css/daterangepicker.css" />
<script src="/js/date-time/moment.min.js"></script>
<script src="/js/date-time/daterangepicker.min.js"></script>
<script src="http://apps.bdimg.com/libs/angular.js/1.4.6/angular.min.js"></script>

<script src="/js/handlebars.js"></script>
</head>
<body class="no-skin">
	<jsp:include page="/jsp/comm/topbar.jsp" flush="true" />
	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			}catch(e){}
		</script>

		<div class="main-container-inner">
			<a class="menu-toggler" id="menu-toggler" href="#"> <span
				class="menu-text"></span>
			</a>
			<jsp:include page="/jsp/comm/sidebar.jsp" flush="true" />

			<div class="main-content">
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
						try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
					</script>
					<ul class="breadcrumb">
						<li>
							<a href="/payConfir/page.htm">线下支付确认</a>
						</li>
						<li>
							<a href="/payConfir/page.htm">结算单支付确认</a>
						</li>
							<li>${statement.statmentName}</li>
					</ul>
					<div class="nav-search" id="nav-search">
						<form class="form-search">
							<span class="input-icon">
								<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off">
								<i class="icon-search nav-search-icon"></i>
							</span>
						</form>
					</div>
				</div>

				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->

							<!-- 模态框（修改） -->

							<form class="form-horizontal" id="editform">

								<div class="modal-body">
									<!-- 图片TODO： -->
									<div>
										<ul class="ace-thumbnails clearfix">
											<div style="visibility: hidden;">
												<li><a class="cboxElement" data-rel="colorbox"
													href="http://127.0.0.1:81/1.jpg"> <img width="0"
														height="0" src="http://127.0.0.1:81/2.png" alt="0*0">
												</a>
												</li>
											</div>
										</ul>
									</div>

									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="fsNo">结算单名称</label>
											<div class="col-sm-8">
												 <input type="text" id="fsName" class="form-control"
													placeholder="结算单名称" value="${statement.statmentName}" readonly="readonly"/>
												 <input type="hidden" name="fsId" class="form-control"
													placeholder="结算单id" value="${statement.statmentId}"/>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="fsName">订单编号</label>
											<div class="col-sm-3">
												<input type="text" id="orderId" name="orderId" readonly="readonly"
													class="form-control" placeholder="订单id" value="${statement.orderId}"/>
											</div>
											<label class="col-sm-2 control-label no-padding-right"
												for="ftCreatdate">创建日期</label>
											<div class="col-sm-3">
												<input type="text" id="ftCreatdate" value="${statement.creatDate}"
													class="form-control" placeholder="创建日期" readonly="readonly"/>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="ftStartdate">用户id</label>
											<div class="col-sm-3">
												<input type="text" id="userID"
													class="form-control"  placeholder="发团日期" value="${statement.userID }" readonly="readonly"/>
											</div>
											<label class="col-sm-2 control-label no-padding-right"
												for="fsOperId">计&nbsp;调&nbsp;&nbsp;员</label>
											<div class="col-sm-3">
												<input type="text" id="operName" class="form-control" placeholder="计调员" value="" readonly="readonly"/>
												<input type="hidden" id="fsOperId" class="form-control" placeholder="计调员" value="${statement.operId}" readonly="readonly"/>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="fdInsuerprice">预估全价</label>
											<div class="col-sm-3">
												<div class="input-group">
													<input type="text" id="totalFee" class="form-control" value="${statement.totalFee }" readonly="readonly">
													<span class="input-group-addon">￥</span>
												</div>
											</div>
											<label class="col-sm-2 control-label no-padding-right"
												for="fdPrice">已付金额</label>
											<div class="col-sm-3">
												<div class="input-group">
													<input type="text" id="paidAmt"
														name="paidAmt" class="form-control" value="${statement.totalFee - statement.amt}" readonly="readonly"/>
													<span class="input-group-addon">￥</span>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="fdPrice">收款金额</label>
											<div class="col-sm-3">
												<div class="input-group">
													<input type="text" id="amt"
														name="amt" class="form-control required isFloatGteZero number" value="${order.amt }"/>
													<span class="input-group-addon">￥</span>
												</div>
											</div>
										</div>
									</div>
									<!-- <div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="fdInsuerprice">付款账号</label>
											<div class="col-sm-3">
												<input type="text" id="totalFee"  placeholder="客户付款账号" class="form-control" value="">
											</div>
										</div>
									</div> -->
									<hr>
									<div id="message" class="alert alert-warning"></div>
								</div>
								<div class="modal-footer">
									<button onclick="history.go(-1)" type="button" class="btn btn-sm btn-default"
										data-dismiss="modal">返回</button>
									<button id="statementConfir_submit" type="button"
										class="btn btn-sm btn-primary">确认收款</button>
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
		</div>
	</div>
	<!-- /.main-container -->
	<script src="/js/bus/payConfir/confir.js"></script>
</body>
</html>
