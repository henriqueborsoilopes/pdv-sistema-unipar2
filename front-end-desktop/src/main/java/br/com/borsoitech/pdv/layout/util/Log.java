package br.com.borsoitech.pdv.layout.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {

    private static final String LOG_DIRECTORY = "log";
    private static final String LOG_FILE_NAME = "log_operacao.txt";
    private static final File logFile = new File(LOG_DIRECTORY, LOG_FILE_NAME);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Log() {
        createLogFileIfNotExists();
    }

    private void createLogFileIfNotExists() {
        try {
            if (!logFile.exists()) {
                File logDir = new File(LOG_DIRECTORY);
                if (!logDir.exists()) {
                    logDir.mkdirs();
                }
                logFile.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void log(String level, String message, String status) {
        String timestamp = dateFormat.format(new Date());
        String logMessage = String.format("%s %-5s - %s - %s", timestamp, level, message, status);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) {
            writer.write(logMessage);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logInfo(String message, String status) {
        log("INFO", message, status);
    }

    public void logDebug(String message, String status) {
        log("DEBUG", message, status);
    }

    public void logError(String message, String status) {
        log("ERROR", message, status);
    }
}

