$(document).ready(function() {
	/**
	 * 隐藏信息div
	 */
	$("#message").hide();
	
	/**
	 * 树形菜单，初始化参数
	 */
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
	$.fn.zTree.init($("#edit_tree_div"), setting, zNodes).expandAll(true);
	
	/**
	 * 权限--全部权限的赋予与取消
	 */
	$("#add_right_all").click(function(event){
		event.stopPropagation();
		var zTree = $.fn.zTree.getZTreeObj("edit_tree_div");
		var checked = $(this).is(":checked");
		zTree.checkAllNodes(checked);
	})
	
	/**
	 * 编辑-->关闭弹出框
	 */
	$("#close").on("click", function () {
		$("#editModal", parent.document).find(".close").click();
	});
	
	/**
	 * 编辑-->提交数据
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
		
		//封装提交数据
		var param = {};
		param["oldOperId"] = $("#oldOperId").val();
		param["sysOper.sysOperId"] = sysOperId; 
		param["sysOper.sysOperName"] = sysOperName; 
		param["sysOper.adminType"] = adminType; 
		param["sysOper.depNo"] = depNo; 
		param["sysOper.stat"] = $("#stat").val(); 
	
		var checkedNodes = $.fn.zTree.getZTreeObj("edit_tree_div").getCheckedNodes(true);
		if(null!=checkedNodes){
			$.each(checkedNodes,function(index,right){
				param["rights["+index+"]"] = right.id;
			})
		}
		
		//最后，以post的方式向服务器提交数据
		var url = "/user/oper/editsubmit.htm";
		$.post(
			url,
			param,
			function(data){
				var json = eval("("+data+")");
				if(json.result == "ok") {
					$("#message").text("修改用户信息成功！");
					$("#message").show();
					return true;
				}
				else {
					$("#message").text("修改用户信息失败:" + json.message );
					$("#message").show();
					return false;
				}
				return false;
			}
		);
	});
	
	//	colorbox
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

});
