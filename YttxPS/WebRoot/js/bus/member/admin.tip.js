jQuery(function($){
	
	var tips={
		"S":{
			"operResult":"禁用了该客户，此客户将无法登陆本系统",
			"operTip":"你确定要禁用该客户吗？",
		},
		"A":{
			"operResult":"启用该客户，此客户将能登陆本系统",
			"operTip":"你确定要启用该客户吗？",
		}
	}
	var oper = $.getUrlParam('oper');
	var tip = tips[oper];
	$("#operResult").text(tip["operResult"]);
	$("#opertip").text(tip["operTip"]);
	
	var param={};
	param.id=$.getUrlParam('id');
	param.oper=oper;
	
	//console.log(param);
	//"确定"按钮的响应事件
	$("#submit").on("click", function () {
		$.ajax({
		     type: 'POST',
		     url: '/member/admin/modCusStat.htm',
		     data: param,
		     success: 
		    	 function(data){
					if(data.result == "ok") {
						$("#message").text("操作成功！");
						return true;
					}
					else {
						$("#message").text("操作失败:" + json.message );
						return false;
					}
					return false;
				} ,
		    dataType: 'json'
		});
	});
	
	
	/**
	 * 删除警告弹出框-->"关闭"按钮的响应时间
	 */
	$("#close").on("click", function () {
		$("#delModal", parent.document).find(".close").click();
	});
});
