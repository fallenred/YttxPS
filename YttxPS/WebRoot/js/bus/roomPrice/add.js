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
		//$("#addModal", parent.document).find(".close").click();
	    $(this).removeData("bs.modal");
        $("#grid-table").trigger("reloadGrid");
	});
	
	//	提交
	$("#add_submit").on("click", function () {
	    $("#addform #message").hide();
		
        if($("#addform input[name='ftStartdate']").val() == '') {
            $("#addform #message").show();
            $("#addform #message").text("开始日期不能为空，请输入");
            $("#addform input[name='ftStartdate']").focus();
            return false;
        }
		if($("#addform select[name='ftEnddate']").val() == '') {
			$("#addform #message").show();
			$("#addform #message").text("结束日期不能为空，请选择");
			$("#addform select[name='ftEnddate']").focus();
			return false;
		}
		if($("#addform input[name='fdPrice']").val() == '') {
			$("#addform #message").show();
			$("#addform #message").text("价格不能为空，请输入");
			$("#addform input[name='fdPrice']").focus();
			return false;
		}
		
		$.post("/room/addRoomPrice.htm",
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
	});

});
