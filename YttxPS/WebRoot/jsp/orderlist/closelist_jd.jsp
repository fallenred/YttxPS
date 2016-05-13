<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
<form id="form_jd">
	<table class="table table-bordered">
		<thead>
			<tr>
				<th colspan="8">房费</td>
			</tr>
		</thead>
		<tbody class="jd_tbody">
			<tr class="width-13">
				<td>酒店名称</td>
				<td>房间类型</td>
				<td>入住日期</td>
				<td>房间单价</td>
				<td>房间数量</td>
				<td>小计</td>
				<td>备注</td>
				<td>操作</td>
			</tr>
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
					<td><input type="text" value="${reslist.unitprice }"  class="jdPrice isFloatGteZero required number input-text" name="body.costdetails.accomadation.reslist[${status.index }].unitprice"></td>
					<td><input type="text" value="${reslist.number }"  class="jdCount isFloatGteZero required input-text" name="body.costdetails.accomadation.reslist[${status.index }].number"></td>
					<td><input type="text" value="${reslist.totalprice }" readonly="readonly" class="jdSum input-text" name="body.costdetails.accomadation.reslist[${status.index }].totalprice"></td>
					<td><input type="text" value="${reslist.remark }" class="jdRemark input-text" name="body.costdetails.accomadation.reslist[${status.index }].remark"></td>
					<td><a style="cursor:pointer;" class="deleteJd remove_tr">删除</a></td>
				</tr>
			</c:forEach>
			<tr class="jd_end">
				<td>总计<input type="hidden" id="jd_index" value="${fn:length(content.costdetails.accomadation.reslist)}"/></td>
				<td colspan="6" style="text-align: left;">
					<input type="text" readonly="readonly" value="${content.costdetails.accomadation.total }" style="width:100%;text-align: left;" class="jdTotal expTotal" id="jdTotal" name="body.costdetails.accomadation.total">
				</td>
				<td><button id="addJd" type="button" class="btn btn-success btn-xs btn_add">新增</button></td>
			</tr>
		</tbody>
	</table>
</form>
<script id="tr-jdlist" type="text/x-handlebars-template">
	<tr class="width-14">
		<td><select style="width:160px;text-align:center" class="jd" name="body.costdetails.accomadation.reslist[{{index}}].accomno"></select>
			<input type="hidden" name="body.costdetails.accomadation.reslist[{{index}}].name" value=""/> </td>
		<td><select style="width:140px;text-align:center"  class="jdType" name="body.costdetails.accomadation.reslist[{{index}}].roomtypeno"></select>
			<input type="hidden" name="body.costdetails.accomadation.reslist[{{index}}].roomtype" value=""/></td>
		<td><input type="text" class="form-control datetimepicker" data-date-format="yyyy-mm-dd" placeholder="入住日期" class="jdDate" name="body.costdetails.accomadation.reslist[{{index}}].time"/></td>
		<td><input type="text" value="{{jdPrice}}" class="jdPrice isFloatGteZero number input-text" name="body.costdetails.accomadation.reslist[{{index}}].unitprice"></td>
		<td><input type="text" value="{{jdCount}}" class="jdCount isFloatGteZero digits input-text" name="body.costdetails.accomadation.reslist[{{index}}].number"></td>
		<td><input type="text" value="{{jdSum}}" class="jdSum input-text" readonly="readonly" name="body.costdetails.accomadation.reslist[{{index}}].totalprice"></td>
		<td><input type="text" value="{{jdRemark}}" class="jdRemark input-text" name="body.costdetails.accomadation.reslist[{{index}}].remark"></td>
		<td><a style="cursor:pointer;" class="deleteJd">删除</a></td>
	</tr>
</script>
