<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html lang="es">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>BBVA Continental</title>
<meta name="description" content="BBVA Continental">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/bootstrap.min.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/main.css?v=${timestamp}'/>">

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

	<div class="no-show-on-print">
		<nav class="navbar navbar-default-one" style="margin-bottom: 1px;">
			<div type="button" data-toggle="collapse" data-target="#bbvaCF"
				aria-expanded="false" class="button navbar-toggle collapsed">
				<span class="sr-only">Cambiar navegación</span><span
					class="icon-bar"></span><span class="icon-bar"></span><span
					class="icon-bar"></span>
			</div>
			<a id="btn_home" name="btn_home" href="<c:url value='${gPath}/'/>"
				class="navbar-brand"></a>
		</nav>
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div id="bbvaCF" class="collapse navbar-collapse">
					<ul id="main-nav" class="nav navbar-nav navbar-right">
						<li><a id="btn_consulta1_a" name="btn_consulta1_a"
							href="<c:url value='${gPath}/consultaCliente/filtroCliente'/>">Consultar
								Financiamiento</a></li>
						<li><a id="btn_simulacion1_a" name="btn_simulacion1_a"
							href="<c:url value='${gPath}/consultaCliente/simulacion'/>">Simular
								Financiamiento</a></li>
						<li><a id="btn_busqueda1_a" name="btn_busqueda1_a"
							href="<c:url value='${gPath}/consultaCliente/busquedaContrato'/>">Búsqueda
								de Contratos</a></li>
					</ul>
				</div>
			</div>
		</nav>
	</div>

	<main class="container">
	<div class="area-contenidos" id="mydiv">
		<div id="cabecera" class="row">
			<div class="col-md-12">
				<div id="navControlador">
					<p id="cabeceraTitulo"></p>
					<h1 id="rojoPrueba">Simulación de Financiamiento</h1>
					<hr>
				</div>
			</div>
		</div>
		<div id="formulario" class="row heightMin">
			<div class="col-md-12 ">
				<form id="form-simulacion" role="form" method="get">
					<div class="row">
						<div class="col-sm-4">
							<div class="form-group">
								<h6>Moneda</h6>
								<select id="simulacion_moneda" name="simulacion_moneda"
									class="form-control">
									<option value="Soles">Soles</option>
								</select>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<h6>Valor del equipo</h6>
								<label id="simulacion_valor_equipo-error" class="error"
									for="simulacion_valor_equipo"></label>
								<div class="input-group">
									<span class="input-group-addon form-warning">S/.</span> <input
										id="simulacion_valor_equipo" name="simulacion_valor_equipo"
										min="1.00" max="999999.99" class="form-control form-warning">
								</div>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<h6>Tarifa de Financiamiento</h6>
								<select id="simulacion_codigo_tarifa"
									name="simulacion_codigo_tarifa" class="form-control">
									<!-- AQUI HAY LOGICA-->
								</select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-4">
							<div class="form-group">
								<h6>Porcentaje Cuota Inicial (Máximo 99.99%)</h6>
								<label id="simulacion_cuota_inicial_porcentaje-error"
									class="error" for="simulacion_cuota_inicial_porcentaje"></label>
								<div class="input-group">
									<span class="input-group-addon form-warning">%</span> <input
										id="simulacion_cuota_inicial_porcentaje"
										name="simulacion_cuota_inicial_porcentaje"
										aria-describedby="cuota_inicial_porcentaje" min="0.00"
										max="99.99" class="form-control form-warning">
								</div>
								<label id="simulacion_cuota_inicial_porcentaje-error"
									class="error"></label>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<h6>Cuota Inicial</h6>
								<div class="input-group">
									<span class="input-group-addon">S/.</span> <input
										id="simulacion_cuota_inicial" type="text"
										name="simulacion_cuota_inicial"
										aria-describedby="cuota_inicial" readonly class="form-control">
								</div>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<h6>Saldo a Financiar</h6>
								<input id="simulacion_saldo_a_financiar" type="text"
									name="simulacion_saldo_a_financiar"
									aria-describedby="saldo_a_financiar" tab-index="0" readonly
									class="form-control">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-4">
							<div class="form-group">
								<h6>Fecha de préstamo</h6>
								<!-- Logica fecha-->
								<input id="simulacion_fecha_inicio" value=""
									name="simulacion_fecha_inicio" type="text" placeholder=""
									readonly class="form-control">
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<h6>Fecha vencimiento</h6>
								<input id="simulacion_fecha_vencimiento" value=""
									name="simulacion_fecha_vencimiento" type="text" placeholder=""
									readonly class="form-control">
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<h6>Envío de comprobante de pago</h6>
								<select id="simulacion_metodo_envio"
									name="simulacion_metodo_envio" class="select form-control">
								</select>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-sm-4">
							<div class="form-group">
								<h6>Cargo Fijo Máximo</h6>
								<input id="cargoFijoMaximo" type="number" name="cargoFijoMaximo"
									min="0.00" max="99999.99" aria-describedby="cargoFijoMaximo"
									class="form-control">

							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-sm-4">
							<a id="btn_simulacion_inicio" name="btn_simulacion_inicio"
								href="index.html" class="btn btn-default">Volver</a>
						</div>
						<div class="col-sm-4 col-sm-offset-4">
							<button id="btn_simulacion_form" name="btn_simulacion_form"
								type="submit" class="btn btn-primary btn-block full-width m-b">Simular</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div id="preloader" class="row ">
			<div class="col-md-6 col-md-offset-3">
				<div class="preload">
					<img src="<c:url value='/resources/img/preload.gif'/>" alt="bbva"
						class="img-center mt">
					<p>Obteniendo resultados</p>
				</div>
			</div>
		</div>
		<div id="alerta" class="row">
			<div class="col-md-8 col-md-offset-2">
				<div id="alertaStatus" role="alert" class="alert alert-warning">
					<h1 id="alertaTitulo" class="alertTitulo"></h1>
					<p id="alertaTexto" class="alertTexto"></p>
				</div>
				<hr>
				<a id="volverAlerta" class="btn btn-default btn-control">Volver
				</a>
			</div>
		</div>


		<div id="contenido" class="row heightMin">
			<div class="row">
				<div class="col-md-3 col-md-offset-9">
					<small class="txt-color">Cargo Fijo Máximo Disponible</small>
					<p id="cargoFijoMaximoResultado"></p>
				</div>
			</div>
			<div class="row">
				<div class="col-md-3">
					<small class="txt-color">Moneda</small>
					<p id="simulacion-resultado-moneda"></p>
				</div>
				<div class="col-md-3">
					<small class="txt-color">C&#243;digo de tarifa</small>
					<p id="simulacion-codigo-tarifa"></p>
				</div>
				<div class="col-md-3">
					<small class="txt-color">Valor del equipo</small>
					<p id="simulacion-resultado-equipo"></p>
				</div>
				<div class="col-md-3">
					<small class="txt-color">Saldo a Financiar</small>
					<p id="simulacion-resultado-Saldo"></p>
				</div>
			</div>

			<div class="row">
				<div class="col-md-3">
					<small class="txt-color">N&#250;mero de cuotas</small>
					<p id="simulacion-resultado-num-cuotas"></p>
				</div>
				<div class="col-md-3">
					<small class="txt-color">Tasa Efectiva Anual (TEA)</small> %
					<p id="simulacion-resultado-TEA"></p>
				</div>
				<div class="col-md-3">
					<small class="txt-color">Tasa de Costo Efectiva Anual
						(TCEA)</small> %
					<p id="simulacion-resultado-TCEA"></p>
				</div>
				<div class="col-md-3">
					<small class="txt-color">Seguro Desgravamen</small> %
					<p id="simulacion-resultado-seguro"></p>
				</div>
			</div>

			<div class="row">
				<div class="col-md-12">
					<div class="table-responsive">
						<table id="tablaContenido" class="table">
							<thead>
								<tr>
									<th>Num Cuotas</th>
									<th>Fecha de Pago</th>
									<th>Saldo Capital</th>
									<th>Amortizaci&#243;n</th>
									<th>Intereses</th>
									<th>Seguro desgravamen</th>
									<th>Com. env&#237;o Comp. pago</th>
									<th>Cuota total</th>
								</tr>
							</thead>

							<tbody id="tablaContenidoBody">

							</tbody>
							<tfoot>
								<tr>
									<td></td>
									<td id="totalSaldoCapital"></td>
									<td><h5>Totales</h5></td>
									<td id="totalAmortizacion"></td>
									<td id="totalIntereses"></td>
									<td id="totalSeguroDesgravamen"></td>
									<td id="totalComisionEnvio"></td>
									<td id="totalCuotaTotal"></td>
								</tr>
							</tfoot>
						</table>
					</div>

				</div>

				<div class="row no-show-on-print">
					<div class="col-md-4">
						<a id="volverContenido" class="btn btn-default btn-control">Volver</a>
					</div>
					<div class="col-md-4">
						<a id="imprimirSimulacion"
							class="btn btn-info btn-control btn-center "
							onClick="printdiv('mydiv')">Imprimir Simulación</a>
					</div>

					<!-- <div class="col-md-4"><a id="imprimirSimulacion" class="btn btn-info btn-control btn-center "  value="Print" onclick="PrintElem('#mydiv')">Imprimir Simulación</a></div> -->
				</div>
			</div>
		</div>

	</div>

	</main>

	<div class="container-fluid footer no-show-on-print">
		<div class="row">
			<div class="col-sm-8 col-sm-offset-4">
				<div class="footer-container">
					<ul class="inline-list">
						<li><img
							src="<c:url value='/resources/img/logo-bbva-pie.png'/>"
							alt="Huascaran"></li>
						<li>
							<div class="footer-divisor">.</div>
						</li>
						<li><img
							src="<c:url value='/resources/img/logo-movistar.png'/>"
							alt="Huascaran"></li>
					</ul>
				</div>
			</div>
		</div>

	</div>
	<!-- scripts aqui-->

	<script
		src="<c:url value='/resources/js/frontend/jquery1.12.3.min.js' />"></script>
	<script src="<c:url value='/resources/js/frontend/bootstrap.min.js' />"></script>
	<script
		src="<c:url value='/resources/js/frontend/globalVars.js?v=${timestamp}' />"></script>
	<script
		src="<c:url value='/resources/js/frontend/jquery.validate.min.js' />"></script>
	<script
		src="<c:url value='/resources/js/frontend/additionalMethods.min.js' />"></script>
	<script
		src="<c:url value='/resources/js/frontend/validacionesGlobal.js?v=${timestamp}' />">   </script>
	<script
		src="<c:url value='/resources/js/frontend/controladorNavegacion.js?v=${timestamp}'/>"></script>
	<script
		src="<c:url value='/resources/js/frontend/simulacion.js?v=${timestamp}'/>"></script>
	<script
		src="<c:url value='/resources/js/inicio/inicio.js?v=${timestamp}'/>"></script>
	<script>
		  gPath = '${gPath}<c:if test="${empty gPath}">${pageContext.servletContext.contextPath}</c:if>';   
		  $(function() {
	 	  	  inicioControles();
		  })
	  </script>


</body>
</html>