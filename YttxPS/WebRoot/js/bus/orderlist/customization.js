jQuery(function($) {
	$("#message").hide();
	$("#reset").click();
	var fsNo = $.getUrlParam('fsNo');
	var startdate = $.getUrlParam('startdate');

	var localsel = $("#selectCity", "#editform").localCity({

		provurl : "/pub/findcity.htm",
		cityurl : "/pub/findcity.htm",
		disturl : "/pub/findcity.htm",
		callback : localcallback
	});
	
	var stat = $("#fiStat").val();
	if (stat == '-10') {
		$(".div_transfer").hide();
	} else if (stat == '-5') {
		$(".div_transfer").hide();
	} else if (stat == '0') {
		$(".div_transfer_stat").show();
	} else if (stat == '1') {
		$(".div_custList").show();
	} else if (stat == '6') {
		$(".div_custList").show();
	} else if (stat == '8') {
		$(".div_custList").show();
	} else if (stat == '32'){
		$("#submit").hide();
	}

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
	//交通方式是否选中
	Handlebars.registerHelper("isSelected",function(v1, v2){
		if (v1 == v2) {
			return 'selected="selected"';
		} else {
			return "";
		}
	});
	//备注状态
	Handlebars.registerHelper("remarkStat",function(v1){
		html = '';
		if (v1 == '0') {
			html = '<option value="0">未付款</option>' +
				   '<option value="1">已付款</option>';
			return html;
		} else if(v1 == '1'){
			return '<option value="1">已付款</option>';
		} else {
			return '<option value="2">作废</option>';
		}
	});
	//备注操作
	Handlebars.registerHelper("remarkOption",function(v1){
		if (v1 == '0') {
			return '<a style="cursor:pointer;" onclick="invalid(this)">作废</a>';
		} else {
			return '';
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
				//成人
				$("#adult").val(data.fcSchedule.adult);
				//儿童
				$("#children").val(data.fcSchedule.children);
				//全陪
				$("#fullguide").val(data.fcSchedule.fullguide);
				//报价
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
				$('#tbody_common').html(commResTemplate1(data.commResSnapshot));
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
				getDictLvl(null, 'bg');
				//显示合计金额
				setTimeout(totalAmount, 2000);
				setTimeout(totalCommAmt, 5000);
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
				$("#scenic").chosen("destroy"); 
				$("#scenic").chosen();
			}
		});
	}
	
	//增加景区addScenic
	$(document).on('click key', '.addScenicBtn', function(event){
		var index = $("#reslistIndex").val();
		var data={
				"index" : index,
				"restype" : "jq",
				"resprop" : "comm",
				"resno" : $("#scenic").val(),
				"resname" : $("#scenic").find("option:selected").text()
		}
		var template = Handlebars.compile($("#tr-common").html());
		$("#table_common tbody").html($("#table_common tbody").html() + template(data));
		resMap.put("isChange_jq", true);
		$("#reslistIndex").val(parseInt(index)+1);
		//获取景区门票列表
		getTicket(null);
		$(".restype").val("mp");
		$(".select_ccno").show();
		//获取酒店级别列表
		$(".batch_ct").hide();
		$(".batch_lvl").show();
		$(".batch_bg").show();
		$(".batch_restype").val("bg");
		$(".batch_resname").html("房型");
		getDictLvl(null, 'bg');
	});
	
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
			data: {
				"guide.lvl" : $("#guideLvl").val(),
				"guide.stat" : "1"
			},
			dataType: "json",
			success: function(data){
				var html = ''; 
				$.each(data, function(commentIndex, comment){
					html += '<option value=' + comment['no'] + '>' + comment['name'] + '</option>';
				});
				$("#guide").html(html);
				$("#guideName").attr("value", $("#guideNo").find("option:selected").text());
				$("#guide").chosen("destroy");
				$("#guide").chosen();
			}
		});
	}
	//导游级别变更
	$(document).on('change key', '#guideLvl', function(event){
		getGuide();
	});
	//资源类型变更
	$(document).on('change key', '#restype', function(event){
		var restype = $(this).val();
		if(restype == 'mp'){
			$(this).parent().parent().find(".select_ccno").show();
			getTicket(this);
		}
		if(restype == 'ct'){
			$(this).parent().parent().find(".batch_Lvl").html('<option value="01"></option>');
			$(this).parent().parent().find(".batch_bg").hide();
			$(this).parent().parent().find(".batch_lvl").show();
			$(this).parent().parent().find(".batch_ct").show();
			$(this).parent().parent().find(".batch_resname").html('菜单');
			$(this).parent().parent().parent().next().find(".tccPrice").val(0);
			getDictLvl(this, 'ct');
			getRestaurant(this);
		}
		if(restype == 'gw'){
			getShop(this);
			$(this).parent().parent().find(".select_ccno").hide();
		}
		if(restype == 'bg'){
			$(this).parent().parent().find(".batch_ct").hide();
			$(this).parent().parent().find(".batch_lvl").show();
			$(this).parent().parent().find(".batch_bg").show();
			$(this).parent().parent().find(".batch_resno").html('');
			$(this).parent().parent().parent().next().find(".batch_ccno").html('');
			$(this).parent().parent().parent().next().find(".tccPrice").val(0);
			$(this).parent().parent().find(".batch_resname").html('房型');
			getDictLvl(this, 'bg');
		}
		if(restype == 'yl'){
			$(this).parent().parent().parent().next().find(".batch_ccno").html('');
			$(this).parent().parent().parent().next().find(".tccPrice").val(0);
			$(this).parent().parent().find(".batch_resname").html('资源');
			$(this).parent().parent().find(".batch_bg").hide();
			$(this).parent().parent().find(".batch_lvl").hide();
			$(this).parent().parent().find(".batch_ct").hide();
			getEntertainment(this);
		}
	});
	//酒店变更
	$(document).on('change key', '.batch_accomadation', function(event){
		getRoom(this);
	});
	//获取字典标准
	function getDictLvl(obj, type){
		$.ajax({
			type: "GET",
			traditional: true,
			url: "/dict/selectDict.htm",
			data: "dict.fsParentno="+type,
			dataType: "json",
			success: function(data){
				html = ''; 
				$.each(data, function(commentIndex, comment){
					html += '<option value=' + comment['fsDictno'] + '>' + comment['fsDictname'] + '</option>';
				});
				//resMap.put(type, html);
				if (obj == null) {
					$(".batch_Lvl").html(html);
					$(".batch_Lvl").each(function(){
						getAccomadation(this)
					});
				} else {
					$(obj).parent().parent().find(".batch_Lvl").html(html);
					resType = $(obj).parent().parent().find("#restype").val();
					if (resType == 'ct'){
						//getRestaurant(obj);
					} else if (resType == 'bg') {
						getRoom($(obj).parent().parent().find(".batch_accomadation"));
					}
				}
			}
		});
	}
	//获取酒店列表
	function getAccomadation(obj){
		var dataArr = new Array();
		$(".scenic").each(function(i, item){
			dataArr.push($(item).val());
		});
		$.ajax({
			type: "GET",
			traditional: true,
			url: "/accomadation/selectAccomadation.htm",
			data: {"scenicNo" : dataArr,
				"accomadation.starlvl" : $(obj).val(),
				"accomadation.stat" : "1"
				},
			dataType: "json",
			success: function(data){
				if (data == '' || data == null) {
					$(obj).parent().parent().find(".batch_accomadation").html('<option>' + "未查询到酒店" + '</option>');
					$(obj).parent().parent().find(".batch_resno").html('');
					$(obj).parent().parent().parent().next().find(".batch_ccno").html('');
					$(obj).parent().parent().parent().next().find(".tccPrice").val(0);
				} else {
					var html = ''; 
					$.each(data, function(commentIndex, comment){
						html += '<option value=' + comment['no'] + '>' + comment['name'] + '</option>';
					});
					$(obj).parent().parent().find(".batch_accomadation").html(html);
					getRoom($(obj).parent().parent().find(".batch_accomadation"));
				}
				$(obj).parent().parent().find(".batch_accomadation").attr("class", "width-80 chosen-select batch_accomadation form-control");
				$(obj).parent().parent().find(".batch_accomadation").chosen("destroy");
				$(obj).parent().parent().find(".batch_accomadation").chosen(); 
				$(obj).parent().parent().find(".batch_accomadation").next().attr("style","width:140px;"); 
			}
		});
	}
	//获取酒店房型列表
	function getRoom(obj){
		$.ajax({
			type: "GET",
			traditional: true,
			url: "/room/selectRoom.htm",
			data: {
				"room.fsAccomno" : $(obj).val(),
				"room.fiStat" : "1"
			},
			dataType: "json",
			success: function(data){
				if (data == '' || data == null) {
					$(obj).parent().parent().find(".batch_resno").html('');
					$(obj).parent().parent().parent().next().find(".batch_ccno").html('');
				} else {
					var html = ''; 
					$.each(data, function(commentIndex, comment){
						html += '<option value=' + comment['fsRoomno'] + '>' + comment['fsName'] + '</option>';
					});
					$(obj).parent().parent().find(".batch_resno").html(html);
					var resno = $(obj).parent().parent().find(".batch_resno").val();
					var dayIndex = $(obj).parent().parent().parent().next().find(".daylistIndex").val();
					date = getDate(startdate, dayIndex);
					var restype = $(obj).parent().parent().find("#restype").val();
					params = 'ftStartdate='+date+'&ftEnddate='+date+'&fsResno='+resno+'&fsRestype='+restype;
					getTccprice(params, resno, date, $(obj).parent().parent().parent().next().find(".batch_ccno"));
				}
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
		date = getDate(startdate, dayIndex);
		params = 'ftStartdate='+date+'&ftEnddate='+date+'&fsResno='+resno+'&fsRestype='+restype;
		getTccprice(params, resno, date, $(this).parent().parent().parent().next().find(".batch_ccno"));
	});
	
	//资源变更
	$(document).on('change key', '.select_resno', function(event){
		var resno = $(this).val();
		var restype = $(this).parent().prev().prev().find("#restype").val();
		date = getDate(startdate,$(this).next().val());
		params = 'ftStartdate='+date+'&ftEnddate='+date+'&fsResno='+resno+'&fsRestype='+restype;
		getTccprice(params, resno, date, $(this).parent().parent().find("#ccno"));
	});
	
	//批次资源消费项变更
	$(document).on('change key', '.batch_ccno', function(event){
		var ccno = $(this).val();
		var resno = $(this).parent().parent().parent().prev().find("#batch_resno").val();
		var daylistIndex = $(this).parent().parent().find(".daylistIndex").val();
		date = getDate(startdate, daylistIndex);
		var price,ccname = '';
		resMap.each(function(key,value,index){
			if (key == resno+date){
				$.each(value, function(commentIndex, comment){
					if(comment['fsCcno'] == ccno){
						price = comment['fdPrice'];
						return;
					}
	        	});
			}
		});
		$(this).parent().parent().find(".tccPrice").val(price);
	});
	
	//菜单类型变更（午餐、晚餐）
	$(document).on('change key', '.ct_special', function(event){
		getRestaurant(this);
	});
	
	//餐标类型变更（人均）
	$(document).on('change key', '.batch_Lvl', function(event){
		resType = $(this).parent().parent().find("#restype").val();
		if (resType == 'ct'){
			getRestaurant(this);
		} else if (resType == 'bg') {
			getAccomadation(this);
		}
	});
	
	function getTccprice(params, resno, date, obj){
		
		$.ajax({
			type: "GET",
			url: "/tccPrice/findTccPrice.htm",
			data: params,
			dataType: "json",
			success: function(data){
				if (data == null || data == '') {
					//alert("未配置资源价格！");
					$(obj).html('');
					$(obj).parent().parent().find(".tccPrice").val(0);
					return;
				}
				html = '';
				$.each(data, function(commentIndex, comment){
					if (commentIndex == 0) {
						$(obj).parent().parent().find(".tccPrice").val(comment['fdPrice']);
					}
					html += '<option value=' + comment['fsCcno'] + '>' + comment['fsCcname'] + '(' + comment['fdPrice'] + '￥)</option>';
				});
				resMap.put(resno+date, data);
				$(obj).html(html);
			}
		});
	}
	
	var resMap = new Map();
	//景区是否变更
	var isChange_jq = false;
	resMap.put("isChange_jq", isChange_jq);
	//获取景区门票列表
	function getTicket(obj){
		var scenic = '';
		$(".scenic").each(function(){
			scenic += $(this).val() + ",";
		});
		var resno = '';
		$.ajax({
			type: "GET",
			traditional: true,
			url: "/ticket/selectTicket.htm",
			data: {
				"scenicno" : scenic,
				"ticket.fiStat" : "1"
			},
			dataType: "json",
			success: function(data){
				var html = ''; 
				$.each(data, function(commentIndex, comment){
					html += '<option value=' + comment['fsNo'] + '>' + comment['fsName'] + '</option>';
				});
				if (obj == null) {
					$(".select_resno").html(html);
					$(".select_ccno").each(function(){
						setTimeout("4000");
						var resno = $(this).parent().parent().find("#resno").val();
						var restype = $(this).parent().parent().find("#restype").val();
						date = getDate(startdate, $(this).parent().parent().find("#dayflag").val());
						params = 'ftStartdate='+date+'&ftEnddate='+date+'&fsResno='+resno+'&fsRestype='+restype;
						getTccprice(params, resno, date, $(this).parent().parent().find("#ccno"));
					});
				} else {
					$(obj).parent().parent().find(".select_resno").html(html);
					var resno = $(obj).parent().parent().find("#resno").val();
					var restype = $(obj).parent().parent().find("#restype").val();
					date = getDate(startdate, $(obj).parent().parent().find("#dayflag").val());
					params = 'ftStartdate='+date+'&ftEnddate='+date+'&fsResno='+resno+'&fsRestype='+restype;
					getTccprice(params, resno, date, $(obj).parent().parent().find("#ccno"));
				}
			}
		});
	}
	//获取景区菜单列表
	function getRestaurant(obj){
		var dataArr = new Array();
		$(".scenic").each(function(i, item){
			dataArr.push($(item).val());
		});
		$.ajax({
			type: "POST",
			url: "/restaurant/selectRestaurant.htm",
			data: {"scenicNo" : dataArr,
				   "lvl" : $(obj).parent().parent().find(".batch_Lvl").val(),
				   "special" : $(obj).parent().parent().find(".ct_special").val(),
				   "stat" : "1"
				},
			dataType: "json",
			success: function(data){
				if(data == null || data == ''){
					$(obj).parent().parent().find(".batch_resno").html('');
					$(obj).parent().parent().parent().next().find(".batch_ccno").html('');
				} else {
					var html = ''; 
					$.each(data, function(commentIndex, comment){
						html += '<option value=' + comment['no'] + '>' + comment['name'] + '</option>';
					});
					$(obj).parent().parent().find(".batch_resno").html(html);
					var resno = $(obj).parent().parent().find("#batch_resno").val();
					var restype = $(obj).parent().parent().find("#restype").val();
					date = getDate(startdate, $(obj).parent().parent().parent().next().find(".daylistIndex").val());
					params = 'ftStartdate='+date+'&ftEnddate='+date+'&fsResno='+resno+'&fsRestype='+restype;
					getTccprice(params, resno, date, $(obj).parent().parent().parent().next().find(".batch_ccno"));
				}
			}
		});
	}
	//获取购物店列表
	function getShop(obj){
		var scenic = '';
		$(".scenic").each(function(){
			scenic += $(this).val() + ",";
		});
		$.ajax({
			type: "GET",
			traditional: true,
			url: "/shop/selectShop.htm",
			data: {
				"scenicno" : scenic,
				"shop.stat" : "1"
			},
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
		var dataArr = new Array();
		$(".scenic").each(function(i, item){
			dataArr.push($(item).val());
		});
		$.ajax({
			type: "POST",
			url: "/entertainment/selectEntertainment.htm",
			data: {
				"scenicNo" : dataArr,
				"stat" : "1"
			},
			dataType: "json",
			success: function(data){
				var html = ''; 
				$.each(data, function(commentIndex, comment){
					html += '<option value=' + comment['fsNo'] + '>' + comment['fsName'] + '</option>';
				});
				resMap.put('entertainment', html);
				$(obj).parent().parent().find(".batch_resno").html(html);
				var resno = $(obj).parent().parent().find("#batch_resno").val();
				var restype = $(obj).parent().parent().find("#restype").val();
				date = getDate(startdate, $(obj).parent().parent().parent().next().find(".daylistIndex").val());
				params = 'ftStartdate='+date+'&ftEnddate='+date+'&fsResno='+resno+'&fsRestype='+restype;
				getTccprice(params, resno, date, $(obj).parent().parent().parent().next().find(".batch_ccno"));
			}
		});
	}
	
	//添加资源项
	$(document).on('click key', '.btn_res', function(event){
		var ccno = $(this).parent().parent().find("#ccno").val();
		var restype = $(this).parent().parent().find("#restype").val();
		if (ccno == null && restype != 'gw') {
			alert("请选择消费项目再进行添加！");
			return;
		}
		//下标
		var reslistIndex = $(this).parent().find(".reslistIndex").val();
		//日期
		var daylistIndex = $(this).parent().find(".daylistIndex").val();
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
		date = getDate(startdate, daylistIndex);
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
		if (restype != 'gw') {
			data["usernum"] = $(this).parent().parent().find("#usernum").val();
		}
		var template = '';
		if (restype == 'cx' || restype == 'dy' || restype == 'jq') {
			//导游、车型模板
			template = Handlebars.compile($("#tr-common").html());
		} else {
			//门票、餐厅、购物模板
			template = Handlebars.compile($("#tr-common1").html());
		}
		$("#table_common" + dayflag + " tbody").html($("#table_common" + dayflag + " tbody").html() + template(data));
		$(this).parent().find(".reslistIndex").val(parseInt(reslistIndex)+1);
		totalAmount();
		totalCommAmt();
	});
	
	//添加批次资源项
	$(document).on('click key', '.btn_batch', function(event){
		var ccno = $(this).parent().parent().find(".batch_ccno").val();
		var tccprice = $(this).parent().parent().find(".tccPrice").val();
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
		var batch_Lvl = prevDiv.find(".batch_Lvl").find("option:selected").text();
		var accomadationName = prevDiv.find(".batch_accomadation").find("option:selected").text();
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
		date = getDate(startdate, dayflag);
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
		if (tccprice != null && tccprice != ''){
			price = tccprice;
		}
		if (restype == 'bg') {
			data["resname"] = batch_Lvl + '-' + accomadationName + '-' + resname;
		}
		data["ccname"] = ccname;
		data["price"] = price;
		data["cctype"] = "0";
		data["usernum"] = $(this).parent().parent().find("#usernum").val();
		var template = Handlebars.compile($("#tr-batch").html());
		$("#table_batch"+ batchIndex +'_'+ dayflag + " tbody").html($("#table_batch"+ batchIndex +'_'+ dayflag + " tbody").html() + template(data));
		$(this).parent().find(".reslistIndex").val(parseInt(reslistIndex)+1);
		totalAmount();
		totalBatchAmt();
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
			return '菜单';
		} else if(value == 'bg'){
			return '酒店';
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
	
	//批次信息切换
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
		var remarkAmt = 0;
		$(".price").each(function(){
			var price = $(this).val();
			var usernum = $(this).next().val();
			if(isNaN(price) || isNaN(usernum) || price=='' || usernum==''){
				return;
			}
			totalAmt = parseFloat(totalAmt) + parseFloat(usernum) * parseFloat(price);
		});
		$(".remarkPrice").each(function(){
			var price = $(this).val();
			var stat = $(this).parent().parent().find(".remark_stat").val();
			if(isNaN(price) || isNaN(stat) || price=='' || stat=='2'){
				return;
			}
			remarkAmt = parseFloat(remarkAmt) + parseFloat(price);
		});
		totalAmt = parseFloat(totalAmt) + parseFloat($("#fdInsuerprice").val());
		remarkAmt = parseFloat(remarkAmt) + parseFloat(totalAmt);
		$("#fdTotalfee").val(totalAmt.toFixed(2));
		$("#totalfee").val(remarkAmt.toFixed(2));
	}
	
	//合计公共资源金额
	function totalCommAmt(){
		//循环批次
		var fdAmt = 0;
		$(".div_comm").each(function(){
			$(this).find(".price").each(function(){
				var price = $(this).val();
				var usernum = $(this).next().val();
				if(isNaN(price) || isNaN(usernum) || price=='' || usernum==''){
					return;
				}
				fdAmt = parseFloat(fdAmt) + parseFloat(usernum) * parseFloat(price);
			});
		});
		$("#commAmt").val(fdAmt);
	}
	
	//合计订单批次金额
	function totalBatchAmt(){
		//循环批次
		$(".batch_div").each(function(){
			var fdAmt = 0;
			$(this).find(".price").each(function(){
				var price = $(this).val();
				var usernum = $(this).next().val();
				if(isNaN(price) || isNaN(usernum) || price=='' || usernum==''){
					return;
				}
				fdAmt = parseFloat(fdAmt) + parseFloat(usernum) * parseFloat(price);
			});
			$(this).parent().next().find("#fdAmt").val(fdAmt);
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
	
	//添加备注项
	$(document).on('click key', '#btn_remarks', function(event){
		$("#message").hide();
		if($("#remarksAmt").valid() && $("#fsContent").valid()){
			var remarksTemplate = Handlebars.compile($("#tr-remarks").html());
			var fsOrderId = $("#fsNo").val();
			var reslistIndex = $("#remarksIndex").val();
			var ftDate = getNowFormatDate();
			var fsContent = $("#fsContent").val();
			var fdAmt = $("#remarksAmt").val();
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
			$('#table_remarks tbody').html($('#table_remarks tbody').html() + remarksTemplate(data));
			$("#fsContent").val('');
			$("#remarksAmt").val('');
			
			totalAmt = parseFloat($("#totalfee").val()) + parseFloat(fdAmt);
			$("#totalfee").val(totalAmt.toFixed(2));
		} else {
			$("#message").text("输入字段验证错误，请重新编辑后再提交");
			$("#message").show();
			return false;
		}
	});
	
	//	提交
	$("#submit").on("click", function () {
		if ($("#editform").valid()) {
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
		} else {
			$("#message").text("输入字段验证错误，请重新编辑后再提交");
			$("#message").show();
			return false;
		}
	});
	
	function getNowFormatDate() {
	    var date = new Date();
	    var seperator1 = "/";
	    var seperator2 = ":";
	    var month = date.getMonth() + 1;
	    var strDate = date.getDate();
	    var hours = date.getHours();
	    var minutes = date.getMinutes();
	    var seconds = date.getSeconds();
	    if (month >= 1 && month <= 9) {
	        month = "0" + month;
	    }
	    if (strDate >= 0 && strDate <= 9) {
	        strDate = "0" + strDate;
	    }
	    if (hours >= 0 && hours <= 9) {
	    	hours = "0" + hours;
	    }
	    if (minutes >= 0 && minutes <= 9) {
	    	minutes = "0" + minutes;
	    }
	    if (seconds >= 0 && seconds <= 9) {
	    	seconds = "0" + seconds;
	    }
	    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
	            + " " + hours + seperator2 + minutes
	            + seperator2 + seconds;
	    return currentdate;
	}
	//下载文件
	$(document).on('click key', '.btn_downFile', function(event){
		//文件路径
		var url = $(this).next().val();
		location.href = url;
	});
	
	//下载游客名单
	$(document).on('click key', '.btn_export', function(event){
		var orderId = $("#fsNo").val();
		var parm = "";
		if(orderId == ''){
			alert("订单号不能为空");
			return;
		}
		parm+="&orderId="+encodeURIComponent(orderId);
		window.location.href = '../../orderlist/exportOrderCusList.htm?1=1'+parm;
	});
	
	//上传游客名单
	$(document).on('click key', '.btn_import', function(event){
		$("#orderId").val(fsNo);
		save();
	});
	
	//保存
	function save(){
		if($("#excel").val()=="" || document.getElementById("excel").files[0] =='请选择xls格式的文件'){
			$("#excel").tips({
				side:3,
	            msg:'请选择文件',
	            bg:'#AE81FF',
	            time:3
	        });
			return false;
		}
		$("#excel1").val($("#excel").val());
		var formData = new FormData($("#uploadForm")[0]);
		$.ajax({  
	          url: '../../orderlist/importExcel.htm' ,  
	          type: 'POST',  
	          data: formData,  
	          async: false,  
	          cache: false,  
	          contentType: false,  
	          processData: false,  
	          success: function (data) { 
	  			$("#upload_message").text(data);
	  			$("#upload_message").show();
	          }
	     });
	}
	
	//删除资源项
	$(document).on('click key', '.removeTr', function(obj){
		type = $(this).parent().find("#restype").val();
		$(this).parent().parent().remove();
		if(type == 'jq'){
			//获取景区门票列表
			getTicket(null);
			$(".restype").val("mp");
			$(".select_ccno").show();
			//获取酒店级别列表
			$(".batch_ct").hide();
			$(".batch_lvl").show();
			$(".batch_bg").show();
			$(".batch_restype").val("bg");
			$(".batch_resno").parent().prev().html("房型");
			getDictLvl(null, 'bg');
		}
		totalAmount();
		totalCommAmt();
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
