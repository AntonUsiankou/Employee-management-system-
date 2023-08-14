<%--
  Created by IntelliJ IDEA.
  User: antonusenkov
  Date: 7.08.23
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>

<div class="jumbotron">
    <div class="container">
        <c:set var="login" value="Guest"/>
        <c:if test="${currAccount ne null}" var="isLogged">
            <c:set var="login" value="${currAccount.employee.name}"/>
        </c:if>
        <h2>Hello ${login}</h2>
        <c:if test="${!isLogged}">
            <a href="/login">Login</a>
        </c:if>
    </div>
</div>
<%@ include file="footer.jsp" %>
