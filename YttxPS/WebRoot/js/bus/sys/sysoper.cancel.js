jQuery(function($) {
	//	提交
	$("#submit").on("click", function () {
		$.ajax({
		     type: 'POST',
		     url: '/user/oper/cancel.htm' ,
		     data: 'sysOperId=' + $.getUrlParam('sysOperId'),
		     success: function(data){
						if(data.result == "ok") {
							$("#message").text("删除用户成功");
							return true;
						}
						else {
							$("#message").text("删除用户失败:" + json.message );
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
