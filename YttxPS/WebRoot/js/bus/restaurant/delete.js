jQuery(function($) {
	/**
	 * 删除警告弹出框-->"确定"按钮的响应时间
	 */
	$("#submit").on("click", function () {
		$.ajax({
		     type: 'POST',
		     url: '/restaurant/del.htm' ,
		     data: 'no=' + $.getUrlParam('no'),
		     success: 
		    	 function(data){
					if(data.result == "ok") {
						$("#message").text("删除餐厅记录成功");
						return true;
					}
					else {
						$("#message").text("删除餐厅记录失败:" + json.message );
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
