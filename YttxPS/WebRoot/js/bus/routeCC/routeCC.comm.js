/**
 * 获取酒店房型列表
 */
function getRoom(fsAccomno){
	$.ajax({
		type: "GET",
		traditional: true,
		url: "/room/selectRoom.htm",
		data: "room.fsAccomno=" + fsAccomno,
		dataType: "json",
		success: function(data){
			var html = ''; 
			$.each(data, function(commentIndex, comment){
				html += '<option value=' + comment['fsRoomno'] + '>' + comment['fsName'] + '</option>';
			});
			$("#room").html(html);
		}
	});
}

/**
 * 获取酒店列表
 */
function getAccomadation(accomadation){
	var scenic = [];
	var req = {};
	$("input[name='scenicGen']").each(function(){
		scenic.push($.trim($(this).val()));
	});
	
	req["accomadation.starlvl"] = accomadation.starlvl;
	req["scenicNo"] = scenic;
	$.ajax({
		type: "GET",
		traditional: true,
		url: "/accomadation/selectAccomadation.htm",
		data: req,
		dataType: "json",
		success: function(data){
			var html = ''; 
			$.each(data, function(commentIndex, comment){
				html += '<option value=' + comment['no'] + '>' + comment['name'] + '</option>';
			});
			$("#accomadationNo").html(html);
			if(accomadation.no != undefined && accomadation.no != null) {
				$("#accomadationNo").find("option[value='" + accomadation.no + "']").attr("selected", "selected");
			}
			getRoom($("#accomadationNo").val());
		}
	});
}

/**
 * 根据行程日获取所有的RouteCC
 */
