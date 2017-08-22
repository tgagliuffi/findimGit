package com.bbva.findim.sql.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.bbva.findim.dom.BiometriaBean;
import com.bbva.findim.dom.BiometriaValidaMaxIntentosBean;
import com.bbva.findim.dom.ResultadoBiometriaBean;
import com.bbva.findim.sql.connection.DBConnection;
import com.bbva.findim.sql.dao.BiometriaDao;


@Repository
public class BiometriaDaoImpl implements BiometriaDao{

	private static final Logger logger = LogManager.getLogger(BiometriaDaoImpl.class);
	
	
	public Long registrar(BiometriaBean biometriaBean) {
		Connection con = null;
		CallableStatement stmt = null;
		String result="";
		Long idLogBiometrico=null;
		try{
			logger.info("INPUT ");
			logger.info("NUMERO DOCUMENTO " + biometriaBean.getNumeroDocumento());
			logger.info("CODIGO PROCESO " + biometriaBean.getCodigoProceso());
			con = DBConnection.getConnection();
			stmt = con.prepareCall("{call P_GRABAR_BIOMETRIA (?,?,?,?,?,?)}");
			
			stmt.setString(1, biometriaBean.getNumeroDocumento());
			stmt.setString(2, String.valueOf(biometriaBean.getCodigoProceso()));
			
			stmt.registerOutParameter(3, java.sql.Types.VARCHAR);
			stmt.registerOutParameter(4, java.sql.Types.VARCHAR);
			stmt.registerOutParameter(5, java.sql.Types.VARCHAR);
			stmt.registerOutParameter(6, java.sql.Types.VARCHAR);
			stmt.executeUpdate();
			//read the OUT parameter now
			result = stmt.getString(3);
			idLogBiometrico=Long.parseLong(result);
			
			logger.info("OUTPUT ");
			logger.info("ID GENERADO " + idLogBiometrico);
			logger.info("ESTADO " + stmt.getString(4));
			logger.info("MSJ TECNICO " + stmt.getString(5));
			logger.info("MSJ FUNCIONAL " + stmt.getString(6));
		}catch(Exception e){
			logger.error("ERROR : " + e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	return idLogBiometrico;	
	}

	public String actualizar(BiometriaBean biometriaBean) {
		Connection con = null;
		CallableStatement stmt = null;
		String nroDoc ="";
		try{
			logger.info("INPUT ");
			logger.info("NUMERO SOLICITUD " + biometriaBean.getNumeroSolicitud());
			logger.info("CODIGO ERROR " + biometriaBean.getCodError());
			logger.info("DESC ERROR " + biometriaBean.getDescError());
			logger.info("CODIGO ERROR RENIEC " + biometriaBean.getCodErrorReniec());
			logger.info("DESC ERROR RENIEC " + biometriaBean.getDescErrorReniec());
			logger.info("CODIGO PC " + biometriaBean.getCodPc());
			logger.info("APE PAT " + biometriaBean.getApellidoPaterno());
			logger.info("APE MAT " + biometriaBean.getApellidoMaterno());
			logger.info("NOMBRE " + biometriaBean.getNombre());
			logger.info("CODIGO REST " + biometriaBean.getCodRestriccion());
			logger.info("DESC REST " + biometriaBean.getDescRestriccion());
			logger.info("CODIGO VIG " + biometriaBean.getCodVigencia());
			logger.info("DESC VIG " + biometriaBean.getDescVigencia());
			logger.info("IP " + biometriaBean.getIp());
			logger.info("MAC " + biometriaBean.getMac());
			logger.info("CODIGO TRANS " + biometriaBean.getCodTransaccion());
			
			con = DBConnection.getConnection();
			stmt = con.prepareCall("{call P_ACTUALIZAR_BIOMETRIA (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			
			stmt.setString(1, String.valueOf(biometriaBean.getNumeroSolicitud()));
			stmt.setString(2, biometriaBean.getCodError());
			stmt.setString(3, biometriaBean.getDescError());
			stmt.setString(4, biometriaBean.getCodErrorReniec());
			stmt.setString(5, biometriaBean.getDescErrorReniec());
			stmt.setString(6, biometriaBean.getCodPc());
			stmt.setString(7, biometriaBean.getApellidoPaterno());
			stmt.setString(8, biometriaBean.getApellidoMaterno());
			stmt.setString(9, biometriaBean.getNombre());
			stmt.setString(10, biometriaBean.getCodRestriccion());
			stmt.setString(11, biometriaBean.getDescRestriccion());
			stmt.setString(12, "null".equals(biometriaBean.getCodVigencia()) ? null : biometriaBean.getCodVigencia());
			stmt.setString(13, biometriaBean.getDescVigencia());
			stmt.setString(14, biometriaBean.getIp());
			stmt.setString(15, biometriaBean.getMac());
			stmt.setString(16, biometriaBean.getCodTransaccion());			
			
			stmt.registerOutParameter(17, java.sql.Types.VARCHAR);
			stmt.registerOutParameter(18, java.sql.Types.VARCHAR);
			stmt.registerOutParameter(19, java.sql.Types.VARCHAR);
			stmt.registerOutParameter(20, java.sql.Types.VARCHAR);
			stmt.executeUpdate();
			//read the OUT parameter now
			nroDoc = stmt.getString(17);
			
			logger.info("OUTPUT ");
			logger.info("DOC IDENTIDAD " + nroDoc);
			logger.info("ESTADO " + stmt.getString(18));
			logger.info("MSJ TECNICO " + stmt.getString(19));
			logger.info("MSJ FUNCIONAL " + stmt.getString(20));
		}catch(Exception e){
			logger.error("ERROR : " + e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	return nroDoc;	
	}

	public ResultadoBiometriaBean obtenerResultado(BiometriaBean biometriaBean) {
		Connection con = null;
		CallableStatement stmt = null;
		ResultadoBiometriaBean bean = new ResultadoBiometriaBean();
		try{
			logger.info("INPUT ");
			logger.info("NUMERO DOCUMENTO " + biometriaBean.getNumeroDocumento());
			logger.info("CODIGO PROCESO " + biometriaBean.getCodigoProceso());
			con = DBConnection.getConnection();
			stmt = con.prepareCall("{call P_RESULTADO_BIOMETRIA (?,?,?,?,?,?,?)}");
			
			stmt.setString(1, biometriaBean.getCodError());
			stmt.setString(2, biometriaBean.getCodErrorReniec());
			stmt.setString(3, biometriaBean.getCodRestriccion());
			stmt.setString(4, biometriaBean.getCodVigencia());
			
			stmt.registerOutParameter(5, java.sql.Types.VARCHAR);
			stmt.registerOutParameter(6, java.sql.Types.VARCHAR);
			stmt.registerOutParameter(7, java.sql.Types.VARCHAR);
			stmt.executeUpdate();
			//read the OUT parameter now
			bean.setResultado(stmt.getString(5));
			bean.setMensajeResultado(stmt.getString(6));
			bean.setCodigoError(stmt.getString(7));
			
			logger.info("OUTPUT ");
			logger.info(bean.toString());
		}catch(Exception e){
			logger.error("ERROR : " + e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	return bean;
	}

	public BiometriaValidaMaxIntentosBean validarMaximoIntentos(String numeroDocumento) {
		Connection con = null;
		CallableStatement stmt = null;
		BiometriaValidaMaxIntentosBean bean = new BiometriaValidaMaxIntentosBean();
		try{
			logger.info("INPUT ");
			logger.info("NUMERO DOCUMENTO " + numeroDocumento);
			con = DBConnection.getConnection();
			stmt = con.prepareCall("{call P_BIOMETRIA_MAXIMO_INTENTOS (?,?,?,?)}");
			
			stmt.setString(1, numeroDocumento);
			
			stmt.registerOutParameter(2, java.sql.Types.VARCHAR);
			stmt.registerOutParameter(3, java.sql.Types.VARCHAR);
			stmt.registerOutParameter(4, java.sql.Types.VARCHAR);
			stmt.executeUpdate();
			//read the OUT parameter now
			bean.setResultado(stmt.getBoolean(2));
			bean.setMensaje(stmt.getString(3));
			
			logger.info("OUTPUT ");
			logger.info(bean.toString());
		}catch(Exception e){
			logger.error("ERROR : " + e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	return bean;
	}

}
