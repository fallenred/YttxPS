//	显示详情
var raw = {};

//批次管理配置
function configOrderCustom(id) {
    var raw = jQuery("#grid-table").jqGrid('getRowData', id);
    var page = "/jsp/orderCustom/orderCustom.jsp?fsNo=" + raw.fsNo+"&fsName="+escape(raw.fsName);
    window.open(page);
};

function showOrderlist(id) {
	raw = jQuery("#grid-table").jqGrid('getRowData', id);
	$("#showModal").modal({
	    remote: "/jsp/routeCC/show.jsp"
	}); 
};

function editOrderlist(id) {
	raw = jQuery("#grid-table").jqGrid('getRowData', id);
	var frameSrc = '';
	if (raw.fsType == '02') {
		//专家线路
		/*frameSrc = "/jsp/orderlist/experts.jsp?fsNo="+raw.fsNo+"&genIndex="+raw.fiGenindex+"&startdate="+raw.ftStartdate;
		$("#editIframe").attr("src", frameSrc);
		$('#editModal').modal({ show: true, backdrop: 'static' });*/
		window.location.href = "/orderlist/showOrder.htm?pageName=experts&fsNo=" + raw.fsNo+"&startdate="+raw.ftStartdate+"&genIndex="+raw.fiGenindex;
		if (raw.fiStat == '0') {
			html +='<option value="0">待审核</option>'
			$(this).contents().find("#fiStat").html(html);
			$(this).contents().find(".div_transfer_stat").show();
		} else if (raw.fiStat == '1') {
			html +='<option value="1">已审核</option>'
			$(this).contents().find("#fiStat").html(html);
			$(this).contents().find(".div_transfer_stat").show();
			$(this).contents().find(".div_custList").show();
		} else if (raw.fiStat == '6') {
			html +='<option value="6">线下支付</option>'
			$(this).contents().find("#fiStat").html(html);
			$(this).contents().find(".div_transfer_stat").show();
			$(this).contents().find(".div_custList").show();
		} else if (raw.fiStat == '8') {
			html ='<option value="8">已付全款(可出团)</option>';
			$(this).contents().find("#fiStat").html(html);
			$(this).contents().find(".div_transfer_stat").show();
			$(this).contents().find(".div_custList").show();
		} else if (raw.fiStat == '32'){
			html ='<option value="32">已入结算单</option>';
			$(this).contents().find(".div_transfer_stat").show();
			$(this).contents().find(".div_custList").show();
			$(this).contents().find("#submit").hide();
		} else {
			$(this).contents().find("#fiStat").html('<option value="'+raw.fiStat+'"></option>');
		}
	} else if(raw.fsType == '03') {
		//定制线路
		/*frameSrc = "/jsp/orderlist/customization.jsp?fsNo="+raw.fsNo+"&startdate="+raw.ftStartdate;
		$("#customizationIframe").attr("src", frameSrc);
		$('#customizationModal').modal({ show: true, backdrop: 'static' });*/
		window.location.href = "/orderlist/showOrder.htm?pageName=customization&fsNo=" + raw.fsNo+"&startdate="+raw.ftStartdate;
	}
};

/**
 * modify by marongcai
 * 删除功能
 * 2016-4-6
 * modify by start
 */
function deleteOrderlist(id) {
	raw = jQuery("#grid-table").jqGrid('getRowData', id);
	var frameSrc = "/jsp/orderlist/delete.jsp?fsNo=" + raw.fsNo;
	$("#delIframe").attr("src", frameSrc);
    $('#delModal').modal({ show: true, backdrop: 'static' });
};
/**
 * modify end
 */

$("#addIframe").on("load",function(){
	$(this).contents().find("#reset").click();
	$(this).contents().find("#fiIndex").val(raw.fiIndex);
	$(this).contents().find("#fsOrderName").val(raw.fsName);
	$(this).contents().find("#fdTotalFee").val(raw.fdTotalFee);
	$(this).contents().find("#fdPaidAmt").val(raw.fdPaidAmt);
});

