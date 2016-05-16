<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
input {
	width: 100%;
	text-align: center;
}

.table tbody tr td {
	vertical-align: middle;
}
</style>
<div class="page-content">
	<div class="row">
		<div class="col-xs-12">
			<!-- PAGE CONTENT BEGINS -->

			<!-- <form class="form-horizontal" id="addform"> -->

			<div class="modal-body">
				<!-- 图片TODO： -->
				<div>
					<ul class="ace-thumbnails clearfix">
						<div style="visibility: hidden;">
							<li><a class="cboxElement" data-rel="colorbox"
								href="http://127.0.0.1:81/1.jpg"> <img width="0" height="0"
									src="http://127.0.0.1:81/2.png" alt="0*0">
							</a></li>
						</div>
					</ul>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">订单明细</h3>
					</div>
					<div class="panel-body">
						<input type="hidden" id="fsNo" value="${order.no }">
						<div class="row">
							<label class="col-sm-3 control-label" style="text-align: right;">订单号：</label>
							<label class="col-sm-3 control-label no-padding-right">${order.no }</label> 
							<label class="col-sm-2 control-label" style="text-align: right;">旅行社：</label> 
							<label class="col-sm-3 control-label no-padding-right">${taname }</label>
						</div>
						<div class="row">
							<label class="col-sm-3 control-label" style="text-align: right;">下单员：</label>
							<label class="col-sm-3 control-label no-padding-right">${order.map["subName"] }</label> 
							<label class="col-sm-2 control-label" style="text-align: right;">操作员：</label> 
							<label class="col-sm-3 control-label no-padding-right">${sessionEntity.name}</label>
						</div>
						<div class="row">
							<label class="col-sm-3 control-label" style="text-align: right;">总人数：</label>
							<label class="col-sm-3"> <label id="lab_adult">${adult }</label>
							<label> -</label> <label id="lab_children">${children }</label>
							<label style="height: 20px;">/2-</label> 
							<label id="lab_fullguide">${fullguide }</label>&nbsp;
							<label>人</label>
							</label>
							<label class="col-sm-2 control-label" style="text-align: right;">订单状态：</label>
							<label class="col-sm-3 control-label no-padding-right">${stat}</label>
						</div>
					</div>
				</div>
				<hr />
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">成本明细</h3>
					</div>
					<div class="panel-body">
						<jsp:include page="/jsp/orderlist/closelist_cl.jsp"></jsp:include><!-- 车辆 -->
						<jsp:include page="/jsp/orderlist/closelist_jd.jsp"></jsp:include><!-- 酒店 -->
						<jsp:include page="/jsp/orderlist/closelist_cf.jsp"></jsp:include><!-- 餐费 -->
						<jsp:include page="/jsp/orderlist/closelist_mp.jsp"></jsp:include><!-- 门票 -->
						<jsp:include page="/jsp/orderlist/closelist_qtzc.jsp"></jsp:include><!-- 其他支出 -->
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">收入明细</h3>
					</div>
					<div class="panel-body">
						<jsp:include page="/jsp/orderlist/closelist_gw.jsp" flush="true"></jsp:include><!-- 购物 -->
						<jsp:include page="/jsp/orderlist/closelist_yl.jsp"></jsp:include><!-- 娱乐 -->
						<jsp:include page="/jsp/orderlist/closelist_qtsr.jsp"></jsp:include><!-- 其他收入 -->
					</div>
				</div>
				<div class="panel panel-default">
					<form id="form_total">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th colspan="7">汇总明细</td>
							</tr>
						</thead>
						<tbody class="qtsr_tbody">
							<tr>
								<td>总支出</td>
								<td>总收入</td>
								<td>保底</td>
								<td>客户总支出</td>
								<td>客户总收入</td>
								<td>已付金额</td>
								<td>应付金额</td>
							</tr>
							<tr>
								<td><input type="text" id="expenditure" value="${content.expenditure }" class="form-control number required"></td>
								<td>
									<input type="text" id="totalIncome" name="body.income" value="${content.income}" class="form-control number required">
									<input type="hidden" id="income" value="${content.income - content.minproceeds }"/>
								</td>
								<td><input type="text" id="minproceeds" value="${content.minproceeds }" class="form-control number required total" name="body.minproceeds"></td>
								<td><input type="text" id="customExpenditure" value="${content.expenditure - content.minproceeds}" class="form-control number required total" name="body.custexpend"></td>
								<td>
									<input type="text" id="customIncome" value="${content.custprofit}" class="form-control number required total" name="body.custprofit">
								</td>
								<td>
									<c:choose>  
									   <c:when test="${fStatement.paidAmt == 0 || fStatement.paidAmt == null}">
											<input type="text" id="paidAmt" value="${order.paidAmt }" class="form-control number required total" name="paidAmt"></td>
									   </c:when>
									   <c:otherwise>
											<input type="text" id="paidAmt" value="${fStatement.paidAmt }" class="form-control number required total" name="paidAmt"></td>
									   </c:otherwise>
									</c:choose> 
								<td>
									<input type="text" id="amt" value="${fStatement.amt}" class="form-control number required total" name="amt">
								</td>
							</tr>
						</tbody>
					</table>
					</form>
				</div>
				<div id="message" class="alert alert-warning"></div>
				<div class="modal-footer">
					<button type="button" onclick="Javascript:history.go(-1)"
						class="btn btn-default btn-sm" data-dismiss="modal">返回</button>
					<c:if test="${fStatement.stat == -10 && order.stat == 8}">
						<button id="confirm" type="button" class="btn btn-primary btn-sm">客户待确认</button>
					</c:if>
					<c:if test="${fStatement.stat == -10 ||  fStatement.stat == 0}">
						<button id="zancun" type="button" class="btn btn-primary btn-sm">保存</button>
					</c:if>
				</div>
			</div>
			<input type="hidden" id="stat" value="${fStatement.stat}">
			<!-- </form> -->
			<!-- PAGE CONTENT ENDS -->
		</div>
		<!-- /.col -->
	</div>
	<!-- /.row -->
