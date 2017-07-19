<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/page/common/common.jsp"%>

<!-- 自定义标签封装pagerForm，简化分页 -->
<custom:paginationForm action="${contextPath}/workattence/mainWorkattenceAction.action">
    <input type="hidden" name="workattence.type" value="${workattence.type}"/>
    <input type="hidden" name="cardCode" value="${cardCode}"/>
</custom:paginationForm>

<div class="pageContent">
        <div class="accountInfo">
            <form name="tform" rel="pagerForm" onsubmit="return navTabSearch(this);" action="${contextPath}/workattence/mainWorkattenceAction.action?flag=1" method="post">
            <table class="searchContent" style="padding-top: 10px">
                <tbody><tr>
                    <td style="font-size:40px">
                        卡号：
                        <input type="text"  style="width:500px;height: 35px;font-size: 25px;vertical-align:middle;position:relative" name="cardCode" value="${cardCode}" maxlength="20" />
                        <input type="hidden" name="lastCardCode" value="${lastCardCode}"/>
                        <input type="hidden" name="lastWorkTime" value="${lastWorkTime}"/>
                    </td>
                    <td><div><button type="submit" style="height:44px;width:90px;font-size: 25px">打卡</button></div></td>
                </tr>
                </tbody></table>
                <input type="hidden" name="workattence.more" value="make sure workattence is not null"/>
            </form>
        </div>

    <div class="accountInfo">
        <p><span style="font-size:40px">${logintype}${result}</span></p>
    </div>

    <table class="table" width="1145" layoutH="168">
        <thead>
        <tr>
            <th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
            <th width="100" > 卡号 </th>
            <th width="100" > 姓名 </th>
            <th width="120" > 登入日期 </th>
            <th width="120" > 登入时间 </th>
            <th width="120" > 登出日期 </th>
            <th width="120" > 登出时间 </th>
            <th width="150" > 备注 </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="workattence" items="${pageModel.list}">
            <tr target="sid_workattence" rel="${workattence.id}">
                <td><input name="ids" value="${workattence.id}" type="checkbox"></td>

                <c:choose>
                    <c:when test="${workattence.type=='S'}">
                        <td>${workattence.studentByStudentid.code}</td>
                        <td>${workattence.studentByStudentid.name}</td>
                    </c:when>
                    <c:otherwise>
                        <td>${workattence.teacherByTeacherid.code}</td>
                        <td>${workattence.teacherByTeacherid.name}</td>
                    </c:otherwise>
                </c:choose>
                <td>${workattence.comedate}</td>
                <td>${workattence.cometime}</td>
                <td>${workattence.leavedate}</td>
                <td>${workattence.leavetime}</td>
                <td>${workattence.more}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- 自定义标签封装pagerForm，简化分页 -->
    <custom:pagination pageModel="${pageModel}"/>
    <script type="text/javascript">

        function focusElment(formName,elemName){
            var elem=document.forms[formName].elements[elemName];
//            if(elem!=undefined)
//            {
                elem.focus();
                elem.select();
//            }

        }

        focusElment("tform","cardCode");

    </script>
</div>

