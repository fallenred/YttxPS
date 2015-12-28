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

							<div class="row">


								<div class="col-sm-4 col-md-offset-4">
									<div class="widget-box">
										<div class="widget-header">
											<h4>用户初始密码修改</h4>
										</div>

										<div class="widget-body">
											<div class="widget-main">
												<div class="row">
													<form class="form-horizontal" id="password-form" method="post" action="/user/password.htm">
														<input type="hidden" id="forceflag" name="forceflag" value="1" />												
														
														<div class="space-4"></div>

														<div class="form-group">
															<label class="col-sm-4 control-label no-padding-right"
																for="form-field-2"> 原始密码 </label>

															<div class="col-sm-8">
																<input type="password" 
																	placeholder="原始密码" class="col-xs-10 col-sm-5" id="origpwd" name="origpwd">

															</div>
														</div>

														<div class="space-4"></div>

														<div class="form-group">
															<label class="col-sm-4 control-label no-padding-right"
																for="form-field-2"> 用户新密码 </label>

															<div class="col-sm-8">
																<input type="password" 
																	placeholder="新密码" class="col-xs-10 col-sm-5" id="targpwd" name="targpwd">															
															</div>
														</div>


														<div class="space-4"></div>

														<div class="form-group">
															<label class="col-sm-4 control-label no-padding-right"
																for="form-field-2"> 确认密码 </label>

															<div class="col-sm-8">
																<input type="password" 
																	placeholder="确认密码" class="col-xs-10 col-sm-5" id="confpwd" name="confpwd">															
															</div>
														</div>
														
														<div class="space-4"></div>
														
														<div class="password-msg center">
															<span class="bigger-150 red">${message}</span>
															<span class="bigger-110 jump-step hide">五秒后页面跳至操作首面,或<a href="/home.htm">点击此处</a></span>
														</div>

														<div class="clearfix form-actions">
															<div class="col-md-offset-3 col-md-9">
																<button class="btn btn-info" type="button" id="confirmbtn">
																	<i class="icon-ok bigger-110"></i> 确认
																</button>
																&nbsp; &nbsp; &nbsp;
																<button class="btn" type="reset" id="resetbtn">
																	<i class="icon-undo bigger-110"></i> 重置
																</button>
															</div>
														</div>
													</form>
												</div>
											</div>
										</div>
									</div>
								</div>


							</div>


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
	
	<script src="/js/bus/sha.js"></script>
    <script src="/js/bus/password.js"></script>
    
    
    <c:if test="${!empty succflag && succflag =='1'}">
    	
	   <script  type="text/javascript">
	          $('.jump-step').removeClass('hide');
	          setTimeout('window.location="/home.htm"',5000);
	    </script>
      
      </c:if>

</body>
</html>
