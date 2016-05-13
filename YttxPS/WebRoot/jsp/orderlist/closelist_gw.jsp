<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script src="/js/bus/orderlist/closelist_gw.js"></script>
<style>
.width-240 {
	width: 240px;
	height: 34px;
}

.width-600 {
	width: 600px;
	height: 34px;
}

.width-50 {
	width: 50px;
}
</style>
<div>
	<table class="table table-bordered">
		<thead>
			<tr>
				<th colspan="12">购物店</th>
			</tr>
		</thead>
		<tbody id="tbody_reslist">
			<tr>
				<td/>
				<td colspan="3">人头返佣</td>
				<td colspan="2">购物明细</td>
				<td colspan="2">返佣明细</td>
				<td colspan="3">合计</td>
				<td></td>
			</tr>
			<tr>
				<td>购物店名称</td>
				<td>人头费</td>
				<td>人数</td>
				<td>返佣</td>
				<td>购物项目</td>
				<td>购物金额</td>
				<td>返佣比例</td>
				<td>返佣金额</td>
				<td>购物合计</td>
				<td>返佣合计</td>
				<td>利润合计</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${content.incomedetails.shop.reslist }"
				var="reslist" varStatus="status">
				<c:forEach items="${reslist.cclist }" var="cclist"
					varStatus="cc_status">
					<tr>
						<c:if test="${cc_status.index == 0 }">
							<td rowspan=${fn:length(reslist.cclist) }><a onClick="getShopInfo('/cOrder/getShopReslist.htm?orderid=${order.no }&resno=${reslist.resno }')">${reslist.name }</a></td>
						</c:if>
						<c:if test="${cc_status.index == 0 }">
							<td rowspan=${fn:length(reslist.cclist) }>${reslist.unitprice }</td>
						</c:if>
						<c:if test="${cc_status.index == 0 }">
							<td rowspan=${fn:length(reslist.cclist) }>${reslist.people }</td>
						</c:if>
						<c:if test="${cc_status.index == 0 }">
							<td rowspan=${fn:length(reslist.cclist) }>${reslist.peopleprofit }</td>
						</c:if>
						<td>${cclist.typename }</td>
						<td>${cclist.consumption }</td>
						<td>${cclist.proportion }%</td>
						<td>${cclist.profit }</td>
						<c:if test="${cc_status.index == 0 }">
							<td rowspan=${fn:length(reslist.cclist) }>${reslist.totalconsp
								}</td>
						</c:if>
						<c:if test="${cc_status.index == 0 }">
							<td rowspan=${fn:length(reslist.cclist) }>${reslist.totalprofit
								}</td>
						</c:if>
						<c:if test="${cc_status.index == 0 }">
							<td rowspan=${fn:length(reslist.cclist) }>${reslist.totalprofit
								+ reslist.peopleprofit }</td>
						</c:if>
						<c:if test="${cc_status.index == 0 }">
							<td rowspan=${fn:length(reslist.cclist) }>
							<a class="remove_tr" onClick="delShopConfirm('/cOrder/delShopReslist.htm?orderid=${order.no }&resno=${reslist.resno }')">删除</a></td>
						</c:if>
					</tr>
				</c:forEach>
			</c:forEach>
			<tr>
				<td>总计收入</td>
				<td colspan="10" style="text-align: left;">
					${content.incomedetails.shop.total }&nbsp;元<input type="hidden" class="total" value="${content.incomedetails.shop.total }"/></td>
				<td><button id="btn_gw_add" class="btn btn-success btn-xs btn_add"
						data-toggle="modal" type="button">新增</button></td>
			</tr>
		</tbody>
	</table>
</div>

