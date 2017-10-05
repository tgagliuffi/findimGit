<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
  <head>       
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BBVA Continental</title>
    <meta name="description" content="BBVA Continental">
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/bootstrap.min.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/main.css?v=${timestamp}'/>">
    
    <script type="text/javascript">
        var constants = {};
	    constants.BIOMATCH_ACTIVO = ${bioMatchActivo};
        constants.BIOMATCH_URL_BASE = '${bioMatchUrlBase}';
        if (Object.freeze) {
            Object.freeze(constants);
        }
		var isIE8 = false;
	</script>

	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta http-equiv="X-UA-Compatible" content="IE=7" />
	<meta http-equiv="X-UA-Compatible" content="IE=8" />
	<meta http-equiv="X-UA-Compatible" content="IE=9" />
	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	<script src="<c:url value='/resources/js/vendor/selectivizr-min.js'/>"></script>
	<script src="<c:url value='/resources/js/vendor/html5shiv.min.js'/>"></script>
	<script src="<c:url value='/resources/js/vendor/respond.min.js'/>"></script>
	<script type="text/javascript">
	    var isIE8 = true;
	</script>
	<!--<![endif]-->
</head>
<body>
	<div id="modalInformacion" tabindex="-1" role="dialog" class="modal fade" data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" data-dismiss="modal" aria-label="Close" class="close" id="btnCerrarModalInformacion">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4>Completar Información</h4>
				</div>
				<div class="modal-body">
					<form id="form-completar-informacion" role="form" method="get">
					
					<div class="row form-inline">
							<div class="col-md-12">
								<h6>Nombre de Cliente</h6>
								<p id="CompletarNombreCliente"></p>
							</div>
							<div class="col-md-6">
<!-- 									<h6>Nombre de Cliente</h6> -->
<!-- 									<p id="CompletarNombreCliente"></p> -->
									<h6>Correo Electrónico</h6>
									<input type="text" id="completar_info_email"
										name="completar_info_email" placeholder=""
										value="" class="form-control form-warning" style="width: 100%">
									
							</div>		
									<div class="col-md-6">
									<h6>Profesión/Ocupación</h6>
									<select id="completar_info_profesion"
										name="completar_info_profesion" required
										class="form-control form-warning" style="width: 100%">
										 <option disabled selected value>-- Elegir una opción --</option>
										<!--<option  selected value="2">adsasd</option> -->
									</select>
									<input type="text" id="completar_info_cod_cliente"
										name="completar_info_cod_cliente" placeholder=""
										value="" class="hide">
									<input type="text" id="completar_info_cod_contrato"
										name="completar_info_cod_contrato" placeholder=""
										value="" class="hide">									
							</div>
						</div>
						<div class="row form-inline">
							<div class="col-md-6">
							<h6>Envío de comprobante de pago</h6>
									<select id="completar_info_envio" name="completar_info_envio" required class="form-control">

											<!--<option  selected value="2">adsasd</option>	-->						

										<option disabled selected value>-- Elegir una opción --</option>								

									</select>
