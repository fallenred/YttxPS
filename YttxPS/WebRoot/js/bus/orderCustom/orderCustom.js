//	显示详情
var raw = {};
function showOrderlist(id) {
	raw = jQuery("#grid-table").jqGrid('getRowData', id);
	$("#showModal").modal({
	    remote: "/jsp/routeCC/show.jsp"
	}); 
};

function editOrderlist(id) {
	raw = jQuery("#grid-table").jqGrid('getRowData', id);
	var frameSrc = "/jsp/orderCustom/edit.jsp";
    $("#editIframe").attr("src", frameSrc);
    $('#editModal').modal({ show: true, backdrop: 'static' });
};

function deleteOrderlist(id) {
	raw = jQuery("#grid-table").jqGrid('getRowData', id);
	var frameSrc = "/jsp/orderCustom/delete.jsp?no=" + raw.fiIndex;
	$("#delIframe").attr("src", frameSrc);
    $('#delModal').modal({ show: true, backdrop: 'static' });
};

$("#addIframe").on("load",function(){
	$(this).contents().find("#reset").click();
	$(this).contents().find("#fiIndex").val(raw.fiIndex);
	$(this).contents().find("#fsOrderName").val(raw.fsName);
	$(this).contents().find("#fdTotalFee").val(raw.fdTotalFee);
	$(this).contents().find("#fdPaidAmt").val(raw.fdPaidAmt);
});


$("#editIframe").on("load",function(){
	$(this).contents().find("#reset").click();
	$(this).contents().find("#fiId").val(raw.fiId);
	$(this).contents().find("#fsOrderId").val(raw.fsOrderId);
	$(this).contents().find("#fsName").val(raw.fsName);
	$(this).contents().find("#fiSeq").val(raw.fiSeq);
	$(this).contents().find("#ftCreatdate").val(raw.ftCreatdate);
	$(this).contents().find("#fiType").val(raw.fiType);
	$(this).contents().find("#fsContactname").val(raw.fsContactname);
	$(this).contents().find("#fsContacttel").val(raw.fsContacttel);
	$(this).contents().find("#fiTotal").val(raw.fiTotal);
	$(this).contents().find("#fiOlder").val(raw.fiOlder);
	$(this).contents().find("#fiAdult").val(raw.fiAdult);
	$(this).contents().find("#fiChildren").val(raw.fiChildren);
	$(this).contents().find("#fsPostscript").val(raw.fsPostscript);
	$(this).contents().find("#fdAmt").val(raw.fdAmt);
	$(this).contents().find("#fiStat").val(raw.fiStat);
	$(this).contents().find("#fiGenindex").val(raw.fiGenindex);
	$(this).contents().find("#ftStartdate").val(raw.ftStartdate);
	//获取资源快照
	if (raw.fiId != null) {
		findSnapshot(this, raw.fiId);
	}
	//获取线路景区
	if (raw.fiGenindex != null) {
		getSceniceGen(this, raw.fiGenindex);
	}
	
	//生成线路日程头
	var html = $(this).contents().find("#myTab").html();
	for (var i = 1; i < raw.fiDays; i ++) {
		html += '<li><a href="#day'+i+'" data-toggle="tab">第'+(parseInt(i)+1)+'天</a></li>';
	}
	$(this).contents().find("#myTab").html(html);
	//生成线路日程体
	getTabContent(this, raw.fiDays);
});

function getSceniceGen(obj, fiGenindex){
	//获取线路景区
	var num = '';
	$.ajax({
		type: "GET",
		url: "/scenicGen/selectScenicGen.htm",
		data: "scenicGen.fiGenindex="+fiGenindex,
		dataType: "json",
		success: function(data){
			var html = '线路景区：'; 
			$.each(data, function(commentIndex, comment){
				html += '<input type="hidden" name="scenicGen" value="' + comment['fsScenicno'] + '"/>' + '&nbsp;&nbsp;<label>' + comment['fsScenicname']+'</label>';
				if(commentIndex == 0)
					num = comment['fiDays'];
			});
			$(obj).contents().find("#div_scenics").html(html);
			getTicket(obj);
			getShop(obj);
			getRestaurant(obj);
			getEntertainment(obj);
		}
	});
}
function getTicket(obj){
	//获取景区票列表
	var scenic = '';
	$(obj).contents().find("input[name='scenicGen']").each(function(){
		scenic += $(this).val() + ",";
	});
	$.ajax({
		type: "GET",
		traditional: true,
		url: "/ticket/selectTicket.htm",
		data: "scenicno=" + scenic,
		dataType: "json",
		success: function(data){
			var html = ''; 
			$.each(data, function(commentIndex, comment){
				html += '<option value=' + comment['fsNo'] + '>' +comment['fsScenicname'] + '--' + comment['fsName'] + '</option>';
			});
			$(obj).contents().find(".select_ticket").html(html);
		}
	});
}

//获取景区餐厅列表
function getRestaurant(obj){
	var dataArr = new Array();
	$(obj).contents().find("input[name='scenicGen']").each(function(i, item){
		dataArr.push($(item).val());
	});
	$.ajax({
		type: "POST",
		url: "/restaurant/selectRestaurant.htm",
		data: {"scenicNo" : dataArr},
		dataType: "json",
		success: function(data){
			var html = ''; 
			$.each(data, function(commentIndex, comment){
				html += '<option value=' + comment['no'] + '>' + comment['name'] + '</option>';
			});
			$(obj).contents().find(".select_restaurant").html(html);
		}
	});
}

//获取景区娱乐项目列表
function getEntertainment(obj){
	var dataArr = new Array();
	$(obj).contents().find("input[name='scenicGen']").each(function(i, item){
		dataArr.push($(item).val());
	});
	$.ajax({
		type: "POST",
		url: "/entertainment/selectEntertainment.htm",
		data: {"scenicNo" : dataArr},
		dataType: "json",
		success: function(data){
			var html = ''; 
			$.each(data, function(commentIndex, comment){
				html += '<option value=' + comment['fsNo'] + '>' + comment['fsName'] + '</option>';
			});
			$(obj).contents().find(".select_entertainment").html(html);
		}
	});
}

