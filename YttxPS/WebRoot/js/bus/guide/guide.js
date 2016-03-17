//显示详情
var raw = {};
function editCustom(id) {
	raw = jQuery("#grid-table").jqGrid('getRowData', id);
	var frameSrc = "/jsp/guide/edit.jsp";
	$("#editIframe").attr("src", frameSrc);
	$('#editModal').modal({ show: true, backdrop: 'static' });
};

function picCustom(id) {
	raw = jQuery("#grid-table").jqGrid('getRowData', id);
	
	var resType = "dy";
	var resNo = raw.no;
	var resName = raw.name;
	var param="resType=" + resType + "&resNo=" + resNo + "&resName=" + resName;
	
	var frameSrc = "/pic/picpage.htm?" + param;
	$("#picIframe").attr("src", frameSrc);
	$('#picModal').modal({ show: true, backdrop: 'static' });
};

function showCustom(id) {
	raw = jQuery("#grid-table").jqGrid('getRowData', id);
	$("#showModal").modal({
		remote: "/jsp/guide/show.jsp"
	});
};

function deleteCustom(id) {
	raw = jQuery("#grid-table").jqGrid('getRowData', id);
	var frameSrc = "/jsp/guide/delete.jsp?no=" + raw.no;
	$("#delIframe").attr("src", frameSrc);
	$('#delModal').modal({ show: true, backdrop: 'static' });
};


$("#showModal").on("shown.bs.modal", function() {
	$(this).find("#reset").click();
	$(this).find("#no").val(raw.no);
	$(this).find("#name").val(raw.name);
	$(this).find("#gender").val(raw.gender);
	$(this).find("#idno").val(raw.idno);
	$(this).contents().find("#workdate").val(raw.workdate);
	$(this).find("#contactno").val(raw.contactno);
	$(this).find("#mainroute").val(raw.mainroute);
	$(this).find("#preferteem").val(raw.preferteem);
	$(this).find("#speciality").val(raw.speciality);
	$(this).find("#desc").val(raw.desc);
	$(this).find("#lvl").val(raw.lvl);
	$(this).find("#salary").val(raw.salary);
	$(this).find("#daysale").val(raw.daysale);
	$(this).find("#weeksale").val(raw.weeksale);
	$(this).find("#monthsale").val(raw.monthsale);
	$(this).find("#stat").val(raw.stat);
});

