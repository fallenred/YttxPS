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
        //$("#editModal", parent.document).find(".close").click();
        $(this).removeData("bs.modal");
        $("#grid-table").trigger("reloadGrid");
    });

    // 提交
    $("#editModal #room_edit_submit").on(
            "click",
            function() {
                $("#editForm #message").hide();
                
                if($("#editForm input[name='name']").val() == '') {
                    $("#editForm #message").show();
                    $("#editForm #message").text("房型名称不能为空，请输入");
                    $('#editForm #name').focus();
                    return false;
                }
                if($("#editForm select[name='type']").val() == '') {
                    $("#editForm #message").show();
                    $("#editForm #message").text("房型类型不能为空，请选择");
                    $('#editForm #type').focus();
                    return false;
                }
                if($("#editForm input[name='accomno']").val() == '') {
                    $("#editForm #message").show();
                    $("#editForm #message").text("酒店代码不能为空，请输入");
                    $('#editForm #accomno').focus();
                    return false;
                }
                if($("#editForm select[name='stat']").val() == '') {
                    $("#editForm #message").show();
                    $("#editForm #message").text("状态不能为空，请选择");
                    $('#editForm #stat').focus();
                    return false;
                }
                
                $("#editForm #edit_submit").attr("disabled", "disabled");
                $.post("/room/editRoom.htm", $("#editForm")
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
            });

});
