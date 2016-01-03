jQuery(function($) {
	$("#message").hide();
	
	//	提交
	$("#submit").on("click", function () {
		$.post("/scenic/delScenic.htm",
				"{no:" + $.getUrlParam('no') + "}",
				function(data){
			console.log(data)
			var json = eval("("+data+")");
					if(json.result == "ok") {
						$("#message").text("删除记录成功");
						$("#message").show();
						$("#grid-table", parent.document).trigger("reloadGrid"); 
						return true;
					}
					else {
						$("#message").text("删除记录失败:" + json.message );
						$("#message").show();
						return false;
					}
					return false;
				});
	});
	
	$("#submit").on("click", function () {
		$("#delModal", parent.document).find(".close").click();
	});
});
