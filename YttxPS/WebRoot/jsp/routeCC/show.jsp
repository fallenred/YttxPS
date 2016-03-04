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
<body style="overflow-x:auto;">
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

							<form class="form-horizontal" id="addform">

								<div class="modal-body">
									<!-- 图片TODO： -->
									<div>
										<ul class="ace-thumbnails clearfix">
											<div style="visibility: hidden;">
												<li><a class="cboxElement" data-rel="colorbox" href="http://127.0.0.1:81/1.jpg"> <img width="0" height="0" src="http://127.0.0.1:81/2.png" alt="0*0">
												</a></li>
											</div>
										</ul>
									</div>

									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fiGenIndex">线路统称</label>
											<div class="col-sm-3">
												<select id="fiGenindex" name="fiGenindex" class="form-control" disabled="disabled">
												</select>
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="fsResno">配置线路</label>
											<div class="col-sm-3">
												<select id="fsResno" name="fsResno" class="form-control" disabled="disabled">
												</select>
											</div>
											
										</div>
									</div>
									
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fiDays">日程</label>
											<div class="col-sm-3">
												<select id="fiDays" name="fiDays" class="form-control query-condition">
												</select>
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="scenic">景区</label>
											<div class="col-sm-3">
												<select id="scenic" name="scenic" class="form-control">
												</select>
											</div>
											<div class="col-sm-3">
												<button type="button" class="btn btn-success pull-right" id="addScenicBtn" disabled="disabled">添加</button>
											</div>
											<div class="col-sm-3">
												<button type="button" class="btn btn-success pull-left" id="rmTransportBtn" disabled="disabled">删除</button>
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="div_scenics"></label>
											<div class="col-sm-8" id="div_scenics">
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="ticket">门票选择</label>
											<div class="col-sm-3">
												<select id="ticket" name="ticket" class="form-control">
												</select>
											</div>
											<div class="col-sm-3">
												<button type="button" class="btn btn-success pull-right" id="addTicketBtn" disabled="disabled">添加</button>
											</div>
											<div class="col-sm-3">
												<button type="button" class="btn btn-success pull-left" id="rmTicketBtn" disabled="disabled">删除</button>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="div_ticket"></label>
											<div class="col-sm-8" id="div_ticket">
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="restaurant">餐厅选择</label>
											<div class="col-sm-3">
												<select id="restaurant" name="restaurant" class="form-control">
												</select>
											</div>
											<div class="col-sm-3">
												<button type="button" class="btn btn-success pull-right" id="addRestaurantBtn" disabled="disabled">添加</button>
											</div>
											<div class="col-sm-3">
												<button type="button" class="btn btn-success pull-left" id="rmRestaurantBtn" disabled="disabled">删除</button>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fsProperty"></label>
											<div class="col-sm-8" id="div_restaurant">
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="entertainment" >娱乐项目选择</label>
											<div class="col-sm-3">
												<select id="entertainment" name="entertainment" class="form-control">
												</select>
											</div>
											<div class="col-sm-3">
												<button type="button" class="btn btn-success pull-right" id="addEntertainmentBtn" disabled="disabled">添加</button>
											</div>
											<div class="col-sm-3">
												<button type="button" class="btn btn-success pull-left" id="rmEntertainmentBtn" disabled="disabled">删除</button>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="div_entertainment"></label>
											<div class="col-sm-8" id="div_entertainment">
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
											<label class="col-sm-2 control-label no-padding-right" for="room">房型选择</label>
											<div class="col-sm-3">
												<select id="room" name="room" class="form-control">
												</select>
											</div>
											<div class="col-sm-3">
												<button type="button" class="btn btn-success pull-right" id="addRoomBtn" disabled="disabled">添加</button>
											</div>
											<div class="col-sm-3">
												<button type="button" class="btn btn-success pull-left" id="rmRoomBtn" disabled="disabled">删除</button>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="div_room"></label>
											<div class="col-sm-8" id="div_room">
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="shop">购物店选择</label>
											<div class="col-sm-3">
												<select id="shop" name="shop" class="form-control">
												</select>
											</div>
											<div class="col-sm-3">
												<button type="button" class="btn btn-success pull-right" id="addShopBtn" disabled="disabled">添加</button>
											</div>
											<div class="col-sm-3">
												<button type="button" class="btn btn-success pull-left" id="rmShopBtn" disabled="disabled">删除</button>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="div_shop"></label>
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
	<script src="/js/bus/routeCC/routeCCCommon.js"></script>
	<script src="/js/bus/routeCC/show.js"></script>

	<c:if test="${!empty succflag && succflag =='1'}">

		<script type="text/javascript">
	          $('.jump-step').removeClass('hide');
	          setTimeout('window.location="/home.htm"',5000);
	    </script>

	</c:if>

</body>
</html>
