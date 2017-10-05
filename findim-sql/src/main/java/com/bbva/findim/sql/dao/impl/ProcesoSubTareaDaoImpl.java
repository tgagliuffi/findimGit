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
import com.bbva.findim.dom.ProcesoSubTareaBean;
import com.bbva.findim.dom.common.Constantes;
import com.bbva.findim.sql.connection.DBConnection;
import com.bbva.findim.sql.dao.BatchLogDao;
import com.bbva.findim.sql.dao.BatchLogDtDao;
import com.bbva.findim.sql.dao.ParametroDao;
import com.bbva.findim.sql.dao.ProcesoSubTareaDao;

@Repository
public class ProcesoSubTareaDaoImpl implements ProcesoSubTareaDao {
	
	private static final Logger logger = LogManager.getLogger(ProcesoSubTareaDaoImpl.class);
	
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
	    this.jdbcTemplate = jdbcTemplate;  
	    jdbcTemplate.setResultsMapCaseInsensitive(true);
	}  
	public void setDatasource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.setResultsMapCaseInsensitive(true);
	}

	public List<ProcesoSubTareaBean> listarSubtareas(String idTarea) {
		// TODO Auto-generated method stub
		List<ProcesoSubTareaBean> listaProcesoSubTarea=null;
		ProcesoSubTareaBean subTareaBean=null;
		try{
			listaProcesoSubTarea= new ArrayList<ProcesoSubTareaBean>();
			List<Map<String, Object>> lista;
			
			lista=jdbcTemplate.queryForList(
				"SELECT * FROM TFINDIM_PROCESO_SUBTAREA where ID_TAREA= ? order by ID_SUBTAREA",
				idTarea
			);
			
			for(Map<String, Object> row : lista){
				subTareaBean= new ProcesoSubTareaBean();
				
				subTareaBean.setIdSubTarea(new BigDecimal(row.get("ID_SUBTAREA").toString()));
				subTareaBean.setIdTarea(row.get("ID_TAREA").toString());
				subTareaBean.setNbSubTarea(row.get("NB_SUBTAREA").toString());
				subTareaBean.setStTarea(row.get("ST_TAREA").toString());
				subTareaBean.setDtSubtarea(row.get("DT_SUBTAREA").toString());
				subTareaBean.setIdPredecesora(row.get("ID_PREDECESORA").toString());
				
				listaProcesoSubTarea.add(subTareaBean);     
		    }
			logger.info("listaProcesoDetalle.size():"+listaProcesoSubTarea.size()); 
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}	
		return listaProcesoSubTarea;
	}

}
