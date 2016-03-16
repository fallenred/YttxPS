<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	
	
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8" />
<title>后台管理系统</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<jsp:include page="/jsp/comm/css.jsp" flush="true" />
<jsp:include page="/jsp/comm/scripts.jsp" flush="true" />
<script type="text/javascript">
	var auditType_item = ${auditType_Json};
	var auditRet_item = ${auditRet_Json};
</script>
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
					<div class="row">
						<div class="col-xs-12">
							<div class="widget-box">
								<div class="widget-body">
									<div class="widget-main">
										<div class="tabbable">
											<ul class="nav nav-tabs" id="tab">
												<c:forEach items="${auditRet_list}" var="item">
													<li <c:if test="${item.fsDictno==0}">class="active"</c:if>>
														<a data-toggle="tab" data-stat="${item.fsDictno}" href="#stat_div" class="auditRetPane">
															<i></i>${item.fsDictname}
														</a>
													</li>
												</c:forEach>
											</ul>
											<div class="tab-content">
												<div id="stat_div" class="tab-pane in active">
													<!-- 全部订单的过滤条件 -->
													<form class="form-horizontal" role="form" id="queryfield">
														<input type="hidden" id="auditRet" value="0">
														<div class="form-group">
															<label class="col-sm-1 control-label no-padding-right" for="id">客户ID</label>
															<div class="col-sm-5">
																<input maxlength="16" name="id" type="text" id="id" placeholder="忽略客户Id" />
															</div>
															<label class="col-sm-1 control-label no-padding-right" for="name">客户名称</label>
															<div class="col-sm-2">
																<input maxlength="50" name="name" type="text" id="name" placeholder="忽略客户名称" />
															</div>
														</div>
														
														<div class="form-group">
															<label class="col-sm-1 control-label no-padding-right" for="taname">旅行社名称</label>
															<div class="col-sm-5">
																<input maxlength="50" name="taname" type="text" 
																	id="taname" placeholder="忽略旅行社名称" />
															</div>
															<label class="col-sm-1 control-label no-padding-right" for="stat">申请审核类型</label>
															<div class="col-sm-5">
																<select id="auditType" name="auditType">
																	<option value="">忽略申请审核类型</option>
																	<c:forEach items="${auditType_list}" var="item">
																		<option value="${item.fsDictno}">${item.fsDictname}</option>
																	</c:forEach>
																</select>
															</div>
														</div>
														<div class="clearfix form-actions">
															<div class="col-md-offset-3 col-md-9">
																<button class="btn btn-info btn-sm" type="button" id="queryfield_submit">
																	<i class="ace-icon fa fa-check bigger-110"></i> 查询
																</button>
																&nbsp; &nbsp; &nbsp;
																<button class="btn btn-sm" type="reset" id="reset">
																	<i class="ace-icon fa fa-undo bigger-110"></i> 重置
																</button>
															</div>
														</div>
													</form>
													<div class="row">
														<div class="col-xs-12">
															<table id="grid-table"></table>
															<div id="grid-pager"></div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
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
	
		<!-- 模态框（查询） -->
		<div class="modal fade" id="showModal" tabindex="-1" role="dialog"
			aria-labelledby="showModalLabel" aria-hidden="false">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							<h4 class="modal-title" id="showModalLabel">会员信息</h4>
						</div>
						<div class="modal-body">
							<iframe id="showIframe" width="100%" height="1200px" frameborder="0" scrolling="no"></iframe>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- 模态框（删除） -->
		<div class="modal fade" id="delModal" tabindex="-1" role="dialog"
			aria-labelledby="delModalLabel" aria-hidden="false">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h4 class="modal-title" id="delModalLabel">提示</h4>
					</div>
					<div class="modal-body">
						<iframe id="delIframe" width="100%" height="200px" frameborder="0" scrolling="no"></iframe>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 图片上传 -->
		<div class="modal fade" id="picModal" tabindex="-1" role="dialog"
			aria-labelledby="picModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: auto; height: auto">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h4 class="modal-title" id="picModalLabel">图片管理</h4>
					</div>
					<div class="modal-body">
						<iframe id="picIframe" width="100%" height="500px" frameborder="0"
							scrolling="yes"></iframe>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="/js/bus/member/audit.member.js"></script>
</body>
</html>
