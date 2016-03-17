
/**
 * 选择表格行事件
 * 
 * @param grid对象
 * @return 表格选中行数据
 */
function getSelectedRow(table) {
    var grid = $(table);
    var rowKey = grid.jqGrid('getGridParam', "selrow");
    var raw = undefined;
    if (!rowKey)
        alert("您未选中行！");
    else
        raw = grid.jqGrid('getRowData', rowKey);
    return raw;
}

function editCustom(id) {
    var raw = jQuery("#grid-table").jqGrid('getRowData', id);
    loadFormData(raw, $("#editForm"));
    $('#editModal').modal({
        show : true,
        backdrop : 'static'
    });
};

function showCustom(id) {
    var raw = jQuery("#grid-table").jqGrid('getRowData', id);
    loadFormData(raw, $("#showForm"));
    $("#showModal").modal({
        show : true,
        backdrop : 'static'
    });
};

function deleteCustom(id) {
    var raw = jQuery("#grid-table").jqGrid('getRowData', id);
    $("#delForm input[name='id']").val(raw.no);
    $("#delForm #message").text("");
    $("#delForm #submit").attr("disabled", false);
    $("#delForm #question").show();
    $('#delModal').modal({
        show : true,
        backdrop : 'static'
    });
};

function picCustom(id) {
	raw = jQuery("#grid-table").jqGrid('getRowData', id);
	
	var resType = "bg";
	var resNo = raw.no;
	var resName = raw.name;
	var param="resType=" + resType + "&resNo=" + resNo + "&resName=" + resName;
	
	var frameSrc = "/pic/picpage.htm?" + param;
    $("#picIframe").attr("src", frameSrc);
    $('#picModal').modal({ show: true, backdrop: 'static' });
};

//酒店房型配置
function roomConfigCustom(id) {
    var raw = jQuery("#grid-table").jqGrid('getRowData', id);
    var page = "/jsp/room/room.jsp?no=" + raw.no+"&name=" + escape(raw.name);
    window.open(page);
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
        $(n).find("option[value='" + raw[$(n).attr('name')] + "']").prop('selected', true);
    });
    // 设置textarea域
    $(from).find("textarea").each(function(i, n) {
        if($(n).attr('title') == 'ckeditor'){
            CKEDITOR.instances[$(n).attr('id')].setData(raw[$(n).attr('name')]);
        }else
            $(n).text(raw[$(n).attr('name')]);
    });
    //查询地区名称
    queryRegionName(from);
}

//查询地区名称
function queryRegionName(from){
    var no = $(from).find("input[name='regionno']").val();
    $.get("/pub/findcityname.htm", {no : no}, function(data){
        var json = eval("(" + data + ")");
        if (json.result == "ok")
            $(from).find("input[name='regionname']").val(json.name);
    });
}

/** 模式窗口关闭时触发事件 */
$("#addModal", parent.document).on("hidden.bs.modal", function() {
    
    $("#addModal #message").hide();
    $("#addModal input[type='text']").val("");
    $("#addModal input[type='hidden']").val("");
    $("#addModal input").prop("checked", false);
    $("#addModal select").val("");
    $("#addModal textarea").text("");
    CKEDITOR.instances["desc_add"].setData('');
    
    $(this).removeData("bs.modal");
    $("#grid-table").trigger("reloadGrid");
});