//获取购物店列表
function getShop(obj){
	var scenic = '';
	$(obj).contents().find("input[name='scenicGen']").each(function(){
		scenic += $(this).val() + ",";
	});
	$.ajax({
		type: "GET",
		traditional: true,
		url: "/shop/selectShop.htm",
		data: "scenicno=" + scenic,
		dataType: "json",
		success: function(data){
			var html = ''; 
			$.each(data, function(commentIndex, comment){
				html += '<option value=' + comment['no'] + '>' +comment['scenicname'] + '--' + comment['name'] + '</option>';
			});
			$(obj).contents().find(".shop").html(html);
		}
	});
}

function getDate(d, num){
	date=new Date(d);
	date.setDate(date.getDate() + parseInt(num));
    return date.getFullYear()+"/"+(date.getMonth()+1)+"/"+date.getDate();
}

//重置iframe高度
function resetIframeHeight(type){
	var height = $("#editIframe").attr("height");
	height = height.substring(0,height.length - 2);
	if ("add" == type) {
		height = (parseInt(height)+32);
	} else {
		height = (parseInt(height)-32);
	}
	$("#editIframe").attr("height", height+"px");
}

//获取资源快照
function findSnapshot(obj, no){
	$.ajax({
		type: "POST",
		url: "/orderCustom/findSnapshot.htm",
		data: 'no='+no,
		dataType: "json",
		success: function(data){
			var html = ''; 
			//模糊字段处理
			var tab = $(obj).contents().find("#myTab").html();
			var content = $(obj).contents().find("#myTabContent").html();
			$.each(data.fuzzySnapshot.daylist, function(commentIndex, comment){
				if (commentIndex != 0){
					//tab头
					tab += '<li><a href="#day'+commentIndex+'" data-toggle="tab">第'+(commentIndex+1)+'天</a></li>';
					//tab体
					getContent(obj, commentIndex);
				}
				mp = '';
				ct = '';
				yl = '';
				bg = '';
				gw = '';
				$.each(comment['reslist'], function(commentIndex, comment){
					if ('mp' == comment['restype']) {
						mp += comment['resname']+"&nbsp;&nbsp;&nbsp;";
					}
					if ('ct' == comment['restype']) {
						ct += comment['resname']+"&nbsp;&nbsp;&nbsp;";
					}
					if ('yl' == comment['restype']) {
						yl += comment['resname']+"&nbsp;&nbsp;&nbsp;";
					}
					if ('bg' == comment['restype']) {
						bg += comment['resname']+"&nbsp;&nbsp;&nbsp;";
					}
					if ('gw' == comment['restype']) {
						gw += comment['resname']+"&nbsp;&nbsp;&nbsp;";
					}
				});
				$(obj).contents().find("#day"+commentIndex).find(".span_mp").html(mp);
				$(obj).contents().find("#day"+commentIndex).find(".span_ct").html(ct);
				$(obj).contents().find("#day"+commentIndex).find(".span_yl").html(yl);
				$(obj).contents().find("#day"+commentIndex).find(".span_bg").html(bg);
				$(obj).contents().find("#day"+commentIndex).find(".span_gw").html(gw);
			});
			//加载酒店标准列表
			getFsStarLvl(obj);
			//加载tab头
			$(obj).contents().find("#myTab").html(tab);
			//处理客户需求
			//精确字段处理
			$.each(data.resSnapshot.daylist, function(dayIndex, dayComment){
				var date = getDate($(obj).contents().find("#ftStartdate").val(), dayIndex);
				mp = '';
				ct = '';
				yl = '';
				bg = '';
				gw = '';
				$.each(dayComment['reslist'], function(index, resComment){
					if ('mp' == resComment['restype']) {
						params = 'ftStartdate='+date+'&ftEnddate='+date+'&fsResno='+resComment['resno']+'&fsRestype=mp';
						//查询门票价格
						$.ajax({
					        type: "GET",
					        url: "/tccPrice/findTccPrice.htm",
					        data: params,
					        dataType: "json",
					        success: function(data){
					        	html = $(obj).contents().find("#div_"+dayIndex+"_mp").html() + '<div class="row"><div class="form-group"><div class="col-sm-9"><span>' +resComment['resname']+'：</span>'+
								  						  '<input name="body.daylist['+dayIndex+'].reslist['+index+'].resname" value="'+resComment['resname']+'" type="hidden"/>'+
								  						  '<input name="body.daylist['+dayIndex+'].reslist['+index+'].resprop" value="prop" type="hidden"/>';
					        	$.each(data, function(commentIndex, comment){
									//团队全票
									if (comment['fsCcno'] == '000005' || comment['fsCcno'] == '000013') {
										html += '<span><!-- 选项编号--></span>';
										//选中
										if(comment['fsCcno'] == resComment['cclist'][0].ccno){
											html += '<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].ccname" value="'+comment['ccname']+'" type="hidden"/>'+
													'<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].ccno" onclick="handlePrice(this)" value="'+comment['fsCcno']+'" type="radio" checked="checked"/>'+
													'<input type="hidden" class="price" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].price" value="'+comment['fdPrice']+'"/>';
										} else {
											html += '<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].ccname" value="'+comment['ccname']+'" type="hidden" disabled="disabled"/>'+
													'<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].ccno" onclick="handlePrice(this)" value="'+comment['fsCcno']+'" type="radio"/>'+
													'<input type="hidden" class="price" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].price" value="'+comment['fdPrice']+'" disabled="disabled"/>';
										}
										html += '<span id="'+comment['fdPrice']+'">&nbsp;团队全价票('+comment['fdPrice']+'￥)&nbsp;&nbsp;&nbsp;&nbsp;</span>';
									}
									//团队半票
									if (comment['fsCcno'] == '000006' || comment['fsCcno'] == '000014') {
										html += '<span><!-- 选项编号--></span>';
										//选中
										if(comment['fsCcno'] == resComment['cclist'][0].ccno){
											html += '<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].ccname" value="'+comment['ccname']+'" type="hidden"/>'+
													'<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].ccno" onclick="handlePrice(this)" value="'+comment['fsCcno']+'" type="radio" checked="checked"/>'+
													'<input type="hidden" class="price" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].price" value="'+comment['fdPrice']+'"/>';
										} else {
											html += '<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].ccname" value="'+comment['ccname']+'" type="hidden" disabled="disabled"/>'+
													'<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].ccno" onclick="handlePrice(this)" value="'+comment['fsCcno']+'" type="radio"/>'+
													'<input type="hidden" class="price" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].price" value="'+comment['fdPrice']+'" disabled="disabled"/>';
										}
										html += '<span id="'+comment['fdPrice']+'">&nbsp;团队半价票('+comment['fdPrice']+'￥)</span></div>'+
												'<label class="col-sm-2 control-label no-padding-right">数量：</label>'+
												'<div class="col-sm-1 no-padding-left">'+
												'<input class="usernum" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].usernum" value="'+resComment['cclist'][0].usernum+'" type="text"/></div>';
									}
					        	});
								 html += '<span><!-- 资源大类 --></span><input type="hidden" name="body.daylist['+dayIndex+'].reslist['+index+'].restype" value="mp"/>' +
									 	 '<span><!-- 选项类型 --></span><input type="hidden" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].cctype" value="1"/>' +
										 '<span><!-- 资源编号 --></span><input type="hidden" class="ticketid" name="body.daylist['+dayIndex+'].reslist['+index+'].resno" value="'+resComment['resno']+'"/></div></div>';
								 if (data != '') {
									 $(obj).contents().find("#div_"+dayIndex+"_mp").html(html);
									 $(obj).contents().find("#day"+dayIndex+"_resIndex").attr("value", parseInt(index)+1);
									 resetIframeHeight("add");
								}
					        }
					    });
					}
					if ('ct' == resComment['restype']) {
						params = 'ftStartdate='+date+'&ftEnddate='+date+'&fsResno='+resComment['resno']+'&fsRestype=ct';
						//查询餐厅价格
						$.ajax({
							type: "GET",
					        url: "/tccPrice/findTccPrice.htm",
					        data: params,
					        dataType: "json",
					        success: function(data){
					        	html = $(obj).contents().find("#div_"+dayIndex+"_ct").html() + '<div class="row"><div class="form-group"><div class="col-sm-9"><span>' +resComment['resname']+'：</span>'+
								  						  '<input name="body.daylist['+dayIndex+'].reslist['+index+'].resname" value="'+resComment['resname']+'" type="hidden"/>'+
								  						  '<input name="body.daylist['+dayIndex+'].reslist['+index+'].resprop" value="prop" type="hidden"/>';
					        	$.each(data, function(commentIndex, comment){
						        		//早餐
										if (comment['fsCcno'] == '000018') {
											html += '<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].ccname" value="'+comment['ccname']+'" type="hidden" disabled="disabled"/>'+
													'<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].cctype" value="1" type="hidden" disabled="disabled"/>'+
													'<input id="'+comment['fsCcno']+'" type="checkbox" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].ccno" onclick="handleRestaurantPrice(this)" value="'+comment['fsCcno']+'"/>'+
													'<input class="price" type="hidden" class="price" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].price" value="'+comment['fdPrice']+'" disabled="disabled"/>'+
													'<span id="'+comment['fdPrice']+'">&nbsp;早餐费用('+comment['fdPrice']+'￥)&nbsp;&nbsp;&nbsp;</span>';
										}
										//午餐
										if (comment['fsCcno'] == '000019') {
											html += '<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[1].ccname" value="'+comment['ccname']+'" type="hidden" disabled="disabled"/>'+
													'<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[1].cctype" value="1" type="hidden" disabled="disabled"/>'+
													'<input id="'+comment['fsCcno']+'" type="checkbox" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[1].ccno" onclick="handleRestaurantPrice(this)" value="'+comment['fsCcno']+'"/>'+
													'<input class="price" type="hidden" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[1].price" value="'+comment['fdPrice']+'" disabled="disabled"/>'+
													'<span id="'+comment['fdPrice']+'">&nbsp;午餐费用('+comment['fdPrice']+'￥)&nbsp;&nbsp;&nbsp;</span>';
										}
										//晚餐
										if (comment['fsCcno'] == '000020') {
											html += '<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[2].ccname" value="'+comment['ccname']+'" type="hidden" disabled="disabled"/>'+
													'<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[2].cctype" value="1" type="hidden" disabled="disabled"/>'+
													'<input id="'+comment['fsCcno']+'" type="checkbox" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[2].ccno" onclick="handleRestaurantPrice(this)" value="'+comment['fsCcno']+'"/>'+
													'<input class="price" type="hidden" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[2].price" value="'+comment['fdPrice']+'" disabled="disabled"/>'+
													'<span id="'+comment['fdPrice']+'">&nbsp;晚餐费用('+comment['fdPrice']+'￥)</span></div>'+
													'<label class="col-sm-2 control-label no-padding-right">数量：</label><div class="col-sm-1 no-padding-left">'+
													'<input class="usernum" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].usernum" value="'+resComment['cclist'][0].usernum+'" type="text"/></div>';
										}
					        	});
					        	html += '<span><!-- 资源大类 --></span><input type="hidden" name="body.daylist['+dayIndex+'].reslist['+index+'].restype" value="ct"/>' +
					        			'<span><!-- 资源编号 --></span><input type="hidden" class="restaurantNo" id="'+resComment['resno']+'" name="body.daylist['+dayIndex+'].reslist['+index+'].resno" value="'+resComment['resno']+'"/></div></div>';
					        	if (data != '') {
					        		$(obj).contents().find("#div_"+dayIndex+"_ct").html(html);
					        		$(obj).contents().find("#day"+dayIndex+"_resIndex").attr("value", parseInt(index)+1);
					        		resetIframeHeight("add");
					        	}
					        	//遍历精确资源快照资源，对页面checkbox进行选中
					        	inputResno = $(obj).contents().find("#div_"+dayIndex+"_ct").find("#"+resComment['resno']);
								$.each(resComment['cclist'], function(index, cclistComment){
									$(inputResno).parent().find("#"+cclistComment['ccno']).attr("checked","checked");
									$(inputResno).parent().find("#"+cclistComment['ccno']).next().attr("disabled", false);
									$(inputResno).parent().find("#"+cclistComment['ccno']).prev().attr("disabled", false);
									$(inputResno).parent().find("#"+cclistComment['ccno']).prev().prev().attr("disabled", false);
								});
					        }
					    });
					}
					if ('yl' == resComment['restype']) {
						params = 'ftStartdate='+date+'&ftEnddate='+date+'&fsResno='+resComment['resno']+'&fsRestype=yl';
						//查询娱乐项目价格
						$.ajax({
					        type: "GET",
					        url: "/tccPrice/findTccPrice.htm",
					        data: params,
					        dataType: "json",
					        success: function(data){
					        	html = $(obj).contents().find("#div_"+dayIndex+"_yl").html() + '<div class="row"><div class="form-group"><div class="col-sm-9"><span>' +resComment['resname']+'：</span>'+
								  						  '<input name="body.daylist['+dayIndex+'].reslist['+index+'].resname" value="'+resComment['resname']+'" type="hidden"/>'+
								  						  '<input name="body.daylist['+dayIndex+'].reslist['+index+'].resprop" value="prop" type="hidden"/>';
					        	$.each(data, function(commentIndex, comment){
									//团队全票
									if (comment['fsCcno'] == '000005' || comment['fsCcno'] == '000013') {
										html += '<span><!-- 选项编号--></span>';
										//选中
										if(comment['fsCcno'] == resComment['cclist'][0].ccno){
											html += '<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].ccname" value="'+comment['ccname']+'" type="hidden"/>'+
													'<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].ccno" onclick="handlePrice(this)" value="'+comment['fsCcno']+'" type="radio" checked="checked"/>'+
													'<input type="hidden" class="price" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].price" value="'+comment['fdPrice']+'"/>';
										} else {
											html += '<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].ccname" value="'+comment['ccname']+'" type="hidden" disabled="disabled"/>'+
													'<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].ccno" onclick="handlePrice(this)" value="'+comment['fsCcno']+'" type="radio"/>'+
													'<input type="hidden" class="price" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].price" value="'+comment['fdPrice']+'" disabled="disabled"/>';
										}
										html += '<span id="'+comment['fdPrice']+'">&nbsp;团队全价票('+comment['fdPrice']+'￥)&nbsp;&nbsp;&nbsp;&nbsp;</span>';
									}
									//团队半票
									if (comment['fsCcno'] == '000006' || comment['fsCcno'] == '000014') {
										html += '<span><!-- 选项编号--></span>';
										//选中
										if(comment['fsCcno'] == resComment['cclist'][0].ccno){
											html += '<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].ccname" value="'+comment['ccname']+'" type="hidden"/>'+
													'<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].ccno" onclick="handlePrice(this)" value="'+comment['fsCcno']+'" type="radio" checked="checked"/>'+
													'<input type="hidden" class="price" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].price" value="'+comment['fdPrice']+'"/>';
										} else {
											html += '<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].ccname" value="'+comment['ccname']+'" type="hidden" disabled="disabled"/>'+
													'<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].ccno" onclick="handlePrice(this)" value="'+comment['fsCcno']+'" type="radio"/>'+
													'<input type="hidden" class="price" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].price" value="'+comment['fdPrice']+'" disabled="disabled"/>';
										}
										html += '<span id="'+comment['fdPrice']+'">&nbsp;团队半价票('+comment['fdPrice']+'￥)</span></div>'+
												'<label class="col-sm-2 control-label no-padding-right">数量：</label>'+
												'<div class="col-sm-1 no-padding-left">'+
												'<input class="usernum" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].usernum" value="'+resComment['cclist'][0].usernum+'" type="text"/></div>';
									}
					        	});
								 html += '<span><!-- 资源大类 --></span><input type="hidden" name="body.daylist['+dayIndex+'].reslist['+index+'].restype" value="yl"/>' +
									 	 '<span><!-- 选项类型 --></span><input type="hidden" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].cctype" value="1"/>' +
										 '<span><!-- 资源编号 --></span><input type="hidden" class="entertainmentid" name="body.daylist['+dayIndex+'].reslist['+index+'].resno" value="'+resComment['resno']+'"/></div></div>';
								 if (data != '') {
									$(obj).contents().find("#div_"+dayIndex+"_yl").html(html);
									$(obj).contents().find("#day"+dayIndex+"_resIndex").attr("value", parseInt(index)+1);
									resetIframeHeight("add");
								}
					        }
					    });
					}
					if ('bg' == resComment['restype']) {
						params = 'ftStartdate='+date+'&ftEnddate='+date+'&fsResno='+resComment['resno']+'&fsRestype=bg';
						//查询宾馆价格
						$.ajax({
					        type: "GET",
					        url: "/tccPrice/findTccPrice.htm",
					        data: params,
					        dataType: "json",
					        success: function(data){
					        	html = $(obj).contents().find("#div_"+dayIndex+"_bg").html() + '<div class="row"><div class="form-group"><div class="col-sm-9"><span>' +resComment['resname']+'：</span>'+
								  						  '<input name="body.daylist['+dayIndex+'].reslist['+index+'].resname" value="'+resComment['resname']+'" type="hidden"/>'+
								  						  '<input name="body.daylist['+dayIndex+'].reslist['+index+'].resprop" value="prop" type="hidden"/>';
					        	$.each(data, function(commentIndex, comment){
									if (comment['fsCcno'] == '000024') {
										html += '<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].ccname" value="'+comment['ccname']+'" type="hidden"/>'+
												'<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].ccno" value="'+comment['fsCcno']+'" type="hidden"/>'+
												'<span id="'+comment['fdPrice']+'">&nbsp;房间消费('+comment['fdPrice']+'￥)&nbsp;&nbsp;&nbsp;&nbsp;</span></div>'+
												'<label class="col-sm-2 control-label no-padding-right">数量：</label>'+
												'<div class="col-sm-1 no-padding-left">'+
												'<input class="usernum" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].usernum" value="'+resComment['cclist'][0].usernum+'" type="text"/></div>';
									}
					        	});
								 html += '<span><!-- 选项价格 --></span><input type="hidden" class="price" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].price" value="'+resComment['cclist'][0].price+'"/>'+
									 	 '<span><!-- 资源大类 --></span><input type="hidden" name="body.daylist['+dayIndex+'].reslist['+index+'].restype" value="bg"/>' +
									 	 '<span><!-- 选项类型 --></span><input type="hidden" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].cctype" value="1"/>' +
										 '<span><!-- 资源编号 --></span><input type="hidden" class="roomNo" name="body.daylist['+dayIndex+'].reslist['+index+'].resno" value="'+resComment['resno']+'"/></div></div>';
								 if (data != '') {
									 $(obj).contents().find("#div_"+dayIndex+"_bg").html(html);
									 $(obj).contents().find("#day"+dayIndex+"_resIndex").attr("value", parseInt(index)+1);
									 resetIframeHeight("add");
								}
					        }
					    });
					}
					if ('gw' == resComment['restype']) {
						params = 'ftStartdate='+date+'&ftEnddate='+date+'&fsResno='+resComment['resno']+'&fsRestype=gw';
						//购物
					    html = $(obj).contents().find("#div_"+dayIndex+"_gw").html() + '<div class="row"><div class="form-group"><div class="col-sm-9"><span>' +resComment['resname']+'</span>'+
								'<input name="body.daylist['+dayIndex+'].reslist['+index+'].resname" value="'+resComment['resname']+'" type="hidden"/>'+
								'<input name="body.daylist['+dayIndex+'].reslist['+index+'].resprop" value="prop" type="hidden"/>'+
								'<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].ccname" value="人头消费" type="hidden"/>'+
								'<input name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].ccno" value="'+resComment['cclist'][0].fsCcno+'" type="hidden"/>';
								 html += '<span><!-- 选项价格 --></span><input type="hidden" class="price" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].price" value="'+resComment['cclist'][0].price+'"/>'+
									 	 '<span><!-- 资源大类 --></span><input type="hidden" name="body.daylist['+dayIndex+'].reslist['+index+'].restype" value="gw"/>' +
									 	 '<span><!-- 选项类型 --></span><input type="hidden" name="body.daylist['+dayIndex+'].reslist['+index+'].cclist[0].cctype" value="0"/>' +
										 '<span><!-- 资源编号 --></span><input type="hidden" class="shopid" name="body.daylist['+dayIndex+'].reslist['+index+'].resno" value="'+resComment['resno']+'"/></div></div>';
						if (data != '') {
								$(obj).contents().find("#div_"+dayIndex+"_gw").html(html);
								$(obj).contents().find("#day"+dayIndex+"_resIndex").attr("value", parseInt(index)+1);
						}
					}
				});
			});
		}
	});
}

