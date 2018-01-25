package iosr.facebookapp.stats.logging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UpdatePostTimeLog {
    private long updatePostTime;

    public UpdatePostTimeLog(long updatePostTime) {
        this.updatePostTime = updatePostTime;
    }

    public long getUpdatePostTime() {
        return updatePostTime;
    }

    public void setUpdatePostTime(long updatePostTime) {
        this.updatePostTime = updatePostTime;
    }
}
