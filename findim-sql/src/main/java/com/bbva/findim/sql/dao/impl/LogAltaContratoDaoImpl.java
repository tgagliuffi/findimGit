package com.bbva.findim.sql.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bbva.findim.dom.AltaContratoRequest;
import com.bbva.findim.dom.RespuestaBean;
import com.bbva.findim.sql.connection.DBConnection;
import com.bbva.findim.sql.dao.LogAltaContratoDao;

@Repository
public class LogAltaContratoDaoImpl implements LogAltaContratoDao {

	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
	    this.jdbcTemplate = jdbcTemplate;  
	    jdbcTemplate.setResultsMapCaseInsensitive(true);
	} 

	public Long insert(AltaContratoRequest input) {
			Connection con = null;
			CallableStatement stmt = null;
			String result="";
			Long id_contrato=null;
			try{
				con = DBConnection.getConnection();
				stmt = con.prepareCall("{call INSERT_ALTA_LOG_TDP_ORACLE (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
				
				stmt.setString(1, input.getLlaveUnica());
				stmt.setString(2, input.getAtributo());
				stmt.setString(3, input.getTarifa());
				stmt.setDouble(4, input.getImporteBien());
				stmt.setDouble(5, input.getImportePrestamo());
				stmt.setInt(6, input.getDiaFacturacion());
				stmt.setInt(7, input.getDiaPagoCredito());
				stmt.setString(8, input.getTipoDocIdentidad());
				stmt.setString(9, input.getNumeroDocIdentidad());
				stmt.setString(10, input.getDireccion());
				stmt.setString(11, input.getDepartamento());
				stmt.setString(12, input.getProvincia());
				stmt.setString(13, input.getDistrito());
				stmt.setString(14, input.getTelefonoFijo());
				stmt.setString(15, input.getTelefonoReferencia());
				stmt.setString(16, input.getTelefonoFinanciado());
				stmt.setString(17, input.getCorreo());
				stmt.setString(18, input.getTipoTransaccion());
				stmt.setString(19, input.getCodigoCanal());
				stmt.setString(20, input.getNombreCanal());
				stmt.setInt(21, input.getCodigoEntidad()!=null?input.getCodigoEntidad():0);
				stmt.setString(22, input.getNombreEntidad()!=null?input.getNombreEntidad():null);
				stmt.setInt(23, input.getCodigoPuntoVenta()!=null?input.getCodigoPuntoVenta():0);
				stmt.setString(24, input.getNombrePuntoVenta()!=null?input.getNombrePuntoVenta():null);
				stmt.setInt(25, input.getTipoEnvio()!=null?input.getTipoEnvio():0);
				stmt.setString(26, input.getDepartamentoFirma()!=null?input.getDepartamentoFirma():null);
				stmt.setDate(27, new java.sql.Date(System.currentTimeMillis()));
				stmt.setString(28, input.getTpIndicadorProceso());
				stmt.setString(29, input.getRtFiltroCliente());
				stmt.setString(30, input.getNbMotivoRechazo());
				stmt.setString(31, input.getTxCodigoError());
				stmt.setString(32, input.getTxMensajeFuncional());
				stmt.setString(33, input.getTxMensajeTecnico());
				
				stmt.registerOutParameter(34, java.sql.Types.VARCHAR);
				stmt.executeUpdate();
				//read the OUT parameter now
				result = stmt.getString(34);
				id_contrato=Long.parseLong(result);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try {
					stmt.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		return id_contrato;		
		
	}

	public Integer update(Long id, RespuestaBean output) {
		return jdbcTemplate.update(
				" UPDATE t132_log_alta_contrato SET"
				+ "    fh_respuesta = now(),"
				+ "    tp_indicador_proceso = ?,"
				+ "    tx_mensaje_funcional = ?,"
				+ "    tx_mensaje_tecnico = ?"
				+ " WHERE cd_log_alta_contrato = ?",
				output.getIndicadorProceso(), output.getMensajeFuncional(), output.getMensajeTecnico(), id
				);
	}

}
