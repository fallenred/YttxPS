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
		$("#regionname", "#addform").val(fullname);
		$("#regionno", "#addform").val(key);
		if (index == 3)
			$("#selectCity", "#addform").hide();
	}

	$("#regionname", "#addform").click(function() {
		$("#selectCity", "#addform").show();
	});


	$("#addModal", parent.document).on("hidden.bs.modal", function() {
	    $(this).removeData("bs.modal");
	});

	//	重置
	$("#reset").on("click", function() {
			$("#selectCity").hide();
			$("#regionno").val(null);
			$("#message").hide();
			$("#message").text("");
		});
	
	//	提交
	$("#submit").on("click", function () {
		if($("#no").val() == '') {
			$("#message").show();
			$("#message").text("景区编码不能为空，请输入");
			$('#no').focus();
			return false;
		} 
		if($("#name").val() == '') {
			$("#message").show();
			$("#message").text("景区名称不能为空，请输入");
			$('#name').focus();
			return false;
		} 
		if($("#lvl").val() == '') {
			$("#message").show();
			$("#message").text("景区等级不能为空，请输入");
			$('#lvl').focus();
			return false;
		} 
		if($("#regionno").val() == '') {
			$("#message").show();
			$("#message").text("景区地区不能为空，请输入");
			$('#regionno').focus();
			return false;
		} 
		$.post("/scenic/addScenic.htm",
				$("#addform").serialize(),
				function(data){

			var json = eval(data);
			console.log(json);
			console.log(json.result);
			console.log(json.message);
					if(json.result == "ok") {
						$("#message").text("增加记录成功");
						$("#message").show();
						return true;
					}
					else {
						$("#message").text("增加记录失败:" + data.message );
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
