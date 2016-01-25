jQuery(function($) {
	//	提交
	$("#submit").on("click", function () {
		$.ajax({
		     type: 'POST',
		     url: '/closelist/delCloselist.htm' ,
		     data: 'no=' + $.getUrlParam('no'),
		     success: function(data){
						if(data.result == "ok") {
							$("#message").text("删除记录成功");
							return true;
						}
						else {
							$("#message").text("删除记录失败:" + json.message );
							return false;
						}
						return false;
					} ,
		    dataType: 'json'
		});
	});
	
	// 关闭
	$("#close").on("click", function () {
		$("#delModal", parent.document).find(".close").click();
	});
});
