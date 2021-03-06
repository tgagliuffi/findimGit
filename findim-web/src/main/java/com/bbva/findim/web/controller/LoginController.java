package com.bbva.findim.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bbva.bean.Encryptor;
import com.bbva.bean.TokenCFInfo;
import com.bbva.bean.TokenUtil;
import com.bbva.findim.dom.util.PropertyUtil;

@Controller
public class LoginController {

	private static final Logger LOGGER = LogManager.getLogger(LoginController.class);

	private static final String SESSION_EXPIRED_REDIRECT = "redirect:sessionExpired";

	@Autowired
	private PropertyUtil prop;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String  login(
			@RequestParam(value = "tokenId") String tokenId,
			HttpServletRequest request,
			HttpServletResponse response) {

		String token = tokenId.replace(" ", "+"); // Los '+' se pierden en la url 

		String pagina = "index";
		try {
    		LOGGER.debug("Inicio - login");
			String urlValidate = prop.getString("urlValidate");
			LOGGER.debug("urlValidate: " + urlValidate);
			TokenCFInfo tokenInfo = new TokenCFInfo();
			if (TokenUtil.validateToken(urlValidate, token, tokenInfo)) {
				HttpSession  sesion = request.getSession(false); 
				if (sesion == null) {
					sesion = request.getSession(true);
				}

				sesion.setAttribute("seguridad_user", tokenInfo.getUser());
				sesion.setAttribute("seguridad_usertype", tokenInfo.getUserType());
				sesion.setAttribute("seguridad_channel", tokenInfo.getChannel());
				LOGGER.info("Login Ok: " + tokenInfo.getUser() + "|" + tokenInfo.getUserType() + "|" + tokenInfo.getChannel());
				/**********************************************************/
				/*    Una vez validado se puede cambiar la expiracion     */
				/*    antes de agregarlo a la cabecera                    */
				/**********************************************************/
				Long expiracion = prop.getLong("token.expiracion");

				if (expiracion == null) {
					tokenInfo.setExpiration(1000 * 60 * 60); //60 minutos de expiracion por defecto
				} else {
					tokenInfo.setExpiration(expiracion);
				}
 
				sesion.setAttribute("token_cf", "{" + Encryptor.encrypt(tokenInfo.getTokenTDP().substring(0, 16), tokenInfo.getTokenTDP().substring(0, 16), tokenInfo.getTokenForm()) + "}");
				sesion.setAttribute("token_tdp", tokenInfo.getTokenTDP());
				LOGGER.debug("Expiracion del token: " + tokenInfo.getExpiration());
				pagina = "redirect:/";
			} else {
				LOGGER.info("Token no valido, redirgiendo a sessionExpired...");
				pagina = SESSION_EXPIRED_REDIRECT;
			}
		} catch (Exception e) {
			LOGGER.error("Fallo la validacion de token", e);
			pagina = SESSION_EXPIRED_REDIRECT;
		}
		return pagina;
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.setAttribute("seguridad_usertype", null);
		session.invalidate();
		return SESSION_EXPIRED_REDIRECT;
	}

}
