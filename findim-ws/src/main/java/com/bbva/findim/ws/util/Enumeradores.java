package com.bbva.findim.ws.util;

public class Enumeradores {
	
	public enum EnumValidacion {                   
		TIPO_NO_VALIDO {
		      public String toString() {
		          return "0100. Tipo no válido para el campo: ";
		      }
		  },
		LONGITUD_NO_VALIDA {
		      public String toString() {
		          return "0200. Longitud no válida para el campo: ";
		      }
		  },   
		
		FORMATO_NO_VALIDO {
		      public String toString() {
		          return "0300. Formato no válido para el campo: ";
		      }
		  },   
		VALOR_NO_VALIDO {
		      public String toString() {
		          return "0400. Valor no válido para el campo: ";
		      }
		  },                              
	}                 
	
	public enum EnumRespuestaFuncionalContrato {                   
		NO_RENIEC {
		      public String toString() {
		          return "1000. Documento del Cliente no se encuentra registrado en Reniec.";
		      }
		  },
		EDAD {
		      public String toString() {
		          return "1100. El cliente no cumple con la edad requerida.";
		      }
		  },
		NO_VIRTUAL {
		      public String toString() {
		          return "1200. Si el envio es virtual, El mail del cliente es obligatorio, Se Abortará el proceso.";
		      }
		  },			                                   
		
	}                 
	
	public enum EnumRespuestaTecnicaContrato {                   
		NO_RENIEC {
		      public String toString() {
		          return "1000. Documento del Cliente no se encuentra registrado en Reniec.";
		      }
		  },
		EDAD {
		      public String toString() {
		          return "1100. El cliente no cumple con la edad requerida.";
		      }
		  },         
		NO_VIRTUAL {
		      public String toString() {
		          return "1200. Si el envio es virtual, El mail del cliente es obligatorio, Se Abortará el proceso.";
		      }
		  },			                                   
	}   
	
	
	public enum EnumRespuestaTecnicaFiltro {                   
		NO_FILTRO {
		      public String toString() {
		          return "2000. El cliente no califica: Filtro BBVA";
		      }
		  },
		
		NO_CONECCION {
		      public String toString() {
		          return "5100. Error en la conexión : Filtro BBVA";
		      }
		  },		
		
	}                   
	
	public enum EnumRespuestaFuncionalFiltro {                   
		NO_FILTRO {
		      public String toString() {
		          return "2000. El cliente no califica: Filtro BBVA";
		      }
		  },
		
		NO_CONECCION {
		      public String toString() {
		          return "5100. Error en la conexión : Filtro BBVA";
		      }
		  },	
		  			
	}   
	
	public enum EnumRespuestaCronograma {                   
		NO_EXISTE_CONTRATO {
		      public String toString() {
		          return "Contrato solicitado no existe.";
		      }
		  },
		NO_EXISTEN_CUOTAS {
		      public String toString() {
		          return "No se generó las cuotas del contrato.";
		      }
		  },
		FORMATO_FECHA_ERRADA {
		      public String toString() {
		          return "Formato de fecha errada.";
		      }
		  },		
	}          	
	
	public enum EnumInvocaFiltro {
		NO(0), SI(1);

		private int Invoca;

		EnumInvocaFiltro(Integer inResultado) {
			this.Invoca = inResultado;
		}
		public Integer getEnumInvocaFiltro() {
			return Invoca;
		}
	}	
	
	
	public enum EnumRespuestaReniec {
		
		NO_CONEXION {
		      public String toString() {
		          return "3001";
		      }
		  },
		NO_CONTROL {
		      public String toString() {
		          return "3002";
		      }
		  },
		SI_MENSAJE {
		      public String toString() {
		          return "0003";
		      }
		  },		
		  
		EXITO {
		      public String toString() {
		          return "0000";
		      }
		  },				  
	}
	
	public enum EnumRespuestaTecnicaReniec {                   
		
