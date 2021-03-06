//	显示详情
var raw = {};
function showEntertainment(id) {
	raw = jQuery("#grid-table").jqGrid('getRowData', id);
	var frameSrc = "/jsp/entertainment/show.jsp";
    $("#showIframe").attr("src", frameSrc);
    $('#showModal').modal({ show: true, backdrop: 'static' });
};

function costEntertainment(id) {
	var raw = jQuery("#grid-table").jqGrid('getRowData', id);
	var page = "/jsp/entertainmentPrice/entertainmentPrice.jsp?fsNo=" + raw.fsNo + "&fsName=" + escape(raw.fsName);
	
	//娱乐项目为作废状态时，不允许添加价格
	if(raw.fiStat == 2){
		bootbox.alert(raw.fsName + "已经失效，不允许配置价格", null);
		return false;
	}
	
	window.open(page);
}

function editEntertainment(id) {
	raw = jQuery("#grid-table").jqGrid('getRowData', id);
	var frameSrc = "/jsp/entertainment/edit.jsp";
    $("#editIframe").attr("src", frameSrc);
    $('#editModal').modal({ show: true, backdrop: 'static' });
};

function deleteEntertainment(id) {
	raw = jQuery("#grid-table").jqGrid('getRowData', id);
	var frameSrc = "/jsp/entertainment/delete.jsp?no=" + raw.fsNo;
	$("#delIframe").attr("src", frameSrc);
    $('#delModal').modal({ show: true, backdrop: 'static' });
};

function picEntertainment(id) {
	raw = jQuery("#grid-table").jqGrid('getRowData', id);
	
	var resType = "yl";
	var resNo = raw.fsNo;
	var resName = raw.fsName;
	var param="resType=" + resType + "&resNo=" + resNo + "&resName=" + resName;
	
	var frameSrc = "/pic/picpage.htm?" + param;
    $("#picIframe").attr("src", frameSrc);
    $('#picModal').modal({ show: true, backdrop: 'static' });
};

$("#showIframe").on("load", function() {
	$(this).contents().find("#reset").click();
	
	$(this).contents().find("#fsNo").val(raw.fsNo);
	$(this).contents().find("#fsName").val(raw.fsName);
	$(this).contents().find("#fsType").val(raw.fsType);
	$(this).contents().find("#fiStat").val(raw.fiStat);
	$(this).contents().find("#fsDesc").val(raw.fsDesc);
	$(this).contents().find("#fsLvl").val(raw.fsLvl);
	$(this).contents().find("#fsAddr").val(raw.fsAddr);
	$(this).contents().find("#fsOpentime").val(raw.fsOpentime);
	$(this).contents().find("#fsRegionno").val(raw.fsRegionno);
	$(this).contents().find("#fsRegionName").val(raw.fsRegionName);
	$(this).contents().find("input").attr("readonly", "readonly");
	$(this).contents().find("textarea").attr("readonly", "readonly");
	$(this).contents().find("select").attr("disabled", "disabled");
});

