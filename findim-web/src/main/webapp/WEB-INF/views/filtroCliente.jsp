<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
  <head>       
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BBVA Consumer Continental</title>
    <meta name="description" content="BBVA Continental">
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/bootstrap.min.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/main.css?v=${timestamp}'/>">

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

    <script type="text/javascript">
    	var constants = {};
    	constants.BIOMATCH_ACTIVO = ${bioMatchActivo};
        constants.BIOMATCH_URL_BASE = '${bioMatchUrlBase}';
        if (Object.freeze) {
            Object.freeze(constants);
        }
	</script>
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
      <div class="area-contenidos">
        <div id="cabecera" class="row">
          <div class="col-md-12">
            <div id="navControlador">
              <p id="cabeceraTitulo"></p>
              <h1>Filtro BBVA</h1>
              <hr>
            </div>
          </div>
        </div>
        <div id="formulario" class="row heightMin">
          <div class="col-md-6 col-md-offset-3">
            <form id="formularioDocumento" name="formularioDocumento" role="form" method="GET">
              <div class="form-group"> 
                <div class="error"></div>
              </div>
              <div class="form-group">
                <label for="tipoDocumento">Seleccione el tipo de documento</label>
                <select id="tipoDocumento" name="tipoDocumento" class="form-control">
                  <option value="DNI">DNI</option>
                </select>
              </div>
              <div class="form-group">
                <label for="numDocumento">Ingrese el número de documento</label>
                <input id="numDocumento" type="number" placeholder="Numero Documento" name="numDocumento" data-minlength="8" data-maxlength="8" required class="form-control form-warning">
                <div class="help-block with-errors"></div>
              </div>
              <div class="row">
                <div class="col-md-6"><a id="volverForm" name="volver" href="index.html" class="btn btn-default">Volver</a></div>
                <div class="col-md-6">
                  <button id="BtnForm" name="BtnForm" type="submit" class="btn btn-primary btn-block">Buscar</button>
                </div>
              </div>
            </form>
          </div>
        </div>
        <div id="preloader" class="row ">
          <div class="col-md-6 col-md-offset-3">
            <div class="preload"><img src="<c:url value='/resources/img/preload.gif'/>" alt="bbva" class="img-center mt">
              <p>Obteniendo resultados</p>
            </div>
          </div>
        </div>
        <div id="alerta" class="row">
          <div class="col-md-8 col-md-offset-2">
            <div id="alertaStatus" role="alert" class="alert alert-warning">           
              <h1 id="alertaTitulo" class="alertTitulo"> </h1>
              <p id="alertaTexto" class="alertTexto"> </p>
            </div>
            <hr><a id="volverAlerta" class="btn btn-default btn-control" >Volver        </a>
          </div>
        </div>
        <div id="contenido" class="row heightMin">
          <div class="col-md-6 col-md-offset-3">
            <div id="alertaFiltro" role="alert" class="alert">           
              <h1 id="alertaFiltroTitulo" class="alertaTitulo"></h1>
              <p id="alertaFiltroTexto" class="alertaTexto"></p>
            </div>
            <hr><a id="volverContenido" class="btn btn-default btn-control">Volver</a>
          </div>
        </div>
        <!--Contenido -- en caso de alerta no se muestra-->
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
      <!-- scripts aqui-->

      <script src="<c:url value='/resources/js/frontend/jquery1.12.3.min.js' />"></script>
      <!-- **** Revisar si es necesario **** -->
	  <script src="<c:url value='/resources/js/vendor/jquery.transport.xdr.min.js' />"></script>
	  <!-- **** -->
      <script src="<c:url value='/resources/js/frontend/bootstrap.min.js' />"></script>
      <script src="<c:url value='/resources/js/frontend/globalVars.js?v=${timestamp}' />"></script>
      <script src="<c:url value='/resources/js/frontend/jquery.validate.min.js' />"></script>
      <script src="<c:url value='/resources/js/frontend/additionalMethods.min.js' />"></script>
      <script src="<c:url value='/resources/js/frontend/validacionesGlobal.js?v=${timestamp}' />"></script>
      <script src="<c:url value='/resources/js/frontend/validacionBarraSuperior.js?v=${timestamp}' />"></script>
      <script src="<c:url value='/resources/js/frontend/controladorNavegacion.js?v=${timestamp}'/>"></script>
      <script src="<c:url value='/resources/js/frontend/filtro.js?v=${timestamp}'/>"></script>
      <script src="<c:url value='/resources/js/inicio/inicio.js?v=${timestamp}'/>"></script>
	   <script>
	   $(function()  {
		  gPath = '${gPath}<c:if test="${empty gPath}">${pageContext.servletContext.contextPath}</c:if>';
	   });
	  </script>    
  </body>
</html>