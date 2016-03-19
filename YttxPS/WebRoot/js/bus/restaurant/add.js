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
		var name = $.trim($("#addform #name").val());//菜单名称
		if(name == '') {
			$("#message").show();
			$("#message").text("菜单名称不能为空，请输入");
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
		
		var special = $("#addform #special").val()//类型标识
		if(special == '') {
			$("#message").show();
			$("#message").text("请选择类型标识");
			$("#addform #special").focus();
			return false;
		}
		
		var lvl = $("#addform input[name='lvl']:checked").val()//餐标
		if(lvl ==undefined) {
			$("#message").show();
			$("#message").text("请选择餐标");
			$("#addform input[name='lvl']").focus();
			return false;
		}
		
		var stat = $("#addform #stat").val();
		if(stat == '') {
			$("#message").show();
			$("#message").text("请选择状态");
			$("#addform #stat").focus();
			return false;
		}
		
		var img = $("#addform #menuImgFile").val();
		if(img == '') {
			$("#message").show();
			$("#message").text("请上传菜单图片");
			$("#addform  #menuImgFile").focus();
			return false;
		}
		
		 $.ajax({  
	            url: '/restaurant/add.htm',  
	            type: 'POST',  
	            data:new FormData($("#addform")[0]),  
	            dataType: 'JSON',  
	            cache: false,  
	            processData: false,  
	            contentType: false
		 }).done(function(json){
				if(json.result == "ok") {
					$("#message").html("新增菜单成功！");
					$("#message").show();
					return true;
				}else {
					$("#message").html("新增菜单失败:" + json.message );
					$("#message").show();
					return false;
				}
				return false;
	        }); 	
	});
});
