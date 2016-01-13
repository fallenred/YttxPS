jQuery(function($) {
	$("#editForm #message").hide();

	//	重置
	$("#editForm #reset").on("click", function() {
			$("#editForm #message").hide();
			$("#editForm #message").text("");
		});
	
	// 关闭
	$("#editForm #close").on("click", function () {
		$("#editForm", parent.document).find(".close").click();
	});
	
	//	提交
	$("#editForm #submit").on("click", function () {
	    $("#editForm #message").hide();
        if($("#editForm input[name='name']").val() == '') {
            $("#editForm #message").show();
            $("#editForm #message").text("驾驶员姓名不能为空，请输入");
            $('#editForm #name').focus();
            return false;
        } 
        if($("#editForm select[name='gender']").val() == '') {
            $("#editForm #message").show();
            $("#editForm #message").text("性别不能为空，请输入");
            $('#editForm #gender').focus();
            return false;
        } 
        
        if($("#editForm input[name='birth']").val() == '') {
            $("#editForm #message").show();
            $("#editForm #message").text("出生日期不能为空，请输入");
            $('#editForm #birth').focus();
            return false;
        }
        
        if($("#editForm select[name='stat']").val() == '') {
            $("#editForm #message").show();
            $("#editForm #message").text("状态不能为空，请输入");
            $('#editForm #stat').focus();
            return false;
        }
        
		$.post("/driver/editDriver.htm",
				$("#editForm").serialize(),
				function(data){
			var json = eval("("+data+")");
					if(json.result == "ok") {
						$("#editForm #message").text("修改记录成功");
						$("#editForm #message").show();
						return true;
					}
					else {
						$("#editForm #message").text("修改记录失败:" + json.message );
						$("#editForm #message").show();
						return false;
					}
					return false;
				});
	});
	
});
