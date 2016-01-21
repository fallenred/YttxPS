/** 模式窗口关闭时触发事件 */
$("#addModal", parent.document).on("hidden.bs.modal", function() {
    
    $("#addModal #message").hide();
    $("#addModal input[type='text']").val("");
    $("#addModal input[type='hidden']").val("");
    $("#addModal input").prop("checked", false);
    $("#addModal select").val("");
    
    $(this).removeData("bs.modal");
    $("#grid-table").trigger("reloadGrid");
});

$("#editModal", parent.document).on("hidden.bs.modal", function() {
    $("#editModal #message").hide();
    $("#editForm input[type='text']").val("");
    $("#editForm input[type='hidden']").val("");
    $("#editForm input").prop("checked", false);
    $("#editForm select").val("");
    $(this).removeData("bs.modal");
    $("#grid-table").trigger("reloadGrid");
});

$("#delModal").on("hidden.bs.modal", function() {
    $(this).removeData("bs.modal");
    $("#grid-table").trigger("reloadGrid");
});

$("#showRoomModal").on("hidden.bs.modal", function() {
    $(this).removeData("bs.modal");
});

jQuery(function($) {
    
    var accomno = $.getUrlParam('no');
    var accomname = $.getUrlParam('name');

    if(accomno == null || accomno == "" || accomno == undefined){
        alert("未取到该酒店编号！");
        $("#roomModal", parent.document).find(".close").click();
    }
    
    // 重置
    $("#reset", "#queryfield").click(function() {
        $("#queryfield").val(null);
        $("#queryfield #regionno").val(null);
    });

    // 查询
    $("#queryfield_submit").on(
            "click",
            function() {
                $("#collapseOne").collapse('hide');
                var postData = $("#grid-table").jqGrid("getGridParam",
                        "postData");
                postData["room.type"] = $("#queryfield").find("#type")
                        .val();
                postData["room.stat"] = $("#queryfield").find("#stat")
                        .val();
                $("#grid-table").jqGrid("setGridParam", {
                    datatype : 'json',
                    postData : postData
                }).trigger("reloadGrid");
            });
    
    // jqgrid
    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";

    // 定义按钮列
    actFormatter = function(cellvalue, options, rawObject) {
        var detail = '<div title="" class="ui-pg-div ui-inline-edit" id="detailButton" style="display: block; cursor: pointer; float: left;" onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" onclick="showCustom('
                + options.rowId
                + ');" data-original-title="查看记录详情"><span class="ui-icon ace-icon fa fa-search-plus grey"></span></div>';

        var editBtn = '<div title="" class="ui-pg-div ui-inline-edit" id="editButton" style="display: block; cursor: pointer; float: left;" onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" onclick="editCustom('
                + options.rowId
                + ');" data-original-title="编辑本条记录"><span class="ui-icon ui-icon-pencil"></span></div>';

        var deleteBtn = '<div title="" class="ui-pg-div ui-inline-edit" id="deleteButton" style="display: block; cursor: pointer; float: left;" onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" onclick="deleteCustom('
                + options.rowId
                + ');" data-original-title="删除本条记录"><span class="ui-icon ace-icon fa fa-trash-o red"></span></div>';
        return detail + editBtn + deleteBtn;
    };

    // resize to fit page size
    $(window).on('resize.jqGrid', function() {
        $(grid_selector).jqGrid('setGridWidth', $(".page-content").width());
    })
    // resize on sidebar collapse/expand
    var parent_column = $(grid_selector).closest('[class*="col-"]');
    $(document).on(
            'settings.ace.jqGrid',
            function(ev, event_name, collapsed) {
                if (event_name === 'sidebar_collapsed'
                        || event_name === 'main_container_fixed') {
                    // setTimeout is for webkit only to give time for DOM
                    // changes and then redraw!!!
                    setTimeout(function() {
                        $(grid_selector).jqGrid('setGridWidth',
                                parent_column.width());
                    }, 0);
                }
            });

    // 状态
    var stat_items = {
        1 : '正常',
        2 : '失效'
    };
    var stat_val = '';
    for (k in stat_items)
        stat_val += ';' + k + ":" + stat_items[k];
    stat_val = stat_val.substring(1);
    
    //三餐情况
    var meal_items = {
            1 : '早餐',
            2 : '中餐',
            3 : '晚餐',
            4 : '宵夜'
        };
        var meal_val = '';
        for (k in meal_items)
            meal_val += ';' + k + ":" + meal_items[k];
        meal_val = meal_val.substring(1);
    
    //房型类型
    var type_items = {'':'未取到房型类型列表，请刷新！'};
    //获取房型类型列表
    $.ajax({ 
        type : "get", 
        url : "/room/getRoomType.htm", 
        async : false, 
        success : function(data){ 
            var json = eval("(" + data + ")"); 
            if(json.result == "ok"){
                type_items = json.data;
            }
        } 
    }); 
    var room_type_val = '';
    var room_type_option = '<option value="">请选择房型类型</option>';
    for (k in type_items){
        room_type_val += ';' + k + ":" + type_items[k];
        room_type_option += '<option value="'+k+'">'+type_items[k]+'</option>';
    }
    room_type_val = room_type_val.substring(1);
    //查询-房型类型数据绑定
    $("#queryfield").find("select[name='type']").html(room_type_option);
    $("#addModal").find("select[name='type']").html(room_type_option);
    $("#showRoomModal").find("select[name='type']").html(room_type_option);
    $("#editModal").find("select[name='type']").html(room_type_option);
    
    //表格
    $(grid_selector).jqGrid(
            {
                url : "/room/findRoom.htm",
                postData:{ "room.accomno": accomno},
                datatype : "json",
                mtype : 'POST',
                height : 395,
                colNames : [ '操作', '编号','房型名称','房型类型','酒店代码','三餐情况','房型价格','状态'],
                colModel : [ {
                    name : 'myaction',
                    index : '',
                    width : 100,
                    fixed : true,
                    sortable : false,
                    resize : false,
                    formatter : actFormatter
                }, {
                    name : 'index',
                    index : 'index',
                    width : 30,
                    sorttype : "char"
                }, {
                    name : 'name',
                    index : 'name',
                    width : 100,
                    editable : false
                }, {
                    name : 'type',
                    index : 'type',
                    width : 50,
                    sortable : false,
                    editable : false,
                    edittype : 'select',
                    sorttype : "char",
                    editoptions : {
                        value : room_type_val
                    },
                    formatter : function(v, opt, rec) {
                        return type_items[v];
                    },
                    unformat : function(v) {
                        for (k in type_items)
                            if (type_items[k] == v)
                                return k;
                        return '03';
                    }
                }, {
                    name : 'accomno',
                    index : 'accomno',
                    width : 80,
                    editable : true,
                    sorttype : "char",
                    hidden : true
                }, {
                    name : 'meal',
                    index : 'meal',
                    width : 90,
                    sortable : false,
                    editable : false,
                    edittype : 'select',
                    editoptions : {
                        value : meal_val
                    },
                    formatter : function(v, opt, rec) {
                        var vals ="";
                        if(v != null && v != ""  && v!= undefined ){
                            for(var t_i =0; t_i < v.length; t_i ++){
                                vals += meal_items[v.substring(t_i,t_i+1)]+","
                            }
                            vals = vals.length>0?vals.substring(0,vals.length-1):vals;
                        }
                        return vals;
                    },
                    unformat : function(v) {
                        if(v != null && v != ""  && v!= undefined ){
                            debugger;
                             var vals = v.split(",");
                             var texts ="";
                             for(index in vals){
                                 for (k in meal_items){
                                     if (meal_items[k] == vals[index])
                                         texts +=k+","
                                 }
                             }
                             return texts.length>0?texts.substring(0,texts.length-1):texts;
                        }
                        return '';
                    }
                }, {
                    name : 'price',
                    index : 'price',
                    width : 30,
                    editable : true,
                    sorttype : "char"
                }, {
                    name : 'stat',
                    index : 'stat',
                    width : 40,
                    sortable : false,
                    editable : false,
                    edittype : 'select',
                    editoptions : {
                        value : stat_val
                    },
                    formatter : function(v, opt, rec) {
                        return stat_items[v];
                    },
                    unformat : function(v) {
                        for (k in stat_items)
                            if (stat_items[k] == v)
                                return k;
                        return '1';
                    }
                } ],

                viewrecords : true,
                rowNum : 10,
                rowList : [ 10, 20, 30 ],
                pager : pager_selector,
                altRows : true,
                loadComplete : function() {
                    var table = this;
                    setTimeout(function() {
                        updatePagerIcons(table);
                        enableTooltips(table);
                    }, 0);
                },
                editurl : "/room/save.htm",
                autowidth : true,
                caption: (accomname!=null && accomname!="")?(accomname+"—房型配置列表"):"酒店房型列表"
            });
    $(window).triggerHandler('resize.jqGrid');// trigger window resize to make
    // the grid get the correct size

    // navButtons
    $(grid_selector).jqGrid('navGrid', pager_selector, { // navbar options
        edit : false,
        editicon : 'ace-icon fa fa-pencil blue',
        add : false,
        addicon : 'ace-icon fa fa-plus-circle purple',
        del : false,
        delicon : 'ace-icon fa fa-trash-o red',
        search : false,
        searchicon : 'ace-icon fa fa-search orange',
        refresh : true,
        refreshicon : 'ace-icon fa fa-refresh green',
    });

    $(grid_selector).jqGrid('navGrid', pager_selector).jqGrid('navButtonAdd',
            pager_selector, {
                caption : "",
                buttonicon : "ace-icon fa fa-plus-circle purple",
                onClickButton : function() {
                    $("#addModal #message").hide();
                    $("#addModal input[type='text']").val("");
                    $("#addModal input[type='hidden']").val("");
                    $("#addModal input").prop("checked", false);
                    $("#addModal select").val("");
                    $("#addModal").find("input[name='accomno']").val(accomno);
                    $('#addModal').modal({
                        show : true,
                        backdrop : 'static'
                    });
                },
                position : "first",
                title : "新增房型",
                cursor : "focus",
            });

    function style_delete_form(form) {
        var buttons = form.next().find('.EditButton .fm-button');
        buttons.addClass('btn btn-sm btn-white btn-round').find(
                '[class*="-icon"]').hide();// ui-icon, s-icon
        buttons.eq(0).addClass('btn-danger').prepend(
                '<i class="ace-icon fa fa-trash-o"></i>');
        buttons.eq(1).addClass('btn-default').prepend(
                '<i class="ace-icon fa fa-times"></i>')
    }

    function style_search_filters(form) {
        form.find('.delete-rule').val('X');
        form.find('.add-rule').addClass('btn btn-xs btn-primary');
        form.find('.add-group').addClass('btn btn-xs btn-success');
        form.find('.delete-group').addClass('btn btn-xs btn-danger');
    }

    function beforeDeleteCallback(e) {
        var form = $(e[0]);
        if (form.data('styled'))
            return false;

        form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner(
                '<div class="widget-header" />')
        style_delete_form(form);

        form.data('styled', true);
    }

    // replace icons with FontAwesome icons like above
    function updatePagerIcons(table) {
        var replacement = {
            'ui-icon-seek-first' : 'ace-icon fa fa-angle-double-left bigger-140',
            'ui-icon-seek-prev' : 'ace-icon fa fa-angle-left bigger-140',
            'ui-icon-seek-next' : 'ace-icon fa fa-angle-right bigger-140',
            'ui-icon-seek-end' : 'ace-icon fa fa-angle-double-right bigger-140'
        };
        $('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon')
                .each(
                        function() {
                            var icon = $(this);
                            var $class = $.trim(icon.attr('class').replace(
                                    'ui-icon', ''));

                            if ($class in replacement)
                                icon.attr('class', 'ui-icon '
                                        + replacement[$class]);
                        })
    }

    function enableTooltips(table) {
        $('.navtable .ui-pg-button').tooltip({
            container : 'body'
        });
        $(table).find('.ui-pg-div').tooltip({
            container : 'body'
        });
    }

    $(document).on('ajaxloadstart', function(e) {
        $(grid_selector).jqGrid('GridUnload');
        $('.ui-jqdialog').remove();
    });
    
});

