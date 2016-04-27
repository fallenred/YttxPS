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
<script src="/js/bootstrap-table-expandable.js"></script>

<script src="/js/jquery.base64.js"></script>
<script src="/js/handlebars.js"></script>

<script src="/js/chosen.jquery.min.js"></script>

<!-- page specific plugin scripts -->
<!-- jqGrid -->
<script src="/js/jqGrid/jquery.jqGrid.min.js"></script>
<script src="/js/jqGrid/i18n/grid.locale-cn.js"></script>

<!-- colorbox -->
<script src="/js/jquery.colorbox-min.js"></script>

<!-- validate -->
<script src="/js/jquery.form.js"></script>
<script src="/js/jquery.validate.min.js"></script>

<!-- dropzone -->
<script src="/js/dropzone.min.js"></script>

<!-- ckeditor -->
<script src="/js/ckeditor/ckeditor.js"></script>

<!-- ace scripts -->

<script src="/js/ace-elements.min.js"></script>
<script src="/js/ace.min.js"></script>

<!-- ace settings handler -->

<script src="/js/typeahead-bs2.min.js"></script>

<!-- bootbox -->
<script src="/js/bootbox.min.js"></script>

<script src="/js/bus/localcity.js"></script>
<script src="/js/bus/pub.js"></script>
<script src="/js/bus/pub.js"></script>
<script src="/js/jquery.jgrowl.js"></script>
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
 /**
 * map数据对象
 */
 function Map() {     
	    /** 存放键的数组(遍历用到) */    
	    this.keys = new Array();     
	    /** 存放数据 */    
	    this.data = new Object();     
	         
	    /**   
	     * 放入一个键值对   
	     * @param {String} key   
	     * @param {Object} value   
	     */    
	    this.put = function(key, value) {     
	        if(this.data[key] == null){     
	            this.keys.push(key);     
	        }     
	        this.data[key] = value;     
	    };     
	         
	    /**   
	     * 获取某键对应的值   
	     * @param {String} key   
	     * @return {Object} value   
	     */    
	    this.get = function(key) {     
	        return this.data[key];     
	    };     
	         
	    /**   
	     * 删除一个键值对   
	     * @param {String} key   
	     */    
	    this.remove = function(key) {     
	        this.keys.remove(key);     
	        this.data[key] = null;     
	    };     
	         
	    /**   
	     * 遍历Map,执行处理函数   
	     *    
	     * @param {Function} 回调函数 function(key,value,index){..}   
	     */    
	    this.each = function(fn){     
	        if(typeof fn != 'function'){     
	            return;     
	        }     
	        var len = this.keys.length;     
	        for(var i=0;i<len;i++){     
	            var k = this.keys[i];     
	            fn(k,this.data[k],i);     
	        }     
	    };     
	         
	    /**   
	     * 获取键值数组(类似Java的entrySet())   
	     * @return 键值对象{key,value}的数组   
	     */    
	    this.entrys = function() {     
	        var len = this.keys.length;     
	        var entrys = new Array(len);     
	        for (var i = 0; i < len; i++) {     
	            entrys[i] = {     
	                key : this.keys[i],     
	                value : this.data[i]     
	            };     
	        }     
	        return entrys;     
	    };     
	         
	    /**   
	     * 判断Map是否为空   
	     */    
	    this.isEmpty = function() {     
	        return this.keys.length == 0;     
	    };     
	         
	    /**   
	     * 获取键值对数量   
	     */    
	    this.size = function(){     
	        return this.keys.length;     
	    };     
	         
	    /**   
	     * 重写toString    
	     */    
	    this.toString = function(){     
	        var s = "{";     
	        for(var i=0;i<this.keys.length;i++,s+=','){     
	            var k = this.keys[i];     
	            s += k+"="+this.data[k];     
	        }     
	        s+="}";     
	        return s;     
	    };     
	}
