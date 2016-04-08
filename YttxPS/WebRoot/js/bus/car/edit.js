jQuery(function($) {
    $("#editForm #message").hide();
    $("#editForm #edit_submit").attr("disabled", false);

    // 重置
    $("#editForm #reset").on("click", function() {
        $("#editForm #message").hide();
        $("#editForm #message").text("");
    });

    // 关闭
    $("#editForm #close").on("click", function() {
        $("#editForm", parent.document).find(".close").click();
    });

    // 提交
    $("#editForm #edit_submit").on(
            "click",
            function() {
            	if ($("#editForm").valid()) {
	                $("#editForm #message").hide();
	                if ($("#editForm input[name='no']").val() == '') {
	                    $("#editForm #message").show();
	                    $("#editForm #message").text("车牌号不能为空，请输入");
	                    $('#editForm #no').focus();
	                    return false;
	                }
	
	                if ($("#editForm input[name='brand']").val() == '') {
	                    $("#editForm #message").show();
	                    $("#editForm #message").text("品牌不能为空，请输入");
	                    $('#editForm #brand').focus();
	                    return false;
	                }
	
	                if ($("#editForm input[name='load']").val() == '') {
	                    $("#editForm #message").show();
	                    $("#editForm #message").text("准载数不能为空，请输入");
	                    $('#editForm #load').focus();
	                    return false;
	                }
	
	                if ($("#editForm input[name='regdate']").val() == '') {
	                    $("#editForm #message").show();
	                    $("#editForm #message").text("注册日期不能为空，请输入");
	                    $('#editForm #regdate').focus();
	                    return false;
	                }
	
	                if ($("#editForm input[name='company']").val() == '') {
	                    $("#editForm #message").show();
	                    $("#editForm #message").text("所属公司不能为空，请输入");
	                    $('#editForm #company').focus();
	                    return false;
	                }
	
	                if ($("#editForm input[name='tel']").val() == '') {
	                    $("#editForm #message").show();
	                    $("#editForm #message").text("联系电话不能为空，请输入");
	                    $('#editForm #tel').focus();
	                    return false;
	                }
	
	                if ($("#editForm input[name='driverindex']").val() == '') {
	                    $("#editForm #message").show();
	                    $("#editForm #message").text("驾驶员信息不能为空，请选择");
	                    $('#editForm #carindex').focus();
	                    return false;
	                }
	
	                if ($("#editForm select[name='stat']").val() == '') {
	                    $("#editForm #message").show();
	                    $("#editForm #message").text("状态不能为空，请输入");
	                    $('#editForm #stat').focus();
	                    return false;
	                }
	                $("#editForm #edit_submit").attr("disabled", "disabled");
	                $.post("/car/editCar.htm", $("#editForm")
	                                .serialize(),
	                                function(data) {
	                                    var json = eval("(" + data + ")");
	                                    $("#editForm #edit_submit").attr(
	                                            "disabled", false);
	                                    if (json.result == "ok") {
	                                        $("#editForm #message").text("修改记录成功");
	                                        $("#editForm #message").show();
	                                        return true;
	                                    } else {
	                                        $("#editForm #message").text(
	                                                "修改记录失败:" + json.message);
	                                        $("#editForm #message").show();
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
