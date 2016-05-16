jQuery(function($) {
	
	//保存其他支出
	var qtzcmap = '';
	$.ajax({
		type: "GET",
		url: "/dict/selectDict.htm",
		data: {
			"dict.fsParentno": "other_exp",
		},
		dataType: "json",
		success: function(data){
			var html = '';
			$.each(data, function(commentIndex, comment){
				html += '<option value=' + comment['fsDictno'] + '>' + comment['fsDictname'] + '</option>';
			});
			qtzcmap = html;
			$(".qtzc").each(function(){
				var value = $(this).val();
				$(this).html(qtzcmap);
				$(this).val(value);
				$(this).attr("class", "qtzc chosen-select");
				$(this).chosen(); 
				$(this).next().attr("style","width:160px;");
				$(this).next().next().val($(this).find("option:selected").text());
				$(".qtzc:last").next().next().val($(".qtzc:last").find("option:selected").text());
			});
//			setQtzcTotal();
		}
	});
	
	//验证数字
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
	function setQtzcSum(children){
		var node = children.parent().parent();
		var qtzcPrice = node.find(".qtzcPrice").val(node.find(".qtzcPrice").val().trim()).val();
		var qtzcCount = node.find(".qtzcCount").val(node.find(".qtzcCount").val().trim()).val();
		if(isNaN(qtzcPrice) || isNaN(qtzcCount) || qtzcPrice == '' || qtzcCount == '')return;
		node.find(".qtzcSum").val((parseFloat(qtzcPrice)*parseFloat(qtzcCount)).toFixed(2));
	}
	
	//计算总计
	function setQtzcTotal(){
		var qtzcTotal = 0;
		$(".qtzcSum").each(function(){
			var qtzcSum = parseFloat($(this).val());
			if(isNaN(qtzcSum) || qtzcSum == '')return;
			qtzcTotal += qtzcSum;
		});
		$("#qtzcTotal").val(qtzcTotal.toFixed(2));
		getExpenditure();
	}
	
	//初始计算
	$(document).on('ready',function(){
		$(".qtzcSum").each(function(){
			setQtzcSum($(this));
		});
//		setQtzcTotal();
	});
	
	//隐藏域值设置
	$(document).on('change key', '.qtzc', function(){
		$(this).next().next().val($(this).find("option:selected").text());
	});
	
	//事件计算
	$(document).on('change key','.qtzcPrice',function(){
		if($(this).valid()){
			setQtzcSum($(this));
			setQtzcTotal();
		}
	});
	$(document).on('change key','.qtzcCount',function(){
		if($(this).val().trim() == '')$(this).val(1);
		if($(this).valid()){
			setQtzcSum($(this));
			setQtzcTotal();
		}
	});
	
	//添加其他支出
	$(document).on('click', '#addQtzc', function(event){
		index = $("#qtzc_index").val();
		var data = {
				"qtzc" : "",
				"qtzcPrice" : "",
				"qtzcCount" : "",
				"qtzcSum" : "",
				"qtzcRemark" : "",
				"index" : index
		};
		var template = Handlebars.compile($("#tr-qtzclist").html());
		$(this).parent().parent().before(template(data));
		$(".qtzc:last").html(qtzcmap);
		$(".qtzc:last").attr("class", "qtzc chosen-select");
		$(".qtzc:last").chosen(); 
		$(".qtzc:last").next().attr("style","width:160px;");
		$(".qtzc:last").next().next().val($(".qtzc:last").find("option:selected").text());
		setQtzcSum($(".qtzc:last"));
//		setQtzcTotal();
		$("#qtzc_index").val(parseInt(index) + 1);

	});
	
	//删除其他支出
	$(document).on('click', '.deleteQtzc', function(event){
		$(this).parent().parent().remove();
		setQtzcTotal();
	});

});
