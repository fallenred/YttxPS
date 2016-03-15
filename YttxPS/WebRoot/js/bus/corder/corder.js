/*
 * 查看订单详情
 */
function showOrder(id){
	var raw = jQuery("#order_table").jqGrid('getRowData', id);
	window.location.href = "/cOrder/showOrder.htm?orderId=" + raw.no;		
}

/*
 *生成结算单
 */
function prodStatement(id){
	var raw = jQuery("#order_table").jqGrid('getRowData', id);
	window.location.href = "/cOrder/prodStat.htm?orderId=" + raw.no;
}

/*
 * 查看结算单详情
 */
function showFStatement(fsId){
	window.location.href="/cOrder/showFS.htm?fsId="+fsId;
}

/*
 *生成结算单
 */
function editFStatement(fsId){
	window.location.href = "/cOrder/openEditFS.htm?fsId="+fsId;
}


/**
 * 结算单--重新加载结算单列表
 */
function reloadFStatmentTable(){
	var postData = $("#stat_table").jqGrid("getGridParam","postData");
	setStateFilterParams(postData);//封装查询条件参数
	$("#stat_table").jqGrid("setGridParam", {//重新加载数据
		datatype : 'json',
		postData : postData
	}).trigger("reloadGrid");
}
/*
 *结算完毕确认 
 */
function closeFStatement(fsId){
	var postData={fsId:fsId};
	$.post(
		"/cOrder/confirmFS.htm",
		postData,
		function(data){
			var json = eval("("+data+")");
			var message=null;
			if(json.result == "ok") {
				message="确认结算完毕成功！<br/>"+
					"<a href='/cOrder/page.htm'>返回结算管理</a>"
				reloadFStatmentTable();
			}else {
				message=data.message;
			}
			$("#confirmMessage").html(message);
			$('#confirmModal').modal({ show: true, backdrop: 'static' });
	});
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
	
	$(order_grid_selector).jqGrid({
		url : "/cOrder/simplelist.htm",
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
						'onclick="showOrder('+ opt.rowId+ ');" data-original-title="查看订单详情">'+
						'<span class="ui-icon ace-icon fa fa-search-plus grey"></span></div>';
				
				var  clearBtn = '<div title="" class="ui-pg-div ui-inline-edit" '+
					'style="display: block; cursor: pointer; float: left;" '+
					'onmouseover="jQuery(this).addClass(\'ui-state-hover\');" '+
					'onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" '+
					'onclick="prodStatement('+ opt.rowId+ ');" data-original-title="生成结算单">'+
					'<span class="ui-icon ace-icon fa fa-cog red"></span></div>';
				return rec.stat==32?detailBtn:detailBtn+clearBtn;
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
	
	/**
	 * 生成结算单列表
	 */
	$(statement_grid).jqGrid({
		url : "/cOrder/FSList.htm",
		datatype : "json",
		mtype : 'POST',
		height : 400,
		colNames : ['结算单号','结算单名称','订单号','客户编号','创建时间' ,'状态','操作'],
		colModel : [{
			name : 'statmentId',
			index : 'statmentId',
			width : 90,
			formatter : function(v, opt, rec) {
				return "<div><a href='/cOrder/showFS.htm?fsId="
				+rec.statmentId+"'>"+rec.statmentId+"</a></div>";
			},
		},
		{
			name : 'statmentName',
			index :'statmentName',
			width : 160,
			sortable : false,
		},
		{
			name : 'orderId',
			index : 'orderId',
			width : 160,
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
			width : 200,
			fixed : true,
			sortable : false,
			resize : false,
			formatter : function(v, opt, rec){
				var showButton = "<button class='btn btn-info btn-xs'"+
							"onclick='showFStatement(\""+rec.statmentId+"\")'>查看</button>"
				
				var editButton ="<button class='btn btn-success btn-xs'"+
							"onclick='editFStatement(\""+rec.statmentId+"\")'>修改</button>"
				
				var  closeButton = "<button class='btn btn-success btn-xs' "+
				"onclick='closeFStatement(\""+rec.statmentId+"\")'>确认结算完毕</button>"
				var retContent = null;
				if(rec.stat==0){//当结算单状态为0的时候，只能查看
					var retContent=showButton+"&nbsp; &nbsp; &nbsp;"+editButton;
				}else if(rec.stat==1){
					var retContent=showButton+"&nbsp; &nbsp; &nbsp;"+closeButton;
				}else{
					var retContent=showButton;
				}
				return retContent;
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
	
	
})