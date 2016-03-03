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
							<!-- 模态框（查询） -->
							<form class="form-horizontal">
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
												<input class="form-control" type="text" id="name" name="name" placeholder="餐厅名称"  
													maxlength="50" readonly="readonly" value="${res.name}"/>
											</div>
											<label class="col-sm-2 control-label no-padding-right">所属地区</label>
											<div class="col-sm-4">
												<input class="form-control" type="text" id="regionno" name="regionno" 
													readonly="readonly" value="${res.regionname}"/>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="addr">餐厅地址</label>
											<div class="col-sm-4">
												<input class="form-control" type="text" id="addr" name="addr" 
													placeholder="餐厅地址"  maxlength="100" readonly="readonly" value="${res.addr}"/>
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="special">特色菜品</label>
											<div class="col-sm-4">
											<!-- TODO 从数据字典中读取 -->
												<select id="special" name="special" class="form-control" disabled>
													<option value="">请选择......</option>
													<option value="01" <c:if test="${res.special=='01'}">selected</c:if>>小吃</option>
													<option value="02" <c:if test="${res.special=='02'}">selected</c:if>>藏餐</option>
													<option value="03" <c:if test="${res.special=='03'}">selected</c:if>>火锅</option>
												</select>
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="menu">餐厅菜单</label>
											<div class="col-sm-10">
												<textarea id="menu" rows="4" cols="20"  name="menu" class="form-control" disabled>${res.menu}</textarea>
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="attention">特殊说明</label>
											<div class="col-sm-10">
												<textarea id="attention" rows="4" cols="20"  name="attention" class="form-control" disabled>${res.attention}</textarea>
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
														<input name="lvl" type="radio" class="ace" value="01" 
															<c:if test="${res.lvl=='01'}">checked</c:if> disabled>
														<span class="lbl">lvl1</span>
													</label>
												</div>
												<div class="checkbox-inline">
													<label>
														<input name="lvl" type="radio" class="ace" value="02"
															<c:if test="${res.lvl=='02'}">checked</c:if> disabled>
														<span class="lbl">lvl2</span>
													</label>
												</div>
												<div class="checkbox-inline">
													<label>
														<input name="lvl" type="radio" class="ace" value="03"
															<c:if test="${res.lvl=='03'}">checked</c:if> disabled>
														<span class="lbl">lvl3</span>
													</label>
												</div>
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="scale">接待规模</label>
											<div class="col-sm-4">
												<input class="form-control" type="text" id="scale" name="scale" 
													placeholder="接待规模(桌)"  maxlength="4" value="${res.scale}" readonly/>
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="stat">状态</label>
											<div class="col-sm-4">
												<select id="stat" name="stat" class="form-control" disabled>
													<option value="">请选择......</option>
													<option value="1" <c:if test="${res.stat==1}">selected</c:if>>正常</option>
													<option value="2" <c:if test="${res.stat==2}">selected</c:if>>失效</option>
												</select>
											</div>
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<button id="close" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="/js/bus/restaurant/show.js"></script>
	<c:if test="${!empty succflag && succflag =='1'}">
		<script type="text/javascript">
	          $('.jump-step').removeClass('hide');
	          setTimeout('window.location="/home.htm"',5000);
	    </script>
	</c:if>
</body>
</html>
