<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
	.input-text{
		width:100%;
		text-align:center;
	}
	.table tbody tr td { 
		vertical-align : middle; 
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
							<label class="col-sm-3 control-label" style="text-align: right;">总支出：</label>
							<label class="col-sm-3 control-label no-padding-right">${content.expenditure }</label> 
							<label class="col-sm-2 control-label" style="text-align: right;">总收入：</label> 
							<label class="col-sm-3 control-label no-padding-right">${content.income}</label>
						</div>
						<div class="row">
							<label class="col-sm-3 control-label" style="text-align: right;">总人数：</label>
							<label class="col-sm-3"> <label id="lab_adult">${adult }</label><label>
									-</label> <label id="lab_children">${children }</label><label style="height: 20px;">/2-</label>
								<label id="lab_fullguide">${fullguide }</label>&nbsp;<label>人</label>
							</label>
						</div>
					</div>
				</div>
				<hr/>

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
				<div id="message" class="alert alert-warning"></div>
				<div class="modal-footer">
					<button type="button" onclick="Javascript:history.go(-1)" class="btn btn-default btn-sm"
						data-dismiss="modal">返回</button>
					<c:if test="${fStatement.stat == -10 }">
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
	if($("#stat").val() == '-1' || $("#stat").val() == '1' || $("#stat").val() == '2'){
		$(".btn_add").remove();
		$(".remove_tr").remove();
	}
	$('.datetimepicker').datetimepicker({
		language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
		autoclose: true,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
	});
})
$("#zancun").on("click", function () {
	var data = $("#form_cf").serialize() + "&" +
	   $("#form_cl").serialize() + "&" +
	   $("#form_jd").serialize() + "&" +
	   $("#form_mp").serialize() + "&" +
	   $("#form_yl").serialize() + "&" +
	   $("#form_qtsr").serialize() + "&" +
	   $("#form_qtzc").serialize() + "&orderid=" + $("#fsNo").val();
	//校验
	var isValid = $("#form_cf").valid() &&
		  $("#form_cl").valid() &&
		  $("#form_jd").valid() &&
		  $("#form_mp").valid() &&
		  $("#form_yl").valid() &&
		  $("#form_qtsr").valid() &&
		  $("#form_qtzc").valid();
	if (isValid) {
		$.post("/cOrder/saveCloselist.htm",
				data,
			function(data){
				var json = eval("("+data+")");
				if(json.result == "ok") {
					$("#message").text("暂存成功");
					$("#message").show();
					return true;
				}
				else {
					$("#message").text("暂存失败:" + json.message );
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
$("#confirm").on("click", function () {
	var data = $("#form_cf").serialize() + "&" +
	   $("#form_cl").serialize() + "&" +
	   $("#form_jd").serialize() + "&" +
	   $("#form_mp").serialize() + "&" +
	   $("#form_yl").serialize() + "&" +
	   $("#form_qtsr").serialize() + "&" +
	   $("#form_qtzc").serialize() + "&orderid=" + $("#fsNo").val() + "&stat=0";
	//校验
	var isValid = $("#form_cf").valid() &&
		  $("#form_cl").valid() &&
		  $("#form_jd").valid() &&
		  $("#form_mp").valid() &&
		  $("#form_yl").valid() &&
		  $("#form_qtsr").valid() &&
		  $("#form_qtzc").valid();
	if (isValid) {
		$.post("/cOrder/saveCloselist.htm",
				data,
			function(data){
				var json = eval("("+data+")");
				if(json.result == "ok") {
					$("#message").text("暂存成功");
					$("#message").show();
					return true;
				}
				else {
					$("#message").text("暂存失败:" + json.message );
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
</script>

