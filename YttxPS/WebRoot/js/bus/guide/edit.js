jQuery(function($) {
	$("#message").hide();
//	var localsel = $("#selectCity", "#editform").localCity({

//	provurl : "/pub/findcity.htm",
//	cityurl : "/pub/findcity.htm",
//	disturl : "/pub/findcity.htm",
//	callback : localcallback
//	});

//	//城市选择器
//	function localcallback(index, key, value, fullkey, fullname) {
//	$("#regionname", "#editform").val(fullname);
//	$("#regionno", "#editform").val(key);
//	if (index == 3)
//	$("#selectCity", "#editform").hide();
//	}

//	$("#regionname", "#editform").click(function() {
//	$("#selectCity", "#editform").show();
//	});


	//	重置
	$("#reset").on("click", function() {
		$("#no").val(null);	
		$("#name").val(null);
		$("#gender").val(null);
		$("#message").hide();
		$("#message").text("");
	});

	// 关闭
	$("#close").on("click", function () {
		$("#editModal", parent.document).find(".close").click();
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
		if($("#gender").val() == '') {
			$("#message").show();
			$("#message").text("性别不能为空，请输入");
			$('#gender').focus();
			return false;
		}
		if($("#idno").val() == '') {
			$("#message").show();
			$("#message").text("身份证号不能为空，请输入");
			$('#idno').focus();
			return false;
		}
		if($("#contactno").val() == '') {
			$("#message").show();
			$("#message").text("联系方式不能为空，请输入");
			$('#contactno').focus();
			return false;
		}
		if($("#lvl").val() == '') {
			$("#message").show();
			$("#message").text("等级不能为空，请输入");
			$('#lvl').focus();
			return false;
		} 
		$.post("/guide/editGuide.htm",
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
