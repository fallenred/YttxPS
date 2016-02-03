//	显示详情
var raw = {};

function showCustom(id) {
	raw = jQuery("#grid-table").jqGrid('getRowData', id);
	$("#showModal").modal({
	    remote: "/jsp/member/edit.jsp"
	});
};

$("#showModal").on("shown.bs.modal", function() {

	$(this).find("#reset").click();
	$(this).find("#no").val(raw.no);
	$(this).find("#name").val(raw.name);
	$(this).find("#lvl").val(raw.lvl);
	$(this).find("#regionno").val(raw.regionno);
	getregionname(raw.regionno, function(name){
		$("#showModal").find("#regionname").val(name);
	});
	$(this).find("#stat").val(raw.stat);
	$(this).find("#addr").val(raw.addr);
	$(this).find("#desc").val(raw.desc);
	$(this).find("#opentime").val(raw.opentime);
	$(this).find("#speciality").val(raw.speciality);
});

$("#showModal").on("hidden.bs.modal", function() {
    $(this).removeData("bs.modal");
});

jQuery(function($) {
	
//	jqGrid form提交
	$("#submit").on("click", function() {
		$("#collapseOne").collapse('hide');
		// $("#collapseTwo").collapse('show');
		var postData = $("#grid-table").jqGrid("getGridParam", "postData");
		postData["custchgau.auditno"] = $("#queryfield").find("#auditno").val();
		if($("#queryfield").find("#audittype").val() != '0')
			postData["custchgau.audittype"] = $("#queryfield").find("#audittype").val();
		else
			postData["custchgau.audittype"] = undefined;
		postData["custchgau.name"] = $("#queryfield").find("#name").val();
		postData["custchgau.taname"] = $("#queryfield").find("#taname").val();
		postData["custchgau.auditret"] = $("#queryfield").find("#auditret").val();
		$("#grid-table").jqGrid("setGridParam", {
			datatype : 'json',
			postData : postData
		}).trigger("reloadGrid");
	});
	
//	重置
	$("#reset", "#queryfield").click(function() {
	});

	//	jqgrid
	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";

	// 定义按钮列
	actFormatter = function(cellvalue, options, rawObject) {
		var editBtn = '<div title="" class="ui-pg-div ui-inline-edit" id="editButton" style="display: block; cursor: pointer; float: left;" onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" onclick="editCustom('
				+ options.rowId
				+ ');" data-original-title="审批本条记录"><span class="ui-icon ui-icon-pencil"></span></div>';
		return editBtn;
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

	//	审批类型
	var type_items = {
		0 : '无效',
		1 : '新增',
		2 : '变更',
		4 : '删除'
	};
	var type_s = '';
	for (k in type_items)
		type_s += ';' + k + ":" + type_items[k];
	type_s = type_s.substring(1);
		
	// 初始化表格
	jQuery(grid_selector).jqGrid(
			{
				url : "/member/findCustChgAu.htm",
				datatype : "json",
				mtype : 'POST',
				height : 400,
				colNames : [ '操作', '审核编号', '审核类型', '客户ID', '客户名称', '旅行社名称',
						'营业许可证照片Url', '税务登记证照片Url', '联系方式', '身份证号',
						'邮件地址','申请时间','审核人','审核时间','审核结果','审核意见'],
				colModel : [ {
					name : 'myac',
					index : '',
					width : 50,
					fixed : true,
					sortable : false,
					resize : false,
					formatter : actFormatter,
				}, {
					name : 'auditno',
					index : 'auditno',
					width : 85,
					sorttype : "int"
				}, {
					name : 'audittype',
					index : 'audittype',
					width : 35,
					sortable : true,
					editable : true,
					edittype : 'select',
					editoptions : {
						value : type_s
					},
					formatter : function(v, opt, rec) {
						return type_items[v];
					},
					unformat : function(v) {
						for (k in type_items)
							if (type_items[k] == v)
								return k;
						return '0';
					},
				}, {
					name : 'id',
					index : 'id',
					editable : true,
					hidden : true,
				}, {
					name : 'name',
					index : 'name',
					width : 100,
					editable : true,
					sorttype : "char",
				}, {
					name : 'taname',
					index : 'taname',
					width : 100,
					editable : true,
					sorttype : "char",
				},{
					name : 'licenceno',
					index : 'licenceno',
					editable : true,
					hidden : true,
				}, {
					name : 'taxlicence',
					index : 'taxlicence',
					editable : true,
					hidden : true,
				}, {
					name : 'contact',
					index : 'contact',
					editable : true,
					hidden : true,
				}, {
					name : 'certid',
					index : 'certid',
					editable : true,
					hidden : true,
				}, {
					name : 'email',
					index : 'email',
					editable : true,
					hidden : true,
				}, {
					name : 'applytime',
					index : 'applytime',
					width : 80,
					editable : true,
				}, {
					name : 'auditor',
					index : 'auditor',
					width : 60,
					editable : true,
				}, {
					name : 'audittime',
					index : 'audittime',
					width : 80,
					editable : true,
				}, {
					name : 'auditret',
					index : 'auditret',
					width : 35,
					sortable : true,
					editable : true,
					edittype : 'select',
					editoptions : {
						value : '0:审核审核未通过;-1:审核未通过;1:审核通过'
					},
					formatter : function(v, opt, rec) {
						if(v == 0)
							return '未审核';
						else if(v == -1)
							return  '审核未通过';
						else if(v == 1)
							return '审核通过';
						else
							return '未定义';
					},
					unformat : function(v) {
						if(v == '未审核')
							return 0;
						else if(v == '审核未通过')
							return -1;
						else if(v == '审核通过')
							return 1;
						else
							return null;
					},
				}, {
					name : 'comment',
					index : 'comment',
					editable : true,
					hidden : true,
				}],

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

				editurl : "/member/edit.htm",
				shrinkToFit : true,
				autowidth : true,

			/**
			 * , grouping:true, groupingView : { groupField : ['name'],
			 * groupDataSorted : true, plusicon : 'fa fa-chevron-down
			 * bigger-110', minusicon : 'fa fa-chevron-up bigger-110' },
			 * caption: "Grouping"
			 */

			});
	$(window).triggerHandler('resize.jqGrid');// trigger window resize to make
	// the grid get the correct size

	// navButtons
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
