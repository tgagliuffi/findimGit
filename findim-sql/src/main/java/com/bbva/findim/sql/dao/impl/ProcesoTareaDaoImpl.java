package com.bbva.findim.sql.dao.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bbva.findim.dom.ProcesoTareaBean;
import com.bbva.findim.sql.dao.ProcesoTareaDao;

@Repository
public class ProcesoTareaDaoImpl implements ProcesoTareaDao {

	private static final Logger logger = LogManager.getLogger(ProcesoTareaDaoImpl.class);
	
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
	    this.jdbcTemplate = jdbcTemplate;  
	    jdbcTemplate.setResultsMapCaseInsensitive(true);
	}  
	public void setDatasource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.setResultsMapCaseInsensitive(true);
	}
	public ProcesoTareaBean obtenerProcesoTareaDao(String idTarea) {
		ProcesoTareaBean procesoTareaBean = null;
		try{
			procesoTareaBean = jdbcTemplate.queryForObject(
				"SELECT ID_TAREA,NB_TAREA,ST_TAREA,FH_INI_TAREA,FH_FIN_TAREA,CD_TAREA,ID_PREDECESORA,EXPRE_CRON"
				+ " FROM TFINDIM_PROCESO_TAREA WHERE ID_TAREA= ? ",
			    new RowMapper<ProcesoTareaBean>() {
					public ProcesoTareaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
						ProcesoTareaBean bean = new ProcesoTareaBean();
						bean.setIdTarea(rs.getBigDecimal("ID_TAREA"));
						bean.setNbTarea(rs.getString("NB_TAREA"));
						bean.setStTarea(rs.getString("ST_TAREA"));
						bean.setFhIniTarea(rs.getString("FH_INI_TAREA"));
						bean.setFhFinTarea(rs.getString("FH_FIN_TAREA"));
						bean.setCdTarea(rs.getString("CD_TAREA"));
						bean.setIdPredecesora(rs.getString("ID_PREDECESORA"));
						bean.setExpreCron(rs.getString("EXPRE_CRON"));
						
						return bean;
					}
				},
			    idTarea
			);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}	
		return procesoTareaBean;
	}
	
	
	public List<ProcesoTareaBean> listarTareas() {
		
		
		List<ProcesoTareaBean> listaTareas=null;
		ProcesoTareaBean tareaBean=null;
		try{
			listaTareas= new ArrayList<ProcesoTareaBean>();
			
			List<Map<String, Object>> lista;
			
			lista=jdbcTemplate.queryForList(
				"select * from tfindim_proceso_tarea");
			
			for(Map<String, Object> row : lista){
				tareaBean= new ProcesoTareaBean();
				
				tareaBean.setIdTarea(new BigDecimal(row.get("ID_TAREA").toString()));
				tareaBean.setNbTarea(row.get("NB_TAREA").toString());
				tareaBean.setStTarea(row.get("ST_TAREA").toString());
				tareaBean.setCdTarea(row.get("CD_TAREA").toString());
				tareaBean.setIdPredecesora(row.get("ID_PREDECESORA")!=null?row.get("ID_PREDECESORA").toString():"");
//				batchLogBean.setFhIniProceso(new Date());
//				batchLogBean.setFhFinProceso(new Date());
				listaTareas.add(tareaBean);     
		    }
			logger.info("listaTareas.size():"+listaTareas.size()); 
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}	
		return listaTareas;
	}

}
