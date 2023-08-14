<%--
  Created by IntelliJ IDEA.
  User: antonusenkov
  Date: 7.08.23
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<div class="jumbotron">
    <div class="container">
        <c:if test="${param.error}">
            <div class="alert alert-danger" role="alert">
                    ${param.error}
            </div>
        </c:if>
    </div>
</div>
<%@ include file="footer.jsp" %>