jQuery(function($) {
	//加载购物店下拉列表
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
			$.each(data, function(commentIndex, comment){
				html += '<option value=' + comment['no'] + '>' + comment['name'] + '</option>';
			});
			$("#shop").html(html);
			$("#shop").attr("class", "width-80 chosen-select form-control");
			$("#shop").chosen(); 
			$("#shop").next().attr("style","width:240px;");
		}
	});

	//购物店项目
	$("#typeno").html("<option value='0'>高原特产</option><option value='1'>藏红花</option><option value='1'>牛角梳</option>");
	$("#typeno").attr("class", "width-80 chosen-select form-control");
	$("#typeno").chosen(); 
	$("#typeno").next().attr("style","width:240px;");

	//计算人头费
	$(document).on('change key', '.peopleprofit', function(event){
		var people = $("#people").val();
		var unitprice = $("#unitprice").val();
		if(isNaN(people) || isNaN(unitprice) || people == '' || unitprice == ''){
			$("#peopleprofit").val(0);
			return;
		}
		$("#peopleprofit").val(parseFloat(people) * parseFloat(unitprice));
	});

	//计算返佣金额
	$(document).on('change key', '.profit', function(event){
		var consumption = $("#consumption").val();
		var proportion = $("#proportion").val();
		if(isNaN(consumption) || isNaN(proportion) || consumption == '' || proportion == ''){
			$("#profit").val(0);
			return;
		}
		$("#profit").val(parseFloat(consumption) * parseFloat(proportion) * parseFloat(0.01));
	});

	//打开模态框
	$(document).on('click', '.btn_gw_add', function(event){
		$(".cc_tbody").html($(".cc_tbody tr:last").html());
		$("#gw_index").val(0);
		$("#totalconsp").html(0);
		$("#totalprofit").html(0);
		$('#addModal').modal('show');
	});
	//关闭模态框
	$(function () { $('#addModal').on('hide.bs.modal', function () {
		alert('嘿，我听说您喜欢模态框...');})
	});

	//新增购物项目
	$(document).on('click', '.btn_cclist_add', function(event){
		index = $("#gw_index").val();
		var data = {
				"name" : $("#shop").find("option:selected").text(),
				"typeno" : $("#typeno").val(),
				"typenname" : $("#typeno").find("option:selected").text(),
				"consumption" : $("#consumption").val(),
				"proportion" : $("#proportion").val(),
				"profit" : $("#profit").val(),
				"remark" : $("#cc_remark").val(),
				"index" : index
		}
		var template = Handlebars.compile($("#tr-cclist").html());
		$(".cc_tbody").html(template(data) + $(".cc_tbody").html());
		$("#gw_index").val(parseInt(index) + 1);
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
	}

	//购物信息提交
	$("#submit_gw").on("click", function () {
		$.post("/cOrder/addGwinfo.htm?orderid="+$("#fsNo").val(),
				$("#form_gw").serialize(),
				function(data){
			var json = eval("("+data+")");
			if(json.result == "ok") {
				$("#message_gw").text("修改记录成功");
				$("#message_gw").show();
				return true;
			}
			else {
				$("#message_gw").text("修改记录失败:" + json.message );
				$("#message_gw").show();
				return false;
			}
			return false;
		});
	});
	
});
