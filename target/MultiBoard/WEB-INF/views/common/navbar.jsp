<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-3">
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/home"><i class="far fa-comment"></i>Forums <span
                        class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#"><i class="fas fa-server"></i>All Activity</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    My Activity Streams
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="#">Unread Content</a>
                    <a class="dropdown-item" href="#">Content I Started</a>
                    <a href="" class="dropdown-item">Content I Follow</a>
                    <a href="" class="dropdown-item">Members I Follow</a>
                    <a href="" class="dropdown-item">Content I Posted In</a>
                </div>
            </li>
        </ul>
        <c:set value="${sessionScope.member}" var="member"/>
        <!--show when login-->
        <c:if test="${member != null}">

            <div class="form-inline my-2 my-lg-0">
                <button class="btn btn-secondary rounded-circle mr-1"><i class="far fa-bell"></i>
                </button>
                <button class="btn btn-secondary rounded-circle mr-1"><i
                        class="fas fa-envelope"></i>
                </button>
                <div class="dropdown">
                    <a class="btn btn-secondary btn-sm dropdown-toggle rounded-pill" href="#"
                       role="button"
                       id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true"
                       aria-expanded="false">
                        <img style="height: 2rem; width: 2rem;" src="<c:out value="${member.profileImg}"/>" alt="..." class="img-thumbnail rounded-circle">
                        <c:out value="${member.username}"/>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                        <a class="dropdown-item" href="#">Profile</a>
                        <a class="dropdown-item" href="#">My Activity</a>
                        <a class="dropdown-item" href="#">Update Status</a>
                        <a href="" class="dropdown-item">Manage Followed Content</a>
                        <a href="/member/logout" class="dropdown-item">Logout</a>
                    </div>
                </div>
            </div>
            <!--show when login-->
            <!-- else -->
        </c:if>
        <c:if test="${member == null}">


            <div class="form-inline">
                <div class="dropdown mr-2">
                    <a class="btn btn-dark dropdown-toggle" href="#" role="button"
                       id="dropdownMenuLink1"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <small>Existing user? Sign In</small>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right"
                         aria-labelledby="dropdownMenuLink1">
                        <div class="col">
                            <form class="px-1 py-2" method="post" action="/member/login">
                                <h5>SIGN IN</h5>
                                <div class="form-group  mt-4">
                                    <label for="exampleDropdownFormEmail1"></label>
                                    <input type="text" class="form-control"
                                           name="emailAddressOrName"
                                           id="exampleDropdownFormEmail1"
                                           placeholder="Email or Display Name">
                                </div>
                                <div class="form-group  mt-2">
                                    <label for="exampleDropdownFormPassword1"></label>
                                    <input type="password" class="form-control"
                                           name="password"
                                           id="exampleDropdownFormPassword1"
                                           placeholder="Password">
                                </div>
                                <div class="form-group mt-2">
                                    <div class="form-check">
                                        <input type="checkbox" class="form-check-input"
                                               name="rememberUser"
                                               id="dropdownCheck">
                                        <label class="form-check-label" for="dropdownCheck">
                                            Remember me
                                        </label>
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-danger btn-block mt-2">Sign in
                                </button>
                                <div class="d-flex flex-row-reverse mt-2">
                                    <a href=""><small style="color: darkgray">Forgot your
                                        password?</small></a>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- sign up-->
                <button type="button" class="btn btn-danger rounded-pill" data-toggle="modal"
                        data-target="#signup">
                    SIGN UP
                </button>
                <!-- Modal -->
                <div class="modal fade" id="signup" tabindex="-1" role="dialog" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h6 class="modal-title">Sign Up</h6>
                                <button type="button" class="close" data-dismiss="modal"
                                        aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body mt-2">
                                <div class="row">
                                    <div class="col-8">
                                        <h5>Get started faster</h5>
                                        <button class="btn btn-primary btn-block"><i
                                                class="fab fa-google mr-3"></i>Sign
                                            in with Google
                                        </button>
                                    </div>
                                </div>
                                <div class="mt-4 p">
                                    <form class="px-2 py-1" action="/member/register" method="post">
                                        <div class="form-group mt-2 row">
                                            <label for="displayName">Display Name</label>
                                            <input type="text" class="form-control mt-2 col-12"
                                                   name="displayName" id="displayName">
                                        </div>
                                        <div class="form-group mt-2 row">
                                            <label for="emailAddress">Email Address</label>
                                            <input type="email" class="form-control mt-2 col-12"
                                                   name="emailAddress" id="emailAddress">
                                        </div>
                                        <div class="form-group mt-2 row">
                                            <label for="confirmEmailAddress">Confirm Email
                                                Address</label>
                                            <input type="email" class="form-control mt-2 col-12"
                                                   name="confirmEmailAddress"
                                                   id="confirmEmailAddress">
                                        </div>
                                        <div class="form-group mt-2 row">
                                            <label for="password">Password</label>
                                            <input type="password" class="form-control mt-2 col-12"
                                                   name="password" id="password">
                                        </div>
                                        <div class="form-group mt-2 row">
                                            <label for="ConfirmPassword">Confirm Password</label>
                                            <input type="password" class="form-control mt-2 col-12"
                                                   name="confirmPassword" id="confirmPassword">
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
                            </div>
                        </div>
                    </div>
                    <!-- sign up-->
                </div>
                <!-- else -->
            </div>
        </c:if>

    </div>
</nav>