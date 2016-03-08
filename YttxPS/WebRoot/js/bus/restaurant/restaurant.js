/**
 * 表格行事件 -->餐厅消费选项配置
 */
function showRestaurantCost(id){
	var raw = jQuery("#grid-table").jqGrid('getRowData', id);
	window.open("/restaurant/costPage.htm?no=" + raw.no);  
};

/**
 * 表格行事件 -->查看餐厅详细信息
 */
function showRestaurant(id) {
	var raw = jQuery("#grid-table").jqGrid('getRowData', id);
	var frameSrc = "/restaurant/show.htm?no=" + raw.no
    $("#showIframe").attr("src", frameSrc);
    $('#showModal').modal({ show: true, backdrop: 'static' });
};

/**
 * 弹出框-->"餐厅信息"弹出框-->隐藏的时候removeData
 */
$("#showModal").on("hidden.bs.modal", function() {
    $(this).removeData("bs.modal");
});


/**
 * 表格行事件 -->修改餐厅信息-->打开弹出框
 */
function eidtRestaurant(id) {
	var raw = jQuery("#grid-table").jqGrid('getRowData', id);
	var frameSrc = "/restaurant/editPage.htm?no=" + raw.no
    $("#editIframe").attr("src", frameSrc);
    $('#editModal').modal({ show: true, backdrop: 'static' });
};

/**
 * 弹出框-->"修改餐厅信息"弹出框-->隐藏的时候重新加载餐厅列表
 */
$("#editModal", parent.document).on("hidden.bs.modal", function() {
	$(this).removeData("bs.modal");
	$("#grid-table").trigger("reloadGrid");
});


/**
 * 表格行事件 -->删除餐厅信息-->打开删除警告弹出框
 */
function deleteRestaurant(id) {
	var raw = jQuery("#grid-table").jqGrid('getRowData', id);
	var frameSrc = "/jsp/restaurant/delete.jsp?no=" + raw.no;
	$("#delIframe").attr("src", frameSrc);
    $('#delModal').modal({ show: true, backdrop: 'static' });
};

/**
 * 弹出框-->"删除警告"弹出框-->隐藏的时候重新加载餐厅列表
 */
$("#delModal", parent.document).on("hidden.bs.modal", function() {
	$(this).removeData("bs.modal");
	$("#grid-table").trigger("reloadGrid");
});


/**
 * 弹出框-->"新增餐厅"弹出框-->隐藏的时候重新加载餐厅列表
 */
$("#addModal", parent.document).on("hidden.bs.modal", function() {
	$(this).removeData("bs.modal");
	$("#grid-table").trigger("reloadGrid");
});

/**
 * 表格行事件 -->上传图片-->打开上传图片弹出框
 */
function picRestaurant(id) {
	var raw = jQuery("#grid-table").jqGrid('getRowData', id);
	var resType="ct";
	var resNo=raw.no;
	var resName=raw.name;
	var param="resType="+resType+"&resNo="+resNo+"&resName="+resName;
	var frameSrc = "/pic/picpage.htm?"+param;
    $("#picIframe").attr("src", frameSrc);
    $('#picModal').modal({ show: true, backdrop: 'static' });
};

