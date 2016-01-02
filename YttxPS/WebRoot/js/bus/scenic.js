
//	定义按钮列
actFormatter = function(cellvalue, options, rawObject) {
	var detail = '<div title="" class="ui-pg-div ui-inline-edit" id="detailButton_1" style="display: block; cursor: pointer; float: left;" onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" onclick="showCustom(options.rowId,rawObject);" data-original-title="查看记录详情"><span class="ui-icon ace-icon fa fa-search-plus grey"></span></div>';

	var editBtn = '<div title="" class="ui-pg-div ui-inline-edit" id="editButton_1" style="display: block; cursor: pointer; float: left;" onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" onclick="editCustom(options.rowId,rawObject);" data-original-title="编辑本条记录"><span class="ui-icon ui-icon-pencil"></span></div>';

	var deleteBtn = '<div title="" class="ui-pg-div ui-inline-edit" id="deleteButton_1" style="display: block; cursor: pointer; float: left;" onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" onclick="deleteCustom(options.rowId,rawObject);" data-original-title="删除本条记录"><span class="ui-icon ace-icon fa fa-trash-o red"></span></div>';

	return detail + editBtn + deleteBtn;
};

function localcallback(index, key, value, fullkey, fullname) {
	$("#regionName").val(fullname);
	$("#region").val(key);
	if(index == 3)
		$("#selectCity").hide();
}
var localsel = $('.localcity').localCity({
	
	provurl : "/pub/findcity.htm", 
	cityurl : "/pub/findcity.htm",
	disturl : "/pub/findcity.htm",
	callback : localcallback
});
$("#regionName").click (function(){
	$("#selectCity").show();
})

$("#submit").click(function(){
	$("#collapseOne").collapse('hide');
	$("#collapseTwo").collapse('show');
	var postData = $("#grid-table").jqGrid("getGridParam", "postData");
	postData["no"] = $("#queryfield").find("#no").val();
	postData["name"] = $("#queryfield").find("#name").val();
	postData["region"] = $("#queryfield").find("#region").val();
	postData["lvl"] = $("#queryfield").find("#lvl").val();
	postData["stat"] = $("#queryfield").find("#stat").val();
	$("#grid-table").jqGrid("setGridParam", { postData: postData }).trigger("reloadGrid");
})

