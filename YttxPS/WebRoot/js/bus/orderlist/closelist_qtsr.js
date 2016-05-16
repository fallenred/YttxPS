jQuery(function($) {
	
	//保存其他收入
	var qtsrmap = '';
	$.ajax({
		type: "GET",
		url: "/dict/selectDict.htm",
		data: {
			"dict.fsParentno": "other_ico",
		},
		dataType: "json",
		success: function(data){
			var html = '';
			$.each(data, function(commentIndex, comment){
				html += '<option value=' + comment['fsDictno'] + '>' + comment['fsDictname'] + '</option>';
			});
			qtsrmap = html;
			$(".qtsr").each(function(){
				var value = $(this).val();
				$(this).html(qtsrmap);
				$(this).val(value);
				$(this).attr("class", "qtsr chosen-select");
				$(this).chosen(); 
				$(this).next().attr("style","width:160px;");
				$(this).next().next().val($(this).find("option:selected").text());
				$(".qtsr:last").next().next().val($(".qtsr:last").find("option:selected").text());
			});
//			setQtsrTotal();
		}
	});
	
//	//验证数字
//	function validate(node){
//		if(node.next().find('[class="validMsg"]')){
//			node.next().remove();
//		}
//		var arr =  node.attr("class").split(" ");
//		var digits = false;
//		var isFloatGteZero = false;
//		var number = false;
//		for(var i = 0; i < arr.length; i++){
//			arr[i] = arr[i].trim();
//			if(arr[i] == 'digits'){
//				digits = true;
//			} else if(arr[i] == 'isFloatGteZero'){
//				isFloatGteZero = true;
//			} else if(arr[i] == 'number'){
//				number = true;
//			}
//		}
//		if(!(digits || isFloatGteZero || number)){
//			return true;
//		}
//		var value = parseFloat(node.val());
//		if(isNaN(value)){
//			//请输入数字
//			node.after('<span style="color:red;" class="validMsg">请输入数字</span>');
//			return false;
//		}else{
//			if(isFloatGteZero&&(value<0)){
//				//请输入非负数
//				node.after('<span style="color:red;" class="validMsg">请输入非负数</span>');
//				return false;
//			}
//			if(digits&&!(/(^[0-9]*[1-9][0-9]*$)/.test(value))){
//				//请输入整数
//				node.after('<span style="color:red;" class="validMsg">请输入整数</span>');
//				return false;
//			}
//		}
//		return true;
//	}
	
	//计算小计
	function setQtsrSum(children){
		var node = children.parent().parent();
		var qtsrPrice = node.find(".qtsrPrice").val(node.find(".qtsrPrice").val().trim()).val();
		var qtsrCount = node.find(".qtsrCount").val(node.find(".qtsrCount").val().trim()).val();
		if(isNaN(qtsrPrice) || isNaN(qtsrCount) || qtsrPrice == '' || qtsrCount == '')return;
		node.find(".qtsrSum").val((parseFloat(qtsrPrice)*parseFloat(qtsrCount)).toFixed(2));
	}
	
	//计算总计
	function setQtsrTotal(){
		var qtsrTotal = 0;
		$(".qtsrSum").each(function(){
			var qtsrSum = parseFloat($(this).val());
			if(isNaN(qtsrSum) || qtsrSum == '')return;
			qtsrTotal += qtsrSum;
		});
		$("#qtsrTotal").val(qtsrTotal.toFixed(2));
		getIncome();
	}
	
	//初始计算
	$(document).on('ready',function(){
		$(".qtsrSum").each(function(){
			setQtsrSum($(this));
		});
//		setQtsrTotal();
	});
	
	//隐藏域值设置
	$(document).on('change key', '.qtsr', function(){
		$(this).next().next().val($(this).find("option:selected").text());
	});
	
	//事件计算
	$(document).on('change key','.qtsrPrice',function(){
		if($(this).valid()){
			setQtsrSum($(this));
			setQtsrTotal();
		}
	});
	$(document).on('change key','.qtsrCount',function(){
		if($(this).val().trim() == '')$(this).val(1);
		if($(this).valid()){
			setQtsrSum($(this));
			setQtsrTotal();
		}
	});
	
	//添加其他收入
	$(document).on('click', '#addQtsr', function(event){
		index = $("#qtsr_index").val();
		var data = {
				"qtsr" : "",
				"qtsrPrice" : "",
				"qtsrCount" : "",
				"qtsrSum" : "",
				"qtsrRemark" : "",
				"index" : index
		};
		var template = Handlebars.compile($("#tr-qtsrlist").html());
		$(this).parent().parent().before(template(data));
		$(".qtsr:last").html(qtsrmap);
		$(".qtsr:last").attr("class", "qtsr chosen-select");
		$(".qtsr:last").chosen(); 
		$(".qtsr:last").next().attr("style","width:160px;");
		$(".qtsr:last").next().next().val($(".qtsr:last").find("option:selected").text());
		setQtsrSum($(".qtsr:last"));
//		setQtsrTotal();
		$("#qtsr_index").val(parseInt(index) + 1);

	});
	
	//删除其他收入
	$(document).on('click', '.deleteQtsr', function(event){
		$(this).parent().parent().remove();
		setQtsrTotal();
	});

});
