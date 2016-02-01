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
	 * 部门管理-->新增部门-->"重置"按钮响应函数
	 */
	$("#reset").click(function() {
		$("#depName").val(null);//部门名称清空
		$("#add_right_all").prop("checked",false);//所有权限按钮-->"未选中"
		$.fn.zTree.getZTreeObj("add_tree_div").checkAllNodes(false);//所有权限置空
	});
	
	/**
	 * 部门管理-->新增部门-->"关闭"按钮响应函数
	 */
	$("#close").click(function () {
		$("#addModal", parent.document).find(".close").click();
	});
	
	/***
	 * 部门管理-->新增部门-->"提交"按钮响应函数
	 */
	$("#submit").click(function (){
		
		if($.trim($("#depName").val()) == '') {
			$("#message").show();
			$("#message").text("部门名称不能为空，请输入");
			$('#depName').focus();
			return false;
		}
		
		var url = "/user/dept/addsubmit.htm";
		var param = {};
		param["sysDep.depName"] = $.trim($("#depName").val());
		
		var checkedNodes = $.fn.zTree.getZTreeObj("add_tree_div").getCheckedNodes(true);
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
					$("#message").text("添加部门成功！");
					$("#message").show();
					return true;
				}
				else {
					$("#message").text("添加部门失败:" + json.message );
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
