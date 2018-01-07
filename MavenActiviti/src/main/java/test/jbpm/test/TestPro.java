package test.jbpm.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class TestPro {
	
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private ManagementService managementService;
	
	@Test
	public void startProcess(){
		try {
			/*Map<String, Object> variables = new HashMap<>(); // 定义一个Map来存放流程变量
			variables.put("userId","xiaobai2");*/
			ProcessInstance startProcessInstanceByKey = runtimeService.startProcessInstanceByKey("leave");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testJava(){
		String userId = "xiaobai1";
		List<Task> taskList = taskService.createTaskQuery().taskAssignee(userId).list();
		for (Task task : taskList) {
			System.err.println("流程实例每次："+task.getName());
			System.err.println("流程创建时间："+task.getCreateTime());
			System.err.println("流程实例每次："+task.getName());
			System.err.println("流程实例ID："+task.getId());
		}
	}
	
	@Test
	public void completTaskById() throws Exception {
		String nextUserId = "xiaobai1";
		String taskId = "2504";
		/*Map<String, Object> variables = new HashMap<>(); // 定义一个Map来存放流程变量
		variables.put("userId",nextUserId);*/
		taskService.complete(taskId);
	}
}
