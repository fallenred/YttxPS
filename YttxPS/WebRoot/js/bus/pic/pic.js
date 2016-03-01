/**
 * 文件上传弹出框（图片选择器）关闭时的响应函数：向后台提交数据
 */
$("#upfileModal").on("hidden.bs.modal", function(){
    $(this).removeData("bs.modal");
    $("#submit").click();
});

var delpic=function(index, path) {
	$.ajax({
	     type: 'POST',
	     url: '/pic/delPic.htm' ,
	     data: 'index=' + index + '&srcFile=' + path,
	     success: function(data){
					if(data.result == "ok") {
						$("#message").text("删除记录成功");
						$("#submit").click();
						return true;
					}
					else {
						$("#message").text("删除记录失败:" + json.message );
						return false;
					}
					return false;
				} ,
	    dataType: 'json',
	});
};

jQuery(function($){
	
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
	
	reloadbox();
	
	//查询图片并且显示出来
	findpics();
	
	//	重置
	$("#reset").on("click", function() {
			$("#belongtype").val(null);
			$("#subtype").val(null);
			$("#index").val(null);
			$("#message").hide();
			$("#message").text("");
			$("#colorbox").hide();
		});
	
	// 关闭
	$("#close").on("click", function () {
		$("#picModal", parent.document).find(".close").click();
	});

	//	文件上传
	$("#upfileBtn").on("click", function() {
		$("#colorbox").hide();
		if($("#resType").val() == "") {
			$("#message").show();
			$("#message").text("文件上传必须输入归属资源代码，请检查输入！");
			$('#resType').focus();
			return;
		}
		if($("#resNo").val() == "") {
			$("#message").show();
			$("#message").text("文件上传必须输入资源代码，请检查输入！");
			$('#resNo').focus();
			return;
		}
		if($("#seq").val() == "") {
			$("#message").show();
			$("#message").text("文件上传必须输入序号，请检查输入！");
			$('#seq').focus();
			return;
		}
		if($("#main").val() == "") {
			$("#message").show();
			$("#message").text("文件上传必须输入是否主图，请检查输入！");
			$('#main').focus();
			return;
		}
		$("#upfileModal").modal({
		    remote: "/jsp/pic/add.jsp"
		});
	});
	
	$("#submit").on("click", function () {
		$("#colorbox").show();
		if($("#no").val() == '') {
			$("#message").show();
			$("#message").text("图片资源编码不能为空，请输入");
			$('#no').focus();
			return false;
		} 
		$("#colorbox").children("li").remove();
		findpics();
	});
	
	
	function reloadbox() {
		$('.ace-thumbnails [data-rel="colorbox"]').colorbox(colorbox_params);
		$("#cboxLoadingGraphic").html("<i class='ace-icon fa fa-spinner orange'></i>");
	}
	
	
	var showpics=function(pics) {
		for(var i=0;i<pics.length;i++){
			var item = '<li><a class="cboxElement" data-rel="colorbox" href="' + pics[i]["srcFile"] + '">' +
				'<img width="150" height="150" src="' + pics[i]["srcFile"] + '" alt="150*150"></a>' +
				'<div class="tools tools-bottom"><a href="#" onclick="delpic(' + pics[i]["index"] + ',\'' + pics[i]["srcFile"] + '\')' +  
				'"><i class="ace-icon fa fa-times red"></i></a></div></li>';
			$("#colorbox").append(item);
		}
		reloadbox();
	}
	
	/**
	 * 找到资源类型和下属资源代码的所有图片并显示出来
	 */
	function findpics(){
		var json = 'page=1&rows=99&pic.no=' + $("#no").val() +
					'&pic.belongtype=' + $("#belongtype").val() +
					'&pic.subtype=' + $("#subtype").val();
		$.ajax({
		     type: 'POST',
		     url: '/pic/findPic.htm' ,
		     data: json,
		     success: function(data){
						if(data.result == "ok") {
							$("#message").text("查询记录成功");
							showpics(data.rows);
							return true;
						}
						else {
							$("#message").text("查询记录失败:" + json.message );
							return false;
						}
						return false;
					} ,
		    dataType: 'json',
		});
	}
});
