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
	
	//获取导游下拉列表
	getGuide();
	//用于存放导游对应价格
	var guideMap = new Map();
	
	//车型变更
	$("#transType").change(function(){
		getTccPrice($("#transType").val(), 'transPrice');
		$("#resTransName").attr("value", $("#transType").find("option:selected").text());
	});
	//导游级别变更
	$("#guideLvl").change(function(){
		getGuide();
	});
	//导游变更后修改价格
	$("#guideNo").change(function(){
		guideMap.each(function(key,value,index){
			if (key == $("#guideNo").val())
				$("#guidePrice").val(value);
	    });
		$("#guideName").attr("value", $("#guideNo").find("option:selected").text());
	});
	
	//获取资源价格
	function getTccPrice(fsResno, id){
		$.ajax({
	        type: "GET",
	        url: "/tccPrice/findTccPriceByKey.htm",
	        data: 'fsResno='+fsResno+'&ftStartdate='+$("#ftStartdate").val()+'&fsCcno=000000&fsRestype=cx',
	        dataType: "json",
	        success: function(data){
	        	$("#"+id).val(data.fdPrice);
	        }
	    });
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
        	$("#genindex").html(html);
        }
    });
	
	//获取导游列表
	function getGuide(){
		$.ajax({
			type: "GET",
			url: "/guide/selectGuide.htm",
			data: "guide.lvl="+$("#guideLvl").val(),
			dataType: "json",
			success: function(data){
				var html = ''; 
				$.each(data, function(commentIndex, comment){
					html += '<option value=' + comment['no'] + '>' + comment['name'] + '</option>';
					guideMap.put(comment['no'], comment['salary']);
					if (commentIndex == 0) {
						$("#guidePrice").attr("value", comment['salary']);
					}
				});
				$("#guideNo").html(html);
				$("#guideName").attr("value", $("#guideNo").find("option:selected").text());
			}
		});
	}
	
	$("#regionname", "#addform").click(function() {
		$("#selectCity", "#addform").show();
	});

	//重置iframe高度
	function resetIframeHeight(type){
		var height = $(window.parent.document).find("#addIframe").attr("height");
		height = height.substring(0,height.length - 2);
		if ("add" == type) {
			height = (parseInt(height)+21);
		} else {
			height = (parseInt(height)-21);
		}
		$(window.parent.document).find("#addIframe").attr("height", height+"px");
	}

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
		$.post("/orderlist/editOrderlist.htm",
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
