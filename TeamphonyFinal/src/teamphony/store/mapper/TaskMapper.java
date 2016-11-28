package teamphony.store.mapper;

import java.util.List;

import teamphony.domain.Task;
import teamphony.domain.TaskFile;

public interface TaskMapper {

	
	void insertTask(Task task);
	
	void insertTaskFile(TaskFile task);

	void updateTask(Task task);

	void deleteTask(int taskId);

	List<Task> selectAllTask();

	Task selectTaskByTaskId(int taskId);

	List<Task> selectTaskByMemberId(String memberId);
	
}
