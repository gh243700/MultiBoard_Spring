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
<jsp:include page="../../views/common/navbar.jsp"/>
<!-- navbar-->

<div class="container-fluid pl-5 pr-5">
    <c:set value="${discussionInfo}" var="discussion"/>
    <i class="fas fa-home"></i><span class="ml-1">home > <c:out
        value="${categoryInfo.title}"/> > <c:out value="${discussion.title}"/></span>
    <hr>

    <h3 class="mt-2"><c:out value="${discussion.title}"/></h3>
    <small><c:out value="${discussion.description}"/></small>
    <div class="row">
        <!--    -->
        <div class="col-10 mt-2">
            <div class="d-flex flex-row-reverse">
                <a class="btn btn-danger p-2" href="#">START NEW TOPIC</a>
            </div>
            <section>
                <nav class="nav bg-white mt-3 p-2">
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
                <c:forEach items="${topicList}" var="topic">
                    <ul class="list-group ">
                        <li class="list-group-item  p-1  ">
                            <div class="row" style="font-size: small">
                                <div class="col-7">
                                    <strong class="d-flex"><c:out value="${topic.title}"/></strong>
                                    <p style="color: darkgray">By <c:out
                                            value="${topic.writerInfo.username}"/>, <c:out
                                            value="${topic.writeDate}"/></p>
                                </div>
                                <div class="col-1 d-flex flex-row-reverse">
                                    <div>
                                        <span>
                                          <c:out value="${topic.replyNumber}"/> replies</span>
                                        <br>
                                        <span style="color: darkgray"><c:out
                                                value="${topic.viewCount}"/> views</span>
                                    </div>
                                </div>
                                <div class="col-4">
                                    <div class="row">
                                        <img src=".." class="img-thumbnail col-1" alt="..">
                                        <div class="col-11">
                                        <span class="">
                                        <c:out value="${topic.lastPostMemberInfo.username}"/></span>
                                            <br>
                                            <span style="color: darkgray">
                                            At <c:out value="${topic.lastPostDate}"/>
                                          </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </ul>
                </c:forEach>
                <!--ul-->
                <nav class="nav bg-white p-2">
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
        </div>
    </div>
</div>

<hr>
<footer class="text-center">
    <small>
        Sample Project
        <br>
        Powered by Springframework
    </small>
</footer>
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