jQuery(function($) {
	$("#message").hide();
	
	var localsel = $("#selectCity", "#addform").localCity({
		
		provurl : "/pub/findcity.htm",
		cityurl : "/pub/findcity.htm",
		disturl : "/pub/findcity.htm",
		callback : localcallback
	});
	
	//酒店类型下拉列表
	getDict('bgjb', 'fsStarLvl');
	
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
        		getRouteArrange()
        		getSceniceGen();
        		getScenice();
        		$("#ticket").html('');
        }
    });
	
	function getDays(num){
		var html="";
		//生成日程下拉列表
		for (var i = 0; i < num ; i++) {
			html += '<option value=' + i + '>第' + (parseInt(i)+1) + '天</option>';
		}
		$("#fiDays").html(html);
	}
	
	function getRouteArrange(){
		//获取线路配置列表
		$.ajax({
			type: "GET",
			url: "/routeArrange/selectRouteArrange.htm",
			data: "arrange.fiGenindex="+$("#fiGenindex").val(),
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
	
	function getScenice(){
		//获取景区列表
		$.ajax({
			type: "POST",
			url: "/scenic/findAllScenic.htm",
			data: '',
			dataType: "json",
			success: function(data){
				var html = ''; 
				$.each(data, function(commentIndex, comment){
					html += '<option value=' + comment['no'] + '>' + comment['name'] + '</option>';
				});
				$("#scenic").html(html);
			}
		});
	}
	
	function getSceniceGen(){
		//获取线路景区
		var num = '';
		$.ajax({
			type: "GET",
			url: "/scenicGen/selectScenicGen.htm",
			data: "scenicGen.fiGenindex="+$("#fiGenindex").val(),
			dataType: "json",
			success: function(data){
				var html = '线路景区：'; 
				$.each(data, function(commentIndex, comment){
					html += '<input type="hidden" name="scenicGen" value="' + comment['fsScenicno'] + '"/>' + '&nbsp;&nbsp;<label>' + comment['fsScenicname']+'</label>';
					if(commentIndex == 0)
						num = comment['fiDays'];
				});
				$("#div_scenics").html(html);
				getTicket();
				getDays(num);
				getShop();
			}
		});
	}
	
	function getTicket(){
		//获取景区票列表
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
					html += '<option value=' + comment['fsNo'] + '>' +comment['fsScenicname'] + '--' + comment['fsName'] + '</option>';
				});
				$("#ticket").html(html);
			}
		});
	}
	
	function getDict(parentno, selectId){
		//初始化字典列表
		$.ajax({
			type: "GET",
			traditional: true,
			url: "/dict/selectDict.htm",
			data: "dict.fsParentno=" + parentno,
			dataType: "json",
			success: function(data){
				var html = '<option value="">'+'--所有类型--' + '</option>'; 
				$.each(data, function(commentIndex, comment){
					html += '<option value=' + comment['fsDictno'] + '>' + comment['fsDictname'] + '</option>';
				});
				$("#"+selectId).html(html);
			}
		});
	}
	
	function getAccomadation(){
		//获取酒店列表
		$.ajax({
			type: "GET",
			traditional: true,
			url: "/accomadation/selectAccomadation.htm",
			data: "accomadation.starlvl=" + $("#fsStarLvl").val(),
			dataType: "json",
			success: function(data){
				var html = ''; 
				$.each(data, function(commentIndex, comment){
					html += '<option value=' + comment['no'] + '>' + comment['name'] + '</option>';
				});
				$("#accomadationNo").html(html);
				getRoom();
			}
		});
	}
	
	function getRoom(){
		//获取酒店房型列表
		$.ajax({
			type: "GET",
			traditional: true,
			url: "/room/selectRoom.htm",
			data: "room.accomno=" + $("#accomadationNo").val(),
			dataType: "json",
			success: function(data){
				var html = ''; 
				$.each(data, function(commentIndex, comment){
					html += '<option value=' + comment['roomno'] + '>' + comment['name'] + '</option>';
				});
				$("#roomno").html(html);
			}
		});
	}
	
	function getShop(){
		//获取购物店列表
		var scenic = '';
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
					html += '<option value=' + comment['no'] + '>' +comment['scenicname'] + '--' + comment['name'] + '</option>';
				});
				$("#shop").html(html);
			}
		});
	}
	
	//线路变更
	$("#fiGenindex").change(function(){
		getRouteArrange();
		getSceniceGen();
		getShop();
	});
	
	//酒店标准变更
	$("#fsStarLvl").change(function(){
		getAccomadation();
	});
	
	//酒店变更
	$("#accomadationNo").change(function(){
		getRoom();
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
		//数组下标
		var index = 0;
		if($("#index").val() != null){
			index = $("#index").val();
		}
		var html = $("#div_ticket").html();
		var val = $("#ticket").val();
		var text = $("#ticket").find("option:selected").text();
		var flag = true;
		$("input[class='ticketid']").each(function(){
			if (val == $(this).val()) { 
				flag = false;
			}
		});
		if (flag) {
			$("#index").attr("value",parseInt(index)+1);
			html += '<input type="hidden" class="ticketid" name="routecc['+index+'].fsResno" value="' + val + '"/>'+
					'<input type="hidden" class="ticket_'+val+'" name="routecc['+index+'].fsRestype" value="mp"/>' + '<label class="ticket_'+val+'">&nbsp;&nbsp;' + text + '：</label>'+
					'<input class="ticket_'+val+'" name="routecc['+index+'].fsCcno" value="01" type="checkbox"/><span class="ticket_'+val+'">&nbsp;团队全价票&nbsp;&nbsp;</span>'+
					'<input class="ticket_'+val+'" name="routecc['+index+'].fsCcno" value="02" type="checkbox"/><span class="ticket_'+val+'">&nbsp;团队半价票&nbsp;&nbsp;<br></span>';
			resetIframeHeight("add");
		}
		$("#div_ticket").html(html);
	});
	
	//删除门票
	$("#rmTicketBtn").click(function(){
		var val = $("#ticket").val();
		if($("#index").val() != null){
			index = $("#index").val();
		}
		$("input[class='ticketid']").each(function(){
			if (val == $(this).val()) {
				$(this).remove();
				$(".ticket_"+val).remove();
				$("#index").attr("value",parseInt(index)-1);
				resetIframeHeight("sub");
			} 
		});
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
			$("#index").attr("value",parseInt(index)+1);
			html += '<input type="hidden" class="shopid" name="routecc['+index+'].fsResno" value="' + val + '"/>'+
					'<input type="hidden" class="shop_'+val+'" name="routecc['+index+'].fsRestype" value="gw"/>' + '<label class="shop_'+val+'">&nbsp;&nbsp;' + text + '：</label>'+
					'<input class="shop_'+val+'" name="routecc['+index+'].fsCcno" value="000021" type="hidden"/><span class="shop_'+val+'">&nbsp;人头消费&nbsp;&nbsp;<br></span>';
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
				$(".shop_"+val).remove();
				$("#index").attr("value",parseInt(index)-1);
				resetIframeHeight("sub");
			} 
		});
	});
	
	//增加房间消费
	$("#addRoomBtn").click(function(){
		//数组下标
		var index = 0;
		if($("#index").val() != null){
			index = $("#index").val();
		}
		var html = $("#div_room").html();
		var val = $("#roomno").val();
		var accomadationName = $("#accomadationNo").find("option:selected").text();
		var roomname = $("#roomno").find("option:selected").text();
		var flag = true;
		$("input[class='roomid']").each(function(){
			if (val == $(this).val()) { 
				flag = false;
			}
		});
		if (flag) {
			$("#index").attr("value",parseInt(index)+1);
			html += '<input type="hidden" class="roomid" name="routecc['+index+'].fsResno" value="' + val + '"/>'+
					'<input type="hidden" class="room_'+val+'" name="routecc['+index+'].fsRestype" value="bg"/>' + 
					'<label class="room_'+val+'">&nbsp;&nbsp;' + accomadationName + '-' + roomname + '：</label>'+
					'<input class="room_'+val+'" name="routecc['+index+'].fsCcno" value="000024" type="hidden"/><span class="room_'+val+'">&nbsp;房间消费&nbsp;&nbsp;<br></span>';
			resetIframeHeight("add");
		}
		$("#div_room").html(html);
	});
	
	//删除房间消费
	$("#rmRoomBtn").click(function(){
		var val = $("#roomno").val();
		if($("#index").val() != null){
			index = $("#index").val();
		}
		$("input[class='roomid']").each(function(){
			if (val == $(this).val()) {
				$(this).remove();
				$(".room_"+val).remove();
				$("#index").attr("value",parseInt(index)-1);
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
		var height = $(window.parent.document).find("#addIframe").attr("height");
		height = height.substring(0,height.length - 2);
		if ("add" == type) {
			height = (parseInt(height)+21);
		} else {
			height = (parseInt(height)-21);
		}
		$(window.parent.document).find("#addIframe").attr("height", height+"px");
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
		$("#addModal", parent.document).find(".close").click();
	});
	
//	提交
	$("#submit").on("click", function () {
		$.post("/routeArrange/addRouteCC.htm",
				$("#addform").serialize(),
				function(data){
			var json = eval("("+data+")");
					if(json.result == "ok") {
						$("#message").text("增加记录成功");
						$("#message").show();
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