//专家线路
$("#editIframe").on("load",function(){
	$(this).contents().find("#reset").click();
	$(this).contents().find("#fsNo").val(raw.fsNo);
	$(this).contents().find("#fiGenindex").val(raw.fiGenindex);
	$(this).contents().find("#genindex").val(raw.fiGenindex);
	$(this).contents().find("#fsName").val(raw.fsName);
	$(this).contents().find("#fsUserId").val(raw.fsUserId);
	$(this).contents().find("#fsUserSubid").val(raw.fsUserSubid);
	$(this).contents().find("#fsOperId").val(raw.fsOperId);
	$(this).contents().find("#ftCreatdate").val(raw.ftCreatdate);
	$(this).contents().find("#fsType").val(raw.fsType);
	$(this).contents().find("#fsRouteId").val(raw.fsRouteId);
	$(this).contents().find("#fsProperty").val(raw.fsProperty);
	$(this).contents().find("#fiDays").val(raw.fiDays);
	$(this).contents().find("#days").val(raw.fiDays);
	$(this).contents().find("#ftStartdate").val(raw.ftStartdate);
	$(this).contents().find("#regionno").val(raw.fsStartplace);
	$(this).contents().find("#regionname").val(raw.regionname);
	$(this).contents().find("#fdPrice").val(raw.fdPrice);
	$(this).contents().find("#fsSummary").val(raw.fsSummary);
	$(this).contents().find("#fdTotalfee").val(raw.fdTotalfee);
	$(this).contents().find("#fdPaidamt").val(raw.fdPaidamt);
	$(this).contents().find("#fsRemark").val(raw.fsRemark);
	$(this).contents().find("#fiStat").val(raw.fiStat);
	$(this).contents().find("#currStat").val(raw.fiStat);
	$(this).contents().find("#fsDac").val(raw.fsDac);
	$(this).contents().find("#fsOperId").val(raw.fsOperId);
	$(this).contents().find("#fdInsuerprice").val(raw.fdInsuerprice);
	$(this).contents().find("#fiVisitornum").val(raw.fiVisitornum);
	$(this).contents().find("#insurenum").val(raw.fiVisitornum);
	if (raw.fiGenindex != null) {
		getGenName(raw.fiGenindex, $(this).contents().find("#genName"));
	}
	html='';
	if (raw.fiStat == '0') {
		html +='<option value="0">待审核</option>'
		$(this).contents().find("#fiStat").html(html);
		$(this).contents().find(".div_transfer_stat").show();
	} else if (raw.fiStat == '1') {
		html +='<option value="1">已审核</option>'
		$(this).contents().find("#fiStat").html(html);
		$(this).contents().find(".div_transfer_stat").show();
		$(this).contents().find(".div_custList").show();
	} else if (raw.fiStat == '6') {
		html +='<option value="6">线下支付</option>'
		$(this).contents().find("#fiStat").html(html);
		$(this).contents().find(".div_transfer_stat").show();
		$(this).contents().find(".div_custList").show();
	} else if (raw.fiStat == '8') {
		html ='<option value="8">已付全款(可出团)</option>';
		$(this).contents().find("#fiStat").html(html);
		$(this).contents().find(".div_transfer_stat").show();
		$(this).contents().find(".div_custList").show();
	} else if (raw.fiStat == '32'){
		html ='<option value="32">已入结算单</option>';
		$(this).contents().find(".div_transfer_stat").show();
		$(this).contents().find(".div_custList").show();
		$(this).contents().find("#submit").hide();
	} else {
		$(this).contents().find("#fiStat").html('<option value="'+raw.fiStat+'"></option>');
	}
	/*$.base64.utf8encode = true;
	$(this).contents().find('#fcSchedule').val($.base64.btoa(raw.fcSchedule));*/
	//获取线路列表
});
/**
 * add by marongcai
 * 删除功能
 * 2016-4-6
 * add by start
 */
$("#delIframe").on("load",function(){
	$(this).contents().find("#reset").click();
	$(this).contents().find("#fsNo").val(raw.fsNo);
});
/**
 * add end
 */

