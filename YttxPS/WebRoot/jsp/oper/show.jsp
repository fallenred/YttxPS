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
<link rel="stylesheet" type="text/css" href="/css/zTreeStyle/zTreeStyle.css">
<script type="text/javascript" src="/js/jquery.ztree.core-3.0.js"></script>
<script type="text/javascript" src="/js/jquery.ztree.excheck-3.0.js"></script>
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

							<!-- 模态框（查询） -->

							<form class="form-horizontal">
								<div class="modal-body">
									<!-- 图片TODO： -->
									<div>
										<ul class="ace-thumbnails clearfix">
										<!-- 
											<div style="visibility: hidden;">
												<li>
													<a class="cboxElement" data-rel="colorbox"  href="http://127.0.0.1:81/1.jpg"> 
														<img width="0" height="0" src="http://127.0.0.1:81/2.png" alt="0*0">
													</a>
												</li>
											</div>
										 -->
										</ul>
									</div>
									<h4>用户信息</h4>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-1 control-label no-padding-right" for="operId">用户编号</label>
											<div class="col-sm-4">
												<input type="text" id="operId" class="form-control"
													placeholder="用户编号" readonly="readonly" value="${operinfo.sysOperId}"/>
											</div>
											
											<label class="col-sm-1 control-label no-padding-right" for="operName">用户名称</label>
											<div class="col-sm-4">
												<input type="text" id="operName" class="form-control"
													placeholder="用户名称" readonly="readonly" value="${operinfo.sysOperName}"/>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-1 control-label no-padding-right" for="depName">所属部门</label>
											<div class="col-sm-4">
												<select class="form-control" id="depName" disabled>
													<option value="">请选择所属部门......</option>
													<c:forEach items="${deps}"  var="dep">
														<option value="${dep.depNo}" <c:if test="${operinfo.depNo==dep.depNo}">selected</c:if>> ${dep.depName}</option>
													</c:forEach>
												</select>
												
											</div>
											
											<label class="col-sm-1 control-label no-padding-right" for="adminType">管理身份</label>
											<div class="col-sm-4">
												<select class="form-control" id="adminType" disabled>
													<option value="" <c:if test="${operinfo.adminType==''}">selected</c:if>>请选择管理身份......</option>
													<option value="1" <c:if test="${operinfo.adminType==1}">selected</c:if>>超级管理员</option>
													<option value="2" <c:if test="${operinfo.adminType==2}">selected</c:if>>部门管理员</option>
													<option value="3" <c:if test="${operinfo.adminType==3}">selected</c:if>>一般用户</option>
												</select>
											</div>
										</div>
									</div>
									<h4>部门权限</h4>
									<div class="row">
										<div id="show_tree_div" class="ztree"></div>
									</div>
								</div>

								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal" id="close">关闭</button>
									<button id="reset" type="reset" class="btn" style="display: none;">重置</button>
									<!--  
											<button type="button" class="btn btn-primary">提交</button>
											-->
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
	
	<c:if test="${!empty succflag && succflag =='1'}">
		<script type="text/javascript">
	          $('.jump-step').removeClass('hide');
	          setTimeout('window.location="/home.htm"',5000);
	    </script>
	</c:if>
	
	
	<script type="text/javascript">
		var menuList =[
     	    <c:forEach items="${operRights}" var="data" varStatus="status">
     	            {id:'${data.id}', pId:'${data.pId}', name:'${data.name}' <c:if test="${data.checked}">,checked:true</c:if>},
     	    </c:forEach>
		    {}
		 ];
	</script>
	<script src="/js/bus/sys/sysoper.show.js"></script>
	
</body>
</html>
