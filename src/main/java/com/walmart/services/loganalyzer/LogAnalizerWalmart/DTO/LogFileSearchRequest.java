package com.walmart.services.loganalyzer.LogAnalizerWalmart.DTO;

public class LogFileSearchRequest {
    private String file;
    private String date;
    private String keyword;

    public String getFile() {
        return file;
    }

    public String getDate() {
        return date;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
