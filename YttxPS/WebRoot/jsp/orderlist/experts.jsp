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
												for="fsNo">订单名称</label>
											<div class="col-sm-3">
												 <input 
													type="text" id="fsName" name="fsName" class="form-control"
													placeholder="订单名称" />
											</div>
											<label class="col-sm-2 control-label no-padding-right"
												for="fiGenindex">所属线路</label>
											<div class="col-sm-3">
												<input type="hidden" class="form-control" id="fiGenindex">
												<input type="text" class="form-control" id="genName">
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="fsName">订单编号</label>
											<div class="col-sm-3">
												<input type="text" id="fsNo" name="fsNo" readonly="readonly"
													class="form-control" placeholder="订单id" />
											</div>
											<label class="col-sm-2 control-label no-padding-right"
												for="ftCreatdate">创建日期</label>
											<div class="col-sm-3">
												<input type="text" id="ftCreatdate" name="ftCreatdate"
													class="form-control" placeholder="创建日期" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="fsOperId">计&nbsp;调&nbsp;&nbsp;员</label>
											<div class="col-sm-3">
												<input type="text" id="fsOperId" name="fsOperName" class="form-control" placeholder="计调员" />
											</div>
											<label class="col-sm-2 control-label no-padding-right"
												for="fsType">路线类型</label>
											<div class="col-sm-3">
												<input type="hidden" id="fsRouteId" name="fsRouteId" class="form-control" placeholder="线路id" /> 
												<select id="fsType" name="fsType" class="form-control" disabled="disabled">
													<option value="02">专家线路</option>
												</select>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="fsProperty">组团类型</label>
											<div class="col-sm-3">
												<select id="fsProperty" name="fsProperty" class="form-control" disabled="disabled">
													<option value="01">独立成团</option>
													<option value="02">散客拼团</option>
												</select>
											</div>
											<label class="col-sm-2 control-label no-padding-right"
												for="ftStartdate">发团日期</label>
											<div class="col-sm-3">
												<input type="text" id="ftStartdate" name="ftStartdate"
													class="form-control"  placeholder="发团日期" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="regionname">发团地点</label>
											<div class="col-sm-3">
												<input class="form-control" type="text" placeholder="请选择行政区域" name="regionname"
													data-key="0086" data-idx="0" data-full="中国"
													id="regionname" class="inp-search" /> <input
													type="hidden" name="fsStartplace" id="regionno" />
												<div id="selectCity" class="localcity"></div>
											</div>
											<label class="col-sm-2 control-label no-padding-right"
												for="insurenum">保&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;险</label>
											<div class="col-sm-3">
												<div class="input-group">
													<input type="text" id="insurenum" class="form-control isFloatGteZero digits">
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
														name="scheduleBody.fdInsuerprice" class="form-control isFloatGteZero number">
													<span class="input-group-addon">￥</span>
												</div>
											</div>
											<label class="col-sm-2 control-label no-padding-right"
												for="fdPrice">单人报价</label>
											<div class="col-sm-3">
												<input type="text" id="fdPrice" name="fdPrice"
													class="form-control isFloatGteZero number" placeholder="初始报价" />
											</div>
										</div>
									</div>
									<!-- <div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="fsSummary">路线摘要</label>
											<div class="col-sm-8">
												<textarea name="fsSummary" id="fsSummary" class="form-control" rows="3" placeholder="路线摘要"></textarea>
											</div>
										</div>
									</div> -->
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="fsRemark">整体备注</label>
											<div class="col-sm-8">
												<textarea name="fsRemark" id="fsRemark" class="form-control" rows="3" placeholder="整体备注"></textarea>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="fcSchedule">日程快照</label>
											<input type="hidden"  id="hSchedule" name="hSchedule" />
											<div class="col-sm-8">
												<textarea class="ckeditor" id="fcSchedule" name="fcSchedule"></textarea>
												<div hidden="hidden" id="schedule"></div>
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
															<!-- <script id="commRes-template1" type="text/x-handlebars-template"> -->
															<div class="tab-pane fade in active" id="common">
																<div class="row" style="margin-top: 10px;">
																	<div class="col-sm-12" style="padding: 10 0 0 0px;">
																		<div class="row">
																			<div class="form-group"  style="padding: 0px 10px 0px 10px;margin-top:-25px;">
																				<div class="col-sm-12">
																					<table id="table_fuzz" class="table table-bordered">
																						<script id="commRes-template1" type="text/x-handlebars-template">
																						<caption style="text-align:left">
																							<h4><font color="#12a6e2">服务标准</font></h4>
																						</caption>
																						<thead>
      																						<tr>
         																						<th>资源类型</th>
         																						<th>标准</th>
         																						<th>备注</th>
      																						</tr>
   																						</thead>
   																						<tbody>
																							{{#each reslist}}
      																						<tr class="warning">
         																						<td>{{#getType restype}}{{/getType}}</td>
         																						<td>{{resname}}</td>
         																						<td>{{remark}}</td>
      																						</tr>
																							{{/each}}
   																						</tbody>
																						</script>
																					</table>
																				</div>
																			</div>
																		</div>
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
																						<input type="text" id="transportPrice" class="form-control isFloatGteZero number">
																						<span class="input-group-addon">￥</span>
																					</div>
																				</div>
																				<label class="col-sm-1 control-label"
																					for="fsRegions">数量</label>
																				<div class="col-sm-2">
																					<div class="input-group">
																						<input type="text" id="transportNum" value="1" class="form-control isFloatGteZero digits">
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
																					<select id="guide" class="width-80 chosen-select form-control"></select>
																				</div>
																				<label class="col-sm-1 control-label" for="fsRegions">价格</label>
																				<div class="col-sm-2">
																					<div class="input-group">
																						<input type="text" id="guidePrice" class="form-control isFloatGteZero number">
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
																					<select id="scenic" class="width-80 chosen-select form-control">
																					</select>
																				</div>
																				<div class="col-sm-1">
																					<button type="button"  class="btn btn-sm addScenicBtn btn-success pull-right">添加</button>
																				</div>
																			</div>
																		</div>
																		<div class="row">
																			<div class="form-group"  style="padding: 0px 10px 0px 10px;">
																				<div class="col-sm-12">
																					<table id="table_common" class="table table-bordered">
																						<caption style="text-align:left">
																							<h4><font color="#12a6e2">调配资源<font></h4>
																						</caption>
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
   																						<tbody id="tbody_common">
																						<script id="commRes-template2" type="text/x-handlebars-template">
																							<input type="hidden" id="reslistIndex" value="{{length reslist}}" placeholder="reslist下标">
																							{{#each reslist}}
      																						<tr>
         																						<td>
																									<a style="cursor:pointer;" class="removeTr">删除</a>
																									<input type="hidden" id="restype" name="commBody.reslist[{{@index}}].restype" value="{{restype}}" placeholder="资源类型">
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
																						</script>
   																						</tbody>
																					</table>
																				</div>
																			</div>
																		</div>
																	</div>
																</div>
															</div>
															<!-- </script> -->
															<!-- 公共资源体 start -->
															<script id="commRes-template3" type="text/x-handlebars-template">
															{{#each daylist}}
															<div class="tab-pane fade" id="day{{@index}}">
																<div class="row" style="margin-top: 10px;">
																	<div class="col-sm-12" style="padding: 10 0 0 0px;">
																		<div class="row" style="padding: 0px 15px 0px 0px;">
																			<div class="form-group">
																				<label class="col-sm-1 control-label">类型</label>
																				<div class="col-sm-2">
																					<select id="restype" class="restype form-control">
																						<option value="mp">门票</option>
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
																				<label class="select_ccno col-sm-1 control-label">项目</label>
																				<div class="col-sm-2">
																					<select id="ccno" class="select_ccno form-control">
																					</select>
																				</div>
																				<label class="col-sm-1 control-label">数量</label>
																				<div class="col-sm-1">
																					<input type="text" id="usernum" class="digits form-control" value="1" placeholder="数量">
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
																									<a style="cursor:pointer;" class="removeTr">删除</a>
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
														<!-- 公共精确资源 end -->
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
																		<div id="myTabContent" class="tab-content batch_div">
																			<!-- 批次酒店、娱乐配置 start -->
																			{{#each body/daylist}}
																			<div class="tab-pane fade {{isActive_pane @index}}" id="batch{{../fiId}}_day{{@index}}">
																				<div class="row" style="margin-top: 10px;">
																					<div class="col-sm-12" style="padding: 10 10 0 0px;margin-top:-25px;">
																						<table id="table_{{../fiId}}_day{{@index}}" class="table table-bordered">
																							<caption style="text-align:left">
																							<h4><font color="#12a6e2">服务标准</font></h4>
																							</caption>
																							<thead>
																								<tr>
																									<th>资源类型</th>
																									<th>服务标准</th>
																									<th>服务选项</th>
																									<th>资源备注</th>
																								</tr>
																							</thead>
																							<tbody>
																							</tbody>
																						</table>
																						<div class="row">
																							<div class="form-group">
																								<label class="col-sm-1 control-label">类型</label>
																								<div class="col-sm-2">
																									<select id="restype" class="batch_restype form-control">
																										<option value="bg">酒店</option>
																										<option value="yl">娱乐</option>
																										<option value="ct">菜单</option>
																									</select>
																								</div>
																								<label style="display:none" class="batch_ct col-sm-1 control-label">类型</label>
																								<div style="display:none" class="batch_ct col-sm-2">
																									<select class="ct_special form-control">
																										<option value="02">午餐</option>
																										<option value="03">晚餐</option>
																									</select>
																								</div>
																								<label class="batch_lvl col-sm-1 control-label">标准</label>
																								<div class="batch_lvl col-sm-2">
																									<select class="batch_Lvl form-control">
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
																								<label class="col-sm-1 control-label">价格</label>
																								<div class="col-sm-2">
																									<div class="input-group">
																										<input type="text" class="number tccPrice form-control">
																										<span class="input-group-addon">￥</span>
																									</div>
																								</div>
																								<label class="col-sm-1 control-label">数量</label>
																								<div class="col-sm-2">
																									<input type="text" id="usernum" class="usernum form-control isFloatGteZero number" value="1"/>
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
																										<caption style="text-align:left">
																										<h4><font color="#12a6e2">调配资源</font></h4>
																										</caption>
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
																												<a style="cursor:pointer;" class="batch_remove">删除</a>
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
																<div class="row div_transfer">
																	<div class="form-group">
																		<label class="col-sm-2 control-label" for="fdTotalfee">批次金额</label>
																		<div class="col-sm-3">
																			<input type="text" class="form-control isFloatGteZero number" name="batchAmt[{{@index}}]" value="{{fdAmt}}" id="fdAmt">
																		</div>
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
												<input type="text" class="form-control isFloatGteZero number" id="totalfee" value="0">
												<input type="hidden" class="form-control" name="fdTotalfee" id="fdTotalfee">
											</div>
											<label class="col-sm-2 control-label no-padding-right" for="fdPaidamt">已缴金额</label>
											<div class="col-sm-3">
												<input type="text" class="form-control isFloatGteZero number" name="fdPaidamt" id="fdPaidamt">
											</div>
										</div>
									</div>
									<div hidden="hidden" class="row div_custList">
										<div class="form-group">
											<label hidden="hidden" class="div_transfer_stat col-sm-2 control-label no-padding-right"
												for="fiStat">订单状态</label>
											<div hidden="hidden" class="div_transfer_stat col-sm-3">
												<select id="fiStat" name="fiStat"
													class="form-control">
													<option value="0">待审核</option>
													<option value="1">已审核</option>
													<option value="4">已付首款</option>
													<option value="8">已付全款(可出团)</option>
												</select>
											</div>
											<label class="col-sm-2 control-label no-padding-right">游客名单</label>
											<div class="col-sm-1">
												<button type="button" data-toggle="modal" data-target="#myModal" class="btn btn-sm btn-success pull-right">上传名单</button>
											</div>
											<div class="col-sm-1">
												<button type="button" class="btn btn_export btn-sm btn-success pull-right">下载名单</button>
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
																					<input id="remarksAmt" type="text" class="form-control isFloatGteZero number" />
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
																									{{#remarkOption fiStat}}{{/remarkOption}}
																									<input type="hidden" name="remarks[{{@index}}].fsOrderId" value="{{fsOrderId}}" placeholder="订单编号">
																									<input type="hidden" name="remarks[{{@index}}].fiSeq" value="{{fiSeq}}" placeholder="序号">
																									<input type="hidden" name="remarks[{{@index}}].ftDate" value="{{ftDate}}" placeholder="备注时间">
																									<input type="hidden" class="remarkPrice" name="remarks[{{@index}}].fdAmt" value="{{fdAmt}}" placeholder="备注金额">
																								</td>
         																						<td>{{ftDate}}</td>
         																						<td><input class="col-sm-12" type="text" name="remarks[{{@index}}].fsContent" value="{{fsContent}}" placeholder="备注内容"></td>
         																						<td>{{fdAmt}}</td>
         																						<td>
																								<select name="remarks[{{@index}}].fiStat" class="remark_stat col-sm-12">
																									{{#remarkStat fiStat}}{{/remarkStat}}
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
	
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog" id="identifier"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
			            <button type="button" class="close" data-dismiss="modal" 
			               aria-hidden="true">×
			            </button>
			            <h4 class="modal-title" id="myModalLabel">名单上传</h4>
			         </div>
			         <div class="modal-body" style="heigh: 400px;">
			         	<form name="uploadForm" id="uploadForm" method="post" enctype="multipart/form-data">
								<div class="row">
									<div class="col-md-5 col-md-offset-1 pull-left">
										<p class="help-block">请选择要上传的文件</p>
										<input type="file" class="form-control" id="excel" name="excel" style="background-color:#BEBEBE;margin-top: 5px;" onchange="fileType(this)" />
										<input type="hidden" id="orderId" name="orderId"/>
									</div>
								</div>
								<div id="upload_message" class="alert alert-warning"></div>
							<div class="modal-footer">
								<a class="btn btn_import btn-sm btn-primary" href="#" role="button">上传</a>
								<button type="button" class="btn btn-sm btn-default" data-dismiss="modal">关闭</button>
							</div>
						</form>
			         </div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
	<script src="/js/bus/orderlist/experts.js"></script>
	<script src="/js/chosen.jquery.min.js"></script>

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
		function fileType(obj){
			var fileType=obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();//获得文件后缀名
		    if(fileType != '.xls'&&fileType != '.xlsx'){
		    	$("#excel").val('');
			    alert("请上传后缀xls或xlsx格式的excel文件");
		    }
		}
		$(function() {
			//上传
			$('#excel').ace_file_input({
				no_file:'请选择EXCEL ...',
				btn_choose:'选择',
				btn_change:'更改',
				droppable:false,
				onchange:null,
				thumbnail:false, //| true | large
				whitelist:'xls|xls',
				blacklist:'gif|png|jpg|jpeg'
				//onchange:''
				//
			});
			
		});
	</script>
	<script type="text/javascript">
		$('#myModal').on('show.bs.modal', function (e) { 
		    $(this).find('.modal-dialog').css({  
		        'margin-top': function () {  
		            var modalHeight = $('#myModal').find('.modal-dialog').height();  
		            return ($(window).height() / 2.2 - (modalHeight / 2));  
		        }
		    });      
		}); 
		//删除订单备注
		function removeRemarkTr(obj){
			$(obj).parent().parent().remove();
		}
		//订单备注作废\生效
		function invalid(obj){
			var stat = $(obj).parent().parent().find(".remark_stat ").val();
			//作废
			if (stat == '0' || stat == '1') {
				$(obj).html('');
				$(obj).parent().parent().find(".remark_stat ").html('<option value="2">作废</option>');
				totalAmount();
			}
		}
		
		//删除批次消费项
		$(document).on('click key', '.batch_remove', function(event){
			$(this).parent().parent().remove();
			totalAmount();
			totalBatchAmt();
		});
		
		//合计订单金额
		function totalAmount(){
			var totalAmt = 0;
			var remarkAmt = 0;
			$(".price").each(function(){
				var price = $(this).val();
				var usernum = $(this).next().val();
				if(isNaN(price) || isNaN(usernum) || price=='' || usernum==''){
					return;
				}
				totalAmt = parseFloat(totalAmt) + parseFloat(usernum) * parseFloat(price);
			});
			$(".remarkPrice").each(function(){
				var price = $(this).val();
				var stat = $(this).parent().parent().find(".remark_stat").val();
				if(isNaN(price) || isNaN(stat) || price=='' || stat=='2'){
					return;
				}
				remarkAmt = parseFloat(remarkAmt) + parseFloat(price);
			});
			totalAmt = parseFloat(totalAmt) + parseFloat($("#fdInsuerprice").val());
			remarkAmt = parseFloat(remarkAmt) + parseFloat(totalAmt);
			$("#fdTotalfee").val(totalAmt.toFixed(2));
			$("#totalfee").val(remarkAmt.toFixed(2));
		}
		
		//合计订单批次金额
		function totalBatchAmt(){
			//循环批次
			$(".batch_div").each(function(){
			var fdAmt = 0;
				$(this).find(".price").each(function(){
					var price = $(this).val();
					var usernum = $(this).next().val();
					if(isNaN(price) || isNaN(usernum) || price=='' || usernum==''){
						return;
					}
					fdAmt = parseFloat(fdAmt) + parseFloat(usernum) * parseFloat(price);
				});
				$(this).parent().next().find("#fdAmt").val(fdAmt);
			});
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
				<a style="cursor:pointer;" class="removeTr">删除</a>
				<input type="hidden" id="restype" name="commBody.reslist[{{index}}].restype" value="{{restype}}" placeholder="资源类型">
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
				<a style="cursor:pointer;" class="removeTr">删除</a>
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
				<a style="cursor:pointer;" class="batch_remove">删除</a>
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
				<a style="cursor:pointer;" onclick="removeRemarkTr(this)">删除</a>
				<input type="hidden" name="remarks[{{index}}].fsOrderId" value="{{fsOrderId}}" placeholder="订单编号">
				<input type="hidden" name="remarks[{{index}}].fiSeq" value="{{fiSeq}}" placeholder="序号">
				<input type="hidden" name="remarks[{{index}}].ftDate" value="{{ftDate}}" placeholder="备注时间">
				<input type="hidden" class="remarkPrice" name="remarks[{{index}}].fdAmt" value="{{fdAmt}}" placeholder="备注金额">
			</td>
         	<td>{{ftDate}}</td>
         	<td><input class="col-sm-12" type="text" name="remarks[{{index}}].fsContent" value="{{fsContent}}" placeholder="备注内容"></td>
         	<td>{{fdAmt}}</td>
         	<td>
			<select name="remarks[{{index}}].fiStat" class="remark_stat col-sm-12">
				<option value="0">未付款</option>
				<option value="1">已付款</option>
				</select>
			</td>
      	</tr>
	</script>
</body>
</html>
