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
											<div class="col-sm-4">
												<input class="form-control" type="text" id="name" name="name" 
													placeholder="餐厅名称"  maxlength="50"/>
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="brand">所属景区</label>
											<div class="col-sm-4">
												<select id="scenic">
													<option value="">选择所属景区...</option>
													<c:forEach items="${scenics}" var="scenic">
														<option value="${scenic.no}">${scenic.name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
											
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="brand">所属地区</label>
											<div class="col-sm-4">
												<input type="text" placeholder="忽略行政区域" name="regionname" 
													id="regionname" data-key="0086" data-idx="0" data-full="中国"
													class="form-control inp-search" /> 
												<input type="hidden" id="regionno" name="regionno" />
												<div class="localcity selectCity" id="selectCity"></div>
											</div>
				
								
											<label class="col-sm-2 control-label no-padding-right" for="addr">餐厅地址</label>
											<div class="col-sm-4">
												<input class="form-control" type="text" id="addr" name="addr" 
													placeholder="餐厅地址"  maxlength="100"/>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="special">特色菜品</label>
											<div class="col-sm-4">
											<!-- TODO 从数据字典中读取 -->
												<select id="special" name="special" class="form-control">
													<option value="">请选择特色菜品...</option>
													<option value="01">小吃</option>
													<option value="02">藏餐</option>
													<option value="03">火锅</option>
												</select>
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="menu">餐厅菜单</label>
											<div class="col-sm-10">
												<textarea id="menu" rows="4" cols="20"  name="menu" 
													class="form-control"></textarea>
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="attention">特殊说明</label>
											<div class="col-sm-10">
												<textarea id="attention" rows="4" cols="20"  name="attention" 
													class="form-control"></textarea>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right">餐厅等级</label>
											<div class="col-sm-10">
											<!-- TODO读取餐厅等级 -->
												<div class="checkbox-inline">
													<label>
														<input name="lvl" type="radio" class="ace" value="01">
														<span class="lbl">lvl1</span>
													</label>
												</div>
												<div class="checkbox-inline">
													<label>
														<input name="lvl" type="radio" class="ace" value="02">
														<span class="lbl">lvl2</span>
													</label>
												</div>
												<div class="checkbox-inline">
													<label>
														<input name="lvl" type="radio" class="ace" value="03">
														<span class="lbl">lvl3</span>
													</label>
												</div>
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="scale">接待规模(桌)</label>
											<div class="col-sm-4">
												<input class="form-control" type="text" id="scale" name="scale" 
													placeholder="接待规模(桌)"  maxlength="4"/>
											</div>
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
	<script src="/js/bus/restaurant/add.js"></script>
	<c:if test="${!empty succflag && succflag =='1'}">
		<script type="text/javascript">
	          $('.jump-step').removeClass('hide');
	          setTimeout('window.location="/home.htm"',5000);
	    </script>
	</c:if>
</body>
</html>
