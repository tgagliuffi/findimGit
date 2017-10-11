package com.bbva.findim.sql.dao.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bbva.findim.dom.ProcesoBatchLogBean;
import com.bbva.findim.dom.ProcesoTareaBean;
import com.bbva.findim.sql.dao.BatchLogDao;

@Repository
public class BatchLogDaoImpl implements BatchLogDao {

	private static final Logger logger = LogManager.getLogger(BatchLogDaoImpl.class);
	
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
	    this.jdbcTemplate = jdbcTemplate;  
	    jdbcTemplate.setResultsMapCaseInsensitive(true);
	}  
	public void setDatasource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.setResultsMapCaseInsensitive(true);
	}
	public ProcesoBatchLogBean obtenerBatchLogDao(String cdProceso) {
		// TODO Auto-generated method stub
		ProcesoBatchLogBean procesoBatchLogBean = null;
		try{
			
			procesoBatchLogBean = jdbcTemplate.queryForObject(
				"SELECT ID_PROCESO,CD_PROCESO,ST_PROCESO,OB_PROCESO,ID_TP_PROCESO,FH_INI_PROCESO,FH_FIN_PROCESO"
				+ " FROM TFINDIM_PROCESO_BATCH_LOG WHERE cd_proceso= ? ",
			    new RowMapper<ProcesoBatchLogBean>() {
					public ProcesoBatchLogBean mapRow(ResultSet rs, int rowNum) throws SQLException {
						ProcesoBatchLogBean bean = new ProcesoBatchLogBean();
						bean.setIdProceso(rs.getBigDecimal("ID_PROCESO"));
						bean.setCdProceso(rs.getString("CD_PROCESO"));
						bean.setStProceso(rs.getString("ST_PROCESO"));
						return bean;
					}
				},
			    cdProceso
			);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}	
		return procesoBatchLogBean;
	}
	public List<ProcesoBatchLogBean> listarUltimosProcesos(String cantidad) {
		// TODO Auto-generated method stub
		List<ProcesoBatchLogBean> listaProcesoBatch=null;
		ProcesoBatchLogBean batchLogBean=null;
		try{
			listaProcesoBatch= new ArrayList<ProcesoBatchLogBean>();
			
			List<Map<String, Object>> lista;
			
			lista=jdbcTemplate.queryForList(
				"select * from tfindim_proceso_batch_log WHERE fh_ini_proceso >= (SELECT SYSDATE-"+cantidad+" FROM DUAL)");
			
			for(Map<String, Object> row : lista){
				batchLogBean= new ProcesoBatchLogBean();
				
				batchLogBean.setIdProceso(new BigDecimal(row.get("ID_PROCESO").toString()));
				batchLogBean.setCdProceso(row.get("CD_PROCESO").toString());
				batchLogBean.setStProceso(row.get("ST_PROCESO")!=null?row.get("ST_PROCESO").toString():"");
				batchLogBean.setObProceso(row.get("OB_PROCESO")!=null?row.get("OB_PROCESO").toString():"");
				batchLogBean.setIdTpProceso(row.get("ID_TP_PROCESO")!=null?row.get("ID_TP_PROCESO").toString():"");
				batchLogBean.setFhIniProceso(new Date());
				batchLogBean.setFhFinProceso(new Date());
				listaProcesoBatch.add(batchLogBean);     
		    }
			logger.info("listaProcesoDetalle.size():"+listaProcesoBatch.size()); 
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}	
		return listaProcesoBatch;
	}

}
