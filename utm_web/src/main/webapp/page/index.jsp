<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/page/common/common.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>信息系统-主页</title>

<link href="${contextPath }/styles/dwz/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${contextPath }/styles/dwz/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${contextPath }/styles/dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="${contextPath }/styles/dwz/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
<!--[if IE]>
<link href="themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->

<!--[if lte IE 9]>
<script src="js/speedup.js" type="text/javascript"></script>
<![endif]-->

<script src="${contextPath }/styles/dwz/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="${contextPath }/styles/dwz/js/jquery.cookie.js" type="text/javascript"></script>
<script src="${contextPath }/styles/dwz/js/jquery.validate.js" type="text/javascript"></script>
<script src="${contextPath }/styles/dwz/js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="${contextPath }/styles/dwz/uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>

<!--如果不使用dwz.min.js,就要使用下面这些-->
<script src="${contextPath }/styles/dwz/js/dwz.core.js" type="text/javascript"></script>
<script src="${contextPath }/styles/dwz/js/dwz.util.date.js" type="text/javascript"></script>
<script src="${contextPath }/styles/dwz/js/dwz.validate.method.js" type="text/javascript"></script>
<script src="${contextPath }/styles/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>
<script src="${contextPath }/styles/dwz/js/dwz.barDrag.js" type="text/javascript"></script>
<script src="${contextPath }/styles/dwz/js/dwz.drag.js" type="text/javascript"></script>
<script src="${contextPath }/styles/dwz/js/dwz.tree.js" type="text/javascript"></script>
<script src="${contextPath }/styles/dwz/js/dwz.accordion.js" type="text/javascript"></script>
<script src="${contextPath }/styles/dwz/js/dwz.ui.js" type="text/javascript"></script>
<script src="${contextPath }/styles/dwz/js/dwz.theme.js" type="text/javascript"></script>
<script src="${contextPath }/styles/dwz/js/dwz.switchEnv.js" type="text/javascript"></script>
<script src="${contextPath }/styles/dwz/js/dwz.alertMsg.js" type="text/javascript"></script>
<script src="${contextPath }/styles/dwz/js/dwz.contextmenu.js" type="text/javascript"></script>
<script src="${contextPath }/styles/dwz/js/dwz.navTab.js" type="text/javascript"></script>
<script src="${contextPath }/styles/dwz/js/dwz.tab.js" type="text/javascript"></script>
<script src="${contextPath }/styles/dwz/js/dwz.resize.js" type="text/javascript"></script>
<script src="${contextPath }/styles/dwz/js/dwz.dialog.js" type="text/javascript"></script>
<script src="${contextPath }/styles/dwz/js/dwz.dialogDrag.js" type="text/javascript"></script>
<script src="${contextPath }/styles/dwz/js/dwz.sortDrag.js" type="text/javascript"></script>
<script src="${contextPath }/styles/dwz/js/dwz.cssTable.js" type="text/javascript"></script>
<script src="${contextPath }/styles/dwz/js/dwz.stable.js" type="text/javascript"></script>
<script src="${contextPath }/styles/dwz/js/dwz.taskBar.js" type="text/javascript"></script>
    <!--导出Excel，传参数不要传中文，乱码-->
<script src="${contextPath }/styles/dwz/js/dwz.ajax.js" type="text/javascript"></script>
<script src="${contextPath }/styles/dwz/js/dwz.pagination.js" type="text/javascript"></script>
<script src="${contextPath }/styles/dwz/js/dwz.database.js" type="text/javascript"></script>
<script src="${contextPath }/styles/dwz/js/dwz.datepicker.js" type="text/javascript"></script>
<script src="${contextPath }/styles/dwz/js/dwz.effects.js" type="text/javascript"></script>
<script src="${contextPath }/styles/dwz/js/dwz.panel.js" type="text/javascript"></script>
<script src="${contextPath }/styles/dwz/js/dwz.checkbox.js" type="text/javascript"></script>
<script src="${contextPath }/styles/dwz/js/dwz.history.js" type="text/javascript"></script>
<script src="${contextPath }/styles/dwz/js/dwz.combox.js" type="text/javascript"></script>
<script src="${contextPath }/styles/dwz/js/dwz.print.js" type="text/javascript"></script>

<!--如果不使用dwz.min.js,就要使用上面这些-->


<%--<script src="${contextPath }/styles/dwz/bin/dwz.min.js" type="text/javascript"></script>--%>

<script src="${contextPath }/styles/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){
	DWZ.init("${contextPath }/styles/dwz/dwz.frag.xml", {
		loginUrl:"login_dialog.html", loginTitle:"登录",	// 弹出登录对话框
//		loginUrl:"login.html",	// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"${contextPath }/styles/dwz/themes"}); // themeBase 相对于index页面的主题base路径
		}
	});
});

</script>
</head>

<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<a class="logo" href="http://j-ui.com">标志</a>
				<ul class="nav">

					<li><a href="${contextPath }/loginOutAction">退出</a></li>
				</ul>
				<ul class="themeList" id="themeList">
					<li theme="default"><div>蓝色</div></li>
					<li theme="green"><div class="selected">绿色</div></li>
					<!--<li theme="red"><div>红色</div></li>-->
					<li theme="purple"><div>紫色</div></li>
					<li theme="silver"><div>银色</div></li>
					<li theme="azure"><div>天蓝</div></li>
				</ul>
			</div>

			<!-- navMenu -->
			
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>

				<div class="accordion" fillSpace="sidebar">
					<div class="accordionHeader">
						<h2><span>Folder</span>考勤管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="${contextPath }/user/queryUserAction.action" target="navTab" rel="userListview">用户信息管理</a>
							</li>
                            </ul>

                        <ul class="tree treeFolder">
                            <li><a href="${contextPath }/student/queryStudentAction.action" target="navTab" rel="studentListview">学生信息管理</a>
                            </li>
                        </ul>

                        <ul class="tree treeFolder">
                            <li><a href="${contextPath }/teacher/queryTeacherAction.action" target="navTab" rel="teacherListview">教师信息管理</a>
                            </li>
                        </ul>

                        <ul class="tree treeFolder">
                            <li><a href="${contextPath }/workattence/queryTeacherWorkattenceAction.action" target="navTab" rel="teacherWorkattenceListview">教师考勤记录</a>
                            </li>
                        </ul>

                        <ul class="tree treeFolder">
                            <li><a href="${contextPath }/workattence/queryStudentWorkattenceAction.action" target="navTab" rel="studentWorkattenceListview">学生考勤记录</a>
                            </li>
                        </ul>
                        <ul class="tree treeFolder">
                            <li><a href="${contextPath }/workattence/showMainWorkattenceAction.action" title="正在考勤" target="navTab" rel="mainWorkattenceView" >进入考勤</a>
                            </li>
                        </ul>

                    </div>
				</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
                        <div class="accountInfo">
                            <div>
                                <span style="line-height:50px;font-size:40px">欢迎登陆考勤管理系统!</span>
                            </div>
                        </div>
                        <div class="accountInfo">
                            <div>
                                <h2>

                                    <a href="${contextPath }/workattence/showMainWorkattenceAction.action" title="正在考勤" target="navTab" rel="mainWorkattenceView" style="color:red;line-height:50px;font-size:40px">》》》马上开始考勤》》》</a></h2>
                            </div>
                        </div>
					</div>
				</div>
			</div>
		</div>

	</div>

	<div id="footer">Copyright &copy; 2014     贾琳shan9liang@163.com</div>


</body>
</html>