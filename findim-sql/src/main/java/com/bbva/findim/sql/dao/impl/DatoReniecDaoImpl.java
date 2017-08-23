package com.bbva.findim.sql.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bbva.findim.dom.DatosReniecBean;
import com.bbva.findim.sql.dao.DatoReniecDao;

@Repository
public class DatoReniecDaoImpl implements DatoReniecDao{
	
	private static final Logger logger = LogManager.getLogger(DatoReniecDaoImpl.class);
	
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
	    this.jdbcTemplate = jdbcTemplate;  
	    jdbcTemplate.setResultsMapCaseInsensitive(true);
	}  
	public void setDatasource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.setResultsMapCaseInsensitive(true);
	}

	public DatosReniecBean obtenerDatosReniec(String numeroDni) {
		DatosReniecBean datosReniecBean = null;
		try{
			
			datosReniecBean = jdbcTemplate.queryForObject(
				"SELECT numero_dni,fecha_caducidad,direccion_amdocs "
			    +" FROM tfindim_datos_reniec WHERE numero_dni= ? ",
			    new RowMapper<DatosReniecBean>() {
					public DatosReniecBean mapRow(ResultSet rs, int rowNum) throws SQLException {
						DatosReniecBean bean = new DatosReniecBean();
						bean.setDireccionAmdocs(rs.getString("numero_dni"));
						bean.setFechaCaducidad(rs.getDate("fecha_caducidad"));
						bean.setNumeroDni(rs.getString("direccion_amdocs"));
						return bean;
					}
				},
			    numeroDni
			);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}	
		return datosReniecBean;
	}
	public void guardarDatoReniec(DatosReniecBean datosReniecBean) {
		// TODO Auto-generated method stub
		jdbcTemplate.update("INSERT INTO tfindim_datos_reniec (numero_dni,fecha_caducidad,direccion_amdocs) VALUES (?, ? ,?)", datosReniecBean.getNumeroDni(),datosReniecBean.getFechaCaducidad(),datosReniecBean.getDireccionAmdocs());
	}

}
