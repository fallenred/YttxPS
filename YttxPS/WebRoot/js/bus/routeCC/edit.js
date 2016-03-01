jQuery(function($) {
	$("#message").hide();
	
	var localsel = $("#selectCity", "#addform").localCity({
		
		provurl : "/pub/findcity.htm",
		cityurl : "/pub/findcity.htm",
		disturl : "/pub/findcity.htm",
		callback : localcallback
	});
	
	var fiGenindex = $.getUrlParam('fiGenindex');   //线路统称序号
    var fsRouteno = $.getUrlParam('fsRouteno');   //线路序号
	
	//酒店类型下拉列表
	getDict('bg', 'fsStarLvl');
	
	//获取线路统称列表
	$.ajax({
        type: "GET",
        url: "/gen/selectGen.htm",
        data: '',
        dataType: "json",
        success: function(data){
        		var html = ''; 
        		$.each(data, function(commentIndex, comment){
        			html += '<option value=' + comment['fiIndex'] + '>' + comment['fsName'] + '</option>';
        		});
        		$("#fiGenindex").html(html);
        		getRouteArrange();   //获取线路配置列表
        		getSceniceGen();   //获取线路景区
        		getScenice();   //获取景区列表
        		$("#ticket").html('');
        }
    });
	
	/**
	 * 生成日程下拉列表
	 */
	function getDays(num){
		var html="";
		for (var i = 0; i < num ; i++) {
			html += '<option value=' + i + '>第' + (parseInt(i) + 1) + '天</option>';
		}
		$("#fiDays").html(html);
		getAllRouteCC($("#fiDays").val(), fiGenindex, fsRouteno);
	}
	
	//
	$("#fiDays").change(function(){
		getAllRouteCC($("#fiDays").val(), fiGenindex, fsRouteno);
	});
	
	/**
	 * 获取线路配置列表
	 */
	function getRouteArrange(){
		$.ajax({
			type: "GET",
			url: "/routeArrange/selectRouteArrange.htm",
			data: "arrange.fiGenindex=" + fiGenindex,
			dataType: "json",
			success: function(data){
				var html = ''; 
				$.each(data, function(commentIndex, comment){
					html += '<option value=' + comment['fsId'] + '>' + comment['fsName'] + '</option>';
				});
				$("#fsResno").html(html);
			}
		});
	}
	
	/**
	 * 获取景区列表
	 */
	function getScenice(){
		$.ajax({
			type: "GET",
			url: "/scenicGen/selectScenicGen.htm",
			data: "scenicGen.fiGenindex=" + fiGenindex,
			dataType: "json",
			success: function(data){
				var html = ''; 
				$.each(data, function(commentIndex, comment){
					html += '<option value=' + comment['fsScenicno'] + '>' + comment['fsScenicname'] + '</option>';
				});
				$("#scenic").html(html);
			}
		});
	}
	
	/**
	 * 获取线路景区
	 */
	function getSceniceGen(){
		var num = '';
		$.ajax({
			type: "GET",
			url: "/scenicGen/selectScenicGen.htm",
			data: "scenicGen.fiGenindex=" + fiGenindex,
			dataType: "json",
			success: function(data){
				var html = '线路景区：'; 
				$.each(data, function(commentIndex, comment){
					html += '<input type="hidden" name="scenicGen" value="' + comment['fsScenicno'] + '"/>' + '&nbsp;&nbsp;<label>' + comment['fsScenicname'] + '</label>';
					if(commentIndex == 0)
						num = comment['fiDays'];
				});
				$("#div_scenics").html(html);
				getTicket();   //获取景区票列表
				getDays(num);   //生成日程下拉列表
				getShop();   //获取购物店列表
				getRestaurant();   //获取餐厅
				getEntertainment();   //获取娱乐项目
			}
		});
	}
	
	/**
	 * 获取景区票列表
	 */
	function getTicket(){
		var scenic = '';
		$("input[name='scenicGen']").each(function(){
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
					html += '<option value=' + comment['fsNo'] + '>' + comment['fsScenicname'] + '--' + comment['fsName'] + '</option>';
				});
				$("#ticket").html(html);
			}
		});
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
				var html = '<option value="--">' + '-- 请选择 --' + '</option>'; 
				$.each(data, function(commentIndex, comment){
					html += '<option value=' + comment['fsDictno'] + '>' + comment['fsDictname'] + '</option>';
				});
				$("#"+selectId).html(html);
			}
		});
	}
	
	/**
	 * 获取酒店列表
	 */
	function getAccomadation(fsStarLvl){
		var scenic = [];
		var req = {};
		var scenicName = $("#scenic").find("option:selected").text();
		$("input[name='scenicGen']").each(function(){
			scenic.push($(this).val());
		});
		
		req["accomadation.starlvl"] = $("#fsStarLvl").val();
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
				getRoom($("#accomadationNo").val());
			}
		});
	}
	
	/**
	 * 获取酒店房型列表
	 */
	function getRoom(accomadationNo){
		$.ajax({
			type: "GET",
			traditional: true,
			url: "/room/selectRoom.htm",
			data: "room.fsAccomno=" + accomadationNo,
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
	
	//增加房型
	$("#addRoomBtn").click(function(){
		var html = $("#div_room").html();
		var val = $("#room").val();
		var text = $("#room").find("option:selected").text();
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
					
					resetIframeHeight("add");
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
		resetIframeHeight("sub");
	});
	
	/**
	 * 获取购物店列表
	 */
	function getShop(){
		var scenic = '';
		var scenicName = $("#scenic").find("option:selected").text();
		$("input[name='scenicGen']").each(function(){
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
					html += '<option value=' + comment['no'] + '>' + comment['name'] + '</option>';
				});
				$("#shop").html(html);
			}
		});
	}
	
	/**
	 * 获取餐厅列表
	 */
	function getRestaurant() {
		var scenic = [];
		var scenicName = $("#scenic").find("option:selected").text();
		$("input[name='scenicGen']").each(function(){
			scenic.push($(this).val());
		});
		
		$.ajax({
			type: "POST",
			traditional: true,
			url: "/restaurant/selectRestaurant.htm",
			data: {"scenicNo[]": scenic},
			dataType: "json",
			success: function(data){
				var html = ''; 
				$.each(data, function(commentIndex, comment){
					html += '<option value=' + comment['no'] + '>' + comment['name'] + '</option>';
				});
				$("#restaurant").html(html);
			}
		});
	}
	
	/**
	 * 获取娱乐项目列表
	 */
	function getEntertainment() {
		var scenic = [];
		var scenicName = $("#scenic").find("option:selected").text();
		$("input[name='scenicGen']").each(function(){
			scenic.push($(this).val());
		});
		
		$.ajax({
			type: "GET",
			traditional: true,
			url: "/entertainment/selectEntertainmentDynamic.htm",
			data: {"scenicNo": scenic},
			dataType: "json",
			success: function(data){
				var html = ''; 
				$.each(data, function(commentIndex, comment){
					html += '<option value=' + comment['fsNo'] + '>' + comment['fsName'] + '</option>';
				});
				$("#entertainment").html(html);
			}
		});
	}
	
	//增加娱乐项目
	$("#addEntertainmentBtn").click(function(){
		var html = $("#div_entertainment").html();
		var val = $("#entertainment").val();
		var text = $("#entertainment").find("option:selected").text();
		var flag = true;
		if($(".entertainment-label-" + val) != null && $(".entertainment-label-" + val).length != 0) {
			flag = false;
		}
		
		if (flag) {
			$.ajax({
				type: "POST",
				traditional: true,
				data: "fsRestype=yl",
				url: "/rescc/findResCC.htm",
				dataType: "json",
				success: function(data){
					html += '<label for="form-field-select-2" class="entertainment-label-' + val + '">' + text + '</label>';
					html += '<select class="form-control entertainment entertainment-select-' + val + '" name="' + val + '" id="entertainments_' + val + '" multiple="multiple">';
					$.each(data.rows, function(i, e){
						html += '<option value="' + e.fsCcno + '">' + e.fsCcname + '</option>';
					});
					
					resetIframeHeight("add");
					$("#div_entertainment").html(html);
				}
			});
		}
	});
	
	//删除娱乐项目
	$("#rmEntertainmentBtn").click(function(){
		var val = $("#entertainment").val();
		$(".entertainment-label-" + val).remove();
		$(".entertainment-select-" + val).remove();
		resetIframeHeight("sub");
	});
	
	//增加餐厅
	$("#addRestaurantBtn").click(function() {
		var html = $("#div_restaurant").html();
		var val = $("#restaurant").val();
		var text = $("restaurant").find("option:selected").text();
		var flag = true;
		
		if(flag) {
			$.ajax({
				type: "POST",
				traditional: true,
				data: "fsRestype=ct",
				url: "/rescc/findResCC.htm",
				dataType: "json",
				success: function(data) {
					html += '<label for="form-field-select-2" class="restaurant-label-' + val + '">' + text + '</label>';
					html += '<select class="form-control restaurant restaurant-select-' + val + '" name="' + val + '" id="restaurants_' + val + '" multiple="multiple">';
					$.each(data.rows, function(i, e){
						html += '<option value="' + e.fsCcno + '">' + e.fsCcname + '</option>';
					});
					
					resetIframeHeight("add");
					$("#div_restaurant").html(html);
				}
			});
		}
	});
	
	//删除餐厅
	$("#rmRestaurantBtn").click(function(){
		var val = $("#restaurant").val();
		$(".restaurant-label-" + val).remove();
		$(".restaurant-select-" + val).remove();
		resetIframeHeight("sub");
	});
	
	//线路变更
	$("#fiGenindex").change(function(){
		getRouteArrange();   //获取线路配置列表
		getSceniceGen();   //获取线路景区
		getShop();   //获取购物店列表
	});
	
	//酒店标准变更
	$("#fsStarLvl").change(function(){
		getAccomadation($("#fsStarLvl"));
	});
	
	//酒店变更
	$("#accomadationNo").change(function(){
		getRoom($("#accomadationNo").val());
	});
	
	//增加景区
	$("#addScenicBtn").click(function(){
		var html = $("#div_scenics").html();
		var val = $("#scenic").val();
		var text = $("#scenic").find("option:selected").text();
		var flag = true;
		$("input[name='scenicGen']").each(function(){
			if (val == $(this).val()) { 
				flag = false;
			} 
		});
		if (flag)
			html += '<input type="hidden" name="scenicGen" value="' + val + '"/>' + '&nbsp;&nbsp;<label>' + text + '</label>&nbsp;&nbsp;';
		$("#div_scenics").html(html);
		getTicket();
		getShop();
	});
	
	//删除景区
	$("#rmTransportBtn").click(function(){
		var html = '线路景区：';
		var val = $("#scenic").val();
		$("input[name='scenicGen']").each(function(){
			if (val != $(this).val()) { 
				html += '<input type="hidden" name="scenicGen" value="' + $(this).val() + '"/>' + '&nbsp;&nbsp;<label>' + $(this).next().text() + '</label>&nbsp;&nbsp;';
			} 
		});
		$("#div_scenics").html(html);
		getTicket();
		getShop();
	});
	
	//增加门票
	$("#addTicketBtn").click(function(){
		var html = $("#div_ticket").html();
		var val = $("#ticket").val();
		var text = $("#ticket").find("option:selected").text();
		var flag = true;
		if($(".ticket-label-" + val) != null && $(".ticket-label-" + val).length != 0) {
			flag = false;
		}
		
		if (flag) {
			//获取门票价格
			$.ajax({
				type: "POST",
				traditional: true,
				data: "fsRestype=mp",
				url: "/rescc/findResCC.htm",
				dataType: "json",
				success: function(data){
					html += '<label for="form-field-select-2" class="ticket-label-' + val + '">' + text + '</label>';
					html += '<select class="form-control ticket ticket-select-' + val + '" name="' + val + '" id="tickets_' + val + '" multiple="multiple">';
					$.each(data.rows, function(i, e){
						html += '<option value="' + e.fsCcno + '">' + e.fsCcname + '</option>';
					});
					
					resetIframeHeight("add");
					$("#div_ticket").html(html);
				}
			});
		}
	});
	
	//删除门票
	$("#rmTicketBtn").click(function(){
		var val = $("#ticket").val();
		$(".ticket-label-" + val).remove();
		$(".ticket-select-" + val).remove();
		resetIframeHeight("sub");
	});
	
	//增加购物店
	$("#addShopBtn").click(function(){
		//数组下标
		var index = 0;
		if($("#index").val() != null){
			index = $("#index").val();
		}
		var html = $("#div_shop").html();
		var val = $("#shop").val();
		var text = $("#shop").find("option:selected").text();
		var flag = true;
		$("input[class='shopid']").each(function(){
			if (val == $(this).val()) { 
				flag = false;
			}
		});
		if (flag) {
			$("#index").attr("value", parseInt(index)+1);
			html += '<input type="hidden" class="shopid" name="routecc[' + val + '].fsResno" value="' + val + '"/>'+
					'<input type="hidden" class="shop_' + val + '" name="routecc[' + val + '].fsRestype" value="gw"/>' + 
					'<label class="shop_' + val + '">&nbsp;&nbsp;' + text + '：</label>' +
					'<input class="shop_'+ val + '" name="routecc[' + val + '].fsCcno" value="000021" type="hidden"/>' +
					'<span class="shop_' + val + '">&nbsp;人头消费&nbsp;&nbsp;<br></span>';
			resetIframeHeight("add");
		}
		$("#div_shop").html(html);
	});
	
	//删除购物店
	$("#rmShopBtn").click(function(){
		var val = $("#shop").val();
		if($("#index").val() != null){
			index = $("#index").val();
		}
		$("input[class='shopid']").each(function(){
			if (val == $(this).val()) {
				$(this).remove();
				$(".shop_" + val).remove();
				$("#index").attr("value", parseInt(index) - 1);
				resetIframeHeight("sub");
			} 
		});
	});
	
	//城市选择器
	function localcallback(index, key, value, fullkey, fullname) {
		$("#regionname", "#addform").val(fullname);
		$("#regionno", "#addform").val(key);
		if (index == 3)
			$("#selectCity", "#addform").hide();
	}

	$("#regionname", "#addform").click(function() {
		$("#selectCity", "#addform").show();
	});

	//重置iframe高度
	function resetIframeHeight(type){
		var height = $(window.parent.document).find("#editIframe").attr("height");
		height = height.substring(0,height.length - 2);
		if ("add" == type) {
			height = (parseInt(height) + 21);
		} else {
			height = (parseInt(height) - 21);
		}
		$(window.parent.document).find("#editIframe").attr("height", height + "px");
	}

	//	重置
	$("#reset").on("click", function() {
			$("#selectCity").hide();
			$("#regionno").val(null);
			$("#message").hide();
			$("#message").text("");
	});
	
	// 关闭
	$("#close").on("click", function () {
			$("#editModal", parent.document).find(".close").click();
	});
	
