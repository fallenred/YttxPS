jQuery(function($) {
	
	//保存车辆列表
	var clmap = '';
	$.ajax({
		type: "GET",
		url: "/transport/selectTransport.htm",
		dataType: "json",
		success: function(data){
			var html = '';
			$.each(data, function(commentIndex, comment){
				html += '<option value='+ comment['fsNo'] +'>'+ comment['fsName'] +'</option>';
			});
			clmap = html;
			$(".cl").each(function(){
				var value = $(this).val();
				$(this).html(clmap);
				$(this).val(value);
				$(this).attr("class", "cl chosen-select");
				$(this).chosen(); 
				$(this).next().attr("style","width:160px;");
			});
			setClTotal();
		}
	});
	
	//验证数字
	function validate(node){
		if(node.next().find('[class="validMsg"]')){
			node.next().remove();
		}
		var arr =  node.attr("class").split(" ");
		var digits = false;
		var isFloatGteZero = false;
		var number = false;
		for(var i = 0; i < arr.length; i++){
			arr[i] = arr[i].trim();
			if(arr[i] == 'digits'){
				digits = true;
			} else if(arr[i] == 'isFloatGteZero'){
				isFloatGteZero = true;
			} else if(arr[i] == 'number'){
				number = true;
			}
		}
		if(!(digits || isFloatGteZero || number)){
			return true;
		}
		var value = parseFloat(node.val());
		if(isNaN(value)){
			//请输入数字
			node.after('<span style="color:red;" class="validMsg">请输入数字</span>');
			return false;
		}else{
			if(isFloatGteZero&&(value<0)){
				//请输入非负数
				node.after('<span style="color:red;" class="validMsg">请输入非负数</span>');
				return false;
			}
			if(digits&&!(/(^[0-9]*[1-9][0-9]*$)/.test(value))){
				//请输入整数
				node.after('<span style="color:red;" class="validMsg">请输入整数</span>');
				return false;
			}
		}
		return true;
	}
	
	//计算小计
	function setClSum(children){
		var node = children.parent().parent();
		var clPrice = node.find(".clPrice").val(node.find(".clPrice").val().trim()).val();
		var clCount = node.find(".clCount").val(node.find(".clCount").val().trim()).val();
		if(isNaN(clPrice) || isNaN(clCount) || clPrice == '' || clCount == '')return;
		node.find(".clSum").val((parseFloat(clPrice)*parseFloat(clCount)).toFixed(2));
	}
	
	//计算总计
	function setClTotal(){
		var clTotal = 0;
		$(".clSum").each(function(){
			var clSum = parseFloat($(this).val());
			if(isNaN(clSum) || clSum == '')return;
			clTotal += clSum;
		});
		$("#clTotal").val(clTotal.toFixed(2));
	}
	
	//初始计算
	$(document).on('ready',function(){
		$(".clSum").each(function(){
			setClSum($(this));
		});
		setClTotal();
	});
	
	//事件计算
	$(document).on('change key','.clPrice',function(){
		if(validate($(this))){
			setClSum($(this));
			setClTotal();
		}
	});
	$(document).on('change key','.clCount',function(){
		if($(this).val().trim() == '')$(this).val(1);
		if(validate($(this))){
			setClSum($(this));
			setClTotal();
		}
	});
	
	//添加车辆
	$(document).on('click', '#addCl', function(event){
		index = $("#cl_index").val();
		var data = {
				"cl" : "",
				"clPrice" : "",
				"clCount" : "",
				"clSum" : "",
				"clRemark" : "",
				"index" : index
		};
		var template = Handlebars.compile($("#tr-cllist").html());
		$(this).parent().parent().before(template(data));
		$(".cl:last").html(clmap);
		$(".cl:last").attr("class", "cl chosen-select");
		$(".cl:last").chosen(); 
		$(".cl:last").next().attr("style","width:160px;");
		setClSum($(".cl:last"));
		setClTotal();
		$("#cl_index").val(parseInt(index) + 1);

	});
	
	//删除车辆
	$(document).on('click', '.deleteCl', function(event){
		$(this).parent().parent().remove();
		setClTotal();
	});

});
