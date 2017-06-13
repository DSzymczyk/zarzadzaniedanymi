package com.agh.data;

import java.util.Date;

/**
 * Created by Dawid on 11.06.2017.
 */
public class FileProxy {
    private Long id;

    private String filename;

    private Date date;

    private boolean valid;

    public FileProxy(Long id, String filename, Date date, boolean valid) {
        this.id = id;
        this.filename = filename;
        this.date = date;
        this.valid = valid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }


}
