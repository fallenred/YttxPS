<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<table class="table table-bordered">
	<thead>
		<tr>
			<th colspan="8">餐费</th>
		</tr>
		<tr class="width-13">
			<th>用餐时间</th>
			<th>菜单名称</th>
			<th>类别</th>
			<th>单价</th>
			<th>数量</th>
			<th>小计</th>
			<th>备注</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${content.costdetails.restaurant.reslist }"
			var="reslist" varStatus="status">
			<tr class="width-13">
				<td><input type="text" value="${reslist.time }" class="form-control datetimepicker cfDate" data-date-format="yyyy-mm-dd" placeholder="用餐日期" name="body.costdetails.restaurant.reslist[${status.index }].time"/></td>
				<td><select style="width:160px;text-align:center" class="cf" name="body.costdetails.restaurant.reslist[${status.index }].resno"><option value="${reslist.resno }">${reslist.name }</option></select>
					<input type="hidden" name="body.costdetails.restaurant.reslist[${status.index }].name" value="${reslist.name }" /> </td>
				<td><select style="width:100px;text-align:center"  class="cfType" name="body.costdetails.restaurant.reslist[${status.index }].type"><option value="${reslist.type }"></option></select></td>
				<td><input type="text" value="${reslist.unitprice }" style="width:100%;text-align:center" class="cfPrice isFloatGteZero number" name="body.costdetails.restaurant.reslist[${status.index }].unitprice"></td>
				<td><input type="text" value="${reslist.number }" style="width:100%;text-align:center" class="cfCount isFloatGteZero digits" name="body.costdetails.restaurant.reslist[${status.index }].number"></td>
				<td><input type="text" value="${reslist.totalprice }" style="width:100%;text-align:center" readonly="readonly" class="cfSum" name="body.costdetails.restaurant.reslist[${status.index }].totalprice"></td>
				<td><input type="text" value="${reslist.remark }" style="width:100%;text-align:center" class="cfRemark" name="body.costdetails.restaurant.reslist[${status.index }].remark"></td>
				<td><a style="cursor:pointer;" class="deleteCf">删除</a></td>
			</tr>
		</c:forEach>
		<tr>
			<td>总计<input type="hidden" id="cf_index" value="0"/></td>
			<td colspan="6" style="text-align: left;"><input type="text" readonly="readonly" value="" style="width:100%;" class="cfTotal" id="cfTotal" name="cfTotal"></td>
			<td><button id="addCf" type="button" class="btn btn-success btn-xs">新增</button></td>
		</tr>
	</tbody>
</table>
<script id="tr-cflist" type="text/x-handlebars-template">
	<tr class="width-14">
		<td><input type="text" class="form-control datetimepicker" data-date-format="yyyy-mm-dd" placeholder="用餐日期" class="cfDate" name="body.costdetails.restaurant.reslist[${status.index }].time"/></td>
		<td><select style="width:160px;text-align:center" class="cf" name="body.costdetails.restaurant.reslist[${status.index }].resno"></select>
			<input class="cfName" type="hidden" name="body.costdetails.restaurant.reslist[${status.index }].name" value="${reslist.name }" /> </td>
		<td><select style="width:100px;text-align:center"  class="cfType" name="body.costdetails.restaurant.reslist[${status.index }].type"></select></td>
		<td><input type="text" value="{{cfPrice}}" style="width:100%;text-align:center" class="cfPrice isFloatGteZero number" name="body.costdetails.restaurant.reslist[${status.index }].unitprice"></td>
		<td><input type="text" value="{{cfCount}}" style="width:100%;text-align:center" class="cfCount isFloatGteZero digits" name="body.costdetails.restaurant.reslist[${status.index }].number"></td>
		<td><input type="text" value="{{cfSum}}" style="width:100%;text-align:center" class="cfSum" readonly="readonly" name="body.costdetails.restaurant.reslist[${status.index }].totalprice"></td>
		<td><input type="text" value="{{cfRemark}}" style="width:100%;text-align:center" class="cfRemark" name="body.costdetails.restaurant.reslist[${status.index }].remark"></td>
		<td><a style="cursor:pointer;" class="batch_remove">删除</a></td>
	</tr>
</script>

