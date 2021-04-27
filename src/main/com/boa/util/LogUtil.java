package com.boa.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class LogUtil {
    private BufferedWriter bufferedWriter = null;

    ZonedDateTime date = ZonedDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyyHHMMSS");
    String fileNameFormat = date.format(formatter);

    public void createLogFile() {
        try {
            File dir = new File("./src/resources/logs/");
            if (!dir.exists()) {
                dir.mkdir();
            }
            //Create File
            File logFile = new File(dir + "/" + fileNameFormat + ".log");
            FileWriter fileWriter = new FileWriter(logFile.getAbsoluteFile());
            bufferedWriter = new BufferedWriter(fileWriter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void write(String message) {
        try {
            bufferedWriter.write(message);
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
