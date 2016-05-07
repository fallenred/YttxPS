<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
				<th colspan="9">购物店</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
				</th>
				<td colspan="3">人头返佣</td>
				<!-- <td>购物金额</td>
				<td>返佣明细</td> -->
				<td colspan="3"></td>
			</tr>
			<tr>
				<td>购物店名称
				</th>
				<td>人头费</td>
				<td>人数</td>
				<td>返佣</td>
				<td>打单金额</td>
				<td>利润</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${content.incomedetails.shop.reslist }"
				var="reslist" varStatus="status">
				<tr>
					<td><a href="#" class="btn_gw_add">${reslist.name }</a> <input
						type="hidden"
						name="body.incomedetails.shop.reslist[${status.index }].resno"
						value="${reslist.resno }" /> <input type="hidden"
						name="body.incomedetails.shop.reslist[${status.index }].time"
						value="${reslist.time }" /> <input type="hidden"
						name="body.incomedetails.shop.reslist[${status.index }].name"
						value="${reslist.name }" /> <input type="hidden"
						name="body.incomedetails.shop.reslist[${status.index }].unitprice"
						value="${reslist.unitprice }" /> <input type="hidden"
						name="body.incomedetails.shop.reslist[${status.index }].people"
						value="${reslist.people }" /> <input type="hidden"
						name="body.incomedetails.shop.reslist[${status.index }].peopleprofit"
						value="${reslist.peopleprofit }" /> <input type="hidden"
						name="body.incomedetails.shop.reslist[${status.index }].totalconsp"
						value="${reslist.totalconsp }" /> <input type="hidden"
						name="body.incomedetails.shop.reslist[${status.index }].totalprofit"
						value="${reslist.totalprofit }" /> <input type="hidden"
						name="body.incomedetails.shop.reslist[${status.index }].remark"
						value="${reslist.remark }" /> <c:forEach
							items="${reslist.cclist }" var="cclist" varStatus="cc_status">
							<input type="hidden"
								name="body.incomedetails.shop.reslist[${status.index }].cclist[${cc_status.index }].typeno"
								value="${cclist.typeno }" />
							<input type="hidden"
								name="body.incomedetails.shop.reslist[${status.index }].cclist[${cc_status.index }].typename"
								value="${cclist.typename }" />
							<input type="hidden"
								name="body.incomedetails.shop.reslist[${status.index }].cclist[${cc_status.index }].consumption"
								value="${cclist.consumption }" />
							<input type="hidden"
								name="body.incomedetails.shop.reslist[${status.index }].cclist[${cc_status.index }].proportion"
								value="${cclist.proportion }" />
							<input type="hidden"
								name="body.incomedetails.shop.reslist[${status.index }].cclist[${cc_status.index }].profit"
								value="${cclist.profit }" />
							<input type="hidden"
								name="body.incomedetails.shop.reslist[${status.index }].cclist[${cc_status.index }].remark"
								value="${cclist.remark }" />
						</c:forEach></td>
					<td>${reslist.unitprice }</td>
					<td>${reslist.people }</td>
					<td>${reslist.peopleprofit }</td>
					<td>${reslist.totalconsp }</td>
					<td>${reslist.totalprofit }</td>
					<td><a href="#">删除</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td>总计收入</td>
				<td colspan="5" style="text-align: left;">
					${content.incomedetails.shop.total }&nbsp;元</td>
				<td><button class="btn btn-success btn-xs btn_gw_add"
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
								<select id="shop" name="reslist[0].resno" class="width-240"
									style="height: 30px"></select> <input type="hidden"
									name="reslist[0].name" id="shopname" value="大唐卡林" />
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
										<td style="width: 60px;"><input type="text" id="people"
											name="reslist[0].people"
											class="form-control isFloatGteZero digits required peopleprofit"></td>
										<td style="width: 50px; text-align: right;">单价</td>
										<td style="width: 60px;"><input type="text"
											id="unitprice" name="reslist[0].unitprice"
											class="form-control isFloatGteZero digits required peopleprofit"
											style="margin-left: 5px;" /></td>
										<td style="width: 70px; text-align: right;">人头费</td>
										<td style="width: 60px;"><input type="text"
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
									class="form-control isFloatGteZero digits required width-600" />
							</div>
						</div>
					</div>
					<hr>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">购物项目</h3>
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="form-group">
									<label class="col-sm-2 control-label no-padding-right"
										for="fsName">项目</label>
									<div class="col-sm-3">
										<select id="typeno" class="width-240" style="height: 30px">
										</select>
									</div>
									<label class="col-sm-2 control-label no-padding-right"
										for="fsName">购物金额</label>
									<div class="col-sm-3">
										<input type="text" id="consumption"
											class="form-control isFloatGteZero digits required width-240 profit" />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label class="col-sm-2 control-label no-padding-right"
										for="fsName">返佣比%</label>
									<div class="col-sm-3">
										<input type="text" id="proportion"
											class="form-control isFloatGteZero digits required width-240 profit" />
									</div>
									<label class="col-sm-2 control-label no-padding-right"
										for="fsName">返佣金额</label>
									<div class="col-sm-3">
										<input type="text" id="profit" readonly="readonly"
											class="form-control isFloatGteZero digits required width-240" />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label class="col-sm-2 control-label no-padding-right"
										for="fsName">项目备注</label>
									<div class="col-sm-8">
										<input type="text" id="cc_remark"
											class="form-control isFloatGteZero digits required width-600" />
									</div>
									<div class="col-sm-1">
										<button type="button"
											class="btn btn_cclist_add btn-sm btn-success">添加</button>
										<input type="hidden" id="gwxm_index" value="0" />
									</div>
								</div>
							</div>
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
									<tr>
										<td>合计</td>
										<td></td>
										<td><label id="totalconsp">0</label><input type="hidden" id="input_totalconsp" name="reslist[0].totalconsp"/></td>
										<td></td>
										<td><label id="totalprofit">0</label><input type="hidden" id="input_totalprofit" name="reslist[0].totalprofit"/></td>
										<td colspan="2"></td>
									</tr>
								</tbody>
							</table>
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
				</form>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
