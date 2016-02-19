jQuery(function($) {
	//	提交
	$("#submit").on("click", function () {
		$.ajax({
		     type: 'POST',
		     url: '/user/dept/cancel.htm' ,
		     data: 'depNo=' + $.getUrlParam('depNo'),
		     success: function(data){
						if(data.result == "ok") {
							$("#message").text("注销部门成功");
							return true;
						}
						else {
							$("#message").text("注销部门失败:" + json.message );
							return false;
						}
						return false;
					} ,
		    dataType: 'json',
		});
	});
	
	// 关闭
	$("#close").on("click", function () {
		$("#delModal", parent.document).find(".close").click();
	});
});
