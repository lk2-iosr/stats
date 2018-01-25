package iosr.facebookapp.stats.logging;

public class SavePostTimeLog {
    private long savePostTime;

    public SavePostTimeLog(long savePostTime) {
        this.savePostTime = savePostTime;
    }

    public long getSavePostTime() {
        return savePostTime;
    }

    public void setSavePostTime(long savePostTime) {
        this.savePostTime = savePostTime;
    }
}
