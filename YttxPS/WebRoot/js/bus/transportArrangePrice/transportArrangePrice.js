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

jQuery(function($) {
    var fsResno = $.getUrlParam('fsResno');   //车型线路资源ID
    var fiGenindex = $.getUrlParam('fiGenindex');
    var fsTransNo = $.getUrlParam('fsTransNo');
    var tgenname = $.getUrlParam('tgenname');   //线路统称名字
    var transName = $.getUrlParam('transName');   //车型名称

    if(fsResno == null || fsResno == "" || fsResno == undefined){
        alert("未取到该车型线路资源ID");
        $("#roomModal", parent.document).find(".close").click();
    }
    
    // jqgrid
    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";

    // 定义按钮列
    actFormatter = function(cellvalue, options, rawObject) {
        var deleteBtn = '<div title="" class="ui-pg-div ui-inline-edit" id="deleteButton" style="display: block; cursor: pointer; float: left;" onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" onclick="deleteCustom('
                + options.rowId
                + ');" data-original-title="删除本条记录"><span class="ui-icon ace-icon fa fa-trash-o red"></span></div>';
        return deleteBtn;
    };

    // resize to fit page size
    $(window).on('resize.jqGrid', function() {
        $(grid_selector).jqGrid('setGridWidth', $(".page-content").width());
    });
    
    // resize on sidebar collapse/expand
    var parent_column = $(grid_selector).closest('[class*="col-"]');
    $(document).on(
            'settings.ace.jqGrid',
            function(ev, event_name, collapsed) {
                if (event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed') {
                    // setTimeout is for webkit only to give time for DOM
                    // changes and then redraw!!!
                    setTimeout(function() {
                        $(grid_selector).jqGrid('setGridWidth', parent_column.width());
                    }, 0);
                }
            });
    
    //表格
    $(grid_selector).jqGrid({
            url : "/transportArrange/findTransportarrangePrice.htm",
            postData:{ "transportarrangePrice.fsResno" : fsResno},
            datatype : "json",
            mtype : 'POST',
            height : 395,
            colNames : [ '操作', '消费选项编号', '资源编号', '资源类型', '开始日期', '结束日期', '定价'],
            colModel : [ 
                {
                    name : 'myaction',
                    index : '',
                    width : 100,
                    fixed : true,
                    sortable : false,
                    resize : false,
                    formatter : actFormatter
                }, {
                    name : 'fsCcno',
                    index : 'fsCcno',
                    width : 30,
                    sorttype : "char",
                    hidden : true
                }, {
                    name : 'fsResno',
                    index : 'fsResno',
                    width : 100,
                    editable : false
                }, {
                    name : 'fsRestype',
                    index : 'fsRestype',
                    width : 50,
                    sortable : false,
                    editable : false,
                    edittype : 'select',
                    sorttype : "char"
                }, {
					name : 'ftStartdate',
					index : 'ftStartdate',
					edittype : 'textarea',
					width : 50,
					editable : true,
					sorttype : "char",
					formatter : function(value){
						var timestamp = "";
						if(value != ''){//rData[7]表示日期列
							timestamp = (new Date(parseFloat(value))).format("yyyy-MM-dd");
						}
						return timestamp;
					}
				}, {
					name : 'ftEnddate',
					index : 'ftEnddate',
					edittype : 'textarea',
					width : 50,
					editable : true,
					sorttype : "char",
					formatter : function(value){
						var timestamp = "";
						if(value != ''){//rData[7]表示日期列
							timestamp = (new Date(parseFloat(value))).format("yyyy-MM-dd");
						}
						return timestamp;
					}
				}, {
                    name : 'fdPrice',
                    index : 'fdPrice',
                    width : 30,
                    editable : true,
                    sorttype : "char"
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
                autowidth : true,
                caption: (tgenname != null && transName != "") ? (tgenname + "/" + transName + " — 车型线路价格列表") : "车型线路价格列表"
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
                    $("#addModal").find("input[name='fsResno']").val(fsResno);
                    $("#addModal").find("input[name='fsTransNo']").val(fsTransNo);
                    $("#addModal").find("input[name='fiGenindex']").val(fiGenindex);
                    $("#addModal").find("input[name='tgenname']").val(tgenname);
                    $("#addModal").find("input[name='transName']").val(transName);
                    $('#addModal').modal({
                        show : true,
                        backdrop : 'static'
                    });
                },
                position : "first",
                title : "新增车型线路价格",
                cursor : "focus",
    });

    function style_search_filters(form) {
        form.find('.delete-rule').val('X');
        form.find('.add-rule').addClass('btn btn-xs btn-primary');
        form.find('.add-group').addClass('btn btn-xs btn-success');
        form.find('.delete-group').addClass('btn btn-xs btn-danger');
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

function deleteCustom(id) {
    var raw = jQuery("#grid-table").jqGrid('getRowData', id);
    $("#delForm input[name='fsResno']").val(raw.fsResno);
    $("#delForm input[name='ftStartdate']").val(raw.ftStartdate);
    $("#delForm input[name='fsCcno']").val(raw.fsCcno);
    $("#delForm input[name='fsRestype']").val(raw.fsRestype);
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

