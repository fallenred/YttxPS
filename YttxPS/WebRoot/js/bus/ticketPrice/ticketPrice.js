//	显示详情
var raw = {};
function showTicketPrice(id) {
	raw = jQuery("#grid-table").jqGrid('getRowData', id);
	$("#showModal").modal({
	    remote: "/jsp/ticketPrice/show.jsp"
	}); 
};

function editTicketPrice(id) {
	raw = jQuery("#grid-table").jqGrid('getRowData', id);
	var frameSrc = "/jsp/ticketPrice/edit.jsp";
    $("#editIframe").attr("src", frameSrc);
    $('#editModal').modal({ show: true, backdrop: 'static' });
};

function deleteTicketPrice(id) {
	raw = jQuery("#grid-table").jqGrid('getRowData', id);
	var frameSrc = "/jsp/ticketPrice/delete.jsp?no=" + raw.fsNo + "&ftStartdate=" + raw.ftStartdate +
					"&ftEnddate=" + raw.ftEnddate;
	$("#delIframe").attr("src", frameSrc);
    $('#delModal').modal({ show: true, backdrop: 'static' });
};

function picTicketPrice(id) {
	raw = jQuery("#grid-table").jqGrid('getRowData', id);
	var frameSrc = "/jsp/pic/pic.jsp?no=" + raw.fsNo;
    $("#picIframe").attr("src", frameSrc);
    $('#picModal').modal({ show: true, backdrop: 'static' });
};

$("#showModal").on("shown.bs.modal", function() {
	$(this).find("#reset").click();
	$(this).find("#fsName").val(raw.fsName);
	$(this).find("#ftStartdate").val(raw.ftStartdate);
	$(this).find("#ftEnddate").val(raw.ftEnddate);
	//旺季
	if (raw.fdFullLowQp == '') {
		$(this).find("#priceType").val('2');
		$(this).find(".peak").show();
		$(this).find(".low").hide();
	}
	$(this).find("#fdFullLowQp").val(raw.fdFullLowQp);
	$(this).find("#fdHalfLowQp").val(raw.fdHalfLowQp);
	$(this).find("#fdChildLowQp").val(raw.fdChildLowQp);
	$(this).find("#fdFreeLowQp").val(raw.fdFreeLowQp);
	$(this).find("#fdFullLowTp").val(raw.fdFullLowTp);
	$(this).find("#fdHalfLowTp").val(raw.fdHalfLowTp);
	$(this).find("#fdChildLowTp").val(raw.fdChildLowTp);
	$(this).find("#fdFreeLowTp").val(raw.fdFreeLowTp);
	$(this).find("#fdFullPeakQp").val(raw.fdFullPeakQp);
	$(this).find("#fdHalfPeakQp").val(raw.fdHalfPeakQp);
	$(this).find("#fdChildPeakQp").val(raw.fdChildPeakQp);
	$(this).find("#fdFreePeakQp").val(raw.fdFreePeakQp);
	$(this).find("#fdFullPeakTp").val(raw.fdFullPeakTp);
	$(this).find("#fdHalfPeakTp").val(raw.fdHalfPeakTp);
	$(this).find("#fdChildPeakTp").val(raw.fdChildPeakTp);
	$(this).find("#fdFreePeakTp").val(raw.fdFreePeakTp);
});

