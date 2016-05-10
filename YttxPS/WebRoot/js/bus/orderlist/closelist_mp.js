jQuery(function($) {
	
	//保存门票列表
	var mpmap = '';
	$.ajax({
		type: "GET",
		url: "/ticket/selectTicket.htm",
		dataType: "json",
		success: function(data){
			var html = '';
			$.each(data, function(commentIndex, comment){
				html += '<option value=' + comment['fsNo'] + '>' + comment['fsName'] + '</option>';
			});
			mpmap = html;
			$(".mp").each(function(){
				var value = $(this).val();
				$(this).html(mpmap);
				$(this).val(value);
				getMpType($(this));
				$(this).attr("class", "mp chosen-select");
				//mp.chosen("destroy");
				$(this).chosen(); 
				$(this).next().attr("style","width:160px;");
				$(".mp:last").next().next().val($(".mp:last").find("option:selected").text());
			});
			setMpTotal();
		}
	});
	
	//获取门票类型
	function getMpType(node){
		$.ajax({
			type: "GET",
			url: "/dict/selectDict.htm",
			data: {
				"dict.fsParentno": "mp",
			},
			dataType: "json",
			success: function(data){
				var html = '';
				$.each(data,function(commentIndex,comment){
					html += '<option value=' + comment['fsDictno'] + '>' + comment['fsDictname'] + '</option>';
				});
				var mpTypeNode = node.parent().next().find(".mpType");
				var value = mpTypeNode.val();
				mpTypeNode.html(html);
				if(value == '' || value == null){
					value = '01';
				}
				mpTypeNode.val(value);
				mpTypeNode.attr("class","mpType chosen-select");
				//mpTypeNode.chosen("destroy");
				mpTypeNode.chosen();
				mpTypeNode.next().attr("style","width:140px;");
				mpTypeNode.next().next().val(mpTypeNode.find("option:selected").text());
				
			}
		});
	}
	
/*	//数字验证
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
	}*/
	
	//设置门票小计
	function setMpSum(children){
		var node = children.parent().parent();
		var mpPrice = node.find(".mpPrice").val(node.find(".mpPrice").val().trim()).val();
		var mpCount = node.find(".mpCount").val(node.find(".mpCount").val().trim()).val();
		if(isNaN(mpPrice) || isNaN(mpCount) || mpPrice == '' || mpCount == '')return;
		node.find(".mpSum").val((parseFloat(mpPrice)*parseFloat(mpCount)).toFixed(2));
	}
	
	//设置门票总计
	function setMpTotal(){
		var mpTotal = 0;
		$(".mpSum").each(function(){
			var value = parseFloat($(this).val());
			if(isNaN(value) || value == '')return;
			mpTotal += value;
		});
		$("#mpTotal").val(mpTotal.toFixed(2));
	}
	
	//初始计算
	$(document).on('ready',function(){
		$(".mpSum").each(function(){
			setMpSum($(this));
		});
		setMpTotal();
	});
	
	//设置隐藏域
	$(document).on('change key', '.mp', function(){
		$(this).next().next().val($(this).find("option:selected").text());
	});
	$(document).on('change key', '.mpType', function(){
		$(this).next().next().val($(this).find("option:selected").text());
	});
	
	//事件计算
	$(document).on('change key','.mpPrice',function(){
		if($(this).valid()){
			setMpSum($(this));
			setMpTotal();
		}
	});
	$(document).on('change key','.mpCount',function(){
		if($(this).val().trim() == '')$(this).val(1);
		if($(this).valid()){
			setMpSum($(this));
			setMpTotal();
		}
	});
	
	//添加门票
	$(document).on('click', '#addMp', function(event){
		index = $("#mp_index").val();
		var data = {
				"mp" : "",
				"mpDate" : "",
				"mpPrice" : "",
				"mpCount" : "",
				"mpSum" : "",
				"mpRemark" : "",
				"index" : index
		};
		var template = Handlebars.compile($("#tr-mplist").html());
		$(this).parent().parent().before(template(data));
		$(".mp:last").html(mpmap);
		$(".mp:last").attr("class", "mp chosen-select");
		$(".mp:last").chosen(); 
		$(".mp:last").next().attr("style","width:160px;");
		getMpType($(".mp:last"));
		$(".mp:last").next().next().val($(".mp:last").find("option:selected").text());
		setMpSum($(".mp:last"));
		setMpTotal();
		$("#mp_index").val(parseInt(index) + 1);
	});

	//删除门票
	$(document).on('click', '.deleteMp', function(event){
		$(this).parent().parent().remove();
		setMpTotal();
	});
	
});
