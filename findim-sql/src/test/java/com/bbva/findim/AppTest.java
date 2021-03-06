package com.bbva.findim;


import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bbva.findim.dom.ParametroBean;
import com.bbva.findim.sql.service.ParametroService;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-test.xml")
@Ignore
public class AppTest 
   
{
	static final Logger logger = LogManager.getLogger(Logger.class.getName());
	
	
	@Autowired
	ParametroService parametroService;
	
	@Test
    public void suite(){
		
		try {
			
			List<ParametroBean> listaParametroBean = parametroService.listarParametrosDetalle(19);
			for (ParametroBean parametroBean : listaParametroBean) {
				System.out.println("prueba " + parametroBean.getNb_auxparamdeta());
				System.out.println("prueba " + parametroBean.getNb_paramdetalle());
				System.out.println("prueba " + parametroBean.getNb_valorparamdeta());
				System.out.println("prueba " + parametroBean.getCd_paramcabecera());
				System.out.println("prueba " + parametroBean.getCd_paramdetalle());
				System.out.println("prueba " + parametroBean.getSt_paramdetalle());
			} 
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Sucedio un error");
		}
		
	}
}
