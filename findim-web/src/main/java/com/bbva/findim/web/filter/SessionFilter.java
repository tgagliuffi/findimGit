package com.bbva.findim.web.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.bbva.bean.TokenUtil;

public class SessionFilter implements Filter {

	private static final Logger LOGGER = LogManager.getLogger(SessionFilter.class);

	private static final String ACCESS_DENIED_PAGE = "/sessionExpired";
	private static final String SECURITY_PAGES = "/seguridad/";
	private static final String RESOURCES = "/resources/";
	private static final String LOGIN_PAGE = "/login";

	private boolean isPageExcluded(String pageUri) {
		if (pageUri.contains(ACCESS_DENIED_PAGE)
				|| pageUri.contains(SECURITY_PAGES)
				|| pageUri.contains(RESOURCES)
				|| pageUri.contains(LOGIN_PAGE)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isSessionInvalid(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session == null) {
			LOGGER.info("Sesion invalida");
			return true;
		}
		String userType = (String) session.getAttribute("seguridad_usertype");
		boolean sessionInvalid = userType == null;
		if (sessionInvalid) {
			LOGGER.info("Sesion invalida");
		}
		return sessionInvalid;
	}

	private boolean isAjaxRequest(HttpServletRequest request) {
		String xRequestWith = request.getHeader("X-Requested-With");
		LOGGER.trace("xRequestWith: " + xRequestWith);

		if ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))) {
			return true;
		}
		return false;
	}

	public boolean isTokenInvalid(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session == null) {
			LOGGER.info("Session invalida");
			return true;
		}
		String tokenCf = (String) session.getAttribute("token_cf");
		String tokenTdp = (String) session.getAttribute("token_tdp");

		if (TokenUtil.validateTokenCF(tokenTdp, tokenCf)) {
			LOGGER.debug("Token Valido");
			return false;
		} else {
			LOGGER.info("Token Invalido");
			return true;
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");

		if ((request instanceof HttpServletRequest) && (response instanceof HttpServletResponse)) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;

			String pageUri = httpServletRequest.getRequestURI();
			LOGGER.debug("pageUri: " + pageUri);
			if (!isPageExcluded(pageUri) &&
					(isSessionInvalid(httpServletRequest) || isTokenInvalid(httpServletRequest))) {

				LOGGER.info("Acceso denegado!... redirigiendo...");
				final String redirectPage = httpServletRequest.getContextPath() + ACCESS_DENIED_PAGE;

				if (isAjaxRequest(httpServletRequest)) {
					String jsonError = "{ \"hasError\": true, \"message\" : \"Acceso Denegado\" }";
					httpServletResponse.setHeader("Cache-Control", "no-cache");
					httpServletResponse.setCharacterEncoding("UTF-8");
					httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
					httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
					PrintWriter pw = response.getWriter();
					pw.println(jsonError);
					pw.flush();
					return;
				}
				
				httpServletResponse.sendRedirect(redirectPage);
				return;
			}
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}
}
