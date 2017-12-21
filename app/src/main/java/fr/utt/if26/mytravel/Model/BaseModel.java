package fr.utt.if26.mytravel.Model;

/**
 * Created by paf on 12/11/17.
 */

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class BaseModel {
    private int id;
    private long createdAt;
    private long updatedAt;

    public BaseModel() {
        long currentTime = System.currentTimeMillis();
        createdAt = currentTime;
        updatedAt = currentTime;
    }

    public BaseModel(long createdAt_pf, long updatedAt_pf) {
        createdAt = createdAt_pf;
        updatedAt = updatedAt_pf;
    }

    public String getCreatedAtFormat() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        String date = sdf.format(new Date(this.getCreatedAt()));
        return date;
    }

    public String getUpdatedAtFormat() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        String date = sdf.format(new Date(this.getCreatedAt()));
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }
}
