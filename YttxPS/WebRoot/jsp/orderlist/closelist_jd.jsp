<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="/js/chosen.jquery.min.js"></script>
<script src="/js/bus/orderlist/closelist_jd.js"></script>
<style>
.width-13 td{
	width:13%;
}
.width-13 th{
	width:13%;
}
</style>
<form id="jdForm">
	<table class="table table-bordered">
		<thead>
			<tr>
				<th colspan="8">房费</th>
			</tr>
			<tr class="width-13">
				<th>酒店名称</th>
				<th>房间类型</th>
				<th>入住日期</th>
				<th>房间单价</th>
				<th>房间数量</th>
				<th>小计</th>
				<th>备注</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody class="jd_tbody">
			<c:forEach items="${content.costdetails.accomadation.reslist }"
				var="reslist" varStatus="status">
				<tr class="width-13">
					<td><select style="width:160px;text-align:center" class="jd" name="body.costdetails.accomadation.reslist[${status.index }].accomno"><option value="${reslist.accomno }">${reslist.name }</option></select>
						<input type="hidden" name="body.costdetails.accomadation.reslist[${status.index }].name" value="${reslist.name }"/> </td>
					<td><select style="width:140px;text-align:center"  class="jdType" name="body.costdetails.accomadation.reslist[${status.index }].roomtypeno">
							<option value="${reslist.roomtypeno }">${reslist.roomtype }</option>
						</select>
						<input type="hidden" name="body.costdetails.accomadation.reslist[${status.index }].roomtype" value="${reslist.roomtype }" /></td>
					<td><input type="text" value="${reslist.time }" class="form-control datetimepicker jdDate input-text" data-date-format="yyyy-mm-dd" placeholder="入住日期" name="body.costdetails.accomadation.reslist[${status.index }].time"/></td>
					<td><input type="text" value="${reslist.unitprice }"  class="jdPrice isFloatGteZero number input-text" name="body.costdetails.accomadation.reslist[${status.index }].unitprice"></td>
					<td><input type="text" value="${reslist.number }"  class="jdCount isFloatGteZero digits input-text" name="body.costdetails.accomadation.reslist[${status.index }].number"></td>
					<td><input type="text" value="${reslist.totalprice }" readonly="readonly" class="jdSum input-text" name="body.costdetails.accomadation.reslist[${status.index }].totalprice"></td>
					<td><input type="text" value="${reslist.remark }" class="jdRemark input-text" name="body.costdetails.accomadation.reslist[${status.index }].remark"></td>
					<td><a style="cursor:pointer;" class="deleteJd">删除</a></td>
				</tr>
			</c:forEach>
			<tr class="jd_end">
				<td>总计<input type="hidden" id="jd_index" value="0"/></td>
				<td colspan="6" style="text-align: left;"><input type="text" readonly="readonly" value="" style="width:100%;" class="jdTotal" id="jdTotal" name="body.costdetails.accomadation.reslist[${status.index }].total"></td>
				<td><button id="addJd" type="button" class="btn btn-success btn-xs">新增</button></td>
			</tr>
		</tbody>
	</table>
</form>
<script id="tr-jdlist" type="text/x-handlebars-template">
	<tr class="width-14">
		<td><select style="width:160px;text-align:center" class="jd" name="body.costdetails.accomadation.reslist[${status.index }].accomno"></select>
			<input type="hidden" name="body.costdetails.accomadation.reslist[${status.index }].name" value=""/> </td>
		<td><select style="width:140px;text-align:center"  class="jdType" name="body.costdetails.accomadation.reslist[${status.index }].roomtypeno"></select>
			<input type="hidden" name="body.costdetails.accomadation.reslist[${status.index }].roomtype" value=""/></td>
		<td><input type="text" class="form-control datetimepicker" data-date-format="yyyy-mm-dd" placeholder="入住日期" class="jdDate" name="body.costdetails.accomadation.reslist[${status.index }].time"/></td>
		<td><input type="text" value="{{jdPrice}}" class="jdPrice isFloatGteZero number input-text" name="body.costdetails.accomadation.reslist[${status.index }].unitprice"></td>
		<td><input type="text" value="{{jdCount}}" class="jdCount isFloatGteZero digits input-text" name="body.costdetails.accomadation.reslist[${status.index }].number"></td>
		<td><input type="text" value="{{jdSum}}" class="jdSum input-text" readonly="readonly" name="body.costdetails.accomadation.reslist[${status.index }].totalprice"></td>
		<td><input type="text" value="{{jdRemark}}" class="jdRemark input-text" name="body.costdetails.accomadation.reslist[${status.index }].remark"></td>
		<td><a style="cursor:pointer;" class="deleteJd">删除</a></td>
	</tr>
</script>