function editCustom(id) {
    var raw = jQuery("#grid-table").jqGrid('getRowData', id);
    $("#editForm #message").hide();
    loadFormData(raw, $("#editForm"));
    $('#editModal').modal({
        show : true,
        backdrop : 'static'
    });
};

function showCustom(id) {
    var raw = jQuery("#grid-table").jqGrid('getRowData', id);
    loadFormData(raw, $("#showForm"));
    $("#showRoomModal").modal({
        show : true,
        backdrop : 'static'
    });
};

function deleteCustom(id) {
    var raw = jQuery("#grid-table").jqGrid('getRowData', id);
    $("#delForm input[name='id']").val(raw.index);
    $("#delForm #message").text("");
    $("#delForm #submit").attr("disabled", false);
    $("#delForm #question").show();
    $('#delModal').modal({
        show : true,
        backdrop : 'static'
    });
};

// 绑定指定表单数据
function loadFormData(raw, from) {
    // 设置radio域
    $(from).find("input[type='radio']").each(function(i, n) {
        if (raw[$(n).attr('name')] == $(n).val())
            $(n).prop('checked', true);
    });
    // 设置checkbox域
    $(from).find("input[type='checkbox']").each(function(i, n) {
        var valstr = raw[$(n).attr('name')];
        var vals = ( valstr != null && valstr != "" ) ? valstr.split(",") : "";
        $(vals).each(function(j, d){
            if ($(n).val() == d)
                $(n).prop('checked', true);
        });
    });
    // 设置text、hidden域
    $(from).find("input[type!='radio'][type!='checkbox']").each(function(i, n) {
        $(n).val(raw[$(n).attr('name')]);
    });
    // 设置select域
    $(from).find("select").each(function(i, n) {
        $(n).find("option[value='" + raw[$(n).attr('name')] + "']")
            .prop('selected', true);
    });
}

