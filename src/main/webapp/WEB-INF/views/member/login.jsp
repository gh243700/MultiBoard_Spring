<!doctype html>
<html lang="en">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="https://kit.fontawesome.com/7827e8eaaf.js"></script>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <title>Forum</title>
</head>
<body class="bg-light">
<jsp:include page="../common/navbar.jsp"/>
<div class="alert alert-danger">
    <strong>info!</strong> <c:out value="${message}"/>
</div>
<div class="container" style="margin-top: 5rem">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card" style="height: 30rem">
                <div class="card-header">Sign In</div>

                <div class="card-body" style="margin-top: 2rem">
                    <form action="" method="">
                        <button class="btn btn-primary mt-3 mb-3"><i class="fab fa-google"></i>
                        </button>
                        <div class="form-group row">
                            <label for="email_address"
                                   class="col-md-4 col-form-label text-md-right">
                                Email or Display Name</label>
                            <div class="col-md-6">
                                <input type="text" id="email_address" class="form-control"
                                       name="emailAddressOrName"
                                       value="<c:out value="${value}"/>"
                                       required autofocus>
                            </div>
                        </div>
                        <div class="form-group row mt-3">
                            <label for="password" class="col-md-4 col-form-label text-md-right">Password</label>
                            <div class="col-md-6">
                                <input type="password" id="password" class="form-control"
                                       name="password" value="<c:out value="${password}"/>"
                                       required>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-md-6 offset-md-4">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" name="rememberUser"> Remember Me
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 offset-md-4">

                            <button type="submit" class="btn btn-danger">
                                Sign In
                            </button>
                            <a href="#" class="btn btn-link" style="color: black">
                                Forgot Your Password?
                            </a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../common/footer.jsp"/>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>
</html>