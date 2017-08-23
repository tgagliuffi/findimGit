package com.bbva.findim.sql.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import com.bbva.findim.dom.common.Constantes;
import com.bbva.findim.sql.dao.ParametroDao;

@Repository
public class ParametroDaoImpl implements ParametroDao {

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

	public List<ParametroBean> listarParametrosDetalle(Integer idPadre) {
		List<ParametroBean> listaParametrosDetalle=null;
		ParametroBean parametroBean=null;
		try{
			listaParametrosDetalle= new ArrayList<ParametroBean>();
			List<Map<String, Object>> lista;
			
			lista=jdbcTemplate.queryForList(
				"SELECT cd_paramdetalle,nb_paramdetalle,cd_paramcabecera, nb_valorparamdeta, nb_auxparamdeta, st_paramdetalle "
			    +" FROM tfindim_parametrodetalle where cd_paramcabecera= ? ORDER BY cd_paramdetalle",
			    idPadre
			);

			for(Map<String, Object> row : lista){
				parametroBean= new ParametroBean();
				parametroBean.setCd_paramdetalle(Integer.parseInt(row.get("cd_paramdetalle").toString()));
				parametroBean.setNb_paramdetalle(row.get("nb_paramdetalle").toString());
				
				parametroBean.setCd_paramcabecera(Integer.parseInt(row.get("cd_paramcabecera").toString()));
				parametroBean.setNb_valorparamdeta(
					row.get("nb_valorparamdeta")==null?"":row.get("nb_valorparamdeta").toString()
				);
				parametroBean.setNb_auxparamdeta(
						row.get("nb_auxparamdeta")==null?"":row.get("nb_auxparamdeta").toString()
				);
				listaParametrosDetalle.add(parametroBean);     
		    }
			logger.info("listaParametrosDetalle.size():"+listaParametrosDetalle.size()); 
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}	
		return listaParametrosDetalle;
	}
	
	public ParametroBean obtenerParametroDetalle(Integer idPadre, Integer idParamDetalle) {
		ParametroBean parametroBean = null;
		try{
			
			parametroBean = jdbcTemplate.queryForObject(
				"SELECT cd_paramdetalle,nb_paramdetalle,cd_paramcabecera, nb_valorparamdeta, nb_auxparamdeta, st_paramdetalle "
			    +" FROM tfindim_parametrodetalle WHERE cd_paramcabecera= ? AND cd_paramdetalle = ?",
			    new RowMapper<ParametroBean>() {
					public ParametroBean mapRow(ResultSet rs, int rowNum) throws SQLException {
						ParametroBean bean = new ParametroBean();
						bean.setCd_paramcabecera(rs.getInt("cd_paramcabecera"));
						bean.setCd_paramdetalle(rs.getInt("cd_paramdetalle"));
						bean.setNb_paramdetalle(rs.getString("nb_paramdetalle"));
						bean.setNb_valorparamdeta(rs.getString("nb_valorparamdeta"));
						bean.setNb_auxparamdeta(rs.getString("nb_auxparamdeta"));
						bean.setSt_paramdetalle(rs.getInt("st_paramdetalle"));
						return bean;
					}
				},
			    idPadre, idParamDetalle
			);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}	
		return parametroBean;
	}