function getContent(obj, index){
	var content = $(obj).contents().find("#myTabContent").html();
	content += '<div class="tab-pane fade" id="day'+index+'">'+
	'<input type="hidden" class="fiDays" name="body.daylist['+index+'].dayflag" value="'+index+'"/>'+
	'<input type="hidden" class="reslistIndex" id="day'+index+'_resIndex" value="0"/>'+
	'<div class="row" style="margin-top: 10px;">'+
		'<div class="col-sm-12" style="padding: 10 0 0 0px;">'+
			'<div class="row">'+
				'<div class="form-group">'+
					'<label class="col-sm-4 pull-left" for="fsRegions">&nbsp;&nbsp;<span class="span_mp"></span></label>'+
					'<label class="col-sm-2 control-label no-padding-right" for="fsRegions">门票选择</label>'+
					'<div class="col-sm-3">'+
						'<select class="select_ticket form-control"></select>'+
					'</div>'+
					'<div class="col-sm-1">'+
						'<button type="button" class="btn addTicketBtn btn-success pull-right" onclick="javascript:addTicket(this);">添加</button>'+
					'</div>'+
					'<div class="col-sm-1">'+
						'<button type="button" class="btn rmTicketBtn btn-success pull-left" onclick="javascript:rmTicket(this);">删除</button>'+
					'</div>'+
					'<div class="row">'+
						'<div class="form-group">'+
							'<div id="div_'+index+'_mp" class="col-md-offset-1 col-sm-8"></div>'+
						'</div>'+
					'</div>'+
				'</div>'+
			'</div>'+
			'<div class="row">'+
				'<div class="form-group">'+
					'<label class="col-sm-4 pull-left" for="fsRegions">&nbsp;&nbsp;<span class="span_ct"></span></label>'+
					'<label class="col-sm-2 control-label no-padding-right" for="fsRegions">餐厅选择</label>'+
					'<div class="col-sm-3">'+
						'<select class="select_restaurant form-control"></select>'+
					'</div>'+
					'<div class="col-sm-1">'+
						'<button type="button" class="btn btn-success pull-right" onclick="javascript:addRestaurant(this);">添加</button>'+
					'</div>'+
					'<div class="col-sm-1">'+
						'<button type="button" class="btn btn-success pull-left" onclick="javascript:rmRestaurant(this);">删除</button>'+
					'</div>'+
					'<div class="row">'+
						'<div class="form-group">'+
							'<div id="div_'+index+'_ct" class="col-md-offset-1 col-sm-8"></div>'+
						'</div>'+
					'</div>'+
				'</div>'+
			'</div>'+
			'<div class="row">'+
				'<div class="form-group">'+
					'<label class="col-sm-4 pull-left" for="fsRegions">&nbsp;&nbsp;<span class="span_yl"></span></label>'+
					'<label class="col-sm-2 control-label no-padding-right" for="fsRegions">娱乐项目选择</label>'+
					'<div class="col-sm-3">'+
						'<select class="select_entertainment form-control"></select>'+
					'</div>'+
					'<div class="col-sm-1">'+
						'<button type="button" class="btn btn-success pull-right" onclick="javascript:addEntertainment(this);">添加</button>'+
					'</div>'+
					'<div class="col-sm-1">'+
						'<button type="button" class="btn btn-success pull-left" onclick="javascript:rmEntertainment(this);">删除</button>'+
					'</div>'+
					'<div class="row">'+
						'<div class="form-group">'+
							'<div id="div_'+index+'_yl" class="col-md-offset-1 col-sm-8"></div>'+
						'</div>'+
					'</div>'+
				'</div>'+
			'</div>'+
			'<div class="row">'+
				'<div class="form-group">'+
					'<label class="col-sm-4 pull-left" for="fsRegions">&nbsp;&nbsp;<span class="span_bg"></span></label>'+
					'<label class="col-sm-2 control-label no-padding-right" for="fsRegions">酒店标准</label>'+
					'<div class="col-sm-3">'+
						'<select onchange="javascript:getAccomadation(this);" class="fsStarLvl form-control"></select>'+
					'</div>'+
				'</div>'+
			'</div>'+
			'<div class="row">'+
				'<div class="form-group">'+
					'<label class="col-sm-2 col-md-offset-4 control-label no-padding-right" for="fsRegions">酒店选择</label>'+
					'<div class="col-sm-3">'+
						'<select onchange="javascript:getRoom(this);" class="accomadationNo form-control"></select>'+
					'</div>'+
				'</div>'+
			'</div>'+
			'<div class="row">'+
				'<div class="form-group">'+
					'<label class="col-sm-2 col-md-offset-4 control-label no-padding-right" for="fsRegions">房型选择</label>'+
					'<div class="col-sm-3">'+
						'<select class="room form-control"></select>'+
					'</div>'+
					'<div class="col-sm-1">'+
						'<button type="button" class="btn btn-success pull-right" onclick="javascript:addRoom(this);">添加</button>'+
					'</div>'+
					'<div class="col-sm-1">'+
						'<button type="button" class="btn btn-success pull-left" onclick="javascript:rmRoom(this);">删除</button>'+
					'</div>'+
					'<div class="row">'+
						'<div class="form-group">'+
							'<div id="div_'+index+'_bg" class="col-md-offset-1 col-sm-8"></div>'+
						'</div>'+
					'</div>'+
				'</div>'+
			'</div>'+
			'<div class="row">'+
				'<div class="form-group">'+
					'<label class="col-sm-4 pull-left" for="fsRegions">&nbsp;&nbsp;<span class="span_gw"></span></label>'+
					'<label class="col-sm-2 control-label no-padding-right" for="fsRegions">购物店选择</label>'+
					'<div class="col-sm-3">'+
						'<select class="shop form-control"></select>'+
					'</div>'+
					'<div class="col-sm-1">'+
						'<button type="button" class="btn btn-success pull-right" onclick="javascript:addShop(this);">添加</button>'+
					'</div>'+
					'<div class="col-sm-1">'+
						'<button type="button" class="btn btn-success pull-left" onclick="javascript:rmShop(this);">删除</button>'+
					'</div>'+
					'<div class="row">'+
						'<div class="form-group">'+
							'<div id="div_'+index+'_gw" class="col-md-offset-1 col-sm-8"></div>'+
						'</div>'+
					'</div>'+
				'</div>'+
			'</div>'+
		'</div>'+
	'</div>'+
'</div>';
	$(obj).contents().find("#myTabContent").html(content);
}

