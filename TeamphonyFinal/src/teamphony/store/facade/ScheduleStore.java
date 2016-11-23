package teamphony.store.facade;

import java.sql.Date;
import java.util.List;

import teamphony.domain.Schedule;

public interface ScheduleStore {

	void insertSchedule(Schedule schedule);
	void updateSchedule(Schedule schedule);
	void deleteSchedule(int scheduleId);
	List<Schedule> selectSchedulesByTeamCode(String teamCode);
	List<Schedule> selectSchedulesByDate(Date startDate);
	Schedule selectScheduleByScheduleId(int ScheduleId);
	
}