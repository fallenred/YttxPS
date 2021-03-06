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
	
	//获取门票列表
	$.ajax({
        type: "GET",
        url: "/ticket/selectTicket.htm",
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
		if ($("#editform").valid()) {
			if($("#fsNo").val() == '' || $("#fsNo").val() == undefined) {
				$("#message").show();
				$("#message").text("门票代码不能为空，请输入");
				$('#no').focus();
				return false;
			}
			
			if($("#ftStartdate").val() == '' || $("#ftStartdate").val() == undefined) {
				$("#message").show();
				$("#message").text("开始日期不能为空，请输入");
				$('#ftStartdate').focus();
				return false;
			}
			
			if($("#ftEnddate").val() == '' || $("#ftEnddate").val() == undefined) {
				$("#message").show();
				$("#message").text("结束日期不能为空，请输入");
				$('#ftEnddate').focus();
				return false;
			}
			
			var ftStartdate = new Date($("#ftStartdate").val());
			var ftEnddate = new Date($("#ftEnddate").val());
			if(ftEnddate - ftStartdate < 0) {
				$("#message").show();
				$("#message").text("结束日期不能早于开始日期");
				$('#ftStartdate').focus();
				return false;
			}
			
			var isChecked = true;
			$(".price").each(function(idx, e){
				if($(e).val() == null || $(e).val() == undefined || $(e).val().trim() == ""){
					$("#message").show();
					$("#message").text($("label[for='" + $(e).attr("id") + "']").text() + "不能为空");
					$(e).focus();
					isChecked = false;
					return false;
				}
			});
			if(!isChecked){
				return isChecked;
			}
			
			$.post("/ticket/editTicketPrice.htm",
					$("#editform").serialize(),
					function(data){
				var json = eval("(" + data + ")");
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
