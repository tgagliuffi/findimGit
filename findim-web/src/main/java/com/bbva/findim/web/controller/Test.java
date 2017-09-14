package com.bbva.findim.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.bbva.findim.bck.domain.customers.Address;
import com.bbva.findim.bck.domain.customers.AddressType;
import com.bbva.findim.bck.domain.customers.Country;
import com.bbva.findim.bck.domain.customers.GeographicGroup;
import com.bbva.findim.bck.domain.customers.GeographicGroupType;
import com.bbva.findim.bck.domain.customers.Location;
import com.bbva.findim.bck.domain.customers.OwnershipType;
import com.bbva.findim.dom.ClienteBean;
import com.bbva.findim.dom.DireccionClienteBean;
import com.bbva.findim.dom.UbicacionDireccionBean;
import com.bbva.findim.web.common.Constantes;

public class Test {

	public static void main(String[] args) {

		String  idUrbano  = "EXTERIOR_NUMBER.";
		String urbano = "AVENUE(|)REPÚBLICA DE PANAMÁ(|)EXTERIOR_NUMBER. 3055(|)BLOCK. / LOT. (|)PISO 3(|)DPTO. 203/ INTERIOR_NUMBER. 204(|)URBANIZATION(|)CORPAC(|)";
		String NOurbano = "AVENUE(|)REPÚBLICA DE PANAMÁ(|)(|)BLOCK. A / LOT. 18 (|)(|)(|)URBANIZATION(|)CORPAC(|)";

		ClienteBean cliente = new ClienteBean();
	    DireccionClienteBean direccionCliente;
	    direccionCliente = new DireccionClienteBean();
		direccionCliente.setIdTipoDireccion("000");
		direccionCliente.setIdTipoPropiedad("HOME");
		direccionCliente.setNbTipoPropiedad("HOGAR");
		direccionCliente.setStrDireccionInputTlf(urbano);
		direccionCliente.setUbicacion(new UbicacionDireccionBean());
		direccionCliente.getUbicacion().setDsUbigeo("0101005");
		direccionCliente.getUbicacion().setNbPais("PER");
		cliente.setDireccionCliente(direccionCliente);
		
		if(esUrbano(direccionCliente.getStrDireccionInputTlf(), idUrbano)){
			List<Address> lstAddress = setDireccionCliente(cliente, true);
			System.out.println("Tamaño de lista Urbano : " + lstAddress.size());
		}else{
			List<Address> lstAddress = setDireccionCliente(cliente, false);
			System.out.println("Tamaño de lista NoUrbano: " + lstAddress.size());
		}
		
		System.out.println("Salida : "  +
				Constantes.EstadoCivil.S.name() + " VALUE : " + 
				Constantes.EstadoCivil.S.getEstadoCivil()
				);
		
		
	}
	
	public static List<Address> setDireccionCliente(ClienteBean cliente, Boolean esUrbano){
		String[] datosDireccion;
		List<Address> lstAddress = null;
		
		if(cliente.getDireccionCliente()!=null){
			if(cliente.getDireccionCliente().getStrDireccionInputTlf()!=null){
				lstAddress = new ArrayList<>();
			
				datosDireccion = cliente.getDireccionCliente().getStrDireccionInputTlf().split(Pattern.quote("(|)"));
				Address  addresses = new Address();
				addresses.setAddressType(new AddressType());
				addresses.getAddressType().setId(cliente.getDireccionCliente().getIdTipoDireccion());
				addresses.setOwnershipType(new OwnershipType());
				addresses.getOwnershipType().setId(cliente.getDireccionCliente().getIdTipoPropiedad());
				
				if(cliente.getDireccionCliente().getUbicacion()!=null){
					addresses.setLocation(new Location());
					addresses.getLocation().setCountry(new Country());
					addresses.getLocation().getCountry().setId(cliente.getDireccionCliente().getUbicacion().getNbPais());
					
					if(cliente.getDireccionCliente().getUbicacion().getDsReferencia() !=null)
						addresses.getLocation().setAdditionalInformation(cliente.getDireccionCliente().getUbicacion().getDsReferencia());

					if(datosDireccion.length==8){
						addresses.getLocation().setGeographicGroups(new ArrayList<>());
						GeographicGroup geographicGroup = null;
						geographicGroup = new GeographicGroup();
						geographicGroup.setGeographicGroupType(new GeographicGroupType());
						geographicGroup.getGeographicGroupType().setId("UBIGEO");
						geographicGroup.setCode(cliente.getDireccionCliente().getUbicacion().getDsUbigeo());
						addresses.getLocation().getGeographicGroups().add(geographicGroup);
						Boolean esCompuesto = false;
						for (int i = 0; i < datosDireccion.length; i++) {
							if(datosDireccion[i]!=null) {
										 geographicGroup = new GeographicGroup();
										 geographicGroup.setGeographicGroupType(new GeographicGroupType());
										 if(esUrbano) {
											 if(datosDireccion[i].indexOf("INTERIOR_NUMBER")>-1) {
												 	String[] arregloRefInterna = datosDireccion[i].split(Pattern.quote("/"));
													geographicGroup.getGeographicGroupType().setId("INTERIOR_NUMBER");
													if(!arregloRefInterna[0].equals("INTERIOR_NUMBER ")) {
														geographicGroup.setName(arregloRefInterna[1].replaceAll("[^0-9]", "").trim());
													}else {
														geographicGroup.setName(arregloRefInterna[0].replaceAll("[^0-9]", "").trim());
													}
												}
												else if(datosDireccion[i].indexOf("EXTERIOR_NUMBER")>-1) {
													geographicGroup.getGeographicGroupType().setId("EXTERIOR_NUMBER");
													geographicGroup.setName(datosDireccion[i].replaceAll("[^0-9]", "").trim());
												}else {
													geographicGroup.getGeographicGroupType().setId(datosDireccion[i]);
													geographicGroup.setName(datosDireccion[i+1]);
													i++;
												}
										 }else {
											 String[] aregloNoUrbano = datosDireccion[3].split(Pattern.quote("/"));
											 if(datosDireccion[i].indexOf("BLOCK.")>-1 && !esCompuesto) {
												 if(aregloNoUrbano.length>=1) {
													geographicGroup.getGeographicGroupType().setId("BLOCK");
													geographicGroup.setName(aregloNoUrbano[0].replaceAll("BLOCK.", "").trim());
													esCompuesto=true;
													i--;
												 }
											}else if(datosDireccion[i].indexOf("LOT.")>-1 && esCompuesto) {
												 if(aregloNoUrbano.length>=1) {
													geographicGroup.getGeographicGroupType().setId("LOT");
													geographicGroup.setName(aregloNoUrbano[1].replaceAll("LOT.", "").trim());
												 }
											}else {
												    if(!datosDireccion[i].equals("")) {
												    	geographicGroup.getGeographicGroupType().setId(datosDireccion[i]);
												    	geographicGroup.setName(datosDireccion[i+1]);
												    	i++;
												    }
												}
										 }
										 
										 if(geographicGroup.getGeographicGroupType().getId()!=null)
											 addresses.getLocation().getGeographicGroups().add(geographicGroup);
							}
						}
					}
				}
				lstAddress.add(addresses);
			}
		}	
		return lstAddress;
	}
	
	public static Boolean esUrbano(String  direccion,String idUrbano){
		if(direccion.indexOf(idUrbano)>-1){
			return true;
		}
		return false;
	}

}
