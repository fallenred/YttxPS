<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt'%>
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
							<span class="editable editable-click" id="username">${order.no}</span>
						</div>
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name">订单类型:</div>
						<div class="profile-info-value">
							<c:choose>
								<c:when test="${order.type=='02'}">衍生线路</c:when>
								<c:when test="${order.type=='03'}">定制线路</c:when>
								<c:otherwise>未知订单类型</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name">组团类型:</div>
						<div class="profile-info-value">
							<c:choose>
								<c:when test="${order.property=='01'}">独立成团</c:when>
								<c:when test="${order.property=='02'}">散客拼团</c:when>
								<c:otherwise>未知组团类型</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name">创建日期:</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="age">${order.createDate}</span>
						</div>
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name">预估全价:</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="age">${order.totalFee}</span>
						</div>
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name">已付金额:</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="age">${order.paidAmt}</span>
						</div>
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name">公共资源</div>
						<div class="profile-info-value">
							<table id="commres" class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th>资源名称</th>
										<th>使用情况</th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${order.resMap!=null}">
										<c:forEach items="${order.resMap}" var="res">
											<tr>
												<td>${res.key}</td>
												<td>${res.value}</td>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
	<div class="hr hr-double"></div>
	
	
	<!-- 游客信息 开始 -->
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
						<!-- 轮询客户批次 -->
						<c:forEach items="${order.batches}" var="bat">
							<div class="panel panel-info radius">
								<div class="panel-body">
									<h5 class="header smaller lighter blue">
										<strong>第${bat.seq+1}批次</strong>
									</h5>
									<!-- 每一批次的统计信息 -->
									<div class="profile-user-info profile-user-info-striped">
										<div class="profile-info-row">
											<div class="profile-info-name">联系人:</div>
											<div class="profile-info-value">
												<span class="" id="username">${bat.contactName}</span>
											</div>
										</div>
										<div class="profile-info-row">
											<div class="profile-info-name">联系电话:</div>
												<div class="profile-info-value">
												<span class="" id="username">${bat.contactTel}</span>
											</div>
										</div>
										<div class="profile-info-row">
											<div class="profile-info-name">成年人数量:</div>
												<div class="profile-info-value">
												<span class="" id="username">${bat.adult}</span>
											</div>
										</div>
										<div class="profile-info-row">
											<div class="profile-info-name">老年人数量:</div>
												<div class="profile-info-value">
												<span class="" id="username">${bat.older}</span>
											</div>
										</div>
										<div class="profile-info-row">
											<div class="profile-info-name">儿童数量:</div>
												<div class="profile-info-value">
												<span class="" id="username">${bat.children}</span>
											</div>
										</div>
										<div class="profile-info-row">
											<div class="profile-info-name">批次备注:</div>
												<div class="profile-info-value">
												<span class="" id="username">${bat.postscript}&nbsp;</span>
											</div>
										</div>	
										<div class="profile-info-row">
											<div class="profile-info-name">预估金额小计:</div>
												<div class="profile-info-value">
												<span class="" id="username">${bat.amt}&nbsp;</span>
											</div>
										</div>										
									</div>
									<!-- 每一批次的统计信息  结束-->
									
									<div class="space-4"></div>
						
									<!-- 轮询每一批次的使用资源信息-->
									<c:forEach items="${bat.resList}" var="day" varStatus="status">
										<div class="table-header">第${status.index+1}天资源使用描述</div>
										<table id="prvres" class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
													<th>资源名称</th>
													<th>资源内容</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${day}" var="item">
													<tr>
														<td>${item.key}</td>
														<td>${item.value}</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</c:forEach>
									<!-- 轮询每一批次的使用资源信息 结束-->
								</div>
							</div>
						</c:forEach>
						<!-- 轮询客户批次  结束-->
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 游客信息 结束 -->
	
	<div class="hr hr-double"></div>
	
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
						<!--循环遍历订单备注  -->
						<table id="prvres" class="table table-striped table-bordered table-hover">
							<c:forEach items="${order.remarks}" var="remark">
								<tr>
									<td class="col-xs-2">${remark.dateDesc}</td>
									<td class="col-xs-10">
										<p>计   调 ID：${remark.operId}</p>
										<p>备注内容：${remark.content}</p>
										<p>发生金额：${remark.amt}</p>
									</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 订单备注  结束 -->
	
	<div class="hr hr-double"></div>
	<!-- 日程快照 -->
		
	<div class="widget-box transparent">
		<div class="widget-header widget-header-small">
			<h4 class="blue smaller">
				<i class="icon-calendar orange"></i> 日程快照
			</h4>
		</div>
		<div class="widget-body">
			<div class="widget-main padding-8">
				<div class="row">
					<div class="col-xs-12">${order.schedule}</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 日程快照  结束-->
	<div class="hr hr-double"></div>
	
	<!-- 整单备注-->
	<div class="widget-box transparent">
		<div class="widget-header widget-header-small">

			<h4 class="blue smaller">
				<i class="icon-calendar orange"></i>整单备注
			</h4>
		</div>
		<div class="widget-body">
			<div class="widget-main padding-8">
				<div class="row">
					<div class="col-xs-12">${order.remark}</div>
				</div>
			</div>
		</div>
	</div>	
	<!-- 整单备注  结束-->
</div>
