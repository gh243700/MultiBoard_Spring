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
<body class="bg-light">
<!-- navbar-->
<jsp:include page="../common/navbar.jsp"/>
<!-- navbar-->
<div class="container-fluid pl-5 pr-5">
    <i class="fas fa-home"></i><span class="ml-1">home</span>
    <hr>
    <h2 class="mt-2">Forums</h2>
    <div class="row">
        <div class="col-10">
            <c:forEach items="${categoryList}" var="category">
                <div class="card border-light mt-2">
                    <div class="card-header"><h4><c:out value="${category.title}"/></h4></div>
                    <c:set value="${category.id}" var="categoryId"/>
                    <c:forEach var="discussion" items="${discussionList}">
                        <c:if test="${discussion.categoryId eq categoryId}">
                            <div class="card-body row">
                                <div class="col-1 text-center">
                                    <div class="btn btn-lg btn-secondary rounded-circle">
                                        <i class="far fa-comments"></i>
                                    </div>
                                </div>
                                <a href="main/forum/page/<c:out value="${discussion.id}"/>" class="col-7"
                                   style="text-decoration: none; color: black">
                                    <h5 class="card-title"><c:out value="${discussion.title}"/></h5>
                                    <p class="card-text"><c:out value="${discussion.description}"/></p>
                                </a>
                                <div class="col-1">
                                    <br>
                                    <p>
                                        <c:out value="${discussion.postCount}"/>
                                        <br>
                                        posts
                                    </p>
                                </div>

                                <div class="col-3" style="">
                                    <img src="" alt="">
                                    <p><c:out value="${discussion.recentPostTopicsTitle}"/>
                                        <br>
                                        By <c:out value="${discussion.member.username}"/>
                                    </p>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </c:forEach>

            <!--member statics -->
            <div class="card border-light mt-2">
                <div class="card-header">Member Statics</div>
                <div class="card-body row">
                    <p class="col">
                        574,138
                        <br>
                        Total Members
                    </p>
                    <p class="col">
                        9,329
                        <br>
                        Most Online
                    </p>
                    <p class="col">
                        NEWEST MEMBER
                        <br>
                        <strong>ZachTheMiscreant</strong>
                        <br>
                        <small>Joined 2 minutes ago</small>
                    </p>
                </div>
            </div>
        </div>
        <!--random -->
        <div class="col-2">
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
            <!-- -->
            <div class="card mt-3">
                <h5 class="card-header">Recent Status Updates</h5>
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