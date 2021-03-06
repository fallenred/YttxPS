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
<link rel="stylesheet" href="/css/my.css" />
<link rel="stylesheet" href="/css/jquery.wm-zoom-1.0.css" />
<script src="/js/bus/user.js"></script>
<script src="/js/jquery.wm-zoom-1.0.js"></script>
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
							<!-- 模态框（查询） -->
							<input type="hidden" id="auditNo"  value="${acus.auditno}">
							<input type="hidden" id="auditType" value="${acus.auditType}">
							<input type="hidden" id="id" value="${acus.id}">
							<div class="modal-body">
								<div class="row">
									<div class="profile-user-info profile-user-info-striped">
										<div class="profile-info-row">
											<div class="profile-info-name col-xs-2">申请审核类型</div>
											<div class="profile-info-value col-xs-10">
												<c:set var="auditTyp" scope="page">${acus.auditType}</c:set>
												<span>${codeMasterMap['cus_attype'][auditTyp]}</span>
											</div>
										</div>
									</div>
									<div class="profile-user-info profile-user-info-striped">
										<div class="profile-info-row">
											<div class="profile-info-name col-xs-2">客户ID</div>
											<div class="profile-info-value col-xs-10">
												<span>${acus.id}</span>
											</div>
										</div>
									</div>
									<c:if test="${ocus==null}">
										<div class="profile-user-info profile-user-info-striped">
											<div class="profile-info-row">
												<div class="profile-info-name col-xs-2">客户名称</div>
												<div class="profile-info-value col-xs-10">
													<span>${acus.name}</span>
												</div>
											</div>
										</div>
										
										<div class="profile-user-info profile-user-info-striped">
											<div class="profile-info-row">
												<div class="profile-info-name col-xs-2">旅行社名称</div>
												<div class="profile-info-value col-xs-10">
													<span>${acus.taname}</span>
												</div>
											</div>
										</div>
										
										<div class="profile-user-info profile-user-info-striped">
											<div class="profile-info-row">
												<div class="profile-info-name col-xs-2">营业许可证</div>
												<div class="profile-info-value col-xs-10">
													<div class="wm-zoom-container imgzoom">
			  											<div class="wm-zoom-box">
																<img src="${acus.licenceno}" style="width:280px; height:210px" 
																data-hight-src="${acus.licenceno}">
			  											</div>
			  										</div>
												</div>
											</div>
										</div>
										
										<div class="profile-user-info profile-user-info-striped">
											<div class="profile-info-row">
												<div class="profile-info-name col-xs-2">税务登记证</div>
												<div class="profile-info-value col-xs-10">
													<div class="wm-zoom-container imgzoom">
			  											<div class="wm-zoom-box">
																<img src="${acus.taxlicence}" style="width:280px; height:210px" 
																data-hight-src="${acus.taxlicence}">
			  											</div>
			  										</div>
												</div>
											</div>
										</div>
										
										<div class="profile-user-info profile-user-info-striped">
											<div class="profile-info-row">
												<div class="profile-info-name col-xs-2">联系方式</div>
												<div class="profile-info-value col-xs-10">
													<span>${acus.contact}</span>
												</div>
											</div>
										</div>
										
										<div class="profile-user-info profile-user-info-striped">
											<div class="profile-info-row">
												<div class="profile-info-name col-xs-2">身份证号</div>
												<div class="profile-info-value col-xs-10">
													<span>${acus.certid}</span>
												</div>
											</div>
										</div>
										
										<div class="profile-user-info profile-user-info-striped">
											<div class="profile-info-row">
												<div class="profile-info-name col-xs-2">邮件地址</div>
												<div class="profile-info-value col-xs-10">
													<span>${acus.email}</span>
												</div>
											</div>
										</div>
										
										<div class="profile-user-info profile-user-info-striped">
											<div class="profile-info-row">
												<div class="profile-info-name col-xs-2">法人代表</div>
												<div class="profile-info-value col-xs-10">
													<span>${acus.legalPerson}</span>
												</div>
											</div>
										</div>
										
										
										<div class="profile-user-info profile-user-info-striped">
											<div class="profile-info-row">
												<div class="profile-info-name col-xs-2">传真</div>
												<div class="profile-info-value col-xs-10">
													<span>${acus.fax}</span>
												</div>
											</div>
										</div>
										
										<div class="profile-user-info profile-user-info-striped">
											<div class="profile-info-row">
												<div class="profile-info-name col-xs-2">地址</div>
												<div class="profile-info-value col-xs-10">
													<span>${acus.address}</span>
												</div>
											</div>
										</div>
										
										<div class="profile-user-info profile-user-info-striped">
											<div class="profile-info-row">
												<div class="profile-info-name col-xs-2">旅行社业务经营许可证</div>
												<div class="profile-info-value col-xs-10">
													<span>${acus.tabLicense}</span>
												</div>
											</div>
										</div>
										
										<div class="profile-user-info profile-user-info-striped">
											<div class="profile-info-row">
												<div class="profile-info-name col-xs-2">申请时间</div>
												<div class="profile-info-value col-xs-10">
													<span>${acus.applyTimeDesc}</span>
												</div>
											</div>
										</div>
										<c:if test="${acus.auditRet==0}">
											<div class="profile-user-info profile-user-info-striped">
												<div class="profile-info-row">
													<div class="profile-info-name col-xs-2">销售人员ID</div>
													<div class="profile-info-value col-xs-10">
														<input type="text" placeholder="选择销售人员" name="operName" 
														id="operName" data-idx="0" readonly /> 
														<div class="localcity selectCity" id="selectUser"></div>
														<input type="hidden" id="operId" name="operId"/> 
													</div>
												</div>
											</div>
										</c:if>
									</c:if>
									<c:if test="${ocus!=null}">
										<div class="profile-user-info profile-user-info-striped">
											<div class="profile-info-row">
												<div class="profile-info-name col-xs-2"><strong>客户信息类型</strong></div>
												<div class="profile-info-value bchange col-xs-5">
													<span><strong>会员信息</strong></span>
												</div>
												<div class="profile-info-value col-xs-5">
													<span><strong>变更信息</strong></span>
												</div>
											</div>
										</div>
										<div class="profile-user-info profile-user-info-striped">
											<div class="profile-info-row">
												<div class="profile-info-name col-xs-2">客户名称</div>
												<div class="profile-info-value bchange col-xs-5">
													<span>${ocus.name}</span>
												</div>
												<div class="profile-info-value col-xs-5">
													<span>${acus.name}</span>
												</div>
											</div>
										</div>
										
										<div class="profile-user-info profile-user-info-striped">
											<div class="profile-info-row">
												<div class="profile-info-name col-xs-2">旅行社名称</div>
												<div class="profile-info-value bchange col-xs-5">
													<span>${ocus.taname}</span>
												</div>
												<div class="profile-info-value col-xs-5">
													<span>${acus.taname}</span>
												</div>
											</div>
										</div>
										
										<div class="profile-user-info profile-user-info-striped">
											<div class="profile-info-row">
												<div class="profile-info-name col-xs-2">营业许可证</div>
												<div class="profile-info-value bchange col-xs-5">
													<div class="wm-zoom-container imgzoom">
			  											<div class="wm-zoom-box">
																<img src="${ocus.licenceno}" style="width:280px; height:210px" 
																data-hight-src="${ocus.licenceno}">
			  											</div>
			  										</div>
												</div>
												<div class="profile-info-value col-xs-5">
													<div class="wm-zoom-container imgzoom">
			  											<div class="wm-zoom-box">
																<img src="${acus.licenceno}" style="width:280px; height:210px" 
																data-hight-src="${acus.licenceno}">
			  											</div>
			  										</div>
												</div>
											</div>
										</div>
										
										<div class="profile-user-info profile-user-info-striped">
											<div class="profile-info-row">
												<div class="profile-info-name col-xs-2">税务登记证</div>
												<div class="profile-info-value bchange col-xs-5">
													<div class="wm-zoom-container imgzoom">
			  											<div class="wm-zoom-box">
																<img src="${ocus.taxlicence}" style="width:280px; height:210px" 
																data-hight-src="${ocus.taxlicence}">
			  											</div>
			  										</div>
												</div>
												
												<div class="profile-info-value col-xs-5">
													<div class="wm-zoom-container imgzoom">
			  											<div class="wm-zoom-box">
																<img src="${acus.taxlicence}" style="width:280px; height:210px" 
																data-hight-src="${acus.taxlicence}">
			  											</div>
			  										</div>
												</div>
											</div>
										</div>
										
										<div class="profile-user-info profile-user-info-striped">
											<div class="profile-info-row">
												<div class="profile-info-name col-xs-2">联系方式</div>
												<div class="profile-info-value bchange col-xs-5">
													<span>${ocus.contact}</span>
												</div>
												<div class="profile-info-value col-xs-5">
													<span>${acus.contact}</span>
												</div>
											</div>
										</div>
										
										<div class="profile-user-info profile-user-info-striped">
											<div class="profile-info-row">
												<div class="profile-info-name col-xs-2">身份证号</div>
												<div class="profile-info-value bchange col-xs-5">
													<span>${ocus.certid}</span>
												</div>
												<div class="profile-info-value col-xs-5">
													<span>${acus.certid}</span>
												</div>
											</div>
										</div>
										
										<div class="profile-user-info profile-user-info-striped">
											<div class="profile-info-row">
												<div class="profile-info-name col-xs-2">邮件地址</div>
												<div class="profile-info-value bchange col-xs-5">
													<span>${ocus.email}</span>
												</div>
												<div class="profile-info-value col-xs-5">
													<span>${acus.email}</span>
												</div>
											</div>
										</div>
										
										<div class="profile-user-info profile-user-info-striped">
											<div class="profile-info-row">
												<div class="profile-info-name col-xs-2">法人代表</div>
												<div class="profile-info-value bchange col-xs-5">
													<span>${ocus.legalPerson}</span>
												</div>
												<div class="profile-info-value col-xs-5">
													<span>${acus.legalPerson}</span>
												</div>
											</div>
										</div>
										
										
										<div class="profile-user-info profile-user-info-striped">
											<div class="profile-info-row">
												<div class="profile-info-name col-xs-2">传真</div>
												<div class="profile-info-value bchange col-xs-5">
													<span>${ocus.fax}</span>
												</div>
												<div class="profile-info-value col-xs-5">
													<span>${acus.fax}</span>
												</div>
											</div>
										</div>
										
										<div class="profile-user-info profile-user-info-striped">
											<div class="profile-info-row">
												<div class="profile-info-name col-xs-2">地址</div>
												<div class="profile-info-value bchange col-xs-5">
													<span>${ocus.address}</span>
												</div>
												<div class="profile-info-value col-xs-5">
													<span>${acus.address}</span>
												</div>
											</div>
										</div>
										
										<div class="profile-user-info profile-user-info-striped">
											<div class="profile-info-row">
												<div class="profile-info-name col-xs-2">经营许可证</div>
												<div class="profile-info-value bchange col-xs-5">
													<span>${ocus.tabLicense}</span>
												</div>
												<div class="profile-info-value col-xs-5">
													<span>${acus.tabLicense}</span>
												</div>
											</div>
										</div>
										
										<div class="profile-user-info profile-user-info-striped">
											<div class="profile-info-row">
												<div class="profile-info-name col-xs-2">申请时间</div>
												<div class="profile-info-value col-xs-10">
													<span>${acus.applyTimeDesc}</span>
												</div>
											</div>
										</div>
									</c:if>
									
									
									<c:if test="${acus.auditRet==0}">
										<div class="profile-user-info profile-user-info-striped">
											<div class="profile-info-row">
												<div class="profile-info-name col-xs-2">审核意见</div>
												<div class="profile-info-value col-xs-10">
													<textarea cols="50" rows="5" id="comment"></textarea>
												</div>
											</div>
										</div>
									</c:if>
									<c:if test="${acus.auditRet!=0}">
										<div class="profile-user-info profile-user-info-striped">
											<div class="profile-info-row">
												<div class="profile-info-name col-xs-2">审核人</div>
												<div class="profile-info-value col-xs-10">
													<span>${acus.auditor}</span>
												</div>
											</div>
										</div>
										<div class="profile-user-info profile-user-info-striped">
											<div class="profile-info-row">
												<div class="profile-info-name col-xs-2">审核时间</div>
												<div class="profile-info-value col-xs-10">
													<span>${acus.auditTimeDesc}</span>
												</div>
											</div>
										</div>
										<div class="profile-user-info profile-user-info-striped">
											<div class="profile-info-row">
												<div class="profile-info-name col-xs-2">审核结果</div>
												<div class="profile-info-value col-xs-10">
													<c:set var="auditRet" scope="page">${acus.auditRet}</c:set>
													<span>${codeMasterMap['cus_adtret'][auditRet]}</span>
												</div>
											</div>
										</div>
										<div class="profile-user-info profile-user-info-striped">
											<div class="profile-info-row">
												<div class="profile-info-name col-xs-2">审核意见</div>
												<div class="profile-info-value col-xs-10">
													<span>${acus.comment}</span>
												</div>
											</div>
										</div>
									</c:if>
								</div>
							</div>
							<div id = "message" class="alert alert-warning"></div>
							<div class="modal-footer">
								<button id="close" type="button" 
									class="btn btn-default" data-dismiss="modal">关闭</button>
								<c:if test="${acus.auditRet==0}">
									<button id="audit_no" type="button" 
										class="btn  btn-info" data-dismiss="modal">
										<span class="glyphicon glyphicon-remove"></span>审核不通过
									</button>
										
									<button id="aduit_ok" type="button" 
										class="btn btn-info" data-dismiss="modal">
										<span class="glyphicon glyphicon-ok"></span>审核通过
									</button>
								</c:if>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="/js/bus/member/audit.audit.js"></script>
	<c:if test="${!empty succflag && succflag =='1'}">
		<script type="text/javascript">
	          $('.jump-step').removeClass('hide');
	          setTimeout('window.location="/home.htm"',5000);
	    </script>
	</c:if>
</body>
</html>
