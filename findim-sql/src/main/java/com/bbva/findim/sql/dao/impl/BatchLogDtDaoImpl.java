package com.bbva.findim.sql.dao.impl;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.expression.ParseException;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.bbva.findim.dom.ParametroBean;
import com.bbva.findim.dom.ParametroCabeceraBean;
import com.bbva.findim.dom.ParametroDetalleBean;
import com.bbva.findim.dom.ProcesoBatchLogDtBean;
import com.bbva.findim.dom.common.Constantes;
import com.bbva.findim.sql.connection.DBConnection;
import com.bbva.findim.sql.dao.BatchLogDao;
import com.bbva.findim.sql.dao.BatchLogDtDao;
import com.bbva.findim.sql.dao.ParametroDao;

@Repository
public class BatchLogDtDaoImpl implements BatchLogDtDao {
	
	@Autowired
	BatchLogDao batchLogDao;

	private static final Logger logger = LogManager.getLogger(BatchLogDtDaoImpl.class);
	
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
	    this.jdbcTemplate = jdbcTemplate;  
	    jdbcTemplate.setResultsMapCaseInsensitive(true);
	}  
	public void setDatasource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.setResultsMapCaseInsensitive(true);
	}

	
	public List<ProcesoBatchLogDtBean> listarDetalleProceso(String cdProceso) {
		// TODO Auto-generated method stub
		List<ProcesoBatchLogDtBean> listaProcesoDetalle=null;
		ProcesoBatchLogDtBean detalleBean=null;
		try{
			listaProcesoDetalle= new ArrayList<ProcesoBatchLogDtBean>();
			List<Map<String, Object>> lista;
			
			lista=jdbcTemplate.queryForList(
				"SELECT * FROM TFINDIM_PROCESO_BATCH_LOG_DT where id_proceso= ? order by nombre_archivo,id_proceso_dt",
				cdProceso
			);

			for(Map<String, Object> row : lista){
				detalleBean= new ProcesoBatchLogDtBean();
				detalleBean.setIdProcesoDt(new BigDecimal(row.get("id_proceso_dt").toString()));
				detalleBean.setTarea(row.get("tarea").toString());
				detalleBean.setPaso(row.get("paso").toString());
				detalleBean.setIdProceso(row.get("id_proceso").toString());
				detalleBean.setNombreArchivo(row.get("nombre_archivo").toString());
				detalleBean.setObsEstado(row.get("obs_estado").toString());
				
				listaProcesoDetalle.add(detalleBean);     
		    }
			logger.info("listaProcesoDetalle.size():"+listaProcesoDetalle.size()); 
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}	
		return listaProcesoDetalle;
	}

}