jQuery(function($) {
	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";

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

	jQuery(grid_selector).jqGrid({
		url:"/scenic/findScenic.htm",
		datatype: "json",
//		mtype: 'POST',
		height : 300,
		colNames : [ '操作', '景点代码', '景点名称', '景点地址', '景点级别', '开放时间', '状态' ],
		colModel : [ {
			name : 'myac',
			index : '',
			width : 80,
			fixed : true,
			sortable : false,
			resize : false,
			formatter : actFormatter,
//			formatoptions : {
//				keys : true,
//				// delbutton: false,//disable delete button
//
//				delOptions : {
//					recreateForm : true,
//					beforeShowForm : beforeDeleteCallback
//				},
////			 editformbutton:true, editOptions:{recreateForm: true,
////			 beforeShowForm:beforeEditCallback}
//			}
		}, {
			name : 'no',
			index : 'no',
			width : 60,
			sorttype : "int"
		}, {
			name : 'name',
			index : 'name',
			width : 100,
			editable : true,
			sorttype : "char",
		}, {
			name : 'addr',
			index : 'addr',
			width : 150,
			editable : true,
		}, {
			name : 'lvl',
			index : 'lvl',
			width : 30,
			editable : true,
		}, {
			name : 'openTime',
			index : 'openTime',
			width : 100,
			editable : true,
		}, {
			name : 'stat',
			index : 'stat',
			width : 30,
			sortable : true,
			editable : true,
		} ],

		viewrecords : true,
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : pager_selector,
		altRows : true,
		// toppager: true,

		multiselect : true,
		// multikey: "ctrlKey",
		multiboxonly : true,
		multipleSearch:true,

		loadComplete : function() {
			var table = this;
			setTimeout(function() {
				styleCheckbox(table);

				updateActionIcons(table);
				updatePagerIcons(table);
				enableTooltips(table);
			}, 0);
		},

		editurl : "/scenic/save.htm",// nothing is saved
		caption : "jqGrid with inline editing"

	// ,autowidth: true,

	/**
	 * , grouping:true, groupingView : { groupField : ['name'], groupDataSorted :
	 * true, plusicon : 'fa fa-chevron-down bigger-110', minusicon : 'fa
	 * fa-chevron-up bigger-110' }, caption: "Grouping"
	 */

	});
	$(window).triggerHandler('resize.jqGrid');// trigger window resize to make
												// the grid get the correct size

	// enable search/filter toolbar
	// jQuery(grid_selector).jqGrid('filterToolbar',{defaultSearch:true,stringResult:true})
	// jQuery(grid_selector).filterToolbar({});

	// switch element when editing inline
	function aceSwitch(cellvalue, options, cell) {
		setTimeout(function() {
			$(cell).find('input[type=checkbox]').addClass(
					'ace ace-switch ace-switch-5').after(
					'<span class="lbl"></span>');
		}, 0);
	}
	// enable datepicker
	function pickDate(cellvalue, options, cell) {
		setTimeout(function() {
			$(cell).find('input[type=text]').datepicker({
				format : 'yyyy-mm-dd',
				autoclose : true
			});
		}, 0);
	}

	// navButtons
	jQuery(grid_selector).jqGrid(
			'navGrid',
			pager_selector,
			{ // navbar options
//				edit : true,
//				editicon : 'ace-icon fa fa-pencil blue',
				add : true,
				addicon : 'ace-icon fa fa-plus-circle purple',
				del : true,
				delicon : 'ace-icon fa fa-trash-o red',
				search : true,
				searchicon : 'ace-icon fa fa-search orange',
				refresh : true,
				refreshicon : 'ace-icon fa fa-refresh green',
//				view : true,
//				viewicon : 'ace-icon fa fa-search-plus grey',
			},
//			{
//				// edit record form
//				closeAfterEdit : true,
//				width : 700,
//				recreateForm : true,
//				beforeShowForm : function(e) {
//					var form = $(e[0]);
//					form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar')
//							.wrapInner('<div class="widget-header" />')
//					style_edit_form(form);
//				}
//			},
			{
				// new record form
				// width: 700,
				closeAfterAdd : true,
				recreateForm : true,
				viewPagerButtons : false,
				beforeShowForm : function(e) {
					var form = $(e[0]);
					form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar')
							.wrapInner('<div class="widget-header" />')
					style_edit_form(form);
				}
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
					style_search_form(form);
				},
				afterRedraw : function() {
					style_search_filters($(this));
				},
				multipleSearch : true,
			/**
			 * multipleGroup:true, showQuery: true
			 */
			}
//			{
//				// view record form
//				recreateForm : true,
//				beforeShowForm : function(e) {
//					var form = $(e[0]);
//					form.closest('.ui-jqdialog').find('.ui-jqdialog-title')
//							.wrap('<div class="widget-header" />')
//				}
//			}
			)



	function style_edit_form(form) {
		// enable datepicker on "sdate" field and switches for "stock" field
		form.find('input[name=sdate]').datepicker({
			format : 'yyyy-mm-dd',
			autoclose : true
		}).end().find('input[name=stock]').addClass(
				'ace ace-switch ace-switch-5').after(
				'<span class="lbl"></span>');
		// don't wrap inside a label element, the checkbox value won't be
		// submitted (POST'ed)
		// .addClass('ace ace-switch ace-switch-5').wrap('<label class="inline"
		// />').after('<span class="lbl"></span>');

		// update buttons classes
		var buttons = form.next().find('.EditButton .fm-button');
		buttons.addClass('btn btn-sm').find('[class*="-icon"]').hide();// ui-icon,
																		// s-icon
		buttons.eq(0).addClass('btn-primary').prepend(
				'<i class="ace-icon fa fa-check"></i>');
		buttons.eq(1).prepend('<i class="ace-icon fa fa-times"></i>')

		buttons = form.next().find('.navButton a');
		buttons.find('.ui-icon').hide();
		buttons.eq(0).append('<i class="ace-icon fa fa-chevron-left"></i>');
		buttons.eq(1).append('<i class="ace-icon fa fa-chevron-right"></i>');
	}

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
	function style_search_form(form) {
		var dialog = form.closest('.ui-jqdialog');
		var buttons = dialog.find('.EditTable')
		buttons.find('.EditButton a[id*="_reset"]').addClass(
				'btn btn-sm btn-info').find('.ui-icon').attr('class',
				'ace-icon fa fa-retweet');
		buttons.find('.EditButton a[id*="_query"]').addClass(
				'btn btn-sm btn-inverse').find('.ui-icon').attr('class',
				'ace-icon fa fa-comment-o');
		buttons.find('.EditButton a[id*="_search"]').addClass(
				'btn btn-sm btn-purple').find('.ui-icon').attr('class',
				'ace-icon fa fa-search');
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

	function beforeEditCallback(e) {
		var form = $(e[0]);
		form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner(
				'<div class="widget-header" />')
		style_edit_form(form);
	}

	// it causes some flicker when reloading or navigating grid
	// it may be possible to have some custom formatter to do this as the grid
	// is being created to prevent this
	// or go back to default browser checkbox styles for the grid
	function styleCheckbox(table) {
		/**
		 * $(table).find('input:checkbox').addClass('ace') .wrap('<label />')
		 * .after('<span class="lbl align-top" />')
		 * 
		 * 
		 * $('.ui-jqgrid-labels th[id*="_cb"]:first-child')
		 * .find('input.cbox[type=checkbox]').addClass('ace') .wrap('<label
		 * />').after('<span class="lbl align-top" />');
		 */
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

	//var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');

	$(document).on('ajaxloadstart', function(e) {
		$(grid_selector).jqGrid('GridUnload');
		$('.ui-jqdialog').remove();
	});
});
