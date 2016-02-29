
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="navbar navbar-default" id="navbar">
	<script type="text/javascript">
		try {
			ace.settings.check('navbar', 'fixed')
		} catch (e) {
		}
	</script>

	<div class="navbar-container" id="navbar-container">
		<div class="navbar-header pull-left">
			<a href="#" class="navbar-brand"> <small> <i
					class="fa fa-leaf"></i> 平台管理系统  </small> </a>
			<!-- /.brand -->
		</div>
		<!-- /.navbar-header -->

		<div class="navbar-header pull-right" role="navigation">
			<ul class="nav ace-nav">
			
				
				<li class="grey"><a data-toggle="dropdown"
					class="dropdown-toggle" href="#"> <i class="fa fa-tasks"></i> <span
						class="badge badge-grey">1</span> </a>
					<ul
						class="pull-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
						<li class="dropdown-header"><i class="fa fa-ok"></i> 0 个任务 </li>

						<!--  
						<li><a href="#">
								<div class="clearfix">
									<span class="pull-left">Software Update</span> <span
										class="pull-right">65%</span>
								</div>

								<div class="progress progress-mini ">
									<div style="width:65%" class="progress-bar "></div>
								</div> </a></li>
						-->
						
						<li><a href="#"> 查看所有任务  <i class="fa fa-arrow-right"></i> </a></li>
					</ul></li>

				<li class="purple"><a data-toggle="dropdown"
					class="dropdown-toggle" href="#"> <i class="fa fa-bell-alt"></i>
						<span class="badge badge-important">1</span> </a>

					<ul
						class="pull-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
						<li class="dropdown-header"><i class="fa fa-warning-sign"></i>
							0个通知 </li>

						<!-- 
						<li><a href="#">
								<div class="clearfix">
									<span class="pull-left"> <i
										class="btn btn-xs no-hover btn-pink icon-comment"></i> New
										Comments </span> <span class="pull-right badge badge-info">+12</span>
								</div> </a></li>
						-->
						<li><a href="#"> 查看所有通知  <i
								class="fa fa-arrow-right"></i> </a></li>
					</ul></li>

				<li class="green"><a data-toggle="dropdown"
					class="dropdown-toggle" href="#"> <i class="fa fa-envelope"></i>
						<span class="badge badge-success">1</span> </a>					
					<ul
						class="pull-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
						<li class="dropdown-header"><i class="fa fa-envelope-alt"></i>1 条信息 </li>
						<!--
						<li><a href="#"><span
								class="msg-body"> <span class="msg-title"> <span
										class="blue">Bob:</span> Nullam quis risus eget urna mollis
										ornare ... </span> <span class="msg-time"> <i
										class="icon-time"></i> <span>3:15 pm</span> </span> </span> </a></li>
						-->
						<li><a href="inbox.html"> 查看所有消息  <i class="fa fa-arrow-right"></i> </a></li>
					</ul></li>

				<li class="light-blue"><a data-toggle="dropdown" href="#"
					class="dropdown-toggle"> 
					 <span
						class="user-info"> <small>您好,</small> 管理员 </span> 
						<i class="fa fa-caret-down"></i> </a>

					<ul
						class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
						<li><a href="#"> <i class="fa fa-cog"></i> 个人设置  </a></li>
						<li><a href="/jsp/user/init.jsp"> <i class="fa fa-lock"></i> 修改密码  </a></li>
						<li class="divider"></li>
						<li><a href="/logout.htm"> <i class="fa fa-off"></i> 退出系统  </a></li>
					</ul></li>
			</ul>
			<!-- /.ace-nav -->
		</div>
		<!-- /.navbar-header -->
	</div>
	<!-- /.container -->
</div>