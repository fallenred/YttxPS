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

function picEntertainmentPrice(id) {
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
	
	if (raw.fdtranscoststp != '') {
		$(this).find("#priceType").val('3');
		$(this).find(".low").hide();
		$(this).find(".trans").show();
	} else {
		if (raw.fdfulllowqp == '') {
			$(this).find("#priceType").val('2');
			$(this).find(".peak").show();
			$(this).find(".low").hide();
		}
	}
	
	$(this).find("#fdfulllowqp").val(raw.fdfulllowqp);   //淡季挂牌价格全票
	$(this).find("#fdhalflowqp").val(raw.fdhalflowqp);   //淡季挂牌价格半票
	$(this).find("#fdchildlowqp").val(raw.fdchildlowqp);   //淡季挂牌价格儿童票
	$(this).find("#fdfreelowqp").val(raw.fdfreelowqp);   //淡季挂牌价格免票
	$(this).find("#fdfulllowtp").val(raw.fdfulllowtp);   //淡季团队价格全票
	$(this).find("#fdhalflowtp").val(raw.fdhalflowtp);   //淡季团队价格半票
	$(this).find("#fdchildlowtp").val(raw.fdchildlowtp);   //淡季团队价格儿童票
	$(this).find("#fdfreelowtp").val(raw.fdfreelowtp);   //淡季团队价格免票
	$(this).find("#fdfullpeakqp").val(raw.fdfullpeakqp);   //旺季挂牌价格全票
	$(this).find("#fdhalfpeakqp").val(raw.fdhalfpeakqp);   //旺季挂牌价格半票
	$(this).find("#fdchildpeakqp").val(raw.fdchildpeakqp);   //旺季挂牌价格儿童票
	$(this).find("#fdfreepeakqp").val(raw.fdfreepeakqp);   //旺季挂牌价格免票
	$(this).find("#fdfullpeaktp").val(raw.fdfullpeaktp);   //旺季团队价格全票
	$(this).find("#fdhalfpeaktp").val(raw.fdhalfpeaktp);   //旺季团队价格半票
	$(this).find("#fdchildpeaktp").val(raw.fdchildpeaktp);   //旺季团队价格儿童票
	$(this).find("#fdfreepeaktp").val(raw.fdfreepeaktp);   //旺季团队价格免票
	$(this).find("#fdtranscoststp").val(raw.fdtranscoststp);   //接送费用
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
	
	if (raw.fdtranscoststp != '') {   //接送费用
		$(this).contents().find("#priceType").val('3');
		$(this).contents().find("#priceTypeName").val('接送费用');
		$(this).contents().find(".low").hide();
		$(this).contents().find(".trans").show();
	} else {
		if (raw.fdfulllowqp == '') {   //旺季
			$(this).contents().find("#priceType").val('2');
			$(this).contents().find("#priceTypeName").val('旺季');
			$(this).contents().find(".peak").show();
			$(this).contents().find(".low").hide();
		} else {
			$(this).contents().find("#priceTypeName").val('淡季');
		}
	}
	
	$(this).contents().find("#fdfulllowqp").val(raw.fdfulllowqp);   //淡季挂牌价格全票
	$(this).contents().find("#fdhalflowqp").val(raw.fdhalflowqp);   //淡季挂牌价格半票
	$(this).contents().find("#fdchildlowqp").val(raw.fdchildlowqp);   //淡季挂牌价格儿童票
	$(this).contents().find("#fdfreelowqp").val(raw.fdfreelowqp);   //淡季挂牌价格免票
	$(this).contents().find("#fdfulllowtp").val(raw.fdfulllowtp);   //淡季团队价格全票
	$(this).contents().find("#fdhalflowtp").val(raw.fdhalflowtp);   //淡季团队价格半票
	$(this).contents().find("#fdchildlowtp").val(raw.fdchildlowtp);   //淡季团队价格儿童票
	$(this).contents().find("#fdfreelowtp").val(raw.fdfreelowtp);   //淡季团队价格免票
	$(this).contents().find("#fdfullpeakqp").val(raw.fdfullpeakqp);   //旺季挂牌价格全票
	$(this).contents().find("#fdhalfpeakqp").val(raw.fdhalfpeakqp);   //旺季挂牌价格半票
	$(this).contents().find("#fdchildpeakqp").val(raw.fdchildpeakqp);   //旺季挂牌价格儿童票
	$(this).contents().find("#fdfreepeakqp").val(raw.fdfreepeakqp);   //旺季挂牌价格免票
	$(this).contents().find("#fdfullpeaktp").val(raw.fdfullpeaktp);   //旺季团队价格全票
	$(this).contents().find("#fdhalfpeaktp").val(raw.fdhalfpeaktp);   //旺季团队价格半票
	$(this).contents().find("#fdchildpeaktp").val(raw.fdchildpeaktp);   //旺季团队价格儿童票
	$(this).contents().find("#fdfreepeaktp").val(raw.fdfreepeaktp);   //旺季团队价格免票
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
	
//	jqGrid form提交
	$("#submit").click(function() {
		$("#collapseOne").collapse('hide');
		var postData = $("#grid-table").jqGrid("getGridParam", "postData");
		postData["entertainmentPrice.fsNo"] = $("#queryfield").find("#fsNo").val();
		postData["entertainmentPrice.fsScenicno"] = $("#queryfield").find("#fsScenicno").val();
		postData["entertainmentPrice.fsName"] = $("#queryfield").find("#fsName").val();
		postData["entertainmentPrice.fsType"] = $("#queryfield").find("#fsType").val();
		postData["entertainmentPrice.fiStat"] = $("#queryfield").find("#fiStat").val();
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
	var picDtn = '<div title="" class="ui-pg-div ui-inline-edit" id="picButton" style="display: block; cursor: pointer; float: left;" onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" onclick="picEntertainmentPrice('
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
			2 : '旺季价格',
			3 : '接送费用'
		};
	var s = '';
	for (k in items)
		s += ';' + k + ":" + items[k];
	s = s.substring(1);
	jQuery(grid_selector).jqGrid(
			{
				url : "/entertainment/findEntertainmentPrice.htm",
				datatype : "json",
				mtype : 'POST',
				height : 400,
				colNames : [ '操作', '娱乐项目代码', '娱乐项目名称', '价格类型', '淡季挂牌价格全票', '淡季挂牌价格半票', '淡季挂牌价格儿童票', 
				             '淡季挂牌价格免票', '淡季团队价格全票', '淡季团队价格半票', '淡季团队价格儿童票', '淡季团队价格免票', '旺季挂牌价格全票',
				             '旺季挂牌价格半票', '旺季挂牌价格儿童票', '旺季挂牌价格免票', '旺季团队价格全票', '旺季团队价格半票', '旺季团队价格儿童票',
				             '旺季团队价格免票', '接送费用', '开始日期', '结束日期' ],
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
					name : 'fdfulllowqp',
					index : 'fdfulllowqp',
					width : 35,
					sortable : true,
					editable : true,
					edittype : 'select',
					editoptions : {
						value : s
					},
					formatter : function(v, opt, rec) {
						//旺季价格
						if (rec.fdtranscoststp != null) {
							return items['3'];
						}
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
					name : 'fdfulllowqp',
					index : 'fdfulllowqp',
					width : 100,
					editable : true,
					hidden : true
				}, {
					name : 'fdhalflowqp',
					index : 'fdhalflowqp',
					width : 100,
					editable : true,
					hidden : true
				}, {
					name : 'fdchildlowqp',
					index : 'fdchildlowqp',
					width : 100,
					editable : true,
					hidden : true
				}, {
					name : 'fdfreelowqp',
					index : 'fdfreelowqp',
					width : 100,
					editable : true,
					hidden : true
				}, {
					name : 'fdfulllowtp',
					index : 'fdfulllowtp',
					width : 100,
					editable : true,
					hidden : true
				}, {
					name : 'fdhalflowtp',
					index : 'fdhalflowtp',
					width : 100,
					editable : true,
					hidden : true
				}, {
					name : 'fdchildlowtp',
					index : 'fdchildlowtp',
					width : 100,
					editable : true,
					hidden : true
				}, {
					name : 'fdfreelowtp',
					index : 'fdfreelowtp',
					width : 100,
					editable : true,
					hidden : true
				}, {
					name : 'fdfullpeakqp',
					index : 'fdfullpeakqp',
					width : 100,
					editable : true,
					hidden : true
				}, {
					name : 'fdhalfpeakqp',
					index : 'fdhalfpeakqp',
					width : 100,
					editable : true,
					hidden : true
				}, {
					name : 'fdchildpeakqp',
					index : 'fdchildpeakqp',
					width : 100,
					editable : true,
					hidden : true
				}, {
					name : 'fdfreepeakqp',
					index : 'fdfreepeakqp',
					width : 100,
					editable : true,
					hidden : true
				}, {
					name : 'fdfullpeaktp',
					index : 'fdfullpeaktp',
					width : 100,
					editable : true,
					hidden : true
				}, {
					name : 'fdhalfpeaktp',
					index : 'fdhalfpeaktp',
					width : 100,
					editable : true,
					hidden : true
				}, {
					name : 'fdchildpeaktp',
					index : 'fdchildpeaktp',
					width : 100,
					editable : true,
					hidden : true
				}, {
					name : 'fdfreepeaktp',
					index : 'fdfreepeaktp',
					width : 100,
					editable : true,
					hidden : true
				}, {
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
