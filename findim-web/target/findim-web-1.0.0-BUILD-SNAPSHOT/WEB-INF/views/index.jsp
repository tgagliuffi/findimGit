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
    <title>BBVA Continental</title>
    <meta name="description" content="BBVA Continental">
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/bootstrap.min.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/main.css'/>">

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
    <nav>
      <header>
        <nav class="navbar navbar-default-one" style="margin-bottom: 1px;" >
        	<div type="button" data-toggle="collapse" data-target="#bbvaCF" aria-expanded="false" class="button navbar-toggle collapsed"><span class="sr-only">Cambiar navegación</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></div>
              <a id="btn_home" name="btn_home" href="<c:url value='${gPath}/'/>" class="navbar-brand"></a>
        </nav>
        <nav class="navbar navbar-default">
          <div class="container-fluid">
            <div id="bbvaCF" class="collapse navbar-collapse">
              <ul id="main-nav" class="nav navbar-nav navbar-right">               
                <li><a id="btn_consulta1_a" name="btn_consulta1_a" href="<c:url value='${gPath}/consultaCliente/filtroCliente'/>">Consultar Financiamiento</a></li>
                <li> <a id="btn_simulacion1_a" name="btn_simulacion1_a" href="<c:url value='${gPath}/consultaCliente/simulacion'/>">Simular Financiamiento</a></li>
                <li><a id="btn_busqueda1_a" name="btn_busqueda1_a" href="<c:url value='${gPath}/consultaCliente/busquedaContrato'/>">Búsqueda de Contratos</a></li>
              </ul>
            </div>
          </div>
        </nav>
      </header>
    </nav>
    <main class="container">
      <div id="inicio">
        <div class="row">
          <div class="col-md-10 col-md-offset-1 area-contenidos">
            <h1 class="mt">Bienvenido(a)!</h1>
            <p>Aquí­ podrás realizar todas tus consultas sobre el financiamiento de equipos, ver si tus clientes califican para este financiamiento, realizar simulaciones para ver cuanto        pagarian mensualmente y realizar una búsqueda de los contratos de tu cliente.</p><br>
            <div class="row mt">
              <div class="col-md-4 txt-area-centrado menu-inicio"><a id="btn-filtro-imagen" href="<c:url value='${gPath}/consultaCliente/filtroCliente'/>" class="big-icon-circle"><img src="<c:url value='/resources/img/filtro_circulo.png '/>" alt="BBVA" class="img-responsive img-center"></a>
                <h4 class="mt">Consultar Financiamiento</h4>
                <p>Verifica si tu cliente califica para un financiamiento</p><a id="btn_consulta1b" href="<c:url value='${gPath}/consultaCliente/filtroCliente'/>" class="mt btn btn-primary">Consultar Financiamiento</a></a>
              </div>
              <div class="col-md-4 txt-area-centrado menu-inicio"><a id="btn-simulacion-imagen" href="<c:url value='${gPath}/consultaCliente/simulacion'/>" class="big-icon-circle"><img src="<c:url value='/resources/img/simulacion_circulo.png'/>" alt="BBVA" class="img-responsive img-center"></a>
                <h4 class="mt">Simular Financiamiento</h4>
                <p>Realiza una simulación del financiamiento</p><br><a id="btn_simulacion1b" href="<c:url value='${gPath}/consultaCliente/simulacion'/>" class="btn btn-primary mt">Simular Financiamiento</a>
              </div>
              <div class="col-md-4 txt-area-center menu-inicio"><a id="btn-busqueda-imagen" href="<c:url value='${gPath}/consultaCliente/busquedaContrato'/>" class="big-icon-circle"> <img src="<c:url value='/resources/img/busqueda_circulo.png'/>" alt="BBVA" class="img-responsive img-center"></a>
                <h4 class="mt">Búsqueda de contratos</h4>
                <p>Busca contratos de tu cliente</p><br><a id="btn_busqueda1b" href="<c:url value='${gPath}/consultaCliente/busquedaContrato'/>" class="btn btn-primary mt">Búsqueda de contratos</a>
              </div>
            </div>
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
      <script src="<c:url value='/resources/js/frontend/globalVars.js' />"></script>
      <script src="<c:url value='/resources/js/frontend/jquery.validate.min.js' />"></script>
      <script src="<c:url value='/resources/js/frontend/additionalMethods.min.js' />"></script>
      <script src="<c:url value='/resources/js/frontend/validacionesGlobal.js' />"></script>
      <script src="<c:url value='/resources/js/inicio/inicio.js' />"></script>

  </body>
   <script>
   $(function() {
// 	  inicioControles();
	  gPath = '${gPath}<c:if test="${empty gPath}">${pageContext.servletContext.contextPath}</c:if>';
   });
  </script>
</html>