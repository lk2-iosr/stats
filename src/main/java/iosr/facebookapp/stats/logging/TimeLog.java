package iosr.facebookapp.stats.logging;

public class TimeLog {

    private String eventType;
    private long time;

    public TimeLog(String eventType, long time) {
        this.eventType = eventType;
        this.time = time;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
