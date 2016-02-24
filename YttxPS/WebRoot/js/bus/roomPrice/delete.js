jQuery(function($) {
	//	提交
	$("#delForm #submit").on("click", function () {
		$.ajax({
		     type: 'POST',
		     url: '/room/delRoomPrice.htm' ,
		     data: $("#delForm").serialize(),
		     success: function(data){
						if(data.result == "ok") {
						    $("#delForm #question").hide();
							$("#delForm #message").text("删除记录成功");
							$("#delForm #submit").attr("disabled", "disabled");
							return true;
						}
						else {
							$("#delForm #message").text("删除记录失败:" + json.message );
							return false;
						}
						return false;
					} ,
		    dataType: 'json',
		});
	});
	
	// 关闭
	$("#delModal #close").on("click", function () {
		$("#delModal", parent.document).find(".close").click();
	});
});
