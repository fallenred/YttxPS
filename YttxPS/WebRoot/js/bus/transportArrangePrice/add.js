jQuery(function($) {
	
    $("#addform #message").hide();
 
    //  重置
    $("#addModal #reset").on("click", function() {
        $("#addModal #message").hide();
        $("#addModal #message").text("");
        $("#addModal input[type='text']").val("");
        $("#addModal input[type='hidden']").val("");
        $("#addModal input").prop("checked", false);
        $("#addModal select").val("");
    });
    
	// 关闭
	$("#addModal #room_close").on("click", function () {
	    $(this).removeData("bs.modal");
        $("#grid-table").trigger("reloadGrid");
	});
	
	//	提交
	$("#add_submit").on("click", function () {
		if ($("#addform").valid()) {
		    $("#addform #message").hide();
			
	        if($("#addform input[name='ftStartdate']").val() == '' || $("#addform input[name='ftStartdate']").val() == undefined) {
	            $("#addform #message").show();
	            $("#addform #message").text("开始日期不能为空，请输入");
	            $("#addform input[name='ftStartdate']").focus();
	            return false;
	        }
			if($("#addform input[name='ftEnddate']").val() == '' || $("#addform input[name='ftEnddate']").val() == undefined) {
				$("#addform #message").show();
				$("#addform #message").text("结束日期不能为空，请选择");
				$("#addform input[name='ftEnddate']").focus();
				return false;
			}
			if($("#addform input[name='fdPrice']").val() == '' || $("#addform input[name='fdPrice']").val() == undefined) {
				$("#addform #message").show();
				$("#addform #message").text("价格不能为空，请输入");
				$("#addform input[name='fdPrice']").focus();
				return false;
			}
			
			var ftStartdate = new Date($("#addform input[name='ftStartdate']").val());
			var ftEnddate = new Date($("#addform input[name='ftEnddate']").val());
			if(ftEnddate - ftStartdate < 0) {
				$("#message").show();
				$("#message").text("结束日期不能早于开始日期");
				$('#ftStartdate').focus();
				return false;
			}
			
			$.post("/transportArrange/addTransportarrangePrice.htm",
			        $("#addform").serialize(),
					function(data){
				        var json = eval("(" + data + ")");
						if(json.result == "ok") {
							$("#addform #message").text("增加记录成功");
							$("#addform #message").show();
							return true;
						}
						else {
							$("#addform #message").text("增加记录失败:" + json.message );
							$("#addform #message").show();
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

});
