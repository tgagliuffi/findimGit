package com.bbva.findim.ws.service;

import com.bbva.findim.dom.FirmaContratoBean;
import com.bbva.findim.ws.util.Enumeradores.EnumRespuestaSignBox;

public interface FirmaContratoService {

	EnumRespuestaSignBox firmaDocumentoContrato(FirmaContratoBean firmaContratoBean) throws Exception;
}
