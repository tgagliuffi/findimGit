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

import com.bbva.findim.dom.OficinaBean;
import com.bbva.findim.dom.UbigeoBean;
import com.bbva.findim.sql.dao.OficinaDao;
@Repository
public class OficinaDaoImpl implements OficinaDao {
	
	private static final Logger logger = LogManager.getLogger(OficinaDaoImpl.class);
	
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
	    this.jdbcTemplate = jdbcTemplate;  
	    jdbcTemplate.setResultsMapCaseInsensitive(true);
	} 
	public void setDatasource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.setResultsMapCaseInsensitive(true);
	}
	
	public UbigeoBean obtenerUbigeoHost(String dpto, String prov, String dist) throws Exception {
		UbigeoBean ubigeoBean = null;
		try{
		
			ubigeoBean = jdbcTemplate.queryForObject(
				" SELECT ID_UBIGEO,CD_DPTO, NB_DPTO, CD_PROV,NB_PROV,CD_DIST, NB_DIST,UBG_RENIEC,UBG_HOST " +
			    " FROM TFINDIM_UBIGEO WHERE CD_DPTO = ? and CD_PROV = ? and CD_DIST = ? ",
			    new RowMapper<UbigeoBean>() {
					public UbigeoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
						UbigeoBean bean = new UbigeoBean();
						bean.setIdUbigeo(rs.getLong("ID_UBIGEO"));
						bean.setCdDpto(rs.getString("CD_DPTO"));
						bean.setNbDpto(rs.getString("NB_DPTO"));
						bean.setCdProv(rs.getString("NB_PROV"));
						bean.setNbProv(rs.getString("CD_DIST"));
						bean.setCdDist(rs.getString("NB_DIST"));
						bean.setNbDist(rs.getString("UBG_RENIEC"));
						bean.setUbgHost(rs.getString("UBG_HOST"));
						return bean;
					}
				},
			    dpto, prov, dist 
			);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}	
		return ubigeoBean;
	
	}
	
	public List<OficinaBean> listarOficinasPorUbigeoHost(String ubigeoHost, String distrito)throws Exception {
		List<OficinaBean> listaOficinaPorUbigeo = null;
		try{
			listaOficinaPorUbigeo= new ArrayList<OficinaBean>();
			List<Map<String, Object>> lista;
			
			lista=jdbcTemplate.queryForList(
				"SELECT ID_OFICINA, CD_OFICINA, NB_OFICINA, UBIGEO_HOST "
			    +" FROM TFINDIM_OFICINA WHERE UBIGEO_HOST = ? ",
			    ubigeoHost
			);

			for(Map<String, Object> row : lista){
				OficinaBean bean = new OficinaBean();
				bean.setIdOficina(new Long(row.get("ID_OFICINA").toString()));
				bean.setCdOficina(row.get("CD_OFICINA").toString());
				bean.setNbOficina(row.get("NB_OFICINA").toString());
				bean.setCdUbigeoHost(row.get("UBIGEO_HOST").toString());
				listaOficinaPorUbigeo.add(bean);     
		    }
			logger.info("listaParametrosDetalle.size():"+listaOficinaPorUbigeo.size()); 
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}	
		return listaOficinaPorUbigeo;
	
	}

}