function getGenName(genindex, obj){
	$.ajax({
		type: "GET",
		url: "/gen/findGenByIndex.htm",
		data: 'index='+genindex,
		dataType: "json",
		success: function(data){
			$(obj).val(data.fsName);
		}
	});
}

//定制线路
$("#customizationIframe").on("load",function(){
	$(this).contents().find("#fsNo").val(raw.fsNo);
	$(this).contents().find("#orderId").val(raw.fsNo);
	$(this).contents().find("#fsName").val(raw.fsName);
	$(this).contents().find("#ftStartdate").val(raw.ftStartdate);
	$(this).contents().find("#fsRemark").val(raw.fsRemark);
	$(this).contents().find("#fcRessnapshot").val(raw.fcRessnapshot);
	$(this).contents().find("#fiVisitornum").val(raw.fiVisitornum);
	$(this).contents().find("#insurenum").val(raw.fiVisitornum);
	$(this).contents().find("#fdTotalfee").val(raw.fdTotalfee);
	$(this).contents().find("#fdPaidamt").val(raw.fdPaidamt);
	$(this).contents().find("#fsOperId").val(raw.fsOperId);
	$(this).contents().find("#fdInsuerprice").val(raw.fdInsuerprice);
	$(this).contents().find("#fdTotalfee").val(raw.fdTotalfee);
	html='';
	if (raw.fiStat == '-10') {
		html +='<option value="-10">询价</option>';
		$(this).contents().find("#fiStat").html(html);
		$(this).contents().find(".div_transfer_stat").show();
		$(this).contents().find(".div_transfer").hide();
	} else if (raw.fiStat == '-5') {
		html +='<option value="-5">报价</option>';
		$(this).contents().find("#fiStat").html(html);
		$(this).contents().find(".div_transfer_stat").show();
		$(this).contents().find(".div_transfer").hide();
	} else if (raw.fiStat == '0') {
		html +='<option value="0">待审核</option>';
		$(this).contents().find("#fiStat").html(html);
		$(this).contents().find(".div_transfer_stat").show();
	} else if (raw.fiStat == '1') {
		html +='<option value="1">已审核</option>'
		$(this).contents().find("#fiStat").html(html);
		$(this).contents().find(".div_transfer_stat").show();
		$(this).contents().find(".div_custList").show();
	} else if (raw.fiStat == '6') {
		html +='<option value="6">线下支付</option>'
		$(this).contents().find("#fiStat").html(html);
		$(this).contents().find(".div_transfer_stat").show();
		$(this).contents().find(".div_custList").show();
	} else if (raw.fiStat == '8') {
		html ='<option value="8">已付全款(可出团)</option>';
		$(this).contents().find("#fiStat").html(html);
		$(this).contents().find(".div_transfer_stat").show();
		$(this).contents().find(".div_custList").show();
	} else if (raw.fiStat == '32'){
		html ='<option value="32">已入结算单</option>';
		$(this).contents().find("#fiStat").html(html);
		$(this).contents().find(".div_transfer_stat").show();
		$(this).contents().find("#submit").hide();
	}  else {
		$(this).contents().find("#fiStat").html('<option value="'+raw.fiStat+'"></option>');
	}
});

