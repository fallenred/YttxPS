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
	$("#submit").on("click", function () {
		$("#depName").val($.trim($("#depName").val()));
		if($("#depName").val() == '') {
			$("#message").show();
			$("#message").text("部门名称不能为空，请输入");
			$('#name').focus();
			return false;
		} 
		
		var url = "/user/dept/editsubmit.htm";
		var param = {};
		param["sysDep.depName"] = $("#depName").val();
		param["sysDep.depNo"] = $("#depNo").val();
		if($("#stat")!=undefined){
			param["sysDep.stat"] = $("#stat").val();
		}
		
		var checkedNodes = $.fn.zTree.getZTreeObj("edit_tree_div").getCheckedNodes(true);
		if(null!=checkedNodes){
			$.each(checkedNodes,function(index,right){
				param["rights["+index+"]"] = right.id;
			})
		}
		
		$.post(
			url,
			param,
			function(data){
				var json = eval("("+data+")");
				if(json.result == "ok") {
					$("#message").text("修改部门信息成功！");
					$("#message").show();
					return true;
				}
				else {
					$("#message").text("修改部门信息失败:" + json.message );
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
