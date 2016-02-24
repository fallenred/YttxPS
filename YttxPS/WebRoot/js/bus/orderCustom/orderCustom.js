//	显示详情
var raw = {};
function showOrderlist(id) {
	raw = jQuery("#grid-table").jqGrid('getRowData', id);
	$("#showModal").modal({
	    remote: "/jsp/routeCC/show.jsp"
	}); 
};

function editOrderlist(id) {
	raw = jQuery("#grid-table").jqGrid('getRowData', id);
	var frameSrc = "/jsp/orderCustom/edit.jsp";
    $("#editIframe").attr("src", frameSrc);
    $('#editModal').modal({ show: true, backdrop: 'static' });
};

function deleteOrderlist(id) {
	raw = jQuery("#grid-table").jqGrid('getRowData', id);
	var frameSrc = "/jsp/orderCustom/delete.jsp?no=" + raw.fiIndex;
	$("#delIframe").attr("src", frameSrc);
    $('#delModal').modal({ show: true, backdrop: 'static' });
};

$("#addIframe").on("load",function(){
	$(this).contents().find("#reset").click();
	$(this).contents().find("#fiIndex").val(raw.fiIndex);
	$(this).contents().find("#fsOrderName").val(raw.fsName);
	$(this).contents().find("#fdTotalFee").val(raw.fdTotalFee);
	$(this).contents().find("#fdPaidAmt").val(raw.fdPaidAmt);
});


$("#editIframe").on("load",function(){
	$(this).contents().find("#reset").click();
	$(this).contents().find("#fiId").val(raw.fiId);
	$(this).contents().find("#fsOrderId").val(raw.fsOrderId);
	$(this).contents().find("#fsName").val(raw.fsName);
	$(this).contents().find("#fiSeq").val(raw.fiSeq);
	$(this).contents().find("#ftCreatdate").val(raw.ftCreatdate);
	$(this).contents().find("#fiType").val(raw.fiType);
	$(this).contents().find("#fsContactname").val(raw.fsContactname);
	$(this).contents().find("#fsContacttel").val(raw.fsContacttel);
	$(this).contents().find("#fiTotal").val(raw.fiTotal);
	$(this).contents().find("#fiOlder").val(raw.fiOlder);
	$(this).contents().find("#fiAdult").val(raw.fiAdult);
	$(this).contents().find("#fiChildren").val(raw.fiChildren);
	$(this).contents().find("#fsPostscript").val(raw.fsPostscript);
	$(this).contents().find("#fdAmt").val(raw.fdAmt);
	$(this).contents().find("#fiStat").val(raw.fiStat);
	$(this).contents().find("#fiGenindex").val(raw.fiGenindex);
	$(this).contents().find("#ftStartdate").val(raw.ftStartdate);
	//获取资源快照
	if (raw.fiId != null) {
		findSnapshot(this, raw.fiId);
	}
	//获取线路景区
	if (raw.fiGenindex != null) {
		getSceniceGen(this, raw.fiGenindex);
	}
	
	
	//生成线路日程头
	var html = $(this).contents().find("#myTab").html();
	for (var i = 1; i < raw.fiDays; i ++) {
		html += '<li><a href="#day'+i+'" data-toggle="tab">第'+(parseInt(i)+1)+'天</a></li>';
	}
	$(this).contents().find("#myTab").html(html);
	//生成线路日程体
	getTabContent(this, raw.fiDays);
});

