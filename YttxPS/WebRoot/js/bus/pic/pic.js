/**
 * 文件上传弹出框（图片选择器）关闭时的响应函数：向后台提交数据
 */
$("#upfileModal").on("hidden.bs.modal", function(){
    $(this).removeData("bs.modal");
    $("#submit").click();
});

/**
 * 将找到的图片显示出来
 */
function showpics(pics){
	for(var i=0;i<pics.length;i++){
		var item = '<li>'+
				   	'<a class="cboxElement" data-rel="colorbox" href="' + pics[i]["srcFile"] + '">' +
						'<img width="150" height="150" src="' + pics[i]["srcFile"] + '" alt="150*150">'+
					'</a>' +
					'<div class="tools tools-bottom">'+
						'<a href="#" onclick="delpic(' + pics[i]["index"] + ',\'' + pics[i]["srcFile"] + '\')">'+
							'<i class="ace-icon fa fa-times red"></i>'+
						'</a>'+
					'</div>'+
				'</li>';
		$("#colorbox").append(item);
	}
	reloadbox();
}

/**
 * 找到资源类型和该资源代码的所有图片并显示出来
 */
function findpics(){
	//封装查询条件
	var data={};
	data["page"]=1;
	data["rows"]=1000;
	data["pic.resType"]=$("#resType").val()
	data["pic.resNo"]=$("#resNo").val()
	
	$.ajax({
	     type: 'POST',
	     url: '/pic/findPic.htm' ,
	     data: data,
	     success: function(data){
					if(data.result == "ok") {
						$("#message").text("查询记录成功");
						$("#colorbox").empty();
						showpics(data.rows);//将查询出来的图片展示出来
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

var delpic=function(index, path){
	$.ajax({
	     type: 'POST',
	     url: '/pic/delPic.htm' ,
	     data: 'index=' + index + '&srcFile=' + path,
	     success: function(data){
					if(data.result == "ok") {
						$("#message").text("删除记录成功");
						findpics();
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
	//从url中获取资源类型和资源代码
	var resNo = $.getUrlParam('resNo');//归属资源编号
	var resType = $.getUrlParam('resType');//归属资源类型
	$("#resType").val(resType);
	$("#resNo").val(resNo);
	
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
	
	//重置
	$("#reset").on("click", function() {
		$("#message").hide();
		$("#message").text("");
		$("#colorbox").hide();
	});
	
	// 关闭
	$("#close").on("click", function () {
		$("#picModal", parent.document).find(".close").click();
	});

	//文件上传按钮
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
	
	$("#submit").on("click", function (){
		if($("#no").val() == '') {
			$("#message").show();
			$("#message").text("图片资源编码不能为空，请输入");
			$('#no').focus();
			return false;
		}
		$("#colorbox").show();
		$("#colorbox").children("li").remove();
		findpics();
	});
	
	
	function reloadbox() {
		$('.ace-thumbnails [data-rel="colorbox"]').colorbox(colorbox_params);
		$("#cboxLoadingGraphic").html("<i class='ace-icon fa fa-spinner orange'></i>");
	}
});
