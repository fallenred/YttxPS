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
		$("#fsRegionName", "#editform").val(fullname);
		$("#fsRegionno", "#editform").val(key);
		if (index == 3)
			$("#selectCity", "#editform").hide();
	}
	
	//获取景点列表
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
        		$("#fsScenicno").html(html);
        }
    });

	$("#fsRegionName", "#editform").click(function() {
		$("#selectCity", "#editform").show();
	});


	//	重置
	$("#reset").on("click", function() {
			$("#selectCity").hide();
			$("#fsRegionno").val(null);
			$("#message").hide();
			$("#message").text("");
		});
	
	// 关闭
	$("#close").on("click", function () {
		$("#editModal", parent.document).find(".close").click();
	});
	
	//	提交
	$("#submit").on("click", function () {
		if($("#fsNo").val() == '' || $("#fsNo").val() == undefined) {
			$("#message").show();
			$("#message").text("娱乐项目代码不能为空，请输入");
			$('#fsNo').focus();
			return false;
		}
		if($("#fsType").val() == '' || $("#fsType").val() == undefined) {
			$("#message").show();
			$("#message").text("娱乐项目类型不能为空，请输入");
			$('#fsType').focus();
			return false;
		}
		if($("#fsRegionno").val() == '' || $("#fsRegionno").val() == undefined) {
			$("#message").show();
			$("#message").text("所属地区不能为空，请输入");
			$('#fsRegionno').focus();
			return false;
		}
		if($("#fsLvl").val() == '' || $("#fsLvl").val() == undefined) {
			$("#message").show();
			$("#message").text("娱乐项目级别不能为空，请输入");
			$('#fsLvl').focus();
			return false;
		}
		if($("#fiStat").val() == '' || $("#fiStat").val() == undefined) {
			$("#message").show();
			$("#message").text("状态不能为空，请输入");
			$('#fiStat').focus();
			return false;
		}
		
		$.post("/entertainment/editEntertainment.htm",
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
