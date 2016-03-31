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
												<li><a class="cboxElement" data-rel="colorbox" href="http://127.0.0.1:81/1.jpg"> <img width="0" height="0" src="http://127.0.0.1:81/2.png" alt="0*0">
												</a></li>
											</div>
										</ul>
									</div>

									<div class="row">
										<div class="form-group">
											<label class="col-sm-1 control-label no-padding-right" for="no">购物店代码</label>
											<div class="col-sm-3">
												<input type="text" id="no" class="form-control" placeholder="购物点代码" readonly="readonly" />
											</div>
											<label class="col-sm-1 control-label no-padding-right" for="regionname">所属地区</label>
											<div class="col-sm-3">
												<input type="text" id="regionname" name="regionname" class="form-control" placeholder="所属地区" readonly="readonly" />
											</div>
											<div class="col-sm-1">
												<input type="hidden" id="regionno" name="regionno" class="form-control" placeholder="所属地区" readonly="readonly" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-1 control-label no-padding-right" for="name">购物店名称</label>
											<div class="col-sm-3">
												<input type="text" id="name" class="form-control" placeholder="购物店名称" readonly="readonly" />
											</div>
											<label class="col-sm-1 control-label no-padding-right" for="desc">经营范围</label>
											<div class="col-sm-3">
												<input type="text" id="desc" class="form-control" placeholder="经营范围" readonly="readonly" />
											</div>

										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-1 control-label no-padding-right" for="opentime">开放时间</label>
											<div class="col-sm-3">
												<input placeholder="开放时间" id="opentime" class="form-control" readonly="readonly"></input>
											</div>
											<label class="col-sm-1 control-label no-padding-right" for="tel">售后电话</label>
											<div class="col-sm-3">
												<input placeholder="售后电话" id="tel" class="form-control" readonly="readonly"></input>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-1 control-label no-padding-right" for="singlereturn">单价商品返点比例</label>
											<div class="col-sm-3">
												<input placeholder="单价商品返点比例" id="singlereturn" class="form-control" readonly="readonly"></input>
											</div>
											<label class="col-sm-1 control-label no-padding-right" for="totalreturn">总体返点比例</label>
											<div class="col-sm-3">
												<input placeholder="总体返点比例" id="totalreturn" class="form-control" readonly="readonly"></input>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-1 control-label no-padding-right" for="mantip">人头费用</label>
											<div class="col-sm-3">
												<input placeholder="人头费用" id="mantip" class="form-control" readonly="readonly"></input>
											</div>
											<label class="col-sm-1 control-label no-padding-right" for="parktip">停车费</label>
											<div class="col-sm-3">
												<input placeholder="停车费" id="parktip" class="form-control" readonly="readonly"></input>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-1 control-label no-padding-right" for="staytime">停留时间</label>
											<div class="col-sm-3">
												<input placeholder="特产" id="staytime" class="form-control" readonly="readonly"></input>
											</div>
											<label class="col-sm-1 control-label no-padding-right" for="policy">淡旺季政策</label>
											<div class="col-sm-3">
												<input placeholder="淡旺季政策" id="policy" class="form-control" readonly="readonly"></input>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-1 control-label no-padding-right" for="stat">购物店状态</label>
											<div class="col-sm-3">
												<select id="stat" class="form-control" disabled="disabled">
													<option value="1">正常</option>
													<option value="2">失效</option>
												</select>
											</div>
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
	<script src="/js/bus/shop/show.js"></script>

	<c:if test="${!empty succflag && succflag =='1'}">

		<script type="text/javascript">
			$('.jump-step').removeClass('hide');
			setTimeout('window.location="/home.htm"', 5000);
		</script>

	</c:if>

</body>
</html>
