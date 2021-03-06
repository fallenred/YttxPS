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
<script type="text/javascript" src="/js/daterangepicker/moment.min.js"></script>
<script type="text/javascript" src="/js/daterangepicker/daterangepicker.js"></script>
<link rel="stylesheet" href="/js/daterangepicker/daterangepicker.css" />

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
			<a class="menu-toggler" id="menu-toggler" href="#"> 
				<span class="menu-text"></span>
			</a>
			<jsp:include page="/jsp/comm/sidebar.jsp" flush="true" />
			
			<div class="main-content">
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
						try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
					</script>
					<ul class="breadcrumb">
						<li>
							<a href="/cOrder/page.htm">结算管理</a>
						</li>
						<li>
							<a href="javascript:void(0)">
								结算单详情
							</a>
						</li>
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
	
<%-- 				<!-- 右边页面的主页面 -->
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<div class="message-content" id="id-message-content">
								<div class="widget-box transparent">
									<div class="widget-header widget-header-small">
										<h4 class="blue smaller">
											<i class="icon-rss orange"></i>结算单详情
										</h4>
									</div>
									
									<div class="widget-body">
										<div class="widget-main padding-8">
											<form method="post" action="/cOrder/submitStat.htm" onsubmit="return checkParams();">
												<input type="hidden" name="oper" value="${oper}">
												<div class="profile-user-info profile-user-info-striped ">
													<c:if test="${fStat.statmentId!=null}">
														<div class="profile-info-row">
															<div class="profile-info-name">结算单号:</div>
															<div class="profile-info-value">
																<input  type="text" id="statmentId" class="col-sm-10"
																	name="statmentId" value="${fStat.statmentId}" readonly/>
															</div>
														</div>
													</c:if>
													
													<div class="profile-info-row">
														<div class="profile-info-name">结算单名称:</div>
														<div class="profile-info-value">
															<input type="text" id="statmentName"  class="col-sm-10"
																name="statmentName" value="${fStat.statmentName}"/>
														</div>
													</div>
													
													<c:if test="${fStat.creatDateDesc!=null}">
														<div class="profile-info-row">
															<div class="profile-info-name">创建时间:</div>
															<div class="profile-info-value">
																<input  type="text" id="creatDate" class="col-sm-10"
																	name="creatDate" value="${fStat.creatDateDesc}" readonly disabled/>
															</div>
														</div>
													</c:if>
													
													<c:if test="${fStat.routeID!=null}">
														<div class="profile-info-row">
															<div class="profile-info-name">线路ID:</div>
															<div class="profile-info-value">
																<input type="text" id="statmentName"  class="col-sm-10"
																	name="routeID" value="${fStat.routeID}" readonly/>
															</div>
														</div>
													</c:if>
													
													<c:if test="${fStat.routeName!=null}">
														<div class="profile-info-row">
															<div class="profile-info-name">线路名称:</div>
															<div class="profile-info-value">
																<input  type="text" id="routeName" class="col-sm-10"
																	name="routeName" value="${fStat.routeName}" readonly/>
															</div>
														</div>
													</c:if>
													
													<div class="profile-info-row">
														<div class="profile-info-name">订单ID:</div>
														<div class="profile-info-value">
															<input  type="text" id="orderId"  class="col-sm-10"
																	name="orderId" value="${fStat.orderId}" readonly/>
														</div>
													</div>
													<div class="profile-info-row">
														<div class="profile-info-name">客户ID:</div>
														<div class="profile-info-value">
															<input  type="text" id="userID" class="col-sm-10"
																	name="userID" value="${fStat.userID}" readonly/>
														</div>
													</div>
													<div class="profile-info-row">
														<div class="profile-info-name">客户子ID:</div>
														<div class="profile-info-value">
															<input  type="text" id="userSubID"  class="col-sm-10"
																	name="userSubID" value="${fStat.userSubID}" readonly/>
														</div>
													</div>
													
													<c:if test="${fStat.operId!=null}">
														<div class="profile-info-row">
															<div class="profile-info-name">计调ID:</div>
															<div class="profile-info-value">
																<input  type="text" id="operId" class="col-sm-10"
																	name="operId" value="${fStat.operId}" readonly/>
															</div>
														</div>
													</c:if>
													<div class="profile-info-row">
														<div class="profile-info-name">订单快照:</div>
														<div class="profile-info-value">
															<textarea class="ckeditor" id="orderContent" name="orderContent" >
																<c:if test="${oper=='A'}">
																	<jsp:include page="orderContent.jsp" flush="true"/>
																</c:if>
																<c:if test="${oper=='E'}">
																	${fStat.orderContent}"
																</c:if>
															</textarea>
														</div>
													</div>
													
													<c:if test="${oper=='A'}">
													<div class="profile-info-row">
														<div class="profile-info-name">订单预估全价:</div>
														<div class="profile-info-value">
															<input  type="text" id="orderTotolFee" class="col-sm-10"
																	name="orderTotolFee" value="${fStat.orderTotolFee}" readonly/>
														</div>
													</div>
													</c:if>
													
													<c:if test="${oper=='A'}">
													<div class="profile-info-row">
														<div class="profile-info-name">订单备注金额统计:</div>
														<div class="profile-info-value">
															<input  type="text" id="remarksAmt" class="col-sm-10"
																	name="remarksAmt" value="${fStat.remarksAmt}" readonly/>
														</div>
													</div>
													</c:if>
													
													<div class="profile-info-row">
														<div class="profile-info-name">结算单预估全价:</div>
														<div class="profile-info-value">
															<input  type="text" id="totalFee" class="col-sm-10"
																	name="totalFee" value="${fStat.totalFee}"/>
														</div>
													</div>
													
													<div class="profile-info-row">
														<div class="profile-info-name">已付金额:</div>
														<div class="profile-info-value">
															<input  type="text" id="paidAmt" class="col-sm-10"
																	name="paidAmt" value="${fStat.paidAmt}"/>
														</div>
													</div>
													
													<div class="profile-info-row">
														<div class="profile-info-name">双方需交易金额:</div>
														<div class="profile-info-value">
															<input  type="text" id="amt" class="col-sm-10"
																	name="amt" value="${fStat.amt}"/>
														</div>
													</div>
													<div class="profile-info-row">
														<div class="profile-info-name">整体备注:</div>
														<div class="profile-info-value"> 
															<textarea name="remark" id="remark" cols="90" rows="5" class="col-sm-10" maxlength="300">${fStat.remark}</textarea>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="space-10"></div>
													<div class="col-sm-offset-9 col-sm-1">
														<button class="btn btn-info .btn-lg " type="submit">提交结算单</button>
													</div>
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- 右边页面的主页面 -->
 --%>		<jsp:include page="/cOrder/showCloselist.htm?orderid=${order.no }" flush="true" />	
 			</div>
		</div>
						
		<!-- 模态框数据验证弹出框 -->
		<div class="modal fade" id="warnModal" tabindex="-1" role="dialog"
			aria-labelledby="warnModalLabel" aria-hidden="false">
			<div class="modal-dialog" style="width: 400px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h4 class="modal-title" id="warnModalLabel">参数验证警告</h4>
					</div>
					<div class="modal-body">
						<div class="warning" id="warnmessage"></div>
					</div>
				</div>
			</div>
		</div>
	</div>		
	<!-- /.main-container -->
	<script type="text/javascript">
		/*
		 * 验证money
		 */
		function validMoney(money){
			var reg = /^-?\d+(\.\d{1,2})?$/;
			if(money != null && reg.test(money))
				return true
			return false;
		}
		
		function alertWarning(message){
			$("#warnmessage").text(message);
			$('#warnModal').modal({ show: true, backdrop: 'static' });
		}
	
		function checkParams(){
			var totalFee = $.trim($("#totalFee").val())//早餐价格
			if( !validMoney(totalFee)) {
				$("#totalFee").focus();
				alertWarning("结算单预估全价的输入格式(XXX.XX)不正确");
				return false;
			}
			
			var paidAmt = $.trim($("#paidAmt").val())//早餐价格
			if( !validMoney(paidAmt)) {
				$("#paidAmt").focus();
				alertWarning("已付金额的输入格式(XXX.XX)不正确");
				return false;
			}
			
			var amt = $.trim($("#amt").val())//早餐价格
			if( !validMoney(amt)) {
				$("#amt").focus();
				alertWarning("双方需交易金额的输入格式(XXX.XX)不正确");
				return false;
			}
			return true;
		}
	</script>
</body>
</html>
