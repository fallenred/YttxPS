$(function(){
	$(document).on("keydown",function(event){
		if(event.keyCode==13){
			$("#loginbtn").click();
		}
	})
	var rememberflag = $.cookie("rememberflag");
	if(rememberflag == null){
		rememberflag = "false";
	}
	if (rememberflag == "true") {
		$("#rememberflag").attr("checked", true);
		$("#userid").val($.cookie("userid"));
	}
	
	$('#login-box').on('click', '#loginbtn', function(event){
		$('.login-msg span').text('');
		if($('#userid').val() == ''){
			$('.login-msg span').text('登录名不能为空');
			$('#userid').focus();
			event.preventDefault();
			return false;
		}
		if($('#password').val() == ''){
			$('.login-msg span').text('登录密码不能为空');
			$('#password').focus();
			event.preventDefault();
			return false;
		}
		
		var flag = $("#rememberflag").prop("checked");
		if(flag == true){
			$.cookie("rememberflag", "true", {expires : 7}); // 存储一个带7天期限的 cookie
			$.cookie("userid", $("#userid").val(), {expires : 7}); // 存储一个带7天期限的 cookie
		}else{
			$.cookie("rememberflag", "false", {expires : -1}); // 删除 cookie
			$.cookie("userid", '', {expires : -1});
		}
		
		$("#password").val(b64_sha1($('#password').val()));
		
		$('#login-form').submit();
		
	});
	

	

});
