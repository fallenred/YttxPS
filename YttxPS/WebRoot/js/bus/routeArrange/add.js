jQuery(function($) {
	$("#message").hide();
	
	var localsel = $("#selectCity", "#addform").localCity({
		
		provurl : "/pub/findcity.htm",
		cityurl : "/pub/findcity.htm",
		disturl : "/pub/findcity.htm",
		callback : localcallback
	});
	
	getGuide({"lvl": $("#guideLvl").val()});
	
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
	
	$("#selectCity", "#addform").blur(function() {
		$("#selectCity", "#addform").hide();
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
        		getTransportArrange({"fiGenindex": $("#fiGenindex").val()});
        }
    });
	
	$("#fiGenindex").change(function(){
		getTransportArrange({"fiGenindex": $("#fiGenindex").val()});
	});
	
	$("#guideLvl").change(function(){
		getGuide({"lvl": $("#guideLvl").val()});
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
		if($("#fiGenindex").val() == '' || $("#fiGenindex").val() == undefined) {
			$("#message").show();
			$("#message").text("所属线路不能为空，请输入");
			$('#fiGenindex').focus();
			return false;
		}
		if($("#fsName").val() == '' || $("#fsName").val() == undefined) {
			$("#message").show();
			$("#message").text("路线名称不能为空，请输入");
			$('#fsName').focus();
			return false;
		}
		if($("#fsStartplaceName").val() == '' || $("#fsStartplaceName").val() == undefined) {
			$("#message").show();
			$("#message").text("发团地不能为空，请输入");
			$('#fsStartplaceName').focus();
			return false;
		}
		if($("#fsProperty").val() == '' || $("#fsProperty").val() == undefined) {
			$("#message").show();
			$("#message").text("线路类型不能为空，请输入");
			$('#fsProperty').focus();
			return false;
		}
		if($("#fiDays").val() == '' || $("#fiDays").val() == undefined) {
			$("#message").show();
			$("#message").text("路线天数不能为空，请输入");
			$('#fiDays').focus();
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
		
		if($("#fiStat").val() == '' || $("#fiStat").val() == undefined) {
			$("#message").show();
			$("#message").text("线路状态不能为空，请输入");
			$('#fiStat').focus();
			return false;
		}
		if($("#transportArrange").val() == '' || $("#transportArrange").val() == undefined) {
			$("#message").show();
			$("#message").text("车型不能为空，请输入");
			$('#transportArrange').focus();
			return false;
		}
		if($("#guideFsNo").val() == '' || $("#guideFsNo").val() == undefined) {
			$("#message").show();
			$("#message").text("导游不能为空，请输入");
			$('#guideFsNo').focus();
			return false;
		}
		if($("#fsTitle").val() == '' || $("#fsTitle").val() == undefined) {
			$("#message").show();
			$("#message").text("线路标题不能为空，请输入");
			$('#fsTitle').focus();
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
