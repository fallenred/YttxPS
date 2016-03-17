jQuery(function($) {
	$("#message").hide();
	$("#reset").click();
	var fsNo = $.getUrlParam('fsNo');

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

	// 关闭
	$("#close").on("click", function () {
		$("#customizationModal", parent.document).find(".close").click();
	});
	//返回+1之后的结果
	Handlebars.registerHelper("addOne",function(index){
		return index+1;
	});
	//返回数组长度
	Handlebars.registerHelper("length",function(array){
		if (array == null || array == '') {
			return 0;
		}
		return array.length;
	});
	//判断是否景区资源
	Handlebars.registerHelper("isScenic",function(value){
		if (value == 'jq'){
			return "scenic";
		} else {
			return "";
		}
	});
	$(function(){
		//加载clob内容
		$.ajax({
			type: "POST",
			url: "/orderlist/findCommSnapshot.htm",
			data: 'no='+fsNo,
			dataType: "json",
			success: function(data){
				//计划天数
				$("#days").val(data.fcSchedule.daylist.length);
				//服务标准
				$("#svcstdcontent").val(data.fcSchedule.svcstdcontent);
				//保险金额
				$("#insuerprice").val(data.fcSchedule.insuerprice);
				//预估金额
				$("#price").val(data.fcSchedule.price);
				var daylistTemplate = Handlebars.compile($("#daylist-template").html());
				//处理早午晚餐页面勾选
				Handlebars.registerHelper('compare',function(value, options) {
					//不勾选
					if(value == 0){
						return options.fn(this);
					}
					//勾选
					else if(value == 1){  
						return options.inverse(this);
					}
				});
				//行程安排
				$('#table-daylist').html(daylistTemplate(data.fcSchedule));
				//附件
				var attachsTemplate = Handlebars.compile($("#attachs-template").html());
				$('#table-attachs').html(attachsTemplate(data.fcSchedule));
				//公共资源头
				var commResTemplate = Handlebars.compile($("#commRes-template").html());
				//公共资源体（导游、车型、景区）
				var commResTemplate1 = Handlebars.compile($("#commRes-template1").html());
				//公共资源体（门票、购物、餐厅）
				var commResTemplate2 = Handlebars.compile($("#commRes-template2").html());
				$('#myTab').html(commResTemplate(data.commResSnapshot));
				$('#myTabContent').html(commResTemplate1(data.commResSnapshot));
				$('#myTabContent').html($('#myTabContent').html() + commResTemplate2(data.commResSnapshot));
				//获取车型列表
				getTransport();
				//获取导游星级列表
				getFsStarLvl();
				//获取景区列表
				getScenic();
				//获取景区门票列表
				getTicket(null);
				//获取酒店级别列表
				getAccomadationLvl(null);
			}
		});
	}); 
	function getScenic(){
		//获取景区列表
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
				$("#scenic").html(html);
			}
		});
	}
	//获取车型列表selectTransport.htm
	function getTransport(){
		$.ajax({
			type: "GET",
			url: "/transport/selectTransport.htm",
			dataType: "json",
			success: function(data){
				var html = ''; 
				$.each(data, function(commentIndex, comment){
					html += '<option value=' + comment['fsNo'] + '>' + comment['fsName'] + '</option>';
				});
				$("#transport").html(html);
			}
		});
	}
	//获取导游星级列表
	function getFsStarLvl(){
		$.ajax({
			type: "GET",
			traditional: true,
			url: "/dict/selectDict.htm",
			data: "dict.fsParentno=dy",
			dataType: "json",
			success: function(data){
				html = ''; 
				$.each(data, function(commentIndex, comment){
					html += '<option value=' + comment['fsDictno'] + '>' + comment['fsDictname'] + '</option>';
				});
				$("#guideLvl").html(html);
				getGuide();
			}
		});
	}
	var guideMap = '';
	//获取导游列表
	function getGuide(){
		guideMap = new Map();
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
						$("#guidePrice").val(comment['salary']);
					}
				});
				$("#guide").html(html);
				$("#guideName").attr("value", $("#guideNo").find("option:selected").text());
			}
		});
	}
	//导游级别变更
	$(document).on('change key', '#guideLvl', function(event){
		getGuide();
	});
	//导游变更后修改价格
	$(document).on('change key', '#guide', function(event){
		guideMap.each(function(key,value,index){
			if (key == $("#guide").val())
				$("#guidePrice").val(value);
		});
		$("#guideName").attr("value", $("#guide").find("option:selected").text());
	});
	//资源类型变更
	$(document).on('change key', '#restype', function(event){
		var restype = $(this).val();
		if(restype == 'mp'){
			getTicket(this);
		}
		if(restype == 'ct'){
			getRestaurant(this);
		}
		if(restype == 'gw'){
			getShop(this);
		}
		if(restype == 'bg'){
			$(this).parent().parent().find(".batch_bg").show();
			$(this).parent().parent().find(".batch_resname").html('房型');
			batch_resname 
			getAccomadationLvl(this);
		}
		if(restype == 'yl'){
			$(this).parent().parent().find(".batch_resname").html('资源');
			$(this).parent().parent().find(".batch_bg").hide();
			getEntertainment(this);
		}
	});
	//酒店标准变更
	$(document).on('change key', '.batch_bgLvl', function(event){
		getAccomadation(this);
	});
	//酒店变更
	$(document).on('change key', '.batch_accomadation', function(event){
		getRoom(this);
	});
	//获取酒店级别
	function getAccomadationLvl(obj){
		var flag = false;
		resMap.each(function(key,value,index){
			if (key == 'bgjb'){
				$(obj).parent().parent().find(".batch_bgLvl").html(value);
				flag = true;
			}
		});
		if(flag) return;
		$.ajax({
			type: "GET",
			traditional: true,
			url: "/dict/selectDict.htm",
			data: "dict.fsParentno=bg",
			dataType: "json",
			success: function(data){
				html = ''; 
				$.each(data, function(commentIndex, comment){
					html += '<option value=' + comment['fsDictno'] + '>' + comment['fsDictname'] + '</option>';
				});
				resMap.put("bgjb", html);
				if (obj == null) {
					$(".batch_bgLvl").html(html);
				} else {
					$(obj).parent().parent().find(".batch_bgLvl").html(html);
				}
			}
		});
	}
	//获取酒店列表
	function getAccomadation(obj){
		var flag = false;
		bg = 'bg' + $(obj).val();
		resMap.each(function(key,value,index){
			if (key == bg){
				$(obj).parent().parent().find(".batch_accomadation").html(value);
				flag = true;
			}
		});
		if(flag) return;
		$.ajax({
			type: "GET",
			traditional: true,
			url: "/accomadation/selectAccomadation.htm",
			data: "accomadation.starlvl=" + $(obj).val(),
			dataType: "json",
			success: function(data){
				var html = ''; 
				$.each(data, function(commentIndex, comment){
					html += '<option value=' + comment['no'] + '>' + comment['name'] + '</option>';
				});
				resMap.put("bg"+$(obj).val(), html);
				$(obj).parent().parent().find(".batch_accomadation").html(html);
			}
		});
	}
	//获取酒店房型列表
	function getRoom(obj){
		var flag = false;
		bg = 'bg' + $(obj).val();
		resMap.each(function(key,value,index){
			if (key == bg){
				$(obj).parent().parent().find(".batch_resno").html(value);
				flag = true;
			}
		});
		$.ajax({
			type: "GET",
			traditional: true,
			url: "/room/selectRoom.htm",
			data: "room.fsAccomno=" + $(obj).val(),
			dataType: "json",
			success: function(data){
				var html = ''; 
				$.each(data, function(commentIndex, comment){
					html += '<option value=' + comment['fsRoomno'] + '>' + comment['fsName'] + '</option>';
				});
				resMap.put("bg"+$(obj).val(), html);
				$(obj).parent().parent().find(".batch_resno").html(html);
			}
		});
	}
	function getDate(d, num){
		date=new Date(d);
		date.setDate(date.getDate() + parseInt(num));
        return date.getFullYear()+"/"+(date.getMonth()+1)+"/"+date.getDate();
    }
	
	//批次资源变更
	$(document).on('change key', '.batch_resno', function(event){
		var resno = $(this).val();
		var restype = $(this).parent().parent().find("#restype").val();
		var dayIndex = $(this).parent().parent().parent().next().find(".daylistIndex").val();
		date = getDate($("#ftStartdate").val(), dayIndex);
		params = 'ftStartdate='+date+'&ftEnddate='+date+'&fsResno='+resno+'&fsRestype='+restype;
		getTccprice(params, resno, date, $(this).parent().parent().parent().next().find(".batch_ccno"));
	});
	
	//资源变更
	$(document).on('change key', '.select_resno', function(event){
		var resno = $(this).val();
		var restype = $(this).parent().prev().prev().find("#restype").val();
		date = getDate($("#ftStartdate").val(),$(this).next().val());
		params = 'ftStartdate='+date+'&ftEnddate='+date+'&fsResno='+resno+'&fsRestype='+restype;
		getTccprice(params, resno, date, $(this).parent().parent().find("#ccno"));
	});
	
	function getTccprice(params, resno, date, obj){
		var flag = false;
		resMap.each(function(key,value,index){
			if (key == resno+date){
				html = '';
				$.each(value, function(commentIndex, comment){
					html += '<option value=' + comment['fsCcno'] + '>' + comment['fsCcname'] + '(' + comment['fdPrice'] + '￥)</option>';
				});
				$(".select_ccno").html(html);
				flag = true;
			}
		});
		if (flag) return;
		$.ajax({
			type: "GET",
			url: "/tccPrice/findTccPrice.htm",
			data: params,
			dataType: "json",
			success: function(data){
				if (data == null || data == '') {
					//alert("未配置资源价格！");
					$(obj).html('');
					return;
				}
				html = '';
				$.each(data, function(commentIndex, comment){
					html += '<option value=' + comment['fsCcno'] + '>' + comment['fsCcname'] + '(' + comment['fdPrice'] + '￥)</option>';
				});
				resMap.put(resno+date, data);
				$(obj).html(html);
			}
		});
	}
	
	var resMap = new Map();
	//获取景区门票列表
	function getTicket(obj){
		//从map中获取景区门票列表内容
		var flag = false;
		resMap.each(function(key,value,index){
			if (key == 'ticket'){
				$(obj).parent().parent().find(".select_resno").html(value);
				flag = true;
			}
		});
		if (flag) return;
		var scenic = '';
		$(".scenic").each(function(){
			scenic += $(this).val() + ",";
		});
		var resno = '';
		$.ajax({
			type: "GET",
			traditional: true,
			url: "/ticket/selectTicket.htm",
			data: "scenicno=" + scenic,
			dataType: "json",
			success: function(data){
				var html = ''; 
				$.each(data, function(commentIndex, comment){
					html += '<option value=' + comment['fsNo'] + '>' + comment['fsName'] + '</option>';
				});
				resMap.put('ticket', html);
				if (obj == null) {
					$(".select_resno").html(html);
				}
				$(obj).parent().parent().find(".select_resno").html(html);
			}
		});
	}
	//获取景区餐厅列表
	function getRestaurant(obj){
		var flag = false;
		resMap.each(function(key,value,index){
			if (key == 'restaurant'){
				$(obj).parent().parent().find(".select_resno").html(value);
				flag = true;
			}
		});
		if (flag) return;
		var dataArr = new Array();
		$(".scenic").each(function(i, item){
			dataArr.push($(item).val());
		});
		$.ajax({
			type: "POST",
			url: "/restaurant/selectRestaurant.htm",
			data: {"scenicNo" : dataArr},
			dataType: "json",
			success: function(data){
				var html = ''; 
				$.each(data, function(commentIndex, comment){
					html += '<option value=' + comment['no'] + '>' + comment['name'] + '</option>';
				});
				resMap.put('restaurant', html);
				$(obj).parent().parent().find(".select_resno").html(html);
			}
		});
	}
	//获取购物店列表
	function getShop(obj){
		var flag = false;
		resMap.each(function(key,value,index){
			if (key == 'shop'){
				$(obj).parent().parent().find(".select_resno").html(value);
				flag = true;
			}
		});
		if (flag) return;
		var scenic = '';
		$("input[name='scenicGen']").each(function(){
			scenic += $(this).val() + ",";
		});
		$.ajax({
			type: "GET",
			traditional: true,
			url: "/shop/selectShop.htm",
			data: "scenicno=" + scenic,
			dataType: "json",
			success: function(data){
				var html = ''; 
				$.each(data, function(commentIndex, comment){
					html += '<option value=' + comment['no'] + '>' + comment['name'] + '</option>';
				});
				resMap.put('shop', html);
				$(obj).parent().parent().find(".select_resno").html(html);
			}
		});
	}
	//获取景区娱乐项目列表
	function getEntertainment(obj){
		var flag = false;
		resMap.each(function(key,value,index){
			if (key == 'entertainment'){
				$(".select_resno").html(value);
				flag = true;
			}
		});
		var dataArr = new Array();
		$(".scenic").each(function(i, item){
			dataArr.push($(item).val());
		});
		$.ajax({
			type: "POST",
			url: "/entertainment/selectEntertainment.htm",
			data: {"scenicNo" : dataArr},
			dataType: "json",
			success: function(data){
				var html = ''; 
				$.each(data, function(commentIndex, comment){
					html += '<option value=' + comment['fsNo'] + '>' + comment['fsName'] + '</option>';
				});
				resMap.put('entertainment', html);
				$(obj).parent().parent().find(".batch_resno").html(html);
			}
		});
	}
	
	//添加资源项
	$(document).on('click key', '.btn_res', function(event){
		var ccno = $(this).parent().parent().find("#ccno").val();
		if (ccno == null) {
			alert("请选择消费项目再进行添加！");
			return;
		}
		//下标
		var reslistIndex = $(this).parent().find(".reslistIndex").val();
		//日期
		var daylistIndex = $(this).parent().find(".daylistIndex").val();
		var restype = $(this).parent().parent().find("#restype").val();
		var resno = $(this).parent().parent().find("#resno").val();
		var resname = $(this).parent().parent().find("#resno").find("option:selected").text();
		if (restype == 'gw') {
			ccno = '';
		}
		var data = {
				"index" : reslistIndex,
				"restype" : restype,
				"resprop" : "prop",
				"resno" : resno,
				"dayflag" : daylistIndex,
				"resname" : resname,
				"ccno" : ccno
		}
		var dayflag = $(this).parent().parent().find("#dayflag").val();
		date = getDate($("#ftStartdate").val(), daylistIndex);
		//获取资源价格
		var price,ccname = '';
		resMap.each(function(key,value,index){
			if (key == resno+date){
				$.each(value, function(commentIndex, comment){
					if(comment['fsCcno'] == ccno){
						price = comment['fdPrice'];
						ccname = comment['fsCcname'];
						return;
					}
	        	});
			}
		});
		data["ccname"] = ccname;
		data["price"] = price;
		data["cctype"] = "0";
		data["usernum"] = $(this).parent().parent().find("#usernum").val();
		var template = '';
		if (restype == 'cx' || restype == 'dy' || restype == 'jq') {
			//导游、车型模板
			template = Handlebars.compile($("#tr-common").html());
		} else {
			//门票、餐厅、购物模板
			template = Handlebars.compile($("#tr-common1").html());
		}
		$("#table_common" + dayflag + " tbody").html($("#table_common" + dayflag + " tbody").html() + template(data));
		totalAmount();
	});
	
	//添加批次资源项
	$(document).on('click key', '.btn_batch', function(event){
		var ccno = $(this).parent().parent().find(".batch_ccno").val();
		if (ccno == null) {
			alert("请选择消费项目再进行添加！");
			return;
		}
		//reslist下标
		var reslistIndex = $(this).parent().find(".reslistIndex").val();
		//批次下标
		var batchIndex = $(this).parent().find(".batchIndex").val();
		//日期
		var dayflag = $(this).parent().find(".daylistIndex").val();
		//资源名称所在的div
		var prevDiv = $(this).parent().parent().parent().prev();
		//alert();
		
		var restype = prevDiv.find("#restype").val();
		var resno = prevDiv.find("#batch_resno").val();
		var resname = prevDiv.find("#batch_resno").find("option:selected").text();
		var data = {
				"fiId" : batchIndex,
				"dayflag" : dayflag,
				"index" : reslistIndex,
				"restype" : restype,
				"resprop" : "prop",
				"resno" : resno,
				"resname" : resname,
				"ccno" : ccno
		}
		date = getDate($("#ftStartdate").val(), dayflag);
		//获取资源价格
		var price,ccname = '';
		resMap.each(function(key,value,index){
			if (key == resno+date){
				$.each(value, function(commentIndex, comment){
					if(comment['fsCcno'] == ccno){
						price = comment['fdPrice'];
						ccname = comment['fsCcname'];
						return;
					}
				});
			}
		});
		data["ccname"] = ccname;
		data["price"] = price;
		data["cctype"] = "0";
		data["usernum"] = $(this).parent().parent().find("#usernum").val();
		var template = Handlebars.compile($("#tr-batch").html());
		$("#table_batch"+ batchIndex +'_'+ dayflag + " tbody").html($("#table_batch"+ batchIndex +'_'+ dayflag + " tbody").html() + template(data));
		totalAmount();
	});
	
	//查询订单所有批次信息
	$.ajax({
		type: "POST",
		url: "/orderCustom/selectOrderCustom.htm",
		data: 'fsOrderId='+fsNo,
		dataType: "json",
		success: function(data){
			var orderCustoms = {
				"orderCustoms" : data
			}
			var batchTemplate = Handlebars.compile($("#batch-template").html());
			//返回数组长度
			$('#table_batch tbody').html(batchTemplate(orderCustoms));
		}
	});

	//返回资源类型
	Handlebars.registerHelper("getType",function(value){
		if(value == 'cx'){
			return '车型';
		} else if(value == 'dy'){
			return '导游';
		} else if(value == 'jq'){
			return '景区';
		} else if(value == 'mp'){
			return '门票';
		} else if(value == 'ct'){
			return '餐厅';
		} else if(value == 'bg'){
			return '宾馆';
		} else if(value == 'yl'){
			return '娱乐';
		} else if(value == 'gw'){
			return '购物';
		}
	});
	//生成批次资源表头tabli
	Handlebars.registerHelper('isActive_li',function(index) {
		if(index == 0){
			return 'active';
		} else {
			return '';
		}
	});
	//生成批次资源表头tab_pane
	Handlebars.registerHelper('isActive_pane',function(index) {
		if(index == 0){
			return 'in active';
		} else {
			return '';
		}
	});
	//根据计划天数生成tab_li头
	Handlebars.registerHelper('getTab_li',function() {
		html = '<li class="active"><a href="#common" data-toggle="tab">车型/导游/景区</a></li>';
		for (i = 0; i < $("#days").val(); i++) {
			html += '<li><a href="#day'+i+'" data-toggle="tab">第'+parseInt(i+1)+'天</a></li>'
		}
		$("#myTab").html(html);
		return;
	});
	
	//导游级别变更
	$(document).on('click key', '.tr_batch', function(event){
		//显示
		if($(this).next().attr("style") == 'display: none;'){
			$(".tr_batch").next().hide();
			$(this).next().fadeIn("8000");
			$(this).next().show();
		} else {
			//隐藏
			$(this).next().fadeIn("8000");
			$(this).next().hide();
		}
	});
	//合计订单金额
	function totalAmount(){
		var totalAmt = 0;
		$(".price").each(function(){
			var price = $(this).val();
			var usernum = $(this).next().val();
			if(isNaN(price) || isNaN(usernum) || price=='' || usernum==''){
				return;
			}
			totalAmt = parseFloat(totalAmt) + parseInt(usernum) * parseFloat(price);
		});
		totalAmt = parseFloat(totalAmt) + parseFloat($("#insuerprice").val());
		$("#fdTotalfee").val(totalAmt.toFixed(2));
	}
	
	//	提交
	$("#submit").on("click", function () {
		$.post("/orderlist/editOrderlist4custom.htm",
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
	
	//下载文件
	$(document).on('click key', '.btn_downFile', function(event){
		//文件路径
		var url = $(this).next().val();
		window.location.href = url;
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
