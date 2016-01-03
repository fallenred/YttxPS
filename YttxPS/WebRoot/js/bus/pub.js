function getregionname(no, callback) {
	if (typeof no != 'undefined') {
		$.get("/pub/findcityname.htm?no=" + no, function(data) {
			var json = eval("(" + data + ")");
			if (json.result == "ok") {
				callback(json.name);
				return json.name;
			} else {
				return "未找到";
			}
		});
	}
};

(function($) {
	$.getUrlParam = function(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return unescape(r[2]);
		return null;
	}
})(jQuery);
