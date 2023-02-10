
package mx.testng.Skandia;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.sql.Driver;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

public class Acceso {

	private WebDriver driver;
	private String baseUrl;
	private com.relevantcodes.extentreports.ExtentReports reports;
	private ExtentTest test;

	@Test
	public void Bloqueo() throws IOException, InterruptedException {

		driver.get(baseUrl);	
		Thread.sleep(3000);
		test.log(LogStatus.PASS, "URL cargada" + test.addScreenCapture(captureScreen()));
		driver.findElement(By.xpath("//*[@class='transButton']")).click();
		
		handleTabs();
		
		test.log(LogStatus.PASS, "Cambio de Pestaña" + test.addScreenCapture(captureScreen()));

		driver.findElement(By.xpath("//img[@src='Resources/personalPlan.png' and @class='float-left']")).click();
		Thread.sleep(3000);
		test.log(LogStatus.PASS, "Clic en el Boton" + test.addScreenCapture(captureScreen()));
		
		driver.findElement(By.xpath("//*[@type='button' and @class='btn btn-success rounded-pill btn-sk padding-button btnInvitation']")).click();
		Thread.sleep(3000);
		test.log(LogStatus.PASS, "Ventana Emergente" + test.addScreenCapture(captureScreen()));
		
		
		driver.findElement(By.xpath("//a[text()='¿Olvidaste tu contraseña?']")).click();
		Thread.sleep(3000);
		test.log(LogStatus.PASS, "Clic en link" + test.addScreenCapture(captureScreen()));
		
		driver.findElement(By.id("username")).click();
		Thread.sleep(3000);		


		driver.findElement(By.id("username")).sendKeys("lealvarez.aquo@gmail.com");
		Thread.sleep(3000);
		test.log(LogStatus.PASS, "Captura del Mail" + test.addScreenCapture(captureScreen()));
		
		driver.findElement(By.xpath("//input[@class='pf-c-button pf-m-primary pf-m-block btn-lg fontNormal normal-text button_login' and @value='Enviar']")).click();
		Thread.sleep(4000);	
		test.log(LogStatus.PASS, "Envio del Mail" + test.addScreenCapture(captureScreen()));
		
		driver.findElement(By.xpath("//input[@id='dnn_ctr731_MessageErrorSolvencia_btnContinuar']")).click();
		Thread.sleep(3000);
		test.log(LogStatus.PASS, "Flujo Terminado" + test.addScreenCapture(captureScreen()));
		
	}

	

	@BeforeTest
	public void beforeTest() {

		System.setProperty("webdriver.chrome.driver", "./resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		baseUrl = "https://www.skandia.com.mx/";
		driver.manage().window().maximize();
		reports = new com.relevantcodes.extentreports.ExtentReports("C:\\Users\\rulax\\Desktop\\Test1.html", true);

		test = reports.startTest("Flujo Acceso");

	}

	@AfterTest
	public void afterTest() {

		reports.endTest(test);
		reports.flush();
		driver.quit();

	}
	
	
	//Metodo para cambio de Pestaña	
	public void handleTabs()throws InterruptedException{
		
		String mainTab= driver.getWindowHandle();
		String newTab="";
		
		Set<String> habdles=driver.getWindowHandles();
		for(String actual:habdles) {
			
			if (!actual.equalsIgnoreCase(mainTab)) {
				System.out.println(" ----- Cambio de Pestaña---");
				driver.switchTo().window(actual);
				newTab= actual;
			}
			
		
		}
		
	}
	

	// Metodo para obtener la fecha y hora actual del sistema
	public String getcurrentdateandtime() {
		String str = null;

		try {
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SSS");
			Date date = new Date();
			str = dateFormat.format(date);
			str = str.replace(" ", "").replaceAll("/", "").replaceAll(":", "");
		} catch (Exception e) {

		}
		return str;
	}

	// Metodo para tomar captura de pantalla
	public String captureScreen() throws IOException {
		TakesScreenshot screen = (TakesScreenshot) driver;
		File src = screen.getScreenshotAs(OutputType.FILE);
		String dest = "C:\\Users\\rulax\\Desktop" + getcurrentdateandtime() + ".png";
		File target = new File(dest);
		FileUtils.copyFile(src, target);
		return dest;
	}

}
