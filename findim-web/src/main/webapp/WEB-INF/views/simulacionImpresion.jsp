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
<body>
  
	<img src="<c:url value='/resources/img/logo-bbva.png' />" alt="bbva" />
  
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

		 <div id="contenido" class="row heightMin">	 
		 
			<div class="table-responsive">
			    <table class="table">
			        <tr>
			            
			            <td>Moneda</td>
			            <td>Código de Tarifa</td>
			            <td>Valor del equipo</td>
			            <td>Saldo a Financiar</td>
			            <td>Cargo Fijo Máximo Disponible</td>
			        </tr>       
			        <tr>
			            <td><p id="simulacion-resultado-moneda"></p></td>
			            <td><p id="simulacion-codigo-tarifa"></p></td>
			            <td><p id="simulacion-resultado-equipo"></p></td>
			            <td><p id="simulacion-resultado-Saldo"></p></td>
			            <td><p id="cargoFijoMaximoResultado"></p></td>
			        </tr>
			        <tr>
			            <td>Número de cuotas</td>
			            <td>Tasa Efectiva Anual (TEA) %</td>
			            <td>Tasa de Costo Efectiva Anual (TCEA) %</td>
			            <td>Seguro Desgravamen</td>
			        </tr>
			        <tr>
			            <td><p id="simulacion-resultado-num-cuotas"></p></td>
			            <td><p id="simulacion-resultado-TEA"></p></td>
			            <td><p id="simulacion-resultado-TCEA"></p></td>
			            <td><p id="simulacion-resultado-seguro"></p></td>
			        </tr>        
			    </table>
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
		        
				<div class="table-responsive">
				    <table id="textoLegal" class="table">
				    </table>
				</div>		        
		    </div>

		    <div class="row no-show-on-print">
		        <div class="col-md-4"><a id="volverContenido" class="btn btn-default btn-control" onClick="closediv()">Cerrar</a></div>
		        <div class="col-md-4"><a id="imprimirSimulacion" class="btn btn-info btn-control btn-center" onClick="printdiv('mydiv');">Imprimir Simulación</a></div> 		        
		    </div>
		    </div>
		</div>

		</div>
    </main>
    <div class="container-fluid footer"></div>
	<script src="<c:url value='/resources/js/frontend/jquery1.12.3.min.js' />" ></script>
	<script src="<c:url value='/resources/js/frontend/bootstrap.min.js' />" ></script>
	<script src="<c:url value='/resources/js/frontend/impresionSimulacion.js?v=${timestamp}' />" ></script>
	<script type="text/javascript">
	 	var objetoContenedor = ${objetoContenedor};
	 	console.log(objetoContenedor);
	</script>
</body>
</html>