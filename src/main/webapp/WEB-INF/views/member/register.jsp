<!DOCTYPE html>
<html lang="en">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <script src="https://kit.fontawesome.com/7827e8eaaf.js"></script>
  <!-- Bootstrap CSS -->
  <link rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
        crossorigin="anonymous">
  <title>Title</title>
</head>
<body>
<jsp:include page="../common/navbar.jsp"/>
<div class="container">
  <form class="px-2 py-1" action="/member/register" method="post">
    <div class="form-group mt-2 row">
      <label for="displayName">Display Name</label>
      <input type="text" class="form-control mt-2 col-12"
             value="<c:out value="${displayName}"/>"
             name="displayName" id="displayName">
      <small class="form-text text-danger"><c:out value="${displayNameMessage}"/></small>
    </div>
    <div class="form-group mt-2 row">
      <label for="emailAddress">Email Address</label>
      <input type="email" class="form-control mt-2 col-12"
             value="<c:out value="${emailAddress}"/>"
             name="emailAddress" id="emailAddress">
    </div>
    <div class="form-group mt-2 row">
      <label for="confirmEmailAddress">Confirm Email
        Address</label>
      <input type="email" class="form-control mt-2 col-12"
             value="<c:out value="${confirmEmailAddress}"/>"
             name="confirmEmailAddress" id="confirmEmailAddress">
      <small class="form-text text-danger"><c:out value="${emailMessage}"/></small>
    </div>
    <div class="form-group mt-2 row">
      <label for="password">Password</label>
      <input type="password" class="form-control mt-2 col-12"
             value="<c:out value="${password}"/>"
             name="password" id="password">
    </div>
    <div class="form-group mt-2 row">
      <label for="confirmPassword">Confirm Password</label>
      <input type="password" class="form-control mt-2 col-12"
             value="<c:out value="${confirmPassword}"/>"
             name="confirmPassword"  id="confirmPassword">
      <small class="form-text text-danger"><c:out value="${passwordMessage}"/></small>
    </div>
    <div class="custom-control custom-checkbox mt-3">
      <input type="checkbox" class="custom-control-input"
             id="Validation1" required>
      <label class="custom-control-label" for="Validation1">I
        agree to the Terms of
        Use and Privacy Policy </label>
      <div class="invalid-feedback">required</div>
    </div>
    <input type="submit" class="btn btn-danger btn-block mt-3">
  </form>
</div>
<jsp:include page="../common/footer.jsp"/>
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