	public List<ParametroCabeceraBean> listaDatosParametros() {
		List<ParametroCabeceraBean> listaCabParametro = null;  
		List<ParametroDetalleBean> listaDetParametro = null;
		List<ParametroDetalleBean> listaDetallesParametro = null;
		try {
			logger.debug("PersistenciaBBVA.listaDatosParametros");
			List<Map<String, Object>> listaParametro;
			listaParametro=jdbcTemplate.queryForList(
					" SELECT c.cd_paramcabecera, c.nb_paramcabecera FROM tfindim_parametrocabecera c "
					+" WHERE c.st_paramcabecera = 1  "
					+" AND fl_editable = 1"
					+" ORDER BY c.cd_paramcabecera "
				);
			ParametroCabeceraBean parametroCabeceraBean = null;
			listaCabParametro = new ArrayList<ParametroCabeceraBean>();
			for(Map<String, Object> row : listaParametro){
				parametroCabeceraBean = new ParametroCabeceraBean();
				parametroCabeceraBean.setIdPadre(row.get("cd_paramcabecera")!=null?Integer.valueOf(row.get("cd_paramcabecera").toString()):0);
				parametroCabeceraBean.setNb_paramcabecera(row.get("nb_paramcabecera")!=null?row.get("nb_paramcabecera").toString():"");
				listaCabParametro.add(parametroCabeceraBean);
			}
			List<Map<String, Object>> listaDetalleParametro;
			listaDetalleParametro=jdbcTemplate.queryForList(
					" SELECT d.cd_paramcabecera, d.cd_paramdetalle, d.nb_paramdetalle, d.nb_valorparamdeta, d.nb_auxparamdeta, d.st_paramdetalle "
					+" FROM tfindim_parametrodetalle d "
					+" ORDER BY d.cd_paramcabecera, d.cd_paramdetalle "
				);
			ParametroDetalleBean parametroDetalleBean;
			listaDetParametro = new ArrayList<ParametroDetalleBean>();
			for(Map<String, Object> row : listaDetalleParametro){
				parametroDetalleBean = new ParametroDetalleBean();
				parametroDetalleBean.setCd_paramcabecera(row.get("cd_paramcabecera")!=null?Integer.valueOf(row.get("cd_paramcabecera").toString()):0);
				parametroDetalleBean.setCd_paramdetalle(row.get("cd_paramdetalle")!=null?Integer.valueOf(row.get("cd_paramdetalle").toString()):0);
				parametroDetalleBean.setNb_paramdetalle(row.get("nb_paramdetalle")!=null?row.get("nb_paramdetalle").toString():"");
				parametroDetalleBean.setNb_valorparamdeta(row.get("nb_valorparamdeta")!=null?row.get("nb_valorparamdeta").toString():"");
				parametroDetalleBean.setNb_auxparamdeta(row.get("nb_auxparamdeta")!=null?row.get("nb_auxparamdeta").toString():"");
				parametroDetalleBean.setSt_paramdetalle(row.get("st_paramdetalle")!=null?Integer.valueOf(row.get("st_paramdetalle").toString()):0);
				listaDetParametro.add(parametroDetalleBean);
			}
	        
	        for (int i = 0; i < listaCabParametro.size(); i++) {
	        	listaDetallesParametro = new ArrayList<ParametroDetalleBean>();
	                for (int j = 0; j < listaDetParametro.size(); j++) {
	                    if (listaCabParametro.get(i).getIdPadre() == listaDetParametro.get(j).getCd_paramcabecera()) {
	                    	listaDetallesParametro.add(listaDetParametro.get(j));
	                    }
	                    
	                }
	                listaCabParametro.get(i).setListaParametrosDetalle(listaDetallesParametro);
	        }
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return listaCabParametro;
	}
	
	public ParametroDetalleBean gestionarParametrosDetalle(ParametroDetalleBean parametroDetalleBean){
		
		Integer tipoError = null;
		
		try {
			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.withFunctionName("fn_gestionar_parametria_detalle").withSchemaName("public")

			.declareParameters(new SqlParameter("p_opcion",      Types.INTEGER))
			.declareParameters(new SqlParameter("p_idCabecera",  Types.INTEGER))
			.declareParameters(new SqlParameter("p_idDetalle",   Types.INTEGER))
			.declareParameters(new SqlParameter("p_descripcion", Types.VARCHAR))
			.declareParameters(new SqlParameter("p_valor",       Types.VARCHAR))
			.declareParameters(new SqlParameter("p_auxiliar",    Types.VARCHAR))
			.declareParameters(new SqlParameter("p_estado",      Types.INTEGER))
			
			.declareParameters(new SqlOutParameter("p_respuesta",   Types.NUMERIC))
			.declareParameters(new SqlOutParameter("p_detalle_tec", Types.VARCHAR))
			.declareParameters(new SqlOutParameter("p_detalle_fun", Types.VARCHAR));

			SqlParameterSource in = new MapSqlParameterSource()
			.addValue("p_opcion",      parametroDetalleBean.getOpcion(), Types.INTEGER)
			.addValue("p_idCabecera",  parametroDetalleBean.getCd_paramcabecera(), Types.INTEGER)
			.addValue("p_idDetalle",   parametroDetalleBean.getCd_paramdetalle(), Types.INTEGER)
			.addValue("p_descripcion", parametroDetalleBean.getNb_paramdetalle(), Types.VARCHAR)
			.addValue("p_valor",       parametroDetalleBean.getNb_valorparamdeta(), Types.VARCHAR)
			.addValue("p_auxiliar",    parametroDetalleBean.getNb_auxparamdeta(), Types.VARCHAR)
			.addValue("p_estado",   parametroDetalleBean.getSt_paramdetalle(), Types.INTEGER);

			Map<?, ?> map = simpleJdbcCall.execute(in);
			parametroDetalleBean.setTipoRespuesta(Integer.parseInt(map.get("p_respuesta").toString()));
			
		} catch (InvalidResultSetAccessException e) {
			logger.error(e.getMessage(), e);
			tipoError = Constantes.Error.InvalidResultSetAccessException.getError();
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			tipoError = Constantes.Error.DataAccessException.getError();
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			tipoError = Constantes.Error.ParseException.getError();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			tipoError = Constantes.Error.Exception.getError();
		}
		if (tipoError != null) {
			parametroDetalleBean.setTipoRespuesta(Constantes.TipoRespuestaWS.Error.getTipoRespuestaWS());
			parametroDetalleBean.setTipoError(tipoError);
		}
		return parametroDetalleBean;
	}

//	public List<ParametroDetalleBean> listaTipoDocumentos() {
//		List<ParametroDetalleBean> listaDetParametro=null;
//		Integer st_paramdetalle;
//		Integer cd_paramcabecera;
//		try {
//			List<Map<String, Object>> listaDetalleParametro;
//			st_paramdetalle = Constantes.PARAM_DATOS_TIPOS_DOCUMENTOS;
//			cd_paramcabecera = Constantes.PARAM_ACTIVOS;
//			MapSqlParameterSource namedParameters = new MapSqlParameterSource()
//					.addValue("st_paramdetalle", st_paramdetalle)
//					.addValue("cd_paramcabecera", cd_paramcabecera);
//			listaDetalleParametro=namedParameterJdbcTemplate.queryForList(
//					" select cd_paramdetalle, nb_paramdetalle from tfindim_parametrodetalle where cd_paramcabecera=:cd_paramcabecera"
//					+ "  and st_paramdetalle=:st_paramdetalle order by cd_paramdetalle ", namedParameters );
//			ParametroDetalleBean parametroDetalleBean;
//			listaDetParametro = new ArrayList<ParametroDetalleBean>();
//			for(Map<String, Object> row : listaDetalleParametro){
//				parametroDetalleBean = new ParametroDetalleBean();
//				parametroDetalleBean.setCd_paramdetalle(row.get("cd_paramdetalle")!=null?Integer.valueOf(row.get("cd_paramdetalle").toString()):0);
//				parametroDetalleBean.setNb_paramdetalle(row.get("nb_paramdetalle")!=null?row.get("nb_paramdetalle").toString():"");
//				listaDetParametro.add(parametroDetalleBean);
//			}
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//		}
//		return listaDetParametro;
//	}

}
