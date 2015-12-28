;$(function(){
	$('#password-form').on('click', '#confirmbtn', function(event){
		$('.password-msg span').text('');
		if($('#origpwd').val() == ''){
			$('.password-msg span').text('原始密码不能不空');
			$('#origpwd').focus();
			event.preventDefault();
			return false;
		}
		if($('#targpwd').val() == ''){
			$('.password-msg span').text('新密码不能为空');
			$('#targpwd').focus();
			event.preventDefault();
			return false;
		}

		if($('#confpwd').val() == ''){
			$('.password-msg span').text('确认密码不能不空');
			$('#confpwd').focus();
			event.preventDefault();
			return false;
		}
		
		if($('#confpwd').val() != $('#targpwd').val()){
			$('.password-msg span').text('新密码与确认密码不一致');
			$('#confpwd').focus();
			event.preventDefault();
			return false;
		}

		$("#origpwd").val(b64_sha1($('#origpwd').val()));
		$("#targpwd").val(b64_sha1($('#targpwd').val()));
		$("#confpwd").val(b64_sha1($('#confpwd').val()));
		$('#password-form').submit();
	});
	
	$('#password-form').on('click', '#resetbtn', function(event){
		$("#origpwd").val('');
		$("#targpwd").val('');
		$("#confpwd").val('');
		
	});
	
});
