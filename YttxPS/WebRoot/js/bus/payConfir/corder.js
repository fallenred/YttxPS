/*
 * 查看订单详情
 */
function showOrder(id){
	var raw = jQuery("#order_table").jqGrid('getRowData', id);
	window.location.href = "/payConfir/showOrder.htm?orderId=" + raw.no;		
}

/*
 *订单确认支付
 */
function prodStatement(id){
	var raw = jQuery("#order_table").jqGrid('getRowData', id);
	window.location.href = "/payConfir/prodStat.htm?orderId=" + raw.no;
}

/*
 * 查看结算单详情
 */
function showStatement(id){
	window.location.href = "/payConfir/showStatement.htm?fsId=" + id;	
}


/**
 * 结算单查询--设置查询条件的参数
 */
function setStateFilterParams(postData){
	//结算单状态
	postData["fstatFilters.stat"] = $.trim($("#statement_stat").val());
	//结算单号
	postData["fstatFilters.statementId"] = $.trim($("#statement_id").val());
	//订单号
	postData["fstatFilters.orderId"] = $.trim($("#statement_order_id").val());
	//客户ID
	postData["fstatFilters.userId"] = $.trim($("#statement_user_id").val());
	//
	postData["fstatFilters.createDateRange"] = $.trim($("#statment_date_range").val());
}


