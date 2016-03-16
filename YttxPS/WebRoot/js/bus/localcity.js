(function($){
    $.fn.localCity = function(options,fn){
        $.fn.localCity.defaults = {  
            defaultcity:null, 
            provurl:'',
            cityurl:'',
            disturl:'',
            callback:function(){return false;}
        };
        var opts = $.extend({}, $.fn.localCity.defaults, options);

        var tabs = '<div class="tabs">'+
                    '<ul class="">'+
                    '<li><a href="javascript:void(0);">省份</a></li>'+
                    '<li><a href="javascript:void(0);">城市</a></li>'+
                    '<li><a href="javascript:void(0);">区县</a></li>'+
                    '</ul>'+
                    '<span class="close-x"></span>'
                   '</div>';
            
        var contexts = '<div class="con">'+
                        '<div class="list"><ul></ul></div>'+
                        '<div class="list"><ul></ul></div>'+
                        '<div class="list"><ul></ul></div>'+
                      '</div>';
            
        var tags = '<li><a href="javascript:"  data-key="{key}" class="">{keyName}</a></li>';       
            
        return this.each(function(){ 
            var that = $(this);
            var defaultprov = null;
            var defaultcity = null;
            var defailtdist = null;
            
            var selprov = '';
            var selcity = '';
            var seldist = '';
            
            var selprovkey = '';
            var selcitykey = '';
            var seldistkey = '';
                    
            if(that.find('.tabs').length <= 0)
                that.append(tabs);
            
            if(that.find('.con').length <= 0)
                that.append(contexts);
            
            if(opts.defaultcity != undefined && opts.defaultcity != null){
                var selecteds =opts.defaultcity.split('-');             
            }
            
            function loadProv(){            
                if(that.find('.list').eq(0).find('li').length == 0)             
                    render('000086', opts.provurl, 0);    
            }
            
            that.delegate('.close-x','click',function(e){
                $(that).removeClass('cityactive');
                $(that).hide();
            });
            
            function loadCity(key){             
                var cityli = that.find('.list').eq(1).find('li');
                $(cityli).remove();
       
                if(that.find('.list').eq(1).find('li').length == 0)             
                    render(key, opts.cityurl, 1);   
                
                var countyli = that.find('.list').eq(2).find('li');
                $(countyli).remove();
            }
            
            function loadCounty(key){               
                var cityli = that.find('.list').eq(2).find('li');
                $(cityli).remove();
 
                
                if(that.find('.list').eq(2).find('li').length == 0)             
                    render(key, opts.disturl, 2);               
            }
            loadProv();
            
            function init(idx, sel){
                if(!sel){
                    return;
                }else{
                    for(var i=0;i<sel.length;i++){
                        fixTags(idx, sel[i].no, sel[i].name)                       
                    }            
                }
                that.find('.tabs a').eq(idx).addClass('current');
                that.find('.list').eq(idx).show();
            }
            
            function fixTags(idx, key, keyName){ 
                if(key && keyName){                                 
                    that.find('.list').eq(idx).find('ul').eq(0).append(tags.replace("{key}",key).replace("{keyName}",keyName));                 
                }
            }       
               
            $(this).on('click','.tabs li',function(event){
                
                var acttab = $(this);      
                var tabs = that.find('.tabs').find('li');               
                var index = tabs.index(acttab);
 console.log("tabs li index:" + index);                                     
                if(that.find('.list').eq(index).find('li').length > 0){
                    that.find('.list').hide();
                    that.find('.list').eq(index).show();
                    
                    $(tabs).find('a').removeClass('current');               
                    $(acttab).find('a').addClass('current');                
                }
            });
                       
            $(this).on('click','.con li',function(event){               
                        
                var acttab = $(that.find('.tabs').find('.current')).parents('li');              
                var tabs = that.find('.tabs').find('li');               
                var index = tabs.index(acttab);
                
                var key = $(this).find('a').eq(0).attr("data-key");
                var text = $(this).find('a').eq(0).text();
                
                $(this).parent().find('a').removeClass('current');                  
                $(this).find('a').eq(0).addClass('current');
                                  
                index++;       
console.log("con li index:" + index + "text:" + text);                      
                if(index == 1){
                    selprov = text;
                    selprovkey = key;
                    selcity = '';
                    seldist = '';
                    selcitykey = '';
                    seldistkey = '';
                    pager(key, text, index);
                    $(this).parents('.list').hide();
                    that.find('.tabs').find('a').removeClass('current');
                    if(tabs.eq(index).find('a').length>0) tabs.eq(index).find('a').addClass('current');
                    loadCity(key);
                }else if(index == 2){
                    selcity = text;
                    selcitykey = key;
                    seldist = '';
                    seldistkey = '';
                    pager(key, text, index);
                    $(this).parents('.list').hide();  
                    that.find('.tabs').find('a').removeClass('current');
                    if(tabs.eq(index).find('a').length>0) tabs.eq(index).find('a').addClass('current');
                    loadCounty(key);
                }else{
                    seldist = text;
                    seldistkey = key;
                     pager(key, text, index);
                    $(that).removeClass('cityactive');
                }
            });
            

            function pager(key, value, index){ 
                if(typeof opts.callback != 'undefined'){ 
                    
                    var fullname = '';
                    var fullkey = '';
                    
                    if(selprovkey != ''){
                        fullname = selprov;
                        fullkey = selprovkey;
                    }
                    
                    if(selcity != ''){
                        fullname += ('-'+selcity);
                        fullkey += ('-'+selcitykey);
                    }
                    
                    if(seldist != ''){
                        fullname += ('-'+seldist);
                        fullkey += ('-'+seldistkey);
                    }
                    
                     var propagation = opts.callback(index, key, value, fullkey, fullname); 
                     return propagation;
                }   
            }
            
            
            function render(key, surl, idx){
                var postParam = "key="+key;             
                $.ajax({    
                    type:'GET',        
                    url:surl,    
                    data:postParam,    
                    cache:false,    
                    dataType:'json',    
                    success:function(res){  
                        if(res.succflag==0){
                            init(idx, res.data);
                        }                                           
                    }
                });                
            }  
        }) 
    }
})(jQuery);