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
        <c:if test="${param.error != null}">
            <div class="alert alert-danger" role="alert">
                Error: ${param.error}
            </div>
        </c:if>
        <form action="<c:url value="/employee/edit"/>" method="post" class="needs-validation" novalidate>
            <input hidden value="${account.employee.id}" name="employeeId">
            <div class="form-group">
                <label>Name</label>
                <input type="text" class="form-control" name="employeeName" value="${account.employee.name}"
                       placeholder="Name"
                       required>
                <div class="invalid-feedback">
                    Please enter name.
                </div>
            </div>
            <div class="form-group">
                <label>Birthday</label>
                <input type="text" class="form-control" name="employeeBirthday" value="${account.employee.birthday}"
                       placeholder="Birthday YYYY-MM-DD"
                       required>
                <div class="invalid-feedback">
                    Please enter birthday.
                </div>
            </div>
            <div class="form-group">
                <label>Department</label>
                <select class="form-control" name="employeeDepartment">
                    <c:forEach items="${departments}" var="department">
                        <option
                                <c:if test="${department.name == account.employee.department.name}">
                                    selected
                                </c:if>>
                                ${department.name}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label>Role</label>
                <select class="form-control" name="employeeRole">
                    <c:forEach items="${roles}" var="role">
                        <option
                                <c:if test="${role.name == account.employee.role.name}">
                                    selected
                                </c:if>
                        >${role.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label>Salary</label>
                <input type="text" class="form-control" name="employeeSalary"
                       value="${account.employee.salary.quantity}"
                       placeholder="Salary" required onkeypress="return isNumber(event)">
                <div class="invalid-feedback">
                    Please enter salary.
                </div>
            </div>
            <hr>
            <div class="form-group">
                <label>Login</label>
                <input hidden name="currLogin" value="${account.login}">
                <input type="text" class="form-control" name="employeeLogin" value="${account.login}"
                       placeholder="Login"
                       required>
                <div class="invalid-feedback">
                    Please enter login.
                </div>
            </div>
            <div class="form-group">
                <label>Password</label>
                <input type="password" class="form-control" name="employeePass" value="${account.pass}"
                       placeholder="Password"
                       required>
                <div class="invalid-feedback">
                    Please enter password.
                </div>
            </div>
            <a href="<c:url value="/employee/view/${account.employee.id}"/>" class="btn btn-secondary btn-block" role="button"
               aria-pressed="true">Back</a>
            <button type="submit" class="btn btn-dark btn-block">Update</button>
        </form>

    </div>
</div>
<script>
    function isNumber(evt) {
        evt = (evt) ? evt : window.event;
        var charCode = (evt.which) ? evt.which : evt.keyCode;
        if ((charCode > 31 && charCode < 48) || charCode > 57) {
            return false;
        }
        return true;
    }

    // Example starter JavaScript for disabling form submissions if there are invalid fields
    (function () {
        'use strict';
        window.addEventListener('load', function () {
            // Fetch all the forms we want to apply custom Bootstrap validation styles to
            var forms = document.getElementsByClassName('needs-validation');
            // Loop over them and prevent submission
            var validation = Array.prototype.filter.call(forms, function (form) {
                form.addEventListener('submit', function (event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
</script>
<%@ include file="../footer.jsp" %>