//	提交
	$("#submit").on("click", function () {
		var index = 0;
		var route = {};
		var fsRouteno = $("#fsResno").val();
		var fiDayflag = $("#fiDays").val();
		
		//门票
		$(".ticket").each(function(idx, e){
			var ticketId = $(e).attr("name");
			var ticketCC  = $(e).val();
			if(ticketCC != null) {
				$.each(ticketCC, function(ccIdx, cc){
					route["routecc[" + index + "].fiDayflag"] = fiDayflag;
					route["routecc[" + index + "].fsCcno"] = cc;
					route["routecc[" + index + "].fsResno"] = ticketId;
					route["routecc[" + index + "].fsRestype"] = "mp";
					route["routecc[" + index + "].fsRouteno"] = fsRouteno;
					index ++;
				});
			}
		});
		
		//餐厅
		$(".restaurant").each(function(idx, e){
			var ticketId = $(e).attr("name");
			var ticketCC  = $(e).val();
			if(ticketCC != null) {
				$.each(ticketCC, function(ccIdx, cc){
					route["routecc[" + index + "].fiDayflag"] = fiDayflag;
					route["routecc[" + index + "].fsCcno"] = cc;
					route["routecc[" + index + "].fsResno"] = ticketId;
					route["routecc[" + index + "].fsRestype"] = "ct";
					route["routecc[" + index + "].fsRouteno"] = fsRouteno;
					index ++;
				});
			}
		});
		
		//娱乐项目
		$(".entertainment").each(function(idx, e){
			var ticketId = $(e).attr("name");
			var ticketCC  = $(e).val();
			if(ticketCC != null) {
				$.each(ticketCC, function(ccIdx, cc){
					route["routecc[" + index + "].fiDayflag"] = fiDayflag;
					route["routecc[" + index + "].fsCcno"] = cc;
					route["routecc[" + index + "].fsResno"] = ticketId;
					route["routecc[" + index + "].fsRestype"] = "yl";
					route["routecc[" + index + "].fsRouteno"] = fsRouteno;
					index ++;
				});
			}
		});
		
		//房型
		$(".room").each(function(idx, e){
			var ticketId = $(e).attr("name");
			var ticketCC  = $(e).val();
			if(ticketCC != null) {
				$.each(ticketCC, function(ccIdx, cc){
					route["routecc[" + index + "].fiDayflag"] = fiDayflag;
					route["routecc[" + index + "].fsCcno"] = cc;
					route["routecc[" + index + "].fsResno"] = ticketId;
					route["routecc[" + index + "].fsRestype"] = "fx";
					route["routecc[" + index + "].fsRouteno"] = fsRouteno;
					index ++;
				});
			}
		});
		
		
		//购物点
		$("input[class='shopid']").each(function(idx, e) {
			var shopId = $(e).attr("value");
			route["routecc[" + index + "].fiDayflag"] = fiDayflag;
			route["routecc[" + index + "].fsCcno"] = $("input[name='routecc[" + shopId + "].fsCcno").val();
			route["routecc[" + index + "].fsResno"] = shopId;
			route["routecc[" + index + "].fsRestype"] = "gw";
			route["routecc[" + index + "].fsRouteno"] = fsRouteno;
			index ++;
		});
		
		route["fiDayflag"] = $("#fiDays").val();
		route["fsId"] = fsRouteno;
		$.post("/routeArrange/editRouteCC.htm",
				route,
				function(data){
					var json = eval("(" + data + ")");
					if(json.result == "ok") {
						$("#message").text("修改记录成功");
						$("#message").show();
						return true;
					}
					else {
						$("#message").text("修改记录失败:" + json.message );
						$("#message").show();
						return false;
					}
					return false;
				});
	});
	
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
													getAccomadation(accomadation.data.starlvl);
													$("#fsStarLvl").find("option[value='" + accomadation.data.starlvl + "']").attr("selected", "selected");
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
					'<label class="shop_' + type.fsResno + '">&nbsp;&nbsp;' + type.fsScenicname + '--' + type.fsName + '：</label>' +
					'<input class="shop_'+ type.fsResno + '" name="routecc[' + type.fsResno + '].fsCcno" value="000021" type="hidden"/>' +
					'<span class="shop_' + type.fsResno + '">&nbsp;人头消费&nbsp;&nbsp;<br></span>';
					resetIframeHeight("add");
				});
				$("#div_shop").html(html);
			}
		});
	}

	//getAllRouteCC(fiDays, fiGenindex, fsRouteno);
	
	//	colorbox
	var $overflow = '';
	var colorbox_params = {
		rel : 'colorbox',
		reposition : true,
		scalePhotos : true,
		scrolling : true,
		previous : '<i class="ace-icon fa fa-arrow-left"></i>',
		next : '<i class="ace-icon fa fa-arrow-right"></i>',
		close : '&times;',
		current : '{current} of {total}',
		maxWidth : '100%',
		maxHeight : '100%',
		onOpen : function() {
			$overflow = document.body.style.overflow;
			document.body.style.overflow = 'hidden';
		},
		onClosed : function() {
			document.body.style.overflow = $overflow;
		},
		onComplete : function() {
			$.colorbox.resize();
		}
	};

	$('.ace-thumbnails [data-rel="colorbox"]').colorbox(colorbox_params);
	$("#cboxLoadingGraphic").html(
			"<i class='ace-icon fa fa-spinner orange'></i>");// let's add a custom loading icon

});
