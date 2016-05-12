jQuery(function($) {
	//保存娱乐项目列表
	var ylmap = '';
	$.ajax({
		type: "POST",
		url: "/entertainment/selectEntertainment.htm",
		data: {
			"scenicNo" : "",
			"stat" : "1"
		},
		dataType: "json",
		success: function(data){
			var html = ''; 
			$.each(data, function(commentIndex, comment){
				html += '<option value=' + comment['fsNo'] + '>' + comment['fsName'] + '</option>';
			});
			ylmap = html;
			$(".yl").each(function(){
				var value = $(this).val();
				$(this).html(ylmap);
				$(this).val(value);
				$(this).attr("class","yl chosen-select");
				$(this).chosen();
				$(this).next().attr("style","width:150px;");
				$(this).next().next().val($(this).find("option:selected").text());
			});
			setYlTotal();
		}
	});
	
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
	
	//设置娱乐利润
	function setYlProfit(children){
		var node = children.parent().parent();
		var ylFee = node.find(".ylFee").val(node.find(".ylFee").val().trim()).val();
		var ylCost = node.find(".ylCost").val(node.find(".ylCost").val().trim()).val();
		var ylCount = node.find(".ylCount").val(node.find(".ylCount").val().trim()).val();
		var proportion = node.find(".proportion").val(node.find(".proportion").val().trim()).val();
		if(isNaN(ylFee) || isNaN(ylCost) || isNaN(proportion) || ylFee == '' || ylCost == '' || proportion == '')return;
		profit = ((parseFloat(ylFee) - parseFloat(ylCost))*parseFloat(ylCount)*parseFloat(proportion)*parseFloat(0.01)).toFixed(2)
		node.find(".ylProfit").val(profit);
	}
	
	//设置娱乐总计
	function setYlTotal(){
		var ylTotal = 0;
		$(".ylProfit").each(function(){
			var value = parseFloat($(this).val());
			if(isNaN(value) || value == '')return;
			ylTotal += value;
		});
		$("#ylTotal").val(ylTotal.toFixed(2));
	}
	
	//初始计算
	$(document).on('ready',function(){
		$(".ylProfit").each(function(){
			setYlProfit($(this));
		});
		setYlTotal();
	});
	$(document).on('change key', '.ylCount',function(){
		$(this).val($(this).val().trim());
		$(this).valid();
	});
	
	//隐藏域值设置
	$(document).on('change key', '.yl', function(){
		$(this).next().next().val($(this).find("option:selected").text());
	});
	
	//事件计算
	$(document).on('change key','.ylFee,.ylCost,.proportion,.ylCount',function(){
		if($(this).val().trim() == '')$(this).val(0);
		if($(this).valid()){
			setYlProfit($(this));
			setYlTotal();
		}
	});
/*	$(document).on('change key','.ylCost',function(){
		if($(this).val().trim() == '')$(this).val(0);
		if(validate($(this))){
			setYlProfit($(this));
			setYlTotal();
		}
	});*/
	
	//添加酒店
	$(document).on('click', '#addYl', function(event){
		index = $("#yl_index").val();
		var data = {
				"yl" : "",
				"ylCount" : "",
				"ylFee" : "",
				"ylCost" : "",
				"ylProfit" : "",
				"ylRemark" : "",
				"index" : index
		};
		var template = Handlebars.compile($("#tr-yllist").html());
		$(this).parent().parent().before(template(data));
		$(".yl:last").html(ylmap);
		$(".yl:last").attr("class", "yl chosen-select");
		$(".yl:last").chosen(); 
		$(".yl:last").next().attr("style","width:150px;");
		$(".yl:last").next().next().val($(".yl:last").find("option:selected").text());
		setYlProfit($(".yl:last"));
		setYlTotal();
		$("#yl_index").val(parseInt(index) + 1);
		$('.datetimepicker').daterangepicker({
			singleDatePicker: true,
			locale : {
				format: 'YYYY-MM-DD',
				applyLabel : '确定',
				cancelLabel : '取消',
				fromLabel : '起始时间',
				toLabel : '结束时间',
				customRangeLabel : '自定义',
				daysOfWeek : [ '日', '一', '二', '三', '四', '五', '六' ],
				monthNames : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月' ],
				firstDay : 1
			},
	        startDate: moment().subtract(6, 'days')
		});
	});

	//删除车辆
	$(document).on('click', '.deleteYl', function(event){
		$(this).parent().parent().remove();
		setYlTotal();
	});

});
