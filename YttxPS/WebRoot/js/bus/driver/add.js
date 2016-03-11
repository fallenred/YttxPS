jQuery(function($) {
    
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
    });
    
	// 关闭
	$("#addModal #close").on("click", function () {
		$("#addModal", parent.document).find(".close").click();
	});
	
	//	提交
	$("#add_submit").on("click", function () {
	    $("#addform #message").hide();
		if($("#addform input[name='name']").val() == '') {
			$("#addform #message").show();
			$("#addform #message").text("驾驶员姓名不能为空，请输入");
			$('#addform #name').focus();
			return false;
		} 
		if($("#addform select[name='gender']").val() == '') {
			$("#addform #message").show();
			$("#addform #message").text("性别不能为空，请输入");
			$('#addform #gender').focus();
			return false;
		} 
		
		if($("#addform input[name='birth']").val() == '') {
			$("#addform #message").show();
			$("#addform #message").text("出生日期不能为空，请输入");
			$('#addform #birth').focus();
			return false;
		}
		
		if($("#addform select[name='stat']").val() == '') {
            $("#addform #message").show();
            $("#addform #message").text("状态不能为空，请输入");
            $('#addform #stat').focus();
            return false;
        }

		$.post("/driver/addDriver.htm",
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
