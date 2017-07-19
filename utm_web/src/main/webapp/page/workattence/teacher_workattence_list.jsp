<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/page/common/common.jsp"%> 
    
<!-- 自定义标签封装pagerForm，简化分页 -->
<custom:paginationForm action="${contextPath}/workattence/queryTeacherWorkattenceAction.action">
	<input type="hidden" name="workattence.teacherByTeacherid.code" value="${workattence.teacherByTeacherid.code}"/>
    <input type="hidden" name="workattence.teacherByTeacherid.name" value="${workattence.teacherByTeacherid.name}"/>
    <input type="hidden" name="workattence.state" value="${workattence.state}"/>
    <input type="hidden" name="dateEnd" value="${dateEnd}"/>
    <input type="hidden" name="dateBegin" value="${dateBegin}"/>
</custom:paginationForm>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${contextPath}/workattence/queryTeacherWorkattenceAction.action" method="post">
	<div class="searchBar">
        <table class="searchContent">
            <tbody><tr>
                <td>
                    卡号：
                    <input type="text" name="workattence.teacherByTeacherid.code" value="${workattence.teacherByTeacherid.code}" maxlength="20" />
                </td>
                <td>
                    姓名：
                    <input type="text" name="workattence.teacherByTeacherid.name" value="${workattence.teacherByTeacherid.name}" maxlength="20" />
                </td>
                <td>是否可用:
                    <select name="workattence.state" >
                        <option value="" <c:if test="${workattence.state==''}">selected="selected"</c:if>>所有</option>
                        <option value="是"  <c:if test="${workattence.state=='是'}">selected="selected"</c:if> >是</option>
                        <option value="否"  <c:if test="${workattence.state=='否'}">selected="selected"</c:if> >否</option>
                    </select>
                </td>
                <td class="dateRange">
                    登入日期：
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
			<li><a class="add" width="440" href="${contextPath}/workattence/showAddTeacherWorkattenceAction.do" target="dialog"  mask="true"><span>添加</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${contextPath}/workattence/deleteTeacherWorkattenceByArrayAction.do" class="delete" warn="请选择用户！"><span>删除</span></a></li>
			<li><a class="edit" width="440" href="${contextPath}/workattence/showUpdateTeacherWorkattenceAction.do?workattence.id={sid_workattence}" target="dialog"  mask="true" warn="请选择一个用户！"><span>修改</span></a></li>
			<li class="line">line</li>
            <li><a class="icon"  href="${contextPath }/workattence/exportTeacherWorkattenceAction.do" target="dwzExport" title="确定要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="1145" layoutH="110">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="100" > 卡号 </th>
                <th width="100" > 姓名 </th>
				<th width="120" orderField="comedate" <c:if test="${ pageModel.orderField=='comedate'}">class="${pageModel.orderDirection}"</c:if>> 登入日期 </th>
				<th width="120" orderField="cometime" <c:if test="${ pageModel.orderField=='cometime'}">class="${pageModel.orderDirection}"</c:if>> 登入时间 </th>
				<th width="120" orderField="leavedate" <c:if test="${ pageModel.orderField=='leavedate'}">class="${pageModel.orderDirection}"</c:if>> 登出日期 </th>
				<th width="120" orderField="leavetime" <c:if test="${ pageModel.orderField=='leavetime'}">class="${pageModel.orderDirection}"</c:if>> 登出时间 </th>
				<th width="150" > 备注 </th>
				<th width="100" orderField="state" <c:if test="${ pageModel.orderField=='state'}">class="${pageModel.orderDirection}"</c:if>> 是否可用 </th>
<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="workattence" items="${pageModel.list}">
			<tr target="sid_workattence" rel="${workattence.id}">
			  <td><input name="ids" value="${workattence.id}" type="checkbox"></td>
							<td>${workattence.teacherByTeacherid.code}</td>
                            <td>${workattence.teacherByTeacherid.name}</td>
							<td>${workattence.comedate}</td>
							<td>${workattence.cometime}</td>
							<td>${workattence.leavedate}</td>
							<td>${workattence.leavetime}</td>
							<td>${workattence.more}</td>
							<td>${workattence.teacherByTeacherid.state}</td>
					<td>
				    <a title="确定要删除这条记录吗？" target="ajaxTodo" href="${contextPath}/workattence/deleteTeacherWorkattenceByIdAction.do?workattence.id=${workattence.id}" class="btnDel">删除</a>
					<a title="编辑"  target="dialog" width="440" mask="true" href="${contextPath}/workattence/showUpdateTeacherWorkattenceAction.do?workattence.id=${workattence.id}"class="btnEdit">编辑</a>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
		<!-- 自定义标签封装pagerForm，简化分页 -->
	<custom:pagination pageModel="${pageModel}"/>
	
</div>
 