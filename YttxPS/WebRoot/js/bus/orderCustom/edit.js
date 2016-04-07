jQuery(function($) {
	$("#message").hide();
	
	var localsel = $("#selectCity", "#editform").localCity({
		
		provurl : "/pub/findcity.htm",
		cityurl : "/pub/findcity.htm",
		disturl : "/pub/findcity.htm",
		callback : localcallback
	});
	
	//城市选择器
	function localcallback(index, key, value, fullkey, fullname) {
		$("#regionname", "#editform").val(fullname);
		$("#regionno", "#editform").val(key);
		if (index == 3)
			$("#selectCity", "#editform").hide();
	}
	
	$("#regionname", "#editform").click(function() {
		$("#selectCity", "#editform").show();
	});
	
	getScenice();
	
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
				$(".scenic.form-control").each(function(){
					$(this).html(html);
	        	});
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
				$(".select_ticket").html(html);
			}
		});
	}
	
	//获取景区餐厅列表
	function getRestaurant(){
		var dataArr = new Array();
		$("input[name='scenicGen']").each(function(i, item){
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
				$(".select_restaurant").html(html);
			}
		});
	}
	
	//获取景区娱乐项目列表
	function getEntertainment(){
		var dataArr = new Array();
		$("input[name='scenicGen']").each(function(i, item){
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
				$(".select_entertainment").html(html);
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
					html += '<option value=' + comment['no'] + '>' + comment['name'] + '</option>';
				});
				$(".shop").html(html);
			}
		});
	}
	
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
		getRestaurant();
		getEntertainment();
		getShop();
	});
	
	//删除景区
	$("#rmScenicBtn").click(function(){
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
		getRestaurant();
		getEntertainment();
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
		$("#editModal", parent.document).find(".close").click();
	});
	
	//	提交
	$("#submit").on("click", function () {
		if ($("#editorm").valid()) {
			//校验数量不能为空
			flag = true;
			$(".usernum").each(function(){
				if ($(this).val() == null || $(this).val() == ''|| $(this).val() == 'null') {
					$("#message").show();
					$("#message").text("数量不能为空，请输入");
					$(this).attr("placeholder", "数量不能为空，请输入");
					$(this).focus();
					flag = false;
				}
			});
			if (!flag) {
				return;
			}
			$.post("/orderCustom/editOrderCustom.htm",
					$("#editform").serialize(),
					function(data){
				var json = eval("("+data+")");
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
		} else {
			$("#message").text("输入字段验证错误，请重新编辑后再提交");
			$("#message").show();
			return false;
		}
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
