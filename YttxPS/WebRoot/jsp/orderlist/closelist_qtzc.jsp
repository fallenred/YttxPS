<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script src="/js/chosen.jquery.min.js"></script>
<script src="/js/bus/orderlist/closelist_qtzc.js"></script>
<style>
.width-13 td{
	width:13%;
}
.width-13 th{
	width:13%;
}
</style>
<form id="form_qtzc">
	<table class="table table-bordered">
		<thead>
			<tr>
				<th colspan="6">其他支出</th>
			</tr>
		</thead>
		<tbody class="qtzc_tbody">
			<tr>
				<td>其他款项</td>
				<td>费用</td>
				<td>数量</td>
				<td>小计</td>
				<td>备注</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${content.costdetails.other.reslist }" var="reslist" varStatus="status">
				<tr>
					<td>
						<select style="width:160px;text-align:center" class="qtzc" name="body.costdetails.other.reslist[${status.index }].typeno">
							<option value="${reslist.typeno }">${reslist.name }</option>
						</select>
						<input type="hidden" name="body.costdetails.other.reslist[${status.index }].name" value="${reslist.name }" class="qtzcName" />
					</td>
					<td>
						<input type="text" value="${reslist.unitprice }" class="qtzcPrice isFloatGteZero required number input-text" name="body.costdetails.other.reslist[${status.index }].unitprice">
		 			</td>
	 				<td>
	 					<input type="text" value="${reslist.number }" class="qtzcCount isFloatGteZero required digits input-text" name="body.costdetails.other.reslist[${status.index }].number">
					</td>
					<td>
						<input type="text" value="${reslist.totalprice }" class="qtzcSum required input-text" name="body.costdetails.other.reslist[${status.index }].totalprice">
					</td>
					<td><input type="text" value="${reslist.remark }" class="qtzcRemark input-text" name="body.costdetails.other.reslist[${status.index }].remark"></td>
					<td><a style="cursor:pointer;" class="deleteQtzc remove_tr">删除</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td>总计<input type="hidden" id="qtzc_index" value="${fn:length(content.costdetails.other.reslist)}"/></td>
				<td colspan="4" style="text-align: left;">
					<input type="text" value="${content.costdetails.other.total }" style="width:100%;text-align: left;" class="qtzcTotal expTotal" id="qtzcTotal" name="body.costdetails.other.total">
				</td>
				<td><button id="addQtzc" type="button" class="btn btn-success btn-xs btn_add">新增</button></td>
			</tr>
		</tbody>
	</table>
</form>
<script id="tr-qtzclist" type="text/x-handlebars-template">
	<tr class="width-14">
		<td>
			<select style="width:160px;text-align:center" class="qtzc" name="body.costdetails.other.reslist[{{index}}].typeno"></select>
			<input class="qtzcName" type="hidden" name="body.costdetails.other.reslist[{{index}}].name" value="${reslist.name }" />
		</td>
		<td><input type="text" value="{{qtzcPrice}}" class="qtzcPrice required isFloatGteZero number input-text" name="body.costdetails.other.reslist[{{index}}].unitprice"></td>
		<td><input type="text" value="{{qtzcCount}}" class="qtzcCount required isFloatGteZero digits input-text" name="body.costdetails.other.reslist[{{index}}].number"></td>
		<td><input type="text" value="{{qtzcSum}}" class="qtzcSum required input-text" name="body.costdetails.other.reslist[{{index}}].totalprice"></td>
		<td><input type="text" value="{{qtzcRemark}}" class="qtzcRemark input-text" name="body.costdetails.other.reslist[{{index}}].remark"></td>
		<td><a style="cursor:pointer;" class="deleteQtzc">删除</a></td>
	</tr>
</script>
