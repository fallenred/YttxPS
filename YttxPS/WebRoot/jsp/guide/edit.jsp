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
											<label class="col-sm-2 control-label no-padding-right" for="name">姓名</label>
											<div class="col-sm-3">
												<input type="hidden" id="no" name="no" />
												<input type="text" id="name" name="name" class="form-control" placeholder="姓名" />
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="gender">性别</label>
											<div class="col-sm-3">
												<select class="form-control" id="gender" name="gender">
													<option value="1">男</option>
													<option value="0">女</option>
												</select>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="gender">身份证号</label>
											<div class="col-sm-3">
												<input type="text" id="idno" name="idno" class="form-control" placeholder="身份证号" />
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="workdate">开始工作日期</label>
											<div class="col-sm-3">
												<input type="text" id="workdate" name="workdate" class="form-control datetimepicker" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="开始工作日期" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="contactno">联系方式</label>
											<div class="col-sm-3">
												<input type="text" placeholder="联系方式" id="contactno" name="contactno" class="form-control"></input>
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="mainroute">主带线路</label>
											<div class="col-sm-3">
												<input type="text" placeholder="主带线路" id="mainroute" name="mainroute" class="form-control"></input>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="preferteem">带团意向</label>
											<div class="col-sm-3">
												<select class="form-control" id="preferteem" name="preferteem">
													<option value="01">纯玩团</option>
													<option value="02">散客团</option>
													<option value="03">购物团</option>
												</select>
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="speciality">特长</label>
											<div class="col-sm-3">
												<input type="text" placeholder="特长" id="speciality" name="speciality" class="form-control"></input>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="desc">导游证号</label>
											<div class="col-sm-3">
												<input type="text" placeholder="导游证号" id="desc" name="desc" class="form-control"></input>
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="lvl">当前级别</label>
											<div class="col-sm-3">
												<select class="form-control" id="lvl" name="lvl">
												</select>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="salary">工资</label>
											<div class="col-sm-3">
												<input type="text" placeholder="工资" id="salary" name="salary" class="form-control"></input>
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="daysale">当日打单金额</label>
											<div class="col-sm-3">
												<input placeholder="当日打单金额" id="daysale" name="daysale" class="form-control"></input>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="weeksale">本周打单金额</label>
											<div class="col-sm-3">
												<input type="text" placeholder="本周打单金额" id="weeksale" name="weeksale" class="form-control"></input>
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="monthsale">本月打单金额</label>
											<div class="col-sm-3">
												<input placeholder="本月打单金额" id="monthsale" name="monthsale" class="form-control"></input>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="lvl">状态</label>
											<div class="col-sm-3">
												<select class="form-control" id="stat" name="stat">
													<option value="1">正常</option>
													<option value="2">失效</option>
											</select>
											</div>
										</div>
										
									</div>
									<div id="message" class="alert alert-warning"></div>
								</div>
								<div class="modal-footer">
									<button id="close" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
									<!-- <button id="reset" type="reset" class="btn">重置</button> -->
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
	<script src="/js/bus/guide/edit.js"></script>

	<c:if test="${!empty succflag && succflag =='1'}">

		<script type="text/javascript">
			$('.jump-step').removeClass('hide');
			setTimeout('window.location="/home.htm"', 5000);
		</script>

	</c:if>
	<script>
		//获取导游级别列表
		$.ajax({
			type : "GET",
			traditional : true,
			url : "/dict/selectDict.htm",
			data : "dict.fsParentno=dy",
			dataType : "json",
			success : function(data) {
				html = '<option value="">' + '--请选择--' + '</option>';
				$.each(data, function(commentIndex, comment) {
					html += '<option value=' + comment['fsDictno'] + '>'
							+ comment['fsDictname'] + '</option>';
				});
				$("#lvl").html(html);
			}
		});
	</script>
	<script type="text/javascript">
		$('.datetimepicker').datetimepicker({
			language : 'zh-CN',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0
		});
	</script>
</body>
</html>
