package test.jbpm.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import test.jbpm.service.IActivitiService;

@Service("activitiService")
public class ActivitiServiceImpl implements IActivitiService {

	private Logger log =Logger.getLogger(ActivitiServiceImpl.class);
	
	@Autowired
	private IdentityService identityService;
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
	
	@Override
	public void deployment() throws Exception {
		repositoryService.createDeployment().addClasspathResource("activiti/leave.bpmn").deploy();
	}
	
	
	public void startProcess(String userId,String name,String remark) throws Exception {
		Map<String, Object> variables = new HashMap<String, Object>(); // 定义一个Map来存放流程变量
		variables.put("startUserId","admin");
		variables.put("name",name);
		variables.put("remark",remark);
		//流程发起前设置发起人，记录在流程历史中
		identityService.setAuthenticatedUserId(userId);
		ProcessInstance startProcessInstanceByKey = runtimeService.startProcessInstanceByKey("leave",variables);
		System.err.println(startProcessInstanceByKey.getId());
		System.err.println(startProcessInstanceByKey.getId());
		System.err.println(startProcessInstanceByKey.getId());
		System.err.println(startProcessInstanceByKey.getId());
	}


	@Override
	public List<Task> selectTaskByUserId(String userId) throws Exception {
		 return taskService.createTaskQuery().taskAssignee(userId).list();
		
	}
	
	@Override
	public void completTaskById(String taskId,String nextUserId) throws Exception {
		Map<String, Object> variables = new HashMap<String, Object>(); // 定义一个Map来存放流程变量
		variables.put("userId",nextUserId);
		taskService.complete(taskId, variables);
	}
	@Override
	public List<Task> historyTask(String userId) throws Exception {
		HistoricProcessInstanceQuery createHistoricProcessInstanceQuery = historyService.createHistoricProcessInstanceQuery();
		HistoricProcessInstanceQuery startedBy = createHistoricProcessInstanceQuery.startedBy(userId);
		List<HistoricProcessInstance> listPage = startedBy.list();
		if(!CollectionUtils.isEmpty(listPage)){
			for(HistoricProcessInstance page : listPage){
				
			}
			
		}
		List<ProcessInstance> list2 = runtimeService.createProcessInstanceQuery().processInstanceId("2501").list();
		HistoricTaskInstanceQuery processInstanceId = historyService.createHistoricTaskInstanceQuery().processInstanceId("5");
		List<HistoricTaskInstance> list = processInstanceId.finished().list();
		//String processDefinitionId = createHistoricProcessInstanceQuery.startedBy(userId).singleResult().getProcessDefinitionId();
//		System.err.println(singleResult.getStartUserId());
		return null;
	}

}