$(document).ready(function(){
	$('#order_date_range').daterangepicker();
	$('#statment_date_range').daterangepicker();
	
	/**
	 * 全部订单--"重置按钮"
	 */
	$("#order_reset").click(function(){
		//清空input输入框
		$("#order_filter_form")
			.find("input[type='text']")
			.each(function(){
				$(this).val("");
			});
		//select复位
		$("order_filter_form")
			.find("select")
			.each(function(){
				$(this).find("option[value='']").prop("selected",true);
			});
	})
	
	/**
	 * 全部订单--"查询按钮"
	 */
	$("#order_submit").click(function(){
		var postData = $("#order_table").jqGrid("getGridParam","postData");
		postData["filters.orderId"] = $.trim($("#order_id").val());
		postData["filters.oderName"] = $.trim($("#order_name").val());
		postData["filters.createDateRange"] = $.trim($("#order_date_range").val());
		postData["filters.routeType"] = $("#order_route_type").val();
		$("#order_table").jqGrid("setGridParam", {
			datatype : 'json',
			postData : postData
		}).trigger("reloadGrid");
	})
	
	
	var order_grid_selector = "#order_table";
	var order_pager_selector = "#order_pager";
	
	$(window).on('resize.jqGrid', function() {
		$(order_pager_selector).jqGrid('setGridWidth', $(".page-content").width());
	})
	
	var parent_column = $(order_grid_selector).closest('[class*="col-"]');
	$(document).on(
			'settings.ace.jqGrid',
			function(ev, event_name, collapsed) {
				if (event_name === 'sidebar_collapsed'
						|| event_name === 'main_container_fixed') {
					setTimeout(function() {
						$(grid_selector).jqGrid('setGridWidth',
								parent_column.width());
					}, 0);
				}
			});
	
	/**
	 * 线下支付订单列表
	 */
	$(order_grid_selector).jqGrid({
		url : "/payConfir/simplelist.htm",
		datatype : "json",
		mtype : 'POST',
		height : 400,
		colNames : ['订单ID','订单名称','用户ID','创建时间','状态' ,'操作'],
		colModel : [{
			name : 'no',
			index : 'no',
			width : 140,
		},
		{
			name : 'name',
			index : 'name',
			width : 200,
			sortable : false,
		},
		{
			name : 'userId',
			index : 'userId',
			width : 100,
			sortable : false,
		}, {
			name : 'createDateDesc',
			index : 'createDateDesc',
			width : 150,
			sortable : false,
		}, 
		{
			name : 'stat',
			index : 'stat',
			width : 150,
			sortable : false,
			formatter : function(v, opt, rec) {
				return order_stat_item[v];
			},
			
		},{
			name : 'myaction',
			index : '',
			width : 100,
			fixed : true,
			sortable : false,
			resize : false,
			formatter : function(v, opt, rec){
				var detailBtn = '<div title="" class="ui-pg-div ui-inline-edit" '+
						'style="display: block; cursor: pointer; float: left;"'+
						'onmouseover="jQuery(this).addClass(\'ui-state-hover\');"'+
						'onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" '+
						'onclick="showOrder('+ opt.rowId + ');" data-original-title="确认收款">'+
						'<span class="ui-icon ace-icon fa fa-cog red"></span></div>';
				return rec.stat==6?detailBtn:detailBtn;
			}
		}],
		viewrecords : true,
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : $(order_pager_selector),
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
	
	/**
	 * 结算单--"重置按钮"
	 */
	$("#stat_reset").on("click",function(){
		//清空input输入框
		$("#stat_filter_form")
			.find("input[type='text']")
			.each(function(){
				$(this).val("");
			});
	})
	
	/**
	 * 结算单--"查询按钮"
	 */
	$("#stat_submit").click(function(){
		 reloadFStatmentTable();
	})
	
	
	/**
	 * 结算单--"标签卡"的点击响应函数
	 */
	$(".statPane").click(function(){
		$("#stat_reset").click();//清空查询条件
		var stat=$(this).attr("data-stat");//设置需要查询的状态
		$("#statement_stat").val(stat);
		//重新加载结算列表数据
		reloadFStatmentTable();
	})
	
	/**
	 * 结算单--"选择器"
	 */
	var statement_grid = "#stat_table";
	var statement_pager = "#stat_pager";
	
	$(window).on('resize.jqGrid', function() {
		$(statement_pager).jqGrid('setGridWidth', $(".page-content").width());
	})
	
	var parent_column = $(statement_grid).closest('[class*="col-"]');
	$(document).on(
			'settings.ace.jqGrid',
			function(ev, event_name, collapsed) {
				if (event_name === 'sidebar_collapsed'
						|| event_name === 'main_container_fixed') {
					setTimeout(function() {
						$(grid_selector).jqGrid('setGridWidth',
								parent_column.width());
					}, 0);
				}
	});
	
	var fsmt_stat_item = {
			'-1' : '线下支付'
		};
	/**
	 * 线下支付结算单列表
	 */
	$(statement_grid).jqGrid({
		url : "/payConfir/FSList.htm",
		datatype : "json",
		mtype : 'POST',
		height : 400,
		colNames : ['订单编号','结算单名称','客户编号','创建时间' ,'状态','操作'],
		colModel : [{
			name : 'no',
			index : 'no',
			width : 170,
			formatter : function(v, opt, rec) {
				return "<div><a href='/payConfir/showStatement.htm?fsId="
				+rec.statmentId+"'>"+rec.orderId+"</a></div>";
			},
		},
		{
			name : 'statmentName',
			index :'statmentName',
			width : 450,
			sortable : false,
		}, {
			name : 'userID',
			index : 'userID',
			width : 140,
			sortable : false,
		}, {
			name : 'creatDateDesc',
			index : 'creatDateDesc',
			width : 150,
			sortable : false,
		},  
		{
			name : 'stat',
			index : 'stat',
			width : 120,
			sortable : false,
			formatter : function(v, opt, rec) {
				return fsmt_stat_item[v];
			},
			
		},{
			name : 'myaction',
			index : '',
			width : 80,
			fixed : true,
			sortable : false,
			resize : false,
			formatter : function(v, opt, rec){
				var closeBtn = '<div title="" class="ui-pg-div ui-inline-edit" id="closeButton" style="display: block; cursor: pointer; float: left;" onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" onclick="showStatement(\''
					+ rec.statmentId
					+ '\');" data-original-title="确认收款"><span class="ui-icon ace-icon fa fa-cog red"></span></div>';
				return closeBtn;
			}
		}],
		viewrecords : true,
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : $(stat_pager),
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
	
	$(window).triggerHandler('resize.jqGrid');

	$(order_grid_selector).jqGrid('navGrid', order_pager_selector, {
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
		$(order_grid_selector).jqGrid('GridUnload');
		$('.ui-jqdialog').remove();
	});
	
	//	订单支付确认提交
	$("#orderConfir_submit").on("click", function () {
		if ($("#editform").valid()) {
			$.post("/payConfir/orderConfir.htm",
					$("#editform").serialize(),
					function(data){
				var json = eval("("+data+")");
				if(json.result == "ok") {
					$("#message").text("修改记录成功");
					$("#message").show();
					return true;
				}
				else {
					$("#message").text("修改记录失败:" + json.message );
					$("#message").show();
					return false;
				}
				return false;
			});
		} else {
			$("#message").text("输入字段验证错误，请重新编辑后再提交");
			$("#message").show();
			return false;
		}
	});
})