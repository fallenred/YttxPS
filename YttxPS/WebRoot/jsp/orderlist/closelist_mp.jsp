<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="/js/chosen.jquery.min.js"></script>
<script src="/js/bus/orderlist/closelist_mp.js"></script>
<style>
.width td{
	width:14%;
}
.width th{
	width:14%;
}
</style>
<table class="table table-bordered">
	<thead>
		<tr>
			<th colspan="7">门票</th>
		</tr>
		<tr class="width-14">
			<th>门票名称</th>
			<th>门票类型</th>
			<th>门票单价</th>
			<th>门票数量</th>
			<th>小计</th>
			<th>备注</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${content.costdetails.ticket.reslist }"
			var="reslist" varStatus="status">
			<tr class="width-14">
				<td><select style="width:160px;text-align:center" class="mp" name="body.costdetails.ticket.reslist[${status.index }].resno"><option value="${reslist.resno }">${reslist.name }</option></select>
					<input type="hidden" name="body.costdetails.ticket.reslist[${status.index }].name" value="${reslist.name }" /> </td>
				<td><select style="width:100%;text-align:center" class="mpType" name="body.costdetails.ticket.reslist[${status.index }].type"><option value="${reslist.type }">${reslist.typename }</option></select>
					<input type="hidden" name="body.costdetails.ticket.reslist[${status.index }].typename" value="${reslist.typename }" /></td>
				<td><input type="text" value="${reslist.unitprice }" style="width:100%;text-align:center" class="mpPrice isFloatGteZero number" name="body.costdetails.ticket.reslist[${status.index }].unitprice"></td>
				<td><input type="text" value="${reslist.number }" style="width:100%;text-align:center" class="mpCount isFloatGteZero number" name="body.costdetails.ticket.reslist[${status.index }].number"></td>
				<td><input type="text" value="${reslist.totalprice }" style="width:100%;text-align:center" readonly="readonly" class="mpSum" name="body.costdetails.ticket.reslist[${status.index }].totalprice"></td>
				<td><input type="text" value="${reslist.remark }" style="width:100%;text-align:center" class="mpRemark" name="body.costdetails.ticket.reslist[${status.index }].remark"></td>
				<td><a style="cursor:pointer;" class="deleteMp">删除</a></td>
			</tr>
		</c:forEach>
		<tr>
			<td>总计<input type="hidden" id="mp_index" value="0"/></td>
			<td colspan="5" style="text-align: left;"><input type="text" readonly="readonly" value="" style="width:100%;" id="mpTotal" name="mpTotal"></td>
			<td><button id="addMp" type="button" class="btn btn-success btn-xs">新增</button></td>
		</tr>
	</tbody>
</table>
<script id="tr-mplist" type="text/x-handlebars-template">
	<tr class="width-14">
		<td><select style="width:160px;text-align:center" class="mp" name="body.costdetails.ticket.reslist[${status.index }].resno"></select>
			<input type="hidden" name="body.costdetails.ticket.reslist[${status.index }].name" value="${reslist.name }"/> </td>
		<td><select style="width:100%;text-align:center" class="mpType" name="body.costdetails.ticket.reslist[${status.index }].type"></select>
			<input type="hidden" name="body.costdetails.ticket.reslist[${status.index }].typename" value="${reslist.typename }"/></td>
		<td><input type="text" value="{{mpPrice}}" style="width:100%;text-align:center" class="mpPrice isFloatGteZero number" name="body.costdetails.ticket.reslist[${status.index }].unitprice"></td>
		<td><input type="text" value="{{mpCount}}" style="width:100%;text-align:center" class="mpCount isFloatGteZero number" name="body.costdetails.ticket.reslist[${status.index }].number"></td>
		<td><input type="text" value="{{mpSum}}" readonly="readonly" style="width:100%;text-align:center" class="mpSum" name="body.costdetails.ticket.reslist[${status.index }].totalprice"></td>
		<td><input type="text" value="{{mpRemark}}" style="width:100%;text-align:center" class="mpRemark" name="body.costdetails.ticket.reslist[${status.index }].remark"></td>
		<td><a style="cursor:pointer;" class="deleteMp">删除</a></td>
	</tr>
</script>
