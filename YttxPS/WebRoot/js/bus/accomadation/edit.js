jQuery(function($) {
    $("#editForm #message").hide();
    $("#editForm #edit_submit").attr("disabled", false);

    //编辑窗口-城市初始化
    var addlocalsec = $("#editModal #editSelectCity").localCity({
        provurl : "/pub/findcity.htm",
        cityurl : "/pub/findcity.htm",
        disturl : "/pub/findcity.htm",
        callback : function(index, key, value, fullkey, fullname) {
            $("#editModal input[name='regionname']").val(fullname);
            $("#editModal input[name='regionno']").val(key);
            if (index == 3) {
                $("#editModal").find(".selectCity").hide();
            }
        }
    });

    //编辑窗口-触发城市选择器
    $("#editModal input[name='regionname']").click(function() {
        $("#editModal").find(".selectCity").show();
    });
    
    // 重置
    $("#editForm #reset").on("click", function() {
        $("#editForm #message").hide();
        $("#editForm #message").text("");
        $("#editForm input[type='text']").val("");
        $("#editForm input[type='hidden']").val("");
        $("#editForm input").prop("checked", false);
        $("#editForm select").val("");
        $("#editForm textarea").text("");
        CKEDITOR.instances["desc_edit"].setData('');
    });

    // 关闭
    $("#editForm #close").on("click", function() {
        $("#editForm", parent.document).find(".close").click();
    });

    // 提交
    $("#editForm #edit_submit").on(
            "click",
            function() {
                $("#editForm #message").hide();
                if($("#editForm input[name='name']").val() == '') {
                    $("#editForm #message").show();
                    $("#editForm #message").text("酒店名称不能为空，请输入");
                    $('#editForm #name').focus();
                    return false;
                }
                if($("#editForm input[name='regionno']").val() == '') {
                    $("#editForm #message").show();
                    $("#editForm #message").text("酒店所属地区不能为空，请输入");
                    $('#editForm #regionname').focus();
                    return false;
                }
                if($("#editForm input[name='starlvl']").val() == '') {
                    $("#editForm #message").show();
                    $("#editForm #message").text("酒店等级不能为空，请输入");
                    $('#editForm #starlvl').focus();
                    return false;
                }
                if($("#editForm input[name='addr']").val() == '') {
                    $("#editForm #message").show();
                    $("#editForm #message").text("酒店地址不能为空，请输入");
                    $('#editForm #addr').focus();
                    return false;
                }
                if($("#editForm select[name='stat']").val() == '') {
                    $("#editForm #message").show();
                    $("#editForm #message").text("状态不能为空，请选择");
                    $('#editForm #stat').focus();
                    return false;
                }
                $("#editForm textarea[name='desc']").val(CKEDITOR.instances["desc_edit"].getData());
                $("#editForm #edit_submit").attr("disabled", "disabled");
                $.post("/accomadation/editAccomadation.htm", $("#editForm")
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
