<%@ page pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>500</title>
</head>
<style>
*{ margin:0; padding:0; font-family:"微软雅黑"}
body{ background-color:#f7f7f7;}
.errorbox{ width:701px; height:342px; margin:0 auto;box-shadow: 0px 1px 5px #DDD; background-color:#fff;}
.error_left{ width:50%; float:left; }
.error_right{ width:50%; float:right;}
.errorbox .error_left .img_box{ padding:20px; padding-right:80px;}
.errorbox .error_left .img_box img{ float:right;}
.errorbox .error_right .error_right_content{ padding:20px;}
.errorbox .error_right .error_right_content h1{ font-size:60px; color:#ff7800;}
.errorbox .error_right .error_right_content h1 span{ font-size:20px; color:gray; font-weight:bold; display:inline-block; margin-left:30px;}
.errorbox .error_right .error_right_content h5{ color:#d2d2d2;}
.errorbox .error_right .error_right_content p{ margin-top:25px;}
.errorbox .error_right .error_right_content p.error_tips{color:#b6b6b6;}
.errorbox .error_right .error_right_content p a:link{color:red;}
</style>
<body>
<div class="errorbox">
    <div class="error_left">
     <div class="img_box"><img src="/images/errorpages.png"></div>
    </div>
    <div class="error_right">
      <div class="error_right_content">
      <h1>500<span>error</span></h1>
       <h5>对不起，服务器不能执行此请求。请稍后重试此请求.........</h5>
       <p class="error_tips">你查看的网页可能已被删除或者暂时不可用。点击以下链接继续浏览网站</p>
       <p><a href="/index.htm">返回首页</a></p>
      </div>
    </div>
</div>
</body>
</html>

