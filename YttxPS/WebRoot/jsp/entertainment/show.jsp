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

							<!-- 模态框（查询） -->

							<form class="form-horizontal">
								<div class="modal-body">
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
											<label class="col-sm-2 control-label no-padding-right" for="fsName">娱乐项目名称</label>
											<div class="col-sm-3">
												<input class="form-control" type="text" id="fsName" name="fsName" placeholder="娱乐项目名称" maxlength="10" />
											</div>
											<label class="col-sm-2 col-md-offset-2 control-label no-padding-right" for="fsAddr">娱乐项目地址</label>
											<div class="col-sm-3">
												<input class="form-control" type="text" id="fsAddr" name="fsAddr" placeholder="娱乐项目地址" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fsRegionName">所属地区</label>
											<div class="col-sm-3">
												<input type="text" placeholder="请选择行政区域" name="fsRegionName" data-key="0086" data-idx="0" data-full="中国" id="fsRegionName" class="form-control inp-search" />
												<input type="hidden" name="fsRegionno" id="fsRegionno" />
												<div id="selectCity" class="localcity"></div>
											</div>
											<label class="col-sm-2 col-md-offset-2 control-label no-padding-right" for="fsType">娱乐项目类型</label>
											<div class="col-sm-3">
												<select id="fsType" name="fsType" class="form-control">
													<option value="01">类型一</option>
													<option value="02">类型二</option>
													<option value="03">类型三</option>
												</select>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fsLvl">娱乐项目级别</label>
											<div class="col-sm-3">
												<select id="fsLvl" name="fsLvl" class="form-control">
													<option value="01">一级</option>
													<option value="02">二级</option>
													<option value="03">三级</option>
												</select>
											</div>
											<label class="col-sm-2 col-md-offset-2 control-label no-padding-right" for="fsType">状态</label>
											<div class="col-sm-3">
												<select id="fiStat" name="fiStat" class="form-control">
													<option value="1">正常</option>
													<option value="2">失效</option>
												</select>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fsOpentime">开放时间</label>
											<div class="col-sm-3">
												<input class="form-control" type="text" id="fsOpentime" name="fsOpentime" placeholder="开放时间" maxlength="100" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fsDesc">描述</label>
											<div class="col-sm-10">
												<textarea class="form-control" id="fsDesc" name="fsDesc" rows="3" placeholder="描述" maxlength="4000"></textarea>
											</div>
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" id="close" class="btn btn-default" data-dismiss="modal">关闭</button>
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
	<script src="/js/bus/entertainment/show.js"></script>

	<c:if test="${!empty succflag && succflag =='1'}">

		<script type="text/javascript">
			$('.jump-step').removeClass('hide');
			setTimeout('window.location="/home.htm"', 5000);
		</script>
	</c:if>

</body>
</html>
