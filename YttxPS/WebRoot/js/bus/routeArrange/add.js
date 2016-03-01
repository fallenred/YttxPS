jQuery(function($) {
	$("#message").hide();
	
	var localsel = $("#selectCity", "#addform").localCity({
		
		provurl : "/pub/findcity.htm",
		cityurl : "/pub/findcity.htm",
		disturl : "/pub/findcity.htm",
		callback : localcallback
	});
	
	//城市选择器
	function localcallback(index, key, value, fullkey, fullname) {
		$("#fsStartplaceName", "#addform").val(fullname);
		$("#fsStartplace", "#addform").val(key);
		if (index == 3)
			$("#selectCity", "#addform").hide();
	}

	$("#fsStartplaceName", "#addform").click(function() {
		$("#selectCity", "#addform").show();
	});
	
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
        		getTransportArrange();
        }
    });
	
	function getTransportArrange(){
		//获取车型列表
		$.ajax({
			type: "GET",
			url: "/transportArrange/selectTransportArrange.htm",
			data: "transportArrange.fiGenindex=" + $("#fiGenindex").val(),
			dataType: "json",
			success: function(data){
				var html = ''; 
				$.each(data, function(commentIndex, comment){
					html += '<option value=' + comment['fsTransno'] + '>' + comment['fsTransName'] + '</option>';
				});
				$("#transportArrange").html(html);
			}
		});
	}
	
	function getGuide(){
		//获取导游列表
		$.ajax({
			type: "GET",
			url: "/guide/selectGuide.htm",
			data: "guide.lvl="+$("#guideLvl").val(),
			dataType: "json",
			success: function(data){
				var html = ''; 
				$.each(data, function(commentIndex, comment){
					html += '<option value=' + comment['no'] + '>' + comment['name'] + '</option>';
				});
				$("#guideFsNo").html(html);
			}
		});
	}
	
	$("#fiGenindex").change(function(){
		getTransportArrange();
	});
	
	getGuide();
	$("#guideLvl").change(function(){
		getGuide();
	});

	//	重置
	$("#reset").on("click", function() {
			$("#selectCity").hide();
			$("#fsStartplace").val(null);
			$("#message").hide();
			$("#message").text("");
		});
	
	// 关闭
	$("#close").on("click", function () {
		$("#addModal", parent.document).find(".close").click();
	});
	
	//	提交
	$("#submit").on("click", function () {
		if($("#fsName").val() == '') {
			$("#message").show();
			$("#message").text("路线名称不能为空，请输入");
			$('#no').focus();
			return false;
		} 
		if($("#fiDays").val() == '') {
			$("#message").show();
			$("#message").text("路线天数不能为空，请输入");
			$('#name').focus();
			return false;
		} 
		$("#fcSchedule").val(CKEDITOR.instances["fcSchedule"].getData());
		$.post("/routeArrange/addRouteArrange.htm",
				$("#addform").serialize(),
				function(data){
				var json = eval("(" + data + ")");
				if(json.result == "ok") {
					$("#message").text("增加记录成功");
					$("#message").show();
					return true;
				} else {
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
