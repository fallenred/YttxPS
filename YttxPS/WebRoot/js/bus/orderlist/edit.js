jQuery(function($) {
	$("#message").hide();
	$.base64.utf8encode = true;
	setTimeout(loadtext,"3000");
	var fsNo = $.getUrlParam('fsNo');
	function loadtext(){
		var ckEditor = CKEDITOR.instances.fcSchedule;
		ckEditor.setData($.base64.atob($('#hSchedule').val(), true));
	}
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
	
	//获取导游级别列表
	getFsStarLvl();
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
	        url: "/tccPrice/findTccPrice.htm",
	        data: 'fsResno='+fsResno+'&ftStartdate='+$("#ftStartdate").val()+'&ftEnddate='+$("#ftStartdate").val()+'&fsCcno=000023&fsRestype=cx',
	        dataType: "json",
	        success: function(data){
	        	$.each(data, function(commentIndex, comment){
	        		$("#"+id).val(comment['fdPrice']);
				});
	        }
	    });
	}
	
	//获取导游列表
	function getFsStarLvl(){
		$.ajax({
			type: "GET",
			traditional: true,
			url: "/dict/selectDict.htm",
			data: "dict.fsParentno=dy",
			dataType: "json",
			success: function(data){
				html = '<option value="">'+'--请选择--' + '</option>'; 
				$.each(data, function(commentIndex, comment){
					html += '<option value=' + comment['fsDictno'] + '>' + comment['fsDictname'] + '</option>';
				});
				$("#guideLvl").html(html);
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
	
	//查询订单所有备注信息
	$.ajax({
		type: "POST",
		url: "/orderlist/findRemarks.htm",
		data: 'orderId='+fsNo,
		dataType: "json",
		success: function(data){
			var remarks = {
				"remarks" : data
			}
			var remarksTemplate = Handlebars.compile($("#remarks-template").html());
			//返回数组长度
			$('#table_remarks tbody').html(remarksTemplate(remarks));
		}
	});
	Handlebars.registerHelper("remarkStat",function(v1, v2){
		if (v1 == v2) {
			return 'selected="selected"';
		} else {
			return "";
		}
	});
	//返回数组长度
	Handlebars.registerHelper("length",function(array){
		if (array == null || array == '') {
			return 0;
		}
		return array.length;
	});
	//添加备注项
	$(document).on('click key', '#btn_remarks', function(event){
		var remarksTemplate = Handlebars.compile($("#tr-remarks").html());
		var fsOrderId = $("#fsNo").val();
		var reslistIndex = $("#remarksIndex").val();
		var ftDate = getNowFormatDate();
		var fsContent = $("#fsContent").val();
		var fdAmt = $("#fdAmt").val();
		if (fdAmt == '') {
			alert("备注金额不能为空！");
			return;
		}
		var fiStat = '0';
		var data = {
				"index" : reslistIndex,
				"fsOrderId" : fsOrderId,
				"fiSeq" : reslistIndex,
				"fsOperId" : fsOperId,
				"ftDate" : ftDate,
				"fsContent" : fsContent,
				"fdAmt" : fdAmt,
				"fiStat" : fiStat
		}
		$("#remarksIndex").val(parseInt($("#remarksIndex").val())+1);
		alert(remarksTemplate(data));
		$('#table_remarks tbody').html($('#table_remarks tbody').html() + remarksTemplate(data));
	});
	
	function getNowFormatDate() {
	    var date = new Date();
	    var seperator1 = "/";
	    var seperator2 = ":";
	    var month = date.getMonth() + 1;
	    var strDate = date.getDate();
	    if (month >= 1 && month <= 9) {
	        month = "0" + month;
	    }
	    if (strDate >= 0 && strDate <= 9) {
	        strDate = "0" + strDate;
	    }
	    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
	            + " " + date.getHours() + seperator2 + date.getMinutes()
	            + seperator2 + date.getSeconds();
	    return currentdate;
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
