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
									<h4>部门信息</h4>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-1 control-label no-padding-right"for="depName">部门名称</label>
											<div class="col-sm-8">
												<input type="text" id="depName" name="depName" class="form-control col-sm-8"
													placeholder="部门名称"  maxlength="128"/>
											</div>
										</div>
									</div>
									<h4>
										部门权限
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
 	     	            {id:'${data.id}', pId:'${data.pId}', name:'${data.name}' <c:if test="${data.checked}">,checked:true</c:if>},
 	     	    </c:forEach>
 	     	 {}
 	    ];
	</script>
	<script src="/js/bus/sys/sysdept.add.js"></script>
</body>
</html>
