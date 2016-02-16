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
											<label class="col-sm-2 control-label no-padding-right" for="fiGenIndex">线路统称</label>
											<div class="col-sm-3">
												<select id="fiGenindex" name="fiGenindex" class="form-control">
												</select>
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="fsName">配置线路</label>
											<div class="col-sm-3">
												<select id="fsResno" name="fsResno" class="form-control">
												</select>
											</div>
											
										</div>
									</div>
									
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fiGenIndex">日程</label>
											<div class="col-sm-3">
												<select id="fiDays" name="fiDays" class="form-control">
												</select>
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fsRegions">景区</label>
											<div class="col-sm-3">
												<select id="scenic" name="scenic" class="form-control">
												</select>
											</div>
											<div class="col-sm-3">
												<button type="button" class="btn btn-success pull-right" id="addScenicBtn">添加</button>
											</div>
											<div class="col-sm-3">
												<button type="button" class="btn btn-success pull-left" id="rmTransportBtn">删除</button>
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fsProperty"></label>
											<div class="col-sm-8" id="div_scenics">
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fsRegions">门票选择</label>
											<div class="col-sm-3">
												<select id="ticket" name="ticket" class="form-control">
												</select>
											</div>
											<div class="col-sm-3">
												<button type="button" class="btn btn-success pull-right" id="addTicketBtn">添加</button>
											</div>
											<div class="col-sm-3">
												<button type="button" class="btn btn-success pull-left" id="rmTicketBtn">删除</button>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fsProperty"></label>
											<div class="col-sm-8" id="div_ticket">
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fsRegions">餐厅选择</label>
											<div class="col-sm-3">
												<select id="transportArrange" name="transportArrange" class="form-control">
												</select>
											</div>
											<div class="col-sm-3">
												<button type="button" class="btn btn-success pull-right" id="addTransportBtn">添加</button>
											</div>
											<div class="col-sm-3">
												<button type="button" class="btn btn-success pull-left" id="rmTransportBtn">删除</button>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fsProperty"></label>
											<div class="col-sm-8">
												<input name="" type="checkbox"/> 早餐  <input name="" type="checkbox"/> 午餐  <input name="" type="checkbox"/> 晚餐  <input name="" type="checkbox"/> 接送费
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fsRegions">娱乐项目选择</label>
											<div class="col-sm-3">
												<select id="transportArrange" name="transportArrange" class="form-control">
												</select>
											</div>
											<div class="col-sm-3">
												<button type="button" class="btn btn-success pull-right" id="addTransportBtn">添加</button>
											</div>
											<div class="col-sm-3">
												<button type="button" class="btn btn-success pull-left" id="rmTransportBtn">删除</button>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fsProperty"></label>
											<div class="col-sm-8">
												<input name="" type="checkbox"/> 全价票  <input name="" type="checkbox"/> 半价票
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fsRegions">酒店标准</label>
											<div class="col-sm-3">
												<select id="fsStarLvl" name="fsStarLvl" class="form-control">
												</select>
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="fsRegions">酒店选择</label>
											<div class="col-sm-3">
												<select id="accomadationNo" name="accomadationNo" class="form-control">
												</select>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fsRegions">房型选择</label>
											<div class="col-sm-3">
												<select id="roomno" name="roomno" class="form-control">
												</select>
											</div>
											<div class="col-sm-3">
												<button type="button" class="btn btn-success pull-right" id="addRoomBtn">添加</button>
											</div>
											<div class="col-sm-3">
												<button type="button" class="btn btn-success pull-left" id="rmRoomBtn">删除</button>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fsProperty"></label>
											<div class="col-sm-8" id="div_room">
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fsRegions">购物店选择</label>
											<div class="col-sm-3">
												<select id="shop" name="shop" class="form-control">
												</select>
											</div>
											<div class="col-sm-3">
												<button type="button" class="btn btn-success pull-right" id="addShopBtn">添加</button>
											</div>
											<div class="col-sm-3">
												<button type="button" class="btn btn-success pull-left" id="rmShopBtn">删除</button>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fsProperty"></label>
											<div class="col-sm-8" id="div_shop">
											</div>
										</div>
									</div>
									<input type="hidden" id="index" value="0"/>
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
	<script src="/js/bus/routeCC/add.js"></script>

	<c:if test="${!empty succflag && succflag =='1'}">

		<script type="text/javascript">
	          $('.jump-step').removeClass('hide');
	          setTimeout('window.location="/home.htm"',5000);
	    </script>

	</c:if>

</body>
</html>
