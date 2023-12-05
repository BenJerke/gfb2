package dev.ben.model;

import org.springframework.stereotype.Component;

@Component
public class WorkPeriod {


    // Within a session, a user can work on multiple tasks. Each task has a start and end time.
    // A work period represents such an instance. We'll create one for each distinct instance that a user works on a task.
    private int sessionId;
    private int taskId;
    private int statusId;
    private long startTimeMilliseconds;
    private long endTimeMilliseconds;

    public WorkPeriod(){

    }
    public WorkPeriod(int sessionId, int taskId, int statusId, long startTimeMilliseconds, long endTimeMilliseconds) {
        this.sessionId = sessionId;
        this.taskId = taskId;
        this.statusId = statusId;
        this.startTimeMilliseconds = startTimeMilliseconds;
        this.endTimeMilliseconds = endTimeMilliseconds;
    }


    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
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
}
