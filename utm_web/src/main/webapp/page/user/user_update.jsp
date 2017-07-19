<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/page/common/common.jsp"%> 

<div class="pageContent">
	
	<form method="post" action="${contextPath }/user/updateUserAction.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
		<div class="pageFormContent" layoutH="58">
            <input type="hidden" name="userEntity.id" value="${userEntity.id}"/>
		
			<div class="unit">
				<label>登录名称：</label>
				<input type="text" name="userEntity.name" value="${userEntity.name}" size="30" class="required" maxlength="10" />
			</div>
			<div class="unit">
				<label>密码：</label>
			    <input type="text" name="userEntity.password" value="${userEntity.password}" size="30" maxlength="10" class="required" />
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>