<!-- 									<h6>Correo Electrónico</h6> -->
<!-- 									<input type="text" id="completar_info_email" -->
<!-- 										name="completar_info_email" placeholder="" -->
<!-- 										value="" class="form-control form-warning" style="width: 100%"> -->
							</div>
							<div class="col-md-6">
									<h6>Centro de trabajo</h6>
									<input type="text" id="completar_info_empresa"
										name="completar_info_empresa"
										class="form-control form-warning"
										maxlength="50" style="width: 100%">
							</div>
						</div>
						<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<h6>Dirección</h6>
		        						<input id="completar_info_direccion" type="text" 
		        						name="completar_info_direccion" aria-describedby="completar_info_direccion" 
		        						readonly="" class="form-control" >
									</div>
								</div>
							</div>
							<div class="row form-inline" id="div_direccion_ref">
							<div class="col-md-12" >
									<h6>Referencia</h6>
										<input type="text" id="completar_info_dir_ref"
										name="completar_info_dir_ref" required
										class="form-control form-warning" style="width: 100%"
										maxlength="120" >
							</div>	
						</div>
						<div class="row" >
							<div class="col-md-4">
									<h6></h6>
									<button id="btn_completar_info_form"
										name="btn_completar_info_form"
										type="submit"
										data-loading-text="Procesando..."
										class="btn btn-primary btn-block" >Guardar</button>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<p id="modalInformacionError" class=""></p>
					<button id="modalInformacionCerrar" type="button" data-dismiss="modal" class="btn btn-default">Cerrar</button>
				</div>
			</div>
		</div>
	</div>
	<div id="modalContratosFirma" tabindex="-1" role="dialog" class="modal fade bs-example-modal-lg" data-backdrop="static">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" data-dismiss="modal" aria-label="Close" class="close" id="btnCerrarModalFirma">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4>Firma de contrato</h4>
				</div>
				<div class="modal-body">
					<!-- 
					<div id="contratos-preload">
						<p> Cargando....</p>
					</div>
 					-->
					<div id="contratos-alerta">
						<div class="row">
							<div role="alert" id="alert_contratos_resultado" class="alert">								
								<h2 class="contratos_titulo_editable">Error al cargar PDF</h2>
								<p class="contratos-error-code"></p>
								<hr>
							</div>
						</div>
					</div>

					<div id="contratos_procede">
						<iframe id="pdfRoute" src="" width="100%" height="350" style="z-index: 10"></iframe>
						<p id="errorMessageTrick"></p>
					</div>
				</div>
				<div class="modal-footer">
					<button id="btnFirmarDocumento" type="button" class="btn btn-info" data-loading-text="Procesando...">Firmar Documento</button>					
					<button id="modalContratoCerrar" type="button" data-dismiss="modal" class="btn btn-default">Cerrar</button>
				</div>
			</div>
		</div>
	</div>
	
	<div id="modalContratosImprimir" tabindex="-1" role="dialog" class="modal fade bs-example-modal-lg" data-backdrop="static">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" data-dismiss="modal" aria-label="Close" class="close" id="btnCerrarModalFirma">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4>Constancia de Financiamiento</h4>
				</div>
				<div class="modal-body">
					<!-- 
					<div id="contratos-preload">
						<p> Cargando....</p>
					</div>
 					-->
					<div id="contratos_imprimir">
						<iframe id="pdfImprimirRoute" src="" width="100%" height="350" style="z-index: 10"></iframe>
						<p id="errorMessageTrick"></p>
					</div>
				</div>
				<div class="modal-footer">
					<button id="btnImprimirDocumento" type="button" class="btn btn-info" data-loading-text="Procesando...">Imprimir Documento</button>					
					<button id="modalImpresionCerrar" type="button" data-dismiss="modal" class="btn btn-default">Cerrar</button>
				</div>
			</div>
		</div>
	</div>

	<!-- modal alerta firma -->
	<div id="modalAlertaFirma" tabindex="-1" role="dialog" class="modal fade" data-backdrop="false" data-keyboard="false">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h4>Firma de contrato</h4>
				</div>
				<div class="modal-body">
					<h1 id="mensaje"></h1>
					<input type="hidden" id="refreshOnClose" />
				</div>
				<div class="modal-footer">
					<button id="modalAlertaFirmaCerrar" type="button" data-dismiss="modal" class="btn btn-primary">Aceptar</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- modal alerta informacion 
	<div id="modalAlertaFirma" tabindex="-1" role="dialog" class="modal fade" data-backdrop="false" data-keyboard="false">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h4>Firma de contrato</h4>
				</div>
				<div class="modal-body">
					<h1 id="mensaje"></h1>
					<input type="hidden" id="refreshOnClose" />
				</div>
				<div class="modal-footer">
					<button id="modalAlertaFirmaCerrar" type="button" data-dismiss="modal" class="btn btn-primary">Aceptar</button>
				</div>
			</div>
		</div>
	</div>-->

	<div id="anulacionContrato" tabindex="-1" role="dialog" class="modal fade" data-backdrop="static">
		<div class="modal-dialog modal-lg">
			<div class="modal-content print-no-shadow print-no-border">
				<div class="modal-header print-no-border">
					<button type="button" data-dismiss="modal" aria-label="Close" class="close hidden-print" id="btnCerrarModalFirma">
						<span aria-hidden="true">&times;</span>
					</button>
					<div class="anulacionLogo"><img src="<c:url value='${gPath}/resources/img/logo-bbva.png'/>" alt="BBVA"></div>
					<h4>Solicitud de Anulación de Préstamo OCB TELEFONÍA</h4>
				</div>
				<div class="modal-body">
					<div>
						<p>Por medio de la presente comunicación, se deja constancia que el cliente <b id="nombreCliente"></b> identificado con DNI N° <b id="dniCliente"></b> solicitó la anulación del préstamo OCB TELEFONÍA N° <b id="contratoCliente"></b>.</p>
						<p>Sobre el particular, el cliente declara conocer que la presente solicitud se procesará una vez que Telefónica del Perú haya hecho llegar a BBVA Consumer Finance Edpyme el presente documento.</p>
						<p>Atentamente</p>
						<br>
						<p>BBVA Consumer Finance Edpyme</p>
						<br>
						<p>En señal de conformidad:</p><br>
						<div class="firma"></div>
						<p>Cliente: <b id="nombreCliente2"></b></p>
						<p>DNI: <b id="dniCliente2"></b></p>
					</div>
					<input type="hidden" id="cdContrato" />
				</div>
				<div class="modal-footer print-no-border">
					<button id="btnPrintDocumento" type="button" class="btn btn-info hidden-print" data-loading-text="Procesando...">Imprimir documento</button>					
					<button type="button" data-dismiss="modal" class="btn btn-default hidden-print">Cerrar</button>
				</div>
			</div>
		</div>
	</div>

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
              <h1>Búsqueda de Contratos</h1>
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
            <hr><a id="volverAlerta" class="btn btn-default btn-control">Volver        </a>
          </div>
        </div>
        <div id="contenido" class="row heightMin">
          <div class="col-md-12">
              <hr><small class="small-cliente">Cliente :</small>
              <!--Logica-->
              <h5 id="datos-cliente"></h5>
              <hr>
              <div class="table-responsive">
                <table id="tablaContenido" class="table">
                  <thead>
                    <tr>
                      <th>Fecha Creación</th>
                      <th>Moneda</th>
                      <th>Valor Equipo</th>
                      <th>Cuota Inicial</th>
                      <th>Saldo a Financiar</th>
                      <th>Código de tarifa</th>
                      <th>Num. Cuotas</th>                      
                      <th>Tasa</th>
                      <th>Seguro Desgravamen</th>
                      <th>Envío Est. Cta.</th>
                      <th>Fecha desembolso</th>
                      <th>Estado</th>
                      <!-- <th>Acciones</th>  -->
                     
                    </tr>
                  </thead>
                  <tbody id="tablaContenidoBody">
                  </tbody>
                </table>
              </div>
            <hr><a id="volverContenido" class="btn btn-default btn-control">Volver</a>
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
      <!-- scripts aqui-->

      <script src="<c:url value='/resources/js/frontend/jquery1.12.3.min.js' />"></script>     
      <script src="<c:url value='/resources/js/frontend/bootstrap.min.js' />"></script>
      <script src="<c:url value='/resources/js/frontend/globalVars.js?v=${timestamp}' />"></script>
      <script src="<c:url value='/resources/js/frontend/jquery.validate.min.js' />"></script>
      <script src="<c:url value='/resources/js/frontend/additionalMethods.min.js?v=${timestamp}' />"></script>
      <script src="<c:url value='/resources/js/frontend/validacionesGlobal.js?v=${timestamp}' />">   </script>
      <script src="<c:url value='/resources/js/frontend/controladorNavegacion.js?v=${timestamp}'/>"></script>      
      <script src="<c:url value='/resources/js/frontend/busquedaContrato.js?v=${timestamp}'/>"></script>
      <script src="<c:url value='/resources/js/inicio/inicio.js?v=${timestamp}'/>"></script>  

	   <script>
	   $(function()  {
		  gPath = '${gPath}<c:if test="${empty gPath}">${pageContext.servletContext.contextPath}</c:if>';
	   });
	  </script>    
  </body>
</html>