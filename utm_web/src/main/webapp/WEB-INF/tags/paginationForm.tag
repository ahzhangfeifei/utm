<!-- 
 * 自定义标签，对dwz的分页进行封装
 * @author     : zhangfeifei
 * @group      : utm
 * @Version    : 1.0.0
 -->

<%@ tag language="java" pageEncoding="UTF-8" body-content="scriptless"  trimDirectiveWhitespaces="true"%>

<!-- 自定义标签 属性 -->
<%@ attribute name="action" type="java.lang.String" required="true"%>
<%@ attribute name="onsubmit" type="java.lang.String"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form id="pagerForm" method="post" action="${action }"
		<c:if test="${onsubmit!=null}"> onsubmit="${onsubmit}"</c:if>>
	<input type="hidden" name="pageNum" value="${pageModel.pageNum}"/>
	<input type="hidden" name="numPerPage" value="${pageModel.numPerPage}"/>
	<input type="hidden" name="orderField" value="${pageModel.orderField}"/>
	<input type="hidden" name="orderDirection" value="${pageModel.orderDirection}"/>
	<jsp:doBody/>
</form>