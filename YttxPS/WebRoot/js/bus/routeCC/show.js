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
					html += '<option value=' + comment['fsNo'] + '>' +comment['fsScenicname'] + '--' + comment['fsName'] + '</option>';
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
			$("#editModal", parent.document).find(".close").click();
	});
	
	//屏蔽部分组件
	$("select:not(.query-condition)").attr("disabled", "disabled");
	$("button[id!='close']").attr("disabled", "disabled");
	
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
