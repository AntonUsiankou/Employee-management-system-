<%--
  Created by IntelliJ IDEA.
  User: antonusenkov
  Date: 7.08.23
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>

<div class="jumbotron">
    <c:if test="${param.error != null}">
        <div class="alert alert-danger" role="alert">
            Error: ${param.error}
        </div>
    </c:if>
    <form action=<c:url value='/login'/> method="post" class="needs-validation" novalidate>
        <div class="form-group">
            <input type="text" class="form-control" name="login" placeholder="Login" required>
            <div class="invalid-feedback">
                Please enter login.
            </div>
        </div>
        <div class="form-group">
            <input type="password" class="form-control" name="pass" placeholder="Password" required>
            <div class="invalid-feedback">
                Please enter password.
            </div>
        </div>
        <input type="submit" class="btn btn-primary" value="Login">
    </form>
</div>
<script>
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
<%@ include file="footer.jsp" %>