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
							<form enctype="multipart/form-data" class="form-horizontal" id="editform" >
								<div class="modal-body">
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="name">餐厅名称</label>
											<div class="col-sm-4">
												<input type="hidden" id="no" name="no" value="${res.no}"/>
												<input class="form-control" type="text" id="name" name="name" placeholder="餐厅名称"  maxlength="50" value="${res.name}"/>
											</div>
											
											<label class="col-sm-2 control-label no-padding-right" for="brand">所属地区</label>
											<div class="col-sm-4">
												<input type="text" placeholder="忽略行政区域" name="regionname" id="regionname" 
													data-key="0086" data-idx="0" data-full="中国" class="form-control inp-search" value="${res.regionname}"/> 
												<input type="hidden" id="regionno" name="regionno" value="${res.regionno}"/>
												<div class="localcity selectCity" id="selectCity"></div>
											</div>
										</div>
									</div>
								
									<div class="row">
										<div class="form-group">
										
											<label class="col-sm-2 control-label no-padding-right" for="addr">餐厅地址</label>
											<div class="col-sm-4">
												<input class="form-control" type="text" id="addr" name="addr" 
													placeholder="餐厅地址"  maxlength="100" value="${res.addr}"/>
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="special">菜品特色</label>
											<div class="col-sm-4">
											<!-- TODO 从数据字典中读取 -->
												<select id="special" name="special" class="form-control">
													<option value="">---菜品特色---</option>
													<c:forEach items="${codeMasterList['cpts']}" var="item">
														<option value="${item.fsDictno}" 
															<c:if test="${res.special==item.fsDictno}">selected</c:if>>${item.fsDictname}</option>
														</c:forEach>
												</select>
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="menu">菜单名称</label>
											<div class="col-sm-10">
												<input id="menu" name="menu" type="text" class="form-control" 
												 	placeholder="菜单名称" value="${res.menu}"/>
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="photo">菜单图片</label>
											<div class="col-sm-10">
												<input type="hidden" name="menuImgFileLoction" value="${res.menuImgFileLoction}">
												<input id="menuImgFile" class="file" type="file" name="menuImgFile"> 
												<script>
												$("#menuImgFile").fileinput({
													showUpload : false,
													'allowedFileExtensions':[
														'jpg',
														'png',
														'gif' ],
													'browseLabel' : '选择菜单图片',
													initialPreview : [ "<img src='${res.menuImgFileLoction}' class='file-preview-image' />" ]
												});
											</script>
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="menuDesc">菜单说明</label>
											<div class="col-sm-10">
												<textarea id="menuDesc" rows="4" cols="20"  name="menuDesc" class="form-control">${res.menuDesc}</textarea>
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="attention">特殊说明</label>
											<div class="col-sm-10">
												<textarea id="attention" rows="4" cols="20"  name="attention" class="form-control">${res.attention}</textarea>
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
															<c:if test="${res.lvl=='01'}">checked</c:if>>
														<span class="lbl">lvl1</span>
													</label>
												</div>
												<div class="checkbox-inline">
													<label>
														<input name="lvl" type="radio" class="ace" value="02"
															<c:if test="${res.lvl=='02'}">checked</c:if>>
														<span class="lbl">lvl2</span>
													</label>
												</div>
												<div class="checkbox-inline">
													<label>
														<input name="lvl" type="radio" class="ace" value="03"
															<c:if test="${res.lvl=='03'}">checked</c:if>>
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
													placeholder="接待规模(桌)"  maxlength="4" value="${res.scale}"/>
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="stat">状态</label>
											<div class="col-sm-4">
												<select id="stat" name="stat" class="form-control">
													<option value="">请选择......</option>
													<option value="1" <c:if test="${res.stat==1}">selected</c:if>>正常</option>
													<option value="2" <c:if test="${res.stat==2}">selected</c:if>>失效</option>
												</select>
											</div>
										</div>
									</div>
									<div id = "message" class="alert alert-warning"></div>
								</div>
								
								<div class="modal-footer">
									<button id="close" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
									<button id="submit" type="button" class="btn btn-primary">提交</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /.main-container -->
	<script src="/js/bus/restaurant/edit.js"></script>
	<c:if test="${!empty succflag && succflag =='1'}">
		<script type="text/javascript">
	          $('.jump-step').removeClass('hide');
	          setTimeout('window.location="/home.htm"',5000);
	    </script>

	</c:if>

</body>
</html>
