<%--
  Created by IntelliJ IDEA.
  User: antonusenkov
  Date: 7.08.23
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../header.jsp" %>
<div class="jumbotron">
    <div class="container">

        <c:if test="${error == true}">
            <h2>${error_message}</h2>
        </c:if>

        <c:if test="${error == null}">
            <c:if test="${employees.size() != 0}">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th scope="col">Name</th>
                        <th scope="col">Department</th>
                        <th scope="col">Role</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${employees}" var="employee">
                        <tr>
                            <td>${employee.name}</td>
                            <td>${employee.department.name}</td>
                            <td>${employee.role.name}</td>
                            <td><a href="/employee/view/${employee.id}">Details</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${currAccount != null && currAccount.employee.role.id eq 1}">
                <a href="<c:url value="/employee/add"/>" class="btn btn-primary btn-block" role="button"
                   aria-pressed="true">Add Employee</a>
            </c:if>
        </c:if>
    </div>
</div>
<%@ include file="../footer.jsp" %>