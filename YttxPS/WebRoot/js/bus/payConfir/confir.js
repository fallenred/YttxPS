	/**
	 *	订单支付确认提交
	 */	
	$("#orderConfir_submit").on("click", function () {
		alert();
		if ($("#editform").valid()) {
			$.post("/payConfir/orderConfir.htm", $("#editform").serialize(),
				function(data){
					var json = eval("("+data+")");
					if(json.result == "ok") {
						$("#message").text("订单支付已确认");
						$("#message").show();
						return true;
					}
					else {
						$("#message").text("订单支付确认失败:" + json.message );
						$("#message").show();
						return false;
					}
					return false;
			});
		} else {
			$("#message").text("输入字段验证错误，请重新编辑后再提交");
			$("#message").show();
			return false;
		}
	});
	/**
	 *	结算单支付确认
	 */	
	$("#statementConfir_submit").on("click", function () {
		if ($("#editform").valid()) {
			$.post("/payConfir/statementConfir.htm", $("#editform").serialize(),
					function(data){
				var json = eval("("+data+")");
				if(json.result == "ok") {
					$("#message").text("结算单支付已确认");
					$("#message").show();
					return true;
				}
				else {
					$("#message").text("结算单支付确认失败:" + json.message );
					$("#message").show();
					return false;
				}
				return false;
			});
		} else {
			$("#message").text("输入字段验证错误，请重新编辑后再提交");
			$("#message").show();
			return false;
		}
	});
	
	/*//查询操作员姓名
	function getOperName(){*/
	operid = $("#fsOperId").val();
		$.ajax({
			type: "GET",
			url: "/pub/findOperName.htm",
			data: {operid : operid},
			dataType: "json",
			success: function(data){
				opername = data.sysOperName;
				$("#operName").val(opername);
			}
		});
	/*}
*/