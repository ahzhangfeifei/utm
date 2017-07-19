<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/page/common/common.jsp"%> 
    
<!-- 自定义标签封装pagerForm，简化分页 -->
<custom:paginationForm action="${contextPath}/teacher/queryTeacherAction.action">
	<input type="hidden" name="teacher.code" value="${teacher.code}"/>
    <input type="hidden" name="teacher.name" value="${teacher.name}"/>
    <input type="hidden" name="teacher.school" value="${teacher.school}"/>
    <input type="hidden" name="teacher.state" value="${teacher.state}"/>
    <input type="hidden" name="dateBegin" value="${dateBegin}"/>
    <input type="hidden" name="dateEnd" value="${dateEnd}"/>
</custom:paginationForm>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${contextPath}/teacher/queryTeacherAction.action" method="post">
	<div class="searchBar">
            <table class="searchContent">
                <tbody><tr>
                    <td>
                        卡号：
                        <input type="text" name="teacher.code" value="${teacher.code}" maxlength="20" />
                    </td>
                    <td>
                        姓名：
                        <input type="text" name="teacher.name" value="${teacher.name}" maxlength="20" />
                    </td>
                    <td>
                        学校：
                        <input type="text" name="teacher.school" value="${teacher.school}" maxlength="20" />
                    </td>
                    <td>是否在职:
                        <select name="teacher.state" >
                            <option value="" <c:if test="${teacher.state==''}">selected="selected"</c:if>>所有</option>
                            <option value="是"  <c:if test="${teacher.state=='是'}">selected="selected"</c:if> >是</option>
                            <option value="否"  <c:if test="${teacher.state=='否'}">selected="selected"</c:if> >否</option>
                        </select>
                    </td>
                    <td class="dateRange">
                        注册时间：
                        <input type="text" name="dateBegin" value="${dateBegin}" minDate="2010-01-01" maxDate="2050-12-31" class="date textInput readonly valid" readonly="true">
                        <span class="limit">-</span>
                        <input type="text" name="dateEnd" value="${ dateEnd }" minDate="2010-01-01" maxDate="2050-12-31" class="date textInput readonly valid" readonly="true">
                    </td>
                    <td><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></td>
                </tr>
                </tbody></table>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${contextPath}/teacher/showAddTeacherAction.do" target="dialog" width="440" height="400" mask="true"><span>添加</span></a></li>
			<li><a title="删除教师，该教师的考勤记录也将一并删除，不可恢复，确实要删除这些教师吗?" target="selectedTodo" rel="ids" href="${contextPath}/teacher/deleteTeacherByArrayAction.do" class="delete" warn="请选择用户！"><span>删除</span></a></li>
			<li><a class="edit" href="${contextPath}/teacher/showUpdateTeacherAction.do?teacher.id={sid_teacher}" target="dialog" width="440" height="400" mask="true" warn="请选择一个用户！"><span>修改</span></a></li>
			<li class="line">line</li>

            <%--<c:set var="searchCondition">--%>
                <%--teacher.code=${teacher.code}&teacher.name=${teacher.name}&teacher.school=${teacher.school}&teacher.state=${teacher.state}&dateBegin=${dateBegin}&dateEnd=${dateEnd}--%>
            <%--</c:set>--%>
            <%--这里提交的也是pagerForm，会把custom:paginationForm中的值提交--%>
			<li><a class="icon" href="${contextPath }/teacher/exportTeacherAction.do" target="dwzExport" title="确定要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="1169" layoutH="110">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="100" orderField="code" <c:if test="${ pageModel.orderField=='code'}">class="${pageModel.orderDirection}"</c:if>> 卡号 </th>
				<th width="100" > 密码 </th>
				<th width="100" orderField="name" <c:if test="${ pageModel.orderField=='name'}">class="${pageModel.orderDirection}"</c:if>> 姓名 </th>
				<th width="100" orderField="sex" <c:if test="${ pageModel.orderField=='sex'}">class="${pageModel.orderDirection}"</c:if>> 性别 </th>
				<th width="100" orderField="age" <c:if test="${ pageModel.orderField=='age'}">class="${pageModel.orderDirection}"</c:if>> 年龄 </th>
				<th width="100" orderField="school" <c:if test="${ pageModel.orderField=='school'}">class="${pageModel.orderDirection}"</c:if>> 学校 </th>
				<th width="100"  <c:if test="${ pageModel.orderField=='address'}">class="${pageModel.orderDirection}"</c:if>> 地址 </th>
				<th width="100"  <c:if test="${ pageModel.orderField=='phone'}">class="${pageModel.orderDirection}"</c:if>> 电话 </th>
				<th width="100" orderField="registtime" <c:if test="${ pageModel.orderField=='registtime'}">class="${pageModel.orderDirection}"</c:if>> 注册时间 </th>
                <th width="100" orderField="state" <c:if test="${ pageModel.orderField=='state'}">class="${pageModel.orderDirection}"</c:if>> 是否在职 </th>
                <th width="100"  <c:if test="${ pageModel.orderField=='more'}">class="${pageModel.orderDirection}"</c:if>> 备注 </th>
<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="teacher" items="${pageModel.list}">
			<tr target="sid_teacher" rel="${teacher.id}">
			  <td><input name="ids" value="${teacher.id}" type="checkbox"></td>
							<td>${teacher.code}</td>
							<td>${teacher.password}</td>
							<td>${teacher.name}</td>
							<td>${teacher.sex}</td>
							<td>${teacher.age}</td>
							<td>${teacher.school}</td>
							<td>${teacher.address}</td>
							<td>${teacher.phone}</td>
							<td>${teacher.registtime}</td>
							<td>${teacher.state}</td>
                            <td>${teacher.more}</td>
					<td>
				    <a title="删除教师，该教师的考勤记录也将一并删除，不可恢复，确定要删除这个教师吗？" target="ajaxTodo" href="${contextPath}/teacher/deleteTeacherByIdAction.do?teacher.id=${teacher.id}" class="btnDel">删除</a>
					<a title="编辑"  target="dialog" width="440" height="400" mask="true" href="${contextPath}/teacher/showUpdateTeacherAction.do?teacher.id=${teacher.id}"class="btnEdit">编辑</a>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
		<!-- 自定义标签封装pagerForm，简化分页 -->
	<custom:pagination pageModel="${pageModel}"/>
	
</div>
 