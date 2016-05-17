<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script src="/js/chosen.jquery.min.js"></script>
<script src="/js/bus/orderlist/closelist_qtsr.js"></script>
<style>
.width-13 td{
	width:13%;
}
.width-13 th{
	width:13%;
}
</style>
<form id="form_qtsr">
	<table class="table table-bordered">
		<thead>
			<tr>
				<th colspan="6">其他收入</th>
			</tr>
		</thead>
		<tbody class="qtsr_tbody">
			<tr>
				<td>项目</td>
				<td>单价</td>
				<td>数量</td>
				<td>小计</td>
				<td>备注</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${content.incomedetails.other.reslist }" var="reslist" varStatus="status">
				<tr>
					<td>
						<select style="width:160px;text-align:center" class="qtsr" name="body.incomedetails.other.reslist[${status.index }].typeno">
							<option value="${reslist.typeno }">${reslist.name }</option>
						</select>
						<input type="hidden" name="body.incomedetails.other.reslist[${status.index }].name" value="${reslist.name }" class="qtsrName" />
					</td>
					<td>
						<input type="text" value="${reslist.unitprice }" class="qtsrPrice required isFloatGteZero number input-text" name="body.incomedetails.other.reslist[${status.index }].unitprice">
					</td>
					<td>
						<input type="text" value="${reslist.number }" class="qtsrCount required isFloatGteZero digits input-text" name="body.incomedetails.other.reslist[${status.index }].number">
					</td>
					<td>
						<input type="text" value="${reslist.totalprice }" class="qtsrSum required input-text isFloatGteZero number" name="body.incomedetails.other.reslist[${status.index }].totalprice">
					</td>
					<td><input type="text" value="${reslist.remark }" class="qtsrRemark input-text" name="body.incomedetails.other.reslist[${status.index }].remark"></td>
					<td><a style="cursor:pointer;" class="deleteQtsr remove_tr">删除</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td>总计<input type="hidden" id="qtsr_index" value="${fn:length(content.incomedetails.other.reslist)}"/></td>
				<td colspan="4" style="text-align: left;">
					<input type="text" value="${content.incomedetails.other.total }" style="width:100%;text-align: left;" class="qtsrTotal incTotal" id="qtsrTotal" name="body.incomedetails.other.total">
				</td>
				<td><button id="addQtsr" type="button" class="btn btn-success btn-xs btn_add">新增</button></td>
			</tr>
		</tbody>
	</table>
</form>
<script id="tr-qtsrlist" type="text/x-handlebars-template">
	<tr class="width-14">
		<td>
			<select style="width:160px;text-align:center" class="qtsr" name="body.incomedetails.other.reslist[{{index}}].typeno"></select>
			<input class="qtsrName" type="hidden" name="body.incomedetails.other.reslist[{{index}}].name" value="${reslist.name }" />
		</td>
		<td><input type="text" value="{{qtsrPrice}}" class="qtsrPrice required isFloatGteZero number input-text" name="body.incomedetails.other.reslist[{{index}}].unitprice"></td>
		<td><input type="text" value="{{qtsrCount}}" class="qtsrCount required isFloatGteZero digits input-text" name="body.incomedetails.other.reslist[{{index}}].number"></td>
		<td><input type="text" value="{{qtsrSum}}" class="qtsrSum required input-text isFloatGteZero number" name="body.incomedetails.other.reslist[{{index}}].totalprice"></td>
		<td><input type="text" value="{{qtsrRemark}}" class="qtsrRemark input-text" name="body.incomedetails.other.reslist[{{index}}].remark"></td>
		<td><a style="cursor:pointer;" class="deleteQtsr">删除</a></td>
	</tr>
</script>