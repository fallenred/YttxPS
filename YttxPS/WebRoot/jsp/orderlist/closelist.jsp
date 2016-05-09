<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.input-text{
	width:100%;
	text-align:center;
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

					<table class="table">
						<caption></caption>
						<tbody>
							<tr>
								<td>订单号:</td>
								<td style="text-align: left">${orderlist.fsNo }</td>
								<td>旅行社:</td>
								<td style="text-align: left">成都美华假期旅行社广州分公司西南假日</td>
							</tr>
							<tr>
								<td>操作员:</td>
								<td style="text-align: left">杨琼玲</td>
								<td>计调员:</td>
								<td style="text-align: left">${sessionEntity.name}</td>
							</tr>
							<tr>
								<td>总人数:</td>
								<td style="text-align: left">
									<label id="lab_adult"></label><label> -</label>
									<label id="lab_children"></label><label style="height: 20px;">/2-</label>
									<label id="lab_fullguide"></label>&nbsp;<label>人</label>
								</td>
								<td></td>
								<td style="text-align: left"></td>
							</tr>
						</tbody>
					</table>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">成本明细</h3>
						</div>
						<div class="panel-body">
							<jsp:include page="/jsp/orderlist/closelist_cl.jsp"></jsp:include><!-- 车辆 -->
							<jsp:include page="/jsp/orderlist/closelist_jd.jsp"></jsp:include><!-- 酒店 -->
							<jsp:include page="/jsp/orderlist/closelist_mp.jsp"></jsp:include><!-- 门票 -->
							<jsp:include page="/jsp/orderlist/closelist_cf.jsp"></jsp:include><!-- 餐费 -->
							<%-- <jsp:include page="/jsp/orderlist/closelist_qtzc.jsp"></jsp:include><!-- 其他支出 --> --%>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">收入明细</h3>
						</div>
						<div class="panel-body">
							<jsp:include page="/jsp/orderlist/closelist_gw.jsp" flush="true"></jsp:include><!-- 购物 -->
							<jsp:include page="/jsp/orderlist/closelist_yl.jsp"></jsp:include><!-- 娱乐 -->
							<%-- <jsp:include page="/jsp/orderlist/closelist_qtsr.jsp"></jsp:include><!-- 其他收入 --> --%>
						</div>
					</div>

					<div class="modal-footer">
						<button id="close" type="button" class="btn btn-default btn-sm"
							data-dismiss="modal">返回</button>
						<button id="submit" type="button" class="btn btn-primary btn-sm">提交</button>
					</div>
				</div>
			<!-- </form> -->
			<!-- PAGE CONTENT ENDS -->
		</div>
		<!-- /.col -->
	</div>
	<!-- /.row -->
</div>

