//	显示详情
var raw = {};

/**
 * 模态框--->"编辑"弹出页面
 */
function editDep(id) {
	raw = jQuery("#grid-table").jqGrid('getRowData', id);
	var frameSrc = "/user/dept/editpage.htm?depNo=" + raw.depNo;
    $("#editIframe").attr("src", frameSrc);
    $('#editModal').modal({ show: true, backdrop: 'static' });
};

/**
 * 模态框--->"查看详情"弹出页面
 */
function showDep(id) {
	raw = jQuery("#grid-table").jqGrid('getRowData', id);
	var frameSrc = "/user/dept/show.htm?depNo=" + raw.depNo
    $("#showIframe").attr("src", frameSrc);
    $('#showModal').modal({ show: true, backdrop: 'static' });
};


/**
 * 模态框--->"注销"弹出页面
 */
function cancelDep(id) {
	raw = jQuery("#grid-table").jqGrid('getRowData', id);
	var frameSrc = "/jsp/dept/cancel.jsp?depNo=" + raw.depNo;
	$("#delIframe").attr("src", frameSrc);
    $('#delModal').modal({ show: true, backdrop: 'static' });
};


$("#addModal", parent.document).on("hidden.bs.modal", function() {
    $(this).removeData("bs.modal");
	$("#grid-table").trigger("reloadGrid");
});

$("#showModal").on("hidden.bs.modal", function() {
    $(this).removeData("bs.modal");
});

$("#showModal").on("shown.bs.modal", function() {
	$(this).find("#reset").click();
});


$("#editIframe").on("load",function(){
	$(this).contents().find("#reset").click();
});

$("#editModal", parent.document).on("hidden.bs.modal", function() {
    $(this).removeData("bs.modal");
    $("#grid-table").trigger("reloadGrid");
});

$("#delModal").on("hidden.bs.modal", function() {
    $(this).removeData("bs.modal");
    $("#grid-table").trigger("reloadGrid");
});

