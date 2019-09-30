<!doctype html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
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
<body class="bg-light" style="height: 100%">
<jsp:include page="../common/navbar.jsp"/>
<!-- navbar-->

<c:set value="${discussionInfo}" var="discussion"/>
<c:set value="${categoryInfo}" var="category"/>
<c:set value="${topicInfo}" var="topic"/>
<c:set value="${posts}" var="posts"/>
<div class="container-fluid pl-5 pr-5">
    <i class="fas fa-home"></i>
    <span class="ml-1">
      <c:out value="Home > ${category.title} > ${discussion.title} > ${topic.title}"/>
  </span>
    <hr>
    <div class="form-inline">
        <div>
            <img src="${topic.writerInfo.profileImg}" alt="" class="img rounded mr-2" style="width: 5rem; height: 5rem;"></div>
        <div>
            <h3 class="mt-2">
                <c:out value="${topic.title}"/>
                <br>
                <small class="form-inline" style="font-size: 1rem">
                    <c:out value="By ${topic.writerInfo.username}, ${topic.writeDate} in
                    ${discussion.title} · ${topic.replyNumber} replies"/></small>
            </h3>
        </div>
    </div>
    <div class="row">
        <!--    -->
        <div class="col-10 mt-2">
            <section>
                <nav class="nav mt-3 p-2">
                    <!-- maximum of 6 btn for paging -->
                    <a class="btn btn-sm  mr-1" href=""><<</a>
                    <a class="btn btn-sm  mr-1" href="">PREV</a>
                    <a class="btn btn-sm  mr-1" href="">1</a>
                    <a class="btn btn-sm  mr-1" href="">2</a>
                    <a class="btn btn-sm  mr-1" href="">3</a>
                    <a class="btn btn-sm  mr-1" href="">4</a>
                    <a class="btn btn-sm  mr-1" href="">5</a>
                    <a class="btn btn-sm  mr-1" href="">6</a>
                    <a class="btn btn-sm  mr-1" href="">NEXT</a>
                    <a class="btn btn-sm  mr-1" href="">>></a>
                </nav>
                <!-- max list of 25-->
                <c:forEach items="${posts}" var="post">
                    <div class="row">
                        <div class="col-1">
                            <div class="">
                                <img src="<c:out value="${post.profileImg}"/>" style="height: 5rem; width: 5rem" alt="..." class="img-rounded mr-2">
                                <h3><c:out value="${post.username}"/></h3>
                                <span>
                                    member <br> <c:out
                                        value="${post.postCount} "/> posts <br> member </span>
                            </div>
                        </div>
                        <div class="col-11">
                            <div>
                                    ${post.content}
                            </div>
                        </div>
                    </div>
                </c:forEach>
                <!--ul-->
                <nav class="nav p-2">
                    <!-- maximum of 6 btn for paging -->
                    <a class="btn btn-sm  mr-1" href=""><<</a>
                    <a class="btn btn-sm  mr-1" href="">PREV</a>
                    <a class="btn btn-sm  mr-1" href="">1</a>
                    <a class="btn btn-sm  mr-1" href="">2</a>
                    <a class="btn btn-sm  mr-1" href="">3</a>
                    <a class="btn btn-sm  mr-1" href="">4</a>
                    <a class="btn btn-sm  mr-1" href="">5</a>
                    <a class="btn btn-sm  mr-1" href="">6</a>
                    <a class="btn btn-sm  mr-1" href="">NEXT</a>
                    <a class="btn btn-sm  mr-1" href="">>></a>
                </nav>
            </section>
        </div>
        <!--random -->
        <div class="col-2" style="">
            <!--random topics -->
            <div class="card">
                <h5 class="card-header">Topics</h5>
                <div class="card-body row">
                    <img src="" alt="" class="col-2">
                    <p class="col-10">
                        <strong>Help. Cant identify problem. Pc wont boot.</strong>
                        By RicHaj
                        <br>
                        Started 1 minute ago
                        <br>
                        Posted in CPUs, Motherboards, and Memory
                    </p>
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