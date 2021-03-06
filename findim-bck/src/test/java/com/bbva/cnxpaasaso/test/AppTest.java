package com.bbva.cnxpaasaso.test;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bbva.findim.bck.service.ApprovalsService;
import com.bbva.findim.bck.service.CatalogService;
import com.bbva.findim.bck.service.CustomerService;
import com.bbva.findim.bck.service.LoanService;
import com.bbva.findim.bck.service.PersonService;
import com.bbva.findim.bck.service.ProposalService;
import com.bbva.findim.bck.service.SeguridadBbvaService;
import com.bbva.findim.bck.service.impl.TariffServiceImpl;
import com.bbva.findim.bck.util.UtilDate;
import com.bbva.findim.dom.CabeceraBean;
import com.bbva.findim.dom.ClienteBean;
import com.bbva.findim.dom.ContratoAltaBean;
import com.bbva.findim.dom.ContratoBean;
import com.bbva.findim.dom.SimulacionBean;
import com.bbva.findim.dom.TarifaBean;
import com.bbva.findim.dom.TotalesBean;
import com.bbva.findim.dom.CalificacionClienteBean;
import com.bbva.findim.dom.CatalogoBean;
import com.bbva.findim.dom.ContactoBean;
import com.bbva.findim.dom.DatosLaboralesBean;
import com.bbva.findim.dom.DireccionBean;
import com.bbva.findim.dom.DocumentoIdentidadBean;
import com.bbva.findim.dom.EmpresaBean;
import com.bbva.findim.dom.GrupoGeografico;
import com.bbva.findim.dom.IngresosBean;
import com.bbva.findim.dom.PersonaBean;
import com.bbva.findim.dom.PrestamoBean;
import com.bbva.findim.dom.ValorIngresoBean;


/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-test.xml")
@Ignore
public class AppTest {

	@Autowired
	CustomerService customerService;
	
	@Autowired
	LoanService loanService;
	
	@Autowired
	CatalogService catalogService;
	
	@Autowired
	SeguridadBbvaService seguridad;
	
	@Autowired
	ApprovalsService approvalsService;
	
	@Autowired
	TariffServiceImpl tariffService;
	
	@Autowired
	PersonService personService;
	
	@Autowired
	ProposalService proposalService;
	
