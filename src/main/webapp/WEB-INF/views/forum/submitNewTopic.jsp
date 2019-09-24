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
  <script type="text/javascript" src="/js/ckeditor/ckeditor.js"></script>
  <script src="/js/ckeditor/"></script>
  <!-- Bootstrap CSS -->
  <link rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
        crossorigin="anonymous">
  <title>Forum</title>
</head>
<body class="bg-light">
<jsp:include page="../common/navbar.jsp"/>
<!-- navbar-->
<div class="container-fluid pl-5 pr-5">
  <i class="fas fa-home"></i><span class="ml-1">home > The Hub > General Discussion</span>
  <hr>
  <div class="mt-3">
    <h3>Create New Topic</h3>
  </div>
  <div class="">
    <!--    -->
    <div class=" mt-2 mb-4">
      <div class="bg-white" style="height: 40rem">
        <nav class="">
          <div class="bg-dark" style="height: 3rem">
          </div>
        </nav>
        <form action="" class="p-3">
          <div class="">
            <div class="form-group">
              <label for="Title">Title</label>
              <input type="text" class="form-control" id="Title">
            </div>
            <div class="form-group">
              <label for="Tags">Tags</label>
              <input type="text" class="form-control" id="Tags">
              <small id="emailHelp" class="form-text text-muted">
                Type tags separated by commas.
                <br>
                Each tag should be no longer than 25 characters.</small>
            </div>
            <div class="forum-group mt-4">
              <textarea name="editor"></textarea>
              <script type="text/javascript">
                CKEDITOR.replace('editor',{ filebrowserUploadUrl: '/main/forum/uploadImg'});
              </script>
              <div class="form-inline" style="height: 4rem; background-color: #e6e6e6">
                <i class="m-2 fas fa-paperclip fa-2x"></i>
                <small>
                  Drag files to attach, or choose files...
                  <br>
                  Max total size 20MB
                </small>
              </div>
            </div>
          </div>
        </form>
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