<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/page/common/common.jsp"%> 
    
<!-- 自定义标签封装pagerForm，简化分页 -->
<custom:paginationForm action="${contextPath}/user/queryUserAction.action">
	<input type="hidden" name="userEntity.name" value="${userEntity.name }"/>
</custom:paginationForm>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${contextPath }/user/queryUserAction.action" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>登录名称：</label>
				<input type="text" name="userEntity.name" value="${userEntity.name}" maxlength="20" />
			</li>
		</ul>
	
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${contextPath }/user/showAddUserAction.do" target="dialog"  mask="true"><span>添加</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${contextPath }/user/deleteUserByArrayAction.do" class="delete" warn="请选择用户！"><span>删除</span></a></li>
			<li><a class="edit" href="${contextPath }/user/showUpdateUserAction.do?userEntity.id={sid_user}" target="dialog"  mask="true" warn="请选择一个用户！"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="确定要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="1169" layoutH="138">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<%--<th width="140" orderField="id" <c:if test="${ pageModel.orderField=='id'}">class="${pageModel.orderDirection}"</c:if>">序号</th>--%>
				<th width="330" orderField="name" <c:if test="${ pageModel.orderField=='name'}">class="${pageModel.orderDirection}"</c:if>">登录名称</th>
				<th width="330" orderField="password" <c:if test="${ pageModel.orderField=='password'}">class="${pageModel.orderDirection}"</c:if>">用户密码</th>

				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${pageModel.list}">
			<tr target="sid_user" rel="${item.id}">
			    <td><input name="ids" value="${item.id}" type="checkbox"></td>
				<%--<td>${item.id}</td>--%>
				<td>${item.name}</td>
				<td>${item.password}</td>
				<td>
				    <a title="确定要删除这条记录吗？" target="ajaxTodo" href="${contextPath }/user/deleteUserByIdAction.do?userEntity.id=${item.id}" class="btnDel">删除</a>
					<a title="编辑"  target="dialog" mask="true" href="${contextPath }/user/showUpdateUserAction.do?userEntity.id=${item.id}"class="btnEdit">编辑</a>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
		<!-- 自定义标签封装pagerForm，简化分页 -->
	<custom:pagination pageModel="${pageModel }"/>
	
</div>
 