package appium04.mi_1er_proyecto_appium;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;


public class Test_Zeus {

	private static AndroidDriver driver;

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		
		// CAPABILITIES
		DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("udid", "ab1ced97");
        capabilities.setCapability(CapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability("platformVersion", "11");
        capabilities.setCapability("appium:adbExecTimeout", 60000);
        capabilities.setCapability("appium:appPackage", "com.upax.zeusgeneric");
        capabilities.setCapability("appium:appActivity", "com.upax.zeusgeneric.uimodules.splash.ZGSplashActivity");
              
              
        //CREAR DRIVER ANDROID
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		
        Thread.sleep(5000);
        
        WebElement txtname = driver.findElement(By.id("com.upax.zeusgeneric:id/zlo_edittext_name"));
		txtname.sendKeys("usuario_test");
		
		WebElement txtmail = driver.findElement(By.id("com.upax.zeusgeneric:id/zlo_edittext_email"));
		txtmail.sendKeys("usuario_test@gmail.com");
		
		WebElement txtpass = driver.findElement(By.id("com.upax.zeusgeneric:id/zlo_edittext_password"));
		txtpass.sendKeys("u1234587");
		
		WebElement txtbutton = driver.findElement(By.id("com.upax.zeusgeneric:id/zlo_button_login_guest"));
		txtbutton.click();
		
        
        //Cerrar Driver
        driver.quit();
	}

}


