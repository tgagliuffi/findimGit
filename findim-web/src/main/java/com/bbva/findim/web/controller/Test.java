package com.bbva.findim.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.bbva.findim.bck.domain.customers.Address;
import com.bbva.findim.bck.domain.customers.AddressType;
import com.bbva.findim.bck.domain.customers.Country;
import com.bbva.findim.bck.domain.customers.GeographicGroup;
import com.bbva.findim.bck.domain.customers.GeographicGroupType;
import com.bbva.findim.bck.domain.customers.Location;
import com.bbva.findim.bck.domain.customers.OwnershipType;
import com.bbva.findim.dom.ClienteBean;
import com.bbva.findim.dom.DireccionBean;
import com.bbva.findim.dom.DireccionClienteBean;
import com.bbva.findim.dom.DireccionDetalleclienteBean;
import com.bbva.findim.dom.UbicacionDireccionBean;

public class Test {

	public static void main(String[] args) {

		String idNoUrbano = "Mz.";
		String  idUrbano  = "INT.";
		String urbano = "AV(|)REPÚBLICA DE PANAMÁ(|)NRO. 3055(|)MZ. / LT. (|)PISO 3(|)DPTO. / INT. 204(|)URB(|)CORPAC(|)";
		
		String[] arreglo = urbano.split("(|)");
	    DireccionClienteBean direccionCliente;
		if(esUrbano(urbano, idUrbano)){
			direccionCliente = new DireccionClienteBean();
			direccionCliente.setUbicacion(new UbicacionDireccionBean());
			direccionCliente.getUbicacion().setLstDetalleDireccion(new ArrayList<DireccionDetalleclienteBean>());
			DireccionDetalleclienteBean bean = new DireccionDetalleclienteBean();
			direccionCliente.getUbicacion().getLstDetalleDireccion().add(bean);
		}else{
			
		}
		
	}
	
	public static void prueba(ClienteBean cliente){
		String[] datosDireccion;
		List<Address> lstAddress = null;
		if(cliente.getDireccionCliente()!=null){
			if(cliente.getDireccionCliente().getStrDireccionInputTlf()!=null){
				datosDireccion = cliente.getDireccionCliente().getStrDireccionInputTlf().split("(|)"); 
				Address  addresses = new Address();
				addresses.setAddressType(new AddressType());
				addresses.getAddressType().setId(cliente.getDireccionCliente().getIdTipoDireccion());
				addresses.setOwnershipType(new OwnershipType());
				addresses.getOwnershipType().setId(cliente.getDireccionCliente().getIdTipoPropiedad());
				
				if(cliente.getDireccionCliente().getUbicacion()!=null){
					addresses.setLocation(new Location());
					addresses.getLocation().setCountry(new Country());
					addresses.getLocation().getCountry().setId(cliente.getDireccionCliente().getUbicacion().getNbPais());
					
					// additionalInformation
					if(cliente.getDireccionCliente().getUbicacion().getDsReferencia() !=null)
						addresses.getLocation().setAdditionalInformation(cliente.getDireccionCliente().getUbicacion().getDsReferencia());

					//Lista de Grupos Geograficos
					if(datosDireccion.length>8){
						for (int i = 0; i < datosDireccion.length; i++) {
							GeographicGroup geographicGroup = new GeographicGroup();
							geographicGroup.setGeographicGroupType(new GeographicGroupType());
//							geographicGroup.getGeographicGroupType().setId(determinarIndicadorDireccion(direccion.getLstGrupoGeografico().get(j).getId()));
//							if(direccion.getLstGrupoGeografico().get(i).getNombre()!=null)
//								geographicGroup.setName(datosDireccion[i]);
//							if(direccion.getLstGrupoGeografico().get(i).getCode()!=null){
//								geographicGroup.setCode(datosDireccion[i]);
//							}
//							lstGeoGroup.add(geographicGroup);
						}
						
					}
				}
				
				
			}
		}	
//			 lstAddress = new ArrayList<Address>();
//			for (int i = 0; i < cliente.getLstDireccionBean().size(); i++) {
//				Address  addresses = new Address();
//				DireccionBean direccion = cliente.getLstDireccionBean().get(i);
//				//Set TypeAddress
//				addresses.setAddressType(new AddressType());
//				addresses.getAddressType().setId(direccion.getIdTipoDireccion());
//				//Set Owner
//				addresses.setOwnershipType(new OwnershipType());
//				addresses.getOwnershipType().setId(direccion.getIdTipoPropiedad());
//				//Set Location
//				if(direccion.getDsDireccion()!=null){
//					addresses.setLocation(new Location());
//					addresses.getLocation().setAddressName(direccion.getDsDireccion());
//					if(direccion.getIdCountry()!=null){
//						addresses.getLocation().setCountry(new Country());
//						addresses.getLocation().getCountry().setId(direccion.getIdCountry());
//					}
//					if(direccion.getRfAdicionalInfo()!=null)
//					addresses.getLocation().setAdditionalInformation(direccion.getRfAdicionalInfo());
//				}
//				//Set GeoGrafica
//				if(direccion.getLstGrupoGeografico()!=null && direccion.getLstGrupoGeografico().size()>0){
//					List<GeographicGroup> lstGeoGroup = new ArrayList<GeographicGroup>();
//						for (int j = 0; j < direccion.getLstGrupoGeografico().size() ; j++) {
//							GeographicGroup geographicGroup = new GeographicGroup();
//							geographicGroup.setGeographicGroupType(new GeographicGroupType());
////							geographicGroup.getGeographicGroupType().setId(determinarIndicadorDireccion(direccion.getLstGrupoGeografico().get(j).getId()));
//							if(direccion.getLstGrupoGeografico().get(j).getNombre()!=null)
//								geographicGroup.setName(direccion.getLstGrupoGeografico().get(j).getNombre());
//							if(direccion.getLstGrupoGeografico().get(j).getCode()!=null){
//								geographicGroup.setCode(direccion.getLstGrupoGeografico().get(j).getCode());
//							}
//							lstGeoGroup.add(geographicGroup);
//						}
//						//set grupo geografico
//						addresses.getLocation().setGeographicGroups(lstGeoGroup);
//				}
//				//Agregamos un dirección
//				lstAddress.add(addresses);
//			}
		
		//Agregamos la lista de direccion
		//clienteBack.setAddresses(lstAddress);
	}
	
	public static Boolean esUrbano(String  direccion,String idUrbano){
		if(direccion.indexOf(idUrbano)>-1){
			return true;
		}
		return false;
	}

}
