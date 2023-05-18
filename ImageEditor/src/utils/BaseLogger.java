package utils;


	import java.io.FileWriter;
	import java.io.IOException;
	import java.io.PrintWriter;
	import java.text.SimpleDateFormat;
	import java.util.Date;

	public class BaseLogger {
	    private static final String INFO_LOG_FILE = "application_info.txt";
	    private static final String ERROR_LOG_FILE = "application_error.txt";
	 
	    private String logFile;

	    private BaseLogger(String logFile) {
	        this.logFile = logFile;
	    }

	    public static BaseLogger info() {
	        return new BaseLogger(INFO_LOG_FILE);
	    }

	    public static BaseLogger error() {
	        return new BaseLogger(ERROR_LOG_FILE);
	    }

	    public void log(String message) {
	        String logEntry = getLogEntry(message);
	        writeLogEntry(logEntry);
	    }

	    private String getLogEntry(String message) {
	        String timeStamp = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy").format(new Date());
	        String logLevel = logFile.equals(INFO_LOG_FILE) ? "[INFO]" : "[ERROR]";
	        return "[" + timeStamp + "]" + logLevel + " " + message;
	    }

	    private void writeLogEntry(String logEntry) {
	        try (PrintWriter writer = new PrintWriter(new FileWriter(logFile, true))) {
	            writer.println(logEntry);
	        } catch (IOException e) {
	            System.out.println("Failed to write log entry to file: " + logFile);
	            e.printStackTrace();
	        }
	    }

	    public void logFilterExecution(String filterName, String fileName, long executionTime) {
	        String message = filterName + " applied to " + fileName + ", took: " + executionTime + "ms";
	        log(message);
	    }

	    public void logLogin(String username) {
	        String message = "User " + username + " logged in";
	        log(message);
	    }

	    public void logImageRead(String fileName) {
	        String message = "Read image file: " + fileName;
	        log(message);
	    }

	    public void logImageWrite(String fileName) {
	        String message = "Write image file: " + fileName;
	        log(message);
	    }
	}


