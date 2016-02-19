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
<body class="no-skin">
	<jsp:include page="/jsp/comm/topbar.jsp" flush="true" />
	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			}
		</script>

		<div class="main-container-inner">
			<a class="menu-toggler" id="menu-toggler" href="#"> <span
				class="menu-text"></span>
			</a>

			<jsp:include page="/jsp/comm/sidebar.jsp" flush="true" />

			<div class="main-content">
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
						try {
							ace.settings.check('breadcrumbs', 'fixed')
						} catch (e) {
						}
					</script>
					<!-- 
					<ul class="breadcrumb">
						<li><i class="ace-icon fa fa-home home-icon"></i> <a href="">后台管理</a>
						</li>

						<li class="active"><a href="#">景区管理</a></li>
					</ul>
					.breadcrumb -->

					<div class="nav-search" id="nav-search">
						<form class="form-search">
							<span class="input-icon"> <input type="text"
								placeholder="Search ..." class="nav-search-input"
								id="nav-search-input" autocomplete="off" /> <i
								class="ace-icon fa fa-search nav-search-icon"></i>
							</span>
						</form>
					</div>
					<!-- #nav-search -->
				</div>

				<div class="page-content">
					<h4>部门信息</h4>
					<div class="row">
						<div class="form-group">
							<label class="col-sm-1 control-label no-padding-right" for="depNo">部门编号</label>
							<div class="col-sm-2">
								<input type="text" id="depNo" class="form-control"
									placeholder="部门编号" readonly="readonly" value="${depInfo.depNo}"/>
							</div>
							
							<label class="col-sm-1 control-label no-padding-right" for="depName">部门名称</label>
							<div class="col-sm-2">
								<input type="text" id="depName" class="form-control"
									placeholder="部门名称" readonly="readonly" value="${depInfo.depName}"/>
							</div>
							<label class="col-sm-2 control-label no-padding-right" for="stat">部门状态</label>
							<div class="col-sm-1">
								<select id="stat" disabled="disabled">
									<option value="1" <c:if test="${depInfo.stat==1}">selected</c:if>>正常</option>
									<option value="2" <c:if test="${depInfo.stat==2}">selected</c:if>>失效</option>
								</select>
							</div>
						</div>
					</div>
					<h4>部门权限</h4>
					<div class="row">
						<div id="info_tree_div" class="ztree"></div>
					</div>
				</div>
				<!-- /.row -->
			</div>
			<!-- /.page-content -->
		</div>
	</div>
	<script type="text/javascript">
		var menuList =[
     	    <c:forEach items="${menulist}" var="data" varStatus="status">
     	            {id:'${data.id}', pId:'${data.pId}', name:'${data.name}'},
     	    </c:forEach>
		    {}
		 ];
	</script>
	<script src="/js/bus/sys/sysdept.info.js"></script>
</body>
</html>
