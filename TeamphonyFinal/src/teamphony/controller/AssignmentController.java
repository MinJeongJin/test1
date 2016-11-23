package teamphony.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import teamphony.domain.Task;
import teamphony.service.facade.TaskService;

@Controller
@RequestMapping("assignment")
public class AssignmentController {

	@Autowired
	private TaskService service;

	@RequestMapping("/createAssignment.do")
	public String createAssignment(Task task) {

		return null;
	}

	@RequestMapping("/reviseAssignment.do")
	public String reviseAssignment(Task task) {

		return null;
	}

	@RequestMapping("/eraseAssignment.do")
	public String eraseAssignment(Task task) {

		return null;
	}

	@RequestMapping("/evaluate.do")
	public String evaluateAssignment(Task task, int starPoint) {

		return null;
	}
	
	
	
	/*[contextPath]/assignment/searchAssignmentByAssignmentId.do
		contextPath = /TeamphonyFinal - > 서버 - > 모듈텝 - > 그리드(Path) */
	@RequestMapping("/searchByAssignmentId.do")
	public String searchAssignmentByAssignmentId(int taskId, Model model) {
		
		taskId =1;
		Task task = service.findTaskByTaskId(taskId);
		model.addAttribute(task);

		return "/assignmentDetail";
	}
	

	@RequestMapping("/searchAll.do")
	public String searchAllAssignment(Model model) {
		
		List<Task> list =service.findAllTask();
		model.addAttribute(list);
		
		System.out.println(list);
		return "/task/assignment/assignmentList";
	}

	
	
	
}