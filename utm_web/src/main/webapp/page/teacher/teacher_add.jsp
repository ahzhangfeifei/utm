<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/page/common/common.jsp"%> 

<div class="pageContent">
	
	<form method="post" action="${contextPath}/teacher/addTeacherAction.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
				<div class="pageFormContent" layoutH="58">
			<div class="unit">
				<label>卡号：</label>
				<input type="text" name="teacher.code" value="" size="30" maxlength="20"  class="alphanumeric required"/>
			</div>
            <div class="unit">
                <label>密码：</label>
                <input type="text" name="teacher.password" value="" size="30" maxlength="6"  class="alphanumeric required"/>
            </div>

            <div class="unit">
				<label>姓名：</label>
				<input type="text" name="teacher.name" value="" size="30" maxlength="20" class="required" />
			</div>
			<div class="unit">
				<label>性别：</label>
                <select class="combox" name="teacher.sex" >
                    <option value="男" selected="selected">男</option>
                    <option value="女">女</option>
                </select>
			</div>
			<div class="unit">
				<label>年龄：</label>
				<input type="text" name="teacher.age" value="" size="30" maxlength="20" class="digits" />
			</div>
			<div class="unit">
				<label>学校：</label>
				<input type="text" name="teacher.school" value="" size="30" maxlength="20"  />
			</div>
			<div class="unit">
				<label>地址：</label>
				<input type="text" name="teacher.address" value="" size="30" maxlength="20"  />
			</div>
			<div class="unit">
				<label>电话：</label>
				<input type="text" name="teacher.phone" value="" size="30" maxlength="20" class="phone required" />
			</div>
            <div class="unit">
                <label>注册时间：</label>
                <jsp:useBean id="now" class="java.util.Date" />
                <input type="text" name="teacher.registtime" value="<fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy-MM-dd" />
" minDate="2010-01-01" maxDate="2050-12-31" class="date textInput readonly valid required" readonly="true" size="30" maxlength="20"/>
                <a class="inputDateButton" href="javascript:;" />
            </div>

			<div class="unit">
				<label>是否在职：</label>
                <select class="combox" name="teacher.state" >
                    <option value="是" selected="selected">是</option>
                    <option value="否">否</option>
                </select>
			</div>
            <div class="unit">
                <label>备注：</label>
                <input type="text" name="teacher.more" value="" size="30" maxlength="20"  />
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

