//	显示详情
var raw = {};
function showCloselist(id) {
	raw = jQuery("#grid-table").jqGrid('getRowData', id);
	$("#showModal").modal({
	    remote: "/jsp/closelist/show.jsp"
	}); 
};

function editCloselist(id) {
	raw = jQuery("#grid-table").jqGrid('getRowData', id);
	var frameSrc = "/jsp/closelist/edit.jsp";
    $("#editIframe").attr("src", frameSrc);
    $('#editModal').modal({ show: true, backdrop: 'static' });
};

function deleteCloselist(id) {
	raw = jQuery("#grid-table").jqGrid('getRowData', id);
	var frameSrc = "/jsp/closelist/delete.jsp?no=" + raw.fsNo;
	$("#delIframe").attr("src", frameSrc);
    $('#delModal').modal({ show: true, backdrop: 'static' });
};

$("#showModal").on("shown.bs.modal", function() {
	$(this).find("#reset").click();
	$(this).find("#fsNo").val(raw.fsNo);
	$(this).find("#fsRouteId").val(raw.fsRouteId);
	$(this).find("#fsRoutename").val(raw.fsRoutename);
	$(this).find("#fsName").val(raw.fsName);
	$(this).find("#fsOrderId").val(raw.fsOrderId);
	$(this).find("#fsUserId").val(raw.fsUserId);
	$(this).find("#fsUserSubid").val(raw.fsUserSubid);
	$(this).find("#fsOperId").val(raw.fsOperId);
	$(this).find("#ftCreatdate").val(raw.ftCreatdate);
	$(this).find("#fdTotalfee").val(raw.fdTotalfee);
	$(this).find("#fdPaidamt").val(raw.fdPaidamt);
	$(this).find("#fdAmt").val(raw.fdAmt);
	$(this).find("#fsRemark").val(raw.fsRemark);
	$(this).find("#fiStat").val(raw.fiStat);
	$(this).find("#fcContent").val(raw.fcContent);
});

$("#editIframe").on("load",function(){
	$(this).contents().find("#reset").click();
	$(this).contents().find("#fsNo").val(raw.fsNo);
	$(this).contents().find("#fsRouteId").val(raw.fsRouteId);
	$(this).contents().find("#fsRoutename").val(raw.fsRoutename);
	$(this).contents().find("#fsName").val(raw.fsName);
	$(this).contents().find("#fsOrderId").val(raw.fsOrderId);
	$(this).contents().find("#fsUserId").val(raw.fsUserId);
	$(this).contents().find("#fsUserSubid").val(raw.fsUserSubid);
	$(this).contents().find("#fsOperId").val(raw.fsOperId);
	$(this).contents().find("#ftCreatdate").val(raw.ftCreatdate);
	$(this).contents().find("#fdTotalfee").val(raw.fdTotalfee);
	$(this).contents().find("#fdPaidamt").val(raw.fdPaidamt);
	$(this).contents().find("#fdAmt").val(raw.fdAmt);
	$(this).contents().find("#fsRemark").val(raw.fsRemark);
	$(this).contents().find("#fiStat").val(raw.fiStat);
	$(this).contents().find("#content").val(raw.fcContent);
});


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
	
	var localsel = $("#selectCity", "#queryfield").localCity({
		provurl : "/pub/findcity.htm",
		cityurl : "/pub/findcity.htm",
		disturl : "/pub/findcity.htm",
		callback : localcallback
	});
	
//	jqGrid form提交
	$("#submit").click(function() {
		$("#collapseOne").collapse('hide');
		// $("#collapseTwo").collapse('show');
		var postData = $("#grid-table").jqGrid("getGridParam", "postData");
		postData["closelist.fsNo"] = $("#queryfield").find("#fsNo").val();
		postData["closelist.fsName"] = $("#queryfield").find("#fsName").val();
		postData["closelist.fiDays"] = $("#queryfield").find("#fiDays").val();
		postData["closelist.fiStat"] = $("#queryfield").find("#fiStat").val();
		$("#grid-table").jqGrid("setGridParam", {
			postData : postData
		}).trigger("reloadGrid");
	})
	
//	城市选择器
	function localcallback(index, key, value, fullkey, fullname) {
		$("#regionname", "#queryfield").val(fullname);
		$("#regionno", "#queryfield").val(key);
		if (index == 3) {
			$("#selectCity", "#queryfield").hide();
		}
	}

	$("#regionname", "#queryfield").click(function() {
		$("#selectCity", "#queryfield").show();
	});
	