<div class="modal fade" id="addModal" tabindex="-1" role="dialog"
	aria-labelledby="addModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="width: 1024px;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
				<h4 class="modal-title" id="addModalLabel">购物信息</h4>
			</div>
			<div class="modal-body" style="height: 900px;">
				<form id="form_gw" class="form-horizontal">
					<div class="row">
						<div class="form-group">
							<label class="col-sm-2 control-label no-padding-right"
								for="resno">购物店</label>
							<div class="col-sm-3">
								<select id="shop" name="reslist[0].resno" class="width-240" style="height: 30px"></select> 
								<input type="hidden" id="resno_gw" name="reslist[0].name" id="shopname" value="" />
							</div>
							<!-- <label class="col-sm-2 control-label no-padding-right"
							for="fsName">导游</label>
						<div class="col-sm-3">
							<select id="gw_guide" class="">
								<option value="">-请选择-</option>
							</select>
						</div> -->
							<label class="col-sm-2 control-label no-padding-right" for="time">进店日期</label>
							<div class="col-sm-3">
								<input type="text" placeholder="进店日期" id="time"
									name="reslist[0].time"
									class="form-control datetimepicker width-240"
									data-date-format="yyyy-mm-dd" readonly="readonly" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group">
							<!-- <label class="col-sm-2 control-label no-padding-right"
							for="fsName">进店日期</label>
						<div class="col-sm-3">
							<input type="text" placeholder="进店日期" name="shoppingDate"
								class="form-control datetimepicker width-240"
								data-date-format="yyyy-mm-dd" readonly="readonly" />
						</div> -->
							<label class="col-sm-2 control-label no-padding-right"
								for="fsName">进店人数</label>
							<div class="col-sm-5">
								<table>
									<tr>
										<td style="width: 110px;"><input type="text" id="people" name="reslist[0].people"
											class="form-control isFloatGteZero digits required peopleprofit"></td>
										<td style="width: 50px; text-align: right;">单价</td>
										<td style="width: 110px;"><input type="text"
											id="unitprice" name="reslist[0].unitprice"
											class="form-control isFloatGteZero digits required peopleprofit"
											style="margin-left: 5px;" /></td>
										<td style="width: 70px; text-align: right;">人头费</td>
										<td style="width: 110px;"><input type="text"
											name="reslist[0].peopleprofit" id="peopleprofit" value=""
											readonly="readonly"
											class="form-control isFloatGteZero digits required"
											style="margin-left: 5px;" /></td>
									</tr>
								</table>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group">
							<label class="col-sm-2 control-label no-padding-right"
								for="fsName">备注</label>
							<div class="col-sm-8">
								<input type="text" id="res_remark" name="reslist[0].remark"
									class="form-control width-600" />
							</div>
						</div>
					</div>
					</form>
					<hr>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">购物项目</h3>
						</div>
						<div class="panel-body">
							<form id="gw" class="form-horizontal">
							<div class="row">
								<div class="form-group">
									<label class="col-sm-2 control-label no-padding-right"
										for="typeno">项目</label>
									<div class="col-sm-3">
										<select id="typeno" class="width-240" style="height: 30px">
										</select>
									</div>
									<label class="col-sm-2 control-label no-padding-right"
										for="consumption">购物金额</label>
									<div class="col-sm-3">
										<input type="text" id="consumption"
											class="form-control isFloatGteZero digits required width-240 profit" />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label class="col-sm-2 control-label no-padding-right"
										for="proportion">返佣比%</label>
									<div class="col-sm-3">
										<input type="text" id="proportion"
											class="form-control isFloatGteZero digits required width-240 profit" />
									</div>
									<label class="col-sm-2 control-label no-padding-right"
										for="profit">返佣金额</label>
									<div class="col-sm-3">
										<input type="text" id="profit" readonly="readonly"
											class="form-control isFloatGteZero required width-240" />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label class="col-sm-2 control-label no-padding-right"
										for="cc_remark">项目备注</label>
									<div class="col-sm-8">
										<input type="text" id="cc_remark"
											class="form-control width-600" />
									</div>
									<div class="col-sm-1">
										<button type="button"
											class="btn btn_cclist_add btn-sm btn-success">添加</button>
									</div>
								</div>
							</div>
							</form>
							<input type="hidden" id="gwxm_index" value="0" />
							<form id="form_gwmx" class="form-horizontal">
							<table class="table table-bordered">
								<thead>
									<tr>
										<th>购物店</th>
										<th>项目</th>
										<th>购物金额</th>
										<th>返佣比例</th>
										<th>返佣金额</th>
										<th>备注</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody class="cc_tbody">
									<tr id="total_cclist">
										<td>合计</td>
										<td></td>
										<td><label id="totalconsp">0</label><input type="hidden"
											id="input_totalconsp" name="reslist[0].totalconsp" /></td>
										<td></td>
										<td><label id="totalprofit">0</label><input type="hidden"
											id="input_totalprofit" name="reslist[0].totalprofit" /></td>
										<td colspan="2"></td>
									</tr>
								</tbody>
							</table>
							</form>
						</div>
					</div>
					<div id="message_gw" class="alert alert-warning"></div>
					<div class="modal-footer">
						<button type="button" class="btn btn-sm btn-default"
							data-dismiss="modal">关闭</button>
						<button id="submit_gw" type="button"
							class="btn btn-sm btn-primary">确定</button>
						<input type="hidden" id="gw_index" value="0" />
					</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