</script>
<!-- 表单验证 -->
<script>
	var msgMap = new Map();
	function refreshMsg(){
		$.ajax({
			type: "POST",
			url: "/message/findNewMsg.htm",
			dataType: "json",
			success: function(data){
				var html = '';
				var count = 0;
				$.each(data, function(commentIndex, comment){
					if(commentIndex == 'rows'){
						for(var i = 0; i < 3; i++){
							/**
							
							html += '<a href="#"><span class="msg-body"><span class="msg-title"><span class="blue">'+ comment[i]['sendid'] +'</span>';
							html += comment[i]['msgText'] +'</span><span class="msg-time"><i class="icon-time"></i><span>';
							html += (new Date(parseFloat(comment[i]['msgDate']))).format("yyyy-MM-dd HH:mm:ss") +'</span></span></span></a>';
							*/
							html += '<a><span>'+ comment[i]['msgHead'] +'</span></a>';
							count += 1;
							var msg = msgMap.get(comment[i]['id']);
							if (msg == null) {
								msgMap.put(comment[i]['id'], comment[i]['id']);
								$.jGrowl.defaults.closerTemplate = '<div class="alert alert-info">全部关闭</div>';
								var alertTypes = ['info'];
								$.jGrowl(comment[i]['msgText'], {
									header: comment[i]['msgHead'],
									theme:  'manilla',
									sticky: true,
									click: function(msg) {
										//alert("You clicked me");
									}
								});
							}
						}
						
					}
				});
				
				$("#newMsg").html(html);
				if(count <3){
					html = count + '条未读信息';
				} else {
					html = '至少' + count + '条未读信息';
				}
				$("#msgCount").html(html);
				$(".msgCount").html(count);
			}
		});
	}
	//refreshMsg();
	setInterval(refreshMsg,120000);
	$().ready(function() {
		// 判断浮点数value是否大于或等于0
	    jQuery.validator.addMethod("isFloatGteZero", function(value, element) { 
	         value=parseFloat(value);      
	         return this.optional(element) || value>=0;       
	    }, "请输入正数"); 
		$("form").validate();
	});
	$.extend($.validator.messages, {
	    required: "必选字段",
	    remote: "请修正该字段",
	    email: "请输入正确格式的电子邮件",
	    url: "请输入合法的网址",
	    date: "请输入合法的日期",
	    dateISO: "请输入合法的日期 (ISO).",
	    number: "请输入合法的数字",
	    digits: "只能输入整数",
	    isFloatGteZero: "只能输入非负数",
	    creditcard: "请输入合法的信用卡号",
	    equalTo: "请再次输入相同的值",
	    accept: "请输入拥有合法后缀名的字符串",
	    maxlength: $.validator.format("请输入一个长度最多是 {0} 的字符串"),
	    minlength: $.validator.format("请输入一个长度最少是 {0} 的字符串"),
	    rangelength: $.validator.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),
	    range: $.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
	    max: $.validator.format("请输入一个最大为 {0} 的值"),
	    min: $.validator.format("请输入一个最小为 {0} 的值")
	});
</script>
<style>
	.error {
		color: red;
	}
	
	.jGrowl-notification {
		background-color: #000000;
		opacity: 0.9;
		filter: progid:DXImageTransform.Microsoft.Alpha(Opacity=(0.9*100 ) );
		-ms-filter: progid:DXImageTransform.Microsoft.Alpha(Opacity=(0.9*100 ) );
		zoom: 1;
		width: 250px;
		padding: 10px;
		margin: 10px;
		text-align: left;
		display: none;
		border-radius: 5px;
		min-height: 40px;
	}
	
	.jGrowl .manilla {
		background-color: #FFF1C2;
		color: navy;
	}
</style>
<script src="/js/date-time/bootstrap-datetimepicker.min.js"></script>
<script src="/js/date-time/locales/bootstrap-datetimepicker.zh-CN.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

<!--[if lt IE 9]>
		<script src="/js/html5shiv.js"></script>
		<script src="/js/respond.min.js"></script>
<![endif]-->