	@Test
    public void suite()
    {
//		ObjectMapper mapper= new ObjectMapper().setVisibility(JsonMethod.FIELD,
//                Visibility.ANY);
		try {
			
//			obtenerCatalogo();
//			listarTarifa();
//			filtrocliente();
//			listarCliente( "DNI", "07020946");
//			obtenerPersona("L", "14090002");
//			altaNocliente();
		//	listarPropuesta("DNI", "08306088");
//			obtenerContrato("00110704009690004172");
//			crearCliente();
			altaPropuesta();
//			actualizarPropuesta();|













		obtnerCronograma(listarPropuesta2("DNI", "08306088").get(0));
	//		obtenerCatalogo();
	//		simulateLoan();
//			//String cadena = "{\"version\": 1,\"severity\": \"FATAL\",\"httpStatus\": 400, \"errorCode\": \"wrongParameters\",\"errorMessage\": \"Los datos ingresados son inválidos.\",\"consumerRequestId\": \"3340b6ed-d21c-43f2-a545-d51413baa680\",\"systemErrorCode\": \"wrongParameters\",\"systemErrorDescription\": \"Los datos ingresados son inválidos.\"}";
//			String cadena = "{\"version\": 1,\"severity\": \"FATAL\",\"http-status\": 400, \"error-code\": \"wrongParameters\",\"error-message\": \"Los datos ingresados son inválidos.\",\"consumer-request-id\": \"3340b6ed-d21c-43f2-a545-d51413baa680\",\"system-error-code\": \"wrongParameters\",\"system-error-description\": \"Los datos ingresados son inválidos.\"}";
//			cadena=cadena.replaceAll("http-status", "httpStatus");
//			cadena=cadena.replaceAll("system-error-code", "systemErrorCode");
//			cadena=cadena.replaceAll("error-code", "errorCode");
//			cadena=cadena.replaceAll("error-message", "errorMessage");
//			cadena=cadena.replaceAll("consumer-request-id", "consumerRequestId");
//			cadena=cadena.replaceAll("system-error-description", "systemErrorDescription");
//			ErrorService user1 = mapper.readValue(cadena, ErrorService.class);
//			System.out.println(user1);
//
//		} catch (JsonGenerationException e) {
//			e.printStackTrace();
//		} catch (JsonMappingException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

				//obtenerCatalogo();
				//listarTarifa();
				//filtrocliente();
				listarCliente( "DNI", "07020946");
				//obtenerPersona("L", "14090002");
				//altaNocliente();
				//listarPropuesta("DNI", "08306088");
				//obtenerContrato("00110704009690004172");
				//crearCliente();
				//altaPropuesta();
				//actualizarPropuesta();|
				//obtnerCronograma(listarPropuesta2("DNI", "08306088").get(0));
				//obtenerCatalogo();
				//simulateLoan();
		  	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        
    }
	
	public void obtenerCatalogo() throws Exception{
	        System.out.println("########################### INICIO CATALOGOS  ###############################");
	        System.out.println("INICIO EL LLAMADO CATALOG 0018");
	        String tsec = seguridad.generarTSec(3);
		    List<CatalogoBean> catalogo = catalogService.obtenerTablaCorporativa("0018", tsec);
		    for (CatalogoBean catalogoBean : catalogo) {
		    	System.out.println(catalogoBean.getIdCatalogo()+" -  " + catalogoBean.getStValue());
			}
//		    System.out.println(ToStringBuilder.reflectionToString(catalogo));
	        
	        System.out.println("INICIO EL LLAMADO CATALOG 0721");
	    	String tsec2 = seguridad.generarTSec(3);
	    	List<CatalogoBean> catalogo2 = catalogService.obtenerTablaCorporativa("0721", tsec2);
	    	System.out.println(ToStringBuilder.reflectionToString(catalogo2));
	    
	        System.out.println("INICIO EL LLAMADO CATALOG 0041");
	    	String tsec3 = seguridad.generarTSec(3);
	    	List<CatalogoBean> catalogo3 = catalogService.obtenerTablaCorporativa("0041", tsec3);
	    	System.out.println(ToStringBuilder.reflectionToString(catalogo3));
	  	    System.out.println("#############################################################################");
	    }
	
	public void listarTarifa(EmpresaBean empresa){
		System.out.println("####################### INICIO EL LLAMADO LISTTARIFF #######################");
		String tsecPublic= seguridad.generarTSec(2);
    	List<TarifaBean> lista = null;
		try {
			lista = tariffService.mostrarTarifas(tsecPublic, empresa);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(ToStringBuilder.reflectionToString(lista));
        System.out.println("############################################################################");
	}
	
	public void filtrocliente() throws Exception{
	        System.out.println("########################### INICIO FILTRO CLIENTE ##############3############");
	    	CalificacionClienteBean resultado = new CalificacionClienteBean();
	        String tsecApp = seguridad.generarTSec(3);
	        resultado = approvalsService.evaluarCliente("DNI", "85147629", tsecApp);
	        System.out.println(ToStringBuilder.reflectionToString(resultado));
		    System.out.println("#############################################################################");
	 }
	
	public void listarCliente(String tipoDocumento, String nroDocumento) throws Exception{
        System.out.println("########################## INICIO LISTAR CLIENTE ##########################");
		String tsecPublic= seguridad.generarTSec(2);
    	ClienteBean customer = customerService.obtenerDatosCliente(tsecPublic,tipoDocumento, nroDocumento, null);
        System.out.println(ToStringBuilder.reflectionToString(customer));
		try {
	    	customer = customerService.obtenerDatosCliente(tsecPublic,tipoDocumento, nroDocumento, null);
//	    	if(customer.getRptErrorService()!=null){
//	            System.out.println("Sucedio un error : " +  customer.getRptErrorService());
//	    	}else{
//	            System.out.println(ToStringBuilder.reflectionToString(customer));
//	    	}
		} catch (Exception e) {
			e.printStackTrace();
//            System.out.println("Sucedio un error : " +  customer.getRptErrorService());
		}
	    System.out.println("#############################################################################");
	}
	
	public void obtenerPersona(String tipoDocumento, String nroDocumento) throws Exception{
        System.out.println("######################### INICIO EL LLAMADO PERSONA ########################");
		String tsecPublic2= seguridad.generarTSec(2);
    	PersonaBean persona = personService.buscarNoCliente(tipoDocumento, nroDocumento, "2", tsecPublic2);
        System.out.println(ToStringBuilder.reflectionToString(persona));
        System.out.println("############################################################################");
	}
	
	public void altaNocliente() throws Exception{
        System.out.println("######################### INICIO EL LLAMADO PERSON #########################");
    	String tsecPublic3= seguridad.generarTSec(2);
    	
    	PersonaBean personaBean = new PersonaBean();
    	personaBean.setNumeroDocumento("14090009");//IMPORTANTE CAMBIAR NUMERO DE DOCUMENTO
    	personaBean.setTipoDocumento("L");
    	personaBean.setNombres("Sasuke");
    	personaBean.setPaterno("Uchiha");
    	personaBean.setMaterno("prueba");
    	personaBean.setClienteNuevo(true);
    	personaBean.setCodigoEstadoCivil("S");
    	personaBean.setCodigoSexo("M");
    	personaBean.setNacimiento(UtilDate.convertStringToJUtilDate("1990-01-01", "YYYY-MM-dd")+"");
    	
    	//CL(|)TAVARA WEST(|)Nro. 1054(|)Mz. G/(|) (|) (|)UR.(|)DANIEL ALCIDES CARRION ET.2(|)
    	
    	List<GrupoGeografico> grupoGeograficoLst = new ArrayList<>();
    	
		GrupoGeografico grupoGeografico=  new GrupoGeografico();
       grupoGeografico.setId("DEPARTMENT");//viene de no cliente
       grupoGeografico.setCode("15");//viene de no cliente
       grupoGeograficoLst.add(grupoGeografico);
       
       GrupoGeografico grupoGeografico2=  new GrupoGeografico();
       grupoGeografico2.setId("PROVINCE");//viene de no cliente
       grupoGeografico2.setCode("01");//viene de no cliente
       grupoGeograficoLst.add(grupoGeografico2);
       
       GrupoGeografico grupoGeografico3=  new GrupoGeografico();
       grupoGeografico3.setId("DISTRICT");//viene de no cliente
       grupoGeografico3.setCode("004");//viene de no cliente
       grupoGeograficoLst.add(grupoGeografico3);
       
       
    	List<DireccionBean> listDireccion = new ArrayList<DireccionBean>();
    	DireccionBean direccion = new DireccionBean();
    	direccion.setLstGrupoGeografico(grupoGeograficoLst);
    	direccion.setDsDireccion("CL(|)konoha(|)Nro. 1054(|)Mz. G/(|) (|) (|)UR.(|)DANIEL ALCIDES CARRION ET.2(|)");
    	listDireccion.add(direccion);
    	
    	
	       
    	personaBean.setLstDirecciones(listDireccion);
    	personaBean=personService.altaNoCliente(personaBean, tsecPublic3);
    	System.out.println("ID PERSONA CREADA : " + personaBean.getId());
    	System.out.println("PERSONA CREADA : " + ToStringBuilder.reflectionToString(personaBean));
        System.out.println("#############################################################################");
	}
	
	public void crearCliente() throws Exception{
           System.out.println("########################## INICIO CREAR CLIENTE ##########################");
		   String tsecPublic= seguridad.generarTSec(2);
		   ClienteBean clienteBean = new ClienteBean();
	       clienteBean.setPrimerNombre("LEONEL");
	       clienteBean.setApellidoPaterno("MESSI");
	       clienteBean.setApellidoMaterno("RIVASPLATA");
	       clienteBean.setFechaNacimiento(UtilDate.convertStringToSqlDate("1985-09-02", "yyyy-MM-dd"+"")+"");
	       
	       DocumentoIdentidadBean documentoIdentidadBean = new DocumentoIdentidadBean();
	       documentoIdentidadBean.setTipoDocumentoIdentidad("DNI");
	       documentoIdentidadBean.setPais("PER");
	       documentoIdentidadBean.setEstado("ACTIVE");
	       documentoIdentidadBean.setFechaExpiracion(UtilDate.convertStringToSqlDate("2019-09-02", "yyyy-MM-dd")+"");
	       documentoIdentidadBean.setNroDocumento("66612345");
	       clienteBean.setLstDocumentoIdentidadBean(new ArrayList<DocumentoIdentidadBean>());
	       clienteBean.getLstDocumentoIdentidadBean().add(documentoIdentidadBean);
	   
	       clienteBean.setGenero("0");
	       clienteBean.setEstadoCivil(1);
	       
	       List<String> nacionalidades = new ArrayList<>();
	       nacionalidades.add("PER");
	       clienteBean.setNacionalidades(nacionalidades);
		  
	       
	       clienteBean.setTelefonoMovil("930457001");
	       clienteBean.setCorreoCliente("ivanbeto@gmail.com");
//	       clienteBean.setIdTipoOcupacion(100);		
	       
//	       DateFormat df3 = new SimpleDateFormat("yyyy-MM-dd");
//	       Date startDate3 = df3.parse("2019-09-02");
	      // clienteBean.setFechaExpiracionDocumento(startDate3);
	       
	       DireccionBean direccionBean = new DireccionBean();
	       direccionBean.setDsDireccion("Avenida República de Ecuador 1234 Piso 2 Interior 205 Urbanización Corpac");
	       direccionBean.setIdCountry("PER");
	       direccionBean.setIdTipoDireccion("LEGAL");
	       direccionBean.setRfAdicionalInfo("COSTADO COLEGIO SAN AGUSTIN");
	       direccionBean.setIdTipoPropiedad("OWNER");
	       
	       List<GrupoGeografico> grupoGeograficoLst = new ArrayList<>();
	       GrupoGeografico grupoGeografico=  new GrupoGeografico();
	       grupoGeografico.setId("DEPARTMENT");
	       grupoGeografico.setCode("01");
	       grupoGeograficoLst.add(grupoGeografico);
	       
	       GrupoGeografico grupoGeografico2=  new GrupoGeografico();
	       grupoGeografico2.setId("PROVINCE");
	       grupoGeografico2.setCode("01");
	       grupoGeograficoLst.add(grupoGeografico2);
	       
	       GrupoGeografico grupoGeografico3=  new GrupoGeografico();
	       grupoGeografico3.setId("DISTRICT");
	       grupoGeografico3.setCode("004");
	       grupoGeograficoLst.add(grupoGeografico3);
	       
	       GrupoGeografico grupoGeografico4=  new GrupoGeografico();
	       grupoGeografico4.setId("URBANIZATION");
	       grupoGeografico4.setNombre("Corpac");
	       grupoGeograficoLst.add(grupoGeografico4);
	       
	       GrupoGeografico grupoGeografico5=  new GrupoGeografico();
	       grupoGeografico5.setId("DESCENT");
	       grupoGeografico5.setNombre("República de Ecuador");
	       grupoGeograficoLst.add(grupoGeografico5);
	       
	       GrupoGeografico grupoGeografico6=  new GrupoGeografico();
	       grupoGeografico6.setId("EXTERIOR_NUMBER");
	       grupoGeografico6.setNombre("1234");
	       grupoGeograficoLst.add(grupoGeografico6);
	       
	       GrupoGeografico grupoGeografico7=  new GrupoGeografico();
	       grupoGeografico7.setId("INTERIOR_NUMBER");
	       grupoGeografico7.setNombre("");
	       grupoGeograficoLst.add(grupoGeografico7);
	       
	       direccionBean.setLstGrupoGeografico(grupoGeograficoLst);
	       
	       clienteBean.setLstDireccionBean(new ArrayList<DireccionBean>());
	       clienteBean.getLstDireccionBean().add(direccionBean);
	       
	       List<ContactoBean> lstContacto = new ArrayList<ContactoBean>();
	       ContactoBean contacto = new ContactoBean();
	       contacto.setDtContacto("945908616");
	       contacto.setPaisContacto("PER");
	       contacto.setTpContato("MOBILE_NUMBER");
	       lstContacto.add(contacto);
	       ContactoBean contacto2 = new ContactoBean();
	       contacto2.setDtContacto("tgagliuffi@gmail.com");
	       contacto2.setTpContato("EMAIL");
	       lstContacto.add(contacto2);
	       clienteBean.setLstContacto(lstContacto);
	       
	       DatosLaboralesBean datosLaboralesBean = new DatosLaboralesBean();
	       datosLaboralesBean.setCentroLaboral("BBVA CONSUMER FINANCE");
	       datosLaboralesBean.setIdOcupacion("100");
	       datosLaboralesBean.setLstIngresos(new ArrayList<IngresosBean>());
	       
	       IngresosBean ingresosBean = new IngresosBean();
	       ingresosBean.setIdTipoIngreso("FIXED");
	       ingresosBean.setLstValorIngreso(new ArrayList<ValorIngresoBean>());
	       
	       ValorIngresoBean valor1 = new ValorIngresoBean();
	       valor1.setMonedaIngreso("PEN");
	       valor1.setMtoIngreso("1000");
	       ingresosBean.getLstValorIngreso().add(valor1);
	       datosLaboralesBean.getLstIngresos().add(ingresosBean);
	       clienteBean.setDatosLaboralesBean(datosLaboralesBean);
	       clienteBean.setLstCondiciones(new ArrayList<String>() );
	       clienteBean.getLstCondiciones().add("");
	     
	       clienteBean =   customerService.altaCliente(tsecPublic, clienteBean, null);
//		   System.out.println("Respuesta : " + clienteBean.getRepuestaService().getExitoDescription());
	       System.out.println("#############################################################################");

	}
	
	public void obtenerContrato(String cdContrato) throws Exception{
        System.out.println("########################## INICIO OBTENER CONTRATO ##########################");
    	PrestamoBean prestamoBean = new PrestamoBean();
    	prestamoBean.setPrestamoId(cdContrato);
        String tsecBack = seguridad.generarTSec(3);
        prestamoBean = loanService.obtenerPrestamo(prestamoBean, tsecBack);
        System.out.println(ToStringBuilder.reflectionToString(prestamoBean));
	    System.out.println("#############################################################################");
	}
 
    public void altaPropuesta() throws Exception{
    	
        System.out.println("############################# ALTA CONTRATO #################################");
	  	String tsecPublic2= seguridad.generarTSec(2);
	  	ContratoAltaBean contrato = new ContratoAltaBean();
	  	contrato.setClienteBean(new ClienteBean());
	  	contrato.getClienteBean().setNumeroDocumento("08743858");
	  	contrato.getClienteBean().setTipoDocumento("DNI");//DNI
	  	contrato.getClienteBean().setCorreoCliente("prueba001@gmail.com");
	  	contrato.getClienteBean().setDescTipoDocumento("DNI");
	  	
	  	contrato.setAddNombreTarifa("BBVA002");
	  	contrato.setMonedaCredito(1);//soles
	  	contrato.setImportePrestamo(500.0);
	  	
	  	contrato.setDiaPago(30);
	  	contrato.setAdddiaFacturacion(24);
	  	contrato.setTelefonoFinanciado("999140901");
	  //	contrato.setIdTipoEnvio(2);//virtual
	  	//contrato.setCodTipoEnvio(ProposalService.TipoEnvio.DIGITAL.getTipoEnvio());
	  	contrato.setNumeroContrato("140988666140988010");
	  	contrato.setValorEquipo(1800.0);
	  	contrato.setMonedaCredito(1);
	  	contrato.setCodTipoEnvio("");
	  	contrato.setProveedorTercero("TELF");
	  	contrato.setCanal("TELF");
	  	contrato.setProductoExterno("CTEL000985699");
	  	contrato = proposalService.altaProposal(tsecPublic2, contrato);
	    System.out.println("Respuesta Propuesta : " + contrato.getNumeroContrato());
  	    System.out.println("#############################################################################");
    }

    public void listarPropuesta(String tipoDocumento, String nroDocumento)throws Exception{
        System.out.println("########################### INICIO LISTAR PROPUESTA ##########################");
        String tsecApp = seguridad.generarTSec(3);
	  	List<ContratoBean> lista = proposalService.listarPropuesta(tsecApp,  tipoDocumento, nroDocumento , null);
	  	System.out.println(ToStringBuilder.reflectionToString(lista));
	    System.out.println("#############################################################################");

    }
    
    public List<ContratoBean> listarPropuesta2(String tipoDocumento, String nroDocumento)throws Exception{
        System.out.println("########################### INICIO LISTAR PROPUESTA ##########################");
        String tsecApp = seguridad.generarTSec(3);
	  	List<ContratoBean> lista = proposalService.listarPropuesta(tsecApp, tipoDocumento, nroDocumento, null);
	  	System.out.println(ToStringBuilder.reflectionToString(lista));
	    System.out.println("#############################################################################");
	    return lista;

    }

    public void actualizarPropuesta() throws Exception{
        System.out.println("########################### INICIO UPDATE PROPUESTA ###########################");
        String tsecApp = seguridad.generarTSec(3);
        ContratoBean contratoUpdate = new ContratoBean(); 
	  	contratoUpdate.setCodigoContrato("001101309690070922");//96089564798564201 
	  	contratoUpdate.setTipoEnvio("VIRTUAL");
	  	contratoUpdate.setCorreo("percy1409@gmail.com");
	  	contratoUpdate.setEstadoContrato("ESTADO_TRAMITE");
	  	
	  	int salida = proposalService.updateProposal(tsecApp, contratoUpdate);
	  	System.out.println("Se realizo el Update : " + salida);
	    System.out.println("#############################################################################");

    }

    public void simulateLoan() throws Exception{
		
		SimulacionBean simulacion = new SimulacionBean();
		String tSec = seguridad.generarTSec(2);
		simulacion.setCabecera(new CabeceraBean());
		
		//simulacion.getCabecera().setTcea("");
		simulacion.getCabecera().setCodigoTarifa("");
		simulacion.getCabecera().setCuotaInicial(new BigDecimal(500.00));
		
		simulacion.getCabecera().setMetodoEnvio(1);
		simulacion.setCdSubProducto("MSC0");
		simulacion.getCabecera().setSaldoFinanciar(new BigDecimal(3000.00));
		simulacion.getCabecera().setNumeroCuotas(24);
		simulacion.getCabecera().setDiaPago(30);
		simulacion.getCabecera().setTea("30");
		
		
		//simulacion.setFechaPrestamo(null);
		simulacion.getCabecera().setInPerCapital(null);
		simulacion.getCabecera().setInPerInteres(null);
		
		simulacion.getCabecera().setInCarCapital(null);
		simulacion.getCabecera().setInCarInteres(null);
		
		//commercialValueAmount
		simulacion.setFechaPrestamo("2017-07-10");
		simulacion.getCabecera().setMoneda("");
		simulacion.getCabecera().setSeguroDesgravamen("A");
		
		simulacion.setTotales(new TotalesBean());
		simulacion.getTotales().setComisionEnvio("");
		simulacion.getTotales().setTotalAmortizacion("");
		simulacion.getTotales().setTotalCuotaTotal("");
		simulacion.getTotales().setTotalIntereses("30");
		simulacion.getTotales().setTotalSaldoCapital("");
		simulacion.getTotales().setTotalSeguroDesgravamen("A");
		
//		simulacion.getCabecera().
		
		simulacion.getCabecera().setValorEquipo(new BigDecimal(3000.00));;
		
		loanService.simularPrestamo(simulacion, tSec);
	}
    
    public void obtnerCronograma(ContratoBean contrato) throws Exception{
    	String tSec1 = seguridad.generarTSec(2);
    	List<TarifaBean> lista = tariffService.mostrarTarifas(seguridad.generarTSec(2), new EmpresaBean());
    	SimulacionBean simulacion = new SimulacionBean();
    	simulacion.setCabecera(new CabeceraBean());
    	
    	String cdSubProducto = null;
    	
    	for (int i = 0; i < lista.size(); i++) {
    		
			if(lista.get(i).getCodigoTarifa().equals(contrato.getCodigoTarifa())){
				simulacion.getCabecera().setTea(lista.get(i).getTasa());
				String[] descrip = lista.get(i).getDescripcion().split("!");
				cdSubProducto=descrip[0];
			}
		}
		//simulacion.getCabecera().setTcea("");
		simulacion.getCabecera().setCodigoTarifa(contrato.getCodigoTarifa());
		simulacion.getCabecera().setCuotaInicial(new BigDecimal(contrato.getImporteInicial()));//de contrato
		
		simulacion.getCabecera().setMetodoEnvio(1);
		simulacion.setCdSubProducto(cdSubProducto);//parametrizar este valor
		simulacion.getCabecera().setSaldoFinanciar(new BigDecimal(contrato.getImportePrestamo()));
		simulacion.getCabecera().setNumeroCuotas(Integer.parseInt(contrato.getNumeroCuotas()));
		//simulacion.getCabecera().setInDiaPago(contrato.getDiaPago());
		simulacion.getCabecera().setTea(contrato.getTasaFinanciamiento());//viene null
		
		
		//simulacion.setFechaPrestamo(null);
		simulacion.getCabecera().setInPerCapital(null);
		simulacion.getCabecera().setInPerInteres(null);
		
		simulacion.getCabecera().setInCarCapital(null);
		simulacion.getCabecera().setInCarInteres(null);
		
		//commercialValueAmount
		simulacion.setFechaPrestamo(contrato.getFechaCreacion());//verificar si es esta fecha
		simulacion.getCabecera().setMoneda("");
		simulacion.getCabecera().setSeguroDesgravamen("A");//parametrizar este valor
		
		simulacion.setTotales(new TotalesBean());
		simulacion.getTotales().setComisionEnvio("");
		simulacion.getTotales().setTotalAmortizacion("");
		simulacion.getTotales().setTotalCuotaTotal("");
		simulacion.getTotales().setTotalIntereses("");
		//simulacion.getTotales().setTotalIntereses(contrato.getTasaFinanciamiento());//viene null
		simulacion.getTotales().setTotalSaldoCapital("");
		simulacion.getTotales().setTotalSeguroDesgravamen("");
//		simulacion.getCabecera().
		simulacion.getCabecera().setValorEquipo(new BigDecimal(3000.00));;
		
		SimulacionBean simulacionSalida = loanService.simularPrestamo(simulacion, tSec1);
		System.out.println(ToStringBuilder.reflectionToString(simulacionSalida));
    	
    	
    }
	
}
