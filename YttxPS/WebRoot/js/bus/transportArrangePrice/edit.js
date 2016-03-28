jQuery(function($) {
    $("#editForm #message").hide();
    $("#editForm #edit_submit").attr("disabled", false);

    // 重置
    $("#editForm #reset").on("click", function() {
        $("#editForm #message").hide();
        $("#editForm #message").text("");
        $("#editForm input[type='text']").val("");
        $("#editForm input[type='hidden']").val("");
        $("#editForm input").prop("checked", false);
        $("#editForm select").val("");
    });

    // 关闭
    $("#editModal #room_close").on("click", function() {
        $(this).removeData("bs.modal");
        $("#grid-table").trigger("reloadGrid");
    });

    // 提交
    $("#editModal #room_edit_submit").on(
            "click",
            function() {
                $("#editForm #message").hide();
                
                if($("#editForm input[name='ftStartdate']").val() == '' || $("#editForm input[name='ftStartdate']").val() == undefined) {
                    $("#editForm #message").show();
                    $("#editForm #message").text("开始日期不能为空，请输入");
                    $("#editForm input[name='ftStartdate']").focus();
                    return false;
                }
        		if($("#editForm input[name='ftEnddate']").val() == '' || $("#editForm input[name='ftEnddate']").val() == undefined) {
        			$("#editForm #message").show();
        			$("#editForm #message").text("结束日期不能为空，请选择");
        			$("#editForm input[name='ftEnddate']").focus();
        			return false;
        		}
        		if($("#editForm input[name='fdPrice']").val() == '' || $("#editForm input[name='fdPrice']").val() == undefined) {
        			$("#editForm #message").show();
        			$("#editForm #message").text("价格不能为空，请输入");
        			$("#editForm input[name='fdPrice']").focus();
        			return false;
        		}
        		
        		var ftStartdate = new Date($("#editForm input[name='ftStartdate']").val());
        		var ftEnddate = new Date($("#editForm input[name='ftEnddate']").val());
        		if(ftEnddate - ftStartdate < 0) {
        			$("#message").show();
        			$("#message").text("结束日期不能早于开始日期");
        			$('#ftStartdate').focus();
        			return false;
        		}
                
                $("#editForm #edit_submit").attr("disabled", "disabled");
                $.post("/transportArrange/editTransportarrangePrice.htm", $("#editForm").serialize(),
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
            });

});