</div>

	<script type="text/javascript">
		//编辑链接
		Handlebars.registerHelper('handle_edit_a',function(value, resno, resname, array) {
			if(value == 0){
				orderid = $("#fsNo").val();
				return '<td rowspan=' + array.length + '>'+ 
				'<a onClick="getShopInfo(\'/cOrder/getShopReslist.htm?orderid='+orderid+'&resno='+resno+'\')">'+resname+'</a>'
				+'</td>';
			}
		});
		//td-rowspan
		Handlebars.registerHelper('isRowspan',function(value, content, array) {
			if(value == 0){
				return '<td rowspan=' + array.length + '>'+ content +'</td>';
			}
		});
		//总利润
		Handlebars.registerHelper('handleProfits',function(value, totalprofit, peopleprofit, array) {
			if(value == 0){
				return '<td rowspan=' + array.length + '>'+ (parseFloat(totalprofit) + parseFloat(peopleprofit)) +'</td>';
			}
		});
		//删除链接
		Handlebars.registerHelper('handle_a_del',function(value, resno, array) {
			if(value == 0){
				orderid = $("#fsNo").val();
				return '<td rowspan=' + array.length + '>'+ 
				'<a onClick="delShopConfirm(\'/cOrder/delShopReslist.htm?orderid='+orderid+'&resno='+resno+'\')">删除</a>'
				+'</td>';
			}
		});
		//编辑购物信息
		function getShopInfo(url) {
			$.post(url, function(data) {
				var json = eval("(" + data + ")");
				if (json.result == "ok") {
					$(".tr_cclist").remove();
					$("#gwxm_index").val(json.reslist.cclist.length);
					$("#message_gw").hide();
					$("#shop").val(json.reslist.resno);//购物店
					$("#resno_gw").val($("#shop").find("option:selected").text());
					$("#shop").next().find("span").html(json.reslist.name);
					$("#time").val(json.reslist.time);//进店日期
					$("#people").val(json.reslist.people);//进店人数
					$("#unitprice").val(json.reslist.unitprice);//单价
					$("#peopleprofit").val(json.reslist.peopleprofit);//人头费总价
					$("#res_remark").val(json.reslist.remark);//备注
					$("#totalconsp").html(json.reslist.totalconsp);//打单总金额
					$("#totalprofit").html(json.reslist.totalprofit);//打单总利润
					$("#gw").find("input").val('');
					var template = Handlebars.compile($("#tr-cclist1").html());
					$("#total_cclist").before(template(json.reslist));
					$("label.error").remove();
					$('#addModal').modal('show');
				}
			});
		} 
		//删除购物信息确认
		function delShopConfirm(url) {
			if (confirm('确定要删除该内容吗?')) {
				$.post(url, function(data) {
					var json = eval("(" + data + ")");
					if (json.result == "ok") {
						var template = Handlebars.compile($("#tr-reslist").html());
						$("#tbody_reslist").html(template(json.shopInfo));
						return true;
					}
				});
			}
		}
	</script>
	<script id="tr-cclist" type="text/x-handlebars-template">
	{{#each cclist}}
	  <tr class="tr_cclist">
         <td>{{../name}}</td>
         <td>{{typename}}</td>
         <td>{{consumption}}</td>
         <td>{{proportion}}</td>
         <td>{{profit}}</td>
         <td>{{remark}}</td>
         <td>
			<a style="cursor:pointer;" class="cclist_remove">删除</a>
			<input name="reslist[0].cclist[{{index}}].typeno" type="hidden" value="{{typeno}}" placeholder="项目类型编号">
			<input name="reslist[0].cclist[{{index}}].typename" type="hidden" value="{{typename}}" placeholder="项目类型名">
			<input name="reslist[0].cclist[{{index}}].consumption" type="hidden" class="gw_consumption" value="{{consumption}}" placeholder="打单金额">
			<input name="reslist[0].cclist[{{index}}].proportion" type="hidden" value="{{proportion}}" placeholder="返佣比例">
			<input name="reslist[0].cclist[{{index}}].profit" type="hidden" class="gw_profit" value="{{profit}}" placeholder="返佣金额">
			<input name="reslist[0].cclist[{{index}}].remark" type="hidden" value="{{remark}}" placeholder="备注">
		 </td>
      </tr>
	{{/each}}
	</script>
	<script id="tr-cclist1" type="text/x-handlebars-template">
	{{#each cclist}}
	  <tr class="tr_cclist">
         <td>{{../name}}</td>
         <td>{{typename}}</td>
         <td>{{consumption}}</td>
         <td>{{proportion}}</td>
         <td>{{profit}}</td>
         <td>{{remark}}</td>
         <td>
			<a style="cursor:pointer;" class="cclist_remove">删除</a>
			<input name="reslist[0].cclist[{{@index}}].typeno" type="hidden" value="{{typeno}}" placeholder="项目类型编号">
			<input name="reslist[0].cclist[{{@index}}].typename" type="hidden" value="{{typename}}" placeholder="项目类型名">
			<input name="reslist[0].cclist[{{@index}}].consumption" type="hidden" class="gw_consumption" value="{{consumption}}" placeholder="打单金额">
			<input name="reslist[0].cclist[{{@index}}].proportion" type="hidden" value="{{proportion}}" placeholder="返佣比例">
			<input name="reslist[0].cclist[{{@index}}].profit" type="hidden" class="gw_profit" value="{{profit}}" placeholder="返佣金额">
			<input name="reslist[0].cclist[{{@index}}].remark" type="hidden" value="{{remark}}" placeholder="备注">
		 </td>
      </tr>
	{{/each}}
	</script>
	<script id="tr-reslist" type="text/x-handlebars-template">
			<tr>
				<td />
				<td colspan="3">人头返佣</td>
				<td colspan="2">购物明细</td>
				<td colspan="2">返佣明细</td>
				<td colspan="3">合计</td>
				<td></td>
			</tr>
			<tr>
				<td>购物店名称</td>
				<td>人头费</td>
				<td>人数</td>
				<td>返佣</td>
				<td>购物项目</td>
				<td>购物金额</td>
				<td>返佣比例</td>
				<td>返佣金额</td>
				<td>购物合计</td>
				<td>返佣合计</td>
				<td>利润合计</td>
				<td>操作</td>
			</tr>
			{{#each reslist}}
				{{#each cclist}}
				<tr>
					{{#handle_edit_a @index ../resno ../name ../cclist}}{{/handle_edit_a}}
					{{#isRowspan @index ../unitprice ../cclist}}{{/isRowspan}}
					{{#isRowspan @index ../people ../cclist}}{{/isRowspan}}
					{{#isRowspan @index ../peopleprofit ../cclist}}{{/isRowspan}}
					<td>{{typename }}</td>
					<td>{{consumption }}</td>
					<td>{{proportion }}%</td>
					<td>{{profit }}</td>
					{{#isRowspan @index ../totalconsp ../cclist}}{{/isRowspan}}
					{{#isRowspan @index ../totalprofit ../cclist}}{{/isRowspan}}
					{{#handleProfits @index ../totalprofit ../peopleprofit ../cclist}}{{/handleProfits}}
					{{#handle_a_del @index ../resno ../cclist}}{{/handle_a_del}}
				</tr>
				{{/each}}
			{{/each}}
			<tr>
				<td>总计收入</td>
				<td colspan="10" style="text-align: left;">
					{{total }}&nbsp;元<input type="hidden" class="incTotal" value="{{total }}"/></td>
				<td><button id="btn_gw_add" class="btn btn-success btn-xs btn_add"
						data-toggle="modal" type="button">新增</button></td>
			</tr>
</script>