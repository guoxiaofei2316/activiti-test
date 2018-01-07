package test.jbpm.service;

import java.util.List;

import org.activiti.engine.task.Task;

public interface IActivitiService {
	
	/**
	 * @discription 发布流程
	 * @throws Exception
	 * @author admin       
	 * @created 2018年1月7日 上午1:13:08
	 */
	void deployment() throws Exception;
	
	/**
	 * @discription 启动流程实例
	 * @throws Exception
	 * @author admin       
	 * @created 2018年1月7日 上午1:28:09
	 */
	void startProcess(String userId,String name,String remark) throws Exception;
	
	/**
	 * @discription 查询个人任务
	 * @throws Exception
	 * @author admin       
	 * @created 2018年1月7日 下午1:15:42
	 */
	List<Task> selectTaskByUserId(String userId) throws Exception;
	
	/**
	 * @discription 完成任务
	 * @param taskId
	 * @param nextUserId
	 * @author admin       
	 * @created 2018年1月7日 下午1:46:24
	 */
	void completTaskById(String taskId,String nextUserId) throws Exception;
	
	
	List<Task> historyTask(String userId) throws Exception;
}
