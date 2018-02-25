<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Shield Certificate</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="${contextPath}/resources/images/logo.png"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="${contextPath}/resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="${contextPath}/resources/fonts/iconic/css/material-design-iconic-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/vendor/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/vendor/animsition/css/animsition.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/vendor/daterangepicker/daterangepicker.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/util.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/main.css">
    <!--===============================================================================================-->
</head>
<body>

<div class="limiter">
    <div class="container-login100" style="background-image: url('${contextPath}/resources/images/bg-01.jpg');">
        <div class="wrap-login100">
            <form method="POST" action="${contextPath}/login" class="login100-form validate-form">
                <div class="${error != null ? 'has-error' : ''}">
					<span class="login100-form-logo">
						<img src="${contextPath}/resources/images/logo.png" alt="" width="90">
					</span>
                    <br>
                    <span><b><p style="text-align: center;">${message}</p></b></span>
                    <span class="login100-form-title p-b-34 p-t-27">
						Connexion
					</span>

                    <div class="wrap-input100 validate-input" data-validate="Enter username">

                        <input class="input100" type="text" name="username" placeholder="Username">
                        <span class="focus-input100" data-placeholder="&#xf207;"></span>
                    </div>

                    <div class="wrap-input100 validate-input" data-validate="Enter password">
                        <span>${error}</span>
                        <input class="input100" type="password" name="password" placeholder="Password">
                        <span class="focus-input100" data-placeholder="&#xf191;"></span>
                    </div>

                    <div class="contact100-form-checkbox">
                        <input class="input-checkbox100" id="ckb1" type="checkbox" name="remember-me">
                        <label class="label-checkbox100" for="ckb1">
                            Se souvenir de moi
                        </label>
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <div class="container-login100-form-btn">
                        <button class="login100-form-btn" type="submit">
                            Se connecter
                        </button>
                    </div>

                    <div class="text-center p-t-90">
                        <a class="txt1" href="${contextPath}/registration">
                            Creer un compte.
                        </a>
                    </div>
                </div>
            </form>
    </div>
</div>
</div>


<div id="dropDownSelect1"></div>

<!--===============================================================================================-->
<script src="${contextPath}/resources/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="${contextPath}/resources/vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
<script src="${contextPath}/resources/vendor/bootstrap/js/popper.js"></script>
<script src="${contextPath}/resources/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="${contextPath}/resources/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
<script src="${contextPath}/resources/vendor/daterangepicker/moment.min.js"></script>
<script src="${contextPath}/resources/vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
<script src="${contextPath}/resources/vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
<script src="${contextPath}/resources/js/main.js"></script>

</body>
</html>
