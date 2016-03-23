<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="message-content" id="id-message-content">
	<!-- 订单概述开始 -->
	<div class="widget-box transparent">
		<div class="widget-header widget-header-small">
			<h4 class="blue smaller">
				<i class="icon-rss orange"></i>订单概述
			</h4>
		</div>
		<div class="widget-body">
			<div class="widget-main padding-8">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<div class="col-xs-2">
							<label class="control-label no-padding-right"><strong>订单编号:</strong></label>
						</div>
						<div class="col-xs-4">
							<label class="input-group">${order.no}</label>
						</div>
						<div class="col-xs-2">
							<label class="control-label no-padding-right"><strong>订单名称:</strong></label>
						</div>
						<div class="col-xs-4">
							<label class="input-group"><c:if test="${order.name!=null}">${order.name}</c:if></label>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<div class="col-xs-2">
							<label class="control-label no-padding-right"><strong>用户ID:</strong></label>
						</div>
						<div class="col-xs-4">
							<label class="input-group">${order.userId}-${order.userSubId}</label>
						</div>
						<div class="col-xs-2">
							<label class="control-label no-padding-right"><strong>计调ID:</strong></label>
						</div>
						<div class="col-xs-4">
							<label class="input-group">${order.operId}</label>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<div class="col-xs-2">
							<label class="control-label no-padding-right"><strong>创建时间:</strong></label>
						</div>
						<div class="col-xs-4">
							<label class="input-group">${order.createDateDesc}</label>
						</div>
						<div class="col-xs-2">
							<label class="control-label no-padding-right"><strong>线路天数:</strong></label>
						</div>
						<div class="col-xs-4">
							<label class="input-group">${order.days}</label>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<div class="col-xs-2">
							<label class="control-label no-padding-right"><strong>发团日期:</strong></label>
						</div>
						<div class="col-xs-4">
							<label class="input-group">${order.startDateDesc}</label>
						</div>
						<div class="col-xs-2">
							<label class="control-label no-padding-right"><strong>发团地:</strong></label>
						</div>
						<div class="col-xs-4">
							<label class="input-group">${order.startPlace}</label>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<div class="col-xs-2">
							<label class="control-label no-padding-right"><strong>初始报价(单人):</strong></label>
						</div>
						<div class="col-xs-4">
							<label class="input-group">${order.price}&nbsp;元/人</label>
						</div>
						<div class="col-xs-2">
							<label class="control-label no-padding-right"><strong>游客人数:</strong></label>
						</div>
						<div class="col-xs-4">
							<label class="input-group">${order.visitorNum}&nbsp;人</label>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<div class="col-xs-2">
							<label class="control-label no-padding-right"><strong>预估金额:</strong></label>
						</div>
						<div class="col-xs-4">
							<label class="input-group">${order.totalFee}&nbsp;元</label>
						</div>
						<div class="col-xs-2">
							<label class="control-label no-padding-right"><strong>已付金额:</strong></label>
						</div>
						<div class="col-xs-4">
							<label class="input-group">${order.paidAmt}&nbsp;元</label>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 订单概述结束 -->
	
	<!-- 游客信息 开始 -->
	<div class="widget-box transparent">
		<div class="widget-header widget-header-small">
			<h4 class="blue smaller">
				<i class="icon-rss orange"></i>游客信息
			</h4>
		</div>
		<div class="widget-body">
			<div class="widget-main padding-8">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<c:if test="${order.tourists!=null}">
							<table id="prvres" class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th>客户姓名</th>
										<th>证件类型</th>
										<th>证件号</th>
										<th>联系方式</th>
										<th>备注</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${order.tourists}" var="tt">
										<tr>
											<td>${tt.name}</td>
											<td>${tt.idType}</td>
											<td>${tt.id}</td>
											<td>${tt.tel}</td>
											<td>${tt.mark}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</c:if>
						
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 游客信息 结束 -->
			
	<!-- 资源使用 开始 -->
	<div class="widget-box transparent">
		<div class="widget-header widget-header-small">
			<h4 class="blue smaller">
				<i class="icon-user orange"></i>资源使用
			</h4>
		</div>
		
		<div class="widget-body">
			<div class="widget-main padding-8">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<table class="table table-striped table-bordered table-hover">
							<tbody>
								<tr>
									<td>公共资源</td>
									<td>
										<table class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
													<th>资源类型</th>
													<th>资源名称</th>
													<th>使用情况</th>
												<tr>
											</thead>
											<tbody>
												<c:if test="${order.resMap!=null}">
													<c:forEach items="${order.resMap}" var="entry">
														<tr>
															<td rowspan="${fn:length(entry.value)}">${zy_map[entry.key]}</td>
															<c:if test="${entry.value!=null}">
																<c:forEach items="${entry.value}" var="res">
																	<td>
																		<c:if test="${res['resName']!=null}">${res['resName']}</c:if>
																	</td>
																	<td>
																		${res['resContent']}
																	</td>
																</c:forEach>
															</c:if>
														</tr>	
													</c:forEach>
												</c:if>
											</tbody>
										</table>
									</td>
								</tr>
								<c:if test="${order.dayResList!=null}">
									<tr>
										<td>每天公共资源</td>
										<td>
											<table class="table table-striped table-bordered table-hover">
												<thead>
													<tr>
														<th>行程时间</th>
														<th>资源说明</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${order.dayResList}" var="daylist" varStatus="status">
														<tr>
															<td>第${status.index+1}天</td>
															<td>
																<table class="table table-striped table-bordered table-hover">
																	<thead>
																		<tr>
																			<th>资源类型</th>
																			<th>资源名称</th>
																			<th>使用情况</th>
																		<tr>
																	</thead>
																	<tbody>
																		<c:if test="${daylist!=null}">
																			<c:forEach items="${daylist}" var="entry">
																				<c:if test="${entry.value!=null}">
																					<c:forEach items="${entry.value}" var="res" varStatus="resStatus">
																						<tr>
																							<c:if test="${resStatus.first}">
																								<td rowspan="${fn:length(entry.value)}">${zy_map[entry.key]}</td>
																							</c:if>
																							<td>
																								<c:if test="${res['resName']!=null}">${res['resName']}</c:if>
																							</td>
																							<td>
																								${res['resContent']}
																							</td>
																						</tr>	
																					</c:forEach>
																				</c:if>
																			</c:forEach>
																		</c:if>
																	</tbody>
																</table>
															</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</td>
									</tr>
								</c:if>
								
								<c:if test="${order.batches!=null}">
									<tr>
										<td>批次资源</td>
										<td>
											<table class="table table-striped table-bordered table-hover">
												<thead>
													<tr>
														<th>游客批次</th>
														<th>资源使用详情</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${order.batches}" var="batch" varStatus="status">
														<tr>
															<td>第${status.index+1}批(${batch.total}人,预估金额：${batch.amt})</td>
															<td>
																<table class="table table-striped table-bordered table-hover">
																	<thead>
																		<tr>
																			<th>行程时间</th>
																			<th>资源说明</th>
																		</tr>
																	</thead>
																	<tbody>
																		<c:if test="${batch.resList!=null}">
																			<c:forEach items="${batch.resList}" var="batchDaylist" varStatus="batchDayStatus">
																				<tr>
																					<td>第${batchDayStatus.index+1}天</td>
																					<td>
																						<table class="table table-striped table-bordered table-hover">
																							<thead>
																								<tr>
																									<th>资源类型</th>
																									<th>资源名称</th>
																									<th>使用情况</th>
																								<tr>
																							</thead>
																							<tbody>
																								<c:if test="${batchDaylist!=null}">
																									<c:forEach items="${batchDaylist}" var="entry">
																										<c:if test="${entry.value!=null}">
																											<c:forEach items="${entry.value}" var="res" varStatus="resStatus">
																												<tr>
																													<c:if test="${resStatus.first}">
																														<td rowspan="${fn:length(entry.value)}">${zy_map[entry.key]}</td>
																													</c:if>
																													<td>
																														<c:if test="${res['resName']!=null}">${res['resName']}</c:if>
																													</td>
																													<td>
																														${res['resContent']}
																													</td>
																												</tr>	
																											</c:forEach>
																										</c:if>
																									</c:forEach>
																								</c:if>
																							</tbody>
																						</table>
																					</td>
																				</tr>
																			</c:forEach>
																		</c:if>
																	</tbody>
																</table>
															</td>
													</c:forEach>
												</tbody>
											</table>
										</td>
									</tr>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
						
	
	
	<!-- 订单备注  开始 -->
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
						<table id="prvres" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>计调Id</th>
									<th>备注内容</th>
									<th>发生金额</th>
									<th>已付金额</th>
									<th>备注状态</th>
									<th>结算状态</th>
								</tr>
							 </thead>
							<tbody>
								<c:forEach items="${order.remarks}" var="tt">
									<tr>
										<td>${tt.operId}</td>
										<td>${tt.content}</td>
										<td>${tt.amt}</td>
										<td>${tt.paidAmt}</td>
										<td>
											<c:if test="${tt.stat==0}">未付款</c:if>
											<c:if test="${tt.stat==1}">已付首款</c:if>
											<c:if test="${tt.stat==2}">已付全款</c:if>
										</td>
										<td>
											<c:if test="${tt.stat==0}">未结算</c:if>
											<c:if test="${tt.stat==1}">已入计算单</c:if>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 订单备注  结束 -->
</div>
