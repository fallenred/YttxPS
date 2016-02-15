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

							<!-- 模态框（新增） -->
							<form class="form-horizontal" id="addform" >
								<div class="modal-body">
									<!-- 图片TODO： -->
									<div>
										<ul class="ace-thumbnails clearfix">
											<div style="visibility: hidden;">
												<li><a class="cboxElement" data-rel="colorbox"
													href="http://127.0.0.1:81/1.jpg"> <img width="0"
														height="0" src="http://127.0.0.1:81/2.png" alt="0*0">
												</a></li>
											</div>
										</ul>
									</div>
									<h4>用户信息</h4>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-1 control-label no-padding-right" for="sysOperId">用户编号</label>
											<div class="col-sm-4">
												<input type="hidden" id="oldOperId"  value="${operinfo.sysOperId}"/><!-- 保存原来的operId -->
												
												<input type="text" id=sysOperId class="form-control"
													placeholder="用户编号"  value="${operinfo.sysOperId}"/>
											</div>
											
											<label class="col-sm-1 control-label no-padding-right" for="sysOperName">用户名称</label>
											<div class="col-sm-4">
												<input type="text" id="sysOperName" class="form-control"
													placeholder="用户名称"  value="${operinfo.sysOperName}"/>
											</div>
										 </div>
										<div class="form-group">
											<label class="col-sm-1 control-label no-padding-right" for="depNo">所属部门</label>
											<div class="col-sm-4">
												<select class="form-control" id="depNo" <c:if test="${cOper.adminType!=1}">disabled</c:if>>
													<option value="">请选择所属部门......</option>
													<c:forEach items="${deps}"  var="dep">
														<option value="${dep.depNo}" <c:if test="${operinfo.depNo==dep.depNo}">selected</c:if>> ${dep.depName}</option>
													</c:forEach>
												</select>	
											</div>
												
											<label class="col-sm-1 control-label no-padding-right" for="adminType">管理身份</label>
											<div class="col-sm-4">
												<select class="form-control" id="adminType">
													<option value="" <c:if test="${operinfo.adminType==''}">selected</c:if>>请选择管理身份......</option>
													<option value="2" <c:if test="${operinfo.adminType==2}">selected</c:if>>部门管理员</option>
													<option value="3" <c:if test="${operinfo.adminType==3}">selected</c:if>>一般用户</option>
												</select>
											</div>
										</div>
										
										<div class="form-group">
											<label class="col-sm-1 control-label no-padding-right" for="depName">用户状态</label>
											<div class="col-sm-4">
												<select class="form-control" id="stat">
													<option value="1" <c:if test="${operinfo.stat==1}">selected</c:if>>正常</option>
													<option value="2" <c:if test="${operinfo.stat==2}">selected</c:if>>失效</option>
												</select>
											</div>
										</div>
									</div>
									<h4>部门权限</h4>
									<div class="row">
										<div id="edit_tree_div" class="ztree"></div>
									</div>
									<div id = "message" class="alert alert-warning">
									</div>
								</div>
								<div class="modal-footer">
									<button id="close" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
									<button id="submit" type="button" class="btn btn-primary">提交</button>
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
 	     	    <c:forEach items="${allrights}" var="data" varStatus="status">
 	     	            {id:'${data.id}', pId:'${data.pId}', name:'${data.name}' <c:if test="${data.checked}">,checked:true</c:if>},
 	     	    </c:forEach>
 	     	 {}
 	    ];
	</script>
	<script src="/js/bus/sys/sysoper.edit.js"></script>
</body>
</html>
