/**
 * 表格行事件 -->查看会员详细信息
 */
function showCustomer(id){
	var frameSrc = "/member/admin/show.htm?id=" +id;
    $("#showIframe").attr("src", frameSrc);
    $('#showModal').modal({ show: true, backdrop: 'static' });
};

$("#showModal").on("hidden.bs.modal", function() {
    $(this).removeData("bs.modal");
});


/**
 * 表格行事件 -->禁用会员
 */
function stopCustomer(id) {
	tip(id,"S");//id--会员id，S--禁止
}

/**
 * 表格行事件 -->启用会员
 */
function activeCustomer(id) {
	tip(id,"A");//id--会员id，D--启用
}


/**
 * 弹出警告框
 */
function tip(id,oper) {
	var raw = jQuery("#grid-table").jqGrid('getRowData', id);
	var frameSrc = "/jsp/member/tip.jsp?id=" + id+"&oper="+oper;
	$("#delIframe").attr("src", frameSrc);
    $('#delModal').modal({ show: true, backdrop: 'static' });
};


$("#delModal", parent.document).on("hidden.bs.modal", function() {
	$(this).removeData("bs.modal");
	$("#grid-table").trigger("reloadGrid");
});


jQuery(function($){
	// 查询条件-->按钮-->"提交"按钮 
	$("#queryfield_submit").on("click",
		function() {
			$("#collapseOne").collapse('hide');
			var postData = $("#grid-table").jqGrid("getGridParam","postData");
			postData["filters.id"]     =  $("#id").val();//客户ID
			postData["filters.name"]   =  $("#name").val()//客户名称
			postData["filters.taname"] =  $("#taname").val();//旅行社名称
			postData["filters.stat"]   =  $("#stat").val();//客户状态
			$("#grid-table").jqGrid("setGridParam", {
				datatype : 'json',
				postData : postData
			}).trigger("reloadGrid");
		});
	

	// 定义grid的选择器
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
			});
	
	function contactStr(object){
		var str = '';
		for (k in object)
			str += ';' + k + ":" + object[k];
		return  str.substring(1);
	}
	//TODO 读取状态数据字典
	// 客户状态
	var stat_items = {
		"0":"未激活",
		"1":"正常",
		"99":"信息变更",
		"-100":"注销"
	};
	
	$(grid_selector).jqGrid(
			{
				url:"/member/admin/listpage.htm",
				datatype : "json",
				mtype : 'POST',
				height : 400,
				colNames : [ '客户ID', '客户名称','旅行社名称', '联系方式', 
				             '邮件地址', '地址', '状态', '操作' ],
				colModel : [ {
					name : 'id',
					index : 'id',
					width : 100,
					sortable : false,
					editable : false,
				},
				{
					name : 'name',
					index : 'name',
					width : 150,
					sortable : false,
					editable : false,
				},
				{
					name : 'taname',
					index : 'taname',
					width : 150,
					sortable : false,
					editable : false,
				}, {
					name : 'contact',
					index : 'contact',
					width : 120,
					sortable : false,
					editable : false,
				}, {
					name : 'email',
					index : 'email',
					width : 100,
					editable : false,
					sortable : false,
				}, 
				{
					name : 'address',
					index : 'address',
					width : 160,
					sortable : false,
					editable : false,
				} , 
				{
					name : 'stat',
					index : 'stat',
					width : 80,
					sortable : false,
					editable : false,
					
					formatter : function(v, opt, rec) {
						return stat_items[v];
					},
					
				},
				{
					name : 'myaction',
					index : '',
					width : 180,
					fixed : true,
					sortable : false,
					resize : false,
					formatter : 
						function(cellvalue, options, rec) {
							var showButton='<a href="javascript:void(0)" class="btn btn-info btn-xs "'+
												'onclick="showCustomer(\''+ rec.id+ '\');">'+
												'<span class="glyphicon glyphicon-zoom-in"></span>查看'+
											'</a>';
							
							var stopButton='<a href="javascript:void(0)" class="btn btn-danger btn-xs"'+
											' onclick="stopCustomer(\''+ rec.id+ '\');">'+
												'<span class="glyphicon glyphicon-minus-sign"></span>禁用'+
											'</a>';
							
							var activeButton='<a href="javascript:void(0)" class="btn btn-success btn-xs"'
													+'onclick="activeCustomer(\''+ rec.id+'\');">'+
												'<span class="glyphicon glyphicon-ok"></span>启用'+
											'</a>';
						          
							var separator="&nbsp;"
							var cusStat=rec.stat;
							var button = showButton;
							if(cusStat==1){
								button=button+separator+stopButton;
							}else if(cusStat==-100){
								button=button+separator+activeButton;
							}
						return button;
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
