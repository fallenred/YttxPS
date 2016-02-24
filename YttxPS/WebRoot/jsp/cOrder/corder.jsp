<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<body class="no-skin">
	<jsp:include page="/jsp/comm/topbar.jsp" flush="true" />
	<div class="main-container" id="main-container">
		<div class="main-container-inner">
			<jsp:include page="/jsp/comm/sidebar.jsp" flush="true" />
			<div class="main-content">
				<div class="page-content">
					<div class="space-10"></div>
					<div class="row">
						<div class="col-xs-12">
							<div class="widget-body">
								<div class="widget-main">
									<form action="/order/list.htm" method="get" id="queryFrm">
										<div class="row">
											<div class="col-xs-5">
												<div class="input-group">
													<span class="input-group-addon"> 
														<i class="icon-calendar bigger-110"></i> 
													</span> 
													<input class="form-control" type="text" name="daterange"
														id="daterange" placeholder="请输入日期范围" />
												</div>
											</div>
											<div class="col-xs-4 ">
												<div class="input-group">
													<span class="input-group-addon"> 
														<i class="icon-exchange bigger-110"></i> 
													</span>
													<input class="form-control" type="text" name="orderid"
														id="orderid" placeholder="请输入订单号" />
												</div>
												</div>
                                                <div class="col-xs-3">
													<div class="pull-right">
														<button type="button" class="btn btn-primary btn-sm"
															id="querybtn" data-toggle="button">
															<i class="icon-search"></i>查询
														</button>
												</div>
											</div>
										</div>
										<input type="hidden" name="stat" id="stat" value="${stat}" />
										<input type="hidden" name="drange" id="drange" value="${daterange}" />
									</form>

									<div class="space-15"></div>
									<div class="row">
										
										<div class="col-xs-12">
											<ul class="nav nav-tabs padding-16" id="orderstat">
												<li class="active">
													<a data-stat="" href="javascript:void();"> 
														<i class="badge bigger-110"></i> 全部订单
													</a>
												</li>		
												<li class="active">
													<a data-stat="" href="javascript:void();">全部结算单</a>
												</li>			
											</ul>
										<div class="space-"></div>
												</div>
											</div>

											<div class="row">
												<div class="col-xs-12">
													<div class="message-container">
														<div id="id-message-list-navbar"
															class="message-navbar align-center clearfix">
															<div class="row">
																<div class="col-xs-2">订单名称</div>
																<div class="col-xs-2">线路名称</div>
																<div class="col-xs-3">创建时间</div>
																<div class="col-xs-3">支付情况</div>
																<div class="col-xs-2">状态</div>
															</div>
														</div>
														<div class="message-list-container">
															<div class="message-list" id="message-list">

																<c:forEach items="${orderlist}" var="order">
																	<div class="message-item">
																		<div class="row">

																			<div class="col-xs-2">

																				<a href="javascript:void();"
																					data-key="${order.orderid}" class="oderext"> <i
																					class="icon-hand-right icon-animated-hand-pointer blue"></i>
																					${order.ordername} </a>

																			</div>

																			<div class="col-xs-2">

																				<a href="/expert/intro/${order.routeid}.htm"
																					target="_blank"> ${order.routename} </a>

																			</div>

																			<div class="col-xs-3">${order.createtime}</div>

																			<div class="col-xs-3">
																				<b class="green bigger-140"><fmt:formatNumber
																						value="${order.paidamt}" type="currency"
																						pattern="0.00" /> </b>/ <b class="red bigger-140"><fmt:formatNumber
																						value="${order.totalfee}" type="currency"
																						pattern="0.00" /> </b>

																			</div>

																			<div class="col-xs-2">${order.statdesc}</div>
																			
																		</div>
																	</div>
																</c:forEach>
															</div>
														</div>

														<div class="message-footer clearfix">															
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	<!-- /.main-container -->

	<jsp:include page="/jsp/comm/footer.jsp" flush="true" />

	<script type="text/x-handlebars-template" id="entryTemplate">
