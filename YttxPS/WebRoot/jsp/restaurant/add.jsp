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
<script src="/js/fileinput/fileinput.js"></script>
<script src="/js/fileinput/fileinput_locale_zh.js"></script>
<link rel="stylesheet" href="/css/fileinput.css" />
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
							<form class="form-horizontal" id="addform" enctype="multipart/form-data">
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
											<label class="col-sm-2 control-label no-padding-right" for="name">菜单名称</label>
											<div class="col-sm-10">
												<input class="form-control" type="text" id="name" name="name" placeholder="菜单名称" maxlength="50" />
											</div>
										</div>
									</div>

									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="regionname">所属地区</label>
											<div class="col-sm-4">
												<input type="text" placeholder="点击选择所属地区" name="regionname" id="regionname" data-key="0086" data-idx="0" data-full="中国" class="form-control inp-search" />
												<div class="localcity selectCity" id="selectCity"></div>
												<input type="hidden" id="regionno" name="regionno" />
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="special">类型标识</label>
											<div class="col-sm-4">
												<select id="special" name="special" class="form-control">
													<option value="">---类型标识---</option>
													<c:forEach items="${codeMasterList['meal_type']}" var="item">
														<option value="${item.fsDictno}">${item.fsDictname}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>

									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="menuImgFile">菜单图片</label>
											<div class="col-sm-10">
												<span class="block input-icon">
													<input id="menuImgFile" class="file" type="file" name="menuImgFile">
													<i class="icon-warning-sign red"></i>
												</span>
												<script>
													$("#menuImgFile").fileinput({
															showUpload : false,
															'allowedFileExtensions' : [
																'jpg',
																'png',
																'gif' ],
															'browseLabel' : '选择菜单图片',
													});
												</script>
											</div>
										</div>
									</div>

									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="menu">菜单描述</label>
											<div class="col-sm-10">
												<textarea id="menu" rows="4" cols="20" name="menu" class="form-control"></textarea>
											</div>
										</div>
									</div>

									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="attention">特殊说明</label>
											<div class="col-sm-10">
												<textarea id="attention" rows="4" cols="20" name="attention" class="form-control"></textarea>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right">餐标</label>
											<div class="col-sm-10">
												<c:forEach items="${codeMasterList['ct']}" var="item" varStatus="status">
													<div class="checkbox-inline">
														<label>
															<input name="lvl" type="radio" class="ace" value="${item.fsDictno}"> <span class="lbl">${item.fsDictname}</span>
														</label>
													</div>
												</c:forEach>
											</div>
										</div>
									</div>

									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="stat">状态</label>
											<div class="col-sm-4">
												<select id="stat" name="stat" class="form-control">
													<option value="">请选择状态......</option>
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
									<button id="reset" type="reset" class="btn">重置</button>
									<button id="submit" type="button" class="btn btn-primary">提交</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="/js/bus/restaurant/add.js"></script>
	<c:if test="${!empty succflag && succflag =='1'}">
		<script type="text/javascript">
			$('.jump-step').removeClass('hide');
			setTimeout('window.location="/home.htm"', 5000);
		</script>
	</c:if>
</body>
</html>
