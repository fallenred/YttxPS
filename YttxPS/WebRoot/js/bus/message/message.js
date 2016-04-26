//显示详情
var raw = {};

function showCustom(id) {
	raw = jQuery("#grid-table").jqGrid('getRowData', id);
	var frameSrc = "/message/show.htm?id=" + raw.id;
	$("#showIframe").attr("src", frameSrc);
	$('#showModal').modal({ show: true, backdrop: 'static' });
};

function deleteCustom(id) {
	raw = jQuery("#grid-table").jqGrid('getRowData', id);
	var frameSrc = "/jsp/message/delete.jsp?id=" + raw.id;
	$("#delIframe").attr("src", frameSrc);
	$('#delModal').modal({ show: true, backdrop: 'static' });
};

$("#showModalLabel").on("load",function(){
	$(this).html(msgTitle);
});

$("#showIframe").on("load", function() {
	$(this).contents().find("#msgText").html(raw.msgText);
});

$("#delModal").on("hidden.bs.modal", function() {
	$(this).removeData("bs.modal");
	$("#grid-table").trigger("reloadGrid");
});

$("#showModal").on("hidden.bs.modal", function() {
	$(this).removeData("bs.modal");
	$("#grid-table").trigger("reloadGrid");
});

jQuery(function($) {
	//	jqGrid form提交
	$("#submit").on("click", function() {
		$("#collapseOne").collapse('hide');
		var postData = $("#grid-table").jqGrid("getGridParam", "postData");
		postData["message.id"] = $("#queryfield").find("#id").val();
		postData["message.sendType"] = $("#queryfield").find("#sendType").val();
		postData["message.msgText"] = $("#queryfield").find("#msgText").val();
		postData["message.sendId"] = $("#queryfield").find("#sendId").val();
		postData["message.recvId"] = $("#queryfield").find("#recvId").val();
		postData["message.subId"] = $("#queryfield").find("#subId").val();
		postData["message.msgDate"] = $("#queryfield").find("#msgDate").val();
		postData["message.msgStat"] = $("#queryfield").find("#msgStat").val();
		$("#grid-table").jqGrid("setGridParam", {
			datatype : 'json',
			postData : postData
		}).trigger("reloadGrid");
	});

	//	重置
	$("#reset", "#queryfield").click(function() {
		$("#regionno", "#queryfield").val(null);
	});

	//	jqgrid
	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";

	// 定义按钮列
	actFormatter = function(cellvalue, options, rawObject) {
		var detail = '<div title="" class="ui-pg-div ui-inline-edit" id="detailButton" style="display: block; cursor: pointer; float: left;" onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" onclick="showCustom('
			+ options.rowId
			+ ');" data-original-title="查看消息"><span class="ui-icon ace-icon fa fa-search-plus grey"></span></div>';

		var deleteBtn = '<div title="" class="ui-pg-div ui-inline-edit" id="deleteButton" style="display: block; cursor: pointer; float: left;" onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" onclick="deleteCustom('
			+ options.rowId
			+ ');" data-original-title="删除消息"><span class="ui-icon ace-icon fa fa-trash-o red"></span></div>';
		return detail + deleteBtn;
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
		0 : '未读',
		1 : '已读'
	};
	var s = '';
	for (k in items)
		s += ';' + k + ":" + items[k];
	s = s.substring(1);

	jQuery(grid_selector).jqGrid(
			{
				url : "/message/findMsg.htm",
				datatype : "json",
				mtype : 'POST',
				height : 400,
				colNames : [ '操作','消息编号','消息发送时间','消息标题','消息内容','消息发送者ID','消息接受者ID', '消息状态' ],
				             colModel : [ {
				            	 name : 'myac',
				            	 index : '',
				            	 width : 70,
				            	 fixed : true,
				            	 sortable : false,
				            	 resize : false,
				            	 formatter : actFormatter
				             }, {
				            	 name : 'id',
				            	 index : 'id',
				            	 editable : true,
				            	 sorttype : "char",
				            	 hidden : true
				            	 
				             }, { name : 'msgDate',
				            	 index : 'msgDate',
				            	 width : 60,
				            	 editable : true,
				            	 sorttype : "date",
									formatter : function(value){
										var timestamp = "";
										if(value!=null && value != ''){//rData[7]表示日期列
											timestamp = (new Date(parseFloat(value))).format("yyyy-MM-dd hh:mm:ss");
										}
										return timestamp;
									}
				             }, {
				            	 name : 'msgHead',
				            	 index : 'msgHead',
				            	 width : 100,
				            	 editable : true,
				            	 sorttype : "char"
				             }, {
				            	 name : 'msgText',
				            	 index : 'msgText',
				            	 width : 150,
				            	 editable : true,
				            	 sorttype : "char"
				             },  { name : 'sendid',
				            	 index : 'sendid',
				            	 editable : true,
				            	 width : 60,
				            	 sorttype : "char"
				             }, { name : 'recvid',
				            	 index : 'recvid',
				            	 width : 60,
				            	 editable : true,
				            	 sorttype : "char"
				             },  {
				            	 name : 'msgStat',
				            	 index : 'msgStat',
				            	 width : 45,
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
				             } ],
				             /**
				              * modify end
				              */

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

				             editurl : "/message/save.htm",
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
				del : false,
				delicon : 'ace-icon fa fa-trash-o red',
				search : false,
				searchicon : 'ace-icon fa fa-search orange',
				refresh : false,
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
