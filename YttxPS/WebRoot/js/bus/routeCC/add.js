jQuery(function($) {
	$("#message").hide();
	
	var localsel = $("#selectCity", "#addform").localCity({
		
		provurl : "/pub/findcity.htm",
		cityurl : "/pub/findcity.htm",
		disturl : "/pub/findcity.htm",
		callback : localcallback
	});
	
	//酒店类型下拉列表
	getDict('bg', 'fsStarLvl');
	
	//获取线路列表
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
		$("#fiDays").val("");
	}
	
	$(".query-condition").change(function(){
		var fiDayflag = $("#fiDays").val();
		var fsRouteno = $("#fsResno").val();
		var fiGenindex = $("#fiGenindex").val();
		
		if(fiDayflag != null && fsRouteno != null && fiGenindex != null) {
			$.ajax({
				type: "POST",
				url: "/routeArrange/findRouteCCCount.htm",
				data: "fiDayflag=" + fiDayflag + "&fsRouteno=" + fsRouteno,
				dataType: "json",
				success: function(data){
					if(data > 0) {
						getAllRouteCC(fiDayflag, fiGenindex, fsRouteno);
						setContentDisable(true);
					} else {
						setContentDisable(false);
					}
				}
			});
		}
	});
	
	/**
	 * 获取线路配置列表
	 */
	function getRouteArrange(){
		$.ajax({
			type: "GET",
			url: "/routeArrange/selectRouteArrange.htm",
			data: "arrange.fiGenindex=" + $("#fiGenindex").val(),
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
			data: "scenicGen.fiGenindex=" + $("#fiGenindex").val(),
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
			data: "scenicGen.fiGenindex=" + $("#fiGenindex").val(),
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
			scenic += $.trim($(this).val()) + ",";
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
					html += '<option value=' + comment['fsNo'] + '>' + comment['fsName'] + '</option>';
				});
				$("#ticket").html(html);
			}
		});
	}
	
	/**
	 * 获取购物店列表
	 */
	function getShop(){
		var scenic = '';
		$("input[name='scenicGen']").each(function(){
			scenic += $.trim($(this).val()) + ",";
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
		$("input[name='scenicGen']").each(function(){
			scenic.push($.trim($(this).val()));
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
		$("input[name='scenicGen']").each(function(){
			scenic.push($.trim($(this).val()));
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
		var text = $("#restaurant").find("option:selected").text();
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
		getScenice();   //获取景区
		getShop();   //获取购物店列表
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

	//	重置
	$("#reset").on("click", function() {
			$("#selectCity").hide();
			$("#regionno").val(null);
			$("#message").hide();
			$("#message").text("");
	});
	
	// 关闭
	$("#close").on("click", function () {
			$("#addModal", parent.document).find(".close").click();
	});
	
	//	提交
	$("#submit").on("click", function () {
		var index = 0;
		var routecc = {};
		var fsRouteno = $("#fsResno").val();
		var fiDayflag = $("#fiDays").val();
		routecc["fsId"] = fsRouteno;
		
		//门票
		$(".ticket").each(function(idx, e){
			var ticketId = $(e).attr("name");
			var ticketCC  = $(e).val();
			$.each(ticketCC, function(ccIdx, cc){
				routecc["routecc[" + index + "].fiDayflag"] = fiDayflag;
				routecc["routecc[" + index + "].fsCcno"] = cc;
				routecc["routecc[" + index + "].fsResno"] = ticketId;
				routecc["routecc[" + index + "].fsRestype"] = "mp";
				routecc["routecc[" + index + "].fsRouteno"] = fsRouteno;
				index ++;
			});
		});
		
		//餐厅
		$(".restaurant").each(function(idx, e){
			var restaurantId = $(e).attr("name");
			var restaurantCC  = $(e).val();
			$.each(restaurantCC, function(ccIdx, cc){
				routecc["routecc[" + index + "].fiDayflag"] = fiDayflag;
				routecc["routecc[" + index + "].fsCcno"] = cc;
				routecc["routecc[" + index + "].fsResno"] = restaurantId;
				routecc["routecc[" + index + "].fsRestype"] = "ct";
				routecc["routecc[" + index + "].fsRouteno"] = fsRouteno;
				index ++;
			});
		});
		
		//娱乐项目
		$(".entertainment").each(function(idx, e){
			var entertainmentId = $(e).attr("name");
			var entertainmentCC  = $(e).val();
			$.each(entertainmentCC, function(ccIdx, cc){
				routecc["routecc[" + index + "].fiDayflag"] = fiDayflag;
				routecc["routecc[" + index + "].fsCcno"] = cc;
				routecc["routecc[" + index + "].fsResno"] = entertainmentId;
				routecc["routecc[" + index + "].fsRestype"] = "yl";
				routecc["routecc[" + index + "].fsRouteno"] = fsRouteno;
				index ++;
			});
		});
		
		//房型
		$(".room").each(function(idx, e){
			var roomId = $(e).attr("name");
			var roomCC  = $(e).val();
			$.each(roomCC, function(ccIdx, cc){
				routecc["routecc[" + index + "].fiDayflag"] = fiDayflag;
				routecc["routecc[" + index + "].fsCcno"] = cc;
				routecc["routecc[" + index + "].fsResno"] = roomId;
				routecc["routecc[" + index + "].fsRestype"] = "bg";
				routecc["routecc[" + index + "].fsRouteno"] = fsRouteno;
				index ++;
			});
		});
		
		
		//购物点
		$("input[class='shopid']").each(function(idx, e) {
			var shopId = $(e).attr("value");
			routecc["routecc[" + index + "].fiDayflag"] = fiDayflag;
			routecc["routecc[" + index + "].fsCcno"] = $("input[name='routecc[" + shopId + "].fsCcno").val();
			routecc["routecc[" + index + "].fsResno"] = shopId;
			routecc["routecc[" + index + "].fsRestype"] = "gw";
			routecc["routecc[" + index + "].fsRouteno"] = fsRouteno;
			index ++;
		});
		
		$.post("/routeArrange/addRouteCC.htm",
				routecc,
				function(data){
					var json = eval("(" + data + ")");
					if(json.result == "ok") {
						$("#message").text("增加记录成功");
						$("#message").show();
						setTimeout(function(){
							$("#message").fadeOut(2000);
						}, 2000);
						setContentDisable(true);
						return true;
					}
					else {
						$("#message").text("增加记录失败:" + json.message );
						$("#message").show();
						return false;
					}
					return false;
				});
	});
	
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
