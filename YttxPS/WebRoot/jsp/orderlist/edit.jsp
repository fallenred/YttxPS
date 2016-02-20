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

							<!-- 模态框（修改） -->

							<form class="form-horizontal" id="editform">

								<div class="modal-body">
									<!-- 图片TODO： -->
									<div>
										<ul class="ace-thumbnails clearfix">
											<div style="visibility: hidden;">
												<li><a class="cboxElement" data-rel="colorbox"
													href="http://127.0.0.1:81/1.jpg"> <img width="0"
														height="0" src="http://127.0.0.1:81/2.png" alt="0*0">
												</a></li>
											</div>
										</ul>
									</div>

									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="fsNo">订单名称</label>
											<div class="col-sm-3">
												<input type="hidden" id="fsNo" name="fsNo"
													class="form-control" placeholder="订单id" /> <input
													type="text" id="fsName" name="fsName" class="form-control"
													placeholder="订单名称" />
											</div>
											<label class="col-sm-2 control-label no-padding-right"
												for="fiGenindex">所属线路</label>
											<div class="col-sm-3">
												<input type="hidden" name="fiGenindex" id="fiGenindex">
												<select id="genindex" disabled="disabled" class="form-control">
												</select>
											</div>

										</div>
									</div>
									<!-- <div class="row">
										<div class="form-group"> -->
											<!-- <label class="col-sm-2 control-label no-padding-right"
												for="fsUserName">用户</label>
											<div class="col-sm-3"> -->
												<input type="hidden" id="fsUserId" name="fsUserId" class="form-control" placeholder="用户id" /> 
												<!-- <input type="text" id="fsUserName" name="fsUserName" class="form-control" placeholder="用户名" /> -->
											<!-- </div> -->
											<!-- <label class="col-sm-2 control-label no-padding-right"
												for="fsUserSubName">子用户</label> -->
											<!-- <div class="col-sm-3"> -->
												<input type="hidden" id="fsUserSubid" name="fsUserSubid" class="form-control" placeholder="用户id" /> 
												<!-- <input type="text" id="fsUserSubName" name="fsUserSubName" class="form-control" placeholder="子用户" /> -->
											<!-- </div>
										</div>
									</div> -->
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="ftCreatdate">创建日期</label>
											<div class="col-sm-3">
												<input type="text" id="ftCreatdate" name="ftCreatdate"
													class="form-control" placeholder="创建日期" />
											</div>
											<label class="col-sm-2 control-label no-padding-right"
												for="fsOperId">计调员</label>
											<div class="col-sm-3">
												<input type="hidden" id="fsOperId" name="fsOperId"
													class="form-control" placeholder="计调员id" /> <input
													type="text" id="fsOperName" name="fsOperName"
													class="form-control" placeholder="计调员" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="fsType">路线类型</label>
											<div class="col-sm-3">
												<input type="hidden" id="fsRouteId" name="fsRouteId" class="form-control" placeholder="线路id" /> 
												<select id="fsType" name="fsType" class="form-control">
													<option value="02">衍生线路</option>
													<option value="03">定制线路</option>
												</select>
											</div>
											<label class="col-sm-2 control-label no-padding-right"
												for="fsProperty">组团类型</label>
											<div class="col-sm-3">
												<select id="fsProperty" name="fsProperty"
													class="form-control">
													<option value="01">独立成团</option>
													<option value="02">散客拼团</option>
												</select>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="ftStartdate">发团日期</label>
											<div class="col-sm-3">
												<input type="text" id="ftStartdate" name="ftStartdate"
													class="form-control" placeholder="发团日期" />
											</div>
											<label class="col-sm-2 control-label no-padding-right"
												for="regionname">发团地点</label>
											<div class="col-sm-3">
												<input class="form-control" type="text"
													placeholder="请选择行政区域" name="regionname" data-key="0086  "
													data-idx="0" data-full="中国" id="regionname"
													class="inp-search" /> <input type="hidden"
													name="fsStartplace" id="regionno" />
												<div id="selectCity" class="localcity"></div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="fdPrice">初始报价(单人)</label>
											<div class="col-sm-3">
												<input type="text" id="fdPrice" name="fdPrice"
													class="form-control" placeholder="初始报价" />
											</div>
											<label class="col-sm-2 control-label no-padding-right"
												for="fdTotalfee">预估价格</label>
											<div class="col-sm-3">
												<input type="text" id="fdTotalfee" name="fdTotalfee"
													class="form-control" placeholder="预估价格" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="fdPrice">已缴金额</label>
											<div class="col-sm-3">
												<input type="text" id="fdPaidamt" name="fdPaidamt"
													class="form-control" placeholder="已缴金额" />
											</div>
											<label class="col-sm-2 control-label no-padding-right"
												for="fiStat">订单状态</label>
											<div class="col-sm-3">
												<select id="fiStat" name="fiStat"
													class="form-control">
													<option value="0">待审核</option>
													<option value="1">已审核待确认</option>
													<option value="-1">审核未通过</option>
													<option value="2">客户已确认</option>
													<option value="32">已入结算单</option>
												</select>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="transName">客户需求车型</label>
											<div class="col-sm-3">
												<input type="text" id="transName" name="" readonly="readonly" class="form-control" placeholder="" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="transType">计调车型</label>
											<div class="col-sm-3">
												<select class="form-control" id="transType" name="reslist[0].resno"></select>
											</div>
											<label class="col-sm-2 control-label no-padding-right"
												for="transPrice">车型价格</label>
											<div class="col-sm-3">
												<input type="hidden" name="reslist[0].cclist.ccno" readonly="readonly" value="000000"/>
												<input type="hidden" name="reslist[0].cclist.ccname" readonly="readonly" value="价格(通用)"/>
												<input type="text" id="transPrice" name="reslist[0].cclist.price" readonly="readonly" class="form-control"/>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="custGuideLvl">客户需求导游类型</label>
											<div class="col-sm-3">
												<input type="text" id="custGuideLvl" name="" readonly="readonly" class="form-control" placeholder="" />
											</div>
											<label class="col-sm-2 control-label no-padding-right"
												for="guideLvl">计调导游类型</label>
											<div class="col-sm-3">
												<select id="guideLvl" name="guideLvl" class="form-control">
													<option value="05">5星导游</option>
													<option value="04">4星导游</option>
													<option value="03">3星导游</option>
													<option value="02">2星导游</option>
													<option value="01">1星导游</option>
												</select>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="guideNo">计调导游选择</label>
											<div class="col-sm-3">
												<select class="form-control" id="guideNo" name="reslist[1].resno"></select>
											</div>
											<label class="col-sm-2 control-label no-padding-right"
												for="guidePrice">导游价格</label>
											<div class="col-sm-3">
												<input type="hidden" name="reslist[1].cclist.ccno" readonly="readonly" value="000000"/>
												<input type="hidden" name="reslist[1].cclist.ccname" readonly="readonly" value="价格(通用)"/>
												<input type="text" id="guidePrice" name="reslist[1].cclist.price" readonly="readonly" class="form-control"/>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="fsSummary">路线摘要</label>
											<div class="col-sm-8">
												<textarea name="fsSummary" id="fsSummary" class="form-control" rows="3" placeholder="路线摘要"></textarea>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fcSchedule">日程快照</label>
											<div class="col-sm-8">
												<textarea class="ckeditor" id="fcSchedule" name="fcSchedule"></textarea>
											</div>
										</div>
									</div>

									<div id="message" class="alert alert-warning"></div>
								</div>
								
								<div class="modal-footer">
									<button id="close" type="button" class="btn btn-default"
										data-dismiss="modal">关闭</button>
									<button id="reset" type="reset" class="btn">重置</button>
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
	<script src="/js/bus/orderlist/edit.js"></script>

	<c:if test="${!empty succflag && succflag =='1'}">

		<script type="text/javascript">
	          $('.jump-step').removeClass('hide');
	          setTimeout('window.location="/home.htm"',5000);
	    </script>

	</c:if>

</body>
</html>
