/**
 * add by marongcai
 * 2016-3-25
 */

//TODO 从数据字典中读取餐厅下面的消费选型
var cost_item ={
	"000000" : "价格"
}
var calendar=null;
/**
 * 
 * 格式化金额
 * @param places
 * @param symbol
 * @param thousand
 * @param decimal
 * @returns
 */

Number.prototype.formatMoney = function(places, symbol, thousand, decimal) {
	places = !isNaN(places = Math.abs(places)) ? places : 2;
	symbol = symbol !== undefined ? symbol : "";  //¥
	thousand = thousand || ",";
	decimal = decimal || ".";
	var number = this, negative = number < 0 ? "-" : "", i = parseInt(
			number = Math.abs(+number || 0).toFixed(places), 10)
			+ "", j = (j = i.length) > 3 ? j % 3 : 0;
	return symbol
			+ negative
			+ (j ? i.substr(0, j) + thousand : "")
			+ i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + thousand)
			+ (places ? decimal + Math.abs(number - i).toFixed(places).slice(2)
					: "");
};

/**
 * 
 * 格式化输出日期
 * 
 * @param fmt
 * @returns
 */

Date.prototype.format = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
};


$(function(){
	/**
	 * 初始化日历表
	 */
	calendar = $('#calendar').fullCalendar({
		header: {
			left: 'prev',
			center: 'title',
			right: 'next'
		},
		titleFormat: {
			month:'yyyy年    MMMM'
		},
		buttonText: {
			prev: '上一月',
			next: '下一月'
		},
		monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],  
        monthNamesShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],  
        dayNames: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"],  
        dayNamesShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"],  
		events:function(start, end, callback){
			var uploadurl = "/routeArrange/cost.htm";
			var param = new Object();
			param.fsId = $.getUrlParam('fsId');
			param.startdate = start.format("yyyy-MM-dd");
			param.enddate = end.format("yyyy-MM-dd");
			$.ajax({
				method : "GET",
				url : uploadurl,
				cache : false,
				data: param
			}).done(function(json) {
				var res=eval("("+json+")")
				var events = [];
				if(res.result == 'ok'){
					var data = res.data;
					//对数据进行加工
					var dataArray = new Array();
					if(null!=data){
						for(var i=0;i<data.length;i++){
							var costPrice = data[i];
							var date =costPrice["FTDATE"];//获取时间
							var costpriceStr = dataArray[date];
							var str = "价格:"+Number(costPrice["price"]).formatMoney()+"元;  ";
							if(costpriceStr == undefined){
								dataArray[date]=str;
							}else{
								dataArray[date]=costpriceStr+str;
							}
						}
					}
					for(var k in dataArray){
						events.push({
							title:dataArray[k],
							start: new Date(k.replace(/-/g,'/')),
							className: 'label-info'
						});	
					}	
					
					callback(events);
				}
			});
		},
		selectable: true,
		unselectAuto:false,
		unselectCancel:false
	});
});