function getAllRouteCC(day, fiGenindex, fsRouteno) {
	//清空
	$("#div_ticket").html("");
	$("#div_shop").html("");
	$("#div_restaurant").html("");
	$("#div_entertainment").html("");
	$("#div_room").html("");
	$("#room").find("option").remove();
	$("#room").val("");
	$("#accomadationNo").find("option").remove();
	$("#accomadationNo").val("");
	$("#fsStarLvl").find("option[value='--']").attr("selected", "selected");
	
	//门票
	$.ajax({
		type: "GET",
		traditional: true,
		url: "/routeArrange/findRouteCCType.htm",
		data: "fsRouteno=" + fsRouteno + "&fiDayflag=" + day + "&fsRestype=mp",
		dataType: "json",
		success: function(data){
			$.each(data, function(typeIdx, type){
				$.ajax({
					type: "POST",
					traditional: true,
					data: "fsRestype=mp",
					url: "/rescc/findResCC.htm",
					dataType: "json",
					success: function(data){
						var html = $("#div_ticket").html();
						html += '<label for="form-field-select-2" class="ticket-label-' + type.fsResno + '">' + type.fsName + '</label>';
						html += '<select class="form-control ticket ticket-select-' + type.fsResno + '" name="' + type.fsResno + '" id="ticket_' + type.fsResno + '" multiple="multiple">';
						$.each(data.rows, function(i, e){
							html += '<option value="' + e.fsCcno + '">' + e.fsCcname + '</option>';
						});
						$("#div_ticket").html(html);
						$.ajax({
							type: "POST",
							traditional: true,
							data: "fsRouteno=" + fsRouteno + "&fiDayflag=" + day + "&fsRestype=mp&fsResno=" + type.fsResno,
							url: "/routeArrange/findRouteCC.htm",
							dataType: "json",
							success: function(data) {
								var select = $("#ticket_" + type.fsResno);
								$.each(data, function(ccIdx, cc){
									$(select).find("option[value='" + cc.fsCcno + "']").attr("selected", "selected");
								});
							}
						});
					}
				});
			});
		}
	});
	
	//餐厅
	$.ajax({
		type: "GET",
		traditional: true,
		url: "/routeArrange/findRouteCCType.htm",
		data: "fsRouteno=" + fsRouteno + "&fiDayflag=" + day + "&fsRestype=ct",
		dataType: "json",
		success: function(data){
			$.each(data, function(typeIdx, type){
				$.ajax({
					type: "POST",
					traditional: true,
					data: "fsRestype=ct",
					url: "/rescc/findResCC.htm",
					dataType: "json",
					success: function(data){
						var html = $("#div_restaurant").html();
						html += '<label for="form-field-select-2" class="restaurant-label-' + type.fsResno + '">' + type.fsName + '</label>';
						html += '<select class="form-control restaurant restaurant-select-' + type.fsResno + '" name="' + type.fsResno + '" id="restaurant_' + type.fsResno + '" multiple="multiple">';
						$.each(data.rows, function(i, e){
							html += '<option value="' + e.fsCcno + '">' + e.fsCcname + '</option>';
						});
						$("#div_restaurant").html(html);
						$.ajax({
							type: "POST",
							traditional: true,
							data: "fsRouteno=" + fsRouteno + "&fiDayflag=" + day + "&fsRestype=ct&fsResno=" + type.fsResno,
							url: "/routeArrange/findRouteCC.htm",
							dataType: "json",
							success: function(data) {
								var select = $("#restaurant_" + type.fsResno);
								$.each(data, function(ccIdx, cc){
									$(select).find("option[value='" + cc.fsCcno + "']").attr("selected", "selected");
								});
							}
						});
					}
				});
			});
		}
	});
	
	//娱乐项目
	$.ajax({
		type: "GET",
		traditional: true,
		url: "/routeArrange/findRouteCCType.htm",
		data: "fsRouteno=" + fsRouteno + "&fiDayflag=" + day + "&fsRestype=yl",
		dataType: "json",
		success: function(data){
			$.each(data, function(typeIdx, type){
				$.ajax({
					type: "POST",
					traditional: true,
					data: "fsRestype=yl",
					url: "/rescc/findResCC.htm",
					dataType: "json",
					success: function(data){
						var html = $("#div_entertainment").html();
						html += '<label for="form-field-select-2" class="entertainment-label-' + type.fsResno + '">' + type.fsName + '</label>';
						html += '<select class="form-control entertainment entertainment-select-' + type.fsResno + '" name="' + type.fsResno + '" id="entertainment_' + type.fsResno + '" multiple="multiple">';
						$.each(data.rows, function(i, e){
							html += '<option value="' + e.fsCcno + '">' + e.fsCcname + '</option>';
						});
						$("#div_entertainment").html(html);
						$.ajax({
							type: "POST",
							traditional: true,
							data: "fsRouteno=" + fsRouteno + "&fiDayflag=" + day + "&fsRestype=yl&fsResno=" + type.fsResno,
							url: "/routeArrange/findRouteCC.htm",
							dataType: "json",
							success: function(data) {
								var select = $("#entertainment_" + type.fsResno);
								$.each(data, function(ccIdx, cc){
									$(select).find("option[value='" + cc.fsCcno + "']").attr("selected", "selected");
								});
							}
						});
					}
				});
			});
		}
	});
	
	//房型
	$.ajax({
		type: "GET",
		traditional: true,
		url: "/routeArrange/findRouteCCType.htm",
		data: "fsRouteno=" + fsRouteno + "&fiDayflag=" + day + "&fsRestype=fx",
		dataType: "json",
		success: function(data){
			$.each(data, function(typeIdx, type){
				$.ajax({
					type: "POST",
					traditional: true,
					data: "fsRestype=fx",
					url: "/rescc/findResCC.htm",
					dataType: "json",
					success: function(data){
						var html = $("#div_room").html();
						html += '<label for="form-field-select-2" class="room-label-' + type.fsResno + '">' + type.fsName + '</label>';
						html += '<select class="form-control room room-select-' + type.fsResno + '" name="' + type.fsResno + '" id="room_' + type.fsResno + '" multiple="multiple">';
						$.each(data.rows, function(i, e){
							html += '<option value="' + e.fsCcno + '">' + e.fsCcname + '</option>';
						});
						$("#div_room").html(html);
						$.ajax({
							type: "POST",
							traditional: true,
							data: "fsRouteno=" + fsRouteno + "&fiDayflag=" + day + "&fsRestype=fx&fsResno=" + type.fsResno,
							url: "/routeArrange/findRouteCC.htm",
							dataType: "json",
							success: function(data) {
								var select = $("#room_" + type.fsResno);
								$.each(data, function(ccIdx, cc){
									$(select).find("option[value='" + cc.fsCcno + "']").attr("selected", "selected");
								});
								$.ajax({
									type: "POST",
									traditional: true,
									data: "id=" + type.fsResno,
									url: "/room/findRoomInfo.htm",
									dataType: "json",
									success: function(room){
										getRoom(room.data.fsAccomno);
										$.ajax({
											type: "POST",
											traditional: true,
											data: "id=" + room.data.fsAccomno,
											url: "/accomadation/findAccomadationInfo.htm",
											dataType: "json",
											success: function(accomadation) {
												getAccomadation(accomadation.data);
												$("#fsStarLvl").find("option[value='" + accomadation.data.starlvl + "']").attr("selected", "selected");
												var roomName = $(".room-label-" + room.data.fsRoomno).text();
												var accName = accomadation.data.name;
												var lvl = $("#fsStarLvl").find("option[value='" + accomadation.data.starlvl + "']").text();
												$(".room-label-" + room.data.fsRoomno).text(lvl + " - " + accName + " - " + roomName);
											}
										});
									}
								});
							}
						});
					}
				});
			});
		}
	});
	
	//购物点
	$.ajax({
		type: "GET",
		traditional: true,
		url: "/routeArrange/findRouteCCType.htm",
		data: "fsRouteno=" + fsRouteno + "&fiDayflag=" + day + "&fsRestype=gw",
		dataType: "json",
		success: function(data){
			var html = $("#div_shop").html();
			$.each(data, function(typeIdx, type){
				
				html += '<input type="hidden" class="shopid" name="routecc[' + type.fsResno + '].fsResno" value="' + type.fsResno + '"/>'+
				'<input type="hidden" class="shop_' + type.fsResno + '" name="routecc[' + type.fsResno + '].fsRestype" value="gw"/>' + 
				'<label class="shop_' + type.fsResno + '">&nbsp;&nbsp;' + type.fsName + '：</label>' +
				'<input class="shop_'+ type.fsResno + '" name="routecc[' + type.fsResno + '].fsCcno" value="000021" type="hidden"/>' +
				'<span class="shop_' + type.fsResno + '">&nbsp;人头消费&nbsp;&nbsp;<br></span>';
			});
			$("#div_shop").html(html);
		}
	});
}

