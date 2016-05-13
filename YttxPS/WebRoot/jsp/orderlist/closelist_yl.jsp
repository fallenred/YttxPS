<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script src="/js/chosen.jquery.min.js"></script>
<script src="/js/bus/orderlist/closelist_yl.js"></script>
<style>
.width-13 th{
	width:13%;
}
.width-13 td{
	width:13%;
}
</style>
<form id="form_yl">
	<table class="table table-bordered">
		<thead>
			<tr>
				<th colspan="9">自费项目</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>自费项目</td>
				<td>消费日期</td>
				<td>参与人数</td>
				<td>收取金额</td>
				<td>成本</td>
				<td>返佣比%</td>
				<td>利润</td>
				<td>备注</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${content.incomedetails.entertainment.reslist }"
				var="reslist" varStatus="status">
				<tr class="width-13">
					<td><select style="width:150px;text-align:center" class="yl" name="body.incomedetails.entertainment.reslist[${status.index }].resno"><option value="${reslist.resno }">${reslist.name }</option></select>
						<input type="hidden" name="body.incomedetails.entertainment.reslist[${status.index }].name" value="${reslist.name }"/> </td>
					<td><input type="text" value="${reslist.time }" class="form-control datetimepicker ylDate" data-date-format="yyyy-mm-dd" placeholder="消费日期" name="body.incomedetails.entertainment.reslist[${status.index }].time" class="ylDate"/></td>
					<td><input type="text" value="${reslist.people }" class="ylCount isFloatGteZero digits input-text" name="body.incomedetails.entertainment.reslist[${status.index }].people"></td>
					<td><input type="text" value="${reslist.consumption }" name="body.incomedetails.entertainment.reslist[${status.index }].consumption" class="ylFee isFloatGteZero number input-text"></td>
					<td><input type="text" value="${reslist.cost }" name="body.incomedetails.entertainment.reslist[${status.index }].cost" class="ylCost isFloatGteZero number input-text"></td>
					<td><input type="text" value="${reslist.proportion }" name="body.incomedetails.entertainment.reslist[${status.index }].proportion" class="proportion input-text"></td>
					<td><input type="text" value="${reslist.totalprofit }" readonly="readonly" name="body.incomedetails.entertainment.reslist[${status.index }].totalprofit" class="ylProfit input-text"></td>
					<td><input type="text" value="${reslist.remark }" class="ylRemark input-text" name="body.incomedetails.entertainment.reslist[${status.index }].remark"></td>
					<td><a style="cursor:pointer;" class="deleteYl remove_tr">删除</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td>总计<input type="hidden" id="yl_index" value="${fn:length(content.incomedetails.entertainment.reslist)}"/></td>
				<td colspan="7" style="text-align: left;">
					<input type="text" readonly="readonly" value="${content.incomedetails.entertainment.total }" style="width:100%;text-align: left;" name="body.incomedetails.entertainment.total" class="ylTotal incTotal" id="ylTotal">
				</td>
				<td><button id="addYl" type="button" class="btn btn-success btn-xs btn_add">新增</button></td>
			</tr>
		</tbody>
	</table>
</form>
<script id="tr-yllist" type="text/x-handlebars-template">
	<tr class="width-13">
		<td><select style="width:150px;text-align:center" class="yl" name="body.incomedetails.entertainment.reslist[{{index}}].resno"></select>
			<input type="hidden" name="body.incomedetails.entertainment.reslist[{{index}}].name" value="${reslist.name }"/> </td>
		<td><input type="text" value="${reslist.time }" class="form-control datetimepicker ylDate" data-date-format="yyyy-mm-dd" placeholder="消费日期" name="body.incomedetails.entertainment.reslist[{{index}}].time"/></td>
		<td><input type="text" value="{{ylConut}}" name="body.incomedetails.entertainment.reslist[{{index}}].people" class="ylCount isFloatGteZero digits input-text"></td>
		<td><input type="text" value="{{ylFee}}" name="body.incomedetails.entertainment.reslist[{{index}}].consumption" class="ylFee isFloatGteZero number input-text"></td>
		<td><input type="text" value="{{ylCost}}" name="body.incomedetails.entertainment.reslist[{{index}}].cost" class="ylCost isFloatGteZero number input-text"></td>
		<td><input type="text" value="{{proportion}}" name="body.incomedetails.entertainment.reslist[{{index}}].proportion" class="proportion input-text"></td>
		<td><input type="text" value="{{ylProfit}}" readonly="readonly" name="body.incomedetails.entertainment.reslist[{{index}}].totalprofit" class="ylProfit input-text"></td>
		<td><input type="text" value="{{ylRemark}}" name="body.incomedetails.entertainment.reslist[{{index}}].remark" class="ylRemark input-text"></td>
		<td><a style="cursor:pointer;" class="deleteYl">删除</a></td>
	</tr>
</script>

