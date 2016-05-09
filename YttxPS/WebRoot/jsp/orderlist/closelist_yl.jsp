<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<table class="table table-bordered">
	<thead>
		<tr>
			<th colspan="8">自费项目</th>
		</tr>
		<tr>
			<th>自费项目</th>
			<th>消费日期</th>
			<th>参与人数</th>
			<th>收取金额</th>
			<th>成本</th>
			<th>利润</th>
			<th>备注</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${content.incomedetails.entertainment.reslist }"
			var="reslist" varStatus="status">
			<tr class="width-13">
				<td><select style="width:150px;text-align:center" class="yl" name="body.incomedetails.entertainment.reslist[${status.index }].resno"><option value="${reslist.resno }">${reslist.name }</option></select>
					<input type="hidden" name="body.incomedetails.entertainment.reslist[${status.index }].name" value="${reslist.name }"/> </td>
				<td><input type="text" value="${reslist.time }" class="form-control datetimepicker ylDate" data-date-format="yyyy-mm-dd" placeholder="消费日期" name="body.incomedetails.entertainment.reslist[${status.index }].time" class="ylDate"/></td>
				<td><input type="text" value="${reslist.people }" class="ylCount isFloatGteZero digits input-text" name="body.incomedetails.entertainment.reslist[${status.index }].people"></td>
				<td><input type="text" value="${reslist.consumption }" name="body.incomedetails.entertainment.reslist[${status.index }].consumption" class="ylFee isFloatGteZero number input-text"></td>
				<td><input type="text" value="${reslist.cost }" name="body.incomedetails.entertainment.reslist[${status.index }].cost" class="ylCost isFloatGteZero number input-text"></td>
				<td><input type="text" value="${reslist.totalprofit }" readonly="readonly" name="body.incomedetails.entertainment.reslist[${status.index }].totalprofit" class="ylProfit input-text"></td>
				<td><input type="text" value="${reslist.remark }" class="ylRemark input-text" name="body.incomedetails.entertainment.reslist[${status.index }].remark"></td>
				<td><a style="cursor:pointer;" class="deleteYl">删除</a></td>
			</tr>
		</c:forEach>
		<tr>
			<td>总计<input type="hidden" id="yl_index" value="0"/></td>
			<td colspan="6" style="text-align: left;"><input type="text" readonly="readonly" value="" style="width:100%;" name="body.incomedetails.entertainment.reslist[${status.index }].total" class="ylTotal" id="ylTotal"></td>
			<td><button id="addYl" type="button" class="btn btn-success btn-xs">新增</button></td>
		</tr>
	</tbody>
</table>
<script id="tr-yllist" type="text/x-handlebars-template">
	<tr class="width-13">
		<td><select style="width:150px;text-align:center" class="yl" name="resultList[0].yllist[{{index}}].resno"></select>
			<input type="hidden" name="body.incomedetails.entertainment.reslist[${status.index }].name" value="${reslist.name }"/> </td>
		<td><input type="text" value="${reslist.time }" class="form-control datetimepicker ylDate" data-date-format="yyyy-mm-dd" placeholder="消费日期" name="body.incomedetails.entertainment.reslist[${status.index }].time"/></td>
		<td><input type="text" value="{{ylConut}}" name="resultList[0].yllist[{{index}}].people" class="ylCount isFloatGteZero digits input-text"></td>
		<td><input type="text" value="{{ylFee}}" name="resultList[0].yllist[{{index}}].consumption" class="ylFee isFloatGteZero number input-text"></td>
		<td><input type="text" value="{{ylCost}}" name="resultList[0].yllist[{{index}}].cost" class="ylCost isFloatGteZero number input-text"></td>
		<td><input type="text" value="{{ylProfit}}" readonly="readonly" name="resultList[0].yllist[{{index}}].totalprofit" class="ylProfit input-text"></td>
		<td><input type="text" value="{{ylRemark}}" name="resultList[0].yllist[{{index}}].remark" class="ylRemark input-text"></td>
		<td><a style="cursor:pointer;" class="deleteYl">删除</a></td>
	</tr>
</script>

