package basicAppium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BasicContact {

    AppiumDriver driver;
    @BeforeEach
    public void configure() throws MalformedURLException {
        DesiredCapabilities capabilities= new DesiredCapabilities();
        capabilities.setCapability("deviceName","EynarH");
        capabilities.setCapability("platformVersion","9.0");
        capabilities.setCapability("appPackage","com.android.contacts");
        capabilities.setCapability("appActivity","com.android.contacts.activities.PeopleActivity");
        capabilities.setCapability("platformName","Android");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        // implicit waits
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }
    @AfterEach
    public void closeApp(){

        //driver.quit();
    }

    @Test
    public void verifyCalculator() throws InterruptedException {
        // click +
        driver.findElement(By.id("com.android.contacts:id/hw_fab")).click();
        // set name
        driver.findElement(By.xpath("//android.widget.EditText[@text='Name']")).sendKeys("Remove");
        //set phone
        driver.findElement(By.xpath("//android.widget.EditText[@text='Phone number']")).sendKeys("2222222");
        // click save
        driver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"Done\"]")).click();
        // verification
        String expectedResult="Remove";
        String actualResult=driver.findElement(By.id("com.android.contacts:id/name")).getText();
        Assertions.assertEquals(expectedResult,actualResult,"ERROR!! no se creo el contacto");
    }
}
