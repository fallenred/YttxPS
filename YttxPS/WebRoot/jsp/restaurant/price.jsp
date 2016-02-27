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
<link rel="stylesheet" href="/css/daterangepicker.css" />
<script src="/js/date-time/moment.min.js"></script>
<script src="/js/date-time/daterangepicker.min.js"></script>
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
							<form class="form-horizontal" id="addform" >
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
											<label class="col-sm-2 control-label no-padding-right" for="name">餐厅名称</label>
											<div class="col-sm-9">
												<input class="unreset" type="hidden" id="no" value="${res.no}"/>
												<input class="form-control unreset" type="text" id="name" name="name" 
													  maxlength="50" value="${res.name}" class="col-sm-5" readOnly/>
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="startdate">日期范围</label>
											<div class="col-sm-9">
												<input type="text" id="date_range" placeholder="日期范围"  readonly>
											</div>
										<!--  
											<label class="col-sm-2 control-label no-padding-right" for="startdate">开始时间</label>
											<div class="col-sm-3">
												<input type="text" id="startdate" class="form-control hasDatepicker" 
													placeholder="输入开始时间,格式如:2016-02-19">
												
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="enddate">结束时间</label>
											<div class="col-sm-3">
												<input type="text" id="enddate" class="form-control hasDatepicker" 
													placeholder="输入结束时间,格式如:2016-02-19">
							
											</div>
											-->
										</div>
									</div>
									
									<!-- TODO消费选项的读取 -->
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="breakfast">早餐价格</label>
											<div class="col-sm-9">
												<input class="form-control" style="width:150px" type="text" id="breakfast" 
													name="breakfast"  placeholder="早餐价格" />
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="lunch">午餐价格</label>
											<div class="col-sm-9">
												<input class="form-control" style="width:150px" type="text" id="lunch" 
													name="lunch"  placeholder="午餐价格" />
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="dinner">晚餐价格</label>
											<div class="col-sm-9">
												<input class="form-control"  style="width:150px" type="text" id="dinner" 
													name="dinner"  placeholder="晚餐价格" />
											</div>
										</div>
									</div>
									<div id = "message" class="alert alert-warning"></div>
								</div>
								
								<div class="modal-footer">
									<button id="close" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
									<button id="reset" type="reset" class="btn" >重置</button>
									<button id="submit" type="button" class="btn btn-primary">提交</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="/js/bus/restaurant/cost.price.js"></script>
	<c:if test="${!empty succflag && succflag =='1'}">
		<script type="text/javascript">
	          $('.jump-step').removeClass('hide');
	          setTimeout('window.location="/home.htm"',5000);
	    </script>
	</c:if>
</body>
</html>
