jQuery(function($) {
	
	//保存酒店列表
	var jdmap = '';
	$.ajax({
		type: "GET",
		traditional: true,
		url: "/accomadation/selectAccomadation2.htm",
		data: {
			"accomadation.stat": "1"
		},
		dataType: "json",
		success: function(data){
			var html = '';
			if (data == '' || data == null) {
				html += '<option>' + "未查询到酒店" + '</option>';
			} else {
				$.each(data, function(commentIndex, comment){
				html += '<option value='+ comment['no'] +'>'+ comment['name'] +'</option>';
				});
			}
			jdmap = html;
			$(".jd").each(function(){
				var value = $(this).val();
				$(this).html(jdmap);
				$(this).val(value);
				$(this).attr("class", "jd chosen-select");
				$(this).chosen(); 
				$(this).next().attr("style","width:160px;");
				getRoom($(this));
				$(this).next().next().val($(this).find("option:selected").text());
			});
			setJdTotal();
		}
	});
/*	$(document).on('change key','.jd',function(){
		getRoom($(this));
	});*/
	
	//获取房型列表
	function getRoom(node){
		$.ajax({
			type: "GET",
			url: "/dict/selectDict.htm",
			data: {
				"dict.fsParentno": "fx",
			},
			dataType: "json",
			success: function(data){
				var html = '';
				$.each(data,function(commentIndex,comment){
					html += '<option value=' + comment['fsDictno'] + '>' + comment['fsDictname'] + '</option>';
				});
				var roomNode = node.parent().next().find(".jdType");
				var value = roomNode.val();
				roomNode.html(html);
				if(value == null || value == ''){
					value = '01';
				}
				roomNode.val(value);
				roomNode.attr("class","jdType chosen-select");
				//roomNode.chosen("destroy");
				roomNode.chosen();
				roomNode.next().attr("style","width:140px;");
				roomNode.next().next().val(roomNode.find("option:selected").text());
			}
		});
	}
	
	//数字验证
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
	
	//设置酒店小计
	function setJdSum(children){
		var node = children.parent().parent();
		var jdPrice = node.find(".jdPrice").val(node.find(".jdPrice").val().trim()).val();
		var jdCount = node.find(".jdCount").val(node.find(".jdCount").val().trim()).val();
		if(isNaN(jdPrice) || isNaN(jdCount) || jdPrice == '' || jdCount == '')return;
		node.find(".jdSum").val((parseFloat(jdPrice)*parseFloat(jdCount)).toFixed(2));
	}
	
	//设置酒店总计
	function setJdTotal(){
		var jdTotal = 0;
		$(".jdSum").each(function(){
			var value = parseFloat($(this).val());
			if(isNaN(value) || value == '')return;
			jdTotal += value;
		});
		$("#jdTotal").val(jdTotal.toFixed(2));
	}
	
	//设置隐藏域
	$(document).on('change key', '.jd', function(){
		$(this).next().next().val($(this).find("option:selected").text());
	});
	$(document).on('change key', '.jdType', function(){
		$(this).next().next().val($(this).find("option:selected").text());
	});
	
	//初始计算
	$(document).on('ready',function(){
		$(".jdSum").each(function(){
			setJdSum($(this));
		});
		setJdTotal();
	});
	
	//事件计算
	$(document).on('change key','.jdPrice',function(){
		if(validate($(this))){
			setJdSum($(this));
			setJdTotal();
		}
	});
	$(document).on('change key','.jdCount',function(){
		if($(this).val().trim() == '')$(this).val(1);
		if(validate($(this))){
			setJdSum($(this));
			setJdTotal();
		}
	});
	
	//添加酒店
	$(document).on('click', '#addJd', function(event){
		index = $("#jd_index").val();
		var data = {
				"jd" : "",
				"jdDate" : "",
				"jdPrice" : "",
				"jdCount" : "",
				"jdSum" : "",
				"jdRemark" : "",
				"index" : index
		};
		var template = Handlebars.compile($("#tr-jdlist").html());
		$(this).parent().parent().before(template(data));
		$(".jd:last").html(jdmap);
		$(".jd:last").attr("class", "jd chosen-select");
		$(".jd:last").chosen(); 
		$(".jd:last").next().attr("style","width:160px;");
		getRoom($(".jd:last"));
		$(".jd:last").next().next().val($(".jd:last").find("option:selected").text());
		
		setJdSum($(".jd:last"));
		setJdTotal();
		$("#jd_index").val(parseInt(index) + 1);
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

	//删除酒店
	$(document).on('click', '.deleteJd', function(event){
		$(this).parent().parent().remove();
		setJdTotal();
	});
});
