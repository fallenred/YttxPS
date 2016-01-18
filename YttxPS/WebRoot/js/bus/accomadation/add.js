jQuery(function($) {
	
    $("#addform #message").hide();
    
  //新增窗口-城市初始化
    var addlocalsec = $("#addModal #addSelectCity").localCity({
        provurl : "/pub/findcity.htm",
        cityurl : "/pub/findcity.htm",
        disturl : "/pub/findcity.htm",
        callback : function(index, key, value, fullkey, fullname) {
            $("#addModal input[name='regionname']").val(fullname);
            $("#addModal input[name='regionno']").val(key);
            if (index == 3) {
                $("#addModal").find(".selectCity").hide();
            }
        }
    });

    //新增窗口-触发城市选择器
    $("#addModal input[name='regionname']").click(function() {
        $("#addModal").find(".selectCity").show();
    });
    
    //  重置
    $("#addModal #reset").on("click", function() {
        $("#addModal #message").hide();
        $("#addModal #message").text("");
        $("#addModal input").val("");
        $("#addModal select").val("");
    });
    
	// 关闭
	$("#addModal #close").on("click", function () {
		$("#addModal", parent.document).find(".close").click();
	});
	
	//	提交
	$("#add_submit").on("click", function () {
	    $("#addform #message").hide();
		if($("#addform input[name='no']").val() == '') {
			$("#addform #message").show();
			$("#addform #message").text("酒店代码不能为空，请输入");
			$('#addform #no').focus();
			return false;
		} 
        if($("#addform input[name='name']").val() == '') {
            $("#addform #message").show();
            $("#addform #message").text("酒店名称不能为空，请输入");
            $('#addform #name').focus();
            return false;
        }
		if($("#addform input[name='regionno']").val() == '') {
			$("#addform #message").show();
			$("#addform #message").text("酒店所属地区不能为空，请输入");
			$('#addform #regionname').focus();
			return false;
		}
		if($("#addform input[name='starlvl']").val() == '') {
			$("#addform #message").show();
			$("#addform #message").text("酒店等级不能为空，请输入");
			$('#addform #starlvl').focus();
			return false;
		}
		if($("#addform input[name='addr']").val() == '') {
            $("#addform #message").show();
            $("#addform #message").text("酒店地址不能为空，请输入");
            $('#addform #addr').focus();
            return false;
        }
		if($("#addform select[name='stat']").val() == '') {
            $("#addform #message").show();
            $("#addform #message").text("状态不能为空，请选择");
            $('#addform #stat').focus();
            return false;
        }

		$.post("/accomadation/addAccomadation.htm",
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
