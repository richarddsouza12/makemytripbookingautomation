package richardenterprises.recources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterterNg {

    public static ExtentReports getReportObject(){

        String path = System.getProperty("user.dir") +"/test-ng-reports/index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test Results");

        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter( reporter );
        extentReports.setSystemInfo("Tester" , "Richard Dsouza");
        return extentReports;

    }
}
