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
		
        if($("#addform input[name='fsName']").val() == '' || $("#addform input[name='fsName']").val() == undefined) {
            $("#addform #message").show();
            $("#addform #message").text("房型名称不能为空，请输入");
            $("#addform input[name='fsName']").focus();
            return false;
        }
		if($("#addform select[name='fsType']").val() == '' || $("#addform select[name='fsType']").val() == undefined) {
			$("#addform #message").show();
			$("#addform #message").text("房型类型不能为空，请选择");
			$("#addform select[name='fsType']").focus();
			return false;
		}
		if($("#addform input[name='fsAccomno']").val() == '' || $("#addform input[name='fsAccomno']").val() == undefined) {
			$("#addform #message").show();
			$("#addform #message").text("酒店代码不能为空，请输入");
			$("#addform input[name='fsAccomno']").focus();
			return false;
		}
		if($("#addform select[name='fiStat']").val() == '' || $("#addform select[name='fiStat']").val() == undefined) {
            $("#addform #message").show();
            $("#addform #message").text("状态不能为空，请选择");
            $("#addform select[name='fiStat']").focus();
            return false;
        }
		
		var fsMeal = "";
		if($("#addform input[name='fsMeal1']").is(":checked") == true) {
			fsMeal += "1";
		} else {
			fsMeal += "0";
		}
		if($("#addform input[name='fsMeal2']").is(":checked") == true) {
			fsMeal += "1";
		} else {
			fsMeal += "0";
		}
		if($("#addform input[name='fsMeal3']").is(":checked") == true) {
			fsMeal += "1";
		} else {
			fsMeal += "0";
		}
		
		$.post("/room/addRoom.htm",
		        $("#addform").serialize() + "&fsMeal=" + fsMeal,
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
