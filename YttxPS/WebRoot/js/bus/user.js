(function($){
    $.fn.user = function(options,fn){
        $.fn.user.defaults = {  
            defaultcity:null, 
            deptvurl:'',
            operurl:'',
            callback:function(){return false;}
        };
        var opts = $.extend({}, $.fn.user.defaults, options);

        var tabs = '<div class="tabs">'+
                    '<ul class="">'+
                    '<li><a href="javascript:void(0);">部门</a></li>'+
                    '<li><a href="javascript:void(0);">用户</a></li>'+
                    '</ul>'+
                    '<span class="close-x"></span>'
                   '</div>';
            
        var contexts = '<div class="con">'+
                        '<div class="list"><ul></ul></div>'+
                        '<div class="list"><ul></ul></div>'+
                      '</div>';
        
        //模板  
        var tags = '<li><a href="javascript:"  data-key="{key}" class="">{keyName}</a></li>';       
            
        return this.each(function(){ 
            var that = $(this);
            var defaultdept = null;//默认部门为空
            var defaultoper = null;//默认操作员为空
            
            var seldept = '';
            var seloper = '';
            
            var seldeptkey = '';
            var seloperkey = '';
                    
            if(that.find('.tabs').length <= 0)
                that.append(tabs);
            
            if(that.find('.con').length <= 0)
                that.append(contexts);
            
            
            
            if(opts.defaultcity != undefined && opts.defaultcity != null){
                var selecteds =opts.defaultcity.split('-');             
            }
            
            
            //加载部门的函数
            function loadDept(){            
                if(that.find('.list').eq(0).find('li').length == 0)             
                    render('1', opts.depturl, 0); //1为传递的参数，状态为正常   
            }
            
            that.delegate('.close-x','click',function(e){
                $(that).removeClass('cityactive');
                that.hide();
            });
            
            
            function loadOper(key){               
                var operli = that.find('.list').eq(1).find('li');
                $(operli).remove();
                
                if(that.find('.list').eq(1).find('li').length == 0)             
                    render(key, opts.operurl, 1);               
            }
            
            //加载部门
            loadDept();
            
            //展示数据
            function init(idx, sel){
                if(!sel){
                    return;
                }else{
                    for(var i=0;i<sel.length;i++){
                    	if(idx==0){
                    		fixTags(idx, sel[i].depNo, sel[i].depName)       
                    	}
                    	if(idx==1){
                    		fixTags(idx, sel[i].sysOperId, sel[i].sysOperName)  
                    	}                
                    }            
                }
                that.find('.tabs a').eq(idx).addClass('current');//上面的选项卡添加current
                that.find('.list').eq(idx).show();//下面响应的div展示
            }
            
            //根据模板生成li
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
                    seldept = text;
                    seldeptkey = key;
                    seloper = '';
                    seloperkey = '';
                    pager(key, text, index);
                    $(this).parents('.list').hide();
                    that.find('.tabs').find('a').removeClass('current');
                    if(tabs.eq(index).find('a').length>0) tabs.eq(index).find('a').addClass('current');
                    loadOper(key);//加载用户
                }else{
                	seloper = text;
                	seloperkey = key;
                     pager(key, text, index);
                    $(that).removeClass('cityactive');
                }
            });
            
            //
            function pager(key, value, index){ 
                if(typeof opts.callback != 'undefined'){ 
                    
                    var fullname = '';
                    var fullkey = '';
                    
                    if(seldeptkey != ''){
                        fullname = seldept;
                        fullkey = seldeptkey;
                    }
                    
                    if(seloper != ''){
                        fullname += ('-'+seloper);
                        fullkey += ('-'+seloperkey);
                    }
                     var propagation = opts.callback(index, key, value, fullkey, fullname); 
                     return propagation;
                }   
            }
            
            //Ajax加载数据
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