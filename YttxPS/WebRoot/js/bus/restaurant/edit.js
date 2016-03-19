jQuery(function($) {
	/*
	 * 隐藏信息提示div
	 */
	$("#message").hide();
	
	/*
	 * 行政区划选择器-->初始化
	 */
	var localsel = $("#selectCity", "#editform").localCity({
		provurl : "/pub/findcity.htm",
		cityurl : "/pub/findcity.htm",
		disturl : "/pub/findcity.htm",
		callback : localcallback
	});
	
	/*
	 * 行政区划选择器-->回调函数
	 */
	function localcallback(index, key, value, fullkey, fullname) {
		$("#regionname", "#editform").val(fullname);
		$("#regionno", "#editform").val(key);
		if (index == 3)
			$("#selectCity", "#editform").hide();
	}
	
	/*
	 * 行政区划选择器-->行政区划触发选择器
	 */
	$("#editform #regionname").click(function() {
		$("#editform #selectCity").show();
	})
	
	/*
	 * 按钮-->"关闭"按钮的响应函数
	 */
	$("#close").on("click", function () {
		$("#editModal", parent.document).find(".close").click();
	});
	
	/*
	 * 按钮-->"提交"按钮的响应函数
	 */
	$("#editform #submit").on("click", function (){
		//第一步，验证数据
		var name = $.trim($("#editform #name").val());//菜单名称
		if(name == '') {
			$("#message").show();
			$("#message").text("菜单名称不能为空，请输入");
			$("#editform #name").focus();
			return false;
		} 
		
		var regionno = $("#editform #regionno").val()//所属地区
		if( regionno == '') {
			$("#message").show();
			$("#message").text("所属地区不能为空，请输入");
			$("#editform #regionname").focus();
			return false;
		} 
		
		var special = $("#editform #special").val()//类型标识
		if(special == '') {
			$("#message").show();
			$("#message").text("请选择类型标识");
			$("#editform #special").focus();
			return false;
		}
		
		var lvl = $("#editform input[name='lvl']:checked").val()//餐标
		if(lvl ==undefined) {
			$("#message").show();
			$("#message").text("请选择餐标");
			$("#editform input[name='lvl']").focus();
			return false;
		}
		
		var stat = $("#editform #stat").val();
		if(stat == '') {
			$("#message").show();
			$("#message").text("请选择状态");
			$("#editform #stat").focus();
			return false;
		}
		
		$.ajax({  
            url: "/restaurant/edit.htm",  
            type: 'POST',  
            data:new FormData($("#editform")[0]),  
            dataType: 'JSON',  
            cache: false,  
            processData: false,  
            contentType: false
		}).done(function(json){
			if(json.result == "ok") {
				$("#message").text("修改菜单成功！");
				$("#message").show();
				return true;
			}else {
				$("#message").text("修改菜单失败:" + json.message );
				$("#message").show();
				return false;
			}
			return false;
        }); 	
		
	});
});
