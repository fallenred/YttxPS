jQuery(function($) {
    $("#addform #message").hide();
    
    $('.datetimepicker').datetimepicker({
		language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
		autoclose: true,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
	});
    
    //  重置
    $("#addModal #reset").on("click", function() {
        $("#addModal #message").hide();
        $("#addModal #message").text("");
        $("#addModal input").val("");
        $("#addModal select").val("");
        $("#addModal .car_driver_msg").text("当前无驾驶员信息！");
    });
    
	// 关闭
	$("#addModal #close").on("click", function () {
		$("#addModal", parent.document).find(".close").click();
	});
	
	//	提交
	$("#addform #add_submit").on("click", function () {
	    $("#addform #message").hide();
		if($("#addform input[name='no']").val() == '') {
			$("#addform #message").show();
			$("#addform #message").text("车牌号不能为空，请输入");
			$('#addform #no').focus();
			return false;
		} 

		if($("#addform input[name='brand']").val() == '') {
			$("#addform #message").show();
			$("#addform #message").text("品牌不能为空，请输入");
			$('#addform #brand').focus();
			return false;
		}

        if($("#addform input[name='load']").val() == '') {
            $("#addform #message").show();
            $("#addform #message").text("准载数不能为空，请输入");
            $('#addform #load').focus();
            return false;
        }

        if($("#addform input[name='regdate']").val() == '') {
            $("#addform #message").show();
            $("#addform #message").text("注册日期不能为空，请输入");
            $('#addform #regdate').focus();
            return false;
        }

        if($("#addform input[name='company']").val() == '') {
            $("#addform #message").show();
            $("#addform #message").text("所属公司不能为空，请输入");
            $('#addform #company').focus();
            return false;
        }
        
        if($("#addform input[name='tel']").val() == '') {
            $("#addform #message").show();
            $("#addform #message").text("联系电话不能为空，请输入");
            $('#addform #tel').focus();
            return false;
        }
        
        if($("#addform input[name='driverindex']").val() == '') {
            $("#addform #message").show();
            $("#addform #message").text("驾驶员信息不能为空，请选择");
            $('#addform #carindex').focus();
            return false;
        }
        
		if($("#addform select[name='stat']").val() == '') {
            $("#addform #message").show();
            $("#addform #message").text("状态不能为空，请输入");
            $('#addform #stat').focus();
            return false;
        }
		$("#addform #add_submit").attr("disabled","disabled");
		$.post("/car/addCar.htm",
		        $("#addform").serialize(),
				function(data){
			        var json = eval("("+data+")");
			        $("#addform #add_submit").attr("disabled",false);
					if(json.result == "ok") {
						$("#addform #message").text("增加记录成功");
						$("#addform #message").show();
						//清空域信息
						$("#addform input").val("");
				        $("#addform select").val("");
				        $("#addModal .car_driver_msg").text("当前无驾驶员信息！");
						return true;
					}else{
					    $("#addform #message").text("增加记录失败:" + json.message );
					    $("#addform #message").show();
					}
					return false;
				});
	});

});