jQuery(function($){
	/**
	 * 功能：部门查询--"提交"按钮click事件的响应函数
	 */
	$("#submit").on("click", function() {
		$("#collapseOne").collapse('hide');//查询条件面板收缩
		
		//TODO 查询条件验证
		
		//封装查询条件数据
		var postData = $("#grid-table").jqGrid("getGridParam", "postData");
		postData["sysDep.depNo"] = $("#queryfield").find("#depno").val();//部门编号
		postData["sysDep.depName"] = $("#queryfield").find("#depname").val();//部门名称
		postData["sysDep.stat"] = $("#queryfield").find("#stat").val();//状态
		$("#grid-table").jqGrid("setGridParam", {//jqGrid重新加载数据
			datatype : 'json',
			postData : postData
		}).trigger("reloadGrid");
	});
	
	
	/**
	 * 功能：部门查询--"重置"按钮click事件的响应函数
	 */
	$("#reset", "#queryfield").click(function(){
		$("#depno").val(null);//重置部门编号
		$("#depname").val(null);//重置部门名称
		$("#stat").find('option[value="1"]').prop("checked",true);//重置部门状态，默认为正常
	});

	//jqGrid选择器
	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";

	/**
	 * 功能：部门查询-->列表(填充数据)-->定义操作列的按钮
	 */
	actFormatter = function(cellvalue, options, rawObject) {
		var detail = '<div title="" class="ui-pg-div ui-inline-edit" id="detailButton" style="display: block; cursor: pointer; float: left;" onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" onclick="showDep('
				+ options.rowId
				+ ');" data-original-title="查看部门详情"><span class="ui-icon ace-icon fa fa-search-plus grey"></span></div>';

		var editBtn = '<div title="" class="ui-pg-div ui-inline-edit" id="editButton" style="display: block; cursor: pointer; float: left;" onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" onclick="editDep('
				+ options.rowId
				+ ');" data-original-title="编辑部门信息"><span class="ui-icon ui-icon-pencil"></span></div>';

		var deleteBtn = '<div title="" class="ui-pg-div ui-inline-edit" id="deleteButton" style="display: block; cursor: pointer; float: left;" onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" onclick="cancelDep('
				+ options.rowId
				+ ');" data-original-title="注销该部门"><span class="ui-icon ace-icon fa fa-trash-o red"></span></div>';
		return detail + editBtn + deleteBtn ;
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
			})

	var items = {
		1 : '正常',
		2 : '失效'
	};
	
	var s = '';
	for (k in items)
		s += ';' + k + ":" + items[k];
	s = s.substring(1);
	
	/**
	 * 功能：部门查询--请求和填充数据
	 */
	jQuery(grid_selector).jqGrid(
			{
				url : "/user/dept/list.htm",
				datatype : "json",
				mtype : 'POST',
				height : 400,
				colNames : [ '操作', '部门编号', '部门名称', '状态' ],
				colModel : [ {
					name : 'myac',
					index : '',
					width : 150,
					fixed : true,
					sortable : false,
					resize : false,
					formatter : actFormatter,
				}, {
					name : 'depNo',
					index : 'depNo',
					width : 200,
					sorttype : "int"
				}, {
					name : 'depName',
					index : 'depName',
					width : 300,
					editable : true,
					sorttype : "char",
				}, {
					name : 'stat',
					index : 'stat',
					width : 200,
					sortable : true,
					editable : true,
					edittype : 'select',
					editoptions : {
						value : s
					},
					formatter : function(v, opt, rec) {
						return items[v];
					},
					unformat : function(v) {
						for (k in items)
							if (items[k] == v)
								return k;
						return '1';
					},
				} ],

				viewrecords : true,
				rowNum : 10,
				rowList : [ 10, 20, 30 ],
				pager : pager_selector,
				altRows : true,

//				multiselect : true,
//				multiboxonly : true,
//				multipleSearch : true,

				loadComplete : function() {
					var table = this;
					setTimeout(function() {

						updateActionIcons(table);
						updatePagerIcons(table);
						enableTooltips(table);
					}, 0);
				},

//				editurl : "/scenic/save.htm",
				shrinkToFit : true,
				autowidth : true,

			});
	$(window).triggerHandler('resize.jqGrid');// trigger window resize to make
	// the grid get the correct size

	/**
	 * 功能：部门查询-->填充数据-->按钮
	 */
	jQuery(grid_selector).jqGrid(
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
				view : false,
				viewicon : 'ace-icon fa fa-search-plus grey',
			},
			{
				// delete record form
				recreateForm : true,
				beforeShowForm : function(e) {
					var form = $(e[0]);
					if (form.data('styled'))
						return false;

					form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar')
							.wrapInner('<div class="widget-header" />')
					style_delete_form(form);

					form.data('styled', true);
				},
				onClick : function(e) {
					alert(1);
				}
			},
			
			{
				// search form
				recreateForm : true,
				afterShowSearch : function(e) {
					var form = $(e[0]);
					form.closest('.ui-jqdialog').find('.ui-jqdialog-title')
							.wrap('<div class="widget-header" />')
					style_search_filters(form);
				},
				afterRedraw : function() {
					style_search_filters($(this));
				},
				multipleSearch : true,
			/**
			 * multipleGroup:true, showQuery: true
			 */
			});
	/**
	 * 功能：部门查询-->数据填充-->添加"新增"按钮
	 */
	jQuery(grid_selector).jqGrid('navGrid', pager_selector).jqGrid(
			'navButtonAdd', pager_selector, {
				caption : "",
				buttonicon : "ace-icon fa fa-plus-circle purple",
				onClickButton : function() {
					var frameSrc = "/user/dept/addpage.htm";
			        $("#addIframe").attr("src", frameSrc);
			        $('#addModal').modal({ show: true, backdrop: 'static' });
				},
				position : "first",
				title : "新增部门",
				cursor : "pointer",
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

	// unlike navButtons icons, action icons in rows seem to be hard-coded
	// you can change them like this in here if you want
	function updateActionIcons(table) {
		/**
		 * var replacement = { 'ui-ace-icon fa fa-pencil' : 'ace-icon fa
		 * fa-pencil blue', 'ui-ace-icon fa fa-trash-o' : 'ace-icon fa
		 * fa-trash-o red', 'ui-icon-disk' : 'ace-icon fa fa-check green',
		 * 'ui-icon-cancel' : 'ace-icon fa fa-times red' };
		 * $(table).find('.ui-pg-div span.ui-icon').each(function(){ var icon =
		 * $(this); var $class = $.trim(icon.attr('class').replace('ui-icon',
		 * '')); if($class in replacement) icon.attr('class', 'ui-icon
		 * '+replacement[$class]); })
		 */
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
