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
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">×</button>
												<h4 class="modal-title" id="showModalLabel">景区详情</h4>
											</div>
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

												<div class="row">
													<div class="form-group">
														<label class="col-sm-1 control-label no-padding-right"
															for="no">景区编码</label>
														<div class="col-sm-2">
															<input type="text" id="no" class="form-control"
																placeholder="景区编码" readonly="readonly" />
														</div>
														<label class="col-sm-2 control-label no-padding-right"
															for="lvl">景区状态</label>
														<div class="col-sm-1">
															<select id="stat" disabled="disabled">
																<option value="1">正常</option>
																<option value="2">失效</option>
															</select>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="form-group">
														<label class="col-sm-1 control-label no-padding-right"
															for="lvl">景区等级</label>
														<div class="col-sm-1">
															<input maxlength="5" type="text" id="lvl"
																placeholder="景区等级" readonly="readonly" />
														</div>
														<label class="col-sm-3 control-label no-padding-right"
															for="regionname">所属地区</label>
														<div class="col-sm-3">
															<input type="text" id="regionname" class="form-control"
																placeholder="所属地区" readonly="readonly" />
														</div>
														<div class="col-sm-1">
															<input type="hidden" id="regionno" class="form-control"
																placeholder="所属地区" readonly="readonly" />
														</div>

													</div>
												</div>
												<div class="row">
													<div class="form-group">
														<label class="col-sm-1 control-label no-padding-right"
															for="name">景区名称</label>
														<div class="col-sm-10">
															<input type="text" id="name" class="form-control"
																placeholder="景区名称" readonly="readonly" />
														</div>
													</div>
												</div>
												<div class="row">
													<div class="form-group">
														<label class="col-sm-1 control-label no-padding-right"
															for="addr">景区地址</label>
														<div class="col-sm-10">
															<input placeholder="景区地址" id="addr" class="form-control"
																readonly="readonly"></input>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="form-group">
														<label class="col-sm-1 control-label no-padding-right"
															for="opentime">开放时间</label>
														<div class="col-sm-10">
															<input placeholder="开放时间" id="opentime"
																class="form-control" readonly="readonly"></input>
														</div>
													</div>
												</div>

												<div class="row">
													<div class="form-group">
														<label class="col-sm-1 control-label no-padding-right"
															for="speciality">景区特产</label>
														<div class="col-sm-10">
															<input placeholder="特产" id="speciality"
																class="form-control" readonly="readonly"></input>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="form-group">
														<label class="col-sm-1 control-label no-padding-right"
															for="desc">景区描述</label>
														<div class="col-sm-10">
															<textarea placeholder="景区描述" id="desc"
																class="form-control" readonly="readonly"></textarea>
														</div>
													</div>
												</div>



											</div>

											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal">关闭</button>
												<button id="reset" type="reset" class="btn"
													style="display: none;">重置</button>
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
	<script src="/js/bus/scenic/show.js"></script>
	
    <c:if test="${!empty succflag && succflag =='1'}">
    	
	   <script  type="text/javascript">
	          $('.jump-step').removeClass('hide');
	          setTimeout('window.location="/home.htm"',5000);
	    </script>
      
      </c:if>

</body>
</html>
