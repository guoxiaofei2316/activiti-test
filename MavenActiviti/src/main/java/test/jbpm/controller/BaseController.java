package test.jbpm.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;


public class BaseController {

	private Logger log =Logger.getLogger(BaseController.class);
	
	public String getUserId(HttpServletRequest request) throws Exception{
		String userId = (String) request.getSession().getAttribute("userId");
		return userId;
	}
}
