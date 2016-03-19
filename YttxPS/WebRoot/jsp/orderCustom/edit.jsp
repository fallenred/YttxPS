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
												<input type="hidden" id="fiId" name="fiId" placeholder="批次id" /> 
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
												<input type="text" id="ftCreatdate" readonly="readonly" name="ftCreatdate" class="form-control" placeholder="创建日期" />
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="fiType">客户类型</label>
											<div class="col-sm-3">
												<select id="fsType" name="fsType" class="form-control" disabled="disabled">
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
											<!-- <label class="col-sm-2 control-label no-padding-right" for="fiStat">订单状态</label>
											<div class="col-sm-3">
												<select id="fiStat" name="fiStat"
													class="form-control">
													<option value="0">待审核</option>
													<option value="1">已审核待确认</option>
													<option value="-1">审核未通过</option>
													<option value="2">客户已确认</option>
													<option value="32">已入结算单</option>
												</select>
											</div> -->
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
												<button type="button" class="btn btn-sm btn-success pull-right" id="addScenicBtn">添加</button>
											</div>
											<div class="col-sm-1">
												<button type="button" class="btn btn-sm btn-success pull-left" id="rmScenicBtn">删除</button>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<div class="col-md-offset-2 col-sm-6" id="div_scenics"></div>
										</div>
									</div>
									<input type="hidden" id="ftStartdate"/>
									<div class="row">
										<!-- <input type="hidden" id="fiDays" name="fiDays" class="form-control" placeholder="线路天数" /> -->
										<ul id="myTab" class="nav nav-tabs">
										</ul>
										<div id="myTabContent" class="tab-content">
											<div class="tab-pane fade in active" id="day0">
												<input type="hidden" class="fiDays" name="body.daylist[0].dayflag" value="0"/>
												<input type="hidden" id="day0_resIndex" class="reslistIndex" value="0"/>
												<div class="row" style="margin-top: 10px;">
													<div class="col-sm-12" style="padding: 10 0 0 0px;">
														<div class="row">
															<div class="form-group">
																<label class="col-sm-4 pull-left" for="fsRegions">&nbsp;&nbsp;<span class="span_mp"></span></label>
																<label class="col-sm-2 control-label no-padding-right" for="fsRegions">门票选择</label>
																<div class="col-sm-3">
																	<select class="select_ticket form-control"></select>
																</div>
																<div class="col-sm-1">
																	<button type="button" class="btn btn-sm addTicketBtn btn-success pull-right" onclick="javascript:addTicket(this);">添加</button>
																</div>
																<div class="col-sm-1">
																	<button type="button" class="btn btn-sm rmTicketBtn btn-success pull-left" onclick="javascript:rmTicket(this);">删除</button>
																</div>
																<div class="row">
																	<div class="form-group">
																		<div class="col-md-offset-1 col-sm-8" id="div_0_mp"></div>
																	</div>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="form-group">
																<label class="col-sm-4 pull-left" for="fsRegions">&nbsp;&nbsp;<span class="span_ct"></span></label>
																<label class="col-sm-2 control-label no-padding-right" for="fsRegions">餐厅选择</label>
																<div class="col-sm-3">
																	<select class="select_restaurant form-control"></select>
																</div>
																<div class="col-sm-1">
																	<button type="button" class="btn btn-sm btn-success pull-right" onclick="javascript:addRestaurant(this);">添加</button>
																</div>
																<div class="col-sm-1">
																	<button type="button" class="btn btn-sm btn-success pull-left" onclick="javascript:rmRestaurant(this);">删除</button>
																</div>
																<div class="row">
																	<div class="form-group">
																		<div class="col-md-offset-1 col-sm-8" id="div_0_ct"> </div>
																	</div>
																</div>
															</div>
														</div>

														<div class="row">
															<div class="form-group">
																<label class="col-sm-4 pull-left" for="fsRegions">&nbsp;&nbsp;<span class="span_yl"></span></label>
																<label class="col-sm-2 control-label no-padding-right" for="fsRegions">娱乐项目选择</label>
																<div class="col-sm-3">
																	<select class="select_entertainment form-control"></select>
																</div>
																<div class="col-sm-1">
																	<button type="button" class="btn btn-sm btn-success pull-right" onclick="javascript:addEntertainment(this);">添加</button>
																</div>
																<div class="col-sm-1">
																	<button type="button" class="btn btn-sm btn-success pull-left" onclick="javascript:rmEntertainment(this);">删除</button>
																</div>
																<div class="row">
																	<div class="form-group">
																		<div class="col-md-offset-1 col-sm-8" id="div_0_yl"></div>
																	</div>
																</div>
															</div>
														</div>

														<div class="row">
															<div class="form-group">
																<label class="col-sm-4 pull-left" for="fsRegions">&nbsp;&nbsp;<span class="span_bg"></span></label>
																<label class="col-sm-2 control-label no-padding-right" for="fsRegions">酒店标准</label>
																<div class="col-sm-3">
																	<select onchange="javascript:getAccomadation(this);" class="fsStarLvl form-control"></select>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="form-group">
																<label class="col-sm-2 col-md-offset-4 control-label no-padding-right" for="fsRegions">酒店选择</label>
																<div class="col-sm-3">
																	<select onchange="javascript:getRoom(this);" class="accomadationNo form-control"></select>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="form-group">
																
																<label class="col-sm-2 col-md-offset-4 control-label no-padding-right" for="fsRegions">房型选择</label>
																<div class="col-sm-3">
																	<select class="room form-control"></select>
																</div>
																<div class="col-sm-1">
																	<button type="button" class="btn btn-sm btn-success pull-right" onclick="javascript:addRoom(this);">添加</button>
																</div>
																<div class="col-sm-1">
																	<button type="button" class="btn btn-sm btn-success pull-left" onclick="javascript:rmRoom(this);">删除</button>
																</div>
																<div class="row">
																	<div class="form-group">
																		<div class="col-md-offset-1 col-sm-8" id="div_0_bg"></div>
																	</div>
																</div>
															</div>
														</div>

														<div class="row">
															<div class="form-group">
																<label class="col-sm-4 pull-left" for="fsRegions">&nbsp;&nbsp;<span class="span_gw"></span></label>
																<label class="col-sm-2 control-label no-padding-right" for="fsRegions">购物店选择</label>
																<div class="col-sm-3">
																	<select class="shop form-control"></select>
																</div>
																<div class="col-sm-1">
																	<button type="button" class="btn btn-sm btn-success pull-right" onclick="javascript:addShop(this);">添加</button>
																</div>
																<div class="col-sm-1">
																	<button type="button" class="btn btn-sm btn-success pull-left" onclick="javascript:rmShop(this);">删除</button>
																</div>
																<div class="row">
																	<div class="form-group">
																		<div class="col-md-offset-1 col-sm-8" id="div_0_gw"></div>
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
									<button id="close" type="button" class="btn btn-sm btn-default"
										data-dismiss="modal">关闭</button>
									<button id="reset" type="reset" class="btn">重置</button>
									<button id="submit" type="button" class="btn btn-sm btn-primary">提交</button>
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
	<script>
	/**
	 * 计算批次金额小计
	 */
	function calculatePrice(){
		countPrice = 0;
		$(".usernum").each(function(){
			usernum = $(this).val();
			if (usernum != ''){
				$(this).parent().parent().find(".price").each(function(){
					price = '';
					if (!$(this).prop("disabled")) {
						price = $(this).val();
					}
					if (price != '') {
						countPrice = parseInt(countPrice) + parseInt(usernum) * parseInt(price);
					}
				});
			}
		});
		$("#fdAmt").val(countPrice);
	}
	
	 $(function() {
		 /**
		  * 数量改变，计算价格
		  */
		   $(document).on('change key', '.usernum', function(event){
			   calculatePrice();
		   })

	   });
	</script>
	<script type="text/javascript">
		//重置iframe高度
		function resetIframeHeight(type){
			var height = $(window.parent.document).find("#editIframe").attr("height");
			height = height.substring(0,height.length - 2);
			if ("add" == type) {
				height = (parseInt(height)+42);
			} else {
				height = (parseInt(height)-42);
			}
			$(window.parent.document).find("#editIframe").attr("height", height+"px");
		}
		
		function getDate(d, num){
			date=new Date(d);
			date.setDate(date.getDate() + parseInt(num));
	        return date.getFullYear()+"/"+(date.getMonth()+1)+"/"+date.getDate();
	    }
		
		function handleRestaurantPrice(obj){
			if($(obj).is(':checked')){
				//选项编号
				$(obj).prev().attr("disabled", false);
				//选项类型
				$(obj).prev().prev().attr("disabled", false);
				//选项名称
				$(obj).next().attr("disabled", false);
				//选项价格
				$(obj).next().next().attr("disabled", false);
			}else {
				$(obj).prev().attr("disabled", true);
				$(obj).prev().prev().attr("disabled", true);
				$(obj).next().attr("disabled", true);
				$(obj).next().next().attr("disabled", true);
			}
			calculatePrice();//点击餐厅的多选框，修改批次金额
			
		}
		
		/**
		 * 门票价格
		 */
		function handlePrice(obj){
			resno = $(obj).attr("class");
			$(obj).parent().parent().find(".price").attr("value", $(obj).next().attr("id"));
			$(obj).parent().find("input:radio").each(function(){
				$(this).prev().attr("disabled", true);
				$(this).next().attr("disabled", true);
				if($(this).is(':checked')){
					$(this).prev().attr("disabled", false);
					$(this).next().attr("disabled", false);
				}
			});
			calculatePrice();//点击门票的单选按钮，修改批次金额
		}
		
		//增加门票
		function addTicket(obj){
			//div块，用于存放计调选择的资源
			ticketDiv = $(obj).parent().next().next().children().children();
			//资源select下拉列表
			ticketSelect = $(obj).parent().prev().children();
			//同一行程日下reslist的总数
			reslistIndex = $(obj).parent().parent().parent().parent().parent().prev();
			//行程日，表示第几天
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
				var date = getDate($("#ftStartdate").val(), dayIndex);
				params = 'ftStartdate='+date+'&ftEnddate='+date+'&fsResno='+ticketSelect.val()+'&fsRestype=mp';
				//查询门票价格
				$.ajax({
			        type: "GET",
			        url: "/tccPrice/findTccPrice.htm",
			        data: params,
			        dataType: "json",
			        success: function(data){
			        	if (data == null || data == '') {
			        		alert("未配置门票价格，请先配置门票价格再添加！");
			        	}
			        	index = parseInt(index)+1;
						html = ticketDiv.html() + '<div class="row"><div class="form-group"><div class="col-sm-9"><span>' +ticketSelect.find("option:selected").text()+'：</span>'+
												  '<input name="body.daylist['+dayIndex+'].reslist['+index+'].resname" value="'+ticketSelect.find("option:selected").text()+'" type="hidden"/>'+
												  '<input name="body.daylist['+dayIndex+'].reslist['+index+'].resprop" value="prop" type="hidden"/>';
						$.each(data, function(commentIndex, comment){
							str = '<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].ccname" value="'+comment['fsCcname']+'" type="hidden" disabled="disabled"/>'+
							'<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].ccno" onclick="handlePrice(this)" value="'+comment['fsCcno']+'" type="radio"/>'+
							'<input type="hidden" class="price" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].price" value="'+comment['fdPrice']+'"/>';
							switch(comment['fsCcno']){
								case '000001':
									html += str + '<span>&nbsp;全票('+comment['fdPrice']+'￥)&nbsp;&nbsp;&nbsp;&nbsp;</span>';
							 	break;
								case '000002':
									html += str + '<span>&nbsp;半票('+comment['fdPrice']+'￥)&nbsp;&nbsp;&nbsp;&nbsp;</span>';
							 	break;
								case '000003':
									html += str + '<span>&nbsp;儿童票('+comment['fdPrice']+'￥)&nbsp;&nbsp;&nbsp;&nbsp;</span>';
							 	break;
								case '000005':
									html += str + '<span>&nbsp;团队全票('+comment['fdPrice']+'￥)&nbsp;&nbsp;&nbsp;&nbsp;</span>';
							 	break;
								case '000006':
									html += str + '<span>&nbsp;团队免票('+comment['fdPrice']+'￥)&nbsp;&nbsp;&nbsp;&nbsp;</span>';
							 	break;
								case '000007':
									html += str + '<span>&nbsp;团队儿童票('+comment['fdPrice']+'￥)&nbsp;&nbsp;&nbsp;&nbsp;</span>';
							 	break;
								case '000004':
									html += str + '<span>&nbsp;免票('+comment['fdPrice']+'￥)&nbsp;&nbsp;&nbsp;&nbsp;</span>';
							 	break;
								default:
									alert("未配置门票消费价格！");
									throw "未配置门票消费价格！";
							  
							}
							/* //团队全票
							if (comment['fsCcno'] == '000005' || comment['fsCcno'] == '000013') {
								html += '<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].ccname" value="'+comment['fsCcname']+'" type="hidden" disabled="disabled"/>'+
										'<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].ccno" onclick="handlePrice(this)" value="'+comment['fsCcno']+'" type="radio"/>'+
										'<input type="hidden" class="price" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].price" value="'+comment['fdPrice']+'"/>'+
										'<span>&nbsp;团队全价票('+comment['fdPrice']+'￥)&nbsp;&nbsp;&nbsp;&nbsp;</span>';
							}
							//团队半票
							if (comment['fsCcno'] == '000006' || comment['fsCcno'] == '000014') {
								html += '<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].ccname" value="'+comment['fsCcname']+'" type="hidden" disabled="disabled"/>'+
										'<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].ccno" onclick="handlePrice(this)" value="'+comment['fsCcno']+'" type="radio"/>'+
										'<input type="hidden" class="price" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].price" value="'+comment['fdPrice']+'"/>'+
										'<span id="'+comment['fdPrice']+'">&nbsp;团队半价票('+comment['fdPrice']+'￥)</span>';
							} */
			        	});
						 html += '</div>'+
								 '<label class="col-sm-2 control-label no-padding-right">数量：</label><div class="col-sm-1 no-padding-left">'+
								 '<input class="usernum" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].usernum" type="text"/></div>'+
							 	 '<span><!-- 选项类型 --></span><input type="hidden" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].cctype" value="1"/>' +
							 	 '<span><!-- 资源大类 --></span><input type="hidden" name="body.daylist['+dayIndex+'].reslist['+index+'].restype" value="mp"/>' +
								 '<span><!-- 资源编号 --></span><input type="hidden" class="ticketid" name="body.daylist['+dayIndex+'].reslist['+index+'].resno" value="'+val+'"/></div></div>';
						if (data != '') {
							$(obj).parent().parent().find(".row").children().children().html(html);
							reslistIndex.attr("value", index);
							resetIframeHeight("add");
						}
			        }, error : function(){
			        	alert("未配置门票价格，请先配置门票价格再添加！");
			        }
			    });
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
					$(this).parent().parent().remove();
					day_index.attr("value",parseInt(index)-1);
					resetIframeHeight("sub");
				} 
			});
			calculatePrice();//点击门票删除按钮，修改批次金额
		}
		
		//增加餐厅
		function addRestaurant(obj){
			restaurantDiv = $(obj).parent().next().next().children().children();
			restaurantSelect = $(obj).parent().prev().children();
			reslistIndex = $(obj).parent().parent().parent().parent().parent().prev();
			dayIndex = reslistIndex.prev().val();
			index = reslistIndex.val();
			var val = restaurantSelect.val();
			var text = restaurantSelect.find("option:selected").text();
			var flag = true;
			$(restaurantDiv).find("input[class='restaurantNo']").each(function(){
				if (val == $(this).val()) { 
					flag = false;
				}
			});
			if (flag) {
				var date = getDate($("#ftStartdate").val(), dayIndex);
				params = 'ftStartdate='+date+'&ftEnddate='+date+'&fsResno='+restaurantSelect.val()+'&fsRestype=ct';
				//查询餐厅价格
				$.ajax({
			        type: "GET",
			        url: "/tccPrice/findTccPrice.htm",
			        data: params,
			        dataType: "json",
			        success: function(data){
			        	if (data == null || data == '') {
			        		alert("未配置餐厅价格，请先配置餐厅价格再添加！");
			        	}
			        	index = parseInt(index)+1;
						html = restaurantDiv.html() + '<div class="row"><div class="form-group"><div class="col-sm-9"><span>' +restaurantSelect.find("option:selected").text()+'：</span>'+
						  							  '<input name="body.daylist['+dayIndex+'].reslist['+index+'].resname" value="'+restaurantSelect.find("option:selected").text()+'" type="hidden"/>'+
						    						  '<input name="body.daylist['+dayIndex+'].reslist['+index+'].resprop" value="prop" type="hidden"/>';
						$.each(data, function(commentIndex, comment){
							//早餐
							if (comment['fsCcno'] == '000018') {
								html += '<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].ccname" value="'+comment['fsCcname']+'" type="hidden" disabled="disabled"/>'+
										'<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].cctype" value="1" type="hidden" disabled="disabled"/>'+
										'<input type="checkbox" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].ccno" onclick="handleRestaurantPrice(this)" value="'+comment['fsCcno']+'"/>'+
										'<input type="hidden" class="price" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].price" value="'+comment['fdPrice']+'" disabled="disabled"/>'+
										'<span id="'+comment['fdPrice']+'">&nbsp;早餐费用('+comment['fdPrice']+'￥)&nbsp;&nbsp;&nbsp;</span>';
							}
							//午餐
							if (comment['fsCcno'] == '000019') {
								html +=	'<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[1].ccname" value="'+comment['fsCcname']+'" type="hidden" disabled="disabled"/>'+ 
										'<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[1].cctype" value="1" type="hidden" disabled="disabled"/>'+
										'<input type="checkbox" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[1].ccno" onclick="handleRestaurantPrice(this)" value="'+comment['fsCcno']+'"/>'+
										'<input class="price" type="hidden" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[1].price" value="'+comment['fdPrice']+'" disabled="disabled"/>'+
										'<span id="'+comment['fdPrice']+'">&nbsp;午餐费用('+comment['fdPrice']+'￥)&nbsp;&nbsp;&nbsp;</span>';
							}
							//晚餐
							if (comment['fsCcno'] == '000020') {
								html += '<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[2].ccname" value="'+comment['fsCcname']+'" type="hidden" disabled="disabled"/>'+
										'<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[2].cctype" value="1" type="hidden" disabled="disabled"/>'+
										'<input type="checkbox" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[2].ccno" onclick="handleRestaurantPrice(this)" value="'+comment['fsCcno']+'"/>'+
										'<input class="price" type="hidden" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[2].price" value="'+comment['fdPrice']+'" disabled="disabled"/>'+
										'<span id="'+comment['fdPrice']+'">&nbsp;晚餐费用('+comment['fdPrice']+'￥)</span>';
							}
			        	});
						html += '</div>'+
								'<label class="col-sm-2 control-label no-padding-right">数量：</label>'+
								'<div class="col-sm-1 no-padding-left"><input class="usernum" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].usernum" type="text"/></div>'+
								'<span><!-- 资源编号 --></span><input type="hidden" class="restaurantNo" name="body.daylist['+dayIndex+'].reslist['+index+'].resno" value="'+val+'"/></div></div>'+
								'<span><!-- 资源大类 --></span><input type="hidden" name="body.daylist['+dayIndex+'].reslist['+index+'].restype" value="ct"/>';
						if (data != '') {
							$(obj).parent().parent().find(".row").children().children().html(html);
							reslistIndex.attr("value", index);
							resetIframeHeight("add");
						}
			        }, error : function(){
			        	alert("未配置餐厅价格，请先配置餐厅价格再添加！");
			        }
			    });
			}
		}
		//删除餐厅
		function rmRestaurant(obj){
			restaurantDiv = $(obj).parent().next().children().children();
			restaurantSelect = $(obj).parent().prev().prev().children();
			day_index = $(obj).parent().parent().parent().parent().parent().prev();
			index = day_index.val();
			val = restaurantSelect.val();
			restaurantDiv.find("input[class='restaurantNo']").each(function(){
				if (val == $(this).val()) {
					$(this).parent().parent().remove();
					day_index.attr("value",parseInt(index)-1);
					resetIframeHeight("sub");
				} 
			});
			calculatePrice();//点击门票删除按钮，修改批次金额
		}
		
		//增加娱乐项目
		function addEntertainment(obj){
			//div块，用于存放计调选择的资源
			entertainmentDiv = $(obj).parent().next().next().children().children();
			//资源select下拉列表
			entertainmentSelect = $(obj).parent().prev().children();
			//同一行程日下reslist的总数
			reslistIndex = $(obj).parent().parent().parent().parent().parent().prev();
			//行程日，表示第几天
			dayIndex = reslistIndex.prev().val();
			index = reslistIndex.val();
			var val = entertainmentSelect.val();
			var text = entertainmentSelect.find("option:selected").text();
			var flag = true;
			$(entertainmentDiv).find("input[class='entertainmentid']").each(function(){
				if (val == $(this).val()) { 
					flag = false;
				}
			});
			if (flag) {
				var date = getDate($("#ftStartdate").val(), dayIndex);
				params = 'ftStartdate='+date+'&ftEnddate='+date+'&fsResno='+entertainmentSelect.val()+'&fsRestype=yl';
				//查询娱乐项目价格
				$.ajax({
			        type: "GET",
			        url: "/tccPrice/findTccPrice.htm",
			        data: params,
			        dataType: "json",
			        success: function(data){
			        	if (data == null || data == '') {
			        		alert("未配置娱乐项目价格，请先配置娱乐项目价格再添加！");
			        	}
			        	index = parseInt(index)+1;
						html = entertainmentDiv.html() + 
								'<div class="row"><div class="form-group"><div class="col-sm-9"><span>' +entertainmentSelect.find("option:selected").text()+'：</span>'+
						  		'<input name="body.daylist['+dayIndex+'].reslist['+index+'].resname" value="'+entertainmentSelect.find("option:selected").text()+'" type="hidden"/>'+
						  		'<input name="body.daylist['+dayIndex+'].reslist['+index+'].resprop" value="prop" type="hidden"/>';
						$.each(data, function(commentIndex, comment){
							if (comment['fsCcno'] != '000017') {
								str = '<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].ccname" value="'+comment['fsCcname']+'" type="hidden" disabled="disabled"/>'+
									  '<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].ccno" onclick="handlePrice(this)" value="'+comment['fsCcno']+'" type="radio"/>'+
									  '<input type="hidden" class="price" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].price" value="'+comment['fdPrice']+'"/>';
							} else {
								str = '<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[2].ccname" value="'+comment['fsCcname']+'" type="hidden" disabled="disabled"/>'+
									  '<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[2].ccno" onclick="handleRestaurantPrice(this)" value="'+comment['fsCcno']+'" type="checkbox"/>'+
									  '<input type="hidden" class="price" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[2].price" value="'+comment['fdPrice']+'"/>';
							}
							switch(comment['fsCcno']){
								case '000001':
									html += str + '<span>&nbsp;全票('+comment['fdPrice']+'￥)&nbsp;&nbsp;&nbsp;&nbsp;</span>';
							 		break;
								case '000002':
									html += str + '<span>&nbsp;半票('+comment['fdPrice']+'￥)&nbsp;&nbsp;&nbsp;&nbsp;</span>';
							 		break;
								case '000003':
									html += str + '<span>&nbsp;儿童票('+comment['fdPrice']+'￥)&nbsp;&nbsp;&nbsp;&nbsp;</span>';
							 		break;
								case '000004':
									html += str + '<span>&nbsp;免票('+comment['fdPrice']+'￥)&nbsp;&nbsp;&nbsp;&nbsp;</span>';
							 		break;
								case '000005':
									html += str + '<span>&nbsp;团队全票('+comment['fdPrice']+'￥)&nbsp;&nbsp;&nbsp;&nbsp;</span>';
							 		break;
								case '000006':
									html += str + '<span>&nbsp;团队免票('+comment['fdPrice']+'￥)&nbsp;&nbsp;&nbsp;&nbsp;</span>';
							 		break;
								case '000007':
									html += str + '<span>&nbsp;团队儿童票('+comment['fdPrice']+'￥)&nbsp;&nbsp;&nbsp;&nbsp;</span>';
							 		break;
								case '000008':
									html += str + '<span>&nbsp;团队免票('+comment['fdPrice']+'￥)&nbsp;&nbsp;&nbsp;&nbsp;</span>';
							 		break;
								case '000017':
									html += str + '<span>&nbsp;接送费用('+comment['fdPrice']+'￥)&nbsp;&nbsp;&nbsp;&nbsp;</span>';
							 		break;
								default:
									alert("未配置娱乐项目消费价格！");
									throw "未配置娱乐项目消费价格！";
							}
							if (commentIndex == data.length - 1) {
			        			html += '</div>';
			        		}
							/* //团队全票
							if (comment['fsCcno'] == '000005' || comment['fsCcno'] == '000013') {
								html += '<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].ccname" value="'+comment['fsCcname']+'" type="hidden" disabled="disabled"/>'+
										'<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].ccno" onclick="handlePrice(this)" value="'+comment['fsCcno']+'" type="radio"/>'+
										'<input type="hidden" class="price" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].price" value="'+comment['fdPrice']+'"/>'+
										'<span>&nbsp;团队全价票('+comment['fdPrice']+'￥)&nbsp;&nbsp;&nbsp;&nbsp;</span>';
							}
							//团队半票
							if (comment['fsCcno'] == '000006' || comment['fsCcno'] == '000014') {
								html += '<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].ccname" value="'+comment['fsCcname']+'" type="hidden" disabled="disabled"/>'+
										'<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].ccno" onclick="handlePrice(this)" value="'+comment['fsCcno']+'" type="radio"/>'+
										'<input type="hidden" class="price" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].price" value="'+comment['fdPrice']+'"/>'+
										'<span>&nbsp;团队半价票('+comment['fdPrice']+'￥)</span></div>';
							}
							//接送费用
							if (comment['fsCcno'] == '000017') {
								html += '<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[2].ccname" value="'+comment['fsCcname']+'" type="hidden" disabled="disabled"/>'+
										'<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[2].ccno" onclick="handleRestaurantPrice(this)" value="'+comment['fsCcno']+'" type="checkbox"/>'+
										'<input type="hidden" class="price" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[2].price" value="'+comment['fdPrice']+'"/>'+
										'<span>&nbsp;接送费用('+comment['fdPrice']+'￥)</span></div>';
										
							} */
			        	});
						 html += '<label class="col-sm-2 control-label no-padding-right">数量：</label>'+
								 '<div class="col-sm-1 no-padding-left"><input class="usernum" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].usernum" type="text"/></div>'+
							 	 '<span><!-- 选项类型 --></span><input type="hidden" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].cctype" value="1"/>' +
							 	 '<span><!-- 资源大类 --></span><input type="hidden" name="body.daylist['+dayIndex+'].reslist['+index+'].restype" value="yl"/>' +
								 '<span><!-- 资源编号 --></span><input type="hidden" class="entertainmentid" name="body.daylist['+dayIndex+'].reslist['+index+'].resno" value="'+val+'"/></div></div>';
						if (data != '') {
							$(obj).parent().parent().find(".row").children().children().html(html);
							reslistIndex.attr("value", index);
							resetIframeHeight("add");
						}
			        }, error : function(){
			        	alert("未配置娱乐项目价格，请先配置娱乐项目价格再添加！");
			        }
			    });
			}
		}
		//删除娱乐项目
		function rmEntertainment(obj){
			entertainmentDiv = $(obj).parent().next().children().children();
			entertainmentSelect = $(obj).parent().prev().prev().children();
			day_index = $(obj).parent().parent().parent().parent().parent().prev();
			index = day_index.val();
			val = entertainmentSelect.val();
			entertainmentDiv.find("input[class='entertainmentid']").each(function(){
				if (val == $(this).val()) {
					$(this).parent().parent().remove();
					day_index.attr("value",parseInt(index)-1);
					resetIframeHeight("sub");
				} 
			});
		}
		
		
		
		//增加房间
		function addRoom(obj){
			roomDiv = $(obj).parent().next().next().children().children();
			roomSelect = $(obj).parent().prev().children();
			fsStarLvlSelect = $(obj).parent().parent().parent().prev().prev().find(".fsStarLvl");
			regionsSelect = $(obj).parent().parent().parent().prev().find(".accomadationNo");
			reslistIndex = $(obj).parent().parent().parent().parent().parent().prev();
			dayIndex = reslistIndex.prev().val();
			index = reslistIndex.val();
			var val = roomSelect.val();
			var text = roomSelect.find("option:selected").text();
			var flag = true;
			$(roomDiv).find("input[class='roomNo']").each(function(){
				if (val == $(this).val()) { 
					flag = false;
				}
			});
			if (flag) {
				var date = getDate($("#ftStartdate").val(), dayIndex);
				params = 'ftStartdate='+date+'&fsResno='+roomSelect.val()+'&fsRestype=bg&fsCcno=000024';
				//查询酒店房间价格
				$.ajax({
			        type: "GET",
			        url: "/tccPrice/findTccPrice.htm",
			        data: params,
			        dataType: "json",
			        success: function(data){
			        	if (data == null || data == '') {
			        		alert("未配置房间价格，请先配置房间价格再添加！");
			        	}
			        	
			        	index = parseInt(index)+1;
			        	$.each(data, function(commentIndex, comment){
							html = roomDiv.html() + '<div class="row"><div class="form-group"><div class="col-sm-9"><span>' +
							fsStarLvlSelect.find("option:selected").text() + '-' + regionsSelect.find("option:selected").text()+ '-'+roomSelect.find("option:selected").text()+'：</span>'+
							  '<input name="body.daylist['+dayIndex+'].reslist['+index+'].resname" value="'+regionsSelect.find("option:selected").text()+'-'+roomSelect.find("option:selected").text()+'" type="hidden"/>'+
							  '<input name="body.daylist['+dayIndex+'].reslist['+index+'].resprop" value="prop" type="hidden"/>';
							html += '<span><!-- 选项编号 --></span><input type="hidden" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].ccno" value="'+comment['fsCcno']+'"/><span>&nbsp;房间消费('+comment['fdPrice']+'￥)</span></div>'+
									'<label class="col-sm-2 control-label no-padding-right">数量：</label>'+
									'<div class="col-sm-1 no-padding-left"><input class="usernum" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].usernum" type="text"/></div>'+
									'<span><!-- 选项价格 --></span><input class="price" type="hidden" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].price" value="'+comment['fdPrice']+'"/>'+
									'<span><!-- 选项类型 --></span><input type="hidden" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].cctype" value="1"/>' +
									'<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].ccname" value="'+comment['fsCcname']+'" type="hidden"/>'+
									'<span><!-- 资源大类 --></span><input type="hidden" name="body.daylist['+dayIndex+'].reslist['+index+'].restype" value="bg"/>' +
									'<span><!-- 资源编号 --></span><input type="hidden" class="roomNo" name="body.daylist['+dayIndex+'].reslist['+index+'].resno" value="'+val+'"/></div></div>';
							$(obj).parent().parent().find(".row").children().children().html(html);
							reslistIndex.attr("value", index);
							resetIframeHeight("add");
							return;
			        	 });
			        }, error : function(){
			        	alert("未配置房型价格，请先配置房型价格再添加！");
			        }
			    });
			}
		}
		//删除房间
		function rmRoom(obj){
			roomDiv = $(obj).parent().next().children().children();
			roomSelect = $(obj).parent().prev().prev().children();
			day_index = $(obj).parent().parent().parent().parent().parent().prev();
			index = day_index.val();
			val = roomSelect.val();
			roomDiv.find("input[class='roomNo']").each(function(){
				if (val == $(this).val()) {
					$(this).parent().parent().remove();
					day_index.attr("value",parseInt(index)-1);
					resetIframeHeight("sub");
				} 
			});
		}
		
		//增加购物店
		function addShop(obj){
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
				var date = getDate($("#ftStartdate").val(), dayIndex);
				/* params = 'ftStartdate='+date+'&fsResno='+shopSelect.val()+'&fsRestype=gw&fsCcno=000021';
				//查询购物店价格
				$.ajax({
			        type: "GET",
			        url: "/tccPrice/findTccPriceByKey.htm",
			        data: params,
			        dataType: "json",
			        success: function(data){
						html = shopDiv.html() + '<span>' +shopSelect.find("option:selected").text()+'</span>'+
						  '<input name="body.daylist['+dayIndex+'].reslist['+index+'].resname" value="'+shopSelect.find("option:selected").text()+'" type="hidden"/>'+
						  '<input name="body.daylist['+dayIndex+'].reslist['+index+'].resprop" value="prop" type="hidden"/>';
						html += '<span><!-- 选项编号 --></span><input type="hidden" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].ccno" value="'+data.fsCcno+'"/><span><br></span>'+
								'<span><!-- 选项价格 --></span><input type="hidden" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].price" value="0"/>'+
								'<span><!-- 资源大类 --></span><input type="hidden" name="body.daylist['+dayIndex+'].reslist['+index+'].restype" value="gw"/>' +
								'<span><!-- 资源编号 --></span><input type="hidden" class="shopid" name="body.daylist['+dayIndex+'].reslist['+index+'].resno" value="'+val+'"/></div></div>';
						$(obj).parent().parent().find(".row").children().children().html(html);
						reslistIndex.attr("value", parseInt(index)+1);
			        }
			    }); */
			    index = parseInt(index)+1;
			    html = shopDiv.html() + '<div class="row"><div class="form-group"><div class="col-sm-9"><span>' +shopSelect.find("option:selected").text()+'</span>'+
				  '<input name="body.daylist['+dayIndex+'].reslist['+index+'].resname" value="'+shopSelect.find("option:selected").text()+'" type="hidden"/>'+
				  '<input name="body.daylist['+dayIndex+'].reslist['+index+'].resprop" value="prop" type="hidden"/>';
				html += '<span><!-- 选项编号 --></span><input type="hidden" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].ccno" value="000021"/><span><br></span>'+
						'<span><!-- 选项价格 --></span><input type="hidden" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].price" value="0"/>'+
						'<span><!-- 资源大类 --></span><input type="hidden" name="body.daylist['+dayIndex+'].reslist['+index+'].restype" value="gw"/>' +
						'<span><!-- 资源编号 --></span><input type="hidden" class="shopid" name="body.daylist['+dayIndex+'].reslist['+index+'].resno" value="'+val+'"/></div></div>';
				$(obj).parent().parent().find(".row").children().children().html(html);
				reslistIndex.attr("value", index);
				resetIframeHeight("add");
			}
		};
		//删除购物店
		function rmShop(obj){
			shopDiv = $(obj).parent().next().children().children();
			shopSelect = $(obj).parent().prev().prev().children();
			day_index = $(obj).parent().parent().parent().parent().parent().prev();
			index = day_index.val();
			val = shopSelect.val();
			shopDiv.find("input[class='shopid']").each(function(){
				if (val == $(this).val()) {
					$(this).parent().parent().remove();
					day_index.attr("value",parseInt(index)-1);
					resetIframeHeight("sub");
				} 
			});
		}
	</script>
	
	<script type="text/javascript">
		
	
		//获取酒店列表
		function getAccomadation(obj){
			var postData = {};
			postData["accomadation.starlvl"] = $(obj).val();//餐厅等级
			$("input[name='scenicGen']").each(function(index){//景区代码
				postData["scenicNo["+index+"]"] = $(this).val();
			})
			
			$.ajax({
				type: "GET",
				traditional: true,
				url: "/accomadation/selectAccomadation.htm",
				data: postData,
				dataType: "json",
				success: function(data){
					var html = ''; 
					$.each(data, function(commentIndex, comment){
						html += '<option value=' + comment['no'] + '>' + comment['name'] + '</option>';
					});
					$(obj).parent().parent().parent().next().children().find(".col-sm-3").children().html(html);
					getRoom($(obj).parent().parent().parent().next().children().find(".col-sm-3").children());
				}
			});
		}
		//获取酒店房型列表
		function getRoom(obj){
			$.ajax({
				type: "GET",
				traditional: true,
				url: "/room/selectRoom.htm",
				data: "room.fsAccomno=" + $(obj).val(),
				dataType: "json",
				success: function(data){
					var html = ''; 
					$.each(data, function(commentIndex, comment){
						html += '<option value=' + comment['fsRoomno'] + '>' + comment['fsName'] + '</option>';
					});
					$(obj).parent().parent().parent().next().children().find(".col-sm-3").children().html(html);
				}
			});
		}
		
		
		//获取餐厅列表
		function getRestaurant(obj){
			
			$.ajax({
				type: "GET",
				traditional: true,
				url: "/restaurant/selectRestaurant.htm",
				//data: "room.accomno=" + $(obj).val(),
				data: "room.accomno=" + $(obj).val(),
				dataType: "json",
				success: function(data){
					var html = ''; 
					$.each(data, function(commentIndex, comment){
						html += '<option value=' + comment['roomno'] + '>' + comment['name'] + '</option>';
					});
					$(".room").html(html);
				}
			});
		}

		/* function countPrice(){
			countPrice = 0;
			$(".usernum").each(function(){
				usernum = $(this).val();
				if (usernum != ''){
					$(this).parent().parent().find(".price").each(function(){
						price = '';
						if (!$(this).prop("disabled")) {
							price = $(this).val();
						}
						if (price != '') {
							countPrice = parseInt(countPrice) + parseInt(usernum) * parseInt(price);
						}
					});
				}
			});
		} */
	</script>

</body>
</html>