</div>
<script id="tr-cclist" type="text/x-handlebars-template">
	<tr>
         <td>{{name}}</td>
         <td>{{typenname}}</td>
         <td>{{consumption}}</td>
         <td>{{proportion}}</td>
         <td>{{profit}}</td>
         <td>{{remark}}</td>
         <td>
			<a style="cursor:pointer;" class="batch_remove">删除</a>
			<input name="reslist[0].cclist[{{index}}].typeno" type="hidden" class="" value="{{typeno}}" placeholder="项目类型编号">
			<input name="reslist[0].cclist[{{index}}].typenname" type="hidden" class="" value="{{typenname}}" placeholder="项目类型名">
			<input name="reslist[0].cclist[{{index}}].consumption" type="hidden" class="gw_consumption" value="{{consumption}}" placeholder="打单金额">
			<input name="reslist[0].cclist[{{index}}].proportion" type="hidden" class="" value="{{proportion}}" placeholder="返佣比例">
			<input name="reslist[0].cclist[{{index}}].profit" type="hidden" class="gw_profit" value="{{profit}}" placeholder="返佣金额">
			<input name="reslist[0].cclist[{{index}}].remark" type="hidden" class="" value="{{remark}}" placeholder="备注">
		 </td>
      </tr>
</script>
<script id="tr-reslist" type="text/x-handlebars-template">
	<td><a href="#">夏尔巴藏寨</a></td>
				<td>50</td>
				<td>19</td>
				<td>950</td>
				<td>7440</td>
				<td>50%</td>
				<td>3348</td>
				<td><a href="#">删除</a></td>
	<tr>
         <td><a href="#">{{name}}</a></td>
         <td>{{unitprice}}</td>
         <td>{{people}}</td>
         <td>{{peopleprofit}}</td>
         <td>{{totalconsumption}}</td>
         <td>{{totalprofit}}</td>
         <td>{{remark}}</td>
        <td>
			<a style="cursor:pointer;" class="batch_remove">删除</a>
		</td>
      </tr>
</script>