$("#editIframe").on("load",function(){
	$(this).contents().find("#reset").click();
	$(this).contents().find("#no").val(raw.no);
	$(this).contents().find("#name").val(raw.name);
	$(this).contents().find("#gender").val(raw.gender);
	$(this).contents().find("#workdate").val(raw.workdate);
	$(this).contents().find("#idno").val(raw.idno);
	$(this).contents().find("#contactno").val(raw.contactno);
	$(this).contents().find("#mainroute").val(raw.mainroute);
	$(this).contents().find("#preferteem").val(raw.preferteem);
	$(this).contents().find("#speciality").val(raw.speciality);
	$(this).contents().find("#desc").val(raw.desc);
	$(this).contents().find("#lvl").val(raw.lvl);
	$(this).contents().find("#salary").val(raw.salary);
	$(this).contents().find("#daysale").val(raw.daysale);
	$(this).contents().find("#weeksale").val(raw.weeksale);
	$(this).contents().find("#monthsale").val(raw.monthsale);
	$(this).contents().find("#stat").val(raw.stat);
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
	//	jqGrid form提交
	$("#submit").on("click", function() {
		$("#collapseOne").collapse('hide');
		var postData = $("#grid-table").jqGrid("getGridParam", "postData");
		postData["guide.no"] = $("#queryfield").find("#no").val();
		postData["guide.name"] = $("#queryfield").find("#name").val();

		postData["guide.gender"] = $("#queryfield").find("#gender").val();
		postData["guide.idno"] = $("#queryfield").find("#idno").val();
		postData["guide.workdate"] = $("#queryfield").find("#workdate").val();
		postData["guide.contactno"] = $("#queryfield").find("#contactno").val();
		postData["guide.mainroute"] = $("#queryfield").find("#mainroute").val();
		postData["guide.preferteem"] = $("#queryfield").find("#preferteem").val();
		postData["guide.speciality"] = $("#queryfield").find("#speciality").val();
		postData["guide.desc"] = $("#queryfield").find("#desc").val();
		postData["guide.lvl"] = $("#queryfield").find("#lvl").val();
		postData["guide.salary"] = $("#queryfield").find("#salary").val();
		postData["guide.daysale"] = $("#queryfield").find("#daysale").val();
		postData["guide.weeksale"] = $("#queryfield").find("#weeksale").val();
		postData["guide.monthsale"] = $("#queryfield").find("#monthsale").val();
		postData["guide.stat"] = $("#queryfield").find("#stat").val();
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
		return detail + editBtn +/* deleteBtn +*/ picDtn;
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
	var gender = {
			1 : '男',
			0 : '女'
	};
	var lvl = {
			'01' : '金牌',
			'02' : '银牌',
			'03' : '铜牌'
	};
	var preferTeem = {
			'01' : '纯玩团',
			'02' : '散客团',
			'03' : '购物团'
	};
	jQuery(grid_selector).jqGrid(
			{
				url : "/guide/findGuide.htm",
				datatype : "json",
				mtype : 'POST',
				height : 400,
				colNames : [ '操作', '导游编号', '姓名', '性别', '身份证号', '开始工作日期',
				             '联系方式', '主带线路', '带团意向','特长','导游证号','当前级别','工资','当日打单金额','本周打单金额','本月打单金额', '状态' ],
				             colModel : [ {
				            	 name : 'myac',
				            	 index : '',
				            	 width : 100,
				            	 fixed : true,
				            	 sortable : false,
				            	 resize : false,
				            	 formatter : actFormatter
				             }, {
				            	 name : 'no',
				            	 index : 'no',
				            	 width : 85,
				            	 sorttype : "int"
				             }, {
				            	 name : 'name',
				            	 index : 'name',
				            	 width : 80,
				            	 editable : true,
				            	 sorttype : "char"
				             }, {
				            	 name : 'gender',
				            	 index : 'gender',
				            	 width : 50,
				            	 editable : true,
				            	 sorttype : "char",
				            	 edittype : 'select',
				            	 editoptions : {
				            		 value : s
				            	 },
				            	 formatter : function(v, opt, rec) {
				            		 return gender[v];
				            	 },
				            	 unformat : function(v) {
				            		 for (k in gender)
				            			 if (gender[k] == v)
				            				 return k;
				            		 return '1';
				            	 }
				             }, { name : 'idno',
				            	 index : 'idno',
				            	 editable : true,
				            	 sorttype : "char"
				             }, { name : 'workdate',
				            	 index : 'workdate',
				            	 width : 90,
				            	 editable : true,
				            	 sorttype : "date",
									formatter : function(value){
										var timestamp = "";
										if(value!=null && value != ''){//rData[7]表示日期列
											timestamp = (new Date(parseFloat(value))).format("yyyy-MM-dd");
										}
										return timestamp;
									}
				             }, { name : 'contactno',
				            	 index : 'contactno',
				            	 editable : true,
				            	 width : 100,
				            	 sorttype : "char"
				             }, { name : 'mainroute',
				            	 index : 'mainroute',
				            	 width : 100,
				            	 editable : true,
				            	 sorttype : "char"
				             }, { name : 'preferteem',
				            	 index : 'preferteem',
				            	 editable : true,
				            	 width : 80,
				            	 sorttype : "char",
				            	 edittype : 'select',
				            	 editoptions : {
				            		 value : s
				            	 },
				            	 formatter : function(v, opt, rec) {
				            		 return preferTeem[v];
				            	 },
				            	 unformat : function(v) {
				            		 for (k in preferTeem)
				            			 if (preferTeem[k] == v)
				            				 return k;
				            		 return '1';
				            	 }
				             }, { name : 'speciality',
				            	 index : 'speciality',
				            	 editable : true,
				            	 width : 100,
				            	 sorttype : "char"
				             }, { name : 'desc',
				            	 index : 'desc',
				            	 editable : true,
				            	 sorttype : "char"
				             }, { name : 'lvl',
				            	 index : 'lvl',
				            	 editable : true,
				            	 width : 70,
				            	 sorttype : "char",
				            	 edittype : 'select',
				            	 editoptions : {
				            		 value : s
				            	 },
				            	 formatter : function(v, opt, rec) {
				            		 return lvl[v];
				            	 },
				            	 unformat : function(v) {
				            		 for (k in lvl)
				            			 if (lvl[k] == v)
				            				 return k;
				            		 return '1';
				            	 }
				             }, { name : 'salary',
				            	 index : 'salary',
				            	 editable : true,
				            	 sorttype : "char",
				            	 hidden : true
				             }, {
				            	 name : 'daysale',
				            	 index : 'daysale',
				            	 width : 300,
				            	 editable : true,
				            	 hidden : true
				             }, {
				            	 name : 'weeksale',
				            	 index : 'weeksale',
				            	 edittype : 'textarea',
				            	 editable : true,
				            	 hidden : true,
				            	 hidden : true
				             }, {
				            	 name : 'monthsale',
				            	 index : 'monthsale',
				            	 width : 70,
				            	 editable : true,
				            	 hidden : true
				             }, {
				            	 name : 'stat',
				            	 index : 'stat',
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

				             editurl : "/guide/save.htm",
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
					var frameSrc = "/jsp/guide/add.jsp";
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
