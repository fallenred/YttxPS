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
<link rel="stylesheet" href="/css/jquery.wm-zoom-1.0.css" />
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
							<form class="form-horizontal">
								<div class="modal-body">
									<div class="row">
										<div class="profile-user-info profile-user-info-striped">
											<div class="profile-info-row">
												<div class="profile-info-name">客户ID</div>
												<div class="profile-info-value">
													<span>${cus.id}</span>
												</div>
											</div>
										</div>
										
										<div class="profile-user-info profile-user-info-striped">
											<div class="profile-info-row">
												<div class="profile-info-name">客户名称</div>
												<div class="profile-info-value">
													<span>${cus.name}</span>
												</div>
											</div>
										</div>
										
										<div class="profile-user-info profile-user-info-striped">
											<div class="profile-info-row">
												<div class="profile-info-name">旅行社名称</div>
												<div class="profile-info-value">
													<span>${cus.taname}</span>
												</div>
											</div>
										</div>
										
										<div class="profile-user-info profile-user-info-striped">
											<div class="profile-info-row">
												<div class="profile-info-name">营业许可证</div>
												<div class="profile-info-value">
													<div class="wm-zoom-container imgzoom">
			  											<div class="wm-zoom-box">
															<img src="${cus.licenceno}" style="width:280px; height:210px" 
																data-hight-src="${cus.licenceno}">
			  											</div>
			  										</div>
												</div>
											</div>
										</div>
										
										<div class="profile-user-info profile-user-info-striped">
											<div class="profile-info-row">
												<div class="profile-info-name">税务登记证</div>
												<div class="profile-info-value">
													<div class="wm-zoom-container imgzoom">
			  											<div class="wm-zoom-box">
															<img src="${cus.taxlicence}" style="width:280px; height:210px" 
																data-hight-src="${cus.taxlicence}">
			  											</div>
			  										</div>
												</div>
											</div>
										</div>
										
										<div class="profile-user-info profile-user-info-striped">
											<div class="profile-info-row">
												<div class="profile-info-name">联系方式</div>
												<div class="profile-info-value">
													<span>${cus.contact}</span>
												</div>
											</div>
										</div>
										
										<div class="profile-user-info profile-user-info-striped">
											<div class="profile-info-row">
												<div class="profile-info-name">身份证号</div>
												<div class="profile-info-value">
													<span>${cus.certid}</span>
												</div>
											</div>
										</div>
										
										<div class="profile-user-info profile-user-info-striped">
											<div class="profile-info-row">
												<div class="profile-info-name">邮件地址</div>
												<div class="profile-info-value">
													<span>${cus.email}</span>
												</div>
											</div>
										</div>
										
										<div class="profile-user-info profile-user-info-striped">
											<div class="profile-info-row">
												<div class="profile-info-name">注册时间</div>
												<div class="profile-info-value">
													<span>${cus.regTimeDesc}</span>
												</div>
											</div>
										</div>
										
										
										<div class="profile-user-info profile-user-info-striped">
											<div class="profile-info-row">
												<div class="profile-info-name">最后更新时间</div>
												<div class="profile-info-value">
													<span>${cus.timeStampDesc}</span>
												</div>
											</div>
										</div>
										
										<div class="profile-user-info profile-user-info-striped">
											<div class="profile-info-row">
												<div class="profile-info-name">法人代表</div>
												<div class="profile-info-value">
													<span>${cus.legalPerson}</span>
												</div>
											</div>
										</div>
										
										
										<div class="profile-user-info profile-user-info-striped">
											<div class="profile-info-row">
												<div class="profile-info-name">传真</div>
												<div class="profile-info-value">
													<span>${cus.fax}</span>
												</div>
											</div>
										</div>
										
										<div class="profile-user-info profile-user-info-striped">
											<div class="profile-info-row">
												<div class="profile-info-name">地址</div>
												<div class="profile-info-value">
													<span>${cus.address}</span>
												</div>
											</div>
										</div>
										
										<div class="profile-user-info profile-user-info-striped">
											<div class="profile-info-row">
												<div class="profile-info-name">旅行社业务经营许可证</div>
												<div class="profile-info-value">
													<span>${cus.tabLicense}</span>
												</div>
											</div>
										</div>
										<div class="profile-user-info profile-user-info-striped">
											<div class="profile-info-row">
												<div class="profile-info-name">销售人员ID</div>
												<div class="profile-info-value">
													<span>${cus.salesManID}</span>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<button id="close" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="/js/bus/member/admin.show.js"></script>
	<c:if test="${!empty succflag && succflag =='1'}">
		<script type="text/javascript">
	          $('.jump-step').removeClass('hide');
	          setTimeout('window.location="/home.htm"',5000);
	    </script>
	</c:if>
</body>
</html>
