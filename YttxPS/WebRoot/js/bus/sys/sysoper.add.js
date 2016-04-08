$(document).ready(function(){
	$("#message").hide();
	var setting = {
	        check:
	        {
	            enable: true,
	            chkboxType: { "Y": "ps", "N": "s" }
	        },
	        data: 
	        {
	            simpleData: 
	            {
	                enable: true
	            }
	        }
	    };
	
	/**
	 * 权限--构建权限树型菜单
	 */
	var zNodes = menuList;
	zNodes.splice(zNodes.length-1,1);
	$.fn.zTree.init($("#add_tree_div"), setting, zNodes);
	
	/**
	 * 权限--全部权限的赋予与取消
	 */
	$("#add_right_all").click(function(event){
		event.stopPropagation();
		var zTree = $.fn.zTree.getZTreeObj("add_tree_div");
		var checked = $(this).is(":checked");
		zTree.checkAllNodes(checked);
	})
	
	
	/***
	 * 用户管理-->新增用户-->"重置"按钮响应函数
	 */
	$("#reset").click(function() {
		$.fn.zTree.getZTreeObj("add_tree_div").checkAllNodes(false);//所有权限置空
		//清空输入框
		$("form-group input[type='text']").each(function(){
			$(this).val();
		})
		//清空输入框
		$("form-group input[type='password']").each(function(){
			$(this).val();
		})
		//重置下拉列表
		$("form-group select").each(function(){
			$(this).find("option[value='']").prop("selected",true);
		})
	});
	
	/**
	 * 用户管理-->新增用户-->"关闭"按钮响应函数
	 */
	$("#close").click(function () {
		$("#addModal", parent.document).find(".close").click();
	});
	

	
	/***
	 * 用户管理-->新增用户-->"提交"按钮响应函数
	 */
	$("#submit").click(function (){
		//首先，验证提交数据
		var sysOperId = $.trim($("#sysOperId").val());//获取用户编号
		if( sysOperId == ''|| sysOperId.length <6||sysOperId.length>16) {
			$("#message").show();
			$("#message").text("用户编号为空/用户编号的长度小于6或者大于16");
			$('#sysOperId').focus();
			return false;
		} else {
			var reg = /^\w{6,16}$/;
			if(!reg.test(sysOperId)) {
				$("#message").show();
				$("#message").text("用户编号格式不正确");
				$('#sysOperId').focus();
				return false;
			}
		}
		
		var sysOperName = $.trim($("#sysOperName").val());//获取用户名称
		if( sysOperName == ''|| sysOperName.length>42) {
			$("#message").show();
			$("#message").text("用户名称为空/用户名称长度超出限定长度");
			$('#sysOperName').focus();
			return false;
		}
		
		var adminType = $("#adminType").val();//获取管理身份
		if(adminType=='undefined'||adminType==null||adminType==''){
			$("#message").show();
			$("#message").text("管理身份未选择");
			$('#adminType').focus();
			return false;
		}
		
		var depNo = $("#depNo").val();//获取部门编号
		if(depNo=='undefined'||depNo==null||depNo==''){
			$("#message").show();
			$("#message").text("所属部门未选择");
			$('#depNo').focus();
			return false;
		}
		
		var pwd = $("#pwd").val();
		var confirmPwd = $("#confirmPwd").val();
		if( pwd == ''|| pwd.length <6||pwd.length>16) {
			$("#message").show();
			$("#message").text("用户密码为空/用户密码的长度小于6或者大于16");
			$('#pwd').focus();
			return false;
		}
		if(pwd != confirmPwd){
			$("#message").show();
			$("#message").text("用户密码和确认密码不一致");
			$('#pwd').focus();
			return false;
		}
		
		//封装提交数据
		var param = {};
		param["sysOper.sysOperId"] = sysOperId; 
		param["sysOper.sysOperName"] = sysOperName; 
		param["sysOper.adminType"] = adminType; 
		param["sysOper.depNo"] = depNo; 
		param["sysOper.sysOperPwd"] = b64_sha1(pwd); 
	
		var checkedNodes = $.fn.zTree.getZTreeObj("add_tree_div").getCheckedNodes(true);
		if(null!=checkedNodes){
			$.each(checkedNodes,function(index,right){
				param["rights["+index+"]"] = right.id;
			})
		}
		
		//最后，以post的方式向服务器提交数据
		var url = "/user/oper/addsubmit.htm";
		$.post(
			url,
			param,
			function(data){
				var json = eval("("+data+")");
				if(json.result == "ok") {
					$("#message").text("添加用户成功！");
					$("#message").show();
					return true;
				}
				else {
					$("#message").text("添加用户失败:" + json.message );
					$("#message").show();
					return false;
				}
				return false;
			}
		);
	});
	
	//--------------------------------------------------------------
	var $overflow = '';
	var colorbox_params = {
		rel : 'colorbox',
		reposition : true,
		scalePhotos : true,
		scrolling : true,
		previous : '<i class="ace-icon fa fa-arrow-left"></i>',
		next : '<i class="ace-icon fa fa-arrow-right"></i>',
		close : '&times;',
		current : '{current} of {total}',
		maxWidth : '100%',
		maxHeight : '100%',
		onOpen : function() {
			$overflow = document.body.style.overflow;
			document.body.style.overflow = 'hidden';
		},
		onClosed : function() {
			document.body.style.overflow = $overflow;
		},
		onComplete : function() {
			$.colorbox.resize();
		}
	};

	$('.ace-thumbnails [data-rel="colorbox"]').colorbox(colorbox_params);
	$("#cboxLoadingGraphic").html(
			"<i class='ace-icon fa fa-spinner orange'></i>");// let's add a custom loading icon
})
