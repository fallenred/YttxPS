jQuery(function($) {
	
	var url = '/pic/addPic.htm?resType=' + $("#resType").val() + 
			'&resNo=' + $("#resNo").val();
	Dropzone.autoDiscover = false;
	try {
		var myDropzone = new Dropzone(
				"#dropzone",
				{
					url : url,
					paramName : "files",
					maxFilesize : 10,
					addRemoveLinks : true,
					dictDefaultMessage : '<span class="bigger-150 bolder"><i class="ace-icon fa fa-caret-right red"></i> 拖动文件</span>上传 \
										<span class="smaller-80 grey">(或点击)</span> <br /> \<i class="upload-icon ace-icon fa fa-cloud-upload blue fa-3x"></i>',
					dictResponseError : '文件上传错误!',
					dictRemoveFile : '',
					previewTemplate : "<div class=\"dz-preview dz-file-preview\">\n  <div class=\"dz-details\">\n    <div class=\"dz-filename\"><span data-dz-name></span></div>\n    <div class=\"dz-size\" data-dz-size></div>\n    <img data-dz-thumbnail />\n  </div>\n  <div class=\"progress progress-small progress-striped active\"><div class=\"progress-bar progress-bar-success\" data-dz-uploadprogress></div></div>\n  <div class=\"dz-success-mark\"><span></span></div>\n  <div class=\"dz-error-mark\"><span></span></div>\n  <div class=\"dz-error-message\"><span data-dz-errormessage></span></div>\n</div>"
				});
	} catch (e) {
		alert('Dropzone.js does not support older browsers!');
	}
});
