package com.walmart.services.loganalyzer.LogAnalizerWalmart.DTO;

import lombok.Data;

import java.util.List;

@Data
public class LogResponse {
    private List<String> lines;

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

}
