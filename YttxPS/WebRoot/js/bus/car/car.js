//  显示详情
var raw = {};

/**
 * 选择表格行事件
 * @param grid对象
 * @return 表格选中行数据
 */
function getSelectedRow(table) {
    var grid = $(table);
    var rowKey = grid.jqGrid('getGridParam',"selrow");
    var raw = undefined;
    if (!rowKey)
        alert("您未选中行！");
    else
        raw = grid.jqGrid('getRowData', rowKey);
    return raw;
}

function editCustom(id) {
    raw = jQuery("#grid-table").jqGrid('getRowData', id);
    loadFormData(raw,$("#editForm"));
    findDriverInfo(raw.driverindex);
    $('#editModal').modal({ show: true, backdrop: 'static' });
};

function showCustom(id) {
    raw = jQuery("#grid-table").jqGrid('getRowData', id);
    loadFormData(raw,$("#showForm"));
    findDriverInfo(raw.driverindex);
    $("#showModal").modal({show: true, backdrop: 'static' });
};

function deleteCustom(id) {
    raw = jQuery("#grid-table").jqGrid('getRowData', id);
    $("#delForm input[name='id']").val(raw.no);
    $("#delForm #message").text("");
    $("#delForm #submit").attr("disabled",false);
    $("#delForm #question").show();
    $('#delModal').modal({ show: true, backdrop: 'static' });
};

//查询驾驶员信息
function findDriverInfo(id){
    if(id == undefined){
        console.log("未获取到驾驶员ID");
        return false;
    }
    $.post("/driver/findDriverInfo.htm",{id : id},function(json){
        if(json.result == "ok") {
            $(".car_driver_input").val(json.data.index);
            $(".car_driver_msg").text("您选择了【"+json.data.name+"】为该车驾驶员。");
            return true;
        }else{
            alert('未获取到驾驶员信息，需要重新打开。');
            $("#editModal", parent.document).find(".close").click();
            $("#showModal", parent.document).find(".close").click();
            return false;
        }
    },'json');
}

//绑定指定表单数据
function loadFormData(raw,from){
    $.each(raw, function( i, n){
        if ($(from).find("input[name='"+i+"']").length != 0)
            $(from).find("input[name='"+i+"']").val(n);
        else if($(from).find("select[name='"+i+"']").length !=0){
            $(from).find("select[name='"+i+"']").find("option[value='" 
                    + n + "']").prop('selected', 'selected');
        }
    });
}

/** 模式窗口关闭时触发事件 */
$("#addModal", parent.document).on("hidden.bs.modal", function() {
    $(this).removeData("bs.modal");
    $("#grid-table").trigger("reloadGrid");
});
$("#editModal", parent.document).on("hidden.bs.modal", function() {
    $(this).removeData("bs.modal");
    $("#grid-table").trigger("reloadGrid");
});
$("#delModal").on("hidden.bs.modal", function() {
    $(this).removeData("bs.modal");
    $("#grid-table").trigger("reloadGrid");
});
$("#showModal").on("hidden.bs.modal", function() {
    $(this).removeData("bs.modal");
});


