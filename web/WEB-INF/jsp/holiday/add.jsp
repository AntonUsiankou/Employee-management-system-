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
        <form action="<c:url value="/employee/add"/>" method="post" class="needs-validation" novalidate>
            <div class="form-group">
                <label>Login</label>
                <input type="text" class="form-control" name="employeeLogin" placeholder="Login" required>
                <div class="invalid-feedback">
                    Please enter login.
                </div>
            </div>
            <div class="form-group">
                <label>Password</label>
                <input type="password" class="form-control" name="employeePass" placeholder="Password" required>
                <div class="invalid-feedback">
                    Please enter password.
                </div>
            </div>
            <hr>
            <div class="form-group">
                <label>Name</label>
                <input type="text" class="form-control" name="employeeName" placeholder="Name" required>
                <div class="invalid-feedback">
                    Please enter name.
                </div>
            </div>
            <div class="form-group">
                <label>Birthday</label>
                <input type="text" class="form-control" name="employeeBirthday" placeholder="Birthday YYYY-MM-DD"
                       required>
                <div class="invalid-feedback">
                    Please enter birthday.
                </div>
            </div>
            <div class="form-group">
                <label>Department</label>
                <select class="form-control" name="employeeDepartment">
                    <c:forEach items="${departments}" var="department">
                        <option>${department.name}</option>
                    </c:forEach>
                </select>
                <div class="invalid-feedback">
                    Please enter department.
                </div>
            </div>
            <div class="form-group">
                <label>Role</label>
                <select class="form-control" name="employeeRole">
                    <c:forEach items="${roles}" var="role">
                        <option>${role.name}</option>
                    </c:forEach>
                </select>
                <div class="invalid-feedback">
                    Please enter role.
                </div>
            </div>
            <hr>
            <div class="form-group">
                <label>Salary</label>
                <input type="text" class="form-control" name="employeeSalary" placeholder="Salary" required
                       onkeypress="return isNumber(event)">
                <div class="invalid-feedback">
                    Please enter salary.
                </div>
            </div>
            <input type="submit" class="btn btn-primary btn-block" value="Add">
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