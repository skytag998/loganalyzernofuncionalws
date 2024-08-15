package com.walmart.services.loganalyzer.LogAnalizerWalmart.Controller;

import com.walmart.services.loganalyzer.LogAnalizerWalmart.DTO.LogFileSearchRequest;
import com.walmart.services.loganalyzer.LogAnalizerWalmart.Service.LogAnalyzerFilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/websphare/logs/files")
public class LogAnalyzerFilesController {

    @Autowired
    private LogAnalyzerFilesService logAnalyzerFilesService;

    @GetMapping
    public String showForm(Model model) throws IOException {
        List<String> files = logAnalyzerFilesService.getFileList();
        model.addAttribute("files", files);
        return "fileLog";
    }

    @PostMapping("/keyword")
    public String getLogsByFileAndKeyword (LogFileSearchRequest logFileSearchRequest,Model model) throws IOException {
        List<String> logs = logAnalyzerFilesService.findByKeyword(logFileSearchRequest);
        String logsFormated = formatLogs(logs);
        model.addAttribute("logs",logsFormated);
        return "logResults";
    }

    @PostMapping("/keyword-date")
    public String getLogsByFileKeywordAndDate (LogFileSearchRequest logFileSearchRequest,Model model) throws IOException {
        List<String> logs = logAnalyzerFilesService.findByDateAndKeyword(logFileSearchRequest);
        String logsFormated =formatLogs(logs);
        model.addAttribute("logs",logsFormated);
        return "logResults";
    }

    @PostMapping("/date")
    public String getLogsByFileAndDate (LogFileSearchRequest logFileSearchRequest,Model model) throws IOException {
        List<String> logs = logAnalyzerFilesService.findByDate(logFileSearchRequest);
        String logsFormated = formatLogs(logs);
        model.addAttribute("logs",logsFormated);
        return "logResults";
    }
    private String formatLogs(List<String> logs ){
        return logs.stream().collect(Collectors.joining("\r\n"));
    }


}
