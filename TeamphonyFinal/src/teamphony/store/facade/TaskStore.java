package teamphony.store.facade;

import java.util.List;

import javax.servlet.http.HttpSession;

import teamphony.domain.Task;

public interface TaskStore {

	void insertTask(Task task, HttpSession session, String assignmentTitle, int assignmentId);

	void updateTask(Task task , String assignmentTitle);

	void deleteTask(String memberId, int taskId, int flag);

	List<Task> selectAllTaskByFlag(int flag, int teamCode);

	Task selectTaskByTaskId(int taskId);

	List<Task> selectTaskByMemberId(String memberId, int teamCode);
	
	List<Task> selectAllAssginment();
	
}