function getSceniceGen(obj, fiGenindex){
	//获取线路景区
	var num = '';
	$.ajax({
		type: "GET",
		url: "/scenicGen/selectScenicGen.htm",
		data: "scenicGen.fiGenindex="+fiGenindex,
		dataType: "json",
		success: function(data){
			var html = '线路景区：'; 
			$.each(data, function(commentIndex, comment){
				html += '<input type="hidden" name="scenicGen" value="' + comment['fsScenicno'] + '"/>' + '&nbsp;&nbsp;<label>' + comment['fsScenicname']+'</label>';
				if(commentIndex == 0)
					num = comment['fiDays'];
			});
			$(obj).contents().find("#div_scenics").html(html);
			getTicket(obj);
			//getDays(num);
			getShop(obj);
		}
	});
}
function getTicket(obj){
	//获取景区票列表
	var scenic = '';
	$(obj).contents().find("input[name='scenicGen']").each(function(){
		scenic += $(this).val() + ",";
	});
	$.ajax({
		type: "GET",
		traditional: true,
		url: "/ticket/selectTicket.htm",
		data: "scenicno=" + scenic,
		dataType: "json",
		success: function(data){
			var html = ''; 
			$.each(data, function(commentIndex, comment){
				html += '<option value=' + comment['fsNo'] + '>' +comment['fsScenicname'] + '--' + comment['fsName'] + '</option>';
			});
			$(obj).contents().find(".select_ticket").html(html);
		}
	});
}
function getShop(obj){
	//获取购物店列表
	var scenic = '';
	$(obj).contents().find("input[name='scenicGen']").each(function(){
		scenic += $(this).val() + ",";
	});
	$.ajax({
		type: "GET",
		traditional: true,
		url: "/shop/selectShop.htm",
		data: "scenicno=" + scenic,
		dataType: "json",
		success: function(data){
			var html = ''; 
			$.each(data, function(commentIndex, comment){
				html += '<option value=' + comment['no'] + '>' +comment['scenicname'] + '--' + comment['name'] + '</option>';
			});
			$(obj).contents().find(".shop").html(html);
		}
	});
}

//获取资源快照
function findSnapshot(obj, no){
	$.ajax({
		type: "POST",
		url: "/orderCustom/findSnapshot.htm",
		data: 'no='+no,
		dataType: "json",
		success: function(data){
			var html = ''; 
			//模糊字段处理
			$.each(data.resSnapshot.reslist, function(commentIndex, comment){
				if(comment['restype'] == 'cx'){
					$(obj).contents().find("#transName").val(comment['resname']);
				}
				if(comment['restype'] == 'dy'){
					$(obj).contents().find("#custGuideLvl").val(comment['resname']);
				}
			});
			//精确字段处理
			$.each(data.fuzzySnapshot.reslist, function(commentIndex, comment){
				alert();
				if(comment['restype'] == 'cx'){
					$(obj).contents().find("#transType").val(comment['resno']);
					$(obj).contents().find("#resTransName").attr("value", comment['resname']);
					$(obj).contents().find("#transPrice").attr("value", comment['cclist'].price);
				}
				if(comment['restype'] == 'dy'){
					$(obj).contents().find("#guideNo").val(comment['resno']);
					$(obj).contents().find("#guideName").attr("value", comment['resname']);
					$(obj).contents().find("#guidePrice").attr("value", comment['cclist'].price);
				}
			});
		}
	});
}

