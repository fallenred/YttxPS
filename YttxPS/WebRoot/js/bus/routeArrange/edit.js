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
        }
    });

	$("#regionname", "#editform").click(function() {
		$("#selectCity", "#editform").show();
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
		$.post("/routeArrange/editRouteArrange.htm",
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
