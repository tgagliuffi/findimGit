<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="es"> 
  <head>       
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BBVA Consumer Finance</title>
    <meta name="description" content="BBVA Consumer Finance">
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/bootstrap.min.css'/>">
	<!--<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/main.css'/>">-->
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/sesion.css?v=${timestamp}'/>">

	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta http-equiv="X-UA-Compatible" content="IE=7" />
	<meta http-equiv="X-UA-Compatible" content="IE=8" />
	<meta http-equiv="X-UA-Compatible" content="IE=9" />
	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	<script src="<c:url value='/resources/js/vendor/selectivizr-min.js'/>"></script>
	<script src="<c:url value='/resources/js/vendor/html5shiv.min.js'/>"></script>
	<script src="<c:url value='/resources/js/vendor/respond.min.js'/>"></script>
	<!--<![endif]-->

  </head>
  <body>
 
        <main class="container">
      <div id="inicio">
        <div class="row">
          <div class="col-md-10 col-md-offset-1 area-contenidos">
            <h1 class="mt tituloSesion">Acceso Denegado</h1>
            <p class="parrafoSesion">Lo sentimos esta página no está disponible</p>
            
          </div>
        </div>
      </div>
    </main>
      <footer class="container-fluid footer">         
        <div class="row">
          <div class="col-sm-8 col-sm-offset-4">
            <div class="footer-container">
              <ul class="inline-list">
                <li><img src="<c:url value='/resources/img/logo-bbva-pie.png'/>" alt="Huascaran"></li>
                <li>
                  <div class="footer-divisor">.</div>
                </li>
                <li><img src="<c:url value='/resources/img/logo-movistar.png'/>" alt="Huascaran"></li>
              </ul>
            </div>
          </div>
        </div>

      </footer>

      <script src="<c:url value='/resources/js/frontend/jquery1.12.3.min.js' />"></script>      
      <script src="<c:url value='/resources/js/frontend/bootstrap.min.js' />"></script>

  </body>
   
</html>