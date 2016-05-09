<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="/js/chosen.jquery.min.js"></script>
<script src="/js/bus/orderlist/closelist_cl.js"></script>

<style>
.width-15 td{
	width:15%;
}
.width-15 th{
	width:15%;
}
</style>
<table class="table table-bordered">
	<thead>
		<tr>
			<th colspan="6">车费</th>
		</tr>
		<tr class="width-15">
			<th>车型</th>
			<th>车型单价</th>
			<th>辆数</th>
			<th>小计</th>
			<th>备注</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody class="cl_tbody">
		<c:forEach items="${content.costdetails.car.reslist }"
			var="reslist" varStatus="status">
			<tr class="width-15">
				<td><select style="width:160px;text-align:center" class="cl" name="body.costdetails.car.reslist[${status.index }].resno"><option value="${reslist.resno }">${reslist.name }</option></select>
					<input type="hidden" name="body.costdetails.car.reslist[${status.index }].name" value="${reslist.name }" class="clName" /> </td>
				<td><input type="text" value="${reslist.unitprice }"  class="clPrice isFloatGteZero number input-text" name="body.costdetails.car.reslist[${status.index }].unitprice"></td>
				<td><input type="text" value="${reslist.number }"  class="clCount isFloatGteZero digits input-text" name="body.costdetails.car.reslist[${status.index }].number"></td>
				<td><input type="text" value="${reslist.totalprice }"  readonly="readonly" class="clSum input-text"  name="body.costdetails.car.reslist[${status.index }].totalprice"></td>
				<td><input type="text" value="${reslist.remark }"  class="clRemark input-text" name="body.costdetails.car.reslist[${status.index }].remark"></td>
				<td><a style="cursor:pointer;" class="deleteCl">删除</a></td>
			</tr>
		</c:forEach>
		<tr class="cl_end">
			<td>总计<input type="hidden" id="cl_index" value="0"/></td>
			<td colspan="4" style="text-align: left;"><input type="text" readonly="readonly" value="" style="width:100%;" class="clTotal" id="clTotal" name="body.costdetails.car.total"></td>
			<td><button id="addCl" type="button" class="btn btn-success btn-xs">新增</button></td>
		</tr>
	</tbody>
</table>

<script id="tr-cllist" type="text/x-handlebars-template">
	<tr class="width-15">
		<td><select style="width:160px;text-align:center" class="cl" name="resultList[0].cllist[{{index}}].cl"></select>
			<input type="hidden" name="body.costdetails.car.reslist[${status.index }].name" value=""/> </td>
		<td><input type="text" value="{{clPrice}}"  class="clPrice isFloatGteZero number input-text" name="resultList[0].cllist[{{index}}].clPrice"></td>
		<td><input type="text" value="{{clCount}}" class="clCount isFloatGteZero digits input-text" name="resultList[0].cllist[{{index}}].clCount"></td>
		<td><input type="text" value="{{clSum}}"  readonly="readonly" class="clSum input-text" name="resultList[0].cllist[{{index}}].clSum"></td>
		<td><input type="text" value="{{clRemark}}"  class="clRemark input-text" name="resultList[0].cllist[{{index}}].clRemark"></td>
		<td><a style="cursor:pointer;" class="deleteCl">删除</a></td>
	</tr>
</script>