package apitest;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Restapitest {
	String id;
	String token;
	String phonenumber;
	String emailaddress;
	ExtentSparkReporter spark;
	ExtentReports report;
	ExtentTest test;
	@BeforeTest
	public void startup()
	{
		RestAssured.baseURI="https://dev.mantramedic.com";
		//String reportPath = System.getProperty("user.dir") + "/test-output/extentreport.html";
		spark = new ExtentSparkReporter("test-output/extentreport.html");
		report=new ExtentReports();
		report.attachReporter(spark);
	    spark.config().setDocumentTitle(" Rest Assured Api Automation");
        spark .config().setTheme(Theme.DARK);
        spark.config().setReportName("Rest Assured Api Automation");
        spark.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
		
	}
	@BeforeMethod
	public void createtest(Method method)
	{
		test=report.createTest(method.getName());
	}
	
	@Test(priority = 1 )
	public void customerlogin()
	{
		
		
		Pojofile2 pro=new Pojofile2("9748049729", "6415", "en");
		Response response=RestAssured.
				given()
				.header("Content-Type","application/json")
				.body(pro)
				.when()
				.post("/api/customer/Login")
				.then()
				.statusCode(200)
				.log().all()
				.extract().response();
		
		System.out.println("Respone body :"+response.toString());
		id=response.jsonPath().getString("data.id");
		System.out.println(" extracted id here : "+id);
		token=response.jsonPath().getString("token");
		System.out.println(" extracted id here : "+token);
		phonenumber=response.jsonPath().getString("data.phone_number");
		System.out.println("Extracted phonenumber :-"+ phonenumber);
		emailaddress=response.jsonPath().getString("data.email_address");
		System.out.println("Extracted email address:- "+ emailaddress);
		
		
	}
	@AfterMethod
	public void resultstatus(ITestResult result)throws IOException
	
	{
		if(result.getStatus()==ITestResult.SUCCESS)
		{
			test.log(Status.PASS, result.getMethod().getMethodName() + " PASSED");
			test.log(Status.PASS, result.getThrowable());
		}
		else if (result.getStatus()==ITestResult.FAILURE) {
			test.log(Status.FAIL, result.getMethod().getMethodName() + " FAILED");
			test.log(Status.FAIL, result.getThrowable());
			
		}
		else
		{
			test.log(Status.SKIP, result.getMethod().getMethodName() + " SKIPPED");
			test.log(Status.SKIP,result.getThrowable());
		}
		
	}
	@AfterClass
	public void reportend()
	{
		report.flush();
	}
	
	
	

}
