$(document).ready(function(){
	$("#message").hide();
	//放大图片
	$(".imgzoom").WMZoom({config:{
		inner : true
		}
	});;
	//初始化会员选择器
	var localsel = $("#selectUser").user({
		depturl : "/user/dept/findDepts.htm",
		operurl : "/user/oper/findOpersByDepNo.htm",
		callback : localcallback
	});

	//会员选择器的回调函数
	function localcallback(index, key, value, fullkey, fullname) {
		$("#operName").val(fullname);
		$("#operId").val(key);
		if (index == 2) {
			$("#selectUser").hide();
		}
	}
	
	//绑定会员选择器
	$("#operName").click(function() {
		$("#selectUser").show();
	})
	
	//关闭按钮绑定响应函数
	$("#close").on("click", function (){
		$("#showModal", parent.document).find(".close").click();
	});
	
	//审核函数
	function auditCustomer(auditRet){
		var param = {};
		var auditType = $("#auditType").val();
		param.auditType = auditType;//审核类型
		
		if(auditType==1){
			var salesmanId = $("#operId").val();
			if(salesmanId==null||$.trim(salesmanId)==''){
				$("#message").text("请选择销售人员").show();
				$("#operName").focus();
				return false;
			}
			param.salesManID=salesmanId;//销售人员Id
		}
		
		param.auditRet = auditRet;//审核结果
		var comment = $("#comment").val();
		if(auditRet==-1){
			if(comment==null||$.trim(comment)==''){
				$("#message").text("请输入审核意见").show();
				$("#comment").focus();
				return false;
			}
		}
		param.comment = comment;//审核意见
		param.id = $("#id").val();  //会员id
		param.auditno = $("#auditNo").val();  // 审核编号
		
		var url="/member/audit/audit.htm"
		$.ajax({    
            type:'post',        
            url:url,    
            data:param,    
            cache:false,    
            dataType:'json',    
            success:function(json){  
            	if(json.result == "ok") {
					$("#message").html("审核成功！");
					$("#message").show();
					return true;
				}else {
					$("#message").html("审核失败:" + json.message );
					$("#message").show();
					return false;
				}
				return false;                                  
            }
        });            
		
		
		
	}
	
	//审核通过按钮绑定响应函数
	$("#aduit_ok").on("click", function (){
		auditCustomer(1);
	});
	
	
	//审核通过按钮绑定响应函数
	$("#audit_no").on("click", function (){
		auditCustomer(-1);
	});
})