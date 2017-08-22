package com.bbva.findim.sql.dao.impl;

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

import com.bbva.findim.dom.EmpresaBean;
import com.bbva.findim.dom.EmpresaDetalle;
import com.bbva.findim.sql.dao.EmpresaDao;

@Repository
public class EmpresaDaoImpl implements EmpresaDao {

	private static final Logger logger = LogManager.getLogger(ParametroDaoImpl.class);
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
	    this.jdbcTemplate = jdbcTemplate;  
	    jdbcTemplate.setResultsMapCaseInsensitive(true);
	}  
	public void setDatasource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.setResultsMapCaseInsensitive(true);
	}

	public EmpresaBean obtenerEmpresa(String cdEmpresa) throws Exception {
		EmpresaBean empresaBean = null;
		try{
		
				empresaBean = jdbcTemplate.queryForObject(
				"SELECT ID_EMPRESA, CD_EMPRESA, NB_EMPRESA, NB_IDENT_EMPRESA, FH_VIGENCIA, ST_ESTADO "
			    +" FROM TFINDIM_EMPRESA WHERE CD_EMPRESA = ? AND ST_ESTADO = 'A' ",
			    new RowMapper<EmpresaBean>() {
					public EmpresaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
						EmpresaBean bean = new EmpresaBean();
						bean.setIdEmpresa(rs.getLong("ID_EMPRESA"));
						bean.setCdEmpresa(rs.getString("CD_EMPRESA"));
						bean.setNombreEmpresa(rs.getString("NB_EMPRESA"));
						bean.setIndenticador(rs.getString("NB_IDENT_EMPRESA"));
						bean.setFechaInscripcion(rs.getString("FH_VIGENCIA"));
						bean.setFechaExpiracion(rs.getString("FH_VIGENCIA"));
						return bean;
					}
				},
			    cdEmpresa 
			);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}	
		return empresaBean;
	}
	
	public List<EmpresaDetalle> listarEmpresa(Long idEmpresa, String cdEmpresa)throws Exception {
		List<EmpresaDetalle> listaEmpresaDetalle = null;
		EmpresaDetalle empresaDetalle=null;
		try{
			listaEmpresaDetalle= new ArrayList<EmpresaDetalle>();
			List<Map<String, Object>> lista;
			
			lista=jdbcTemplate.queryForList(
				"SELECT ID_EMP_CONF, NB_PARAMETRO, NB_VALOR "
			    +" FROM TFINDIM_EMPRESA_CONFIGURACION WHERE ID_EMPRESA = ? AND  ST_ESTADO = 'A' ORDER BY NB_PARAMETRO",
			    idEmpresa
			);

			for(Map<String, Object> row : lista){
				empresaDetalle= new EmpresaDetalle();
				empresaDetalle.setCdEmpresa(cdEmpresa);
				empresaDetalle.setIdEmpresaConfiguracion(new Long(row.get("ID_EMP_CONF").toString()));
				empresaDetalle.setNbParametro(row.get("NB_PARAMETRO").toString());
				empresaDetalle.setNbValue(row.get("NB_VALOR").toString());
				listaEmpresaDetalle.add(empresaDetalle);     
		    }
			logger.info("listaParametrosDetalle.size():"+listaEmpresaDetalle.size()); 
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}	
		return listaEmpresaDetalle;
	}

}
