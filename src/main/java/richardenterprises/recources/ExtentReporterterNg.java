package richardenterprises.recources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReporterterNg {

    public static ExtentReports getReportObject( String dateTimeStamp ) {

        String path = System.getProperty("user.dir") +"/test-ng-reports/" + dateTimeStamp +"/index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter( path );
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test Results");

        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter( reporter );
        extentReports.setSystemInfo("Tester" , "Richard Dsouza");
        return extentReports;

    }

}
