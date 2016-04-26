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
<link rel="stylesheet" href="/css/daterangepicker.css" />
<script src="/js/date-time/moment.min.js"></script>
<script src="/js/date-time/daterangepicker.min.js"></script>
<script src="/js/handlebars.js"></script>
<script type="text/javascript">
	var order_stat_item = ${order_stat_item};
	var fsmt_stat_item = ${fsmt_stat_item};
</script>
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
				<!-- 上面的搜索栏 -->
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
						try {
							ace.settings.check('breadcrumbs', 'fixed')
						} catch (e) {
						}
					</script>
					<div class="nav-search" id="nav-search">
						<form class="form-search">
							<span class="input-icon"> <input type="text"
								placeholder="Search ..." class="nav-search-input"
								id="nav-search-input" autocomplete="off" /> <i
								class="ace-icon fa fa-search nav-search-icon"></i>
							</span>
						</form>
					</div>
				</div>
				<!-- 搜索栏结束 -->
				
				<!-- 右边页面的主页面 -->
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<div class="widget-box">
								<div class="widget-body">
									<div class="widget-main">
										<!-- 选项卡开始 -->
										<div class="tabbable">
											<ul class="nav nav-tabs" id="tab">
												<li<c:if test="${closeID == null }"> class="active"</c:if>>
													<a data-toggle="tab" id="all_order_a" href="#orderlist">
														<i></i>订单支付确认
													</a>
												</li>
												<li<c:if test="${closeID != null }"> class="active"</c:if>>
													<a data-toggle="tab" id="stat_div_a" href="#closelist" class="statPane">
														<i></i>结算单支付确认
													</a>
												</li>
												<!-- <li>
													<a data-toggle="tab" id="all_order_a" href="#all_order" class="statPane">
														<i></i>已确认账务流水
													</a>
												</li> -->
											</ul>
											
											<div class="tab-content">
												<!--全部订单 pane-->
												<div id="orderlist" class="tab-pane in active">
													<!-- 全部订单的过滤条件 -->
													<form class="form-horizontal" id="order_filter_form">
														<div class="form-group">
															<label class="col-sm-1 control-label no-padding-right" for="order_id">订单编号</label>
															<div class="col-sm-4 ">
																<input type="text" id="order_id" value="<c:if test="${orderID != null }">${orderID }</c:if>" placeholder="忽略订单编号" class="col-xs-10 col-sm-10">
															</div>
															<label class="col-sm-1 col-sm-offset-1 control-label no-padding-right" for="order_name">订单名称</label>
															<div class="col-sm-4">
																<input type="text" id="order_name" placeholder="忽略订单名称" class="col-xs-10 col-sm-10">
															</div>
														</div>
														
														<div class="form-group">
															<label class="col-sm-1 control-label no-padding-right" for="order_date_range">创建时间</label>
															<div class="col-sm-4 ">
																<input type="text" id="order_date_range" placeholder="创建时间范围" class="col-xs-10 col-sm-10" readonly>
															</div>
															<label class="col-sm-1 col-sm-offset-1 control-label no-padding-right" for="order_route_type">线路类型</label>
															<div class="col-sm-4">
																<select id="order_route_type" class="col-xs-10 col-sm-10">
																	<option value="" selected>请选择线路类型...</option>
																	<option value="02">专家线路</option>
																	<option value="03">定制线路</option>
																</select>
															</div>
														</div>
														<div class="form-group">
															<div class="col-sm-offset-4 col-sm-4">
																<button class="btn btn-info btn-xs" type="button" id="order_submit">
																	<i class="ace-icon fa fa-check"></i>查询
																</button>
																&nbsp; &nbsp; &nbsp;
																<button class="btn btn-xs" type="reset" id="order_reset">
																	<i class="ace-icon fa fa-undo"></i> 重置
																</button>
															</div>
														</div>
														<input type="hidden" name="order_drange" id="order_drange"/>
													</form>
													<!-- 全部订单的过滤条件结束 -->
													<table id="order_table"></table>
													<div id="order_pager"></div>
												</div>
												<!--全部订单 pane 结束-->
												<div id="closelist" class="tab-pane">
													<!-- 全部订单的过滤条件 -->
													<form class="form-horizontal" id="stat_filter_form">
														<input type="hidden" id="statement_stat" value="1">
														<div class="form-group">
															<label class="col-sm-1 control-label no-padding-right" for="statement_order_id">订单号</label>
															<div class="col-sm-4 ">
																<input type="text" id="statement_order_id" value="${closeID }" placeholder="忽略订单号" class="col-xs-10 col-sm-10">
															</div>
															
															<label class="col-sm-1 col-sm-offset-1 control-label no-padding-right" for="statement_order_id">客户ID</label>
															<div class="col-sm-4">
																<input type="text" id="statement_user_id" placeholder="忽略客户ID" class="col-xs-10 col-sm-10">
															</div>
														</div>
														<div class="form-group">
															<label class="col-sm-1 control-label no-padding-right" for="statment_date_range">创建时间</label>
															<div class="col-sm-4">
																<input type="text" id="statment_date_range" placeholder="创建时间范围" class="col-xs-10 col-sm-10" readonly>
																<input type="hidden" name="stat_drange" id="stat_drange"/>
															</div>
														</div>
														<div class="form-group">
															<div class="col-sm-offset-4 col-sm-4">
																<button class="btn btn-info btn-xs" type="button" id="stat_submit">
																	<i class="ace-icon fa fa-check"></i>查询
																</button>
																&nbsp; &nbsp; &nbsp;
																<button class="btn btn-xs" type="reset" id="stat_reset">
																	<i class="ace-icon fa fa-undo"></i> 重置
																</button>
															</div>
														</div>
														<input type="hidden" name="order_drange" id="order_drange"/>
													</form>
													<!-- 全部订单的过滤条件结束 -->
													<table id="stat_table"></table>
													<div id="stat_pager"></div>
												</div>
											</div>
										</div>
										<!-- 选项卡 结束-->
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- 右边页面的主页面 -->
			</div>
		</div>
	</div>	
	
	<!-- 模态框数据验证弹出框 -->
		<div class="modal fade" id="confirmModal" tabindex="-1" role="dialog"
			aria-labelledby="confirmModalLabel" aria-hidden="false">
			<div class="modal-dialog" style="width: 400px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h4 class="modal-title" id="confirmModalLabel">确认结算完毕提示</h4>
					</div>
					<div class="modal-body">
						<div class="warning" id="confirmMessage"></div>
					</div>
				</div>
			</div>
		</div>	
	<!-- /.main-container -->
	<script src="/js/bus/payConfir/corder.js"></script>
</body>
</html>
