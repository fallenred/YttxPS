jQuery(function($) {
	
	var no = $.getUrlParam('no');
	$("#no").val(no);
	
	// 查询该资源对应的所有图片并显示
	showpics(no);
	
	//	重置
	$("#reset").on("click", function() {
			$("#belongtype").val(null);
			$("#subtype").val(null);
			$("#index").val(null);
			$("#message").hide();
			$("#message").text("");
		});
	
	// 关闭
	$("#close").on("click", function () {
		$("#picModal", parent.document).find(".close").click();
	});

	//	文件上传
	$("#upfileBtn").on("click", function() {
		console.log($("#message"));
		if($("#belongtype").val() == "") {
			$("#message").show();
			$("#message").text("文件上传必须输入归属资源代码，请检查输入！");
			$('#belongtype').focus();
			return;
		}
		
		$("#upfileModal").modal({
		    remote: "/jsp/pic/add.jsp"
		});
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

	function showpics(no) {
		var json = 'page=1&rows=99&pic.no=' + $.getUrlParam('no');
		$.ajax({
		     type: 'POST',
		     url: '/pic/findPic.htm' ,
		     data: json,
		     success: function(data){
						if(data.result == "ok") {
							$("#message").text("删除记录成功");
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
		/*
		<li><a class="cboxElement" data-rel="colorbox"
			href="http://127.0.0.1:81/1.jpg"> <img width="150"
				height="150" src="http://127.0.0.1:81/2.png" alt="150*150">
		</a>
		<div class="tools tools-bottom">

					<a href="#">
						<i class="ace-icon fa fa-times red"></i>
					</a>
				</div>
		</li>
		*/
	}
	
});
