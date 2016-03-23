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
                
                if($("#editForm input[name='fsName']").val() == '' || $("#addform input[name='fsName']").val() == undefined) {
                    $("#editForm #message").show();
                    $("#editForm #message").text("房型名称不能为空，请输入");
                    $("#editForm input[name='fsName']").focus();
                    return false;
                }
        		if($("#editForm select[name='fsType']").val() == '' || $("#addform select[name='fsType']").val() == undefined) {
        			$("#editForm #message").show();
        			$("#editForm #message").text("房型类型不能为空，请选择");
        			$("#editForm select[name='fsType']").focus();
        			return false;
        		}
        		if($("#editForm input[name='fsAccomno']").val() == '' || $("#addform input[name='fsAccomno']").val() == undefined) {
        			$("#editForm #message").show();
        			$("#editForm #message").text("酒店代码不能为空，请输入");
        			$("#editForm input[name='fsAccomno']").focus();
        			return false;
        		}
        		if($("#editForm select[name='fiStat']").val() == '' || $("#addform select[name='fiStat']").val() == undefined) {
                    $("#editForm #message").show();
                    $("#editForm #message").text("状态不能为空，请选择");
                    $("#editForm select[name='fiStat']").focus();
                    return false;
                }
        		
        		var fsMeal = "";
        		if($("#editForm input[name='fsMeal1']").is(":checked") == true) {
        			fsMeal += "1";
        		} else {
        			fsMeal += "0";
        		}
        		if($("#editForm input[name='fsMeal2']").is(":checked") == true) {
        			fsMeal += "1";
        		} else {
        			fsMeal += "0";
        		}
        		if($("#editForm input[name='fsMeal3']").is(":checked") == true) {
        			fsMeal += "1";
        		} else {
        			fsMeal += "0";
        		}
                
                $("#editForm #edit_submit").attr("disabled", "disabled");
                $.post("/room/editRoom.htm", $("#editForm").serialize() + "&fsMeal=" + fsMeal,
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