function getTabContent(obj, num){
	var html = $(obj).contents().find("#myTabContent").html();
	for (var i = 1; i < num; i ++) {
	html += '<div class="tab-pane fade" id="day'+i+'">'+
						'<div class="row" style="margin-top: 10px;">'+
							'<div class="col-sm-12" style="padding: 10 0 0 0px;">'+
								'<div class="row">'+
									'<div class="form-group">'+
										'<label class="col-sm-2 control-label no-padding-right" for="fsRegions">门票选择</label>'+
										'<div class="col-sm-3">'+
											'<select id="ticket" name="ticket" class="ticket form-control"></select>'+
										'</div>'+
										'<div class="col-sm-3">'+
											'<button type="button" class="btn btn-success pull-right" id="addTicketBtn">添加</button>'+
										'</div>'+
										'<div class="col-sm-3"> <button type="button" class="btn btn-success pull-left" id="rmTicketBtn">删除</button>'+
										'</div>'+
									'</div>'+
								'</div>'+
								'<div class="row">'+
									'<div class="form-group">'+
										'<label class="col-sm-2 control-label no-padding-right" for="fsProperty"></label>'+
										'<div class="col-sm-8" id="div_ticket"></div>'+
									'</div>'+
								'</div>'+
								'<div class="row">'+
									'<div class="form-group">'+
										'<label class="col-sm-2 control-label no-padding-right" for="fsRegions">餐厅选择</label>'+
										'<div class="col-sm-3">'+
											'<select id="transportArrange" name="transportArrange" class="form-control"></select>'+
										'</div>'+
										'<div class="col-sm-3">'+
											'<button type="button" class="btn btn-success pull-right" id="addTransportBtn">添加</button>'+
										'</div>'+
										'<div class="col-sm-3">'+
											'<button type="button" class="btn btn-success pull-left" id="rmTransportBtn">删除</button>'+
										'</div>'+
									'</div>'+
								'</div>'+
								'<div class="row">'+
									'<div class="form-group">'+
										'<label class="col-sm-2 control-label no-padding-right" for="fsProperty"></label>'+
										'<div class="col-sm-8">'+
											'<input name="" type="checkbox" /> 早餐&nbsp;&nbsp;&nbsp;'+
											'<input name="" type="checkbox" /> 午餐&nbsp;&nbsp;&nbsp;'+
											'<input name="" type="checkbox" /> 晚餐&nbsp;&nbsp;&nbsp;'+
										'</div>'+
									'</div>'+
								'</div>'+
								'<div class="row">'+
									'<div class="form-group">'+
										'<label class="col-sm-2 control-label no-padding-right" for="fsRegions">娱乐项目选择</label>'+
										'<div class="col-sm-3">'+
											'<select id="transportArrange" name="transportArrange" class="form-control"></select>'+
										'</div>'+
										'<div class="col-sm-3">'+
											'<button type="button" class="btn btn-success pull-right" id="addTransportBtn">添加</button>'+
										'</div>'+
										'<div class="col-sm-3">'+
											'<button type="button" class="btn btn-success pull-left" id="rmTransportBtn">删除</button>'+
										'</div>'+
									'</div>'+
								'</div>'+
								'<div class="row">'+
									'<div class="form-group">'+
										'<label class="col-sm-2 control-label no-padding-right" for="fsProperty"></label>'+
										'<div class="col-sm-8">'+
											'<input name="" type="checkbox" /> 全价票 <input name="" type="checkbox" /> 半价票'+
										'</div>'+
									'</div>'+
								'</div>'+
								'<div class="row">'+
									'<div class="form-group">'+
										'<label class="col-sm-2 control-label no-padding-right" for="fsRegions">酒店标准</label>'+
										'<div class="col-sm-3">'+
											'<select id="fsStarLvl" name="fsStarLvl" class="form-control"></select>'+
										'</div>'+
										'<label class="col-sm-2 control-label no-padding-right" for="fsRegions">酒店选择</label>'+
										'<div class="col-sm-3">'+
											'<select id="accomadationNo" name="accomadationNo" class="form-control"></select>'+
										'</div>'+
									'</div>'+
								'</div>'+
								'<div class="row">'+
									'<div class="form-group">'+
										'<label class="col-sm-2 control-label no-padding-right" for="fsRegions">房型选择</label>'+
										'<div class="col-sm-3">'+
											'<select id="roomno" name="roomno" class="form-control"></select>'+
										'</div>'+
										'<div class="col-sm-3">'+
											'<button type="button" class="btn btn-success pull-right" id="addRoomBtn">添加</button>'+
										'</div>'+
										'<div class="col-sm-3">'+
											'<button type="button" class="btn btn-success pull-left" id="rmRoomBtn">删除</button>'+
										'</div>'+
									'</div>'+
								'</div>'+
								'<div class="row">'+
									'<div class="form-group">'+
										'<label class="col-sm-2 control-label no-padding-right" for="fsProperty"></label>'+
										'<div class="col-sm-8" id="div_room"></div>'+
									'</div>'+
								'</div>'+
								'<div class="row">'+
									'<div class="form-group">'+
										'<label class="col-sm-2 control-label no-padding-right" for="fsRegions">购物店选择</label>'+
										'<div class="col-sm-3">'+
											'<select id="shop" name="shop" class="form-control"></select>'+
										'</div>'+
										'<div class="col-sm-3">'+
											'<button type="button" class="btn btn-success pull-right" id="addShopBtn">添加</button>'+
										'</div>'+
										'<div class="col-sm-3">'+
											'<button type="button" class="btn btn-success pull-left" id="rmShopBtn">删除</button>'+
										'</div>'+
									'</div>'+
								'</div>'+
								'<div class="row">'+
									'<div class="form-group">'+
										'<label class="col-sm-2 control-label no-padding-right" for="fsProperty"></label>'+
										'<div class="col-sm-8" id="div_shop"></div>'+
									'</div>'+
								'</div>'+
							'</div>'+
						'</div>'+
				'</div>';
	}
	$(obj).contents().find("#myTabContent").html(html);
}

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
		// $("#collapseTwo").collapse('show');
		var postData = $("#grid-table").jqGrid("getGridParam", "postData");
		postData["orderCustom.fsNo"] = $("#queryfield").find("#fsNo").val();
		postData["orderCustom.fsName"] = $("#queryfield").find("#fsName").val();
		postData["orderCustom.fiDays"] = $("#queryfield").find("#fiDays").val();
		postData["orderCustom.fiStat"] = $("#queryfield").find("#fiStat").val();
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
		var detail = '<div title="" class="ui-pg-div ui-inline-edit" id="detailButton" style="display: block; cursor: pointer; float: left;" onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" onclick="showOrderlist('
			+ options.rowId
			+ ');" data-original-title="查看订单"><span class="ace-icon fa fa-plus-circle purple"></span></div>';

	var editBtn = '<div title="" class="ui-pg-div ui-inline-edit" id="editButton" style="display: block; cursor: pointer; float: left;" onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" onclick="editOrderlist('
			+ options.rowId
			+ ');" data-original-title="订单批次修改"><span class="ui-icon ui-icon-pencil"></span></div>';

	var deleteBtn = '<div title="" class="ui-pg-div ui-inline-edit" id="deleteButton" style="display: block; cursor: pointer; float: left;" onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" onclick="deleteOrderlist('
			+ options.rowId
			+ ');" data-original-title="订单批次删除"><span class="ui-icon ace-icon fa fa-trash-o red"></span></div>';
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

	var items = {
		'0' : '待审核',
		'1' : '已审核待确认',
		'-1' : '审核未通过',
		'2' : '客户已确认',
		'32' : '已入账结算'
	};
	var s = '';
	for (k in items)
		s += ';' + k + ":" + items[k];
	s = s.substring(1);
	//线路类型
	var fiType = {
			'1' : '团队客户',
			'2' : '散客客户'
		};
	var s1 = '';
	for (k in fiType)
			s1 += ';' + k + ":" + fiType[k];
	s1 = s1.substring(1);
	jQuery(grid_selector).jqGrid(
			{
				url : "/orderCustom/findOrderCustom.htm",
				datatype : "json",
				mtype : 'POST',
				height : 400,
				colNames : ['操作', '序号', '订单ID','订单名称', '批次顺序号', '创建时间', '批次类型', '联系人', '联系电话',
				            '总人数', '老人数', '成年人数', '儿童数', '附言', '预估金额小计', '状态','订单线路','发团日期'],
				colModel : [ {
					name : 'myac',
					index : '',
					width : 70,
					fixed : true,
					sortable : false,
					resize : false,
					formatter : actFormatter
				}, {
					name : 'fiId',
					index : 'fiId',
					width : 85,
					sorttype : "int",
					hidden : true
				}, {
					name : 'fsOrderId',
					index : 'fsOrderId',
					width : 100,
					editable : true,
					sorttype : "char",
					hidden : true
				}, {
					name : 'fsName',
					index : 'fsName',
					width : 100,
					editable : true,
					sorttype : "char",
					hidden : true
				}, {
					name : 'fiSeq',
					index : 'fiSeq',
					width : 100,
					editable : true,
					sorttype : "char"
				}, {
					name : 'ftCreatdate',
					index : 'ftCreatdate',
					width : 50,
					editable : true,
					sorttype : "date",
					formatter : function(value){
						var timestamp = "";
						if(value != ''){//rData[7]表示日期列
							timestamp = (new Date(parseFloat(value))).format("yyyy/MM/dd");
						}
						return timestamp;
					}
				}, {
					name : 'fiType',
					index : 'fiType',
					width : 80,
					sortable : true,
					editable : true,
					edittype : 'select',
					editoptions : {
						value : s
					},
					formatter : function(v, opt, rec) {
						return fiType[v];
					},
					unformat : function(v) {
						for (k in fiType)
							if (fiType[k] == v)
								return k;
						return '1';
					}
				}, {
					name : 'fsContactname',
					index : 'fsContactname',
					width : 100,
					editable : true,
					sorttype : "int",
					hidden : true
				}, {
					name : 'fsContacttel',
					index : 'fsContacttel',
					width : 100,
					editable : true,
					sorttype : "int",
					hidden : true
				}, {
					name : 'fiTotal',
					index : 'fiTotal',
					width : 45,
					editable : true,
					sorttype : "int"
				}, {
					name : 'fiOlder',
					index : 'fiOlder',
					width : 100,
					editable : true,
					sorttype : "int",
					hidden : true
				}, {
					name : 'fiAdult',
					index : 'fiAdult',
					width : 45,
					sortable : true,
					sorttype : "char"
				}, {
					name : 'fiChildren',
					index : 'fiChildren',
					width : 50,
					editable : true,
					sorttype : "int"
				}, {
					name : 'fsPostscript',
					index : 'fsPostscript',
					width : 50,
					editable : true,
					sorttype : "int"
				}, {
					name : 'fdAmt',
					index : 'fdAmt',
					width : 60,
					editable : true,
					sorttype : "int"
				}, {
					name : 'fiStat',
					index : 'fiStat',
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
				}, {
					name : 'fiGenindex',
					index : 'fiGenindex',
					width : 100,
					editable : true,
					sorttype : "char",
					hidden : true
				},{
					name : 'ftStartdate',
					index : 'ftStartdate',
					width : 50,
					editable : true,
					sorttype : "date",
					hidden : true,
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

//				multiselect : true,
//				multiboxonly : true,
//				multipleSearch : true,

				loadComplete : function() {
					var table = this;
					setTimeout(function() {

						updateActionIcons(table);
						updatePagerIcons(table);
						enableTooltips(table);
					}, 0);
				},

				editurl : "/orderCustom/save.htm",
				shrinkToFit : true,
				autowidth : true

			/**
			 * , grouping:true, groupingView : { groupField : ['name'],
			 * groupDataSorted : true, plusicon : 'fa fa-chevron-down
			 * bigger-110', minusicon : 'fa fa-chevron-up bigger-110' },
			 * caption: "Grouping"
			 */

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

	/*jQuery(grid_selector).jqGrid('navGrid', pager_selector).jqGrid(
			'navButtonAdd', pager_selector, {
				caption : "",
				buttonicon : "ace-icon fa fa-plus-circle purple",
				onClickButton : function() {
					var frameSrc = "/jsp/orderCustom/add.jsp";
			        $("#addIframe").attr("src", frameSrc);
			        $('#addModal').modal({ show: true, backdrop: 'static' });
				},
				position : "first",
				title : "新增记录",
				cursor : "pointer"
			});*/

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
