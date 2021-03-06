package teamphony.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import teamphony.domain.Task;
import teamphony.domain.TaskFile;
import teamphony.domain.TaskMember;

public interface TaskMapper {

//수정했음
	void insertAssignment(Task task);
//추가했음
	void insertSubmission(Task task);
	
	void insertTaskFile(TaskFile taskFile);
	
	void insertTaskMemberForAssignment(@Param("taskId") int taskId, @Param("memberId") String memberId, @Param("assignmentTitle") String assignmentTitle);
	
	void updateTaskMemberForSubmission(@Param("assignmentId") int assignmentId, @Param("loginedMemberId") String loginedMemberId, @Param("submissionId") int submissionId);

	void updateTask(Task task);
//	추가했음
	void updateTaskPoint(Task task);
	
	void updateTaskMemberForSubmissionDelete(@Param("memberId") String memberId, @Param("taskId") int taskId);
//	추가했음
	void deleteTask(int taskId);
//	추가 했음
	void deleteTaskMember(int taskId);
//	 추가했음
	void deleteTaskFile(int taskId);
//	추가 했음
	void deleteMemberIdByTaskId(int taskId);
//	변경했음
	List<Task> selectAllTaskByFlag(@Param("flag") int flag, @Param("teamCode") int teamCode);
//	추가했음
	List<TaskFile> selectAllFileList();
//	추가 했음
	List<TaskFile> selectFileListByTaskId(int taskId);
// 추가 했음
	List<TaskMember> selectTaskMemberByAssignmentId(int taskId);
// 추가 했음	
	List<TaskMember> selectTaskMemberBySubmissionId(int taskId);
//	추가 했음
	String[] selectMemberIdByAssignmentId(int assignmentId);
//	추가 했음
	String[] selectMemberIdBySubmissionId(int submissionId);
	
	Task selectTaskByTaskId(int taskId);
//추가 했음
	List<Integer> selectAssignmentIdByMemberId(String memberId);
//추가 했음
	List<Integer> selectSubmissionIdByMemberId(String memberId);
	
	List<Task> selectAllAssginment();
	
	List<String> selectAssignmentTitleBySubmissionId(int submissionId);
//추가 했음	
	List<Task> selectMembersTaskByMemberId(@Param("memberId") String memberId, @Param("teamCode") int teamCode);
	
	
}