package com.walmart.services.loganalyzer.LogAnalizerWalmart.Controller;

import com.walmart.services.loganalyzer.LogAnalizerWalmart.Service.LogAnalyzerTimeLapseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping ("/websphare/live/log/timelapse")
public class LogAnalyzerTimeLapseController {
    @Autowired
    private LogAnalyzerTimeLapseService logAnalyzerTimeLapseService;
    @GetMapping
    public String showForm(){
        return "timeLapseLog";
    }

    @GetMapping("/last-minutes")
    public String getLpgsByLastMinutes (@RequestParam("minutes") int minutes, Model model) throws IOException {
        List<String> logs = logAnalyzerTimeLapseService.getByLastMinutes(minutes);
        String formatedLogs = formatLogs(logs);
        model.addAttribute("logs",formatedLogs);
        return "logResults";
    }

    private String formatLogs(List<String> logs){
        return logs.stream().collect(Collectors.joining("\r\n"));
    }
}