//	重置
	$("#reset", "#queryfield").click(function() {
		$("#selectCity", "#queryfield").hide();
		$("#regionno", "#queryfield").val(null);
	});

	//	jqgrid
	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";

	// 定义按钮列
	actFormatter = function(cellvalue, options, rawObject) {
		var detail = '<div title="" class="ui-pg-div ui-inline-search" id="detailButton" style="display: block; cursor: pointer; float: left;" onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" onclick="showCloselist('
			+ options.rowId
			+ ');" data-original-title="查看结算单信息"><span class="ui-icon ace-icon fa fa-search-plus grey"></span></div>';

		var editBtn = '<div title="" class="ui-pg-div ui-inline-edit" id="editButton" style="display: block; cursor: pointer; float: left;" onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" onclick="editCloselist('
				+ options.rowId
				+ ');" data-original-title="编辑结算单信息"><span class="ui-icon ui-icon-pencil"></span></div>';
	
		var deleteBtn = '<div title="" class="ui-pg-div ui-inline-edit" id="deleteButton" style="display: block; cursor: pointer; float: left;" onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" onclick="deleteCloselist('
				+ options.rowId
				+ ');" data-original-title="删除结算单记录"><span class="ui-icon ace-icon fa fa-trash-o red"></span></div>';
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

	var items = {
		'0' : '客户待确认',
		'1' : '结算单已确认',
		'2' : '结算完毕'
	};
	var s = '';
	for (k in items)
		s += ';' + k + ":" + items[k];
	s = s.substring(1);
	jQuery(grid_selector).jqGrid(
			{
				url : "/closelist/findCloselist.htm",
				datatype : "json",
				mtype : 'POST',
				height : 400,
				colNames : [ '操作', '结算id', '线路id', '线路名称', '结算单名称', '订单ID', '用户ID', '用户子ID', '计调ID',
				             '创建时间', '结算单快照', '预估全价', '已付金额', '双方需交易金额', '整体备注', '状态'],
				colModel : [ {
					name : 'myac',
					index : '',
					width : 70,
					fixed : true,
					sortable : false,
					resize : false,
					formatter : actFormatter
				}, {
					name : 'fsNo',
					index : 'fsNo',
					width : 85,
					sorttype : "char",
					hidden : true
				}, {
					name : 'fsRouteId',
					index : 'fsRouteId',
					width : 100,
					editable : true,
					sorttype : "char",
					hidden : true
				}, {
					name : 'fsRoutename',
					index : 'fsRoutename',
					width : 100,
					editable : true,
					sorttype : "char"
				}, {
					name : 'fsName',
					index : 'fsName',
					width : 100,
					editable : true,
					sorttype : "char"
				}, {
					name : 'fsOrderId',
					index : 'fsOrderId',
					width : 100,
					editable : true,
					sorttype : "char",
					hidden : true
				}, {
					name : 'fsUserId',
					index : 'fsUserId',
					width : 100,
					editable : true,
					sorttype : "char",
					hidden : true
				}, {
					name : 'fsUserSubid',
					index : 'fsUserSubid',
					width : 100,
					editable : true,
					sorttype : "char",
					hidden : true
				}, {
					name : 'fsOperId',
					index : 'fsOperId',
					width : 100,
					editable : true,
					sorttype : "char",
					hidden : true
				}, {
					name : 'ftCreatdate',
					index : 'ftCreatdate',
					width : 100,
					editable : true,
					sorttype : "char",
					formatter : function(value){
						var timestamp = "";
						if(value != ''){//rData[7]表示日期列
							timestamp = (new Date(parseFloat(value))).format("yyyy/MM/dd");
						}
						return timestamp;
					}
				}, {
					name : 'fcContent',
					index : 'fcContent',
					width : 100,
					editable : true,
					sorttype : "char",
					hidden : true
				}, {
					name : 'fdTotalfee',
					index : 'fdTotalfee',
					width : 100,
					editable : true,
					sorttype : "char"
				}, {
					name : 'fdPaidamt',
					index : 'fdPaidamt',
					width : 100,
					editable : true,
					sorttype : "char"
				}, {
					name : 'fdAmt',
					index : 'fdAmt',
					width : 100,
					editable : true,
					sorttype : "char"
				}, {
					name : 'fsRemark',
					index : 'fsRemark',
					width : 100,
					editable : true,
					sorttype : "char"
				}, {
					name : 'fiStat',
					index : 'fiStat',
					width : 65,
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
					}
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

				editurl : "/closelist/save.htm",
				shrinkToFit : true,
				autowidth : true

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
				viewicon : 'ace-icon fa fa-search-plus grey'
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
				multipleSearch : true
			/**
			 * multipleGroup:true, showQuery: true
			 */
			});

	/*jQuery(grid_selector).jqGrid('navGrid', pager_selector).jqGrid(
			'navButtonAdd', pager_selector, {
				caption : "",
				buttonicon : "ace-icon fa fa-plus-circle purple",
				onClickButton : function() {
					var frameSrc = "/jsp/closelist/add.jsp";
			        $("#addIframe").attr("src", frameSrc);
			        $('#addModal').modal({ show: true, backdrop: 'static' });
				},
				position : "first",
				title : "新增记录",
				cursor : "pointer"
			});*/

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
