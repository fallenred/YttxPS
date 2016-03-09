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
<script src="/js/handlebars.js"></script>
<script src="/js/bus/pic/jquery.gridly.js"></script>
<link href="/css/jquery.gridly.css" rel="stylesheet" type="text/css" />
<link href="/css/jquery.gridly.sample.css" rel="stylesheet" type="text/css" />
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
							<div class="row">
								<div class="col-xs-6">
									<h3><span id="resName">${resName}</span></h3>
									<input id="resNo" type="hidden" value="${resNo}">
									<input id="resType" type="hidden" value="${resType}">
								</div>
							</div>
							<div class="row">
								<div id = "message" class="col-xs-8 alert alert-warning"></div>
								<div class="col-xs-1">
									<button id="upfileBtn" class="btn btn-purple">上传图片</button>
								</div>
							</div>
							
							<div class="row">
								<div class="col-xs-12" id="image-panel"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal fade" id="upfileModal" tabindex="-1" role="dialog"
				aria-labelledby="upfileModalLabel" aria-hidden="false">
				<div class="modal-dialog" style="width: 75%;height:100px">
					<div class="modal-content">
						
					</div>
				</div>
			</div>
		</div>
		
	</div>
	<c:if test="${!empty succflag && succflag =='1'}">
		<script type="text/javascript">
			$('.jump-step').removeClass('hide');
			setTimeout('window.location="/home.htm"', 5000);
		</script>
	</c:if>
	<script type="text/x-handlebars-template" id="imgTpt">
		{{#each this}}
			<div class="imgblock" id="img_wrapper_{{index}}" data-id="{{index}}" data-seq="{{seq}}" data-srcFile="{{srcFile}}">
				<a class="imgdelete" id="img_a_{{index}}" onclick="deleteImg('img_a_{{index}}')">×</a>
				<img src="{{srcFile}}" whidth="100%" hieght="100%">
			</div>
		{{/each}}
	</script>
	<script src="/js/bus/pic/pic.js"></script>
</body>
</html>
