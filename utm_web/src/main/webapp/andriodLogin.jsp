<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%--<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>--%>
<%@ include file="/page/common/common.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>信息管理平台</title>
<link href="${contextPath }/styles/dwz/themes/css/login.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div id="login">
		<div id="login_header">
			<h1 class="login_logo">
				<a href="#"><img src="${contextPath }/styles/dwz/themes/default/images/login_logo.png" /></a>
			</h1>
			<div class="login_headerContent">
				<%--<div class="navList">--%>
					<%--<ul>--%>
						<%--<li><a href="#">反馈</a></li>--%>
						<%--<li><a href="doc/dwz-userEntity-guide.pdf" target="_blank">帮助</a></li>--%>
					<%--</ul>--%>
				<%--</div>--%>
				<h2 class="login_title"><img src="${contextPath }/styles/dwz/themes/default/images/login_title.png" /></h2>
			</div>
		</div>
		<div id="login_content">
			<div class="loginForm">
                <%--<form action="loginInAction" method="post">   --%>
                    <form action="workattence/LoginForAndroidAction" method="post">
                    <p id="result" style="color:red">
                        ${loginResult}
                    </p>
					<p>
						<label>用户名：</label>
						<input type="text" id="name" name="userEntity.name" size="20" class="login_input" maxlength="10"  class="required"/>
					</p>
					<p>
						<label>密码：</label>
						<input type="password" id="password" name="userEntity.password" size="20" class="login_input" maxlength="10"  class="required"/>
					</p>

					<div class="login_bar">
						<input class="sub" type="submit" value=" " />
					</div>
				</form>
			</div>
			<div class="login_banner"><img src="${contextPath }/styles/dwz/themes/default/images/login_banner.jpg" /></div>
			<div class="login_main">
				<%--<ul class="helpList">--%>
					<%--<li><a href="#">什么是后台管理平台</a></li>--%>
					<%--<li><a href="#">该平台可以为您提供的服务？</a></li>--%>
					<%--<li><a href="#">忘记密码怎么办？</a></li>--%>
					<%--<li><a href="#">为什么登录失败？</a></li>--%>
				<%--</ul>--%>
				<%--<div class="login_inner">--%>
					<%--<p>后台管理平台，使得开发系统更快捷、高效</p>--%>
					<%--<p>后台管理平台，使得开发系统更快捷、高效</p>--%>
				<%--</div>--%>
			</div>
		</div>
		<div id="login_footer">
			Copyright &copy; All Rights Reserved.
		</div>
	</div>
</body>
</html>

