/*
 * 验证日期格式
 */
function validDateFormat(dateStr){
	if(dateStr==null||dateStr==''){
		return false;
	}
	if( /^\d{4}[\-](0?[1-9]|1[012])[\-](0?[1-9]|[12][0-9]|3[01])$/.test(dateStr))
		return true
	return false;
}
/*
 * 验证两个开始时间是否小于结束时间
 */
function compareDate(startDate,endDate){
	var sDate=new Date(startDate.replace(/-/g,'/'));
	var eDate=new Date(endDate.replace(/-/g,'/'));
	return eDate<sDate?false:true;
}
/*
 * 验证价格
 */
function validMoney(money){
	if(money==null||money==''){
		return true;
	}
	if(/^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/.test(money))
		return true
	return false;
}

jQuery(function($) {
	/*
	 * 隐藏message div
	 */
	$("#message").hide();
	$('#date_range').daterangepicker();
	/*
	 * 按钮-->"关闭"按钮的响应函数
	 */
	$("#close").on("click", function () {
		$("#priceModal", parent.document).find(".close").click();
	});
	
	/*
	 * 按钮-->"重置"按钮的响应函数
	 */
	$("#reset").on("click", function(){
		$("input").filter("not(.unreset)").val("");
	});
	
	/*
	 * 按钮-->"提交"按钮的响应函数
	 */
	$("#submit").on("click", function (){
		//第一步，验证数据
		var resNo = $("#no").val();//菜单名称
		
	
		var dRange = $.trim($("#date_range").val())
		if( dRange=='') {
			$("#message").show();
			$("#message").text("请录入日期范围");
			$("#dRange").focus();
			return false;
		} 
		
		var price = $.trim($("#price").val())//价格
		if( !validMoney(price)) {
			$("#message").show();
			$("#message").text("价格录入格式不正确");
			$("#price").focus();
			return false;
		}
		if(price == '' || price == undefined){
			$("#message").show();
			$("#message").text("价格不允许为空");
			$("#price").focus();
			return false;
		}
		
		//获取开始时间和结束时间
		function getDate(range,index){
			var dates = range.split("-")
			return dates[index].replace(/\//g,'-');
		}
		var postData = {};
		postData["no"]         = resNo;
		postData["startDate"]  = getDate(dRange, 0);
		postData["endDate"]    = getDate(dRange, 1);

		if(price != ''){
			postData["prices[0].fsCcno"]= "000000";
			postData["prices[0].fdPrice"]= price;
		}
		$.post(
			"/restaurant/submitPrice.htm",
			postData,
			function(data){
				var json = eval("("+data+")");
				if(json.result == "ok") {
					$("#message").text("添加菜单消费选项成功！");
					$("#message").show();
					return true;
				}else {
					$("#message").text("添加菜单消费选项失败:" + json.message );
					$("#message").show();
					return false;
				}
				return false;
			});
	});
});
