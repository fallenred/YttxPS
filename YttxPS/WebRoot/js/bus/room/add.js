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
	    $("#addform #message").hide();
		
        if($("#addform input[name='name']").val() == '') {
            $("#addform #message").show();
            $("#addform #message").text("房型名称不能为空，请输入");
            $("#addform input[name='name']").focus();
            return false;
        }
		if($("#addform select[name='type']").val() == '') {
			$("#addform #message").show();
			$("#addform #message").text("房型类型不能为空，请选择");
			$("#addform select[name='type']").focus();
			return false;
		}
		if($("#addform input[name='accomno']").val() == '') {
			$("#addform #message").show();
			$("#addform #message").text("酒店代码不能为空，请输入");
			$("#addform input[name='accomno']").focus();
			return false;
		}
		if($("#addform select[name='stat']").val() == '') {
            $("#addform #message").show();
            $("#addform #message").text("状态不能为空，请选择");
            $("#addform select[name='stat']").focus();
            return false;
        }
		
		$.post("/room/addRoom.htm",
		        $("#addform").serialize(),
				function(data){
			        var json = eval("("+data+")");
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
