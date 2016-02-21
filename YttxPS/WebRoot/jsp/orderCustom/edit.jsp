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
											<label class="col-sm-2 control-label no-padding-right" for="fsName">所属订单</label>
											<div class="col-sm-3">
												<input type="hidden" id="fsOrderId" name="fsOrderId" placeholder="订单id" /> 
												<input type="text" id="fsName" class="form-control" placeholder="所属订单" />
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="fiSeq">订单批次号</label>
											<div class="col-sm-3">
												<input type="text" id="fiSeq" name="fiSeq" class="form-control" placeholder="批次号" />
											</div>

										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="ftCreatdate">创建日期</label>
											<div class="col-sm-3">
												<input type="text" id="ftCreatdate" name="ftCreatdate" class="form-control" placeholder="创建日期" />
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="fiType">客户类型</label>
											<div class="col-sm-3">
												<select id="fsType" name="fsType" class="form-control">
													<option value="1">团队客户</option>
													<option value="2">散客客户</option>
												</select>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fsContactname">联系人</label>
											<div class="col-sm-3">
												<input type="text" id="fsContactname" name="fsContactname" class="form-control" placeholder="联系人" /> 
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="fsContacttel">联系电话</label>
											<div class="col-sm-3">
												<input type="text" id="fsContacttel" name="fsContacttel" class="form-control" placeholder="联系电话" /> 
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fiTotal">总人数</label>
											<div class="col-sm-3">
												<input type="text" id="fiTotal" name="fiTotal" class="form-control" placeholder="总人数" />
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="fiOlder">老人数量</label>
											<div class="col-sm-3">
												<input type="text" id="fiOlder" name="fiOlder" class="form-control" placeholder="老人数量" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fiAdult">成年人数量</label>
											<div class="col-sm-3">
												<input type="text" id="fiAdult" name="fiAdult" class="form-control" placeholder="成年人数量" />
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="fiChildren">儿童数量</label>
											<div class="col-sm-3">
												<input type="text" id="fiChildren" name="fiChildren" class="form-control" placeholder="儿童数量" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fdAmt">预估金额</label>
											<div class="col-sm-3">
												<input type="text" id="fdAmt" name="fdAmt" class="form-control" placeholder="预估金额" />
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="fiStat">订单状态</label>
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
												for="regionname">附言</label>
											<div class="col-sm-8">
												<textarea name="fsPostscript" id="fsPostscript" class="form-control" rows="3" placeholder="附言"></textarea>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<input type="hidden" id="fiGenindex"/>
											<label class="col-sm-2 control-label no-padding-right" for="fsRegions">景区选择</label>
											<div class="col-sm-3">
												<select id="scenic" class="scenic form-control">
												</select>
											</div>
											<div class="col-sm-1">
												<button type="button" class="btn btn-success pull-right" id="addScenicBtn">添加</button>
											</div>
											<div class="col-sm-1">
												<button type="button" class="btn btn-success pull-left" id="rmScenicBtn">删除</button>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<div class="col-md-offset-2 col-sm-6" id="div_scenics"></div>
										</div>
									</div>
									<input type="text" id="ftStartdate"/>
									<div class="row">
										<!-- <input type="hidden" id="fiDays" name="fiDays" class="form-control" placeholder="线路天数" /> -->
										<ul id="myTab" class="nav nav-tabs">
											<li class="active"><a href="#day0" data-toggle="tab">第1天</a></li>
										</ul>
										<div id="myTabContent" class="tab-content">
											<div class="tab-pane fade in active" id="day0">
												<input type="text" class="fiDays" name="body.daylist[0].dayflag" value="0"/>
												<input type="text" class="reslistIndex" value="0"/>
												<div class="row" style="margin-top: 10px;">
													<div class="col-sm-12" style="padding: 10 0 0 0px;">
														<div class="row">
															<div class="form-group">
																<label class="col-sm-4 pull-left" for="fsRegions">&nbsp;&nbsp;客户需求：<span id="span_ticket"></span></label>
																<label class="col-sm-2 control-label no-padding-right" for="fsRegions">门票选择</label>
																<div class="col-sm-3">
																	<select class="select_ticket form-control">
																	</select>
																</div>
																<div class="col-sm-1">
																	<button type="button" class="btn addTicketBtn btn-success pull-right" onclick="javascript:addTicket(this);">添加</button>
																</div>
																<div class="col-sm-1">
																	<button type="button" class="btn rmTicketBtn btn-success pull-left" onclick="javascript:rmTicket(this);">删除</button>
																</div>
																<div class="row">
																	<div class="form-group">
																		<div class="col-md-offset-6 col-sm-6"></div>
																	</div>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="form-group">
																<label class="col-sm-4 pull-left" for="fsRegions">&nbsp;&nbsp;客户需求：<span id="span_restaurant"></span></label>
																<label class="col-sm-2 control-label no-padding-right" for="fsRegions">餐厅选择</label>
																<div class="col-sm-3">
																	<select id="restaurant" class="form-control">
																	</select>
																</div>
																<div class="col-sm-1">
																	<button type="button" class="btn btn-success pull-right" id="addTransportBtn">添加</button>
																</div>
																<div class="col-sm-1">
																	<button type="button" class="btn btn-success pull-left" id="rmTransportBtn">删除</button>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="form-group">
																<div class="col-md-offset-6 col-sm-6">
																	<input name="" type="checkbox" /> 早餐&nbsp;&nbsp;&nbsp;
																	<input name="" type="checkbox" /> 午餐&nbsp;&nbsp;&nbsp;
																	<input name="" type="checkbox" /> 晚餐&nbsp;&nbsp;&nbsp;
																</div>
															</div>
														</div>

														<div class="row">
															<div class="form-group">
																<label class="col-sm-4 pull-left" for="fsRegions">&nbsp;&nbsp;客户需求：<span id="span_ticket"></span></label>
																<label class="col-sm-2 control-label no-padding-right" for="fsRegions">娱乐项目选择</label>
																<div class="col-sm-3">
																	<select id="transportArrange" name="transportArrange"
																		class="form-control">
																	</select>
																</div>
																<div class="col-sm-1">
																	<button type="button" class="btn btn-success pull-right" id="addTransportBtn">添加</button>
																</div>
																<div class="col-sm-1">
																	<button type="button" class="btn btn-success pull-left" id="rmTransportBtn">删除</button>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="form-group">
																<label class="col-sm-2 control-label no-padding-right"
																	for="fsProperty"></label>
																<div class="col-md-offset-6 col-sm-6">
																	<input name="" type="checkbox" />全价票&nbsp;&nbsp;&nbsp;
																	<input name="" type="checkbox" /> 半价票
																</div>
															</div>
														</div>

														<div class="row">
															<div class="form-group">
																<label class="col-sm-4 pull-left" for="fsRegions">&nbsp;&nbsp;客户需求：<span id="span_ticket"></span></label>
																<label class="col-sm-2 control-label no-padding-right" for="fsRegions">酒店标准</label>
																<div class="col-sm-3">
																	<select onchange="javascript:getAccomadation(this);" class="fsStarLvl form-control">
																	</select>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="form-group">
																<label class="col-sm-2 col-md-offset-4 control-label no-padding-right" for="fsRegions">酒店选择</label>
																<div class="col-sm-3">
																	<select onchange="javascript:getRoom(this);" class="accomadationNo form-control">
																	</select>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="form-group">
																
																<label class="col-sm-2 col-md-offset-4 control-label no-padding-right" for="fsRegions">房型选择</label>
																<div class="col-sm-3">
																	<select class="roomno form-control">
																	</select>
																</div>
																<div class="col-sm-1">
																	<button type="button" class="btn btn-success pull-right" id="addRoomBtn">添加</button>
																</div>
																<div class="col-sm-1">
																	<button type="button" class="btn btn-success pull-left" id="rmRoomBtn">删除</button>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="form-group">
																<div class="col-md-offset-6 col-sm-6" id="div_room"></div>
															</div>
														</div>

														<div class="row">
															<div class="form-group">
																<label class="col-sm-4 pull-left" for="fsRegions">&nbsp;&nbsp;客户需求：<span id="span_ticket"></span></label>
																<label class="col-sm-2 control-label no-padding-right" for="fsRegions">购物店选择</label>
																<div class="col-sm-3">
																	<select class="shop form-control">
																	</select>
																</div>
																<div class="col-sm-1">
																	<button type="button" class="btn btn-success pull-right" onclick="javascript:addShop(this);">添加</button>
																</div>
																<div class="col-sm-1">
																	<button type="button" class="btn btn-success pull-left" onclick="javascript:rmShop(this);">删除</button>
																</div>
																<div class="row">
																	<div class="form-group">
																		<div class="col-md-offset-6 col-sm-6"></div>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
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
	<script src="/js/bus/orderCustom/edit.js"></script>

	<c:if test="${!empty succflag && succflag =='1'}">

		<script type="text/javascript">
	          $('.jump-step').removeClass('hide');
	          setTimeout('window.location="/home.htm"',5000);
	    </script>

	</c:if>
	<script type="text/javascript">
		//重置iframe高度
		function resetIframeHeight(type){
			var height = $(window.parent.document).find("#editIframe").attr("height");
			height = height.substring(0,height.length - 2);
			if ("add" == type) {
				height = (parseInt(height)+21);
			} else {
				height = (parseInt(height)-21);
			}
			$(window.parent.document).find("#editIframe").attr("height", height+"px");
		}
		
		//增加门票
		function addTicket(obj){
			//数组下标
			ticketDiv = $(obj).parent().next().next().children().children();
			ticketSelect = $(obj).parent().prev().children();
			reslistIndex = $(obj).parent().parent().parent().parent().parent().prev();
			dayIndex = reslistIndex.prev().val();
			index = reslistIndex.val();
			var val = ticketSelect.val();
			var text = ticketSelect.find("option:selected").text();
			var flag = true;
			$(ticketDiv).find("input[class='ticketid']").each(function(){
				if (val == $(this).val()) { 
					flag = false;
				}
			});
			if (flag) {
				reslistIndex.attr("value", parseInt(index)+1);
				params = 'ftStartdate='+$("#ftStartdate").val()+'&fsResno='+ticketSelect.val()+'&fsRestype=mp';
				//查询门票价格
				$.ajax({
			        type: "GET",
			        url: "/tccPrice/findTccPrice.htm",
			        data: params,
			        dataType: "json",
			        success: function(data){
						html = ticketDiv.html() + '<span class="'+val+'">' +ticketSelect.find("option:selected").text()+'：</span>';
						$.each(data, function(commentIndex, comment){
							//团队全票
							if (comment['fsCcno'] == '000005' || comment['fsCcno'] == '000013') {
								html += '<span class="'+val+'"><!-- 选项编号--></span><input class="'+val+'" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist.ccno" value="'+comment['fsCcno']+'" type="checkbox"/><span class="'+val+'">&nbsp;团队全价票('+comment['fdPrice']+'￥)&nbsp;&nbsp;&nbsp;&nbsp;</span>';
								html += '<span class="'+val+'"><!-- 选项价格 --></span><input type="hidden" class="'+val+'" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist.price" value="'+comment['fdPrice']+'"/>';
							}
							//团队半票
							if (comment['fsCcno'] == '000006' || comment['fsCcno'] == '000014') {
								html += '<span class="'+val+'"><!-- 选项编号--></span><input class="'+val+'" name="body.daylist['+dayIndex+'].reslist['+index+'].restype" value="'+comment['fsCcno']+'" type="checkbox"/><span class="'+val+'">&nbsp;团队半价票('+comment['fdPrice']+'￥)<br></span>';
								html += '<span class="'+val+'"><!-- 选项价格 --></span><input type="hidden" class="'+val+'" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist.price" value="'+comment['fdPrice']+'"/>';
							}
			        	});
						html += '<span class="'+val+'"><!-- 日期标识--></span><input type="hidden" class="'+val+'" name="body.daylist['+dayIndex+'].dayflag" value="' + dayIndex + '"/>' +
								'<span class="'+val+'"><!-- 资源大类 --></span><input type="hidden" class="'+val+'" name="body.daylist['+dayIndex+'].reslist['+index+'].restype" value="mp"/>' +
								'<span class="'+val+'"><!-- 资源编号 --></span><input type="hidden" class="ticketid" name="body.daylist['+dayIndex+'].reslist['+index+'].restype" value="'+val+'"/>';
						$(obj).parent().parent().find(".row").children().children().html(html);
			        }
			    });
				resetIframeHeight("add");
			}
		}
		//删除门票
		function rmTicket(obj){
			ticketDiv = $(obj).parent().next().children().children();
			ticketSelect = $(obj).parent().prev().prev().children();
			day_index = $(obj).parent().parent().parent().parent().parent().prev();
			index = day_index.val();
			val = ticketSelect.val();
			ticketDiv.find("input[class='ticketid']").each(function(){
				if (val == $(this).val()) {
					$(this).remove();
					$("."+val).remove();
					day_index.attr("value",parseInt(index)-1);
					resetIframeHeight("sub");
				} 
			});
		}
		
		//增加购物店
		function addShop(obj){
			//数组下标
			shopDiv = $(obj).parent().next().next().children().children();
			shopSelect = $(obj).parent().prev().children();
			reslistIndex = $(obj).parent().parent().parent().parent().parent().prev();
			dayIndex = reslistIndex.prev().val();
			index = reslistIndex.val();
			var val = shopSelect.val();
			var text = shopSelect.find("option:selected").text();
			var flag = true;
			$(shopDiv).find("input[class='shopid']").each(function(){
				if (val == $(this).val()) { 
					flag = false;
				}
			});
			if (flag) {
				reslistIndex.attr("value", parseInt(index)+1);
				params = 'ftStartdate='+$("#ftStartdate").val()+'&fsResno='+shopSelect.val()+'&fsRestype=gw&fsCcno=000021';
				//查询购物店价格
				$.ajax({
			        type: "GET",
			        url: "/tccPrice/findTccPriceByKey.htm",
			        data: params,
			        dataType: "json",
			        success: function(data){
						html = shopDiv.html() + '<span class="'+val+'">' +shopSelect.find("option:selected").text()+'：</span>';
						html += '<span class="'+val+'"><!-- 选项编号 --></span><input type="hidden" class="'+val+'" name="body.daylist['+dayIndex+'].reslist['+index+'].restype" value="'+data.fsCcno+'"/><span class="'+val+'">&nbsp;人头消费('+data.fdPrice+'￥)<br></span>'+
								'<span class="'+val+'"><!-- 选项价格 --></span><input type="hidden" class="'+val+'" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist.price" value="'+data.fdPrice+'"/>'+
								'<span class="'+val+'"><!-- 日期标识 --></span><input type="hidden" class="'+val+'" name="body.daylist['+dayIndex+'].dayflag" value="' + dayIndex + '"/>' +
								'<span class="'+val+'"><!-- 资源大类 --></span><input type="hidden" class="'+val+'" name="body.daylist['+dayIndex+'].reslist['+index+'].restype" value="mp"/>' +
								'<span class="'+val+'"><!-- 资源编号 --></span><input type="hidden" class="shopid" name="body.daylist['+dayIndex+'].reslist['+index+'].restype" value="'+val+'"/>';
						$(obj).parent().parent().find(".row").children().children().html(html);
			        }
			    });
				resetIframeHeight("add");
			}
		};
		//删除门票
		function rmShop(obj){
			shopDiv = $(obj).parent().next().children().children();
			shopSelect = $(obj).parent().prev().prev().children();
			day_index = $(obj).parent().parent().parent().parent().parent().prev();
			index = day_index.val();
			val = shopSelect.val();
			shopDiv.find("input[class='shopid']").each(function(){
				if (val == $(this).val()) {
					$(this).remove();
					$("."+val).remove();
					day_index.attr("value",parseInt(index)-1);
					resetIframeHeight("sub");
				} 
			});
		}
	</script>
	<script type="text/javascript">
		//获取酒店列表
		function getAccomadation(obj){
			$.ajax({
				type: "GET",
				traditional: true,
				url: "/accomadation/selectAccomadation.htm",
				data: "accomadation.starlvl=" + $(obj).val(),
				dataType: "json",
				success: function(data){
					var html = ''; 
					$.each(data, function(commentIndex, comment){
						html += '<option value=' + comment['no'] + '>' + comment['name'] + '</option>';
					});
					$(".accomadationNo").html(html);
					getRoom($(obj).parent().parent().parent().next().find(".accomadationNo"));
				}
			});
		}
		//获取酒店房型列表
		function getRoom(obj){
			$.ajax({
				type: "GET",
				traditional: true,
				url: "/room/selectRoom.htm",
				data: "room.accomno=" + $(obj).val(),
				dataType: "json",
				success: function(data){
					var html = ''; 
					$.each(data, function(commentIndex, comment){
						html += '<option value=' + comment['roomno'] + '>' + comment['name'] + '</option>';
					});
					$(".roomno").html(html);
				}
			});
		}
	</script>
</body>
</html>
