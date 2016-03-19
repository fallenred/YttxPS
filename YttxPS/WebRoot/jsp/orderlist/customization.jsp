<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8" />
<title>后台管理系统</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="//netdna.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
<jsp:include page="/jsp/comm/css.jsp" flush="true" />
<jsp:include page="/jsp/comm/scripts.jsp" flush="true" />
<style>
.table thead tr th {
	text-align: center;
}

.table tbody tr td {
	text-align: center;
	vertical-align: middle;
}
</style>
</head>
<body>
	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			}
		</script>

		<div class="main-container-inner">

			<div class="main-content">

				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->

							<!-- 模态框（修改） -->

							<form class="form-horizontal" id="editform">

								<div class="modal-body">
									<!-- 图片TODO： -->
									<div>
										<ul class="ace-thumbnails clearfix">
											<div style="visibility: hidden;">
												<li><a class="cboxElement" data-rel="colorbox"
													href="http://127.0.0.1:81/1.jpg"> <img width="0"
														height="0" src="http://127.0.0.1:81/2.png" alt="0*0">
												</a>
												</li>
											</div>
										</ul>
									</div>

									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="fsName">订单名称</label>
											<div class="col-sm-8">
												<input type="hidden" id="fsNo" name="fsNo"
													class="form-control" placeholder="订单id" /> <input
													type="text" id="fsName" name="fsName" readonly="readonly"
													class="form-control" placeholder="订单名称" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="ftStartdate">出发日期</label>
											<div class="col-sm-3">
												<input type="text" id="ftStartdate" name="ftStartdate"
													class="form-control" data-date-format="yyyy/mm/dd"
													readonly="readonly" placeholder="出发日期" />
											</div>
											<label class="col-sm-2 control-label no-padding-right"
												for="days">计划天数</label>
											<div class="col-sm-3">
												<div class="input-group">
													<input type="text" id="days" readonly="readonly"
														class="form-control"> <span
														class="input-group-addon">天</span>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="fiVisitornum">游客人数</label>
											<div class="col-sm-3">
												<input type="text" id="fiVisitornum" class="form-control"
													placeholder="游客人数" />
											</div>
											<label class="col-sm-2 control-label no-padding-right"
												for="insurenum">保险</label>
											<div class="col-sm-3">
												<div class="input-group">
													<input type="text" id="insurenum" class="form-control">
													<span class="input-group-addon">份/人/天</span>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="fdInsuerprice">保险费用</label>
											<div class="col-sm-3">
												<div class="input-group">
													<input type="text" id="fdInsuerprice"
														name="scheduleBody.fdInsuerprice" class="form-control">
													<span class="input-group-addon">￥</span>
												</div>
											</div>
											<label class="col-sm-7 control-label"
												style="text-align: left;" for="fsProperty"><p
													class="text-danger">（保险费用说明：1元2天/人，超出2天后按0.5元每天/人计算）</p> </label>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="fsRemark">订单备注</label>
											<div class="col-sm-8">
												<textarea name="fsRemark" id="fsRemark" class="form-control"
													rows="3" placeholder="整体备注"></textarea>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="editTable">行程要求</label>
											<div class="col-sm-8">
												<table class="table table-bordered" id="editTable">
													<thead>
														<tr>
															<th width="10%">日期</th>
															<th width="11%">交通</th>
															<th width="50%">行程</th>
															<th width="6%">早餐</th>
															<th width="6%">午餐</th>
															<th width="6%">晚餐</th>
															<th width="11%">酒店</th>
														</tr>
													</thead>
													<tbody id="table-daylist">
														<script id="daylist-template" type="text/x-handlebars-template">
														{{#each daylist}}
															<tr>
																<td>{{date}}<input name="scheduleBody.daylist[{{@index}}].date" type="hidden" value="{{date}}"></td>
																<td>
																	<select name="scheduleBody.daylist[{{@index}}].transport" class="col-sm-12">
																		<option value="01" {{#isSelected transport '01'}}{{/isSelected}}>汽车</option>
																		<option value="02" {{#isSelected transport '02'}}{{/isSelected}}>飞机</option>
																		<option value="03" {{#isSelected transport '03'}}{{/isSelected}}>火车</option>
																		<option value="04" {{#isSelected transport '04'}}{{/isSelected}}>船只</option>
																		<option value="05" {{#isSelected transport '05'}}{{/isSelected}}>其他</option>
																	</select>
																</td>
																<td><input class="col-sm-12" name="scheduleBody.daylist[{{@index}}].schedule" type="text" value="{{schedule}}"></td>
																<td>
																	<input name="scheduleBody.daylist[{{@index}}].breakfast" value="1" type="checkbox" 
																	{{#compare breakfast}}
																	{{else}}
																		checked="checked"
																	{{/compare}}
																	/>
																</td>
																<td>
																	<input name="scheduleBody.daylist[{{@index}}].lunch" value="1" type="checkbox" 
																	{{#compare lunch}}
																	{{else}}
																		checked="checked"
																	{{/compare}}
																	/>
																</td>
																<td>
																	<input name="scheduleBody.daylist[{{@index}}].dinner" value="1" type="checkbox" 
																	{{#compare dinner}}
																		
																	{{else}}
																		checked="checked"
																	{{/compare}}
																	/>
																</td>
																<td><input class="col-sm-12" name="scheduleBody.daylist[{{@index}}].hotel" type="text" value="{{hotel}}"></td>
															</tr>
														{{/each}}
														</script>
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="">文件下载</label>
											<div class="col-sm-8">
												<table class="table table-bordered">
													<thead>
														<tr>
															<th width="10%">操作</th>
															<th width="20%">文件名称</th>
															<th width="70%">文件路径</th>
														</tr>
													</thead>
													<tbody id="table-attachs">
														<script id="attachs-template" type="text/x-handlebars-template">
														{{#each attachs}}
															<tr>
																<td>
																	<button type="button" class="btn btn_downFile btn-xs btn-primary">下载</button>
																	<input name="scheduleBody.attachs[{{@index}}].attachment" type="hidden" value="{{attachment}}">
																</td>
																	
																<td>{{attachname}}<input name="scheduleBody.attachs[{{@index}}].attachname" type="hidden" value="{{attachname}}"></td>
																<td><a herf="{{attachname}}">{{attachment}}</a></td>
															</tr>
														{{/each}}
														</script>
													</tbody>
													<tbody />
												</table>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="svcstdcontent">服务标准</label>
											<div class="col-sm-8">
												<textarea name="scheduleBody.svcstdcontent"
													id="svcstdcontent" class="form-control" rows="3"
													placeholder="服务标准"></textarea>
											</div>
										</div>
									</div>
									<hr>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="fdPrice">计调预估</label>
											<div class="col-sm-8">
												<div class="panel panel-default">
													<div class="panel-heading">
														<h3 class="panel-title">行程安排&报价</h3>
													</div>
													<div class="panel-body">
														<div class="row">
															<label class="col-sm-3 control-label"
																style="text-align: left;" for=""><h5>预估行程</h5> </label>
															<div class="col-sm-12">
																<div class="form-group" style="padding: 10px;">
																	<textarea name="fcRessnapshot" id="fcRessnapshot"
																		class="form-control" rows="3"
																		placeholder="为定制线路客户预估一个大致行程安排，通过此行程安排再预估一个价格"></textarea>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="form-group">
																<label class="col-sm-2 control-label" for="transName">报价金额</label>
																<div class="col-sm-3">
																	<input type="text" id="price" name="scheduleBody.price"
																		class="form-control" placeholder="报价" />
																</div>
																<label class="col-sm-2 control-label" for="fsOperId">计调人员</label>
																<div class="col-sm-3">
																	<input type="text" id="fsOperId" readonly="readonly" class="form-control" placeholder="计调人员" />
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<hr>
									<!-- 计调配置公共资源start -->
									<div class="row div_transfer">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="commResSnapshot">公共资源</label>
											<div class="col-sm-8">
												<div class="panel panel-default">
													<div class="panel-heading">
														<h4 class="panel-title">公共资源配置</h4>
													</div>
													<div class="panel-body" id="commResSnapshot">
														<!-- 公共精确资源 start -->
														<ul id="myTab" class="nav nav-tabs">
														<script id="commRes-template" type="text/x-handlebars-template">
															<li class="active"><a href="#common" data-toggle="tab">车型/导游/景区</a></li>
															{{#each daylist}}
															<li><a href="#day{{@index}}" data-toggle="tab">第{{addOne @index}}天</a></li>
															{{/each}}
														</script>
														</ul>
														<div id="myTabContent" class="tab-content">
															<script id="commRes-template1" type="text/x-handlebars-template">
															<div class="tab-pane fade in active" id="common">
																<div class="row" style="margin-top: 10px;">
																	<div class="col-sm-12" style="padding: 10 0 0 0px;">
																		<div class="row" style="padding: 10 10 0 0px;">
																			<div class="form-group">
																				<label class="col-sm-2 control-label" for="fsRegions">车辆类型</label>
																				<div class="col-sm-2">
																					<select id="transport" class="form-control">
																					</select>
																				</div>
																				<label class="col-sm-1 control-label" for="fsRegions">价格</label>
																				<div class="col-sm-2">
																					<div class="input-group">
																						<input type="text" id="transportPrice" class="form-control">
																						<span class="input-group-addon">￥</span>
																					</div>
																				</div>
																				<label class="col-sm-1 control-label"
																					for="fsRegions">数量</label>
																				<div class="col-sm-2">
																					<div class="input-group">
																						<input type="text" id="transportNum" value="1" class="form-control">
																						<span class="input-group-addon">辆</span>
																					</div>
																				</div>
																				<div class="col-sm-1">
																					<button type="button"
																						class="btn btn_add btn-sm btn-success pull-right"
																						onclick="javascript:addTransport(this);">添加</button>
																				</div>
																			</div>
																		</div>
																		<div class="row">
																			<div class="form-group">
																				<label class="col-sm-2 control-label" for="fsRegions">导游星级</label>
																				<div class="col-sm-2">
																					<select id="guideLvl" class="form-control"></select>
																				</div>
																				<label class="col-sm-1 control-label" for="fsRegions">导游</label>
																				<div class="col-sm-2">
																					<select id="guide" class="form-control"></select>
																				</div>
																				<label class="col-sm-1 control-label" for="fsRegions">价格</label>
																				<div class="col-sm-2">
																					<div class="input-group">
																						<input type="text" id="guidePrice" class="form-control">
																						<span class="input-group-addon">￥</span>
																					</div>
																				</div>
																				<div class="col-sm-1">
																					<button type="button"
																						class="btn btn_add btn-sm btn-success pull-right"
																						onclick="javascript:addGuide(this);">添加</button>
																				</div>
																			</div>
																		</div>
																		<div class="row">
																			<div class="form-group">
																				<label class="col-sm-2 control-label"
																					for="fsRegions">景区选择</label>
																				<div class="col-sm-2">
																					<select id="scenic" class="form-control">
																					</select>
																				</div>
																				<div class="col-sm-1">
																					<button type="button" onclick="javascript:addScenic(this);"
																						class="btn btn-sm addScenicBtn btn-success pull-right">添加</button>
																				</div>
																			</div>
																		</div>
																		<div class="row">
																			<div class="form-group"  style="padding: 0px 10px 0px 10px;">
																				<div class="col-sm-12">
																					<table id="table_common" class="table table-bordered">
																						<thead>
      																						<tr>
         																						<th>操作</th>
         																						<th>资源类型</th>
         																						<th>资源名称</th>
         																						<th>消费项</th>
         																						<th>价格</th>
         																						<th>数量</th>
      																						</tr>
   																						</thead>
   																						<tbody>
																							<input type="hidden" id="reslistIndex" value="{{length reslist}}" placeholder="reslist下标">
																							{{#each reslist}}
      																						<tr>
         																						<td>
																									<a style="cursor:pointer;" onclick="removeTr(this)">删除</a>
																									<input type="hidden" name="commBody.reslist[{{@index}}].restype" value="{{restype}}" placeholder="资源类型">
																									<input type="hidden" name="commBody.reslist[{{@index}}].resprop" value="{{resprop}}" placeholder="资源属性">
																									<input type="hidden" name="commBody.reslist[{{@index}}].resno" value="{{resno}}" class="{{isScenic restype}}" placeholder="资源编号">
																									<input type="hidden" name="commBody.reslist[{{@index}}].resname" value="{{resname}}" placeholder="资源名称">
																									<input type="hidden" name="commBody.reslist[{{@index}}].cclist[0].ccno" value="{{#each cclist}}{{ccno}}{{/each}}" placeholder="选项编号">
																									<input type="hidden" name="commBody.reslist[{{@index}}].cclist[0].ccname" value="{{#each cclist}}{{ccname}}{{/each}}" placeholder="选项名称">
																									<input type="hidden" name="commBody.reslist[{{@index}}].cclist[0].price" value="{{#each cclist}}{{price}}{{/each}}" class="price" placeholder="选项价格">
																									<input type="hidden" name="commBody.reslist[{{@index}}].cclist[0].usernum" value="{{#each cclist}}{{usernum}}{{/each}}" class="usernum" placeholder="消费人数">
																									<input type="hidden" name="commBody.reslist[{{@index}}].cclist[0].cctype" value="{{#each cclist}}{{cctype}}{{/each}}" placeholder="选项类型">
																								</td>
         																						<td>{{getType restype}}</td>
         																						<td>{{resname}}</td>
         																						<td>{{#each cclist}}{{ccname}}{{/each}}</td>
         																						<td>{{#each cclist}}{{price}}{{/each}}</td>
         																						<td>{{#each cclist}}{{usernum}}{{/each}}</td>
      																						</tr>
																							{{/each}}
   																						</tbody>
																					</table>
																				</div>
																			</div>
																		</div>
																	</div>
																</div>
															</div>
															</script>
															<!-- 公共资源体 start -->
															<script id="commRes-template2" type="text/x-handlebars-template">
															{{#each daylist}}
															<div class="tab-pane fade" id="day{{@index}}">
																<div class="row" style="margin-top: 10px;">
																	<div class="col-sm-12" style="padding: 10 0 0 0px;">
																		<div class="row" style="padding: 0px 15px 0px 0px;">
																			<div class="form-group">
																				<label class="col-sm-1 control-label">类型</label>
																				<div class="col-sm-2">
																					<select id="restype" class="form-control">
																						<option value="mp">门票</option>
																						<option value="ct">餐厅</option>
																						<option value="gw">购物</option>
																					</select>
																				</div>
																				<label class="col-sm-1 control-label">资源</label>
																				<div class="col-sm-2">
																					<select id="resno" class="select_resno form-control">
																						<option value="">-请选择-</option>
																					</select>
																					<input type="hidden" id="dayflag" value="{{@index}}"  placeholder="日期天数">
																				</div>
																				<label class="col-sm-1 control-label">项目</label>
																				<div class="col-sm-2">
																					<select id="ccno" class="select_ccno form-control">
																					</select>
																				</div>
																				<label class="col-sm-1 control-label">数量</label>
																				<div class="col-sm-1">
																					<input type="text" id="usernum" class="form-control" value="1" placeholder="数量">
																				</div>
																				<div class="col-sm-1">
																					<button type="button" class="btn btn_add btn_res btn-sm btn-success">添加</button>
																					<input type="hidden" class="reslistIndex" value="{{length reslist}}" placeholder="daylist下标">
																					<input type="hidden" class="daylistIndex" name="commBody.daylist[{{@index}}].dayflag" value="{{@index}}" placeholder="日期">
																				</div>
																			</div>
																		</div>
																		<div class="row">
																			<div class="form-group"  style="padding: 0px 10px 0px 10px;">
																				<div class="col-sm-12">
																					<table id="table_common{{@index}}" class="table table-bordered">
																						<thead>
      																						<tr>
         																						<th>操作</th>
         																						<th>资源类型</th>
         																						<th>资源名称</th>
         																						<th>消费项</th>
         																						<th>价格</th>
         																						<th>数量</th>
      																						</tr>
   																						</thead>
   																						<tbody>
																							{{#each reslist}}
      																						<tr>
         																						<td>
																									<a style="cursor:pointer;" onclick="removeTr(this)">删除</a>
																									<input type="hidden" name="commBody.daylist[{{../dayflag}}].reslist[{{@index}}].restype" value="{{restype}}" placeholder="资源类型">
																									<input type="hidden" name="commBody.daylist[{{../dayflag}}].reslist[{{@index}}].resprop" value="{{resprop}}" placeholder="资源属性">
																									<input type="hidden" name="commBody.daylist[{{../dayflag}}].reslist[{{@index}}].resno" value="{{resno}}" placeholder="资源编号">
																									<input type="hidden" name="commBody.daylist[{{../dayflag}}].reslist[{{@index}}].resname" value="{{resname}}" placeholder="资源名称">
																									<input type="hidden" name="commBody.daylist[{{../dayflag}}].reslist[{{@index}}].cclist[0].ccno" value="{{#each cclist}}{{ccno}}{{/each}}" placeholder="选项编号">
																									<input type="hidden" name="commBody.daylist[{{../dayflag}}].reslist[{{@index}}].cclist[0].ccname" value="{{#each cclist}}{{ccname}}{{/each}}" placeholder="选项名称">
																									<input type="hidden" name="commBody.daylist[{{../dayflag}}].reslist[{{@index}}].cclist[0].price" value="{{#each cclist}}{{price}}{{/each}}" class="price" placeholder="选项价格">
																									<input type="hidden" name="commBody.daylist[{{../dayflag}}].reslist[{{@index}}].cclist[0].usernum" value="{{#each cclist}}{{usernum}}{{/each}}" class="usernum" placeholder="消费人数">
																									<input type="hidden" name="commBody.daylist[{{../dayflag}}].reslist[{{@index}}].cclist[0].cctype" value="{{#each cclist}}{{cctype}}{{/each}}" placeholder="选项类型">
																								</td>
         																						<td>{{getType restype}}</td>
         																						<td>{{resname}}</td>
         																						<td>{{#each cclist}}{{ccname}}{{/each}}</td>
         																						<td>{{#each cclist}}{{price}}{{/each}}</td>
         																						<td>{{#each cclist}}{{usernum}}{{/each}}</td>
      																						</tr>
																							{{/each}}
   																						</tbody>
																					</table>
																				</div>
																			</div>
																		</div>
																	</div>
																</div>
															</div>
															{{/each}}
															</script>
															<!-- 公共资源体 end -->
														</div>
														<!-- 
														</script>
														公共精确资源 end -->
													</div>
												</div>
											</div>
										</div>
									</div>
									<!-- 计调配置公共资源end -->
									<!-- 计调配置批次资源start -->
									<div class="row div_transfer">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="transType">订单批次</label>
											<div class="col-sm-8">
												<table id="table_batch" class="table table-hover table-expandable">
													<thead>
														<tr>
															<th width="8%">批次号</th>
															<th width="8%">总人数</th>
															<th width="10%">联系人</th>
															<th width="10%">联系方式</th>
															<th width="60%">备注</th>
															<th width="2%"></th>
														</tr>
													</thead>
													<tbody>
														<!-- 循环批次信息start -->
														<script id="batch-template" type="text/x-handlebars-template">
														{{#each orderCustoms}}
														<input type="hidden" name="batchBody[{{fiSeq}}].fiId" value="{{fiId}}" placeholder="批次序号">
														<tr class="tr_batch">
															<td>{{addOne fiSeq}}</td>
															<td>{{fiTotal}}</td>
															<td>{{fsContactname}}</td>
															<td>{{fsContacttel}}</td>
															<td>{{fsPostscript}}</td>
															<td id="fa"><i class="fa fa-angle-double-down"/></td>
														</tr>
														<tr style="display: none;">
															<td colspan="6">
																<div class="panel panel-default">
																	<div class="panel-heading" align="left">
																		<h4 class="panel-title">批次资源配置</h4>
																	</div>
																	<div class="panel-body">
																		<ul id="Tab" class="nav nav-tabs">
																			{{#each body/daylist}}
																			<li class="{{isActive_li @index}}"><a href="#batch{{../fiId}}_day{{@index}}" data-toggle="tab">第{{addOne @index}}天</a></li>
																			{{/each}}
																		</ul>
																		<div id="myTabContent" class="tab-content">
																			<!-- 批次酒店、娱乐配置 start -->
																			{{#each body/daylist}}
																			<div class="tab-pane fade {{isActive_pane @index}}" id="batch{{../fiId}}_day{{@index}}">
																				<div class="row" style="margin-top: 10px;">
																					<div class="col-sm-12" style="padding: 10 10 0 0px;">
																						<div class="row">
																							<div class="form-group">
																								<label class="col-sm-1 control-label">类型</label>
																								<div class="col-sm-2">
																									<select id="restype" class="form-control">
																										<option value="bg">酒店</option>
																										<option value="yl">娱乐</option>
																									</select>
																								</div>
																								<label class="batch_bg col-sm-1 control-label">标准</label>
																								<div class="batch_bg col-sm-2">
																									<select class="batch_bgLvl form-control">
																									</select>
																								</div>
																								<label class="batch_bg col-sm-1 control-label">酒店</label>
																								<div class="batch_bg col-sm-2">
																									<select class="batch_accomadation form-control">
																									</select>
																								</div>
																								<label class="batch_resname col-sm-1 control-label">房型</label>
																								<div class="col-sm-2">
																									<select id="batch_resno" class="batch_resno form-control">
																									</select>
																								</div>
																							</div>
																						</div>
																						<div class="row" style="">
																							<div class="form-group">
																								<label class="col-sm-1 control-label">项目</label>
																								<div class="col-sm-2">
																									<select class="batch_ccno form-control"></select>
																								</div>
																								<label class="col-sm-1 control-label">数量</label>
																								<div class="col-sm-2">
																									<input type="text" id="usernum" class="usernum form-control" value="1"/>
																								</div>
																								<div class="col-sm-1">
																									<button type="button" class="btn btn_add btn_batch btn-sm btn-success pull-right">添加</button>
																									<input type="hidden" class="batchIndex" value="{{../fiSeq}}" placeholder="批次下标">
																									<input type="hidden" class="daylistIndex" name="batchBody[{{../fiSeq}}].daylist[{{dayflag}}].dayflag" value="{{dayflag}}" placeholder="日期">
																									<input type="hidden" class="reslistIndex" value="{{length reslist}}" placeholder="reslist下标">
																								</div>
																							</div>
																						</div>
																						<div class="row">
																							<div class="form-group" style="padding: 0px 10px 0px 10px;">
																								<div class="col-sm-12">
																									<table id="table_batch{{../fiSeq}}_{{dayflag}}" class="table table-bordered">
																										<thead>
																											<tr>
																												<th>操作</th>
																												<th>资源类型</th>
																												<th>资源名称</th>
																												<th>消费项目</th>
																												<th>价格</th>
																												<th>数量</th>
																											</tr>
																										</thead>
																										<tbody>
																											{{#each reslist}}
      																										<tr>
         																										<td>
																												<a style="cursor:pointer;" onclick="removeTr(this)">删除</a>
																												<input type="hidden" name="batchBody[{{../../fiSeq}}].daylist[{{../dayflag}}].reslist[{{@index}}].restype" value="{{restype}}" placeholder="资源类型">
																												<input type="hidden" name="batchBody[{{../../fiSeq}}].daylist[{{../dayflag}}].reslist[{{@index}}].resprop" value="{{resprop}}" placeholder="资源属性">
																												<input type="hidden" name="batchBody[{{../../fiSeq}}].daylist[{{../dayflag}}].reslist[{{@index}}].resno" value="{{resno}}" placeholder="资源编号">
																												<input type="hidden" name="batchBody[{{../../fiSeq}}].daylist[{{../dayflag}}].reslist[{{@index}}].resname" value="{{resname}}" placeholder="资源名称">
																												<input type="hidden" name="batchBody[{{../../fiSeq}}].daylist[{{../dayflag}}].reslist[{{@index}}].cclist[0].ccno" value="{{#each cclist}}{{ccno}}{{/each}}" placeholder="选项编号">
																												<input type="hidden" name="batchBody[{{../../fiSeq}}].daylist[{{../dayflag}}].reslist[{{@index}}].cclist[0].ccname" value="{{#each cclist}}{{ccname}}{{/each}}" placeholder="选项名称">
																												<input type="hidden" name="batchBody[{{../../fiSeq}}].daylist[{{../dayflag}}].reslist[{{@index}}].cclist[0].price" value="{{#each cclist}}{{price}}{{/each}}" class="price" placeholder="选项价格">
																												<input type="hidden" name="batchBody[{{../../fiSeq}}].daylist[{{../dayflag}}].reslist[{{@index}}].cclist[0].usernum" value="{{#each cclist}}{{usernum}}{{/each}}" class="usernum" placeholder="消费人数">
																												<input type="hidden" name="batchBody[{{../../fiSeq}}].daylist[{{../dayflag}}].reslist[{{@index}}].cclist[0].cctype" value="{{#each cclist}}{{cctype}}{{/each}}" placeholder="选项类型">
																												</td>
         																										<td>{{getType restype}}</td>
         																										<td>{{resname}}</td>
         																										<td>{{#each cclist}}{{ccname}}{{/each}}</td>
         																										<td>{{#each cclist}}{{price}}{{/each}}</td>
         																										<td>{{#each cclist}}{{usernum}}{{/each}}</td>
      																										</tr>
																											{{/each}}
																										</tbody>
																									</table>
																								</div>
																							</div>
																						</div>
																					</div>
																				</div>
																			</div>
																			{{/each}}
																			<!-- 批次酒店、娱乐配置 end -->
																		</div>
																	</div>
																</div>
															</td>
														</tr>
														{{/each}}
														</script>
														<!-- 循环批次信息end -->
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<div class="row div_transfer">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fdTotalfee">订单金额</label>
											<div class="col-sm-3">
												<input type="text" class="form-control" name="fdTotalfee" id="fdTotalfee">
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="fdPaidamt">已缴金额</label>
											<div class="col-sm-3">
												<input type="text" class="form-control" name="fdPaidamt" id="fdPaidamt">
											</div>
										</div>
									</div>
									<div class="row div_transfer">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fiStat">订单状态</label>
											<div class="col-sm-3">
												<select id="fiStat" name="fiStat" class="form-control">
												</select>
											</div>
										</div>
									</div>
									<hr>
									<!-- 订单备注start -->
									<div class="row div_transfer">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="remarks">订单备注</label>
											<div class="col-sm-8">
												<div class="panel panel-default">
													<div class="panel-body" id="remarks">
														<!-- 公共精确资源 start -->
														<ul id="remarksTab" class="nav nav-tabs">
														<script id="commRes-template" type="text/x-handlebars-template">
															<li class="active"><a href="#common" data-toggle="tab">车型/导游/景区</a></li>
															{{#each daylist}}
															<li><a href="#day{{@index}}" data-toggle="tab">第{{addOne @index}}天</a></li>
															{{/each}}
														</script>
														</ul>
														<div id="remarksTab" class="tab-content">
															<div class="tab-pane fade in active" id="common">
																<div class="row" style="margin-top: 10px;">
																	<div class="col-sm-12" style="padding: 10 0 0 0px;">
																		<div class="row" style="padding: 10 10 0 0px;">
																			<div class="form-group">
																				<label class="col-sm-1 control-label" for="fsContent">内容</label>
																				<div class="col-sm-7">
																					<input id="fsContent" type="text" class="form-control" />
																				</div>
																				<label class="col-sm-1 control-label" for="fdAmt">金额</label>
																				<div class="col-sm-1">
																					<input id="fdAmt" type="text" class="form-control" />
																				</div>
																				<div class="col-sm-1">
																					<button type="button" id="btn_remarks"
																						class="btn btn-sm btn-success pull-right">添加</button>
																				</div>
																			</div>
																		</div>
																		<div class="row">
																			<div class="form-group"  style="padding: 0px 10px 0px 10px;">
																				<div class="col-sm-12">
																					<table id="table_remarks" class="table table-bordered">
																						<thead>
      																						<tr>
         																						<th width="7%">操作</th>
         																						<th width="7%">序号</th>
         																						<th width="20%">备注时间</th>
         																						<th width="45%">备注内容</th>
         																						<th width="7%">金额</th>
         																						<th width="15%">状态</th>
      																						</tr>
   																						</thead>
   																						<tbody>
   																							<script id="remarks-template" type="text/x-handlebars-template">
																							<input type="hidden" id="remarksIndex" value="{{length remarks}}" placeholder="remarks下标">
																							{{#each remarks}}
      																						<tr>
         																						<td>
																									<a style="cursor:pointer;" onclick="removeTr(this)">删除</a>
																									<input type="hidden" name="remarks[{{@index}}].fsOrderId" value="{{fsOrderId}}" placeholder="订单编号">
																									<input type="hidden" name="remarks[{{@index}}].fiSeq" value="{{fiSeq}}" placeholder="序号">
																									<input type="hidden" name="remarks[{{@index}}].ftDate" value="{{ftDate}}" placeholder="备注时间">
																									<input type="hidden" name="remarks[{{@index}}].fdAmt" value="{{fdAmt}}" placeholder="备注金额">
																								</td>
         																						<td>{{fiSeq}}</td>
         																						<td>{{ftDate}}</td>
         																						<td><input class="col-sm-12" type="text" name="remarks[{{@index}}].fsContent" value="{{fsContent}}" placeholder="备注内容"></td>
         																						<td>{{fdAmt}}</td>
         																						<td>
																								<select name="remarks[{{@index}}].fiStat" class="col-sm-12">
																									<option value="0" {{#remarkStat fiStat '0'}}{{/remarkStat}}>未付款</option>
																									<option value="1" {{#remarkStat fiStat '1'}}{{/remarkStat}}>已付首款</option>
																									<option value="2" {{#remarkStat fiStat '2'}}{{/remarkStat}}>已付全款</option>
																								</select>
																								</td>
      																						</tr>
																							{{/each}}
																							</script>
   																						</tbody>
																					</table>
																				</div>
																			</div>
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<!-- 订单备注end -->
									<div id="message" class="alert alert-warning"></div>
								</div>

								<div class="modal-footer">
									<button id="close" type="button" class="btn btn-sm btn-default"
										data-dismiss="modal">关闭</button>
									<button id="reset" type="reset" class="btn btn-sm">重置</button>
									<button id="submit" type="button"
										class="btn btn-sm btn-primary">提交</button>
								</div>
							</form>
							<!-- PAGE CONTENT ENDS -->
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.page-content -->
			</div>
			<!-- /.main-content -->

		</div>
		<!-- /.main-container-inner -->

	</div>
	<!-- /.main-container -->
	<script src="/js/bus/orderlist/customization.js"></script>

	<c:if test="${!empty succflag && succflag =='1'}">

		<script type="text/javascript">
			$('.jump-step').removeClass('hide');
			setTimeout('window.location="/home.htm"', 5000);
		</script>

	</c:if>

	<script type="text/javascript">
		$('.datetimepicker').datetimepicker({
			language : 'zh-CN',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0
		});
	</script>
	<script>
		//dom创建文本框
		//var input = document.createElement("input");
		//input.type="text" ;
		//得到当前的单元格
		var currentCell;
		function editCell(event) {
			var input = $(this).find("input");
			input.type = "text"
			if (event == null) {
				currentCell = window.event.srcElement;
			} else {
				currentCell = event.target;
			}
			//根据Dimmacro 的建议修定下面的bug 非常感谢
			if (currentCell.tagName == "TD") {

				//用单元格的值来填充文本框的值
				input.value = currentCell.innerHTML;
				//当文本框丢失焦点时调用last
				input.onblur = last;
				input.ondblclick = last;
				currentCell.innerHTML = "";
				//把文本框加到当前单元格上.
				currentCell.appendChild(input);
				//根据liu_binq63 的建议修定下面的bug 非常感谢
				input.focus();
			}
		}
		function last() {
			//充文本框的值给当前单元格
			currentCell.innerHTML = input.value;
		}
		//最后为表格绑定处理方法.
		//document.getElementById("editTable").ondblclick=editCell;
	</script>
	<script type="text/javascript">
		//删除资源项
		function removeTr(obj){
			$(obj).parent().parent().remove();
			totalAmount();
		}
		
		//合计订单金额
		function totalAmount(){
			var totalAmt = 0;
			$(".price").each(function(){
				var price = $(this).val();
				var usernum = $(this).next().val();
				if(isNaN(price) || isNaN(usernum) || price=='' || usernum==''){
					return;
				}
				totalAmt = parseFloat(totalAmt) + parseInt(usernum) * parseFloat(price);
			});
			totalAmt = parseFloat(totalAmt) + parseFloat($("#fdInsuerprice").val());
			$("#fdTotalfee").val(totalAmt.toFixed(2));
		}
		
		//增加车型addTransport
		function addTransport(obj){
			var index = $("#reslistIndex").val();
			var data = {
					"index" : index,
					"restype" : "cx",
					"resprop" : "comm",
					"resno" : $("#transport").val(),
					"resname" : $("#transport").find("option:selected").text(),
					"ccno" : "000023"
			}
			data["ccname"] = "车辆消费";
			data["price"] = $("#transportPrice").val();
			data["cctype"] = "0";
			data["usernum"] = $("#transportNum").val();
			var template = Handlebars.compile($("#tr-common").html());
			$("#table_common tbody").html($("#table_common tbody").html() + template(data));
			$("#reslistIndex").val(parseInt(index)+1);
			totalAmount();
		}
		//增加导游addGuide
		function addGuide(obj){
			var index = $("#reslistIndex").val();
			var data = {
					"index" : index,
					"restype" : "dy",
					"resprop" : "comm",
					"resno" : $("#guide").val(),
					"resname" : $("#guideLvl").find("option:selected").text() + "导游-" + $("#guide").find("option:selected").text(),
					"ccno" : "000000"
			}
			data["ccname"] = "价格(通用)";
			data["price"] = $("#guidePrice").val();
			data["cctype"] = "0";
			data["usernum"] = "1";
			var template = Handlebars.compile($("#tr-common").html());
			$("#table_common tbody").html($("#table_common tbody").html() + template(data));
			$("#reslistIndex").val(parseInt(index)+1);
			totalAmount();
		}
	</script>
	<!-- 公共资源模板(车型、导游、景区) -->
	<script id="tr-common" type="text/x-handlebars-template">
		<tr>
			<td>
				<a style="cursor:pointer;" onclick="removeTr(this)">删除</a>
				<input type="hidden" name="commBody.reslist[{{index}}].restype" value="{{restype}}" placeholder="资源类型">
				<input type="hidden" name="commBody.reslist[{{index}}].resprop" value="{{resprop}}" placeholder="资源属性">
				<input type="hidden" name="commBody.reslist[{{index}}].resno" value="{{resno}}"  class="{{isScenic restype}}" placeholder="资源编号">
				<input type="hidden" name="commBody.reslist[{{index}}].resname" value="{{resname}}" placeholder="资源名称">
				<input type="hidden" name="commBody.reslist[{{index}}].cclist[0].ccno" value="{{ccno}}" placeholder="选项编号">
				<input type="hidden" name="commBody.reslist[{{index}}].cclist[0].ccname" value="{{ccname}}" placeholder="选项名称">
				<input type="hidden" name="commBody.reslist[{{index}}].cclist[0].price" value="{{price}}" class="price" placeholder="选项价格">
				<input type="hidden" name="commBody.reslist[{{index}}].cclist[0].usernum" value="{{usernum}}" class="usernum" placeholder="消费人数">
				<input type="hidden" name="commBody.reslist[{{index}}].cclist[0].cctype" value="{{cctype}}" placeholder="选项类型">
			</td>
 			<td>{{getType restype}}</td>
 			<td>{{resname}}</td>
			<td>{{ccname}}</td>
 			<td>{{price}}</td>
  			<td>{{usernum}}</td>
   		</tr>
	</script>
	<!-- 公共资源模板（门票、餐厅、购物） -->
	<script id="tr-common1" type="text/x-handlebars-template">
		<tr>
        	<td>
				<a style="cursor:pointer;" onclick="removeTr(this)">删除</a>
				<input type="hidden" name="commBody.daylist[{{dayflag}}].reslist[{{index}}].restype" value="{{restype}}" placeholder="资源类型">
				<input type="hidden" name="commBody.daylist[{{dayflag}}].reslist[{{index}}].resprop" value="{{resprop}}" placeholder="资源属性">
				<input type="hidden" name="commBody.daylist[{{dayflag}}].reslist[{{index}}].resno" value="{{resno}}" placeholder="资源编号">
				<input type="hidden" name="commBody.daylist[{{dayflag}}].reslist[{{index}}].resname" value="{{resname}}" placeholder="资源名称">
				<input type="hidden" name="commBody.daylist[{{dayflag}}].reslist[{{index}}].cclist[0].ccno" value="{{ccno}}" placeholder="选项编号">
				<input type="hidden" name="commBody.daylist[{{dayflag}}].reslist[{{index}}].cclist[0].ccname" value="{{ccname}}" placeholder="选项名称">
				<input type="hidden" name="commBody.daylist[{{dayflag}}].reslist[{{index}}].cclist[0].price" value="{{price}}" class="price" placeholder="选项价格">
				<input type="hidden" name="commBody.daylist[{{dayflag}}].reslist[{{index}}].cclist[0].usernum" value="{{usernum}}" class="usernum" placeholder="消费人数">
				<input type="hidden" name="commBody.daylist[{{dayflag}}].reslist[{{index}}].cclist[0].cctype" value="{{cctype}}" placeholder="选项类型">
			</td>
         	<td>{{getType restype}}</td>
         	<td>{{resname}}</td>
         	<td>{{ccname}}</td>
         	<td>{{price}}</td>
         	<td>{{usernum}}</td>
      	</tr>
	</script>
	<!-- 批次资源模板（娱乐、宾馆） -->
	<script id="tr-batch" type="text/x-handlebars-template">
		<tr>
        	<td>
				<a style="cursor:pointer;" onclick="removeTr(this)">删除</a>
				<input type="hidden" name="batchBody[{{fiId}}].daylist[{{dayflag}}].reslist[{{index}}].restype" value="{{restype}}" placeholder="资源类型">
				<input type="hidden" name="batchBody[{{fiId}}].daylist[{{dayflag}}].reslist[{{index}}].resprop" value="{{resprop}}" placeholder="资源属性">
				<input type="hidden" name="batchBody[{{fiId}}].daylist[{{dayflag}}].reslist[{{index}}].resno" value="{{resno}}" placeholder="资源编号">
				<input type="hidden" name="batchBody[{{fiId}}].daylist[{{dayflag}}].reslist[{{index}}].resname" value="{{resname}}" placeholder="资源名称">
				<input type="hidden" name="batchBody[{{fiId}}].daylist[{{dayflag}}].reslist[{{index}}].cclist[0].ccno" value="{{ccno}}" placeholder="选项编号">
				<input type="hidden" name="batchBody[{{fiId}}].daylist[{{dayflag}}].reslist[{{index}}].cclist[0].ccname" value="{{ccname}}" placeholder="选项名称">
				<input type="hidden" name="batchBody[{{fiId}}].daylist[{{dayflag}}].reslist[{{index}}].cclist[0].price" value="{{price}}}" class="price" placeholder="选项价格">
				<input type="hidden" name="batchBody[{{fiId}}].daylist[{{dayflag}}].reslist[{{index}}].cclist[0].usernum" value="{{usernum}}" class="usernum" placeholder="消费人数">
				<input type="hidden" name="batchBody[{{fiId}}].daylist[{{dayflag}}].reslist[{{index}}].cclist[0].cctype" value="{{cctype}}" placeholder="选项类型">
			</td>
         	<td>{{getType restype}}</td>
         	<td>{{resname}}</td>
         	<td>{{ccname}}</td>
         	<td>{{price}}</td>
         	<td>{{usernum}}</td>
      	</tr>
	</script>
	<!-- 订单备注模板 -->
	<script id="tr-remarks" type="text/x-handlebars-template">
		<tr>
      		<td>
				<a style="cursor:pointer;" onclick="removeTr(this)">删除</a>
				<input type="hidden" name="remarks[{{index}}].fsOrderId" value="{{fsOrderId}}" placeholder="订单编号">
				<input type="hidden" name="remarks[{{index}}].fiSeq" value="{{fiSeq}}" placeholder="序号">
				<input type="hidden" name="remarks[{{index}}].ftDate" value="{{ftDate}}" placeholder="备注时间">
				<input type="hidden" name="remarks[{{index}}].fdAmt" value="{{fdAmt}}" placeholder="备注金额">
			</td>
       		<td>{{fiSeq}}</td>
         	<td>{{ftDate}}</td>
         	<td><input class="col-sm-12" type="text" name="remarks[{{index}}].fsContent" value="{{fsContent}}" placeholder="备注内容"></td>
         	<td>{{fdAmt}}</td>
         	<td>
			<select name="remarks[{{index}}].fiStat" class="col-sm-12">
				<option value="0">未付款</option>
				<option value="1">已付首款</option>
				<option value="2">已付全款</option>
				</select>
			</td>
      	</tr>
	</script>
</body>
</html>
