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
<script src="/js/bus/sha.js"></script>
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
											<label class="col-sm-1 control-label no-padding-right"for="sysOperId">用户编号</label>
											<div class="col-sm-4">
												<input type="text" id="sysOperId" name="sysOperId" class="form-control"
													placeholder="用户编号"  maxlength="16"/>
											</div>
											<label class="col-sm-1 control-label no-padding-right" for="sysOperName">用户名称</label>
											<div class="col-sm-4">
												<input type="text" id="sysOperName" name="sysOperName" class="form-control"
													placeholder="用户名称"  maxlength="128"/>
											</div>
										</div>
										<c:if test="${oper.adminType==1}">
											<div class="form-group">
												<label class="col-sm-1 control-label no-padding-right" for="adminType">管理身份</label>
												<div class="col-sm-4">
													<select class="form-control" id="adminType" name="adminType">
														<option value="">请选择......</option>
														<option value="2">部门管理员</option>
														<option value="3">一般用户</option>
													</select>
												</div>
												<label class="col-sm-1 control-label no-padding-right"  for="depNo">所属部门</label>
												<div class="col-sm-4">
													<select class="form-control" id="depNo" name="depNo">
														<option value="">请选择......</option>
														<c:forEach items="${deps}" var="dep">
															<option value="${dep.depNo}">${dep.depName}</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</c:if>
										<c:if test="${oper.adminType==2}">
											<input type="hidden" id="adminType" value="3">
											<input type="hidden" id="depNo" value="${oper.depNo}">
										</c:if>
									</div>
									
									<h4>用户密码</h4>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-1 control-label no-padding-right" for="sysOperId">用户密码</label>
											<div class="col-sm-4">
												<input type="password" id="pwd"  class="form-control" placeholder="用户密码"  maxlength="16"/>
											</div>
											<label class="col-sm-1 control-label no-padding-right" for="sysOperName">确认密码</label>
											<div class="col-sm-4">
												<input type="password" id="confirmPwd"  class="form-control"  placeholder="确认密码"  maxlength="16"/>
											</div>
										</div>
									</div>
								
								
									<h4>
										用户权限
										<small>
											<input type="checkbox" id="add_right_all">全部权限
										</small>
									</h4>
									<div class="row">
										<div id="add_tree_div" class="ztree col-sm-offset-1"></div>
									</div>
									<div id = "message" class="alert alert-warning">
									</div>
								</div>
									<div class="modal-footer">
									<button id="close" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
									<button id="reset" type="reset" class="btn" >重置</button>
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
 	     	    <c:forEach items="${menulist}" var="data" varStatus="status">
 	     	            {id:'${data.id}', pId:'${data.pId}', name:'${data.name}'},
 	     	    </c:forEach>
 	     	 {}
 	    ];
	</script>
	<script src="/js/bus/sys/sysoper.add.js"></script>
</body>
</html>
