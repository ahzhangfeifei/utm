<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/page/common/common.jsp"%> 

<div class="pageContent">
	
	<form method="post" action="${contextPath}/teacher/updateTeacherAction.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
		<div class="pageFormContent" layoutH="58">
            <input type="hidden" name="teacher.id" value="${teacher.id}"/>
			<div class="unit">
				<label>卡号：</label>
				<input type="text" name="teacher.code" value="${ teacher.code }" size="30" maxlength="20"  class="required alphanumeric" readonly="true"/>
			</div>
            <div class="unit">
                <label>密码：</label>
                <input type="text" name="teacher.password" value="${teacher.password}" size="30" maxlength="6"  class="alphanumeric required"/>
            </div>
			<div class="unit">
				<label>姓名：</label>
				<input type="text" name="teacher.name" value="${ teacher.name }" size="30" maxlength="20"  class="required"/>
			</div>
			<div class="unit">
				<label>性别：</label>
                <select class="combox"
                        name="teacher.sex">
                    <option value="男"  <c:if test="${teacher.sex=='男'}">selected="selected"</c:if> >男</option>
                    <option value="女"  <c:if test="${teacher.sex=='女'}">selected="selected"</c:if> >女</option>
                </select>
            </div>
			<div class="unit">
				<label>年龄：</label>
				<input type="text" name="teacher.age" value="${ teacher.age }" size="30" maxlength="20" class="digits" />
			</div>
			<div class="unit">
				<label>学校：</label>
				<input type="text" name="teacher.school" value="${ teacher.school }" size="30" maxlength="20"  />
			</div>
			<div class="unit">
				<label>地址：</label>
				<input type="text" name="teacher.address" value="${ teacher.address }" size="30" maxlength="20"  />
			</div>
			<div class="unit">
				<label>电话：</label>
				<input type="text" name="teacher.phone" value="${ teacher.phone }" size="30" maxlength="20" class="phone required"  />
			</div>
			<div class="unit">
				<label>注册时间：</label>
                <input type="text" name="teacher.registtime" size="30" maxlength="20" value="${ teacher.registtime }" minDate="2010-01-01" maxDate="2050-12-31" class="date textInput readonly valid required" readonly="true"/>
                <a class="inputDateButton" href="javascript:;" />
            </div>
			<div class="unit">
				<label>是否在职：</label>
                <select class="combox" name="teacher.state" >
                    <option value="是"  <c:if test="${teacher.state=='是'}">selected="selected"</c:if> >是</option>
                    <option value="否"  <c:if test="${teacher.state=='否'}">selected="selected"</c:if> >否</option>
                </select>
            </div>
            <div class="unit">
                <label>备注：</label>
                <input type="text" name="teacher.more" value="${ teacher.more }" size="30" maxlength="20"  />
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

