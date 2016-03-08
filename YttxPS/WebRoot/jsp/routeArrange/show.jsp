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
									<!-- 图片TODO： -->
									<div>
										<ul class="ace-thumbnails clearfix">
											<div style="visibility: hidden;">
												<li>
													<a class="cboxElement" data-rel="colorbox" href="http://127.0.0.1:81/1.jpg"> <img width="0" height="0" src="http://127.0.0.1:81/2.png" alt="0*0"></a>
												</li>
											</div>
										</ul>
									</div>

									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fiGenIndex">所属线路</label>
											<div class="col-sm-3">
												<select id="fiGenindex" name="fiGenindex" class="form-control" disabled="disabled">
												</select>
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="fsName">路线名称</label>
											<div class="col-sm-3">
												<input type="hidden" id="fsId" name="fsId" />
												<input type="text" id="fsName" name="fsName" class="form-control" placeholder="路线名称" readonly="readonly" />
											</div>
											
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fsStartplace">发团地</label>
											<div class="col-sm-3">
												<input class="form-control" type="text" placeholder="请选择行政区域" name="fsStartplaceName" data-key="0086" data-idx="0" data-full="中国" id="fsStartplaceName" class="inp-search" readonly="readonly" />
												<input type="hidden" name="fsStartplace" id="fsStartplace" />
												<div id="selectCity" class="localcity"></div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fsProperty">线路类型</label>
											<div class="col-sm-3">
												<select class="form-control" id="fsProperty" name="fsProperty" disabled="disabled">
													<option value="1">常规线路</option>
													<option value="2">纯玩团</option>
													<option value="3">品质游</option>
													<option value="4">热门线路</option>
													<option value="5">特卖线路</option>
												</select>
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="fiDays">线路天数</label>
											<div class="col-sm-3">
												<input type="text" id="fiDays" name="fiDays" class="form-control" placeholder="线路天数" readonly="readonly" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="ftStartdate">有效日期</label>
											<div class="col-sm-3">
												<input type="text" id="ftStartdate" name="ftStartdate" class="form-control datetimepicker" readonly data-date-format="yyyy-mm-dd" maxlength="10" placeholder="" disabled="disabled" />
											</div>
											<label class="col-sm-1 control-label" style="width: 10px;">至</label>
											<div class="col-sm-3">
												<input type="text" id="ftEnddate" name="ftEnddate" class="form-control datetimepicker" readonly data-date-format="yyyy-mm-dd" maxlength="10" placeholder="" disabled="disabled" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fiStat">线路状态</label>
											<div class="col-sm-3">
												<select class="form-control" id="fiStat" name="fiStat" disabled="disabled">
													<option value="1">正常</option>
													<option value="-100">注销</option>
												</select>
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="transportArrange">车型选择</label>
											<div class="col-sm-3">
												<select id="transportArrange" name="routecc[0].fsResno" class="form-control" disabled="disabled">
												</select>
												<input type="hidden" name="routecc[0].fsRestype" value="cx" />
												<input type="hidden" name="routecc[0].fiDayflag" value="0" />
												<input type="hidden" name="routecc[0].fsCcno" value="000023" />
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fsRegions">导游星级</label>
											<div class="col-sm-3">
												<select id="guideLvl" name="guideLvl" class="form-control" disabled="disabled">
													<option value="01">金牌</option>
													<option value="02">银牌</option>
													<option value="03">铜牌</option>
												</select>
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="fiDays">导游选择</label>
											<div class="col-sm-3">
												<select id="guideFsNo" name="routecc[1].fsResno" class="form-control" disabled="disabled">
												</select>
												<input type="hidden" name="routecc[1].fsRestype" value="dy" />
												<input type="hidden" name="routecc[1].fiDayflag" value="0" />
												<input type="hidden" name="routecc[1].fsCcno" value="000000" />
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fsTitle">线路标题</label>
											<div class="col-sm-8">
												<input type="text" id="fsTitle" name="fsTitle" class="form-control" placeholder="线路标题" readonly="readonly" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fsTitlepic">线路缩略图</label>
											<div class="col-sm-8">
												<input type="text" id="fsTitlepic" name="fsTitlepic" class="form-control" placeholder="" readonly="readonly" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fsSummary">线路摘要</label>
											<div class="col-sm-8">
												<textarea class="form-control" id="fsSummary" name="fsSummary" rows="3" placeholder="线路摘要" disabled="disabled" readonly="readonly"></textarea>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fcSchedule">日程快照</label>
											<div class="col-sm-8">
												<textarea class="ckeditor" id="fcSchedule" name="fcSchedule" disabled="disabled" readonly="readonly"></textarea>
											</div>
										</div>
									</div>
									<div id = "message" class="alert alert-warning">
										
									</div>
								</div>

								<div class="modal-footer">
									<button id="close" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
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
	<script src="/js/bus/routeArrange/routeArrange.comm.js"></script>
	<script src="/js/bus/routeArrange/show.js"></script>

	<c:if test="${!empty succflag && succflag =='1'}">

		<script type="text/javascript">
			$('.jump-step').removeClass('hide');
			setTimeout('window.location="/home.htm"', 5000);
		</script>
	</c:if>

</body>
</html>
