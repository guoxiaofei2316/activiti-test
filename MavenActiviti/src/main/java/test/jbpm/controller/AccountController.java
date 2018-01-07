package test.jbpm.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import test.jbpm.api.MesUser;
import test.jbpm.service.IPersonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/account")
public class AccountController extends BaseController {

	private Logger log =Logger.getLogger(AccountController.class);
	
	@Autowired
	private IPersonService personService;
	
	@RequestMapping("/toLogin")
	public ModelAndView toLogin(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		ModelAndView mav = new ModelAndView("index/login", model);
		return mav;
	}
	
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		String userName = request.getParameter("userName");
//		String passwords = request.getParameter("passwords");
//		MesUser user = null;
		request.getSession().setAttribute("userId", userName);
		ModelAndView mav =  new ModelAndView("index/index", model);
		/*if(user != null){
			if(user.getUserPassword().equals(passwords)){
				request.getSession().setAttribute("userId", userName);
				return new ModelAndView("index/index", model);
			}else{
				model.put("error", "密码错误");
			}
		}else{
			model.put("error", "用户名或密码错误");
		}*/
//		ModelAndView mav = new ModelAndView("forward:/jbpm/toLogin", model);
		return mav;
	}
	
	
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		ModelAndView mav = new ModelAndView("index/index", model);
		return mav;
	}
	
	@RequestMapping("/left")
	public ModelAndView left(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		ModelAndView mav = new ModelAndView("index/left", model);
		return mav;
	}
	
	@RequestMapping("/loginout")
	public void loginout(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model) throws IOException{
		request.getSession().invalidate();
		 response.sendRedirect("/account/toLogin");  
	}
}
