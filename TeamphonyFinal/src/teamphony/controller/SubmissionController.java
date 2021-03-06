package teamphony.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import sun.security.action.GetBooleanAction;
import teamphony.domain.Member;
import teamphony.domain.Task;
import teamphony.domain.TaskFile;
import teamphony.domain.TaskMember;
import teamphony.domain.Team;
import teamphony.service.facade.TaskService;
import teamphony.service.facade.TeamService;
import teamphony.util.MediaUtils;
import teamphony.util.UploadFileUtils;

@Controller
@RequestMapping("submission")
public class SubmissionController {
	
	@Autowired
	private TaskService service;
	@Autowired
	private TeamService teamService;
	private static final Logger logger = LoggerFactory.getLogger(SubmissionController.class);

//	private static final String filePathOnly = "c:/upload";

	
	@RequestMapping("/create.do")
	public String createSubmission(String assignmentTitle, String assignmentId, Model model) {
		
		Task task = service.findTaskByTaskId(Integer.parseInt(assignmentId));
		
		model.addAttribute("task",task);
		
		return "/task/submission/submissionRegister";
	}
	
	@RequestMapping(value = "/create.do", method = RequestMethod.POST)
	public String createSubmission(HttpServletRequest request
									,HttpSession httpSession,
									@RequestParam("attchFile") MultipartFile[] attchFileList
									,String title
									,String contents
									,String flag
									,String assignmentTitle
									,String assignmentId
									,Model model
									,String committed) { 
		
		String uploadPath = request.getSession().getServletContext().getRealPath("/");
		
		TaskMember taskMember= new TaskMember();
		taskMember.setCommitted(Integer.parseInt(committed));

		
		List<TaskMember> taskMemberList = new ArrayList<>();
		taskMemberList.add(taskMember);
		
		Task task = new Task();
		
		task.setTitle(title);
		task.setContents(contents);
		task.setFlag(Integer.parseInt(flag));
		task.setTeamCode((int)httpSession.getAttribute("teamCode"));
		task.setTaskFileList(new ArrayList<>());
		
		
		
		System.out.println("=========submissionController// create.do==============");

		// 첨부 파일 List파일저장 , TASKFILE_TB 저장
		
		for (MultipartFile file : attchFileList) {
			TaskFile taskFile = new TaskFile();
			
			try {
				
				String saveName = uploadFile(file.getOriginalFilename(), file.getBytes(), request);
				
				System.out.println("saveName= " + saveName);
				
				int lastIdx = saveName.lastIndexOf('_')+1;
				saveName = saveName.substring(lastIdx, saveName.length());
				
				taskFile.setFilePath(saveName);
				task.getTaskFileList().add(taskFile);
				
				System.out.println("=================setFilePath!!=======================");
				System.out.println("getFilePath= "+ taskFile.getFilePath());
				System.out.println("==============forEach  End!===========================");
				
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("task.getTaskFileList().size= "+ task.getTaskFileList().size());
		
		for(TaskFile file : task.getTaskFileList()){
			System.out.println("filePath= "+ file.getFilePath());
			
		}
		service.registerTask(task, httpSession, assignmentTitle, Integer.parseInt(assignmentId)); // task_tb 저장
		
		return "redirect:searchAll.do";
	}
	
	
	private static String uploadFile(String originalName
			,byte[] fileData
			,HttpServletRequest request) throws Exception{

		String uploadPath = request.getSession().getServletContext().getRealPath("/");
		
		UUID uid = UUID.randomUUID();
		
		String uploadedFileName = null;
		
		if ( originalName != null ){
		
		String savedName  = uid.toString() + "_" + originalName;
		
		String savedPath = calcPath(uploadPath);
		
		File target = new File(uploadPath+ savedPath, savedName);
		
		FileCopyUtils.copy(fileData, target);
		
		String formatName = originalName.substring(originalName.lastIndexOf(".")+1);
		
		uploadedFileName = makeIcon(uploadPath, savedPath, savedName);
		
		}

		return uploadedFileName;
		}
	
	
	
	private static String calcPath(String uploadPath){
		Calendar cal= Calendar.getInstance();
		
		String yearPath =File.separator+ cal.get(Calendar.YEAR);
		
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
		
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		
		makeDir(uploadPath, yearPath, monthPath, datePath );
		
		logger.info(datePath);
		
		return datePath;
	}
	
	private static void makeDir (String uploadPath, String... paths){
		
		if(new File(paths[paths.length-1]).exists()){
			return;
		}
		
		for(String path : paths){
			File dirPath = new File(uploadPath + path);
			
			if(! dirPath.exists() ){
				dirPath.mkdir();
			}
		}
	}
	
	
	
	private static String makeIcon(String uploadPath, String path, String fileName) throws Exception{
		
		String iconName = uploadPath + path + File.separator + fileName;
		
		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');		
	}
	

	

	@RequestMapping("/revise.do")
	public String reviseSubmission(int taskId, Model model) {

		Task task = service.findTaskByTaskId(taskId);
		model.addAttribute("task", task);

		return "task/submission/submissionModify";
	}

	@RequestMapping(value = "/revise.do", method = RequestMethod.POST)
	public String reviseSubmission(HttpServletRequest request
									,HttpSession httpSession,
									@RequestParam("attchFile") MultipartFile[] attchFileList
									,String taskId
									,String title
									,String contents
									,String flag
									,String assignmentTitle) {
		
		String filePathOnly1 = request.getSession().getServletContext().getRealPath("/");
		
		Task task = new Task();
		
		task.setTaskId(Integer.parseInt(taskId));
		task.setTitle(title);
		task.setContents(contents);
		task.setFlag(Integer.parseInt(flag));
		
		// 첨부 파일 List파일저장 , TASKFILE_TB 저장
		List<TaskFile> taskFileList = new ArrayList<TaskFile>();
		for (MultipartFile attchFile : attchFileList) {
			
			String filePath = filePathOnly1  + attchFile.getOriginalFilename();
			
			if(filePath != filePathOnly1 ){
				
				TaskFile taskFile = new TaskFile(filePath);
				File f = new File(filePath);
				
				try {
					
					attchFile.transferTo(f);
					taskFileList.add(taskFile);
					
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			}
		task.setTaskFileList(taskFileList);
		
		service.modifyTask(task,assignmentTitle);
		; // task_tb 저장
		return "redirect:searchAll.do";
	}

	@RequestMapping("/erase.do")
	public String eraseSubmission(String memberId, String taskId, String flag) {
		
		System.out.println("taskId= "+ Integer.parseInt(taskId)+ "\n" + "flag= "+ Integer.parseInt(flag));
		service.removeTask(memberId, Integer.parseInt(taskId),Integer.parseInt(flag));

		return "redirect:searchAll.do";
	}

	@RequestMapping("/searchByTaskId.do")
	public String searchSubmissionByTaskId(String taskId, Model model) {
		
		Task task = service.findTaskByTaskId(Integer.parseInt(taskId));
		model.addAttribute("task", task);
		
		return "/task/submission/submissionDetail";
	}

	@RequestMapping("/searchAll.do")
	public String searchAllSubmission(HttpSession httpSession, Model model) {
		
		List<Task> taskList = service.findAllTaskByFlag(1, (int)httpSession.getAttribute("teamCode"));
		
		model.addAttribute("taskList", taskList);
		
		for(Task task : taskList){
			
			for(TaskFile taskFile : task.getTaskFileList()){
				
				taskFile.setFilePath(taskFile.getFilePath());
			}
		}
		
		return "/task/submission/submissionList";
	}
	
	@RequestMapping("/evaluate.do")
	public String evaluateAssignment(HttpSession httpSession
									, String loginedId
									, String taskId
									, Model model){
		Team team = new Team();
		team = teamService.findTeamByTeamCode((int)httpSession.getAttribute("teamCode"));
		
		if( !loginedId.equals(team.getLeaderId()) ){
			return "common/leaderError";
		}
		
		model.addAttribute("task", service.findTaskByTaskId(Integer.parseInt(taskId)));
		return "/task/submission/submissionEvaluate";
	}

	@RequestMapping(value="/evaluate.do", method=RequestMethod.POST)
	public String evaluateAssignment(String taskId
									,String point
									,String evaluated
									,String evaluationCnt
									,String assignmentTitle) {
		
		Task task = service.findTaskByTaskId(Integer.parseInt(taskId));
		
		task.setPoint(Integer.parseInt(point));
		task.setEvaluationCnt(Integer.parseInt(evaluationCnt));
		
		task.setEvaluated(Integer.parseInt(evaluated));
		service.modifyTask(task, assignmentTitle);
		
		return "redirect:searchAll.do";
	}
}
