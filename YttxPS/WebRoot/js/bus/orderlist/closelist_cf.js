jQuery(function($) {
	
	//保存餐类别
	type = '';
	type += '<option value="01">早餐</option>';
	type += '<option value="02">午餐</option>';
	type += '<option value="03">晚餐</option>';
	
	//保存菜单
	var cfmap = '';
	$.ajax({
		type: "POST",
		traditional: true,
		url: "/restaurant/selectRestaurant.htm",
		data:{
			"stat": "1"
		},
		dataType: "json",
		success: function(data){
			var html = '';
			$.each(data, function(commentIndex, comment){
				html += '<option value='+ comment['no'] +'>'+ comment['name'] +'</option>';
			});
			cfmap = html;
			$(".cf").each(function(){
				var value = $(this).val();
				$(this).html(cfmap);
				$(this).val(value);
				$(this).attr("class", "cf chosen-select");
				$(this).chosen(); 
				$(this).next().attr("style","width:160px;");
				$(this).next().next().val($(this).find("option:selected").text());
				var typeNode = $(this).parent().next().find(".cfType");
				var typeValue = typeNode.val();
				typeNode.html(type);
				typeNode.val(typeValue);
				typeNode.attr("class", "cf chosen-select");
				typeNode.chosen(); 
				typeNode.next().attr("style","width:100px;");
				
			});
			setCfTotal();
		}
	});
	
/*	//验证数字
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
	*/
	//计算小计
	function setCfSum(children){
		var node = children.parent().parent();
		var cfPrice = node.find(".cfPrice").val(node.find(".cfPrice").val().trim()).val();
		var cfCount = node.find(".cfCount").val(node.find(".cfCount").val().trim()).val();
		if(isNaN(cfPrice) || isNaN(cfCount) || cfPrice == '' || cfCount == '')return;
		node.find(".cfSum").val((parseFloat(cfPrice)*parseFloat(cfCount)).toFixed(2));
	}
	
	//计算总计
	function setCfTotal(){
		var cfTotal = 0;
		$(".cfSum").each(function(){
			var cfSum = parseFloat($(this).val());
			if(isNaN(cfSum) || cfSum == '')return;
			cfTotal += cfSum;
		});
		$("#cfTotal").val(cfTotal.toFixed(2));
	}
	
	//初始计算
	$(document).on('ready',function(){
		$(".cfSum").each(function(){
			setCfSum($(this));
		});
		setCfTotal();
	});
	
	//隐藏域值设置
	$(document).on('change key', '.cf', function(){
		$(this).next().next().val($(this).find("option:selected").text());
	});
	
	//事件计算
	$(document).on('change key','.cfPrice',function(){
		if($(this).valid()){
			setCfSum($(this));
			setCfTotal();
		}
	});
	$(document).on('change key','.cfCount',function(){
		if($(this).val().trim() == '')$(this).val(1);
		if($(this).valid()){
			setCfSum($(this));
			setCfTotal();
		}
	});
	
	//添加餐费
	$(document).on('click', '#addCf', function(event){
		index = $("#cf_index").val();
		var data = {
				"cf" : "",
				"cfPrice" : "",
				"cfCount" : "",
				"cfSum" : "",
				"cfRemark" : "",
				"index" : index
		};
		var template = Handlebars.compile($("#tr-cflist").html());
		$(this).parent().parent().before(template(data));
		$(".cf:last").html(cfmap);
		$(".cf:last").attr("class", "cf chosen-select");
		$(".cf:last").chosen(); 
		$(".cf:last").next().attr("style","width:160px;");
		var typeNode = $(".cf:last").parent().next().find(".cfType");
		typeNode.html(type);
		typeNode.attr("class", "cfType chosen-select");
		typeNode.chosen(); 
		typeNode.next().attr("style","width:100px;");
		$(".cf:last").next().next().val($(".cf:last").find("option:selected").text());
		setCfSum($(".cf:last"));
		setCfTotal();
		$("#cf_index").val(parseInt(index) + 1);
		$('.datetimepicker').datetimepicker({
			language:  'zh-CN',
	        weekStart: 1,
	        todayBtn:  1,
			autoclose: true,
			todayHighlight: 1,
			startView: 2,
			minView: 2,
			forceParse: 0
		});

	});
	
	//删除餐费
	$(document).on('click', '.deleteCf', function(event){
		$(this).parent().parent().remove();
		setCfTotal();
	});

});
