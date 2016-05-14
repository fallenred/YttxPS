<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
<form id="form_mp">
	<table class="table table-bordered">
		<thead>
			<tr>
				<th colspan="7">门票</td>
			</tr>
		</thead>
		<tbody>
			<tr class="width-14">
				<td>门票名称</td>
				<td>门票类型</td>
				<td>门票单价</td>
				<td>门票数量</td>
				<td>小计</td>
				<td>备注</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${content.costdetails.ticket.reslist }"
				var="reslist" varStatus="status">
				<tr class="width-14">
					<td><select style="width:160px;text-align:center" class="mp" name="body.costdetails.ticket.reslist[${status.index }].resno"><option value="${reslist.resno }">${reslist.name }</option></select>
						<input type="hidden" name="body.costdetails.ticket.reslist[${status.index }].name" value="${reslist.name }" /> </td>
					<td><select style="width:100%;text-align:center" class="mpType" name="body.costdetails.ticket.reslist[${status.index }].type"><option value="${reslist.type }">${reslist.typename }</option></select>
						<input type="hidden" name="body.costdetails.ticket.reslist[${status.index }].typename" value="${reslist.typename }" /></td>
					<td><input type="text" value="${reslist.unitprice }" class="mpPrice isFloatGteZero number required input-text" name="body.costdetails.ticket.reslist[${status.index }].unitprice"></td>
					<td><input type="text" value="${reslist.number }" class="mpCount isFloatGteZero number required input-text" name="body.costdetails.ticket.reslist[${status.index }].number"></td>
					<td><input type="text" value="${reslist.totalprice }" readonly="readonly" class="mpSum input-text" name="body.costdetails.ticket.reslist[${status.index }].totalprice"></td>
					<td><input type="text" value="${reslist.remark }" class="mpRemark input-text" name="body.costdetails.ticket.reslist[${status.index }].remark"></td>
					<td><a style="cursor:pointer;" class="deleteMp remove_tr">删除</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td>总计<input type="hidden" id="mp_index" value="${fn:length(content.costdetails.ticket.reslist)}"/></td>
				<td colspan="5" style="text-align: left;">
					<input type="text" readonly="readonly" value="${content.costdetails.ticket.total }" class="expTotal" style="width:100%;text-align: left;" id="mpTotal" name="body.costdetails.ticket.total">
				</td>
				<td><button id="addMp" type="button" class="btn btn-success btn-xs btn_add">新增</button></td>
			</tr>
		</tbody>
	</table>
</form>
<script id="tr-mplist" type="text/x-handlebars-template">
	<tr class="width-14">
		<td><select style="width:160px;text-align:center" class="mp" name="body.costdetails.ticket.reslist[{{index}}].resno"></select>
			<input type="hidden" name="body.costdetails.ticket.reslist[{{index}}].name" value="${reslist.name }"/> </td>
		<td><select style="width:100%;text-align:center" class="mpType" name="body.costdetails.ticket.reslist[{{index}}].type"></select>
			<input type="hidden" name="body.costdetails.ticket.reslist[{{index}}].typename" value="${reslist.typename }"/></td>
		<td><input type="text" value="{{mpPrice}}" class="mpPrice isFloatGteZero number required input-text" name="body.costdetails.ticket.reslist[{{index}}].unitprice"></td>
		<td><input type="text" value="{{mpCount}}" class="mpCount isFloatGteZero number required input-text" name="body.costdetails.ticket.reslist[{{index}}].number"></td>
		<td><input type="text" value="{{mpSum}}" readonly="readonly" class="mpSum input-text" name="body.costdetails.ticket.reslist[{{index}}].totalprice"></td>
		<td><input type="text" value="{{mpRemark}}" class="mpRemark input-text" name="body.costdetails.ticket.reslist[{{index}}].remark"></td>
		<td><a style="cursor:pointer;" class="deleteMp">删除</a></td>
	</tr>
</script>
