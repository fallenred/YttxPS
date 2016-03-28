//	显示详情
var raw = {};
function showEntertainmentPrice(id) {
	raw = jQuery("#grid-table").jqGrid('getRowData', id);
	$("#showModal").modal({
	    remote: "/jsp/entertainmentPrice/show.jsp"
	}); 
};

function editEntertainmentPrice(id) {
	raw = jQuery("#grid-table").jqGrid('getRowData', id);
	var frameSrc = "/jsp/entertainmentPrice/edit.jsp";
    $("#editIframe").attr("src", frameSrc);
    $('#editModal').modal({ show: true, backdrop: 'static' });
};

function deleteEntertainmentPrice(id) {
	raw = jQuery("#grid-table").jqGrid('getRowData', id);
	var frameSrc = "/jsp/entertainmentPrice/delete.jsp?no=" + raw.fsNo + "&ftStartdate=" + raw.ftStartdate +
					"&ftEnddate=" + raw.ftEnddate;
	$("#delIframe").attr("src", frameSrc);
    $('#delModal').modal({ show: true, backdrop: 'static' });
};

$("#showModal").on("shown.bs.modal", function() {
	$(this).find("#reset").click();
	$(this).find("#fsName").val(raw.fsName);
	$(this).find("#ftStartdate").val(raw.ftStartdate);
	$(this).find("#ftEnddate").val(raw.ftEnddate);
	
	$(this).find("#fdfulllowqp").val(raw.fdfulllowqp);   //挂牌价格全票
	$(this).find("#fdhalflowqp").val(raw.fdhalflowqp);   //挂牌价格半票
	$(this).find("#fdchildlowqp").val(raw.fdchildlowqp);   //挂牌价格儿童票
	$(this).find("#fdfreelowqp").val(raw.fdfreelowqp);   //挂牌价格免票
	$(this).find("#fdfulllowtp").val(raw.fdfulllowtp);   //团队价格全票
	$(this).find("#fdhalflowtp").val(raw.fdhalflowtp);   //团队价格半票
	$(this).find("#fdchildlowtp").val(raw.fdchildlowtp);   //团队价格儿童票
	$(this).find("#fdfreelowtp").val(raw.fdfreelowtp);   //团队价格免票
	$(this).find("#fdtranscoststp").val(raw.fdtranscoststp);   //接送费用
});

$("#editIframe").on("load",function(){
	$(this).contents().find("#reset").click();
	$(this).contents().find("#fsNo").val(raw.fsNo);
	$(this).contents().find("#fsName").val(raw.fsName);
	$(this).contents().find("#ftStartdate").val(raw.ftStartdate);
	$(this).contents().find("#ftEnddate").val(raw.ftEnddate);
	
	$(this).contents().find("#fdfulllowqp").val(raw.fdfulllowqp);   //挂牌价格全票
	$(this).contents().find("#fdhalflowqp").val(raw.fdhalflowqp);   //挂牌价格半票
	$(this).contents().find("#fdchildlowqp").val(raw.fdchildlowqp);   //挂牌价格儿童票
	$(this).contents().find("#fdfreelowqp").val(raw.fdfreelowqp);   //挂牌价格免票
	$(this).contents().find("#fdfulllowtp").val(raw.fdfulllowtp);   //团队价格全票
	$(this).contents().find("#fdhalflowtp").val(raw.fdhalflowtp);   //团队价格半票
	$(this).contents().find("#fdchildlowtp").val(raw.fdchildlowtp);   //团队价格儿童票
	$(this).contents().find("#fdfreelowtp").val(raw.fdfreelowtp);   //团队价格免票
	$(this).contents().find("#fdtranscoststp").val(raw.fdtranscoststp);   //接送费用
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
	
	var fsNo = $.getUrlParam("fsNo");   //娱乐项目资源ID
	var fsName = $.getUrlParam("fsName");   //娱乐项目名称
	
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
		var detail = '<div title="" class="ui-pg-div ui-inline-edit" id="detailButton" style="display: block; cursor: pointer; float: left;" onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" onclick="showEntertainmentPrice('
			+ options.rowId
			+ ');" data-original-title="查看记录详情"><span class="ui-icon ace-icon fa fa-search-plus grey"></span></div>';

	var editBtn = '<div title="" class="ui-pg-div ui-inline-edit" id="editButton" style="display: block; cursor: pointer; float: left;" onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" onclick="editEntertainmentPrice('
			+ options.rowId
			+ ');" data-original-title="编辑本条记录"><span class="ui-icon ui-icon-pencil"></span></div>';

	var deleteBtn = '<div title="" class="ui-pg-div ui-inline-edit" id="deleteButton" style="display: block; cursor: pointer; float: left;" onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" onclick="deleteEntertainmentPrice('
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

	jQuery(grid_selector).jqGrid(
			{
				url : "/entertainment/findEntertainmentPrice.htm",
				postData : {"entertainment.fsNo" : fsNo},
				datatype : "json",
				mtype : 'POST',
				height : 400,
				colNames : [ '操作', '娱乐项目代码', '娱乐项目名称', '挂牌价格全票', '挂牌价格半票', '挂牌价格儿童票', 
				             '挂牌价格免票', '团队价格全票', '团队价格半票', '团队价格儿童票', '团队价格免票', '接送费用', '开始日期', '结束日期' ],
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
				}, {//挂牌价格全票
					name : 'fdfulllowqp',
					index : 'fdfulllowqp',
					width : 100,
					editable : true,
					hidden : true
				}, {//挂牌价格半票
					name : 'fdhalflowqp',
					index : 'fdhalflowqp',
					width : 100,
					editable : true,
					hidden : true
				}, {//挂牌价格儿童票
					name : 'fdchildlowqp',
					index : 'fdchildlowqp',
					width : 100,
					editable : true,
					hidden : true
				}, {//挂牌价格免票
					name : 'fdfreelowqp',
					index : 'fdfreelowqp',
					width : 100,
					editable : true,
					hidden : true
				}, {//团队价格全票
					name : 'fdfulllowtp',
					index : 'fdfulllowtp',
					width : 100,
					editable : true,
					hidden : true
				}, {//团队价格半票
					name : 'fdhalflowtp',
					index : 'fdhalflowtp',
					width : 100,
					editable : true,
					hidden : true
				}, {//团队价格儿童票
					name : 'fdchildlowtp',
					index : 'fdchildlowtp',
					width : 100,
					editable : true,
					hidden : true
				}, {//团队价格免票
					name : 'fdfreelowtp',
					index : 'fdfreelowtp',
					width : 100,
					editable : true,
					hidden : true
				}, {//接送费用
					name : 'fdtranscoststp',
					index : 'fdtranscoststp',
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
				}],

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

				editurl : "/entertainmentPrice/save.htm",
				shrinkToFit : true,
				autowidth : true
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
					var frameSrc = "/jsp/entertainmentPrice/add.jsp";
			        $("#addIframe").attr("src", frameSrc);
			        $('#addModal').modal({ show: true, backdrop: 'static' });
			        $("#addIframe").on("load", function(){
			        	$(this).contents().find("#reset").click();
			        	$(this).contents().find("#fsNo").val(fsNo);
			        	$(this).contents().find("#fsName").val(fsName);
			        });
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
