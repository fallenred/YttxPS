<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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

							<!-- 模态框（修改） -->

							<form class="form-horizontal" id="editform">

								<div class="modal-body">
									<!-- 图片TODO： -->
									<div>
										<ul class="ace-thumbnails clearfix">
											<div style="visibility: hidden;">
												<li>
													<a class="cboxElement" data-rel="colorbox" href="http://127.0.0.1:81/1.jpg">
														<img width="0" height="0" src="http://127.0.0.1:81/2.png" alt="0*0">
													</a>
												</li>
											</div>
										</ul>
									</div>

									<div class="row">
										<div class="form-group">
											<label class="col-sm-1 control-label no-padding-right" for="no">景区编码</label>
											<div class="col-sm-2">
												<input type="text" id="no" name="no" class="form-control" placeholder="景区编码需唯一" maxlength="10" readonly="readonly" />
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="lvl">景区状态</label>
											<div class="col-sm-1">
												<select id="stat" name="stat">
													<option value="1">正常</option>
													<option value="2">失效</option>
												</select>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-1 control-label no-padding-right" for="lvl">景区等级</label>
											<div class="col-sm-2">
												<select id="lvl" name="lvl">
													<option value="">请选择景区等级</option>
													<c:forEach items="${codeMasterList['sc_lvl']}" var="lvl" varStatus="status">
														<option value="${lvl.fsDictno}">${lvl.fsDictname}</option>
													</c:forEach>
												</select>
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="regionname">所属地区</label>
											<div class="col-sm-3">
												<input type="text" placeholder="请选择行政区域" name="regionname" data-key="0086" data-idx="0" data-full="中国" id="regionname" class="inp-search" />
												<input type="hidden" name="regionno" id="regionno" />
												<div id="selectCity" class="localcity"></div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-1 control-label no-padding-right" for="name">景区名称</label>
											<div class="col-sm-10">
												<input type="text" id="name" name="name" class="form-control" placeholder="景区名称" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-1 control-label no-padding-right" for="addr">景区地址</label>
											<div class="col-sm-10">
												<input type="text" placeholder="景区地址" id="addr" name="addr" class="form-control"></input>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-1 control-label no-padding-right" for="opentime">开放时间</label>
											<div class="col-sm-10">
												<input type="text" placeholder="开放时间" id="opentime" name="opentime" class="form-control"></input>
											</div>
										</div>
									</div>

									<div class="row">
										<div class="form-group">
											<label class="col-sm-1 control-label no-padding-right" for="speciality">景区特产</label>
											<div class="col-sm-10">
												<input type="text" placeholder="特产" id="speciality" name="speciality" class="form-control"></input>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-1 control-label no-padding-right" for="desc">景区描述</label>
											<div class="col-sm-10">
												<textarea placeholder="景区描述" id="desc" name="desc" class="form-control"></textarea>
											</div>
										</div>
									</div>
									<div id="message" class="alert alert-warning"></div>
								</div>
								<div class="modal-footer">
									<button id="close" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
									<!--<button id="reset" type="reset" class="btn" >重置</button>  -->
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
	<script src="/js/bus/scenic/edit.js"></script>

	<c:if test="${!empty succflag && succflag =='1'}">

		<script type="text/javascript">
			$('.jump-step').removeClass('hide');
			setTimeout('window.location="/home.htm"', 5000);
		</script>

	</c:if>

</body>
</html>
