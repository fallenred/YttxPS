//	显示详情
var raw = {};
function showCustom(id) {
	raw = jQuery("#grid-table").jqGrid('getRowData', id);
	$("#showModal").modal({
	    remote: "/jsp/scenic/show.jsp"
	});
};

function editCustom(id) {
	raw = jQuery("#grid-table").jqGrid('getRowData', id);
	var frameSrc = "/jsp/scenic/edit.jsp";
    $("#editIframe").attr("src", frameSrc);
    $('#editModal').modal({ show: true, backdrop: 'static' });
};

function deleteCustom(id) {
	raw = jQuery("#grid-table").jqGrid('getRowData', id);
	var frameSrc = "/jsp/scenic/delete.jsp?no=" + raw.no;
	$("#delIframe").attr("src", frameSrc);
    $('#delModal').modal({ show: true, backdrop: 'static' });
};

function picCustom(id) {
	raw = jQuery("#grid-table").jqGrid('getRowData', id);
	window.location.href="/jsp/pic/pic.jsp?no=" + raw.no;
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

$("#editIframe").on("load",function(){
	$(this).contents().find("#reset").click();
	$(this).contents().find("#no").val(raw.no);
	$(this).contents().find("#name").val(raw.name);
	$(this).contents().find("#lvl").val(raw.lvl);
	getregionname(raw.regionno, function(name){
		$("#editIframe").contents().find("#regionname").val(name);
	});
	$(this).contents().find("#regionno").val(raw.regionno);
	$(this).contents().find("#stat").val(raw.stat);
	$(this).contents().find("#addr").val(raw.addr);
	$(this).contents().find("#desc").val(raw.desc);
	$(this).contents().find("#opentime").val(raw.opentime);
	$(this).contents().find("#speciality").val(raw.speciality);
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
		window.history.back(-1);
		$("#collapseOne").collapse('hide');
		// $("#collapseTwo").collapse('show');
		var postData = $("#grid-table").jqGrid("getGridParam", "postData");
		postData["scenic.no"] = $("#queryfield").find("#no").val();
		postData["scenic.name"] = $("#queryfield").find("#name").val();
		postData["scenic.regionno"] = $("#queryfield").find("#regionno").val();
		postData["scenic.lvl"] = $("#queryfield").find("#lvl").val();
		postData["scenic.stat"] = $("#queryfield").find("#stat").val();
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
		var detail = '<div title="" class="ui-pg-div ui-inline-edit" id="detailButton" style="display: block; cursor: pointer; float: left;" onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" onclick="showCustom('
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
		return detail + editBtn + deleteBtn + picDtn;
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
	jQuery(grid_selector).jqGrid(
			{
				url : "/scenic/findScenic.htm",
				datatype : "json",
				mtype : 'POST',
				height : 400,
				colNames : [ '操作', '景点代码', '景点名称', '所选地区', '景点地址', '景点描述',
						'景点级别', '开放时间', '特产', '状态' ],
				colModel : [ {
					name : 'myac',
					index : '',
					width : 100,
					fixed : true,
					sortable : false,
					resize : false,
					formatter : actFormatter,
				}, {
					name : 'no',
					index : 'no',
					width : 85,
					sorttype : "int"
				}, {
					name : 'name',
					index : 'name',
					width : 200,
					editable : true,
					sorttype : "char",
				}, {
					name : 'regionno',
					index : 'regionno',
					editable : true,
					hidden : true,
				}, {
					name : 'addr',
					index : 'addr',
					width : 300,
					editable : true,
				}, {
					name : 'desc',
					index : 'desc',
					edittype : 'textarea',
					editable : true,
					hidden : true,
				}, {
					name : 'lvl',
					index : 'lvl',
					width : 70,
					editable : true,
				}, {
					name : 'opentime',
					index : 'opentime',
					width : 160,
					editable : true,
				}, {
					name : 'speciality',
					index : 'speciality',
					edittype : 'textarea',
					editable : true,
					hidden : true,
				}, {
					name : 'stat',
					index : 'stat',
					width : 35,
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

				editurl : "/scenic/save.htm",
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

	jQuery(grid_selector).jqGrid('navGrid', pager_selector).jqGrid(
			'navButtonAdd', pager_selector, {
				caption : "",
				buttonicon : "ace-icon fa fa-plus-circle purple",
				onClickButton : function() {
					var frameSrc = "/jsp/scenic/add.jsp";
			        $("#addIframe").attr("src", frameSrc);
			        $('#addModal').modal({ show: true, backdrop: 'static' });
				},
				position : "first",
				title : "新增记录",
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
