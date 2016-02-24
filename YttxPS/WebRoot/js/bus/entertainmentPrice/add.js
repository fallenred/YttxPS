jQuery(function($) {
	$("#message").hide();
	
	var localsel = $("#selectCity", "#addform").localCity({
		
		provurl : "/pub/findcity.htm",
		cityurl : "/pub/findcity.htm",
		disturl : "/pub/findcity.htm",
		callback : localcallback
	});
	
	$(".peak").hide();
	$(".trans").hide();
	$("#priceType").bind("change", function(){ 
	    if($(this).val() == 1){   //淡季
	      $(".low").show();
	      $(".peak").hide();
	      $(".trans").hide();
	    } else if($(this).val() == 2) {   //旺季
	    	$(".peak").show();
		    $(".low").hide();
		    $(".trans").hide();
	    } else {   //接送费用
	    	$(".peak").hide();
		    $(".low").hide();
		    $(".trans").show();
	    }
	}); 
	
	//获取门票列表
	$.ajax({
        type: "GET",
        url: "/entertainment/selectEntertainment.htm",
        data: '',
        dataType: "json",
        success: function(data){
        		var html = ''; 
        		$.each(data, function(commentIndex, comment){
        			html += '<option value=' + comment['fsNo'] + '>' + comment['fsName'] + '</option>';
        		});
        		$("#fsNo").html(html);
        }
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
		if($("#fsNo").val() == '') {
			$("#message").show();
			$("#message").text("门票代码不能为空，请输入");
			$('#no').focus();
			return false;
		} 
		if($("#fsScenicno").val() == '') {
			$("#message").show();
			$("#message").text("所属景区不能为空，请输入");
			$('#name').focus();
			return false;
		} 
		if($("#fsType").val() == '') {
			$("#message").show();
			$("#message").text("门票类型不能为空，请输入");
			$('#lvl').focus();
			return false;
		} 
		$.post("/entertainment/addEntertainmentPrice.htm",
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
