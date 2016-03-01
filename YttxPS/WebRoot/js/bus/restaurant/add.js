jQuery(function($) {
	/*
	 * 隐藏信息提示div
	 */
	$("#message").hide();
	
	/*
	 * 行政区划选择器-->初始化
	 */
	var localsel = $("#selectCity", "#addform").localCity({
		provurl : "/pub/findcity.htm",
		cityurl : "/pub/findcity.htm",
		disturl : "/pub/findcity.htm",
		callback : localcallback
	});
	
	/*
	 * 行政区划选择器-->回调函数
	 */
	function localcallback(index, key, value, fullkey, fullname) {
		$("#regionname", "#addform").val(fullname);
		$("#regionno", "#addform").val(key);
		if (index == 3)
			$("#selectCity", "#addform").hide();
	}
	
	/*
	 * 行政区划选择器-->行政区划触发选择器
	 */
	$("#addform #regionname").click(function() {
		$("#addform #selectCity").show();
	})
	
	
	
	/*
	 * 按钮-->"关闭"按钮的响应函数
	 */
	$("#close").on("click", function () {
		$("#addModal", parent.document).find(".close").click();
	});
	
	/*
	 * 按钮-->"重置"按钮的响应函数
	 */
	$("#reset").on("click", function(){
		//隐藏并清空信息提示div
        $("#addModal #message").hide();
        $("#addModal #message").text("");
        //清空input
        $("#addModal input[type='text']").val("");
        $("#addModal input[type='hidden']").val("");
        $("#addModal input[type='radio']").prop("checked", false);
        //select复位
        $("#addModal select").find("option[value='']").prop("selected",true);
        //清空文本域
        $("#addModal textarea").text("");
        $("#addform #selectCity").hide();
	});
	
	/*
	 * 按钮-->"提交"按钮的响应函数
	 */
	$("#addform #submit").on("click", function (){
		//第一步，验证数据
		var name = $.trim($("#addform #name").val());//餐厅名称
		if(name == '') {
			$("#message").show();
			$("#message").text("餐厅名称不能为空，请输入");
			$("#addform #name").focus();
			return false;
		} 
		
		var regionno = $("#addform #regionno").val()//所属地区
		if( regionno == '') {
			$("#message").show();
			$("#message").text("所属地区不能为空，请输入");
			$("#addform #regionname").focus();
			return false;
		} 
		
		var special = $("#addform #special").val()//特殊菜品
		if(special == '') {
			$("#message").show();
			$("#message").text("请选择特殊菜品");
			$("#addform #special").focus();
			return false;
		}
		
		var lvl = $("#addform input[name='lvl']:checked").val()//等级
		if(lvl ==undefined) {
			$("#message").show();
			$("#message").text("请选择特餐厅等级");
			$("#addform input[name='lvl']").focus();
			return false;
		}
		
		var scale = $.trim($("#addform #scale").val())//接待规模
		var re = /^[1-9]+[0-9]*]*$/;
		if (!re.test(scale)){
			$("#message").show();
			$("#message").text("接待规模请输入正整数");
			$("#addform #scale").focus();
			return false;
		}
		
		var stat = $("#addform #stat").val();
		if(stat == '') {
			$("#message").show();
			$("#message").text("请选择状态");
			$("#addform #stat").focus();
			return false;
		}
		
		var postData = {};
		postData["name"]      = name;
		postData["regionno"]  = regionno;
		postData["scenicNo"]    = scenic;
		postData["addr"]      = $.trim($("#addform #addr").val());
		postData["special"]   = special;
		postData["menu"]      = $("#addform #menu").val();
		postData["attention"] = $("#addform #attention").val();
		postData["lvl"]       = lvl;
		postData["scale"]     = scale;
		postData["stat"]      = stat;
		
		
		$.post(
			"/restaurant/add.htm",
			postData,
			function(data){
				var json = eval("("+data+")");
				if(json.result == "ok") {
					$("#message").html("新增餐厅成功！");
					$("#message").show();
					return true;
				}else {
					$("#message").html("新增餐厅失败:" + json.message );
					$("#message").show();
					return false;
				}
				return false;
			});
	});
});