$("#editModal", parent.document).on("hidden.bs.modal", function() {
    $("#editModal #message").hide();
    $("#editForm input[type='text']").val("");
    $("#editForm input[type='hidden']").val("");
    $("#editForm input").prop("checked", false);
    $("#editForm select").val("");
    $("#editForm textarea").text("");
    CKEDITOR.instances["desc_edit"].setData('');
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

/**
 * add by marongcai
 * 下拉框从数据库动态获取
 * 2016-3-17
 * add by start
 */
$.ajax({
	type: "GET",
	traditional: true,
	url: "/dict/selectDict.htm",
	data: "dict.fsParentno=bg",
	dataType: "json",
	success: function(data){
		html = '<option value="">---请选择---</option>'; 
		$.each(data, function(commentIndex, comment){
			html += '<option value=' + comment['fsDictno'] + '>' + comment['fsDictname'] + '</option>';
		});
		$("#starlvl").html(html);
	}
});
/**
 * add end
 */

jQuery(function($) {
    //酒店房型配置 iframe 高度适配
    $("#roomIframe").css({"height": screen.height});
    
    // 查询条件-城市初始化
    var localsel = $("#queryfield #selectCity").localCity({
        provurl : "/pub/findcity.htm",
        cityurl : "/pub/findcity.htm",
        disturl : "/pub/findcity.htm",
        callback : localcallback
    });

    // 查询条件-城市选择器回调
    function localcallback(index, key, value, fullkey, fullname) {
        $("#queryfield #regionname").val(fullname);
        $("#queryfield #regionno").val(key);
        if (index == 3) {
            $("#queryfield #selectCity").hide();
        }
    }
    // 查询条件-触发城市选择器
    $("#queryfield #regionname").click(function() {
        $("#queryfield #selectCity").show();
    })

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
                var postData = $("#grid-table").jqGrid("getGridParam", "postData");
                /**
                 * modify by marongcai
                 * 酒店代码改为酒店等级
                 * 2016-3-16
                 * modify by start
                 */
                postData["accomadation.starlvl"] = $("#queryfield").find("#starlvl").val();
                /**
                 * modify end
                 */
                postData["accomadation.name"] = $("#queryfield").find("#name").val();
                postData["accomadation.regionno"] = $("#queryfield").find("#regionno").val();
                postData["accomadation.stat"] = $("#queryfield").find("#stat").val();
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
        
        var roomBtn = '<div title="" class="ui-pg-div ui-inline-edit" id="roomButton" style="display: block; cursor: pointer; float: left;" onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" onclick="roomConfigCustom('
                + options.rowId
                + ');" data-original-title="酒店房型配置"><span class="ui-icon ace-icon fa fa-cog red"></span></div>';
        
        var detailBtn = '<div title="" class="ui-pg-div ui-inline-edit" id="roomButton" style="display: block; cursor: pointer; float: left;" onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" onclick="showCustom('
            + options.rowId
            + ');" data-original-title="查看记录详情"><span class="ui-icon ace-icon fa fa-search-plus grey"></span></div>';
        
        var editBtn = '<div title="" class="ui-pg-div ui-inline-edit" id="editButton" style="display: block; cursor: pointer; float: left;" onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" onclick="editCustom('
                + options.rowId
                + ');" data-original-title="编辑本条记录"><span class="ui-icon ui-icon-pencil"></span></div>';

        var deleteBtn = '<div title="" class="ui-pg-div ui-inline-edit" id="deleteButton" style="display: block; cursor: pointer; float: left;" onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" onclick="deleteCustom('
                + options.rowId
                + ');" data-original-title="删除本条记录"><span class="ui-icon ace-icon fa fa-trash-o red"></span></div>';
        
        var picDtn = '<div title="" class="ui-pg-div ui-inline-edit" id="picButton" style="display: block; cursor: pointer; float: left;" onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" onclick="picCustom('
			+ options.rowId
			+ ');" data-original-title="编辑资源图片"><span class="ui-icon ace-icon fa fa-file-image-o green"></span></div>';
        
        return roomBtn + detailBtn + editBtn + deleteBtn + picDtn;
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
    
    $.ajax({
    	type: "GET",
    	url: "/dict/selectDict.htm",
    	data: "dict.fsParentno=bg",
    	dataType: "json",
    	async : false,
    	success: function(data){
    		var items = {};
    		$.each(data, function(commentIndex, comment){
    			items[comment["fsDictno"]] = comment["fsDictname"];
    		});
    		$(document).data("lvl_items", items);
    	}
	});

    //酒店等级
    var lvl_items = $(document).data("lvl_items");
    var lvl_val = '';
    for (k in lvl_items)
       lvl_val += ';' + k + ":" + lvl_items[k];
    lvl_val = lvl_val.substring(1);
    
    $(grid_selector).jqGrid(
            {
                url : "/accomadation/findAccomadation.htm",
                datatype : "json",
                mtype : 'POST',
                height : 400,
                colNames : [ '操作', '酒店代码', '所属地区编号', '等级', '酒店名称', '地址',
                        '酒店介绍', '酒店相关设施', '状态' ],
                colModel : [ {
                    name : 'myaction',
                    index : '',
                    width : 110,
                    fixed : true,
                    sortable : false,
                    resize : false,
                    formatter : actFormatter
                }, {
                    name : 'no',
                    index : 'no',
                    width : 50,
                    sorttype : "char"
                }, {
                    name : 'regionno',
                    index : 'regionno',
                    /**
                	 * modify by marongcai
                	 * 调整了显示的宽度
                	 * 2016-3-16
                	 * modify by start
                	 */
                    width : 50,
                    editable : true,
                    sorttype : "char"
                }, {
                    name : 'starlvl',
                    index : 'starlvl',
                    width : 45,
                    /**
                     * modify end
                     */
                    sortable : false,
                    editable : false,
                    edittype : 'select',
                    sorttype : "char",
                    editoptions : {
                        value : lvl_val
                    },
                    formatter : function(v, opt, rec) {
                        console.log(v);
                        return lvl_items[v];
                    },
                    unformat : function(v) {
                        for (k in lvl_items)
                            if (lvl_items[k] == v)
                                return k;
                        return '03';
                    }
                }, {
                    name : 'name',
                    index : 'name',
                    width : 80,
                    editable : false
                }, {
                    name : 'addr',
                    index : 'addr',
                    width : 80,
                    editable : true
                }, {
                    name : 'desc',
                    index : 'desc',
                    width : 80,
                    editable : false,
                    hidden : true
                }, {
                    name : 'speciality',
                    index : 'speciality',
                    width : 0,
                    editable : false,
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
                // multiselect : true,
                // multiboxonly : true,
                // multipleSearch : true,
                loadComplete : function() {
                    var table = this;
                    setTimeout(function() {
                        updatePagerIcons(table);
                        enableTooltips(table);
                    }, 0);
                },
                editurl : "/accomadation/save.htm",
                shrinkToFit : true,
                autowidth : true
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
                    $('#addModal').modal({
                        show : true,
                        backdrop : 'static'
                    });
                },
                position : "first",
                title : "新增酒店",
                cursor : "focus",
            });

    function style_delete_form(form) {
        var buttons = form.next().find('.EditButton .fm-button');
        buttons.addClass('btn btn-sm btn-white btn-round').find('[class*="-icon"]').hide();// ui-icon, s-icon
        buttons.eq(0).addClass('btn-danger').prepend('<i class="ace-icon fa fa-trash-o"></i>');
        buttons.eq(1).addClass('btn-default').prepend('<i class="ace-icon fa fa-times"></i>');
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

        form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />');
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
                            var $class = $.trim(icon.attr('class').replace('ui-icon', ''));

                            if ($class in replacement)
                                icon.attr('class', 'ui-icon ' + replacement[$class]);
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