$("#editIframe").on("load",function(){
	$(this).contents().find("#reset").click();
	$(this).contents().find("#fsNo").val(raw.fsNo);
	$(this).contents().find("#fsName").val(raw.fsName);
	$(this).contents().find("#fsScenicno").val(raw.fsScenicno);
	$(this).contents().find("#fsType").val(raw.fsType);
	$(this).contents().find("#fiStat").val(raw.fiStat);
	$(this).contents().find("#fsDesc").val(raw.fsDesc);
	$(this).contents().find("#ftStartdate").val(raw.ftStartdate);
	$(this).contents().find("#ftEnddate").val(raw.ftEnddate);
	//旺季
	if (raw.fdFullLowQp == '') {
		$(this).contents().find("#priceType").val('2');
		$(this).contents().find("#priceTypeName").val('旺季');
		$(this).contents().find(".peak").show();
		$(this).contents().find(".low").hide();
	} else {
		$(this).contents().find("#priceTypeName").val('淡季');
	}
	$(this).contents().find("#fdFullLowQp").val(raw.fdFullLowQp);
	$(this).contents().find("#fdHalfLowQp").val(raw.fdHalfLowQp);
	$(this).contents().find("#fdChildLowQp").val(raw.fdChildLowQp);
	$(this).contents().find("#fdFreeLowQp").val(raw.fdFreeLowQp);
	$(this).contents().find("#fdFullLowTp").val(raw.fdFullLowTp);
	$(this).contents().find("#fdHalfLowTp").val(raw.fdHalfLowTp);
	$(this).contents().find("#fdChildLowTp").val(raw.fdChildLowTp);
	$(this).contents().find("#fdFreeLowTp").val(raw.fdFreeLowTp);
	$(this).contents().find("#fdFullPeakQp").val(raw.fdFullPeakQp);
	$(this).contents().find("#fdHalfPeakQp").val(raw.fdHalfPeakQp);
	$(this).contents().find("#fdChildPeakQp").val(raw.fdChildPeakQp);
	$(this).contents().find("#fdFreePeakQp").val(raw.fdFreePeakQp);
	$(this).contents().find("#fdFullPeakTp").val(raw.fdFullPeakTp);
	$(this).contents().find("#fdHalfPeakTp").val(raw.fdHalfPeakTp);
	$(this).contents().find("#fdChildPeakTp").val(raw.fdChildPeakTp);
	$(this).contents().find("#fdFreePeakTp").val(raw.fdFreePeakTp);
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
	var fsNo = $.getUrlParam('fsNo');
    var fsName = $.getUrlParam('fsName');

    if(fsNo == null || fsNo == "" || fsNo == undefined){
        alert("未取到门票编号！");
        $("#roomModal", parent.document).find(".close").click();
    }
    $("title").html(fsName + "-门票价格维护");
    
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
		postData["ticketPrice.fsNo"] = $("#queryfield").find("#fsNo").val();
		postData["ticketPrice.fsScenicno"] = $("#queryfield").find("#fsScenicno").val();
		postData["ticketPrice.fsName"] = $("#queryfield").find("#fsName").val();
		postData["ticketPrice.fsType"] = $("#queryfield").find("#fsType").val();
		postData["ticketPrice.fiStat"] = $("#queryfield").find("#fiStat").val();
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
		var detail = '<div title="" class="ui-pg-div ui-inline-edit" id="detailButton" style="display: block; cursor: pointer; float: left;" onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" onclick="showTicketPrice('
			+ options.rowId
			+ ');" data-original-title="查看记录详情"><span class="ui-icon ace-icon fa fa-search-plus grey"></span></div>';

	var editBtn = '<div title="" class="ui-pg-div ui-inline-edit" id="editButton" style="display: block; cursor: pointer; float: left;" onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" onclick="editTicketPrice('
			+ options.rowId
			+ ');" data-original-title="编辑本条记录"><span class="ui-icon ui-icon-pencil"></span></div>';

	var deleteBtn = '<div title="" class="ui-pg-div ui-inline-edit" id="deleteButton" style="display: block; cursor: pointer; float: left;" onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" onclick="deleteTicketPrice('
			+ options.rowId
			+ ');" data-original-title="删除本条记录"><span class="ui-icon ace-icon fa fa-trash-o red"></span></div>';
	var picDtn = '<div title="" class="ui-pg-div ui-inline-edit" id="picButton" style="display: block; cursor: pointer; float: left;" onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" onclick="picTicketPrice('
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
			});

	var items = {
			1 : '淡季价格',
			2 : '旺季价格'
		};
	var s = '';
	for (k in items)
		s += ';' + k + ":" + items[k];
	s = s.substring(1);
	jQuery(grid_selector).jqGrid(
			{
				url : "/ticket/findTicketPrice.htm",
				datatype : "json",
				mtype : 'POST',
				height : 400,
				colNames : [ '操作', '门票代码', '票名称', '价格类型' ,'团队全票价格', '淡季挂牌价格全票', '淡季挂牌价格半票', '淡季挂牌价格儿童票', 
				             '淡季挂牌价格免票', '淡季团队价格全票', '淡季团队价格半票', '淡季团队价格儿童票', '淡季团队价格免票', '旺季挂牌价格全票',
				             '旺季挂牌价格半票', '旺季挂牌价格儿童票', '旺季挂牌价格免票', '旺季团队价格全票', '旺季团队价格半票', '旺季团队价格儿童票',
				             '旺季团队价格免票', '开始日期', '结束日期' ],
				colModel : [ {
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
					width : 70,
					editable : true,
					sorttype : "char"
				}, {
					name : 'fdFullLowQp',
					index : 'fdFullLowQp',
					width : 35,
					sortable : true,
					editable : true,
					edittype : 'select',
					editoptions : {
						value : s
					},
					formatter : function(v, opt, rec) {
						//旺季价格
						if (v == null) {
							return items['2'];
						} else {
							return items['1'];
						}
					},
					unformat : function(v) {
						for (k in items)
							if (items[k] == v)
								return k;
						return '1';
					}
				}, {
					name : 'fdFullTp',
					index : 'fdFullTp',
					width : 35,
					sortable : true,
					editable : true
				}, {
					name : 'fdFullLowQp',
					index : 'fdFullLowQp',
					width : 100,
					editable : true,
					hidden : true
				}, {
					name : 'fdHalfLowQp',
					index : 'fdHalfLowQp',
					width : 100,
					editable : true,
					hidden : true
				}, {
					name : 'fdChildLowQp',
					index : 'fdChildLowQp',
					width : 100,
					editable : true,
					hidden : true
				}, {
					name : 'fdFreeLowQp',
					index : 'fdFreeLowQp',
					width : 100,
					editable : true,
					hidden : true
				}, {
					name : 'fdFullLowTp',
					index : 'fdFullLowTp',
					width : 100,
					editable : true,
					hidden : true
				}, {
					name : 'fdHalfLowTp',
					index : 'fdHalfLowTp',
					width : 100,
					editable : true,
					hidden : true
				}, {
					name : 'fdChildLowTp',
					index : 'fdChildLowTp',
					width : 100,
					editable : true,
					hidden : true
				}, {
					name : 'fdFreeLowTp',
					index : 'fdFreeLowTp',
					width : 100,
					editable : true,
					hidden : true
				}, {
					name : 'fdFullPeakQp',
					index : 'fdFullPeakQp',
					width : 100,
					editable : true,
					hidden : true
				}, {
					name : 'fdHalfPeakQp',
					index : 'fdHalfPeakQp',
					width : 100,
					editable : true,
					hidden : true
				}, {
					name : 'fdChildPeakQp',
					index : 'fdChildPeakQp',
					width : 100,
					editable : true,
					hidden : true
				}, {
					name : 'fdFreePeakQp',
					index : 'fdFreePeakQp',
					width : 100,
					editable : true,
					hidden : true
				}, {
					name : 'fdFullPeakTp',
					index : 'fdFullPeakTp',
					width : 100,
					editable : true,
					hidden : true
				}, {
					name : 'fdHalfPeakTp',
					index : 'fdHalfPeakTp',
					width : 100,
					editable : true,
					hidden : true
				}, {
					name : 'fdChildPeakTp',
					index : 'fdChildPeakTp',
					width : 100,
					editable : true,
					hidden : true
				}, {
					name : 'fdFreePeakTp',
					index : 'fdFreePeakTp',
					width : 100,
					editable : true,
					hidden : true
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
							timestamp = (new Date(parseFloat(value))).format("yyyy/MM/dd");
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
							timestamp = (new Date(parseFloat(value))).format("yyyy/MM/dd");
						}
						return timestamp;
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

				editurl : "/ticketPrice/save.htm",
				shrinkToFit : true,
				autowidth : true,
				caption: (fsName!=null && fsName!="")?(fsName+"—门票价格配置列表"):"门票价格列表"
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

	jQuery(grid_selector).jqGrid('navGrid', pager_selector).jqGrid(
			'navButtonAdd', pager_selector, {
				caption : "",
				buttonicon : "ace-icon fa fa-plus-circle purple",
				onClickButton : function() {
					var frameSrc = "/jsp/ticketPrice/add.jsp";
			        $("#addIframe").attr("src", frameSrc);
			        $('#addModal').modal({ show: true, backdrop: 'static' });
				},
				position : "first",
				title : "新增记录",
				cursor : "pointer"
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