jQuery(function($) {
    
    //重置
    $("#reset", "#queryfield").click(function() {
        $("#queryfield").val(null);
    });
    //查询
    $("#queryfield_submit").on("click", function() {
        $("#collapseOne").collapse('hide');
        var postData = $("#grid-table").jqGrid("getGridParam", "postData");
        postData["car.no"] = $("#queryfield").find("#no").val();
        postData["car.stat"] = $("#queryfield").find("#stat").val();
        $("#grid-table").jqGrid("setGridParam", {
            datatype : 'json',
            postData : postData
        }).trigger("reloadGrid");
    });
    
    //  jqgrid
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
    
    driverFormatter = function(){
        return selectBtn;
    }
    
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
    
    //状态
    var stat_items = {
        1 : '正常',
        2 : '失效'
    };
    var stat_val = '';
    for (k in stat_items)
        stat_val += ';' + k + ":" + stat_items[k];
    stat_val = stat_val.substring(1);
    
    $(grid_selector).jqGrid(
            {
                url : "/car/findCar.htm",
                datatype : "json",
                mtype : 'POST',
                height : 400,
                colNames : [ '操作', '车牌号', '品牌', '准载数', '注册日期', '所属公司', '联系电话','驾驶员ID', '状态' ],
                colModel : [ {
                    name : 'myaction',
                    index : '',
                    width : 100,
                    fixed : true,
                    sortable : false,
                    resize : false,
                    formatter : actFormatter
                }, {
                    name : 'no',
                    index : 'no',
                    width : 100,
                    sorttype : "char"
                }, {
                    name : 'brand',
                    index : 'brand',
                    width : 100,
                    editable : true,
                    sorttype : "char"
                }, {
                    name : 'load',
                    index : 'load',
                    width : 50,
                    editable : true,
                    sorttype : "char"
                }, {
                    name : 'regdate',
                    index : 'regdate',
                    width : 80,
                    editable : true
                }, {
                    name : 'company',
                    index : 'company',
                    width : 80,
                    editable : true
                }, {
                    name : 'tel',
                    index : 'tel',
                    width : 80,
                    editable : true
                }, {
                    name : 'driverindex',
                    index : 'driverindex',
                    width : 0,
                    editable : false,
                    display : false,
                    hidden: true
                }, {
                    name : 'stat',
                    index : 'stat',
                    width : 40,
                    sortable : true,
                    editable : true,
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
    //          multiselect : true,
    //          multiboxonly : true,
    //          multipleSearch : true,
                loadComplete : function() {
                    var table = this;
                    setTimeout(function() {
                       
                        updatePagerIcons(table);
                        enableTooltips(table);
                    }, 0);
                },
                editurl : "/car/save.htm",
                shrinkToFit : true,
                autowidth : true
            });
    $(window).triggerHandler('resize.jqGrid');// trigger window resize to make
    // the grid get the correct size
    
    // navButtons
    $(grid_selector).jqGrid(
            'navGrid',
            pager_selector,
            { // navbar options
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
    
    $(grid_selector).jqGrid('navGrid', pager_selector).jqGrid(
            'navButtonAdd', pager_selector, {
                caption : "",
                buttonicon : "ace-icon fa fa-plus-circle purple",
                onClickButton : function() {
                    $("#addModal .car_driver_msg").text("当前无驾驶员信息！");
                    $('#addModal').modal({ show: true, backdrop: 'static' });
                },
                position : "first",
                title : "新增车辆",
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
    
    //开启选择驾驶员信息窗口事件
    $(".carDirverSearchButtom").click(function(){
        $('#carDirverSearchModal').modal({ show: true, backdrop: 'static' });
        var dirver_grid_table = "#dirver-grid-table";
        var dirver_grid_pager = "#dirver-grid-pager";
        $(dirver_grid_table).jqGrid(
                {
                    url : "/driver/findDriver.htm?stat=1",
                    datatype : "json",
                    mtype : 'POST',
                    colNames : ['ID', '驾驶员姓名', '性别', '出生日期' ],
                    colModel : [ {
                        name : 'index',
                        index : 'index',
                        sorttype : "int",
                        hidden: true
                    }, {
                        name : 'name',
                        index : 'name',
                    }, {
                        name : 'gender',
                        index : 'gender',
                        edittype : 'select',
                        editoptions : {
                            value : '1:男;2:女'
                        },
                        formatter : function(v, opt, rec) {
                            return v == 1 ? '男' : '女';
                        },
                        unformat : function(v) {
                            return '男' == v ? '1' : '2';
                        },
                    }, {
                        name : 'birth',
                        index : 'birth',
                    } ],
                    viewrecords : true,
                    rowNum : 10,
                    rowList : [ 10, 20, 30 ],
                    pager : dirver_grid_pager,
                    altRows : true,
                    loadComplete : function() {
                        var table = this;
                        setTimeout(function() {
                            updatePagerIcons(table);
                            enableTooltips(table);
                        }, 0);
                    },
                    shrinkToFit : true,
                    width : 580,
                    height: 350
                });
    });
    
    //查询驾驶员信息事件
    $("#driver_submit").on("click", function() {
        var postData = $("#dirver-grid-table").jqGrid("getGridParam", "postData");
        postData["driver.name"] = $("#carDirverSearchForm").find("#name").val();
        postData["driver.stat"] = '1';
        $("#dirver-grid-table").jqGrid("setGridParam", {
            datatype : 'json',
            postData : postData
        }).trigger("reloadGrid");
    });
    
    //确定选择驾驶员信息事件
    $("#enter_driver_sumit").on("click", function() {
        var row = getSelectedRow($("#dirver-grid-table"));
        if(row == undefined){
            return false;
        }
        $(".car_driver_input").val(row.index);
        $(".car_driver_msg").text("您选择了【"+row.name+"】为该车驾驶员。");
    });
    
});
