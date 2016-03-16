//表格行事件 --打开会员审核页面
function auidtCustomer(id,auditNo,auditType){
	var frameSrc = "/member/audit/auditpage.htm?id=" +id
		+"&auditNo="+auditNo+"&auditType="+auditType;
    $("#showIframe").attr("src", frameSrc);
    $('#showModal').modal({ show: true, backdrop: 'static' });
};

$("#showModal").on("hidden.bs.modal", function() {
    $(this).removeData("bs.modal");
    $("#grid-table").trigger("reloadGrid");
});


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

//封装查询条件参数
function setStateFilterParams(postData){
	postData["filters.id"]     =  $("#id").val();//客户ID
	postData["filters.name"]   =  $("#name").val()//客户名称
	postData["filters.taname"] =  $("#taname").val();//旅行社名称
	postData["filters.auditType"]   =  $("#auditType").val();//客户状态
	postData["filters.auditRet"]   =  $("#auditRet").val();//审核结果
}

//重新加载表单数据
function reloadCusAdtRecs(){
	var postData = $("#grid-table").jqGrid("getGridParam","postData");
	setStateFilterParams(postData);//封装查询条件参数
	$("#grid-table").jqGrid("setGridParam", {//重新加载数据
		datatype : 'json',
		postData : postData
	}).trigger("reloadGrid");
}


jQuery(function($){
	// 查询条件-->按钮-->"提交"按钮 
	$("#queryfield_submit").on("click",function() {
			reloadCusAdtRecs();//重新加载数据
		});
	
	// "标签卡"的点击响应函数
	$(".auditRetPane").click(function(){
		$("#reset").click();//清空查询条件
		var stat=$(this).attr("data-stat");//设置需要查询的状态
		$("#auditRet").val(stat);
		//重新加载结算列表数据
		reloadCusAdtRecs();
	})
	

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
	
	$(grid_selector).jqGrid({
		url:"/member/audit/listpage.htm",
		datatype : "json",
		mtype : 'POST',
		height : 400,
		colNames : [ '客户ID', '客户名称','旅行社名称', '联系方式', '申请审核类型', '审核人','审核时间','审核结果','操作' ],
		colModel : [ {
			name : 'id',
			index : 'id',
			width : 80,
			sortable : false,
			editable : false,
		},
		{
			name : 'name',
			index : 'name',
			width : 140,
			sortable : false,
			editable : false,
		},
		{
			name : 'taname',
			index : 'taname',
			width : 140,
			sortable : false,
			editable : false,
		}, {
			name : 'contact',
			index : 'contact',
			width : 100,
			sortable : false,
			editable : false,
		}, {
			name : 'auditType',
			index : 'auditType',
			width : 60,
			editable : false,
			sortable : false,
			formatter: function(v, opt, rec) {
				return auditType_item[v];
			},
		}, 
		{
			name : 'auditor',
			index : 'auditor',
			width : 100,
			sortable : false,
			editable : false,
		} , 
		{
			name : 'auditTimeDesc',
			index : 'auditTimeDesc',
			width : 110,
			sortable : false,
			editable : false,
		},
		{
			name : 'auditRet',
			index : 'auditRet',
			width : 60,
			sortable : false,
			editable : false,
			formatter : function(v, opt, rec) {
				return auditRet_item[v];
			},
			
		},
		{
			name : 'myaction',
			index : '',
			width : 120,
			fixed : true,
			sortable : false,
			resize : false,
			formatter : 
				function(cellvalue, options, rec) {
					
					var auditButton='<a href="javascript:void(0)" class="btn btn-info btn-xs "'+
										'onclick="auidtCustomer(\''+ rec.id+ '\',\''+rec.auditno+'\',\''+rec.auditType+'\');">'+
										'<span class="glyphicon glyphicon-check"></span>审核'+
									'</a>';
					
					var showButton='<a href="javascript:void(0)" class="btn btn-info btn-xs "'+
										'onclick="auidtCustomer(\''+ rec.id+ '\',\''+rec.auditno+'\',\''+rec.auditType+'\');">'+
										'<span class="glyphicon glyphicon-zoom-in"></span>查看审核记录'+
									'</a>';
					var auditRet = rec.auditRet;
				return auditRet==0?auditButton:showButton;
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
