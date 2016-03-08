function getTransportArrange(arrange){
	//获取车型列表
	$.ajax({
		type: "GET",
		url: "/transportArrange/selectTransportArrange.htm",
		data: "transportArrange.fiGenindex=" + arrange.fiGenindex,
		dataType: "json",
		success: function(data){
			var html = ''; 
			$.each(data, function(commentIndex, comment){
				html += '<option value=' + comment['fsTransno'] + '>' + comment['fsTransName'] + '</option>';
			});
			$("#transportArrange").html(html);
			if(arrange.transportArrange != undefined) {
				$("#transportArrange").val(arrange.transportArrange);
			}
		}
	});
}
	
function getGuide(guide){
	//获取导游列表
	$.ajax({
		type: "GET",
		url: "/guide/selectGuide.htm",
		data: "guide.lvl=" + guide.lvl,
		dataType: "json",
		success: function(data){
			var html = ''; 
			$.each(data, function(commentIndex, comment){
				html += '<option value=' + comment['no'] + '>' + comment['name'] + '</option>';
			});
			$("#guideFsNo").html(html);
			$("#guideLvl").val(guide.lvl);
			if(guide.no != undefined) {
				$("#guideFsNo").val(guide.no);
			}
		}
	});
}
