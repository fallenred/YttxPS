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
		var name = $.trim($("#editform #name").val());//餐厅名称
		if(name == '') {
			$("#message").show();
			$("#message").text("餐厅名称不能为空，请输入");
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
		
		var special = $("#editform #special").val()//特殊菜品
		if(special == '') {
			$("#message").show();
			$("#message").text("请选择特殊菜品");
			$("#editform #special").focus();
			return false;
		}
		
		var lvl = $("#editform input[name='lvl']:checked").val()//等级
		if(lvl ==undefined) {
			$("#message").show();
			$("#message").text("请选择特餐厅等级");
			$("#editform input[name='lvl']").focus();
			return false;
		}
		
		var scale = $.trim($("#editform #scale").val())//接待规模
		var re = /^[1-9]+[0-9]*]*$/;
		if (!re.test(scale)){
			$("#message").show();
			$("#message").text("接待规模请输入正整数");
			$("#editform #scale").focus();
			return false;
		}
		
		var stat = $("#editform #stat").val();
		if(stat == '') {
			$("#message").show();
			$("#message").text("请选择状态");
			$("#editform #stat").focus();
			return false;
		}
		
		var img = $("#editform #menuImgFile").val();
		var srcFile =$("#editform #menuImgFileLoction").val();
		if(img == ''&& (srcFile==null||srcFile=='')) {
			$("#message").show();
			$("#message").text("请上传菜单图片");
			$("#editform  #menuImgFile").focus();
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
				$("#message").text("修改餐厅成功！");
				$("#message").show();
				return true;
			}else {
				$("#message").text("修改餐厅失败:" + json.message );
				$("#message").show();
				return false;
			}
			return false;
        }); 	
		
	});
});
