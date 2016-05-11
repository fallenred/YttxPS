<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script src="/js/chosen.jquery.min.js"></script>
<script src="/js/bus/orderlist/closelist_cf.js"></script>
<style>
.width-13 td{
	width:13%;
}
.width-13 th{
	width:13%;
}

</style>
<form id="form_cf">
	<table class="table table-bordered">
		<thead>
			<tr>
				<th colspan="8">餐费</td>
			</tr>
		</thead>
		<tbody>
			<tr class="width-13">
				<td>用餐时间</td>
				<td>菜单名称</td>
				<td>类别</td>
				<td>单价</td>
				<td>数量</td>
				<td>小计</td>
				<td>备注</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${content.costdetails.restaurant.reslist }"
				var="reslist" varStatus="status">
				<tr class="width-13">
					<td><input type="text" value="${reslist.time }" class="form-control datetimepicker cfDate" data-date-format="yyyy-mm-dd" placeholder="用餐日期" name="body.costdetails.restaurant.reslist[${status.index }].time"/></td>
					<td><select style="width:160px;text-align:center" class="cf" name="body.costdetails.restaurant.reslist[${status.index }].resno"><option value="${reslist.resno }">${reslist.name }</option></select>
						<input type="hidden" name="body.costdetails.restaurant.reslist[${status.index }].name" value="${reslist.name }" /> </td>
					<td><select style="width:100px;text-align:center"  class="cfType" name="body.costdetails.restaurant.reslist[${status.index }].type"><option value="${reslist.type }"></option></select></td>
					<td><input type="text" value="${reslist.unitprice }" class="cfPrice isFloatGteZero number input-text" name="body.costdetails.restaurant.reslist[${status.index }].unitprice"></td>
					<td><input type="text" value="${reslist.number }" class="cfCount isFloatGteZero digits input-text" name="body.costdetails.restaurant.reslist[${status.index }].number"></td>
					<td><input type="text" value="${reslist.totalprice }" readonly="readonly" class="cfSum input-text" name="body.costdetails.restaurant.reslist[${status.index }].totalprice"></td>
					<td><input type="text" value="${reslist.remark }" class="cfRemark input-text" name="body.costdetails.restaurant.reslist[${status.index }].remark"></td>
					<td><a style="cursor:pointer;" class="deleteCf remove_tr">删除</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td>总计<input type="hidden" id="cf_index" value="${fn:length(content.costdetails.restaurant.reslist)}"/></td>
				<td colspan="6" style="text-align: left;"><input type="text" readonly="readonly" value="" style="width:100%;" class="cfTotal" id="cfTotal" name="body.costdetails.restaurant.total"></td>
				<td><button id="addCf" type="button" class="btn btn-success btn-xs btn_add">新增</button></td>
			</tr>
		</tbody>
	</table>
</form>
<script id="tr-cflist" type="text/x-handlebars-template">
	<tr class="width-14 tr_list">
		<td><input type="text" class="form-control datetimepicker cfDate" data-date-format="yyyy-mm-dd" placeholder="用餐日期" name="body.costdetails.restaurant.reslist[{{index}}].time"/></td>
		<td><select style="width:160px;text-align:center" class="cf" name="body.costdetails.restaurant.reslist[{{index}}].resno"></select>
			<input class="cfName" type="hidden" name="body.costdetails.restaurant.reslist[{{index}}].name" value="${reslist.name }" /> </td>
		<td><select style="width:100px;text-align:center"  class="cfType" name="body.costdetails.restaurant.reslist[{{index}}].type"></select></td>
		<td><input type="text" value="{{cfPrice}}" class="cfPrice isFloatGteZero number input-text" name="body.costdetails.restaurant.reslist[{{index}}].unitprice"></td>
		<td><input type="text" value="{{cfCount}}" class="cfCount isFloatGteZero digits input-text" name="body.costdetails.restaurant.reslist[{{index}}].number"></td>
		<td><input type="text" value="{{cfSum}}" class="cfSum input-text" readonly="readonly" name="body.costdetails.restaurant.reslist[{{index}}].totalprice"></td>
		<td><input type="text" value="{{cfRemark}}" class="cfRemark input-text" name="body.costdetails.restaurant.reslist[{{index}}].remark"></td>
		<td><a style="cursor:pointer;" class="deleteCf">删除</a></td>
	</tr>
</script>

