package com.bbva.findim.sql.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bbva.findim.dom.EventoContratoBean;
import com.bbva.findim.sql.connection.DBConnection;
import com.bbva.findim.sql.dao.LogContratoEventoDao;
@Repository
public class LogContratoEventoDaoImpl implements LogContratoEventoDao {

	@SuppressWarnings("unused")
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
	    this.jdbcTemplate = jdbcTemplate;  
	    jdbcTemplate.setResultsMapCaseInsensitive(true);
	} 
	
	@SuppressWarnings("unused")
	public Integer registrarEventoContrato(EventoContratoBean eventoContratoBean) {
		Integer tipoRespuesta = 0;                                                                                        
		Connection con = null;
		CallableStatement stmt = null;
		String codRpta="";
		String rpta="";
		String mnsj="";
		try{
			con = DBConnection.getConnection();
			stmt = con.prepareCall("{call P_CONTRATO_EVENTO_INS (?,?,?,?,?,?)}");
			
			stmt.setString(1, eventoContratoBean.getIdContrato());
			stmt.setString(2, eventoContratoBean.getCliente());
			stmt.setInt(3, eventoContratoBean.getTipoEvento());			
			stmt.registerOutParameter(4, java.sql.Types.VARCHAR);
			stmt.registerOutParameter(5, java.sql.Types.VARCHAR);			
			stmt.registerOutParameter(6, java.sql.Types.VARCHAR);
			stmt.executeUpdate();
			
			codRpta =  stmt.getString(4);
			rpta = stmt.getString(5);
			mnsj = stmt.getString(6);
			tipoRespuesta = Integer.parseInt(codRpta);
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
		return tipoRespuesta;
	}

}