		NO_CONECCION {
		      public String toString() {
		          return "5200. Error en la conexión - Servicio Reniec.";
		      }
		  },	
		  			
	}   
	
	public enum EnumTipoTransaccion {                   
		ALTA {
		      public String toString() {
		          return "ALTA";
		      }
		  },
		RENOVACION {
		      public String toString() {
		          return "CAEQ";
		      }
		  },
		PORTABILIDAD {
		      public String toString() {
		          return "PORT";
		      }
		  },		
	}          	
	
	
	public enum EnumTipoEnvio {
		
		VIRTUAL {
		      public String toString() {
		          return "1";
		      }
		  },
		FISICO {
		      public String toString() {
		          return "2";
		      }
		  },
		NINGUNO {
		      public String toString() {
		          return "0";
		      }
		  },
		
	}          	
		
	public enum EnumRespuestaFiltroEdad {
		Minimo(18), Maximo(75);

		private Integer respuesta;

		EnumRespuestaFiltroEdad(Integer respuesta) {
			this.respuesta = respuesta;
		}

		public Integer getRespuestaFiltroEdad() {
			return respuesta;
		}
	}	


	public enum EnumValorEquipo {
		Maximo(999999999999.99);

		private Double respuesta;

		EnumValorEquipo(Double respuesta) {
			this.respuesta = respuesta;
		}

		public Double getEnumValorEquipo() {
			return respuesta;
		}
	}	

	public enum EnumRespuestaSignBox {

		CORRECTO(0, "CORRECTO"),
        ERROR(1, "ERROR");
		
        		
		private Integer codigo;
		private String descripcion;

		private EnumRespuestaSignBox(Integer codigo, String descripcion) {
			this.codigo = codigo;
			this.descripcion = descripcion;
		}

		public Integer getCodigo() {
			return codigo;
		}

		public String getDescripcion() {
			return descripcion;
		}
	}
	
	public enum EnumRespuestaEmail {                   
		NO_EXISTE_CONTRATO_PDF {
		      public String toString() {
		          return "No se encontró el Contrato PDF.";
		      }
		  },
		NO_GENERO_CONTRATO_PDF {
		      public String toString() {
		          return "No se generó Contrato PDF.";
		      }
		  },	
		NO_PROCESO_SERVICIO {
		      public String toString() {
		          return "No se realizó el proceso de Notificación";
		      }
		  },	
		NO_TERMINO_PROCESO {
		      public String toString() {
		          return "No se culminó el proceso de Notificación";
		      }
		  },		
	} 
	
	public enum EnumRespuestaGenerarPdf {                   
		GENERACION_PDF_EXITO {
		      public String toString() {
		          return "Se generó correctamente el documento PDF.";
		      }
		  },
		
		GENERACION_PDF_ERROR {
		      public String toString() {
		          return "Error al generar PDF.";
		      }
		  },
	}
	
	
	public enum EnumIndicadorProceso {
		NO(0), SI(1);

		private Integer IndicadorProceso;

		EnumIndicadorProceso(Integer inResultado) {
			this.IndicadorProceso = inResultado;
		}
		public Integer getIndicadorProceso() {
			return IndicadorProceso;
		}
	}
	
	public enum EnumTipoRespuestaCancelacion {
		EXITOSO(1), ERROR_VALIDACION_COMPROBANTE(0), ERROR_TOTAL(-1);

		private Integer IndicadorProceso;

		EnumTipoRespuestaCancelacion(Integer inResultado) {
			this.IndicadorProceso = inResultado;
		}
		public Integer getIndicadorProceso() {
			return IndicadorProceso;
		}
	}
	
public enum EnumRptaFiltroGeneral {
		
		APROBADO {
		      public String toString() {
		          return "APROBADO";
		      }
		  },
		RECHAZADO {
		      public String toString() {
		          return "RECHAZADO";
		      }
		  },
		NINGUNO {
		      public String toString() {
		          return "NINGUNO";
		      }
		  },
		
	}         	
	
}
