/**
 * 找到资源类型和下属资源代码的所有图片并显示出来
 */
function findpics(){
	var json={};
	json["resNo"]=$("#resNo").val();
	json["resType"]=$("#resType").val();
	$.ajax({
	     type: 'POST',
	     url: '/pic/findPic.htm' ,
	     data: json,
	     success: function(data){
					if(data.result == "ok") {
						$("#message").text("查询记录成功");
						showpics(data.data);
						return true;
					}
					else {
						$("#message").text("查询记录失败:" + data.message );
						return false;
					}
					return false;
				} ,
	    dataType: 'json',
	});
}



/**
 * 文件上传弹出框（图片选择器）关闭时的响应函数：向后台提交数据
 */
$("#upfileModal").on("hidden.bs.modal", function(){
    $(this).removeData("bs.modal");
    findpics();//重新加载图片
});


/**
 * 拖拽插件--删除链接
 */
function deleteImg(id){
    var $this = $("#"+id);
    var $wrapper =  $this.closest('.imgblock');
    var id=$wrapper.attr("data-id");
    var srcFile = $wrapper.attr("data-srcFile");
    //alert("当前图片的index："+id+",srcFile:"+srcFile);
    
    //封装参数
    var param = {};
    param.index = id;
    param.srcfile = srcFile;
    $("body").data("img_id",id);
    
    //ajax调用
    var url="/pic/delPic.htm";
    $.ajax({
	     type: 'POST',
	     url:url,
	     data:param,
	     success: function(data){
					if(data.result == "ok"){
						$("#message").text("删除图片成功");
						var img_id=$("body").data("img_id");
						var imgWrapperId="img_wrapper_"+img_id;
						$("#"+imgWrapperId).remove();
						return $("#image-panel").gridly("layout");
					}
					else {
						$("#message").text("删除图片失败:" + json.message );
						return false;
					}
					return false;
				} ,
	    dataType: 'json',
	});
}

/**
 * 拖拽插件--拖拽后调用
 */
var reordered = function($elements) {//拖拽后调用
	var arr = $elements;
	if(arr.length<=1){//如果只有一张图片不用改变序号直接返回
		return false;
	}
	var param={};
	for (i = 0; i < arr.length; i++){
		var $wrapper=$("#"+arr[i].id);
		if(i === 0 ){
			param["piclist["+i+"].main"]=1;
		}else{
			param["piclist["+i+"].main"]=0;
		}
		param["piclist["+i+"].index"] =  $wrapper.attr("data-id");
		param["piclist["+i+"].seq"] =  i;
		param["piclist["+i+"].resType"] = $("#resType").val();
		param["piclist["+i+"].resNo"] = $("#resNo").val();
	}
	//console.log(param);
	
	var url="/pic/updateSeqs.htm";
	 $.ajax({
	     type: 'POST',
	     url:url,
	     data:param,
	     success: function(data){
					if(data.result == "ok"){
					}
					else {
						$("#message").text("修改图片显示顺序失败:" + json.message );
						return false;
					}
					return false;
				} ,
	    dataType: 'json',
	});
	
	
};

function showpics(pics) {
	var tpt=$("#imgTpt").html();
	var template = Handlebars.compile(tpt);
	var html= template(pics);	
	$("#image-panel").html(html)
	$('#image-panel').gridly({
	      callbacks: {reordered: reordered}
	});
}

jQuery(function($){
	//查询图片并且显示出来
	findpics();
	//	文件上传
	$("#upfileBtn").on("click", function() {
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
		$("#upfileModal").modal({
		    remote: "/jsp/pic/add.jsp"
		});
	});
});