//获取酒店标准列表
function getFsStarLvl(obj){
	$.ajax({
		type: "GET",
		traditional: true,
		url: "/dict/selectDict.htm",
		data: "dict.fsParentno=bgjb",
		dataType: "json",
		success: function(data){
			html = '<option value="">'+'--请选择--' + '</option>'; 
			$.each(data, function(commentIndex, comment){
				html += '<option value=' + comment['fsDictno'] + '>' + comment['fsDictname'] + '</option>';
			});
			$(obj).contents().find(".fsStarLvl").html(html);
		}
	});
}


$("#addModal", parent.document).on("hidden.bs.modal", function() {
    $(this).removeData("bs.modal");
    $("#grid-table").trigger("reloadGrid");
});

$("#editModal", parent.document).on("hidden.bs.modal", function() {
    $(this).removeData("bs.modal");
    $("#grid-table").trigger("reloadGrid");
});

$("#delModal").on("hidden.bs.modal", function() {
    $(this).removeData("bs.modal");
    $("#grid-table").trigger("reloadGrid");
});

jQuery(function($) {
	
	var localsel = $("#selectCity", "#queryfield").localCity({
		provurl : "/pub/findcity.htm",
		cityurl : "/pub/findcity.htm",
		disturl : "/pub/findcity.htm",
		callback : localcallback
	});
	
//	jqGrid form提交
	$("#submit").click(function() {
		$("#collapseOne").collapse('hide');
		// $("#collapseTwo").collapse('show');
		var postData = $("#grid-table").jqGrid("getGridParam", "postData");
		postData["orderCustom.fsNo"] = $("#queryfield").find("#fsNo").val();
		postData["orderCustom.fsName"] = $("#queryfield").find("#fsName").val();
		postData["orderCustom.fiDays"] = $("#queryfield").find("#fiDays").val();
		postData["orderCustom.fiStat"] = $("#queryfield").find("#fiStat").val();
		$("#grid-table").jqGrid("setGridParam", {
			postData : postData
		}).trigger("reloadGrid");
	})
	
//	城市选择器
	function localcallback(index, key, value, fullkey, fullname) {
		$("#regionname", "#queryfield").val(fullname);
		$("#regionno", "#queryfield").val(key);
		if (index == 3) {
			$("#selectCity", "#queryfield").hide();
		}
	}

	$("#regionname", "#queryfield").click(function() {
		$("#selectCity", "#queryfield").show();
	});
	
//	重置
	$("#reset", "#queryfield").click(function() {
		$("#selectCity", "#queryfield").hide();
		$("#regionno", "#queryfield").val(null);
	});

	//	jqgrid
	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";

	// 定义按钮列
	actFormatter = function(cellvalue, options, rawObject) {

	var editBtn = '<div title="" class="ui-pg-div ui-inline-edit" id="editButton" style="display: block; cursor: pointer; float: left;" onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" onclick="editOrderlist('
			+ options.rowId
			+ ');" data-original-title="订单批次修改"><span class="ui-icon ui-icon-pencil"></span></div>';

	var deleteBtn = '<div title="" class="ui-pg-div ui-inline-edit" id="deleteButton" style="display: block; cursor: pointer; float: left;" onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" onclick="deleteOrderlist('
			+ options.rowId
			+ ');" data-original-title="订单批次删除"><span class="ui-icon ace-icon fa fa-trash-o red"></span></div>';
	return editBtn + deleteBtn;
	};

	// resize to fit page size
	$(window).on('resize.jqGrid', function() {
		$(grid_selector).jqGrid('setGridWidth', $(".page-content").width());
	})
	// resize on sidebar collapse/expand
	var parent_column = $(grid_selector).closest('[class*="col-"]');
	$(document).on(
			'settings.ace.jqGrid',
			function(ev, event_name, collapsed) {
				if (event_name === 'sidebar_collapsed'
						|| event_name === 'main_container_fixed') {
					// setTimeout is for webkit only to give time for DOM
					// changes and then redraw!!!
					setTimeout(function() {
						$(grid_selector).jqGrid('setGridWidth',
								parent_column.width());
					}, 0);
				}
			});

	var items = {
		'0' : '待审核',
		'1' : '已审核待确认',
		'-1' : '审核未通过',
		'2' : '客户已确认',
		'32' : '已入账结算'
	};
	var s = '';
	for (k in items)
		s += ';' + k + ":" + items[k];
	s = s.substring(1);
	//线路类型
	var fiType = {
			'1' : '团队客户',
			'2' : '散客客户'
		};
	var s1 = '';
	for (k in fiType)
			s1 += ';' + k + ":" + fiType[k];
	s1 = s1.substring(1);
	jQuery(grid_selector).jqGrid(
			{
				url : "/orderCustom/findOrderCustom.htm",
				datatype : "json",
				mtype : 'POST',
				height : 400,
				colNames : ['操作', '序号', '订单ID','订单名称', '批次顺序号', '创建时间', '批次类型', '联系人', '联系电话',
				            '总人数', '老人数', '成年人数', '儿童数', '附言', '预估金额小计', '状态','订单线路','发团日期'],
				colModel : [ {
					name : 'myac',
					index : '',
					width : 70,
					fixed : true,
					sortable : false,
					resize : false,
					formatter : actFormatter
				}, {
					name : 'fiId',
					index : 'fiId',
					width : 85,
					sorttype : "int",
					hidden : true
				}, {
					name : 'fsOrderId',
					index : 'fsOrderId',
					width : 100,
					editable : true,
					sorttype : "char",
					hidden : true
				}, {
					name : 'fsName',
					index : 'fsName',
					width : 100,
					editable : true,
					sorttype : "char",
					hidden : true
				}, {
					name : 'fiSeq',
					index : 'fiSeq',
					width : 100,
					editable : true,
					sorttype : "char"
				}, {
					name : 'ftCreatdate',
					index : 'ftCreatdate',
					width : 50,
					editable : true,
					sorttype : "date",
					formatter : function(value){
						var timestamp = "";
						if(value != ''){//rData[7]表示日期列
							timestamp = (new Date(parseFloat(value))).format("yyyy/MM/dd");
						}
						return timestamp;
					}
				}, {
					name : 'fiType',
					index : 'fiType',
					width : 80,
					sortable : true,
					editable : true,
					edittype : 'select',
					editoptions : {
						value : s
					},
					formatter : function(v, opt, rec) {
						return fiType[v];
					},
					unformat : function(v) {
						for (k in fiType)
							if (fiType[k] == v)
								return k;
						return '1';
					}
				}, {
					name : 'fsContactname',
					index : 'fsContactname',
					width : 100,
					editable : true,
					sorttype : "int",
					hidden : true
				}, {
					name : 'fsContacttel',
					index : 'fsContacttel',
					width : 100,
					editable : true,
					sorttype : "int",
					hidden : true
				}, {
					name : 'fiTotal',
					index : 'fiTotal',
					width : 45,
					editable : true,
					sorttype : "int"
				}, {
					name : 'fiOlder',
					index : 'fiOlder',
					width : 100,
					editable : true,
					sorttype : "int",
					hidden : true
				}, {
					name : 'fiAdult',
					index : 'fiAdult',
					width : 45,
					sortable : true,
					sorttype : "char"
				}, {
					name : 'fiChildren',
					index : 'fiChildren',
					width : 50,
					editable : true,
					sorttype : "int"
				}, {
					name : 'fsPostscript',
					index : 'fsPostscript',
					width : 50,
					editable : true,
					sorttype : "int"
				}, {
					name : 'fdAmt',
					index : 'fdAmt',
					width : 60,
					editable : true,
					sorttype : "int"
				}, {
					name : 'fiStat',
					index : 'fiStat',
					width : 45,
					sortable : true,
					editable : true,
					edittype : 'select',
					editoptions : {
						value : s
					},
					formatter : function(v, opt, rec) {
						return items[v];
					},
					unformat : function(v) {
						for (k in items)
							if (items[k] == v)
								return k;
						return '1';
					}
				}, {
					name : 'fiGenindex',
					index : 'fiGenindex',
					width : 100,
					editable : true,
					sorttype : "char",
					hidden : true
				},{
					name : 'ftStartdate',
					index : 'ftStartdate',
					width : 50,
					editable : true,
					sorttype : "date",
					hidden : true,
					formatter : function(value){
						var timestamp = "";
						if(value != ''){//rData[7]表示日期列
							timestamp = (new Date(parseFloat(value))).format("yyyy/MM/dd");
						}
						return timestamp;
					}
				}],

				viewrecords : true,
				rowNum : 10,
				rowList : [ 10, 20, 30 ],
				pager : pager_selector,
				altRows : true,

//				multiselect : true,
//				multiboxonly : true,
//				multipleSearch : true,

				loadComplete : function() {
					var table = this;
					setTimeout(function() {

						updateActionIcons(table);
						updatePagerIcons(table);
						enableTooltips(table);
					}, 0);
				},

				editurl : "/orderCustom/save.htm",
				shrinkToFit : true,
				autowidth : true

			/**
			 * , grouping:true, groupingView : { groupField : ['name'],
			 * groupDataSorted : true, plusicon : 'fa fa-chevron-down
			 * bigger-110', minusicon : 'fa fa-chevron-up bigger-110' },
			 * caption: "Grouping"
			 */

			});
	$(window).triggerHandler('resize.jqGrid');// trigger window resize to make
	// the grid get the correct size

	// navButtons
	jQuery(grid_selector).jqGrid(
			'navGrid',
			pager_selector,
			{ // navbar options
				edit : false,
				editicon : 'ace-icon fa fa-pencil blue',
				add : false,
				addicon : 'ace-icon fa fa-plus-circle purple',
				del : false,
				delicon : 'ace-icon fa fa-trash-o red',
				search : false,
				searchicon : 'ace-icon fa fa-search orange',
				refresh : true,
				refreshicon : 'ace-icon fa fa-refresh green',
				view : false,
				viewicon : 'ace-icon fa fa-search-plus grey'
			},
			{
				// delete record form
				recreateForm : true,
				beforeShowForm : function(e) {
					var form = $(e[0]);
					if (form.data('styled'))
						return false;

					form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar')
							.wrapInner('<div class="widget-header" />')
					style_delete_form(form);

					form.data('styled', true);
				},
				onClick : function(e) {
					alert(1);
				}
			},
			{
				// search form
				recreateForm : true,
				afterShowSearch : function(e) {
					var form = $(e[0]);
					form.closest('.ui-jqdialog').find('.ui-jqdialog-title')
							.wrap('<div class="widget-header" />')
					style_search_filters(form);
				},
				afterRedraw : function() {
					style_search_filters($(this));
				},
				multipleSearch : true
			/**
			 * multipleGroup:true, showQuery: true
			 */
			});

	/*jQuery(grid_selector).jqGrid('navGrid', pager_selector).jqGrid(
			'navButtonAdd', pager_selector, {
				caption : "",
				buttonicon : "ace-icon fa fa-plus-circle purple",
				onClickButton : function() {
					var frameSrc = "/jsp/orderCustom/add.jsp";
			        $("#addIframe").attr("src", frameSrc);
			        $('#addModal').modal({ show: true, backdrop: 'static' });
				},
				position : "first",
				title : "新增记录",
				cursor : "pointer"
			});*/

	function style_delete_form(form) {
		var buttons = form.next().find('.EditButton .fm-button');
		buttons.addClass('btn btn-sm btn-white btn-round').find(
				'[class*="-icon"]').hide();// ui-icon, s-icon
		buttons.eq(0).addClass('btn-danger').prepend(
				'<i class="ace-icon fa fa-trash-o"></i>');
		buttons.eq(1).addClass('btn-default').prepend(
				'<i class="ace-icon fa fa-times"></i>')
	}

	function style_search_filters(form) {
		form.find('.delete-rule').val('X');
		form.find('.add-rule').addClass('btn btn-xs btn-primary');
		form.find('.add-group').addClass('btn btn-xs btn-success');
		form.find('.delete-group').addClass('btn btn-xs btn-danger');
	}

	function beforeDeleteCallback(e) {
		var form = $(e[0]);
		if (form.data('styled'))
			return false;

		form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner(
				'<div class="widget-header" />')
		style_delete_form(form);

		form.data('styled', true);
	}

	// unlike navButtons icons, action icons in rows seem to be hard-coded
	// you can change them like this in here if you want
	function updateActionIcons(table) {
		/**
		 * var replacement = { 'ui-ace-icon fa fa-pencil' : 'ace-icon fa
		 * fa-pencil blue', 'ui-ace-icon fa fa-trash-o' : 'ace-icon fa
		 * fa-trash-o red', 'ui-icon-disk' : 'ace-icon fa fa-check green',
		 * 'ui-icon-cancel' : 'ace-icon fa fa-times red' };
		 * $(table).find('.ui-pg-div span.ui-icon').each(function(){ var icon =
		 * $(this); var $class = $.trim(icon.attr('class').replace('ui-icon',
		 * '')); if($class in replacement) icon.attr('class', 'ui-icon
		 * '+replacement[$class]); })
		 */
	}

	// replace icons with FontAwesome icons like above
	function updatePagerIcons(table) {
		var replacement = {
			'ui-icon-seek-first' : 'ace-icon fa fa-angle-double-left bigger-140',
			'ui-icon-seek-prev' : 'ace-icon fa fa-angle-left bigger-140',
			'ui-icon-seek-next' : 'ace-icon fa fa-angle-right bigger-140',
			'ui-icon-seek-end' : 'ace-icon fa fa-angle-double-right bigger-140'
		};
		$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon')
				.each(
						function() {
							var icon = $(this);
							var $class = $.trim(icon.attr('class').replace(
									'ui-icon', ''));

							if ($class in replacement)
								icon.attr('class', 'ui-icon '
										+ replacement[$class]);
						})
	}

	function enableTooltips(table) {
		$('.navtable .ui-pg-button').tooltip({
			container : 'body'
		});
		$(table).find('.ui-pg-div').tooltip({
			container : 'body'
		});
	}

	$(document).on('ajaxloadstart', function(e) {
		$(grid_selector).jqGrid('GridUnload');
		$('.ui-jqdialog').remove();
	});
	
});
