<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!-- basic scripts -->
<script src="/js/ace-extra.min.js"></script>

<!--[if !IE]> -->
<script type="text/javascript">
	window.jQuery
			|| document.write("<script src='/js/jquery.min.js'>"
					+ "<"+"/script>");
</script>
<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
</script>
<![endif]-->

<script type="text/javascript">
	if ("ontouchend" in document)
		document.write("<script src='/js/jquery.mobile.custom.min.js'>"
				+ "<"+"/script>");
</script>

<script src="/js/bootstrap.min.js"></script>
<script src="/js/date-time/bootstrap-datepicker.min.js"></script>



<!-- page specific plugin scripts -->
<!-- jqGrid -->
<script src="/js/jqGrid/jquery.jqGrid.min.js"></script>
<script src="/js/jqGrid/i18n/grid.locale-cn.js"></script>

<!-- colorbox -->
<script src="/js/jquery.colorbox-min.js"></script>

<!-- dropzone -->
<script src="/js/dropzone.min.js"></script>

<!-- ckeditor -->
<script src="/js/ckeditor/ckeditor.js"></script>

<!-- ace scripts -->

<script src="/js/ace-elements.min.js"></script>
<script src="/js/ace.min.js"></script>

<!-- ace settings handler -->

<script src="/js/typeahead-bs2.min.js"></script>

<script src="/js/bus/localcity.js"></script>
<script src="/js/bus/pub.js"></script>
<script type="text/javascript">
/** 
 * 时间对象的格式化; 
 */  
Date.prototype.format = function(format) {  
    /* 
     * 使用例子:format="yyyy-MM-dd hh:mm:ss"; 
     */  
    var o = {  
        "M+" : this.getMonth() + 1, // month  
        "d+" : this.getDate(), // day  
        "h+" : this.getHours(), // hour  
        "m+" : this.getMinutes(), // minute  
        "s+" : this.getSeconds(), // second  
        "q+" : Math.floor((this.getMonth() + 3) / 3), // quarter  
        "S" : this.getMilliseconds()  
        // millisecond  
    }  
   
    if (/(y+)/.test(format)) {  
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4  
                        - RegExp.$1.length));  
    }  
   
    for (var k in o) {  
        if (new RegExp("(" + k + ")").test(format)) {  
            format = format.replace(RegExp.$1, RegExp.$1.length == 1  
                            ? o[k]  
                            : ("00" + o[k]).substr(("" + o[k]).length));  
        }  
    }  
    return format;  
}
</script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

<!--[if lt IE 9]>
		<script src="/js/html5shiv.js"></script>
		<script src="/js/respond.min.js"></script>
<![endif]-->
