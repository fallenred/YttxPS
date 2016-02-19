$(document).ready(function(){
	/**
	 * 权限--zTree的初始化参数
	 */
	var setting = {
	        check: {
	            enable: false
	        },
	        data: {
	            simpleData: {
	                enable: true
	            }
	        }
	    };

	/**
	 * 权限--构建权限树型菜单
	 */
	var zNodes = menuList;
	zNodes.splice(zNodes.length-1,1);
	$.fn.zTree.init($("#show_tree_div"), setting, zNodes).expandAll(true);

	/**
	 * 用户管理-->查看弹出框-->"关闭"按钮响应函数
	 */
	$("#close").click(function () {
		$("#showModal", parent.document).find(".close").click();
	});
	
	//--------------------------------------------------------------------------------------
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