package com.walmart.services.loganalyzer.LogAnalizerWalmart.Controller;

import com.walmart.services.loganalyzer.LogAnalizerWalmart.Model.LogRequest;
import com.walmart.services.loganalyzer.LogAnalizerWalmart.Service.LogAnalyzerLiveService;
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
@RequestMapping("/websphare/live/log")
public class LogAnalyzerLiveController {
    @Autowired
    private LogAnalyzerLiveService logAnalyzerLiveService;

    @GetMapping
    public String showForm(){
        return "liveLog";
    }

    @PostMapping("/keyword")
    public String getLogsByKeyword(LogRequest logRequest, Model model) throws IOException {
        List<String> logs = logAnalyzerLiveService.filteredByKeyword(logRequest);
        String logsFormated = formatLogs(logs);
        model.addAttribute("logs", logsFormated);
        return "logResults";
    }

    @PostMapping("/keyword-date")
    public String getLogsByKeywordAndDate(LogRequest logRequest, Model model) throws IOException {
        System.out.println(logRequest.getDate() +" "+logRequest.getKeyword()+" LLega aqui");
        List<String> logs = logAnalyzerLiveService.filteredByKeywordAndDate(logRequest);
        String logsFormated = formatLogs(logs);
        model.addAttribute("logs", logsFormated);
        return "logResults";
    }

    @PostMapping("/by-date")
    public String getLogsByDate(LogRequest logRequest, Model model) throws IOException {
        System.out.println(logRequest.getDate() +" "+logRequest.getKeyword()+" LLega aqui");
        List<String> logs = logAnalyzerLiveService.filteredByDate(logRequest);
        String logsFormated = formatLogs(logs);
        model.addAttribute("logs", logsFormated);
        return "logResults";
    }

    private String formatLogs(List<String> logs){
        return logs.stream().collect(Collectors.joining("\r\n"));
    }
}