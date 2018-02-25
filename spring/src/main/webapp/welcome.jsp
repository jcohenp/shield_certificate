<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <link rel="apple-touch-icon" sizes="76x76" href="${contextPath}/resources/images/dashboard/apple-icon.png">
    <link rel="icon" type="image/png" href="${contextPath}/resources/images/favicon.ico">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>Shield Certificate - Dashboard</title>
    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no' name='viewport' />
    <!--     Fonts and icons     -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700,200" rel="stylesheet" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" />
    <!-- CSS Files -->
    <link href="${contextPath}/resources/css/dashboard/bootstrap.min.css" rel="stylesheet" />
    <link href="${contextPath}/resources/css/dashboard/light-bootstrap-dashboard.css?v=2.0.1" rel="stylesheet" />
    <!-- CSS Just for demo purpose, don't include it in your project -->
    <link href="${contextPath}/resources/css/dashboard/demo.css" rel="stylesheet" />
</head>

<body>
<div class="wrapper">
    <div class="sidebar" data-image="${contextPath}/resources/images/dashboard/sidebar-5.jpg">
        <!--
    Tip 1: You can change the color of the sidebar using: data-color="purple | blue | green | orange | red"

    Tip 2: you can also add an image using data-image tag
-->
        <div class="sidebar-wrapper">
            <div class="logo">
                <a href="http://www.creative-tim.com" class="simple-text">
                    Shield Certificate
                </a>
            </div>
            <ul class="nav">
                <li class="nav-item active">
                    <a class="nav-link" href="./table.html">
                        <i class="nc-icon nc-notes"></i>
                        <p>Vos Certificats</p>
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <div class="main-panel">
        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg " color-on-scroll="500">
            <div class=" container-fluid  "><div class="collapse navbar-collapse justify-content-end" id="navigation">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="#pablo">
                                <span class="no-icon">Se deconnecter</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- End Navbar -->
        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card card-plain table-plain-bg">
                            <div class="card-header ">
                                <c:if test="${pageContext.request.userPrincipal.name != null}">
                                    <h2 class="card-title">Bienvenue,<b> ${pageContext.request.userPrincipal.name} </b></h2>
                                </c:if>
                                <br>

                                <h4 class="card-title">Liste des Certificats</h4>
                            </div>
                            <div class="card-body table-full-width table-responsive">
                                <table class="table table-hover">
                                    <thead>
                                    <th>ID</th>
                                    <th>Nom du Certificat</th>
                                    <th>Date de creation</th>
                                    <th>Date limite de validiter</th>
                                    <th>Operations</th>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td>1</td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>2</td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>3</td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>4</td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>5</td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>6</td>
                                        <td> </td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <footer class="footer">
            <div class="container">
                <nav>
                    <p class="copyright text-center">
                        &copy;
                        <script>
                            document.write(new Date().getFullYear())
                        </script>
                        <a href="http://www.creative-tim.com">Shield Certificate</a>
                    </p>
                </nav>
            </div>
        </footer>
    </div>
</div>
</body>
<!--   Core JS Files   -->
<script src="${contextPath}/resources/js/dashboard/core/jquery.3.2.1.min.js" type="text/javascript"></script>
<script src="${contextPath}/resources/js/dashboard/core/popper.min.js" type="text/javascript"></script>
<script src="${contextPath}/resources/js/dashboard/core/bootstrap.min.js" type="text/javascript"></script>
<!--  Plugin for Switches, full documentation here: http://www.jque.re/plugins/version3/bootstrap.switch/ -->
<script src="${contextPath}/resources/js/dashboard/plugins/bootstrap-switch.js"></script>
<!--  Google Maps Plugin    -->
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>
<!--  Chartist Plugin  -->
<script src="${contextPath}/resources/js/dashboard/plugins/chartist.min.js"></script>
<!--  Notifications Plugin    -->
<script src="${contextPath}/resources/js/dashboard/plugins/bootstrap-notify.js"></script>
<!-- Control Center for Light Bootstrap Dashboard: scripts for the example pages etc -->
<script src="${contextPath}/resources/js/dashboard/light-bootstrap-dashboard.js?v=2.0.1" type="text/javascript"></script>
<!-- Light Bootstrap Dashboard DEMO methods, don't include it in your project! -->
<script src="${contextPath}/resources/js/dashboard/demo.js"></script>

</html>
