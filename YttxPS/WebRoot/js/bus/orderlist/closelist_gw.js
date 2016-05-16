jQuery(function($) {
	//加载购物店下拉列表
	var map = new Map();
	$.ajax({
		type: "GET",
		traditional: true,
		url: "/shop/selectShop.htm",
		data: {
			"shop.stat" : "1"
		},
		dataType: "json",
		success: function(data){
			var html = ''; 
			$.each(data, function(index, comment){
				html += '<option value=' + comment['no'] + '>' + comment['name'] + '</option>';
			});
			$("#shop").html(html);
			$("#shop").attr("class", "width-80 chosen-select form-control");
			$("#shop").chosen(); 
			$("#shop").next().attr("style","width:240px;");
			map.put("shop", html);
		}
	});

	//购物项目
	$.ajax({
		type: "GET",
		url: "/dict/selectDict.htm",
		data : {"dict.fsParentno" : "gw_xf"},
		dataType: "json",
		success: function(data){
			var html = '';
			$.each(data, function(commentIndex, comment){
				html += '<option value='+ comment['fsDictno'] +'>'+ comment['fsDictname'] +'</option>';
			});
			$("#typeno").html(html);
			$("#typeno").attr("class", "width-80 chosen-select form-control");
			$("#typeno").chosen(); 
			$("#typeno").next().attr("style","width:240px;");
		}
	});

	//购物店变更事件
	$(document).on('change key', '#shop', function(event){
		$("#resno_gw").val($("#shop").find("option:selected").text());
	});

	//计算人头费
	$(document).on('change key', '.peopleprofit', function(event){
		var people = $("#people").val();
		var unitprice = $("#unitprice").val();
		if(isNaN(people) || isNaN(unitprice) || people == '' || unitprice == ''){
			$("#peopleprofit").val(0);
			return;
		}
		$("#peopleprofit").val(ignoreSingleDigits(parseFloat(people) * parseFloat(unitprice)));
	});

	//计算返佣金额
	$(document).on('change key', '.profit', function(event){
		var consumption = $("#consumption").val();
		var proportion = $("#proportion").val();
		if(isNaN(consumption) || isNaN(proportion) || consumption == '' || proportion == ''){
			$("#profit").val(0);
			return;
		}
		$("#profit").val(ignoreSingleDigits(parseFloat(consumption) * parseFloat(proportion) * parseFloat(0.01)));
	});

	//打开模态框
	$(document).on('click', '#btn_gw_add', function(event){
		$(".tr_cclist").remove();
		$("#gw").find("input").val('');
		$("#form_gw").find("input").val('');
		$("#message_gw").hide();
		$("label.error").remove();
		$("#resno_gw").val($("#shop").find("option:selected").text());
		$('#addModal').modal('show');
	});

	//新增购物项目
	$(document).on('click', '.btn_cclist_add', function(event){
		if($("#consumption").valid() && $("#proportion").valid()){
			index = $("#gwxm_index").val();
			var data = {"name" : $("#shop").find("option:selected").text(),cclist:[{
				"typeno" : $("#typeno").val(),
				"typename" : $("#typeno").find("option:selected").text(),
				"consumption" : $("#consumption").val(),
				"proportion" : $("#proportion").val(),
				"profit" : $("#profit").val(),
				"remark" : $("#cc_remark").val(),
				"index" : index
			}]};
			var template = Handlebars.compile($("#tr-cclist").html());
			$("#total_cclist").before(template(data));
			$("#gwxm_index").val(parseInt(index) + 1);
			totalConsp();
			totalProfit();
		}
	});
	//删除购物项目
	$(document).on('click', '.cclist_remove', function(event){
		$(this).parent().parent().remove();
		totalConsp();
		totalProfit();
	});

	//合计打单金额
	function totalConsp(){
		var totalconsp = 0;
		$(".gw_consumption").each(function(){
			var consumption = $(this).val();
			if(isNaN(consumption) || consumption == ''){
				return;
			}
			totalconsp = parseFloat(consumption) + parseFloat(totalconsp);
		});
		$("#totalconsp").html(totalconsp);
		$("#input_totalconsp").val(totalconsp);
	}
	//合计返款金额
	function totalProfit(){
		var totalprofit = 0;
		$(".gw_profit").each(function(){
			var profit = $(this).val();
			if(isNaN(profit) || profit == ''){
				return;
			}
			totalprofit = parseFloat(profit) + parseFloat(totalprofit);
		});
		$("#totalprofit").html(totalprofit);
		$("#input_totalprofit").val(totalprofit);
		getIncome();
	}

	//购物信息提交
	$("#submit_gw").on("click", function () {
		if ($("#form_gwmx").valid() && $("#form_gw").valid()) {
			$.post("/cOrder/addShopReslist.htm?orderid="+$("#fsNo").val(),
					$("#form_gw").serialize()+"&"+$("#form_gwmx").serialize(),
					function(data){
				var json = eval("("+data+")");
				if(json.result == "ok") {
					$("#message_gw").text("修改记录成功");
					$("#message_gw").show();
					var template = Handlebars.compile($("#tr-reslist").html());
					$("#tbody_reslist").html(template(json.shopInfo));
					getIncome();
					return true;
				}
				else {
					$("#message_gw").text("修改记录失败:" + json.message );
					$("#message_gw").show();
					return false;
				}
				return false;
			});
		}
	});

});
