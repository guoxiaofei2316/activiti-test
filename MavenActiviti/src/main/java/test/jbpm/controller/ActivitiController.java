package test.jbpm.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.task.Task;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import test.jbpm.service.IActivitiService;

@Controller
@RequestMapping("/activiti")
public class ActivitiController extends BaseController {

	private Logger log =Logger.getLogger(ActivitiController.class);
	
	@Autowired
	private IActivitiService activitiService;
	
	@RequestMapping("/deployment")
	public void deployment(HttpServletRequest request,HttpServletResponse response){
		try {
			activitiService.deployment();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/createProcess")
	public ModelAndView createProcess(HttpServletRequest request,HttpServletResponse response){
		return new ModelAndView("/index/crtate",null);
	}
	
	@RequestMapping("/startProcess")
	public void startProcess(HttpServletRequest request,HttpServletResponse response){
		try {
			String name = request.getParameter("name");
			String remark = request.getParameter("remark");
			String userId = super.getUserId(request);
			activitiService.startProcess(userId,name,remark);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/findAllTask")
	public ModelAndView findAllTask(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		ModelAndView mav = null;
		
		try {
			activitiService.historyTask("admin");
			String userId = super.getUserId(request);
			List<Task> taskList = activitiService.selectTaskByUserId(userId);
			model.put("taskList", taskList);
			for (Task task : taskList) {
				System.err.println("流程实例每次："+task.getName());
				System.err.println("流程创建时间："+task.getCreateTime());
				System.err.println("流程实例每次："+task.getAssignee());
			}
			mav = new ModelAndView("/index/mytasklist",model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	
	@RequestMapping("/completTaskById")
	public ModelAndView completTaskById(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model){
		ModelAndView mav = null;
		String taskId = request.getParameter("taskId");
		String nextUserId = request.getParameter("nextUserId");		
		try {
			String userId = super.getUserId(request);
			activitiService.completTaskById(taskId, nextUserId);
			return new ModelAndView("redirect:/activiti/findAllTask?userId="+userId,model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mav;
	}
}
