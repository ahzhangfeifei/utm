<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/page/common/common.jsp"%> 

<div class="pageContent">
	
	<form method="post" action="${contextPath}/workattence/addStudentWorkattenceAction.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
				<div class="pageFormContent" layoutH="58">
                    <jsp:useBean id="now" class="java.util.Date" />
			<div class="unit">
				<label>卡号：</label>
				<input type="text" name="workattence.studentByStudentid.code" value="" size="30" maxlength="20"  class="alphanumeric required" />
			</div>
			<div class="unit">
				<label>登入日期：</label>
                <input type="text" name="workattence.comedate" value="<fmt:formatDate value="${now}" type="both" dateStyle="long"  pattern="yyyy-MM-dd" />" minDate="2010-01-01" maxDate="2050-12-31" class="date textInput readonly valid required" readonly="true" size="30" maxlength="20"/>
                <a class="inputDateButton" href="javascript:;" />
			</div>
			<div class="unit">
				<label>登入时间：</label>
                <input name="workattence.cometime" value="<fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="HH:mm:ss" />" class="date textInput readonly valid" size="30" maxlength="20" type="text" readonly="true" datefmt="HH:mm:ss"/>
                <a class="inputDateButton" href="javascript:;" />
			</div>
			<div class="unit">
				<label>登出日期：</label>
                <input type="text" name="workattence.leavedate" value="<fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy-MM-dd" />" minDate="2010-01-01" maxDate="2050-12-31" class="date textInput readonly valid required" readonly="true" size="30" maxlength="20"/>
                <a class="inputDateButton" href="javascript:;" />
            </div>
			<div class="unit">
				<label>登出时间：</label>
                <input name="workattence.leavetime" value="<fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="HH:mm:ss" />" class="date textInput readonly valid" size="30" maxlength="20" type="text" readonly="true" datefmt="HH:mm:ss"/>
                <a class="inputDateButton" href="javascript:;" />
			</div>
            <%--<div class="unit">--%>
                <%--<label>是否可用：</label>--%>
                <%--<select class="combox" name="workattence.state" >--%>
                    <%--<option value="是" selected="selected">是</option>--%>
                    <%--<option value="否">否</option>--%>
                <%--</select>--%>
            <%--</div>--%>
            <div class="unit">
                <label>备注：</label>
                <input type="text" name="workattence.more" value="" size="30" maxlength="20"  />
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

