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
		$("#fsStartplaceName", "#editform").val(fullname);
		$("#fsStartplace", "#editform").val(key);
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
	
	var fsId = $.getUrlParam("fsId");
	
	//获取线路信息
	$.ajax({
		type: "GET",
        url: "/routeArrange/findUniqRouteArrange.htm",
        data: {"fsId": fsId},
        dataType: "json",
        success: function(data){
        	$("#fsId").val(data.data.routeArrange.fsId);   //线路主键id
        	$("#fiGenindex").val(data.data.routeArrange.fiGenindex);   //所属线路统称Idx
        	$("#fsName").val(data.data.routeArrange.fsName);   //线路名称
        	$("#fsRegions").val(data.data.routeArrange.fsRegions);   //所属地区列表
        	$("#fiDays").val(data.data.routeArrange.fiDays);   //线路天数
        	$("#fsProperty").val(data.data.routeArrange.fsProperty);   //"线路类型: 01:专家推荐 02:热门线路 03:特价线路"
        	$("#ftStartdate").val($.setTime(data.data.routeArrange.ftStartdate));   //线路有效起始日(不设置时表示不限?)
        	$("#ftEnddate").val($.setTime(data.data.routeArrange.ftEnddate));   //线路有效截止日
        	$("#fsStartplace").val(data.data.routeArrange.fsStartplace);   //"发团地(不设置时表示不限?) --发团地改成6位地区代码吧,一般来说指定同一市区即可,街道应该是可变的"
        	$("#fsStartplaceName").val(data.data.routeArrange.fsStartplaceName);   //
        	$("#fsTitle").val(data.data.routeArrange.fsTitle);   //线路标题
        	$("#fsTitlepic").val(data.data.routeArrange.fsTitlepic);   //线路缩略图 图片路径
        	$("#fsSummary").val(data.data.routeArrange.fsSummary);   //线路摘要
        	$("#fiStat").val(data.data.routeArrange.fiStat);   //日程快照
        	CKEDITOR.instances["fcSchedule"].setData(data.data.routeArrange.fcSchedule);   //日程快照
        	
        	var arrange = {};
        	arrange.fiGenindex = data.data.routeArrange.fiGenindex;
        	if(data.data.transportArrange != undefined) {
        		arrange.transportArrange = data.data.transportArrange.fsNo;
        	} else {
        		arrange.transportArrange = "";
        	}
        	getTransportArrange(arrange);
        	
        	if(data.data.guide != undefined) {
        		getGuide(data.data.guide);
        	} else {
        		getGuide({"lvl" : ""});
        	}
        }
	});
	
	$("#fiGenindex").change(function(){
		getTransportArrange({"fiGenindex": $("#fiGenindex").val()});
	});
	
	$("#guideLvl").change(function(){
		getGuide({"lvl": $("#guideLvl").val()});
	});

	$("#fsStartplaceName", "#editform").click(function() {
		$("#selectCity", "#editform").show();
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