$("#customizationModal", parent.document).on("hidden.bs.modal", function() {
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
		postData["orderlistWithBLOBs.fsNo"] = $("#queryfield").find("#fsNo").val();
		postData["orderlistWithBLOBs.fsName"] = $("#queryfield").find("#fsName").val();
		postData["orderlistWithBLOBs.fsTaName"] = $("#queryfield").find("#fsTaName").val();
		postData["orderlistWithBLOBs.fsId"] = $("#queryfield").find("#fsId").val();
		postData["orderlistWithBLOBs.fsType"] = $("#queryfield").find("#fsType").val();
		postData["orderlistWithBLOBs.fiStat"] = $("#queryfield").find("#fiStat").val();
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
	var configBtn = '<div title="" class="ui-pg-div ui-inline-edit" id="roomButton" style="display: block; cursor: pointer; float: left;" onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" onclick="configOrderCustom('
            + options.rowId
            + ');" data-original-title="订单批次配置"><span class="ui-icon ace-icon fa fa-cog red"></span></div>';
	var editBtn = '<div title="" class="ui-pg-div ui-inline-edit" id="editButton" style="display: block; cursor: pointer; float: left;" onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" onclick="editOrderlist('
			+ options.rowId
			+ ');" data-original-title="计调订单"><span class="ui-icon ui-icon-pencil"></span></div>';

	var deleteBtn = '<div title="" class="ui-pg-div ui-inline-edit" id="deleteButton" style="display: block; cursor: pointer; float: left;" onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" onclick="deleteOrderlist('
			+ options.rowId
			+ ');" data-original-title="删除订单"><span class="ui-icon ace-icon fa fa-trash-o red"></span></div>';
	/**
	 * modify by marongcai
	 * 添加删除功能
	 * 2016-4-6
	 * modify by start
	 */
	var returnBtn = editBtn;
	if($("#depNo").val() == "5"){
		returnBtn += deleteBtn;
	}
	return returnBtn;
	/**
	 * modify end
	 */
	};

	// resize to fit page size
	$(window).on('resize.jqGrid', function() {
		$(grid_selector).jqGrid('setGridWidth', $(".page-content").width());
	});
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
	/**
	 * modify by marongcai
	 * 32状态从已完结改为已结算
	 * 2016-4-5
	 * modify start
	 */
	var items = {
		'-10' : '询价',
		'-5' : '报价',
		'0' : '待审核',
		'1' : '已审核',
		'2' : '已确认(待付款)',
		'4' : '已付首款',
		'6' : '线下支付', 
		'8' : '已付全款(可出团)',
		'32' : '已结算'
	};
	/**
	 * modify end
	 */
	var s = '';
	for (k in items)
		s += ';' + k + ":" + items[k];
	s = s.substring(1);
	//线路类型
	var fsType = {
			'02' : '专家线路',
			'03' : '订制线路'
		};
	var s1 = '';
	for (k in fsType)
			s1 += ';' + k + ":" + fsType[k];
	s1 = s1.substring(1);
	//线路类型
	var fsProperty = {
			'01' : '独立成团',
			'02' : '散客拼团'
		};
	var s2 = '';
	for (k in fsProperty)
			s1 += ';' + k + ":" + fsProperty[k];
	s2 = s2.substring(1);	
	/**
	 * modify by marongcai
	 * 隐藏线路天数、发团日期，添加旅行社名称列
	 * 2016-3-18
	 * modify by start
	 */
	jQuery(grid_selector).jqGrid(
			{
				url : "/orderlist/findOrderlist.htm",
				postData : {"orderlistWithBLOBs.fsNo":$("#fsNo").val()},
				datatype : "json",
				mtype : 'POST',
				height : 400,
				colNames : ['操作', '订单编号', '线路统称Idx', '订单名称', '用户编号', '子用户编号', '计调ID', '创建时间', '线路类型', 'fs_Route_ID', '组团类型', '线路天数','旅行社名称',
				            '发团日期', '发团地id', '发团地', '线路报价', '线路摘要', '预估全价', '已缴金额', '整体备注', '状态', '验证码', '日程快照', '资源快照', '总人数','保险费用'],
				colModel : [ {
					name : 'myac',
					index : '',
					width : 60,
					fixed : true,
					sortable : false,
					resize : false,
					formatter : actFormatter
				}, {
					name : 'fsNo',
					index : 'fsNo',
					width : 100,
					sorttype : "int"
				}, {
					name : 'fiGenindex',
					index : 'fiGenindex',
					width : 100,
					editable : true,
					sorttype : "char",
					hidden : true
				}, {
					name : 'fsName',
					index : 'fsName',
					width : 100,
					editable : true,
					sorttype : "char"
				}, {
					name : 'fsUserId',
					index : 'fsUserId',
					width : 50,
					editable : true,
					sorttype : "int"
				}, {
					name : 'fsUserSubid',
					index : 'fsUserSubid',
					width : 50,
					editable : true,
					sorttype : "int"
				}, {
					name : 'fsOperId',
					index : 'fsOperId',
					width : 100,
					editable : true,
					sorttype : "int",
					hidden : true
				}, {
					name : 'ftCreatdate',
					index : 'ftCreatdate',
					width : 50,
					editable : true,
					sorttype : "date",
					formatter : function(value){
						var timestamp = "";
						if(value != ''){//rData[7]表示日期列
							timestamp = (new Date(parseFloat(value))).format("yyyy/MM/dd hh:mm:ss");
						}
						return timestamp;
					}
				}, {
					name : 'fsType',
					index : 'fsType',
					width : 45,
					sortable : true,
					editable : true,
					edittype : 'select',
					editoptions : {
						value : s1
					},
					formatter : function(v, opt, rec) {
						return fsType[v];
					},
					unformat : function(v) {
						for (k in fsType)
							if (fsType[k] == v)
								return k;
						return '1';
					}
				}, {
					name : 'fsRouteId',
					index : 'fsRouteId',
					width : 100,
					editable : true,
					sorttype : "int",
					hidden : true
				}, {
					name : 'fsProperty',
					index : 'fsProperty',
					width : 45,
					sortable : true,
					editable : true,
					edittype : 'select',
					editoptions : {
						value : s2
					},
					formatter : function(v, opt, rec) {
						return fsProperty[v];
					},
					unformat : function(v) {
						for (k in fsProperty)
							if (fsProperty[k] == v)
								return k;
						return '';
					}
				}, {
					name : 'fiDays',
					index : 'fiDays',
					width : 50,
					editable : true,
					sorttype : "int",
					hidden : true
				},  {
					name : 'fsTaName',
					index : 'fsTaName',
					width : 50,
					editable : true,
					sorttype : "int"
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
				}, {
					name : 'fsStartplace',
					index : 'fsStartplace',
					width : 50,
					editable : true,
					sorttype : "int",
					hidden : true
				}, {
					name : 'regionname',
					index : 'regionname',
					width : 50,
					editable : true,
					sorttype : "int",
					hidden : true
				}, {
					name : 'fdPrice',
					index : 'fdPrice',
					width : 60,
					editable : true,
					sorttype : "int"
				}, {
					name : 'fsSummary',
					index : 'fsSummary',
					width : 100,
					editable : true,
					sorttype : "int",
					hidden : true
				}, {
					name : 'fdTotalfee',
					index : 'fdTotalfee',
					width : 50,
					editable : true,
					sorttype : "int"
				}, {
					name : 'fdPaidamt',
					index : 'fdPaidamt',
					width : 50,
					editable : true,
					sorttype : "int"
				}, {
					name : 'fsRemark',
					index : 'fsRemark',
					width : 100,
					editable : true,
					sorttype : "char",
					hidden : true
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
						return '-1';
					}
				}, {
					name : 'fsDac',
					index : 'fsDac',
					width : 100,
					editable : true,
					sorttype : "char",
					hidden : true
				}, {
					name : 'fcSchedule',
					index : 'fcSchedule',
					width : 100,
					editable : true,
					sorttype : "char",
					hidden : true
				}, {
					name : 'fcRessnapshot',
					index : 'fcRessnapshot',
					width : 100,
					editable : true,
					sorttype : "char",
					hidden : true
				}, {
					name : 'fiVisitornum',
					index : 'fiVisitornum',
					width : 100,
					editable : true,
					sorttype : "char",
					hidden : true
				}, {
					name : 'fdInsuerprice',
					index : 'fdInsuerprice',
					width : 100,
					editable : true,
					sorttype : "char",
					hidden : true
				}],
				/**
				 * modify end
				 */

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

				editurl : "/orderlist/save.htm",
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
					var frameSrc = "/jsp/orderlist/add.jsp";
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