<div class="col-xs-12">
		<div class="message-content" id="id-message-content">
			<div class="widget-box transparent">
				<div class="widget-header widget-header-small">
					<h4 class="blue smaller">
						<i class="icon-rss orange"></i> 订单详情
					</h4>
				</div>
				<div class="widget-body">
					<div class="widget-main padding-8">
						<div class="profile-user-info profile-user-info-striped">
							<div class="profile-info-row">
								<div class="profile-info-name">订单号:</div>
								<div class="profile-info-value">
									<span class="editable editable-click" id="username">{{orderid}}</span>
								</div>
							</div>
							<div class="profile-info-row">
								<div class="profile-info-name">订单类型:</div>
								<div class="profile-info-value">{{typename}}</div>
							</div>
							<div class="profile-info-row">
								<div class="profile-info-name">组团类型:</div>
								<div class="profile-info-value">{{teamtypename}}</div>
							</div>
							<div class="profile-info-row">
								<div class="profile-info-name">创建日期:</div>

								<div class="profile-info-value">
									<span class="editable editable-click" id="age">{{createtime}}</span>
								</div>
							</div>
							<div class="profile-info-row">
								<div class="profile-info-name">公共资源</div>

								<div class="profile-info-value">
									<table id="commres"
										class="table table-striped table-bordered table-hover">
										<thead>
											<tr>
												<th>资源类型</th>
												<th>需求概述</th>
												<th>计调计划</th>
											</tr>
										</thead>
										
										<tbody>
											{{#each res}}
											<tr>
												<td>{{resname}}</td>
												<td>{{ask}}</td>
												<td>{{answer}}</td>
											</tr>
											{{/each}}
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="hr hr-double"></div>

			<div class="widget-box transparent">
				<div class="widget-header widget-header-small">

					<h4 class="blue smaller">
						<i class="icon-user orange"></i> 游客详情
					</h4>
				</div>

				<div class="widget-body">
					<div class="widget-main padding-8">
						<div class="row">
							<div class="col-xs-12">


								{{#each orderguest}}
								<div class="panel panel-info radius">
									<div class="panel-body">
										<h5 class="header smaller lighter blue">
											<strong>第{{plus @index}}批次</strong>
										</h5>

							
										<div class="profile-user-info profile-user-info-striped">
											<div class="profile-info-row">
												<div class="profile-info-name">联系人:</div>
													<div class="profile-info-value">
													<span class="" id="username">{{contactname}}</span>
												</div>
											</div>
											<div class="profile-info-row">
												<div class="profile-info-name">联系电话:</div>
													<div class="profile-info-value">
													<span class="" id="username">{{contacttel}}</span>
												</div>
											</div>
											<div class="profile-info-row">
												<div class="profile-info-name">成年人数量:</div>
													<div class="profile-info-value">
													<span class="" id="username">{{adult}}</span>
												</div>
											</div>
											<div class="profile-info-row">
												<div class="profile-info-name">老年人数量:</div>
													<div class="profile-info-value">
													<span class="" id="username">{{older}}</span>
												</div>
											</div>
											<div class="profile-info-row">
												<div class="profile-info-name">儿童数量:</div>
													<div class="profile-info-value">
													<span class="" id="username">{{children}}</span>
												</div>
											</div>
											<div class="profile-info-row">
												<div class="profile-info-name">批次备注:</div>
													<div class="profile-info-value">
													<span class="" id="username">{{postscript}}&nbsp;</span>
												</div>
											</div>									
										</div>
										<div class="space-4"></div>
										<div class="table-header">游客名单</div>
										<table class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
													<th>姓名</th>
													<th>联系电话</th>
													<th>性别</th>
													<th>证件号</th>

												</tr>
											</thead>
											<tbody>

												{{#each orderguestlist}}
												<tr>
													<td class="center">{{name}}</td>

													<td>{{tel}}</td>
													<td>{{sex}}</td>
													<td>{{id}}</td>
												</tr>
												{{/each}}
											</tbody>
										</table>

										<div class="space-4"></div>

										{{#each displayress}}
										<div class="table-header">第{{plus dayflag}}天资源使用描述</div>
										<table id="prvres" class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
													<th>资源类型</th>
													<th>需求概述</th>
													<th>计调计划</th>
												</tr>
											</thead>
											<tbody>
												{{#each ress}}
												<tr>
													<td>{{resname}}</td>
													<td>{{ask}}</td>
													<td>{{answer}}</td>
												</tr>
												{{/each}}
											</tbody>
										</table>
										{{/each}}
									</div>
								</div>
								{{/each}}
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="hr hr-double"></div>
			<div class="widget-box transparent">
				<div class="widget-header widget-header-small">

					<h4 class="blue smaller">
						<i class="icon-edit orange"></i> 订单备注
					</h4>
				</div>
				<div class="widget-body">
					<div class="widget-main padding-8">
						<div class="row">
							<div class="col-xs-12">
								<div class="timeline-container">
									
									{{#each remarklists}}
									<div class="timeline-label">
										<span class="label label-success arrowed-in-right label-lg">
											<b>{{subdate time}}</b> </span>
									</div>
									<div class="timeline-item clearfix">
										<div class="timeline-info">
											<i
												class="timeline-indicator icon-edit btn btn-success no-hover"></i>
										</div>

										<div class="widget-box">
											<div
												class="widget-header header-color-green widget-header-small">
												<h5 class="smaller">计调id:{{operid}}</h5>

												<span class="widget-toolbar no-border"> <i
													class="icon-time bigger-110"></i> {{subtime time}} </span> <span
													class="widget-toolbar"> <a href="#"
													data-action="collapse"> <i class="icon-chevron-up"></i>
												</a> </span>
											</div>

											<div class="widget-body">
												<div class="widget-body-inner" style="display: block;">
													<div class="widget-main">
														<p>{{content}}</p>
														<p>
															<span class="red bolder bigger-130">发生金额:{{amt}}</span>
														</p>
													</div>
												</div>
											</div>
										</div>
									</div>
									{{/each}}

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="hr hr-double"></div>
			<div class="widget-box transparent">
				<div class="widget-header widget-header-small">

					<h4 class="blue smaller">
						<i class="icon-calendar orange"></i> 日程快照
					</h4>
				</div>
				<div class="widget-body">
					<div class="widget-main padding-8">
						<div class="row">
							<div class="col-xs-12">{{schedule}}</div>
						</div>
					</div>
				</div>
			</div>

			<div class="hr hr-double"></div>

			<div class="widget-box transparent">
				<div class="widget-header widget-header-small">

					<h4 class="blue smaller">
						<i class="icon-calendar orange"></i> 整单备注
					</h4>
				</div>
				<div class="widget-body">
					<div class="widget-main padding-8">
						<div class="row">
							<div class="col-xs-12">{{postscript}} {{remark}}</div>
						</div>
					</div>
				</div>
			</div>

			<div class="hr hr-double"></div>

			<div class="message-attachment clearfix">
				<div class="attachment-images pull-right">
					<div class="vspace-sm-4"></div>
					<div>
						<form action="/order/confirmstatus.htm" method="post" id="confirmFrm">
						<input type="hidden" name="orderid" value="{{orderid}}" />
						<input type="hidden" name="submitstatus" value="{{stat}}" />
						{{analyze stat}}

						</form>

					</div>
				</div>
			</div>
		</div>
		<!-- /.message-content -->
	</div>
		
	</script>

	<script src="/js/bus/orderlist.js"></script>

</body>
</html>




					
					
				</div>
			</div>
		</div>
	</div>			
	<!-- /.main-container -->
	<script src="/js/bus/sys/sysdept.js"></script>
</body>
</html>
