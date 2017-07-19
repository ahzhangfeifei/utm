<!-- 
 *  自定义标签，对dwz的分页进行封装
 * @Version    : 1.0.0
 -->

<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ attribute name="pageModel" type="com.utm.basic.entity.PageModel" required="true"%>
<%@ attribute name="pageNumShown" type="java.lang.Integer"%>
<%@ attribute name="begin" type="java.lang.Integer"%>
<%@ attribute name="end" type="java.lang.Integer"%>
<%@ attribute name="step" type="java.lang.Integer"%>
<%@ attribute name="target" type="java.lang.String"%>
<%@ attribute name="rel" type="java.lang.String"%>
<%@ attribute name="onchange" type="java.lang.String"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${pageNumShown == null}" >
	<c:set var="pageNumShown" value="10"/>
</c:if>
<c:if test="${begin == null}" >
	<c:set var="begin" value="10"/>
</c:if>
<c:if test="${end == null}" >
	<c:set var="end" value="50"/>
</c:if>
<c:if test="${step == null}" >
	<c:set var="step" value="5"/>
</c:if>
<c:if test="${target == null}" >
	<c:set var="target" value="navTab"/>
</c:if>
<c:if test="${onchange == null}" >
	<c:set var="onchange" value="navTabPageBreak({numPerPage:this.value})"/>
</c:if>

<div class="panelBar">
	<div class="pages">
		<span>每页显示</span>
		<select name="numPerPage" onchange="${onchange}">
			<c:forEach begin="${begin }" end="${end }" step="${step }" varStatus="s">
				<c:choose>
					<c:when test="${numPerPage==s.index }">
						<option value="${s.index}" selected="selected">${s.index}</option>
					</c:when>
					<c:when test="${numPerPage!=s.index }">
						<option value="${s.index}">${s.index}</option>
					</c:when>
				</c:choose>
			</c:forEach>						
		</select>
		<span>总条数: ${pageModel.totalRecords}</span>
	</div>
	<div <c:if test="${rel!=null }">rel="${rel}" </c:if> class="pagination" targetType="${target}" totalCount="${pageModel.totalRecords}" numPerPage="${pageModel.numPerPage}" pageNumShown="${pageNumShown}" currentPage="${pageModel.pageNum}"></div>
</div>