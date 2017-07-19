<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/page/common/common.jsp"%> 
    
<!-- 自定义标签封装pagerForm，简化分页 -->
<custom:paginationForm action="${contextPath}/student/queryStudentAction.action">
	<input type="hidden" name="student.code" value="${student.code}"/>
    <input type="hidden" name="student.name" value="${student.name}"/>
    <input type="hidden" name="student.school" value="${student.school}"/>
    <input type="hidden" name="student.state" value="${student.state}"/>
    <input type="hidden" name="dateBegin" value="${dateBegin}"/>
    <input type="hidden" name="dateEnd" value="${dateEnd}"/>
</custom:paginationForm>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${contextPath}/student/queryStudentAction.action" method="post">
	<div class="searchBar">
        <table class="searchContent">
            <tbody><tr>
                <td>
                    卡号：
                    <input type="text" name="student.code" value="${student.code}" maxlength="20" />
                </td>
                <td>
                    姓名：
                   <input type="text" name="student.name" value="${student.name}" maxlength="20" />
                </td>

                <td>
                    学校：
                    <input type="text" name="student.school" value="${student.school}" maxlength="20" />
                </td>
                <td>是否在读:
                    <select name="student.state" >
                        <option value="" <c:if test="${student.state==''}">selected="selected"</c:if>>所有</option>
                        <option value="是"  <c:if test="${student.state=='是'}">selected="selected"</c:if> >是</option>
                        <option value="否"  <c:if test="${student.state=='否'}">selected="selected"</c:if> >否</option>
                    </select>
                </td>
                <td class="dateRange">
                    注册时间：
                    <input type="text" name="dateBegin" value="${dateBegin}" minDate="2010-01-01" maxDate="2050-12-31" class="date textInput readonly valid" readonly="true">
                    <span class="limit">-</span>
                    <input type="text" name="dateEnd" value="${dateEnd}" minDate="2010-01-01" maxDate="2050-12-31" class="date textInput readonly valid" readonly="true">
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
			<li><a class="add" href="${contextPath}/student/showAddStudentAction.do" target="dialog" width="440" height="470"  mask="true"><span>添加</span></a></li>
			<li><a title="删除学生，该学生的考勤记录也将一并删除，不可恢复，确实要删除这些学生吗?" target="selectedTodo" rel="ids" href="${contextPath}/student/deleteStudentByArrayAction.do" class="delete" warn="请选择用户！"><span>删除</span></a></li>
			<li><a class="edit" href="${contextPath}/student/showUpdateStudentAction.do?student.id={sid_student}" target="dialog" width="440" height="470"  mask="true" warn="请选择一个用户！"><span>修改</span></a></li>
			<li class="line">line</li>
            <li><a class="icon" href="${contextPath }/student/exportStudentAction.do" target="dwzExport" title="确定要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="1169" layoutH="110">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="100" orderField="code" <c:if test="${ pageModel.orderField=='code'}">class="${pageModel.orderDirection}"</c:if>> 卡号 </th>
                <th width="100"> 密码 </th>
                <th width="100" orderField="name" <c:if test="${ pageModel.orderField=='name'}">class="${pageModel.orderDirection}"</c:if>> 姓名 </th>
                <th width="50" orderField="sex" <c:if test="${ pageModel.orderField=='sex'}">class="${pageModel.orderDirection}"</c:if>> 性别 </th>
				<th width="50" orderField="age" <c:if test="${ pageModel.orderField=='age'}">class="${pageModel.orderDirection}"</c:if>> 年龄 </th>
				<th width="100" > 地址 </th>
				<th width="100" > 学校 </th>
				<th width="100" orderField="grade" <c:if test="${ pageModel.orderField=='grade'}">class="${pageModel.orderDirection}"</c:if>> 年级 </th>
				<th width="50"  > 班级 </th>
				<th width="100" > 家庭电话 </th>
				<th width="100" > 学生电话 </th>
				<th width="100" > 注册时间 </th>
                <th width="50" > 是否在读 </th>
                <th width="50" > 备注 </th>

<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="student" items="${pageModel.list}">
			<tr target="sid_student" rel="${student.id}">
			  <td><input name="ids" value="${student.id}" type="checkbox"></td>
							<td>${student.code}</td>
                            <td>${student.password}</td>
							<td>${student.name}</td>
							<td>${student.sex}</td>
							<td>${student.age}</td>
							<td>${student.address}</td>
							<td>${student.school}</td>
							<td>${student.grade}</td>
							<td>${student.clazz}</td>
							<td>${student.familyphone}</td>
							<td>${student.phone}</td>
							<td>${student.registtime}</td>
                            <td>${student.state}</td>
							<td>${student.more}</td>
					<td>
				    <a title="删除学生，该学生的考勤记录也将一并删除，不可恢复，确定要删除这个学生吗？" target="ajaxTodo" href="${contextPath}/student/deleteStudentByIdAction.do?student.id=${student.id}" class="btnDel">删除</a>
					<a title="编辑"  target="dialog" width="440" height="470" mask="true" href="${contextPath}/student/showUpdateStudentAction.do?student.id=${student.id}"class="btnEdit">编辑</a>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
		<!-- 自定义标签封装pagerForm，简化分页 -->
	<custom:pagination pageModel="${pageModel}"/>
	
</div>
 