<%--
  Created by IntelliJ IDEA.
  User: antonusenkov
  Date: 7.08.23
  Time: 17:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../header.jsp" %>
<div class="jumbotron">
    <div class="container">
        <form>
            <div class="form-group">
                <label>Name</label>
                <input type="text" class="form-control" value="${account.employee.name}" disabled>
            </div>
            <div class="form-group">
                <label>Birthday</label>
                <input type="text" class="form-control" value="${account.employee.birthday}" disabled>
            </div>
            <div class="form-group">
                <label>Department</label>
                <input type="text" class="form-control" value="${account.employee.department.name}" disabled>
            </div>
            <div class="form-group">
                <label>Role</label>
                <input type="text" class="form-control" value="${account.employee.role.name}" disabled>
            </div>
            <div class="form-group">
                <label>Salary</label>
                <input type="text" class="form-control" value="${account.employee.salary.quantity}" disabled>
            </div>
            <div class="form-group">
                <label>Login</label>
                <input type="text" class="form-control" value="${account.login}" disabled>
            </div>
        </form>

        <a href="<c:url value="/holiday/employee/${account.employee.id}"/>" class="btn btn-success btn-block"
           role="button" aria-pressed="true">Holidays</a>

        <c:if test="${currAccount.employee.role.id eq 1}">
            <a href="<c:url value="/employee/edit/${account.employee.id}"/>" class="btn btn-dark btn-block" role="button">Edit</a>
            <c:if test="${currAccount.login != account.login}">
                <button type="button" class="btn btn-outline-danger btn-block" data-toggle="modal"
                        data-target="#deleteModal">
                    Delete
                </button>
                <!-- Modal -->
                <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog"
                     aria-labelledby="deleteModalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="deleteModalLabel">Delete Employee</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                Name ${account.employee.name}<br>
                                Birthday ${account.employee.birthday}<br>
                                Department ${account.employee.department.name}<br>
                                Role ${account.employee.role.name}
                            </div>
                            <div class="modal-footer">
                                <form action="<c:url value="/employee/delete"/>" method="post">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    <input hidden name="employee_id" value="${account.employee.id}">
                                    <button type="submit" class="btn btn-danger">Delete</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Modal -->
            </c:if>
        </c:if>

    </div>
</div>
<%@ include file="../footer.jsp" %>