</div>
<script type="text/javascript">
	jQuery(function($) {
		if ($("#stat").val() == '-1' || $("#stat").val() == '1'
				|| $("#stat").val() == '2') {
			$(".btn_add").remove();
			$(".remove_tr").remove();
		}
		$('.datetimepicker').daterangepicker(
				{
					singleDatePicker : true,
					locale : {
						format : 'YYYY-MM-DD',
						applyLabel : '确定',
						cancelLabel : '取消',
						fromLabel : '起始时间',
						toLabel : '结束时间',
						customRangeLabel : '自定义',
						daysOfWeek : [ '日', '一', '二', '三', '四', '五', '六' ],
						monthNames : [ '一月', '二月', '三月', '四月', '五月', '六月',
								'七月', '八月', '九月', '十月', '十一月', '十二月' ],
						firstDay : 1
					}
				});
	})

	$("#zancun").on("click", function() {
				var data = $("#form_cf").serialize() + "&"
						+ $("#form_cl").serialize() + "&"
						+ $("#form_jd").serialize() + "&"
						+ $("#form_mp").serialize() + "&"
						+ $("#form_yl").serialize() + "&"
						+ $("#form_qtsr").serialize() + "&"
						+ $("#form_qtzc").serialize() + "&"
						+ $("#form_total").serialize() + "&orderid="
						+ $("#fsNo").val();
				//校验
				var isValid = $("#form_cf").valid() && $("#form_cl").valid()
						&& $("#form_jd").valid() && $("#form_mp").valid()
						&& $("#form_yl").valid() && $("#form_qtsr").valid()
						&& $("#form_qtzc").valid() && $("#form_total").valid();
				if (isValid) {
					$.post("/cOrder/saveCloselist.htm", data, function(data) {
						var json = eval("(" + data + ")");
						if (json.result == "ok") {
							$("#message").text("保存成功");
							$("#message").show();
							return true;
						} else {
							$("#message").text("暂存失败:" + json.message);
							$("#message").show();
							return false;
						}
						return false;
					});
				} else {
					$("#message").text("输入字段验证错误，请重新编辑后再保存");
					$("#message").show();
					return false;
				}
			});
	$("#confirm").on("click", function() {
				var data = $("#form_cf").serialize() + "&"
						+ $("#form_cl").serialize() + "&"
						+ $("#form_jd").serialize() + "&"
						+ $("#form_mp").serialize() + "&"
						+ $("#form_yl").serialize() + "&"
						+ $("#form_qtsr").serialize() + "&"
						+ $("#form_qtzc").serialize() + "&"
						+ $("#form_total").serialize() + "&orderid="
						+ $("#fsNo").val() + "&stat=0";
				//校验
				var isValid = $("#form_cf").valid() && $("#form_cl").valid()
						&& $("#form_jd").valid() && $("#form_mp").valid()
						&& $("#form_yl").valid() && $("#form_qtsr").valid()
						&& $("#form_qtzc").valid() && $("#form_total").valid();
				if (isValid) {
					$.post("/cOrder/saveCloselist.htm", data, function(data) {
						var json = eval("(" + data + ")");
						if (json.result == "ok") {
							$("#message").text("保存成功");
							$("#message").show();
							return true;
						} else {
							$("#message").text("保存失败:" + json.message);
							$("#message").show();
							return false;
						}
						return false;
					});
				} else {
					$("#message").text("输入字段验证错误，请重新编辑后再保存");
					$("#message").show();
					return false;
				}
			});
	//总支出
	function getExpenditure(){
		expTotal = 0;
		$(".expTotal").each(function(){
			total = $(this).val();
			if(isNaN(total) || total == '') {
				total = 0;
			}
			expTotal = parseFloat(total) + parseFloat(expTotal);
		});
		$("#expenditure").val(expTotal);
	}
	//总收入
	function getIncome(){
		incTotal = 0;
		minproceeds = parseFloat($("#minproceeds").val());//保底
		$(".incTotal").each(function(){
			total = $(this).val();
			if(isNaN(total) || total == '') {
				total = 0;
			}
			incTotal = parseFloat(total) + parseFloat(incTotal);
		});
		$("#totalIncome").val(incTotal);
		$("#income").val(incTotal - minproceeds);
	}
	/* $(document).on('change key', '.total',function(){
		if(isNaN($(this).val()) || $(this).val() == '') {
			$(this).val(0);
		}
		minproceeds = $("#minproceeds").val();//保底
		expenditure = $("#expenditure").val();//总支出
		totalIncome = $("#totalIncome").val();//总收入
		paidAmt = $("#paidAmt").val();//已付金额
		amt = $("#amt").val();//应付金额
		$("#income").val(totalIncome - minproceeds);//利润 = 总利润 - 保底
		$("#customExpenditure").val(parseFloat(expenditure) - parseFloat(minproceeds));//客户支出 = 总支出 - 保底
		if ($(this).attr("id") != 'customIncome') {
			$("#customIncome").val($("#income").val());//客户利润 = 利润 * 分配系数
		}
		customIncome = $("#customIncome").val();//客户总收入
		customExpenditure = $("#customExpenditure").val();//客户总支出
		$("#amt").val(parseFloat(customExpenditure) - parseFloat(paidAmt) - parseFloat(customIncome));//应付款 = 已付金额 + 客户利润 - 客户支出
	}); */
</script>