jQuery(function($) {
	/*
	 * 查询条件-->行政区划初始化
	 */
	var localsel = $("#selectCity").localCity({
		provurl : "/pub/findcity.htm",
		cityurl : "/pub/findcity.htm",
		disturl : "/pub/findcity.htm",
		callback : localcallback
	});

	/*
	 * 查询条件-->行政区划选择器回调函数
	 */
	function localcallback(index, key, value, fullkey, fullname) {
		$("#regionname").val(fullname);
		$("#regionno").val(key);
		if (index == 3) {
			$("#selectCity").hide();
		}
	}
	
	/*
	 * 查询条件-->行政区划触发选择器
	 */
	$("#regionname").click(function() {
		$("#selectCity").show();
	})

	/*
	 * 查询条件-->按钮-->"重置"按钮
	 */ 
	$("#reset").click(function() {
		var $form =$("#queryfield");
		$form.find("input[type='text']").each(function(){//首先清空input域
			$(this).val(null);
		})
		$form.find("select").each(function(){//设置select
			$(this).find("option[value='']").prop("checked",true);
		})
		$("#regionno").val(null);//清空行政区划的值
		$("#selectCity").hide();//隐藏行政区划选择器
	});

	/*
	 * 查询条件-->按钮-->"提交"按钮
	 */
	$("#queryfield_submit").on("click",
		function() {
			$("#collapseOne").collapse('hide');
			var postData = $("#grid-table").jqGrid("getGridParam","postData");
			//TODO  进行数据验证
			//餐厅名称
			postData["restaurant.name"] = $("#name").val();
			//行政区划
			postData["restaurant.regionno"] = $("#regionno").val();
			//接待规模
			postData["restaurant.minScale"] = $("#scale_min").val();
			postData["restaurant.maxScale"] = $("#scale_max").val();
			//菜品特色
			postData["restaurant.special"] = $("#special").val();
			//等级
			postData["restaurant.lvl"] = $("#lvl").val();
			//状态
			postData["restaurant.stat"] = $("#stat").val();
			console.log("查询参数：");
			console.log(postData);
			$("#grid-table").jqGrid("setGridParam", {
				datatype : 'json',
				postData : postData
			}).trigger("reloadGrid");
		});
	

	/*
	 * 定义grid的选择器
	 */
	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";

	/*
	 * 定义按钮列
	 */
	var actFormatter = function(cellvalue, options, rawObject) {
		var costBtn = '<div title="" class="ui-pg-div ui-inline-edit" '+
							'id="roomButton" style="display: block; cursor: pointer; float: left;" '+
							'onmouseover="jQuery(this).addClass(\'ui-state-hover\');" '+
							'onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" '+
							'onclick="showRestaurantCost('+ options.rowId+ ');" data-original-title="餐厅消费选项配置">'+
							'<span class="ui-icon ace-icon fa fa-cog red"></span></div>';

		var detailBtn = '<div title="" class="ui-pg-div ui-inline-edit" '+
							'id="roomButton" style="display: block; cursor: pointer; float: left;"'+
							'onmouseover="jQuery(this).addClass(\'ui-state-hover\');"'+
							'onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" '+
							'onclick="showRestaurant('+ options.rowId+ ');" data-original-title="查看餐厅信息">'+
							'<span class="ui-icon ace-icon fa fa-search-plus grey"></span></div>';
		
		
		var editBtn = '<div title="" class="ui-pg-div ui-inline-edit" '+
						'id="editButton" style="display: block; cursor: pointer; float: left;" '+
						'onmouseover="jQuery(this).addClass(\'ui-state-hover\');" '+
						'onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" '+
						'onclick="eidtRestaurant('+ options.rowId+ ');" data-original-title="修改餐厅信息">'+
						'<span class="ui-icon ui-icon-pencil"></span></div>';
		
		var picDtn = '<div title="" class="ui-pg-div ui-inline-edit" '+
						'id="picButton" style="display: block; cursor: pointer; float: left;" '+
						'onmouseover="jQuery(this).addClass(\'ui-state-hover\');" '+
						'onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" '+
						'onclick="picRestaurant('+ options.rowId+ ');" data-original-title="编辑资源图片">'+
						'<span class="ui-icon ace-icon fa fa-file-image-o green"></span></div>';

		
		var deleteBtn = '<div title="" class="ui-pg-div ui-inline-edit" '+
						'id="deleteButton" style="display: block; cursor: pointer; float: left;" '+
						'onmouseover="jQuery(this).addClass(\'ui-state-hover\');" '+
						'onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" '+
						'onclick="deleteRestaurant('+ options.rowId+ ');" data-original-title="删除该餐厅">'
						+'<span class="ui-icon ace-icon fa fa-trash-o red"></span></div>';
		return costBtn + detailBtn + editBtn + picDtn+deleteBtn;
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
	
	function contactStr(object){
		var str = '';
		for (k in object)
			str += ';' + k + ":" + object[k];
		return  str.substring(1);
	}
	//TODO 读取菜品特色的数据字典
	//菜品特色
	var special_items = {
		'01' : '小吃',
		'02' : '藏餐',
		'03' : '火锅',
		'04' : '中餐',
		'05' : '其他'
	}
	var special_val = contactStr(special_items);
	

	//TODO 读取餐厅等级的数据字典
	//餐厅等级
	var lvl_items = {
		'01' : 'lvl1',
		'02' : 'lvl2',
		'03' : 'lvl3',
	};
	var lvl_val = contactStr(lvl_items);
	
	//TODO 读取状态数据字典
	// 状态
	var stat_items = {
		1 : '正常',
		2 : '失效'
	};
	//TODO 读取所属地区的数据字典
	var stat_val = contactStr(stat_items)
	
	$(grid_selector).jqGrid(
			{
				url : "/restaurant/list.htm",
				datatype : "json",
				mtype : 'POST',
				height : 400,
				colNames : [ '操作', '餐厅编号','餐厅名称', '所属地区', 
				             '餐厅地址', '接待规模(桌)', '菜品特色',/*'等级',*/ '状态' ],
				colModel : [ {
					name : 'myaction',
					index : '',
					width : 120,
					fixed : true,
					sortable : false,
					resize : false,
					formatter : actFormatter
				}, {
					name : 'no',
					index : 'no',
					width : 100,
					sorttype : "char"
				},
				{
					name : 'name',
					index : 'name',
					width : 180,
					sortable : false,
					editable : false,
				},
				{
					name : 'regionname',
					index : 'regionname',
					width : 150,
					sortable : false,
					editable : false,
				}, {
					name : 'addr',
					index : 'addr',
					width : 200,
					sortable : false,
					editable : false,
				}, {
					name : 'scale',
					index : 'scale',
					width : 80,
					editable : false,
					sortable : "int",
				}, 
				{
					name : 'special',
					index : 'special',
					width : 80,
					sortable : false,
					editable : false,
					edittype : 'select',
					editoptions : {
						value : special_val
					},
					formatter : function(v, opt, rec) {
						return special_items[v];
					},
					unformat : function(v) {
						for (k in special_items)
							if (special_items[k] == v)
								return k;
						return v;
					}
				} , /*
				{
					name : 'lvl',
					index : 'lvl',
					width : 80,
					sortable : false,
					editable : false,
					edittype : 'select',
					editoptions : {
						value : lvl_val
					},
					formatter : function(v, opt, rec) {
						return lvl_items[v];
					},
					unformat : function(v) {
						for (k in lvl_items)
							if (lvl_items[k] == v)
								return k;
						return v;
					}
				},*/
				{
					name : 'stat',
					index : 'stat',
					width : 40,
					sortable : false,
					editable : false,
					edittype : 'select',
					editoptions : {
						value : stat_val
					},
					formatter : function(v, opt, rec) {
						return stat_items[v];
					},
					unformat : function(v) {
						for (k in stat_items)
							if (stat_items[k] == v)
								return k;
						return v ;
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
						updatePagerIcons(table);
						enableTooltips(table);
					}, 0);
				},
				shrinkToFit : true,
				autowidth : true
			});
	$(window).triggerHandler('resize.jqGrid');// trigger window resize to make

	$(grid_selector).jqGrid('navGrid', pager_selector, { // navbar options
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
	});

	$(grid_selector).jqGrid('navGrid', pager_selector).jqGrid('navButtonAdd',
			pager_selector, {
				caption : "",
				buttonicon : "ace-icon fa fa-plus-circle purple",
				onClickButton : function() {
					var frameSrc = "/restaurant/addPage.htm";
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
