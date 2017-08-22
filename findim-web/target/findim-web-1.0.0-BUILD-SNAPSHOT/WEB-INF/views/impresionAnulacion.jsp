<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>BBVA Consumer Finance</title>
	<meta name="description" content="BBVA Consumer Finance">
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/bootstrap.min.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/main.css?v=${timestamp}'/>">
</head>
<body style="max-height: 800px;">
	<div id="divAnulacion">
		<div class="anulacionLogo"><img src="<c:url value='${gPath}/resources/img/logo-bbva.png'/>" alt="BBVA"></div>
	
		<h4>Solicitud de Anulación de Préstamo OCB TELEFONÍA</h4>

		<div>
			<p>Por medio de la presente comunicación, se deja constancia que el cliente <b>${nombreCliente}</b> identificado con DNI N° <b>${dniCliente}</b> solicitó la anulación del préstamo OCB TELEFONÍA N° <b>${numeroContrato}</b>.</p>
			<p>Sobre el particular, el cliente declara conocer que la presente solicitud se procesará una vez que Telefónica del Perú haya hecho llegar a BBVA Consumer Finance Edpyme el presente documento.</p>
			<p>Atentamente</p>
			<br>
			<p>BBVA Consumer Finance Edpyme</p>
			<br>
			<p>En señal de conformidad:</p><br>
			<div class="firma"></div>
			<p>Cliente: <b>${nombreCliente}</b></p>
			<p>DNI: <b>${dniCliente}</b></p>
		</div>
		<a id="imprimirAnulacion" class="hidden-print" style="display:none;" onclick="printDivById('divAnulacion');">Imprimir Simulación</a>
	</div>

	<script src="<c:url value='/resources/js/frontend/jquery1.12.3.min.js' />" ></script>
	<script src="<c:url value='/resources/js/frontend/bootstrap.min.js' />" ></script>
	<script src="<c:url value='/resources/js/frontend/impresionAnulacion.js?v=${timestamp}' />" ></script>
</body>
</html>