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
		$.post("/scenic/editScenic.htm",
				$("#editform").serialize(),
				function(data){
			console.log(data)
			var json = eval("("+data+")");
					if(json.result == "ok") {
						$("#message").text("修改记录成功");
						$("#message").show();
						console.log($("#grid-table", parent.document).html());
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
