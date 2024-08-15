package com.walmart.services.loganalyzer.LogAnalizerWalmart.Service;

import com.walmart.services.loganalyzer.LogAnalizerWalmart.utils.DateLogUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
@Service
public class LogAnalyzerTimeLapseService {

private static final Logger LOGGER = LoggerFactory.getLogger(LogAnalyzerTimeLapseService.class);

    @Value("${log.file.path}")
    private String logFilePath ;

    public List<String> getByLastMinutes (int minutes) throws IOException {
        List<String> lines = readLog();
        List<String> logReturn = new ArrayList<>();
        String now = DateLogUtils.extractDateTime(getLastLine());
        String minutesAgo = DateLogUtils.getMinutesAgo(now,minutes);
        for (String  line : lines){
            String extractedDate = DateLogUtils.extractDateTime(line);
            if(DateLogUtils.isDateWithinRange(extractedDate,minutesAgo,now)){
                logReturn.add(line);
                System.out.println(line);
            }
        }
        logReturn.sort(Comparator.comparing(line ->DateLogUtils.extractDateTime((String) line)).reversed());
        return  logReturn;
    }



    private List<String> readLog () throws IOException{
        List<String> lines = new ArrayList<>();
        try(RandomAccessFile randomAccessFile = new RandomAccessFile(logFilePath,"r")){
            String line;
            while ((line = randomAccessFile.readLine()) != null){
                lines.add(line);
            }
        }catch (IOException e){
            LOGGER.error("Error al leer el archivo:"+logFilePath, e);
        }
        return lines;
    }

    private  String getLastLine () throws IOException {
        List <String> lines  = readLog();
        String finalLine ="";
        for (String line : lines){
            finalLine= line;
        }
        return finalLine;
    }

}
