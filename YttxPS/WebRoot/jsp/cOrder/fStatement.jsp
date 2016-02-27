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
							<a href="javascript:void(0)">结算单</a>
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
	
				<!-- 右边页面的主页面 -->
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
											<div class="profile-user-info profile-user-info-striped">
												<div class="profile-info-row">
													<div class="profile-info-name">结算单号:</div>
													<div class="profile-info-value">${fs.statmentId}</div>
												</div>
												<div class="profile-info-row">
													<div class="profile-info-name">结算单名称:</div>
													<div class="profile-info-value">${fs.statmentName}</div>
												</div>
												<div class="profile-info-row">
													<div class="profile-info-name">创建时间:</div>
													<div class="profile-info-value">${fs.creatDateDesc}</div>
												</div>
												
												<c:if test="${fStat.routeID!=null}">
													<div class="profile-info-row">
														<div class="profile-info-name">线路ID:</div>
														<div class="profile-info-value">${fs.routeID}</div>
													</div>
												</c:if>
												
												<c:if test="${fStat.routeName!=null}">
													<div class="profile-info-row">
														<div class="profile-info-name">线路名称:</div>
														<div class="profile-info-value">${fs.routeName}</div>
													</div>
												</c:if>
												
												<div class="profile-info-row">
													<div class="profile-info-name">订单ID:</div>
													<div class="profile-info-value">${fs.orderId}</div>
												</div>
												<div class="profile-info-row">
													<div class="profile-info-name">客户ID:</div>
													<div class="profile-info-value">${fs.userID}-${fs.userSubID}</div>
												</div>
												<div class="profile-info-row">
													<div class="profile-info-name">订单快照:</div>
													<div class="profile-info-value">${fs.orderContent}</div>
												</div>
												<div class="profile-info-row">
													<div class="profile-info-name">结算单预估全价:</div>
													<div class="profile-info-value">${fs.totalFee}</div>
												</div>
												
												<div class="profile-info-row">
													<div class="profile-info-name">已付金额:</div>
													<div class="profile-info-value">${fs.paidAmt}</div>
												</div>
												
												<div class="profile-info-row">
													<div class="profile-info-name">双方需交易金额:</div>
													<div class="profile-info-value">${fs.amt}</div>
												</div>
												<div class="profile-info-row">
													<div class="profile-info-name">整体备注:</div>
													<div class="profile-info-value">${fs.remark}</div>
												</div>
											</div>
											<div class="row">
												<div class="space-10"></div>
												<div class="col-sm-offset-8 col-sm-4">
													<c:if test="${fs.stat==0}">
														<button class="btn btn-warning btn-lg">正在等待客户确认</button>
													</c:if>
													<c:if test="${fs.stat==1}">
														<button class="btn btn-success btn-lg " type="button" onClick="adminConfirmFS('${fs.statmentId}')">客户已确认，请确认结算是否完毕？</button>
													</c:if>
													<c:if test="${fs.stat==2}">
														<button class="btn btn-warning btn-lg">结算完毕</button>
													</c:if>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- 右边页面的主页面 -->
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
						<h4 class="modal-title" id="warnModalLabel">确认结算完毕提示</h4>
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
		function adminConfirmFS(fsId){
			var postData={fsId:fsId};
			$.post(
				"/cOrder/confirmFS.htm",
				postData,
				function(data){
					var json = eval("("+data+")");
					var message=null;
					if(json.result == "ok") {
						message="确认结算完毕成功！<br/>"+
							"<a href='/cOrder/page.htm'>返回结算管理</a>"
					}else {
						message=data.message;
					}
					$("#warnmessage").html(message);
					$('#warnModal').modal({ show: true, backdrop: 'static' });
				});
		}
	</script>
</body>
</html>