/**
 * 重置iframe高度
 * @param type
 */
function resetIframeHeight(type){
	var height = $(window.parent.document).find("#addIframe").attr("height");
	height = height.substring(0,height.length - 2);
	if ("add" == type) {
		height = (parseInt(height) + 21);
	} else {
		height = (parseInt(height) - 21);
	}
	$(window.parent.document).find("#addIframe").attr("height", height + "px");
}

function setContentDisable(isDisable) {
	if(isDisable) {
		$("select:not(.query-condition)").attr("disabled", "disabled");
		$("input").attr("readonly", "readonly");
		$("button[id!='close']").attr("disabled", "disabled");
	} else {
		$("select:not(.query-condition)").attr("disabled", false);
		$("input").attr("readonly", false);
		$("button[id!='close']").attr("disabled", false);
		
		$("#div_ticket").html("");
		$("#div_restaurant").html("");
		$("#div_entertainment").html("");
		$("#div_room").html("");
		$("#div_shop").html("");
	}
}

/**
 * 初始化字典列表
 */
function getDict(parentno, selectId){
	$.ajax({
		type: "GET",
		traditional: true,
		url: "/dict/selectDict.htm",
		data: "dict.fsParentno=" + parentno,
		dataType: "json",
		success: function(data){
			var html = '<option value="">' + '-- 请选择 --' + '</option>'; 
			$.each(data, function(commentIndex, comment){
				html += '<option value=' + comment['fsDictno'] + '>' + comment['fsDictname'] + '</option>';
			});
			$("#" + selectId).html(html);
		}
	});
}

//增加房型
$("#addRoomBtn").click(function(){
	var html = $("#div_room").html();
	var val = $("#room").val();
	var text = $("#fsStarLvl").find("option:selected").text() + " - " + 
			$("#accomadationNo").find("option:selected").text() + " - " + 
			$("#room").find("option:selected").text();
	var flag = true;
	if($(".room-label-" + val) != null && $(".room-label-" + val).length != 0) {
		flag = false;
	}
	
	if (flag) {
		$.ajax({
			type: "POST",
			traditional: true,
			data: "fsRestype=fx",
			url: "/rescc/findResCC.htm",
			dataType: "json",
			success: function(data){
				html += '<label for="form-field-select-2" class="room-label-' + val + '">' + text + '</label>';
				html += '<select class="form-control room room-select-' + val + '" name="' + val + '" id="rooms_' + val + '" multiple="multiple">';
				$.each(data.rows, function(i, e){
					html += '<option value="' + e.fsCcno + '">' + e.fsCcname + '</option>';
				});
				
				$("#div_room").html(html);
			}
		});
	}
});

//删除房型
$("#rmRoomBtn").click(function(){
	var val = $("#room").val();
	$(".room-label-" + val).remove();
	$(".room-select-" + val).remove();
});

//酒店标准变更
$("#fsStarLvl").change(function(){
	getAccomadation({"starlvl": $("#fsStarLvl").val()});
});

//酒店变更
$("#accomadationNo").change(function(){
	getRoom($("#accomadationNo").val());
});