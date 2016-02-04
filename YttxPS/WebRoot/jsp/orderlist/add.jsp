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

									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fsProperty">路线类型</label>
											<div class="col-sm-2">
												<select id="fsProperty" name="fsProperty">
													<option value="01">专家推荐</option>
													<option value="02">热门路线</option>
													<option value="03">特价路线</option>
												</select>
											</div>
											<label class="col-sm-2 col-md-offset-2 control-label no-padding-right" for="fiGenIndex">路线标题</label>
											<div class="col-sm-2">
												<input type="text" id="fiDays" name="fiDays" class="form-control" placeholder="线路标题"  />
											</div>
											
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fsProperty">路线名称</label>
											<div class="col-sm-2">
												<input type="text" id="fiDays" name="fiDays" class="form-control" placeholder="线路名称"  />
											</div>
											<label class="col-sm-2 col-md-offset-2 control-label no-padding-right" for="fiGenIndex">行程天数</label>
											<div class="col-sm-2">
												<input type="text" id="fiDays" name="fiDays" class="form-control" placeholder="行程天数"  />
											</div>
											
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="regionname">所属地区</label>
											<div class="col-sm-2">
												<input type="text" placeholder="请选择行政区域" name="regionname"
													data-key="0086" data-idx="0" data-full="中国"
													id="regionname" class="inp-search" /> <input
													type="hidden" name="regionno" id="regionno" />
												<div id="selectCity" class="localcity"></div>
											</div>
											<label class="col-sm-2 col-md-offset-2 control-label no-padding-right" for="regionname">出发地点</label>
											<div class="col-sm-2">
												<input type="text" placeholder="请选择行政区域" name="regionname"
													data-key="0086" data-idx="0" data-full="中国"
													id="regionname" class="inp-search" /> <input
													type="hidden" name="regionno" id="regionno" />
												<div id="selectCity" class="localcity"></div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="regionname">出发日期</label>
											<div class="col-sm-2">
												<input type="text" id="fiDays" name="fiDays" class="form-control" placeholder=""/>
											</div>
											<div class="col-sm-2">
												<input type="text" id="fiDays" name="fiDays" class="form-control" placeholder=""/>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="regionname">路线摘要</label>
											<div class="col-sm-8">
												<textarea class="form-control" rows="3" placeholder="路线摘要"></textarea>
											</div>
										</div>
									</div>
									<div>
										<div class="row" style="margin-top: 10px;">
											<div class="col-sm-10 col-md-offset-1" style="border: 1px solid #999;padding: 0px;">
												<h4 style="margin-left: 10px;">第一天</h5>
												<div class="row">
													<div class="form-group">
														<label class="col-sm-2 control-label" for="regionname">路线</label>
														<div class="col-sm-3">
															<input type="text" id="fiDays" name="fiDays" class="form-control" placeholder="路线"/>
														</div>
														<label class="col-sm-2 control-label" for="regionname">住宿</label>
														<div class="col-sm-3">
															<input type="text" id="fiDays" name="fiDays" class="form-control" placeholder="住宿"/>
														</div>
													</div>
												</div>
												<div class="row col-sm-11">
													<div class="form-group" style="padding: 0px;">
														<label class="col-sm-2 control-label" for="regionname">早餐</label>
														<div class="col-sm-2">
															<input type="text" id="fiDays" name="fiDays" class="form-control" placeholder="早餐"/>
														</div>
														<label class="col-sm-2 control-label" for="regionname">中餐</label>
														<div class="col-sm-2">
															<input type="text" id="fiDays" name="fiDays" class="form-control" placeholder="中餐"/>
														</div>
														<label class="col-sm-2 control-label" for="regionname">晚餐</label>
														<div class="col-sm-2">
															<input type="text" id="fiDays" name="fiDays" class="form-control" placeholder="晚餐"/>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="form-group" style="padding: 0px;">
														<label class="col-sm-2 control-label" for="regionname">景区</label>
														<div class="col-sm-2">
															<input type="text" id="fiDays" name="fiDays" class="form-control" placeholder="景区"/>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="form-group" style="padding: 0px;">
														<label class="col-sm-2 control-label" for="regionname">景区介绍</label>
														<div class="col-sm-9">
															<textarea class="form-control" rows="3" placeholder="景区介绍"></textarea>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="row" style="margin-top: 10px;">
											<div class="col-sm-10 col-md-offset-1" style="border: 1px solid #999;padding: 0px;">
												<h4 style="margin-left: 10px;">第二天</h5>
												<div class="row">
													<div class="form-group">
														<label class="col-sm-2 control-label" for="regionname">路线</label>
														<div class="col-sm-3">
															<input type="text" id="fiDays" name="fiDays" class="form-control" placeholder="路线"/>
														</div>
														<label class="col-sm-2 control-label" for="regionname">住宿</label>
														<div class="col-sm-3">
															<input type="text" id="fiDays" name="fiDays" class="form-control" placeholder="住宿"/>
														</div>
													</div>
												</div>
												<div class="row col-sm-11">
													<div class="form-group" style="padding: 0px;">
														<label class="col-sm-2 control-label" for="regionname">早餐</label>
														<div class="col-sm-2">
															<input type="text" id="fiDays" name="fiDays" class="form-control" placeholder="早餐"/>
														</div>
														<label class="col-sm-2 control-label" for="regionname">中餐</label>
														<div class="col-sm-2">
															<input type="text" id="fiDays" name="fiDays" class="form-control" placeholder="中餐"/>
														</div>
														<label class="col-sm-2 control-label" for="regionname">晚餐</label>
														<div class="col-sm-2">
															<input type="text" id="fiDays" name="fiDays" class="form-control" placeholder="晚餐"/>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="form-group" style="padding: 0px;">
														<label class="col-sm-2 control-label" for="regionname">景区</label>
														<div class="col-sm-2">
															<input type="text" id="fiDays" name="fiDays" class="form-control" placeholder="景区"/>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="form-group" style="padding: 0px;">
														<label class="col-sm-2 control-label" for="regionname">景区介绍</label>
														<div class="col-sm-9">
															<textarea class="form-control" rows="3" placeholder="景区介绍"></textarea>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="row col-sm-2 col-md-offset-9" style="margin-top: 10px;">
											<button type="button" class="btn btn-success" data-dismiss="modal">新增天数</button>
										</div>
									</div>
									
									<div class="row" style="margin-top: 10px;">
										<div class="col-sm-10 col-md-offset-1">
											<h4 style="margin-left: 10px;">费用说明</h4>
											<hr style="border: none; background-color: #999; height: 1px;"/>
											<div class="row">
												<div class="form-group">
													<label class="col-sm-2 control-label" for="regionname">交通</label>
													<div class="col-sm-9">
														<input type="text" id="fiDays" name="fiDays" class="form-control" placeholder="交通"/>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="form-group">
													<label class="col-sm-2 control-label" for="regionname">门票</label>
													<div class="col-sm-9">
														<input type="text" id="fiDays" name="fiDays" class="form-control" placeholder="门票"/>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="form-group">
													<label class="col-sm-2 control-label" for="regionname">住宿</label>
													<div class="col-sm-9">
														<input type="text" id="fiDays" name="fiDays" class="form-control" placeholder="住宿"/>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="form-group">
													<label class="col-sm-2 control-label" for="regionname">用餐</label>
													<div class="col-sm-9">
														<input type="text" id="fiDays" name="fiDays" class="form-control" placeholder="用餐"/>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="form-group">
													<label class="col-sm-2 control-label" for="regionname">导游</label>
													<div class="col-sm-9">
														<input type="text" id="fiDays" name="fiDays" class="form-control" placeholder="导游"/>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="form-group">
													<label class="col-sm-2 control-label" for="regionname">其他</label>
													<div class="col-sm-9">
														<input type="text" id="fiDays" name="fiDays" class="form-control" placeholder="其他"/>
													</div>
												</div>
											</div>
										</div>
									</div>
									
									<div class="row" style="margin-top: 10px;">
										<div class="col-sm-10 col-md-offset-1">
											<h4 style="margin-left: 10px;">预定须知</h4>
											<hr style="border: none; background-color: #999; height: 1px;"/>
											<div class="row">
												<div class="form-group">
													<div class="col-md-offset-1 col-sm-10">
														<textarea class="form-control" rows="3" placeholder="预订须知"></textarea>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="row" style="margin-top: 10px;">
										<div class="col-sm-10 col-md-offset-1">
											<h4 style="margin-left: 10px;">优惠活动</h4>
											<hr style="border: none; background-color: #999; height: 1px;"/>
											<div class="row">
												<div class="form-group">
													<div class="col-md-offset-1 col-sm-10">
														<textarea class="form-control" rows="3" placeholder="优惠活动"></textarea>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="row" style="margin-top: 10px;">
										<div class="col-sm-10 col-md-offset-1">
											<h4 style="margin-left: 10px;">重要提示</h4>
											<hr style="border: none; background-color: #999; height: 1px;"/>
											<div class="row">
												<div class="form-group">
													<div class="col-md-offset-1 col-sm-10">
														<textarea class="form-control" rows="3" placeholder="重要提示"></textarea>
													</div>
												</div>
											</div>
										</div>
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
	<script src="/js/bus/orderlist/add.js"></script>

	<c:if test="${!empty succflag && succflag =='1'}">

		<script type="text/javascript">
	          $('.jump-step').removeClass('hide');
	          setTimeout('window.location="/home.htm"',5000);
	    </script>

	</c:if>

</body>
</html>
