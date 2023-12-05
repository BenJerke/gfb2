package dev.ben.model;
import org.springframework.stereotype.Component;

import java.util.List;
//

@Component
public class Session {
    private int id;
    private int userId;
    private String notes;
    private long startTimeMilliseconds;
    private long endTimeMilliseconds;
    private List<WorkPeriod> workPeriods;

    public List<WorkPeriod> getWorkPeriods() {
        return workPeriods;
    }

    public void setWorkPeriods(List<WorkPeriod> workPeriods) {
        this.workPeriods = workPeriods;
    }


    public Session (){

    };

    public Session(int id, int userId, String notes, long startTimestamp, List<WorkPeriod> workPeriods) {
        this.id = id;
        this.userId = userId;
        this.notes = notes;
        this.startTimeMilliseconds = startTimestamp;
        this.workPeriods = workPeriods;
    }

    public Session(int id, int userId, String notes, long startTimestamp, long endTimestamp) {
        this.id = id;
        this.userId = userId;
        this.notes = notes;
        this.startTimeMilliseconds = startTimestamp;
        this.endTimeMilliseconds = endTimestamp;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public long getStartTimeMilliseconds() {
        return startTimeMilliseconds;
    }

    public void setStartTimeMilliseconds(long startTimeMilliseconds) {
        this.startTimeMilliseconds = startTimeMilliseconds;
    }

    public long getEndTimeMilliseconds() {
        return endTimeMilliseconds;
    }

    public void setEndTimeMilliseconds(long endTimeMilliseconds) {
        this.endTimeMilliseconds = endTimeMilliseconds;
    }

    public long getSessionDuration(){
        return this.endTimeMilliseconds - this.startTimeMilliseconds;
    }

}
