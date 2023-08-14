<%--
  Created by IntelliJ IDEA.
  User: antonusenkov
  Date: 7.08.23
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../header.jsp" %>
<div class="jumbotron">
    <div class="container">

        <div class="list-group">
            <c:forEach items="${departments}" var="department">
                <a href="<c:url value="/department/view/${department.id}"/>"
                   class="list-group-item list-group-item-action">${department.name}</a>
            </c:forEach>
        </div>

    </div>
</div>
<%@ include file="../footer.jsp" %>