$("#editIframe").on("load", function(){
	$(this).contents().find("#reset").click();
	
	$(this).contents().find("#fsNo").val(raw.fsNo);
	$(this).contents().find("#fsName").val(raw.fsName);
	$(this).contents().find("#fsType").val(raw.fsType);
	$(this).contents().find("#fiStat").val(raw.fiStat);
	$(this).contents().find("#fsDesc").val(raw.fsDesc);
	$(this).contents().find("#fsLvl").val(raw.fsLvl);
	$(this).contents().find("#fsAddr").val(raw.fsAddr);
	$(this).contents().find("#fsOpentime").val(raw.fsOpentime);
	$(this).contents().find("#fsRegionno").val(raw.fsRegionno);
	$(this).contents().find("#fsRegionName").val(raw.fsRegionName);
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

$("#picModal").on("hidden.bs.modal", function() {
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
		var postData = $("#grid-table").jqGrid("getGridParam", "postData");
		postData["entertainment.fsNo"] = $("#queryfield").find("#fsNo").val();
		postData["entertainment.fsScenicno"] = $("#queryfield").find("#fsScenicno").val();
		postData["entertainment.fsName"] = $("#queryfield").find("#fsName").val();
		postData["entertainment.fsType"] = $("#queryfield").find("#fsType").val();
		postData["entertainment.fiStat"] = $("#queryfield").find("#fiStat").val();
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
		var costBtn = '<div title="" class="ui-pg-div ui-inline-edit" id="costBtn" style="display: block; cursor: pointer; float: left;" onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" onclick="costEntertainment('
			+ options.rowId
			+ ');" data-original-title="娱乐项目价格配置"><span class="ui-icon ace-icon fa fa-cog red"></span></div>';
		
		var detail = '<div title="" class="ui-pg-div ui-inline-edit" id="detailButton" style="display: block; cursor: pointer; float: left;" onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" onclick="showEntertainment('
			+ options.rowId
			+ ');" data-original-title="查看记录详情"><span class="ui-icon ace-icon fa fa-search-plus grey"></span></div>';

		var editBtn = '<div title="" class="ui-pg-div ui-inline-edit" id="editButton" style="display: block; cursor: pointer; float: left;" onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" onclick="editEntertainment('
			+ options.rowId
			+ ');" data-original-title="编辑本条记录"><span class="ui-icon ui-icon-pencil"></span></div>';

		var deleteBtn = '<div title="" class="ui-pg-div ui-inline-edit" id="deleteButton" style="display: block; cursor: pointer; float: left;" onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" onclick="deleteEntertainment('
			+ options.rowId
			+ ');" data-original-title="删除本条记录"><span class="ui-icon ace-icon fa fa-trash-o red"></span></div>';
		var picDtn = '<div title="" class="ui-pg-div ui-inline-edit" id="picButton" style="display: block; cursor: pointer; float: left;" onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" onclick="picEntertainment('
			+ options.rowId
			+ ');" data-original-title="编辑资源图片"><span class="ui-icon ace-icon fa fa-file-image-o green"></span></div>';
		
		return costBtn + detail + editBtn + picDtn;
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
				if (event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed') {
					// setTimeout is for webkit only to give time for DOM
					// changes and then redraw!!!
					setTimeout(function() {
						$(grid_selector).jqGrid('setGridWidth', parent_column.width());
					}, 0);
				}
			});

	var items = {
		1 : '正常',
		2 : '失效'
	};
	var fsTypes = {
		'01' : '类型一',
		'02' : '类型二',
		'03' : '类型三'
	};
	var fsLvl = {
		'01' : '一级',
		'02' : '二级',
		'03' : '三级'
	};
	
	var s = '';
	for (k in items)
		s += ';' + k + ":" + items[k];
	s = s.substring(1);
	
	var t = '';
	for (k in fsTypes)
		t += ';' + k + ":" + fsTypes[k];
	t = t.substring(1);
	
	var g = '';
	for (k in fsLvl)
		g += ';' + k + ":" + fsTypes[k];
	g = g.substring(1);
	
	jQuery(grid_selector).jqGrid(
			{
				url : "/entertainment/findEntertainment.htm",
				datatype : "json",
				mtype : 'POST',
				height : 400,
				colNames : [ '操作', '娱乐项目代码', '娱乐项目名称', '景区代码', '所属景区代码', '所属地区代码', '所属地区', '娱乐项目地址', '娱乐项目类型', '状态', '级别', '开放时间', '描述' ],
				colModel : [{
					name : 'myac',
					index : '',
					width : 90,
					fixed : true,
					sortable : false,
					resize : false,
					formatter : actFormatter
				}, {
					name : 'fsNo',
					index : 'fsNo',
					width : 85,
					sorttype : "int",
					editable : true,
					hidden : true
				}, {
					name : 'fsName',
					index : 'fsName',
					width : 50,
					editable : true,
					sorttype : "char"
				}, {
					name : 'fsScenicno',
					index : 'fsScenicno',
					width : 100,
					editable : true,
					sorttype : "char",
					hidden : true
				}, {
					name : 'fsScenicname',
					index : 'fsScenicname',
					width : 50,
					editable : true,
					sorttype : "char",
					hidden : true
				}, {
					name : 'fsRegionno',
					index : 'fsRegionno',
					width : 100,
					editable : true,
					sorttype : "char",
					hidden : true
				}, {
					name : 'fsRegionName',
					index : 'fsRegionName',
					width : 100,
					editable : true,
					sorttype : "char"
				}, {
					name : 'fsAddr',
					index : 'fsAddr',
					width : 50,
					editable : true,
					sorttype : "char"
				}, {
					name : 'fsType',
					index : 'fsType',
					width : 30,
					sortable : true,
					editable : true,
					edittype : 'select',
					editoptions : {
						value : t
					},
					formatter : function(v, opt, rec) {
						return fsTypes[v];
					},
					unformat : function(v) {
						for (k in fsTypes)
							if (fsTypes[k] == v)
								return k;
						return '1';
					}
				}, {
					name : 'fiStat',
					index : 'fiStat',
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
					}
				},{
					name : 'fsLvl',
					index : 'fsLvl',
					width : 35,
					sortable : true,
					editable : true,
					edittype : 'select',
					editoptions : {
						value : g
					},
					formatter : function(v, opt, rec) {
						return fsLvl[v];
					},
					unformat : function(v) {
						for (k in fsLvl)
							if (fsLvl[k] == v)
								return k;
						return '1';
					}
				}, {
					name : 'fsOpentime',
					index : 'fsOpentime',
					width : 50,
					editable : true,
					sorttype : "char"
				}, {
					name : 'fsDesc',
					index : 'fsDesc',
					edittype : 'textarea',
					width : 100,
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
						updateActionIcons(table);
						updatePagerIcons(table);
						enableTooltips(table);
					}, 0);
				},

				editurl : "/entertainment/save.htm",
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

					form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
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
					form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
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

	jQuery(grid_selector).jqGrid('navGrid', pager_selector).jqGrid(
			'navButtonAdd', pager_selector, {
				caption : "",
				buttonicon : "ace-icon fa fa-plus-circle purple",
				onClickButton : function() {
					var frameSrc = "/jsp/entertainment/add.jsp";
			        $("#addIframe").attr("src", frameSrc);
			        $('#addModal').modal({ show: true, backdrop: 'static' });
				},
				position : "first",
				title : "新增记录",
				cursor : "pointer"
			});

	function style_delete_form(form) {
		var buttons = form.next().find('.EditButton .fm-button');
		buttons.addClass('btn btn-sm btn-white btn-round').find('[class*="-icon"]').hide();   // ui-icon, s-icon
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

		form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
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
		$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function() {
				var icon = $(this);
				var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
				if ($class in replacement)
					icon.attr('class', 'ui-icon ' + replacement[$class]);
		});
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
